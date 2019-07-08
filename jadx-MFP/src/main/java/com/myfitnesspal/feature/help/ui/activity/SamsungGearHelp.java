package com.myfitnesspal.feature.help.ui.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.tizen.service.MfpGearMessageBridge;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.ViewUtils;
import com.uacf.gear.bridge.UacfGearBridgeBase.LocalBinder;

public class SamsungGearHelp extends MfpActivity {
    private static final String EVENT_SCREEN_VIEW = "SCREEN_SamsungGearHelp";
    private static final String EVENT_TEST_CONNECTION = "test_samsung_connection_again";
    private static final String GEAR_APP_MARKET_URI = "market://details?id=com.samsung.android.app.watchmanager";
    private static final String SAP_MARKET_URI = "market://details?id=com.samsung.accessory";
    @BindView(2131362468)
    View enableBluetooth;
    @BindView(2131362827)
    View installGearApp;
    @BindView(2131362828)
    View installSAP;
    /* access modifiers changed from: private */
    public boolean isConnected;
    @BindView(2131363727)
    View statusHelp;
    @BindView(2131363728)
    View statusOk;
    @BindView(2131363729)
    TextView statusText;
    @BindView(2131363888)
    View tryAgainButton;

    public String getAnalyticsScreenTag() {
        return EVENT_SCREEN_VIEW;
    }

    public boolean shouldDisplayAds() {
        return false;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, SamsungGearHelp.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.samsung_gear_help);
        ViewUtils.setGone(this.statusOk);
        initListeners();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        refresh();
    }

    /* access modifiers changed from: private */
    public void updateUi() {
        setBusy(false);
        ViewUtils.setVisible(this.isConnected, this.statusOk);
        this.statusText.setText(this.isConnected ? R.string.samsung_gear_connected : R.string.samsung_gear_not_connected);
    }

    /* access modifiers changed from: private */
    public void refresh() {
        setBusy(true);
        bindService(new Intent(this, MfpGearMessageBridge.class), new ServiceConnection() {
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                LocalBinder localBinder = (LocalBinder) iBinder;
                if (localBinder != null) {
                    SamsungGearHelp.this.isConnected = localBinder.isConnected();
                }
                SamsungGearHelp.this.updateUi();
                SamsungGearHelp.this.unbindService(this);
            }

            public void onServiceDisconnected(ComponentName componentName) {
                SamsungGearHelp.this.setBusy(false);
            }
        }, 1);
    }

    /* access modifiers changed from: private */
    public void showActivityAndEatException(Intent intent) {
        try {
            startActivity(intent);
        } catch (Exception unused) {
        }
    }

    private void initListeners() {
        this.enableBluetooth.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.settings.BLUETOOTH_SETTINGS");
                SamsungGearHelp.this.showActivityAndEatException(intent);
            }
        });
        this.installGearApp.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(SamsungGearHelp.GEAR_APP_MARKET_URI));
                SamsungGearHelp.this.showActivityAndEatException(intent);
            }
        });
        this.installSAP.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(SamsungGearHelp.SAP_MARKET_URI));
                SamsungGearHelp.this.showActivityAndEatException(intent);
            }
        });
        this.tryAgainButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SamsungGearHelp.this.getAnalyticsService().reportEvent(SamsungGearHelp.EVENT_TEST_CONNECTION);
                SamsungGearHelp.this.refresh();
            }
        });
    }
}
