package com.brightcove.player.offline;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class RequestConfig {
    public static final int DEFAULT_VIDEO_BITRATE = 500000;
    public static final int VISIBILITY_HIDDEN = 2;
    public static final int VISIBILITY_VISIBLE = 0;
    public static final int VISIBILITY_VISIBLE_NOTIFY_COMPLETED = 1;
    public static final int VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION = 3;
    private boolean bluetoothDownloadAllowed;
    private String description;
    private File downloadPath;
    private boolean meteredDownloadAllowed;
    private boolean mobileDownloadAllowed;
    private int notificationVisibility = 0;
    private boolean roamingDownloadAllowed;
    private String title;
    private int videoBitrate = DEFAULT_VIDEO_BITRATE;
    private boolean wifiDownloadAllowed;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RequestVisibility {
    }

    @Nullable
    public File getDownloadPath() {
        return this.downloadPath;
    }

    public RequestConfig setDownloadPath(@NonNull File file) {
        synchronized (this) {
            this.downloadPath = file;
        }
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isMobileDownloadAllowed() {
        return this.mobileDownloadAllowed;
    }

    public boolean isWifiDownloadAllowed() {
        return this.wifiDownloadAllowed;
    }

    public boolean isBluetoothDownloadAllowed() {
        return this.bluetoothDownloadAllowed;
    }

    public boolean isRoamingDownloadAllowed() {
        return this.roamingDownloadAllowed;
    }

    public boolean isMeteredDownloadAllowed() {
        return this.meteredDownloadAllowed;
    }

    public int getNotificationVisibility() {
        return this.notificationVisibility;
    }

    public int getVideoBitrate() {
        return this.videoBitrate;
    }

    public RequestConfig setTitle(String str) {
        synchronized (this) {
            this.title = str;
        }
        return this;
    }

    public RequestConfig setDescription(String str) {
        synchronized (this) {
            this.description = str;
        }
        return this;
    }

    public RequestConfig setMobileDownloadAllowed(boolean z) {
        synchronized (this) {
            this.mobileDownloadAllowed = z;
        }
        return this;
    }

    public RequestConfig setWifiDownloadAllowed(boolean z) {
        synchronized (this) {
            this.wifiDownloadAllowed = z;
        }
        return this;
    }

    public RequestConfig setBluetoothDownloadAllowed(boolean z) {
        synchronized (this) {
            this.bluetoothDownloadAllowed = z;
        }
        return this;
    }

    public RequestConfig setRoamingDownloadAllowed(boolean z) {
        synchronized (this) {
            this.roamingDownloadAllowed = z;
        }
        return this;
    }

    public RequestConfig setMeteredDownloadAllowed(boolean z) {
        synchronized (this) {
            this.meteredDownloadAllowed = z;
        }
        return this;
    }

    public RequestConfig setVideoBitrate(int i) {
        synchronized (this) {
            this.videoBitrate = i;
        }
        return this;
    }

    public RequestConfig setNotificationVisibility(int i) {
        synchronized (this) {
            this.notificationVisibility = i;
        }
        return this;
    }

    public RequestConfig copy() {
        RequestConfig requestConfig = new RequestConfig();
        synchronized (this) {
            requestConfig.downloadPath = this.downloadPath;
            requestConfig.title = this.title;
            requestConfig.description = this.description;
            requestConfig.mobileDownloadAllowed = this.mobileDownloadAllowed;
            requestConfig.wifiDownloadAllowed = this.wifiDownloadAllowed;
            requestConfig.bluetoothDownloadAllowed = this.bluetoothDownloadAllowed;
            requestConfig.roamingDownloadAllowed = this.roamingDownloadAllowed;
            requestConfig.meteredDownloadAllowed = this.meteredDownloadAllowed;
            requestConfig.videoBitrate = this.videoBitrate;
            requestConfig.notificationVisibility = this.notificationVisibility;
        }
        return requestConfig;
    }
}
