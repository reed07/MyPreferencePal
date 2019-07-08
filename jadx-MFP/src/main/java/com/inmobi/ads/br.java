package com.inmobi.ads;

/* compiled from: Utils */
public final class br {
    public static String a(String str) {
        if (str.equalsIgnoreCase("window.mraidview")) {
            return "mraidview";
        }
        if (str.equalsIgnoreCase("window.imraidview")) {
            return "imraidview";
        }
        if (str.equalsIgnoreCase("window.imaiview")) {
            return "imaiview";
        }
        StringBuilder sb = new StringBuilder("NA_ERROR_");
        sb.append(str);
        return sb.toString();
    }
}
