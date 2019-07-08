package io.grpc;

import java.util.concurrent.atomic.AtomicLong;

@Internal
public final class InternalLogId {
    private static final AtomicLong idAlloc = new AtomicLong();
    private final long id;
    private final String tag;

    public static InternalLogId allocate(String str) {
        return new InternalLogId(str, getNextId());
    }

    static long getNextId() {
        return idAlloc.incrementAndGet();
    }

    protected InternalLogId(String str, long j) {
        this.tag = str;
        this.id = j;
    }

    public long getId() {
        return this.id;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.tag);
        sb.append("-");
        sb.append(this.id);
        return sb.toString();
    }
}
