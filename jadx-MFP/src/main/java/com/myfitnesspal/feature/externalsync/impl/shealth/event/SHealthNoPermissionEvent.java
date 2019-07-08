package com.myfitnesspal.feature.externalsync.impl.shealth.event;

import com.myfitnesspal.shared.event.ConsumableEvent;

public class SHealthNoPermissionEvent extends ConsumableEvent {
    private Error error = Error.UnknownFailure;

    public enum Error {
        UnknownFailure,
        NoPermission,
        UserDenied
    }

    public SHealthNoPermissionEvent(Error error2) {
        this.error = error2;
    }

    public Error getError() {
        return this.error;
    }
}
