package com.shinobicontrols.charts;

class SChartGLErrorHandler {
    SChartGLErrorHandler() {
    }

    /* access modifiers changed from: 0000 */
    public void logMessageImpl(String str) {
        cx.a(str);
    }

    /* access modifiers changed from: 0000 */
    public void handleErrorImpl(String str) {
        cx.c(str);
        throw new Error(str);
    }
}
