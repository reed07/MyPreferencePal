package com.samsung.accessory.safiletransfer.a;

import org.json.JSONException;
import org.json.JSONObject;

public final class k {
    private String a;
    private String b;
    private String c;
    private String d;
    private long e;
    private long f;
    private String g;
    private String h;
    private String i;
    private String j;

    public k() {
    }

    public k(String str, String str2, String str3, String str4, long j2, long j3, String str5, String str6, String str7, String str8) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = j2;
        this.f = j3;
        this.g = str5;
        this.h = str6;
        this.i = str7;
        this.j = str8;
    }

    public final JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("SourcePath", this.a);
        jSONObject.put("DestinationPath", this.b);
        jSONObject.put("PeerId", this.c);
        jSONObject.put("ContainerId", this.d);
        jSONObject.put("AccessoryId", this.e);
        jSONObject.put("FileSize", this.f);
        jSONObject.put("FileName", this.g);
        jSONObject.put("FileURI", this.h);
        jSONObject.put("PackageName", this.i);
        jSONObject.put("AgentClassName", this.j);
        return jSONObject;
    }
}
