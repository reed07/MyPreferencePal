package com.mopub.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import com.mopub.volley.Network;
import com.mopub.volley.RequestQueue;
import java.io.File;

public class Volley {
    public static RequestQueue newRequestQueue(Context context, BaseHttpStack baseHttpStack) {
        BasicNetwork basicNetwork;
        if (baseHttpStack != null) {
            basicNetwork = new BasicNetwork(baseHttpStack);
        } else if (VERSION.SDK_INT >= 9) {
            basicNetwork = new BasicNetwork((BaseHttpStack) new HurlStack());
        } else {
            String str = "volley/0";
            try {
                String packageName = context.getPackageName();
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
                StringBuilder sb = new StringBuilder();
                sb.append(packageName);
                sb.append("/");
                sb.append(packageInfo.versionCode);
                str = sb.toString();
            } catch (NameNotFoundException unused) {
            }
            basicNetwork = new BasicNetwork((HttpStack) new HttpClientStack(AndroidHttpClient.newInstance(str)));
        }
        return newRequestQueue(context, (Network) basicNetwork);
    }

    @Deprecated
    public static RequestQueue newRequestQueue(Context context, HttpStack httpStack) {
        if (httpStack == null) {
            return newRequestQueue(context, (BaseHttpStack) null);
        }
        return newRequestQueue(context, (Network) new BasicNetwork(httpStack));
    }

    private static RequestQueue newRequestQueue(Context context, Network network) {
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(new File(context.getCacheDir(), "volley")), network);
        requestQueue.start();
        return requestQueue;
    }

    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, (BaseHttpStack) null);
    }
}
