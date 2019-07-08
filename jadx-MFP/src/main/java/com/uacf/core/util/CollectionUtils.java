package com.uacf.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CollectionUtils {
    public static int size(Collection<?> collection) {
        if (collection != null) {
            return collection.size();
        }
        return 0;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return size(collection) == 0;
    }

    public static boolean notEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static int size(Map<?, ?> map) {
        if (map != null) {
            return map.size();
        }
        return 0;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return size(map) == 0;
    }

    public static boolean notEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static Map<String, String> nameValuePairsToMap(Object... objArr) {
        if (objArr.length % 2 == 0) {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < objArr.length - 1; i += 2) {
                hashMap.put(Strings.toString(objArr[i]), Strings.toString(objArr[i + 1]));
            }
            return hashMap;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid URL name/value pairs: ");
        sb.append(Strings.toString(objArr));
        throw new RuntimeException(sb.toString());
    }

    public static <T> List<T> toList(Collection<T> collection) {
        return Enumerable.select(collection, (ReturningFunction1<U, T>) new ReturningFunction1<T, T>() {
            public T execute(T t) throws RuntimeException {
                return t;
            }
        });
    }

    public static <T> List<T> toList(Iterator<T> it) {
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public static <T> T getLastItemFromList(List<T> list) {
        if (isEmpty((Collection<?>) list)) {
            return null;
        }
        return list.get(size((Collection<?>) list) - 1);
    }

    public static boolean areContentsEqual(Collection<?> collection, Collection<?> collection2) {
        boolean z = true;
        if (collection == null && collection2 == null) {
            return true;
        }
        if (collection == null || collection2 == null) {
            return false;
        }
        if (size(collection) != size(collection2) || !collection.containsAll(collection2)) {
            z = false;
        }
        return z;
    }

    public static <T> Collection<T> getDifferences(Collection<T> collection, Collection<T> collection2) {
        HashSet hashSet = new HashSet();
        int size = size(collection);
        int size2 = size(collection2);
        if (size > 0 || size2 > 0) {
            if (size > size2) {
                hashSet.addAll(collection);
                hashSet.removeAll(collection2);
            } else {
                hashSet.addAll(collection2);
                hashSet.removeAll(collection);
            }
        }
        return hashSet;
    }
}
