package com.myfitnesspal.feature.search.ui.dialog;

import android.content.Context;
import android.os.Bundle;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u0011H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\n\u0010\u000bR7\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00180\u0017X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006$"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/dialog/SortOrderBottomSheet;", "Lcom/myfitnesspal/shared/ui/fragment/MfpBottomSheetFragment;", "()V", "contentResId", "", "getContentResId", "()I", "value", "Lcom/myfitnesspal/feature/search/model/SortOrder;", "currentSortOrder", "setCurrentSortOrder", "(Lcom/myfitnesspal/feature/search/model/SortOrder;)V", "onSortOrderSelected", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "sortOrder", "", "getOnSortOrderSelected", "()Lkotlin/jvm/functions/Function1;", "setOnSortOrderSelected", "(Lkotlin/jvm/functions/Function1;)V", "sortOrderToViewMap", "", "Landroid/widget/TextView;", "title", "", "getTitle", "()Ljava/lang/String;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setUpButtons", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SortOrderBottomSheet.kt */
public final class SortOrderBottomSheet extends MfpBottomSheetFragment {
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_CURRENT_SORT_ORDER = "current_sort_order";
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public SortOrder currentSortOrder = SortOrder.NONE;
    @Nullable
    private Function1<? super SortOrder, Unit> onSortOrderSelected;
    private Map<SortOrder, ? extends TextView> sortOrderToViewMap;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/dialog/SortOrderBottomSheet$Companion;", "", "()V", "EXTRA_CURRENT_SORT_ORDER", "", "newInstance", "Lcom/myfitnesspal/feature/search/ui/dialog/SortOrderBottomSheet;", "sortOrder", "Lcom/myfitnesspal/feature/search/model/SortOrder;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: SortOrderBottomSheet.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SortOrderBottomSheet newInstance(@Nullable SortOrder sortOrder) {
            return (SortOrderBottomSheet) FragmentExtKt.withArgs(new SortOrderBottomSheet(), new SortOrderBottomSheet$Companion$newInstance$1(sortOrder));
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
        return R.layout.bottom_sheet_search_sort;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @NotNull
    public String getTitle() {
        String string = getString(R.string.sort);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.sort)");
        return string;
    }

    @Nullable
    public final Function1<SortOrder, Unit> getOnSortOrderSelected() {
        return this.onSortOrderSelected;
    }

    public final void setOnSortOrderSelected(@Nullable Function1<? super SortOrder, Unit> function1) {
        this.onSortOrderSelected = function1;
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

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
        setUpButtons();
        this.sortOrderToViewMap = MapsKt.mapOf(TuplesKt.to(SortOrder.DATE_DESCENDING, (TextView) _$_findCachedViewById(R.id.sortDateAscending)), TuplesKt.to(SortOrder.ALPHABETICAL_ASCENDING, (TextView) _$_findCachedViewById(R.id.sortAlphabeticallyAscending)), TuplesKt.to(SortOrder.ALPHABETICAL_DESCENDING, (TextView) _$_findCachedViewById(R.id.sortAlphabeticallyDescending)));
        Bundle arguments = getArguments();
        Serializable serializable = arguments != null ? arguments.getSerializable(EXTRA_CURRENT_SORT_ORDER) : null;
        if (!(serializable instanceof SortOrder)) {
            serializable = null;
        }
        SortOrder sortOrder = (SortOrder) serializable;
        if (sortOrder == null) {
            sortOrder = SortOrder.DATE_DESCENDING;
        }
        setCurrentSortOrder(sortOrder);
        setPositiveAction(new SortOrderBottomSheet$onViewCreated$1(this));
    }

    private final void setUpButtons() {
        ((TextView) _$_findCachedViewById(R.id.sortDateAscending)).setOnClickListener(new SortOrderBottomSheet$setUpButtons$1(this));
        ((TextView) _$_findCachedViewById(R.id.sortAlphabeticallyAscending)).setOnClickListener(new SortOrderBottomSheet$setUpButtons$2(this));
        ((TextView) _$_findCachedViewById(R.id.sortAlphabeticallyDescending)).setOnClickListener(new SortOrderBottomSheet$setUpButtons$3(this));
    }
}
