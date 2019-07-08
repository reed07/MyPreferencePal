package com.myfitnesspal.feature.search.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.search.util.SortOrderHelper;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.SearchTabs;
import com.myfitnesspal.shared.model.SortOrderCheckableListItem;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u0000 !2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0012H\u0014J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J,\u0010\u0018\u001a\u00020\u00192\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 H\u0014R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\""}, d2 = {"Lcom/myfitnesspal/feature/search/ui/dialog/SortOrderDialog;", "Lcom/myfitnesspal/feature/search/ui/dialog/SortFilterDialogBase;", "Lcom/myfitnesspal/shared/model/SortOrderCheckableListItem;", "()V", "sortOrderHelper", "Lcom/myfitnesspal/feature/search/util/SortOrderHelper;", "getSortOrderHelper", "()Lcom/myfitnesspal/feature/search/util/SortOrderHelper;", "setSortOrderHelper", "(Lcom/myfitnesspal/feature/search/util/SortOrderHelper;)V", "sortOrderSubject", "Lio/reactivex/subjects/PublishSubject;", "Lcom/myfitnesspal/feature/search/model/SortOrder;", "getSortOrderSubject", "()Lio/reactivex/subjects/PublishSubject;", "getItemsList", "", "activeTabId", "", "getTitleResId", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onItemClick", "", "parent", "Landroid/widget/AdapterView;", "view", "Landroid/view/View;", "position", "id", "", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SortOrderDialog.kt */
public final class SortOrderDialog extends SortFilterDialogBase<SortOrderCheckableListItem> {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final SortOrder[] MOST_USED_ORDER = {SortOrder.RECENTLY_USED, SortOrder.ALPHABETICAL_ASCENDING, SortOrder.ALPHABETICAL_DESCENDING};
    /* access modifiers changed from: private */
    @NotNull
    public static final SortOrder[] MY_FOODS_ORDER = {SortOrder.DATE_DESCENDING, SortOrder.ALPHABETICAL_ASCENDING, SortOrder.ALPHABETICAL_DESCENDING};
    /* access modifiers changed from: private */
    @NotNull
    public static final SortOrder[] RECENTLY_USED_ORDER = {SortOrder.RECENTLY_USED, SortOrder.ALPHABETICAL_ASCENDING, SortOrder.ALPHABETICAL_DESCENDING};
    @NotNull
    public static final String TAG = "SortOrderDialog";
    private HashMap _$_findViewCache;
    @Inject
    @NotNull
    public SortOrderHelper sortOrderHelper;
    @NotNull
    private final PublishSubject<SortOrder> sortOrderSubject;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000eH\u0007R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0019\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u000e\u0010\r\u001a\u00020\u000eXT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/dialog/SortOrderDialog$Companion;", "", "()V", "MOST_USED_ORDER", "", "Lcom/myfitnesspal/feature/search/model/SortOrder;", "getMOST_USED_ORDER", "()[Lcom/myfitnesspal/feature/search/model/SortOrder;", "[Lcom/myfitnesspal/feature/search/model/SortOrder;", "MY_FOODS_ORDER", "getMY_FOODS_ORDER", "RECENTLY_USED_ORDER", "getRECENTLY_USED_ORDER", "TAG", "", "newInstance", "Lcom/myfitnesspal/feature/search/ui/dialog/SortOrderDialog;", "activeTabId", "", "mealName", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: SortOrderDialog.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SortOrder[] getMOST_USED_ORDER() {
            return SortOrderDialog.MOST_USED_ORDER;
        }

        @NotNull
        public final SortOrder[] getRECENTLY_USED_ORDER() {
            return SortOrderDialog.RECENTLY_USED_ORDER;
        }

        @NotNull
        public final SortOrder[] getMY_FOODS_ORDER() {
            return SortOrderDialog.MY_FOODS_ORDER;
        }

        @JvmStatic
        @NotNull
        public final SortOrderDialog newInstance(int i, @NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, Extras.MEAL_NAME);
            SortOrderDialog sortOrderDialog = new SortOrderDialog();
            SortFilterDialogBase.setArgumentsToFragment(sortOrderDialog, i, str);
            return sortOrderDialog;
        }
    }

    @JvmStatic
    @NotNull
    public static final SortOrderDialog newInstance(int i, @NotNull String str) {
        return Companion.newInstance(i, str);
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

    /* access modifiers changed from: protected */
    public int getTitleResId() {
        return R.string.sort_order;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public SortOrderDialog() {
        PublishSubject<SortOrder> create = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "PublishSubject.create<SortOrder>()");
        this.sortOrderSubject = create;
    }

    @NotNull
    public final SortOrderHelper getSortOrderHelper() {
        SortOrderHelper sortOrderHelper2 = this.sortOrderHelper;
        if (sortOrderHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortOrderHelper");
        }
        return sortOrderHelper2;
    }

    public final void setSortOrderHelper(@NotNull SortOrderHelper sortOrderHelper2) {
        Intrinsics.checkParameterIsNotNull(sortOrderHelper2, "<set-?>");
        this.sortOrderHelper = sortOrderHelper2;
    }

    @NotNull
    public final PublishSubject<SortOrder> getSortOrderSubject() {
        return this.sortOrderSubject;
    }

    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        component().inject(this);
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        Intrinsics.checkExpressionValueIsNotNull(onCreateDialog, "super.onCreateDialog(savedInstanceState)");
        return onCreateDialog;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public List<SortOrderCheckableListItem> getItemsList(int i) {
        int i2;
        SortOrder[] sortOrderArr;
        int i3;
        ArrayList arrayList = new ArrayList();
        switch (i) {
            case SearchTabs.TAB_FREQUENT_FOODS /*6000*/:
                sortOrderArr = MOST_USED_ORDER;
                i2 = R.string.mostUsed;
                break;
            case 6001:
                sortOrderArr = RECENTLY_USED_ORDER;
                i2 = R.string.sort_recent;
                break;
            case 6002:
            case 6003:
            case 6004:
                sortOrderArr = MY_FOODS_ORDER;
                i2 = R.string.date_created;
                break;
            default:
                throw new IllegalArgumentException("Invalid tab id!");
        }
        SortOrderHelper sortOrderHelper2 = this.sortOrderHelper;
        if (sortOrderHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortOrderHelper");
        }
        SortOrder currentSortOrderForTab = sortOrderHelper2.getCurrentSortOrderForTab(i);
        int length = sortOrderArr.length;
        for (int i4 = 0; i4 < length; i4++) {
            SortOrder sortOrder = sortOrderArr[i4];
            boolean z = sortOrder == currentSortOrderForTab;
            if (i4 == 0) {
                i3 = i2;
            } else if (sortOrder == SortOrder.ALPHABETICAL_ASCENDING) {
                i3 = R.string.sort_asc;
            } else if (sortOrder == SortOrder.ALPHABETICAL_DESCENDING) {
                i3 = R.string.sort_desc;
            } else {
                throw new IllegalStateException("Illegal sort order id".toString());
            }
            String string = getString(i3);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(itemTextResId)");
            arrayList.add(new SortOrderCheckableListItem(string, z, sortOrder));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onItemClick(@NotNull AdapterView<?> adapterView, @NotNull View view, int i, long j) {
        Intrinsics.checkParameterIsNotNull(adapterView, "parent");
        Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
        SortOrder sortOrderId = ((SortOrderCheckableListItem) this.checkableListItems.get(i)).getSortOrderId();
        SortOrderHelper sortOrderHelper2 = this.sortOrderHelper;
        if (sortOrderHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortOrderHelper");
        }
        sortOrderHelper2.setCurrentSortOrderForSelectorButton(this.activeTabId, sortOrderId);
        SortOrderHelper.reportSortOrderChangedEvent((AnalyticsService) this.analyticsService.get(), this.activeTabId, sortOrderId, this.mealName);
        this.sortOrderSubject.onNext(SortOrder.NONE);
    }
}
