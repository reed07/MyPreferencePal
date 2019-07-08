package com.myfitnesspal.feature.recipes.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import butterknife.OnClick;
import com.facebook.ads.AudienceNetworkActivity;
import com.mopub.common.Constants;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.recipes.api.RecipeParseResult;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.recipes.task.ParseRecipeTask;
import com.myfitnesspal.feature.recipes.task.ParseRecipeTask.CompletedEvent;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Http;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.util.MfpWebViewChromeClient;
import com.myfitnesspal.shared.util.MfpWebViewClient;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Set;
import javax.inject.Inject;

public class RecipeImportBrowserActivity extends MfpActivity {
    private static final String EXTRA_ANALYTICS_INTENT_DATA = "analytics_intent";
    private static final String EXTRA_CURRENT_URL = "current_url";
    private static final String EXTRA_MEAL_NAME = "meal_name";
    private static final String EXTRA_RECIPE_URI = "recipe_uri";
    private static final String EXTRA_URL_HISTORY = "url_history";
    private static final String GOOGLE_SEARCH_URL = "http://www.google.com/search";
    private static final String HOME_URI = "/mobile/recipes/partners/list";
    private static final String SCHEME_HTTP = "http";
    private static final String SCHEME_HTTPS = "https";
    @Inject
    Lazy<ApiUrlProvider> apiUrlProvider;
    @BindView(2131362013)
    View browserBackButton;
    @BindView(2131362017)
    View browserForwardButton;
    @BindView(2131363428)
    ImageButton browserRefreshOrStopButton;
    @BindView(2131361982)
    View browserToolbar;
    @BindView(2131361976)
    public WebView browserView;
    private String homeUrl;
    @BindView(2131362794)
    View importContainer;
    @BindView(2131362945)
    View importLoadingContainer;
    @BindView(2131362018)
    View importRecipeButton;
    @BindView(2131363314)
    ProgressBar pageLoadingProgress;
    @Inject
    Lazy<RecipeService> recipeService;
    @Inject
    Lazy<RecipesAnalyticsHelper> recipesAnalyticsHelper;
    @BindView(2131363578)
    EditText searchUrlInputView;
    private Set<String> urlHistory;
    private RecipeImporterChromeClient webViewChromeClient;

    private class RecipeImporterChromeClient extends MfpWebViewChromeClient {
        private boolean loading;

        RecipeImporterChromeClient() {
            super(RecipeImportBrowserActivity.this);
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            String url = webView.getUrl();
            boolean equals = Strings.equals(url, RecipeImportBrowserActivity.this.getHomeUrl());
            setLoading(i != 100);
            if (!isLoading()) {
                RecipeImportBrowserActivity.this.addUrlToHistory(url);
            }
            RecipeImportBrowserActivity.this.pageLoadingProgress.setProgress(i);
            RecipeImportBrowserActivity.this.searchUrlInputView.setText(equals ? "" : Strings.toString(url));
            RecipeImportBrowserActivity.this.toggleImportButtonVisibility(!equals);
        }

        public boolean isLoading() {
            return this.loading;
        }

        private void setLoading(boolean z) {
            this.loading = z;
            RecipeImportBrowserActivity.this.toggleBrowserLoadingState(z);
        }
    }

    private class RecipeImporterClient extends MfpWebViewClient {
        private static final String BAD_AD_HACK_PREFIX = "data:text/html";

        RecipeImporterClient() {
            super(RecipeImportBrowserActivity.this);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (super.shouldOverrideUrlLoading(webView, str)) {
                return true;
            }
            if (str.startsWith(BAD_AD_HACK_PREFIX)) {
                reportBadAdEvent(webView, str);
                return true;
            } else if (Strings.equals(str, "about:blank")) {
                return false;
            } else {
                if (str.startsWith("intent://myfitnesspal")) {
                    str = str.replace(Constants.INTENT_SCHEME, ExternalWebViewFragment.SOURCE_STRING);
                }
                if (str.startsWith("mfp://myfitnesspal")) {
                    RecipeImportBrowserActivity.this.getNavigationHelper().withIntent(SharedIntents.newUriIntent(str)).startActivity();
                    return true;
                }
                webView.loadUrl(str);
                return true;
            }
        }

