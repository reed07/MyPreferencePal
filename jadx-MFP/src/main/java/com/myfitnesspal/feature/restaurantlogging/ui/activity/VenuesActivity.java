package com.myfitnesspal.feature.restaurantlogging.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.Observable;
import android.graphics.Rect;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.permissions.PermissionsMixin;
import com.myfitnesspal.feature.restaurantlogging.model.MfpLocation;
import com.myfitnesspal.feature.restaurantlogging.model.RequestedVenueLocation;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.restaurantlogging.model.VenuesRequestData;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingAnalyticsHelper;
import com.myfitnesspal.feature.restaurantlogging.service.VenueService;
import com.myfitnesspal.feature.restaurantlogging.ui.view.VenuesListView;
import com.myfitnesspal.feature.restaurantlogging.ui.viewmodel.VenuesViewModel;
import com.myfitnesspal.feature.restaurantlogging.ui.viewmodel.VenuesViewModel.Property;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Debouncer;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class VenuesActivity extends MfpActivity {
    private static final String CURRENT_SEARCH_MAP_STATE = "current_search_map_state";
    private static final int DEFAULT_LANDSCAPE_RADIUS_METERS = 2000;
    private static final float DEFAULT_ZOOM_LEVEL = 12.0f;
    public static final String EXTRA_IS_CURRENTLY_IN_MEAL_CREATION_FLOW = "is_currently_in_meal_creation_flow";
    private static final String FINDING_LOCATION_PROGRESS_TAG = "finding_location_progress_tag";
    private static final String FLOW_ID = "flow_id";
    private static final int MENU_SEARCH = 1001;
    private static final int MINIMUM_DISTANCE_SEARCH_AGAIN = 500;
    private static final int ON_EDITOR_ACTION_DELAY = 200;
    private SearchBoxAndMapState currentSearchBoxAndMapState;
    private VenueListState currentVenueListState;
    @BindView(2131362467)
    View emptyView;
    private String flowId;
    private Runnable focusMapViewAndHideSoftInputRunnable = new Runnable() {
        public void run() {
            VenuesActivity.this.mapView.requestFocus();
            VenuesActivity.this.getImmHelper().hideSoftInput();
        }
    };
    private Runnable focusRestaurantEditTextAndShowSoftInputRunnable = new Runnable() {
        public void run() {
            VenuesActivity.this.restaurantEditText.requestFocus();
            VenuesActivity.this.getImmHelper().showSoftInput(VenuesActivity.this.restaurantEditText);
        }
    };
    /* access modifiers changed from: private */
    public Debouncer<String> getLocationAddressOrRequestVenuesDebouncer = new Debouncer<String>(200) {
        /* access modifiers changed from: protected */
        public void onDebounced(String str) {
            VenuesActivity.this.getLocationAddressOrRequestVenues(str);
        }
    };
    private GoogleApiClient googleApiClient;
    /* access modifiers changed from: private */
    public GoogleMap googleMap;
    private boolean hasLocationPermission;
    /* access modifiers changed from: private */
    public RequestedVenueLocation lastRequestedVenueLocation;
    @BindView(2131362949)
    View loadingVenues;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @BindView(2131362955)
    EditText locationEditText;
    private boolean madeRequestForUserLocationOnce;
    @BindView(2131364135)
    MapView mapView;
    @BindView(2131363088)
    View myLocationBtn;
    private final OnCameraChangeListener onCameraChangeListener = new OnCameraChangeListener() {
        public void onCameraChange(CameraPosition cameraPosition) {
            double d;
            double d2;
            if (VenuesActivity.this.lastRequestedVenueLocation != null || VenuesActivity.this.userLocation != null) {
                if (VenuesActivity.this.lastRequestedVenueLocation != null) {
                    d2 = VenuesActivity.this.lastRequestedVenueLocation.getLatitude();
                    d = VenuesActivity.this.lastRequestedVenueLocation.getLongitude();
                } else {
                    d2 = VenuesActivity.this.userLocation.getLatitude();
                    d = VenuesActivity.this.userLocation.getLongitude();
                }
                LatLng latLng = cameraPosition.target;
                float[] fArr = new float[1];
                Location.distanceBetween(d2, d, latLng.latitude, latLng.longitude, fArr);
                if (fArr[0] > 500.0f) {
                    VenuesActivity.this.searchAgainBtn.setVisibility(0);
                }
            }
        }
    };
    private final OnConnectionFailedListener onConnectionFailedListener = $$Lambda$VenuesActivity$Qj2pkl_HF1Y9lAWQA2jpa4LeaA.INSTANCE;
    private final OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        public void onMapReady(GoogleMap googleMap) {
            VenuesActivity.this.googleMap = googleMap;
        }
    };
    private final OnMarkerClickListener onMarkerClickListener = new OnMarkerClickListener() {
        public final boolean onMarkerClick(Marker marker) {
            return VenuesActivity.lambda$new$4(VenuesActivity.this, marker);
        }
    };
    private final OnMyLocationChangeListener onMyLocationChangeListener = new OnMyLocationChangeListener() {
        public final void onMyLocationChange(Location location) {
            VenuesActivity.this.myLocationChange(location);
        }
    };
    @BindView(2131363241)
    View parentView;
    private Runnable resetListSelection = new Runnable() {
        public void run() {
            VenuesActivity.this.venuesListView.setSelection(0);
        }
    };
    @BindView(2131363477)
    EditText restaurantEditText;
    @Inject
    Lazy<RestaurantLoggingAnalyticsHelper> restaurantLoggingAnalyticsHelper;
    @BindView(2131363564)
    View searchAgainBtn;
    @BindView(2131362815)
    View searchContainer;
    private OnEditorActionListener searchOnEditorActionListener = new OnEditorActionListener() {
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            VenuesActivity.this.getLocationAddressOrRequestVenuesDebouncer.call(Strings.toString(VenuesActivity.this.locationEditText.getText()));
            return true;
        }
    };
    @BindView(2131363833)
    TextView titleTextView;
    /* access modifiers changed from: private */
    public Location userLocation;
    @BindView(2131364136)
    View venueMapContainer;
    @Inject
    Lazy<VenueService> venueService;
    @BindView(2131364134)
    VenuesListView venuesListView;
    private VenuesViewModel venuesViewModel;

    private static class GetLocationAddressTask extends EventedTaskBase<RequestedVenueLocation, IOException> {
        private final String locationName;

        public static final class CompletedEvent extends TaskEventBase<RequestedVenueLocation, IOException> {
        }

        protected GetLocationAddressTask(String str) {
            super(CompletedEvent.class);
            this.locationName = str;
        }

        /* access modifiers changed from: protected */
        public RequestedVenueLocation exec(Context context) throws IOException {
            List fromLocationName = new Geocoder(context).getFromLocationName(this.locationName, 1);
            if (CollectionUtils.isEmpty((Collection<?>) fromLocationName)) {
                return null;
            }
            Address address = (Address) fromLocationName.get(0);
            RequestedVenueLocation requestedVenueLocation = new RequestedVenueLocation(this.locationName, address.getLatitude(), address.getLongitude());
            return requestedVenueLocation;
        }
    }

    private enum SearchBoxAndMapState {
        MapOnly,
        SearchBoxOnly,
        SearchBoxAndMap
    }

    private enum VenueListState {
        Blank,
        Empty,
        Loading,
        DisplayList
    }

    private enum ZoomState {
        UseDefaultZoomLevel,
        UseCurrentZoomLevel,
        UseCurrentZoomLevelIfHigherThanDefault
    }

    static /* synthetic */ void lambda$new$5(ConnectionResult connectionResult) {
    }

    public String getAnalyticsScreenTag() {
        return "venues";
    }

    public int getCustomToolbarLayoutResId() {
        return R.layout.venue_toolbar;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, VenuesActivity.class).putExtra("date", new Date().getTime());
    }

    public static Intent newStartIntent(Context context, String str, Date date) {
        return newStartIntent(context, str, date, false);
    }

    public static Intent newStartIntent(Context context, String str, Date date, boolean z) {
        return new Intent(context, VenuesActivity.class).putExtra(Extras.MEAL_NAME, str).putExtra("date", date.getTime()).putExtra(EXTRA_IS_CURRENTLY_IN_MEAL_CREATION_FLOW, z);
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.venues);
        MaterialUtils.enableAppBarScrollIfLollipop(this, this.venuesListView);
        setFlowId(bundle);
        this.venuesListView.setIntentAndFlowId(getIntent(), this.flowId);
        setTitle(R.string.restaurants);
        setCurrentSearchBoxAndMapState(SearchBoxAndMapState.values()[BundleUtils.getInt(bundle, CURRENT_SEARCH_MAP_STATE, SearchBoxAndMapState.MapOnly.ordinal())]);
        setCurrentVenueListState(VenueListState.Blank);
        buildGoogleApiClient();
        initViewModel();
        initMap(bundle);
        setViewListeners();
        if (bundle == null) {
            ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportEntryPoint(getMealName());
            ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportFlowStartDES(this.flowId);
        }
    }

    private void setFlowId(Bundle bundle) {
        this.flowId = bundle == null ? UUID.randomUUID().toString() : BundleUtils.getString(bundle, "flow_id");
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.googleApiClient.connect();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mapView.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mapView.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.googleApiClient.disconnect();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(CURRENT_SEARCH_MAP_STATE, this.currentSearchBoxAndMapState.ordinal());
        super.onSaveInstanceState(bundle);
        this.mapView.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mapView.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 185 && i2 == -1) {
            if (ExtrasUtils.getBoolean(intent, Extras.SHOW_RECEIVED_MENU_REQUEST_SNACKBAR)) {
                showReceivedMenuRequestSnackbar();
            } else if (ExtrasUtils.containsKey(intent, MealEditorMixin.EXTRA_FOOD_ENTRIES) || ExtrasUtils.containsKey(intent, "food_entry")) {
                setResult(-1, intent);
                finish();
            }
        } else if (i == 205) {
            checkPermissions();
        }
    }

    private void showReceivedMenuRequestSnackbar() {
        new SnackbarBuilder(this.parentView).setMessage((int) R.string.received_menu_request).showWithDelay();
    }

    public void onUpPressed() {
        onBackPressed();
    }

    public void onBackPressed() {
        if (this.currentSearchBoxAndMapState != SearchBoxAndMapState.MapOnly) {
            setCurrentSearchBoxAndMapState(SearchBoxAndMapState.MapOnly);
            return;
        }
        ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportFlowEndDES(this.flowId);
        super.onBackPressed();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.currentSearchBoxAndMapState == SearchBoxAndMapState.MapOnly) {
            MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.searchBtn).setIcon(R.drawable.ic_search_white_24dp), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 1001) {
            setCurrentSearchBoxAndMapState(SearchBoxAndMapState.SearchBoxOnly);
            ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportVenueSearchClicked(getMealName());
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void setViewListeners() {
        this.restaurantEditText.setOnTouchListener(new OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return VenuesActivity.lambda$setViewListeners$0(VenuesActivity.this, view, motionEvent);
            }
        });
        this.restaurantEditText.setOnEditorActionListener(this.searchOnEditorActionListener);
        this.locationEditText.setOnEditorActionListener(this.searchOnEditorActionListener);
        this.searchAgainBtn.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                VenuesActivity.lambda$setViewListeners$1(VenuesActivity.this, view);
            }
        });
        this.myLocationBtn.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                VenuesActivity.lambda$setViewListeners$2(VenuesActivity.this, view);
            }
        });
    }

    public static /* synthetic */ boolean lambda$setViewListeners$0(VenuesActivity venuesActivity, View view, MotionEvent motionEvent) {
        Rect rect = new Rect();
        view.getHitRect(rect);
        if (rect.contains((int) motionEvent.getX(), (int) motionEvent.getY()) && motionEvent.getAction() == 1) {
            venuesActivity.setCurrentSearchBoxAndMapState(SearchBoxAndMapState.SearchBoxOnly);
        }
        return false;
    }

    public static /* synthetic */ void lambda$setViewListeners$1(VenuesActivity venuesActivity, View view) {
        LatLng latLng = venuesActivity.googleMap.getCameraPosition().target;
        RequestedVenueLocation requestedVenueLocation = new RequestedVenueLocation(null, latLng.latitude, latLng.longitude);
        venuesActivity.requestVenues(requestedVenueLocation, ZoomState.UseCurrentZoomLevel, true);
        venuesActivity.searchAgainBtn.setVisibility(8);
    }

    public static /* synthetic */ void lambda$setViewListeners$2(VenuesActivity venuesActivity, View view) {
        if (!venuesActivity.isLocationEnabled()) {
            venuesActivity.showLocationSettingsDialog();
        } else {
            venuesActivity.googleMap.setOnMyLocationChangeListener(venuesActivity.onMyLocationChangeListener);
        }
    }

    private boolean isLastRequestedLocation(String str) {
        RequestedVenueLocation requestedVenueLocation = this.lastRequestedVenueLocation;
        return requestedVenueLocation != null && Strings.equals(str, requestedVenueLocation.getSearchQuery());
    }

    /* access modifiers changed from: private */
    public void getLocationAddressOrRequestVenues(String str) {
        RequestedVenueLocation requestedVenueLocation;
        boolean isEmpty = TextUtils.isEmpty(str);
        if (isEmpty || isLastRequestedLocation(str)) {
            if (isEmpty) {
                requestedVenueLocation = null;
            } else {
                requestedVenueLocation = this.lastRequestedVenueLocation;
            }
            requestVenues(requestedVenueLocation);
        } else {
            ProgressDialogFragment.newInstance(R.string.finding_location).show(getSupportFragmentManager(), FINDING_LOCATION_PROGRESS_TAG);
            new GetLocationAddressTask(str).run(getRunner());
        }
        ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportVenueSearchPerformed(getMealName());
    }

    public void setTitle(CharSequence charSequence) {
        this.titleTextView.setText(charSequence);
    }

    private void setCurrentSearchBoxAndMapState(SearchBoxAndMapState searchBoxAndMapState) {
        boolean z;
        boolean z2;
        boolean z3;
        if (this.currentSearchBoxAndMapState != searchBoxAndMapState) {
            this.currentSearchBoxAndMapState = searchBoxAndMapState;
            switch (this.currentSearchBoxAndMapState) {
                case SearchBoxOnly:
                    this.restaurantEditText.post(this.focusRestaurantEditTextAndShowSoftInputRunnable);
                    z = true;
                    z3 = true;
                    z2 = false;
                    break;
                case SearchBoxAndMap:
                    z = true;
                    z3 = false;
                    z2 = true;
                    break;
                default:
                    z = false;
                    z3 = false;
                    z2 = true;
                    break;
            }
            boolean z4 = z || z3;
            ViewUtils.setVisible(z4, this.searchContainer);
            ViewUtils.setVisible(!z4, this.titleTextView);
            ViewUtils.setVisible(z2, this.venueMapContainer);
            ViewUtils.setVisible(z, this.restaurantEditText);
            ViewUtils.setVisible(z3, this.locationEditText);
            Toolbar toolbar = getToolbar();
            if (z2) {
                MaterialUtils.setToolbarDefaultHeight(this, toolbar);
                this.mapView.post(this.focusMapViewAndHideSoftInputRunnable);
            } else {
                MaterialUtils.setToolbarHeight(toolbar, getResources().getDimensionPixelSize(R.dimen.venue_search_toolbar_height));
            }
            invalidateOptionsMenu();
        }
    }

    private void setCurrentVenueListState(VenueListState venueListState) {
        if (this.currentVenueListState != venueListState) {
            this.currentVenueListState = venueListState;
            ViewUtils.setGone(this.venuesListView);
            ViewUtils.setGone(this.loadingVenues);
            ViewUtils.setGone(this.emptyView);
            switch (this.currentVenueListState) {
                case Empty:
                    ViewUtils.setVisible(this.emptyView);
                    break;
                case Loading:
                    ViewUtils.setVisible(this.loadingVenues);
                    break;
                case DisplayList:
                    ViewUtils.setVisible(this.venuesListView);
                    break;
            }
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == 2;
    }

    private void initMap(Bundle bundle) {
        this.mapView.onCreate(bundle);
        this.venuesViewModel.loadMap(this.mapView);
    }

    private void initViewModel() {
        this.venuesViewModel = (VenuesViewModel) getViewModel();
        if (this.venuesViewModel == null) {
            this.venuesViewModel = (VenuesViewModel) setViewModel(new VenuesViewModel(getRunner(), this.venueService));
        }
    }

    private void buildGoogleApiClient() {
        this.googleApiClient = new Builder(this).addOnConnectionFailedListener(this.onConnectionFailedListener).addApi(LocationServices.API).build();
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.VENUES_LIST_FETCHED) {
            List venues = this.venuesViewModel.getVenues();
            setVenues(venues);
            ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportVenueList(venues, getMealName());
        } else if (i == Property.VENUES_LIST_FETCH_FAILED) {
            setCurrentVenueListState(VenueListState.Empty);
        } else if (i == Property.MAP_ENABLED) {
            this.googleMap = this.venuesViewModel.googleMap();
            checkPermissions();
        }
    }

    private void setVenues(List<Venue> list) {
        if (CollectionUtils.isEmpty((Collection<?>) list)) {
            setCurrentVenueListState(VenueListState.Empty);
            return;
        }
        setCurrentVenueListState(VenueListState.DisplayList);
        this.venuesListView.setItems(list);
        this.venuesListView.post(this.resetListSelection);
        showVenueMarkers(list);
    }

    private String getMealName() {
        return ExtrasUtils.getString(getIntent(), Extras.MEAL_NAME);
    }

    private void showVenueMarkers(List<Venue> list) {
        this.googleMap.clear();
        BitmapDescriptor fromResource = BitmapDescriptorFactory.fromResource(R.drawable.ic_venue_point);
        for (Venue venue : list) {
            MfpLocation location = venue.getLocation();
            this.googleMap.addMarker(new MarkerOptions().title(venue.getName()).position(new LatLng(location.getLatitude(), location.getLongitude())).icon(fromResource));
        }
    }

    private void moveToRequestedLocationOrUserLocation(RequestedVenueLocation requestedVenueLocation, ZoomState zoomState) {
        moveToRequestedLocationOrUserLocation(requestedVenueLocation, zoomState, false);
    }

    private void moveToRequestedLocationOrUserLocation(RequestedVenueLocation requestedVenueLocation, ZoomState zoomState, boolean z) {
        double d;
        double d2;
        if (requestedVenueLocation == null) {
            Location location = this.userLocation;
            if (location == null) {
                showLocationSettingsDialog();
                return;
            }
            d2 = location.getLatitude();
            d = this.userLocation.getLongitude();
        } else {
            d2 = requestedVenueLocation.getLatitude();
            d = requestedVenueLocation.getLongitude();
        }
        moveToLocation(d2, d, zoomState, z);
    }

    private void showLocationSettingsDialog() {
        ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialogWithTitleAndListeners(getString(R.string.location_required), getString(R.string.enable_your_location_or_enter_other_location), getString(R.string.allow), new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                VenuesActivity.this.getNavigationHelper().withIntent(SharedIntents.newLocationSettingsIntent()).startActivity();
            }
        }, getString(R.string.no_thanks_correct), null);
    }

    private void moveToLocation(double d, double d2, ZoomState zoomState, boolean z) {
        float f = this.googleMap.getCameraPosition().zoom;
        switch (zoomState) {
            case UseDefaultZoomLevel:
                f = 12.0f;
                break;
            case UseCurrentZoomLevel:
                break;
            case UseCurrentZoomLevelIfHigherThanDefault:
                f = Math.max(f, 12.0f);
                break;
            default:
                throw new IllegalStateException("Unsupported zoom state");
        }
        CameraUpdate newCameraPosition = CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(d, d2)).zoom(f).build());
        if (z) {
            this.googleMap.animateCamera(newCameraPosition);
        } else {
            this.googleMap.moveCamera(newCameraPosition);
        }
    }

    @Subscribe
    public void onGetLocationAddressTaskCompleted(CompletedEvent completedEvent) {
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(FINDING_LOCATION_PROGRESS_TAG);
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
        RequestedVenueLocation requestedVenueLocation = (RequestedVenueLocation) completedEvent.getResult();
        if (requestedVenueLocation != null) {
            requestVenues(requestedVenueLocation);
        } else {
            setCurrentVenueListState(VenueListState.Empty);
        }
    }

    private void requestVenues(RequestedVenueLocation requestedVenueLocation) {
        requestVenues(requestedVenueLocation, ZoomState.UseDefaultZoomLevel);
    }

    private void requestVenues(RequestedVenueLocation requestedVenueLocation, ZoomState zoomState) {
        requestVenues(requestedVenueLocation, zoomState, false);
    }

    private void requestVenues(RequestedVenueLocation requestedVenueLocation, ZoomState zoomState, boolean z) {
        moveToRequestedLocationOrUserLocation(requestedVenueLocation, zoomState);
        this.venuesViewModel.load(new VenuesRequestData(this.userLocation, Strings.toString(this.restaurantEditText.getText()), calculateRadius(), requestedVenueLocation));
        setCurrentSearchBoxAndMapState(z ? this.currentSearchBoxAndMapState : SearchBoxAndMapState.SearchBoxAndMap);
        setCurrentVenueListState(VenueListState.Loading);
        ViewUtils.setGone(this.searchAgainBtn);
        this.lastRequestedVenueLocation = requestedVenueLocation;
        this.googleMap.setOnCameraChangeListener(this.onCameraChangeListener);
    }

    private float calculateRadius() {
        if (isLandscape()) {
            return 2000.0f;
        }
        VisibleRegion visibleRegion = this.googleMap.getProjection().getVisibleRegion();
        float[] fArr = new float[1];
        LatLng latLng = visibleRegion.nearLeft;
        LatLng latLng2 = visibleRegion.farLeft;
        Location.distanceBetween(latLng.latitude, latLng.longitude, latLng2.latitude, latLng2.longitude, fArr);
        return fArr[0] / 2.0f;
    }

    /* access modifiers changed from: private */
    public void myLocationChange(Location location) {
        this.userLocation = location;
        this.googleMap.setOnMyLocationChangeListener(null);
        if (this.madeRequestForUserLocationOnce) {
            moveToRequestedLocationOrUserLocation(null, ZoomState.UseCurrentZoomLevelIfHigherThanDefault, true);
            return;
        }
        requestVenues(null, ZoomState.UseCurrentZoomLevelIfHigherThanDefault, true);
        this.madeRequestForUserLocationOnce = true;
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService("location");
        return checkIfProviderEnabled(locationManager, "gps") || checkIfProviderEnabled(locationManager, "network");
    }

    private boolean checkIfProviderEnabled(LocationManager locationManager, String str) {
        try {
            return locationManager.isProviderEnabled(str);
        } catch (Exception unused) {
            return false;
        }
    }

    public static /* synthetic */ boolean lambda$new$4(VenuesActivity venuesActivity, Marker marker) {
        marker.showInfoWindow();
        LatLng position = marker.getPosition();
        venuesActivity.moveToLocation(position.latitude, position.longitude, ZoomState.UseCurrentZoomLevel, true);
        return true;
    }

    /* access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public void initVenuesForLocation() {
        this.googleMap.setMyLocationEnabled(this.hasLocationPermission);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        List venues = this.venuesViewModel.getVenues();
        if (CollectionUtils.notEmpty((Collection<?>) venues)) {
            this.madeRequestForUserLocationOnce = true;
            setVenues(venues);
        } else {
            this.googleMap.setOnMyLocationChangeListener(this.onMyLocationChangeListener);
        }
        this.googleMap.setOnMarkerClickListener(this.onMarkerClickListener);
        ViewUtils.setVisible(this.myLocationBtn);
        if (!isLocationEnabled()) {
            showLocationSettingsDialog();
        }
    }

    private void checkPermissions() {
        this.hasLocationPermission = false;
        ((PermissionsMixin) mixin(PermissionsMixin.class)).checkPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, new Function0() {
            public final Object invoke() {
                return VenuesActivity.lambda$checkPermissions$7(VenuesActivity.this);
            }
        }, new Function0() {
            public final Object invoke() {
                return VenuesActivity.this.initVenuesForLocation();
            }
        }, new Function0() {
            public final Object invoke() {
                return VenuesActivity.this.showPermissionDeniedDialog();
            }
        }, new Function0() {
            public final Object invoke() {
                return VenuesActivity.this.initVenuesForLocation();
            }
        });
    }

    public static /* synthetic */ Unit lambda$checkPermissions$7(VenuesActivity venuesActivity) {
        venuesActivity.hasLocationPermission = true;
        ((LocalSettingsService) venuesActivity.localSettingsService.get()).setUserAcceptedLocationPermission(venuesActivity.hasLocationPermission);
        venuesActivity.initVenuesForLocation();
        return null;
    }

    /* access modifiers changed from: private */
    public void showPermissionDeniedDialog() {
        new Handler().post(new Runnable() {
            public final void run() {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity) = (r1v0 'this' com.myfitnesspal.feature.restaurantlogging.ui.activity.-$$Lambda$VenuesActivity$PZ0PeS9PMhCJKfzoJxWDF4--tQg A[THIS]) com.myfitnesspal.feature.restaurantlogging.ui.activity.-$$Lambda$VenuesActivity$PZ0PeS9PMhCJKfzoJxWDF4--tQg.f$0 com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity) com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity.lambda$showPermissionDeniedDialog$11(com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity):void type: STATIC in method: com.myfitnesspal.feature.restaurantlogging.ui.activity.-$$Lambda$VenuesActivity$PZ0PeS9PMhCJKfzoJxWDF4--tQg.run():void, dex: classes2.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                    	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                    Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                    	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                    	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                    	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                    	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                    	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                    	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                    	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                    	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                    	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 38 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 55 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity r0 = com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity) com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity.lambda$showPermissionDeniedDialog$11(com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.restaurantlogging.ui.activity.$$Lambda$VenuesActivity$PZ0PeS9PMhCJKfzoJxWDF4tQg.run():void");
            }
        });
    }
}
