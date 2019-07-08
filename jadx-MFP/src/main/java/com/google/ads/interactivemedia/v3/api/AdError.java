package com.google.ads.interactivemedia.v3.api;

import com.brightcove.player.network.DownloadStatus;
import com.google.logging.type.LogSeverity;

/* compiled from: IMASDK */
public final class AdError extends Exception {
    private final AdErrorCode a;
    private final AdErrorType b;

    /* compiled from: IMASDK */
    public enum AdErrorCode {
        INTERNAL_ERROR(-1),
        VAST_MALFORMED_RESPONSE(100),
        UNKNOWN_AD_RESPONSE(1010),
        VAST_LOAD_TIMEOUT(301),
        VAST_TOO_MANY_REDIRECTS(302),
        VIDEO_PLAY_ERROR(LogSeverity.WARNING_VALUE),
        VAST_MEDIA_LOAD_TIMEOUT(402),
        VAST_LINEAR_ASSET_MISMATCH(403),
        OVERLAY_AD_PLAYING_FAILED(500),
        OVERLAY_AD_LOADING_FAILED(502),
        VAST_NONLINEAR_ASSET_MISMATCH(503),
        COMPANION_AD_LOADING_FAILED(603),
        UNKNOWN_ERROR(900),
        VAST_EMPTY_RESPONSE(DownloadStatus.ERROR_FILE_ALREADY_EXISTS),
        FAILED_TO_REQUEST_ADS(DownloadStatus.ERROR_TOO_MANY_REDIRECTS),
        VAST_ASSET_NOT_FOUND(DownloadStatus.ERROR_DEVICE_NOT_FOUND),
        ADS_REQUEST_NETWORK_ERROR(1012),
        INVALID_ARGUMENTS(1101),
        PLAYLIST_NO_CONTENT_TRACKING(1205);
        
        private final int a;

        private AdErrorCode(int i) {
            this.a = i;
        }

        public final int getErrorNumber() {
            return this.a;
        }

        public final boolean equals(int i) {
            return this.a == i;
        }

        public final String toString() {
            String name = name();
            int i = this.a;
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 41);
            sb.append("AdErrorCode [name: ");
            sb.append(name);
            sb.append(", number: ");
            sb.append(i);
            sb.append("]");
            return sb.toString();
        }
    }

    /* compiled from: IMASDK */
    public enum AdErrorType {
        LOAD,
        PLAY
    }

    public AdError(AdErrorType adErrorType, int i, String str) {
        AdErrorCode adErrorCode;
        AdErrorCode[] values = AdErrorCode.values();
        int length = values.length;
        int i2 = 0;
        while (true) {
            if (i2 < length) {
                adErrorCode = values[i2];
                if (adErrorCode.getErrorNumber() == i) {
                    break;
                }
                i2++;
            } else if (1204 == i) {
                adErrorCode = AdErrorCode.INTERNAL_ERROR;
            } else {
                adErrorCode = AdErrorCode.UNKNOWN_ERROR;
            }
        }
        this(adErrorType, adErrorCode, str);
    }

    public AdError(AdErrorType adErrorType, AdErrorCode adErrorCode, String str) {
        super(str);
        this.b = adErrorType;
        this.a = adErrorCode;
    }

    public final AdErrorType getErrorType() {
        return this.b;
    }

    public final AdErrorCode getErrorCode() {
        return this.a;
    }

    public final int getErrorCodeNumber() {
        return this.a.getErrorNumber();
    }

    public final String getMessage() {
        return super.getMessage();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.b);
        String valueOf2 = String.valueOf(this.a);
        String message = getMessage();
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 45 + String.valueOf(valueOf2).length() + String.valueOf(message).length());
        sb.append("AdError [errorType: ");
        sb.append(valueOf);
        sb.append(", errorCode: ");
        sb.append(valueOf2);
        sb.append(", message: ");
        sb.append(message);
        sb.append("]");
        return sb.toString();
    }
}
