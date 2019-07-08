package com.inmobi.commons.core.configs;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: ConfigNetworkClient */
class e implements Runnable {
    private static final String a = "com.inmobi.commons.core.configs.e";
    private f b;
    private a c;
    private final f d;

    /* compiled from: ConfigNetworkClient */
    public interface a {
        void a();

        void a(ConfigResponse configResponse);
    }

    e(a aVar, f fVar, f fVar2) {
        this.c = aVar;
        this.b = fVar;
        this.d = fVar2;
    }

    private void a(f fVar, Map<String, ConfigResponse> map) {
        for (Entry entry : map.entrySet()) {
            ConfigResponse configResponse = (ConfigResponse) entry.getValue();
            String str = (String) entry.getKey();
            if (!configResponse.a()) {
                this.c.a(configResponse);
                fVar.c.remove(str);
            }
        }
    }

    @NonNull
    private static ConfigNetworkResponse a(f fVar) {
        com.inmobi.commons.core.network.e eVar = new com.inmobi.commons.core.network.e(fVar);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        return new ConfigNetworkResponse(fVar.c, eVar.a(), SystemClock.elapsedRealtime() - elapsedRealtime);
    }

    private boolean a(f fVar, int i, Map<String, ConfigResponse> map) throws InterruptedException {
        if (i > fVar.a) {
            for (Entry key : fVar.c.entrySet()) {
                String str = (String) key.getKey();
                if (map.containsKey(str)) {
                    this.c.a((ConfigResponse) map.get(str));
                }
            }
            return true;
        }
        Thread.sleep((long) (fVar.b * 1000));
        return false;
    }

    public void run() {
        Map<String, ConfigResponse> map;
        int i = 0;
        int i2 = 0;
        do {
            try {
                if (i2 > this.b.a) {
                    break;
                }
                ConfigNetworkResponse a2 = a(this.b);
                map = a2.a;
                if (!(a2.a() && this.d != null)) {
                    a(this.b, map);
                    if (this.b.c.isEmpty()) {
                        break;
                    }
                    i2++;
                } else {
                    while (i <= this.d.a) {
                        ConfigNetworkResponse a3 = a(this.d);
                        Map<String, ConfigResponse> map2 = a3.a;
                        if (a3.a()) {
                            break;
                        }
                        a(this.d, map2);
                        if (this.d.c.isEmpty()) {
                            break;
                        }
                        i++;
                        if (a(this.d, i, map2)) {
                            break;
                        }
                    }
                    this.c.a();
                    return;
                }
            } catch (InterruptedException unused) {
                return;
            }
        } while (!a(this.b, i2, map));
        this.c.a();
    }
}
