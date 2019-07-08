package com.twitter.sdk.android.core.identity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.twitter.sdk.android.core.R;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthException;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.internal.TwitterApi;
import com.twitter.sdk.android.core.internal.oauth.OAuth1aService;

public class OAuthActivity extends Activity implements Listener {
    OAuthController oAuthController;
    private ProgressBar spinner;
    private WebView webView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.tw__activity_oauth);
        this.spinner = (ProgressBar) findViewById(R.id.tw__spinner);
        this.webView = (WebView) findViewById(R.id.tw__web_view);
        int i = 0;
        boolean z = bundle != null ? bundle.getBoolean("progress", false) : true;
        ProgressBar progressBar = this.spinner;
        if (!z) {
            i = 8;
        }
        progressBar.setVisibility(i);
        OAuthController oAuthController2 = new OAuthController(this.spinner, this.webView, (TwitterAuthConfig) getIntent().getParcelableExtra("auth_config"), new OAuth1aService(TwitterCore.getInstance(), new TwitterApi()), this);
        this.oAuthController = oAuthController2;
        this.oAuthController.startAuth();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        if (this.spinner.getVisibility() == 0) {
            bundle.putBoolean("progress", true);
        }
        super.onSaveInstanceState(bundle);
    }

    public void onBackPressed() {
        this.oAuthController.handleAuthError(0, new TwitterAuthException("Authorization failed, request was canceled."));
    }

    public void onComplete(int i, Intent intent) {
        setResult(i, intent);
        finish();
    }
}
