package com.inmobi.a.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.inmobi.a.o;
import com.inmobi.a.p.b;
import com.inmobi.commons.a.a;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@TargetApi(17)
/* compiled from: CellularInfoUtil */
public class c {
    private static final String a = "c";

    private static boolean a(int i, int i2) {
        return (i & i2) == i2;
    }

    public static Map<String, String> a() {
        Object obj;
        HashMap hashMap = new HashMap();
        Context b = a.b();
        if (b == null) {
            return hashMap;
        }
        b bVar = o.a().a.a;
        if (!(bVar.n && bVar.a)) {
            return hashMap;
        }
        int i = o.a().a.a.m;
        boolean a2 = a(i, 2);
        boolean a3 = a(i, 1);
        a aVar = new a();
        TelephonyManager telephonyManager = (TelephonyManager) b.getSystemService("phone");
        if (!a2) {
            int[] a4 = a(telephonyManager.getNetworkOperator());
            aVar.a = a4[0];
            aVar.b = a4[1];
            String networkCountryIso = telephonyManager.getNetworkCountryIso();
            if (networkCountryIso != null) {
                aVar.e = networkCountryIso.toLowerCase(Locale.ENGLISH);
            }
        }
        if (!a3) {
            int[] a5 = a(telephonyManager.getSimOperator());
            aVar.c = a5[0];
            aVar.d = a5[1];
        }
        String str = "s-ho";
        String str2 = null;
        if (aVar.c == -1 && aVar.d == -1) {
            obj = null;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(aVar.c);
            sb.append("_");
            sb.append(aVar.d);
            obj = sb.toString();
        }
        hashMap.put(str, obj);
        String str3 = "s-co";
        if (!(aVar.a == -1 && aVar.b == -1)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(aVar.a);
            sb2.append("_");
            sb2.append(aVar.b);
            str2 = sb2.toString();
        }
        hashMap.put(str3, str2);
        hashMap.put("s-iso", aVar.e);
        return hashMap;
    }

    private static int[] a(String str) {
        int[] iArr = {-1, -1};
        if (str == null || str.equals("")) {
            return iArr;
        }
        try {
            int parseInt = Integer.parseInt(str.substring(0, 3));
            int parseInt2 = Integer.parseInt(str.substring(3));
            iArr[0] = parseInt;
            iArr[1] = parseInt2;
        } catch (IndexOutOfBoundsException | NumberFormatException unused) {
        }
        return iArr;
    }

