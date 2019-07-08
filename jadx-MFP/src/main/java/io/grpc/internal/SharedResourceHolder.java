package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.IdentityHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class SharedResourceHolder {
    private static final SharedResourceHolder holder = new SharedResourceHolder(new ScheduledExecutorFactory() {
        public ScheduledExecutorService createScheduledExecutor() {
            return Executors.newSingleThreadScheduledExecutor(GrpcUtil.getThreadFactory("grpc-shared-destroyer-%d", true));
        }
    });
    /* access modifiers changed from: private */
    public ScheduledExecutorService destroyer;
    private final ScheduledExecutorFactory destroyerFactory;
    /* access modifiers changed from: private */
    public final IdentityHashMap<Resource<?>, Instance> instances = new IdentityHashMap<>();

    private static class Instance {
        ScheduledFuture<?> destroyTask;
        final Object payload;
        int refcount;

        Instance(Object obj) {
            this.payload = obj;
        }
    }

    public interface Resource<T> {
        void close(T t);

        T create();
    }

    interface ScheduledExecutorFactory {
        ScheduledExecutorService createScheduledExecutor();
    }

    SharedResourceHolder(ScheduledExecutorFactory scheduledExecutorFactory) {
        this.destroyerFactory = scheduledExecutorFactory;
    }

    public static <T> T get(Resource<T> resource) {
        return holder.getInternal(resource);
    }

    public static <T> T release(Resource<T> resource, T t) {
        return holder.releaseInternal(resource, t);
    }

    /* access modifiers changed from: 0000 */
    public synchronized <T> T getInternal(Resource<T> resource) {
        Instance instance;
        instance = (Instance) this.instances.get(resource);
        if (instance == null) {
            instance = new Instance(resource.create());
            this.instances.put(resource, instance);
        }
        if (instance.destroyTask != null) {
            instance.destroyTask.cancel(false);
            instance.destroyTask = null;
        }
        instance.refcount++;
        return instance.payload;
    }

    /* access modifiers changed from: 0000 */
    public synchronized <T> T releaseInternal(final Resource<T> resource, final T t) {
        final Instance instance = (Instance) this.instances.get(resource);
        if (instance != null) {
            boolean z = false;
            Preconditions.checkArgument(t == instance.payload, "Releasing the wrong instance");
            Preconditions.checkState(instance.refcount > 0, "Refcount has already reached zero");
            instance.refcount--;
            if (instance.refcount == 0) {
                if (GrpcUtil.IS_RESTRICTED_APPENGINE) {
                    resource.close(t);
                    this.instances.remove(resource);
                } else {
                    if (instance.destroyTask == null) {
                        z = true;
                    }
                    Preconditions.checkState(z, "Destroy task already scheduled");
                    if (this.destroyer == null) {
                        this.destroyer = this.destroyerFactory.createScheduledExecutor();
                    }
                    instance.destroyTask = this.destroyer.schedule(new LogExceptionRunnable(new Runnable() {
                        public void run() {
                            synchronized (SharedResourceHolder.this) {
                                if (instance.refcount == 0) {
                                    resource.close(t);
                                    SharedResourceHolder.this.instances.remove(resource);
                                    if (SharedResourceHolder.this.instances.isEmpty()) {
                                        SharedResourceHolder.this.destroyer.shutdown();
                                        SharedResourceHolder.this.destroyer = null;
                                    }
                                }
                            }
                        }
                    }), 1, TimeUnit.SECONDS);
                }
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("No cached instance found for ");
            sb.append(resource);
            throw new IllegalArgumentException(sb.toString());
        }
        return null;
    }
}
