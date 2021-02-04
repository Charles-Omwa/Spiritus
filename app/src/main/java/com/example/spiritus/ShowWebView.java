
package com.example.spiritus;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class ShowWebView extends AppCompatActivity {

    //private Button button;
    private WebView webView;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle("Devotion Blog");
        setContentView(R.layout.show_web_view);

        //Get webview 
        webView = (WebView) findViewById(R.id.webView1);

        startWebView("https://www.raystedman.org/daily-devotions?gclid=Cj0KCQiA0-6ABhDMARIsAFVdQv8MimjHpbGLrpyQ1Ttlb1Zhxo_oQtIeQk-fkpn55fCvVFH1L1fI5RsaAjpgEALw_wcB");

    }

    private void startWebView(String url) {

        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        webView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(ShowWebView.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }
            public void onPageFinished(WebView view, String url) {
                try{
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }

        });

        // Javascript inabled on webview
        webView.getSettings().setJavaScriptEnabled(true);


        webView.loadUrl(url);


    }

    // Open previous opened link from history on webview when back button pressed

    @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }

}