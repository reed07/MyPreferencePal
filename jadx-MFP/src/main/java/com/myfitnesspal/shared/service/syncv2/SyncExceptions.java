package com.myfitnesspal.shared.service.syncv2;

import com.myfitnesspal.feature.registration.model.PasswordResetData;
import com.myfitnesspal.shared.api.ApiException;
import com.uacf.sync.engine.UacfScheduleException;

public final class SyncExceptions {

    public static class ApiSyncException extends UacfScheduleException {
        public ApiSyncException(ApiException apiException) {
            super(apiException, apiException.getStatusCode(), apiException.getMessage());
        }

        public ApiException getCause() {
            return (ApiException) super.getCause();
        }
    }

    public static class PasswordResetRequiredException extends UacfScheduleException {
        private PasswordResetData resetData;

        public PasswordResetRequiredException(PasswordResetData passwordResetData, int i, String str) {
            super(i, str);
            this.resetData = passwordResetData;
        }

        public PasswordResetData getResetData() {
            return this.resetData;
        }
    }

    public static class UnknownLegacySyncFailure extends UacfScheduleException {
        public UnknownLegacySyncFailure(int i, String str) {
            super(i, str);
        }

        public UnknownLegacySyncFailure(Throwable th, int i, String str) {
            super(th, i, str);
        }
    }

    private SyncExceptions() {
    }
}
