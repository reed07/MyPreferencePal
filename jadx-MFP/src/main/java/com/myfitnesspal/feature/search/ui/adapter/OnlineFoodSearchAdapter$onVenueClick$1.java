package com.myfitnesspal.feature.search.ui.adapter;

import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/myfitnesspal/feature/restaurantlogging/model/Venue;", "<anonymous parameter 1>", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchAdapter.kt */
final class OnlineFoodSearchAdapter$onVenueClick$1 extends Lambda implements Function2<Venue, Integer, Unit> {
    public static final OnlineFoodSearchAdapter$onVenueClick$1 INSTANCE = new OnlineFoodSearchAdapter$onVenueClick$1();

    OnlineFoodSearchAdapter$onVenueClick$1() {
        super(2);
    }

    public final void invoke(@Nullable Venue venue, int i) {
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Venue) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}
