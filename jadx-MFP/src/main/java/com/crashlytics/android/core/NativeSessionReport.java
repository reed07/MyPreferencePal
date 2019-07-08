package com.crashlytics.android.core;

import com.crashlytics.android.core.Report.Type;
import io.fabric.sdk.android.Fabric;
import java.io.File;
import java.util.Map;

class NativeSessionReport implements Report {
    private final File reportDirectory;

    public Map<String, String> getCustomHeaders() {
        return null;
    }

    public File getFile() {
        return null;
    }

    public String getFileName() {
        return null;
    }

    public NativeSessionReport(File file) {
        this.reportDirectory = file;
    }

    public void remove() {
        File[] files;
        for (File file : getFiles()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Removing native report file at ");
            sb.append(file.getPath());
            Fabric.getLogger().d("CrashlyticsCore", sb.toString());
            file.delete();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Removing native report directory at ");
        sb2.append(this.reportDirectory);
        Fabric.getLogger().d("CrashlyticsCore", sb2.toString());
        this.reportDirectory.delete();
    }

    public String getIdentifier() {
        return this.reportDirectory.getName();
    }

    public File[] getFiles() {
        return this.reportDirectory.listFiles();
    }

    public Type getType() {
        return Type.NATIVE;
    }
}
