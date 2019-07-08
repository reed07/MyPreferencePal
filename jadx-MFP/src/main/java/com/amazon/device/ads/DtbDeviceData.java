package com.amazon.device.ads;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

final class DtbDeviceData {
    private static final String LOG_TAG = "DtbDeviceData";
    private static DtbDeviceData deviceDataInstance;
    private boolean bad_mac = false;
    private boolean bad_serial = false;
    private boolean bad_udid = false;
    private JSONObject deviceInfoJson = new JSONObject();
    private HashMap<String, Object> deviceParams = new HashMap<>();
    private HashMap<String, Object> optionalParams = new HashMap<>();
    private String orientation = null;
    private String screenSize = null;
    private String sha1_mac = null;
    private String sha1_serial = null;
    private String sha1_udid = null;
    private String ua = null;

    public static DtbDeviceData getDeviceDataInstance() {
        if (AdRegistration.getContext() != null) {
            if (deviceDataInstance == null) {
                if (AdRegistration.getContext() != null) {
                    deviceDataInstance = new DtbDeviceData(AdRegistration.getContext());
                } else {
                    DtbLog.debugError("Invalid intialization of Device Data. Context is null");
                    throw new IllegalArgumentException("Invalid intialization of Device Data. Context is null");
                }
            }
            return deviceDataInstance;
        }
        DtbLog.debugError("unable to initialize advertising info without setting app context");
        throw new IllegalArgumentException("unable to initialize advertising info without setting app context");
    }

    public JSONObject getParamsJson() {
        return this.deviceInfoJson;
    }

    public HashMap<String, Object> getDeviceParams() {
        return this.deviceParams;
    }

    public HashMap<String, Object> getOptionalParams() {
        return this.optionalParams;
    }

    private DtbDeviceData(Context context) {
        getMacAddress(context);
        getUdid(context);
        getSerial();
        getScreenSize();
        getOrientation();
        getDeviceInfoJson();
        getUserAgent();
        buildDeviceParams();
        buildOptionalParams();
    }

    private void buildDeviceParams() {
        this.deviceParams.put("dt", "android");
        this.deviceParams.put("app", "app");
        this.deviceParams.put("aud", "3p");
        String str = this.ua;
        if (str != null) {
            this.deviceParams.put("ua", str);
        }
        this.deviceParams.put("sdkVer", DtbCommonUtils.getSDKVersion());
        JSONObject jSONObject = this.deviceInfoJson;
        if (jSONObject != null) {
            this.deviceParams.put("dinfo", jSONObject);
        }
    }

    private void buildOptionalParams() {
        String str = this.sha1_mac;
        if (str != null) {
            this.optionalParams.put("sha1_mac", str);
        }
        String str2 = this.sha1_udid;
        if (str2 != null) {
            this.optionalParams.put("sha1_udid", str2);
        }
        String str3 = this.sha1_serial;
        if (str3 != null) {
            this.optionalParams.put("sha1_serial", str3);
        }
        if (this.bad_mac) {
            this.optionalParams.put("badMac", "true");
        }
        if (this.bad_serial) {
            this.optionalParams.put("badSerial", "true");
        }
        if (this.bad_udid) {
            this.optionalParams.put("badUdid", "true");
        }
    }

    private void getDeviceInfoJson() {
        float f;
        String str = "Android";
        String str2 = Build.MODEL;
        String str3 = Build.MANUFACTURER;
        String str4 = VERSION.RELEASE;
        String country = Locale.getDefault().getCountry();
        String language = Locale.getDefault().getLanguage();
        String connectionType = getConnectionType();
        TelephonyManager telephonyManager = (TelephonyManager) AdRegistration.getContext().getSystemService("phone");
        String networkOperatorName = telephonyManager != null ? telephonyManager.getNetworkOperatorName() : null;
        if (!str3.equals("motorola") || !str2.equals("MB502")) {
            WindowManager windowManager = (WindowManager) AdRegistration.getContext().getSystemService("window");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            f = displayMetrics.scaledDensity;
        } else {
            f = 1.0f;
        }
        String f2 = Float.toString(f);
        try {
            this.deviceInfoJson.put(Http.OS, str);
            this.deviceInfoJson.put("model", str2);
            this.deviceInfoJson.put("make", str3);
            this.deviceInfoJson.put("osVersion", str4);
            this.deviceInfoJson.put("connectionType", connectionType);
            if (country != null) {
                this.deviceInfoJson.put("country", country);
            }
            if (f2 != null) {
                this.deviceInfoJson.put("scalingFactor", f2);
            }
            if (networkOperatorName != null) {
                this.deviceInfoJson.put(Attributes.CARRIER, networkOperatorName);
            }
            if (language != null) {
                this.deviceInfoJson.put("language", language);
            }
            if (this.screenSize != null) {
                this.deviceInfoJson.put("screenSize", this.screenSize);
            }
            if (this.orientation != null) {
                this.deviceInfoJson.put("orientation", this.orientation);
            }
        } catch (JSONException unused) {
            DtbLog.error(LOG_TAG, "JSONException while producing deviceInfoJson");
        }
    }

