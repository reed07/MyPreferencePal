package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Locale;

public final class zzaua {
    private float zzbwv;
    private int zzdwo;
    private int zzdwp;
    private int zzecp;
    private boolean zzecq;
    private boolean zzecr;
    private String zzecs;
    private String zzect;
    private boolean zzecu;
    private final boolean zzecv;
    private boolean zzecw;
    private boolean zzecx;
    private boolean zzecy;
    private String zzecz;
    private String zzeda;
    private String zzedb;
    private int zzedc;
    private int zzedd;
    private int zzede;
    private int zzedf;
    private int zzedg;
    private int zzedh;
    private double zzedi;
    private boolean zzedj;
    private boolean zzedk;
    private int zzedl;
    private String zzedm;
    private String zzedn;
    private boolean zzedo;

    public zzaua(Context context) {
        PackageManager packageManager = context.getPackageManager();
        zzp(context);
        zzq(context);
        zzr(context);
        Locale locale = Locale.getDefault();
        boolean z = true;
        this.zzecq = zza(packageManager, "geo:0,0?q=donuts") != null;
        if (zza(packageManager, "http://www.google.com") == null) {
            z = false;
        }
        this.zzecr = z;
        this.zzect = locale.getCountry();
        zzwu.zzpv();
        this.zzecu = zzbat.zzaaq();
        this.zzecv = DeviceProperties.isLatchsky(context);
        this.zzecw = DeviceProperties.isSidewinder(context);
        this.zzecz = locale.getLanguage();
        this.zzeda = zza(context, packageManager);
        this.zzedb = zzs(context);
        Resources resources = context.getResources();
        if (resources != null) {
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            if (displayMetrics != null) {
                this.zzbwv = displayMetrics.density;
                this.zzdwo = displayMetrics.widthPixels;
                this.zzdwp = displayMetrics.heightPixels;
            }
        }
    }

    public zzaua(Context context, zzatz zzatz) {
        zzp(context);
        zzq(context);
        zzr(context);
        this.zzedm = Build.FINGERPRINT;
        this.zzedn = Build.DEVICE;
        this.zzedo = PlatformVersion.isAtLeastIceCreamSandwichMR1() && zzabk.zzj(context);
        this.zzecq = zzatz.zzecq;
        this.zzecr = zzatz.zzecr;
        this.zzect = zzatz.zzect;
        this.zzecu = zzatz.zzecu;
        this.zzecv = zzatz.zzecv;
        this.zzecw = zzatz.zzecw;
        this.zzecz = zzatz.zzecz;
        this.zzeda = zzatz.zzeda;
        this.zzedb = zzatz.zzedb;
        this.zzbwv = zzatz.zzbwv;
        this.zzdwo = zzatz.zzdwo;
        this.zzdwp = zzatz.zzdwp;
    }

    private final void zzp(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager != null) {
            try {
                this.zzecp = audioManager.getMode();
                this.zzecx = audioManager.isMusicActive();
                this.zzecy = audioManager.isSpeakerphoneOn();
                this.zzedc = audioManager.getStreamVolume(3);
                this.zzedg = audioManager.getRingerMode();
                this.zzedh = audioManager.getStreamVolume(2);
                return;
            } catch (Throwable th) {
                zzbv.zzlj().zza(th, "DeviceInfo.gatherAudioInfo");
            }
        }
        this.zzecp = -2;
        this.zzecx = false;
        this.zzecy = false;
        this.zzedc = 0;
        this.zzedg = 0;
        this.zzedh = 0;
    }

    @TargetApi(16)
    private final void zzq(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.zzecs = telephonyManager.getNetworkOperator();
        this.zzede = telephonyManager.getNetworkType();
        this.zzedf = telephonyManager.getPhoneType();
        this.zzedd = -2;
        this.zzedk = false;
        this.zzedl = -1;
        zzbv.zzlf();
        if (zzayh.zzn(context, "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                this.zzedd = activeNetworkInfo.getType();
                this.zzedl = activeNetworkInfo.getDetailedState().ordinal();
            } else {
                this.zzedd = -1;
            }
            if (VERSION.SDK_INT >= 16) {
                this.zzedk = connectivityManager.isActiveNetworkMetered();
            }
        }
    }

    private final void zzr(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        boolean z = false;
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("status", -1);
            this.zzedi = (double) (((float) registerReceiver.getIntExtra(Param.LEVEL, -1)) / ((float) registerReceiver.getIntExtra("scale", -1)));
            if (intExtra == 2 || intExtra == 5) {
                z = true;
            }
            this.zzedj = z;
            return;
        }
        this.zzedi = -1.0d;
        this.zzedj = false;
    }

    private static String zzs(Context context) {
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo("com.android.vending", 128);
            if (packageInfo == null) {
                return null;
            }
            int i = packageInfo.versionCode;
            String str = packageInfo.packageName;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
            sb.append(i);
            sb.append(".");
            sb.append(str);
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    private static String zza(Context context, PackageManager packageManager) {
        ResolveInfo zza = zza(packageManager, "market://details?id=com.google.android.gms.ads");
        if (zza == null) {
            return null;
        }
        ActivityInfo activityInfo = zza.activityInfo;
        if (activityInfo == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(activityInfo.packageName, 0);
            if (packageInfo == null) {
                return null;
            }
            int i = packageInfo.versionCode;
            String str = activityInfo.packageName;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
            sb.append(i);
            sb.append(".");
            sb.append(str);
            return sb.toString();
        } catch (NameNotFoundException unused) {
            return null;
        }
    }

    private static ResolveInfo zza(PackageManager packageManager, String str) {
        try {
            return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
        } catch (Throwable th) {
            zzbv.zzlj().zza(th, "DeviceInfo.getResolveInfo");
            return null;
        }
    }

    public final zzatz zzwx() {
        zzatz zzatz = new zzatz(this.zzecp, this.zzecq, this.zzecr, this.zzecs, this.zzect, this.zzecu, this.zzecv, this.zzecw, this.zzecx, this.zzecy, this.zzecz, this.zzeda, this.zzedb, this.zzedc, this.zzedd, this.zzede, this.zzedf, this.zzedg, this.zzedh, this.zzbwv, this.zzdwo, this.zzdwp, this.zzedi, this.zzedj, this.zzedk, this.zzedl, this.zzedm, this.zzedo, this.zzedn);
        return zzatz;
    }
}
