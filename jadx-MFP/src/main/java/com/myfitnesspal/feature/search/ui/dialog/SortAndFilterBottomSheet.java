package com.myfitnesspal.feature.search.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.shared.extension.FragmentExtKt;
import com.myfitnesspal.shared.ui.fragment.MfpBottomSheetFragment;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010'\u001a\u00020\u0016H\u0002J\b\u0010(\u001a\u00020\u0016H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX.¢\u0006\u0002\n\u0000RL\u0010\u0010\u001a4\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f0\rX.¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u001f8VX\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006*"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/dialog/SortAndFilterBottomSheet;", "Lcom/myfitnesspal/shared/ui/fragment/MfpBottomSheetFragment;", "()V", "contentResId", "", "getContentResId", "()I", "value", "Lcom/myfitnesspal/feature/search/model/SortOrder;", "currentSortOrder", "setCurrentSortOrder", "(Lcom/myfitnesspal/feature/search/model/SortOrder;)V", "mealSelectionToViewMap", "", "", "Landroid/widget/TextView;", "onSortAndFilterSelected", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "sortOrder", "shouldShowAllMeals", "", "getOnSortAndFilterSelected", "()Lkotlin/jvm/functions/Function2;", "setOnSortAndFilterSelected", "(Lkotlin/jvm/functions/Function2;)V", "setShouldShowAllMeals", "(Z)V", "sortOrderToViewMap", "title", "", "getTitle", "()Ljava/lang/String;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setDefaultValues", "setUpButtons", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SortAndFilterBottomSheet.kt */
public final class SortAndFilterBottomSheet extends MfpBottomSheetFragment {
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_CURRENT_MEAL_NAME = "current_meal_name";
    private static final String EXTRA_CURRENT_SORT_ORDER = "current_sort_order";
    private static final String EXTRA_SHOULD_SHOW_ALL_MEALS = "should_show_all_meals";
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public SortOrder currentSortOrder = SortOrder.RECENTLY_USED;
    private Map<Boolean, ? extends TextView> mealSelectionToViewMap;
    @Nullable
    private Function2<? super SortOrder, ? super Boolean, Unit> onSortAndFilterSelected;
    /* access modifiers changed from: private */
    public boolean shouldShowAllMeals = true;
    private Map<SortOrder, ? extends TextView> sortOrderToViewMap;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/dialog/SortAndFilterBottomSheet$Companion;", "", "()V", "EXTRA_CURRENT_MEAL_NAME", "", "EXTRA_CURRENT_SORT_ORDER", "EXTRA_SHOULD_SHOW_ALL_MEALS", "newInstance", "Lcom/myfitnesspal/feature/search/ui/dialog/SortAndFilterBottomSheet;", "currentSortOrder", "Lcom/myfitnesspal/feature/search/model/SortOrder;", "mealName", "shouldShowAllMeals", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: SortAndFilterBottomSheet.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public static /* synthetic */ SortAndFilterBottomSheet newInstance$default(Companion companion, SortOrder sortOrder, String str, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = true;
            }
            return companion.newInstance(sortOrder, str, z);
        }

        @NotNull
        public final SortAndFilterBottomSheet newInstance(@Nullable SortOrder sortOrder, @Nullable String str, boolean z) {
            return (SortAndFilterBottomSheet) FragmentExtKt.withArgs(new SortAndFilterBottomSheet(), new SortAndFilterBottomSheet$Companion$newInstance$1(sortOrder, str, z));
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public int getContentResId() {
        return R.layout.bottom_sheet_sort_and_filter;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @NotNull
    public String getTitle() {
        String string = getString(R.string.sort_and_filter);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.sort_and_filter)");
        return string;
    }

    @Nullable
    public final Function2<SortOrder, Boolean, Unit> getOnSortAndFilterSelected() {
        return this.onSortAndFilterSelected;
    }

    public final void setOnSortAndFilterSelected(@Nullable Function2<? super SortOrder, ? super Boolean, Unit> function2) {
        this.onSortAndFilterSelected = function2;
    }

    /* access modifiers changed from: private */
    public final void setCurrentSortOrder(SortOrder sortOrder) {
        Map<SortOrder, ? extends TextView> map = this.sortOrderToViewMap;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortOrderToViewMap");
        }
        TextView textView = (TextView) map.get(this.currentSortOrder);
        if (textView != null) {
            textView.setCompoundDrawables(null, null, null, null);
        }
        this.currentSortOrder = sortOrder;
        Context context = getContext();
        if (context != null) {
            Map<SortOrder, ? extends TextView> map2 = this.sortOrderToViewMap;
            if (map2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortOrderToViewMap");
            }
            TextView textView2 = (TextView) map2.get(sortOrder);
            if (textView2 != null) {
                textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context, R.drawable.ic_check_mark_blue), null);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void setShouldShowAllMeals(boolean z) {
        Map<Boolean, ? extends TextView> map = this.mealSelectionToViewMap;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mealSelectionToViewMap");
        }
        TextView textView = (TextView) map.get(Boolean.valueOf(this.shouldShowAllMeals));
        if (textView != null) {
            textView.setCompoundDrawables(null, null, null, null);
        }
        this.shouldShowAllMeals = z;
        Context context = getContext();
        if (context != null) {
            Map<Boolean, ? extends TextView> map2 = this.mealSelectionToViewMap;
            if (map2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mealSelectionToViewMap");
            }
            TextView textView2 = (TextView) map2.get(Boolean.valueOf(z));
            if (textView2 != null) {
                textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context, R.drawable.ic_check_mark_blue), null);
            }
        }
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
        setUpButtons();
        setDefaultValues();
        setPositiveAction(new SortAndFilterBottomSheet$onViewCreated$1(this));
    }

    private final void setUpButtons() {
        ((TextView) _$_findCachedViewById(R.id.sortMostRecent)).setOnClickListener(new SortAndFilterBottomSheet$setUpButtons$1(this));
        ((TextView) _$_findCachedViewById(R.id.sortMostFrequent)).setOnClickListener(new SortAndFilterBottomSheet$setUpButtons$2(this));
        ((TextView) _$_findCachedViewById(R.id.sortAlphabeticallyAscending)).setOnClickListener(new SortAndFilterBottomSheet$setUpButtons$3(this));
        ((TextView) _$_findCachedViewById(R.id.sortAlphabeticallyDescending)).setOnClickListener(new SortAndFilterBottomSheet$setUpButtons$4(this));
        this.sortOrderToViewMap = MapsKt.mapOf(TuplesKt.to(SortOrder.RECENTLY_USED, (TextView) _$_findCachedViewById(R.id.sortMostRecent)), TuplesKt.to(SortOrder.FREQUENTLY_USED, (TextView) _$_findCachedViewById(R.id.sortMostFrequent)), TuplesKt.to(SortOrder.ALPHABETICAL_ASCENDING, (TextView) _$_findCachedViewById(R.id.sortAlphabeticallyAscending)), TuplesKt.to(SortOrder.ALPHABETICAL_DESCENDING, (TextView) _$_findCachedViewById(R.id.sortAlphabeticallyDescending)));
        ((TextView) _$_findCachedViewById(R.id.filterByMeal)).setOnClickListener(new SortAndFilterBottomSheet$setUpButtons$5(this));
        ((TextView) _$_findCachedViewById(R.id.filterByAllMeals)).setOnClickListener(new SortAndFilterBottomSheet$setUpButtons$6(this));
        this.mealSelectionToViewMap = MapsKt.mapOf(TuplesKt.to(Boolean.valueOf(false), (TextView) _$_findCachedViewById(R.id.filterByMeal)), TuplesKt.to(Boolean.valueOf(true), (TextView) _$_findCachedViewById(R.id.filterByAllMeals)));
    }

    private final void setDefaultValues() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable(EXTRA_CURRENT_SORT_ORDER);
            if (!(serializable instanceof SortOrder)) {
                serializable = null;
            }
            SortOrder sortOrder = (SortOrder) serializable;
            if (sortOrder == null) {
                sortOrder = SortOrder.RECENTLY_USED;
            }
            setCurrentSortOrder(sortOrder);
            String string = arguments.getString(EXTRA_CURRENT_MEAL_NAME);
            if (string != null) {
                TextView textView = (TextView) _$_findCachedViewById(R.id.filterByMeal);
                Intrinsics.checkExpressionValueIsNotNull(textView, "filterByMeal");
                textView.setText(string);
                setShouldShowAllMeals(arguments.getBoolean(EXTRA_SHOULD_SHOW_ALL_MEALS));
                return;
            }
            Group group = (Group) _$_findCachedViewById(R.id.filterSegmentGroup);
            Intrinsics.checkExpressionValueIsNotNull(group, "filterSegmentGroup");
            group.setVisibility(8);
            return;
        }
        Group group2 = (Group) _$_findCachedViewById(R.id.filterSegmentGroup);
        Intrinsics.checkExpressionValueIsNotNull(group2, "filterSegmentGroup");
        group2.setVisibility(8);
    }
}
