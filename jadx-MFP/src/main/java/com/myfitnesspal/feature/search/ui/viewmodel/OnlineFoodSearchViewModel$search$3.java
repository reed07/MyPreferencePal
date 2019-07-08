package com.myfitnesspal.feature.search.ui.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import com.myfitnesspal.feature.registration.model.Resource.Error;
import com.uacf.core.util.Ln;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "error", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchViewModel.kt */
final class OnlineFoodSearchViewModel$search$3<T> implements Consumer<Throwable> {
    final /* synthetic */ OnlineFoodSearchViewModel this$0;

    OnlineFoodSearchViewModel$search$3(OnlineFoodSearchViewModel onlineFoodSearchViewModel) {
        this.this$0 = onlineFoodSearchViewModel;
    }

    public final void accept(Throwable th) {
        Ln.e(th);
        MutableLiveData searchResults = this.this$0.getSearchResults();
        Intrinsics.checkExpressionValueIsNotNull(th, "error");
        searchResults.setValue(new Error(th));
    }
}
