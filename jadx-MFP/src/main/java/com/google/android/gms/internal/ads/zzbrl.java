package com.google.android.gms.internal.ads;

import java.io.IOException;

public class zzbrl extends IOException {
    private zzbsl zzfqu = null;

    public zzbrl(String str) {
        super(str);
    }

    public final zzbrl zzj(zzbsl zzbsl) {
        this.zzfqu = zzbsl;
        return this;
    }

    static zzbrl zzanc() {
        return new zzbrl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzbrl zzand() {
        return new zzbrl("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzbrl zzane() {
        return new zzbrl("CodedInputStream encountered a malformed varint.");
    }

    static zzbrl zzanf() {
        return new zzbrl("Protocol message contained an invalid tag (zero).");
    }

    static zzbrl zzang() {
        return new zzbrl("Protocol message end-group tag did not match expected tag.");
    }

    static zzbrm zzanh() {
        return new zzbrm("Protocol message tag had invalid wire type.");
    }

    static zzbrl zzani() {
        return new zzbrl("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    static zzbrl zzanj() {
        return new zzbrl("Failed to parse the message.");
    }

    static zzbrl zzank() {
        return new zzbrl("Protocol message had invalid UTF-8.");
    }
}
