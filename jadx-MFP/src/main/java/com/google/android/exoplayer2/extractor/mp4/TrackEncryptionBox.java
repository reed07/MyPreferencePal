package com.google.android.exoplayer2.extractor.mp4;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.TrackOutput.CryptoData;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;

public final class TrackEncryptionBox {
    private static final String TAG = "TrackEncryptionBox";
    public final CryptoData cryptoData;
    public final byte[] defaultInitializationVector;
    public final boolean isEncrypted;
    public final int perSampleIvSize;
    @Nullable
    public final String schemeType;

    public TrackEncryptionBox(boolean z, @Nullable String str, int i, byte[] bArr, int i2, int i3, @Nullable byte[] bArr2) {
        boolean z2 = true;
        boolean z3 = i == 0;
        if (bArr2 != null) {
            z2 = false;
        }
        Assertions.checkArgument(z2 ^ z3);
        this.isEncrypted = z;
        this.schemeType = str;
        this.perSampleIvSize = i;
        this.defaultInitializationVector = bArr2;
        this.cryptoData = new CryptoData(schemeToCryptoMode(str), bArr, i2, i3);
    }

    private static int schemeToCryptoMode(@Nullable String str) {
        if (str == null) {
            return 1;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != 3046605) {
            if (hashCode != 3046671) {
                if (hashCode != 3049879) {
                    if (hashCode == 3049895 && str.equals(C.CENC_TYPE_cens)) {
                        c = 1;
                    }
                } else if (str.equals(C.CENC_TYPE_cenc)) {
                    c = 0;
                }
            } else if (str.equals(C.CENC_TYPE_cbcs)) {
                c = 3;
            }
        } else if (str.equals(C.CENC_TYPE_cbc1)) {
            c = 2;
        }
        switch (c) {
            case 0:
            case 1:
                return 1;
            case 2:
            case 3:
                return 2;
            default:
                String str2 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Unsupported protection scheme type '");
                sb.append(str);
                sb.append("'. Assuming AES-CTR crypto mode.");
                Log.w(str2, sb.toString());
                return 1;
        }
    }
}
