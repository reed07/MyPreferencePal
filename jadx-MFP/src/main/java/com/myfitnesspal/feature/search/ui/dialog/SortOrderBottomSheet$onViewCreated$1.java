package com.myfitnesspal.feature.search.ui.dialog;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: SortOrderBottomSheet.kt */
final class SortOrderBottomSheet$onViewCreated$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ SortOrderBottomSheet this$0;

    SortOrderBottomSheet$onViewCreated$1(SortOrderBottomSheet sortOrderBottomSheet) {
        this.this$0 = sortOrderBottomSheet;
        super(0);
    }

    public final void invoke() {
        Function1 onSortOrderSelected = this.this$0.getOnSortOrderSelected();
        if (onSortOrderSelected != null) {
            Unit unit = (Unit) onSortOrderSelected.invoke(this.this$0.currentSortOrder);
        }
        this.this$0.dismiss();
    }
}
