package com.myfitnesspal.shared.ui.activity.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.event.BusyEvent;
import com.myfitnesspal.shared.model.FullScreenWebViewIntentExtras;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.util.MfpWebViewChromeClientWithProgress;
import com.myfitnesspal.shared.util.MfpWebViewClient;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

public class FullScreenWebView extends MfpActivity {
    protected static final String EXTRA_WEB_VIEW_INTENT_EXTRAS = "web_view_intent_extras";
    private static final String MAIL_TO = "mailto:";
    @Inject
    Lazy<ApiUrlProvider> apiUrlProvider;
    protected WebView browser;
    /* access modifiers changed from: private */
    public boolean clearHistoryAfterLoad;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    protected TextView offlineMessage;
    private MfpWebViewChromeClientWithProgress webViewChromeClient;
    private WebViewClient webViewClient;
    /* access modifiers changed from: private */
    public FullScreenWebViewIntentExtras webViewIntentExtras;

    protected class Client extends MfpWebViewClient {
        public Client(Activity activity) {
            super(activity);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            FullScreenWebView.this.getMessageBus().post(new BusyEvent(1, true));
            FullScreenWebView.this.onPageLoaded(webView, str);
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            FullScreenWebView.this.getMessageBus().post(new BusyEvent(1, false));
            if (FullScreenWebView.this.clearHistoryAfterLoad) {
                FullScreenWebView.this.clearHistoryAfterLoad = false;
                webView.clearHistory();
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.startsWith(FullScreenWebView.MAIL_TO)) {
                Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(str));
                if (intent.resolveActivity(FullScreenWebView.this.getPackageManager()) != null) {
                    FullScreenWebView.this.getNavigationHelper().withIntent(intent).startActivity();
                } else {
                    Ln.e("No app found that can handle mailto: ", new Object[0]);
                }
                return true;
            } else if (super.shouldOverrideUrlLoading(webView, str)) {
                return true;
            } else {
                if (!FullScreenWebView.this.webViewIntentExtras.handleAllClicksExternally()) {
                    return false;
                }
                FullScreenWebView.this.getNavigationHelper().withIntent(SharedIntents.newUriIntent(str)).startActivity();
                return true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPageLoaded(WebView webView, String str) {
    }

    public static Intent newStartIntentForWeeklyDigest(Context context, String str, boolean z, String str2) {
        return startIntent(context, str, z, str2);
    }

    public static Intent newStartIntentForYearInReview(Context context, String str, boolean z, String str2) {
        return startIntent(context, str, z, str2);
    }

    public static Intent newStartIntentForSponsor(Context context, String str, String str2) {
        return newStartIntent(context, new FullScreenWebViewIntentExtras().setTitle(str).setUrl(str2));
    }

    public static Intent newStartIntentForSponsoredFood(Context context, String str, String str2) {
        return newStartIntent(context, new FullScreenWebViewIntentExtras().setTitle(str).setUrl(str2));
    }

    public static Intent newStartIntentForGenericWebView(Context context, String str, String str2) {
        return newStartIntent(context, new FullScreenWebViewIntentExtras().setUrl(str2).setHandleAllClicksExternally(true).setShowCloseAsBackButton(true).setWebViewKeyToLaunch(str));
    }

    public static Intent newStartIntent(Context context, String str, String str2, boolean z, boolean z2, boolean z3) {
        return newStartIntent(context, new FullScreenWebViewIntentExtras().setTitle(str2).setUrl(str).setHandleAllClicksExternally(z).setAddLanguageHeader(z2).setLoadUrlWithAuthHeader(z3));
    }

    public static Intent newStartIntent(Context context, FullScreenWebViewIntentExtras fullScreenWebViewIntentExtras) {
        return new Intent(context, FullScreenWebView.class).putExtra(EXTRA_WEB_VIEW_INTENT_EXTRAS, fullScreenWebViewIntentExtras);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (!this.webViewChromeClient.handleOnActivityResult(i, i2, intent)) {
            super.onActivityResult(i, i2, intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        this.webViewIntentExtras = (FullScreenWebViewIntentExtras) ExtrasUtils.getParcelable(getIntent(), EXTRA_WEB_VIEW_INTENT_EXTRAS, FullScreenWebViewIntentExtras.class.getClassLoader());
        String title = this.webViewIntentExtras.getTitle();
        super.onCreate(bundle);
        setContentView((int) R.layout.full_screen_webview);
        setTitle(title);
        if (this.webViewIntentExtras.showCloseAsBackButton()) {
            getToolbar().setNavigationIcon((int) R.drawable.ic_close_white_24dp);
        }
        this.offlineMessage = (TextView) findById(R.id.offlineMessage);
        this.webViewChromeClient = new MfpWebViewChromeClientWithProgress(this);
        this.webViewClient = new Client(this);
        this.browser = (WebView) findById(R.id.webviewFaq);
        this.browser.getSettings().setJavaScriptEnabled(true);
        this.browser.setWebViewClient(getWebViewClient());
        this.browser.setWebChromeClient(getWebViewChromeClient());
    }

    /* access modifiers changed from: protected */
    public WebViewClient getWebViewClient() {
        return this.webViewClient;
    }

    /* access modifiers changed from: protected */
    public WebChromeClient getWebViewChromeClient() {
        return this.webViewChromeClient;
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        reloadPage();
    }

    public void onBackPressed() {
        if (this.browser.canGoBack()) {
            this.browser.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public boolean shouldShowTitle() {
        return !this.webViewIntentExtras.handleAllClicksExternally() && super.shouldShowTitle();
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        return this.webViewIntentExtras.getUrl();
    }

    /* access modifiers changed from: protected */
    public void reloadPage() {
        String url = getUrl();
        if (this.webViewIntentExtras.loadUrlWithAuthHeader()) {
            loadUrlWithAuthHeaders(url);
        } else {
            loadUrl(url);
        }
        if (Strings.notEmpty(this.webViewIntentExtras.getWebviewKeyToLaunch())) {
            String webviewKeyToLaunch = this.webViewIntentExtras.getWebviewKeyToLaunch();
            getAnalyticsService().reportEvent(Events.INTERSTITIAL_VIEW, MapUtil.createMap("type", webviewKeyToLaunch));
            ((LocalSettingsService) this.localSettingsService.get()).trackGenericWebView(webviewKeyToLaunch);
        }
    }

    /* access modifiers changed from: protected */
    public void loadUrlWithAuthHeaders(String str) {
        this.clearHistoryAfterLoad = true;
        Map credentials = ((ApiUrlProvider) this.apiUrlProvider.get()).getCredentials();
        addLanguageHeader(credentials);
        this.browser.loadUrl(str, credentials);
    }

    /* access modifiers changed from: protected */
    public void loadUrl(String str) {
        this.clearHistoryAfterLoad = true;
        HashMap hashMap = new HashMap();
        addLanguageHeader(hashMap);
        this.browser.loadUrl(str, hashMap);
    }

    private void addLanguageHeader(Map<String, String> map) {
        if (this.webViewIntentExtras.addLanguageHeader()) {
            map.put("Accept-Language", getCountryService().getCurrentLocaleIdentifierForV2());
        }
    }

    private static Intent startIntent(Context context, String str, boolean z, String str2) {
        return newStartIntent(context, new FullScreenWebViewIntentExtras().setTitle(str).setLoadUrlWithAuthHeader(z).setUrl(str2));
    }
}
