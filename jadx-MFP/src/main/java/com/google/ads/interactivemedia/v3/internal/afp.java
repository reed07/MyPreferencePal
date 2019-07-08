package com.google.ads.interactivemedia.v3.internal;

import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import java.io.IOException;

/* compiled from: IMASDK */
class afp {
    private ahc a;
    private byte[] b;
    private final int c = 239;

    public void a() {
        this.b = new byte[this.c];
        byte[] bArr = this.b;
        this.a = ahc.a(bArr, 0, bArr.length);
    }

    public void a(int i, long j) throws IOException {
        ahc ahc = this.a;
        ahc.a(i, 0);
        while ((-128 & j) != 0) {
            ahc.a((byte) ((((int) j) & Statements.GetOwnedFoodIdsDateDescending) | 128));
            j >>>= 7;
        }
        ahc.a((byte) ((int) j));
    }

    public void a(int i, String str) throws IOException {
        ahc ahc = this.a;
        ahc.a(i, 2);
        ahc.a(str);
    }

    public byte[] b() throws IOException {
        int a2 = this.a.a();
        if (a2 < 0) {
            throw new IOException();
        } else if (a2 == 0) {
            return this.b;
        } else {
            byte[] bArr = this.b;
            byte[] bArr2 = new byte[(bArr.length - a2)];
            System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
            return bArr2;
        }
    }

    public afp(int i) {
        a();
    }
}
