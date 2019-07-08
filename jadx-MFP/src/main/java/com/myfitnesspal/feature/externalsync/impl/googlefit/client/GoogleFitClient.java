package com.myfitnesspal.feature.externalsync.impl.googlefit.client;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants.SyncScopes;
import com.myfitnesspal.feature.externalsync.impl.googlefit.event.GoogleFitConnectErrorEvent;
import com.myfitnesspal.feature.externalsync.impl.googlefit.listener.GoogleFitClientListener;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.GoogleFitScope;
import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitSubscriptionService;
import com.myfitnesspal.feature.externalsync.service.WaitCondition;
import com.myfitnesspal.shared.constants.Constants.Preference;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.squareup.otto.Bus;
import com.uacf.core.preferences.KeyedSharedPreferences;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GoogleFitClient {
    private static final String AUTH_PENDING = "mfp_auth_state_pending";
    public static final int REQUEST_OAUTH = 128;
    /* access modifiers changed from: private */
    public static final String TAG = "GoogleFitClient";
    private boolean authInProgress;
    private final Context context = MyFitnessPalApp.getInstance();
    /* access modifiers changed from: private */
    public GoogleFitClientListener googleFitClientListener;
    private final Lazy<GoogleFitSubscriptionService> googleFitSubscriptionService;
    private final Lazy<KeyedSharedPreferences> keyedSharedPreferences;
    /* access modifiers changed from: private */
    public GoogleApiClient mGoogleApiClient;
    /* access modifiers changed from: private */
    public final Lazy<Bus> messageBus;
    /* access modifiers changed from: private */
    public List<GoogleFitScope> scopes;
    private Lazy<Session> session;
    /* access modifiers changed from: private */
    public final Lazy<SharedPreferences> sharedPreferences;
    private final Lazy<StepService> stepService;
    private String userId;
    /* access modifiers changed from: private */
    public final WaitCondition waitForConnect = new WaitCondition();

    public GoogleFitClient(Lazy<Session> lazy, Lazy<SharedPreferences> lazy2, Lazy<KeyedSharedPreferences> lazy3, Lazy<GoogleFitSubscriptionService> lazy4, Lazy<StepService> lazy5, Lazy<Bus> lazy6) {
        this.session = lazy;
        this.sharedPreferences = lazy2;
        this.keyedSharedPreferences = lazy3;
        this.googleFitSubscriptionService = lazy4;
        this.stepService = lazy5;
        this.messageBus = lazy6;
    }

    public void onCreate(Bundle bundle) {
        this.authInProgress = BundleUtils.getBoolean(bundle, AUTH_PENDING);
    }

    public void onStart(GoogleFitClientListener googleFitClientListener2) {
        updateListener(googleFitClientListener2);
    }

    public void onDestroy(GoogleFitClientListener googleFitClientListener2) {
        if (this.googleFitClientListener == googleFitClientListener2) {
            this.googleFitClientListener = null;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 128) {
            this.authInProgress = false;
            if (i2 == -1) {
                GoogleApiClient googleApiClient = this.mGoogleApiClient;
                if (googleApiClient != null && !googleApiClient.isConnecting() && !this.mGoogleApiClient.isConnected()) {
                    connect();
                }
            }
        }
    }

    public void resetTimePointerForSyncExercises() {
        ((KeyedSharedPreferences) this.keyedSharedPreferences.get()).edit().putLong(Preference.FIT_EXERCISES_SYNC_TIME_POINT, Long.MIN_VALUE).apply();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean(AUTH_PENDING, this.authInProgress);
    }

    public boolean connectAndWait() {
        buildGoogleApiClient();
        if (!this.mGoogleApiClient.isConnected()) {
            this.waitForConnect.reset();
            this.mGoogleApiClient.connect();
            this.waitForConnect.await();
        }
        if (hasScope(new GoogleFitScope(SyncScopes.FITNESS_ACTIVITY_READ_WRITE))) {
            ((GoogleFitSubscriptionService) this.googleFitSubscriptionService.get()).subscribe(this.mGoogleApiClient);
        }
        return this.mGoogleApiClient.isConnected();
    }

    public void connect() {
        buildGoogleApiClient();
        if (this.mGoogleApiClient.isConnected()) {
            GoogleFitClientListener googleFitClientListener2 = this.googleFitClientListener;
            if (googleFitClientListener2 != null) {
                googleFitClientListener2.onConnected();
            }
        } else {
            this.mGoogleApiClient.connect();
        }
        if (hasScope(new GoogleFitScope(SyncScopes.FITNESS_ACTIVITY_READ_WRITE))) {
            ((GoogleFitSubscriptionService) this.googleFitSubscriptionService.get()).subscribe(this.mGoogleApiClient);
        }
    }

    public boolean isAuthInProgress() {
        return this.authInProgress;
    }

    public void setAuthInProgress() {
        this.authInProgress = true;
    }

    public boolean isConnecting() {
        GoogleApiClient googleApiClient = this.mGoogleApiClient;
        return googleApiClient != null && googleApiClient.isConnecting();
    }

    public boolean isConnected() {
        GoogleApiClient googleApiClient = this.mGoogleApiClient;
        return googleApiClient != null && googleApiClient.isConnected();
    }

    public boolean isEnabled() {
        return ((SharedPreferences) this.sharedPreferences.get()).getBoolean(String.format(Locale.ENGLISH, "%s_%s", new Object[]{GoogleFitConstants.SharedPreferences.IS_GOOGLE_FIT_ENABLED, this.userId}), false);
    }

    public void disconnect() {
        GoogleApiClient googleApiClient = this.mGoogleApiClient;
        if (googleApiClient != null && googleApiClient.isConnected()) {
            this.mGoogleApiClient.disconnect();
        }
    }

    public void disable() {
        GoogleApiClient googleApiClient = this.mGoogleApiClient;
        if (googleApiClient != null && googleApiClient.isConnected()) {
            unsubscribeToCollectData(this.mGoogleApiClient, new Function1<Boolean>() {
                public void execute(Boolean bool) {
                    Fitness.ConfigApi.disableFit(GoogleFitClient.this.mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
                        public void onResult(@NonNull Status status) {
                            GoogleFitClient.this.setGoogleFitEnabled(false);
                            GoogleFitClient.this.mGoogleApiClient = null;
                            GoogleFitClient.this.scopes = null;
                            ((SharedPreferences) GoogleFitClient.this.sharedPreferences.get()).edit().remove(GoogleFitConstants.SharedPreferences.SCOPES).apply();
                            Ln.i("Google Fit disabled", new Object[0]);
                            if (GoogleFitClient.this.googleFitClientListener != null) {
                                GoogleFitClient.this.googleFitClientListener.onConnectionDisabled();
                            }
                        }
                    });
                }
            });
        }
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setScopeAndConnect(List<GoogleFitScope> list) {
        if (list != null) {
            this.scopes = new ArrayList(list);
            ((SharedPreferences) this.sharedPreferences.get()).edit().remove(GoogleFitConstants.SharedPreferences.SCOPES).apply();
            this.mGoogleApiClient = null;
            ((SharedPreferences) this.sharedPreferences.get()).edit().putString(GoogleFitConstants.SharedPreferences.SCOPES, mapFromListToJsonString(this.scopes)).apply();
            connect();
        }
    }

    private boolean hasScope(GoogleFitScope googleFitScope) {
        List scopes2 = getScopes();
        return scopes2 != null && scopes2.contains(googleFitScope);
    }

    public GoogleApiClient getGoogleApiClient() {
        return this.mGoogleApiClient;
    }

    public boolean isEnabledForSync() {
        return isEnabled() && this.mGoogleApiClient != null;
    }

    private void buildGoogleApiClient() {
        if (this.mGoogleApiClient == null) {
            Builder addOnConnectionFailedListener = new Builder(this.context).addApi(Fitness.RECORDING_API).addApi(Fitness.HISTORY_API).addApi(Fitness.CONFIG_API).addConnectionCallbacks(new ConnectionCallbacks() {
                public void onConnected(Bundle bundle) {
                    GoogleFitClient.this.waitForConnect.complete();
                    Log.i(GoogleFitClient.TAG, "Connected!!!");
                    GoogleFitClient.this.setGoogleFitEnabled(true);
                    if (GoogleFitClient.this.googleFitClientListener != null) {
                        GoogleFitClient.this.googleFitClientListener.onConnected();
                    }
                }

                public void onConnectionSuspended(int i) {
                    GoogleFitClient.this.waitForConnect.complete();
                    if (i == 2) {
                        Log.d(GoogleFitClient.TAG, "Connection lost.  Cause: Network Lost.");
                    } else if (i == 1) {
                        Log.d(GoogleFitClient.TAG, "Connection lost.  Reason: Service Disconnected");
                    }
                }
            }).addOnConnectionFailedListener(new OnConnectionFailedListener() {
                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                    GoogleFitClient.this.waitForConnect.complete();
                    String access$600 = GoogleFitClient.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Connection failed. Cause: ");
                    sb.append(connectionResult.toString());
                    Log.d(access$600, sb.toString());
                    if (connectionResult.getErrorCode() != 3 || !GoogleFitClient.this.isEnabled()) {
                        ((Bus) GoogleFitClient.this.messageBus.get()).post(new GoogleFitConnectErrorEvent(connectionResult));
                        return;
                    }
                    Log.d(GoogleFitClient.TAG, "Google Fit Service disabled");
                    GoogleFitClient.this.setGoogleFitEnabled(false);
                }
            });
            List<GoogleFitScope> scopes2 = getScopes();
            if (scopes2 != null) {
                for (GoogleFitScope scope : scopes2) {
                    addOnConnectionFailedListener.addScope(getGoogleFitScopeFromMfpFitScope(scope.getScope()));
                }
            }
            this.mGoogleApiClient = addOnConnectionFailedListener.build();
        }
    }

    private void updateListener(GoogleFitClientListener googleFitClientListener2) {
        this.googleFitClientListener = googleFitClientListener2;
    }

    private void unsubscribeToCollectData(GoogleApiClient googleApiClient, Function1<Boolean> function1) {
        ((GoogleFitSubscriptionService) this.googleFitSubscriptionService.get()).unsubscribe(googleApiClient, function1);
    }

    /* access modifiers changed from: private */
    public void setGoogleFitEnabled(boolean z) {
        String str = this.userId;
        if (str != null && str.length() != 0) {
            ((SharedPreferences) this.sharedPreferences.get()).edit().putBoolean(String.format(Locale.ENGLISH, "%s_%s", new Object[]{GoogleFitConstants.SharedPreferences.IS_GOOGLE_FIT_ENABLED, this.userId}), z).apply();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.gms.common.api.Scope getGoogleFitScopeFromMfpFitScope(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = -1245722617(0xffffffffb5bfc807, float:-1.4288825E-6)
            if (r0 == r1) goto L_0x0028
            r1 = 897142804(0x35795014, float:9.287626E-7)
            if (r0 == r1) goto L_0x001e
            r1 = 993118780(0x3b31ca3c, float:0.0027128598)
            if (r0 == r1) goto L_0x0014
            goto L_0x0032
        L_0x0014:
            java.lang.String r0 = "mfp_fitness_nutrition_read_write"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0032
            r3 = 2
            goto L_0x0033
        L_0x001e:
            java.lang.String r0 = "mfp_fitness_body_read_write"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0032
            r3 = 1
            goto L_0x0033
        L_0x0028:
            java.lang.String r0 = "mfp_fitness_activity_read_write"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0032
            r3 = 0
            goto L_0x0033
        L_0x0032:
            r3 = -1
        L_0x0033:
            switch(r3) {
                case 0: goto L_0x004c;
                case 1: goto L_0x0044;
                case 2: goto L_0x003c;
                default: goto L_0x0036;
            }
        L_0x0036:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r3.<init>()
            throw r3
        L_0x003c:
            com.google.android.gms.common.api.Scope r3 = new com.google.android.gms.common.api.Scope
            java.lang.String r0 = "https://www.googleapis.com/auth/fitness.nutrition.write"
            r3.<init>(r0)
            return r3
        L_0x0044:
            com.google.android.gms.common.api.Scope r3 = new com.google.android.gms.common.api.Scope
            java.lang.String r0 = "https://www.googleapis.com/auth/fitness.body.write"
            r3.<init>(r0)
            return r3
        L_0x004c:
            com.google.android.gms.common.api.Scope r3 = new com.google.android.gms.common.api.Scope
            java.lang.String r0 = "https://www.googleapis.com/auth/fitness.activity.write"
            r3.<init>(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient.getGoogleFitScopeFromMfpFitScope(java.lang.String):com.google.android.gms.common.api.Scope");
    }

    private List<GoogleFitScope> getScopes() {
        if (this.scopes == null) {
            List<GoogleFitScope> mapFromJsonStringToList = mapFromJsonStringToList(((SharedPreferences) this.sharedPreferences.get()).getString(GoogleFitConstants.SharedPreferences.SCOPES, null));
            if (mapFromJsonStringToList != null && mapFromJsonStringToList.size() > 0) {
                this.scopes = mapFromJsonStringToList;
            }
        }
        return this.scopes;
    }

    private String mapFromListToJsonString(List<GoogleFitScope> list) {
        String str = "";
        try {
            return new Gson().toJson((Object) list);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return str;
        }
    }

    private List<GoogleFitScope> mapFromJsonStringToList(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            return (List) new Gson().fromJson(str, new TypeToken<List<GoogleFitScope>>() {
            }.getType());
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return arrayList;
        }
    }
}
