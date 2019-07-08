package com.amazon.device.ads;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import kotlin.text.Typography;

class DtbDebugProperties {
    private static DtbDebugProperties debugProperties = null;
    private static boolean isInitialized = false;
    static boolean isInternalDebugMode = false;
    private static Set<String> preDefinedKeys;
    private static HashMap<String, String> propertyMap = new HashMap<>();

    DtbDebugProperties() {
    }

    public static DtbDebugProperties getInstance() {
        if (!isInitialized) {
            DtbLog.debug("Running the debug initialization.");
            debugProperties = new DtbDebugProperties();
            preDefinedKeys = new HashSet();
            preDefinedKeys.add("aaxHostname".toLowerCase());
            preDefinedKeys.add("sisUrl".toLowerCase());
            preDefinedKeys.add("useSecure".toLowerCase());
            preDefinedKeys.add("configHostname".toLowerCase());
            preDefinedKeys.add("internalDebugMode".toLowerCase());
            initializeDtbDebugProperties();
        }
        return debugProperties;
    }

    public boolean isDebugMode() {
        return isInternalDebugMode;
    }

    public static void initializeDtbDebugProperties() {
        InputStream inputStream;
        try {
            if (AdRegistration.isTestMode()) {
                if (AdRegistration.getContext() == null) {
                    DtbLog.debugError("unable to initialize debug preferences without setting app context");
                    throw new IllegalArgumentException("unable to initialize debug preferences without setting app context");
                }
            }
            String string = DtbCommonUtils.getApplicationBundle().getString("com.amazon.device.ads.dtb.debug.override");
            if (!DtbCommonUtils.isNullOrEmpty(string)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Override file: ");
                sb.append(string);
                DtbLog.debug(sb.toString());
                InputStream resourceAsStream = DtbCommonUtils.getResourceAsStream(string);
                if (resourceAsStream == null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Failed to read override from classpath, trying to read override file from absolute location: ");
                    sb2.append(string);
                    DtbLog.debug(sb2.toString());
                    File file = new File(string);
                    if (!file.exists()) {
                        DtbLog.debug("Couldn't find the override file, skipping.");
                        return;
                    }
                    inputStream = new FileInputStream(file);
                } else {
                    inputStream = resourceAsStream;
                }
                DtbLog.debug("Reading debug params..");
                Properties properties = new Properties();
                properties.load(inputStream);
                for (Entry entry : properties.entrySet()) {
                    String str = (String) entry.getKey();
                    if (DtbCommonUtils.isNullOrWhiteSpace(str)) {
                        DtbLog.debug("Error: The debug property name must not be null or empty string");
                    } else {
                        String str2 = (String) entry.getValue();
                        if (str2 != null) {
                            str2 = str2.trim();
                        }
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Overrides found: ");
                        sb3.append(str);
                        sb3.append(" --> ");
                        sb3.append(str2);
                        DtbLog.debug(sb3.toString());
                        if (str.equalsIgnoreCase("internalDebugMode")) {
                            isInternalDebugMode = str2.equalsIgnoreCase("true");
                        }
                        propertyMap.put(str, str2);
                    }
                }
                inputStream.close();
            }
        } catch (Exception e) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Error:: Failed to read the debug params. ignoring.");
            sb4.append(e.getStackTrace());
            DtbLog.debug(sb4.toString());
        } catch (Throwable th) {
            inputStream.close();
            throw th;
        }
        isInitialized = true;
    }

    public static String getAaxHostName(String str) {
        return getDebugProperty("aaxHostname", str);
    }

    public static String getRoute53EnabledCNAME(String str) {
        return getDebugProperty("route53EnabledAAXCname", str);
    }

    public static String getSISUrl(String str) {
        return getDebugProperty("sisUrl", str);
    }

    public static boolean getIsSecure(boolean z) {
        String debugProperty = getDebugProperty("useSecure", "");
        if (debugProperty.equals("true")) {
            return true;
        }
        if (debugProperty.equals("false")) {
            return false;
        }
        return z;
    }

    public static String getConfigHostName(String str) {
        return getDebugProperty("configHostname", str);
    }

    private static String getDebugProperty(String str, String str2) {
        if (!AdRegistration.isTestMode() || !isInternalDebugMode) {
            return str2;
        }
        HashMap<String, String> hashMap = propertyMap;
        return (hashMap == null || hashMap.get(str) == null) ? str2 : (String) propertyMap.get(str);
    }

    public static String getEncodedUrlParams() {
        if (!isInternalDebugMode) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Entry entry : propertyMap.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            try {
                if (!preDefinedKeys.contains(str.toLowerCase())) {
                    sb.append(Typography.amp);
                    sb.append(str);
                    sb.append('=');
                    sb.append(URLEncoder.encode(str2, "UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                DtbLog.debugError(String.format("Cannot encode %d=%d due to exception %d", new Object[]{str, str2, e.getMessage()}));
            }
        }
        return sb.toString();
    }
}
