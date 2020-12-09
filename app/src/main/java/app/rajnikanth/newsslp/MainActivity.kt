package app.rajnikanth.newsslp

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    var webView: WebView? = null
    var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        imageView = findViewById(R.id.imv_internetconnection)
        webView = findViewById(R.id.webview)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView!!.canGoBack()) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    private fun showNetworkMessage(isConnected: Boolean) {
        if (isConnected) {
            imageView!!.isVisible = false
            webView!!.isVisible = true

            val webSettings = webView!!.settings
            webSettings.javaScriptEnabled = true

            val webViewClient = WebViewClientImpl(this)
            webView!!.webViewClient = webViewClient

            webView!!.loadUrl("https://namastheslp.com")
        } else {
            imageView!!.isVisible = true
            webView!!.isVisible = false
        }
    }
}