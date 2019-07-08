package com.myfitnesspal.feature.restaurantlogging.task;

import android.content.Context;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.restaurantlogging.model.VenuesRequestData;
import com.myfitnesspal.feature.restaurantlogging.service.VenueService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import dagger.Lazy;
import java.util.List;

public class GetVenuesTask extends EventedTaskBase<List<Venue>, ApiException> {
    private final Lazy<VenueService> venueService;
    private final VenuesRequestData venuesRequestData;

    public static class CompletedEvent extends TaskEventBase<List<Venue>, ApiException> {
    }

    public GetVenuesTask(Lazy<VenueService> lazy, VenuesRequestData venuesRequestData2) {
        super(CompletedEvent.class);
        this.venueService = lazy;
        this.venuesRequestData = venuesRequestData2;
    }

    /* access modifiers changed from: protected */
    public List<Venue> exec(Context context) throws ApiException {
        return ((VenueService) this.venueService.get()).getVenuesForLocationAndRadius(this.venuesRequestData);
    }
}
