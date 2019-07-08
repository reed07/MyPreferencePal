package com.google.android.gms.internal.ads;

import java.util.Locale;

public final class zzoa {
    private final String[] zzbef;
    private final int[] zzbeg;
    private final String[] zzbeh;
    private final int zzbei;

    public static zzoa zzaa(String str) {
        String[] strArr = new String[5];
        int[] iArr = new int[4];
        String[] strArr2 = new String[4];
        strArr[0] = "";
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            int indexOf = str.indexOf("$", i);
            char c = 65535;
            if (indexOf == -1) {
                String valueOf = String.valueOf(strArr[i2]);
                String valueOf2 = String.valueOf(str.substring(i));
                strArr[i2] = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                i = str.length();
            } else if (indexOf != i) {
                String valueOf3 = String.valueOf(strArr[i2]);
                String valueOf4 = String.valueOf(str.substring(i, indexOf));
                strArr[i2] = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
                i = indexOf;
            } else if (str.startsWith("$$", i)) {
                strArr[i2] = String.valueOf(strArr[i2]).concat("$");
                i += 2;
            } else {
                int i3 = i + 1;
                int indexOf2 = str.indexOf("$", i3);
                String substring = str.substring(i3, indexOf2);
                if (substring.equals("RepresentationID")) {
                    iArr[i2] = 1;
                } else {
                    int indexOf3 = substring.indexOf("%0");
                    String str2 = "%01d";
                    if (indexOf3 != -1) {
                        str2 = substring.substring(indexOf3);
                        if (!str2.endsWith("d")) {
                            str2 = String.valueOf(str2).concat("d");
                        }
                        substring = substring.substring(0, indexOf3);
                    }
                    int hashCode = substring.hashCode();
                    if (hashCode != -1950496919) {
                        if (hashCode != 2606829) {
                            if (hashCode == 38199441 && substring.equals("Bandwidth")) {
                                c = 1;
                            }
                        } else if (substring.equals("Time")) {
                            c = 2;
                        }
                    } else if (substring.equals("Number")) {
                        c = 0;
                    }
                    switch (c) {
                        case 0:
                            iArr[i2] = 2;
                            break;
                        case 1:
                            iArr[i2] = 3;
                            break;
                        case 2:
                            iArr[i2] = 4;
                            break;
                        default:
                            String str3 = "Invalid template: ";
                            String valueOf5 = String.valueOf(str);
                            throw new IllegalArgumentException(valueOf5.length() != 0 ? str3.concat(valueOf5) : new String(str3));
                    }
                    strArr2[i2] = str2;
                }
                i2++;
                strArr[i2] = "";
                i = indexOf2 + 1;
            }
        }
        return new zzoa(strArr, iArr, strArr2, i2);
    }

    private zzoa(String[] strArr, int[] iArr, String[] strArr2, int i) {
        this.zzbef = strArr;
        this.zzbeg = iArr;
        this.zzbeh = strArr2;
        this.zzbei = i;
    }

    public final String zza(String str, int i, int i2, long j) {
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        while (true) {
            int i4 = this.zzbei;
            if (i3 < i4) {
                sb.append(this.zzbef[i3]);
                int[] iArr = this.zzbeg;
                if (iArr[i3] == 1) {
                    sb.append(str);
                } else if (iArr[i3] == 2) {
                    sb.append(String.format(Locale.US, this.zzbeh[i3], new Object[]{Integer.valueOf(i)}));
                } else if (iArr[i3] == 3) {
                    sb.append(String.format(Locale.US, this.zzbeh[i3], new Object[]{Integer.valueOf(i2)}));
                } else if (iArr[i3] == 4) {
                    sb.append(String.format(Locale.US, this.zzbeh[i3], new Object[]{Long.valueOf(j)}));
                }
                i3++;
            } else {
                sb.append(this.zzbef[i4]);
                return sb.toString();
            }
        }
    }
}
