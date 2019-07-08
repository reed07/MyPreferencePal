package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JWSAlgorithm;
import java.util.Collection;

class AlgorithmSupportMessage {
    private static String itemize(Collection collection) {
        StringBuilder sb = new StringBuilder();
        Object[] array = collection.toArray();
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                if (i < array.length - 1) {
                    sb.append(", ");
                } else if (i == array.length - 1) {
                    sb.append(" or ");
                }
            }
            sb.append(array[i].toString());
        }
        return sb.toString();
    }

    public static String unsupportedJWSAlgorithm(JWSAlgorithm jWSAlgorithm, Collection<JWSAlgorithm> collection) {
        StringBuilder sb = new StringBuilder("Unsupported JWS algorithm ");
        sb.append(jWSAlgorithm);
        sb.append(", must be ");
        sb.append(itemize(collection));
        return sb.toString();
    }

    private AlgorithmSupportMessage() {
    }
}
