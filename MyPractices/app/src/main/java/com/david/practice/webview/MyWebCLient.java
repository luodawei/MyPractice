package com.david.practice.webview;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2016/11/21.
 */
public class MyWebCLient extends WebViewClient {
    //执行页面加载 之前的回调
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }
    //页面开始加载的监听
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }
    //页面加载结束的监听，此时页面加载完成，但图片可能还在加载中
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }
    //加载网络资源的时候调用
    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }
    //通知主机应用程序webView内容遗留下来的前一页导航将不在
    @Override
    public void onPageCommitVisible(WebView view, String url) {
        super.onPageCommitVisible(view, url);
    }
    //应用程序加载访问页面而返回得到的数据
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }
    //访问某个页面返回错误时回调
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
    }
}
