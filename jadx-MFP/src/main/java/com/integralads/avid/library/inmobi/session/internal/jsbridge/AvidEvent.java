package com.integralads.avid.library.inmobi.session.internal.jsbridge;

import org.json.JSONObject;

public class AvidEvent {
    private JSONObject data;
    private int tag;
    private String type;

    public AvidEvent() {
    }

    public AvidEvent(int i, String str, JSONObject jSONObject) {
        this.tag = i;
        this.type = str;
        this.data = jSONObject;
    }

    public String getType() {
        return this.type;
    }

    public JSONObject getData() {
        return this.data;
    }
}
