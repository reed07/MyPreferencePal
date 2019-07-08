package org.apache.sanselan.formats.jpeg;

import java.io.IOException;
import java.io.InputStream;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.common.BinaryFileParser;

public class JpegUtils extends BinaryFileParser implements JpegConstants {

    public interface Visitor {
        boolean beginSOS();

        boolean visitSOS(int i, byte[] bArr, InputStream inputStream);

        boolean visitSegment(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) throws ImageReadException, IOException;
    }

    public JpegUtils() {
        setByteOrder(77);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x007d A[SYNTHETIC, Splitter:B:40:0x007d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void traverseJFIF(org.apache.sanselan.common.byteSources.ByteSource r11, org.apache.sanselan.formats.jpeg.JpegUtils.Visitor r12) throws org.apache.sanselan.ImageReadException, java.io.IOException {
        /*
            r10 = this;
            java.io.InputStream r11 = r11.getInputStream()     // Catch:{ all -> 0x0079 }
            byte[] r0 = SOI     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = "Not a Valid JPEG File: doesn't begin with 0xffd8"
            r10.readAndVerifyBytes(r11, r0, r1)     // Catch:{ all -> 0x0077 }
            int r0 = r10.getByteOrder()     // Catch:{ all -> 0x0077 }
        L_0x000f:
            java.lang.String r1 = "markerBytes"
            java.lang.String r2 = "markerBytes"
            r3 = 2
            byte[] r6 = r10.readByteArray(r1, r3, r11, r2)     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = "marker"
            int r5 = r10.convertByteArrayToShort(r1, r6, r0)     // Catch:{ all -> 0x0077 }
            r1 = 65497(0xffd9, float:9.1781E-41)
            if (r5 == r1) goto L_0x0053
            r1 = 65498(0xffda, float:9.1782E-41)
            if (r5 != r1) goto L_0x0029
            goto L_0x0053
        L_0x0029:
            java.lang.String r1 = "segmentLengthBytes"
            java.lang.String r2 = "segmentLengthBytes"
            byte[] r8 = r10.readByteArray(r1, r3, r11, r2)     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = "segmentLength"
            int r7 = r10.convertByteArrayToShort(r1, r8, r0)     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = "Segment Data"
            int r2 = r7 + -2
            java.lang.String r3 = "Invalid Segment: insufficient data"
            byte[] r9 = r10.readByteArray(r1, r2, r11, r3)     // Catch:{ all -> 0x0077 }
            r4 = r12
            boolean r1 = r4.visitSegment(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0077 }
            if (r1 != 0) goto L_0x000f
            if (r11 == 0) goto L_0x0052
            r11.close()     // Catch:{ Exception -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r11 = move-exception
            org.apache.sanselan.util.Debug.debug(r11)
        L_0x0052:
            return
        L_0x0053:
            boolean r0 = r12.beginSOS()     // Catch:{ all -> 0x0077 }
            if (r0 != 0) goto L_0x0064
            if (r11 == 0) goto L_0x0063
            r11.close()     // Catch:{ Exception -> 0x005f }
            goto L_0x0063
        L_0x005f:
            r11 = move-exception
            org.apache.sanselan.util.Debug.debug(r11)
        L_0x0063:
            return
        L_0x0064:
            boolean r12 = r12.visitSOS(r5, r6, r11)     // Catch:{ all -> 0x0077 }
            r12 = r12 ^ 1
            if (r11 == 0) goto L_0x0076
            if (r12 == 0) goto L_0x0076
            r11.close()     // Catch:{ Exception -> 0x0072 }
            goto L_0x0076
        L_0x0072:
            r11 = move-exception
            org.apache.sanselan.util.Debug.debug(r11)
        L_0x0076:
            return
        L_0x0077:
            r12 = move-exception
            goto L_0x007b
        L_0x0079:
            r12 = move-exception
            r11 = 0
        L_0x007b:
            if (r11 == 0) goto L_0x0085
            r11.close()     // Catch:{ Exception -> 0x0081 }
            goto L_0x0085
        L_0x0081:
            r11 = move-exception
            org.apache.sanselan.util.Debug.debug(r11)
        L_0x0085:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.sanselan.formats.jpeg.JpegUtils.traverseJFIF(org.apache.sanselan.common.byteSources.ByteSource, org.apache.sanselan.formats.jpeg.JpegUtils$Visitor):void");
    }
}
