package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.ConnectivityState;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class ConnectivityStateManager {
    private ArrayList<Listener> listeners = new ArrayList<>();
    private volatile ConnectivityState state = ConnectivityState.IDLE;

    private static final class Listener {
        final Runnable callback;
        final Executor executor;

        /* access modifiers changed from: 0000 */
        public void runInExecutor() {
            this.executor.execute(this.callback);
        }
    }

    ConnectivityStateManager() {
    }

    /* access modifiers changed from: 0000 */
    public void gotoState(@Nonnull ConnectivityState connectivityState) {
        Preconditions.checkNotNull(connectivityState, "newState");
        if (!(this.state == connectivityState || this.state == ConnectivityState.SHUTDOWN)) {
            this.state = connectivityState;
            if (!this.listeners.isEmpty()) {
                ArrayList<Listener> arrayList = this.listeners;
                this.listeners = new ArrayList<>();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Listener) it.next()).runInExecutor();
                }
            }
        }
    }
}
