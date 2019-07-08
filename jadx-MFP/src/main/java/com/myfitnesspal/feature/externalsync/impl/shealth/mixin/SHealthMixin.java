package com.myfitnesspal.feature.externalsync.impl.shealth.mixin;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.externalsync.impl.shealth.event.SHealthConnectionErrorEvent;
import com.myfitnesspal.feature.externalsync.impl.shealth.event.SHealthConnectionEvent;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNegativeListener;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import dagger.Lazy;

public class SHealthMixin extends RunnerLifecycleMixin {
    private static final String RESOLVE_S_HEALTH_ERROR_DIALOG_FRAGMENT = "resolve_s_health_dialog_fragment";
    private static final String UNKNOWN_S_HEALTH_ERROR_DIALOG_FRAGMENT = "unknown_s_health_dialog_fragment";
    /* access modifiers changed from: private */
    public static SHealthConnectionErrorEvent lastConnectionError;
    private final SHealthConnection connection;
    private DialogNegativeListener onCancelResolveClickListener = new DialogNegativeListener() {
        public void onClick() {
            SHealthMixin.lastConnectionError = null;
        }
    };
    private DialogPositiveListener onInstallResolveClickListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            if (SHealthMixin.lastConnectionError != null && SHealthMixin.lastConnectionError.getError().hasResolution()) {
                SHealthMixin.lastConnectionError.getError().resolve(SHealthMixin.this.getMfpActivity());
            }
            SHealthMixin.lastConnectionError = null;
        }
    };
    private final Session session;

    public SHealthMixin(MfpUiComponentInterface mfpUiComponentInterface, Lazy<SHealthConnection> lazy) {
        super(mfpUiComponentInterface);
        this.session = mfpUiComponentInterface.getSession();
        this.connection = (SHealthConnection) lazy.get();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.session.getUser().isLoggedIn()) {
            this.connection.connect();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.session.getUser().isLoggedIn()) {
            this.connection.updateStepSourceRegistration(getRunner());
        }
    }

    public SHealthConnection getConnection() {
        return this.connection;
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!RESOLVE_S_HEALTH_ERROR_DIALOG_FRAGMENT.equals(str)) {
            return false;
        }
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) dialogFragment;
        alertDialogFragment.setPositiveListener(this.onInstallResolveClickListener);
        alertDialogFragment.setNegativeListener(this.onCancelResolveClickListener);
        return true;
    }

    public void onConnectionEvent(SHealthConnectionEvent sHealthConnectionEvent) {
        if (sHealthConnectionEvent.isConnected() && sHealthConnectionEvent.isJustPaired() && !sHealthConnectionEvent.consumed()) {
            sHealthConnectionEvent.consume();
            this.connection.startPermissionRequest(getActivity());
        }
    }

    public void onConnectionErrorEvent(SHealthConnectionErrorEvent sHealthConnectionErrorEvent) {
        if (getMfpActivity().isEnabled() && !sHealthConnectionErrorEvent.consumed()) {
            sHealthConnectionErrorEvent.consume();
            if (sHealthConnectionErrorEvent.getError().hasResolution()) {
                lastConnectionError = sHealthConnectionErrorEvent;
                AlertDialogFragment alertDialogFragment = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.shealth_dialog_title)).setMessage((int) R.string.shealth_install_app)).setPositiveText(R.string.shealth_button_resolve, this.onInstallResolveClickListener)).setNegativeText(R.string.cancel, this.onCancelResolveClickListener);
                alertDialogFragment.setCancelable(false);
                getMfpActivity().showDialogFragment(alertDialogFragment, RESOLVE_S_HEALTH_ERROR_DIALOG_FRAGMENT);
                return;
            }
            lastConnectionError = null;
            AlertDialogFragment alertDialogFragment2 = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.shealth_dialog_title)).setMessage((int) R.string.shealth_unknown_error)).setNegativeText(R.string.ok, null);
            alertDialogFragment2.setCancelable(false);
            getMfpActivity().showDialogFragment(alertDialogFragment2, UNKNOWN_S_HEALTH_ERROR_DIALOG_FRAGMENT);
        }
    }
}