    private void getUdid(Context context) {
        try {
            String string = Secure.getString(context.getContentResolver(), "android_id");
            if (!(string == null || string.length() == 0)) {
                if (!string.equalsIgnoreCase("9774d56d682e549c")) {
                    this.sha1_udid = DtbCommonUtils.getURLEncodedString(generateSha1Hash(string));
                    return;
                }
            }
            this.sha1_udid = null;
            this.bad_udid = true;
        } catch (NoSuchAlgorithmException unused) {
            this.bad_udid = true;
        }
    }

    private void getMacAddress(Context context) {
        String str;
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            WifiInfo connectionInfo = wifiManager != null ? wifiManager.getConnectionInfo() : null;
            if (connectionInfo == null) {
                str = null;
            } else {
                str = connectionInfo.getMacAddress();
            }
            if (str != null) {
                if (str.length() != 0) {
                    if (!Pattern.compile("((([0-9a-fA-F]){1,2}[-:]){5}([0-9a-fA-F]){1,2})").matcher(str).find()) {
                        this.sha1_mac = null;
                        this.bad_mac = true;
                        return;
                    }
                    this.sha1_mac = DtbCommonUtils.getURLEncodedString(generateSha1Hash(str));
                    return;
                }
            }
            this.sha1_mac = null;
            this.bad_mac = true;
        } catch (NoSuchAlgorithmException unused) {
            this.bad_mac = true;
        } catch (SecurityException e) {
            String str2 = LOG_TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to get WIFI Manager: ");
            sb.append(e.getClass().getSimpleName());
            DtbLog.info(str2, sb.toString());
            this.sha1_mac = null;
        } catch (ExceptionInInitializerError e2) {
            String str3 = LOG_TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to get WIFI Manager: ");
            sb2.append(e2.getClass().getSimpleName());
            DtbLog.info(str3, sb2.toString());
            this.sha1_mac = null;
        }
    }

    private void getSerial() {
        try {
            String str = (String) Build.class.getDeclaredField("SERIAL").get(Build.class);
            if (!(str == null || str.length() == 0)) {
                if (!str.equalsIgnoreCase("unknown")) {
                    this.sha1_serial = DtbCommonUtils.getURLEncodedString(generateSha1Hash(str));
                    return;
                }
            }
            this.bad_serial = true;
        } catch (NoSuchAlgorithmException unused) {
            this.bad_serial = true;
        } catch (Exception unused2) {
        }
    }

    private String generateSha1Hash(String str) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("SHA-1");
        instance.update(str.getBytes());
        byte[] digest = instance.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(Integer.toHexString((b & 255) | 256).substring(1));
        }
        return sb.toString();
    }

    private void getUserAgent() {
        String property = System.getProperty("http.agent");
        if (DtbCommonUtils.isNullOrEmpty(property)) {
            property = "DTBAndroid";
        }
        this.ua = property;
    }

    public String getUserAgentString() {
        return this.ua;
    }

    private String getConnectionType() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) AdRegistration.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "0";
        }
        int type = activeNetworkInfo.getType();
        if (type == 6) {
            return "13";
        }
        switch (type) {
            case 0:
                return Integer.toString(activeNetworkInfo.getSubtype());
            case 1:
                return "Wifi";
            default:
                return "0";
        }
    }

    private void getScreenSize() {
        this.screenSize = DtbDeviceDataRetriever.getScreenSize(new DisplayMetrics());
    }

    private void getOrientation() {
        this.orientation = DtbDeviceDataRetriever.getOrientation(AdRegistration.getContext());
    }
}
