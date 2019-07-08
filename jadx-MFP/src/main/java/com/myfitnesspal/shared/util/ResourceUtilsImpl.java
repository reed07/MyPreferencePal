package com.myfitnesspal.shared.util;

import android.content.Context;

public class ResourceUtilsImpl implements ResourceUtils {
    public int getResourceIdentifier(Context context, String str) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier(str, "string", context.getPackageName());
    }
}
