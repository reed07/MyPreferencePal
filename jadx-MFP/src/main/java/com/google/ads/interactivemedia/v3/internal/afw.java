package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class afw extends afv {
    static final afw a = new afw();
    private static final int b = Integer.numberOfLeadingZeros(31);

    afw() {
        super("CharMatcher.whitespace()");
    }

    public final boolean a(char c) {
        return " 　\r   　 \u000b　   　 \t     \f 　 　　 \n 　".charAt((48906 * c) >>> b) == c;
    }
}