    @SuppressLint({"NewApi"})
    private static boolean d() {
        if (VERSION.SDK_INT < 28) {
            return true;
        }
        LocationManager locationManager = (LocationManager) a.b().getSystemService("location");
        if (locationManager != null) {
            return locationManager.isLocationEnabled();
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map<java.lang.String, java.lang.String> b() {
        /*
            com.inmobi.a.o r0 = com.inmobi.a.o.a()
            com.inmobi.a.p r0 = r0.a
            com.inmobi.a.p$b r0 = r0.a
            boolean r1 = r0.p
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0014
            boolean r0 = r0.a
            if (r0 == 0) goto L_0x0014
            r0 = 1
            goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            r1 = 0
            if (r0 == 0) goto L_0x00e6
            android.content.Context r0 = com.inmobi.commons.a.a.b()
            if (r0 == 0) goto L_0x0034
            java.lang.String r4 = "signals"
            java.lang.String r5 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r4 = com.inmobi.commons.core.utilities.e.a(r0, r4, r5)
            java.lang.String r5 = "signals"
            java.lang.String r6 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r0 = com.inmobi.commons.core.utilities.e.a(r0, r5, r6)
            if (r4 != 0) goto L_0x0032
            if (r0 == 0) goto L_0x0034
        L_0x0032:
            r0 = 1
            goto L_0x0035
        L_0x0034:
            r0 = 0
        L_0x0035:
            if (r0 == 0) goto L_0x00e6
            boolean r0 = d()
            if (r0 != 0) goto L_0x003f
            goto L_0x00e6
        L_0x003f:
            android.content.Context r0 = com.inmobi.commons.a.a.b()
            if (r0 != 0) goto L_0x0047
            goto L_0x00e6
        L_0x0047:
            java.lang.String r4 = "phone"
            java.lang.Object r0 = r0.getSystemService(r4)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            java.lang.String r4 = r0.getNetworkOperator()
            int[] r4 = a(r4)
            r5 = r4[r3]
            java.lang.String r6 = java.lang.String.valueOf(r5)
            r2 = r4[r2]
            java.lang.String r7 = java.lang.String.valueOf(r2)
            int r2 = android.os.Build.VERSION.SDK_INT
            r5 = 17
            if (r2 < r5) goto L_0x0092
            java.util.List r2 = r0.getAllCellInfo()
            if (r2 == 0) goto L_0x0092
            r8 = r1
            r5 = 0
        L_0x0071:
            int r9 = r2.size()
            if (r5 >= r9) goto L_0x0086
            java.lang.Object r8 = r2.get(r5)
            android.telephony.CellInfo r8 = (android.telephony.CellInfo) r8
            boolean r9 = r8.isRegistered()
            if (r9 != 0) goto L_0x0086
            int r5 = r5 + 1
            goto L_0x0071
        L_0x0086:
            if (r8 == 0) goto L_0x0092
            com.inmobi.a.a.b r1 = new com.inmobi.a.a.b
            int r0 = r0.getNetworkType()
            r1.<init>(r8, r6, r7, r0)
            goto L_0x00e6
        L_0x0092:
            android.telephony.CellLocation r2 = r0.getCellLocation()
            if (r2 == 0) goto L_0x00e6
            r3 = r4[r3]
            r4 = -1
            if (r3 != r4) goto L_0x009e
            goto L_0x00e6
        L_0x009e:
            com.inmobi.a.a.b r1 = new com.inmobi.a.a.b
            r1.<init>()
            boolean r3 = r2 instanceof android.telephony.cdma.CdmaCellLocation
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == 0) goto L_0x00c7
            android.telephony.cdma.CdmaCellLocation r2 = (android.telephony.cdma.CdmaCellLocation) r2
            r1.b = r4
            int r0 = r0.getNetworkType()
            r1.c = r0
            int r0 = r2.getSystemId()
            int r3 = r2.getNetworkId()
            int r2 = r2.getBaseStationId()
            java.lang.String r0 = com.inmobi.a.a.b.a(r6, r0, r3, r2)
            r1.a = r0
            goto L_0x00e6
        L_0x00c7:
            android.telephony.gsm.GsmCellLocation r2 = (android.telephony.gsm.GsmCellLocation) r2
            r1.b = r4
            int r0 = r0.getNetworkType()
            r1.c = r0
            int r8 = r2.getLac()
            int r9 = r2.getCid()
            int r10 = r2.getPsc()
            r11 = 2147483647(0x7fffffff, float:NaN)
            java.lang.String r0 = com.inmobi.a.a.b.a(r6, r7, r8, r9, r10, r11)
            r1.a = r0
        L_0x00e6:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            if (r1 == 0) goto L_0x00fa
            java.lang.String r2 = "c-sc"
            org.json.JSONObject r1 = r1.a()
            java.lang.String r1 = r1.toString()
            r0.put(r2, r1)
        L_0x00fa:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.a.a.c.b():java.util.Map");
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x0125  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map<java.lang.String, java.lang.String> c() {
        /*
            boolean r0 = com.inmobi.commons.a.a.a()
            r1 = 1
            if (r0 == 0) goto L_0x0115
            boolean r0 = com.inmobi.commons.a.a.a()
            r2 = 0
            if (r0 == 0) goto L_0x001e
            android.content.Context r0 = com.inmobi.commons.a.a.b()
            java.lang.String r3 = "signals"
            java.lang.String r4 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r0 = com.inmobi.commons.core.utilities.e.a(r0, r3, r4)
            if (r0 == 0) goto L_0x001e
            r0 = 1
            goto L_0x001f
        L_0x001e:
            r0 = 0
        L_0x001f:
            if (r0 == 0) goto L_0x0115
            boolean r0 = d()
            if (r0 == 0) goto L_0x0115
            com.inmobi.a.o r0 = com.inmobi.a.o.a()
            com.inmobi.a.p r0 = r0.a
            com.inmobi.a.p$b r0 = r0.a
            boolean r3 = r0.o
            if (r3 == 0) goto L_0x0039
            boolean r0 = r0.a
            if (r0 == 0) goto L_0x0039
            r0 = 1
            goto L_0x003a
        L_0x0039:
            r0 = 0
        L_0x003a:
            if (r0 != 0) goto L_0x003e
            goto L_0x0115
        L_0x003e:
            android.content.Context r0 = com.inmobi.commons.a.a.b()
            if (r0 != 0) goto L_0x004b
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            goto L_0x011a
        L_0x004b:
            java.lang.String r3 = "phone"
            java.lang.Object r0 = r0.getSystemService(r3)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.lang.String r4 = r0.getNetworkOperator()
            int[] r4 = a(r4)
            r5 = r4[r2]
            java.lang.String r6 = java.lang.String.valueOf(r5)
            r4 = r4[r1]
            java.lang.String r7 = java.lang.String.valueOf(r4)
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 17
            if (r4 < r5) goto L_0x009b
            java.util.List r4 = r0.getAllCellInfo()
            if (r4 == 0) goto L_0x009b
            java.util.Iterator r2 = r4.iterator()
        L_0x007c:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0107
            java.lang.Object r4 = r2.next()
            android.telephony.CellInfo r4 = (android.telephony.CellInfo) r4
            boolean r5 = r4.isRegistered()
            if (r5 != 0) goto L_0x007c
            com.inmobi.a.a.b r5 = new com.inmobi.a.a.b
            int r8 = r0.getNetworkType()
            r5.<init>(r4, r6, r7, r8)
            r3.add(r5)
            goto L_0x007c
        L_0x009b:
            java.util.List r0 = r0.getNeighboringCellInfo()
            if (r0 == 0) goto L_0x010f
            boolean r4 = r0.isEmpty()
            if (r4 == 0) goto L_0x00a8
            goto L_0x010f
        L_0x00a8:
            java.util.Iterator r0 = r0.iterator()
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x0109
            java.lang.Object r0 = r0.next()
            android.telephony.NeighboringCellInfo r0 = (android.telephony.NeighboringCellInfo) r0
            com.inmobi.a.a.b r4 = new com.inmobi.a.a.b
            r4.<init>()
            int r5 = r0.getNetworkType()
            r4.c = r5
            int r8 = r0.getRssi()
            r9 = 99
            if (r8 != r9) goto L_0x00d1
            r2 = 2147483647(0x7fffffff, float:NaN)
            r4.b = r2
            goto L_0x00f2
        L_0x00d1:
            r8 = 3
            if (r5 == r8) goto L_0x00dc
            r8 = 15
            if (r5 == r8) goto L_0x00dc
            switch(r5) {
                case 8: goto L_0x00dc;
                case 9: goto L_0x00dc;
                case 10: goto L_0x00dc;
                default: goto L_0x00db;
            }
        L_0x00db:
            goto L_0x00dd
        L_0x00dc:
            r2 = 1
        L_0x00dd:
            if (r2 == 0) goto L_0x00e8
            int r2 = r0.getRssi()
            int r2 = r2 + -116
            r4.b = r2
            goto L_0x00f2
        L_0x00e8:
            int r2 = r0.getRssi()
            int r2 = r2 * 2
            int r2 = r2 + -113
            r4.b = r2
        L_0x00f2:
            int r8 = r0.getLac()
            int r9 = r0.getCid()
            r10 = -1
            r11 = 2147483647(0x7fffffff, float:NaN)
            java.lang.String r0 = com.inmobi.a.a.b.a(r6, r7, r8, r9, r10, r11)
            r4.a = r0
            r3.add(r4)
        L_0x0107:
            r0 = r3
            goto L_0x011a
        L_0x0109:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            goto L_0x011a
        L_0x010f:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            goto L_0x011a
        L_0x0115:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x011a:
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            boolean r3 = r0.isEmpty()
            if (r3 != 0) goto L_0x0145
            org.json.JSONArray r3 = new org.json.JSONArray
            r3.<init>()
            int r4 = r0.size()
            int r4 = r4 - r1
            java.lang.Object r0 = r0.get(r4)
            com.inmobi.a.a.b r0 = (com.inmobi.a.a.b) r0
            org.json.JSONObject r0 = r0.a()
            r3.put(r0)
            java.lang.String r0 = "v-sc"
            java.lang.String r1 = r3.toString()
            r2.put(r0, r1)
        L_0x0145:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.a.a.c.c():java.util.Map");
    }
}
