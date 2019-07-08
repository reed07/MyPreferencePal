package com.google.android.gms.internal.ads;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzaka {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final zzajz<JSONObject> zzdkb = new zzakc();
    private static final zzajx<InputStream> zzdkc = zzakb.zzdkd;

    static final /* synthetic */ InputStream zzf(JSONObject jSONObject) throws JSONException {
        return new ByteArrayInputStream(jSONObject.toString().getBytes(UTF_8));
    }
}
