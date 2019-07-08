package com.myfitnesspal.shared.util;

import android.os.CountDownTimer;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.Strings;

public class PincodeHelper {
    private static PincodeHelper INSTANCE = null;
    private static final int TICK_INTERVAL = 100;
    private static final int TIMEOUT = 500;
    /* access modifiers changed from: private */
    public boolean enteredApp = false;
    private boolean ignorePincodeTemporarily = false;
    private String pincode = null;
    private Boolean pincodeLockEnabled = null;
    /* access modifiers changed from: private */
    public boolean pincodeSuccess = false;
    private ApplicationInBackgroundTimer timer;

    private class ApplicationInBackgroundTimer extends CountDownTimer {
        public void onTick(long j) {
        }

        ApplicationInBackgroundTimer(long j, long j2) {
            super(j, j2);
        }

        public void onFinish() {
            PincodeHelper.this.enteredApp = false;
            PincodeHelper.this.pincodeSuccess = false;
        }
    }

    public static synchronized PincodeHelper current() {
        PincodeHelper pincodeHelper;
        synchronized (PincodeHelper.class) {
            if (INSTANCE == null) {
                INSTANCE = new PincodeHelper();
            }
            pincodeHelper = INSTANCE;
        }
        return pincodeHelper;
    }

    private PincodeHelper() {
        INSTANCE = this;
    }

    public void setEnteredApp(boolean z) {
        this.enteredApp = z;
    }

    public void setPincodeSuccess(boolean z) {
        this.pincodeSuccess = z;
    }

    public void setIgnorePincodeTemporarily(boolean z) {
        this.ignorePincodeTemporarily = z;
    }

    public boolean hasEnteredApp() {
        return this.enteredApp;
    }

    public boolean isPincodeSuccess() {
        return this.pincodeSuccess;
    }

    public String getPincode(Session session, DbConnectionManager dbConnectionManager) {
        String str = this.pincode;
        if (str != null) {
            return str;
        }
        return dbConnectionManager.usersDbAdapter().appUnlockPINCodeForUser(session.getUser().getUserV1());
    }

    private void startCountDown() {
        ApplicationInBackgroundTimer applicationInBackgroundTimer = new ApplicationInBackgroundTimer(500, 100);
        this.timer = applicationInBackgroundTimer;
        this.timer.start();
    }

    private void cancelCountDown() {
        ApplicationInBackgroundTimer applicationInBackgroundTimer = this.timer;
        if (applicationInBackgroundTimer != null) {
            applicationInBackgroundTimer.cancel();
        }
    }

    public void activityResumed(boolean z) {
        if (z) {
            cancelCountDown();
        }
    }

    public void activityPaused(boolean z) {
        if (z && !this.ignorePincodeTemporarily) {
            startCountDown();
        }
    }

    public static boolean isPincodeValid(String str) {
        return Strings.notEmpty(str) && str.length() == 4;
    }

    public boolean isPincodeLockEnabled(GlobalSettingsService globalSettingsService) {
        if (this.pincodeLockEnabled == null) {
            this.pincodeLockEnabled = Boolean.valueOf(globalSettingsService.getRequiresPinCodeOnAppEntry());
        }
        return this.pincodeLockEnabled.booleanValue();
    }

    public void updatePincode(String str, Session session, GlobalSettingsService globalSettingsService, DbConnectionManager dbConnectionManager) {
        boolean isPincodeValid = isPincodeValid(str);
        if (!isPincodeValid) {
            str = "";
        }
        this.pincode = str;
        this.pincodeSuccess = isPincodeValid;
        globalSettingsService.setRequiresPinCodeOnAppEntry(isPincodeValid);
        this.pincodeLockEnabled = Boolean.valueOf(isPincodeValid);
        dbConnectionManager.usersDbAdapter().saveAppUnlockPINCode(this.pincode, session.getUser());
    }
}
