package com.inmobi.commons.core.utilities.b;

import android.location.Location;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.d.c;
import com.myfitnesspal.shared.constants.Constants.Challenges;
import java.util.HashMap;
import java.util.Locale;

/* compiled from: PublisherProvidedUserInfoDao */
public final class g {
    private static int a = Integer.MIN_VALUE;
    private static String b = null;
    private static String c = null;
    private static String d = null;
    private static String e = null;
    private static String f = null;
    private static String g = null;
    private static int h = Integer.MIN_VALUE;
    private static String i;
    private static String j;
    private static String k;
    private static String l;
    private static Location m;

    public static String a() {
        return c.a("user_info_store");
    }

    public static void b() {
        a(a);
        a(b);
        b(c);
        c(d);
        d(e);
        e(f);
        f(g);
        b(h);
        g(i);
        h(j);
        i(k);
        j(l);
        a(m);
    }

    public static void a(int i2) {
        if (!a.a() || i2 == Integer.MIN_VALUE) {
            a = i2;
        } else {
            c.b("user_info_store").a(Challenges.USER_AGE, i2);
        }
    }

    public static void a(String str) {
        if (!a.a() || str == null) {
            b = str;
        } else {
            c.b("user_info_store").a("user_age_group", str);
        }
    }

    public static void b(String str) {
        if (!a.a() || str == null) {
            c = str;
        } else {
            c.b("user_info_store").a("user_area_code", str);
        }
    }

    public static void c(String str) {
        if (!a.a() || str == null) {
            d = str;
        } else {
            c.b("user_info_store").a("user_post_code", str);
        }
    }

    public static void d(String str) {
        if (!a.a() || str == null) {
            e = str;
        } else {
            c.b("user_info_store").a("user_city_code", str);
        }
    }

    public static void e(String str) {
        if (!a.a() || str == null) {
            f = str;
        } else {
            c.b("user_info_store").a("user_state_code", str);
        }
    }

    public static void f(String str) {
        if (!a.a() || str == null) {
            g = str;
        } else {
            c.b("user_info_store").a(Challenges.USER_COUNTRY_CODE, str);
        }
    }

    public static void b(int i2) {
        if (!a.a() || i2 == Integer.MIN_VALUE) {
            h = i2;
        } else {
            c.b("user_info_store").a("user_yob", i2);
        }
    }

    public static void g(String str) {
        if (!a.a() || str == null) {
            i = str;
        } else {
            c.b("user_info_store").a(Challenges.USER_GENDER, str);
        }
    }

    public static void h(String str) {
        if (!a.a() || str == null) {
            j = str;
        } else {
            c.b("user_info_store").a("user_education", str);
        }
    }

    public static void i(String str) {
        if (!a.a() || str == null) {
            k = str;
        } else {
            c.b("user_info_store").a("user_language", str);
        }
    }

    public static void j(String str) {
        if (!a.a() || str == null) {
            l = str;
        } else {
            c.b("user_info_store").a("user_interest", str);
        }
    }

    public static Location c() {
        Location location = m;
        if (location != null) {
            return location;
        }
        String c2 = c.b("user_info_store").c("user_location");
        Location location2 = null;
        if (c2 == null) {
            return null;
        }
        Location location3 = new Location("");
        try {
            String[] split = c2.split(",");
            location3.setLatitude(Double.parseDouble(split[0]));
            location3.setLongitude(Double.parseDouble(split[1]));
            location3.setAccuracy(Float.parseFloat(split[2]));
            location3.setTime(Long.parseLong(split[3]));
            location2 = location3;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException unused) {
        }
        return location2;
    }

    public static void a(Location location) {
        if (!a.a() || location == null) {
            m = location;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(location.getLatitude());
        sb.append(",");
        sb.append(location.getLongitude());
        sb.append(",");
        sb.append((int) location.getAccuracy());
        sb.append(",");
        sb.append(location.getTime());
        c.b("user_info_store").a("user_location", sb.toString());
    }

    public static HashMap<String, String> d() {
        HashMap<String, String> hashMap = new HashMap<>();
        int i2 = a;
        if (i2 == Integer.MIN_VALUE) {
            i2 = c.b("user_info_store").d(Challenges.USER_AGE);
        }
        if (i2 != Integer.MIN_VALUE && i2 > 0) {
            hashMap.put("u-age", String.valueOf(i2));
        }
        int i3 = h;
        if (i3 == Integer.MIN_VALUE) {
            i3 = c.b("user_info_store").d("user_yob");
        }
        if (i3 != Integer.MIN_VALUE && i3 > 0) {
            hashMap.put("u-yearofbirth", String.valueOf(i3));
        }
        String str = e;
        if (str == null) {
            str = c.b("user_info_store").c("user_city_code");
        }
        String str2 = f;
        if (str2 == null) {
            str2 = c.b("user_info_store").c("user_state_code");
        }
        String str3 = g;
        if (str3 == null) {
            str3 = c.b("user_info_store").c(Challenges.USER_COUNTRY_CODE);
        }
        String str4 = "";
        if (!(str == null || str.trim().length() == 0)) {
            str4 = str.trim();
        }
        if (!(str2 == null || str2.trim().length() == 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str4);
            sb.append("-");
            sb.append(str2.trim());
            str4 = sb.toString();
        }
        if (!(str3 == null || str3.trim().length() == 0)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str4);
            sb2.append("-");
            sb2.append(str3.trim());
            str4 = sb2.toString();
        }
        if (!(str4 == null || str4.trim().length() == 0)) {
            hashMap.put("u-location", str4);
        }
        String str5 = b;
        if (str5 == null) {
            str5 = c.b("user_info_store").c("user_age_group");
        }
        if (str5 != null) {
            hashMap.put("u-agegroup", str5.toLowerCase(Locale.ENGLISH));
        }
        String str6 = c;
        if (str6 == null) {
            str6 = c.b("user_info_store").c("user_area_code");
        }
        if (str6 != null) {
            hashMap.put("u-areacode", str6);
        }
        String str7 = d;
        if (str7 == null) {
            str7 = c.b("user_info_store").c("user_post_code");
        }
        if (str7 != null) {
            hashMap.put("u-postalcode", str7);
        }
        String str8 = i;
        if (str8 == null) {
            str8 = c.b("user_info_store").c(Challenges.USER_GENDER);
        }
        if (str8 != null) {
            hashMap.put("u-gender", str8);
        }
        String str9 = j;
        if (str9 == null) {
            str9 = c.b("user_info_store").c("user_education");
        }
        if (str9 != null) {
            hashMap.put("u-education", str9);
        }
        String str10 = k;
        if (str10 == null) {
            str10 = c.b("user_info_store").c("user_language");
        }
        if (str10 != null) {
            hashMap.put("u-language", str10);
        }
        String str11 = l;
        if (str11 == null) {
            str11 = c.b("user_info_store").c("user_interest");
        }
        if (str11 != null) {
            hashMap.put("u-interests", str11);
        }
        return hashMap;
    }
}
