package com.myfitnesspal.feature.search.ui.activity;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.ClearableEditText;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\f¸\u0006\u0000"}, d2 = {"com/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$initViewPager$1$1", "Landroid/support/v4/view/ViewPager$OnPageChangeListener;", "onPageScrollStateChanged", "", "state", "", "onPageScrolled", "position", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodSearchActivityV2.kt */
public final class FoodSearchActivityV2$initViewPager$$inlined$let$lambda$1 implements OnPageChangeListener {
    final /* synthetic */ FoodSearchActivityV2 this$0;

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    FoodSearchActivityV2$initViewPager$$inlined$let$lambda$1(FoodSearchActivityV2 foodSearchActivityV2) {
        this.this$0 = foodSearchActivityV2;
    }

    public void onPageSelected(int i) {
        this.this$0.setShowingOnlineSearch(false);
        ClearableEditText clearableEditText = (ClearableEditText) this.this$0._$_findCachedViewById(R.id.searchEditText);
        ViewPagerAdapter access$getViewPagerAdapter$p = this.this$0.viewPagerAdapter;
        clearableEditText.setHint(access$getViewPagerAdapter$p != null ? access$getViewPagerAdapter$p.getHintForPosition(i) : R.string.search_for_a_food);
        FoodSearchActivityV2 foodSearchActivityV2 = this.this$0;
        ClearableEditText clearableEditText2 = (ClearableEditText) foodSearchActivityV2._$_findCachedViewById(R.id.searchEditText);
        Intrinsics.checkExpressionValueIsNotNull(clearableEditText2, "searchEditText");
        foodSearchActivityV2.updateLocalFragmentQueryString(String.valueOf(clearableEditText2.getText()));
    }
}
