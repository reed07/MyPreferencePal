package com.myfitnesspal.shared.logging;

import com.myfitnesspal.shared.util.CrashlyticsUtil;
import com.uacf.core.logging.PrinterBase;

public class CrashlyticsPrinter extends PrinterBase {
    public void printException(Throwable th, Object obj, Object... objArr) {
        if (this.enabled) {
            CrashlyticsUtil.logIfEnabled(th);
        }
    }
}
