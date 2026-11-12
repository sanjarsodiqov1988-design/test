package uz.nextgen.dsptest;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView web;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        web = new WebView(this);
        web.setBackgroundColor(Color.parseColor("#0f172a"));
        setContentView(web);
        WebSettings ws = web.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView v, String url, String msg, final JsResult r) {
                new AlertDialog.Builder(MainActivity.this)
                    .setMessage(msg)
                    .setPositiveButton("OK", (d, w) -> r.confirm())
                    .setOnCancelListener(d -> r.cancel())
                    .show();
                return true;
            }
        });
        if (s == null) web.loadUrl("file:///android_asset/index.html");
        else web.restoreState(s);
    }

    @Override
    protected void onSaveInstanceState(Bundle o) {
        super.onSaveInstanceState(o);
        web.saveState(o);
    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) web.goBack(); else super.onBackPressed();
    }
}
