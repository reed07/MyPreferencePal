package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;
import java.util.Map;

public interface zzbik extends IInterface {
    void beginAdUnitExposure(String str) throws RemoteException;

    void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException;

    void endAdUnitExposure(String str) throws RemoteException;

    long generateEventId() throws RemoteException;

    String getAppIdOrigin() throws RemoteException;

    String getAppInstanceId() throws RemoteException;

    List getConditionalUserProperties(String str, String str2) throws RemoteException;

    String getCurrentScreenClass() throws RemoteException;

    String getCurrentScreenName() throws RemoteException;

    String getGmpAppId() throws RemoteException;

    int getMaxUserProperties(String str) throws RemoteException;

    Map getUserProperties(String str, String str2, boolean z) throws RemoteException;

    void logEvent(String str, String str2, Bundle bundle) throws RemoteException;

    void performAction(Bundle bundle) throws RemoteException;

    Bundle performActionWithResponse(Bundle bundle) throws RemoteException;

    void setConditionalUserProperty(Bundle bundle) throws RemoteException;

    void zza(String str, String str2, IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzb(IObjectWrapper iObjectWrapper, String str, String str2) throws RemoteException;
}
