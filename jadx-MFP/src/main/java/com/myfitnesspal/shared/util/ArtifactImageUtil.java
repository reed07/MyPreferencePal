package com.myfitnesspal.shared.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.view.View;
import java.io.IOException;

public class ArtifactImageUtil {
    public static void renderMeasuredViewToFile(View view, String str) throws IOException {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        writeBitmapToFile(createBitmap, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0024  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void writeBitmapToFile(android.graphics.Bitmap r2, java.lang.String r3) throws java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r3)
            r0.delete()
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0021 }
            r1.<init>(r3)     // Catch:{ all -> 0x0021 }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ all -> 0x001e }
            r0 = 100
            r2.compress(r3, r0, r1)     // Catch:{ all -> 0x001e }
            r1.close()
            if (r2 == 0) goto L_0x001d
            r2.recycle()
        L_0x001d:
            return
        L_0x001e:
            r3 = move-exception
            r0 = r1
            goto L_0x0022
        L_0x0021:
            r3 = move-exception
        L_0x0022:
            if (r0 == 0) goto L_0x0027
            r0.close()
        L_0x0027:
            if (r2 == 0) goto L_0x002c
            r2.recycle()
        L_0x002c:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.util.ArtifactImageUtil.writeBitmapToFile(android.graphics.Bitmap, java.lang.String):void");
    }
}
