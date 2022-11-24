package com.example.marvelapp.ui.WebView

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.databinding.FragmentWebViewBinding
import com.example.marvelapp.utils.gone
import com.example.marvelapp.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewFragment : BaseFragment<FragmentWebViewBinding>(FragmentWebViewBinding::inflate) {

    private val args by navArgs<WebViewFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButtonWebView.setOnClickListener {
            findNavController().navigate(WebViewFragmentDirections.actionWebViewFragmentToTabControllerFragment(args.hero))
        }
        initViews()
    }

    private fun initViews(){
        binding.webView.webViewClient = WebViewClient()
        args.hero.detail?.get(0)?.url?.let { binding.webView.loadUrl(it) }

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
}