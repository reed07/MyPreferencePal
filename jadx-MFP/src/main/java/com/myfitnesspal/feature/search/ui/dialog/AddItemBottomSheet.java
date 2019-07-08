package com.myfitnesspal.feature.search.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.recipes.ui.activity.RecipesAndFoods;
import com.myfitnesspal.shared.ui.fragment.MfpBottomSheetFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\tH\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R7\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001d"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/dialog/AddItemBottomSheet;", "Lcom/myfitnesspal/shared/ui/fragment/MfpBottomSheetFragment;", "()V", "contentResId", "", "getContentResId", "()I", "onItemSelected", "Lkotlin/Function1;", "Lcom/myfitnesspal/feature/search/ui/dialog/AddItemBottomSheet$AddItemOption;", "Lkotlin/ParameterName;", "name", "item", "", "getOnItemSelected", "()Lkotlin/jvm/functions/Function1;", "setOnItemSelected", "(Lkotlin/jvm/functions/Function1;)V", "title", "", "getTitle", "()Ljava/lang/String;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "triggerAndDismiss", "AddItemOption", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AddItemBottomSheet.kt */
public final class AddItemBottomSheet extends MfpBottomSheetFragment {
    private HashMap _$_findViewCache;
    @Nullable
    private Function1<? super AddItemOption, Unit> onItemSelected;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/dialog/AddItemBottomSheet$AddItemOption;", "", "anltName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getAnltName", "()Ljava/lang/String;", "QUICK_ADD", "SCAN_BARCODE", "CREATE_RECIPE", "CREATE_MEAL", "CREATE_FOOD", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AddItemBottomSheet.kt */
    public enum AddItemOption {
        QUICK_ADD("quick_add"),
        SCAN_BARCODE("scan_barcode"),
        CREATE_RECIPE(RecipesAndFoods.EXTRA_CREATE_RECIPE),
        CREATE_MEAL("create_meal"),
        CREATE_FOOD("create_food");
        
        @NotNull
        private final String anltName;

        protected AddItemOption(String str) {
            Intrinsics.checkParameterIsNotNull(str, "anltName");
            this.anltName = str;
        }

        @NotNull
        public final String getAnltName() {
            return this.anltName;
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
        return R.layout.bottom_sheet_search_add_item;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @NotNull
    public String getTitle() {
        String string = getString(R.string.add_item);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.add_item)");
        return string;
    }

    @Nullable
    public final Function1<AddItemOption, Unit> getOnItemSelected() {
        return this.onItemSelected;
    }

    public final void setOnItemSelected(@Nullable Function1<? super AddItemOption, Unit> function1) {
        this.onItemSelected = function1;
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
        ((TextView) _$_findCachedViewById(R.id.quickAddButton)).setOnClickListener(new AddItemBottomSheet$onViewCreated$1(this));
        ((TextView) _$_findCachedViewById(R.id.scanBarcodeButton)).setOnClickListener(new AddItemBottomSheet$onViewCreated$2(this));
        ((TextView) _$_findCachedViewById(R.id.createRecipeButton)).setOnClickListener(new AddItemBottomSheet$onViewCreated$3(this));
        ((TextView) _$_findCachedViewById(R.id.createMealButton)).setOnClickListener(new AddItemBottomSheet$onViewCreated$4(this));
        ((TextView) _$_findCachedViewById(R.id.createFoodButton)).setOnClickListener(new AddItemBottomSheet$onViewCreated$5(this));
    }

    /* access modifiers changed from: private */
    public final void triggerAndDismiss(AddItemOption addItemOption) {
        Function1<? super AddItemOption, Unit> function1 = this.onItemSelected;
        if (function1 != null) {
            Unit unit = (Unit) function1.invoke(addItemOption);
        }
        dismiss();
    }
}
