package com.uacf.core.logging;

public class StdOutPrinter extends PrinterBase {
    public int println(int i, String str) {
        if (!this.enabled) {
            return 0;
        }
        System.err.println(processMessage(str));
        return 1;
    }

    /* access modifiers changed from: protected */
    public String processMessage(String str) {
        return String.format("[StdOutPrint] [%s] [%s] %s", new Object[]{Thread.currentThread().getStackTrace()[5].getFileName(), Thread.currentThread().getName(), str});
    }
}
