package com.uacf.core.logging;

import java.util.Arrays;
import java.util.List;

public class MultiPrinter implements Printer {
    private final List<Printer> printers;

    public MultiPrinter(Printer... printerArr) {
        this.printers = Arrays.asList(printerArr);
    }

    public void printException(Throwable th, Object obj, Object... objArr) {
        for (Printer printException : this.printers) {
            printException.printException(th, obj, objArr);
        }
    }

    public int println(int i, String str) {
        int i2 = 0;
        for (Printer printer : this.printers) {
            int println = printer.println(i, str);
            if (printer instanceof LogcatPrinter) {
                i2 = println;
            }
        }
        return i2;
    }

    public void clear() {
        for (Printer clear : this.printers) {
            clear.clear();
        }
    }

    public void setEnabled(boolean z) {
        for (Printer enabled : this.printers) {
            enabled.setEnabled(z);
        }
    }

    public void setEnabled(Class<? extends Printer> cls, boolean z) {
        for (Printer printer : this.printers) {
            if (printer.getClass().isAssignableFrom(cls)) {
                printer.setEnabled(z);
            }
        }
    }
}
