package com.teamsports.sportsnewspager.sportsnewspager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by HTao on 2015/3/26.
 */
@ContentView(R.layout.activity_web)
public class WebActivity extends Activity {
    @ViewInject(R.id.webView)
    private WebView webView;
    private static String Info = "WebActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        String url = getIntent().getStringExtra("url");
        Log.i(Info, "-----" + url);
        webView.getSettings().setJavaScriptEnabled(true);
        // 让webview对象支持解析alert()等特殊的javascript语句
        webView.setWebChromeClient(new WebChromeClient());
        // 如果不使用该句代码，在点击超链地址后，会跳出程序，而弹出浏览器访问网页。
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
