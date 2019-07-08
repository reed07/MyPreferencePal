package com.myfitnesspal.shared.util;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import com.myfitnesspal.feature.debug.ui.activity.AdvancedDebuggingActivity;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.ShakeDetector.OnShakeListener;
import dagger.Lazy;

public class ShakeDetectorMixin extends RunnerLifecycleMixin {
    private Sensor accelerometer;
    private Lazy<AdvancedDebuggingUtil> advancedDebuggingUtil;
    /* access modifiers changed from: private */
    public NavigationHelper navigationHelper;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;

    public ShakeDetectorMixin(MfpUiComponentInterface mfpUiComponentInterface, NavigationHelper navigationHelper2, Lazy<AdvancedDebuggingUtil> lazy) {
        super(mfpUiComponentInterface);
        this.navigationHelper = navigationHelper2;
        this.advancedDebuggingUtil = lazy;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (((AdvancedDebuggingUtil) this.advancedDebuggingUtil.get()).shouldShowAdvancedDebuggingMenu()) {
            this.sensorManager = (SensorManager) getMfpActivity().getSystemService("sensor");
            this.accelerometer = this.sensorManager.getDefaultSensor(1);
            this.shakeDetector = new ShakeDetector();
            this.shakeDetector.setOnShakeListener(new OnShakeListener() {
                public void onShake() {
                    ShakeDetectorMixin.this.navigationHelper.withContext(ShakeDetectorMixin.this.getMfpActivity()).withIntent(AdvancedDebuggingActivity.newStartIntent(ShakeDetectorMixin.this.getMfpActivity())).startActivity();
                }
            });
        }
    }

    public void onResume() {
        super.onResume();
        SensorManager sensorManager2 = this.sensorManager;
        if (sensorManager2 != null) {
            sensorManager2.registerListener(this.shakeDetector, this.accelerometer, 2);
        }
    }

    public void onPause() {
        super.onPause();
        SensorManager sensorManager2 = this.sensorManager;
        if (sensorManager2 != null) {
            sensorManager2.unregisterListener(this.shakeDetector);
        }
    }
}
