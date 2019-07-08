package com.amazon.device.ads;

import android.content.Context;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class DtbAdRequestParamsBuilder {
    private final String LOG_TAG = DtbAdRequestParamsBuilder.class.getSimpleName();

    /* renamed from: com.amazon.device.ads.DtbAdRequestParamsBuilder$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$AdType = new int[AdType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$ads$AdType[AdType.VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    DtbAdRequestParamsBuilder() {
    }

    public HashMap<String, Object> getParams(Context context, List<DTBAdSize> list, Map<String, String> map) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.putAll(getCommonParams(context));
        hashMap.putAll(getSlotParams(list));
        hashMap.putAll(getCustomParams(map));
        return hashMap;
    }

    private HashMap<String, Object> getCommonParams(Context context) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("isDTBMobile", "true");
        hashMap.put("appId", DtbSharedPreferences.getInstance().getAppKey());
        hashMap.put("adsdk", DtbCommonUtils.getSDKVersion());
        String idfa = DtbSharedPreferences.getInstance().getIdfa();
        if (!DtbCommonUtils.isNullOrEmpty(idfa)) {
            hashMap.put("idfa", idfa);
        } else {
            hashMap.putAll(DtbDeviceData.getDeviceDataInstance().getOptionalParams());
        }
        Boolean optOut = DtbSharedPreferences.getInstance().getOptOut();
        if (optOut != null) {
            hashMap.put("oo", Boolean.toString(optOut.booleanValue()));
        }
        JSONObject paramsJson = DtbDeviceData.getDeviceDataInstance().getParamsJson();
        if (paramsJson != null) {
            hashMap.put("dinfo", paramsJson);
        }
        String userAgentString = DtbDeviceData.getDeviceDataInstance().getUserAgentString();
        if (userAgentString != null) {
            hashMap.put("ua", userAgentString);
        }
        hashMap.put("pkg", DtbPackageNativeData.getPackageNativeDataInstance(context).getParamsJson());
        String adId = DtbSharedPreferences.getInstance().getAdId();
        if (adId != null) {
            hashMap.put("ad-id", adId);
        }
        if (AdRegistration.isTestMode()) {
            hashMap.put("isTest", "true");
        }
        if (AdRegistration.isLocationEnabled()) {
            String locationParam = new DtbGeoLocation().getLocationParam();
            if (locationParam != null && !locationParam.isEmpty()) {
                hashMap.put("geoloc", locationParam);
            }
        }
        return hashMap;
    }

    private HashMap<String, Object> getSlotParams(List<DTBAdSize> list) {
        HashMap<String, Object> hashMap = new HashMap<>();
        JSONArray jSONArray = new JSONArray();
        try {
            int i = 1;
            for (DTBAdSize dTBAdSize : list) {
                JSONObject jSONObject = new JSONObject();
                if (dTBAdSize.isInterstitialAd()) {
                    jSONObject.put("sz", "interstitial");
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(dTBAdSize.getWidth());
                    sb.append(AvidJSONUtil.KEY_X);
                    sb.append(dTBAdSize.getHeight());
                    jSONObject.put("sz", sb.toString());
                }
                jSONObject.put("slot", dTBAdSize.getSlotUUID());
                int i2 = i + 1;
                jSONObject.put("slotId", i);
                JSONArray jSONArray2 = new JSONArray();
                if (AnonymousClass1.$SwitchMap$com$amazon$device$ads$AdType[dTBAdSize.getDTBAdType().ordinal()] != 1) {
                    jSONArray2.put(AdType.DISPLAY.toString());
                } else {
                    jSONArray2.put(AdType.VIDEO.toString());
                }
                jSONObject.put("supportedMediaTypes", jSONArray2);
                if (dTBAdSize.getPubSettings() != null) {
                    jSONObject.put("ps", dTBAdSize.getPubSettings());
                }
                jSONArray.put(jSONObject);
                i = i2;
            }
            hashMap.put("slots", jSONArray);
        } catch (JSONException unused) {
            DtbLog.warn(this.LOG_TAG, "Error constructing slot parameters");
        }
        return hashMap;
    }

    private HashMap<String, Object> getCustomParams(Map<String, String> map) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (AdRegistration.isTestMode() && DtbDebugProperties.getInstance().isDebugMode() && map != null) {
            for (String str : map.keySet()) {
                hashMap.put(str, (String) map.get(str));
            }
        }
        return hashMap;
    }
}
