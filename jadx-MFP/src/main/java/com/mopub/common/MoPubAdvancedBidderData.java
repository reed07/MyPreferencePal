package com.mopub.common;

import android.support.annotation.NonNull;
import com.mopub.common.logging.MoPubLog;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class MoPubAdvancedBidderData {
    @NonNull
    final String mCreativeNetworkName;
    @NonNull
    final String mToken;

    public MoPubAdvancedBidderData(@NonNull String str, @NonNull String str2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        this.mToken = str;
        this.mCreativeNetworkName = str2;
    }

    @NonNull
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(GooglePlayConstants.BILLING_JSON_FIELD_TOKEN, this.mToken);
        } catch (JSONException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid token format: ");
            sb.append(this.mToken);
            MoPubLog.e(sb.toString());
        }
        return jSONObject;
    }
}
