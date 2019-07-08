package com.samsung.android.sdk.internal.healthdata;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.samsung.android.sdk.healthdata.HealthDataResolver.AggregateResult;
import com.samsung.android.sdk.healthdata.HealthDataResolver.ReadResult;
import com.samsung.android.sdk.healthdata.HealthPermissionManager.PermissionResult;
import com.samsung.android.sdk.healthdata.HealthResultHolder.BaseResult;
import com.samsung.android.sdk.internal.healthdata.ICallbackRegister.Stub;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class HealthResultReceiver implements Parcelable {
    public static final Creator<HealthResultReceiver> CREATOR = new Creator<HealthResultReceiver>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new HealthResultReceiver[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            int dataPosition = parcel.dataPosition();
            parcel.readInt();
            parcel.readStrongBinder();
            int readInt = parcel.readInt();
            parcel.setDataPosition(dataPosition);
            switch (readInt) {
                case 0:
                    return new Async(parcel, 0);
                case 1:
                    return new Sync(parcel, 0);
                case 2:
                    return new ForwardAsync(parcel, 0);
                default:
                    StringBuilder sb = new StringBuilder("Invalid result parcel type : ");
                    sb.append(readInt);
                    throw new IllegalArgumentException(sb.toString());
            }
        }
    };
    private final int a;

    public static class Async extends HealthResultReceiver {
        private RemoteResultListener a;
        private ICallbackRegister b;
        /* access modifiers changed from: private */
        public IHealthResultReceiver c;
        private final int d;
        private final int e;
        private Intent f;

        class a extends Stub {
            private a() {
            }

            /* synthetic */ a(Async async, byte b) {
                this();
            }

            public final void setCallback(int i, IHealthResultReceiver iHealthResultReceiver) throws RemoteException {
                Async.this.c = iHealthResultReceiver;
            }

            public final void cancel(int i) throws RemoteException {
                Async.this.onCancelResult(i);
            }
        }

        class b extends IHealthResultReceiver.Stub {
            private b() {
            }

            /* synthetic */ b(Async async, byte b) {
                this();
            }

            public final void send(int i, Bundle bundle) {
                Async.this.a(i, bundle);
            }
        }

        public boolean isSync() {
            return false;
        }

        /* access modifiers changed from: protected */
        public void onCancelResult(int i) {
        }

        /* synthetic */ Async(Parcel parcel, byte b2) {
            this(parcel);
        }

        protected Async() {
            super(0);
            this.d = 0;
            this.e = 0;
        }

        private Async(Parcel parcel) {
            super(parcel, 0);
            this.c = new b(this, 0);
            this.b = Stub.asInterface(parcel.readStrongBinder());
            parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            parcel.readBundle(HealthResultReceiver.a(this.e));
            this.f = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
            try {
                this.b.setCallback(0, this.c);
            } catch (RemoteException unused) {
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            HealthResultReceiver.super.writeToParcel(parcel, i);
            synchronized (this) {
                if (this.b == null) {
                    this.b = new a(this, 0);
                }
                parcel.writeStrongBinder(this.b.asBinder());
            }
            parcel.writeInt(0);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            parcel.writeBundle(null);
            parcel.writeParcelable(this.f, 0);
        }

        public void send(int i, Bundle bundle) {
            IHealthResultReceiver iHealthResultReceiver = this.c;
            if (iHealthResultReceiver != null) {
                try {
                    iHealthResultReceiver.send(i, bundle);
                } catch (RemoteException unused) {
                }
                this.c = null;
                return;
            }
            a(i, bundle);
        }

        public <T extends BaseResult> void registerListener(RemoteResultListener<T> remoteResultListener) {
            this.a = remoteResultListener;
        }

        /* access modifiers changed from: private */
        public void a(int i, Bundle bundle) {
            if (this.a != null) {
                bundle.setClassLoader(PermissionResult.class.getClassLoader());
                switch (bundle.getInt("type", -1)) {
                    case 1:
                        this.a.onReceiveHealthResult(i, (ReadResult) bundle.getParcelable(IpcUtil.KEY_PARCEL));
                        break;
                    case 2:
                        this.a.onReceiveHealthResult(i, (AggregateResult) bundle.getParcelable(IpcUtil.KEY_PARCEL));
                        break;
                    case 3:
                        int i2 = bundle.getInt("PERMISSION_RESULT_COUNT");
                        bundle.remove("PERMISSION_RESULT_COUNT");
                        bundle.remove("type");
                        this.a.onReceiveHealthResult(i, new PermissionResult(bundle, i2));
                        break;
                    default:
                        this.a.onReceiveHealthResult(i, (BaseResult) bundle.getParcelable(IpcUtil.KEY_PARCEL));
                        break;
                }
                this.a = null;
            }
        }

        public void cancel(int i) throws RemoteException {
            ICallbackRegister iCallbackRegister = this.b;
            if (iCallbackRegister != null) {
                iCallbackRegister.cancel(i);
            }
        }

        public Intent getIntent() {
            return this.f;
        }

        public void setIntent(Intent intent) {
            this.f = intent;
        }
    }

    public static class ForwardAsync extends HealthResultReceiver {
        private IHealthResultReceiver a;
        private RemoteResultListener b;
        private AtomicBoolean c;

        class a extends IHealthResultReceiver.Stub {
            private a() {
            }

            /* synthetic */ a(ForwardAsync forwardAsync, byte b) {
                this();
            }

            public final void send(int i, Bundle bundle) {
                ForwardAsync.a(ForwardAsync.this, i, bundle);
            }
        }

        public boolean isSync() {
            return false;
        }

        /* synthetic */ ForwardAsync(Parcel parcel, byte b2) {
            this(parcel);
        }

        public ForwardAsync() {
            super(0);
            this.a = new a(this, 0);
            this.c = new AtomicBoolean(false);
        }

        private ForwardAsync(Parcel parcel) {
            super(parcel, 0);
            this.a = IHealthResultReceiver.Stub.asInterface(parcel.readStrongBinder());
            parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            HealthResultReceiver.super.writeToParcel(parcel, i);
            parcel.writeStrongBinder(this.a.asBinder());
            parcel.writeInt(2);
        }

        public void send(int i, Bundle bundle) {
            try {
                this.a.send(i, bundle);
            } catch (RemoteException unused) {
            }
        }

        public <T extends BaseResult> void registerListener(RemoteResultListener<T> remoteResultListener) {
            this.b = remoteResultListener;
        }

        public void cancel() throws RemoteException {
            this.c.set(true);
        }

        static /* synthetic */ void a(ForwardAsync forwardAsync, int i, Bundle bundle) {
            if (!forwardAsync.c.get() && forwardAsync.b != null) {
                bundle.setClassLoader(PermissionResult.class.getClassLoader());
                switch (bundle.getInt("type", -1)) {
                    case 1:
                        forwardAsync.b.onReceiveHealthResult(i, (ReadResult) bundle.getParcelable(IpcUtil.KEY_PARCEL));
                        break;
                    case 2:
                        forwardAsync.b.onReceiveHealthResult(i, (AggregateResult) bundle.getParcelable(IpcUtil.KEY_PARCEL));
                        break;
                    case 3:
                        int i2 = bundle.getInt("PERMISSION_RESULT_COUNT");
                        bundle.remove("PERMISSION_RESULT_COUNT");
                        bundle.remove("type");
                        forwardAsync.b.onReceiveHealthResult(i, new PermissionResult(bundle, i2));
                        break;
                    default:
                        forwardAsync.b.onReceiveHealthResult(i, (BaseResult) bundle.getParcelable(IpcUtil.KEY_PARCEL));
                        break;
                }
                forwardAsync.b = null;
            }
        }
    }

    public static class Sync extends HealthResultReceiver {
        private final int a;
        private final int b;
        private final Bundle c;

        public boolean isSync() {
            return true;
        }

        /* synthetic */ Sync(int i, int i2, Bundle bundle, byte b2) {
            this(i, i2, bundle);
        }

        /* synthetic */ Sync(Parcel parcel, byte b2) {
            this(parcel);
        }

        private Sync(int i, int i2, Bundle bundle) {
            super(0);
            this.a = i;
            this.b = i2;
            this.c = bundle;
        }

        private Sync(Parcel parcel) {
            super(parcel, 0);
            parcel.readStrongBinder();
            parcel.readInt();
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readBundle(HealthResultReceiver.a(this.b));
        }

        public void writeToParcel(Parcel parcel, int i) {
            HealthResultReceiver.super.writeToParcel(parcel, i);
            parcel.writeStrongBinder(null);
            parcel.writeInt(1);
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeBundle(this.c);
            parcel.writeParcelable(null, 0);
        }

        public Bundle getBundle() {
            return this.c;
        }
    }

    public int describeContents() {
        return 0;
    }

    public abstract boolean isSync();

    /* synthetic */ HealthResultReceiver(byte b) {
        this();
    }

    /* synthetic */ HealthResultReceiver(Parcel parcel, byte b) {
        this(parcel);
    }

    private HealthResultReceiver() {
        this.a = 0;
    }

    public static HealthResultReceiver createSyncResult(int i, int i2, Bundle bundle) {
        return new Sync(i, i2, bundle, 0);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
    }

    private HealthResultReceiver(Parcel parcel) {
        this.a = parcel.readInt();
    }

    static /* synthetic */ ClassLoader a(int i) {
        switch (i) {
            case 1:
                return ReadResult.class.getClassLoader();
            case 2:
                return AggregateResult.class.getClassLoader();
            case 3:
                return PermissionResult.class.getClassLoader();
            default:
                return BaseResult.class.getClassLoader();
        }
    }
}
