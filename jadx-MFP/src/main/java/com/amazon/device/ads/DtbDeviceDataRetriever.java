package com.amazon.device.ads;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.myfitnesspal.shared.constants.Constants.Extras;

class DtbDeviceDataRetriever {
    private static int[][] rotationArray = {new int[]{1, 0, 9, 8}, new int[]{0, 9, 8, 1}};

    DtbDeviceDataRetriever() {
    }

    public static String getScreenSize(DisplayMetrics displayMetrics) {
        try {
            ((WindowManager) AdRegistration.getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(displayMetrics.widthPixels));
            sb.append(AvidJSONUtil.KEY_X);
            sb.append(String.valueOf(displayMetrics.heightPixels));
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getOrientation(Context context) {
        switch (determineCanonicalScreenOrientation(context)) {
            case 0:
            case 8:
                return Extras.ORIENTATION_LANDSCAPE;
            case 1:
            case 9:
                return Extras.ORIENTATION_PORTRAIT;
            default:
                return "unknown";
        }
    }

    private static int determineCanonicalScreenOrientation(Context context) {
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        int i = context.getResources().getConfiguration().orientation;
        boolean z = false;
        if (i == 1) {
            if (rotation == 0 || rotation == 2) {
                z = true;
            }
        } else if (i != 2) {
            z = true;
        } else if (rotation == 1 || rotation == 3) {
            z = true;
        }
        return rotationArray[!z][rotation];
    }
}
