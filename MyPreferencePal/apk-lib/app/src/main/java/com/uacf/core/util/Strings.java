package com.uacf.core.util;

import android.os.Bundle;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import lanchon.dexpatcher.annotation.DexAction;
import lanchon.dexpatcher.annotation.DexEdit;
import lanchon.dexpatcher.annotation.DexIgnore;

@DexIgnore
public class Strings {
    public static final boolean isEmpty(String str) {
        return false;
    }

    public static final boolean isEmpty(Object obj) {
        return false;
    }

    public static final boolean isEmptyOrWhitespace(String str) {
        return false;
    }

    public static final boolean notEmpty(String str) {
        return false;
    }

    public static boolean notEmpty(Object obj) {
        return false;
    }

    public static final boolean equals(Object obj, Object obj2) {
        return false;
    }

    public static final boolean equals(String str, String str2) {
        return false;
    }

    public static final boolean startsWith(String str, String str2) {
        return false;
    }

    public static boolean equalsIgnoreCase(Object obj, Object obj2) {
        return false;
    }

    public static String toString(Object obj) {
        return null;
    }

    public static String toString(Object obj, String str) {
        return null;
    }

    public static String toStringOrDefaultIfEmpty(Object obj, String str) {
        return null;
    }

    public static String toString(Object obj, String str, String str2) {
        return null;
    }

    public static String trimmed(Strings strings) {
        return null;
    }

    public static String trimmed(Object obj) {
        return null;
    }

    public static boolean toBoolean(String str) {
        return false;
    }

    public static <T> String join(String str, Collection<T> collection) {
        return null;
    }

    public static <T> String join(String str, T... tArr) {
        return null;
    }

    public static <T, U> String join(Map<T, U> map, String str, String str2) {
        return null;
    }

    public static List<String> split(String str, String str2) {
        return null;
    }

    public static <T> String joinFormatted(String str, String str2, Collection<T> collection) {
        return null;
    }

    private static String joinArray(String str, Object obj) {
        return null;
    }

    public static String capitalize(String str) {
        return null;
    }

    public static String toTitleCase(String str) {
        return null;
    }

    public static int instancesOfCharIn(String str, char c) {
        return 0;
    }

    public static int length(Object obj) {
        return 0;
    }

    public static int length(String str) {
        return 0;
    }

    public static String toString(Bundle bundle, String str, String str2) {
        return null;
    }

    public static String repeat(char c, int i) {
        return null;
    }

    public static String repeat(String str, int i) {
        return null;
    }

    public static String tabify(Object obj, Object obj2, int i) {
        return null;
    }

    public static String[] toStringArray(Object[] objArr) {return null;
    }

    public static String encryptString(String str) {return null;
    }

    public static String toHexString(ByteBuffer byteBuffer) {return null;
    }

    public static String initStringWithFormattedFloat(float f, int i) {return null;
    }

    public static String arrayOfStringsToCSV(String[] strArr) {return null;
    }

    public static String[] csvToArrayOfStringsWithEscaping(String str) {return null;
    }

    public static String onlyCertainChars(String str, String str2) {return null;
    }

    public static String onlyAlphaNum(String str) {
        return null;
    }

    public static String signNumber(String str, float f) {
        return null;
    }

    public static String reverseSignNumber(String str, float f) {
        return null;
    }

    public static String extractUrl(String str) {
        return null;
    }
}
