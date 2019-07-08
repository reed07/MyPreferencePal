package com.myfitnesspal.feature.search.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/myfitnesspal/feature/search/ui/adapter/OnlineFoodSearchAdapter$VenueViewHolder$setData$1$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchAdapter.kt */
final class OnlineFoodSearchAdapter$VenueViewHolder$setData$$inlined$let$lambda$1 implements OnClickListener {
    final /* synthetic */ int $position$inlined;
    final /* synthetic */ Venue $venue;
    final /* synthetic */ VenueViewHolder this$0;

    OnlineFoodSearchAdapter$VenueViewHolder$setData$$inlined$let$lambda$1(Venue venue, VenueViewHolder venueViewHolder, int i) {
        this.$venue = venue;
        this.this$0 = venueViewHolder;
        this.$position$inlined = i;
    }

    public final void onClick(View view) {
        OnlineFoodSearchAdapter.this.getOnVenueClick().invoke(this.$venue, Integer.valueOf(this.$position$inlined));
    }
}
