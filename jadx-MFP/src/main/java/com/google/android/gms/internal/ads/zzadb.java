package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzadb extends IInterface {
    double getScale() throws RemoteException;

    Uri getUri() throws RemoteException;

    IObjectWrapper zzsa() throws RemoteException;
}
