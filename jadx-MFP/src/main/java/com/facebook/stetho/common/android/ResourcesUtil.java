package com.facebook.stetho.common.android;

import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import com.facebook.stetho.common.LogUtil;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ResourcesUtil {
    private static int getResourcePackageId(int i) {
        return (i >>> 24) & 255;
    }

    private ResourcesUtil() {
    }

    @Nonnull
    public static String getIdStringQuietly(Object obj, @Nullable Resources resources, int i) {
        try {
            return getIdString(resources, i);
        } catch (NotFoundException unused) {
            String fallbackIdString = getFallbackIdString(i);
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown identifier encountered on ");
            sb.append(obj);
            sb.append(": ");
            sb.append(fallbackIdString);
            LogUtil.w(sb.toString());
            return fallbackIdString;
        }
    }

    public static String getIdString(@Nullable Resources resources, int i) throws NotFoundException {
        String str;
        String str2;
        if (resources == null) {
            return getFallbackIdString(i);
        }
        if (getResourcePackageId(i) != 127) {
            str2 = resources.getResourcePackageName(i);
            str = ":";
        } else {
            str2 = "";
            str = "";
        }
        String resourceTypeName = resources.getResourceTypeName(i);
        String resourceEntryName = resources.getResourceEntryName(i);
        StringBuilder sb = new StringBuilder(str2.length() + 1 + str.length() + resourceTypeName.length() + 1 + resourceEntryName.length());
        sb.append("@");
        sb.append(str2);
        sb.append(str);
        sb.append(resourceTypeName);
        sb.append("/");
        sb.append(resourceEntryName);
        return sb.toString();
    }

    private static String getFallbackIdString(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        sb.append(Integer.toHexString(i));
        return sb.toString();
    }
}
