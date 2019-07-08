package org.apache.sanselan;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class FormatCompliance {
    private final ArrayList comments = new ArrayList();
    private final String description;
    private final boolean failOnError;

    public FormatCompliance(String str, boolean z) {
        this.description = str;
        this.failOnError = z;
    }

    public static final FormatCompliance getDefault() {
        return new FormatCompliance("ignore", false);
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        dump(new PrintWriter(stringWriter));
        return stringWriter.getBuffer().toString();
    }

    public void dump(PrintWriter printWriter) {
        StringBuilder sb = new StringBuilder();
        sb.append("Format Compliance: ");
        sb.append(this.description);
        printWriter.println(sb.toString());
        if (this.comments.size() == 0) {
            printWriter.println("\tNo comments.");
        } else {
            int i = 0;
            while (i < this.comments.size()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("\t");
                int i2 = i + 1;
                sb2.append(i2);
                sb2.append(": ");
                sb2.append(this.comments.get(i));
                printWriter.println(sb2.toString());
                i = i2;
            }
        }
        printWriter.println("");
        printWriter.flush();
    }
}
