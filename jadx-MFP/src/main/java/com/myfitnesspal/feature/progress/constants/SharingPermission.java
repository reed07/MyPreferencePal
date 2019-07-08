package com.myfitnesspal.feature.progress.constants;

import com.myfitnesspal.android.R;

public enum SharingPermission {
    Community(R.string.permission_mfp_community),
    FriendsOnly(R.string.permission_friends_only);
    
    private final int stringId;

    private SharingPermission(int i) {
        this.stringId = i;
    }

    public int getStringId() {
        return this.stringId;
    }
}
