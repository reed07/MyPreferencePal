package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec.CryptoInfo;

public final class zzhk {
    public byte[] iv;
    private byte[] key;
    private int mode;
    public int[] numBytesOfClearData;
    public int[] numBytesOfEncryptedData;
    private int numSubSamples;
    private int zzagb;
    private int zzagc;
    private final CryptoInfo zzagd;
    private final zzhm zzage;

    public zzhk() {
        this.zzagd = zzqe.SDK_INT >= 16 ? new CryptoInfo() : null;
        this.zzage = zzqe.SDK_INT >= 24 ? new zzhm(this.zzagd) : null;
    }

    public final void set(int i, int[] iArr, int[] iArr2, byte[] bArr, byte[] bArr2, int i2) {
        this.numSubSamples = i;
        this.numBytesOfClearData = iArr;
        this.numBytesOfEncryptedData = iArr2;
        this.key = bArr;
        this.iv = bArr2;
        this.mode = i2;
        this.zzagb = 0;
        this.zzagc = 0;
        if (zzqe.SDK_INT >= 16) {
            CryptoInfo cryptoInfo = this.zzagd;
            cryptoInfo.numSubSamples = this.numSubSamples;
            cryptoInfo.numBytesOfClearData = this.numBytesOfClearData;
            cryptoInfo.numBytesOfEncryptedData = this.numBytesOfEncryptedData;
            cryptoInfo.key = this.key;
            cryptoInfo.iv = this.iv;
            cryptoInfo.mode = this.mode;
            if (zzqe.SDK_INT >= 24) {
                this.zzage.set(0, 0);
            }
        }
    }

    @TargetApi(16)
    public final CryptoInfo zzdr() {
        return this.zzagd;
    }
}
