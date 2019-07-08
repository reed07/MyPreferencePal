package com.myfitnesspal.feature.addentry.util;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.search.ui.adapter.FoodSearchAdapter;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class PairedFoodsHelper {
    private final Lazy<AnalyticsService> analyticsService;
    private FoodSearchAdapter foodSearchAdapter;
    private final Lazy<ImageService> imageService;
    private final Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private final Lazy<MealUtil> mealHelperUtil;
    private final int mealId;
    private final Lazy<MultiAddFoodHelper> multiAddFoodHelper;
    private final long originalFoodId;
    private final String originalUid;
    private OnClickListener pairedItemOnClickListener = $$Lambda$PairedFoodsHelper$Bx4aIF9PTqf_AfNDoWF6wCS3gs.INSTANCE;
    private final Lazy<SearchService> searchService;
    private final Lazy<UserEnergyService> userEnergyService;

    public PairedFoodsHelper(long j, int i, String str, Lazy<ImageService> lazy, Lazy<UserEnergyService> lazy2, Lazy<MealUtil> lazy3, Lazy<LocalizedStringsUtil> lazy4, Lazy<MultiAddFoodHelper> lazy5, Lazy<SearchService> lazy6, Lazy<AnalyticsService> lazy7) {
        this.originalFoodId = j;
        this.originalUid = str;
        this.mealId = i;
        this.imageService = lazy;
        this.userEnergyService = lazy2;
        this.mealHelperUtil = lazy3;
        this.localizedStringsUtil = lazy4;
        this.multiAddFoodHelper = lazy5;
        this.searchService = lazy6;
        this.analyticsService = lazy7;
    }

    public void addPairedFoods(Activity activity) {
        View findViewById = activity.findViewById(R.id.paired_foods_container);
        if (findViewById != null) {
            ((SearchService) this.searchService.get()).fetchPairedFoods(this.originalFoodId, this.mealId, this.originalUid, new Function1(findViewById, activity) {
                private final /* synthetic */ View f$1;
                private final /* synthetic */ Activity f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void execute(Object obj) {
                    PairedFoodsHelper.lambda$addPairedFoods$0(PairedFoodsHelper.this, this.f$1, this.f$2, (List) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$addPairedFoods$0(PairedFoodsHelper pairedFoodsHelper, View view, Activity activity, List list) throws RuntimeException {
        int size = CollectionUtils.size((Collection<?>) list);
        if (size != 0) {
            ViewUtils.setVisible(view);
            FoodSearchAdapter foodSearchAdapter2 = new FoodSearchAdapter(activity, pairedFoodsHelper.imageService, pairedFoodsHelper.userEnergyService, pairedFoodsHelper.mealHelperUtil, pairedFoodsHelper.localizedStringsUtil, pairedFoodsHelper.multiAddFoodHelper, true);
            pairedFoodsHelper.foodSearchAdapter = foodSearchAdapter2;
            pairedFoodsHelper.foodSearchAdapter.addAll(list);
            ViewGroup viewGroup = (ViewGroup) ViewUtils.findById(view, R.id.food_item_container);
            viewGroup.removeAllViews();
            for (int i = 0; i < size; i++) {
                View view2 = pairedFoodsHelper.foodSearchAdapter.getView(i, null, viewGroup);
                viewGroup.addView(view2);
                view2.setOnClickListener(pairedFoodsHelper.pairedItemOnClickListener);
            }
            ((AnalyticsService) pairedFoodsHelper.analyticsService.get()).reportEvent(Events.PAIRED_FOOD_DISPLAYED);
        }
    }

    public Set<DiaryEntryCellModel> getSelectedPairedFoods() {
        FoodSearchAdapter foodSearchAdapter2 = this.foodSearchAdapter;
        if (foodSearchAdapter2 == null) {
            return null;
        }
        return foodSearchAdapter2.getSelectedPairedFoods();
    }
}
