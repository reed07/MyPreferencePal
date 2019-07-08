package io.fabric.sdk.android;

import android.os.SystemClock;
import android.text.TextUtils;
import com.google.ads.AdRequest;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

class FabricKitsFinder implements Callable<Map<String, KitInfo>> {
    final String apkFileName;

    FabricKitsFinder(String str) {
        this.apkFileName = str;
    }

    public Map<String, KitInfo> call() throws Exception {
        HashMap hashMap = new HashMap();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        hashMap.putAll(findImplicitKits());
        hashMap.putAll(findRegisteredKits());
        StringBuilder sb = new StringBuilder();
        sb.append("finish scanning in ");
        sb.append(SystemClock.elapsedRealtime() - elapsedRealtime);
        Fabric.getLogger().v("Fabric", sb.toString());
        return hashMap;
    }

    private Map<String, KitInfo> findImplicitKits() {
        HashMap hashMap = new HashMap();
        try {
            Class.forName("com.google.android.gms.ads.AdView");
            KitInfo kitInfo = new KitInfo("com.google.firebase.firebase-ads", AdRequest.VERSION, "binary");
            hashMap.put(kitInfo.getIdentifier(), kitInfo);
            Fabric.getLogger().v("Fabric", "Found kit: com.google.firebase.firebase-ads");
        } catch (Exception unused) {
        }
        return hashMap;
    }

    private Map<String, KitInfo> findRegisteredKits() throws Exception {
        HashMap hashMap = new HashMap();
        ZipFile loadApkFile = loadApkFile();
        Enumeration entries = loadApkFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            if (zipEntry.getName().startsWith("fabric/") && zipEntry.getName().length() > 7) {
                KitInfo loadKitInfo = loadKitInfo(zipEntry, loadApkFile);
                if (loadKitInfo != null) {
                    hashMap.put(loadKitInfo.getIdentifier(), loadKitInfo);
                    Fabric.getLogger().v("Fabric", String.format("Found kit:[%s] version:[%s]", new Object[]{loadKitInfo.getIdentifier(), loadKitInfo.getVersion()}));
                }
            }
        }
        if (loadApkFile != null) {
            try {
                loadApkFile.close();
            } catch (IOException unused) {
            }
        }
        return hashMap;
    }

    private KitInfo loadKitInfo(ZipEntry zipEntry, ZipFile zipFile) {
        InputStream inputStream;
        try {
            inputStream = zipFile.getInputStream(zipEntry);
            try {
                Properties properties = new Properties();
                properties.load(inputStream);
                String property = properties.getProperty("fabric-identifier");
                String property2 = properties.getProperty("fabric-version");
                String property3 = properties.getProperty("fabric-build-type");
                if (TextUtils.isEmpty(property) || TextUtils.isEmpty(property2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid format of fabric file,");
                    sb.append(zipEntry.getName());
                    throw new IllegalStateException(sb.toString());
                }
                KitInfo kitInfo = new KitInfo(property, property2, property3);
                CommonUtils.closeQuietly(inputStream);
                return kitInfo;
            } catch (IOException e) {
                e = e;
                try {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Error when parsing fabric properties ");
                    sb2.append(zipEntry.getName());
                    Fabric.getLogger().e("Fabric", sb2.toString(), e);
                    CommonUtils.closeQuietly(inputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    CommonUtils.closeQuietly(inputStream);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
            StringBuilder sb22 = new StringBuilder();
            sb22.append("Error when parsing fabric properties ");
            sb22.append(zipEntry.getName());
            Fabric.getLogger().e("Fabric", sb22.toString(), e);
            CommonUtils.closeQuietly(inputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            CommonUtils.closeQuietly(inputStream);
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public ZipFile loadApkFile() throws IOException {
        return new ZipFile(this.apkFileName);
    }
}
