package com.mopub.common;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.common.privacy.AdvertisingId;
import com.mopub.network.AdResponse;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdReport implements Serializable {
    private static final long serialVersionUID = 1;
    private final AdResponse mAdResponse;
    private final String mAdUnitId;
    private final AdvertisingId mAdvertisingId;
    private final Locale mDeviceLocale;
    private final String mDeviceModel;
    private final String mSdkVersion;

    public AdReport(@NonNull String str, @NonNull ClientMetadata clientMetadata, @NonNull AdResponse adResponse) {
        this.mAdUnitId = str;
        this.mSdkVersion = clientMetadata.getSdkVersion();
        this.mDeviceModel = clientMetadata.getDeviceModel();
        this.mDeviceLocale = clientMetadata.getDeviceLocale();
        this.mAdvertisingId = clientMetadata.getMoPubIdentifier().getAdvertisingInfo();
        this.mAdResponse = adResponse;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        appendKeyValue(sb, "sdk_version", this.mSdkVersion);
        appendKeyValue(sb, "creative_id", this.mAdResponse.getDspCreativeId());
        appendKeyValue(sb, "platform_version", Integer.toString(VERSION.SDK_INT));
        appendKeyValue(sb, "device_model", this.mDeviceModel);
        appendKeyValue(sb, "ad_unit_id", this.mAdUnitId);
        String str2 = "device_locale";
        Locale locale = this.mDeviceLocale;
        if (locale == null) {
            str = null;
        } else {
            str = locale.toString();
        }
        appendKeyValue(sb, str2, str);
        appendKeyValue(sb, "device_id", this.mAdvertisingId.getIdentifier(MoPub.canCollectPersonalInformation()));
        appendKeyValue(sb, "network_type", this.mAdResponse.getNetworkType());
        appendKeyValue(sb, Http.PLATFORM, "android");
        appendKeyValue(sb, "timestamp", getFormattedTimeStamp(this.mAdResponse.getTimestamp()));
        appendKeyValue(sb, AppEventsConstants.EVENT_PARAM_AD_TYPE, this.mAdResponse.getAdType());
        Object width = this.mAdResponse.getWidth();
        Object height = this.mAdResponse.getHeight();
        String str3 = "ad_size";
        StringBuilder sb2 = new StringBuilder();
        sb2.append("{");
        if (width == null) {
            width = "0";
        }
        sb2.append(width);
        sb2.append(", ");
        if (height == null) {
            height = "0";
        }
        sb2.append(height);
        sb2.append("}");
        appendKeyValue(sb, str3, sb2.toString());
        return sb.toString();
    }

    public String getResponseString() {
        return this.mAdResponse.getStringBody();
    }

    public String getDspCreativeId() {
        return this.mAdResponse.getDspCreativeId();
    }

    private void appendKeyValue(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(" : ");
        sb.append(str2);
        sb.append("\n");
    }

    private String getFormattedTimeStamp(long j) {
        if (j != -1) {
            return new SimpleDateFormat("M/d/yy hh:mm:ss a z", Locale.US).format(new Date(j));
        }
        return null;
    }
}
