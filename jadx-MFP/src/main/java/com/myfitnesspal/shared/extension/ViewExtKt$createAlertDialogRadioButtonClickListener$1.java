package com.myfitnesspal.shared.extension;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: ViewExt.kt */
final class ViewExtKt$createAlertDialogRadioButtonClickListener$1 implements OnClickListener {
    final /* synthetic */ long $id;
    final /* synthetic */ View $itemView;
    final /* synthetic */ int $position;
    final /* synthetic */ AdapterView $this_createAlertDialogRadioButtonClickListener;

    ViewExtKt$createAlertDialogRadioButtonClickListener$1(AdapterView adapterView, View view, int i, long j) {
        this.$this_createAlertDialogRadioButtonClickListener = adapterView;
        this.$itemView = view;
        this.$position = i;
        this.$id = j;
    }

    public final void onClick(View view) {
        this.$this_createAlertDialogRadioButtonClickListener.performItemClick(this.$itemView, this.$position, this.$id);
    }
}
