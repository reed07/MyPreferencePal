package com.myfitnesspal.feature.blog.ui.fragment;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.facebook.appevents.AppEventsConstants;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.blog.util.BlogEndpointHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Http;
import com.myfitnesspal.shared.constants.Constants.Settings.App.URLs;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment;
import com.myfitnesspal.shared.util.MfpWebViewChromeClientWithProgress;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;

public class BlogFragment extends ExternalWebViewFragment {
    public static final String MEDIUM = "medium";
    public static final String SOURCE = "source";
    @Inject
    Lazy<ApiUrlProvider> apiUrlProvider;
    @Inject
    Lazy<CountryService> countryService;
    @Inject
    Lazy<PremiumService> premiumService;

    public String getAnalyticsScreenTag() {
        return Screens.BLOG;
    }

    public static BlogFragment newInstance(String str, String str2, String str3) {
        BlogFragment blogFragment = new BlogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        bundle.putString("source", str2);
        bundle.putString("medium", str3);
        blogFragment.setArguments(bundle);
        return blogFragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.blog_activity, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        initViews(view);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setHasOptionsMenu(true);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.refresh).setIcon(R.drawable.ic_refresh_white_24dp), 2);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 100) {
            return super.onOptionsItemSelected(menuItem);
        }
        onRefreshPressed();
        return true;
    }

    public void onStop() {
        super.onStop();
        this.analyticsService.reportEvent(Events.BLOG_SUMMARY, getMutableAnalyticsAttributes());
    }

    private void initViews(View view) {
        BlogEndpointHelper blogEndpointHelper = new BlogEndpointHelper(this.countryService, this.apiUrlProvider);
        String blogUrlForCurrentLocale = blogEndpointHelper.getBlogUrlForCurrentLocale();
        String blogHostForCurrentLocale = blogEndpointHelper.getBlogHostForCurrentLocale();
        String string = BundleUtils.getString(getArguments(), "url");
        populateUtmParams(string);
        if (Strings.notEmpty(string)) {
            if (Strings.notEmpty(Uri.parse(string).getHost())) {
                blogHostForCurrentLocale = Uri.parse(string).getHost();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(blogUrlForCurrentLocale);
                sb.append(string);
                string = sb.toString();
            }
        }
        Bundle arguments = getArguments();
        setTitle(BundleUtils.getBoolean(arguments, "premium_content", false) ? R.string.premium_content_content : R.string.blog, new Object[0]);
        setWebView((WebView) view.findViewById(R.id.webViewBlog));
        WebView webView = getWebView();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebChromeClient(new MfpWebViewChromeClientWithProgress((MfpActivity) getActivity()));
        if (Strings.notEmpty(string)) {
            blogUrlForCurrentLocale = string;
        }
        String string2 = BundleUtils.getString(arguments, "source");
        String string3 = BundleUtils.getString(arguments, "medium");
        Builder appendQueryParameter = Uri.parse(blogUrlForCurrentLocale).buildUpon().appendQueryParameter(Http.NATIVE_CLIENT, AppEventsConstants.EVENT_PARAM_VALUE_YES).appendQueryParameter(URLs.BLOG_PREMIUM_QUERY_PARAM, Strings.toString(Boolean.valueOf(((PremiumService) this.premiumService.get()).isPremiumSubscribed())));
        if (Strings.notEmpty(string2) || Strings.notEmpty(string3)) {
            appendQueryParameter.appendQueryParameter("utm_medium", string3).appendQueryParameter("utm_source", string2);
        } else if (Strings.notEmpty(this.utmMedium) || Strings.notEmpty(this.utmSource)) {
            appendQueryParameter.appendQueryParameter("utm_medium", this.utmMedium).appendQueryParameter("utm_source", this.utmSource);
        }
        loadPageInWebView(blogHostForCurrentLocale, appendQueryParameter.toString());
    }

    private void onRefreshPressed() {
        WebView webView = getWebView();
        if (webView != null) {
            webView.reload();
        }
    }
}
