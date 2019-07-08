package com.myfitnesspal.feature.externalsync.impl.googlefit.mixin;

import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.event.GoogleFitConnectErrorEvent;
import com.myfitnesspal.feature.externalsync.impl.googlefit.event.GoogleFitConnectedEvent;
import com.myfitnesspal.feature.externalsync.impl.googlefit.event.GoogleFitDisabledEvent;
import com.myfitnesspal.feature.externalsync.impl.googlefit.listener.GoogleFitClientListener;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitStepsUtils;
import com.myfitnesspal.feature.externalsync.task.RegisterStepSourceTask;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Ln;
import com.uacf.taskrunner.Runner.CacheMode;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;

public class GoogleFitMixin extends RunnerLifecycleMixin {
    private BusEventHandler busEventHandler = new BusEventHandler();
    /* access modifiers changed from: private */
    public Lazy<GoogleFitClient> googleFitClient;
    private GoogleFitClientListener googleFitClientListener = new GoogleFitClientListener() {
        public void onConnectionSuspended(int i) {
        }

        public void onConnected() {
            if (GoogleFitMixin.this.session.getUser().isLoggedIn()) {
                GoogleFitMixin.this.getMessageBus().post(new GoogleFitConnectedEvent());
                new RegisterStepSourceTask(GoogleFitMixin.this.stepService, GoogleFitStepsUtils.createGoogleFitStepSource(GoogleFitMixin.this.session), 0).setDedupeMode(DedupeMode.CancelExisting).setCacheMode(CacheMode.None).run(GoogleFitMixin.this.getRunner());
            }
        }

        public void onConnectionDisabled() {
            if (GoogleFitMixin.this.session.getUser().isLoggedIn()) {
                GoogleFitMixin.this.getMessageBus().post(new GoogleFitDisabledEvent());
                new RegisterStepSourceTask(GoogleFitMixin.this.stepService, GoogleFitStepsUtils.createGoogleFitStepSource(GoogleFitMixin.this.session), 1).setDedupeMode(DedupeMode.CancelExisting).setCacheMode(CacheMode.None).run(GoogleFitMixin.this.getRunner());
            }
        }
    };
    /* access modifiers changed from: private */
    public Session session;
    /* access modifiers changed from: private */
    public Lazy<StepService> stepService;

    private class BusEventHandler {
        private BusEventHandler() {
        }

        @Subscribe
        public void onGoogleFitConnectError(GoogleFitConnectErrorEvent googleFitConnectErrorEvent) {
            if (GoogleFitMixin.this.getMfpActivity().isEnabled() && !googleFitConnectErrorEvent.consumed()) {
                googleFitConnectErrorEvent.consume();
                ConnectionResult connectionResult = googleFitConnectErrorEvent.getConnectionResult();
                if (!connectionResult.hasResolution()) {
                    GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), GoogleFitMixin.this.getActivity(), 0).show();
                } else if (!((GoogleFitClient) GoogleFitMixin.this.googleFitClient.get()).isAuthInProgress()) {
                    try {
                        Ln.d("Attempting to resolve failed connection", new Object[0]);
                        ((GoogleFitClient) GoogleFitMixin.this.googleFitClient.get()).setAuthInProgress();
                        connectionResult.startResolutionForResult(GoogleFitMixin.this.getActivity(), 128);
                    } catch (SendIntentException e) {
                        Ln.e("Exception while starting resolution activity", e);
                    }
                }
            }
        }
    }

    public GoogleFitMixin(MfpUiComponentInterface mfpUiComponentInterface, Lazy<StepService> lazy, Session session2, Lazy<GoogleFitClient> lazy2) {
        super(mfpUiComponentInterface);
        this.session = session2;
        this.stepService = lazy;
        this.googleFitClient = lazy2;
    }

    public GoogleFitClient getClient() {
        return (GoogleFitClient) this.googleFitClient.get();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((GoogleFitClient) this.googleFitClient.get()).onCreate(bundle);
        ((GoogleFitClient) this.googleFitClient.get()).setUserId(this.session.getUser().getUserId());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ((GoogleFitClient) this.googleFitClient.get()).onActivityResult(i, i2, intent);
    }

    public void onStart() {
        super.onStart();
        ((GoogleFitClient) this.googleFitClient.get()).onStart(this.googleFitClientListener);
    }

    public void onPause() {
        super.onPause();
        getMessageBus().unregister(this.busEventHandler);
    }

    public void onResume() {
        super.onResume();
        getMessageBus().register(this.busEventHandler);
        if (this.session.getUser().isLoggedIn() && ((GoogleFitClient) this.googleFitClient.get()).isEnabled() && getMfpActivity().showAsTopLevelActivity()) {
            ((GoogleFitClient) this.googleFitClient.get()).connect();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ((GoogleFitClient) this.googleFitClient.get()).onSaveInstanceState(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        ((GoogleFitClient) this.googleFitClient.get()).onDestroy(this.googleFitClientListener);
    }
}
