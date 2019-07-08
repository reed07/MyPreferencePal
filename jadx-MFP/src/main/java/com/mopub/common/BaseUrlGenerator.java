package com.mopub.common;

import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.network.Networking;
import com.mopub.network.PlayServicesUrlRewriter;

public abstract class BaseUrlGenerator {
    private boolean mFirstParam;
    private StringBuilder mStringBuilder;

    public abstract String generateUrlString(String str);

    /* access modifiers changed from: protected */
    public void initUrlString(String str, String str2) {
        StringBuilder sb = new StringBuilder(Networking.getScheme());
        sb.append("://");
        sb.append(str);
        sb.append(str2);
        this.mStringBuilder = sb;
        this.mFirstParam = true;
    }

    /* access modifiers changed from: protected */
    public String getFinalUrlString() {
        return this.mStringBuilder.toString();
    }

    /* access modifiers changed from: protected */
    public void addParam(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            this.mStringBuilder.append(getParamDelimiter());
            this.mStringBuilder.append(str);
            this.mStringBuilder.append("=");
            this.mStringBuilder.append(Uri.encode(str2));
        }
    }

    /* access modifiers changed from: protected */
    public void addParam(String str, Boolean bool) {
        if (bool != null) {
            this.mStringBuilder.append(getParamDelimiter());
            this.mStringBuilder.append(str);
            this.mStringBuilder.append("=");
            this.mStringBuilder.append(bool.booleanValue() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
        }
    }

    private String getParamDelimiter() {
        if (!this.mFirstParam) {
            return "&";
        }
        this.mFirstParam = false;
        return "?";
    }

    /* access modifiers changed from: protected */
    public void setApiVersion(String str) {
        addParam("v", str);
    }

    /* access modifiers changed from: protected */
    public void setAppVersion(String str) {
        addParam("av", str);
    }

    /* access modifiers changed from: protected */
    public void setExternalStoragePermission(boolean z) {
        addParam("android_perms_ext_storage", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
    }

    /* access modifiers changed from: protected */
    public void setDeviceInfo(String... strArr) {
        StringBuilder sb = new StringBuilder();
        if (strArr != null && strArr.length >= 1) {
            for (int i = 0; i < strArr.length - 1; i++) {
                sb.append(strArr[i]);
                sb.append(",");
            }
            sb.append(strArr[strArr.length - 1]);
            addParam("dn", sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void appendAdvertisingInfoTemplates() {
        addParam("udid", PlayServicesUrlRewriter.UDID_TEMPLATE);
        addParam("dnt", PlayServicesUrlRewriter.DO_NOT_TRACK_TEMPLATE);
    }

    /* access modifiers changed from: protected */
    public void setDeviceDimensions(@NonNull Point point) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(point.x);
        addParam("w", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("");
        sb2.append(point.y);
        addParam("h", sb2.toString());
    }
}
