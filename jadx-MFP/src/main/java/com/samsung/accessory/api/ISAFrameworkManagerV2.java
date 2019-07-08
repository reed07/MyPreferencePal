package com.samsung.accessory.api;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
import com.samsung.android.sdk.accessory.SAPeerAgent;

public interface ISAFrameworkManagerV2 extends IInterface {

    public static abstract class Stub extends Binder implements ISAFrameworkManagerV2 {

        static class Proxy implements ISAFrameworkManagerV2 {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public Bundle acceptServiceConnection(long j, String str, SAPeerAgent sAPeerAgent, long j2, ISAServiceConnectionCallback iSAServiceConnectionCallback, ISAServiceChannelCallback iSAServiceChannelCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    if (sAPeerAgent != null) {
                        obtain.writeInt(1);
                        sAPeerAgent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeLong(j2);
                    Bundle bundle = null;
                    obtain.writeStrongBinder(iSAServiceConnectionCallback != null ? iSAServiceConnectionCallback.asBinder() : null);
                    obtain.writeStrongBinder(iSAServiceChannelCallback != null ? iSAServiceChannelCallback.asBinder() : null);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public int authenticatePeerAgent(long j, String str, SAPeerAgent sAPeerAgent, ISAPeerAgentAuthCallback iSAPeerAgentAuthCallback, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    if (sAPeerAgent != null) {
                        obtain.writeInt(1);
                        sAPeerAgent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSAPeerAgentAuthCallback != null ? iSAPeerAgentAuthCallback.asBinder() : null);
                    obtain.writeLong(j2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void cleanupAgent(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int closeServiceConnection(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int findPeerAgents(long j, long j2, String str, ISAPeerAgentCallback iSAPeerAgentCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSAPeerAgentCallback != null ? iSAPeerAgentCallback.asBinder() : null);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getAgentDetails(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getAgentId(long j, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ResultReceiver getClientCallback(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getLocalAgentId(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isSocketConnected(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(13, obtain, obtain2, 0);
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

            public Bundle makeFrameworkConnection(int i, String str, IDeathCallback iDeathCallback, int i2, ISAServiceConnectionIndicationCallback iSAServiceConnectionIndicationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    Bundle bundle = null;
                    obtain.writeStrongBinder(iDeathCallback != null ? iDeathCallback.asBinder() : null);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(iSAServiceConnectionIndicationCallback != null ? iSAServiceConnectionIndicationCallback.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registerComponent(long j, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registerMexCallback(long j, String str, ISAMexCallback iSAMexCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSAMexCallback != null ? iSAMexCallback.asBinder() : null);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int rejectServiceConnection(long j, String str, SAPeerAgent sAPeerAgent, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    if (sAPeerAgent != null) {
                        obtain.writeInt(1);
                        sAPeerAgent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeLong(j2);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int requestServiceConnection(long j, String str, SAPeerAgent sAPeerAgent, ISAServiceConnectionCallback iSAServiceConnectionCallback, ISAServiceChannelCallback iSAServiceChannelCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    if (sAPeerAgent != null) {
                        obtain.writeInt(1);
                        sAPeerAgent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iSAServiceConnectionCallback != null ? iSAServiceConnectionCallback.asBinder() : null);
                    if (iSAServiceChannelCallback != null) {
                        iBinder = iSAServiceChannelCallback.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int send(long j, String str, long j2, byte[] bArr, boolean z, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeLong(j2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int sendCompressed(long j, String str, long j2, byte[] bArr, boolean z, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeLong(j2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int sendMessage(long j, String str, String str2, long j2, byte[] bArr, boolean z, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeLong(j2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void sendMessageDeliveryStatus(long j, long j2, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int sendUncompressed(long j, String str, long j2, byte[] bArr, boolean z, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeLong(j2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int tearFrameworkConnection(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregisterMexCallback(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ISAFrameworkManagerV2 asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ISAFrameworkManagerV2)) ? new Proxy(iBinder) : (ISAFrameworkManagerV2) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int i3 = i;
            Parcel parcel3 = parcel;
            Parcel parcel4 = parcel2;
            if (i3 != 1598968902) {
                boolean z = false;
                switch (i3) {
                    case 1:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        Bundle makeFrameworkConnection = makeFrameworkConnection(parcel.readInt(), parcel.readString(), com.samsung.accessory.api.IDeathCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), com.samsung.accessory.api.ISAServiceConnectionIndicationCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        if (makeFrameworkConnection != null) {
                            parcel4.writeInt(1);
                            makeFrameworkConnection.writeToParcel(parcel4, 1);
                        } else {
                            parcel4.writeInt(0);
                        }
                        return true;
                    case 2:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        registerComponent(parcel.readLong(), parcel.createByteArray());
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        Bundle localAgentId = getLocalAgentId(parcel.readLong(), parcel.readString());
                        parcel2.writeNoException();
                        if (localAgentId != null) {
                            parcel4.writeInt(1);
                            localAgentId.writeToParcel(parcel4, 1);
                        } else {
                            parcel4.writeInt(0);
                        }
                        return true;
                    case 4:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        int findPeerAgents = findPeerAgents(parcel.readLong(), parcel.readLong(), parcel.readString(), com.samsung.accessory.api.ISAPeerAgentCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel4.writeInt(findPeerAgents);
                        return true;
                    case 5:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        int authenticatePeerAgent = authenticatePeerAgent(parcel.readLong(), parcel.readString(), parcel.readInt() != 0 ? (SAPeerAgent) SAPeerAgent.CREATOR.createFromParcel(parcel3) : null, com.samsung.accessory.api.ISAPeerAgentAuthCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                        parcel2.writeNoException();
                        parcel4.writeInt(authenticatePeerAgent);
                        return true;
                    case 6:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        int requestServiceConnection = requestServiceConnection(parcel.readLong(), parcel.readString(), parcel.readInt() != 0 ? (SAPeerAgent) SAPeerAgent.CREATOR.createFromParcel(parcel3) : null, com.samsung.accessory.api.ISAServiceConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), com.samsung.accessory.api.ISAServiceChannelCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel4.writeInt(requestServiceConnection);
                        return true;
                    case 7:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        Bundle acceptServiceConnection = acceptServiceConnection(parcel.readLong(), parcel.readString(), parcel.readInt() != 0 ? (SAPeerAgent) SAPeerAgent.CREATOR.createFromParcel(parcel3) : null, parcel.readLong(), com.samsung.accessory.api.ISAServiceConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), com.samsung.accessory.api.ISAServiceChannelCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        if (acceptServiceConnection != null) {
                            parcel4.writeInt(1);
                            acceptServiceConnection.writeToParcel(parcel4, 1);
                        } else {
                            parcel4.writeInt(0);
                        }
                        return true;
                    case 8:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        int rejectServiceConnection = rejectServiceConnection(parcel.readLong(), parcel.readString(), parcel.readInt() != 0 ? (SAPeerAgent) SAPeerAgent.CREATOR.createFromParcel(parcel3) : null, parcel.readLong());
                        parcel2.writeNoException();
                        parcel4.writeInt(rejectServiceConnection);
                        return true;
                    case 9:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        long readLong = parcel.readLong();
                        String readString = parcel.readString();
                        long readLong2 = parcel.readLong();
                        byte[] createByteArray = parcel.createByteArray();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        int send = send(readLong, readString, readLong2, createByteArray, z, parcel.readInt(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel4.writeInt(send);
                        return true;
                    case 10:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        int closeServiceConnection = closeServiceConnection(parcel.readLong(), parcel.readString());
                        parcel2.writeNoException();
                        parcel4.writeInt(closeServiceConnection);
                        return true;
                    case 11:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        Bundle agentDetails = getAgentDetails(parcel.readLong(), parcel.readString());
                        parcel2.writeNoException();
                        if (agentDetails != null) {
                            parcel4.writeInt(1);
                            agentDetails.writeToParcel(parcel4, 1);
                        } else {
                            parcel4.writeInt(0);
                        }
                        return true;
                    case 12:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        int tearFrameworkConnection = tearFrameworkConnection(parcel.readLong());
                        parcel2.writeNoException();
                        parcel4.writeInt(tearFrameworkConnection);
                        return true;
                    case 13:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        boolean isSocketConnected = isSocketConnected(parcel.readLong(), parcel.readString());
                        parcel2.writeNoException();
                        parcel4.writeInt(isSocketConnected ? 1 : 0);
                        return true;
                    case 14:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        ResultReceiver clientCallback = getClientCallback(parcel.readLong());
                        parcel2.writeNoException();
                        if (clientCallback != null) {
                            parcel4.writeInt(1);
                            clientCallback.writeToParcel(parcel4, 1);
                        } else {
                            parcel4.writeInt(0);
                        }
                        return true;
                    case 15:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        registerMexCallback(parcel.readLong(), parcel.readString(), com.samsung.accessory.api.ISAMexCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 16:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        unregisterMexCallback(parcel.readLong(), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 17:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        long readLong3 = parcel.readLong();
                        String readString2 = parcel.readString();
                        String readString3 = parcel.readString();
                        long readLong4 = parcel.readLong();
                        byte[] createByteArray2 = parcel.createByteArray();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        int sendMessage = sendMessage(readLong3, readString2, readString3, readLong4, createByteArray2, z, parcel.readInt(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel4.writeInt(sendMessage);
                        return true;
                    case 18:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        sendMessageDeliveryStatus(parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 19:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        cleanupAgent(parcel.readLong(), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 20:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        String agentId = getAgentId(parcel.readLong(), parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel4.writeString(agentId);
                        return true;
                    case 21:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        long readLong5 = parcel.readLong();
                        String readString4 = parcel.readString();
                        long readLong6 = parcel.readLong();
                        byte[] createByteArray3 = parcel.createByteArray();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        int sendUncompressed = sendUncompressed(readLong5, readString4, readLong6, createByteArray3, z, parcel.readInt(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel4.writeInt(sendUncompressed);
                        return true;
                    case 22:
                        parcel3.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                        long readLong7 = parcel.readLong();
                        String readString5 = parcel.readString();
                        long readLong8 = parcel.readLong();
                        byte[] createByteArray4 = parcel.createByteArray();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        int sendCompressed = sendCompressed(readLong7, readString5, readLong8, createByteArray4, z, parcel.readInt(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel4.writeInt(sendCompressed);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel4.writeString("com.samsung.accessory.api.ISAFrameworkManagerV2");
                return true;
            }
        }
    }

    Bundle acceptServiceConnection(long j, String str, SAPeerAgent sAPeerAgent, long j2, ISAServiceConnectionCallback iSAServiceConnectionCallback, ISAServiceChannelCallback iSAServiceChannelCallback) throws RemoteException;

    int authenticatePeerAgent(long j, String str, SAPeerAgent sAPeerAgent, ISAPeerAgentAuthCallback iSAPeerAgentAuthCallback, long j2) throws RemoteException;

    void cleanupAgent(long j, String str) throws RemoteException;

    int closeServiceConnection(long j, String str) throws RemoteException;

    int findPeerAgents(long j, long j2, String str, ISAPeerAgentCallback iSAPeerAgentCallback) throws RemoteException;

    Bundle getAgentDetails(long j, String str) throws RemoteException;

    String getAgentId(long j, String str, String str2) throws RemoteException;

    ResultReceiver getClientCallback(long j) throws RemoteException;

    Bundle getLocalAgentId(long j, String str) throws RemoteException;

    boolean isSocketConnected(long j, String str) throws RemoteException;

    Bundle makeFrameworkConnection(int i, String str, IDeathCallback iDeathCallback, int i2, ISAServiceConnectionIndicationCallback iSAServiceConnectionIndicationCallback) throws RemoteException;

    void registerComponent(long j, byte[] bArr) throws RemoteException;

    void registerMexCallback(long j, String str, ISAMexCallback iSAMexCallback) throws RemoteException;

    int rejectServiceConnection(long j, String str, SAPeerAgent sAPeerAgent, long j2) throws RemoteException;

    int requestServiceConnection(long j, String str, SAPeerAgent sAPeerAgent, ISAServiceConnectionCallback iSAServiceConnectionCallback, ISAServiceChannelCallback iSAServiceChannelCallback) throws RemoteException;

    int send(long j, String str, long j2, byte[] bArr, boolean z, int i, int i2, int i3) throws RemoteException;

    int sendCompressed(long j, String str, long j2, byte[] bArr, boolean z, int i, int i2, int i3) throws RemoteException;

    int sendMessage(long j, String str, String str2, long j2, byte[] bArr, boolean z, int i, int i2, int i3) throws RemoteException;

    void sendMessageDeliveryStatus(long j, long j2, int i, int i2) throws RemoteException;

    int sendUncompressed(long j, String str, long j2, byte[] bArr, boolean z, int i, int i2, int i3) throws RemoteException;

    int tearFrameworkConnection(long j) throws RemoteException;

    void unregisterMexCallback(long j, String str) throws RemoteException;
}
