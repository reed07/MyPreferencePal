package com.facebook.ads.internal.w.b;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import org.json.JSONArray;

public class s {
    public static final String a(Throwable th) {
        if (th == null) {
            return null;
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    public static String a(List<String> list, String str) {
        StringBuilder sb = new StringBuilder("");
        for (String append : list) {
            sb.append(append);
            sb.append(str);
        }
        return sb.toString();
    }

    public static final String a(StackTraceElement[] stackTraceElementArr) {
        JSONArray jSONArray = new JSONArray();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            jSONArray.put(stackTraceElement.toString());
        }
        return jSONArray.toString();
    }
}
