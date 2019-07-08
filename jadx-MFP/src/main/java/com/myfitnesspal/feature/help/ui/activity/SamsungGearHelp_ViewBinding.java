package com.myfitnesspal.feature.help.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SamsungGearHelp_ViewBinding implements Unbinder {
    private SamsungGearHelp target;

    @UiThread
    public SamsungGearHelp_ViewBinding(SamsungGearHelp samsungGearHelp) {
        this(samsungGearHelp, samsungGearHelp.getWindow().getDecorView());
    }

    @UiThread
    public SamsungGearHelp_ViewBinding(SamsungGearHelp samsungGearHelp, View view) {
        this.target = samsungGearHelp;
        samsungGearHelp.statusText = (TextView) Utils.findRequiredViewAsType(view, R.id.statusText, "field 'statusText'", TextView.class);
        samsungGearHelp.statusOk = Utils.findRequiredView(view, R.id.statusOk, "field 'statusOk'");
        samsungGearHelp.statusHelp = Utils.findRequiredView(view, R.id.statusHelp, "field 'statusHelp'");
        samsungGearHelp.installGearApp = Utils.findRequiredView(view, R.id.installGearApp, "field 'installGearApp'");
        samsungGearHelp.installSAP = Utils.findRequiredView(view, R.id.installSAP, "field 'installSAP'");
        samsungGearHelp.enableBluetooth = Utils.findRequiredView(view, R.id.enableBluetooth, "field 'enableBluetooth'");
        samsungGearHelp.tryAgainButton = Utils.findRequiredView(view, R.id.tryAgainButton, "field 'tryAgainButton'");
    }

    @CallSuper
    public void unbind() {
        SamsungGearHelp samsungGearHelp = this.target;
        if (samsungGearHelp != null) {
            this.target = null;
            samsungGearHelp.statusText = null;
            samsungGearHelp.statusOk = null;
            samsungGearHelp.statusHelp = null;
            samsungGearHelp.installGearApp = null;
            samsungGearHelp.installSAP = null;
            samsungGearHelp.enableBluetooth = null;
            samsungGearHelp.tryAgainButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
