package com.google.ads.interactivemedia.v3.internal;

import com.brightcove.player.event.AbstractEvent;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.RandomAccess;

/* compiled from: IMASDK */
public class agj {
    Object[] a;
    int b;
    boolean c;

    /* access modifiers changed from: 0000 */
    public void b() {
    }

    static Object a(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder(20);
        sb.append("at index ");
        sb.append(i);
        throw new NullPointerException(sb.toString());
    }

    static boolean a(Map<?, ?> map, Object obj) {
        if (map == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        return map.entrySet().equals(((Map) obj).entrySet());
    }

    static String a(Map<?, ?> map) {
        StringBuilder a2 = a(map.size());
        a2.append('{');
        boolean z = true;
        for (Entry entry : map.entrySet()) {
            if (!z) {
                a2.append(", ");
            }
            z = false;
            a2.append(entry.getKey());
            a2.append('=');
            a2.append(entry.getValue());
        }
        a2.append('}');
        return a2.toString();
    }

    static boolean a(List<?> list, Object obj) {
        if (obj == afx.a(list)) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list2 = (List) obj;
        int size = list.size();
        if (size != list2.size()) {
            return false;
        }
        if (!(list instanceof RandomAccess) || !(list2 instanceof RandomAccess)) {
            return a(list.iterator(), list2.iterator());
        }
        for (int i = 0; i < size; i++) {
            if (!afx.a(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    static int b(List<?> list, Object obj) {
        if (list instanceof RandomAccess) {
            return d(list, obj);
        }
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (afx.a(obj, listIterator.next())) {
                return listIterator.previousIndex();
            }
        }
        return -1;
    }

    private static int d(List<?> list, Object obj) {
        int size = list.size();
        int i = 0;
        if (obj == null) {
            while (i < size) {
                if (list.get(i) == null) {
                    return i;
                }
                i++;
            }
        } else {
            while (i < size) {
                if (obj.equals(list.get(i))) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    static int c(List<?> list, Object obj) {
        if (list instanceof RandomAccess) {
            return e(list, obj);
        }
        ListIterator listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            if (afx.a(obj, listIterator.previous())) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    private static int e(List<?> list, Object obj) {
        if (obj == null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                if (list.get(size) == null) {
                    return size;
                }
            }
        } else {
            for (int size2 = list.size() - 1; size2 >= 0; size2--) {
                if (obj.equals(list.get(size2))) {
                    return size2;
                }
            }
        }
        return -1;
    }

    public static boolean a(Iterator<?> it, Iterator<?> it2) {
        while (it.hasNext()) {
            if (!it2.hasNext()) {
                return false;
            }
            if (!afx.a(it.next(), it2.next())) {
                return false;
            }
        }
        if (!it2.hasNext()) {
            return true;
        }
        return false;
    }

    public static <T> agt<T> a(T t) {
        return new agi(t);
    }

    static void a(Object obj, Object obj2) {
        if (obj == null) {
            String valueOf = String.valueOf(obj2);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24);
            sb.append("null key in entry: null=");
            sb.append(valueOf);
            throw new NullPointerException(sb.toString());
        } else if (obj2 == null) {
            String valueOf2 = String.valueOf(obj);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 26);
            sb2.append("null value in entry: ");
            sb2.append(valueOf2);
            sb2.append("=null");
            throw new NullPointerException(sb2.toString());
        }
    }

    static int a(int i, String str) {
        if (i >= 0) {
            return i;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40);
        sb.append(str);
        sb.append(" cannot be negative but was: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    static StringBuilder a(int i) {
        a(i, AbstractEvent.SIZE);
        return new StringBuilder((int) Math.min(((long) i) << 3, 1073741824));
    }

    static int b(int i) {
        return (int) (((long) Integer.rotateLeft((int) (((long) i) * -862048943), 15)) * 461845907);
    }

    static int b(Object obj) {
        return b(obj == null ? 0 : obj.hashCode());
    }

    static int a(int i, int i2) {
        if (i2 >= 0) {
            int i3 = i + (i >> 1) + 1;
            if (i3 < i2) {
                i3 = Integer.highestOneBit(i2 - 1) << 1;
            }
            if (i3 < 0) {
                return Integer.MAX_VALUE;
            }
            return i3;
        }
        throw new AssertionError("cannot store more than MAX_VALUE elements");
    }

    public agj() {
        this(4);
    }

    agj(int i) {
        this.a = new Object[(i * 2)];
        this.b = 0;
        this.c = false;
    }

    private void c(int i) {
        int i2 = i << 1;
        Object[] objArr = this.a;
        if (i2 > objArr.length) {
            this.a = Arrays.copyOf(objArr, a(objArr.length, i2));
            this.c = false;
        }
    }

    public agj b(K k, V v) {
        c(this.b + 1);
        a((Object) k, (Object) v);
        Object[] objArr = this.a;
        int i = this.b;
        objArr[i * 2] = k;
        objArr[(i * 2) + 1] = v;
        this.b = i + 1;
        return this;
    }

    public agj a(Entry<? extends K, ? extends V> entry) {
        return b((K) entry.getKey(), (V) entry.getValue());
    }

    public agj a(Iterable<? extends Entry<? extends K, ? extends V>> iterable) {
        if (iterable instanceof Collection) {
            c(this.b + ((Collection) iterable).size());
        }
        for (Entry a2 : iterable) {
            a(a2);
        }
        return this;
    }

    public age<K, V> a() {
        b();
        this.c = true;
        return agm.a(this.b, this.a);
    }
}