        private void reportBadAdEvent(WebView webView, String str) {
            RecipeImportBrowserActivity.this.getAnalyticsService().reportEvent(Events.RECIPE_BROWSER_BAD_AD, MapUtil.createMap(Attributes.SOURCE_URL, Strings.toString(webView != null ? webView.getUrl() : ""), Attributes.AD_URL, Strings.toString(str)));
        }
    }

    public boolean showToolbar() {
        return false;
    }

    public static Intent newStartIntent(Context context, RecipeAnalyticsIntentData recipeAnalyticsIntentData, String str) {
        return new Intent(context, RecipeImportBrowserActivity.class).putExtra(EXTRA_ANALYTICS_INTENT_DATA, recipeAnalyticsIntentData).putExtra("meal_name", str);
    }

    public static Intent newStartIntentWithRecipeUri(Context context, String str, RecipeAnalyticsIntentData recipeAnalyticsIntentData) {
        return new Intent(context, RecipeImportBrowserActivity.class).putExtra(EXTRA_RECIPE_URI, str).putExtra(EXTRA_ANALYTICS_INTENT_DATA, recipeAnalyticsIntentData);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.recipe_import_browser);
        init(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(EXTRA_CURRENT_URL, this.browserView.getUrl());
        BundleUtils.putStringSet(bundle, EXTRA_URL_HISTORY, this.urlHistory);
    }

