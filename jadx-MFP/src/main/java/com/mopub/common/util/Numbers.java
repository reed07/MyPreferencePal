package com.mopub.common.util;

public class Numbers {
    private Numbers() {
    }

    public static Double parseDouble(Object obj) throws ClassCastException {
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to parse ");
                sb.append(obj);
                sb.append(" as double.");
                throw new ClassCastException(sb.toString());
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to parse ");
            sb2.append(obj);
            sb2.append(" as double.");
            throw new ClassCastException(sb2.toString());
        }
    }

    public static long convertMillisecondsToSecondsRoundedUp(long j) {
        return Math.round(Math.ceil((double) (((float) j) / 1000.0f)));
    }
}
