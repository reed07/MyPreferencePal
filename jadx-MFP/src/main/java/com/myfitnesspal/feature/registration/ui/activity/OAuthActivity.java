package com.myfitnesspal.feature.registration.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.android.login.Welcome;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.constants.Constants.Login;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.MfpWebViewClient;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import javax.inject.Inject;

public class OAuthActivity extends Activity {
    private static final String ERROR_UNKNOWN = "unknown";
    private static final String EXTRA_HAS_ATTEMPTED_LOGIN = "has_attempted_login";
    private static final int FAILSAFE_TIMEOUT_MILLIS = 5000;
    private static final boolean VERBOSE_LOGGING_ENABLED = false;
    @Inject
    Lazy<ApiUrlProvider> apiUrlProvider;
    /* access modifiers changed from: private */
    public Runnable errorFailsafe = new Runnable() {
        public void run() {
            OAuthActivity.debug("errorFailsafe.run(): fired", new Object[0]);
            OAuthActivity oAuthActivity = OAuthActivity.this;
            oAuthActivity.finishWithError(oAuthActivity.webViewError);
        }
    };
    private boolean finishedWithError;
    /* access modifiers changed from: private */
    public Handler handler = new Handler();
    private boolean hasAttemptedLogin;
    @Inject
    Lazy<LoginModel> loginModel;
    @Inject
    Lazy<NavigationHelper> navigationHelper;
    /* access modifiers changed from: private */
    public RelativeLayout progressView;
    @Inject
    Lazy<Session> session;
    /* access modifiers changed from: private */
    public WebView webView;
    /* access modifiers changed from: private */
    public WebViewError webViewError = null;

    final class OAuth2JavaScriptInterface {
        OAuth2JavaScriptInterface() {
        }

        @JavascriptInterface
        public void processErrorAndFinish(String str) {
            OAuthActivity.debug("processErrorAndFinish() called: %s", str);
            String[] split = str.trim().toLowerCase().split(":");
            if (split.length == 2 && split[0].equals("error")) {
                OAuthActivity.debug("processErrorAndFinish: more specific error found %s %s", split[0], split[1]);
                OAuthActivity.this.webViewError = new WebViewError(split[0], split[1]);
            }
            if (OAuthActivity.this.webViewError == null) {
                OAuthActivity.debug("processErrorAndFinish: no error?? using UNKNOWN", new Object[0]);
                OAuthActivity.this.webViewError = new WebViewError("unknown", "unknown");
            }
            OAuthActivity oAuthActivity = OAuthActivity.this;
            oAuthActivity.finishWithError(oAuthActivity.webViewError);
        }
    }

    private static final class Params {
        public static final String ACCESS_TYPE = "access_type";
        public static final String ACTION = "action";
        public static final String CLIENT_ID = "client_id";
        public static final String DISPLAY = "display";
        public static final String ERROR = "error";
        public static final String ERROR_DESCRIPTION = "error_description";
        public static final String REDIRECT_URI = "redirect_uri";
        public static final String RESPONSE_TYPE = "response_type";
        public static final String SCOPE = "scope";
        public static final String THIRD_PARTY_SERVICE_ID = "third_party_service_id";
        public static final String THIRD_PARTY_TOKEN = "third_party_token";
        public static final String USERNAME = "username";

        private Params() {
        }
    }

    private static class WebViewError {
        public final String code;
        public final String description;

        public WebViewError(String str, String str2) {
            this.code = str;
            this.description = str2;
        }
    }

    /* access modifiers changed from: private */
    public static void debug(String str, Object... objArr) {
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, OAuthActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        debug("onCreate entered", new Object[0]);
        setContentView(R.layout.oauth2view);
        ((MyFitnessPalApp) getApplication()).component().inject(this);
        initState(bundle);
        initViews();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(EXTRA_HAS_ATTEMPTED_LOGIN, this.hasAttemptedLogin);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        initState(bundle);
    }

    private void initState(Bundle bundle) {
        this.hasAttemptedLogin = BundleUtils.getBoolean(bundle, EXTRA_HAS_ATTEMPTED_LOGIN);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        debug("onResume entered", new Object[0]);
        if (!((Session) this.session.get()).getUser().isLoggedIn()) {
            debug("onResume: user NOT signed in!", new Object[0]);
            if (this.hasAttemptedLogin) {
                debug("onResume: already tried user login, but failed", new Object[0]);
                setResult(0);
                finishWithError(new WebViewError("app_login_failed", AppEventsConstants.EVENT_PARAM_VALUE_YES));
                return;
            }
            debug("onResume: redirecting to login...", new Object[0]);
            this.hasAttemptedLogin = true;
            ((NavigationHelper) this.navigationHelper.get()).withContext(this).withAction(Login.OAUTH2).withIntent(Welcome.newStartIntent(this)).startActivity();
            return;
        }
        debug("onResume: state look OK, loading OAuth page...", new Object[0]);
        setWebViewRequest();
    }

