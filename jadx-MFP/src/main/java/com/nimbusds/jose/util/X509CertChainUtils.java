package com.nimbusds.jose.util;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import net.minidev.json.JSONArray;

public class X509CertChainUtils {
    public static List<Base64> parseX509CertChain(JSONArray jSONArray) throws ParseException {
        LinkedList linkedList = new LinkedList();
        int i = 0;
        while (i < jSONArray.size()) {
            Object obj = jSONArray.get(i);
            if (obj == null) {
                StringBuilder sb = new StringBuilder("The X.509 certificate at position ");
                sb.append(i);
                sb.append(" must not be null");
                throw new ParseException(sb.toString(), 0);
            } else if (obj instanceof String) {
                linkedList.add(new Base64((String) obj));
                i++;
            } else {
                StringBuilder sb2 = new StringBuilder("The X.509 certificate at position ");
                sb2.append(i);
                sb2.append(" must be encoded as a Base64 string");
                throw new ParseException(sb2.toString(), 0);
            }
        }
        return linkedList;
    }

    private X509CertChainUtils() {
    }
}
