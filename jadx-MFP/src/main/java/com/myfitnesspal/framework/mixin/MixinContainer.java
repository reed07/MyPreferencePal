package com.myfitnesspal.framework.mixin;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.myfitnesspal.framework.mixin.LifecycleMixin.AttachTarget;
import java.util.LinkedHashMap;
import java.util.Map;

public class MixinContainer {
    final Map<Class<? extends LifecycleMixin>, LifecycleMixin> mixins = new LinkedHashMap();

    public void register(LifecycleMixin... lifecycleMixinArr) {
        for (LifecycleMixin register : lifecycleMixinArr) {
            register((T) register);
        }
    }

    public <T extends LifecycleMixin> T register(T t) {
        Preconditions.calledFromMainThread();
        Class cls = t.getClass();
        if (!this.mixins.containsKey(cls)) {
            this.mixins.put(cls, t);
            t.onRegister();
            return t;
        }
        throw new IllegalArgumentException("specified mixin type already registered");
    }

    public <T extends LifecycleMixin> T get(Class<T> cls) {
        Preconditions.calledFromMainThread();
        T t = (LifecycleMixin) this.mixins.get(cls);
        if (t != null) {
            return t;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("mixin type ");
        sb.append(cls.toString());
        sb.append(" not registered");
        throw new IllegalArgumentException(sb.toString());
    }

    public <T extends LifecycleMixin> T unregister(Class<T> cls) {
        Preconditions.calledFromMainThread();
        T t = get(cls);
        this.mixins.remove(cls);
        t.onUnregister();
        return t;
    }

    public void onCreate(Bundle bundle) {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onCreate : this.mixins.values()) {
            onCreate.onCreate(bundle);
        }
    }

    public void onPostCreate(Bundle bundle) {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onPostCreate : this.mixins.values()) {
            onPostCreate.onPostCreate(bundle);
        }
    }

    public void onStart() {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onStart : this.mixins.values()) {
            onStart.onStart();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onActivityResult : this.mixins.values()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
    }

    public void onResume() {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onResume : this.mixins.values()) {
            onResume.onResume();
        }
    }

    public void onPostResume() {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onPostResume : this.mixins.values()) {
            onPostResume.onPostResume();
        }
    }

    public void onPause() {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onPause : this.mixins.values()) {
            onPause.onPause();
        }
    }

    public void onStop() {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onStop : this.mixins.values()) {
            onStop.onStop();
        }
    }

    public void onRestart() {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onRestart : this.mixins.values()) {
            onRestart.onRestart();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onSaveInstanceState : this.mixins.values()) {
            onSaveInstanceState.onSaveInstanceState(bundle);
        }
    }

    public void onDestroy() {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onDestroy : this.mixins.values()) {
            onDestroy.onDestroy();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onWindowFocusChanged : this.mixins.values()) {
            onWindowFocusChanged.onWindowFocusChanged(z);
        }
    }

    public void onAttached(AttachTarget attachTarget) {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onAttached : this.mixins.values()) {
            onAttached.onAttached(attachTarget);
        }
    }

    public void onDetached(AttachTarget attachTarget) {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onDetached : this.mixins.values()) {
            onDetached.onDetached(attachTarget);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        Preconditions.calledFromMainThread();
        for (LifecycleMixin onConfigurationChanged : this.mixins.values()) {
            onConfigurationChanged.onConfigurationChanged(configuration);
        }
    }
}
