package com.myfitnesspal.feature.search.ui.adapter;

import com.myfitnesspal.shared.model.v2.MfpFood;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/myfitnesspal/shared/model/v2/MfpFood;", "<anonymous parameter 1>", "", "<anonymous parameter 2>", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchAdapter.kt */
final class OnlineFoodSearchAdapter$onCheckedChange$1 extends Lambda implements Function3<MfpFood, Integer, Boolean, Unit> {
    public static final OnlineFoodSearchAdapter$onCheckedChange$1 INSTANCE = new OnlineFoodSearchAdapter$onCheckedChange$1();

    OnlineFoodSearchAdapter$onCheckedChange$1() {
        super(3);
    }

    public final void invoke(@Nullable MfpFood mfpFood, int i, boolean z) {
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((MfpFood) obj, ((Number) obj2).intValue(), ((Boolean) obj3).booleanValue());
        return Unit.INSTANCE;
    }
}
