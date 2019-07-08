package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzahu;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzayh;
import java.util.Map;
import org.json.JSONObject;

final class zzv implements Runnable {
    private final /* synthetic */ Map zzdfm;
    final /* synthetic */ zzahu zzdfn;
    private final /* synthetic */ HttpClient zzdfo;

    zzv(HttpClient httpClient, Map map, zzahu zzahu) {
        this.zzdfo = httpClient;
        this.zzdfm = map;
        this.zzdfn = zzahu;
    }

    public final void run() {
        zzaxz.zzdn("Received Http request.");
        try {
            JSONObject send = this.zzdfo.send(new JSONObject((String) this.zzdfm.get("http_request")));
            if (send == null) {
                zzaxz.e("Response should not be null.");
            } else {
                zzayh.zzelc.post(new zzw(this, send));
            }
        } catch (Exception e) {
            zzaxz.zzb("Error converting request to json.", e);
        }
    }
}
