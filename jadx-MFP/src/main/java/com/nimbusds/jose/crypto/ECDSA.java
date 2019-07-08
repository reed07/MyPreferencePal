package com.nimbusds.jose.crypto;

import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Signature;

class ECDSA {
    public static Signature getSignerAndVerifier(JWSAlgorithm jWSAlgorithm, Provider provider) throws JOSEException {
        String str;
        if (jWSAlgorithm.equals(JWSAlgorithm.ES256)) {
            str = "SHA256withECDSA";
        } else if (jWSAlgorithm.equals(JWSAlgorithm.ES384)) {
            str = "SHA384withECDSA";
        } else if (jWSAlgorithm.equals(JWSAlgorithm.ES512)) {
            str = "SHA512withECDSA";
        } else {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(jWSAlgorithm, ECDSAProvider.SUPPORTED_ALGORITHMS));
        }
        if (provider == null) {
            return Signature.getInstance(str);
        }
        try {
            return Signature.getInstance(str, provider);
        } catch (NoSuchAlgorithmException e) {
            StringBuilder sb = new StringBuilder("Unsupported ECDSA algorithm: ");
            sb.append(e.getMessage());
            throw new JOSEException(sb.toString(), e);
        }
    }

    public static int getSignatureByteArrayLength(JWSAlgorithm jWSAlgorithm) throws JOSEException {
        if (jWSAlgorithm.equals(JWSAlgorithm.ES256)) {
            return 64;
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.ES384)) {
            return 96;
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.ES512)) {
            return PacketTypes.DiaryDaySummary;
        }
        throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(jWSAlgorithm, ECDSAProvider.SUPPORTED_ALGORITHMS));
    }

    public static byte[] transcodeSignatureToConcat(byte[] bArr, int i) throws JOSEException {
        int i2;
        if (bArr.length < 8 || bArr[0] != 48) {
            throw new JOSEException("Invalid  ECDSA signature format");
        }
        if (bArr[1] > 0) {
            i2 = 2;
        } else if (bArr[1] == -127) {
            i2 = 3;
        } else {
            throw new JOSEException("Invalid  ECDSA signature format");
        }
        byte b = bArr[i2 + 1];
        int i3 = b;
        while (i3 > 0 && bArr[((i2 + 2) + b) - i3] == 0) {
            i3--;
        }
        int i4 = i2 + 2 + b;
        byte b2 = bArr[i4 + 1];
        int i5 = b2;
        while (i5 > 0 && bArr[((i4 + 2) + b2) - i5] == 0) {
            i5--;
        }
        int max = Math.max(Math.max(i3, i5), i / 2);
        int i6 = i2 - 1;
        if ((bArr[i6] & 255) == bArr.length - i2 && (bArr[i6] & 255) == b + 2 + 2 + b2 && bArr[i2] == 2 && bArr[i4] == 2) {
            int i7 = max * 2;
            byte[] bArr2 = new byte[i7];
            System.arraycopy(bArr, i4 - i3, bArr2, max - i3, i3);
            System.arraycopy(bArr, ((i4 + 2) + b2) - i5, bArr2, i7 - i5, i5);
            return bArr2;
        }
        throw new JOSEException("Invalid  ECDSA signature format");
    }

    public static byte[] transcodeSignatureToDER(byte[] bArr) throws JOSEException {
        byte[] bArr2;
        int length = bArr.length / 2;
        int i = length;
        while (i > 0 && bArr[length - i] == 0) {
            i--;
        }
        int i2 = length - i;
        int i3 = bArr[i2] < 0 ? i + 1 : i;
        int i4 = length;
        while (i4 > 0 && bArr[(length * 2) - i4] == 0) {
            i4--;
        }
        int i5 = (length * 2) - i4;
        int i6 = bArr[i5] < 0 ? i4 + 1 : i4;
        int i7 = i3 + 2 + 2 + i6;
        if (i7 <= 255) {
            int i8 = 1;
            if (i7 < 128) {
                bArr2 = new byte[(i3 + 4 + 2 + i6)];
            } else {
                bArr2 = new byte[(i3 + 5 + 2 + i6)];
                bArr2[1] = -127;
                i8 = 2;
            }
            bArr2[0] = 48;
            int i9 = i8 + 1;
            bArr2[i8] = (byte) i7;
            int i10 = i9 + 1;
            bArr2[i9] = 2;
            int i11 = i10 + 1;
            bArr2[i10] = (byte) i3;
            int i12 = i11 + i3;
            System.arraycopy(bArr, i2, bArr2, i12 - i, i);
            int i13 = i12 + 1;
            bArr2[i12] = 2;
            int i14 = i13 + 1;
            bArr2[i13] = (byte) i6;
            System.arraycopy(bArr, i5, bArr2, (i14 + i6) - i4, i4);
            return bArr2;
        }
        throw new JOSEException("Invalid ECDSA signature format");
    }

    private ECDSA() {
    }
}
