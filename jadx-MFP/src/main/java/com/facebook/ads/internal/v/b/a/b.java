package com.facebook.ads.internal.v.b.a;

import com.facebook.ads.internal.v.b.a;
import com.facebook.ads.internal.v.b.l;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Locale;

public class b implements a {
    public File a;
    private final a b;
    private RandomAccessFile c;

    public b(File file, a aVar) {
        File file2;
        if (aVar != null) {
            try {
                this.b = aVar;
                File parentFile = file.getParentFile();
                if (parentFile.exists()) {
                    if (!parentFile.isDirectory()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("File ");
                        sb.append(parentFile);
                        sb.append(" is not directory!");
                        throw new IOException(sb.toString());
                    }
                } else if (!parentFile.mkdirs()) {
                    throw new IOException(String.format(Locale.US, "Directory %s can't be created", new Object[]{parentFile.getAbsolutePath()}));
                }
                boolean exists = file.exists();
                if (exists) {
                    file2 = file;
                } else {
                    File parentFile2 = file.getParentFile();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(file.getName());
                    sb2.append(".download");
                    file2 = new File(parentFile2, sb2.toString());
                }
                this.a = file2;
                this.c = new RandomAccessFile(this.a, exists ? "r" : "rw");
            } catch (IOException e) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Error using file ");
                sb3.append(file);
                sb3.append(" as disc cache");
                throw new l(sb3.toString(), e);
            }
        } else {
            throw new NullPointerException();
        }
    }

    public synchronized int a() {
        try {
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error reading length of file ");
            sb.append(this.a);
            throw new l(sb.toString(), e);
        }
        return (int) this.c.length();
    }

    public synchronized int a(byte[] bArr, long j, int i) {
        try {
            this.c.seek(j);
        } catch (IOException e) {
            throw new l(String.format(Locale.US, "Error reading %d bytes with offset %d from file[%d bytes] to buffer[%d bytes]", new Object[]{Integer.valueOf(i), Long.valueOf(j), Integer.valueOf(a()), Integer.valueOf(bArr.length)}), e);
        }
        return this.c.read(bArr, 0, i);
    }

    public synchronized void a(byte[] bArr, int i) {
        try {
            if (!d()) {
                this.c.seek((long) a());
                this.c.write(bArr, 0, i);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Error append cache: cache file ");
                sb.append(this.a);
                sb.append(" is completed!");
                throw new l(sb.toString());
            }
        } catch (IOException e) {
            String str = "Error writing %d bytes to %s from buffer with size %d";
            throw new l(String.format(Locale.US, str, new Object[]{Integer.valueOf(i), this.c, Integer.valueOf(bArr.length)}), e);
        }
    }

    public synchronized void b() {
        try {
            this.c.close();
            this.b.a(this.a);
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error closing file ");
            sb.append(this.a);
            throw new l(sb.toString(), e);
        }
    }

    public synchronized void c() {
        if (!d()) {
            b();
            File file = new File(this.a.getParentFile(), this.a.getName().substring(0, this.a.getName().length() - 9));
            if (this.a.renameTo(file)) {
                this.a = file;
                try {
                    this.c = new RandomAccessFile(this.a, "r");
                } catch (IOException e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Error opening ");
                    sb.append(this.a);
                    sb.append(" as disc cache");
                    throw new l(sb.toString(), e);
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Error renaming file ");
                sb2.append(this.a);
                sb2.append(" to ");
                sb2.append(file);
                sb2.append(" for completion!");
                throw new l(sb2.toString());
            }
        }
    }

    public synchronized boolean d() {
        return !this.a.getName().endsWith(".download");
    }
}
