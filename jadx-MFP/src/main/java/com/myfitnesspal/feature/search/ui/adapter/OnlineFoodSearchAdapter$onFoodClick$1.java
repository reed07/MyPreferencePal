package com.myfitnesspal.feature.search.ui.adapter;

import com.myfitnesspal.shared.model.v2.MfpFood;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/myfitnesspal/shared/model/v2/MfpFood;", "<anonymous parameter 1>", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchAdapter.kt */
final class OnlineFoodSearchAdapter$onFoodClick$1 extends Lambda implements Function2<MfpFood, Integer, Unit> {
    public static final OnlineFoodSearchAdapter$onFoodClick$1 INSTANCE = new OnlineFoodSearchAdapter$onFoodClick$1();

    OnlineFoodSearchAdapter$onFoodClick$1() {
        super(2);
    }

    public final void invoke(@Nullable MfpFood mfpFood, int i) {
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((MfpFood) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}
