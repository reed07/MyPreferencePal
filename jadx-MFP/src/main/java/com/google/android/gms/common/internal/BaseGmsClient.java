package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import com.myfitnesspal.shared.constants.Constants.Analytics;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public abstract class BaseGmsClient<T extends IInterface> {
    @KeepForSdk
    public static final int CONNECT_STATE_CONNECTED = 4;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    @KeepForSdk
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = {"service_esmobile", "service_googleme"};
    @KeepForSdk
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private static final Feature[] zzbs = new Feature[0];
    private final Context mContext;
    final Handler mHandler;
    private final Object mLock;
    private int zzbt;
    private long zzbu;
    private long zzbv;
    private int zzbw;
    private long zzbx;
    @VisibleForTesting
    private zzh zzby;
    private final Looper zzbz;
    private final GmsClientSupervisor zzca;
    private final GoogleApiAvailabilityLight zzcb;
    /* access modifiers changed from: private */
    public final Object zzcc;
    /* access modifiers changed from: private */
    @GuardedBy("mServiceBrokerLock")
    public IGmsServiceBroker zzcd;
    @VisibleForTesting
    protected ConnectionProgressReportCallbacks zzce;
    @GuardedBy("mLock")
    private T zzcf;
    /* access modifiers changed from: private */
    public final ArrayList<zzc<?>> zzcg;
    @GuardedBy("mLock")
    private zze zzch;
    @GuardedBy("mLock")
    private int zzci;
    /* access modifiers changed from: private */
    public final BaseConnectionCallbacks zzcj;
    /* access modifiers changed from: private */
    public final BaseOnConnectionFailedListener zzck;
    private final int zzcl;
    private final String zzcm;
    /* access modifiers changed from: private */
    public ConnectionResult zzcn;
    /* access modifiers changed from: private */
    public boolean zzco;
    private volatile zzb zzcp;
    @VisibleForTesting
    protected AtomicInteger zzcq;

    @KeepForSdk
    public interface BaseConnectionCallbacks {
        @KeepForSdk
        void onConnected(@Nullable Bundle bundle);

        @KeepForSdk
        void onConnectionSuspended(int i);
    }

    @KeepForSdk
    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    @KeepForSdk
    public interface ConnectionProgressReportCallbacks {
        @KeepForSdk
        void onReportServiceBinding(@NonNull ConnectionResult connectionResult);
    }

    protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        @KeepForSdk
        public LegacyClientCallbackAdapter() {
        }

        public void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                baseGmsClient.getRemoteService(null, baseGmsClient.getScopes());
                return;
            }
            if (BaseGmsClient.this.zzck != null) {
                BaseGmsClient.this.zzck.onConnectionFailed(connectionResult);
            }
        }
    }

    @KeepForSdk
    public interface SignOutCallbacks {
        @KeepForSdk
        void onSignOutComplete();
    }

    private abstract class zza extends zzc<Boolean> {
        private final int statusCode;
        private final Bundle zzcr;

        @BinderThread
        protected zza(int i, Bundle bundle) {
            super(Boolean.valueOf(true));
            this.statusCode = i;
            this.zzcr = bundle;
        }

        /* access modifiers changed from: protected */
        public abstract void zza(ConnectionResult connectionResult);

        /* access modifiers changed from: protected */
        public abstract boolean zzm();

        /* access modifiers changed from: protected */
        public final void zzn() {
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void zza(Object obj) {
            PendingIntent pendingIntent = null;
            if (((Boolean) obj) == null) {
                BaseGmsClient.this.zza(1, null);
                return;
            }
            int i = this.statusCode;
            if (i != 0) {
                if (i != 10) {
                    BaseGmsClient.this.zza(1, null);
                    Bundle bundle = this.zzcr;
                    if (bundle != null) {
                        pendingIntent = (PendingIntent) bundle.getParcelable(BaseGmsClient.KEY_PENDING_INTENT);
                    }
                    zza(new ConnectionResult(this.statusCode, pendingIntent));
                } else {
                    BaseGmsClient.this.zza(1, null);
                    throw new IllegalStateException(String.format("A fatal developer error has occurred. Class name: %s. Start service action: %s. Service Descriptor: %s. ", new Object[]{getClass().getSimpleName(), BaseGmsClient.this.getStartServiceAction(), BaseGmsClient.this.getServiceDescriptor()}));
                }
            } else if (!zzm()) {
                BaseGmsClient.this.zza(1, null);
                zza(new ConnectionResult(8, null));
            }
        }
    }

    final class zzb extends com.google.android.gms.internal.common.zze {
        public zzb(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            ConnectionResult connectionResult;
            ConnectionResult connectionResult2;
            if (BaseGmsClient.this.zzcq.get() != message.arg1) {
                if (zzb(message)) {
                    zza(message);
                }
            } else if ((message.what == 1 || message.what == 7 || message.what == 4 || message.what == 5) && !BaseGmsClient.this.isConnecting()) {
                zza(message);
            } else {
                PendingIntent pendingIntent = null;
                if (message.what == 4) {
                    BaseGmsClient.this.zzcn = new ConnectionResult(message.arg2);
                    if (!BaseGmsClient.this.zzl() || BaseGmsClient.this.zzco) {
                        if (BaseGmsClient.this.zzcn != null) {
                            connectionResult2 = BaseGmsClient.this.zzcn;
                        } else {
                            connectionResult2 = new ConnectionResult(8);
                        }
                        BaseGmsClient.this.zzce.onReportServiceBinding(connectionResult2);
                        BaseGmsClient.this.onConnectionFailed(connectionResult2);
                        return;
                    }
                    BaseGmsClient.this.zza(3, null);
                } else if (message.what == 5) {
                    if (BaseGmsClient.this.zzcn != null) {
                        connectionResult = BaseGmsClient.this.zzcn;
                    } else {
                        connectionResult = new ConnectionResult(8);
                    }
                    BaseGmsClient.this.zzce.onReportServiceBinding(connectionResult);
                    BaseGmsClient.this.onConnectionFailed(connectionResult);
                } else if (message.what == 3) {
                    if (message.obj instanceof PendingIntent) {
                        pendingIntent = (PendingIntent) message.obj;
                    }
                    ConnectionResult connectionResult3 = new ConnectionResult(message.arg2, pendingIntent);
                    BaseGmsClient.this.zzce.onReportServiceBinding(connectionResult3);
                    BaseGmsClient.this.onConnectionFailed(connectionResult3);
                } else if (message.what == 6) {
                    BaseGmsClient.this.zza(5, null);
                    if (BaseGmsClient.this.zzcj != null) {
                        BaseGmsClient.this.zzcj.onConnectionSuspended(message.arg2);
                    }
                    BaseGmsClient.this.onConnectionSuspended(message.arg2);
                    BaseGmsClient.this.zza(5, 1, null);
                } else if (message.what == 2 && !BaseGmsClient.this.isConnected()) {
                    zza(message);
                } else if (zzb(message)) {
                    ((zzc) message.obj).zzo();
                } else {
                    int i = message.what;
                    StringBuilder sb = new StringBuilder(45);
                    sb.append("Don't know how to handle message: ");
                    sb.append(i);
                    Log.wtf("GmsClient", sb.toString(), new Exception());
                }
            }
        }

        private static void zza(Message message) {
            zzc zzc = (zzc) message.obj;
            zzc.zzn();
            zzc.unregister();
        }

        private static boolean zzb(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 7;
        }
    }

    protected abstract class zzc<TListener> {
        private TListener zzct;
        private boolean zzcu = false;

        public zzc(TListener tlistener) {
            this.zzct = tlistener;
        }

        /* access modifiers changed from: protected */
        public abstract void zza(TListener tlistener);

        /* access modifiers changed from: protected */
        public abstract void zzn();

        public final void zzo() {
            TListener tlistener;
            synchronized (this) {
                tlistener = this.zzct;
                if (this.zzcu) {
                    String valueOf = String.valueOf(this);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
                    sb.append("Callback proxy ");
                    sb.append(valueOf);
                    sb.append(" being reused. This is not safe.");
                    Log.w("GmsClient", sb.toString());
                }
            }
            if (tlistener != null) {
                try {
                    zza(tlistener);
                } catch (RuntimeException e) {
                    zzn();
                    throw e;
                }
            } else {
                zzn();
            }
            synchronized (this) {
                this.zzcu = true;
            }
            unregister();
        }

        public final void unregister() {
            removeListener();
            synchronized (BaseGmsClient.this.zzcg) {
                BaseGmsClient.this.zzcg.remove(this);
            }
        }

        public final void removeListener() {
            synchronized (this) {
                this.zzct = null;
            }
        }
    }

    @VisibleForTesting
    public static final class zzd extends com.google.android.gms.common.internal.IGmsCallbacks.zza {
        private BaseGmsClient zzcv;
        private final int zzcw;

        public zzd(@NonNull BaseGmsClient baseGmsClient, int i) {
            this.zzcv = baseGmsClient;
            this.zzcw = i;
        }

        @BinderThread
        public final void zza(int i, @Nullable Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        @BinderThread
        public final void onPostInitComplete(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
            Preconditions.checkNotNull(this.zzcv, "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzcv.onPostInitHandler(i, iBinder, bundle, this.zzcw);
            this.zzcv = null;
        }

        @BinderThread
        public final void zza(int i, @NonNull IBinder iBinder, @NonNull zzb zzb) {
            Preconditions.checkNotNull(this.zzcv, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Preconditions.checkNotNull(zzb);
            this.zzcv.zza(zzb);
            onPostInitComplete(i, iBinder, zzb.zzcz);
        }
    }

    @VisibleForTesting
    public final class zze implements ServiceConnection {
        private final int zzcw;

        public zze(int i) {
            this.zzcw = i;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IGmsServiceBroker iGmsServiceBroker;
            if (iBinder == null) {
                BaseGmsClient.this.zzb(16);
                return;
            }
            synchronized (BaseGmsClient.this.zzcc) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                if (iBinder == null) {
                    iGmsServiceBroker = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IGmsServiceBroker)) {
                        iGmsServiceBroker = new zza(iBinder);
                    } else {
                        iGmsServiceBroker = (IGmsServiceBroker) queryLocalInterface;
                    }
                }
                baseGmsClient.zzcd = iGmsServiceBroker;
            }
            BaseGmsClient.this.zza(0, (Bundle) null, this.zzcw);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            synchronized (BaseGmsClient.this.zzcc) {
                BaseGmsClient.this.zzcd = null;
            }
            BaseGmsClient.this.mHandler.sendMessage(BaseGmsClient.this.mHandler.obtainMessage(6, this.zzcw, 1));
        }
    }

    protected final class zzf extends zza {
        private final IBinder zzcx;

        @BinderThread
        public zzf(int i, IBinder iBinder, Bundle bundle) {
            super(i, bundle);
            this.zzcx = iBinder;
        }

        /* access modifiers changed from: protected */
        public final void zza(ConnectionResult connectionResult) {
            if (BaseGmsClient.this.zzck != null) {
                BaseGmsClient.this.zzck.onConnectionFailed(connectionResult);
            }
            BaseGmsClient.this.onConnectionFailed(connectionResult);
        }

        /* access modifiers changed from: protected */
        public final boolean zzm() {
            try {
                String interfaceDescriptor = this.zzcx.getInterfaceDescriptor();
                if (!BaseGmsClient.this.getServiceDescriptor().equals(interfaceDescriptor)) {
                    String serviceDescriptor = BaseGmsClient.this.getServiceDescriptor();
                    StringBuilder sb = new StringBuilder(String.valueOf(serviceDescriptor).length() + 34 + String.valueOf(interfaceDescriptor).length());
                    sb.append("service descriptor mismatch: ");
                    sb.append(serviceDescriptor);
                    sb.append(" vs. ");
                    sb.append(interfaceDescriptor);
                    Log.e("GmsClient", sb.toString());
                    return false;
                }
                IInterface createServiceInterface = BaseGmsClient.this.createServiceInterface(this.zzcx);
                if (createServiceInterface == null || (!BaseGmsClient.this.zza(2, 4, createServiceInterface) && !BaseGmsClient.this.zza(3, 4, createServiceInterface))) {
                    return false;
                }
                BaseGmsClient.this.zzcn = null;
                Bundle connectionHint = BaseGmsClient.this.getConnectionHint();
                if (BaseGmsClient.this.zzcj != null) {
                    BaseGmsClient.this.zzcj.onConnected(connectionHint);
                }
                return true;
            } catch (RemoteException unused) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    protected final class zzg extends zza {
        @BinderThread
        public zzg(int i, Bundle bundle) {
            super(i, null);
        }

        /* access modifiers changed from: protected */
        public final void zza(ConnectionResult connectionResult) {
            BaseGmsClient.this.zzce.onReportServiceBinding(connectionResult);
            BaseGmsClient.this.onConnectionFailed(connectionResult);
        }

        /* access modifiers changed from: protected */
        public final boolean zzm() {
            BaseGmsClient.this.zzce.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    @KeepForSdk
    public abstract T createServiceInterface(IBinder iBinder);

    @KeepForSdk
    public Account getAccount() {
        return null;
    }

    @KeepForSdk
    public Bundle getConnectionHint() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Nullable
    @KeepForSdk
    public String getLocalStartServiceAction() {
        return null;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    @NonNull
    public abstract String getServiceDescriptor();

    /* access modifiers changed from: protected */
    @KeepForSdk
    @NonNull
    public abstract String getStartServiceAction();

    /* access modifiers changed from: protected */
    @KeepForSdk
    public String getStartServicePackage() {
        return "com.google.android.gms";
    }

    /* access modifiers changed from: 0000 */
    @KeepForSdk
    public void onSetConnectState(int i, T t) {
    }

    @KeepForSdk
    public boolean providesSignIn() {
        return false;
    }

    @KeepForSdk
    public boolean requiresAccount() {
        return false;
    }

    @KeepForSdk
    public boolean requiresGooglePlayServices() {
        return true;
    }

    @KeepForSdk
    public boolean requiresSignIn() {
        return false;
    }

    @KeepForSdk
    protected BaseGmsClient(Context context, Looper looper, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailabilityLight.getInstance(), i, (BaseConnectionCallbacks) Preconditions.checkNotNull(baseConnectionCallbacks), (BaseOnConnectionFailedListener) Preconditions.checkNotNull(baseOnConnectionFailedListener), str);
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this.mLock = new Object();
        this.zzcc = new Object();
        this.zzcg = new ArrayList<>();
        this.zzci = 1;
        this.zzcn = null;
        this.zzco = false;
        this.zzcp = null;
        this.zzcq = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.zzbz = (Looper) Preconditions.checkNotNull(looper, "Looper must not be null");
        this.zzca = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcb = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.mHandler = new zzb(looper);
        this.zzcl = i;
        this.zzcj = baseConnectionCallbacks;
        this.zzck = baseOnConnectionFailedListener;
        this.zzcm = str;
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        this.mLock = new Object();
        this.zzcc = new Object();
        this.zzcg = new ArrayList<>();
        this.zzci = 1;
        this.zzcn = null;
        this.zzco = false;
        this.zzcp = null;
        this.zzcq = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.mHandler = (Handler) Preconditions.checkNotNull(handler, "Handler must not be null");
        this.zzbz = handler.getLooper();
        this.zzca = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcb = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zzcl = i;
        this.zzcj = baseConnectionCallbacks;
        this.zzck = baseOnConnectionFailedListener;
        this.zzcm = null;
    }

    @Nullable
    private final String zzj() {
        String str = this.zzcm;
        return str == null ? this.mContext.getClass().getName() : str;
    }

    /* access modifiers changed from: private */
    public final void zza(zzb zzb2) {
        this.zzcp = zzb2;
    }

    @Nullable
    @KeepForSdk
    public final Feature[] getAvailableFeatures() {
        zzb zzb2 = this.zzcp;
        if (zzb2 == null) {
            return null;
        }
        return zzb2.zzda;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    @CallSuper
    public void onConnectedLocked(@NonNull T t) {
        this.zzbv = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    @CallSuper
    public void onConnectionSuspended(int i) {
        this.zzbt = i;
        this.zzbu = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    @CallSuper
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzbw = connectionResult.getErrorCode();
        this.zzbx = System.currentTimeMillis();
    }

    /* access modifiers changed from: private */
    public final void zza(int i, T t) {
        zzh zzh;
        Preconditions.checkArgument((i == 4) == (t != null));
        synchronized (this.mLock) {
            this.zzci = i;
            this.zzcf = t;
            onSetConnectState(i, t);
            switch (i) {
                case 1:
                    if (this.zzch != null) {
                        this.zzca.zza(getStartServiceAction(), getStartServicePackage(), 129, this.zzch, zzj());
                        this.zzch = null;
                        break;
                    }
                    break;
                case 2:
                case 3:
                    if (!(this.zzch == null || this.zzby == null)) {
                        String zzt = this.zzby.zzt();
                        String packageName = this.zzby.getPackageName();
                        StringBuilder sb = new StringBuilder(String.valueOf(zzt).length() + 70 + String.valueOf(packageName).length());
                        sb.append("Calling connect() while still connected, missing disconnect() for ");
                        sb.append(zzt);
                        sb.append(" on ");
                        sb.append(packageName);
                        Log.e("GmsClient", sb.toString());
                        this.zzca.zza(this.zzby.zzt(), this.zzby.getPackageName(), this.zzby.zzq(), this.zzch, zzj());
                        this.zzcq.incrementAndGet();
                    }
                    this.zzch = new zze(this.zzcq.get());
                    if (this.zzci != 3 || getLocalStartServiceAction() == null) {
                        zzh = new zzh(getStartServicePackage(), getStartServiceAction(), false, 129);
                    } else {
                        zzh = new zzh(getContext().getPackageName(), getLocalStartServiceAction(), true, 129);
                    }
                    this.zzby = zzh;
                    if (!this.zzca.zza(new zza(this.zzby.zzt(), this.zzby.getPackageName(), this.zzby.zzq()), this.zzch, zzj())) {
                        String zzt2 = this.zzby.zzt();
                        String packageName2 = this.zzby.getPackageName();
                        StringBuilder sb2 = new StringBuilder(String.valueOf(zzt2).length() + 34 + String.valueOf(packageName2).length());
                        sb2.append("unable to connect to service: ");
                        sb2.append(zzt2);
                        sb2.append(" on ");
                        sb2.append(packageName2);
                        Log.e("GmsClient", sb2.toString());
                        zza(16, (Bundle) null, this.zzcq.get());
                        break;
                    }
                    break;
                case 4:
                    onConnectedLocked(t);
                    break;
            }
        }
    }

    /* access modifiers changed from: private */
    public final boolean zza(int i, int i2, T t) {
        synchronized (this.mLock) {
            if (this.zzci != i) {
                return false;
            }
            zza(i2, t);
            return true;
        }
    }

    @KeepForSdk
    public void checkAvailabilityAndConnect() {
        int isGooglePlayServicesAvailable = this.zzcb.isGooglePlayServicesAvailable(this.mContext, getMinApkVersion());
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, (T) null);
            triggerNotAvailable(new LegacyClientCallbackAdapter(), isGooglePlayServicesAvailable, null);
            return;
        }
        connect(new LegacyClientCallbackAdapter());
    }

    @KeepForSdk
    public void connect(@NonNull ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.zzce = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        zza(2, (T) null);
    }

    @KeepForSdk
    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzci == 4;
        }
        return z;
    }

    @KeepForSdk
    public boolean isConnecting() {
        boolean z;
        synchronized (this.mLock) {
            if (this.zzci != 2) {
                if (this.zzci != 3) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    private final boolean zzk() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzci == 3;
        }
        return z;
    }

    @KeepForSdk
    public void disconnect() {
        this.zzcq.incrementAndGet();
        synchronized (this.zzcg) {
            int size = this.zzcg.size();
            for (int i = 0; i < size; i++) {
                ((zzc) this.zzcg.get(i)).removeListener();
            }
            this.zzcg.clear();
        }
        synchronized (this.zzcc) {
            this.zzcd = null;
        }
        zza(1, (T) null);
    }

    @KeepForSdk
    public void triggerConnectionSuspended(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(6, this.zzcq.get(), i));
    }

    /* access modifiers changed from: private */
    public final void zzb(int i) {
        int i2;
        if (zzk()) {
            i2 = 5;
            this.zzco = true;
        } else {
            i2 = 4;
        }
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(i2, this.zzcq.get(), 16));
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    @VisibleForTesting
    public void triggerNotAvailable(@NonNull ConnectionProgressReportCallbacks connectionProgressReportCallbacks, int i, @Nullable PendingIntent pendingIntent) {
        this.zzce = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(3, this.zzcq.get(), i, pendingIntent));
    }

    @KeepForSdk
    public final Context getContext() {
        return this.mContext;
    }

    @KeepForSdk
    public final Looper getLooper() {
        return this.zzbz;
    }

    @KeepForSdk
    public Feature[] getApiFeatures() {
        return zzbs;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1, i2, -1, new zzf(i, iBinder, bundle)));
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, @Nullable Bundle bundle, int i2) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(7, i2, -1, new zzg(i, null)));
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    @KeepForSdk
    public final T getService() throws DeadObjectException {
        T t;
        synchronized (this.mLock) {
            if (this.zzci != 5) {
                checkConnected();
                Preconditions.checkState(this.zzcf != null, "Client is connected but service is null");
                t = this.zzcf;
            } else {
                throw new DeadObjectException();
            }
        }
        return t;
    }

    @WorkerThread
    @KeepForSdk
    public void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        Bundle getServiceRequestExtraArgs = getGetServiceRequestExtraArgs();
        GetServiceRequest getServiceRequest = new GetServiceRequest(this.zzcl);
        getServiceRequest.zzdh = this.mContext.getPackageName();
        getServiceRequest.zzdk = getServiceRequestExtraArgs;
        if (set != null) {
            getServiceRequest.zzdj = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (requiresSignIn()) {
            getServiceRequest.zzdl = getAccount() != null ? getAccount() : new Account("<<default account>>", AccountType.GOOGLE);
            if (iAccountAccessor != null) {
                getServiceRequest.zzdi = iAccountAccessor.asBinder();
            }
        } else if (requiresAccount()) {
            getServiceRequest.zzdl = getAccount();
        }
        getServiceRequest.zzdm = zzbs;
        getServiceRequest.zzdn = getApiFeatures();
        try {
            synchronized (this.zzcc) {
                if (this.zzcd != null) {
                    this.zzcd.getService(new zzd(this, this.zzcq.get()), getServiceRequest);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            triggerConnectionSuspended(1);
        } catch (SecurityException e2) {
            throw e2;
        } catch (RemoteException | RuntimeException e3) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e3);
            onPostInitHandler(8, null, null, this.zzcq.get());
        }
    }

    @KeepForSdk
    public void onUserSignOut(@NonNull SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.onSignOutComplete();
    }

    @KeepForSdk
    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Set<Scope> getScopes() {
        return Collections.EMPTY_SET;
    }

    @KeepForSdk
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        T t;
        IGmsServiceBroker iGmsServiceBroker;
        synchronized (this.mLock) {
            i = this.zzci;
            t = this.zzcf;
        }
        synchronized (this.zzcc) {
            iGmsServiceBroker = this.zzcd;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("REMOTE_CONNECTING");
                break;
            case 3:
                printWriter.print("LOCAL_CONNECTING");
                break;
            case 4:
                printWriter.print("CONNECTED");
                break;
            case 5:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print(Analytics.UNKNOWN);
                break;
        }
        printWriter.append(" mService=");
        if (t == null) {
            printWriter.append("null");
        } else {
            printWriter.append(getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(t.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (iGmsServiceBroker == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzbv > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.zzbv;
            String format = simpleDateFormat.format(new Date(j));
            StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 21);
            sb.append(j);
            sb.append(" ");
            sb.append(format);
            append.println(sb.toString());
        }
        if (this.zzbu > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            int i2 = this.zzbt;
            switch (i2) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append(String.valueOf(i2));
                    break;
            }
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j2 = this.zzbu;
            String format2 = simpleDateFormat.format(new Date(j2));
            StringBuilder sb2 = new StringBuilder(String.valueOf(format2).length() + 21);
            sb2.append(j2);
            sb2.append(" ");
            sb2.append(format2);
            append2.println(sb2.toString());
        }
        if (this.zzbx > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzbw));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j3 = this.zzbx;
            String format3 = simpleDateFormat.format(new Date(j3));
            StringBuilder sb3 = new StringBuilder(String.valueOf(format3).length() + 21);
            sb3.append(j3);
            sb3.append(" ");
            sb3.append(format3);
            append3.println(sb3.toString());
        }
    }

    @Nullable
    @KeepForSdk
    public IBinder getServiceBrokerBinder() {
        synchronized (this.zzcc) {
            if (this.zzcd == null) {
                return null;
            }
            IBinder asBinder = this.zzcd.asBinder();
            return asBinder;
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzl() {
        if (this.zzco || TextUtils.isEmpty(getServiceDescriptor()) || TextUtils.isEmpty(getLocalStartServiceAction())) {
            return false;
        }
        try {
            Class.forName(getServiceDescriptor());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @KeepForSdk
    public String getEndpointPackageName() {
        if (isConnected()) {
            zzh zzh = this.zzby;
            if (zzh != null) {
                return zzh.getPackageName();
            }
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    @KeepForSdk
    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }
}
