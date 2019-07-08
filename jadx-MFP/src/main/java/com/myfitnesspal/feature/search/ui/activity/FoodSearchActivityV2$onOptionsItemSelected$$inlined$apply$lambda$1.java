package com.myfitnesspal.feature.search.ui.activity;

import com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet.AddItemOption;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "selectedItem", "Lcom/myfitnesspal/feature/search/ui/dialog/AddItemBottomSheet$AddItemOption;", "invoke", "com/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$onOptionsItemSelected$dialog$1$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodSearchActivityV2.kt */
final class FoodSearchActivityV2$onOptionsItemSelected$$inlined$apply$lambda$1 extends Lambda implements Function1<AddItemOption, Unit> {
    final /* synthetic */ FoodSearchActivityV2 this$0;

    FoodSearchActivityV2$onOptionsItemSelected$$inlined$apply$lambda$1(FoodSearchActivityV2 foodSearchActivityV2) {
        this.this$0 = foodSearchActivityV2;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((AddItemOption) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull AddItemOption addItemOption) {
        Intrinsics.checkParameterIsNotNull(addItemOption, "selectedItem");
        FoodSearchActivityV2.access$getViewModel$p(this.this$0).reportToolbarPlusItemSelected(addItemOption);
        switch (addItemOption) {
            case QUICK_ADD:
                this.this$0.navigateToQuickAdd();
                return;
            case SCAN_BARCODE:
                this.this$0.navigateToScanBarcode();
                return;
            case CREATE_RECIPE:
                this.this$0.navigateToCreateRecipe();
                return;
            case CREATE_MEAL:
                this.this$0.navigateToCreateMeal();
                return;
            case CREATE_FOOD:
                this.this$0.navigateToCreateFood();
                return;
            default:
                return;
        }
    }
}
