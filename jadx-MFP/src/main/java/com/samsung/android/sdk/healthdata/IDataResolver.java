package com.samsung.android.sdk.healthdata;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl;
import com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl;
import com.samsung.android.sdk.internal.healthdata.HealthResultReceiver;
import com.samsung.android.sdk.internal.healthdata.InsertRequestImpl;
import com.samsung.android.sdk.internal.healthdata.ReadRequestImpl;
import com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl;

public interface IDataResolver extends IInterface {

    public static abstract class Stub extends Binder implements IDataResolver {

        static class a implements IDataResolver {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final HealthResultReceiver readData(ReadRequestImpl readRequestImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDataResolver");
                    if (readRequestImpl != null) {
                        obtain.writeInt(1);
                        readRequestImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (HealthResultReceiver) HealthResultReceiver.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final HealthResultReceiver insertData(InsertRequestImpl insertRequestImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDataResolver");
                    if (insertRequestImpl != null) {
                        obtain.writeInt(1);
                        insertRequestImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (HealthResultReceiver) HealthResultReceiver.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final HealthResultReceiver deleteData(DeleteRequestImpl deleteRequestImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDataResolver");
                    if (deleteRequestImpl != null) {
                        obtain.writeInt(1);
                        deleteRequestImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (HealthResultReceiver) HealthResultReceiver.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final HealthResultReceiver aggregateData(AggregateRequestImpl aggregateRequestImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDataResolver");
                    if (aggregateRequestImpl != null) {
                        obtain.writeInt(1);
                        aggregateRequestImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (HealthResultReceiver) HealthResultReceiver.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final HealthResultReceiver updateData(UpdateRequestImpl updateRequestImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDataResolver");
                    if (updateRequestImpl != null) {
                        obtain.writeInt(1);
                        updateRequestImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (HealthResultReceiver) HealthResultReceiver.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void readData2(String str, HealthResultReceiver healthResultReceiver, ReadRequestImpl readRequestImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDataResolver");
                    obtain.writeString(str);
                    if (healthResultReceiver != null) {
                        obtain.writeInt(1);
                        healthResultReceiver.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (readRequestImpl != null) {
                        obtain.writeInt(1);
                        readRequestImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void insertData2(String str, HealthResultReceiver healthResultReceiver, InsertRequestImpl insertRequestImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDataResolver");
                    obtain.writeString(str);
                    if (healthResultReceiver != null) {
                        obtain.writeInt(1);
                        healthResultReceiver.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (insertRequestImpl != null) {
                        obtain.writeInt(1);
                        insertRequestImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void deleteData2(String str, HealthResultReceiver healthResultReceiver, DeleteRequestImpl deleteRequestImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDataResolver");
                    obtain.writeString(str);
                    if (healthResultReceiver != null) {
                        obtain.writeInt(1);
                        healthResultReceiver.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (deleteRequestImpl != null) {
                        obtain.writeInt(1);
                        deleteRequestImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void aggregateData2(String str, HealthResultReceiver healthResultReceiver, AggregateRequestImpl aggregateRequestImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDataResolver");
                    obtain.writeString(str);
                    if (healthResultReceiver != null) {
                        obtain.writeInt(1);
                        healthResultReceiver.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (aggregateRequestImpl != null) {
                        obtain.writeInt(1);
                        aggregateRequestImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void updateData2(String str, HealthResultReceiver healthResultReceiver, UpdateRequestImpl updateRequestImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDataResolver");
                    obtain.writeString(str);
                    if (healthResultReceiver != null) {
                        obtain.writeInt(1);
                        healthResultReceiver.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (updateRequestImpl != null) {
                        obtain.writeInt(1);
                        updateRequestImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
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
            attachInterface(this, "com.samsung.android.sdk.healthdata.IDataResolver");
        }

        public static IDataResolver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.sdk.healthdata.IDataResolver");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDataResolver)) {
                return new a(iBinder);
            }
            return (IDataResolver) queryLocalInterface;
        }

        /* JADX WARNING: type inference failed for: r2v0 */
        /* JADX WARNING: type inference failed for: r2v1, types: [com.samsung.android.sdk.internal.healthdata.ReadRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v3, types: [com.samsung.android.sdk.internal.healthdata.ReadRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v4, types: [com.samsung.android.sdk.internal.healthdata.InsertRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v6, types: [com.samsung.android.sdk.internal.healthdata.InsertRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v7, types: [com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v9, types: [com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v10, types: [com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v13, types: [com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v15, types: [com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v16, types: [com.samsung.android.sdk.internal.healthdata.ReadRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v18, types: [com.samsung.android.sdk.internal.healthdata.ReadRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v19, types: [com.samsung.android.sdk.internal.healthdata.InsertRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v21, types: [com.samsung.android.sdk.internal.healthdata.InsertRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v22, types: [com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v24, types: [com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v25, types: [com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v27, types: [com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v28, types: [com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v30, types: [com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl] */
        /* JADX WARNING: type inference failed for: r2v31 */
        /* JADX WARNING: type inference failed for: r2v32 */
        /* JADX WARNING: type inference failed for: r2v33 */
        /* JADX WARNING: type inference failed for: r2v34 */
        /* JADX WARNING: type inference failed for: r2v35 */
        /* JADX WARNING: type inference failed for: r2v36 */
        /* JADX WARNING: type inference failed for: r2v37 */
        /* JADX WARNING: type inference failed for: r2v38 */
        /* JADX WARNING: type inference failed for: r2v39 */
        /* JADX WARNING: type inference failed for: r2v40 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.samsung.android.sdk.internal.healthdata.InsertRequestImpl, com.samsung.android.sdk.internal.healthdata.ReadRequestImpl, com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl, com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl, com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl]
  uses: [com.samsung.android.sdk.internal.healthdata.ReadRequestImpl, com.samsung.android.sdk.internal.healthdata.InsertRequestImpl, com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl, com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl, com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl]
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
        /* JADX WARNING: Unknown variable types count: 11 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r4 == r0) goto L_0x01c3
                r0 = 0
                r2 = 0
                switch(r4) {
                    case 1: goto L_0x019b;
                    case 2: goto L_0x0173;
                    case 3: goto L_0x014b;
                    case 4: goto L_0x0123;
                    case 5: goto L_0x00fb;
                    case 6: goto L_0x00cc;
                    case 7: goto L_0x009d;
                    case 8: goto L_0x006e;
                    case 9: goto L_0x003f;
                    case 10: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0010:
                java.lang.String r4 = "com.samsung.android.sdk.healthdata.IDataResolver"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0028
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.HealthResultReceiver> r7 = com.samsung.android.sdk.internal.healthdata.HealthResultReceiver.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                com.samsung.android.sdk.internal.healthdata.HealthResultReceiver r7 = (com.samsung.android.sdk.internal.healthdata.HealthResultReceiver) r7
                goto L_0x0029
            L_0x0028:
                r7 = r2
            L_0x0029:
                int r0 = r5.readInt()
                if (r0 == 0) goto L_0x0038
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl> r0 = com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl.CREATOR
                java.lang.Object r5 = r0.createFromParcel(r5)
                r2 = r5
                com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl r2 = (com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl) r2
            L_0x0038:
                r3.updateData2(r4, r7, r2)
                r6.writeNoException()
                return r1
            L_0x003f:
                java.lang.String r4 = "com.samsung.android.sdk.healthdata.IDataResolver"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0057
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.HealthResultReceiver> r7 = com.samsung.android.sdk.internal.healthdata.HealthResultReceiver.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                com.samsung.android.sdk.internal.healthdata.HealthResultReceiver r7 = (com.samsung.android.sdk.internal.healthdata.HealthResultReceiver) r7
                goto L_0x0058
            L_0x0057:
                r7 = r2
            L_0x0058:
                int r0 = r5.readInt()
                if (r0 == 0) goto L_0x0067
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl> r0 = com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl.CREATOR
                java.lang.Object r5 = r0.createFromParcel(r5)
                r2 = r5
                com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl r2 = (com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl) r2
            L_0x0067:
                r3.aggregateData2(r4, r7, r2)
                r6.writeNoException()
                return r1
            L_0x006e:
                java.lang.String r4 = "com.samsung.android.sdk.healthdata.IDataResolver"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0086
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.HealthResultReceiver> r7 = com.samsung.android.sdk.internal.healthdata.HealthResultReceiver.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                com.samsung.android.sdk.internal.healthdata.HealthResultReceiver r7 = (com.samsung.android.sdk.internal.healthdata.HealthResultReceiver) r7
                goto L_0x0087
            L_0x0086:
                r7 = r2
            L_0x0087:
                int r0 = r5.readInt()
                if (r0 == 0) goto L_0x0096
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl> r0 = com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl.CREATOR
                java.lang.Object r5 = r0.createFromParcel(r5)
                r2 = r5
                com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl r2 = (com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl) r2
            L_0x0096:
                r3.deleteData2(r4, r7, r2)
                r6.writeNoException()
                return r1
            L_0x009d:
                java.lang.String r4 = "com.samsung.android.sdk.healthdata.IDataResolver"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x00b5
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.HealthResultReceiver> r7 = com.samsung.android.sdk.internal.healthdata.HealthResultReceiver.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                com.samsung.android.sdk.internal.healthdata.HealthResultReceiver r7 = (com.samsung.android.sdk.internal.healthdata.HealthResultReceiver) r7
                goto L_0x00b6
            L_0x00b5:
                r7 = r2
            L_0x00b6:
                int r0 = r5.readInt()
                if (r0 == 0) goto L_0x00c5
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.InsertRequestImpl> r0 = com.samsung.android.sdk.internal.healthdata.InsertRequestImpl.CREATOR
                java.lang.Object r5 = r0.createFromParcel(r5)
                r2 = r5
                com.samsung.android.sdk.internal.healthdata.InsertRequestImpl r2 = (com.samsung.android.sdk.internal.healthdata.InsertRequestImpl) r2
            L_0x00c5:
                r3.insertData2(r4, r7, r2)
                r6.writeNoException()
                return r1
            L_0x00cc:
                java.lang.String r4 = "com.samsung.android.sdk.healthdata.IDataResolver"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x00e4
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.HealthResultReceiver> r7 = com.samsung.android.sdk.internal.healthdata.HealthResultReceiver.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                com.samsung.android.sdk.internal.healthdata.HealthResultReceiver r7 = (com.samsung.android.sdk.internal.healthdata.HealthResultReceiver) r7
                goto L_0x00e5
            L_0x00e4:
                r7 = r2
            L_0x00e5:
                int r0 = r5.readInt()
                if (r0 == 0) goto L_0x00f4
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.ReadRequestImpl> r0 = com.samsung.android.sdk.internal.healthdata.ReadRequestImpl.CREATOR
                java.lang.Object r5 = r0.createFromParcel(r5)
                r2 = r5
                com.samsung.android.sdk.internal.healthdata.ReadRequestImpl r2 = (com.samsung.android.sdk.internal.healthdata.ReadRequestImpl) r2
            L_0x00f4:
                r3.readData2(r4, r7, r2)
                r6.writeNoException()
                return r1
            L_0x00fb:
                java.lang.String r4 = "com.samsung.android.sdk.healthdata.IDataResolver"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x010f
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl> r4 = com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl r2 = (com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl) r2
            L_0x010f:
                com.samsung.android.sdk.internal.healthdata.HealthResultReceiver r4 = r3.updateData(r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x011f
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x0122
            L_0x011f:
                r6.writeInt(r0)
            L_0x0122:
                return r1
            L_0x0123:
                java.lang.String r4 = "com.samsung.android.sdk.healthdata.IDataResolver"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0137
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl> r4 = com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl r2 = (com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl) r2
            L_0x0137:
                com.samsung.android.sdk.internal.healthdata.HealthResultReceiver r4 = r3.aggregateData(r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x0147
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x014a
            L_0x0147:
                r6.writeInt(r0)
            L_0x014a:
                return r1
            L_0x014b:
                java.lang.String r4 = "com.samsung.android.sdk.healthdata.IDataResolver"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x015f
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl> r4 = com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl r2 = (com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl) r2
            L_0x015f:
                com.samsung.android.sdk.internal.healthdata.HealthResultReceiver r4 = r3.deleteData(r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x016f
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x0172
            L_0x016f:
                r6.writeInt(r0)
            L_0x0172:
                return r1
            L_0x0173:
                java.lang.String r4 = "com.samsung.android.sdk.healthdata.IDataResolver"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0187
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.InsertRequestImpl> r4 = com.samsung.android.sdk.internal.healthdata.InsertRequestImpl.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.samsung.android.sdk.internal.healthdata.InsertRequestImpl r2 = (com.samsung.android.sdk.internal.healthdata.InsertRequestImpl) r2
            L_0x0187:
                com.samsung.android.sdk.internal.healthdata.HealthResultReceiver r4 = r3.insertData(r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x0197
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x019a
            L_0x0197:
                r6.writeInt(r0)
            L_0x019a:
                return r1
            L_0x019b:
                java.lang.String r4 = "com.samsung.android.sdk.healthdata.IDataResolver"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x01af
                android.os.Parcelable$Creator<com.samsung.android.sdk.internal.healthdata.ReadRequestImpl> r4 = com.samsung.android.sdk.internal.healthdata.ReadRequestImpl.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.samsung.android.sdk.internal.healthdata.ReadRequestImpl r2 = (com.samsung.android.sdk.internal.healthdata.ReadRequestImpl) r2
            L_0x01af:
                com.samsung.android.sdk.internal.healthdata.HealthResultReceiver r4 = r3.readData(r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x01bf
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x01c2
            L_0x01bf:
                r6.writeInt(r0)
            L_0x01c2:
                return r1
            L_0x01c3:
                java.lang.String r4 = "com.samsung.android.sdk.healthdata.IDataResolver"
                r6.writeString(r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.healthdata.IDataResolver.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    HealthResultReceiver aggregateData(AggregateRequestImpl aggregateRequestImpl) throws RemoteException;

    void aggregateData2(String str, HealthResultReceiver healthResultReceiver, AggregateRequestImpl aggregateRequestImpl) throws RemoteException;

    HealthResultReceiver deleteData(DeleteRequestImpl deleteRequestImpl) throws RemoteException;

    void deleteData2(String str, HealthResultReceiver healthResultReceiver, DeleteRequestImpl deleteRequestImpl) throws RemoteException;

    HealthResultReceiver insertData(InsertRequestImpl insertRequestImpl) throws RemoteException;

    void insertData2(String str, HealthResultReceiver healthResultReceiver, InsertRequestImpl insertRequestImpl) throws RemoteException;

    HealthResultReceiver readData(ReadRequestImpl readRequestImpl) throws RemoteException;

    void readData2(String str, HealthResultReceiver healthResultReceiver, ReadRequestImpl readRequestImpl) throws RemoteException;

    HealthResultReceiver updateData(UpdateRequestImpl updateRequestImpl) throws RemoteException;

    void updateData2(String str, HealthResultReceiver healthResultReceiver, UpdateRequestImpl updateRequestImpl) throws RemoteException;
}
