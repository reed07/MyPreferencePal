package com.amazon.device.ads;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import org.json.JSONException;
import org.json.JSONObject;

class DtbPackageNativeData {
    private static DtbPackageNativeData packageNativeDataInstance;
    private JSONObject json = new JSONObject();

    protected static synchronized DtbPackageNativeData getPackageNativeDataInstance(Context context) {
        DtbPackageNativeData dtbPackageNativeData;
        synchronized (DtbPackageNativeData.class) {
            if (packageNativeDataInstance == null) {
                packageNativeDataInstance = new DtbPackageNativeData(context);
            }
            dtbPackageNativeData = packageNativeDataInstance;
        }
        return dtbPackageNativeData;
    }

    private DtbPackageNativeData(Context context) {
        PackageInfo packageInfo;
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        String str = (String) packageManager.getApplicationLabel(context.getApplicationInfo());
        try {
            packageInfo = packageManager.getPackageInfo(packageName, 0);
        } catch (NameNotFoundException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Package ");
            sb.append(packageName);
            sb.append(" not found");
            DtbLog.error(sb.toString());
            packageInfo = null;
        }
        String str2 = packageInfo != null ? packageInfo.versionName : "";
        String num = packageInfo != null ? Integer.toString(packageInfo.versionCode) : "";
        try {
            this.json.put("lbl", str);
            this.json.put("pn", packageName);
            if (!num.equals("")) {
                this.json.put("v", num);
            }
            if (!str2.equals("")) {
                this.json.put("vn", str2);
            }
        } catch (JSONException unused2) {
            DtbLog.error("JSON exception while buildinf package native data");
        }
    }

    /* access modifiers changed from: protected */
    public JSONObject getParamsJson() {
        return this.json;
    }
}
