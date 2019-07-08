package com.myfitnesspal.feature.consents.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0007"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/myfitnesspal/feature/consents/adapter/AdConsentsAdapter$AdConsentViewHolder$bind$1$2$1", "com/myfitnesspal/feature/consents/adapter/AdConsentsAdapter$AdConsentViewHolder$$special$$inlined$let$lambda$2"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsAdapter.kt */
final class AdConsentsAdapter$AdConsentViewHolder$bind$$inlined$with$lambda$2 implements OnClickListener {
    final /* synthetic */ Pair $consent$inlined;
    final /* synthetic */ Function3 $onConsentChecked$inlined;
    final /* synthetic */ Function1 $onLearnedMoreClicked$inlined;
    final /* synthetic */ boolean $shouldShowCheckBox$inlined;

    AdConsentsAdapter$AdConsentViewHolder$bind$$inlined$with$lambda$2(Pair pair, boolean z, Function3 function3, Function1 function1) {
        this.$consent$inlined = pair;
        this.$shouldShowCheckBox$inlined = z;
        this.$onConsentChecked$inlined = function3;
        this.$onLearnedMoreClicked$inlined = function1;
    }

    public final void onClick(View view) {
        this.$onLearnedMoreClicked$inlined.invoke(this.$consent$inlined.getFirst());
    }
}
