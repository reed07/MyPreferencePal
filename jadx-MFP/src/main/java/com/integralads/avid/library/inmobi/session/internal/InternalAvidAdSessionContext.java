package com.integralads.avid.library.inmobi.session.internal;

import android.content.Context;
import com.integralads.avid.library.inmobi.AvidContext;
import com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext;
import org.json.JSONException;
import org.json.JSONObject;

public class InternalAvidAdSessionContext {
    private ExternalAvidAdSessionContext avidAdSessionContext;
    private String avidAdSessionId;
    private String avidAdSessionType;
    private String mediaType;

    public InternalAvidAdSessionContext(Context context, String str, String str2, String str3, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        AvidContext.getInstance().init(context);
        this.avidAdSessionId = str;
        this.avidAdSessionContext = externalAvidAdSessionContext;
        this.avidAdSessionType = str2;
        this.mediaType = str3;
    }

    public String getAvidAdSessionId() {
        return this.avidAdSessionId;
    }

    public JSONObject getFullContext() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("avidAdSessionId", this.avidAdSessionId);
            jSONObject.put("bundleIdentifier", AvidContext.getInstance().getBundleId());
            jSONObject.put("partner", AvidContext.getInstance().getPartnerName());
            jSONObject.put("partnerVersion", this.avidAdSessionContext.getPartnerVersion());
            jSONObject.put("avidLibraryVersion", AvidContext.getInstance().getAvidVersion());
            jSONObject.put(com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext.CONTEXT_AVID_AD_SESSION_TYPE, this.avidAdSessionType);
            jSONObject.put(com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext.CONTEXT_MEDIA_TYPE, this.mediaType);
            jSONObject.put(com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext.CONTEXT_IS_DEFERRED, this.avidAdSessionContext.isDeferred());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
