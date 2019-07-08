package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import okio.ByteString;

public final class CertificatePinner {
    public static final CertificatePinner DEFAULT = new Builder().build();
    private final Map<String, Set<ByteString>> hostnameToPins;

    public static final class Builder {
        /* access modifiers changed from: private */
        public final Map<String, Set<ByteString>> hostnameToPins = new LinkedHashMap();

        public CertificatePinner build() {
            return new CertificatePinner(this);
        }
    }

    private CertificatePinner(Builder builder) {
        this.hostnameToPins = Util.immutableMap(builder.hostnameToPins);
    }

    public void check(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        Set<ByteString> findMatchingPins = findMatchingPins(str);
        if (findMatchingPins != null) {
            int size = list.size();
            int i = 0;
            while (i < size) {
                if (!findMatchingPins.contains(sha1((X509Certificate) list.get(i)))) {
                    i++;
                } else {
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Certificate pinning failure!");
            sb.append("\n  Peer certificate chain:");
            int size2 = list.size();
            for (int i2 = 0; i2 < size2; i2++) {
                X509Certificate x509Certificate = (X509Certificate) list.get(i2);
                sb.append("\n    ");
                sb.append(pin(x509Certificate));
                sb.append(": ");
                sb.append(x509Certificate.getSubjectDN().getName());
            }
            sb.append("\n  Pinned certificates for ");
            sb.append(str);
            sb.append(":");
            for (ByteString byteString : findMatchingPins) {
                sb.append("\n    sha1/");
                sb.append(byteString.base64());
            }
            throw new SSLPeerUnverifiedException(sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public Set<ByteString> findMatchingPins(String str) {
        Set<ByteString> set;
        Set<ByteString> set2 = (Set) this.hostnameToPins.get(str);
        int indexOf = str.indexOf(46);
        if (indexOf != str.lastIndexOf(46)) {
            Map<String, Set<ByteString>> map = this.hostnameToPins;
            StringBuilder sb = new StringBuilder();
            sb.append("*.");
            sb.append(str.substring(indexOf + 1));
            set = (Set) map.get(sb.toString());
        } else {
            set = null;
        }
        if (set2 == null && set == null) {
            return null;
        }
        if (set2 == null || set == null) {
            return set2 != null ? set2 : set;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(set2);
        linkedHashSet.addAll(set);
        return linkedHashSet;
    }

    public static String pin(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            StringBuilder sb = new StringBuilder();
            sb.append("sha1/");
            sb.append(sha1((X509Certificate) certificate).base64());
            return sb.toString();
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    private static ByteString sha1(X509Certificate x509Certificate) {
        return Util.sha1(ByteString.of(x509Certificate.getPublicKey().getEncoded()));
    }
}
