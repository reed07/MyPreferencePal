package com.google.android.gms.internal.ads;

final class zzbtq {
    static String zzaq(zzbpu zzbpu) {
        zzbtr zzbtr = new zzbtr(zzbpu);
        StringBuilder sb = new StringBuilder(zzbtr.size());
        for (int i = 0; i < zzbtr.size(); i++) {
            byte zzem = zzbtr.zzem(i);
            if (zzem == 34) {
                sb.append("\\\"");
            } else if (zzem == 39) {
                sb.append("\\'");
            } else if (zzem != 92) {
                switch (zzem) {
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
                        if (zzem >= 32 && zzem <= 126) {
                            sb.append((char) zzem);
                            break;
                        } else {
                            sb.append('\\');
                            sb.append((char) (((zzem >>> 6) & 3) + 48));
                            sb.append((char) (((zzem >>> 3) & 7) + 48));
                            sb.append((char) ((zzem & 7) + 48));
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
