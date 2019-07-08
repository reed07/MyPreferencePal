package com.myfitnesspal.shared.ui.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.widget.FrameLayout;
import com.myfitnesspal.android.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "onShow"}, k = 3, mv = {1, 1, 13})
/* compiled from: MfpBottomSheetFragment.kt */
final class MfpBottomSheetFragment$onCreateDialog$1 implements OnShowListener {
    final /* synthetic */ Dialog $dialog;
    final /* synthetic */ MfpBottomSheetFragment this$0;

    MfpBottomSheetFragment$onCreateDialog$1(MfpBottomSheetFragment mfpBottomSheetFragment, Dialog dialog) {
        this.this$0 = mfpBottomSheetFragment;
        this.$dialog = dialog;
    }

    public final void onShow(DialogInterface dialogInterface) {
        MfpBottomSheetFragment mfpBottomSheetFragment = this.this$0;
        FrameLayout frameLayout = (FrameLayout) this.$dialog.findViewById(R.id.design_bottom_sheet);
        Intrinsics.checkExpressionValueIsNotNull(frameLayout, "dialog.design_bottom_sheet");
        mfpBottomSheetFragment.setupBottomSheetBehavior(frameLayout);
    }
}
