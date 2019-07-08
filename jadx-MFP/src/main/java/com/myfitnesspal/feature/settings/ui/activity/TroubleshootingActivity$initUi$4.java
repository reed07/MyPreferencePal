package com.myfitnesspal.feature.settings.ui.activity;

import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: TroubleshootingActivity.kt */
final class TroubleshootingActivity$initUi$4<TData> implements DialogPositiveListener<Object> {
    final /* synthetic */ TroubleshootingActivity this$0;

    TroubleshootingActivity$initUi$4(TroubleshootingActivity troubleshootingActivity) {
        this.this$0 = troubleshootingActivity;
    }

    public final void onClick(Object obj) {
        this.this$0.getDiagnostics();
    }
}
