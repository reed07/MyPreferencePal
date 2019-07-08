package com.myfitnesspal.feature.walkthrough.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.ClearableEditText;

public class WalkthroughFoodSearchFragment_ViewBinding implements Unbinder {
    private WalkthroughFoodSearchFragment target;

    @UiThread
    public WalkthroughFoodSearchFragment_ViewBinding(WalkthroughFoodSearchFragment walkthroughFoodSearchFragment, View view) {
        this.target = walkthroughFoodSearchFragment;
        walkthroughFoodSearchFragment.editTxtFoodSearch = (ClearableEditText) Utils.findRequiredViewAsType(view, R.id.txt_food_search, "field 'editTxtFoodSearch'", ClearableEditText.class);
        walkthroughFoodSearchFragment.scanButton = (ImageButton) Utils.findRequiredViewAsType(view, R.id.btn_scan, "field 'scanButton'", ImageButton.class);
        walkthroughFoodSearchFragment.searchResultsWalkThroughContainer = Utils.findRequiredView(view, R.id.search_results_walkthrough_container, "field 'searchResultsWalkThroughContainer'");
        walkthroughFoodSearchFragment.divider = view.findViewById(R.id.divider);
    }

    @CallSuper
    public void unbind() {
        WalkthroughFoodSearchFragment walkthroughFoodSearchFragment = this.target;
        if (walkthroughFoodSearchFragment != null) {
            this.target = null;
            walkthroughFoodSearchFragment.editTxtFoodSearch = null;
            walkthroughFoodSearchFragment.scanButton = null;
            walkthroughFoodSearchFragment.searchResultsWalkThroughContainer = null;
            walkthroughFoodSearchFragment.divider = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
