package com.google.ads.interactivemedia.v3.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Queue;

/* compiled from: IMASDK */
final class qt {
    private final BufferedReader a;
    private final Queue<String> b;
    private String c;

    public qt(Queue<String> queue, BufferedReader bufferedReader) {
        this.b = queue;
        this.a = bufferedReader;
    }

    public final boolean a() throws IOException {
        if (this.c != null) {
            return true;
        }
        if (!this.b.isEmpty()) {
            this.c = (String) this.b.poll();
            return true;
        }
        do {
            String readLine = this.a.readLine();
            this.c = readLine;
            if (readLine == null) {
                return false;
            }
            this.c = this.c.trim();
        } while (this.c.isEmpty());
        return true;
    }

    public final String b() throws IOException {
        if (!a()) {
            return null;
        }
        String str = this.c;
        this.c = null;
        return str;
    }
}
