package com.google.ads.interactivemedia.v3.internal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* compiled from: IMASDK */
public final class aac {
    public static final xl A = new abf(Calendar.class, GregorianCalendar.class, Y);
    public static final xl B = a(Locale.class, Z);
    public static final xj<wz> C = new aaz();
    public static final xl D = b(wz.class, C);
    public static final xl E = new abb();
    private static final xj<Class> F = new aad().nullSafe();
    private static final xj<BitSet> G = new aao().nullSafe();
    private static final xj<Boolean> H = new aba();
    private static final xj<Number> I = new abj();
    private static final xj<Number> J = new abk();
    private static final xj<Number> K = new abl();
    private static final xj<AtomicInteger> L = new abm().nullSafe();
    private static final xj<AtomicBoolean> M = new abn().nullSafe();
    private static final xj<AtomicIntegerArray> N = new aae().nullSafe();
    private static final xj<Number> O = new aai();
    private static final xj<Character> P = new aaj();
    private static final xj<String> Q = new aak();
    private static final xj<StringBuilder> R = new aan();
    private static final xj<StringBuffer> S = new aap();
    private static final xj<URL> T = new aaq();
    private static final xj<URI> U = new aar();
    private static final xj<InetAddress> V = new aas();
    private static final xj<UUID> W = new aat();
    private static final xj<Currency> X = new aau().nullSafe();
    private static final xj<Calendar> Y = new aax();
    private static final xj<Locale> Z = new aay();
    public static final xl a = a(Class.class, F);
    public static final xl b = a(BitSet.class, G);
    public static final xj<Boolean> c = new abi();
    public static final xl d = a(Boolean.TYPE, Boolean.class, H);
    public static final xl e = a(Byte.TYPE, Byte.class, I);
    public static final xl f = a(Short.TYPE, Short.class, J);
    public static final xl g = a(Integer.TYPE, Integer.class, K);
    public static final xl h = a(AtomicInteger.class, L);
    public static final xl i = a(AtomicBoolean.class, M);
    public static final xl j = a(AtomicIntegerArray.class, N);
    public static final xj<Number> k = new aaf();
    public static final xj<Number> l = new aag();
    public static final xj<Number> m = new aah();
    public static final xl n = a(Number.class, O);
    public static final xl o = a(Character.TYPE, Character.class, P);
    public static final xj<BigDecimal> p = new aal();
    public static final xj<BigInteger> q = new aam();
    public static final xl r = a(String.class, Q);
    public static final xl s = a(StringBuilder.class, R);
    public static final xl t = a(StringBuffer.class, S);
    public static final xl u = a(URL.class, T);
    public static final xl v = a(URI.class, U);
    public static final xl w = b(InetAddress.class, V);
    public static final xl x = a(UUID.class, W);
    public static final xl y = a(Currency.class, X);
    public static final xl z = new aav();

    public static <TT> xl a(abt<TT> abt, xj<TT> xjVar) {
        return new abc(abt, xjVar);
    }

    public static <TT> xl a(Class<TT> cls, xj<TT> xjVar) {
        return new abd(cls, xjVar);
    }

    public static <TT> xl a(Class<TT> cls, Class<TT> cls2, xj<? super TT> xjVar) {
        return new abe(cls, cls2, xjVar);
    }

    private static <T1> xl b(Class<T1> cls, xj<T1> xjVar) {
        return new abg(cls, xjVar);
    }
}
