package com.myfitnesspal.feature.recipes.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class RecipeImportBrowserActivity_ViewBinding implements Unbinder {
    private RecipeImportBrowserActivity target;
    private View view2131362013;
    private View view2131362017;
    private View view2131362018;
    private View view2131363428;

    @UiThread
    public RecipeImportBrowserActivity_ViewBinding(RecipeImportBrowserActivity recipeImportBrowserActivity) {
        this(recipeImportBrowserActivity, recipeImportBrowserActivity.getWindow().getDecorView());
    }

    @UiThread
    public RecipeImportBrowserActivity_ViewBinding(final RecipeImportBrowserActivity recipeImportBrowserActivity, View view) {
        this.target = recipeImportBrowserActivity;
        recipeImportBrowserActivity.searchUrlInputView = (EditText) Utils.findRequiredViewAsType(view, R.id.search_url_input, "field 'searchUrlInputView'", EditText.class);
        recipeImportBrowserActivity.pageLoadingProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progress, "field 'pageLoadingProgress'", ProgressBar.class);
        recipeImportBrowserActivity.browserView = (WebView) Utils.findRequiredViewAsType(view, R.id.browser, "field 'browserView'", WebView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_import_recipe, "field 'importRecipeButton' and method 'onImportRecipeClick'");
        recipeImportBrowserActivity.importRecipeButton = findRequiredView;
        this.view2131362018 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                recipeImportBrowserActivity.onImportRecipeClick();
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.btn_back, "field 'browserBackButton' and method 'onBackClick'");
        recipeImportBrowserActivity.browserBackButton = findRequiredView2;
        this.view2131362013 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                recipeImportBrowserActivity.onBackClick();
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.btn_forward, "field 'browserForwardButton' and method 'onForwardClick'");
        recipeImportBrowserActivity.browserForwardButton = findRequiredView3;
        this.view2131362017 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                recipeImportBrowserActivity.onForwardClick();
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.refresh_or_stop, "field 'browserRefreshOrStopButton' and method 'onRefreshOrStopClick'");
        recipeImportBrowserActivity.browserRefreshOrStopButton = (ImageButton) Utils.castView(findRequiredView4, R.id.refresh_or_stop, "field 'browserRefreshOrStopButton'", ImageButton.class);
        this.view2131363428 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                recipeImportBrowserActivity.onRefreshOrStopClick();
            }
        });
        recipeImportBrowserActivity.importLoadingContainer = Utils.findRequiredView(view, R.id.loading_container, "field 'importLoadingContainer'");
        recipeImportBrowserActivity.browserToolbar = Utils.findRequiredView(view, R.id.browser_toolbar, "field 'browserToolbar'");
        recipeImportBrowserActivity.importContainer = Utils.findRequiredView(view, R.id.import_container, "field 'importContainer'");
    }

    @CallSuper
    public void unbind() {
        RecipeImportBrowserActivity recipeImportBrowserActivity = this.target;
        if (recipeImportBrowserActivity != null) {
            this.target = null;
            recipeImportBrowserActivity.searchUrlInputView = null;
            recipeImportBrowserActivity.pageLoadingProgress = null;
            recipeImportBrowserActivity.browserView = null;
            recipeImportBrowserActivity.importRecipeButton = null;
            recipeImportBrowserActivity.browserBackButton = null;
            recipeImportBrowserActivity.browserForwardButton = null;
            recipeImportBrowserActivity.browserRefreshOrStopButton = null;
            recipeImportBrowserActivity.importLoadingContainer = null;
            recipeImportBrowserActivity.browserToolbar = null;
            recipeImportBrowserActivity.importContainer = null;
            this.view2131362018.setOnClickListener(null);
            this.view2131362018 = null;
            this.view2131362013.setOnClickListener(null);
            this.view2131362013 = null;
            this.view2131362017.setOnClickListener(null);
            this.view2131362017 = null;
            this.view2131363428.setOnClickListener(null);
            this.view2131363428 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
