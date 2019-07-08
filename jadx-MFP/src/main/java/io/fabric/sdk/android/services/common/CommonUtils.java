package io.fabric.sdk.android.services.common;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.brightcove.player.event.AbstractEvent;
import com.facebook.internal.ServerProtocol;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.common.base.Ascii;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.myfitnesspal.shared.constants.Constants.ABTest.SmartWaterPhase1;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import io.fabric.sdk.android.Fabric;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CommonUtils {
    public static final Comparator<File> FILE_MODIFIED_COMPARATOR = new Comparator<File>() {
        public int compare(File file, File file2) {
            return (int) (file.lastModified() - file2.lastModified());
        }
    };
    private static final char[] HEX_VALUES = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static Boolean clsTrace;
    private static long totalRamInBytes = -1;

    enum Architecture {
        X86_32,
        X86_64,
        ARM_UNKNOWN,
        PPC,
        PPC64,
        ARMV6,
        ARMV7,
        UNKNOWN,
        ARMV7S,
        ARM64;
        
        private static final Map<String, Architecture> matcher = null;

        static {
            matcher = new HashMap(4);
            matcher.put("armeabi-v7a", ARMV7);
            matcher.put("armeabi", ARMV6);
            matcher.put("arm64-v8a", ARM64);
            matcher.put("x86", X86_32);
        }

        static Architecture getValue() {
            String str = Build.CPU_ABI;
            if (TextUtils.isEmpty(str)) {
                Fabric.getLogger().d("Fabric", "Architecture#getValue()::Build.CPU_ABI returned null or empty");
                return UNKNOWN;
            }
            Architecture architecture = (Architecture) matcher.get(str.toLowerCase(Locale.US));
            if (architecture == null) {
                architecture = UNKNOWN;
            }
            return architecture;
        }
    }

    public static String logPriorityToString(int i) {
        switch (i) {
            case 2:
                return "V";
            case 3:
                return "D";
            case 4:
                return "I";
            case 5:
                return Attributes.WEDNESDAY;
            case 6:
                return "E";
            case 7:
                return SmartWaterPhase1.SMART_WATER_BRANDING_ON_VARIANT;
            default:
                return "?";
        }
    }

    public static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences("com.crashlytics.prefs", 0);
    }

    public static String extractFieldFromSystemFile(File file, String str) {
        BufferedReader bufferedReader;
        String str2 = null;
        if (file.exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader(file), 1024);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        String[] split = Pattern.compile("\\s*:\\s*").split(readLine, 2);
                        if (split.length > 1 && split[0].equals(str)) {
                            str2 = split[1];
                            break;
                        }
                    } catch (Exception e) {
                        e = e;
                        try {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Error parsing ");
                            sb.append(file);
                            Fabric.getLogger().e("Fabric", sb.toString(), e);
                            closeOrLog(bufferedReader, "Failed to close system file reader.");
                            return str2;
                        } catch (Throwable th) {
                            th = th;
                        }
                    }
                }
            } catch (Exception e2) {
                e = e2;
                bufferedReader = null;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Error parsing ");
                sb2.append(file);
                Fabric.getLogger().e("Fabric", sb2.toString(), e);
                closeOrLog(bufferedReader, "Failed to close system file reader.");
                return str2;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                closeOrLog(bufferedReader, "Failed to close system file reader.");
                throw th;
            }
            closeOrLog(bufferedReader, "Failed to close system file reader.");
        }
        return str2;
    }

    public static int getCpuArchitectureInt() {
        return Architecture.getValue().ordinal();
    }

    public static synchronized long getTotalRamInBytes() {
        long j;
        synchronized (CommonUtils.class) {
            if (totalRamInBytes == -1) {
                long j2 = 0;
                String extractFieldFromSystemFile = extractFieldFromSystemFile(new File("/proc/meminfo"), "MemTotal");
                if (!TextUtils.isEmpty(extractFieldFromSystemFile)) {
                    String upperCase = extractFieldFromSystemFile.toUpperCase(Locale.US);
                    try {
                        if (upperCase.endsWith("KB")) {
                            j2 = convertMemInfoToBytes(upperCase, "KB", 1024);
                        } else if (upperCase.endsWith("MB")) {
                            j2 = convertMemInfoToBytes(upperCase, "MB", ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES);
                        } else if (upperCase.endsWith("GB")) {
                            j2 = convertMemInfoToBytes(upperCase, "GB", 1073741824);
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Unexpected meminfo format while computing RAM: ");
                            sb.append(upperCase);
                            Fabric.getLogger().d("Fabric", sb.toString());
                        }
                    } catch (NumberFormatException e) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Unexpected meminfo format while computing RAM: ");
                        sb2.append(upperCase);
                        Fabric.getLogger().e("Fabric", sb2.toString(), e);
                    }
                }
                totalRamInBytes = j2;
            }
            j = totalRamInBytes;
        }
        return j;
    }

    static long convertMemInfoToBytes(String str, String str2, int i) {
        return Long.parseLong(str.split(str2)[0].trim()) * ((long) i);
    }

    public static RunningAppProcessInfo getAppProcessInfo(String str, Context context) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(AbstractEvent.ACTIVITY)).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    return runningAppProcessInfo;
                }
            }
        }
        return null;
    }

    public static String streamToString(InputStream inputStream) throws IOException {
        Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "";
    }

    public static String sha1(String str) {
        return hash(str, "SHA-1");
    }

    public static String sha256(String str) {
        return hash(str, "SHA-256");
    }

    public static String sha1(InputStream inputStream) {
        return hash(inputStream, "SHA-1");
    }

    private static String hash(String str, String str2) {
        return hash(str.getBytes(), str2);
    }

    private static String hash(InputStream inputStream, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return hexify(instance.digest());
                }
                instance.update(bArr, 0, read);
            }
        } catch (Exception e) {
            Fabric.getLogger().e("Fabric", "Could not calculate hash for app icon.", e);
            return "";
        }
    }

    private static String hash(byte[] bArr, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return hexify(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not create hashing algorithm: ");
            sb.append(str);
            sb.append(", returning empty string.");
            Fabric.getLogger().e("Fabric", sb.toString(), e);
            return "";
        }
    }

    public static String createInstanceIdFrom(String... strArr) {
        String str = null;
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str2 : strArr) {
            if (str2 != null) {
                arrayList.add(str2.replace("-", "").toLowerCase(Locale.US));
            }
        }
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        for (String append : arrayList) {
            sb.append(append);
        }
        String sb2 = sb.toString();
        if (sb2.length() > 0) {
            str = sha1(sb2);
        }
        return str;
    }

    public static long calculateFreeRamInBytes(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService(AbstractEvent.ACTIVITY)).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static long calculateUsedDiskSpaceInBytes(String str) {
        StatFs statFs = new StatFs(str);
        long blockSize = (long) statFs.getBlockSize();
        return (((long) statFs.getBlockCount()) * blockSize) - (blockSize * ((long) statFs.getAvailableBlocks()));
    }

    public static Float getBatteryLevel(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return null;
        }
        return Float.valueOf(((float) registerReceiver.getIntExtra(Param.LEVEL, -1)) / ((float) registerReceiver.getIntExtra("scale", -1)));
    }

    public static boolean getProximitySensorEnabled(Context context) {
        boolean z = false;
        if (isEmulator(context)) {
            return false;
        }
        if (((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) != null) {
            z = true;
        }
        return z;
    }

    public static void logControlled(Context context, String str) {
        if (isClsTrace(context)) {
            Fabric.getLogger().d("Fabric", str);
        }
    }

    public static void logControlledError(Context context, String str, Throwable th) {
        if (isClsTrace(context)) {
            Fabric.getLogger().e("Fabric", str);
        }
    }

    public static void logControlled(Context context, int i, String str, String str2) {
        if (isClsTrace(context)) {
            Fabric.getLogger().log(i, "Fabric", str2);
        }
    }

    public static boolean isClsTrace(Context context) {
        if (clsTrace == null) {
            clsTrace = Boolean.valueOf(getBooleanResourceValue(context, "com.crashlytics.Trace", false));
        }
        return clsTrace.booleanValue();
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

    public static int getResourcesIdentifier(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, getResourcePackageName(context));
    }

    public static boolean isEmulator(Context context) {
        return ServerProtocol.DIALOG_PARAM_SDK_VERSION.equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT) || Secure.getString(context.getContentResolver(), "android_id") == null;
    }

    public static boolean isRooted(Context context) {
        boolean isEmulator = isEmulator(context);
        String str = Build.TAGS;
        if ((!isEmulator && str != null && str.contains("test-keys")) || new File("/system/app/Superuser.apk").exists()) {
            return true;
        }
        File file = new File("/system/xbin/su");
        if (isEmulator || !file.exists()) {
            return false;
        }
        return true;
    }

    public static boolean isDebuggerAttached() {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger();
    }

    public static int getDeviceState(Context context) {
        int i = isEmulator(context) ? 1 : 0;
        if (isRooted(context)) {
            i |= 2;
        }
        return isDebuggerAttached() ? i | 4 : i;
    }

    public static int getBatteryVelocity(Context context, boolean z) {
        Float batteryLevel = getBatteryLevel(context);
        if (!z || batteryLevel == null) {
            return 1;
        }
        if (((double) batteryLevel.floatValue()) >= 99.0d) {
            return 3;
        }
        return ((double) batteryLevel.floatValue()) < 99.0d ? 2 : 0;
    }

    public static String hexify(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i] & 255;
            int i2 = i * 2;
            char[] cArr2 = HEX_VALUES;
            cArr[i2] = cArr2[b >>> 4];
            cArr[i2 + 1] = cArr2[b & Ascii.SI];
        }
        return new String(cArr);
    }

    public static boolean isAppDebuggable(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static String getStringsFileValue(Context context, String str) {
        int resourcesIdentifier = getResourcesIdentifier(context, str, "string");
        return resourcesIdentifier > 0 ? context.getString(resourcesIdentifier) : "";
    }

    public static void closeOrLog(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Fabric.getLogger().e("Fabric", str, e);
            }
        }
    }

    public static void flushOrLog(Flushable flushable, String str) {
        if (flushable != null) {
            try {
                flushable.flush();
            } catch (IOException e) {
                Fabric.getLogger().e("Fabric", str, e);
            }
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String padWithZerosToMaxIntWidth(int i) {
        if (i >= 0) {
            return String.format(Locale.US, "%1$10s", new Object[]{Integer.valueOf(i)}).replace(' ', '0');
        }
        throw new IllegalArgumentException("value must be zero or greater");
    }

    public static String getResourcePackageName(Context context) {
        int i = context.getApplicationContext().getApplicationInfo().icon;
        if (i > 0) {
            return context.getResources().getResourcePackageName(i);
        }
        return context.getPackageName();
    }

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

    public static String getAppIconHashOrNull(Context context) {
        InputStream inputStream;
        String str = null;
        try {
            inputStream = context.getResources().openRawResource(getAppIconResourceId(context));
            try {
                String sha1 = sha1(inputStream);
                if (!isNullOrEmpty(sha1)) {
                    str = sha1;
                }
                closeOrLog(inputStream, "Failed to close icon input stream.");
                return str;
            } catch (Exception e) {
                e = e;
                try {
                    Fabric.getLogger().e("Fabric", "Could not calculate hash for app icon.", e);
                    closeOrLog(inputStream, "Failed to close icon input stream.");
                    return null;
                } catch (Throwable th) {
                    th = th;
                    closeOrLog(inputStream, "Failed to close icon input stream.");
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            inputStream = null;
            Fabric.getLogger().e("Fabric", "Could not calculate hash for app icon.", e);
            closeOrLog(inputStream, "Failed to close icon input stream.");
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            closeOrLog(inputStream, "Failed to close icon input stream.");
            throw th;
        }
    }

    public static int getAppIconResourceId(Context context) {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    public static String resolveBuildId(Context context) {
        int resourcesIdentifier = getResourcesIdentifier(context, "io.fabric.android.build_id", "string");
        if (resourcesIdentifier == 0) {
            resourcesIdentifier = getResourcesIdentifier(context, "com.crashlytics.android.build_id", "string");
        }
        if (resourcesIdentifier == 0) {
            return null;
        }
        String string = context.getResources().getString(resourcesIdentifier);
        StringBuilder sb = new StringBuilder();
        sb.append("Build ID is: ");
        sb.append(string);
        Fabric.getLogger().d("Fabric", sb.toString());
        return string;
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

    public static boolean checkPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    @SuppressLint({"MissingPermission"})
    public static boolean canTryConnection(Context context) {
        boolean z = true;
        if (!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
            z = false;
        }
        return z;
    }
}
