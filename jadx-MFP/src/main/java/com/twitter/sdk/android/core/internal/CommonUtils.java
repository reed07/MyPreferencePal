package com.twitter.sdk.android.core.internal;

import android.content.Context;
import android.content.res.Resources;
import com.twitter.sdk.android.core.Twitter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CommonUtils {
    private static Boolean clsTrace;

    public static void copyStream(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public static void closeOrLog(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Twitter.getLogger().e("Twitter", str, e);
            }
        }
    }

    static String getResourcePackageName(Context context) {
        int i = context.getApplicationContext().getApplicationInfo().icon;
        if (i > 0) {
            return context.getResources().getResourcePackageName(i);
        }
        return context.getPackageName();
    }

    static int getResourcesIdentifier(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, getResourcePackageName(context));
    }

    public static boolean getBooleanResourceValue(Context context, String str, boolean z) {
        if (context != null) {
            Resources resources = context.getResources();
            if (resources != null) {
                int resourcesIdentifier = getResourcesIdentifier(context, str, "bool");
                if (resourcesIdentifier > 0) {
                    return resources.getBoolean(resourcesIdentifier);
                }
                int resourcesIdentifier2 = getResourcesIdentifier(context, str, "string");
                if (resourcesIdentifier2 > 0) {
                    return Boolean.parseBoolean(context.getString(resourcesIdentifier2));
                }
            }
        }
        return z;
    }

    public static String getStringResourceValue(Context context, String str, String str2) {
        if (context != null) {
            Resources resources = context.getResources();
            if (resources != null) {
                int resourcesIdentifier = getResourcesIdentifier(context, str, "string");
                if (resourcesIdentifier > 0) {
                    return resources.getString(resourcesIdentifier);
                }
            }
        }
        return str2;
    }

    static boolean isClsTrace(Context context) {
        if (clsTrace == null) {
            clsTrace = Boolean.valueOf(getBooleanResourceValue(context, "com.twitter.sdk.android.TRACE_ENABLED", false));
        }
        return clsTrace.booleanValue();
    }

    public static void logControlled(Context context, String str) {
        if (isClsTrace(context)) {
            Twitter.getLogger().d("Twitter", str);
        }
    }

    public static void logControlledError(Context context, String str, Throwable th) {
        if (isClsTrace(context)) {
            Twitter.getLogger().e("Twitter", str);
        }
    }

    public static void logControlled(Context context, int i, String str, String str2) {
        if (isClsTrace(context)) {
            Twitter.getLogger().log(i, "Twitter", str2);
        }
    }

    public static void logOrThrowIllegalStateException(String str, String str2) {
        if (!Twitter.isDebug()) {
            Twitter.getLogger().w(str, str2);
            return;
        }
        throw new IllegalStateException(str2);
    }
}
