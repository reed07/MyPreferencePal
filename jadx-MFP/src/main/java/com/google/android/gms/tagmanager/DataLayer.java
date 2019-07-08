package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@VisibleForTesting
public class DataLayer {
    public static final String EVENT_KEY = "event";
    public static final Object OBJECT_NOT_PRESENT = new Object();
    private static final String[] zzbbb = "gtm.lifetime".toString().split("\\.");
    private static final Pattern zzbbc = Pattern.compile("(\\d+)\\s*([smhd]?)");
    private final ConcurrentHashMap<zzb, Integer> zzbbd;
    private final Map<String, Object> zzbbe;
    private final ReentrantLock zzbbf;
    private final LinkedList<Map<String, Object>> zzbbg;
    private final zzc zzbbh;
    /* access modifiers changed from: private */
    public final CountDownLatch zzbbi;

    static final class zza {
        public final String mKey;
        public final Object mValue;

        zza(String str, Object obj) {
            this.mKey = str;
            this.mValue = obj;
        }

        public final String toString() {
            String str = this.mKey;
            String obj = this.mValue.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(obj).length());
            sb.append("Key: ");
            sb.append(str);
            sb.append(" value: ");
            sb.append(obj);
            return sb.toString();
        }

        public final int hashCode() {
            return Arrays.hashCode(new Integer[]{Integer.valueOf(this.mKey.hashCode()), Integer.valueOf(this.mValue.hashCode())});
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (!this.mKey.equals(zza.mKey) || !this.mValue.equals(zza.mValue)) {
                return false;
            }
            return true;
        }
    }

    interface zzb {
        void zzd(Map<String, Object> map);
    }

    interface zzc {
        void zza(zzaq zzaq);

        void zza(List<zza> list, long j);

        void zzdj(String str);
    }

    @VisibleForTesting
    DataLayer() {
        this(new zzao());
    }

    DataLayer(zzc zzc2) {
        this.zzbbh = zzc2;
        this.zzbbd = new ConcurrentHashMap<>();
        this.zzbbe = new HashMap();
        this.zzbbf = new ReentrantLock();
        this.zzbbg = new LinkedList<>();
        this.zzbbi = new CountDownLatch(1);
        this.zzbbh.zza(new zzap(this));
    }

    public String toString() {
        String sb;
        synchronized (this.zzbbe) {
            StringBuilder sb2 = new StringBuilder();
            for (Entry entry : this.zzbbe.entrySet()) {
                sb2.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", new Object[]{entry.getKey(), entry.getValue()}));
            }
            sb = sb2.toString();
        }
        return sb;
    }

    public void pushEvent(String str, Map<String, Object> map) {
        HashMap hashMap = new HashMap(map);
        hashMap.put("event", str);
        push(hashMap);
    }

    public void push(String str, Object obj) {
        push(zzk(str, obj));
    }

    public void push(Map<String, Object> map) {
        try {
            this.zzbbi.await();
        } catch (InterruptedException unused) {
            zzdi.zzab("DataLayer.push: unexpected InterruptedException");
        }
        zzf(map);
    }

    /* access modifiers changed from: private */
    public final void zzf(Map<String, Object> map) {
        Long l;
        Object obj;
        this.zzbbf.lock();
        try {
            this.zzbbg.offer(map);
            int i = 0;
            if (this.zzbbf.getHoldCount() == 1) {
                int i2 = 0;
                while (true) {
                    Map map2 = (Map) this.zzbbg.poll();
                    if (map2 == null) {
                        break;
                    }
                    synchronized (this.zzbbe) {
                        for (String str : map2.keySet()) {
                            zzb(zzk(str, map2.get(str)), this.zzbbe);
                        }
                    }
                    for (zzb zzd : this.zzbbd.keySet()) {
                        zzd.zzd(map2);
                    }
                    i2++;
                    if (i2 > 500) {
                        this.zzbbg.clear();
                        throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
                    }
                }
            }
            String[] strArr = zzbbb;
            int length = strArr.length;
            Object obj2 = map;
            while (true) {
                l = null;
                if (i >= length) {
                    obj = obj2;
                    break;
                }
                String str2 = strArr[i];
                if (!(obj2 instanceof Map)) {
                    obj = 0;
                    break;
                } else {
                    i++;
                    obj2 = ((Map) obj2).get(str2);
                }
            }
            if (obj != 0) {
                l = zzdi(obj.toString());
            }
            if (l != null) {
                ArrayList arrayList = new ArrayList();
                zza(map, "", arrayList);
                this.zzbbh.zza(arrayList, l.longValue());
            }
            this.zzbbf.unlock();
        } catch (Throwable th) {
            this.zzbbf.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzdh(String str) {
        push(str, null);
        this.zzbbh.zzdj(str);
    }

    private final void zza(Map<String, Object> map, String str, Collection<zza> collection) {
        for (Entry entry : map.entrySet()) {
            String str2 = str.length() == 0 ? "" : ".";
            String str3 = (String) entry.getKey();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(str3).length());
            sb.append(str);
            sb.append(str2);
            sb.append(str3);
            String sb2 = sb.toString();
            if (entry.getValue() instanceof Map) {
                zza((Map) entry.getValue(), sb2, collection);
            } else if (!sb2.equals("gtm.lifetime")) {
                collection.add(new zza(sb2, entry.getValue()));
            }
        }
    }

    @VisibleForTesting
    private static Long zzdi(String str) {
        long j;
        Matcher matcher = zzbbc.matcher(str);
        if (!matcher.matches()) {
            String str2 = "unknown _lifetime: ";
            String valueOf = String.valueOf(str);
            zzdi.zzdm(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return null;
        }
        try {
            j = Long.parseLong(matcher.group(1));
        } catch (NumberFormatException unused) {
            String str3 = "illegal number in _lifetime value: ";
            String valueOf2 = String.valueOf(str);
            zzdi.zzab(valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
            j = 0;
        }
        if (j <= 0) {
            String str4 = "non-positive _lifetime: ";
            String valueOf3 = String.valueOf(str);
            zzdi.zzdm(valueOf3.length() != 0 ? str4.concat(valueOf3) : new String(str4));
            return null;
        }
        String group = matcher.group(2);
        if (group.length() == 0) {
            return Long.valueOf(j);
        }
        char charAt = group.charAt(0);
        if (charAt == 'd') {
            return Long.valueOf(j * 1000 * 60 * 60 * 24);
        }
        if (charAt == 'h') {
            return Long.valueOf(j * 1000 * 60 * 60);
        }
        if (charAt == 'm') {
            return Long.valueOf(j * 1000 * 60);
        }
        if (charAt == 's') {
            return Long.valueOf(j * 1000);
        }
        String str5 = "unknown units in _lifetime: ";
        String valueOf4 = String.valueOf(str);
        zzdi.zzab(valueOf4.length() != 0 ? str5.concat(valueOf4) : new String(str5));
        return null;
    }

    public Object get(String str) {
        String[] split;
        synchronized (this.zzbbe) {
            Object obj = this.zzbbe;
            for (String str2 : str.split("\\.")) {
                if (!(obj instanceof Map)) {
                    return null;
                }
                obj = ((Map) obj).get(str2);
                if (obj == null) {
                    return null;
                }
            }
            return obj;
        }
    }

    @VisibleForTesting
    public static Map<String, Object> mapOf(Object... objArr) {
        if (objArr.length % 2 == 0) {
            HashMap hashMap = new HashMap();
            int i = 0;
            while (i < objArr.length) {
                if (objArr[i] instanceof String) {
                    hashMap.put(objArr[i], objArr[i + 1]);
                    i += 2;
                } else {
                    String valueOf = String.valueOf(objArr[i]);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 21);
                    sb.append("key is not a string: ");
                    sb.append(valueOf);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            return hashMap;
        }
        throw new IllegalArgumentException("expected even number of key-value pairs");
    }

    @VisibleForTesting
    public static List<Object> listOf(Object... objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object add : objArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzb zzb2) {
        this.zzbbd.put(zzb2, Integer.valueOf(0));
    }

    static Map<String, Object> zzk(String str, Object obj) {
        HashMap hashMap = new HashMap();
        String[] split = str.toString().split("\\.");
        int i = 0;
        HashMap hashMap2 = hashMap;
        while (i < split.length - 1) {
            HashMap hashMap3 = new HashMap();
            hashMap2.put(split[i], hashMap3);
            i++;
            hashMap2 = hashMap3;
        }
        hashMap2.put(split[split.length - 1], obj);
        return hashMap;
    }

    @VisibleForTesting
    private final void zzb(Map<String, Object> map, Map<String, Object> map2) {
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj instanceof List) {
                if (!(map2.get(str) instanceof List)) {
                    map2.put(str, new ArrayList());
                }
                zza((List) obj, (List) map2.get(str));
            } else if (obj instanceof Map) {
                if (!(map2.get(str) instanceof Map)) {
                    map2.put(str, new HashMap());
                }
                zzb((Map) obj, (Map) map2.get(str));
            } else {
                map2.put(str, obj);
            }
        }
    }

    @VisibleForTesting
    private final void zza(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add(null);
        }
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof List) {
                if (!(list2.get(i) instanceof List)) {
                    list2.set(i, new ArrayList());
                }
                zza((List) obj, (List) list2.get(i));
            } else if (obj instanceof Map) {
                if (!(list2.get(i) instanceof Map)) {
                    list2.set(i, new HashMap());
                }
                zzb((Map) obj, (Map) list2.get(i));
            } else if (obj != OBJECT_NOT_PRESENT) {
                list2.set(i, obj);
            }
        }
    }
}
