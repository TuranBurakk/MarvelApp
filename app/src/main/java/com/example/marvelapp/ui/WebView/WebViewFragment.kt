package com.example.marvelapp.ui.WebView

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.data.entity.ResultsData
import com.example.marvelapp.databinding.ActivityWebViewBinding
import com.example.marvelapp.utils.gone
import com.example.marvelapp.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewFragment : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityWebViewBinding.inflate(layoutInflater)
            setContentView(binding.root)
            val hero = intent.getStringExtra("id")
            binding.webView.webViewClient = WebViewClient()

        if (hero != null) {
            binding.webView.loadUrl(hero)
        }
            binding.backButtonWebView.setOnClickListener {
               finish()
            }
    }



    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.progressBar.gone()
            binding.webView.show()
        }
    }
    companion object{
        private const val ID = "id"
        fun newIntent(context: Context,url:String) : Intent{
            val intent = Intent(context,WebViewFragment:: class.java)
            intent.putExtra(ID,url)
            return intent
        }
    }
}