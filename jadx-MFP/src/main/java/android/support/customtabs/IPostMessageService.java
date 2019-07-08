package android.support.customtabs;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPostMessageService extends IInterface {

    public static abstract class Stub extends Binder implements IPostMessageService {
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "android.support.customtabs.IPostMessageService");
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                Bundle bundle = null;
                switch (i) {
                    case 2:
                        parcel.enforceInterface("android.support.customtabs.IPostMessageService");
                        ICustomTabsCallback asInterface = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(parcel.readStrongBinder());
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        onMessageChannelReady(asInterface, bundle);
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface("android.support.customtabs.IPostMessageService");
                        ICustomTabsCallback asInterface2 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(parcel.readStrongBinder());
                        String readString = parcel.readString();
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        onPostMessage(asInterface2, readString, bundle);
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("android.support.customtabs.IPostMessageService");
                return true;
            }
        }
    }

    void onMessageChannelReady(ICustomTabsCallback iCustomTabsCallback, Bundle bundle) throws RemoteException;

    void onPostMessage(ICustomTabsCallback iCustomTabsCallback, String str, Bundle bundle) throws RemoteException;
}
