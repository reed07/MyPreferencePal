package com.inmobi.commons.core.configs;

import com.facebook.stetho.server.http.HttpStatus;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.network.d;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

final class ConfigNetworkResponse {
    /* access modifiers changed from: private */
    public static final String b = "com.inmobi.commons.core.configs.ConfigNetworkResponse";
    Map<String, ConfigResponse> a = new HashMap();
    private Map<String, a> c;
    private d d;
    private d e;
    private long f;

    public static class ConfigResponse {
        ConfigResponseStatus a;
        a b;
        d c;

        public enum ConfigResponseStatus {
            SUCCESS(200),
            NOT_MODIFIED(304),
            PRODUCT_NOT_FOUND(HttpStatus.HTTP_NOT_FOUND),
            INTERNAL_ERROR(500),
            UNKNOWN(-1);
            
            private int a;

            private ConfigResponseStatus(int i) {
                this.a = i;
            }

            public final int getValue() {
                return this.a;
            }

            public static ConfigResponseStatus fromValue(int i) {
                ConfigResponseStatus[] values;
                for (ConfigResponseStatus configResponseStatus : values()) {
                    if (configResponseStatus.a == i) {
                        return configResponseStatus;
                    }
                }
                return UNKNOWN;
            }
        }

        public ConfigResponse(JSONObject jSONObject, a aVar) {
            this.b = aVar;
            if (jSONObject != null) {
                try {
                    this.a = ConfigResponseStatus.fromValue(jSONObject.getInt("status"));
                    if (this.a == ConfigResponseStatus.SUCCESS) {
                        this.b.a(jSONObject.getJSONObject(Param.CONTENT));
                        if (!this.b.c()) {
                            this.c = new d(2, "The received config has failed validation.");
                            ConfigNetworkResponse.b;
                            StringBuilder sb = new StringBuilder("Config type:");
                            sb.append(this.b.a());
                            sb.append(" Error code:");
                            sb.append(this.c.a);
                            sb.append(" Error message:");
                            sb.append(this.c.b);
                        }
                    } else if (this.a == ConfigResponseStatus.NOT_MODIFIED) {
                        ConfigNetworkResponse.b;
                        StringBuilder sb2 = new StringBuilder("Config type:");
                        sb2.append(this.b.a());
                        sb2.append(" Config not modified");
                    } else {
                        this.c = new d(1, this.a.toString());
                        ConfigNetworkResponse.b;
                        StringBuilder sb3 = new StringBuilder("Config type:");
                        sb3.append(this.b.a());
                        sb3.append(" Error code:");
                        sb3.append(this.c.a);
                        sb3.append(" Error message:");
                        sb3.append(this.c.b);
                    }
                } catch (JSONException e) {
                    this.c = new d(2, e.getLocalizedMessage());
                    ConfigNetworkResponse.b;
                    StringBuilder sb4 = new StringBuilder("Config type:");
                    sb4.append(this.b.a());
                    sb4.append(" Error code:");
                    sb4.append(this.c.a);
                    sb4.append(" Error message:");
                    sb4.append(this.c.b);
                }
            }
        }

        public final boolean a() {
            return this.c != null;
        }
    }

    ConfigNetworkResponse(Map<String, a> map, d dVar, long j) {
        this.c = map;
        this.d = dVar;
        this.f = j;
        c();
    }

    private void c() {
        if (!this.d.a()) {
            try {
                JSONObject jSONObject = new JSONObject(this.d.b());
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                    if (this.c.get(str) != null) {
                        this.a.put(str, new ConfigResponse(jSONObject2, (a) this.c.get(str)));
                    }
                }
            } catch (JSONException e2) {
                this.e = new d(2, e2.getLocalizedMessage());
                StringBuilder sb = new StringBuilder("Error code:");
                sb.append(this.e.a);
                sb.append(" Error message:");
                sb.append(this.e.b);
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("name", a(this.c));
                    hashMap.put("errorCode", "ParsingError");
                    hashMap.put("reason", e2.getLocalizedMessage());
                    hashMap.put("latency", Long.valueOf(this.f));
                    b.a();
                    b.a("root", "InvalidConfig", hashMap);
                } catch (Exception e3) {
                    StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                    sb2.append(e3.getMessage());
                    sb2.append(")");
                }
            }
        } else {
            for (Entry entry : this.c.entrySet()) {
                ConfigResponse configResponse = new ConfigResponse(null, (a) entry.getValue());
                configResponse.c = new d(0, "Network error in fetching config.");
                this.a.put(entry.getKey(), configResponse);
            }
            this.e = new d(0, this.d.b.b);
            StringBuilder sb3 = new StringBuilder("Error code:");
            sb3.append(this.e.a);
            sb3.append(" Error message:");
            sb3.append(this.e.b);
            try {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("name", a(this.c));
                hashMap2.put("errorCode", String.valueOf(this.d.b.a.getValue()));
                hashMap2.put("reason", this.d.b.b);
                hashMap2.put("latency", Long.valueOf(this.f));
                b.a();
                b.a("root", "InvalidConfig", hashMap2);
            } catch (Exception e4) {
                StringBuilder sb4 = new StringBuilder("Error in submitting telemetry event : (");
                sb4.append(e4.getMessage());
                sb4.append(")");
            }
        }
    }

    public final boolean a() {
        d dVar = this.d;
        if (!(dVar == null || dVar.b == null)) {
            if (this.d.b.a != ErrorCode.BAD_REQUEST) {
                int value = this.d.b.a.getValue();
                if (500 <= value && value < 600) {
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    private static String a(Map<String, a> map) {
        String str = "";
        for (String str2 : map.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str2);
            sb.append(",");
            str = sb.toString();
        }
        return str.substring(0, str.length() - 1);
    }
}
