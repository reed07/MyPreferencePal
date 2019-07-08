package com.uacf.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class Enumerable {
    public static <T, U> List<U> select(Collection<T> collection, ReturningFunction1<U, T> returningFunction1) {
        return select(collection, true, returningFunction1);
    }

    public static <T, U> List<U> select(Collection<T> collection, ReturningFunction2<U, T, Integer> returningFunction2) {
        return select(collection, true, returningFunction2);
    }

    public static <T, U> List<U> select(Collection<T> collection, boolean z, ReturningFunction1<U, T> returningFunction1) {
        return select(collection, z, 0, returningFunction1);
    }

    public static <T, U> List<U> select(Collection<T> collection, boolean z, ReturningFunction2<U, T, Integer> returningFunction2) {
        return select(collection, z, 0, returningFunction2);
    }

    public static <T, U> List<U> select(T[] tArr, ReturningFunction1<U, T> returningFunction1) {
        return select((Collection<T>) tArr != null ? Arrays.asList(tArr) : null, returningFunction1);
    }

    public static <T, U> List<U> select(Collection<T> collection, boolean z, int i, final ReturningFunction1<U, T> returningFunction1) {
        return select(collection, z, i, (ReturningFunction2<U, T, Integer>) new ReturningFunction2<U, T, Integer>() {
            public U execute(T t, Integer num) {
                return returningFunction1.execute(t);
            }
        });
    }

    public static <T, U> List<U> select(Collection<T> collection, boolean z, int i, ReturningFunction2<U, T, Integer> returningFunction2) {
        ArrayList arrayList = new ArrayList();
        if (CollectionUtils.notEmpty(collection)) {
            int i2 = 0;
            for (T execute : collection) {
                int i3 = i2 + 1;
                Object execute2 = returningFunction2.execute(execute, Integer.valueOf(i2));
                if (z || execute2 != null) {
                    arrayList.add(execute2);
                }
                if (i > 0 && arrayList.size() == i) {
                    break;
                }
                i2 = i3;
            }
        }
        return arrayList;
    }

    public static <T, U> List<U> selectMany(Collection<T> collection, ReturningFunction1<Collection<U>, T> returningFunction1) {
        ArrayList arrayList = new ArrayList();
        if (CollectionUtils.notEmpty(collection)) {
            for (T execute : collection) {
                Collection collection2 = (Collection) returningFunction1.execute(execute);
                if (collection2 != null) {
                    arrayList.addAll(collection2);
                }
            }
        }
        return arrayList;
    }

    public static <T> T firstOrDefault(Collection<T> collection) {
        return firstOrDefault(collection, new ReturningFunction1<Boolean, T>() {
            public Boolean execute(T t) {
                return Boolean.valueOf(t != null);
            }
        });
    }

    public static <T> T firstOrDefault(Collection<T> collection, ReturningFunction1<Boolean, T> returningFunction1) {
        if (CollectionUtils.notEmpty(collection)) {
            for (T next : collection) {
                if (((Boolean) returningFunction1.execute(next)).booleanValue()) {
                    return next;
                }
            }
        }
        return null;
    }

    public static <T> Collection<T> where(Collection<T> collection, ReturningFunction1<Boolean, T> returningFunction1) {
        ArrayList arrayList = new ArrayList();
        if (CollectionUtils.notEmpty(collection)) {
            for (Object next : collection) {
                if (((Boolean) returningFunction1.execute(next)).booleanValue()) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public static <T> Collection<T> where(T[] tArr, ReturningFunction1<Boolean, T> returningFunction1) {
        ArrayList arrayList = new ArrayList();
        if (ArrayUtil.notEmpty(tArr)) {
            for (T t : tArr) {
                if (((Boolean) returningFunction1.execute(t)).booleanValue()) {
                    arrayList.add(t);
                }
            }
        }
        return arrayList;
    }

    public static <T> List<T> where(Collection<T> collection, ReturningFunction2<Boolean, T, Integer> returningFunction2) {
        ArrayList arrayList = new ArrayList();
        if (CollectionUtils.notEmpty(collection)) {
            int i = 0;
            for (Object next : collection) {
                int i2 = i + 1;
                if (((Boolean) returningFunction2.execute(next, Integer.valueOf(i))).booleanValue()) {
                    arrayList.add(next);
                }
                i = i2;
            }
        }
        return arrayList;
    }

    public static <T> int indexOf(T[] tArr, ReturningFunction1<Boolean, T> returningFunction1) {
        return indexOf((Collection<T>) Arrays.asList(tArr), returningFunction1);
    }

    public static <T> int indexOf(Collection<T> collection, ReturningFunction1<Boolean, T> returningFunction1) {
        if (CollectionUtils.notEmpty(collection)) {
            int i = 0;
            for (T execute : collection) {
                if (((Boolean) returningFunction1.execute(execute)).booleanValue()) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public static <T, U> int indexOf(Collection<T> collection, U u, ReturningFunction1<U, T> returningFunction1) {
        if (CollectionUtils.notEmpty(collection)) {
            int i = 0;
            for (T execute : collection) {
                if (Strings.equals(returningFunction1.execute(execute), (Object) u)) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public static <T> boolean any(Collection<T> collection, ReturningFunction1<Boolean, T> returningFunction1) {
        if (CollectionUtils.notEmpty(collection)) {
            for (T execute : collection) {
                if (((Boolean) returningFunction1.execute(execute)).booleanValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <T> void forEach(Collection<T> collection, Function1<T> function1) {
        if (CollectionUtils.notEmpty(collection)) {
            for (T execute : collection) {
                function1.execute(execute);
            }
        }
    }

    public static <T> void forEach(Collection<T> collection, Function2<T, Integer> function2) {
        if (CollectionUtils.notEmpty(collection)) {
            int i = 0;
            for (T execute : collection) {
                function2.execute(execute, Integer.valueOf(i));
                i++;
            }
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List<T>, code=java.util.Collection, for r2v0, types: [java.util.Collection, java.util.List<T>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> java.util.Collection<T> union(final java.util.List<T> r1, java.util.Collection r2) {
        /*
            if (r1 == 0) goto L_0x000b
            com.uacf.core.util.Enumerable$3 r0 = new com.uacf.core.util.Enumerable$3
            r0.<init>(r1)
            java.util.Collection r2 = where(r2, r0)
        L_0x000b:
            java.util.Collection r1 = concat(r1, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.util.Enumerable.union(java.util.List, java.util.List):java.util.Collection");
    }

    public static <T> Collection<T> concat(Collection<T> collection, Collection<T> collection2) {
        if (collection == null) {
            if (collection2 == null) {
                collection2 = new ArrayList<>();
            }
            return collection2;
        } else if (collection2 == null) {
            return collection;
        } else {
            collection.addAll(collection2);
            return collection;
        }
    }
}
