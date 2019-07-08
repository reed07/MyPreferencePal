package com.myfitnesspal.feature.search.ui.activity;

import com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment.Trigger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "trigger", "Lcom/myfitnesspal/feature/search/ui/fragment/OnlineFoodSearchFragment$Trigger;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodSearchActivityV2.kt */
final class FoodSearchActivityV2$ViewPagerAdapter$instantiateItem$1 extends Lambda implements Function2<String, Trigger, Unit> {
    final /* synthetic */ ViewPagerAdapter this$0;

    FoodSearchActivityV2$ViewPagerAdapter$instantiateItem$1(ViewPagerAdapter viewPagerAdapter) {
        this.this$0 = viewPagerAdapter;
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((String) obj, (Trigger) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull String str, @NotNull Trigger trigger) {
        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(trigger, "trigger");
        this.this$0.this$0.showOnlineSearch(trigger);
    }
}