    private void initViews() {
        this.progressView = (RelativeLayout) findViewById(R.id.oAuth2_progressview);
        this.webView = (WebView) findViewById(R.id.oAuth2_webview);
        WebSettings settings = this.webView.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptEnabled(true);
        this.webView.setWebViewClient(new MfpWebViewClient(this) {
            public void onPageFinished(WebView webView, String str) {
                if (OAuthActivity.this.webViewError != null) {
                    OAuthActivity.debug("onPageFinished with error! invoking javascript callback and scheduling the failsafe!", new Object[0]);
                    OAuthActivity.this.handler.postDelayed(OAuthActivity.this.errorFailsafe, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
                    OAuthActivity.this.webView.addJavascriptInterface(new OAuth2JavaScriptInterface(), "oAuth2");
                    OAuthActivity.this.webView.loadUrl("javascript:window.oAuth2.processErrorAndFinish(document.getElementsByTagName('body')[0].innerHTML);");
                    return;
                }
                OAuthActivity.debug("onPageFinished: %s", str);
                OAuthActivity.this.progressView.setVisibility(8);
                OAuthActivity.this.webView.setVisibility(0);
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                OAuthActivity.this.webViewError = null;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                OAuthActivity.debug("shouldOverrideUrlLoading: %s", str);
                if (super.shouldOverrideUrlLoading(webView, str)) {
                    OAuthActivity.debug("shouldOverrideUrlLoading: return true superclass", new Object[0]);
                    return true;
                } else if (str.contains(Strings.toString(OAuthActivity.this.getIntent().getStringExtra("redirect_uri")))) {
                    OAuthActivity.debug("shouldOverrideUrlLoading: return true because redirect valid", new Object[0]);
                    OAuthActivity.this.finishWithSuccess(str);
                    return true;
                } else {
                    OAuthActivity.debug("shouldOverrideUrlLoading: return false (default case)", new Object[0]);
                    return false;
                }
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                OAuthActivity.debug("onReceivedError: code=%s, desc=%s, url=%s", Integer.valueOf(i), str, str2);
                OAuthActivity.this.webViewError = new WebViewError(String.valueOf(i), str);
            }
        });
    }

    private void setWebViewRequest() {
        User user = ((Session) this.session.get()).getUser();
        HashMap hashMap = new HashMap();
        hashMap.put("username", user.getUsername());
        if (((LoginModel) this.loginModel.get()).getFacebookData().isValid()) {
            hashMap.put("third_party_service_id", String.valueOf(1));
            hashMap.put(Params.THIRD_PARTY_TOKEN, ((LoginModel) this.loginModel.get()).getFacebookData().getAccessToken());
        }
        Intent intent = getIntent();
        hashMap.put("client_id", Strings.toString(intent.getStringExtra("client_id")));
        hashMap.put("redirect_uri", Strings.toString(intent.getStringExtra("redirect_uri")));
        hashMap.put("response_type", Strings.toString(intent.getStringExtra("response_type")));
        hashMap.put("scope", Strings.toString(intent.getStringExtra("scope")));
        hashMap.put(Params.ACCESS_TYPE, Strings.toString(intent.getStringExtra(Params.ACCESS_TYPE)));
        hashMap.put("display", Strings.toString(intent.getStringExtra("display")));
        Builder buildUpon = Uri.parse(((ApiUrlProvider) this.apiUrlProvider.get()).getOAuth2Url()).buildUpon();
        for (String str : hashMap.keySet()) {
            buildUpon.appendQueryParameter(str, Strings.toString(hashMap.get(str)));
        }
        String uri = buildUpon.build().toString();
        debug("OAuth URL: %s", uri);
        this.webView.loadUrl(uri, ((ApiUrlProvider) this.apiUrlProvider.get()).getCredentials());
    }

    /* access modifiers changed from: private */
    public void finishWithSuccess(String str) {
        debug("finishWithSuccess(): %s", str);
        setResult(-1, new Intent().setData(Uri.parse(str)));
        finish();
    }

    /* access modifiers changed from: private */
    public void finishWithError(WebViewError webViewError2) {
        this.handler.removeCallbacks(this.errorFailsafe);
        if (this.finishedWithError) {
            debug("finishWithError() finally fired, but was too late! already called!", new Object[0]);
            return;
        }
        this.finishedWithError = true;
        debug("finishWithError(): code=%s error=%s", webViewError2.code, webViewError2.description);
        setResult(-1, new Intent(Strings.toString(getIntent().getStringExtra("action"))).setData(Uri.parse(Strings.toString(getIntent().getStringExtra("redirect_uri"))).buildUpon().appendQueryParameter("error", webViewError2.code).appendQueryParameter("error_description", webViewError2.description).build()));
        finish();
    }
}
