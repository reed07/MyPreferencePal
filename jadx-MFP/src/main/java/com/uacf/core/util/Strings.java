package com.uacf.core.util;

import android.os.Bundle;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Strings {
    private static final String DEFAULT_JOIN_FORMAT = "%s";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_PATTERN_STRING, 2);
    private static final String URL_PATTERN_STRING = "((https?):(//)+[\\w\\d:#@%/;$()~_?\\+-=.&]*)";

    public static final boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static final boolean isEmpty(Object obj) {
        return isEmpty(toString(obj));
    }

    public static final boolean isEmptyOrWhitespace(String str) {
        return isEmpty(str) || isEmpty(str.trim());
    }

    public static final boolean notEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean notEmpty(Object obj) {
        return toString(obj).trim().length() != 0;
    }

    public static final boolean equals(Object obj, Object obj2) {
        return equals(toString(obj), toString(obj2));
    }

    public static final boolean equals(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 != null) {
            return str2.equals(str);
        }
        return false;
    }

    public static final boolean startsWith(String str, String str2) {
        return str != null && str.startsWith(str2);
    }

    public static boolean equalsIgnoreCase(Object obj, Object obj2) {
        return toString(obj).toLowerCase().equals(toString(obj2).toLowerCase());
    }

    public static String toString(Object obj) {
        return toString(obj, "");
    }

    public static String toString(Object obj, String str) {
        return toString(obj, ", ", str);
    }

    public static String toStringOrDefaultIfEmpty(Object obj, String str) {
        String strings = toString(obj, str);
        return notEmpty(strings) ? strings : str;
    }

    public static String toString(Object obj, String str, String str2) {
        if (obj == null) {
            return str2;
        }
        if (obj instanceof InputStream) {
            return toString((InputStream) obj);
        }
        if (obj instanceof Reader) {
            return toString((Reader) obj);
        }
        if (obj instanceof Object[]) {
            return join(str, (T[]) (Object[]) obj);
        }
        if (obj instanceof Bundle) {
            return toString((Bundle) obj);
        }
        return obj instanceof Collection ? join(str, (Collection) obj) : joinArray(str, obj);
    }

    public static String trimmed(Strings strings) {
        return toString(strings).trim();
    }

    public static String trimmed(Object obj) {
        return toString(obj).trim();
    }

    public static boolean toBoolean(String str) {
        return "true".equalsIgnoreCase(str);
    }

    public static <T> String join(String str, Collection<T> collection) {
        return joinFormatted(str, DEFAULT_JOIN_FORMAT, collection);
    }

    public static <T> String join(String str, T... tArr) {
        return join(str, (Collection<T>) Arrays.asList(tArr));
    }

    public static <T, U> String join(Map<T, U> map, String str, String str2) {
        if (map == null || map.isEmpty() || isEmpty(str) || isEmpty(str2)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Object next : map.keySet()) {
            if (!z) {
                sb.append(str2);
            }
            sb.append(String.format("%s%s%s", new Object[]{toString(next), str, toString(map.get(next))}));
            z = false;
        }
        return sb.toString();
    }

    public static List<String> split(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (notEmpty(str)) {
            if (isEmpty(str2)) {
                arrayList.add(str);
            } else {
                arrayList.addAll(Arrays.asList(str.split(str2)));
            }
        }
        return arrayList;
    }

    public static <T> String joinFormatted(String str, String str2, Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        if (isEmpty(str2)) {
            str2 = DEFAULT_JOIN_FORMAT;
        }
        Iterator it = collection.iterator();
        StringBuilder sb = new StringBuilder(String.format(str2, new Object[]{it.next()}));
        while (it.hasNext()) {
            Object next = it.next();
            if (notEmpty(next)) {
                sb.append(str);
                sb.append(String.format(str2, new Object[]{toString(next)}));
            }
        }
        return sb.toString();
    }

    private static String joinArray(String str, Object obj) {
        if (!(obj instanceof byte[]) && !(obj instanceof short[]) && !(obj instanceof int[]) && !(obj instanceof long[]) && !(obj instanceof float[])) {
            boolean z = obj instanceof double[];
            if (!z && !(obj instanceof boolean[]) && !z && !(obj instanceof char[])) {
                return obj.toString();
            }
        }
        int length = Array.getLength(obj);
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            objArr[i] = Array.get(obj, i);
        }
        return join(str, (T[]) objArr);
    }

    public static String capitalize(String str) {
        String strings = toString(str);
        if (strings.length() < 2) {
            return strings.length() >= 1 ? strings.toUpperCase() : strings;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(strings.substring(0, 1).toUpperCase());
        sb.append(strings.substring(1));
        return sb.toString();
    }

    public static String toTitleCase(String str) {
        if (isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        String[] split = str.split(" ");
        int length = split.length;
        for (int i = 0; i < length; i++) {
            sb.append(capitalize(split[i]));
            if (i < length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static int instancesOfCharIn(String str, char c) {
        if (str != null && str.length() == 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == c) {
                i++;
            }
        }
        return i;
    }

    public static int length(Object obj) {
        return length(toString(obj));
    }

    public static int length(String str) {
        if (str == null) {
            return 0;
        }
        return str.length();
    }

    public static String toString(Bundle bundle, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (bundle != null) {
            for (String str3 : bundle.keySet()) {
                sb.append(str3);
                sb.append(str2);
                sb.append(toString(bundle.get(str3)));
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String repeat(char c, int i) {
        return String.format(String.format("%%0%dd", new Object[]{Integer.valueOf(i)}), new Object[]{Integer.valueOf(0)}).replace('0', c);
    }

    public static String repeat(String str, int i) {
        return String.format(String.format("%%0%dd", new Object[]{Integer.valueOf(i)}), new Object[]{Integer.valueOf(0)}).replace("0", str);
    }

    public static String tabify(Object obj, Object obj2, int i) {
        return String.format(String.format("%%%ds = %%s", new Object[]{Integer.valueOf(i)}), new Object[]{toString(obj), toString(obj2)});
    }

    public static String[] toStringArray(Object[] objArr) {
        String[] strArr = new String[objArr.length];
        int length = objArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            strArr[i2] = toString(objArr[i]);
            i++;
            i2 = i3;
        }
        return strArr;
    }

    public static String encryptString(String str) {
        if (isEmpty(str)) {
            return str;
        }
        String str2 = "";
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(str.getBytes());
            StringTokenizer stringTokenizer = new StringTokenizer(toHexString(ByteBuffer.wrap(instance.digest())), " ", false);
            while (stringTokenizer.hasMoreElements()) {
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(stringTokenizer.nextElement());
                str2 = sb.toString();
            }
            return str2;
        } catch (NoSuchAlgorithmException e) {
            Ln.e(e);
            return null;
        }
    }

    public static String toHexString(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (byteBuffer.hasRemaining()) {
            String substring = Integer.toHexString((byteBuffer.get() & 255) + 256).substring(1);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(substring.length() < 2 ? "0" : "");
            sb2.append(substring);
            sb2.append(" ");
            sb.append(sb2.toString());
        }
        return sb.toString();
    }

    public static String initStringWithFormattedFloat(float f, int i) {
        return NumberUtils.localeStringFromFloatWithExactFractionDigits(f, i);
    }

    public static String arrayOfStringsToCSV(String[] strArr) {
        return joinFormatted(",", "\"%s\"", strArr != null ? Arrays.asList(strArr) : null);
    }

    public static String[] csvToArrayOfStringsWithEscaping(String str) {
        if (isEmpty(str)) {
            return new String[0];
        }
        String[] split = str.substring(0, str.length() - 1).split("\",");
        int length = split.length;
        for (int i = 0; i < length; i++) {
            split[i] = split[i].substring(1).replace("\\,", ",").replace("\"\"", "\"");
        }
        return split;
    }

    public static String onlyCertainChars(String str, String str2) {
        if (isEmpty(str) || isEmpty(str2)) {
            return str;
        }
        return str.replaceAll(String.format("[^%s]", new Object[]{str2}), "");
    }

    public static String onlyAlphaNum(String str) {
        return onlyCertainChars(str, "a-zA-Z0-9");
    }

    public static String signNumber(String str, float f) {
        if (isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(f < BitmapDescriptorFactory.HUE_RED ? "- " : "");
        sb.append(str);
        return sb.toString();
    }

    public static String reverseSignNumber(String str, float f) {
        if (isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(f < BitmapDescriptorFactory.HUE_RED ? "+ " : "- ");
        sb.append(str);
        return sb.toString();
    }

    public static String extractUrl(String str) {
        if (notEmpty(str)) {
            Matcher matcher = URL_PATTERN.matcher(str);
            if (matcher.find()) {
                return str.substring(matcher.start(0), matcher.end(0));
            }
        }
        return "";
    }
}
