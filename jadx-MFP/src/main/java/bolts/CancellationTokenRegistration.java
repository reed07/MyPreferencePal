package bolts;

import java.io.Closeable;

public class CancellationTokenRegistration implements Closeable {
    private Runnable action;
    private boolean closed;
    private final Object lock;
    private CancellationTokenSource tokenSource;

    public void close() {
        synchronized (this.lock) {
            if (!this.closed) {
                this.closed = true;
                this.tokenSource.unregister(this);
                this.tokenSource = null;
                this.action = null;
            }
        }
    }
}
