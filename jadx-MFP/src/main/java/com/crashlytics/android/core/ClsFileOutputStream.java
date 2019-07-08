package com.crashlytics.android.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

class ClsFileOutputStream extends FileOutputStream {
    public static final FilenameFilter TEMP_FILENAME_FILTER = new FilenameFilter() {
        public boolean accept(File file, String str) {
            return str.endsWith(".cls_temp");
        }
    };
    private boolean closed = false;
    private File complete;
    private File inProgress;
    private final String root;

    public ClsFileOutputStream(File file, String str) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(".cls_temp");
        super(new File(file, sb.toString()));
        StringBuilder sb2 = new StringBuilder();
        sb2.append(file);
        sb2.append(File.separator);
        sb2.append(str);
        this.root = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(this.root);
        sb3.append(".cls_temp");
        this.inProgress = new File(sb3.toString());
    }

    public synchronized void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            super.flush();
            super.close();
            StringBuilder sb = new StringBuilder();
            sb.append(this.root);
            sb.append(".cls");
            File file = new File(sb.toString());
            if (this.inProgress.renameTo(file)) {
                this.inProgress = null;
                this.complete = file;
                return;
            }
            String str = "";
            if (file.exists()) {
                str = " (target already exists)";
            } else if (!this.inProgress.exists()) {
                str = " (source does not exist)";
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Could not rename temp file: ");
            sb2.append(this.inProgress);
            sb2.append(" -> ");
            sb2.append(file);
            sb2.append(str);
            throw new IOException(sb2.toString());
        }
    }

    public void closeInProgressStream() throws IOException {
        if (!this.closed) {
            this.closed = true;
            super.flush();
            super.close();
        }
    }
}
