package com.myfitnesspal.feature.search.ui.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import com.facebook.ads.internal.j.e;
import com.myfitnesspal.feature.registration.model.Resource.Error;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "e", "", "kotlin.jvm.PlatformType", "accept", "com/myfitnesspal/feature/search/ui/viewmodel/OnlineFoodSearchViewModel$fetchNextPage$1$2"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchViewModel.kt */
final class OnlineFoodSearchViewModel$fetchNextPage$$inlined$let$lambda$2<T> implements Consumer<Throwable> {
    final /* synthetic */ OnlineFoodSearchViewModel this$0;

    OnlineFoodSearchViewModel$fetchNextPage$$inlined$let$lambda$2(OnlineFoodSearchViewModel onlineFoodSearchViewModel) {
        this.this$0 = onlineFoodSearchViewModel;
    }

    public final void accept(Throwable th) {
        MutableLiveData nextPage = this.this$0.getNextPage();
        Intrinsics.checkExpressionValueIsNotNull(th, e.a);
        nextPage.setValue(new Error(th));
    }
}
