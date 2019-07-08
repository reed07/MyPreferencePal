package com.shinobicontrols.charts;

class SChartGLDrawer {
    private final long nativeHandle = 0;

    private native void alloc(boolean z, SChartGLErrorHandler sChartGLErrorHandler);

    private native void delete();

    /* access modifiers changed from: 0000 */
    public native void beginRender(boolean z);

    /* access modifiers changed from: 0000 */
    public native void drawBandSeriesFill(float[] fArr, Series<?> series, float[] fArr2, int i, int i2, int i3, int i4, int i5, float[] fArr3);

    /* access modifiers changed from: 0000 */
    public native void drawBarColumnFill(float[] fArr, Series<?> series, float[] fArr2, int i, int i2, int i3, int i4, int i5, float f, float f2, int i6, boolean z, float[] fArr3);

    /* access modifiers changed from: 0000 */
    public native void drawBarColumnLine(float[] fArr, Series<?> series, float[] fArr2, int i, int i2, int i3, boolean z, boolean z2, float f, float f2, float f3, int i4, float[] fArr3);

    /* access modifiers changed from: 0000 */
    public native void drawCandlesticks(float[] fArr, Series<?> series, int i, int[] iArr, int[] iArr2, int i2, int i3, boolean z, boolean z2, float f, float f2, float f3, int i4, float f4, float[] fArr2);

    /* access modifiers changed from: 0000 */
    public native void drawDataPoints(float[] fArr, Series<?> series, int[] iArr, int i, int i2, int i3, float f, float[] fArr2, float f2, float f3, int i4, float[] fArr3, boolean z);

    /* access modifiers changed from: 0000 */
    public native void drawHorizontalFill(float[] fArr, Series<?> series, int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, float f, float f2, float f3, int i6, boolean z3, float[] fArr2);

    /* access modifiers changed from: 0000 */
    public native void drawLineStrip(float[] fArr, Series<?> series, int i, int i2, int i3, float f, float f2, int i4, float[] fArr2);

    /* access modifiers changed from: 0000 */
    public native void drawOHLCPoints(float[] fArr, Series<?> series, int i, int[] iArr, int[] iArr2, int i2, float f, float f2, float f3, float[] fArr2);

    /* access modifiers changed from: 0000 */
    public native void drawRadialSlice(int i, Series<?> series, float f, float f2, float f3, float f4, float f5, int i2, int i3, boolean z, float f6, int i4, float f7);

    /* access modifiers changed from: 0000 */
    public native boolean endRender(AnimationManager animationManager);

    /* access modifiers changed from: 0000 */
    public native boolean getPerformCalculations();

    /* access modifiers changed from: 0000 */
    public native void setFrameBufferSize(int i, int i2);

    /* access modifiers changed from: 0000 */
    public native void setPerformCalculations(boolean z);

    static {
        System.loadLibrary("shinobicharts-android");
    }

    SChartGLDrawer(boolean z, SChartGLErrorHandler sChartGLErrorHandler) {
        alloc(z, sChartGLErrorHandler);
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        delete();
    }
}
