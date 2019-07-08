package com.inmobi.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.HandlerThread;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.e.f;
import com.inmobi.commons.core.utilities.b.g;
import com.inmobi.commons.core.utilities.e;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

/* compiled from: LocationInfo */
public class m implements LocationListener {
    static boolean a = false;
    /* access modifiers changed from: private */
    public static final String e = "m";
    private static m f;
    private static final Object g = new Object();
    /* access modifiers changed from: private */
    public static boolean h = false;
    LocationManager b;
    HandlerThread c = new HandlerThread("LThread");
    GoogleApiClient d;

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public static m a() {
        m mVar = f;
        if (mVar == null) {
            synchronized (g) {
                mVar = f;
                if (mVar == null) {
                    mVar = new m();
                    f = mVar;
                }
            }
        }
        return mVar;
    }

    private m() {
        this.c.start();
        this.b = (LocationManager) a.b().getSystemService("location");
    }

    static boolean b() {
        try {
            GoogleApiClient.class.getName();
            FusedLocationProviderClient.class.getName();
            LocationServices.class.getName();
            return false;
        } catch (NoClassDefFoundError unused) {
            return true;
        }
    }

    static boolean c() {
        try {
            if (e.a(a.b(), "signals", "android.permission.ACCESS_FINE_LOCATION") || e.a(a.b(), "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            try {
                StringBuilder sb = new StringBuilder("location changed. ts:");
                sb.append(location.getTime());
                sb.append(" lat:");
                sb.append(location.getLatitude());
                sb.append(":");
                sb.append(location.getLongitude());
                sb.append(" accu:");
                sb.append(location.getAccuracy());
            } catch (Exception e2) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                return;
            }
        }
        if (c()) {
            this.b.removeUpdates(this);
        }
    }

    public final synchronized HashMap<String, Object> d() {
        return a(i(), true);
    }

    public final HashMap<String, String> e() {
        HashMap<String, String> hashMap = new HashMap<>();
        String str = "loc-consent-status";
        String str2 = c() ? g() ? "AUTHORISED" : "DENIED" : "DENIED";
        hashMap.put(str, str2.toLowerCase(Locale.ENGLISH));
        return hashMap;
    }

