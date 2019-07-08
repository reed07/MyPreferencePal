package com.myfitnesspal.feature.nutrition.service.renderer;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.nutrition.model.FoodListItem;
import com.myfitnesspal.feature.nutrition.service.NutritionAnalyticsHelper;
import com.myfitnesspal.feature.nutrition.ui.adapter.FoodListAdapter;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiniFoodListsChartRendererImpl extends FoodListsChartRendererBase {
    private static final int MAX_ITEMS_TO_SHOW = 5;
    Map<String, Boolean> analyticsEventTrackerMap = new HashMap();
    Lazy<NutritionAnalyticsHelper> nutritionAnalyticsHelper;

    /* access modifiers changed from: protected */
    public int getSpinnerContainerId() {
        return R.id.mini_food_list_spinner_container;
    }

    public MiniFoodListsChartRendererImpl(Context context, Lazy<CoreChartRendererBaseConstructorArgs> lazy, Lazy<NutritionAnalyticsHelper> lazy2) {
        super(context, lazy);
        this.nutritionAnalyticsHelper = lazy2;
    }

    /* access modifiers changed from: protected */
    public void addFoodListItemsView(ViewGroup viewGroup, View view, int i) {
        setClickableTextState(viewGroup, i);
        ((ViewGroup) ViewUtils.findById(viewGroup, R.id.content_container)).addView(view);
    }

    /* access modifiers changed from: protected */
    public View getViewForFoodList(List<FoodListItem> list, int i, int i2) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.context).inflate(R.layout.mini_food_list_items, null);
        int min = Math.min(CollectionUtils.size((Collection<?>) list), 5);
        FoodListAdapter foodListAdapter = new FoodListAdapter(this.context, 0, list, true);
        for (int i3 = 0; i3 < min; i3++) {
            viewGroup.addView(foodListAdapter.getView(i3, null, viewGroup));
        }
        viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(viewGroup, i, i2) {
            private final /* synthetic */ ViewGroup f$1;
            private final /* synthetic */ int f$2;
            private final /* synthetic */ int f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onGlobalLayout() {
                MiniFoodListsChartRendererImpl.lambda$getViewForFoodList$0(MiniFoodListsChartRendererImpl.this, this.f$1, this.f$2, this.f$3);
            }
        });
        return viewGroup;
    }

    public static /* synthetic */ void lambda$getViewForFoodList$0(MiniFoodListsChartRendererImpl miniFoodListsChartRendererImpl, ViewGroup viewGroup, int i, int i2) {
        View nestScrollViewInParentHierarchy = miniFoodListsChartRendererImpl.getNestScrollViewInParentHierarchy(viewGroup);
        if (nestScrollViewInParentHierarchy instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) nestScrollViewInParentHierarchy;
            Rect rect = new Rect();
            nestedScrollView.getHitRect(rect);
            miniFoodListsChartRendererImpl.checkVisibility(rect, viewGroup, viewGroup.getChildCount(), i, i2);
            miniFoodListsChartRendererImpl.setScrollListenerForNutrients(rect, viewGroup, nestedScrollView, i, i2);
        }
    }

    private void setClickableTextState(ViewGroup viewGroup, int i) {
        TextView textView = (TextView) ViewUtils.findById(viewGroup, R.id.clickable_text);
        ViewUtils.setVisible(i > 5 || i == 0, textView);
        if (i == 0) {
            TextViewUtils.setTextColor(textView, getColor(R.color.black_hint_text));
            textView.setText(this.context.getString(R.string.food_list_no_food_logged));
            textView.setOnClickListener(null);
        }
    }

    private void setScrollListenerForNutrients(Rect rect, ViewGroup viewGroup, NestedScrollView nestedScrollView, int i, int i2) {
        $$Lambda$MiniFoodListsChartRendererImpl$7k5O6fr3YhwFD6OY8UeyallXV2U r0 = new OnScrollChangeListener(rect, viewGroup, i, i2) {
            private final /* synthetic */ Rect f$1;
            private final /* synthetic */ ViewGroup f$2;
            private final /* synthetic */ int f$3;
            private final /* synthetic */ int f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                MiniFoodListsChartRendererImpl.this.checkVisibility(this.f$1, this.f$2, this.f$2.getChildCount(), this.f$3, this.f$4);
            }
        };
        nestedScrollView.setOnScrollChangeListener(r0);
    }

    /* access modifiers changed from: private */
    public void checkVisibility(Rect rect, ViewGroup viewGroup, int i, int i2, int i3) {
        String identifierForNutrientIndex = NutritionalValues.getIdentifierForNutrientIndex(i2);
        if ((!this.analyticsEventTrackerMap.containsKey(identifierForNutrientIndex) || !((Boolean) this.analyticsEventTrackerMap.get(identifierForNutrientIndex)).booleanValue()) && viewGroup != null) {
            int i4 = i - 1;
            if (viewGroup.getChildAt(i4) != null && viewGroup.getChildAt(i4).getGlobalVisibleRect(rect)) {
                this.analyticsEventTrackerMap.put(identifierForNutrientIndex, Boolean.valueOf(true));
                if (i3 <= 0) {
                    ((NutritionAnalyticsHelper) this.nutritionAnalyticsHelper.get()).reportFoodAnalysisViewed(identifierForNutrientIndex);
                } else {
                    ((NutritionAnalyticsHelper) this.nutritionAnalyticsHelper.get()).reportFoodAnalysisViewed(NutritionalValues.getIdentifierForNutrientIndex(9));
                    ((NutritionAnalyticsHelper) this.nutritionAnalyticsHelper.get()).reportFoodAnalysisViewed(NutritionalValues.getIdentifierForNutrientIndex(1));
                    ((NutritionAnalyticsHelper) this.nutritionAnalyticsHelper.get()).reportFoodAnalysisViewed(NutritionalValues.getIdentifierForNutrientIndex(12));
                }
            }
        }
    }

    private View getNestScrollViewInParentHierarchy(View view) {
        if (view instanceof NestedScrollView) {
            return view;
        }
        View nestScrollViewInParentHierarchy = getNestScrollViewInParentHierarchy((View) view.getParent());
        if (nestScrollViewInParentHierarchy != null) {
            view = nestScrollViewInParentHierarchy;
        }
        return view;
    }
}
