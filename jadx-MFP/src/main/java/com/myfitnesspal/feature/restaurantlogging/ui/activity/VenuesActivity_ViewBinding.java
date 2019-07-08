package com.myfitnesspal.feature.restaurantlogging.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.gms.maps.MapView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.restaurantlogging.ui.view.VenuesListView;

public class VenuesActivity_ViewBinding implements Unbinder {
    private VenuesActivity target;

    @UiThread
    public VenuesActivity_ViewBinding(VenuesActivity venuesActivity) {
        this(venuesActivity, venuesActivity.getWindow().getDecorView());
    }

    @UiThread
    public VenuesActivity_ViewBinding(VenuesActivity venuesActivity, View view) {
        this.target = venuesActivity;
        venuesActivity.parentView = Utils.findRequiredView(view, R.id.parent_view, "field 'parentView'");
        venuesActivity.venuesListView = (VenuesListView) Utils.findRequiredViewAsType(view, R.id.venue_list, "field 'venuesListView'", VenuesListView.class);
        venuesActivity.venueMapContainer = Utils.findRequiredView(view, R.id.venue_map_container, "field 'venueMapContainer'");
        venuesActivity.mapView = (MapView) Utils.findRequiredViewAsType(view, R.id.venue_map, "field 'mapView'", MapView.class);
        venuesActivity.searchContainer = Utils.findRequiredView(view, R.id.input_container, "field 'searchContainer'");
        venuesActivity.titleTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'titleTextView'", TextView.class);
        venuesActivity.restaurantEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.restaurant_input, "field 'restaurantEditText'", EditText.class);
        venuesActivity.locationEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.location_input, "field 'locationEditText'", EditText.class);
        venuesActivity.searchAgainBtn = Utils.findRequiredView(view, R.id.search_again_btn, "field 'searchAgainBtn'");
        venuesActivity.myLocationBtn = Utils.findRequiredView(view, R.id.my_location_btn, "field 'myLocationBtn'");
        venuesActivity.loadingVenues = Utils.findRequiredView(view, R.id.loading_venues, "field 'loadingVenues'");
        venuesActivity.emptyView = Utils.findRequiredView(view, R.id.empty_view, "field 'emptyView'");
    }

    @CallSuper
    public void unbind() {
        VenuesActivity venuesActivity = this.target;
        if (venuesActivity != null) {
            this.target = null;
            venuesActivity.parentView = null;
            venuesActivity.venuesListView = null;
            venuesActivity.venueMapContainer = null;
            venuesActivity.mapView = null;
            venuesActivity.searchContainer = null;
            venuesActivity.titleTextView = null;
            venuesActivity.restaurantEditText = null;
            venuesActivity.locationEditText = null;
            venuesActivity.searchAgainBtn = null;
            venuesActivity.myLocationBtn = null;
            venuesActivity.loadingVenues = null;
            venuesActivity.emptyView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
