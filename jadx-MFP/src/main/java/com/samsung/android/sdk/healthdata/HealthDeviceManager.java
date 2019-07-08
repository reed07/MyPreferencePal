package com.samsung.android.sdk.healthdata;

import android.os.RemoteException;
import com.samsung.android.sdk.internal.healthdata.ErrorUtil;
import java.util.List;

public class HealthDeviceManager {
    private final HealthDataStore a;

    public HealthDeviceManager(HealthDataStore healthDataStore) {
        this.a = healthDataStore;
    }

    private IDeviceManager a() {
        try {
            IDeviceManager iDeviceManager = HealthDataStore.getInterface(this.a).getIDeviceManager();
            if (iDeviceManager != null) {
                return iDeviceManager;
            }
            throw new IllegalStateException("IDeviceManager is null");
        } catch (RemoteException e) {
            throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
        }
    }

    public HealthDevice getLocalDevice() {
        b();
        try {
            return a().getLocalDevice();
        } catch (RemoteException e) {
            throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
        }
    }

    public List<HealthDevice> getAllDevices() {
        b();
        try {
            return a().getAllRegisteredDevices();
        } catch (RemoteException e) {
            throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
        }
    }

    public String registerDevice(HealthDevice healthDevice) {
        if (healthDevice != null) {
            IDeviceManager a2 = a();
            b();
            try {
                return a2.registerDevice(healthDevice);
            } catch (RemoteException e) {
                throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
            }
        } else {
            throw new IllegalArgumentException(ErrorUtil.getNullArgumentMessage());
        }
    }

    public HealthDevice getDeviceBySeed(String str) {
        a(str);
        b();
        try {
            return a().getRegisteredDevice(str);
        } catch (RemoteException e) {
            throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
        }
    }

    public HealthDevice getDeviceByUuid(String str) {
        a(str);
        b();
        try {
            return a().getRegisteredDeviceByUuid(str);
        } catch (RemoteException e) {
            throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
        }
    }

    public List<String> getDeviceUuidsByGroup(int i) {
        b();
        try {
            return a().getDeviceUuidsBy(Integer.toString(i), 0);
        } catch (RemoteException e) {
            throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
        }
    }

    public List<String> getDeviceUuidsByCustomName(String str) {
        a(str);
        b();
        try {
            return a().getDeviceUuidsBy(str, 1);
        } catch (RemoteException e) {
            throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
        }
    }

    public List<String> getDeviceUuidsByModel(String str) {
        a(str);
        b();
        try {
            return a().getDeviceUuidsBy(str, 2);
        } catch (RemoteException e) {
            throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
        }
    }

    public List<String> getDeviceUuidsByManufacturer(String str) {
        a(str);
        b();
        try {
            return a().getDeviceUuidsBy(str, 3);
        } catch (RemoteException e) {
            throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
        }
    }

    public boolean changeCustomName(String str, String str2) {
        a(str);
        a(str2);
        b();
        try {
            return a().changeDeviceName(str, str2);
        } catch (RemoteException e) {
            throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
        }
    }

    private static void a(String str) {
        if (str == null) {
            throw new IllegalArgumentException(ErrorUtil.getNullArgumentMessage());
        }
    }

    private void b() {
        if (a() == null) {
            throw new IllegalStateException("Illegal store connection state");
        }
    }
}
