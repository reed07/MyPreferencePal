package org.apache.commons.io.monitor;

import java.io.File;
import java.io.Serializable;

public class FileEntry implements Serializable {
    static final FileEntry[] EMPTY_ENTRIES = new FileEntry[0];
    private FileEntry[] children;
    private boolean directory;
    private boolean exists;
    private final File file;
    private long lastModified;
    private long length;
    private String name;
    private final FileEntry parent;

    public FileEntry(FileEntry fileEntry, File file2) {
        if (file2 != null) {
            this.file = file2;
            this.parent = fileEntry;
            this.name = file2.getName();
            return;
        }
        throw new IllegalArgumentException("File is missing");
    }

    public boolean refresh(File file2) {
        boolean z = this.exists;
        long j = this.lastModified;
        boolean z2 = this.directory;
        long j2 = this.length;
        this.name = file2.getName();
        this.exists = file2.exists();
        this.directory = this.exists ? file2.isDirectory() : false;
        long j3 = 0;
        this.lastModified = this.exists ? file2.lastModified() : 0;
        if (this.exists && !this.directory) {
            j3 = file2.length();
        }
        this.length = j3;
        if (this.exists == z && this.lastModified == j && this.directory == z2 && this.length == j2) {
            return false;
        }
        return true;
    }

    public FileEntry newChildInstance(File file2) {
        return new FileEntry(this, file2);
    }

    public FileEntry[] getChildren() {
        FileEntry[] fileEntryArr = this.children;
        return fileEntryArr != null ? fileEntryArr : EMPTY_ENTRIES;
    }

    public void setChildren(FileEntry[] fileEntryArr) {
        this.children = fileEntryArr;
    }

    public File getFile() {
        return this.file;
    }

    public boolean isExists() {
        return this.exists;
    }

    public boolean isDirectory() {
        return this.directory;
    }
}
