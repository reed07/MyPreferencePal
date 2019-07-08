package io.grpc;

import io.grpc.Context.Storage;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ThreadLocalContextStorage extends Storage {
    private static final ThreadLocal<Context> localContext = new ThreadLocal<>();
    private static final Logger log = Logger.getLogger(ThreadLocalContextStorage.class.getName());

    ThreadLocalContextStorage() {
    }

    public Context doAttach(Context context) {
        Context current = current();
        localContext.set(context);
        return current;
    }

    public void detach(Context context, Context context2) {
        if (current() != context) {
            log.log(Level.SEVERE, "Context was not attached when detaching", new Throwable().fillInStackTrace());
        }
        doAttach(context2);
    }

    public Context current() {
        return (Context) localContext.get();
    }
}
