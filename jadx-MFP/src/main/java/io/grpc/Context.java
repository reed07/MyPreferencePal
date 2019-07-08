package io.grpc;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Context {
    private static final PersistentHashArrayMappedTrie<Key<?>, Object> EMPTY_ENTRIES = new PersistentHashArrayMappedTrie<>();
    public static final Context ROOT = new Context(null, EMPTY_ENTRIES);
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(Context.class.getName());
    private static final AtomicReference<Storage> storage = new AtomicReference<>();
    final CancellableContext cancellableAncestor;
    final int generation;
    final PersistentHashArrayMappedTrie<Key<?>, Object> keyValueEntries;
    private ArrayList<ExecutableListener> listeners;
    private CancellationListener parentListener = new ParentListener(this, null);

    /* renamed from: io.grpc.Context$1 reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context this$0;
        final /* synthetic */ Runnable val$r;

        public void run() {
            Context attach = this.this$0.attach();
            try {
                this.val$r.run();
            } finally {
                this.this$0.detach(attach);
            }
        }
    }

    public static final class CancellableContext extends Context implements Closeable {
        private Throwable cancellationCause;
        private boolean cancelled;
        private final Deadline deadline;
        private ScheduledFuture<?> pendingDeadline;
        private final Context uncancellableSurrogate;

        /* access modifiers changed from: 0000 */
        public boolean canBeCancelled() {
            return true;
        }

        public Context attach() {
            return this.uncancellableSurrogate.attach();
        }

        public void detach(Context context) {
            this.uncancellableSurrogate.detach(context);
        }

        public boolean cancel(Throwable th) {
            boolean z;
            synchronized (this) {
                z = true;
                if (!this.cancelled) {
                    this.cancelled = true;
                    if (this.pendingDeadline != null) {
                        this.pendingDeadline.cancel(false);
                        this.pendingDeadline = null;
                    }
                    this.cancellationCause = th;
                } else {
                    z = false;
                }
            }
            if (z) {
                notifyAndClearListeners();
            }
            return z;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
            return false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x000d, code lost:
            if (io.grpc.Context.super.isCancelled() == false) goto L_0x0017;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
            cancel(io.grpc.Context.super.cancellationCause());
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean isCancelled() {
            /*
                r2 = this;
                monitor-enter(r2)
                boolean r0 = r2.cancelled     // Catch:{ all -> 0x0019 }
                r1 = 1
                if (r0 == 0) goto L_0x0008
                monitor-exit(r2)     // Catch:{ all -> 0x0019 }
                return r1
            L_0x0008:
                monitor-exit(r2)     // Catch:{ all -> 0x0019 }
                boolean r0 = io.grpc.Context.super.isCancelled()
                if (r0 == 0) goto L_0x0017
                java.lang.Throwable r0 = io.grpc.Context.super.cancellationCause()
                r2.cancel(r0)
                return r1
            L_0x0017:
                r0 = 0
                return r0
            L_0x0019:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0019 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.Context.CancellableContext.isCancelled():boolean");
        }

        public Throwable cancellationCause() {
            if (isCancelled()) {
                return this.cancellationCause;
            }
            return null;
        }

        public Deadline getDeadline() {
            return this.deadline;
        }

        public void close() {
            cancel(null);
        }
    }

    public interface CancellationListener {
        void cancelled(Context context);
    }

    private enum DirectExecutor implements Executor {
        INSTANCE;

        public String toString() {
            return "Context.DirectExecutor";
        }

        public void execute(Runnable runnable) {
            runnable.run();
        }
    }

    private class ExecutableListener implements Runnable {
        private final Executor executor;
        /* access modifiers changed from: private */
        public final CancellationListener listener;

        /* synthetic */ ExecutableListener(Context context, Executor executor2, CancellationListener cancellationListener, AnonymousClass1 r4) {
            this(executor2, cancellationListener);
        }

        private ExecutableListener(Executor executor2, CancellationListener cancellationListener) {
            this.executor = executor2;
            this.listener = cancellationListener;
        }

        /* access modifiers changed from: private */
        public void deliver() {
            try {
                this.executor.execute(this);
            } catch (Throwable th) {
                Context.log.log(Level.INFO, "Exception notifying context listener", th);
            }
        }

        public void run() {
            this.listener.cancelled(Context.this);
        }
    }

    public static final class Key<T> {
        private final T defaultValue;
        private final String name;

        Key(String str) {
            this(str, null);
        }

        Key(String str, T t) {
            this.name = (String) Context.checkNotNull(str, "name");
            this.defaultValue = t;
        }

        public T get() {
            return get(Context.current());
        }

        public T get(Context context) {
            T access$900 = context.lookup(this);
            return access$900 == null ? this.defaultValue : access$900;
        }

        public String toString() {
            return this.name;
        }
    }

    private class ParentListener implements CancellationListener {
        private ParentListener() {
        }

        /* synthetic */ ParentListener(Context context, AnonymousClass1 r2) {
            this();
        }

        public void cancelled(Context context) {
            Context context2 = Context.this;
            if (context2 instanceof CancellableContext) {
                ((CancellableContext) context2).cancel(context.cancellationCause());
            } else {
                context2.notifyAndClearListeners();
            }
        }
    }

    public static abstract class Storage {
        public abstract Context current();

        public abstract void detach(Context context, Context context2);

        @Deprecated
        public void attach(Context context) {
            throw new UnsupportedOperationException("Deprecated. Do not call.");
        }

        public Context doAttach(Context context) {
            Context current = current();
            attach(context);
            return current;
        }
    }

    static Storage storage() {
        Storage storage2 = (Storage) storage.get();
        return storage2 == null ? createStorage() : storage2;
    }

    private static Storage createStorage() {
        try {
            storage.compareAndSet(null, (Storage) Class.forName("io.grpc.override.ContextStorageOverride").getConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (ClassNotFoundException e) {
            if (storage.compareAndSet(null, new ThreadLocalContextStorage())) {
                log.log(Level.FINE, "Storage override doesn't exist. Using default", e);
            }
        } catch (Exception e2) {
            throw new RuntimeException("Storage override failed to initialize", e2);
        }
        return (Storage) storage.get();
    }

    public static <T> Key<T> key(String str) {
        return new Key<>(str);
    }

    public static <T> Key<T> keyWithDefault(String str, T t) {
        return new Key<>(str, t);
    }

    public static Context current() {
        Context current = storage().current();
        return current == null ? ROOT : current;
    }

    private Context(Context context, PersistentHashArrayMappedTrie<Key<?>, Object> persistentHashArrayMappedTrie) {
        int i;
        this.cancellableAncestor = cancellableAncestor(context);
        this.keyValueEntries = persistentHashArrayMappedTrie;
        if (context == null) {
            i = 0;
        } else {
            i = context.generation + 1;
        }
        this.generation = i;
        validateGeneration(this.generation);
    }

    /* access modifiers changed from: 0000 */
    public boolean canBeCancelled() {
        return this.cancellableAncestor != null;
    }

    public Context attach() {
        Context doAttach = storage().doAttach(this);
        return doAttach == null ? ROOT : doAttach;
    }

    public void detach(Context context) {
        checkNotNull(context, "toAttach");
        storage().detach(this, context);
    }

    public boolean isCancelled() {
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext == null) {
            return false;
        }
        return cancellableContext.isCancelled();
    }

    public Throwable cancellationCause() {
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext == null) {
            return null;
        }
        return cancellableContext.cancellationCause();
    }

    public Deadline getDeadline() {
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext == null) {
            return null;
        }
        return cancellableContext.getDeadline();
    }

    public void addListener(CancellationListener cancellationListener, Executor executor) {
        checkNotNull(cancellationListener, "cancellationListener");
        checkNotNull(executor, "executor");
        if (canBeCancelled()) {
            ExecutableListener executableListener = new ExecutableListener(this, executor, cancellationListener, null);
            synchronized (this) {
                if (isCancelled()) {
                    executableListener.deliver();
                } else if (this.listeners == null) {
                    this.listeners = new ArrayList<>();
                    this.listeners.add(executableListener);
                    if (this.cancellableAncestor != null) {
                        this.cancellableAncestor.addListener(this.parentListener, DirectExecutor.INSTANCE);
                    }
                } else {
                    this.listeners.add(executableListener);
                }
            }
        }
    }

    public void removeListener(CancellationListener cancellationListener) {
        if (canBeCancelled()) {
            synchronized (this) {
                if (this.listeners != null) {
                    int size = this.listeners.size() - 1;
                    while (true) {
                        if (size < 0) {
                            break;
                        } else if (((ExecutableListener) this.listeners.get(size)).listener == cancellationListener) {
                            this.listeners.remove(size);
                            break;
                        } else {
                            size--;
                        }
                    }
                    if (this.listeners.isEmpty()) {
                        if (this.cancellableAncestor != null) {
                            this.cancellableAncestor.removeListener(this.parentListener);
                        }
                        this.listeners = null;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        r1 = 0;
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        if (r2 >= r0.size()) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        if ((io.grpc.Context.ExecutableListener.access$500((io.grpc.Context.ExecutableListener) r0.get(r2)) instanceof io.grpc.Context.ParentListener) != false) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        io.grpc.Context.ExecutableListener.access$400((io.grpc.Context.ExecutableListener) r0.get(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0033, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        if (r1 >= r0.size()) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        if ((io.grpc.Context.ExecutableListener.access$500((io.grpc.Context.ExecutableListener) r0.get(r1)) instanceof io.grpc.Context.ParentListener) == false) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        io.grpc.Context.ExecutableListener.access$400((io.grpc.Context.ExecutableListener) r0.get(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0053, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        r0 = r4.cancellableAncestor;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0058, code lost:
        if (r0 == null) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005a, code lost:
        r0.removeListener(r4.parentListener);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyAndClearListeners() {
        /*
            r4 = this;
            boolean r0 = r4.canBeCancelled()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            monitor-enter(r4)
            java.util.ArrayList<io.grpc.Context$ExecutableListener> r0 = r4.listeners     // Catch:{ all -> 0x0060 }
            if (r0 != 0) goto L_0x000e
            monitor-exit(r4)     // Catch:{ all -> 0x0060 }
            return
        L_0x000e:
            java.util.ArrayList<io.grpc.Context$ExecutableListener> r0 = r4.listeners     // Catch:{ all -> 0x0060 }
            r1 = 0
            r4.listeners = r1     // Catch:{ all -> 0x0060 }
            monitor-exit(r4)     // Catch:{ all -> 0x0060 }
            r1 = 0
            r2 = 0
        L_0x0016:
            int r3 = r0.size()
            if (r2 >= r3) goto L_0x0036
            java.lang.Object r3 = r0.get(r2)
            io.grpc.Context$ExecutableListener r3 = (io.grpc.Context.ExecutableListener) r3
            io.grpc.Context$CancellationListener r3 = r3.listener
            boolean r3 = r3 instanceof io.grpc.Context.ParentListener
            if (r3 != 0) goto L_0x0033
            java.lang.Object r3 = r0.get(r2)
            io.grpc.Context$ExecutableListener r3 = (io.grpc.Context.ExecutableListener) r3
            r3.deliver()
        L_0x0033:
            int r2 = r2 + 1
            goto L_0x0016
        L_0x0036:
            int r2 = r0.size()
            if (r1 >= r2) goto L_0x0056
            java.lang.Object r2 = r0.get(r1)
            io.grpc.Context$ExecutableListener r2 = (io.grpc.Context.ExecutableListener) r2
            io.grpc.Context$CancellationListener r2 = r2.listener
            boolean r2 = r2 instanceof io.grpc.Context.ParentListener
            if (r2 == 0) goto L_0x0053
            java.lang.Object r2 = r0.get(r1)
            io.grpc.Context$ExecutableListener r2 = (io.grpc.Context.ExecutableListener) r2
            r2.deliver()
        L_0x0053:
            int r1 = r1 + 1
            goto L_0x0036
        L_0x0056:
            io.grpc.Context$CancellableContext r0 = r4.cancellableAncestor
            if (r0 == 0) goto L_0x005f
            io.grpc.Context$CancellationListener r1 = r4.parentListener
            r0.removeListener(r1)
        L_0x005f:
            return
        L_0x0060:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0060 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.Context.notifyAndClearListeners():void");
    }

    /* access modifiers changed from: private */
    public Object lookup(Key<?> key) {
        return this.keyValueEntries.get(key);
    }

    /* access modifiers changed from: private */
    public static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    static CancellableContext cancellableAncestor(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof CancellableContext) {
            return (CancellableContext) context;
        }
        return context.cancellableAncestor;
    }

    private static void validateGeneration(int i) {
        if (i == 1000) {
            log.log(Level.SEVERE, "Context ancestry chain length is abnormally long. This suggests an error in application code. Length exceeded: 1000", new Exception());
        }
    }
}
