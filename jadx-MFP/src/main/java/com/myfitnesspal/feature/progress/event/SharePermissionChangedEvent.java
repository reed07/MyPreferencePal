package com.myfitnesspal.feature.progress.event;

import com.myfitnesspal.feature.progress.constants.SharingPermission;

public class SharePermissionChangedEvent {
    private SharingPermission sharingPermission;

    public SharePermissionChangedEvent(SharingPermission sharingPermission2) {
        this.sharingPermission = sharingPermission2;
    }

    public SharingPermission getSharingPermission() {
        return this.sharingPermission;
    }
}
