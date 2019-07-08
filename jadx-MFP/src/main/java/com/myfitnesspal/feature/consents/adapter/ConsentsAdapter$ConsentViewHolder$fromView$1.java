package com.myfitnesspal.feature.consents.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.feature.consents.adapter.ConsentsAdapter.ConsentViewHolder;
import io.reactivex.subjects.PublishSubject;
import io.uacf.consentservices.sdk.UacfConsent;
import kotlin.Metadata;
import kotlin.Pair;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsAdapter.kt */
final class ConsentsAdapter$ConsentViewHolder$fromView$1 implements OnClickListener {
    final /* synthetic */ PublishSubject $subject;
    final /* synthetic */ UacfConsent $uacfConsent;
    final /* synthetic */ ConsentViewHolder this$0;

    ConsentsAdapter$ConsentViewHolder$fromView$1(ConsentViewHolder consentViewHolder, PublishSubject publishSubject, UacfConsent uacfConsent) {
        this.this$0 = consentViewHolder;
        this.$subject = publishSubject;
        this.$uacfConsent = uacfConsent;
    }

    public final void onClick(View view) {
        this.$subject.onNext(new Pair(this.$uacfConsent, this.this$0.itemView));
    }
}
