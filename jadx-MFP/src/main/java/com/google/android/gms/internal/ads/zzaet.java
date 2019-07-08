package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzaet extends IInterface {
    void onUnconfirmedClickCancelled() throws RemoteException;

    void onUnconfirmedClickReceived(String str) throws RemoteException;
}
