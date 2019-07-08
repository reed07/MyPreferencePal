package com.myfitnesspal.shared.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SearchFoodItemBaseActivity_ViewBinding implements Unbinder {
    private SearchFoodItemBaseActivity target;

    @UiThread
    public SearchFoodItemBaseActivity_ViewBinding(SearchFoodItemBaseActivity searchFoodItemBaseActivity) {
        this(searchFoodItemBaseActivity, searchFoodItemBaseActivity.getWindow().getDecorView());
    }

    @UiThread
    public SearchFoodItemBaseActivity_ViewBinding(SearchFoodItemBaseActivity searchFoodItemBaseActivity, View view) {
        this.target = searchFoodItemBaseActivity;
        searchFoodItemBaseActivity.inputText = (EditText) Utils.findRequiredViewAsType(view, R.id.input, "field 'inputText'", EditText.class);
    }

    @CallSuper
    public void unbind() {
        SearchFoodItemBaseActivity searchFoodItemBaseActivity = this.target;
        if (searchFoodItemBaseActivity != null) {
            this.target = null;
            searchFoodItemBaseActivity.inputText = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
