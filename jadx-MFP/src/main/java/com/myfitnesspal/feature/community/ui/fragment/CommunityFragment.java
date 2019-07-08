package com.myfitnesspal.feature.community.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.Toast;
import com.facebook.ads.AudienceNetworkActivity;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.community.service.CommunityService;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.model.v2.MfpUrl;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment;
import com.myfitnesspal.shared.util.MfpWebViewChromeClient;
import com.myfitnesspal.shared.util.MfpWebViewChromeClientWithProgress;
import com.myfitnesspal.shared.util.MfpWebViewClient;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import javax.inject.Inject;

public class CommunityFragment extends ExternalWebViewFragment {
    private static final int DONE_BUTTON = 1024;
    private static final String FORUM_URL = "http://myfitnesspal.vanillaforums.com";
    @Inject
    CommunityService communityService;
    /* access modifiers changed from: private */
    public String forumHost = "myfitnesspal.vanillaforums.com";
    /* access modifiers changed from: private */
    public String forumUrl;
    private String target = "";
    private MfpWebViewChromeClient webviewChromeClient;

    public static CommunityFragment newInstance(Bundle bundle) {
        CommunityFragment communityFragment = new CommunityFragment();
        if (bundle != null) {
            new Bundle().putAll(bundle);
            communityFragment.setArguments(bundle);
        }
        return communityFragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.forum_activity, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        startSingleSignOn();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setHasOptionsMenu(true);
        resolveUrl();
        startSingleSignOn();
    }

    private void resolveUrl() {
        String string = BundleUtils.getString(getArguments(), "url");
        populateUtmParams(string);
        try {
            if (Strings.notEmpty(string)) {
                if (Strings.notEmpty(Uri.parse(string).getHost())) {
                    this.forumHost = Uri.parse(string).getHost();
                }
                this.target = string.substring(string.indexOf(this.forumHost) + this.forumHost.length());
                if (this.target.contains("?")) {
                    this.target = this.target.substring(0, this.target.indexOf("?"));
                }
                this.target = URLEncoder.encode(this.target, AudienceNetworkActivity.WEBVIEW_ENCODING);
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (!this.webviewChromeClient.handleOnActivityResult(i, i2, intent)) {
            super.onActivityResult(i, i2, intent);
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.refresh).setIcon(R.drawable.ic_refresh_white_24dp), 2);
        MenuItemCompat.setShowAsAction(menu.add(0, DONE_BUTTON, 0, R.string.done), 2);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 100) {
            WebView webView = getWebView();
            if (webView != null) {
                webView.reload();
            }
            return true;
        } else if (itemId != DONE_BUTTON) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            getActivity().finish();
            return true;
        }
    }

    public void onStop() {
        super.onStop();
        this.analyticsService.reportEvent(Events.FORUM_SUMMARY, getMutableAnalyticsAttributes());
    }

    /* access modifiers changed from: private */
    public void onSingleSignOnComplete(List<String> list) {
        View view = getView();
        if (view != null) {
            this.webviewChromeClient = new MfpWebViewChromeClientWithProgress((MfpActivity) getActivity());
            this.webviewChromeClient.setFragmentTarget(this);
            WebView webView = setWebView((WebView) view.findViewById(R.id.webViewForum));
            webView.getSettings().setJavaScriptEnabled(true);
            try {
                if (VERSION.SDK_INT > 20) {
                    webView.getSettings().setMixedContentMode(0);
                }
            } catch (NoSuchMethodError unused) {
            }
            webView.setWebChromeClient(this.webviewChromeClient);
            CookieSyncManager createInstance = CookieSyncManager.createInstance(webView.getContext());
            CookieManager instance = CookieManager.getInstance();
            instance.setAcceptCookie(true);
            instance.removeSessionCookie();
            for (String split : list) {
                String str = split.split(";")[0];
                instance.setCookie(MfpWebViewClient.MFP_HOST, str);
                instance.setCookie("http://www.myfitnesspal.com", str);
                instance.setCookie("http://myfitnesspal.com", str);
                instance.setCookie("http://beta-community.myfitnesspal.com", str);
                instance.setCookie("http://www.beta-community.myfitnesspal.com", str);
                instance.setCookie("www.beta-community.myfitnesspal.com", str);
                instance.setCookie("http://api.myfitnesspal.com", str);
            }
            createInstance.sync();
            loadPageInWebView(this.forumHost, this.forumUrl);
        }
    }

    private void startSingleSignOn() {
        this.communityService.singleSignOn(this.target, new Function2<ApiResponse<MfpUrl>, List<String>>() {
            public void execute(ApiResponse<MfpUrl> apiResponse, List<String> list) {
                if (apiResponse.getItem() != null) {
                    CommunityFragment.this.forumUrl = ((MfpUrl) apiResponse.getItem()).getUrl();
                    if (Strings.notEmpty(CommunityFragment.this.forumUrl)) {
                        try {
                            CommunityFragment.this.forumHost = Uri.parse(CommunityFragment.this.forumUrl).getHost();
                        } catch (NullPointerException unused) {
                            Ln.e("Unable to parse forumHost from forumUrl", new Object[0]);
                        }
                    }
                } else {
                    CommunityFragment.this.forumUrl = CommunityFragment.FORUM_URL;
                }
                CommunityFragment.this.onSingleSignOnComplete(list);
            }
        }, new MfpV2ApiErrorCallback() {
            public void execute(ApiResponseBase apiResponseBase) throws RuntimeException {
                StringBuilder sb = new StringBuilder();
                sb.append("Could not complete single sign on: ");
                sb.append(apiResponseBase.getError());
                Ln.e(sb.toString(), new Object[0]);
                Toast.makeText(CommunityFragment.this.getContext(), R.string.community_unavailable, 1).show();
            }
        });
    }
}
