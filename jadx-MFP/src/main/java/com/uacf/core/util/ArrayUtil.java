package com.uacf.core.util;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayUtil<T> {
    public static String[] merge(String[]... strArr) {
        int i = 0;
        int i2 = 0;
        for (String[] length : strArr) {
            i++;
            i2 += length.length;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Arrays passed for merging : ");
        sb.append(i);
        Ln.d(sb.toString(), new Object[0]);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Array size of resultig array : ");
        sb2.append(i2);
        Ln.d(sb2.toString(), new Object[0]);
        String[] strArr2 = (String[]) Array.newInstance(strArr[0][0].getClass(), i2);
        int i3 = 0;
        for (String[] strArr3 : strArr) {
            System.arraycopy(strArr3, 0, strArr2, i3, strArr3.length);
            i3 += strArr3.length;
        }
        return strArr2;
    }

    public static <T> T[] appendToArray(T[] tArr, T t) {
        int length = tArr.length;
        T[] copyOf = Arrays.copyOf(tArr, length + 1);
        copyOf[length] = t;
        return copyOf;
    }

    public static int size(Object[] objArr) {
        if (objArr != null) {
            return objArr.length;
        }
        return 0;
    }

    public static boolean isEmpty(Object[] objArr) {
        return size(objArr) == 0;
    }

    public static boolean notEmpty(Object[] objArr) {
        return size(objArr) > 0;
    }
}
