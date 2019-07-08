package com.google.ads.interactivemedia.v3.internal;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* compiled from: IMASDK */
public final class jx {
    private final ByteArrayOutputStream a = new ByteArrayOutputStream(512);
    private final DataOutputStream b = new DataOutputStream(this.a);

    public final byte[] a(ju juVar) {
        this.a.reset();
        try {
            a(this.b, juVar.a);
            a(this.b, juVar.b != null ? juVar.b : "");
            a(this.b, 1000);
            a(this.b, 0);
            a(this.b, juVar.c);
            a(this.b, juVar.d);
            this.b.write(juVar.e);
            this.b.flush();
            return this.a.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void a(DataOutputStream dataOutputStream, String str) throws IOException {
        dataOutputStream.writeBytes(str);
        dataOutputStream.writeByte(0);
    }

    private static void a(DataOutputStream dataOutputStream, long j) throws IOException {
        dataOutputStream.writeByte(((int) (j >>> 24)) & 255);
        dataOutputStream.writeByte(((int) (j >>> 16)) & 255);
        dataOutputStream.writeByte(((int) (j >>> 8)) & 255);
        dataOutputStream.writeByte(((int) j) & 255);
    }
}
