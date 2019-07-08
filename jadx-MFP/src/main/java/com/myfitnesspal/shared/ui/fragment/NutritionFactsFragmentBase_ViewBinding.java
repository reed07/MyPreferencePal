package com.myfitnesspal.shared.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class NutritionFactsFragmentBase_ViewBinding implements Unbinder {
    private NutritionFactsFragmentBase target;

    @UiThread
    public NutritionFactsFragmentBase_ViewBinding(NutritionFactsFragmentBase nutritionFactsFragmentBase, View view) {
        this.target = nutritionFactsFragmentBase;
        nutritionFactsFragmentBase.simpleView = Utils.findRequiredView(view, R.id.nutritionFactsSimple, "field 'simpleView'");
        nutritionFactsFragmentBase.detailedView = Utils.findRequiredView(view, R.id.nutritionFactsDetailed, "field 'detailedView'");
        nutritionFactsFragmentBase.showMore = Utils.findRequiredView(view, R.id.showMore, "field 'showMore'");
    }

    @CallSuper
    public void unbind() {
        NutritionFactsFragmentBase nutritionFactsFragmentBase = this.target;
        if (nutritionFactsFragmentBase != null) {
            this.target = null;
            nutritionFactsFragmentBase.simpleView = null;
            nutritionFactsFragmentBase.detailedView = null;
            nutritionFactsFragmentBase.showMore = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
