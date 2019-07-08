package com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin;

import android.content.Context;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.restaurantlogging.model.MenusActivityBundleData;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.restaurantlogging.service.VenueService;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.MenusActivity;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.squareup.otto.Subscribe;
import dagger.Lazy;
import java.util.UUID;
import javax.inject.Inject;

public class VenueDeepLinkMixin extends DeepLinkMixinBase {
    @Inject
    Lazy<VenueService> venueService;

    static class FindVenueByIdTask extends EventedTaskBase<Venue, ApiException> {
        private String venueId;
        private Lazy<VenueService> venueService;

        static class CompletedEvent extends TaskEventBase<Venue, ApiException> {
            CompletedEvent() {
            }
        }

        public FindVenueByIdTask(Lazy<VenueService> lazy, String str) {
            super((TaskEventBase) new CompletedEvent());
            this.venueService = lazy;
            this.venueId = str;
        }

        /* access modifiers changed from: protected */
        public Venue exec(Context context) throws ApiException {
            return ((VenueService) this.venueService.get()).getVenueById(this.venueId);
        }
    }

    public VenueDeepLinkMixin(MfpUiComponentInterface mfpUiComponentInterface) {
        super(mfpUiComponentInterface);
        component().inject(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new FindVenueByIdTask(this.venueService, getActivity().getIntent().getData().getLastPathSegment()).run(getRunner());
    }

    @Subscribe
    public void onQueryCompleted(CompletedEvent completedEvent) {
        Venue venue = (Venue) completedEvent.getResult();
        if (venue != null) {
            getComponentInterface().getNavigationHelper().withIntent(MenusActivity.newStartIntent(getActivity(), new MenusActivityBundleData(venue, UUID.randomUUID().toString(), MenusActivity.DEEP_LINK_SOURCE))).finishActivityAfterNavigation().startActivity();
            return;
        }
        onInvalidResponse((int) R.string.error_occured);
    }
}
