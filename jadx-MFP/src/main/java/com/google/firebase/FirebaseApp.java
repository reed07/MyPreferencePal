package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.annotations.PublicApi;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.components.Component;
import com.google.firebase.components.Component.AnonymousClass1;
import com.google.firebase.components.zzf;
import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;
import com.google.firebase.internal.InternalTokenProvider;
import com.google.firebase.internal.InternalTokenResult;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.concurrent.GuardedBy;

@PublicApi
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public class FirebaseApp {
    @GuardedBy("LOCK")
    static final Map<String, FirebaseApp> zza = new ArrayMap();
    private static final List<String> zzb = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
    private static final List<String> zzc = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    private static final List<String> zzd = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
    private static final List<String> zze = Arrays.asList(new String[0]);
    private static final Set<String> zzf = Collections.emptySet();
    /* access modifiers changed from: private */
    public static final Object zzg = new Object();
    private static final Executor zzh = new zzb(0);
    private final Context zzi;
    private final String zzj;
    private final FirebaseOptions zzk;
    private final zzf zzl;
    private final SharedPreferences zzm;
    private final Publisher zzn;
    /* access modifiers changed from: private */
    public final AtomicBoolean zzo = new AtomicBoolean(false);
    private final AtomicBoolean zzp = new AtomicBoolean();
    private final AtomicBoolean zzq;
    private final List<IdTokenListener> zzr = new CopyOnWriteArrayList();
    private final List<BackgroundStateChangeListener> zzs = new CopyOnWriteArrayList();
    private final List<FirebaseAppLifecycleListener> zzt = new CopyOnWriteArrayList();
    private InternalTokenProvider zzu;
    private IdTokenListenersCountChangedListener zzv;

    @KeepForSdk
    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    public interface BackgroundStateChangeListener {
        @KeepForSdk
        void onBackgroundStateChanged(boolean z);
    }

    @KeepForSdk
    @Deprecated
    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    public interface IdTokenListener {
        @KeepForSdk
        void onIdTokenChanged(@NonNull InternalTokenResult internalTokenResult);
    }

    @KeepForSdk
    @Deprecated
    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    public interface IdTokenListenersCountChangedListener {
        @KeepForSdk
        void onListenerCountChanged(int i);
    }

    @TargetApi(14)
    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    static class zza implements com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener {
        private static AtomicReference<zza> zza = new AtomicReference<>();

        private zza() {
        }

        public final void onBackgroundStateChanged(boolean z) {
            synchronized (FirebaseApp.zzg) {
                Iterator it = new ArrayList(FirebaseApp.zza.values()).iterator();
                while (it.hasNext()) {
                    FirebaseApp firebaseApp = (FirebaseApp) it.next();
                    if (firebaseApp.zzo.get()) {
                        firebaseApp.zza(z);
                    }
                }
            }
        }

        static /* synthetic */ void zza(Context context) {
            if (PlatformVersion.isAtLeastIceCreamSandwich() && (context.getApplicationContext() instanceof Application)) {
                Application application = (Application) context.getApplicationContext();
                if (zza.get() == null) {
                    zza zza2 = new zza();
                    if (zza.compareAndSet(null, zza2)) {
                        BackgroundDetector.initialize(application);
                        BackgroundDetector.getInstance().addListener(zza2);
                    }
                }
            }
        }
    }

    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    static class zzb implements Executor {
        private static final Handler zza = new Handler(Looper.getMainLooper());

        private zzb() {
        }

        /* synthetic */ zzb(byte b) {
            this();
        }

        public final void execute(@NonNull Runnable runnable) {
            zza.post(runnable);
        }
    }

    @TargetApi(24)
    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    static class zzc extends BroadcastReceiver {
        private static AtomicReference<zzc> zza = new AtomicReference<>();
        private final Context zzb;

        private zzc(Context context) {
            this.zzb = context;
        }

        public final void onReceive(Context context, Intent intent) {
            synchronized (FirebaseApp.zzg) {
                for (FirebaseApp zza2 : FirebaseApp.zza.values()) {
                    zza2.zze();
                }
            }
            this.zzb.unregisterReceiver(this);
        }

        static /* synthetic */ void zza(Context context) {
            if (zza.get() == null) {
                zzc zzc = new zzc(context);
                if (zza.compareAndSet(null, zzc)) {
                    context.registerReceiver(zzc, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }
    }

    @PublicApi
    @NonNull
    public Context getApplicationContext() {
        zzc();
        return this.zzi;
    }

    @PublicApi
    @NonNull
    public String getName() {
        zzc();
        return this.zzj;
    }

    @PublicApi
    @NonNull
    public FirebaseOptions getOptions() {
        zzc();
        return this.zzk;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FirebaseApp)) {
            return false;
        }
        return this.zzj.equals(((FirebaseApp) obj).getName());
    }

    public int hashCode() {
        return this.zzj.hashCode();
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.zzj).add("options", this.zzk).toString();
    }

    @PublicApi
    public static List<FirebaseApp> getApps(Context context) {
        ArrayList arrayList;
        synchronized (zzg) {
            arrayList = new ArrayList(zza.values());
        }
        return arrayList;
    }

    @Nullable
    @PublicApi
    public static FirebaseApp getInstance() {
        FirebaseApp firebaseApp;
        synchronized (zzg) {
            firebaseApp = (FirebaseApp) zza.get("[DEFAULT]");
            if (firebaseApp == null) {
                StringBuilder sb = new StringBuilder("Default FirebaseApp is not initialized in this process ");
                sb.append(ProcessUtils.getMyProcessName());
                sb.append(". Make sure to call FirebaseApp.initializeApp(Context) first.");
                throw new IllegalStateException(sb.toString());
            }
        }
        return firebaseApp;
    }

    @PublicApi
    public static FirebaseApp getInstance(@NonNull String str) {
        FirebaseApp firebaseApp;
        String str2;
        synchronized (zzg) {
            firebaseApp = (FirebaseApp) zza.get(str.trim());
            if (firebaseApp == null) {
                List zzd2 = zzd();
                if (zzd2.isEmpty()) {
                    str2 = "";
                } else {
                    StringBuilder sb = new StringBuilder("Available app names: ");
                    sb.append(TextUtils.join(", ", zzd2));
                    str2 = sb.toString();
                }
                throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", new Object[]{str, str2}));
            }
        }
        return firebaseApp;
    }

    @Nullable
    @PublicApi
    public static FirebaseApp initializeApp(Context context) {
        synchronized (zzg) {
            if (zza.containsKey("[DEFAULT]")) {
                FirebaseApp instance = getInstance();
                return instance;
            }
            FirebaseOptions fromResource = FirebaseOptions.fromResource(context);
            if (fromResource == null) {
                Log.d("FirebaseApp", "Default FirebaseApp failed to initialize because no default options were found. This usually means that com.google.gms:google-services was not applied to your gradle project.");
                return null;
            }
            FirebaseApp initializeApp = initializeApp(context, fromResource);
            return initializeApp;
        }
    }

    @PublicApi
    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions) {
        return initializeApp(context, firebaseOptions, "[DEFAULT]");
    }

    @PublicApi
    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions, String str) {
        FirebaseApp firebaseApp;
        zza.zza(context);
        String trim = str.trim();
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (zzg) {
            boolean z = !zza.containsKey(trim);
            StringBuilder sb = new StringBuilder("FirebaseApp name ");
            sb.append(trim);
            sb.append(" already exists!");
            Preconditions.checkState(z, sb.toString());
            Preconditions.checkNotNull(context, "Application context cannot be null.");
            firebaseApp = new FirebaseApp(context, trim, firebaseOptions);
            zza.put(trim, firebaseApp);
        }
        firebaseApp.zze();
        return firebaseApp;
    }

    @KeepForSdk
    @Deprecated
    public void setTokenProvider(@NonNull InternalTokenProvider internalTokenProvider) {
        this.zzu = (InternalTokenProvider) Preconditions.checkNotNull(internalTokenProvider);
    }

    @KeepForSdk
    @Deprecated
    public void setIdTokenListenersCountChangedListener(@NonNull IdTokenListenersCountChangedListener idTokenListenersCountChangedListener) {
        this.zzv = (IdTokenListenersCountChangedListener) Preconditions.checkNotNull(idTokenListenersCountChangedListener);
        this.zzv.onListenerCountChanged(this.zzr.size());
    }

    @KeepForSdk
    @Deprecated
    public Task<GetTokenResult> getToken(boolean z) {
        zzc();
        InternalTokenProvider internalTokenProvider = this.zzu;
        if (internalTokenProvider == null) {
            return Tasks.forException(new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode."));
        }
        return internalTokenProvider.getAccessToken(z);
    }

    @Nullable
    @KeepForSdk
    @Deprecated
    public String getUid() throws FirebaseApiNotAvailableException {
        zzc();
        InternalTokenProvider internalTokenProvider = this.zzu;
        if (internalTokenProvider != null) {
            return internalTokenProvider.getUid();
        }
        throw new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.");
    }

    @PublicApi
    public void delete() {
        if (this.zzp.compareAndSet(false, true)) {
            synchronized (zzg) {
                zza.remove(this.zzj);
            }
            Iterator it = this.zzt.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    @KeepForSdk
    public <T> T get(Class<T> cls) {
        zzc();
        return this.zzl.get(cls);
    }

    @PublicApi
    public void setAutomaticResourceManagementEnabled(boolean z) {
        zzc();
        if (this.zzo.compareAndSet(!z, z)) {
            boolean isInBackground = BackgroundDetector.getInstance().isInBackground();
            if (z && isInBackground) {
                zza(true);
            } else if (!z && isInBackground) {
                zza(false);
            }
        }
    }

    @KeepForSdk
    public boolean isDataCollectionDefaultEnabled() {
        zzc();
        return this.zzq.get();
    }

    @KeepForSdk
    public void setDataCollectionDefaultEnabled(boolean z) {
        zzc();
        if (this.zzq.compareAndSet(!z, z)) {
            this.zzm.edit().putBoolean("firebase_data_collection_default_enabled", z).commit();
            this.zzn.publish(new Event(DataCollectionDefaultChange.class, new DataCollectionDefaultChange(z)));
        }
    }

    private FirebaseApp(Context context, String str, FirebaseOptions firebaseOptions) {
        this.zzi = (Context) Preconditions.checkNotNull(context);
        this.zzj = Preconditions.checkNotEmpty(str);
        this.zzk = (FirebaseOptions) Preconditions.checkNotNull(firebaseOptions);
        this.zzv = new com.google.firebase.internal.zza();
        this.zzm = context.getSharedPreferences("com.google.firebase.common.prefs", 0);
        this.zzq = new AtomicBoolean(zzb());
        List zza2 = AnonymousClass1.zza(context).zza();
        this.zzl = new zzf(zzh, zza2, Component.of(context, Context.class, new Class[0]), Component.of(this, FirebaseApp.class, new Class[0]), Component.of(firebaseOptions, FirebaseOptions.class, new Class[0]));
        this.zzn = (Publisher) this.zzl.get(Publisher.class);
    }

    private boolean zzb() {
        if (this.zzm.contains("firebase_data_collection_default_enabled")) {
            return this.zzm.getBoolean("firebase_data_collection_default_enabled", true);
        }
        try {
            PackageManager packageManager = this.zzi.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(this.zzi.getPackageName(), 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("firebase_data_collection_default_enabled"))) {
                    return applicationInfo.metaData.getBoolean("firebase_data_collection_default_enabled");
                }
            }
        } catch (NameNotFoundException unused) {
        }
        return true;
    }

    private void zzc() {
        Preconditions.checkState(!this.zzp.get(), "FirebaseApp was deleted");
    }

    @KeepForSdk
    @Deprecated
    public List<IdTokenListener> getListeners() {
        zzc();
        return this.zzr;
    }

    @VisibleForTesting
    @KeepForSdk
    public boolean isDefaultApp() {
        return "[DEFAULT]".equals(getName());
    }

    @UiThread
    @KeepForSdk
    @Deprecated
    public void notifyIdTokenListeners(@NonNull InternalTokenResult internalTokenResult) {
        Log.d("FirebaseApp", "Notifying auth state listeners.");
        int i = 0;
        for (IdTokenListener onIdTokenChanged : this.zzr) {
            onIdTokenChanged.onIdTokenChanged(internalTokenResult);
            i++;
        }
        Log.d("FirebaseApp", String.format("Notified %d auth state listeners.", new Object[]{Integer.valueOf(i)}));
    }

    /* access modifiers changed from: private */
    public void zza(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (BackgroundStateChangeListener onBackgroundStateChanged : this.zzs) {
            onBackgroundStateChanged.onBackgroundStateChanged(z);
        }
    }

    @KeepForSdk
    @Deprecated
    public void addIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        zzc();
        Preconditions.checkNotNull(idTokenListener);
        this.zzr.add(idTokenListener);
        this.zzv.onListenerCountChanged(this.zzr.size());
    }

    @KeepForSdk
    @Deprecated
    public void removeIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        zzc();
        Preconditions.checkNotNull(idTokenListener);
        this.zzr.remove(idTokenListener);
        this.zzv.onListenerCountChanged(this.zzr.size());
    }

    @KeepForSdk
    public void addBackgroundStateChangeListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        zzc();
        if (this.zzo.get() && BackgroundDetector.getInstance().isInBackground()) {
            backgroundStateChangeListener.onBackgroundStateChanged(true);
        }
        this.zzs.add(backgroundStateChangeListener);
    }

    @KeepForSdk
    public void removeBackgroundStateChangeListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        zzc();
        this.zzs.remove(backgroundStateChangeListener);
    }

    @KeepForSdk
    public String getPersistenceKey() {
        StringBuilder sb = new StringBuilder();
        sb.append(Base64Utils.encodeUrlSafeNoPadding(getName().getBytes(Charset.defaultCharset())));
        sb.append("+");
        sb.append(Base64Utils.encodeUrlSafeNoPadding(getOptions().getApplicationId().getBytes(Charset.defaultCharset())));
        return sb.toString();
    }

    @KeepForSdk
    public void addLifecycleEventListener(@NonNull FirebaseAppLifecycleListener firebaseAppLifecycleListener) {
        zzc();
        Preconditions.checkNotNull(firebaseAppLifecycleListener);
        this.zzt.add(firebaseAppLifecycleListener);
    }

    @KeepForSdk
    public void removeLifecycleEventListener(@NonNull FirebaseAppLifecycleListener firebaseAppLifecycleListener) {
        zzc();
        Preconditions.checkNotNull(firebaseAppLifecycleListener);
        this.zzt.remove(firebaseAppLifecycleListener);
    }

    @KeepForSdk
    public static String getPersistenceKey(String str, FirebaseOptions firebaseOptions) {
        StringBuilder sb = new StringBuilder();
        sb.append(Base64Utils.encodeUrlSafeNoPadding(str.getBytes(Charset.defaultCharset())));
        sb.append("+");
        sb.append(Base64Utils.encodeUrlSafeNoPadding(firebaseOptions.getApplicationId().getBytes(Charset.defaultCharset())));
        return sb.toString();
    }

    private static List<String> zzd() {
        ArrayList arrayList = new ArrayList();
        synchronized (zzg) {
            for (FirebaseApp name : zza.values()) {
                arrayList.add(name.getName());
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void zze() {
        boolean isDeviceProtectedStorage = ContextCompat.isDeviceProtectedStorage(this.zzi);
        if (isDeviceProtectedStorage) {
            zzc.zza(this.zzi);
        } else {
            this.zzl.zza(isDefaultApp());
        }
        zza(FirebaseApp.class, this, zzb, isDeviceProtectedStorage);
        if (isDefaultApp()) {
            zza(FirebaseApp.class, this, zzc, isDeviceProtectedStorage);
            zza(Context.class, this.zzi, zzd, isDeviceProtectedStorage);
        }
    }

    private static <T> void zza(Class<T> cls, T t, Iterable<String> iterable, boolean z) {
        for (String str : iterable) {
            if (z) {
                try {
                    if (!zze.contains(str)) {
                    }
                } catch (ClassNotFoundException unused) {
                    if (!zzf.contains(str)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append(" is not linked. Skipping initialization.");
                        Log.d("FirebaseApp", sb.toString());
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str);
                        sb2.append(" is missing, but is required. Check if it has been removed by Proguard.");
                        throw new IllegalStateException(sb2.toString());
                    }
                } catch (NoSuchMethodException unused2) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str);
                    sb3.append("#getInstance has been removed by Proguard. Add keep rule to prevent it.");
                    throw new IllegalStateException(sb3.toString());
                } catch (InvocationTargetException e) {
                    Log.wtf("FirebaseApp", "Firebase API initialization failure.", e);
                } catch (IllegalAccessException e2) {
                    StringBuilder sb4 = new StringBuilder("Failed to initialize ");
                    sb4.append(str);
                    Log.wtf("FirebaseApp", sb4.toString(), e2);
                }
            }
            Method method = Class.forName(str).getMethod("getInstance", new Class[]{cls});
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                method.invoke(null, new Object[]{t});
            }
        }
    }
}
