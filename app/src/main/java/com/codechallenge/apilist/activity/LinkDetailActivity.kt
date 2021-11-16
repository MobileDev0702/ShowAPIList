package com.codechallenge.apilist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.codechallenge.apilist.R
import com.codechallenge.apilist.util.LoadingIndicator

class LinkDetailActivity : AppCompatActivity() {

    lateinit var loadingIndicator: LoadingIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link_detail)

        initLoadingIndicator()
        loadUrl()
    }

    private fun initLoadingIndicator() {
        loadingIndicator = LoadingIndicator().getInstance()!!
    }

    private fun loadUrl() {
        loadingIndicator.showProgress(this)
        val url = intent.getStringExtra("Link")
        var detailView: WebView = findViewById(R.id.wv_linkdetail)

        detailView.settings.loadsImagesAutomatically = true
        detailView.settings.javaScriptEnabled = true
        detailView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        detailView.loadUrl(url!!)

        detailView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loadingIndicator.hideProgress()
            }
        }
    }
}