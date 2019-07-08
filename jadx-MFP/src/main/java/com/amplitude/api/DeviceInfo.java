package com.amplitude.api;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.mopub.common.GpsHelper;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class DeviceInfo {
    private CachedInfo cachedInfo;
    /* access modifiers changed from: private */
    public Context context;
    private boolean locationListening = true;

    private class CachedInfo {
        /* access modifiers changed from: private */
        public String advertisingId;
        /* access modifiers changed from: private */
        public String brand;
        /* access modifiers changed from: private */
        public String carrier;
        /* access modifiers changed from: private */
        public String country;
        /* access modifiers changed from: private */
        public boolean gpsEnabled;
        /* access modifiers changed from: private */
        public String language;
        /* access modifiers changed from: private */
        public boolean limitAdTrackingEnabled;
        /* access modifiers changed from: private */
        public String manufacturer;
        /* access modifiers changed from: private */
        public String model;
        /* access modifiers changed from: private */
        public String osName;
        /* access modifiers changed from: private */
        public String osVersion;
        /* access modifiers changed from: private */
        public String versionName;

        private String getOsName() {
            return "android";
        }

        private CachedInfo() {
            this.advertisingId = getAdvertisingId();
            this.versionName = getVersionName();
            this.osName = getOsName();
            this.osVersion = getOsVersion();
            this.brand = getBrand();
            this.manufacturer = getManufacturer();
            this.model = getModel();
            this.carrier = getCarrier();
            this.country = getCountry();
            this.language = getLanguage();
            this.gpsEnabled = checkGPSEnabled();
        }

        private String getVersionName() {
            try {
                return DeviceInfo.this.context.getPackageManager().getPackageInfo(DeviceInfo.this.context.getPackageName(), 0).versionName;
            } catch (NameNotFoundException unused) {
                return null;
            }
        }

        private String getOsVersion() {
            return VERSION.RELEASE;
        }

        private String getBrand() {
            return Build.BRAND;
        }

        private String getManufacturer() {
            return Build.MANUFACTURER;
        }

        private String getModel() {
            return Build.MODEL;
        }

        private String getCarrier() {
            try {
                return ((TelephonyManager) DeviceInfo.this.context.getSystemService("phone")).getNetworkOperatorName();
            } catch (Exception unused) {
                return null;
            }
        }

        private String getCountry() {
            String countryFromLocation = getCountryFromLocation();
            if (!Utils.isEmptyString(countryFromLocation)) {
                return countryFromLocation;
            }
            String countryFromNetwork = getCountryFromNetwork();
            if (!Utils.isEmptyString(countryFromNetwork)) {
                return countryFromNetwork;
            }
            return getCountryFromLocale();
        }

        private String getCountryFromLocation() {
            if (!DeviceInfo.this.isLocationListening()) {
                return null;
            }
            Location mostRecentLocation = DeviceInfo.this.getMostRecentLocation();
            if (mostRecentLocation != null) {
                try {
                    if (Geocoder.isPresent()) {
                        List<Address> fromLocation = DeviceInfo.this.getGeocoder().getFromLocation(mostRecentLocation.getLatitude(), mostRecentLocation.getLongitude(), 1);
                        if (fromLocation != null) {
                            for (Address address : fromLocation) {
                                if (address != null) {
                                    return address.getCountryCode();
                                }
                            }
                        }
                    }
                } catch (IOException | IllegalArgumentException | IllegalStateException | NoSuchMethodError | NullPointerException unused) {
                }
            }
            return null;
        }

        private String getCountryFromNetwork() {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) DeviceInfo.this.context.getSystemService("phone");
                if (telephonyManager.getPhoneType() != 2) {
                    String networkCountryIso = telephonyManager.getNetworkCountryIso();
                    if (networkCountryIso != null) {
                        return networkCountryIso.toUpperCase(Locale.US);
                    }
                }
            } catch (Exception unused) {
            }
            return null;
        }

        private String getCountryFromLocale() {
            return Locale.getDefault().getCountry();
        }

        private String getLanguage() {
            return Locale.getDefault().getLanguage();
        }

        private String getAdvertisingId() {
            if ("Amazon".equals(getManufacturer())) {
                return getAndCacheAmazonAdvertisingId();
            }
            return getAndCacheGoogleAdvertisingId();
        }

        private String getAndCacheAmazonAdvertisingId() {
            ContentResolver contentResolver = DeviceInfo.this.context.getContentResolver();
            boolean z = false;
            if (Secure.getInt(contentResolver, Events.LIMIT_AD_TRACKING, 0) == 1) {
                z = true;
            }
            this.limitAdTrackingEnabled = z;
            this.advertisingId = Secure.getString(contentResolver, Attributes.ADVERTISING_ID);
            return this.advertisingId;
        }

        private String getAndCacheGoogleAdvertisingId() {
            try {
                boolean z = true;
                Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{DeviceInfo.this.context});
                Boolean bool = (Boolean) invoke.getClass().getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(invoke, new Object[0]);
                if (bool == null || !bool.booleanValue()) {
                    z = false;
                }
                this.limitAdTrackingEnabled = z;
                this.advertisingId = (String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
            } catch (ClassNotFoundException unused) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services SDK not found!");
            } catch (InvocationTargetException unused2) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services not available");
            } catch (Exception e) {
                AmplitudeLog.getLogger().e("com.amplitude.api.DeviceInfo", "Encountered an error connecting to Google Play Services", e);
            }
            return this.advertisingId;
        }

        private boolean checkGPSEnabled() {
            boolean z = false;
            try {
                Integer num = (Integer) Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(null, new Object[]{DeviceInfo.this.context});
                if (num != null && num.intValue() == 0) {
                    z = true;
                }
                return z;
            } catch (NoClassDefFoundError unused) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services Util not found!");
                return false;
            } catch (ClassNotFoundException unused2) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services Util not found!");
                return false;
            } catch (NoSuchMethodException unused3) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services not available");
                return false;
            } catch (InvocationTargetException unused4) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services not available");
                return false;
            } catch (IllegalAccessException unused5) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services not available");
                return false;
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error when checking for Google Play Services: ");
                sb.append(e);
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", sb.toString());
                return false;
            }
        }
    }

    public DeviceInfo(Context context2) {
        this.context = context2;
    }

    private CachedInfo getCachedInfo() {
        if (this.cachedInfo == null) {
            this.cachedInfo = new CachedInfo();
        }
        return this.cachedInfo;
    }

    public void prefetch() {
        getCachedInfo();
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public String getVersionName() {
        return getCachedInfo().versionName;
    }

    public String getOsName() {
        return getCachedInfo().osName;
    }

    public String getOsVersion() {
        return getCachedInfo().osVersion;
    }

    public String getBrand() {
        return getCachedInfo().brand;
    }

    public String getManufacturer() {
        return getCachedInfo().manufacturer;
    }

    public String getModel() {
        return getCachedInfo().model;
    }

    public String getCarrier() {
        return getCachedInfo().carrier;
    }

    public String getCountry() {
        return getCachedInfo().country;
    }

    public String getLanguage() {
        return getCachedInfo().language;
    }

    public String getAdvertisingId() {
        return getCachedInfo().advertisingId;
    }

    public boolean isLimitAdTrackingEnabled() {
        return getCachedInfo().limitAdTrackingEnabled;
    }

    public boolean isGooglePlayServicesEnabled() {
        return getCachedInfo().gpsEnabled;
    }

    public Location getMostRecentLocation() {
        List<String> list;
        Object obj;
        Location location = null;
        if (!isLocationListening()) {
            return null;
        }
        LocationManager locationManager = (LocationManager) this.context.getSystemService("location");
        if (locationManager == null) {
            return null;
        }
        try {
            list = locationManager.getProviders(true);
        } catch (SecurityException unused) {
            list = null;
        }
        if (list == null) {
            return null;
        }
        ArrayList<Location> arrayList = new ArrayList<>();
        for (String lastKnownLocation : list) {
            try {
                obj = locationManager.getLastKnownLocation(lastKnownLocation);
            } catch (IllegalArgumentException | SecurityException unused2) {
                obj = null;
            }
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        long j = -1;
        for (Location location2 : arrayList) {
            if (location2.getTime() > j) {
                location = location2;
                j = location2.getTime();
            }
        }
        return location;
    }

    public boolean isLocationListening() {
        return this.locationListening;
    }

    /* access modifiers changed from: protected */
    public Geocoder getGeocoder() {
        return new Geocoder(this.context, Locale.ENGLISH);
    }
}
