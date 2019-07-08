package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzadx extends IInterface {
    void destroy() throws RemoteException;

    List<String> getAvailableAssetNames() throws RemoteException;

    String getCustomTemplateId() throws RemoteException;

    zzyp getVideoController() throws RemoteException;

    void performClick(String str) throws RemoteException;

    void recordImpression() throws RemoteException;

    String zzbq(String str) throws RemoteException;

    zzadb zzbr(String str) throws RemoteException;

    boolean zzi(IObjectWrapper iObjectWrapper) throws RemoteException;

    IObjectWrapper zzsc() throws RemoteException;

    IObjectWrapper zzsg() throws RemoteException;
}
