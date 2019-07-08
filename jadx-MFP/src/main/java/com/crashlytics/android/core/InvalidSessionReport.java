package com.crashlytics.android.core;

import com.crashlytics.android.core.Report.Type;
import io.fabric.sdk.android.Fabric;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class InvalidSessionReport implements Report {
    private final Map<String, String> customHeaders = new HashMap(ReportUploader.HEADER_INVALID_CLS_FILE);
    private final File[] files;
    private final String identifier;

    public InvalidSessionReport(String str, File[] fileArr) {
        this.files = fileArr;
        this.identifier = str;
    }

    public String getFileName() {
        return this.files[0].getName();
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public File getFile() {
        return this.files[0];
    }

    public File[] getFiles() {
        return this.files;
    }

    public Map<String, String> getCustomHeaders() {
        return Collections.unmodifiableMap(this.customHeaders);
    }

    public void remove() {
        File[] fileArr;
        for (File file : this.files) {
            StringBuilder sb = new StringBuilder();
            sb.append("Removing invalid report file at ");
            sb.append(file.getPath());
            Fabric.getLogger().d("CrashlyticsCore", sb.toString());
            file.delete();
        }
    }

    public Type getType() {
        return Type.JAVA;
    }
}
