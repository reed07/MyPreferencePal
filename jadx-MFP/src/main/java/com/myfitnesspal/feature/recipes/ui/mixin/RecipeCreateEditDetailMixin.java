package com.myfitnesspal.feature.recipes.ui.mixin;

import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.behavior.HeaderImageScrollBehavior;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.fragment.NutritionFactsFragmentBase;
import com.myfitnesspal.shared.ui.fragment.impl.NewNutritionFactsFragment;
import com.myfitnesspal.shared.ui.fragment.impl.NewNutritionFactsFragment.OnNutritionFactsExpandedOrCollapsedListener;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Strings;
import com.uacf.core.util.VersionUtils;
import com.uacf.core.util.ViewUtils;

public class RecipeCreateEditDetailMixin extends RunnerLifecycleMixin {
    private static final RequestOptions IMAGE_OPTIONS = new RequestOptions().fitCenter().centerCrop().placeholder((int) R.drawable.meal_photo_default_bg).error(R.drawable.meal_photo_default_bg);
    private static final String NUTRITIONAL_FRAGMENT_TAG = "nutritional_fragment";
    /* access modifiers changed from: private */
    public final Fragment fragment;
    private final OnNutritionFactsExpandedOrCollapsedListener nutritionFactsExpandedOrCollapsedListener = new OnNutritionFactsExpandedOrCollapsedListener() {
        public void onNutritionFactsExpandedOrCollapsed(boolean z) {
            NestedScrollView nestedScrollView = (NestedScrollView) ViewUtils.findById(RecipeCreateEditDetailMixin.this.fragment.getView(), R.id.nested_scroll_parent);
            if (!z && nestedScrollView != null) {
                nestedScrollView.scrollTo(0, 0);
            }
        }
    };

    public RecipeCreateEditDetailMixin(MfpFragment mfpFragment) {
        super(mfpFragment);
        this.fragment = mfpFragment;
    }

    public void displayRecipeImage(MfpRecipe mfpRecipe, ImageView imageView) {
        String str = (String) Enumerable.firstOrDefault(mfpRecipe.getSourceImageUrls());
        if (Strings.notEmpty(str)) {
            ViewUtils.setVisible(imageView);
            MfpActivity mfpActivity = (MfpActivity) getActivity();
            Glide.with((FragmentActivity) mfpActivity).load(str).apply(IMAGE_OPTIONS).into(imageView);
            if (VersionUtils.isLollipopOrHigher()) {
                ((LayoutParams) ((ViewGroup) mfpActivity.findViewById(R.id.content_frame)).getLayoutParams()).setBehavior(new HeaderImageScrollBehavior(mfpActivity, imageView, R.id.nested_scroll_parent));
            }
        }
    }

    public void renderNutritionInfo(MfpRecipe mfpRecipe) {
        FragmentManager childFragmentManager = this.fragment.getChildFragmentManager();
        MfpNutritionalContents nutritionalContents = mfpRecipe.getNutritionalContents();
        double doubleValue = mfpRecipe.getServings().doubleValue();
        NewNutritionFactsFragment newNutritionFactsFragment = (NewNutritionFactsFragment) childFragmentManager.findFragmentByTag(NUTRITIONAL_FRAGMENT_TAG);
        if (newNutritionFactsFragment == null) {
            NewNutritionFactsFragment newInstance = NewNutritionFactsFragment.newInstance(nutritionalContents, 2, 0, doubleValue);
            FragmentTransaction beginTransaction = childFragmentManager.beginTransaction();
            beginTransaction.add(R.id.nutrition_container, newInstance, NUTRITIONAL_FRAGMENT_TAG);
            beginTransaction.commit();
            newInstance.setNutritionFactsExpandedOrCollapsedListener(this.nutritionFactsExpandedOrCollapsedListener);
            return;
        }
        newNutritionFactsFragment.setNutritionalContentsAndMultiplier(nutritionalContents, doubleValue);
    }

    public void updateNutritionInfo(MfpRecipe mfpRecipe) {
        NutritionFactsFragmentBase nutritionFactsFragment = getNutritionFactsFragment();
        if (nutritionFactsFragment != null) {
            nutritionFactsFragment.setNutritionalContents(MfpNutritionalContents.fromIngredientList(mfpRecipe.getIngredients()));
        }
    }

    public void updateServings(double d) {
        NutritionFactsFragmentBase nutritionFactsFragment = getNutritionFactsFragment();
        if (nutritionFactsFragment != null) {
            nutritionFactsFragment.setMultiplier(d);
        }
    }

    private NutritionFactsFragmentBase getNutritionFactsFragment() {
        return (NutritionFactsFragmentBase) this.fragment.getChildFragmentManager().findFragmentByTag(NUTRITIONAL_FRAGMENT_TAG);
    }
}
