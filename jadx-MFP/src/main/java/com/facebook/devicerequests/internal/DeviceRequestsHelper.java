package com.facebook.devicerequests.internal;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdManager.RegistrationListener;
import android.net.nsd.NsdServiceInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.SmartLoginOption;
import com.facebook.internal.Utility;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.EnumMap;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceRequestsHelper {
    static final String DEVICE_INFO_DEVICE = "device";
    static final String DEVICE_INFO_MODEL = "model";
    public static final String DEVICE_INFO_PARAM = "device_info";
    public static final String DEVICE_TARGET_USER_ID = "target_user_id";
    static final String SDK_FLAVOR = "android";
    static final String SDK_HEADER = "fbsdk";
    static final String SERVICE_TYPE = "_fb._tcp.";
    private static final String TAG = DeviceRequestsHelper.class.getCanonicalName();
    private static HashMap<String, RegistrationListener> deviceRequestsListeners = new HashMap<>();

    public static String getDeviceInfo() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device", Build.DEVICE);
            jSONObject.put("model", Build.MODEL);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public static boolean startAdvertisementService(String str) {
        if (isAvailable()) {
            return startAdvertisementServiceImpl(str);
        }
        return false;
    }

    public static boolean isAvailable() {
        return VERSION.SDK_INT >= 16 && FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId()).getSmartLoginOptions().contains(SmartLoginOption.Enabled);
    }

    public static Bitmap generateQRCode(String str) {
        EnumMap enumMap = new EnumMap(EncodeHintType.class);
        enumMap.put(EncodeHintType.MARGIN, Integer.valueOf(2));
        try {
            BitMatrix encode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 200, 200, enumMap);
            int height = encode.getHeight();
            int width = encode.getWidth();
            int[] iArr = new int[(height * width)];
            for (int i = 0; i < height; i++) {
                int i2 = i * width;
                for (int i3 = 0; i3 < width; i3++) {
                    iArr[i2 + i3] = encode.get(i3, i) ? -16777216 : -1;
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            try {
                createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
                return createBitmap;
            } catch (WriterException unused) {
                return createBitmap;
            }
        } catch (WriterException unused2) {
            return null;
        }
    }

    public static void cleanUpAdvertisementService(String str) {
        cleanUpAdvertisementServiceImpl(str);
    }

    @TargetApi(16)
    private static boolean startAdvertisementServiceImpl(final String str) {
        if (deviceRequestsListeners.containsKey(str)) {
            return true;
        }
        final String format = String.format("%s_%s_%s", new Object[]{SDK_HEADER, String.format("%s-%s", new Object[]{"android", FacebookSdk.getSdkVersion().replace('.', '|')}), str});
        NsdServiceInfo nsdServiceInfo = new NsdServiceInfo();
        nsdServiceInfo.setServiceType(SERVICE_TYPE);
        nsdServiceInfo.setServiceName(format);
        nsdServiceInfo.setPort(80);
        NsdManager nsdManager = (NsdManager) FacebookSdk.getApplicationContext().getSystemService("servicediscovery");
        AnonymousClass1 r4 = new RegistrationListener() {
            public void onServiceUnregistered(NsdServiceInfo nsdServiceInfo) {
            }

            public void onUnregistrationFailed(NsdServiceInfo nsdServiceInfo, int i) {
            }

            public void onServiceRegistered(NsdServiceInfo nsdServiceInfo) {
                if (!format.equals(nsdServiceInfo.getServiceName())) {
                    DeviceRequestsHelper.cleanUpAdvertisementService(str);
                }
            }

            public void onRegistrationFailed(NsdServiceInfo nsdServiceInfo, int i) {
                DeviceRequestsHelper.cleanUpAdvertisementService(str);
            }
        };
        deviceRequestsListeners.put(str, r4);
        nsdManager.registerService(nsdServiceInfo, 1, r4);
        return true;
    }

    @TargetApi(16)
    private static void cleanUpAdvertisementServiceImpl(String str) {
        RegistrationListener registrationListener = (RegistrationListener) deviceRequestsListeners.get(str);
        if (registrationListener != null) {
            try {
                ((NsdManager) FacebookSdk.getApplicationContext().getSystemService("servicediscovery")).unregisterService(registrationListener);
            } catch (IllegalArgumentException e) {
                Utility.logd(TAG, (Exception) e);
            }
            deviceRequestsListeners.remove(str);
        }
    }
}
