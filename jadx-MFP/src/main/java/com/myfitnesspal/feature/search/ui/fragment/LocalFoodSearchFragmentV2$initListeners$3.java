package com.myfitnesspal.feature.search.ui.fragment;

import com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment.Trigger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "query", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
final class LocalFoodSearchFragmentV2$initListeners$3 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ LocalFoodSearchFragmentV2 this$0;

    LocalFoodSearchFragmentV2$initListeners$3(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        this.this$0 = localFoodSearchFragmentV2;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull String str) {
        Trigger trigger;
        Intrinsics.checkParameterIsNotNull(str, "query");
        Function2 onSearchRequested = this.this$0.getOnSearchRequested();
        if (LocalFoodSearchFragmentV2.access$getLocalSearchViewModel$p(this.this$0).isResultEmpty()) {
            trigger = Trigger.NO_HISTORY_RESULTS;
        } else {
            trigger = Trigger.TAP_FROM_HISTORY;
        }
        onSearchRequested.invoke(str, trigger);
    }
}
