package com.myfitnesspal.feature.search.ui.constants;

import android.support.annotation.StringRes;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.FoodSearchView.TabName;
import com.myfitnesspal.shared.constants.Constants.Analytics.ListType;
import com.myfitnesspal.shared.constants.Constants.SearchTabs;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0001\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0015B'\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fj\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0016"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "", "tabId", "", "labelResId", "analyticsTabName", "", "listType", "(Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;)V", "getAnalyticsTabName", "()Ljava/lang/String;", "getLabelResId", "()I", "getListType", "getTabId", "ALL", "RECENT", "FREQUENT", "MY_FOODS", "MEALS", "RECIPES", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodSearchTab.kt */
public enum FoodSearchTab {
    ALL(SearchTabs.TAB_ALL, R.string.tab_title_all, "history", "history"),
    RECENT(6001, R.string.recent, "recent", "recent"),
    FREQUENT(SearchTabs.TAB_FREQUENT_FOODS, R.string.frequent, "frequent", "frequent"),
    MY_FOODS(6002, R.string.myFoods, TabName.MYFOODS, ListType.MY_FOODS),
    MEALS(6003, R.string.meals, "meals", "meals"),
    RECIPES(6004, R.string.recipes, "recipes", "recipes");
    
    public static final Companion Companion = null;
    @NotNull
    private final String analyticsTabName;
    @StringRes
    private final int labelResId;
    @NotNull
    private final String listType;
    private final int tabId;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab$Companion;", "", "()V", "fromTabId", "Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "tabId", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodSearchTab.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @Nullable
        public final FoodSearchTab fromTabId(int i) {
            if (i == 6008) {
                return FoodSearchTab.ALL;
            }
            switch (i) {
                case SearchTabs.TAB_FREQUENT_FOODS /*6000*/:
                    return FoodSearchTab.FREQUENT;
                case 6001:
                    return FoodSearchTab.RECENT;
                case 6002:
                    return FoodSearchTab.MY_FOODS;
                case 6003:
                    return FoodSearchTab.MEALS;
                case 6004:
                    return FoodSearchTab.RECIPES;
                default:
                    return null;
            }
        }
    }

    @JvmStatic
    @Nullable
    public static final FoodSearchTab fromTabId(int i) {
        return Companion.fromTabId(i);
    }

    protected FoodSearchTab(int i, int i2, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "analyticsTabName");
        Intrinsics.checkParameterIsNotNull(str2, "listType");
        this.tabId = i;
        this.labelResId = i2;
        this.analyticsTabName = str;
        this.listType = str2;
    }

    public final int getTabId() {
        return this.tabId;
    }

    public final int getLabelResId() {
        return this.labelResId;
    }

    @NotNull
    public final String getAnalyticsTabName() {
        return this.analyticsTabName;
    }

    @NotNull
    public final String getListType() {
        return this.listType;
    }

    static {
        Companion = new Companion(null);
    }
}
