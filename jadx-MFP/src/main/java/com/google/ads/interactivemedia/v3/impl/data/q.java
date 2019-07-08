package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: IMASDK */
final class q extends ae {
    private Boolean attached;
    private v bounds;
    private Boolean hidden;
    private String type;

    q() {
    }

    public final ae attached(boolean z) {
        this.attached = Boolean.valueOf(z);
        return this;
    }

    public final ae bounds(v vVar) {
        if (vVar != null) {
            this.bounds = vVar;
            return this;
        }
        throw new NullPointerException("Null bounds");
    }

    public final ae hidden(boolean z) {
        this.hidden = Boolean.valueOf(z);
        return this;
    }

    public final ae type(String str) {
        if (str != null) {
            this.type = str;
            return this;
        }
        throw new NullPointerException("Null type");
    }

    public final ad build() {
        String str = "";
        if (this.attached == null) {
            str = String.valueOf(str).concat(" attached");
        }
        if (this.bounds == null) {
            str = String.valueOf(str).concat(" bounds");
        }
        if (this.hidden == null) {
            str = String.valueOf(str).concat(" hidden");
        }
        if (this.type == null) {
            str = String.valueOf(str).concat(" type");
        }
        if (!str.isEmpty()) {
            String str2 = "Missing required properties:";
            String valueOf = String.valueOf(str);
            throw new IllegalStateException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        p pVar = new p(this.attached.booleanValue(), this.bounds, this.hidden.booleanValue(), this.type, null);
        return pVar;
    }
}
