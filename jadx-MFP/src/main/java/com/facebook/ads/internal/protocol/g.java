package com.facebook.ads.internal.protocol;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.w.h.b;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class g {
    private final a a;
    @Nullable
    private final Long b;
    @Nullable
    private final String c;
    @Nullable
    private final String d;

    private enum a {
        ID,
        CREATIVE,
        NONE
    }

    public g(Context context, String str, String str2, e eVar) {
        if (TextUtils.isEmpty(str)) {
            this.a = a.NONE;
            this.b = null;
            this.d = null;
            this.c = null;
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            switch (a.valueOf(jSONObject.getString("type").toUpperCase())) {
                case ID:
                    this.a = a.ID;
                    this.b = Long.valueOf(jSONObject.getString("bid_id"));
                    this.d = jSONObject.getString("device_id");
                    this.c = null;
                    break;
                case CREATIVE:
                    this.a = a.CREATIVE;
                    this.b = Long.valueOf(jSONObject.getString("bid_id"));
                    this.d = jSONObject.getString("device_id");
                    this.c = new JSONObject(jSONObject.getString("payload")).toString();
                    break;
                default:
                    AdErrorType adErrorType = AdErrorType.BID_PAYLOAD_ERROR;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unsupported BidPayload type ");
                    sb.append(jSONObject.getString("type"));
                    throw new b(adErrorType, sb.toString());
            }
            if (!jSONObject.getString("sdk_version").equals("5.1.0")) {
                throw new b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for SDK version %s being used on SDK version %s", new Object[]{this.b, jSONObject.getString("sdk_version"), "5.1.0"}));
            } else if (jSONObject.getString("resolved_placement_id").equals(str2)) {
                HashSet hashSet = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(e.WEBVIEW_INTERSTITIAL_HORIZONTAL.a()), Integer.valueOf(e.WEBVIEW_INTERSTITIAL_VERTICAL.a()), Integer.valueOf(e.WEBVIEW_INTERSTITIAL_TABLET.a()), Integer.valueOf(e.WEBVIEW_INTERSTITIAL_UNKNOWN.a())}));
                if (jSONObject.getInt(MessengerShareContentUtility.ATTACHMENT_TEMPLATE_TYPE) != eVar.a()) {
                    if (!hashSet.contains(Integer.valueOf(jSONObject.getInt(MessengerShareContentUtility.ATTACHMENT_TEMPLATE_TYPE))) || !hashSet.contains(Integer.valueOf(eVar.a()))) {
                        throw new b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for template %s being used on template %s", new Object[]{this.b, Integer.valueOf(jSONObject.getInt(MessengerShareContentUtility.ATTACHMENT_TEMPLATE_TYPE)), eVar}));
                    }
                }
            } else {
                throw new b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for placement %s being used on placement %s", new Object[]{this.b, jSONObject.getString("resolved_placement_id"), str2}));
            }
        } catch (JSONException e) {
            com.facebook.ads.internal.w.h.a.b(context, "api", b.d, e);
            throw new b(AdErrorType.BID_PAYLOAD_ERROR, "Invalid BidPayload", e);
        }
    }

    public void a(String str) {
        if (!this.d.equals(str)) {
            throw new b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for IDFA %s being used on IDFA %s", new Object[]{this.b, this.d, str}));
        }
    }

    public boolean a() {
        return this.a == a.CREATIVE;
    }

    @Nullable
    public String b() {
        return this.c;
    }

    public boolean c() {
        return this.a != a.NONE;
    }

    @Nullable
    public String d() {
        Long l = this.b;
        if (l == null) {
            return null;
        }
        return l.toString();
    }
}
