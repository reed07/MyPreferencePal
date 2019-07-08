package com.myfitnesspal.shared.model;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/myfitnesspal/shared/model/UserLocation;", "", "latitude", "", "longitude", "(Ljava/lang/String;Ljava/lang/String;)V", "isLocationValid", "", "()Z", "getLatitude", "()Ljava/lang/String;", "setLatitude", "(Ljava/lang/String;)V", "getLongitude", "setLongitude", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: UserLocation.kt */
public final class UserLocation {
    @Nullable
    private String latitude;
    @Nullable
    private String longitude;

    public UserLocation(@Nullable String str, @Nullable String str2) {
        this.latitude = str;
        this.longitude = str2;
    }

    @Nullable
    public final String getLatitude() {
        return this.latitude;
    }

    @Nullable
    public final String getLongitude() {
        return this.longitude;
    }

    public final void setLatitude(@Nullable String str) {
        this.latitude = str;
    }

    public final void setLongitude(@Nullable String str) {
        this.longitude = str;
    }

    public final boolean isLocationValid() {
        CharSequence charSequence = this.latitude;
        if (charSequence == null || charSequence.length() == 0) {
            return false;
        }
        CharSequence charSequence2 = this.longitude;
        return !(charSequence2 == null || charSequence2.length() == 0);
    }
}
