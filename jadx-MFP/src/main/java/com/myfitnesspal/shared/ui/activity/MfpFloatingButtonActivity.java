package com.myfitnesspal.shared.ui.activity;

import android.os.Bundle;
import android.util.Pair;
import android.view.ViewGroup;
import com.myfitnesspal.android.R;
import com.uacf.floatingbuttonmenu.FloatingButtonMenu;
import com.uacf.floatingbuttonmenu.FloatingButtonMenu.OnItemClickListener;
import java.util.List;

public class MfpFloatingButtonActivity extends MfpActivity implements MfpFloatingButtonHost {
    private static final int ACTIVITY_ROOT_VIEW_ID = 2131363547;
    private FloatingButtonMenu floatingButtonMenu;

    public boolean isFloatingButtonVisible() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        registerMixin(new FloatingButtonMixin(this));
        super.onCreate(bundle);
    }

    public void onSetContentView() {
        super.onSetContentView();
        ((FloatingButtonMixin) mixin(FloatingButtonMixin.class)).onSetContentView();
    }

    public void addBusEventHandlers(List<BusEventHandler> list) {
        super.addBusEventHandlers(list);
        ((FloatingButtonMixin) mixin(FloatingButtonMixin.class)).addBusEventHandlers(list);
    }

    public void showFloatingButton(FloatingButtonMenu floatingButtonMenu2, OnItemClickListener onItemClickListener, List<Pair<String, Integer>> list) {
        if (floatingButtonMenu2 != null) {
            this.floatingButtonMenu = floatingButtonMenu2;
            FloatingButtonMixin.initDefaultMenu(this, floatingButtonMenu2, list);
            floatingButtonMenu2.setAnimationHandler(FloatingButtonMixin.getDefaultAnimator(floatingButtonMenu2)).setOnItemClickListener(onItemClickListener);
            updateFloatingButtonMenuBottomMargin();
        }
    }

    public ViewGroup getFloatingButtonParent() {
        return (ViewGroup) findViewById(R.id.screen_parent);
    }

    /* access modifiers changed from: protected */
    public void onBannerAdSetupCompleted() {
        updateFloatingButtonMenuBottomMargin();
    }

    private void updateFloatingButtonMenuBottomMargin() {
        int i;
        if (this.floatingButtonMenu != null) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.floating_button_margin_right);
            if (getBannerAdDisplayState() != BannerAdDisplayState.Visible) {
                i = getResources().getDimensionPixelSize(R.dimen.floating_button_margin_bottom_no_ads);
            } else {
                i = getResources().getDimensionPixelSize(R.dimen.floating_button_margin_bottom_ads);
            }
            this.floatingButtonMenu.setFloatingButtonMargin(0, 0, dimensionPixelSize, i);
        }
    }
}
