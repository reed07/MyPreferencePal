package com.myfitnesspal.feature.consents.model;

import android.arch.lifecycle.MutableLiveData;
import com.myfitnesspal.feature.consents.model.Resource.Error;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.TypeCastException;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsViewModel.kt */
final class ConsentsViewModel$load$2<T> implements Consumer<Throwable> {
    final /* synthetic */ ConsentsViewModel this$0;

    ConsentsViewModel$load$2(ConsentsViewModel consentsViewModel) {
        this.this$0 = consentsViewModel;
    }

    public final void accept(Throwable th) {
        MutableLiveData access$getConsents$p = this.this$0.consents;
        String str = "Consents failed to load";
        if (th != null) {
            access$getConsents$p.setValue(new Error(new Throwable(str, th)));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Throwable");
    }
}
