package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import org.jetbrains.annotations.NotNull;

public class BitEncoding {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean FORCE_8TO7_ENCODING;

    static {
        String str;
        try {
            str = System.getProperty("kotlin.jvm.serialization.use8to7");
        } catch (SecurityException unused) {
            str = null;
        }
        FORCE_8TO7_ENCODING = "true".equals(str);
    }

    private BitEncoding() {
    }

    private static void addModuloByte(@NotNull byte[] bArr, int i) {
        int length = bArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = (byte) ((bArr[i2] + i) & Statements.GetOwnedFoodIdsDateDescending);
        }
    }

    @NotNull
    public static byte[] decodeBytes(@NotNull String[] strArr) {
        if (strArr.length > 0 && !strArr[0].isEmpty()) {
            char charAt = strArr[0].charAt(0);
            if (charAt == 0) {
                return UtfEncodingKt.stringsToBytes(dropMarker(strArr));
            }
            if (charAt == 65535) {
                strArr = dropMarker(strArr);
            }
        }
        byte[] combineStringArrayIntoBytes = combineStringArrayIntoBytes(strArr);
        addModuloByte(combineStringArrayIntoBytes, Statements.GetOwnedFoodIdsDateDescending);
        return decode7to8(combineStringArrayIntoBytes);
    }

    @NotNull
    private static String[] dropMarker(@NotNull String[] strArr) {
        String[] strArr2 = (String[]) strArr.clone();
        strArr2[0] = strArr2[0].substring(1);
        return strArr2;
    }

    @NotNull
    private static byte[] combineStringArrayIntoBytes(@NotNull String[] strArr) {
        int i = 0;
        for (String length : strArr) {
            i += length.length();
        }
        byte[] bArr = new byte[i];
        int length2 = strArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length2) {
            String str = strArr[i2];
            int length3 = str.length();
            int i4 = i3;
            int i5 = 0;
            while (i5 < length3) {
                int i6 = i4 + 1;
                bArr[i4] = (byte) str.charAt(i5);
                i5++;
                i4 = i6;
            }
            i2++;
            i3 = i4;
        }
        return bArr;
    }

    /* JADX WARNING: type inference failed for: r5v2 */
    /* JADX WARNING: type inference failed for: r5v4 */
    /* JADX WARNING: type inference failed for: r5v5 */
    /* JADX WARNING: type inference failed for: r5v6 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r5v2
  assigns: []
  uses: []
  mth insns count: 30
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 2 */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] decode7to8(@org.jetbrains.annotations.NotNull byte[] r11) {
        /*
            int r0 = r11.length
            int r0 = r0 * 7
            int r0 = r0 / 8
            byte[] r1 = new byte[r0]
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x000b:
            if (r3 >= r0) goto L_0x002f
            byte r6 = r11[r4]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 >>> r5
            r7 = 1
            int r4 = r4 + r7
            byte r8 = r11[r4]
            int r9 = r5 + 1
            int r10 = r7 << r9
            int r10 = r10 - r7
            r7 = r8 & r10
            int r8 = 7 - r5
            int r7 = r7 << r8
            int r6 = r6 + r7
            byte r6 = (byte) r6
            r1[r3] = r6
            r6 = 6
            if (r5 != r6) goto L_0x002b
            int r4 = r4 + 1
            r5 = 0
            goto L_0x002c
        L_0x002b:
            r5 = r9
        L_0x002c:
            int r3 = r3 + 1
            goto L_0x000b
        L_0x002f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.BitEncoding.decode7to8(byte[]):byte[]");
    }
}
