package com.myfitnesspal.feature.consents.adapter;

import android.view.View;
import com.myfitnesspal.feature.consents.adapter.ConsentsAdapter.ConsentViewHolder;
import io.reactivex.functions.Consumer;
import io.uacf.consentservices.sdk.UacfConsent;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005 \u0006*\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "it", "Lkotlin/Pair;", "Lio/uacf/consentservices/sdk/UacfConsent;", "Landroid/view/View;", "kotlin.jvm.PlatformType", "accept", "com/myfitnesspal/feature/consents/adapter/ConsentsAdapter$ConsentViewHolder$bind$1$2"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsAdapter.kt */
final class ConsentsAdapter$ConsentViewHolder$bind$$inlined$with$lambda$2<T> implements Consumer<Pair<? extends UacfConsent, ? extends View>> {
    final /* synthetic */ Pair $consent$inlined;
    final /* synthetic */ Function3 $onConsentChecked$inlined;
    final /* synthetic */ Function2 $onLearnedMoreClicked$inlined;
    final /* synthetic */ ConsentViewHolder this$0;

    ConsentsAdapter$ConsentViewHolder$bind$$inlined$with$lambda$2(ConsentViewHolder consentViewHolder, Pair pair, Function3 function3, Function2 function2) {
        this.this$0 = consentViewHolder;
        this.$consent$inlined = pair;
        this.$onConsentChecked$inlined = function3;
        this.$onLearnedMoreClicked$inlined = function2;
    }

    public final void accept(Pair<? extends UacfConsent, ? extends View> pair) {
        Function2 function2 = this.$onLearnedMoreClicked$inlined;
        Object first = this.$consent$inlined.getFirst();
        View view = this.this$0.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
        function2.invoke(first, view);
    }
}
