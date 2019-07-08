package com.uacf.sync.provider.sdk.model;

import com.uacf.core.util.Strings;
import java.util.List;

public class SyncModeImport extends SyncMode {
    public SyncModeImport(List<String> list) {
        this(null, list);
    }

    public SyncModeImport(String str, List<String> list) {
        String str2;
        String str3;
        StringBuilder sb = new StringBuilder();
        sb.append("import-token");
        if (Strings.notEmpty(str)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("-");
            sb2.append(str);
            str2 = sb2.toString();
        } else {
            str2 = "";
        }
        sb.append(str2);
        String sb3 = sb.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append("import-finished");
        if (Strings.notEmpty(str)) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("-");
            sb5.append(str);
            str3 = sb5.toString();
        } else {
            str3 = "";
        }
        sb4.append(str3);
        super(sb3, sb4.toString(), list);
    }
}
