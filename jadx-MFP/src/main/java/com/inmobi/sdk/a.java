package com.inmobi.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import com.inmobi.commons.core.d.c;
import com.inmobi.commons.core.utilities.b.g;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/* compiled from: FileManager */
final class a {
    @SuppressLint({"SdCardPath"})
    public static boolean a(Context context) {
        List asList = Arrays.asList(new String[]{"carbpreference", "IMAdMLtvpRuleCache", "inmobiAppAnalyticsSession", "aeskeygenerate", "impref", "IMAdTrackerStatusUpload", "IMAdMMediationCache", "inmobiAppAnalyticsAppId", "inmobiAppAnalyticsSession", "inmobisdkaid", "IMAdTrackerStatusUpload", "testAppPref"});
        for (int i = 0; i < asList.size(); i++) {
            StringBuilder sb = new StringBuilder("/data/data/");
            sb.append(context.getPackageName());
            sb.append("/shared_prefs/");
            sb.append((String) asList.get(i));
            sb.append(".xml");
            File file = new File(sb.toString());
            if (file.exists()) {
                file.delete();
            }
        }
        List asList2 = Arrays.asList(new String[]{c.a("carb_store"), c.a("config_store"), c.a("aes_key_store"), c.a("mraid_js_store"), g.a()});
        for (int i2 = 0; i2 < asList2.size(); i2++) {
            StringBuilder sb2 = new StringBuilder("/data/data/");
            sb2.append(context.getPackageName());
            sb2.append("/shared_prefs/");
            sb2.append((String) asList2.get(i2));
            sb2.append(".xml");
            File file2 = new File(sb2.toString());
            if (file2.exists()) {
                file2.delete();
            }
        }
        List asList3 = Arrays.asList(new String[]{"inmobi.cache", "inmobi.cache.data", "inmobi.cache.data.events.number", "inmobi.cache.data.events.timestamp"});
        for (int i3 = 0; i3 < asList3.size(); i3++) {
            if (context.getCacheDir() != null) {
                File file3 = new File(context.getCacheDir(), (String) asList3.get(i3));
                if (file3.exists()) {
                    file3.delete();
                }
            }
        }
        List asList4 = Arrays.asList(new String[]{"eventlog", "imai_click_events"});
        for (int i4 = 0; i4 < asList4.size(); i4++) {
            if (context.getDir("data", 0) != null) {
                File file4 = new File(context.getDir("data", 0), (String) asList4.get(i4));
                if (file4.exists()) {
                    file4.delete();
                }
            }
        }
        if (b(context).size() != 0) {
            return true;
        }
        return false;
    }

    private static boolean a(Context context, String str) {
        File databasePath = context.getDatabasePath(str);
        return databasePath == null || !databasePath.exists() || context.deleteDatabase(str);
    }

    public static List<String> b(Context context) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        hashSet.add("adcache.db");
        hashSet.add("appengage.db");
        hashSet.add("im.db");
        hashSet.add("ltvp.db");
        hashSet.add("analytics.db");
        hashSet.add("com.im.db");
        String[] databaseList = context.databaseList();
        if (databaseList != null && databaseList.length > 0) {
            for (String str : databaseList) {
                if (hashSet.contains(str) && !a(context, str)) {
                    arrayList.add(str);
                } else if (str.matches("com\\.im_([0-9]+\\.){3}db") && !str.equals(com.inmobi.commons.core.d.a.a) && !a(context, str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }
}
