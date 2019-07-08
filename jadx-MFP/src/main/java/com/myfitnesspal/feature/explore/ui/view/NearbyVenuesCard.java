package com.myfitnesspal.feature.explore.ui.view;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.location.Location;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition.Builder;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.explore.analytics.ExploreAnalytics.CardType;
import com.myfitnesspal.feature.restaurantlogging.model.MfpLocation;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity;
import com.uacf.core.util.Ln;
import java.util.List;

public class NearbyVenuesCard extends ExploreCardBase implements ActivityLifecycleCallbacks {
    private static final int DEFAULT_MAP_ZOOM = 12;
    private GoogleMap googleMap;
    private MapView mapView;
    private List<Venue> venues;

    public String getAnalyticsType() {
        return CardType.NEARBY_VENUES;
    }

    /* access modifiers changed from: protected */
    public int getButtonTextId() {
        return R.string.explore_card_button_restaurants;
    }

    /* access modifiers changed from: protected */
    public int getContentLayoutId() {
        return R.layout.explore_card_nearby_restaurants;
    }

    /* access modifiers changed from: protected */
    public int getLeftBadgeLayoutId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getRightBadgeLayoutId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getTitleTextId() {
        return R.string.explore_card_title_restaurants;
    }

    public NearbyVenuesCard(Context context) {
        this(context, null, 0);
    }

    public NearbyVenuesCard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NearbyVenuesCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onInflated() {
        this.mapView = (MapView) findViewById(R.id.explore_card_map_view);
        this.mapView.getMapAsync(new OnMapReadyCallback() {
            public final void onMapReady(GoogleMap googleMap) {
                NearbyVenuesCard.lambda$onInflated$2(NearbyVenuesCard.this, googleMap);
            }
        });
        setOnContentViewClickListener(new OnClickListener() {
            public final void onClick(View view) {
                NearbyVenuesCard.this.getNavigationHelper().withIntent(VenuesActivity.newStartIntent(NearbyVenuesCard.this.getContext())).startActivity();
            }
        });
    }

    public static /* synthetic */ void lambda$onInflated$2(NearbyVenuesCard nearbyVenuesCard, GoogleMap googleMap2) {
        nearbyVenuesCard.googleMap = googleMap2;
        googleMap2.getUiSettings().setAllGesturesEnabled(false);
        googleMap2.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap2.setOnMapClickListener(new OnMapClickListener() {
            public final void onMapClick(LatLng latLng) {
                NearbyVenuesCard.this.headerAndContentContainer.callOnClick();
            }
        });
        googleMap2.setOnMarkerClickListener(new OnMarkerClickListener() {
            public final boolean onMarkerClick(Marker marker) {
                return NearbyVenuesCard.this.headerAndContentContainer.callOnClick();
            }
        });
        nearbyVenuesCard.drawVenues(nearbyVenuesCard.venues);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        this.mapView.onCreate(null);
    }

    public void onActivityStarted(Activity activity) {
        this.mapView.onStart();
    }

    public void onActivityResumed(Activity activity) {
        this.mapView.onResume();
    }

    public void onActivityPaused(Activity activity) {
        this.mapView.onPause();
    }

    public void onActivityStopped(Activity activity) {
        this.mapView.onStop();
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        try {
            this.mapView.onSaveInstanceState(bundle);
        } catch (BadParcelableException e) {
            Ln.e(e);
        }
    }

    public void onActivityDestroyed(Activity activity) {
        this.mapView.onDestroy();
    }

    public void moveMapToLocation(Location location) {
        if (location != null && this.googleMap != null) {
            this.googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(new Builder().target(new LatLng(location.getLatitude(), location.getLongitude())).zoom(12.0f).build()));
        }
    }

    public void drawVenues(List<Venue> list) {
        this.venues = list;
        if (list != null) {
            GoogleMap googleMap2 = this.googleMap;
            if (googleMap2 != null) {
                googleMap2.clear();
                BitmapDescriptor fromResource = BitmapDescriptorFactory.fromResource(R.drawable.ic_venue_point);
                for (Venue location : list) {
                    MfpLocation location2 = location.getLocation();
                    this.googleMap.addMarker(new MarkerOptions().position(new LatLng(location2.getLatitude(), location2.getLongitude())).icon(fromResource));
                }
            }
        }
    }
}
