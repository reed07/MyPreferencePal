package com.facebook.ads.internal.ipc;

import android.content.Context;
import android.os.Message;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.RewardData;
import com.facebook.ads.internal.c.c;
import com.facebook.ads.internal.c.d;
import com.facebook.ads.internal.c.e;
import com.facebook.ads.internal.c.g;
import com.facebook.ads.internal.c.j;
import com.facebook.ads.internal.e.a.C0004a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.w.h.b;
import com.facebook.stetho.websocket.CloseCodes;
import java.io.Serializable;
import java.util.EnumSet;

public class a {
    public static final String a = "a";
    private final Context b;
    private final com.facebook.ads.internal.e.a c = com.facebook.ads.internal.e.a.a();

    a(Context context) {
        this.b = context;
    }

    public boolean a(Message message) {
        com.facebook.ads.internal.e.a aVar;
        int i;
        String string = message.getData().getString("STR_AD_ID_KEY");
        int i2 = message.what;
        switch (i2) {
            case 1010:
                String string2 = message.getData().getString("STR_PLACEMENT_KEY");
                String string3 = message.getData().getString("STR_BID_PAYLOAD_KEY");
                EnumSet<CacheFlag> enumSet = (EnumSet) message.getData().getSerializable("SRL_INT_CACHE_FLAGS_KEY");
                String string4 = message.getData().getString("STR_EXTRA_HINTS_KEY");
                AdInternalSettings.a = message.getData().getBundle("BUNDLE_SETTINGS_KEY");
                g gVar = new g(this.b, null, string2);
                gVar.d = string4;
                gVar.f = string3;
                gVar.e = enumSet;
                C0004a e = com.facebook.ads.internal.e.a.a().e(string);
                if (e != null) {
                    if (e.c == null) {
                        d dVar = new d(gVar, this.c, string);
                        dVar.a(gVar.e, gVar.f);
                        e.c = dVar;
                    } else if (e.c instanceof d) {
                        ((d) e.c).a(gVar.e, gVar.f);
                    }
                    this.c.a(1015, string);
                } else {
                    int i3 = b.m;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Missing ad: ");
                    sb.append(string);
                    com.facebook.ads.internal.w.h.a.b(this.b, "api", i3, new Exception(sb.toString()));
                }
                return true;
            case CloseCodes.UNEXPECTED_CONDITION /*1011*/:
                c a2 = com.facebook.ads.internal.e.a.a().a(string);
                if (a2 == null || !(a2 instanceof d)) {
                    int i4 = b.m;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Missing bundle for message: ");
                    sb2.append(message);
                    com.facebook.ads.internal.w.h.a.b(this.b, "api", i4, new Exception(sb2.toString()));
                } else {
                    ((d) a2).e();
                    this.c.a(1016, string);
                }
                return true;
            case 1012:
                com.facebook.ads.internal.e.a.a().b(string);
                aVar = this.c;
                i = 1017;
                break;
            default:
                switch (i2) {
                    case 2000:
                        String string5 = message.getData().getString("STR_PLACEMENT_KEY");
                        String string6 = message.getData().getString("STR_BID_PAYLOAD_KEY");
                        Boolean valueOf = Boolean.valueOf(message.getData().getBoolean("BOOL_RV_FAIL_ON_CACHE_FAILURE_KEY"));
                        String string7 = message.getData().getString("STR_EXTRA_HINTS_KEY");
                        AdInternalSettings.a = message.getData().getBundle("BUNDLE_SETTINGS_KEY");
                        j jVar = new j(this.b, string5, null);
                        jVar.d = string7;
                        jVar.f = string6;
                        jVar.g = valueOf.booleanValue();
                        Serializable serializable = message.getData().getSerializable("SRL_RV_REWARD_DATA_KEY");
                        if (serializable instanceof RewardData) {
                            jVar.e = (RewardData) serializable;
                        }
                        C0004a e2 = com.facebook.ads.internal.e.a.a().e(string);
                        if (e2 != null) {
                            if (e2.c == null) {
                                e eVar = new e(jVar, this.c, string);
                                eVar.a(jVar.f, jVar.g);
                                e2.c = eVar;
                            } else if (e2.c instanceof e) {
                                ((e) e2.c).a(jVar.f, jVar.g);
                            }
                            this.c.a(2010, string);
                        } else {
                            int i5 = b.m;
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("Missing ad: ");
                            sb3.append(string);
                            com.facebook.ads.internal.w.h.a.b(this.b, "api", i5, new Exception(sb3.toString()));
                        }
                        return true;
                    case 2001:
                        c a3 = com.facebook.ads.internal.e.a.a().a(string);
                        if (a3 == null || !(a3 instanceof e)) {
                            int i6 = b.m;
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append("Missing ad: ");
                            sb4.append(string);
                            com.facebook.ads.internal.w.h.a.b(this.b, "api", i6, new Exception(sb4.toString()));
                        } else {
                            ((e) a3).a(message.getData().getInt("INT_RV_APP_ORIENTATION_KEY", 0));
                            this.c.a(2011, string);
                        }
                        return true;
                    case 2002:
                        com.facebook.ads.internal.e.a.a().b(string);
                        aVar = this.c;
                        i = 2012;
                        break;
                    case 2003:
                        c a4 = com.facebook.ads.internal.e.a.a().a(string);
                        if (a4 == null || !(a4 instanceof e)) {
                            int i7 = b.m;
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("Missing ad: ");
                            sb5.append(string);
                            com.facebook.ads.internal.w.h.a.b(this.b, "api", i7, new Exception(sb5.toString()));
                        } else {
                            e eVar2 = (e) a4;
                            Serializable serializable2 = message.getData().getSerializable("SRL_RV_REWARD_DATA_KEY");
                            if (serializable2 instanceof RewardData) {
                                eVar2.a((RewardData) serializable2);
                            }
                        }
                        return true;
                    default:
                        return false;
                }
        }
        aVar.a(i, string);
        return true;
    }
}
