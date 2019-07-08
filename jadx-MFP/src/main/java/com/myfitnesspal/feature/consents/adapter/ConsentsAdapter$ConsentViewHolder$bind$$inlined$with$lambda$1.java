package com.myfitnesspal.feature.consents.adapter;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.myfitnesspal.feature.consents.adapter.ConsentsAdapter.ConsentViewHolder;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged", "com/myfitnesspal/feature/consents/adapter/ConsentsAdapter$ConsentViewHolder$bind$1$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsAdapter.kt */
final class ConsentsAdapter$ConsentViewHolder$bind$$inlined$with$lambda$1 implements OnCheckedChangeListener {
    final /* synthetic */ Pair $consent$inlined;
    final /* synthetic */ Function3 $onConsentChecked$inlined;
    final /* synthetic */ Function2 $onLearnedMoreClicked$inlined;
    final /* synthetic */ ConsentViewHolder this$0;

    ConsentsAdapter$ConsentViewHolder$bind$$inlined$with$lambda$1(ConsentViewHolder consentViewHolder, Pair pair, Function3 function3, Function2 function2) {
        this.this$0 = consentViewHolder;
        this.$consent$inlined = pair;
        this.$onConsentChecked$inlined = function3;
        this.$onLearnedMoreClicked$inlined = function2;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Function3 function3 = this.$onConsentChecked$inlined;
        Object first = this.$consent$inlined.getFirst();
        View view = this.this$0.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
        function3.invoke(first, view, Boolean.valueOf(z));
    }
}
