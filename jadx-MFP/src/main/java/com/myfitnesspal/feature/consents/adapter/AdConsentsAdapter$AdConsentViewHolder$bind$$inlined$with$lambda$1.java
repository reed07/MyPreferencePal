package com.myfitnesspal.feature.consents.adapter;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import io.uacf.consentservices.sdk.UacfConsent;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\t"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged", "com/myfitnesspal/feature/consents/adapter/AdConsentsAdapter$AdConsentViewHolder$bind$1$1$1", "com/myfitnesspal/feature/consents/adapter/AdConsentsAdapter$AdConsentViewHolder$$special$$inlined$let$lambda$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsAdapter.kt */
final class AdConsentsAdapter$AdConsentViewHolder$bind$$inlined$with$lambda$1 implements OnCheckedChangeListener {
    final /* synthetic */ Pair $consent$inlined;
    final /* synthetic */ Function3 $onConsentChecked$inlined;
    final /* synthetic */ Function1 $onLearnedMoreClicked$inlined;
    final /* synthetic */ boolean $shouldShowCheckBox$inlined;
    final /* synthetic */ View $this_with$inlined;

    AdConsentsAdapter$AdConsentViewHolder$bind$$inlined$with$lambda$1(View view, Pair pair, boolean z, Function3 function3, Function1 function1) {
        this.$this_with$inlined = view;
        this.$consent$inlined = pair;
        this.$shouldShowCheckBox$inlined = z;
        this.$onConsentChecked$inlined = function3;
        this.$onLearnedMoreClicked$inlined = function1;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Function3 function3 = this.$onConsentChecked$inlined;
        String id = ((UacfConsent) this.$consent$inlined.getFirst()).getId();
        Intrinsics.checkExpressionValueIsNotNull(id, "consent.first.id");
        View view = this.$this_with$inlined;
        Intrinsics.checkExpressionValueIsNotNull(view, "this");
        function3.invoke(id, view, Boolean.valueOf(z));
    }
}
