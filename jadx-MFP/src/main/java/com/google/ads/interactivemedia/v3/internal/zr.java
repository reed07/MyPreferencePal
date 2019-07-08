package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/* compiled from: IMASDK */
public final class zr implements xl {
    private final xu a;
    private final wn b;
    private final yj c;
    private final zi d;
    private final abr e = abr.a();

    public zr(xu xuVar, wn wnVar, yj yjVar, zi ziVar) {
        this.a = xuVar;
        this.b = wnVar;
        this.c = yjVar;
        this.d = ziVar;
    }

    private final boolean a(Field field, boolean z) {
        yj yjVar = this.c;
        return !yjVar.a(field.getType(), z) && !yjVar.a(field, z);
    }

    public final <T> xj<T> a(wo woVar, abt<T> abt) {
        LinkedHashMap linkedHashMap;
        ys ysVar;
        Class cls;
        int i;
        int i2;
        Field[] fieldArr;
        ys ysVar2;
        LinkedHashMap linkedHashMap2;
        Type type;
        boolean z;
        List list;
        Type type2;
        zt ztVar;
        xj xjVar;
        zr zrVar = this;
        wo woVar2 = woVar;
        Class a2 = abt.a();
        if (!Object.class.isAssignableFrom(a2)) {
            return null;
        }
        abt<T> abt2 = abt;
        ys a3 = zrVar.a.a(abt2);
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        if (!a2.isInterface()) {
            Type b2 = abt.b();
            Class cls2 = a2;
            abt<T> abt3 = abt2;
            while (cls2 != Object.class) {
                Field[] declaredFields = cls2.getDeclaredFields();
                int length = declaredFields.length;
                boolean z2 = false;
                int i3 = 0;
                while (i3 < length) {
                    Field field = declaredFields[i3];
                    boolean a4 = zrVar.a(field, true);
                    boolean a5 = zrVar.a(field, z2);
                    if (a4 || a5) {
                        zrVar.e.a(field);
                        Type a6 = xq.a(abt3.b(), cls2, field.getGenericType());
                        xn xnVar = (xn) field.getAnnotation(xn.class);
                        if (xnVar == null) {
                            z = a4;
                            list = Collections.singletonList(zrVar.b.a(field));
                        } else {
                            String a7 = xnVar.a();
                            String[] b3 = xnVar.b();
                            if (b3.length == 0) {
                                z = a4;
                                list = Collections.singletonList(a7);
                            } else {
                                z = a4;
                                list = new ArrayList(b3.length + 1);
                                list.add(a7);
                                for (String add : b3) {
                                    list.add(add);
                                }
                            }
                        }
                        int size = list.size();
                        zt ztVar2 = null;
                        int i4 = 0;
                        while (i4 < size) {
                            String str = (String) list.get(i4);
                            if (i4 != 0) {
                                type2 = b2;
                                z = false;
                            } else {
                                type2 = b2;
                            }
                            abt a8 = abt.a(a6);
                            boolean a9 = yt.a((Type) a8.a());
                            String str2 = str;
                            xm xmVar = (xm) field.getAnnotation(xm.class);
                            if (xmVar != null) {
                                ztVar = ztVar2;
                                xjVar = zi.a(zrVar.a, woVar2, a8, xmVar);
                            } else {
                                ztVar = ztVar2;
                                xjVar = null;
                            }
                            boolean z3 = xjVar != null;
                            if (xjVar == null) {
                                xjVar = woVar2.a(a8);
                            }
                            String str3 = str2;
                            zt ztVar3 = r0;
                            zt ztVar4 = ztVar;
                            int i5 = i4;
                            Field field2 = field;
                            int i6 = i3;
                            int i7 = size;
                            int i8 = length;
                            boolean z4 = z3;
                            Field[] fieldArr2 = declaredFields;
                            Class cls3 = cls2;
                            List list2 = list;
                            Type type3 = type2;
                            ys ysVar3 = a3;
                            LinkedHashMap linkedHashMap4 = linkedHashMap3;
                            zt ztVar5 = new zt(this, str3, z, a5, field2, z4, xjVar, woVar, a8, a9);
                            ztVar2 = ztVar4 == null ? (zt) linkedHashMap4.put(str3, ztVar3) : ztVar4;
                            i4 = i5 + 1;
                            cls2 = cls3;
                            b2 = type3;
                            linkedHashMap3 = linkedHashMap4;
                            a3 = ysVar3;
                            field = field2;
                            declaredFields = fieldArr2;
                            list = list2;
                            i3 = i6;
                            size = i7;
                            length = i8;
                            zrVar = this;
                            woVar2 = woVar;
                        }
                        zt ztVar6 = ztVar2;
                        i2 = i3;
                        i = length;
                        fieldArr = declaredFields;
                        cls = cls2;
                        type = b2;
                        ysVar2 = a3;
                        linkedHashMap2 = linkedHashMap3;
                        if (ztVar6 != null) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(type);
                            sb.append(" declares multiple JSON fields named ");
                            sb.append(ztVar6.a);
                            throw new IllegalArgumentException(sb.toString());
                        }
                    } else {
                        i2 = i3;
                        i = length;
                        fieldArr = declaredFields;
                        cls = cls2;
                        type = b2;
                        ysVar2 = a3;
                        linkedHashMap2 = linkedHashMap3;
                    }
                    i3 = i2 + 1;
                    cls2 = cls;
                    b2 = type;
                    linkedHashMap3 = linkedHashMap2;
                    a3 = ysVar2;
                    declaredFields = fieldArr;
                    length = i;
                    z2 = false;
                    zrVar = this;
                    woVar2 = woVar;
                }
                Class cls4 = cls2;
                Type type4 = b2;
                ys ysVar4 = a3;
                LinkedHashMap linkedHashMap5 = linkedHashMap3;
                abt3 = abt.a(xq.a(abt3.b(), cls4, cls4.getGenericSuperclass()));
                cls2 = abt3.a();
                a3 = ysVar4;
                zrVar = this;
                woVar2 = woVar;
            }
            ys ysVar5 = a3;
            linkedHashMap = linkedHashMap3;
            ysVar = ysVar5;
        } else {
            ys ysVar6 = a3;
            linkedHashMap = linkedHashMap3;
            ysVar = ysVar6;
        }
        return new zs(ysVar, linkedHashMap);
    }
}
