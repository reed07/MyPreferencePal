package com.uacf.taskrunner;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.uacf.taskrunner.Invoker.Builder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class Runner {
    private static final String ANONYMOUS_TASK_NAME;
    /* access modifiers changed from: private */
    public static final Map<Long, Runner> DANGEROUS_STATIC_MAP = new HashMap();
    private static final String EXTRA_NAME;
    private static final String EXTRA_RUNNER_ID;
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final String TAG = Runner.class.getCanonicalName();
    /* access modifiers changed from: private */
    public Set<TaskCallbacks> callbacks;
    private Class<?> callingType;
    /* access modifiers changed from: private */
    public Context context;
    private CacheMode defaultCacheMode = CacheMode.None;
    private DedupeMode defaultDedupeMode = DedupeMode.Throw;
    private Runnable destroyRunnable = new Runnable() {
        public void run() {
            Runner runner;
            if (!Runner.this.destroyed()) {
                Runner.debug("destroying runner id=%d", Long.valueOf(Runner.this.id));
                synchronized (Runner.this) {
                    Runner.this.state = State.Destroyed;
                    Runner.this.invoker.shutdown();
                    Runner.this.invoker = null;
                }
                synchronized (Runner.DANGEROUS_STATIC_MAP) {
                    runner = (Runner) Runner.DANGEROUS_STATIC_MAP.remove(Long.valueOf(Runner.this.id));
                }
                if (runner != null) {
                    runner.pendingDelivery.clear();
                    runner.pendingStart.clear();
                    runner.runningByName.clear();
                    runner.instanceCache.clear();
                    runner.callbacks.clear();
                }
                Runner.debug("destroyed runner id=%d", Long.valueOf(Runner.this.id));
            }
        }
    };
    /* access modifiers changed from: private */
    public long id = -1;
    /* access modifiers changed from: private */
    public Map<String, TaskDescriptor> instanceCache = new HashMap();
    /* access modifiers changed from: private */
    public Invoker invoker;
    private String name;
    /* access modifiers changed from: private */
    public Map<TaskDescriptor, Throwable> pendingDelivery = new HashMap();
    /* access modifiers changed from: private */
    public List<TaskDescriptor> pendingStart = new ArrayList();
    /* access modifiers changed from: private */
    public Map<String, TaskDescriptor> runningByName = new HashMap();
    /* access modifiers changed from: private */
    public State state = State.Paused;

    public enum CacheMode {
        None,
        CacheOnSuccess,
        CacheAlways
    }

    public enum DedupeMode {
        Throw,
        CancelExisting,
        UseExisting
    }

    private enum State {
        Running,
        Paused,
        Destroyed
    }

    public interface TaskCallbacks {
        void onTaskCompleted(String str, long j, Task task, Object obj);

        void onTaskError(String str, long j, Task task, Throwable th);
    }

    private static class TaskDescriptor {
        CacheMode cacheMode;
        boolean canceled;
        Future<?> future;
        long id;
        String name;
        Task task;

        private TaskDescriptor() {
            this.canceled = false;
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(TAG);
        sb.append(".runner_id");
        EXTRA_RUNNER_ID = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(TAG);
        sb2.append(".name");
        EXTRA_NAME = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(TAG);
        sb3.append(".ANONYMOUS_TASK");
        ANONYMOUS_TASK_NAME = sb3.toString();
    }

    private Runner(Context context2, Class<?> cls, String str, long j, Builder builder) {
        Context applicationContext = context2.getApplicationContext();
        if (applicationContext != null) {
            context2 = applicationContext;
        }
        this.context = context2;
        this.name = str;
        this.id = j;
        this.callingType = cls;
        if (builder == null) {
            builder = new Builder(str).setUseCachedExecutor();
        }
        this.callbacks = new HashSet();
        this.invoker = builder.build();
        synchronized (DANGEROUS_STATIC_MAP) {
            DANGEROUS_STATIC_MAP.put(Long.valueOf(this.id), this);
        }
    }

    public long run(Task task) {
        return run(ANONYMOUS_TASK_NAME, task);
    }

    public long run(String str, Task task) {
        return run(str, task, this.defaultCacheMode, this.defaultDedupeMode);
    }

    public long run(String str, Task task, DedupeMode dedupeMode) {
        return run(str, task, this.defaultCacheMode, dedupeMode);
    }

    public long run(String str, Task task, CacheMode cacheMode, DedupeMode dedupeMode) {
        throwIfDestroyed();
        throwIfNotOnMainThread();
        debug("attempting to enqueue task name='%s'", str);
        if (!ANONYMOUS_TASK_NAME.equals(str)) {
            TaskDescriptor taskDescriptor = (TaskDescriptor) this.runningByName.get(str);
            if (taskDescriptor != null) {
                switch (dedupeMode) {
                    case Throw:
                        throw new RuntimeException("Task already enqueued!");
                    case CancelExisting:
                        cancel(str);
                        break;
                    case UseExisting:
                        return taskDescriptor.id;
                }
            }
        } else {
            cacheMode = CacheMode.None;
        }
        TaskDescriptor taskDescriptor2 = new TaskDescriptor();
        taskDescriptor2.name = str;
        taskDescriptor2.id = NEXT_ID.incrementAndGet();
        taskDescriptor2.cacheMode = cacheMode;
        taskDescriptor2.task = task;
        if (cacheMode != CacheMode.None) {
            TaskDescriptor taskDescriptor3 = (TaskDescriptor) this.instanceCache.get(str);
            if (taskDescriptor3 != null) {
                debug("task name='%s' found in cache, short-circuiting...", str);
                postCompleted(taskDescriptor3);
                return taskDescriptor3.id;
            }
            this.instanceCache.put(str, taskDescriptor2);
        }
        this.runningByName.put(str, taskDescriptor2);
        debug("task name='%s' given id=%d", taskDescriptor2.name, Long.valueOf(taskDescriptor2.id));
        if (this.state == State.Paused) {
            this.pendingStart.add(taskDescriptor2);
        } else {
            startAsync(taskDescriptor2);
        }
        return taskDescriptor2.id;
    }

    public boolean cancel(String str) {
        throwIfDestroyed();
        throwIfNotOnMainThread();
        TaskDescriptor taskDescriptor = (TaskDescriptor) this.runningByName.get(str);
        if (taskDescriptor == null) {
            return false;
        }
        cancel(taskDescriptor);
        return true;
    }

    public boolean cancel(long j) {
        throwIfDestroyed();
        throwIfNotOnMainThread();
        for (TaskDescriptor taskDescriptor : this.runningByName.values()) {
            if (taskDescriptor.id == j) {
                cancel(taskDescriptor);
                return true;
            }
        }
        return false;
    }

    public boolean running(String str) {
        throwIfNotOnMainThread();
        return this.runningByName.containsKey(str);
    }

    public void pause() {
        debug("(start) pause id=%d", Long.valueOf(this.id));
        throwIfDestroyed();
        throwIfNotOnMainThread();
        this.state = State.Paused;
        debug("(finish) pause id=%d", Long.valueOf(this.id));
    }

    public void resume() {
        debug("(start) resume id=%d", Long.valueOf(this.id));
        throwIfDestroyed();
        throwIfNotOnMainThread();
        this.state = State.Running;
        debug("dispatching task completion, total=%d", Integer.valueOf(this.pendingDelivery.size()));
        for (TaskDescriptor taskDescriptor : this.pendingDelivery.keySet()) {
            completed(taskDescriptor, (Throwable) this.pendingDelivery.get(taskDescriptor));
        }
        debug("dispatching task start, total=%d", Integer.valueOf(this.pendingStart.size()));
        for (TaskDescriptor startAsync : this.pendingStart) {
            startAsync(startAsync);
        }
        this.pendingDelivery.clear();
        this.pendingStart.clear();
        debug("(finish) resume id=%d", Long.valueOf(this.id));
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0032 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.uacf.taskrunner.Runner attach(android.content.Context r14, java.lang.Class<?> r15, com.uacf.taskrunner.Runner.TaskCallbacks r16, android.os.Bundle r17, com.uacf.taskrunner.Invoker.Builder r18) {
        /*
            r0 = r17
            throwIfNotOnMainThread()
            java.util.concurrent.atomic.AtomicLong r1 = NEXT_ID
            long r1 = r1.incrementAndGet()
            r3 = 0
            r4 = 1
            if (r0 == 0) goto L_0x002e
            java.lang.String r5 = getExtraBundleName(r15)
            android.os.Bundle r0 = r0.getBundle(r5)
            if (r0 == 0) goto L_0x002e
            java.lang.String r5 = EXTRA_RUNNER_ID
            long r1 = r0.getLong(r5, r1)
            java.lang.String r0 = "attempting to reattach to runner with id=%d"
            java.lang.Object[] r5 = new java.lang.Object[r4]
            java.lang.Long r6 = java.lang.Long.valueOf(r1)
            r5[r3] = r6
            debug(r0, r5)
            r11 = r1
            goto L_0x002f
        L_0x002e:
            r11 = r1
        L_0x002f:
            java.util.Map<java.lang.Long, com.uacf.taskrunner.Runner> r1 = DANGEROUS_STATIC_MAP
            monitor-enter(r1)
            java.util.Map<java.lang.Long, com.uacf.taskrunner.Runner> r0 = DANGEROUS_STATIC_MAP     // Catch:{ all -> 0x006b }
            java.lang.Long r2 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x006b }
            java.lang.Object r0 = r0.get(r2)     // Catch:{ all -> 0x006b }
            com.uacf.taskrunner.Runner r0 = (com.uacf.taskrunner.Runner) r0     // Catch:{ all -> 0x006b }
            monitor-exit(r1)     // Catch:{ all -> 0x006b }
            if (r0 != 0) goto L_0x0064
            java.lang.String r0 = "%s-%d"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = r15.getCanonicalName()
            r1[r3] = r2
            java.lang.Long r2 = java.lang.Long.valueOf(r11)
            r1[r4] = r2
            java.lang.String r10 = java.lang.String.format(r0, r1)
            com.uacf.taskrunner.Runner r0 = new com.uacf.taskrunner.Runner
            r7 = r0
            r8 = r14
            r9 = r15
            r13 = r18
            r7.<init>(r8, r9, r10, r11, r13)
            r1 = r0
            r0 = r16
            goto L_0x0067
        L_0x0064:
            r1 = r0
            r0 = r16
        L_0x0067:
            r1.attach(r0)
            return r1
        L_0x006b:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x006b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.taskrunner.Runner.attach(android.content.Context, java.lang.Class, com.uacf.taskrunner.Runner$TaskCallbacks, android.os.Bundle, com.uacf.taskrunner.Invoker$Builder):com.uacf.taskrunner.Runner");
    }

    public long getId() {
        return this.id;
    }

    public void saveState(Bundle bundle) {
        throwIfNotOnMainThread();
        if (bundle != null) {
            bundle.putBundle(getExtraBundleName(this.callingType), toBundle());
        }
    }

    public void detach(TaskCallbacks taskCallbacks) {
        detach(taskCallbacks, 3500);
    }

    public void detach(TaskCallbacks taskCallbacks, long j) {
        throwIfNotOnMainThread();
        debug("detaching id=%d", Long.valueOf(this.id), Long.valueOf(j));
        if (this.callbacks.contains(taskCallbacks)) {
            if (this.state == State.Running) {
                pause();
            }
            this.callbacks.remove(taskCallbacks);
            if (this.callbacks.isEmpty()) {
                debug("empty callback set! will destroy in %d millis", Long.valueOf(j));
                this.invoker.getHandler().removeCallbacks(this.destroyRunnable);
                this.invoker.getHandler().postDelayed(this.destroyRunnable, j);
                return;
            }
            return;
        }
        throw new RuntimeException("Wrong instance detaching??");
    }

    public boolean destroyed() {
        throwIfNotOnMainThread();
        return this.state == State.Destroyed;
    }

    public boolean removeFromInstanceCache(String str) {
        throwIfNotOnMainThread();
        return this.instanceCache.remove(str) != null;
    }

    private void cancel(TaskDescriptor taskDescriptor) {
        taskDescriptor.canceled = true;
        if (taskDescriptor.future != null) {
            taskDescriptor.future.cancel(true);
        }
        this.instanceCache.remove(taskDescriptor.name);
    }

    private void startAsync(final TaskDescriptor taskDescriptor) {
        taskDescriptor.future = this.invoker.async(new Runnable() {
            public void run() {
                try {
                    taskDescriptor.task.run(Runner.this.context);
                    Runner.this.postCompleted(taskDescriptor);
                } catch (Throwable th) {
                    Runner.this.postCompleted(taskDescriptor, th);
                }
            }
        });
    }

    public void attach(TaskCallbacks taskCallbacks) {
        throwIfDestroyed();
        throwIfNotOnMainThread();
        this.callbacks.add(taskCallbacks);
        this.invoker.getHandler().removeCallbacks(this.destroyRunnable);
        debug("attached to runner with id=%d", Long.valueOf(this.id));
    }

    /* access modifiers changed from: private */
    public void postCompleted(TaskDescriptor taskDescriptor) {
        postCompleted(taskDescriptor, null);
    }

    /* access modifiers changed from: private */
    public synchronized void postCompleted(final TaskDescriptor taskDescriptor, final Throwable th) {
        if (!(this.state == State.Destroyed || this.invoker == null)) {
            this.invoker.post(new Runnable() {
                public void run() {
                    Runner.this.completed(taskDescriptor, th);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void completed(TaskDescriptor taskDescriptor, Throwable th) {
        if (destroyed()) {
            return;
        }
        if (this.state == State.Paused) {
            debug("task finished but runner paused, stashing result until resume", new Object[0]);
            this.pendingDelivery.put(taskDescriptor, th);
            return;
        }
        if (this.runningByName.get(taskDescriptor.name) == taskDescriptor) {
            this.runningByName.remove(taskDescriptor.name);
        }
        raiseCallback(taskDescriptor, th);
    }

    private void raiseCallback(TaskDescriptor taskDescriptor, Throwable th) {
        Object obj;
        String str;
        taskDescriptor.future = null;
        if (th == null) {
            try {
                obj = taskDescriptor.task.get();
            } catch (Throwable th2) {
                th = th2;
                obj = null;
            }
        } else {
            obj = null;
        }
        switch (taskDescriptor.cacheMode) {
            case None:
                this.instanceCache.remove(taskDescriptor.name);
                break;
            case CacheAlways:
                this.instanceCache.put(taskDescriptor.name, taskDescriptor);
                break;
            case CacheOnSuccess:
                if (th == null) {
                    this.instanceCache.put(taskDescriptor.name, taskDescriptor);
                    break;
                } else {
                    this.instanceCache.remove(taskDescriptor.name);
                    break;
                }
        }
        if (taskDescriptor.canceled) {
            debug("task name='%s' id='%d' CANCELED", taskDescriptor.name, Long.valueOf(taskDescriptor.id));
            return;
        }
        HashSet<TaskCallbacks> hashSet = new HashSet<>(this.callbacks);
        if (th != null) {
            debug("task name='%s' id='%d' FAILED with error='%s'", taskDescriptor.name, Long.valueOf(taskDescriptor.id), th.getClass().getCanonicalName());
            for (TaskCallbacks onTaskError : hashSet) {
                onTaskError.onTaskError(taskDescriptor.name, taskDescriptor.id, taskDescriptor.task, th);
            }
            return;
        }
        String str2 = "task name='%s' id='%d' SUCCEEDED with result type='%s'";
        Object[] objArr = new Object[3];
        objArr[0] = taskDescriptor.name;
        objArr[1] = Long.valueOf(taskDescriptor.id);
        if (obj == null) {
            str = "null";
        } else {
            str = obj.getClass().getCanonicalName();
        }
        objArr[2] = str;
        debug(str2, objArr);
        for (TaskCallbacks onTaskCompleted : hashSet) {
            onTaskCompleted.onTaskCompleted(taskDescriptor.name, taskDescriptor.id, taskDescriptor.task, obj);
        }
    }

    private Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_RUNNER_ID, this.id);
        bundle.putString(EXTRA_NAME, this.name);
        return bundle;
    }

    private static void throwIfNotOnMainThread() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new RuntimeException("method not called from main thread");
        }
    }

    private void throwIfDestroyed() {
        if (this.state == State.Destroyed) {
            throw new IllegalStateException("instance already destroyed!");
        }
    }

    private static String getExtraBundleName(Class<?> cls) {
        StringBuilder sb = new StringBuilder();
        sb.append(TAG);
        sb.append("[");
        sb.append(cls.getCanonicalName());
        sb.append("].runner");
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public static void debug(String str, Object... objArr) {
        Log.d(TAG, String.format(str, objArr));
    }
}
