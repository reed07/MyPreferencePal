package com.samsung.accessory.safiletransfer.a;

import org.json.JSONException;
import org.json.JSONObject;

public final class j {
    private String a;
    private String b;
    private String c;
    private String d;
    private long e;
    private String f;
    private String g;

    public j() {
    }

    public j(String str, String str2, String str3, String str4, long j, String str5, String str6) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = j;
        this.f = str5;
        this.g = str6;
    }

    public final JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("SourcePath", this.a);
        jSONObject.put("DestinationPath", this.b);
        jSONObject.put("PeerId", this.c);
        jSONObject.put("ContainerId", this.d);
        jSONObject.put("AccessoryId", this.e);
        jSONObject.put("PackageName", this.f);
        jSONObject.put("AgentClassName", this.g);
        return jSONObject;
    }
}
