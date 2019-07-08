package org.apache.commons.io.input;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class NullReader extends Reader {
    private boolean eof;
    private long mark;
    private final boolean markSupported;
    private long position;
    private long readlimit;
    private final long size;
    private final boolean throwEofException;

    /* access modifiers changed from: protected */
    public int processChar() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void processChars(char[] cArr, int i, int i2) {
    }

    public void close() throws IOException {
        this.eof = false;
        this.position = 0;
        this.mark = -1;
    }

    public synchronized void mark(int i) {
        if (this.markSupported) {
            this.mark = this.position;
            this.readlimit = (long) i;
        } else {
            throw new UnsupportedOperationException("Mark not supported");
        }
    }

    public boolean markSupported() {
        return this.markSupported;
    }

    public int read() throws IOException {
        if (!this.eof) {
            long j = this.position;
            if (j == this.size) {
                return doEndOfFile();
            }
            this.position = j + 1;
            return processChar();
        }
        throw new IOException("Read after end of file");
    }

    public int read(char[] cArr) throws IOException {
        return read(cArr, 0, cArr.length);
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        if (!this.eof) {
            long j = this.position;
            long j2 = this.size;
            if (j == j2) {
                return doEndOfFile();
            }
            this.position = j + ((long) i2);
            long j3 = this.position;
            if (j3 > j2) {
                i2 -= (int) (j3 - j2);
                this.position = j2;
            }
            processChars(cArr, i, i2);
            return i2;
        }
        throw new IOException("Read after end of file");
    }

    public synchronized void reset() throws IOException {
        if (!this.markSupported) {
            throw new UnsupportedOperationException("Mark not supported");
        } else if (this.mark < 0) {
            throw new IOException("No position has been marked");
        } else if (this.position <= this.mark + this.readlimit) {
            this.position = this.mark;
            this.eof = false;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Marked position [");
            sb.append(this.mark);
            sb.append("] is no longer valid - passed the read limit [");
            sb.append(this.readlimit);
            sb.append("]");
            throw new IOException(sb.toString());
        }
    }

    public long skip(long j) throws IOException {
        if (!this.eof) {
            long j2 = this.position;
            long j3 = this.size;
            if (j2 == j3) {
                return (long) doEndOfFile();
            }
            this.position = j2 + j;
            long j4 = this.position;
            if (j4 > j3) {
                j -= j4 - j3;
                this.position = j3;
            }
            return j;
        }
        throw new IOException("Skip after end of file");
    }

    private int doEndOfFile() throws EOFException {
        this.eof = true;
        if (!this.throwEofException) {
            return -1;
        }
        throw new EOFException();
    }
}
