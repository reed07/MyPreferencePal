package com.myfitnesspal.shared.ui.fragment;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: MfpBottomSheetFragment.kt */
final class MfpBottomSheetFragment$negativeAction$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ MfpBottomSheetFragment this$0;

    MfpBottomSheetFragment$negativeAction$1(MfpBottomSheetFragment mfpBottomSheetFragment) {
        this.this$0 = mfpBottomSheetFragment;
        super(0);
    }

    public final void invoke() {
        this.this$0.getDialog().dismiss();
    }
}
