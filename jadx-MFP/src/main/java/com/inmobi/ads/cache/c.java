package com.inmobi.ads.cache;

import android.net.Uri;
import android.support.annotation.NonNull;
import com.inmobi.a.n;
import com.inmobi.commons.core.a.a;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AdAssetFetcher */
public final class c {
    private static final String b = "c";
    e a;

    c(e eVar) {
        this.a = eVar;
    }

    static String a(@NonNull a aVar, File file, long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", aVar.d);
            jSONObject.put("saved_url", Uri.fromFile(file));
            jSONObject.put("size_in_bytes", file.length());
            jSONObject.put("download_started_at", j);
            jSONObject.put("download_ended_at", j2);
        } catch (JSONException e) {
            a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return jSONObject.toString().replace("\"", "\\\"");
    }

    static void a(long j, long j2, long j3) {
        try {
            n.a().a(0);
            n.a().b(j2);
            n.a().c(j3 - j);
        } catch (Exception e) {
            new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }
}
