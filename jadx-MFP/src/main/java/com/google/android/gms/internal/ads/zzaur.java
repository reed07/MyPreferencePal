package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzaur extends IInterface {
    int getAmount() throws RemoteException;

    String getType() throws RemoteException;
}
