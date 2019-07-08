package com.samsung.android.sdk.accessory;

import android.content.Context;
import android.util.Log;
import com.samsung.android.sdk.SsdkInterface;
import com.samsung.android.sdk.SsdkUnsupportedException;

public class SA implements SsdkInterface {
    public static final int DEVICE_ACCESSORY = 0;
    public static final int SERVICE_FILETRANSFER = 1;
    public static final int SERVICE_MESSAGE = 2;
    public static final int SERVICE_SOCKET = 3;
    private i a;
    private boolean b = false;

    /* access modifiers changed from: protected */
    public void clearSdkConfig() {
        this.a = null;
    }

    public int getVersionCode() {
        return 9;
    }

    public String getVersionName() {
        return "2.5.3";
    }

    public void initialize(Context context) throws SsdkUnsupportedException {
        if (context == null) {
            throw new IllegalArgumentException("Illegal argument: context");
        } else if (this.a == null) {
            if (!this.b) {
                StringBuilder sb = new StringBuilder(String.valueOf(context.getPackageName()));
                sb.append("#");
                sb.append(getVersionCode());
                f.a(context, sb.toString());
                this.b = true;
            }
            try {
                this.a = new i(context);
                Log.d("[SA_SDK]SA", "Initializing SA");
                a.a().a(context);
            } catch (c e) {
                throw new SsdkUnsupportedException(e.getMessage(), e.a());
            }
        }
    }

    public boolean isFeatureEnabled(int i) {
        switch (i) {
            case 0:
            case 3:
                return true;
            case 1:
                return i.j();
            case 2:
                return i.g();
            default:
                throw new IllegalArgumentException();
        }
    }
}
