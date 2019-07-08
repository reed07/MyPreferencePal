package com.google.android.gms.internal.measurement;

final class zzwz {
    static String zzd(zzte zzte) {
        zzxa zzxa = new zzxa(zzte);
        StringBuilder sb = new StringBuilder(zzxa.size());
        for (int i = 0; i < zzxa.size(); i++) {
            byte zzam = zzxa.zzam(i);
            if (zzam == 34) {
                sb.append("\\\"");
            } else if (zzam == 39) {
                sb.append("\\'");
            } else if (zzam != 92) {
                switch (zzam) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (zzam >= 32 && zzam <= 126) {
                            sb.append((char) zzam);
                            break;
                        } else {
                            sb.append('\\');
                            sb.append((char) (((zzam >>> 6) & 3) + 48));
                            sb.append((char) (((zzam >>> 3) & 7) + 48));
                            sb.append((char) ((zzam & 7) + 48));
                            break;
                        }
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }
}
