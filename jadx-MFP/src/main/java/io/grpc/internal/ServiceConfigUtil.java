package io.grpc.internal;

import android.support.v4.app.NotificationCompat;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.protobuf.util.TimeUtil;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@VisibleForTesting
public final class ServiceConfigUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);

    private static long saturatedAdd(long j, long j2) {
        long j3 = j + j2;
        boolean z = true;
        boolean z2 = (j2 ^ j) < 0;
        if ((j ^ j3) < 0) {
            z = false;
        }
        return z2 | z ? j3 : ((j3 >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    private ServiceConfigUtil() {
    }

    @Nullable
    static Throttle getThrottlePolicy(@Nullable Map<String, Object> map) {
        String str = "retryThrottling";
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        Map object = getObject(map, str);
        float floatValue = getDouble(object, "maxTokens").floatValue();
        float floatValue2 = getDouble(object, "tokenRatio").floatValue();
        boolean z = true;
        Preconditions.checkState(floatValue > BitmapDescriptorFactory.HUE_RED, "maxToken should be greater than zero");
        if (floatValue2 <= BitmapDescriptorFactory.HUE_RED) {
            z = false;
        }
        Preconditions.checkState(z, "tokenRatio should be greater than zero");
        return new Throttle(floatValue, floatValue2);
    }

    @Nullable
    static Integer getMaxAttemptsFromRetryPolicy(Map<String, Object> map) {
        if (!map.containsKey("maxAttempts")) {
            return null;
        }
        return Integer.valueOf(getDouble(map, "maxAttempts").intValue());
    }

    @Nullable
    static Long getInitialBackoffNanosFromRetryPolicy(Map<String, Object> map) {
        if (!map.containsKey("initialBackoff")) {
            return null;
        }
        try {
            return Long.valueOf(parseDuration(getString(map, "initialBackoff")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    static Long getMaxBackoffNanosFromRetryPolicy(Map<String, Object> map) {
        if (!map.containsKey("maxBackoff")) {
            return null;
        }
        try {
            return Long.valueOf(parseDuration(getString(map, "maxBackoff")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    static Double getBackoffMultiplierFromRetryPolicy(Map<String, Object> map) {
        if (!map.containsKey("backoffMultiplier")) {
            return null;
        }
        return getDouble(map, "backoffMultiplier");
    }

    @Nullable
    static List<String> getRetryableStatusCodesFromRetryPolicy(Map<String, Object> map) {
        if (!map.containsKey("retryableStatusCodes")) {
            return null;
        }
        return checkStringList(getList(map, "retryableStatusCodes"));
    }

    @Nullable
    static Integer getMaxAttemptsFromHedgingPolicy(Map<String, Object> map) {
        if (!map.containsKey("maxAttempts")) {
            return null;
        }
        return Integer.valueOf(getDouble(map, "maxAttempts").intValue());
    }

    @Nullable
    static Long getHedgingDelayNanosFromHedgingPolicy(Map<String, Object> map) {
        if (!map.containsKey("hedgingDelay")) {
            return null;
        }
        try {
            return Long.valueOf(parseDuration(getString(map, "hedgingDelay")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    static List<String> getNonFatalStatusCodesFromHedgingPolicy(Map<String, Object> map) {
        if (!map.containsKey("nonFatalStatusCodes")) {
            return null;
        }
        return checkStringList(getList(map, "nonFatalStatusCodes"));
    }

    @Nullable
    static String getServiceFromName(Map<String, Object> map) {
        if (!map.containsKey(NotificationCompat.CATEGORY_SERVICE)) {
            return null;
        }
        return getString(map, NotificationCompat.CATEGORY_SERVICE);
    }

    @Nullable
    static String getMethodFromName(Map<String, Object> map) {
        if (!map.containsKey(Param.METHOD)) {
            return null;
        }
        return getString(map, Param.METHOD);
    }

    @Nullable
    static Map<String, Object> getRetryPolicyFromMethodConfig(Map<String, Object> map) {
        if (!map.containsKey("retryPolicy")) {
            return null;
        }
        return getObject(map, "retryPolicy");
    }

    @Nullable
    static Map<String, Object> getHedgingPolicyFromMethodConfig(Map<String, Object> map) {
        if (!map.containsKey("hedgingPolicy")) {
            return null;
        }
        return getObject(map, "hedgingPolicy");
    }

    @Nullable
    static List<Map<String, Object>> getNameListFromMethodConfig(Map<String, Object> map) {
        if (!map.containsKey("name")) {
            return null;
        }
        return checkObjectList(getList(map, "name"));
    }

    @Nullable
    static Long getTimeoutFromMethodConfig(Map<String, Object> map) {
        if (!map.containsKey("timeout")) {
            return null;
        }
        try {
            return Long.valueOf(parseDuration(getString(map, "timeout")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    static Boolean getWaitForReadyFromMethodConfig(Map<String, Object> map) {
        if (!map.containsKey("waitForReady")) {
            return null;
        }
        return getBoolean(map, "waitForReady");
    }

    @Nullable
    static Integer getMaxRequestMessageBytesFromMethodConfig(Map<String, Object> map) {
        if (!map.containsKey("maxRequestMessageBytes")) {
            return null;
        }
        return Integer.valueOf(getDouble(map, "maxRequestMessageBytes").intValue());
    }

    @Nullable
    static Integer getMaxResponseMessageBytesFromMethodConfig(Map<String, Object> map) {
        if (!map.containsKey("maxResponseMessageBytes")) {
            return null;
        }
        return Integer.valueOf(getDouble(map, "maxResponseMessageBytes").intValue());
    }

    @Nullable
    static List<Map<String, Object>> getMethodConfigFromServiceConfig(Map<String, Object> map) {
        if (!map.containsKey("methodConfig")) {
            return null;
        }
        return checkObjectList(getList(map, "methodConfig"));
    }

    @Nullable
    @VisibleForTesting
    public static String getLoadBalancingPolicyFromServiceConfig(Map<String, Object> map) {
        if (!map.containsKey("loadBalancingPolicy")) {
            return null;
        }
        return getString(map, "loadBalancingPolicy");
    }

    @Nullable
    public static String getStickinessMetadataKeyFromServiceConfig(Map<String, Object> map) {
        if (!map.containsKey("stickinessMetadataKey")) {
            return null;
        }
        return getString(map, "stickinessMetadataKey");
    }

    static List<Object> getList(Map<String, Object> map, String str) {
        Object checkNotNull = Preconditions.checkNotNull(map.get(str), "no such key %s", (Object) str);
        if (checkNotNull instanceof List) {
            return (List) checkNotNull;
        }
        throw new ClassCastException(String.format("value %s for key %s in %s is not List", new Object[]{checkNotNull, str, map}));
    }

    static Map<String, Object> getObject(Map<String, Object> map, String str) {
        Object checkNotNull = Preconditions.checkNotNull(map.get(str), "no such key %s", (Object) str);
        if (checkNotNull instanceof Map) {
            return (Map) checkNotNull;
        }
        throw new ClassCastException(String.format("value %s for key %s in %s is not object", new Object[]{checkNotNull, str, map}));
    }

    static Double getDouble(Map<String, Object> map, String str) {
        Object checkNotNull = Preconditions.checkNotNull(map.get(str), "no such key %s", (Object) str);
        if (checkNotNull instanceof Double) {
            return (Double) checkNotNull;
        }
        throw new ClassCastException(String.format("value %s for key %s in %s is not Double", new Object[]{checkNotNull, str, map}));
    }

    static String getString(Map<String, Object> map, String str) {
        Object checkNotNull = Preconditions.checkNotNull(map.get(str), "no such key %s", (Object) str);
        if (checkNotNull instanceof String) {
            return (String) checkNotNull;
        }
        throw new ClassCastException(String.format("value %s for key %s in %s is not String", new Object[]{checkNotNull, str, map}));
    }

    static Boolean getBoolean(Map<String, Object> map, String str) {
        Object checkNotNull = Preconditions.checkNotNull(map.get(str), "no such key %s", (Object) str);
        if (checkNotNull instanceof Boolean) {
            return (Boolean) checkNotNull;
        }
        throw new ClassCastException(String.format("value %s for key %s in %s is not Boolean", new Object[]{checkNotNull, str, map}));
    }

    private static List<Map<String, Object>> checkObjectList(List<Object> list) {
        int i = 0;
        while (i < list.size()) {
            if (list.get(i) instanceof Map) {
                i++;
            } else {
                throw new ClassCastException(String.format("value %s for idx %d in %s is not object", new Object[]{list.get(i), Integer.valueOf(i), list}));
            }
        }
        return list;
    }

    static List<String> checkStringList(List<Object> list) {
        int i = 0;
        while (i < list.size()) {
            if (list.get(i) instanceof String) {
                i++;
            } else {
                throw new ClassCastException(String.format("value %s for idx %d in %s is not string", new Object[]{list.get(i), Integer.valueOf(i), list}));
            }
        }
        return list;
    }

    private static long parseDuration(String str) throws ParseException {
        boolean z;
        if (str.isEmpty() || str.charAt(str.length() - 1) != 's') {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid duration string: ");
            sb.append(str);
            throw new ParseException(sb.toString(), 0);
        }
        if (str.charAt(0) == '-') {
            str = str.substring(1);
            z = true;
        } else {
            z = false;
        }
        String substring = str.substring(0, str.length() - 1);
        String str2 = "";
        int indexOf = substring.indexOf(46);
        if (indexOf != -1) {
            str2 = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
        }
        long parseLong = Long.parseLong(substring);
        int parseNanos = str2.isEmpty() ? 0 : parseNanos(str2);
        if (parseLong >= 0) {
            if (z) {
                parseLong = -parseLong;
                parseNanos = -parseNanos;
            }
            try {
                return normalizedDuration(parseLong, parseNanos);
            } catch (IllegalArgumentException unused) {
                throw new ParseException("Duration value is out of range.", 0);
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Invalid duration string: ");
            sb2.append(str);
            throw new ParseException(sb2.toString(), 0);
        }
    }

    private static int parseNanos(String str) throws ParseException {
        int i = 0;
        for (int i2 = 0; i2 < 9; i2++) {
            i *= 10;
            if (i2 < str.length()) {
                if (str.charAt(i2) < '0' || str.charAt(i2) > '9') {
                    throw new ParseException("Invalid nanoseconds.", 0);
                }
                i += str.charAt(i2) - '0';
            }
        }
        return i;
    }

    private static long normalizedDuration(long j, int i) {
        long j2 = (long) i;
        long j3 = NANOS_PER_SECOND;
        if (j2 <= (-j3) || j2 >= j3) {
            j = LongMath.checkedAdd(j, j2 / NANOS_PER_SECOND);
            i = (int) (j2 % NANOS_PER_SECOND);
        }
        if (j > 0 && i < 0) {
            i = (int) (((long) i) + NANOS_PER_SECOND);
            j--;
        }
        if (j < 0 && i > 0) {
            i = (int) (((long) i) - NANOS_PER_SECOND);
            j++;
        }
        if (durationIsValid(j, i)) {
            return saturatedAdd(TimeUnit.SECONDS.toNanos(j), (long) i);
        }
        throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", new Object[]{Long.valueOf(j), Integer.valueOf(i)}));
    }

    private static boolean durationIsValid(long j, int i) {
        if (j < TimeUtil.DURATION_SECONDS_MIN || j > TimeUtil.DURATION_SECONDS_MAX) {
            return false;
        }
        long j2 = (long) i;
        if (j2 < -999999999 || j2 >= NANOS_PER_SECOND) {
            return false;
        }
        int i2 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        return (i2 >= 0 && i >= 0) || (i2 <= 0 && i <= 0);
    }
}
