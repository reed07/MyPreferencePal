package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.impl.data.aa;
import com.google.ads.interactivemedia.v3.impl.data.af;
import java.net.MalformedURLException;

/* compiled from: IMASDK */
public final class ado {
    private static final wo a = new wv().a(UiElement.class, af.GSON_TYPE_ADAPTER).a(CompanionAdSlot.class, new adp()).a((xl) new aft()).a();
    private final adq b;
    private final Object c;
    private final String d;
    private final adr e;

    public static ado a(String str) throws MalformedURLException, xh {
        Uri parse = Uri.parse(str);
        String substring = parse.getPath().substring(1);
        if (parse.getQueryParameter("sid") != null) {
            return new ado(adq.valueOf(substring), adr.valueOf(parse.getQueryParameter("type")), parse.getQueryParameter("sid"), a.a(parse.getQueryParameter("data"), aa.class));
        }
        throw new MalformedURLException("Session id must be provided in message.");
    }

    public ado(adq adq, adr adr, String str, Object obj) {
        this.b = adq;
        this.e = adr;
        this.d = str;
        this.c = obj;
    }

    public ado(adq adq, adr adr, String str) {
        this(adq, adr, str, null);
    }

    public final adq a() {
        return this.b;
    }

    public final adr b() {
        return this.e;
    }

    public final Object c() {
        return this.c;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        agj b2 = new agj().b("type", this.e).b("sid", this.d);
        Object obj = this.c;
        if (obj != null) {
            b2.b("data", obj);
        }
        return String.format("%s('%s', %s);", new Object[]{"javascript:adsense.mobileads.afmanotify.receiveMessage", this.b, a.a((Object) b2.a())});
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ado)) {
            return false;
        }
        ado ado = (ado) obj;
        return this.b == ado.b && afx.a(this.c, ado.c) && afx.a((Object) this.d, (Object) ado.d) && this.e == ado.e;
    }

    public final int hashCode() {
        return afx.a(this.b, this.c, this.d, this.e);
    }

    public final String toString() {
        return String.format("JavaScriptMessage [command=%s, type=%s, sid=%s, data=%s]", new Object[]{this.b, this.e, this.d, this.c});
    }
}
