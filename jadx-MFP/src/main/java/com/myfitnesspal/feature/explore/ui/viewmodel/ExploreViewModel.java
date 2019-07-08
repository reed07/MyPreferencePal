package com.myfitnesspal.feature.explore.ui.viewmodel;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.LocationServices;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.explore.service.ExploreService;
import com.myfitnesspal.feature.explore.service.ExploreService.Response;
import com.myfitnesspal.feature.profile.ui.viewmodel.MyInfoViewModel.OnlineDataTask;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.taskrunner.Runner;
import com.uacf.taskrunner.Tasks.Blocking;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;

public class ExploreViewModel extends RunnerViewModel<Void> {
    private final ConfigService configService;
    private Response data;
    /* access modifiers changed from: private */
    public GoogleApiClient googleApiClient;
    /* access modifiers changed from: private */
    public Location lastKnownLocation;
    private Set<Integer> seenCards = new HashSet();

    public static class ExploreDataTask extends Blocking<Response, Throwable> {
        public static final String NAME = OnlineDataTask.class.getCanonicalName();
        @Inject
        ExploreService exploreService;
        Location location;

        ExploreDataTask(ConfigService configService, @Nullable Location location2) {
            this.location = location2;
            MyFitnessPalApp.getInstance().component().inject(this);
        }

        static boolean loading(Runner runner) {
            return runner.running(NAME);
        }

        /* access modifiers changed from: protected */
        public Response exec(Context context) throws Throwable {
            return this.exploreService.getData(this.location);
        }
    }

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int LAST_KNOWN_LOCATION = ViewModelPropertyId.next();
    }

    public ExploreViewModel(Runner runner, ConfigService configService2) {
        super(runner);
        this.configService = configService2;
    }

    public Response getData() {
        return this.data;
    }

    public void load(Void... voidArr) {
        if (ConfigUtils.isNearbyVenuesExploreCardEnabled(this.configService)) {
            this.googleApiClient.connect();
        } else {
            startExploreDataTask(null);
        }
    }

    public void onResume(Context context) {
        initGoogleApiClient(context);
    }

    public void onPause() {
        if (this.googleApiClient.isConnected() || this.googleApiClient.isConnecting()) {
            this.googleApiClient.disconnect();
        }
        this.googleApiClient = null;
    }

    public void markCardSeen(int i) {
        this.seenCards.add(Integer.valueOf(i));
    }

    public boolean cardSeen(int i) {
        return this.seenCards.contains(Integer.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.isFrom(getRunner()) && taskDetails.getTaskName().equals(ExploreDataTask.NAME)) {
            if (taskDetails.successful()) {
                this.data = (Response) taskDetails.getResult();
                setState(State.Loaded);
                return;
            }
            setState(State.Error);
        }
    }

    /* access modifiers changed from: private */
    public void startExploreDataTask(@Nullable Location location) {
        if (!ExploreDataTask.loading(getRunner())) {
            setState(State.Loading);
            getRunner().run(ExploreDataTask.NAME, new ExploreDataTask(this.configService, location));
        }
    }

    private void initGoogleApiClient(Context context) {
        this.googleApiClient = new Builder(context).addApi(LocationServices.API).addConnectionCallbacks(new ConnectionCallbacks() {
            public void onConnectionSuspended(int i) {
            }

            public void onConnected(@Nullable Bundle bundle) {
                ExploreViewModel exploreViewModel = ExploreViewModel.this;
                exploreViewModel.lastKnownLocation = exploreViewModel.getSFLocation();
                ExploreViewModel.this.notifyPropertyChanged(Property.LAST_KNOWN_LOCATION);
                ExploreViewModel.this.googleApiClient.disconnect();
                ExploreViewModel exploreViewModel2 = ExploreViewModel.this;
                exploreViewModel2.startExploreDataTask(exploreViewModel2.lastKnownLocation);
            }
        }).build();
    }

    public Location getLastKnownLocation() {
        return this.lastKnownLocation;
    }

    /* access modifiers changed from: private */
    public Location getSFLocation() {
        Location location = new Location("fused");
        location.setAccuracy(56.1f);
        location.setAltitude(0.0d);
        location.setLatitude(37.7799813d);
        location.setLongitude(-122.390806d);
        return location;
    }
}
