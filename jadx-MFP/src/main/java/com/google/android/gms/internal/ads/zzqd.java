package com.google.android.gms.internal.ads;

import android.text.TextUtils;

public final class zzqd {
    public static String zzc(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        int[] zzag = zzag(str2);
        if (zzag[0] != -1) {
            sb.append(str2);
            zza(sb, zzag[1], zzag[2]);
            return sb.toString();
        }
        int[] zzag2 = zzag(str);
        if (zzag[3] == 0) {
            sb.append(str, 0, zzag2[3]);
            sb.append(str2);
            return sb.toString();
        } else if (zzag[2] == 0) {
            sb.append(str, 0, zzag2[2]);
            sb.append(str2);
            return sb.toString();
        } else if (zzag[1] != 0) {
            int i = zzag2[0] + 1;
            sb.append(str, 0, i);
            sb.append(str2);
            return zza(sb, zzag[1] + i, i + zzag[2]);
        } else if (str2.charAt(zzag[1]) == '/') {
            sb.append(str, 0, zzag2[1]);
            sb.append(str2);
            return zza(sb, zzag2[1], zzag2[1] + zzag[2]);
        } else if (zzag2[0] + 2 >= zzag2[1] || zzag2[1] != zzag2[2]) {
            int lastIndexOf = str.lastIndexOf(47, zzag2[2] - 1);
            int i2 = lastIndexOf == -1 ? zzag2[1] : lastIndexOf + 1;
            sb.append(str, 0, i2);
            sb.append(str2);
            return zza(sb, zzag2[1], i2 + zzag[2]);
        } else {
            sb.append(str, 0, zzag2[1]);
            sb.append('/');
            sb.append(str2);
            return zza(sb, zzag2[1], zzag2[1] + zzag[2] + 1);
        }
    }

    private static String zza(StringBuilder sb, int i, int i2) {
        int i3;
        if (i >= i2) {
            return sb.toString();
        }
        if (sb.charAt(i) == '/') {
            i++;
        }
        int i4 = i;
        int i5 = i2;
        int i6 = i4;
        while (i6 <= i5) {
            if (i6 == i5) {
                i3 = i6;
            } else if (sb.charAt(i6) == '/') {
                i3 = i6 + 1;
            } else {
                i6++;
            }
            int i7 = i4 + 1;
            if (i6 == i7 && sb.charAt(i4) == '.') {
                sb.delete(i4, i3);
                i5 -= i3 - i4;
                i6 = i4;
            } else if (i6 == i4 + 2 && sb.charAt(i4) == '.' && sb.charAt(i7) == '.') {
                i4 = sb.lastIndexOf("/", i4 - 2) + 1;
                int i8 = i4 > i ? i4 : i;
                sb.delete(i8, i3);
                i5 -= i3 - i8;
                i6 = i4;
            } else {
                i4 = i6 + 1;
                i6 = i4;
            }
        }
        return sb.toString();
    }

    private static int[] zzag(String str) {
        int i;
        int[] iArr = new int[4];
        if (TextUtils.isEmpty(str)) {
            iArr[0] = -1;
            return iArr;
        }
        int length = str.length();
        int indexOf = str.indexOf(35);
        if (indexOf != -1) {
            length = indexOf;
        }
        int indexOf2 = str.indexOf(63);
        if (indexOf2 == -1 || indexOf2 > length) {
            indexOf2 = length;
        }
        int indexOf3 = str.indexOf(47);
        if (indexOf3 == -1 || indexOf3 > indexOf2) {
            indexOf3 = indexOf2;
        }
        int indexOf4 = str.indexOf(58);
        if (indexOf4 > indexOf3) {
            indexOf4 = -1;
        }
        int i2 = indexOf4 + 2;
        if (i2 < indexOf2 && str.charAt(indexOf4 + 1) == '/' && str.charAt(i2) == '/') {
            i = str.indexOf(47, indexOf4 + 3);
            if (i == -1 || i > indexOf2) {
                i = indexOf2;
            }
        } else {
            i = indexOf4 + 1;
        }
        iArr[0] = indexOf4;
        iArr[1] = i;
        iArr[2] = indexOf2;
        iArr[3] = length;
        return iArr;
    }
}
