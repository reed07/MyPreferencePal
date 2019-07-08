package com.myfitnesspal.feature.restaurantlogging.task;

import android.content.Context;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenu;
import com.myfitnesspal.feature.restaurantlogging.service.VenueService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import dagger.Lazy;
import java.util.List;

public class GetMenusTask extends EventedTaskBase<List<MfpMenu>, ApiException> {
    private final String venueId;
    private final Lazy<VenueService> venueService;

    public static class CompletedEvent extends TaskEventBase<List<MfpMenu>, ApiException> {
    }

    public GetMenusTask(Lazy<VenueService> lazy, String str) {
        super(CompletedEvent.class);
        this.venueService = lazy;
        this.venueId = str;
    }

    /* access modifiers changed from: protected */
    public List<MfpMenu> exec(Context context) throws ApiException {
        return ((VenueService) this.venueService.get()).getMenusForVenueId(this.venueId);
    }
}
