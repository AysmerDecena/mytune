package org.aysmer.mytune;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceError;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public final class MainActivity extends Activity {
    private static final String HOME = "https://aysmerdecena.github.io/mytune/";
    private WebView webView;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setSupportMultipleWindows(false);
        webView.setWebViewClient(new WebViewClient() {
            @Override public void onPageFinished(WebView view, String url) {
                if (isMyTune(Uri.parse(url))) {
                    view.evaluateJavascript("window.open=function(url){window.location.href=String(url);return null;};", null);
                }
            }

            @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                if (isYoutubeWatch(uri)) {
                    openVideoApp(uri);
                    return true;
                }
                if (isMyTune(uri) || isYoutubeEmbed(uri)) return false;
                return true;
            }

            @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                if (isYoutubeWatch(uri)) {
                    openVideoApp(uri);
                    return true;
                }
                return (isMyTune(uri) || isYoutubeEmbed(uri)) ? false : true;
            }
        });
        webView.setWebChromeClient(new android.webkit.WebChromeClient());
        setContentView(webView);
        if (state == null) webView.loadUrl(HOME);
        else webView.restoreState(state);
    }

    private boolean isYoutubeHost(Uri uri) {
        String host = uri.getHost();
        return "youtube.com".equalsIgnoreCase(host)
            || (host != null && host.toLowerCase().endsWith(".youtube.com"))
            || "youtu.be".equalsIgnoreCase(host);
    }

    private boolean isYoutubeWatch(Uri uri) {
        if (!"https".equalsIgnoreCase(uri.getScheme()) || !isYoutubeHost(uri)) return false;
        String host = uri.getHost();
        return ("youtu.be".equalsIgnoreCase(host) && uri.getPathSegments().size() == 1)
            || ("/watch".equals(uri.getPath()) && uri.getQueryParameter("v") != null);
    }

    private boolean isYoutubeEmbed(Uri uri) {
        return "https".equalsIgnoreCase(uri.getScheme())
            && "youtube.com".equalsIgnoreCase(uri.getHost())
            && uri.getPath() != null && uri.getPath().startsWith("/embed/");
    }

    private boolean isMyTune(Uri uri) {
        return "https".equalsIgnoreCase(uri.getScheme())
            && "aysmerdecena.github.io".equalsIgnoreCase(uri.getHost())
            && (uri.getPath() == null || uri.getPath().startsWith("/mytune"));
    }

    private void openVideoApp(Uri uri) {
        Intent view = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(Intent.createChooser(view, "Open video with"));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(this, "No app can open this video. Install NewPipe or YouTube.", Toast.LENGTH_LONG).show();
        }
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        webView.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }

    @Override protected void onDestroy() {
        if (webView != null) webView.destroy();
        super.onDestroy();
    }
}
