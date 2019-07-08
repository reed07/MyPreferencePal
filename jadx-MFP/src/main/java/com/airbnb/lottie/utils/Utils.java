package com.airbnb.lottie.utils;

import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.L;
import com.airbnb.lottie.animation.content.TrimPathContent;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.Closeable;

public final class Utils {
    private static final float SQRT_2 = ((float) Math.sqrt(2.0d));
    private static float dpScale = -1.0f;
    private static final PathMeasure pathMeasure = new PathMeasure();
    private static final float[] points = new float[4];
    private static final Path tempPath = new Path();
    private static final Path tempPath2 = new Path();

    public static int hashFor(float f, float f2, float f3, float f4) {
        int i = f != BitmapDescriptorFactory.HUE_RED ? (int) (((float) 527) * f) : 17;
        if (f2 != BitmapDescriptorFactory.HUE_RED) {
            i = (int) (((float) (i * 31)) * f2);
        }
        if (f3 != BitmapDescriptorFactory.HUE_RED) {
            i = (int) (((float) (i * 31)) * f3);
        }
        return f4 != BitmapDescriptorFactory.HUE_RED ? (int) (((float) (i * 31)) * f4) : i;
    }

    public static boolean isAtLeastVersion(int i, int i2, int i3, int i4, int i5, int i6) {
        boolean z = false;
        if (i < i4) {
            return false;
        }
        if (i > i4) {
            return true;
        }
        if (i2 < i5) {
            return false;
        }
        if (i2 > i5) {
            return true;
        }
        if (i3 >= i6) {
            z = true;
        }
        return z;
    }

    private Utils() {
    }

    public static Path createPath(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4) {
        Path path = new Path();
        path.moveTo(pointF.x, pointF.y);
        if (pointF3 == null || pointF4 == null || (pointF3.length() == BitmapDescriptorFactory.HUE_RED && pointF4.length() == BitmapDescriptorFactory.HUE_RED)) {
            path.lineTo(pointF2.x, pointF2.y);
        } else {
            Path path2 = path;
            path2.cubicTo(pointF3.x + pointF.x, pointF.y + pointF3.y, pointF2.x + pointF4.x, pointF2.y + pointF4.y, pointF2.x, pointF2.y);
        }
        return path;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public static float getScale(Matrix matrix) {
        float[] fArr = points;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        float f = SQRT_2;
        fArr[2] = f;
        fArr[3] = f;
        matrix.mapPoints(fArr);
        float[] fArr2 = points;
        return ((float) Math.hypot((double) (fArr2[2] - fArr2[0]), (double) (fArr2[3] - fArr2[1]))) / 2.0f;
    }

    public static void applyTrimPathIfNeeded(Path path, @Nullable TrimPathContent trimPathContent) {
        if (trimPathContent != null) {
            applyTrimPathIfNeeded(path, ((Float) trimPathContent.getStart().getValue()).floatValue() / 100.0f, ((Float) trimPathContent.getEnd().getValue()).floatValue() / 100.0f, ((Float) trimPathContent.getOffset().getValue()).floatValue() / 360.0f);
        }
    }

    public static void applyTrimPathIfNeeded(Path path, float f, float f2, float f3) {
        L.beginSection("applyTrimPathIfNeeded");
        pathMeasure.setPath(path, false);
        float length = pathMeasure.getLength();
        if (f == 1.0f && f2 == BitmapDescriptorFactory.HUE_RED) {
            L.endSection("applyTrimPathIfNeeded");
        } else if (length < 1.0f || ((double) Math.abs((f2 - f) - 1.0f)) < 0.01d) {
            L.endSection("applyTrimPathIfNeeded");
        } else {
            float f4 = f * length;
            float f5 = f2 * length;
            float f6 = f3 * length;
            float min = Math.min(f4, f5) + f6;
            float max = Math.max(f4, f5) + f6;
            if (min >= length && max >= length) {
                min = (float) MiscUtils.floorMod(min, length);
                max = (float) MiscUtils.floorMod(max, length);
            }
            if (min < BitmapDescriptorFactory.HUE_RED) {
                min = (float) MiscUtils.floorMod(min, length);
            }
            if (max < BitmapDescriptorFactory.HUE_RED) {
                max = (float) MiscUtils.floorMod(max, length);
            }
            int i = (min > max ? 1 : (min == max ? 0 : -1));
            if (i == 0) {
                path.reset();
                L.endSection("applyTrimPathIfNeeded");
                return;
            }
            if (i >= 0) {
                min -= length;
            }
            tempPath.reset();
            pathMeasure.getSegment(min, max, tempPath, true);
            if (max > length) {
                tempPath2.reset();
                pathMeasure.getSegment(BitmapDescriptorFactory.HUE_RED, max % length, tempPath2, true);
                tempPath.addPath(tempPath2);
            } else if (min < BitmapDescriptorFactory.HUE_RED) {
                tempPath2.reset();
                pathMeasure.getSegment(min + length, length, tempPath2, true);
                tempPath.addPath(tempPath2);
            }
            path.set(tempPath);
            L.endSection("applyTrimPathIfNeeded");
        }
    }

    public static float dpScale() {
        if (dpScale == -1.0f) {
            dpScale = Resources.getSystem().getDisplayMetrics().density;
        }
        return dpScale;
    }
}
