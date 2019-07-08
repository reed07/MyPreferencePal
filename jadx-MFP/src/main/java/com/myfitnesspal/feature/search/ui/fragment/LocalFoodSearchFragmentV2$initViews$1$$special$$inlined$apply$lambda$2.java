package com.myfitnesspal.feature.search.ui.fragment;

import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "sortOrder", "Lcom/myfitnesspal/feature/search/model/SortOrder;", "invoke", "com/myfitnesspal/feature/search/ui/fragment/LocalFoodSearchFragmentV2$initViews$1$sortOrderDialog$2$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
final class LocalFoodSearchFragmentV2$initViews$1$$special$$inlined$apply$lambda$2 extends Lambda implements Function1<SortOrder, Unit> {
    final /* synthetic */ LocalFoodSearchFragmentV2$initViews$1 this$0;

    LocalFoodSearchFragmentV2$initViews$1$$special$$inlined$apply$lambda$2(LocalFoodSearchFragmentV2$initViews$1 localFoodSearchFragmentV2$initViews$1) {
        this.this$0 = localFoodSearchFragmentV2$initViews$1;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SortOrder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull SortOrder sortOrder) {
        Intrinsics.checkParameterIsNotNull(sortOrder, "sortOrder");
        LocalFoodSearchViewModel.changeSortOrder$default(LocalFoodSearchFragmentV2.access$getLocalSearchViewModel$p(this.this$0.this$0), sortOrder, false, 2, null);
    }
}
