package com.myfitnesspal.feature.search.ui.dialog;

import android.os.Bundle;
import com.myfitnesspal.feature.search.model.SortOrder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Landroid/os/Bundle;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: SortAndFilterBottomSheet.kt */
final class SortAndFilterBottomSheet$Companion$newInstance$1 extends Lambda implements Function1<Bundle, Unit> {
    final /* synthetic */ SortOrder $currentSortOrder;
    final /* synthetic */ String $mealName;
    final /* synthetic */ boolean $shouldShowAllMeals;

    SortAndFilterBottomSheet$Companion$newInstance$1(SortOrder sortOrder, String str, boolean z) {
        this.$currentSortOrder = sortOrder;
        this.$mealName = str;
        this.$shouldShowAllMeals = z;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Bundle) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "receiver$0");
        bundle.putSerializable("current_sort_order", this.$currentSortOrder);
        bundle.putSerializable("current_meal_name", this.$mealName);
        bundle.putBoolean("should_show_all_meals", this.$shouldShowAllMeals);
    }
}
