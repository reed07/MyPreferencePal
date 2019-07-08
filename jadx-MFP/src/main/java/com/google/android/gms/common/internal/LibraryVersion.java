package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import com.myfitnesspal.shared.constants.Constants.Analytics;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@KeepForSdk
public class LibraryVersion {
    private static final GmsLogger zzel = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzem = new LibraryVersion();
    private ConcurrentHashMap<String, String> zzen = new ConcurrentHashMap<>();

    @KeepForSdk
    public static LibraryVersion getInstance() {
        return zzem;
    }

    @VisibleForTesting
    protected LibraryVersion() {
    }

    @KeepForSdk
    public String getVersion(@NonNull String str) {
        Preconditions.checkNotEmpty(str, "Please provide a valid libraryName");
        if (this.zzen.containsKey(str)) {
            return (String) this.zzen.get(str);
        }
        Properties properties = new Properties();
        String str2 = null;
        try {
            InputStream resourceAsStream = LibraryVersion.class.getResourceAsStream(String.format("/%s.properties", new Object[]{str}));
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
                str2 = properties.getProperty("version", null);
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12 + String.valueOf(str2).length());
                sb.append(str);
                sb.append(" version is ");
                sb.append(str2);
                zzel.v("LibraryVersion", sb.toString());
            } else {
                GmsLogger gmsLogger = zzel;
                String str3 = "LibraryVersion";
                String str4 = "Failed to get app version for libraryName: ";
                String valueOf = String.valueOf(str);
                gmsLogger.e(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
            }
        } catch (IOException e) {
            GmsLogger gmsLogger2 = zzel;
            String str5 = "LibraryVersion";
            String str6 = "Failed to get app version for libraryName: ";
            String valueOf2 = String.valueOf(str);
            gmsLogger2.e(str5, valueOf2.length() != 0 ? str6.concat(valueOf2) : new String(str6), e);
        }
        if (str2 == null) {
            str2 = Analytics.UNKNOWN;
            zzel.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used");
        }
        this.zzen.put(str, str2);
        return str2;
    }
}