    public final synchronized HashMap<String, String> f() {
        HashMap<String, String> hashMap;
        HashMap hashMap2;
        hashMap = new HashMap<>();
        Location i = i();
        if (i != null) {
            hashMap2 = a(i, true);
        } else {
            hashMap2 = a(g.c(), false);
        }
        for (Entry entry : hashMap2.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().toString());
        }
        return hashMap;
    }

    public static void a(boolean z) {
        a = z;
    }

    /* access modifiers changed from: 0000 */
    @SuppressLint({"newApi"})
    @TargetApi(19)
    public final boolean g() {
        boolean z;
        boolean z2;
        int i;
        Context b2 = a.b();
        if (b2 == null) {
            return false;
        }
        if (VERSION.SDK_INT >= 28) {
            LocationManager locationManager = this.b;
            if (locationManager != null) {
                return locationManager.isLocationEnabled();
            }
            return false;
        } else if (VERSION.SDK_INT < 19 || VERSION.SDK_INT >= 28) {
            if (this.b != null) {
                if (e.a(b2, "signals", "android.permission.ACCESS_FINE_LOCATION")) {
                    z = this.b.isProviderEnabled("gps");
                    z2 = false;
                } else if (e.a(b2, "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
                    z2 = this.b.isProviderEnabled("network");
                    z = false;
                } else {
                    z2 = false;
                    z = false;
                }
                if (z2 || z) {
                    return true;
                }
            }
            return false;
        } else {
            try {
                i = Secure.getInt(b2.getContentResolver(), "location_mode");
            } catch (SettingNotFoundException unused) {
                i = 0;
            }
            if (i != 0) {
                return true;
            }
            return false;
        }
    }

    private Location i() {
        Location location;
        Location location2;
        Location location3 = null;
        try {
            if (!a || !g() || !c()) {
                location = null;
                return a(location, location3);
            }
            location = h ? k() : null;
            try {
                if (this.b != null) {
                    location3 = j();
                }
            } catch (Exception e2) {
                Exception exc = e2;
                location2 = location;
                e = exc;
                new StringBuilder("SDK encountered unexpected error in getting a location fix; ").append(e.getMessage());
                location = location2;
                return a(location, location3);
            }
            return a(location, location3);
        } catch (Exception e3) {
            e = e3;
            location2 = null;
            new StringBuilder("SDK encountered unexpected error in getting a location fix; ").append(e.getMessage());
            location = location2;
            return a(location, location3);
        }
    }

    private static Location a(Location location, Location location2) {
        if (location == null && location2 == null) {
            try {
                b.a().a(new f("signals", "LocationFixFailed"));
            } catch (Exception e2) {
                StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                sb.append(e2.getMessage());
                sb.append(")");
            }
            return null;
        } else if (location == null) {
            StringBuilder sb2 = new StringBuilder("Location info provided by Android Api client:");
            sb2.append(location2);
            sb2.append(" ts : ");
            sb2.append(location2.getTime());
            return location2;
        } else if (location2 == null) {
            StringBuilder sb3 = new StringBuilder("Location info provided by Google Api client:");
            sb3.append(location);
            sb3.append(" ts : ");
            sb3.append(location.getTime());
            return location;
        } else {
            long time = location.getTime() - location2.getTime();
            boolean z = true;
            boolean z2 = time > 120000;
            boolean z3 = time < -120000;
            boolean z4 = time > 0;
            if (z2) {
                StringBuilder sb4 = new StringBuilder("Location info provided by Google Api client:");
                sb4.append(location);
                sb4.append(" ts : ");
                sb4.append(location.getTime());
                return location;
            } else if (z3) {
                StringBuilder sb5 = new StringBuilder("Location info provided by Android Api client:");
                sb5.append(location2);
                sb5.append(" ts : ");
                sb5.append(location2.getTime());
                return location2;
            } else {
                int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
                boolean z5 = accuracy > 0;
                boolean z6 = accuracy < 0;
                if (accuracy <= 200) {
                    z = false;
                }
                if (z6 || (z4 && (!z5 || !z))) {
                    StringBuilder sb6 = new StringBuilder("Location info provided by Google Api client:");
                    sb6.append(location);
                    sb6.append(" ts : ");
                    sb6.append(location.getTime());
                    return location;
                }
                StringBuilder sb7 = new StringBuilder("Location info provided by Android Api client:");
                sb7.append(location2);
                sb7.append(" ts : ");
                sb7.append(location2.getTime());
                return location2;
            }
        }
    }

    private Location j() {
        Criteria criteria = new Criteria();
        if (e.a(a.b(), "signals", "android.permission.ACCESS_FINE_LOCATION")) {
            criteria.setAccuracy(1);
        } else if (e.a(a.b(), "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
            criteria.setAccuracy(2);
        }
        boolean z = false;
        criteria.setCostAllowed(false);
        String bestProvider = this.b.getBestProvider(criteria, true);
        Location location = null;
        if (bestProvider != null) {
            try {
                location = this.b.getLastKnownLocation(bestProvider);
            } catch (Exception e2) {
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", "SecurityException");
                    StringBuilder sb = new StringBuilder();
                    sb.append(e2.getMessage());
                    hashMap.put("message", sb.toString());
                    b.a();
                    b.a("signals", "ExceptionCaught", hashMap);
                } catch (Exception e3) {
                    StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                    sb2.append(e3.getMessage());
                    sb2.append(")");
                }
            }
            if (location == null) {
                location = l();
            }
        }
        StringBuilder sb3 = new StringBuilder("Location info provided by Location manager:");
        if (location != null) {
            z = true;
        }
        sb3.append(z);
        return location;
    }

    private static Location k() {
        try {
            return (Location) LocationServices.getFusedLocationProviderClient(a.b()).getLastLocation().getResult();
        } catch (Exception unused) {
            return null;
        }
    }

    private Location l() {
        LocationManager locationManager = this.b;
        Location location = null;
        if (locationManager != null) {
            List providers = locationManager.getProviders(true);
            for (int size = providers.size() - 1; size >= 0; size--) {
                String str = (String) providers.get(size);
                if (this.b.isProviderEnabled(str)) {
                    try {
                        location = this.b.getLastKnownLocation(str);
                    } catch (SecurityException e2) {
                        try {
                            HashMap hashMap = new HashMap();
                            hashMap.put("type", "SecurityException");
                            StringBuilder sb = new StringBuilder();
                            sb.append(e2.getMessage());
                            hashMap.put("message", sb.toString());
                            b.a();
                            b.a("signals", "ExceptionCaught", hashMap);
                        } catch (Exception e3) {
                            StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                            sb2.append(e3.getMessage());
                            sb2.append(")");
                        }
                    }
                    if (location != null) {
                        break;
                    }
                }
            }
        }
        return location;
    }

    private HashMap<String, Object> a(Location location, boolean z) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Context b2 = a.b();
        if (b2 == null) {
            return hashMap;
        }
        if (location != null) {
            if (location.getTime() > 0) {
                hashMap.put("u-ll-ts", Long.valueOf(location.getTime()));
            }
            StringBuilder sb = new StringBuilder();
            sb.append(location.getLatitude());
            sb.append(",");
            sb.append(location.getLongitude());
            sb.append(",");
            sb.append((int) location.getAccuracy());
            hashMap.put("u-latlong-accu", sb.toString());
            hashMap.put("sdk-collected", Integer.valueOf(z ? 1 : 0));
        }
        if (a) {
            hashMap.put("loc-allowed", Integer.valueOf(g() ? 1 : 0));
        }
        if (!g() || !c()) {
            hashMap.put("loc-granularity", "none");
        } else {
            if (e.a(b2, "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
                hashMap.put("loc-granularity", "coarse");
            }
            if (e.a(b2, "signals", "android.permission.ACCESS_FINE_LOCATION")) {
                hashMap.put("loc-granularity", "fine");
            }
        }
        return hashMap;
    }
}
