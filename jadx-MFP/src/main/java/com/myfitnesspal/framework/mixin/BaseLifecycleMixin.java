package com.myfitnesspal.framework.mixin;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import com.myfitnesspal.framework.mixin.LifecycleMixin.AttachTarget;

public abstract class BaseLifecycleMixin implements LifecycleMixin {
    private static Handler handler;
    private State state = State.Unknown;

    protected enum State {
        Unknown,
        Created,
        Started,
        Resumed,
        Paused,
        Stopped,
        Destroyed
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onAttached(AttachTarget attachTarget) {
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onDetached(AttachTarget attachTarget) {
    }

    public void onPostCreate(Bundle bundle) {
    }

    public void onPostResume() {
    }

    public void onRegister() {
    }

    public void onRestart() {
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onUnregister() {
    }

    public void onWindowFocusChanged(boolean z) {
    }

    /* access modifiers changed from: protected */
    public State getState() {
        return this.state;
    }

    /* access modifiers changed from: protected */
    public Handler getHandler() {
        if (handler == null) {
            handler = new Handler();
        }
        return handler;
    }

    public void onCreate(Bundle bundle) {
        this.state = State.Created;
    }

    public void onStart() {
        this.state = State.Started;
    }

    public void onResume() {
        this.state = State.Resumed;
    }

    public void onPause() {
        this.state = State.Paused;
    }

    public void onStop() {
        this.state = State.Stopped;
    }

    public void onDestroy() {
        this.state = State.Destroyed;
    }
}
