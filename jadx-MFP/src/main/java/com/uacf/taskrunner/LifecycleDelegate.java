package com.uacf.taskrunner;

import android.content.Context;
import android.os.Bundle;
import com.uacf.taskrunner.Invoker.Builder;
import com.uacf.taskrunner.Runner.TaskCallbacks;

public class LifecycleDelegate {
    private Builder builder;
    private TaskCallbacks callbacks;
    private Class<?> callingType;
    private Context context;
    private Runner runner;

    public LifecycleDelegate(Context context2, TaskCallbacks taskCallbacks, Class<?> cls, Builder builder2) {
        Context applicationContext = context2.getApplicationContext();
        if (applicationContext != null) {
            context2 = applicationContext;
        }
        this.context = context2;
        this.callbacks = taskCallbacks;
        this.callingType = cls;
        this.builder = builder2;
    }

    public void onCreate(Bundle bundle) {
        this.runner = Runner.attach(this.context, this.callingType, this.callbacks, bundle, this.builder);
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.runner.saveState(bundle);
    }

    public void onResume() {
        this.runner.resume();
    }

    public void onPause() {
        this.runner.pause();
    }

    public void onDestroy() {
        this.runner.detach(this.callbacks);
    }

    public Runner runner() {
        return this.runner;
    }
}
