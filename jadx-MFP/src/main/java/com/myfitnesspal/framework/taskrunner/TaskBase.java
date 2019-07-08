package com.myfitnesspal.framework.taskrunner;

import com.uacf.taskrunner.Runner;
import com.uacf.taskrunner.Runner.CacheMode;
import com.uacf.taskrunner.Runner.DedupeMode;
import com.uacf.taskrunner.Tasks.Blocking;
import java.lang.Throwable;

public abstract class TaskBase<ResultT, ErrorT extends Throwable> extends Blocking<ResultT, ErrorT> {
    private CacheMode cacheMode;
    private DedupeMode dedupeMode;

    public long run(Runner runner) {
        return run(runner, false);
    }

    public long run(Runner runner, boolean z) {
        if (z) {
            runner.removeFromInstanceCache(getTaskName());
        }
        return runner.run(getTaskName(), this, getCacheMode(), getDedupeMode());
    }

    public void anonymous(Runner runner) {
        runner.run(this);
    }

    public final <T extends TaskBase<ResultT, ErrorT>> T setCacheMode(CacheMode cacheMode2) {
        this.cacheMode = cacheMode2;
        return this;
    }

    public final <T extends TaskBase<ResultT, ErrorT>> T setDedupeMode(DedupeMode dedupeMode2) {
        this.dedupeMode = dedupeMode2;
        return this;
    }

    /* access modifiers changed from: protected */
    public final DedupeMode getDedupeMode() {
        DedupeMode dedupeMode2 = this.dedupeMode;
        return dedupeMode2 != null ? dedupeMode2 : getDefaultDedupeMode();
    }

    /* access modifiers changed from: protected */
    public final CacheMode getCacheMode() {
        CacheMode cacheMode2 = this.cacheMode;
        return cacheMode2 != null ? cacheMode2 : getDefaultCacheMode();
    }

    /* access modifiers changed from: protected */
    public DedupeMode getDefaultDedupeMode() {
        return DedupeMode.UseExisting;
    }

    /* access modifiers changed from: protected */
    public CacheMode getDefaultCacheMode() {
        return CacheMode.None;
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        return getClass().getCanonicalName();
    }
}
