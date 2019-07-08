package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzc;
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

final class zzbso {
    static String zza(zzbsl zzbsl, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zza(zzbsl, sb, 0);
        return sb.toString();
    }

    private static void zza(zzbsl zzbsl, StringBuilder sb, int i) {
        Method[] declaredMethods;
        boolean z;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet<>();
        for (Method method : zzbsl.getClass().getDeclaredMethods()) {
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
                    zza(sb, i, zzgb(concat), zzbrd.zza(method2, (Object) zzbsl, new Object[0]));
                }
            }
            if (replaceFirst.endsWith("Map") && !replaceFirst.equals("Map")) {
                String valueOf3 = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                String valueOf4 = String.valueOf(replaceFirst.substring(1, replaceFirst.length() - 3));
                String concat2 = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zza(sb, i, zzgb(concat2), zzbrd.zza(method3, (Object) zzbsl, new Object[0]));
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
                    Object zza = zzbrd.zza(method4, (Object) zzbsl, new Object[0]);
                    if (method5 == null) {
                        boolean z2 = zza instanceof Boolean ? !((Boolean) zza).booleanValue() : zza instanceof Integer ? ((Integer) zza).intValue() == 0 : zza instanceof Float ? ((Float) zza).floatValue() == BitmapDescriptorFactory.HUE_RED : zza instanceof Double ? ((Double) zza).doubleValue() == 0.0d : zza instanceof String ? zza.equals("") : zza instanceof zzbpu ? zza.equals(zzbpu.zzfli) : zza instanceof zzbsl ? zza == ((zzbsl) zza).zzamv() : zza instanceof Enum ? ((Enum) zza).ordinal() == 0 : false;
                        z = !z2;
                    } else {
                        z = ((Boolean) zzbrd.zza(method5, (Object) zzbsl, new Object[0])).booleanValue();
                    }
                    if (z) {
                        zza(sb, i, zzgb(concat3), zza);
                    }
                }
            }
        }
        if (zzbsl instanceof zzc) {
            Iterator it = ((zzc) zzbsl).zzfqa.iterator();
            if (it.hasNext()) {
                ((Entry) it.next()).getKey();
                throw new NoSuchMethodError();
            }
        }
        zzbrd zzbrd = (zzbrd) zzbsl;
        if (zzbrd.zzfpu != null) {
            zzbrd.zzfpu.zza(sb, i);
        }
    }

    static final void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object zza : (List) obj) {
                zza(sb, i, str, zza);
            }
        } else if (obj instanceof Map) {
            for (Entry zza2 : ((Map) obj).entrySet()) {
                zza(sb, i, str, zza2);
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
                sb.append(zzbtq.zzaq(zzbpu.zzfw((String) obj)));
                sb.append(Typography.quote);
            } else if (obj instanceof zzbpu) {
                sb.append(": \"");
                sb.append(zzbtq.zzaq((zzbpu) obj));
                sb.append(Typography.quote);
            } else if (obj instanceof zzbrd) {
                sb.append(" {");
                zza((zzbrd) obj, sb, i + 2);
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
                zza(sb, i4, IpcUtil.KEY_CODE, entry.getKey());
                zza(sb, i4, "value", entry.getValue());
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

    private static final String zzgb(String str) {
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
