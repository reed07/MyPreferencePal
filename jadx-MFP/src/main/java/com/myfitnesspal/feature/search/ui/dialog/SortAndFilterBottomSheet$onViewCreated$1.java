package com.myfitnesspal.feature.search.ui.dialog;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: SortAndFilterBottomSheet.kt */
final class SortAndFilterBottomSheet$onViewCreated$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ SortAndFilterBottomSheet this$0;

    SortAndFilterBottomSheet$onViewCreated$1(SortAndFilterBottomSheet sortAndFilterBottomSheet) {
        this.this$0 = sortAndFilterBottomSheet;
        super(0);
    }

    public final void invoke() {
        Function2 onSortAndFilterSelected = this.this$0.getOnSortAndFilterSelected();
        if (onSortAndFilterSelected != null) {
            Unit unit = (Unit) onSortAndFilterSelected.invoke(this.this$0.currentSortOrder, Boolean.valueOf(this.this$0.shouldShowAllMeals));
        }
        this.this$0.dismiss();
    }
}
