package com.inmobi.ads;

import com.facebook.share.internal.ShareConstants;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: NativeImageAsset */
public final class as extends ak {
    as(String str, String str2, al alVar, String str3, int i, JSONObject jSONObject) {
        this(str, str2, alVar, str3, new LinkedList(), i, jSONObject);
    }

    as(String str, String str2, al alVar, String str3, List<NativeTracker> list, int i, JSONObject jSONObject) {
        super(str, str2, ShareConstants.IMAGE_URL, alVar, list);
        this.e = str3;
        if (jSONObject != null) {
            this.i = i;
            this.f = jSONObject;
        }
    }
}
