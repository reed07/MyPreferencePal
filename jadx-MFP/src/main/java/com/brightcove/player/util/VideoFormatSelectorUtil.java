package com.brightcove.player.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.List;

public final class VideoFormatSelectorUtil {
    private static final float FRACTION_TO_CONSIDER_FULLSCREEN = 0.98f;

    public static int[] selectVideoFormatsForDefaultDisplay(Context context, List<? extends Representation> list, String[] strArr, boolean z) throws DecoderQueryException {
        Point viewportSize = getViewportSize(context);
        return selectVideoFormats(list, strArr, z, true, viewportSize.x, viewportSize.y);
    }

    public static int[] selectVideoFormats(List<? extends Representation> list, String[] strArr, boolean z, boolean z2, int i, int i2) throws DecoderQueryException {
        List<? extends Representation> list2 = list;
        ArrayList arrayList = new ArrayList();
        int maxH264DecodableFrameSize = MediaCodecUtil.maxH264DecodableFrameSize();
        int size = list.size();
        int i3 = Integer.MAX_VALUE;
        for (int i4 = 0; i4 < size; i4++) {
            Format format = ((Representation) list2.get(i4)).format;
            if (isFormatPlayable(format, strArr, z, maxH264DecodableFrameSize)) {
                arrayList.add(Integer.valueOf(i4));
                if (format.width <= 0 || format.height <= 0) {
                    boolean z3 = z2;
                    int i5 = i;
                    int i6 = i2;
                } else {
                    Point maxVideoSizeInViewport = getMaxVideoSizeInViewport(z2, i, i2, format.width, format.height);
                    int i7 = format.width * format.height;
                    if (format.width >= ((int) (((float) maxVideoSizeInViewport.x) * FRACTION_TO_CONSIDER_FULLSCREEN)) && format.height >= ((int) (((float) maxVideoSizeInViewport.y) * FRACTION_TO_CONSIDER_FULLSCREEN)) && i7 < i3) {
                        i3 = i7;
                    }
                }
            } else {
                boolean z4 = z2;
                int i8 = i;
                int i9 = i2;
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            Format format2 = ((Representation) list2.get(((Integer) arrayList.get(size2)).intValue())).format;
            if (format2.width > 0 && format2.height > 0 && format2.width * format2.height > i3) {
                arrayList.remove(size2);
            }
        }
        return Util.toArray(arrayList);
    }

    private static boolean isFormatPlayable(Format format, String[] strArr, boolean z, int i) {
        if (strArr != null && !Util.contains(strArr, format.containerMimeType)) {
            return false;
        }
        if (z && (format.width >= 1280 || format.height >= 720)) {
            return false;
        }
        if (format.width <= 0 || format.height <= 0 || format.width * format.height <= i) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000d, code lost:
        if (r1 != r3) goto L_0x0013;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Point getMaxVideoSizeInViewport(boolean r3, int r4, int r5, int r6, int r7) {
        /*
            if (r3 == 0) goto L_0x0010
            r3 = 1
            r0 = 0
            if (r6 <= r7) goto L_0x0008
            r1 = 1
            goto L_0x0009
        L_0x0008:
            r1 = 0
        L_0x0009:
            if (r4 <= r5) goto L_0x000c
            goto L_0x000d
        L_0x000c:
            r3 = 0
        L_0x000d:
            if (r1 == r3) goto L_0x0010
            goto L_0x0013
        L_0x0010:
            r2 = r5
            r5 = r4
            r4 = r2
        L_0x0013:
            int r3 = r6 * r4
            int r0 = r7 * r5
            if (r3 < r0) goto L_0x0023
            android.graphics.Point r3 = new android.graphics.Point
            int r4 = com.google.android.exoplayer2.util.Util.ceilDivide(r0, r6)
            r3.<init>(r5, r4)
            return r3
        L_0x0023:
            android.graphics.Point r5 = new android.graphics.Point
            int r3 = com.google.android.exoplayer2.util.Util.ceilDivide(r3, r7)
            r5.<init>(r3, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.util.VideoFormatSelectorUtil.getMaxVideoSizeInViewport(boolean, int, int, int, int):android.graphics.Point");
    }

    private static Point getViewportSize(Context context) {
        if (Util.MODEL == null || !Util.MODEL.startsWith("BRAVIA") || !context.getPackageManager().hasSystemFeature("com.sony.dtv.hardware.panel.qfhd")) {
            return getDisplaySize(((WindowManager) context.getSystemService("window")).getDefaultDisplay());
        }
        return new Point(3840, 2160);
    }

    private static Point getDisplaySize(Display display) {
        Point point = new Point();
        if (Util.SDK_INT >= 17) {
            getDisplaySizeV17(display, point);
        } else if (Util.SDK_INT >= 16) {
            getDisplaySizeV16(display, point);
        } else {
            getDisplaySizeV9(display, point);
        }
        return point;
    }

    @TargetApi(17)
    private static void getDisplaySizeV17(Display display, Point point) {
        display.getRealSize(point);
    }

    @TargetApi(16)
    private static void getDisplaySizeV16(Display display, Point point) {
        display.getSize(point);
    }

    private static void getDisplaySizeV9(Display display, Point point) {
        point.x = display.getWidth();
        point.y = display.getHeight();
    }

    private VideoFormatSelectorUtil() {
    }
}
