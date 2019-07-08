package com.amazon.device.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Set;

class DtbSharedPreferences {
    public static DtbSharedPreferences dtbSharedPreferencesInstance = null;
    private static boolean isIgnore = false;
    private static SharedPreferences sharedPreferences;

    private static SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = AdRegistration.getContext().getSharedPreferences("com.amazon.device.ads.dtb.preferences", 0);
        }
        return sharedPreferences;
    }

    private DtbSharedPreferences(String str) {
        saveAppKey(str);
    }

    protected DtbSharedPreferences() {
    }

    public static DtbSharedPreferences getInstance(String str) {
        if (AdRegistration.getContext() != null) {
            if (dtbSharedPreferencesInstance == null) {
                dtbSharedPreferencesInstance = new DtbSharedPreferences(str);
            }
            return dtbSharedPreferencesInstance;
        }
        throw new IllegalArgumentException("unable to initialize shared preferences without setting app context");
    }

    public static DtbSharedPreferences getInstance() {
        DtbSharedPreferences dtbSharedPreferences = dtbSharedPreferencesInstance;
        if (dtbSharedPreferences != null) {
            return dtbSharedPreferences;
        }
        throw new IllegalArgumentException("unable to retrieve shared preferences without intialization");
    }

    public String getAppKey() {
        return (String) getPref("mDTB_Android_AppKey", String.class);
    }

    public String getVersionInUse() {
        return (String) getPref("amzn-dtb-version_in_use", String.class);
    }

    public void setVersionInUse(String str) {
        savePref("amzn-dtb-version_in_use", str);
    }

    private void saveAppKey(String str) {
        String uRLEncodedString = DtbCommonUtils.getURLEncodedString(str);
        if (uRLEncodedString != null) {
            savePref("mDTB_Android_AppKey", uRLEncodedString);
        }
    }

    public String getAdId() {
        return (String) getPref("amzn-dtb-ad-id", String.class);
    }

    public void saveAdId(String str) {
        if (str != null) {
            savePref("amzn-dtb-ad-id", str);
        }
    }

    public String getIdfa() {
        return (String) getPref("amzn-dtb-idfa", String.class);
    }

    public void saveIdfa(String str) {
        if (str != null) {
            savePref("amzn-dtb-idfa", str);
        } else {
            savePref("amzn-dtb-idfa", "");
        }
    }

    public Boolean getOptOut() {
        if (!containsPreference("amzn-dtb-oo")) {
            return null;
        }
        return (Boolean) getPref("amzn-dtb-oo", Boolean.class);
    }

    public void saveOptOut(Boolean bool) {
        flushPreference("amzn-dtb-oo");
        if (bool != null) {
            savePref("amzn-dtb-oo", bool);
        }
    }

    public boolean getIsAdIdNew() {
        return ((Boolean) getPref("amzn-dtb-adid-new", Boolean.class)).booleanValue();
    }

    public void saveIsAdIdNew(boolean z) {
        savePref("amzn-dtb-adid-new", Boolean.valueOf(z));
    }

    public boolean getIsAdIdChanged() {
        return ((Boolean) getPref("amzn-dtb-adid-changed", Boolean.class)).booleanValue();
    }

    public void saveIsAdIdChanged(boolean z) {
        savePref("amzn-dtb-adid-changed", Boolean.valueOf(z));
    }

    public boolean isGooglePlayServicesUnavailable() {
        return ((Boolean) getPref("amzn-dtb-is-gps-unavailable", Boolean.class)).booleanValue();
    }

    public void setGooglePlayServicesUnavailable(boolean z) {
        savePref("amzn-dtb-is-gps-unavailable", Boolean.valueOf(z));
    }

    public Long getSisLastCheckIn() {
        return (Long) getPref("amzn-dtb-ad-sis-last-checkin", Long.class);
    }

    public void saveSisLastCheckIn(long j) {
        savePref("amzn-dtb-ad-sis-last-checkin", Long.valueOf(j));
    }

    public Long getConfigLastCheckIn() {
        return (Long) getPref("amzn-dtb-ad-sis-last-checkin", Long.class);
    }

    public void saveConfigLastCheckIn(long j) {
        savePref("amzn-dtb-ad-sis-last-checkin", Long.valueOf(j));
    }

    public void saveAaxHostname(String str) {
        if (!DtbCommonUtils.isNullOrEmpty(str)) {
            savePref("amzn-dtb-ad-aax-hostname", str);
        } else {
            savePref("amzn-dtb-ad-aax-hostname", DtbConstants.AAX_HOSTNAME);
        }
    }

    public String getAaxHostname() {
        String str = (String) getPref("amzn-dtb-ad-aax-hostname", String.class);
        if (!AdRegistration.isTestMode() || !DtbDebugProperties.isInternalDebugMode) {
            return DtbCommonUtils.isNullOrEmpty(str) ? DtbConstants.AAX_HOSTNAME : str;
        }
        if (str == null) {
            str = DtbConstants.AAX_HOSTNAME;
        }
        return DtbDebugProperties.getAaxHostName(str);
    }

    public String getRoute53EnabledCNAME() {
        if (!AdRegistration.isTestMode() || !DtbDebugProperties.isInternalDebugMode) {
            return DtbConstants.AAX_ROUTE53_ENABLED_CNAME;
        }
        return DtbDebugProperties.getRoute53EnabledCNAME(DtbConstants.AAX_ROUTE53_ENABLED_CNAME);
    }

    public long getConfigTtl() {
        long longValue = ((Long) getPref("amzn-dtb-ad-config-ttl", Long.class)).longValue();
        if (longValue < 1 || longValue > 172800000) {
            return 172800000;
        }
        return longValue;
    }

    public void saveConfigTtl(long j) {
        if (j < 1 || j > 172800000) {
            savePref("amzn-dtb-ad-config-ttl", Long.valueOf(172800000));
        } else {
            savePref("amzn-dtb-ad-config-ttl", Long.valueOf(j));
        }
    }

    public boolean saveSisEndpoint(String str) {
        if (!DtbCommonUtils.isNullOrEmpty(str)) {
            String str2 = (String) getPref("amzn-dtb-ad-sis-endpoint", String.class);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("/api3");
            String sb2 = sb.toString();
            if (str2 != null && str2.equals(sb2)) {
                return false;
            }
            savePref("amzn-dtb-ad-sis-endpoint", sb2);
            return true;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(DtbConstants.SIS_END_POINT);
        sb3.append("/api3");
        savePref("amzn-dtb-ad-sis-endpoint", sb3.toString());
        return false;
    }

    public String getSisEndpoint() {
        String str = (String) getPref("amzn-dtb-ad-sis-endpoint", String.class);
        if (!DtbCommonUtils.isNullOrEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(DtbConstants.SIS_END_POINT);
        sb.append("/api3");
        return sb.toString();
    }

    public void saveSisLastPing(long j) {
        savePref("amzn-dtb-ad-sis-last-ping", Long.valueOf(j));
    }

    public long getSisLastPing() {
        return ((Long) getPref("amzn-dtb-ad-sis-last-ping", Long.class)).longValue();
    }

    private static <T> void savePref(String str, T t) {
        if (!isIgnore) {
            SharedPreferences sharedPreferences2 = getSharedPreferences();
            if (sharedPreferences2 != null) {
                Editor edit = sharedPreferences2.edit();
                if (t instanceof Boolean) {
                    edit.putBoolean(str, ((Boolean) t).booleanValue());
                } else if (t instanceof String) {
                    edit.putString(str, (String) t);
                } else if (t instanceof Integer) {
                    edit.putInt(str, ((Integer) t).intValue());
                } else if (t instanceof Float) {
                    edit.putFloat(str, ((Float) t).floatValue());
                } else if (t instanceof Long) {
                    edit.putLong(str, ((Long) t).longValue());
                } else if (t instanceof Set) {
                    edit.putStringSet(str, (Set) t);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Saving of ");
                    sb.append(t.getClass());
                    sb.append(" is not supported.");
                    throw new IllegalArgumentException(sb.toString());
                }
                edit.commit();
            }
        }
    }

    private static <T> T getPref(String str, Class<T> cls) {
        if (isIgnore) {
            return null;
        }
        SharedPreferences sharedPreferences2 = getSharedPreferences();
        if (cls.isAssignableFrom(String.class)) {
            return sharedPreferences2.getString(str, null);
        }
        if (cls.isAssignableFrom(Set.class)) {
            return sharedPreferences2.getStringSet(str, null);
        }
        if (cls.isAssignableFrom(Boolean.class)) {
            return Boolean.valueOf(sharedPreferences2.getBoolean(str, false));
        }
        if (cls.isAssignableFrom(Long.class)) {
            return Long.valueOf(sharedPreferences2.getLong(str, 0));
        }
        if (cls.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(sharedPreferences2.getInt(str, 0));
        }
        if (cls.isAssignableFrom(Float.class)) {
            return Float.valueOf(sharedPreferences2.getFloat(str, BitmapDescriptorFactory.HUE_RED));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cls.getName());
        sb.append(" is not supported");
        throw new IllegalArgumentException(sb.toString());
    }

    private static boolean containsPreference(String str) {
        return getSharedPreferences().contains(str);
    }

    private static void flushPreference(String str) {
        SharedPreferences sharedPreferences2 = getSharedPreferences();
        if (sharedPreferences2.contains(str)) {
            Editor edit = sharedPreferences2.edit();
            edit.remove(str);
            edit.apply();
        }
    }
}
