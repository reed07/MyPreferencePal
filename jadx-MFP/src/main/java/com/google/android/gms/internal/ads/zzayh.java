package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.brightcove.player.event.AbstractEvent;
import com.google.android.exoplayer2.C;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.common.net.HttpHeaders;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzayh {
    public static final Handler zzelc = new zzaya(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public String zzeiz;
    /* access modifiers changed from: private */
    public boolean zzeld = true;
    private boolean zzele = false;
    private boolean zzelf = false;
    @GuardedBy("this")
    private Pattern zzelg;
    @GuardedBy("this")
    private Pattern zzelh;

    public final void zza(Context context, String str, boolean z, HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", zzo(context, str));
        httpURLConnection.setUseCaches(false);
    }

    public static boolean zzah(Context context) {
        boolean z;
        Context zzu = zzaum.zzu(context);
        Intent intent = new Intent();
        intent.setClassName(zzu, AdActivity.CLASS_NAME);
        boolean z2 = false;
        try {
            ResolveInfo resolveActivity = zzu.getPackageManager().resolveActivity(intent, 65536);
            if (resolveActivity == null || resolveActivity.activityInfo == null) {
                zzaxz.zzeo("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
            } else {
                String str = "com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".";
                if ((resolveActivity.activityInfo.configChanges & 16) == 0) {
                    zzaxz.zzeo(String.format(str, new Object[]{"keyboard"}));
                    z = false;
                } else {
                    z = true;
                }
                if ((resolveActivity.activityInfo.configChanges & 32) == 0) {
                    zzaxz.zzeo(String.format(str, new Object[]{"keyboardHidden"}));
                    z = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 128) == 0) {
                    zzaxz.zzeo(String.format(str, new Object[]{"orientation"}));
                    z = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 256) == 0) {
                    zzaxz.zzeo(String.format(str, new Object[]{"screenLayout"}));
                    z = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 512) == 0) {
                    zzaxz.zzeo(String.format(str, new Object[]{"uiMode"}));
                    z = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 1024) == 0) {
                    zzaxz.zzeo(String.format(str, new Object[]{"screenSize"}));
                    z = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 2048) == 0) {
                    zzaxz.zzeo(String.format(str, new Object[]{"smallestScreenSize"}));
                } else {
                    z2 = z;
                }
            }
            return z2;
        } catch (Exception e) {
            zzaxz.zzc("Could not verify that com.google.android.gms.ads.AdActivity is declared in AndroidManifest.xml", e);
            zzbv.zzlj().zza(e, "AdUtil.hasAdActivity");
            return false;
        }
    }

    public static boolean zzn(Context context, String str) {
        Context zzu = zzaum.zzu(context);
        return Wrappers.packageManager(zzu).checkPermission(str, zzu.getPackageName()) == 0;
    }

    public static void zza(Context context, String str, List<String> list) {
        for (String zzbah : list) {
            new zzbah(context, str, zzbah).zzwa();
        }
    }

    public static void zzc(Context context, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        zza(context, str, (List<String>) arrayList);
    }

    public final void zza(Context context, List<String> list) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!TextUtils.isEmpty(zzbwi.zzbp(activity))) {
                if (list == null) {
                    zzaxz.v("Cannot ping urls: empty list.");
                } else if (!zzabk.zzj(context)) {
                    zzaxz.v("Cannot ping url because custom tabs is not supported");
                } else {
                    zzabk zzabk = new zzabk();
                    zzabk.zza((zzabl) new zzayk(this, list, zzabk, context));
                    zzabk.zzd(activity);
                }
            }
        }
    }

    public static String zza(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder sb = new StringBuilder(8192);
        char[] cArr = new char[2048];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read == -1) {
                return sb.toString();
            }
            sb.append(cArr, 0, read);
        }
    }

    public final boolean zzai(Context context) {
        if (this.zzele) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver(new zzayo(this, null), intentFilter);
        this.zzele = true;
        return true;
    }

    public final boolean zzaj(Context context) {
        if (this.zzelf) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.ads.intent.DEBUG_LOGGING_ENABLEMENT_CHANGED");
        context.getApplicationContext().registerReceiver(new zzayn(this, null), intentFilter);
        this.zzelf = true;
        return true;
    }

    public final void zza(Context context, String str, WebSettings webSettings) {
        webSettings.setUserAgentString(zzo(context, str));
    }

    private static String zzzr() {
        StringBuilder sb = new StringBuilder(256);
        sb.append("Mozilla/5.0 (Linux; U; Android");
        if (VERSION.RELEASE != null) {
            sb.append(" ");
            sb.append(VERSION.RELEASE);
        }
        sb.append("; ");
        sb.append(Locale.getDefault());
        if (Build.DEVICE != null) {
            sb.append("; ");
            sb.append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                sb.append(" Build/");
                sb.append(Build.DISPLAY);
            }
        }
        sb.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x003b, code lost:
        continue;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x001d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0045 */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0025 A[Catch:{ Exception -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0059 A[Catch:{ Exception -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005e A[Catch:{ Exception -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a7 A[Catch:{ Exception -> 0x00b6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzo(android.content.Context r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            java.lang.String r1 = r4.zzeiz     // Catch:{ all -> 0x00d2 }
            if (r1 == 0) goto L_0x000b
            java.lang.String r5 = r4.zzeiz     // Catch:{ all -> 0x00d2 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d2 }
            return r5
        L_0x000b:
            if (r6 != 0) goto L_0x0013
            java.lang.String r5 = zzzr()     // Catch:{ all -> 0x00d2 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d2 }
            return r5
        L_0x0013:
            com.google.android.gms.internal.ads.zzayp r1 = com.google.android.gms.ads.internal.zzbv.zzlh()     // Catch:{ Exception -> 0x001d }
            java.lang.String r1 = r1.getDefaultUserAgent(r5)     // Catch:{ Exception -> 0x001d }
            r4.zzeiz = r1     // Catch:{ Exception -> 0x001d }
        L_0x001d:
            java.lang.String r1 = r4.zzeiz     // Catch:{ all -> 0x00d2 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x00d2 }
            if (r1 == 0) goto L_0x006e
            com.google.android.gms.internal.ads.zzwu.zzpv()     // Catch:{ all -> 0x00d2 }
            boolean r1 = com.google.android.gms.internal.ads.zzbat.zzaar()     // Catch:{ all -> 0x00d2 }
            if (r1 != 0) goto L_0x0068
            r1 = 0
            r4.zzeiz = r1     // Catch:{ all -> 0x00d2 }
            android.os.Handler r1 = zzelc     // Catch:{ all -> 0x00d2 }
            com.google.android.gms.internal.ads.zzayl r2 = new com.google.android.gms.internal.ads.zzayl     // Catch:{ all -> 0x00d2 }
            r2.<init>(r4, r5)     // Catch:{ all -> 0x00d2 }
            r1.post(r2)     // Catch:{ all -> 0x00d2 }
        L_0x003b:
            java.lang.String r1 = r4.zzeiz     // Catch:{ all -> 0x00d2 }
            if (r1 != 0) goto L_0x006e
            java.lang.Object r1 = r4.mLock     // Catch:{ InterruptedException -> 0x0045 }
            r1.wait()     // Catch:{ InterruptedException -> 0x0045 }
            goto L_0x003b
        L_0x0045:
            java.lang.String r1 = zzzr()     // Catch:{ all -> 0x00d2 }
            r4.zzeiz = r1     // Catch:{ all -> 0x00d2 }
            java.lang.String r1 = "Interrupted, use default user agent: "
            java.lang.String r2 = r4.zzeiz     // Catch:{ all -> 0x00d2 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00d2 }
            int r3 = r2.length()     // Catch:{ all -> 0x00d2 }
            if (r3 == 0) goto L_0x005e
            java.lang.String r1 = r1.concat(r2)     // Catch:{ all -> 0x00d2 }
            goto L_0x0064
        L_0x005e:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x00d2 }
            r2.<init>(r1)     // Catch:{ all -> 0x00d2 }
            r1 = r2
        L_0x0064:
            com.google.android.gms.internal.ads.zzaxz.zzeo(r1)     // Catch:{ all -> 0x00d2 }
            goto L_0x003b
        L_0x0068:
            java.lang.String r1 = zzak(r5)     // Catch:{ all -> 0x00d2 }
            r4.zzeiz = r1     // Catch:{ all -> 0x00d2 }
        L_0x006e:
            java.lang.String r1 = r4.zzeiz     // Catch:{ all -> 0x00d2 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00d2 }
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00d2 }
            int r2 = r2.length()     // Catch:{ all -> 0x00d2 }
            int r2 = r2 + 10
            java.lang.String r3 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x00d2 }
            int r3 = r3.length()     // Catch:{ all -> 0x00d2 }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d2 }
            r3.<init>(r2)     // Catch:{ all -> 0x00d2 }
            r3.append(r1)     // Catch:{ all -> 0x00d2 }
            java.lang.String r1 = " (Mobile; "
            r3.append(r1)     // Catch:{ all -> 0x00d2 }
            r3.append(r6)     // Catch:{ all -> 0x00d2 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x00d2 }
            r4.zzeiz = r6     // Catch:{ all -> 0x00d2 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r5 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r5)     // Catch:{ Exception -> 0x00b6 }
            boolean r5 = r5.isCallerInstantApp()     // Catch:{ Exception -> 0x00b6 }
            if (r5 == 0) goto L_0x00c0
            java.lang.String r5 = r4.zzeiz     // Catch:{ Exception -> 0x00b6 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x00b6 }
            java.lang.String r6 = ";aia"
            java.lang.String r5 = r5.concat(r6)     // Catch:{ Exception -> 0x00b6 }
            r4.zzeiz = r5     // Catch:{ Exception -> 0x00b6 }
            goto L_0x00c0
        L_0x00b6:
            r5 = move-exception
            com.google.android.gms.internal.ads.zzaxk r6 = com.google.android.gms.ads.internal.zzbv.zzlj()     // Catch:{ all -> 0x00d2 }
            java.lang.String r1 = "AdUtil.getUserAgent"
            r6.zza(r5, r1)     // Catch:{ all -> 0x00d2 }
        L_0x00c0:
            java.lang.String r5 = r4.zzeiz     // Catch:{ all -> 0x00d2 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x00d2 }
            java.lang.String r6 = ")"
            java.lang.String r5 = r5.concat(r6)     // Catch:{ all -> 0x00d2 }
            r4.zzeiz = r5     // Catch:{ all -> 0x00d2 }
            java.lang.String r5 = r4.zzeiz     // Catch:{ all -> 0x00d2 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d2 }
            return r5
        L_0x00d2:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00d2 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayh.zzo(android.content.Context, java.lang.String):java.lang.String");
    }

    @VisibleForTesting
    protected static String zzak(Context context) {
        try {
            return new WebView(context).getSettings().getUserAgentString();
        } catch (Throwable unused) {
            return zzzr();
        }
    }

    public static String zzdx(String str) {
        return Uri.parse(str).buildUpon().query(null).build().toString();
    }

    public final JSONObject zzn(Map<String, ?> map) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                zza(jSONObject, str, map.get(str));
            }
            return jSONObject;
        } catch (ClassCastException e) {
            String str2 = "Could not convert map to JSON: ";
            String valueOf = String.valueOf(e.getMessage());
            throw new JSONException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }

    public final JSONObject zza(@Nullable Bundle bundle, JSONObject jSONObject) {
        if (bundle == null) {
            return null;
        }
        try {
            return zze(bundle);
        } catch (JSONException e) {
            zzaxz.zzb("Error converting Bundle to JSON", e);
            return null;
        }
    }

    private final JSONObject zze(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            zza(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    private final JSONArray zza(Collection<?> collection) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object zza : collection) {
            zza(jSONArray, zza);
        }
        return jSONArray;
    }

    private final void zza(JSONArray jSONArray, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONArray.put(zze((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONArray.put(zzn((Map) obj));
        } else if (obj instanceof Collection) {
            jSONArray.put(zza((Collection) obj));
        } else if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            JSONArray jSONArray2 = new JSONArray();
            for (Object zza : objArr) {
                zza(jSONArray2, zza);
            }
            jSONArray.put(jSONArray2);
        } else {
            jSONArray.put(obj);
        }
    }

    private final void zza(JSONObject jSONObject, String str, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONObject.put(str, zze((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONObject.put(str, zzn((Map) obj));
        } else if (obj instanceof Collection) {
            if (str == null) {
                str = "null";
            }
            jSONObject.put(str, zza((Collection) obj));
        } else if (obj instanceof Object[]) {
            jSONObject.put(str, zza((Collection<?>) Arrays.asList((Object[]) obj)));
        } else {
            jSONObject.put(str, obj);
        }
    }

    public static Map<String, String> zzg(Uri uri) {
        if (uri == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String str : zzbv.zzlh().zzh(uri)) {
            hashMap.put(str, uri.getQueryParameter(str));
        }
        return hashMap;
    }

    public static String zzzs() {
        return UUID.randomUUID().toString();
    }

    public static int zzdy(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
            sb.append("Could not parse value:");
            sb.append(valueOf);
            zzaxz.zzeo(sb.toString());
            return 0;
        }
    }

    public static String zzzt() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return str2;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        return sb.toString();
    }

    private static int[] zzzu() {
        return new int[]{0, 0};
    }

    public static int[] zzg(Activity activity) {
        Window window = activity.getWindow();
        if (window != null) {
            View findViewById = window.findViewById(16908290);
            if (findViewById != null) {
                return new int[]{findViewById.getWidth(), findViewById.getHeight()};
            }
        }
        return zzzu();
    }

    public final int[] zzh(Activity activity) {
        int[] zzg = zzg(activity);
        zzwu.zzpv();
        zzwu.zzpv();
        return new int[]{zzbat.zzb((Context) activity, zzg[0]), zzbat.zzb((Context) activity, zzg[1])};
    }

    public final int[] zzi(Activity activity) {
        int[] iArr;
        Window window = activity.getWindow();
        if (window != null) {
            View findViewById = window.findViewById(16908290);
            if (findViewById != null) {
                iArr = new int[]{findViewById.getTop(), findViewById.getBottom()};
                zzwu.zzpv();
                zzwu.zzpv();
                return new int[]{zzbat.zzb((Context) activity, iArr[0]), zzbat.zzb((Context) activity, iArr[1])};
            }
        }
        iArr = zzzu();
        zzwu.zzpv();
        zzwu.zzpv();
        return new int[]{zzbat.zzb((Context) activity, iArr[0]), zzbat.zzb((Context) activity, iArr[1])};
    }

    public static boolean zzdz(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }

    public static DisplayMetrics zza(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static Builder zzal(Context context) {
        return new Builder(context);
    }

    public static zzzy zzam(Context context) {
        return new zzzy(context);
    }

    public static Bitmap zzt(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    public static PopupWindow zza(View view, int i, int i2, boolean z) {
        return new PopupWindow(view, i, i2, false);
    }

    private static String zzan(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(AbstractEvent.ACTIVITY);
            if (activityManager == null) {
                return null;
            }
            List runningTasks = activityManager.getRunningTasks(1);
            if (runningTasks != null && !runningTasks.isEmpty()) {
                RunningTaskInfo runningTaskInfo = (RunningTaskInfo) runningTasks.get(0);
                if (!(runningTaskInfo == null || runningTaskInfo.topActivity == null)) {
                    return runningTaskInfo.topActivity.getClassName();
                }
            }
            return null;
        } catch (Exception unused) {
        }
    }

    public static String zza(Context context, View view, zzwf zzwf) {
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcqo)).booleanValue()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("width", zzwf.width);
            jSONObject2.put("height", zzwf.height);
            jSONObject.put(AbstractEvent.SIZE, jSONObject2);
            jSONObject.put(AbstractEvent.ACTIVITY, zzan(context));
            if (!zzwf.zzckl) {
                JSONArray jSONArray = new JSONArray();
                while (view != null) {
                    ViewParent parent = view.getParent();
                    if (parent != null) {
                        int i = -1;
                        if (parent instanceof ViewGroup) {
                            i = ((ViewGroup) parent).indexOfChild(view);
                        }
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("type", parent.getClass().getName());
                        jSONObject3.put("index_of_child", i);
                        jSONArray.put(jSONObject3);
                    }
                    view = (parent == null || !(parent instanceof View)) ? null : (View) parent;
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("parents", jSONArray);
                }
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            zzaxz.zzc("Fail to get view hierarchy json", e);
            return null;
        }
    }

    public static boolean zzao(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(AbstractEvent.ACTIVITY);
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager != null) {
                if (keyguardManager != null) {
                    List runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses == null) {
                        return false;
                    }
                    Iterator it = runningAppProcesses.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        RunningAppProcessInfo runningAppProcessInfo = (RunningAppProcessInfo) it.next();
                        if (Process.myPid() == runningAppProcessInfo.pid) {
                            if (runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && zzaq(context)) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean zzap(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(AbstractEvent.ACTIVITY);
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager != null) {
                if (keyguardManager != null) {
                    List runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses == null) {
                        return false;
                    }
                    Iterator it = runningAppProcesses.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        RunningAppProcessInfo runningAppProcessInfo = (RunningAppProcessInfo) it.next();
                        if (Process.myPid() == runningAppProcessInfo.pid) {
                            if (runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && zzaq(context)) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean zzaq(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return false;
        }
        return powerManager.isScreenOn();
    }

    public final void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z) {
        if (z) {
            zzbv.zzlf();
            bundle.putString("device", zzzt());
            bundle.putString("eids", TextUtils.join(",", zzaan.zzqw()));
        }
        zzwu.zzpv();
        zzbat.zza(context, str, str2, bundle, z, new zzaym(this, context, str));
    }

    public final void zzb(Context context, String str, String str2, Bundle bundle, boolean z) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcsw)).booleanValue()) {
            zza(context, str, str2, bundle, z);
        }
    }

    public static void zzd(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            runnable.run();
        } else {
            zzayf.zzc(runnable);
        }
    }

    public static Bitmap zzu(View view) {
        if (view == null) {
            return null;
        }
        Bitmap zzw = zzw(view);
        if (zzw == null) {
            zzw = zzv(view);
        }
        return zzw;
    }

    private static Bitmap zzv(@NonNull View view) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            if (width != 0) {
                if (height != 0) {
                    Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.RGB_565);
                    Canvas canvas = new Canvas(createBitmap);
                    view.layout(0, 0, width, height);
                    view.draw(canvas);
                    return createBitmap;
                }
            }
            zzaxz.zzeo("Width or height of view is zero");
            return null;
        } catch (RuntimeException e) {
            zzaxz.zzb("Fail to capture the webview", e);
            return null;
        }
    }

    private static Bitmap zzw(@NonNull View view) {
        Bitmap bitmap = null;
        try {
            boolean isDrawingCacheEnabled = view.isDrawingCacheEnabled();
            view.setDrawingCacheEnabled(true);
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                bitmap = Bitmap.createBitmap(drawingCache);
            }
            view.setDrawingCacheEnabled(isDrawingCacheEnabled);
        } catch (RuntimeException e) {
            zzaxz.zzb("Fail to capture the web view", e);
        }
        return bitmap;
    }

    public static Bitmap zzar(Context context) {
        Bitmap bitmap = null;
        if (!(context instanceof Activity)) {
            return null;
        }
        try {
            Window window = ((Activity) context).getWindow();
            if (window != null) {
                bitmap = zzw(window.getDecorView().getRootView());
            }
        } catch (RuntimeException e) {
            zzaxz.zzb("Fail to capture screen shot", e);
        }
        return bitmap;
    }

    public static void zza(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Throwable unused) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
        }
    }

    public static int zzas(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return 0;
        }
        return applicationInfo.targetSdkVersion;
    }

    public final boolean zza(View view, Context context) {
        Context applicationContext = context.getApplicationContext();
        return zza(view, applicationContext != null ? (PowerManager) applicationContext.getSystemService("power") : null, zzat(context));
    }

    @Nullable
    private static KeyguardManager zzat(Context context) {
        Object systemService = context.getSystemService("keyguard");
        if (systemService == null || !(systemService instanceof KeyguardManager)) {
            return null;
        }
        return (KeyguardManager) systemService;
    }

    public final boolean zza(View view, PowerManager powerManager, KeyguardManager keyguardManager) {
        boolean z;
        boolean z2;
        if (!zzbv.zzlf().zzeld) {
            if (keyguardManager == null) {
                z2 = false;
            } else {
                z2 = keyguardManager.inKeyguardRestrictedInputMode();
            }
            if (z2 && !zzx(view)) {
                z = false;
                if (view.getVisibility() == 0 && view.isShown()) {
                    if ((powerManager != null || powerManager.isScreenOn()) && z) {
                        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcst)).booleanValue() || view.getLocalVisibleRect(new Rect()) || view.getGlobalVisibleRect(new Rect())) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        z = true;
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcst)).booleanValue()) {
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0016 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzx(android.view.View r2) {
        /*
            android.view.View r2 = r2.getRootView()
            r0 = 0
            if (r2 == 0) goto L_0x0012
            android.content.Context r2 = r2.getContext()
            boolean r1 = r2 instanceof android.app.Activity
            if (r1 == 0) goto L_0x0012
            android.app.Activity r2 = (android.app.Activity) r2
            goto L_0x0013
        L_0x0012:
            r2 = r0
        L_0x0013:
            r1 = 0
            if (r2 != 0) goto L_0x0017
            return r1
        L_0x0017:
            android.view.Window r2 = r2.getWindow()
            if (r2 != 0) goto L_0x001e
            goto L_0x0022
        L_0x001e:
            android.view.WindowManager$LayoutParams r0 = r2.getAttributes()
        L_0x0022:
            if (r0 == 0) goto L_0x002d
            int r2 = r0.flags
            r0 = 524288(0x80000, float:7.34684E-40)
            r2 = r2 & r0
            if (r2 == 0) goto L_0x002d
            r2 = 1
            return r2
        L_0x002d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayh.zzx(android.view.View):boolean");
    }

    @TargetApi(16)
    public static boolean zzau(Context context) {
        if (context == null || !PlatformVersion.isAtLeastJellyBean()) {
            return false;
        }
        KeyguardManager zzat = zzat(context);
        if (zzat == null || !zzat.isKeyguardLocked()) {
            return false;
        }
        return true;
    }

    public static int zzy(@Nullable View view) {
        if (view == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        while (parent != null && !(parent instanceof AdapterView)) {
            parent = parent.getParent();
        }
        if (parent == null) {
            return -1;
        }
        return ((AdapterView) parent).getPositionForView(view);
    }

    public static boolean zzav(Context context) {
        try {
            context.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
            return false;
        } catch (ClassNotFoundException unused) {
            return true;
        } catch (Throwable th) {
            zzaxz.zzb("Error loading class.", th);
            zzbv.zzlj().zza(th, "AdUtil.isLiteSdk");
            return false;
        }
    }

    public static Bundle zzzv() {
        Bundle bundle = new Bundle();
        try {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcpi)).booleanValue()) {
                MemoryInfo memoryInfo = new MemoryInfo();
                Debug.getMemoryInfo(memoryInfo);
                bundle.putParcelable("debug_memory_info", memoryInfo);
            }
            Runtime runtime = Runtime.getRuntime();
            bundle.putLong("runtime_free_memory", runtime.freeMemory());
            bundle.putLong("runtime_max_memory", runtime.maxMemory());
            bundle.putLong("runtime_total_memory", runtime.totalMemory());
            bundle.putInt("web_view_count", zzbv.zzlj().zzyp());
        } catch (Exception e) {
            zzaxz.zzc("Unable to gather memory stats", e);
        }
        return bundle;
    }

    @TargetApi(18)
    public static void zza(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            zzb(context, intent);
            bundle.putString("com.android.browser.application_id", context.getPackageName());
            context.startActivity(intent);
            String uri2 = uri.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(uri2).length() + 26);
            sb.append("Opening ");
            sb.append(uri2);
            sb.append(" in a new browser.");
            zzaxz.zzdn(sb.toString());
        } catch (ActivityNotFoundException e) {
            zzaxz.zzb("No browser is found.", e);
        }
    }

    @TargetApi(18)
    public static void zzb(Context context, Intent intent) {
        if (intent != null && PlatformVersion.isAtLeastJellyBeanMR2()) {
            Bundle extras = intent.getExtras() != null ? intent.getExtras() : new Bundle();
            extras.putBinder("android.support.customtabs.extra.SESSION", null);
            extras.putString("com.android.browser.application_id", context.getPackageName());
            intent.putExtras(extras);
        }
    }

    public static void zzd(Context context, String str, String str2) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes("UTF-8"));
            openFileOutput.close();
        } catch (Exception e) {
            zzaxz.zzb("Error writing to file in internal storage.", e);
        }
    }

    public static String zzp(Context context, String str) {
        try {
            return new String(IOUtils.readInputStreamFully(context.openFileInput(str), true), "UTF-8");
        } catch (IOException unused) {
            zzaxz.zzdn("Error reading from internal storage.");
            return "";
        }
    }

    @TargetApi(24)
    public static boolean zza(Activity activity, Configuration configuration) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwn)).booleanValue()) {
            return !activity.isInMultiWindowMode();
        }
        zzwu.zzpv();
        int zza = zzbat.zza((Context) activity, configuration.screenHeightDp);
        int zza2 = zzbat.zza((Context) activity, configuration.screenWidthDp);
        DisplayMetrics zza3 = zza((WindowManager) activity.getApplicationContext().getSystemService("window"));
        int i = zza3.heightPixels;
        int i2 = zza3.widthPixels;
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = identifier > 0 ? activity.getResources().getDimensionPixelSize(identifier) : 0;
        int round = ((int) Math.round(((double) activity.getResources().getDisplayMetrics().density) + 0.5d)) * ((Integer) zzwu.zzpz().zzd(zzaan.zzcwk)).intValue();
        return zze(i, zza + dimensionPixelSize, round) && zze(i2, zza2, round);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
        if (((java.lang.String) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcqq)).equals(r3.zzelg.pattern()) == false) goto L_0x0025;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzea(java.lang.String r4) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            monitor-enter(r3)     // Catch:{ PatternSyntaxException -> 0x0046 }
            java.util.regex.Pattern r0 = r3.zzelg     // Catch:{ all -> 0x0043 }
            if (r0 == 0) goto L_0x0025
            com.google.android.gms.internal.ads.zzaac<java.lang.String> r0 = com.google.android.gms.internal.ads.zzaan.zzcqq     // Catch:{ all -> 0x0043 }
            com.google.android.gms.internal.ads.zzaak r2 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ all -> 0x0043 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0043 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0043 }
            java.util.regex.Pattern r2 = r3.zzelg     // Catch:{ all -> 0x0043 }
            java.lang.String r2 = r2.pattern()     // Catch:{ all -> 0x0043 }
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x0043 }
            if (r0 != 0) goto L_0x0037
        L_0x0025:
            com.google.android.gms.internal.ads.zzaac<java.lang.String> r0 = com.google.android.gms.internal.ads.zzaan.zzcqq     // Catch:{ all -> 0x0043 }
            com.google.android.gms.internal.ads.zzaak r2 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ all -> 0x0043 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0043 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0043 }
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)     // Catch:{ all -> 0x0043 }
            r3.zzelg = r0     // Catch:{ all -> 0x0043 }
        L_0x0037:
            java.util.regex.Pattern r0 = r3.zzelg     // Catch:{ all -> 0x0043 }
            java.util.regex.Matcher r4 = r0.matcher(r4)     // Catch:{ all -> 0x0043 }
            boolean r4 = r4.matches()     // Catch:{ all -> 0x0043 }
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            return r4
        L_0x0043:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            throw r4     // Catch:{ PatternSyntaxException -> 0x0046 }
        L_0x0046:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayh.zzea(java.lang.String):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
        if (((java.lang.String) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcqr)).equals(r3.zzelh.pattern()) == false) goto L_0x0025;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzeb(java.lang.String r4) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            monitor-enter(r3)     // Catch:{ PatternSyntaxException -> 0x0046 }
            java.util.regex.Pattern r0 = r3.zzelh     // Catch:{ all -> 0x0043 }
            if (r0 == 0) goto L_0x0025
            com.google.android.gms.internal.ads.zzaac<java.lang.String> r0 = com.google.android.gms.internal.ads.zzaan.zzcqr     // Catch:{ all -> 0x0043 }
            com.google.android.gms.internal.ads.zzaak r2 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ all -> 0x0043 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0043 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0043 }
            java.util.regex.Pattern r2 = r3.zzelh     // Catch:{ all -> 0x0043 }
            java.lang.String r2 = r2.pattern()     // Catch:{ all -> 0x0043 }
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x0043 }
            if (r0 != 0) goto L_0x0037
        L_0x0025:
            com.google.android.gms.internal.ads.zzaac<java.lang.String> r0 = com.google.android.gms.internal.ads.zzaan.zzcqr     // Catch:{ all -> 0x0043 }
            com.google.android.gms.internal.ads.zzaak r2 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ all -> 0x0043 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0043 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0043 }
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)     // Catch:{ all -> 0x0043 }
            r3.zzelh = r0     // Catch:{ all -> 0x0043 }
        L_0x0037:
            java.util.regex.Pattern r0 = r3.zzelh     // Catch:{ all -> 0x0043 }
            java.util.regex.Matcher r4 = r0.matcher(r4)     // Catch:{ all -> 0x0043 }
            boolean r4 = r4.matches()     // Catch:{ all -> 0x0043 }
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            return r4
        L_0x0043:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            throw r4     // Catch:{ PatternSyntaxException -> 0x0046 }
        L_0x0046:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayh.zzeb(java.lang.String):boolean");
    }

    @VisibleForTesting
    private static boolean zze(int i, int i2, int i3) {
        return Math.abs(i - i2) <= i3;
    }

    @VisibleForTesting
    public static Bundle zza(zzsx zzsx) {
        String str;
        String str2;
        String str3;
        if (zzsx == null) {
            return null;
        }
        if (zzbv.zzlj().zzyq().zzzc() && zzbv.zzlj().zzyq().zzze()) {
            return null;
        }
        if (zzsx.zznw()) {
            zzsx.wakeup();
        }
        zzsr zznu = zzsx.zznu();
        if (zznu != null) {
            str3 = zznu.zznj();
            str2 = zznu.zznk();
            str = zznu.zznl();
            if (str3 != null) {
                zzbv.zzlj().zzyq().zzdq(str3);
            }
            if (str != null) {
                zzbv.zzlj().zzyq().zzdr(str);
            }
        } else {
            str3 = zzbv.zzlj().zzyq().zzzd();
            str = zzbv.zzlj().zzyq().zzzf();
            str2 = null;
        }
        Bundle bundle = new Bundle(1);
        if (str != null && !zzbv.zzlj().zzyq().zzze()) {
            bundle.putString("v_fp_vertical", str);
        }
        if (str3 != null && !zzbv.zzlj().zzyq().zzzc()) {
            bundle.putString("fingerprint", str3);
            if (!str3.equals(str2)) {
                bundle.putString("v_fp", str2);
            }
        }
        if (!bundle.isEmpty()) {
            return bundle;
        }
        return null;
    }

    @Nullable
    public static WebResourceResponse zze(Context context, String str, String str2) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("User-Agent", zzbv.zzlf().zzo(context, str));
            hashMap.put(HttpHeaders.CACHE_CONTROL, "max-stale=3600");
            String str3 = (String) new zzazs(context).zzc(str2, hashMap).get(60, TimeUnit.SECONDS);
            if (str3 != null) {
                return new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(str3.getBytes("UTF-8")));
            }
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            zzaxz.zzc("Could not fetch MRAID JS.", e);
        }
        return null;
    }

    public static WebResourceResponse zzd(HttpURLConnection httpURLConnection) throws IOException {
        String str;
        String str2;
        zzbv.zzlf();
        String contentType = httpURLConnection.getContentType();
        if (TextUtils.isEmpty(contentType)) {
            str = "";
        } else {
            str = contentType.split(";")[0].trim();
        }
        zzbv.zzlf();
        String contentType2 = httpURLConnection.getContentType();
        if (!TextUtils.isEmpty(contentType2)) {
            String[] split = contentType2.split(";");
            if (split.length != 1) {
                int i = 1;
                while (true) {
                    if (i >= split.length) {
                        break;
                    }
                    if (split[i].trim().startsWith("charset")) {
                        String[] split2 = split[i].trim().split("=");
                        if (split2.length > 1) {
                            str2 = split2[1].trim();
                            break;
                        }
                    }
                    i++;
                }
            }
        }
        str2 = "";
        Map headerFields = httpURLConnection.getHeaderFields();
        HashMap hashMap = new HashMap(headerFields.size());
        for (Entry entry : headerFields.entrySet()) {
            if (!(entry.getKey() == null || entry.getValue() == null || ((List) entry.getValue()).size() <= 0)) {
                hashMap.put((String) entry.getKey(), (String) ((List) entry.getValue()).get(0));
            }
        }
        return zzbv.zzlh().zza(str, str2, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage(), hashMap, httpURLConnection.getInputStream());
    }

    public static void zza(Context context, Throwable th) {
        if (context != null) {
            boolean z = false;
            try {
                z = ((Boolean) zzwu.zzpz().zzd(zzaan.zzcof)).booleanValue();
            } catch (IllegalStateException unused) {
            }
            if (z) {
                CrashUtils.addDynamiteErrorToDropBox(context, th);
            }
        }
    }

    @Deprecated
    public final String zzaw(Context context) {
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcym)).booleanValue()) {
            return "";
        }
        try {
            return (String) zzayf.zza(new zzayi(this, context)).get();
        } catch (InterruptedException unused) {
            Thread.interrupted();
            return "";
        } catch (ExecutionException unused2) {
            return "";
        }
    }

    @Deprecated
    public final Bundle zzax(Context context) {
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcyn)).booleanValue()) {
            return null;
        }
        try {
            return (Bundle) zzayf.zza(new zzayj(this, context)).get();
        } catch (InterruptedException unused) {
            Thread.interrupted();
            return null;
        } catch (ExecutionException unused2) {
            return null;
        }
    }

    public static boolean zzay(Context context) {
        if (!(context instanceof Activity)) {
            return false;
        }
        Window window = ((Activity) context).getWindow();
        if (window == null || window.getDecorView() == null) {
            return false;
        }
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        window.getDecorView().getGlobalVisibleRect(rect, null);
        window.getDecorView().getWindowVisibleDisplayFrame(rect2);
        if (rect.bottom == 0 || rect2.bottom == 0 || rect.top != rect2.top) {
            return false;
        }
        return true;
    }

    public static boolean zzec(String str) {
        if (!zzbax.isEnabled()) {
            return false;
        }
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcws)).booleanValue()) {
            return false;
        }
        String str2 = (String) zzwu.zzpz().zzd(zzaan.zzcwu);
        if (!str2.isEmpty()) {
            for (String equals : str2.split(";")) {
                if (equals.equals(str)) {
                    return false;
                }
            }
        }
        String str3 = (String) zzwu.zzpz().zzd(zzaan.zzcwt);
        if (str3.isEmpty()) {
            return true;
        }
        for (String equals2 : str3.split(";")) {
            if (equals2.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
