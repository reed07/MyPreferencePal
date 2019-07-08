package com.myfitnesspal.feature.addentry.util;

import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.android.R;
import com.uacf.core.util.ViewUtils;

/* renamed from: com.myfitnesspal.feature.addentry.util.-$$Lambda$PairedFoodsHelper$Bx4-aIF9PTqf_AfNDoWF6wCS3gs reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$PairedFoodsHelper$Bx4aIF9PTqf_AfNDoWF6wCS3gs implements OnClickListener {
    public static final /* synthetic */ $$Lambda$PairedFoodsHelper$Bx4aIF9PTqf_AfNDoWF6wCS3gs INSTANCE = new $$Lambda$PairedFoodsHelper$Bx4aIF9PTqf_AfNDoWF6wCS3gs();

    private /* synthetic */ $$Lambda$PairedFoodsHelper$Bx4aIF9PTqf_AfNDoWF6wCS3gs() {
    }

    public final void onClick(View view) {
        ViewUtils.findById(view, R.id.multiSelectCheckBox).performClick();
    }
}
