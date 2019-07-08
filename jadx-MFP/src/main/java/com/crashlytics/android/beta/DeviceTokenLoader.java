package com.crashlytics.android.beta;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import io.fabric.sdk.android.services.cache.ValueLoader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DeviceTokenLoader implements ValueLoader<String> {
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String load(android.content.Context r8) throws java.lang.Exception {
        /*
            r7 = this;
            long r0 = java.lang.System.nanoTime()
            java.lang.String r2 = ""
            r3 = 0
            java.lang.String r4 = "io.crash.air"
            java.util.zip.ZipInputStream r3 = r7.getZipInputStreamOfApkFrom(r8, r4)     // Catch:{ NameNotFoundException -> 0x004a, FileNotFoundException -> 0x0038, IOException -> 0x0026 }
            java.lang.String r2 = r7.determineDeviceToken(r3)     // Catch:{ NameNotFoundException -> 0x004a, FileNotFoundException -> 0x0038, IOException -> 0x0026 }
            if (r3 == 0) goto L_0x005a
            r3.close()     // Catch:{ IOException -> 0x0017 }
            goto L_0x005a
        L_0x0017:
            r8 = move-exception
            io.fabric.sdk.android.Logger r3 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r4 = "Beta"
            java.lang.String r5 = "Failed to close the APK file"
            r3.e(r4, r5, r8)
            goto L_0x005a
        L_0x0024:
            r8 = move-exception
            goto L_0x0086
        L_0x0026:
            r8 = move-exception
            io.fabric.sdk.android.Logger r4 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ all -> 0x0024 }
            java.lang.String r5 = "Beta"
            java.lang.String r6 = "Failed to read the APK file"
            r4.e(r5, r6, r8)     // Catch:{ all -> 0x0024 }
            if (r3 == 0) goto L_0x005a
            r3.close()     // Catch:{ IOException -> 0x0017 }
            goto L_0x005a
        L_0x0038:
            r8 = move-exception
            io.fabric.sdk.android.Logger r4 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ all -> 0x0024 }
            java.lang.String r5 = "Beta"
            java.lang.String r6 = "Failed to find the APK file"
            r4.e(r5, r6, r8)     // Catch:{ all -> 0x0024 }
            if (r3 == 0) goto L_0x005a
            r3.close()     // Catch:{ IOException -> 0x0017 }
            goto L_0x005a
        L_0x004a:
            io.fabric.sdk.android.Logger r8 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ all -> 0x0024 }
            java.lang.String r4 = "Beta"
            java.lang.String r5 = "Beta by Crashlytics app is not installed"
            r8.d(r4, r5)     // Catch:{ all -> 0x0024 }
            if (r3 == 0) goto L_0x005a
            r3.close()     // Catch:{ IOException -> 0x0017 }
        L_0x005a:
            long r3 = java.lang.System.nanoTime()
            long r3 = r3 - r0
            double r0 = (double) r3
            r3 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r0 = r0 / r3
            io.fabric.sdk.android.Logger r8 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r3 = "Beta"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Beta device token load took "
            r4.append(r5)
            r4.append(r0)
            java.lang.String r0 = "ms"
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r8.d(r3, r0)
            return r2
        L_0x0086:
            if (r3 == 0) goto L_0x0098
            r3.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x0098
        L_0x008c:
            r0 = move-exception
            io.fabric.sdk.android.Logger r1 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r2 = "Beta"
            java.lang.String r3 = "Failed to close the APK file"
            r1.e(r2, r3, r0)
        L_0x0098:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.beta.DeviceTokenLoader.load(android.content.Context):java.lang.String");
    }

    /* access modifiers changed from: 0000 */
    public ZipInputStream getZipInputStreamOfApkFrom(Context context, String str) throws NameNotFoundException, FileNotFoundException {
        return new ZipInputStream(new FileInputStream(context.getPackageManager().getApplicationInfo(str, 0).sourceDir));
    }

    /* access modifiers changed from: 0000 */
    public String determineDeviceToken(ZipInputStream zipInputStream) throws IOException {
        ZipEntry nextEntry = zipInputStream.getNextEntry();
        if (nextEntry != null) {
            String name = nextEntry.getName();
            if (name.startsWith("assets/com.crashlytics.android.beta/dirfactor-device-token=")) {
                return name.substring(59, name.length() - 1);
            }
        }
        return "";
    }
}
