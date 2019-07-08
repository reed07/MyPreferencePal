package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: IMASDK */
final class i extends w {
    private Integer height;
    private Integer left;
    private Integer top;
    private Integer width;

    i() {
    }

    public final w left(int i) {
        this.left = Integer.valueOf(i);
        return this;
    }

    public final w top(int i) {
        this.top = Integer.valueOf(i);
        return this;
    }

    public final w height(int i) {
        this.height = Integer.valueOf(i);
        return this;
    }

    public final w width(int i) {
        this.width = Integer.valueOf(i);
        return this;
    }

    public final v build() {
        String str = "";
        if (this.left == null) {
            str = String.valueOf(str).concat(" left");
        }
        if (this.top == null) {
            str = String.valueOf(str).concat(" top");
        }
        if (this.height == null) {
            str = String.valueOf(str).concat(" height");
        }
        if (this.width == null) {
            str = String.valueOf(str).concat(" width");
        }
        if (!str.isEmpty()) {
            String str2 = "Missing required properties:";
            String valueOf = String.valueOf(str);
            throw new IllegalStateException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        h hVar = new h(this.left.intValue(), this.top.intValue(), this.height.intValue(), this.width.intValue(), null);
        return hVar;
    }
}
