package com.google.common.eventbus;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

class Subscriber {
    /* access modifiers changed from: private */
    @Weak
    public EventBus bus;
    private final Executor executor;
    private final Method method;
    @VisibleForTesting
    final Object target;

    @VisibleForTesting
    static final class SynchronizedSubscriber extends Subscriber {
        private SynchronizedSubscriber(EventBus eventBus, Object obj, Method method) {
            super(eventBus, obj, method);
        }

        /* access modifiers changed from: 0000 */
        public void invokeSubscriberMethod(Object obj) throws InvocationTargetException {
            synchronized (this) {
                Subscriber.super.invokeSubscriberMethod(obj);
            }
        }
    }

    static Subscriber create(EventBus eventBus, Object obj, Method method2) {
        return isDeclaredThreadSafe(method2) ? new Subscriber(eventBus, obj, method2) : new SynchronizedSubscriber(eventBus, obj, method2);
    }

    private Subscriber(EventBus eventBus, Object obj, Method method2) {
        this.bus = eventBus;
        this.target = Preconditions.checkNotNull(obj);
        this.method = method2;
        method2.setAccessible(true);
        this.executor = eventBus.executor();
    }

    /* access modifiers changed from: 0000 */
    public final void dispatchEvent(final Object obj) {
        this.executor.execute(new Runnable() {
            public void run() {
                try {
                    Subscriber.this.invokeSubscriberMethod(obj);
                } catch (InvocationTargetException e) {
                    Subscriber.this.bus.handleSubscriberException(e.getCause(), Subscriber.this.context(obj));
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void invokeSubscriberMethod(Object obj) throws InvocationTargetException {
        try {
            this.method.invoke(this.target, new Object[]{Preconditions.checkNotNull(obj)});
        } catch (IllegalArgumentException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Method rejected target/argument: ");
            sb.append(obj);
            throw new Error(sb.toString(), e);
        } catch (IllegalAccessException e2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Method became inaccessible: ");
            sb2.append(obj);
            throw new Error(sb2.toString(), e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof Error) {
                throw ((Error) e3.getCause());
            }
            throw e3;
        }
    }

    /* access modifiers changed from: private */
    public SubscriberExceptionContext context(Object obj) {
        return new SubscriberExceptionContext(this.bus, obj, this.target, this.method);
    }

    public final int hashCode() {
        return ((this.method.hashCode() + 31) * 31) + System.identityHashCode(this.target);
    }

    public final boolean equals(@NullableDecl Object obj) {
        boolean z = false;
        if (!(obj instanceof Subscriber)) {
            return false;
        }
        Subscriber subscriber = (Subscriber) obj;
        if (this.target == subscriber.target && this.method.equals(subscriber.method)) {
            z = true;
        }
        return z;
    }

    private static boolean isDeclaredThreadSafe(Method method2) {
        return method2.getAnnotation(AllowConcurrentEvents.class) != null;
    }
}
