package com.amazon.device.ads;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class DTBAdResponse {
    private String bidId;
    private boolean isVideo;
    private Map<String, List<String>> kvpDictionary = new HashMap();
    private Map<DTBAdSize, List<DtbPricePoint>> pricepoints = new HashMap();

    DTBAdResponse() {
    }

    /* access modifiers changed from: 0000 */
    public void setBidId(String str) {
        this.bidId = str;
    }

    /* access modifiers changed from: 0000 */
    public void setKvpDictionary(JSONObject jSONObject) throws JSONException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (this.kvpDictionary.get(str) == null) {
                        this.kvpDictionary.put(str, new ArrayList());
                    }
                    ((List) this.kvpDictionary.get(str)).add(jSONArray.getString(i));
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void putPricePoint(DtbPricePoint dtbPricePoint) {
        if (this.pricepoints.get(dtbPricePoint.getAdSize()) == null) {
            this.pricepoints.put(dtbPricePoint.getAdSize(), new ArrayList());
        }
        ((List) this.pricepoints.get(dtbPricePoint.getAdSize())).add(dtbPricePoint);
    }

    public Map<String, List<String>> getDefaultDisplayAdsRequestCustomParams() {
        HashMap hashMap = new HashMap();
        if (!this.isVideo) {
            if (this.pricepoints.size() > 0) {
                hashMap.put("amzn_b", Collections.singletonList(this.bidId));
                hashMap.put("amzn_h", Collections.singletonList(DtbSharedPreferences.getInstance().getAaxHostname()));
                for (DtbPricePoint pricePoint : (List) this.pricepoints.get((DTBAdSize) getDTBAds().get(0))) {
                    hashMap.put("amznslots", Collections.singletonList(pricePoint.getPricePoint()));
                }
            }
            hashMap.putAll(getKvpDictionary());
        }
        return hashMap;
    }

    private Map<String, List<String>> getKvpDictionary() {
        return this.kvpDictionary;
    }

    public Map<String, String> getDefaultVideoAdsRequestCustomParams() {
        HashMap hashMap = new HashMap();
        if (this.isVideo) {
            hashMap.put("amzn_vid", this.bidId);
            hashMap.put("amzn_h", DtbSharedPreferences.getInstance().getAaxHostname());
            for (DtbPricePoint pricePoint : (List) this.pricepoints.get((DTBAdSize) getDTBAds().get(0))) {
                hashMap.put("amznslots", pricePoint.getPricePoint());
            }
            for (Entry entry : this.kvpDictionary.entrySet()) {
                hashMap.put(entry.getKey(), TextUtils.join(", ", (Iterable) entry.getValue()));
            }
        }
        return hashMap;
    }

    public int getAdCount() {
        return this.pricepoints.size();
    }

    public List<DTBAdSize> getDTBAds() {
        return new ArrayList(this.pricepoints.keySet());
    }

    public String getPricePoints(DTBAdSize dTBAdSize) {
        List list = (List) this.pricepoints.get(dTBAdSize);
        if (list == null || list.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(((DtbPricePoint) list.get(i)).getPricePoint());
            if (i != list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    @Deprecated
    public String getDefaultPricePoints() {
        if (getAdCount() == 0) {
            return null;
        }
        return getPricePoints((DTBAdSize) getDTBAds().get(0));
    }

    /* access modifiers changed from: protected */
    public boolean isVideo() {
        return this.isVideo;
    }

    /* access modifiers changed from: protected */
    public void setVideo(boolean z) {
        this.isVideo = z;
    }
}
