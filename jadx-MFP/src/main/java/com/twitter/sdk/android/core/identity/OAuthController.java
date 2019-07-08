package com.twitter.sdk.android.core.identity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthException;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.oauth.OAuth1aService;
import com.twitter.sdk.android.core.internal.oauth.OAuthResponse;

class OAuthController implements Listener {
    /* access modifiers changed from: private */
    public final TwitterAuthConfig authConfig;
    final Listener listener;
    /* access modifiers changed from: private */
    public final OAuth1aService oAuth1aService;
    TwitterAuthToken requestToken;
    private final ProgressBar spinner;
    /* access modifiers changed from: private */
    public final WebView webView;

    interface Listener {
        void onComplete(int i, Intent intent);
    }

    OAuthController(ProgressBar progressBar, WebView webView2, TwitterAuthConfig twitterAuthConfig, OAuth1aService oAuth1aService2, Listener listener2) {
        this.spinner = progressBar;
        this.webView = webView2;
        this.authConfig = twitterAuthConfig;
        this.oAuth1aService = oAuth1aService2;
        this.listener = listener2;
    }

    /* access modifiers changed from: 0000 */
    public void startAuth() {
        Twitter.getLogger().d("Twitter", "Obtaining request token to start the sign in flow");
        this.oAuth1aService.requestTempToken(newRequestTempTokenCallback());
    }

    /* access modifiers changed from: 0000 */
    public Callback<OAuthResponse> newRequestTempTokenCallback() {
        return new Callback<OAuthResponse>() {
            public void success(Result<OAuthResponse> result) {
                OAuthController.this.requestToken = ((OAuthResponse) result.data).authToken;
                String authorizeUrl = OAuthController.this.oAuth1aService.getAuthorizeUrl(OAuthController.this.requestToken);
                Twitter.getLogger().d("Twitter", "Redirecting user to web view to complete authorization flow");
                OAuthController oAuthController = OAuthController.this;
                oAuthController.setUpWebView(oAuthController.webView, new OAuthWebViewClient(OAuthController.this.oAuth1aService.buildCallbackUrl(OAuthController.this.authConfig), OAuthController.this), authorizeUrl, new OAuthWebChromeClient());
            }

            public void failure(TwitterException twitterException) {
                Twitter.getLogger().e("Twitter", "Failed to get request token", twitterException);
                OAuthController.this.handleAuthError(1, new TwitterAuthException("Failed to get request token"));
            }
        };
    }

    /* access modifiers changed from: protected */
    public void handleAuthError(int i, TwitterAuthException twitterAuthException) {
        Intent intent = new Intent();
        intent.putExtra("auth_error", twitterAuthException);
        this.listener.onComplete(i, intent);
    }

    /* access modifiers changed from: 0000 */
    public void setUpWebView(WebView webView2, WebViewClient webViewClient, String str, WebChromeClient webChromeClient) {
        WebSettings settings = webView2.getSettings();
        settings.setAllowFileAccess(false);
        settings.setJavaScriptEnabled(false);
        settings.setSaveFormData(false);
        webView2.setVerticalScrollBarEnabled(false);
        webView2.setHorizontalScrollBarEnabled(false);
        webView2.setWebViewClient(webViewClient);
        webView2.loadUrl(str);
        webView2.setVisibility(4);
        webView2.setWebChromeClient(webChromeClient);
    }

    private void handleWebViewSuccess(Bundle bundle) {
        Twitter.getLogger().d("Twitter", "OAuth web view completed successfully");
        if (bundle != null) {
            String string = bundle.getString("oauth_verifier");
            if (string != null) {
                Twitter.getLogger().d("Twitter", "Converting the request token to an access token.");
                this.oAuth1aService.requestAccessToken(newRequestAccessTokenCallback(), this.requestToken, string);
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Failed to get authorization, bundle incomplete ");
        sb.append(bundle);
        Twitter.getLogger().e("Twitter", sb.toString(), null);
        handleAuthError(1, new TwitterAuthException("Failed to get authorization, bundle incomplete"));
    }

    /* access modifiers changed from: 0000 */
    public Callback<OAuthResponse> newRequestAccessTokenCallback() {
        return new Callback<OAuthResponse>() {
            public void success(Result<OAuthResponse> result) {
                Intent intent = new Intent();
                OAuthResponse oAuthResponse = (OAuthResponse) result.data;
                intent.putExtra("screen_name", oAuthResponse.userName);
                intent.putExtra("user_id", oAuthResponse.userId);
                intent.putExtra("tk", oAuthResponse.authToken.token);
                intent.putExtra("ts", oAuthResponse.authToken.secret);
                OAuthController.this.listener.onComplete(-1, intent);
            }

            public void failure(TwitterException twitterException) {
                Twitter.getLogger().e("Twitter", "Failed to get access token", twitterException);
                OAuthController.this.handleAuthError(1, new TwitterAuthException("Failed to get access token"));
            }
        };
    }

    private void handleWebViewError(WebViewException webViewException) {
        Twitter.getLogger().e("Twitter", "OAuth web view completed with an error", webViewException);
        handleAuthError(1, new TwitterAuthException("OAuth web view completed with an error"));
    }

    private void dismissWebView() {
        this.webView.stopLoading();
        dismissSpinner();
    }

    private void dismissSpinner() {
        this.spinner.setVisibility(8);
    }

    public void onPageFinished(WebView webView2, String str) {
        dismissSpinner();
        webView2.setVisibility(0);
    }

    public void onSuccess(Bundle bundle) {
        handleWebViewSuccess(bundle);
        dismissWebView();
    }

    public void onError(WebViewException webViewException) {
        handleWebViewError(webViewException);
        dismissWebView();
    }
}
