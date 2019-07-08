package com.amazon.device.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.event.EventType;
import com.google.android.exoplayer2.C;
import com.mopub.common.Constants;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.StringTokenizer;
import org.json.JSONException;
import org.json.JSONObject;

class DtbCommonUtils {

    public static class APIVersion {
        public int majorVersion = 0;
        public int minorVersion = 0;
    }

    public static String getSDKVersion() {
        return "aps-android-7.4.3";
    }

    private DtbCommonUtils() {
    }

    public static boolean launchActivityForIntentLink(String str, Context context) {
        if (str == null || str.equals("")) {
            str = "about:blank";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Launch Intent: ");
        sb.append(str);
        DtbLog.debug(sb.toString());
        Intent intent = new Intent();
        if (str.startsWith("intent:")) {
            try {
                intent = Intent.parseUri(str, 1);
            } catch (URISyntaxException unused) {
                return false;
            }
        } else {
            intent.setData(Uri.parse(str));
        }
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException unused2) {
            String action = intent.getAction();
            if (action != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Could not handle ");
                sb2.append(action.startsWith("market://") ? "market" : Constants.INTENT_SCHEME);
                sb2.append(" action: ");
                sb2.append(action);
                DtbLog.warn(sb2.toString());
            } else {
                DtbLog.warn("Could not handle nil action ");
            }
            return false;
        }
    }

    public static boolean isClassAvailable(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static String getURLEncodedString(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "UTF-8").replace("+", "%20").replace(EventType.ANY, "%2A").replace("%7E", "~");
        } catch (UnsupportedEncodingException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to url encode :");
            sb.append(str);
            DtbLog.debugError(sb.toString());
            return str;
        }
    }

    public static boolean isOnMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static int parseInt(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Exception parsing the integer from string:");
            sb.append(str);
            DtbLog.debugError(sb.toString());
            return i;
        }
    }

    static Bundle getApplicationBundle() throws NameNotFoundException {
        if (AdRegistration.getContext() != null) {
            return AdRegistration.getContext().getPackageManager().getApplicationInfo(AdRegistration.getContext().getPackageName(), 128).metaData;
        }
        throw new IllegalStateException("Application Context can't be null");
    }

    public static InputStream getResourceAsStream(String str) {
        return DtbCommonUtils.class.getResourceAsStream(str);
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.equals("");
    }

    public static boolean isNullOrWhiteSpace(String str) {
        return isNullOrEmpty(str) || str.trim().equals("");
    }

    public static long getMilliSeconds(String str) {
        return Long.parseLong(str) * 1000;
    }

    @TargetApi(17)
    public static boolean isActivityDestroyed(Activity activity) {
        return VERSION.SDK_INT > 16 && activity.isDestroyed();
    }

    public static String getParamsAsJsonString(Map<String, Object> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            jSONObject.put(str, map.get(str));
        }
        return jSONObject.toString();
    }

    public static String exceptionStackToString(Exception exc) {
        String str;
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        String localizedMessage = exc.getLocalizedMessage();
        if (localizedMessage != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("\nDetails:");
            sb.append(localizedMessage);
            str = sb.toString();
        } else {
            str = "\nDetails:";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(stringWriter.toString());
        return sb2.toString();
    }

    public static String exceptionToString(Exception exc) {
        String localizedMessage = exc.getLocalizedMessage();
        if (localizedMessage == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(localizedMessage);
        sb.append(")");
        return sb.toString();
    }

    @Nullable
    public static String getStringFieldValue(String str, String str2) {
        String str3 = null;
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            if (declaredField.isAccessible()) {
                str3 = (String) declaredField.get(null);
                return str3;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Field:");
            sb.append(str2);
            sb.append(" is not accessable");
            DtbLog.debug(sb.toString());
            return null;
        } catch (NoSuchFieldException unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Field:");
            sb2.append(str2);
            sb2.append(" does not exist");
            DtbLog.debug(sb2.toString());
        } catch (SecurityException unused2) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Field:");
            sb3.append(str2);
            sb3.append(" is not accessable");
            DtbLog.debug(sb3.toString());
        } catch (IllegalArgumentException e) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Illegal Argument exception:");
            sb4.append(e.getMessage());
            DtbLog.debug(sb4.toString());
        } catch (IllegalAccessException e2) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Illegal Access exception:");
            sb5.append(e2.getMessage());
            DtbLog.debug(sb5.toString());
        } catch (ClassNotFoundException unused3) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append("Class notFound:");
            sb6.append(str);
            DtbLog.debug(sb6.toString());
        }
    }

    @Nullable
    public static Integer getIntegerFieldValue(String str, String str2) {
        Integer num = null;
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            if (declaredField.isAccessible()) {
                num = (Integer) declaredField.get(null);
                return num;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Field:");
            sb.append(str2);
            sb.append(" is not accessable");
            DtbLog.debug(sb.toString());
            return null;
        } catch (NoSuchFieldException unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Field:");
            sb2.append(str2);
            sb2.append(" does not exist");
            DtbLog.debug(sb2.toString());
        } catch (SecurityException unused2) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Field:");
            sb3.append(str2);
            sb3.append(" is not accessable");
            DtbLog.debug(sb3.toString());
        } catch (IllegalArgumentException e) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Illegal Argument exception:");
            sb4.append(e.getMessage());
            DtbLog.debug(sb4.toString());
        } catch (IllegalAccessException e2) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Illegal Access exception:");
            sb5.append(e2.getMessage());
            DtbLog.debug(sb5.toString());
        } catch (ClassNotFoundException unused3) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append("Class notFound:");
            sb6.append(str);
            DtbLog.debug(sb6.toString());
            return null;
        }
    }

    @NonNull
    public static APIVersion getAPIVersion(String str) {
        APIVersion aPIVersion = new APIVersion();
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
            String str2 = "0";
            String nextToken = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : str;
            if (stringTokenizer.hasMoreTokens()) {
                str2 = stringTokenizer.nextToken();
            }
            try {
                aPIVersion.majorVersion = Integer.parseInt(nextToken);
                aPIVersion.minorVersion = Integer.parseInt(str2);
            } catch (NumberFormatException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid API version:");
                sb.append(str);
                DtbLog.warn(sb.toString());
            }
        }
        return aPIVersion;
    }
}
