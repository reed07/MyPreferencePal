package com.lightstep.tracer.shared;

import com.google.protobuf.Timestamp;
import com.google.protobuf.Timestamp.Builder;
import java.math.BigInteger;
import java.util.Random;

class Util {
    private static ThreadLocal<Random> random = new ThreadLocal<Random>() {
        /* access modifiers changed from: protected */
        public Random initialValue() {
            return new Random(System.currentTimeMillis() * (System.nanoTime() % 1000000) * Thread.currentThread().getId() * ((long) (Math.random() * 1024.0d)));
        }
    };

    Util() {
    }

    static long generateRandomGUID() {
        return ((Random) random.get()).nextLong();
    }

    static long protoTimeToEpochMicros(Timestamp timestamp) {
        return (timestamp.getSeconds() * 1000000) + ((long) (timestamp.getNanos() / 1000));
    }

    static Timestamp epochTimeMicrosToProtoTime(long j) {
        Builder newBuilder = Timestamp.newBuilder();
        newBuilder.setSeconds(j / 1000000);
        newBuilder.setNanos(((int) (j % 1000000)) * 1000);
        return newBuilder.build();
    }

    static long nowMicrosApproximate() {
        return System.currentTimeMillis() * 1000;
    }

    static Long fromHexString(String str) {
        Long l = null;
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            l = Long.valueOf(new BigInteger(str, 16).longValue());
        } catch (NumberFormatException unused) {
        }
        return l;
    }

    static String toHexString(long j) {
        return Long.toHexString(j);
    }
}
