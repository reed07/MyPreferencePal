package com.inmobi.commons.core.configs;

import com.facebook.ads.internal.j.e;
import com.facebook.appevents.AppEventsConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PkConfig */
public final class g extends a {
    public String a = "010001";
    public String b = "E72409364B865B757E1D6B8DB73011BBB1D20C1A9F931ADD3C4C09E2794CE102F8AA7F2D50EB88F9880A576E6C7B0E95712CAE9416F7BACB798564627846E93B";
    public String c = AppEventsConstants.EVENT_PARAM_VALUE_YES;
    private String d = "rsa";

    public final String a() {
        return "pk";
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        this.a = jSONObject.getString(e.a);
        this.b = jSONObject.getString("m");
        this.d = jSONObject.getString("alg");
        this.c = jSONObject.getString("ver");
    }

    public final JSONObject b() throws JSONException {
        JSONObject b2 = super.b();
        b2.put(e.a, this.a);
        b2.put("m", this.b);
        b2.put("alg", this.d);
        b2.put("ver", this.c);
        return b2;
    }

    public final boolean c() {
        return (this.a.trim().length() == 0 || this.b.trim().length() == 0 || this.d.trim().length() == 0 || this.c.trim().length() == 0) ? false : true;
    }

    public final a d() {
        return new g();
    }
}