    public void onBackPressed() {
        if (this.browserView.canGoBack()) {
            this.browserView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick({2131362018})
    public void onImportRecipeClick() {
        importRecipe(this.browserView.getUrl());
    }

    /* access modifiers changed from: 0000 */
    @OnClick({2131362013})
    public void onBackClick() {
        if (this.browserView.canGoBack()) {
            this.browserView.goBack();
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick({2131362017})
    public void onForwardClick() {
        if (this.browserView.canGoForward()) {
            this.browserView.goForward();
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick({2131363428})
    public void onRefreshOrStopClick() {
        if (this.webViewChromeClient.isLoading()) {
            this.browserView.stopLoading();
        } else {
            this.browserView.reload();
        }
    }

    @Subscribe
    public void onParseRecipeTaskCompletedEvent(CompletedEvent completedEvent) {
        RecipeParseResult recipeParseResult = (RecipeParseResult) completedEvent.getResult();
        if (recipeParseResult == null) {
            recipeParseResult = new RecipeParseResult();
            recipeParseResult.setUrl(completedEvent.getOriginalUrl());
        }
        if (recipeParseResult.getServings() == 0.0d) {
            recipeParseResult.setServings(1.0d);
        }
        toggleImportProgressVisibility();
        RecipeAnalyticsIntentData analyticsIntentData = getAnalyticsIntentData();
        getNavigationHelper().withIntent(RecipeImportReviewActivity.newStartIntent(this, recipeParseResult, analyticsIntentData, getIntent().getStringExtra("meal_name"))).startActivity();
        ((RecipesAnalyticsHelper) this.recipesAnalyticsHelper.get()).reportRecipeBrowser(analyticsIntentData, this.urlHistory.size());
    }

    private void init(Bundle bundle) {
        this.urlHistory = BundleUtils.getStringSet(bundle, EXTRA_URL_HISTORY);
        addStatusBarHeightToBrowserToolbar();
        setupBrowserView(bundle);
        toggleImportProgressVisibility();
        toggleImportButtonVisibility(false);
        setListeners();
        String string = ExtrasUtils.getString(getIntent(), EXTRA_RECIPE_URI);
        if (Strings.notEmpty(string)) {
            importRecipe(string);
        }
        if (bundle == null) {
            ((RecipesAnalyticsHelper) this.recipesAnalyticsHelper.get()).reportRecipeUrlList(getAnalyticsIntentData());
        }
    }

    private void addStatusBarHeightToBrowserToolbar() {
        View view = this.browserToolbar;
        view.setPadding(view.getPaddingLeft(), this.browserToolbar.getPaddingTop(), this.browserToolbar.getPaddingRight(), this.browserToolbar.getPaddingBottom());
    }

    private void setupBrowserView(Bundle bundle) {
        RecipeImporterClient recipeImporterClient = new RecipeImporterClient();
        this.webViewChromeClient = new RecipeImporterChromeClient();
        this.browserView.setWebViewClient(recipeImporterClient);
        this.browserView.setWebChromeClient(this.webViewChromeClient);
        this.browserView.getSettings().setJavaScriptEnabled(true);
        this.browserView.getSettings().setDomStorageEnabled(true);
        this.browserView.clearCache(true);
        this.browserView.loadUrl(BundleUtils.getString(bundle, EXTRA_CURRENT_URL, getHomeUrl()));
    }

    private void setListeners() {
        this.searchUrlInputView.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 2 && ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 0)) {
                    return false;
                }
                RecipeImportBrowserActivity.this.loadUrlFromEntry();
                return true;
            }
        });
    }

    /* access modifiers changed from: private */
    public void loadUrlFromEntry() {
        String str;
        getImmHelper().hideSoftInput();
        String strings = Strings.toString(this.searchUrlInputView.getText());
        if (Patterns.WEB_URL.matcher(strings).matches()) {
            Uri parse = Uri.parse(strings);
            if (Strings.isEmpty(parse.getScheme())) {
                parse = parse.buildUpon().scheme("http").build();
            } else if (!Strings.equalsIgnoreCase(parse.getScheme(), "http") || !Strings.equalsIgnoreCase(parse.getScheme(), "https")) {
                parse = parse.buildUpon().scheme("http").build();
            }
            str = parse.toString();
        } else {
            str = buildGoogleUrlFrom(strings);
        }
        this.browserView.loadUrl(str);
    }

    private void importRecipe(String str) {
        new ParseRecipeTask(this.recipeService, str).run(getRunner());
        toggleImportProgressVisibility();
    }

    /* access modifiers changed from: private */
    public void addUrlToHistory(String str) {
        if (!Strings.equals(str, getHomeUrl())) {
            this.urlHistory.add(str);
        }
    }

    /* access modifiers changed from: private */
    public void toggleImportButtonVisibility(boolean z) {
        ViewUtils.setVisible(z, this.importContainer);
    }

    /* access modifiers changed from: private */
    public void toggleBrowserLoadingState(boolean z) {
        ViewUtils.setVisible(z, this.pageLoadingProgress);
        this.browserRefreshOrStopButton.setImageResource(z ? R.drawable.ic_nav_close : R.drawable.ic_nav_refresh);
        this.browserBackButton.setEnabled(this.browserView.canGoBack());
        this.browserForwardButton.setEnabled(this.browserView.canGoForward());
    }

    private void toggleImportProgressVisibility() {
        ViewUtils.setVisible(getRunner().running(ParseRecipeTask.NAME), this.importLoadingContainer);
    }

    /* access modifiers changed from: private */
    public String getHomeUrl() {
        if (Strings.isEmpty(this.homeUrl)) {
            this.homeUrl = Uri.parse(((ApiUrlProvider) this.apiUrlProvider.get()).getBaseUrlForWebsite(HOME_URI)).buildUpon().scheme("http").clearQuery().build().toString();
        }
        return this.homeUrl;
    }

    private RecipeAnalyticsIntentData getAnalyticsIntentData() {
        return (RecipeAnalyticsIntentData) ExtrasUtils.getParcelable(getIntent(), EXTRA_ANALYTICS_INTENT_DATA, RecipeAnalyticsIntentData.class.getClassLoader());
    }

    private String buildGoogleUrlFrom(String str) {
        try {
            return Uri.parse(GOOGLE_SEARCH_URL).buildUpon().appendQueryParameter(Http.Q, URLEncoder.encode(str, AudienceNetworkActivity.WEBVIEW_ENCODING)).build().toString();
        } catch (UnsupportedEncodingException e) {
            Ln.w(e, "Could not load url, invalid url encoding. Loading google instead", new Object[0]);
            return GOOGLE_SEARCH_URL;
        }
    }
}
