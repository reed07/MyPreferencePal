package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzuo.zzc;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import kotlin.text.Typography;

final class zzvy {
    static String zza(zzvv zzvv, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zza(zzvv, sb, 0);
        return sb.toString();
    }

    private static void zza(zzvv zzvv, StringBuilder sb, int i) {
        Method[] declaredMethods;
        boolean z;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet<>();
        for (Method method : zzvv.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String replaceFirst = str.replaceFirst("get", "");
            if (replaceFirst.endsWith("List") && !replaceFirst.endsWith("OrBuilderList") && !replaceFirst.equals("List")) {
                String valueOf = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                String valueOf2 = String.valueOf(replaceFirst.substring(1, replaceFirst.length() - 4));
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                Method method2 = (Method) hashMap.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zzb(sb, i, zzgf(concat), zzuo.zza(method2, (Object) zzvv, new Object[0]));
                }
            }
            if (replaceFirst.endsWith("Map") && !replaceFirst.equals("Map")) {
                String valueOf3 = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                String valueOf4 = String.valueOf(replaceFirst.substring(1, replaceFirst.length() - 3));
                String concat2 = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zzb(sb, i, zzgf(concat2), zzuo.zza(method3, (Object) zzvv, new Object[0]));
                }
            }
            String str2 = "set";
            String valueOf5 = String.valueOf(replaceFirst);
            if (((Method) hashMap2.get(valueOf5.length() != 0 ? str2.concat(valueOf5) : new String(str2))) != null) {
                if (replaceFirst.endsWith("Bytes")) {
                    String str3 = "get";
                    String valueOf6 = String.valueOf(replaceFirst.substring(0, replaceFirst.length() - 5));
                    if (hashMap.containsKey(valueOf6.length() != 0 ? str3.concat(valueOf6) : new String(str3))) {
                    }
                }
                String valueOf7 = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                String valueOf8 = String.valueOf(replaceFirst.substring(1));
                String concat3 = valueOf8.length() != 0 ? valueOf7.concat(valueOf8) : new String(valueOf7);
                String str4 = "get";
                String valueOf9 = String.valueOf(replaceFirst);
                Method method4 = (Method) hashMap.get(valueOf9.length() != 0 ? str4.concat(valueOf9) : new String(str4));
                String str5 = "has";
                String valueOf10 = String.valueOf(replaceFirst);
                Method method5 = (Method) hashMap.get(valueOf10.length() != 0 ? str5.concat(valueOf10) : new String(str5));
                if (method4 != null) {
                    Object zza = zzuo.zza(method4, (Object) zzvv, new Object[0]);
                    if (method5 == null) {
                        boolean z2 = zza instanceof Boolean ? !((Boolean) zza).booleanValue() : zza instanceof Integer ? ((Integer) zza).intValue() == 0 : zza instanceof Float ? ((Float) zza).floatValue() == BitmapDescriptorFactory.HUE_RED : zza instanceof Double ? ((Double) zza).doubleValue() == 0.0d : zza instanceof String ? zza.equals("") : zza instanceof zzte ? zza.equals(zzte.zzbts) : zza instanceof zzvv ? zza == ((zzvv) zza).zzwj() : zza instanceof Enum ? ((Enum) zza).ordinal() == 0 : false;
                        z = !z2;
                    } else {
                        z = ((Boolean) zzuo.zza(method5, (Object) zzvv, new Object[0])).booleanValue();
                    }
                    if (z) {
                        zzb(sb, i, zzgf(concat3), zza);
                    }
                }
            }
        }
        if (zzvv instanceof zzc) {
            Iterator it = ((zzc) zzvv).zzbyl.iterator();
            if (it.hasNext()) {
                ((Entry) it.next()).getKey();
                throw new NoSuchMethodError();
            }
        }
        zzuo zzuo = (zzuo) zzvv;
        if (zzuo.zzbyf != null) {
            zzuo.zzbyf.zzb(sb, i);
        }
    }

    static final void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object zzb : (List) obj) {
                zzb(sb, i, str, zzb);
            }
        } else if (obj instanceof Map) {
            for (Entry zzb2 : ((Map) obj).entrySet()) {
                zzb(sb, i, str, zzb2);
            }
        } else {
            sb.append(10);
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzwz.zzd(zzte.zzga((String) obj)));
                sb.append(Typography.quote);
            } else if (obj instanceof zzte) {
                sb.append(": \"");
                sb.append(zzwz.zzd((zzte) obj));
                sb.append(Typography.quote);
            } else if (obj instanceof zzuo) {
                sb.append(" {");
                zza((zzuo) obj, sb, i + 2);
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else if (obj instanceof Entry) {
                sb.append(" {");
                Entry entry = (Entry) obj;
                int i4 = i + 2;
                zzb(sb, i4, IpcUtil.KEY_CODE, entry.getKey());
                zzb(sb, i4, "value", entry.getValue());
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj.toString());
            }
        }
    }

    private static final String zzgf(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}
