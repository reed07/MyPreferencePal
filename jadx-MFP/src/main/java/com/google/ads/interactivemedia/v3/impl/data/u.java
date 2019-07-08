package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: IMASDK */
final class u extends ai {
    private Float volume;

    u() {
    }

    public final ai volume(float f) {
        this.volume = Float.valueOf(f);
        return this;
    }

    public final ah build() {
        String str = "";
        if (this.volume == null) {
            str = String.valueOf(str).concat(" volume");
        }
        if (str.isEmpty()) {
            return new t(this.volume.floatValue(), null);
        }
        String str2 = "Missing required properties:";
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }
}
