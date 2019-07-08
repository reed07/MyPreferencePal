package com.myfitnesspal.framework.mixin;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public interface LifecycleMixin {

    public enum AttachTarget {
        Window,
        FragmentManager
    }

    void onActivityResult(int i, int i2, Intent intent);

    void onAttached(AttachTarget attachTarget);

    void onConfigurationChanged(Configuration configuration);

    void onCreate(Bundle bundle);

    void onDestroy();

    void onDetached(AttachTarget attachTarget);

    void onPause();

    void onPostCreate(Bundle bundle);

    void onPostResume();

    void onRegister();

    void onRestart();

    void onResume();

    void onSaveInstanceState(Bundle bundle);

    void onStart();

    void onStop();

    void onUnregister();

    void onWindowFocusChanged(boolean z);
}
