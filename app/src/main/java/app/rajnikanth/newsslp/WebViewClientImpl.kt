package app.rajnikanth.newsslp

import android.app.Activity
import android.webkit.WebViewClient
import android.webkit.WebView
import android.content.Intent
import android.net.Uri

class WebViewClientImpl(activity: Activity?) : WebViewClient() {
    private var activity: Activity? = null
    override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
        if (url.indexOf("namastheslp.com") > -1) return false
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity!!.startActivity(intent)
        return true
    }

    init {
        this.activity = activity
    }
}