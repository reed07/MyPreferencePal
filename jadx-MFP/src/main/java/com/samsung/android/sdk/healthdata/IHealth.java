package com.samsung.android.sdk.healthdata;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.sdk.internal.healthdata.HealthResultReceiver;

public interface IHealth extends IInterface {

    public static abstract class Stub extends Binder implements IHealth {

        static class a implements IHealth {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final Bundle getUserProfile() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealth");
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final Bundle getConnectionResult(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealth");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final HealthResultReceiver waitForInit(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealth");
                    obtain.writeLong(j);
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (HealthResultReceiver) HealthResultReceiver.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IDeviceManager getIDeviceManager() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealth");
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.samsung.android.sdk.healthdata.IDeviceManager.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IDataResolver getIDataResolver() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealth");
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.samsung.android.sdk.healthdata.IDataResolver.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IDataWatcher getIDataWatcher() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealth");
                    this.a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.samsung.android.sdk.healthdata.IDataWatcher.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final HealthResultReceiver requestHealthDataPermissions(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealth");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (HealthResultReceiver) HealthResultReceiver.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final Bundle isHealthDataPermissionAcquired(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealth");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean isKeyAccessible() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealth");
                    boolean z = false;
                    this.a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final Bundle getConnectionResult2(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealth");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final Bundle getUserProfile2(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealth");
                    obtain.writeString(str);
                    this.a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void waitForInit2(String str, HealthResultReceiver healthResultReceiver, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealth");
                    obtain.writeString(str);
                    if (healthResultReceiver != null) {
                        obtain.writeInt(1);
                        healthResultReceiver.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeLong(j);
                    this.a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final Intent requestHealthDataPermissions2(String str, HealthResultReceiver healthResultReceiver, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealth");
                    obtain.writeString(str);
                    if (healthResultReceiver != null) {
                        obtain.writeInt(1);
                        healthResultReceiver.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final Bundle isHealthDataPermissionAcquired2(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealth");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.samsung.android.sdk.healthdata.IHealth");
        }

        public static IHealth asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.sdk.healthdata.IHealth");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IHealth)) {
                return new a(iBinder);
            }
            return (IHealth) queryLocalInterface;
        }

        /* JADX WARNING: type inference failed for: r2v0 */
        /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r2v2, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r2v3, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r2v4, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r2v5, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r2v6, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r2v7, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r2v9, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r2v10, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r2v12, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r2v13, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r2v15, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r2v16, types: [com.samsung.android.sdk.internal.healthdata.HealthResultReceiver] */
        /* JADX WARNING: type inference failed for: r2v18, types: [com.samsung.android.sdk.internal.healthdata.HealthResultReceiver] */
        /* JADX WARNING: type inference failed for: r2v19, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r2v22, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r2v23, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r2v25, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r2v26 */
        /* JADX WARNING: type inference failed for: r2v27 */
        /* JADX WARNING: type inference failed for: r2v28 */
        /* JADX WARNING: type inference failed for: r2v29 */
        /* JADX WARNING: type inference failed for: r2v30 */
        /* JADX WARNING: type inference failed for: r2v31 */
        /* JADX WARNING: type inference failed for: r2v32 */
        /* JADX WARNING: type inference failed for: r2v33 */
        /* JADX WARNING: type inference failed for: r2v34 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], android.os.IBinder, android.os.Bundle, com.samsung.android.sdk.internal.healthdata.HealthResultReceiver]
  uses: [android.os.IBinder, android.os.Bundle, com.samsung.android.sdk.internal.healthdata.HealthResultReceiver]
  mth insns count: 183
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 10 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r6, android.os.Parcel r7, android.os.Parcel r8, int r9) throws android.os.RemoteException {
            /*
                r5 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r6 == r0) goto L_0x01d9
                r0 = 0
                r2 = 0
                switch(r6) {
                    case 1: goto L_0x01c0;
                    case 2: goto L_0x019f;
                    case 3: goto L_0x0182;
                    case 4: goto L_0x016c;
                    case 5: goto L_0x0156;
                    case 6: goto L_0x0140;
                    case 7: goto L_0x0118;
                    case 8: goto L_0x00f0;
                    case 9: goto L_0x00e0;
                    case 10: goto L_0x00b8;
                    case 11: goto L_0x009b;
                    case 12: goto L_0x0078;
                    case 13: goto L_0x003c;
                    case 14: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r6 = super.onTransact(r6, r7, r8, r9)
                return r6
            L_0x0010:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r7.enforceInterface(r6)
                java.lang.String r6 = r7.readString()
                int r9 = r7.readInt()
                if (r9 == 0) goto L_0x0028
                android.os.Parcelable$Creator r9 = android.os.Bundle.CREATOR
                java.lang.Object r7 = r9.createFromParcel(r7)
                r2 = r7
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x0028:
                android.os.Bundle r6 = r5.isHealthDataPermissionAcquired2(r6, r2)
                r8.writeNoException()
                if (r6 == 0) goto L_0x0038
                r8.writeInt(r1)
                r6.writeToParcel(r8, r1)
                goto L_0x003b
            L_0x0038:
                r8.writeInt(r0)
            L_0x003b:
                return r1
            L_0x003c:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r7.enforceInterface(r6)
                java.lang.String r6 = r7.readString()
                int r9 = r7.readInt()
                if (r9 == 0) goto L_0x0054
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.HealthResultReceiver> r9 = com.samsung.android.sdk.internal.healthdata.HealthResultReceiver.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r7)
                com.samsung.android.sdk.internal.healthdata.HealthResultReceiver r9 = (com.samsung.android.sdk.internal.healthdata.HealthResultReceiver) r9
                goto L_0x0055
            L_0x0054:
                r9 = r2
            L_0x0055:
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x0064
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.lang.Object r7 = r2.createFromParcel(r7)
                r2 = r7
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x0064:
                android.content.Intent r6 = r5.requestHealthDataPermissions2(r6, r9, r2)
                r8.writeNoException()
                if (r6 == 0) goto L_0x0074
                r8.writeInt(r1)
                r6.writeToParcel(r8, r1)
                goto L_0x0077
            L_0x0074:
                r8.writeInt(r0)
            L_0x0077:
                return r1
            L_0x0078:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r7.enforceInterface(r6)
                java.lang.String r6 = r7.readString()
                int r9 = r7.readInt()
                if (r9 == 0) goto L_0x0090
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.HealthResultReceiver> r9 = com.samsung.android.sdk.internal.healthdata.HealthResultReceiver.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r7)
                r2 = r9
                com.samsung.android.sdk.internal.healthdata.HealthResultReceiver r2 = (com.samsung.android.sdk.internal.healthdata.HealthResultReceiver) r2
            L_0x0090:
                long r3 = r7.readLong()
                r5.waitForInit2(r6, r2, r3)
                r8.writeNoException()
                return r1
            L_0x009b:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r7.enforceInterface(r6)
                java.lang.String r6 = r7.readString()
                android.os.Bundle r6 = r5.getUserProfile2(r6)
                r8.writeNoException()
                if (r6 == 0) goto L_0x00b4
                r8.writeInt(r1)
                r6.writeToParcel(r8, r1)
                goto L_0x00b7
            L_0x00b4:
                r8.writeInt(r0)
            L_0x00b7:
                return r1
            L_0x00b8:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r7.enforceInterface(r6)
                int r6 = r7.readInt()
                if (r6 == 0) goto L_0x00cc
                android.os.Parcelable$Creator r6 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r6.createFromParcel(r7)
                r2 = r6
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x00cc:
                android.os.Bundle r6 = r5.getConnectionResult2(r2)
                r8.writeNoException()
                if (r6 == 0) goto L_0x00dc
                r8.writeInt(r1)
                r6.writeToParcel(r8, r1)
                goto L_0x00df
            L_0x00dc:
                r8.writeInt(r0)
            L_0x00df:
                return r1
            L_0x00e0:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r7.enforceInterface(r6)
                boolean r6 = r5.isKeyAccessible()
                r8.writeNoException()
                r8.writeInt(r6)
                return r1
            L_0x00f0:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r7.enforceInterface(r6)
                int r6 = r7.readInt()
                if (r6 == 0) goto L_0x0104
                android.os.Parcelable$Creator r6 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r6.createFromParcel(r7)
                r2 = r6
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x0104:
                android.os.Bundle r6 = r5.isHealthDataPermissionAcquired(r2)
                r8.writeNoException()
                if (r6 == 0) goto L_0x0114
                r8.writeInt(r1)
                r6.writeToParcel(r8, r1)
                goto L_0x0117
            L_0x0114:
                r8.writeInt(r0)
            L_0x0117:
                return r1
            L_0x0118:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r7.enforceInterface(r6)
                int r6 = r7.readInt()
                if (r6 == 0) goto L_0x012c
                android.os.Parcelable$Creator r6 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r6.createFromParcel(r7)
                r2 = r6
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x012c:
                com.samsung.android.sdk.internal.healthdata.HealthResultReceiver r6 = r5.requestHealthDataPermissions(r2)
                r8.writeNoException()
                if (r6 == 0) goto L_0x013c
                r8.writeInt(r1)
                r6.writeToParcel(r8, r1)
                goto L_0x013f
            L_0x013c:
                r8.writeInt(r0)
            L_0x013f:
                return r1
            L_0x0140:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r7.enforceInterface(r6)
                com.samsung.android.sdk.healthdata.IDataWatcher r6 = r5.getIDataWatcher()
                r8.writeNoException()
                if (r6 == 0) goto L_0x0152
                android.os.IBinder r2 = r6.asBinder()
            L_0x0152:
                r8.writeStrongBinder(r2)
                return r1
            L_0x0156:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r7.enforceInterface(r6)
                com.samsung.android.sdk.healthdata.IDataResolver r6 = r5.getIDataResolver()
                r8.writeNoException()
                if (r6 == 0) goto L_0x0168
                android.os.IBinder r2 = r6.asBinder()
            L_0x0168:
                r8.writeStrongBinder(r2)
                return r1
            L_0x016c:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r7.enforceInterface(r6)
                com.samsung.android.sdk.healthdata.IDeviceManager r6 = r5.getIDeviceManager()
                r8.writeNoException()
                if (r6 == 0) goto L_0x017e
                android.os.IBinder r2 = r6.asBinder()
            L_0x017e:
                r8.writeStrongBinder(r2)
                return r1
            L_0x0182:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r7.enforceInterface(r6)
                long r6 = r7.readLong()
                com.samsung.android.sdk.internal.healthdata.HealthResultReceiver r6 = r5.waitForInit(r6)
                r8.writeNoException()
                if (r6 == 0) goto L_0x019b
                r8.writeInt(r1)
                r6.writeToParcel(r8, r1)
                goto L_0x019e
            L_0x019b:
                r8.writeInt(r0)
            L_0x019e:
                return r1
            L_0x019f:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r7.enforceInterface(r6)
                java.lang.String r6 = r7.readString()
                int r7 = r7.readInt()
                android.os.Bundle r6 = r5.getConnectionResult(r6, r7)
                r8.writeNoException()
                if (r6 == 0) goto L_0x01bc
                r8.writeInt(r1)
                r6.writeToParcel(r8, r1)
                goto L_0x01bf
            L_0x01bc:
                r8.writeInt(r0)
            L_0x01bf:
                return r1
            L_0x01c0:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r7.enforceInterface(r6)
                android.os.Bundle r6 = r5.getUserProfile()
                r8.writeNoException()
                if (r6 == 0) goto L_0x01d5
                r8.writeInt(r1)
                r6.writeToParcel(r8, r1)
                goto L_0x01d8
            L_0x01d5:
                r8.writeInt(r0)
            L_0x01d8:
                return r1
            L_0x01d9:
                java.lang.String r6 = "com.samsung.android.sdk.healthdata.IHealth"
                r8.writeString(r6)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.healthdata.IHealth.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    Bundle getConnectionResult(String str, int i) throws RemoteException;

    Bundle getConnectionResult2(Bundle bundle) throws RemoteException;

    IDataResolver getIDataResolver() throws RemoteException;

    IDataWatcher getIDataWatcher() throws RemoteException;

    IDeviceManager getIDeviceManager() throws RemoteException;

    Bundle getUserProfile() throws RemoteException;

    Bundle getUserProfile2(String str) throws RemoteException;

    Bundle isHealthDataPermissionAcquired(Bundle bundle) throws RemoteException;

    Bundle isHealthDataPermissionAcquired2(String str, Bundle bundle) throws RemoteException;

    boolean isKeyAccessible() throws RemoteException;

    HealthResultReceiver requestHealthDataPermissions(Bundle bundle) throws RemoteException;

    Intent requestHealthDataPermissions2(String str, HealthResultReceiver healthResultReceiver, Bundle bundle) throws RemoteException;

    HealthResultReceiver waitForInit(long j) throws RemoteException;

    void waitForInit2(String str, HealthResultReceiver healthResultReceiver, long j) throws RemoteException;
}
