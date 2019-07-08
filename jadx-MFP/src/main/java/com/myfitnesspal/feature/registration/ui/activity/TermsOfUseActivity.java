package com.myfitnesspal.feature.registration.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.event.BusyEvent;
import com.myfitnesspal.shared.model.FullScreenWebViewIntentExtras;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import javax.inject.Inject;

public class TermsOfUseActivity extends FullScreenWebView {
    private static final int AGREE = 99;
    private static final int SWITCH_LANGUAGE = 100;
    @Inject
    protected ApiUrlProvider apiUrlProvider;
    @Inject
    protected ConfigService configService;
    @Inject
    protected CountryService countryService;
    private boolean hasPostedTimer;
    private boolean isShowingNonEnglish;
    /* access modifiers changed from: private */
    public boolean showedAgreeButtonAlready;

    protected class TOSClient extends Client {
        public TOSClient(Activity activity) {
            super(activity);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri parse = Uri.parse(str);
            String host = parse.getHost();
            String scheme = parse.getScheme();
            boolean z = Strings.notEmpty(host) && (host.contains("networkadvertising.org") || host.contains("aboutads.info") || host.contains("jumptap.com"));
            boolean z2 = Strings.notEmpty(scheme) && scheme.equals("mailto");
            if (!z && !z2) {
                return false;
            }
            TermsOfUseActivity.this.getNavigationHelper().withIntent(SharedIntents.newUriIntent(str)).startActivity();
            return true;
        }
    }

    public boolean onSearchRequested() {
        return false;
    }

    public static Intent newStartIntent(Context context) {
        return newStartIntent(context, false, null);
    }

    public static Intent newStartIntent(Context context, boolean z, Bundle bundle) {
        return new Intent(context, TermsOfUseActivity.class).putExtra(Extras.USER_MUST_AGREE, z).putExtra(Extras.ORIGINAL_EXTRAS, bundle).putExtra("web_view_intent_extras", new FullScreenWebViewIntentExtras().setTitle(context.getString(R.string.licenseAgreementHeader)));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setTitle(R.string.licenseAgreementHeader);
        this.isShowingNonEnglish = !isLocaleEnglish();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if (ExtrasUtils.getBoolean(getIntent(), Extras.USER_MUST_AGREE, false)) {
            if (!isBusy() || this.showedAgreeButtonAlready) {
                this.showedAgreeButtonAlready = true;
                boolean z = !isLocaleEnglish() && ConnectivityUtil.isOnline();
                if (z) {
                    MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.english_caps).setTitle(!this.isShowingNonEnglish ? getCountryService().getLocalizedNameForCurrentLanguage().toUpperCase() : getString(R.string.english_caps)), 6);
                }
                String string = getString(R.string.agreeBtn);
                MenuItem add = menu.add(0, 99, 0, string);
                if (z) {
                    TextView textView = (TextView) getLayoutInflater().inflate(R.layout.prominent_action_item, null);
                    textView.setText(string);
                    textView.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            TermsOfUseActivity.this.onAgree();
                        }
                    });
                    MenuItemCompat.setActionView(add, (View) textView);
                }
                MenuItemCompat.setShowAsAction(add, 2);
            } else if (!this.hasPostedTimer) {
                this.hasPostedTimer = true;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (!TermsOfUseActivity.this.showedAgreeButtonAlready) {
                            TermsOfUseActivity.this.showedAgreeButtonAlready = true;
                            TermsOfUseActivity.this.supportInvalidateOptionsMenu();
                        }
                    }
                }, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
            }
        }
        return true;
    }

    private boolean isLocaleEnglish() {
        return getCountryService().isEnglishCurrentDialect();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 99:
                onAgree();
                return true;
            case 100:
                this.isShowingNonEnglish = !this.isShowingNonEnglish;
                reloadPage();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* access modifiers changed from: private */
    public void onAgree() {
        getMessageBus().post(new BusyEvent(2, true));
        getSession().getUser().markTOSAccepted();
        scheduleSync();
        Bundle bundle = ExtrasUtils.getBundle(getIntent(), Extras.ORIGINAL_EXTRAS);
        getNavigationHelper().finishActivityAfterNavigation().withExtras(bundle).withExtras(bundle).withIntent(HomeActivity.newStartIntent(this)).startActivity();
    }

    /* access modifiers changed from: protected */
    public WebViewClient getWebViewClient() {
        return new TOSClient(this);
    }

    /* access modifiers changed from: protected */
    public void reloadPage() {
        loadUrl(getUrl());
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        if (!ConnectivityUtil.isOnline()) {
            return this.configService.getEnglishTosUrl();
        }
        return Uri.parse(this.apiUrlProvider.getBaseUrlForWebsite(this.configService.getTermsAndPrivacyUrl())).buildUpon().appendQueryParameter(Http.LANG, this.isShowingNonEnglish ? this.countryService.getCurrentLocaleIdentifier() : "en-US").toString();
    }

    @Subscribe
    public void onBusyEvent(BusyEvent busyEvent) {
        supportInvalidateOptionsMenu();
    }
}
