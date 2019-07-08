package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.common.BaseUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;

class PositioningUrlGenerator extends BaseUrlGenerator {
    @NonNull
    private String mAdUnitId;
    @NonNull
    private final Context mContext;

    public PositioningUrlGenerator(@NonNull Context context) {
        this.mContext = context;
    }

    @NonNull
    public PositioningUrlGenerator withAdUnitId(@NonNull String str) {
        this.mAdUnitId = str;
        return this;
    }

    public String generateUrlString(@NonNull String str) {
        initUrlString(str, Constants.POSITIONING_HANDLER);
        setAdUnitId(this.mAdUnitId);
        setApiVersion(AppEventsConstants.EVENT_PARAM_VALUE_YES);
        ClientMetadata instance = ClientMetadata.getInstance(this.mContext);
        setSdkVersion(instance.getSdkVersion());
        setDeviceInfo(instance.getDeviceManufacturer(), instance.getDeviceModel(), instance.getDeviceProduct());
        setAppVersion(instance.getAppVersion());
        appendAdvertisingInfoTemplates();
        return getFinalUrlString();
    }

    private void setAdUnitId(@NonNull String str) {
        addParam("id", str);
    }

    private void setSdkVersion(@NonNull String str) {
        addParam("nv", str);
    }
}
