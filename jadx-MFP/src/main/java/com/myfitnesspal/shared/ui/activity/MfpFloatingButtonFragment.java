package com.myfitnesspal.shared.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.uacf.floatingbuttonmenu.FloatingButtonMenu;
import com.uacf.floatingbuttonmenu.FloatingButtonMenu.OnItemClickListener;
import java.util.List;

public class MfpFloatingButtonFragment extends MfpFragment implements MfpFloatingButtonHost {
    private static final int FRAGMENT_ROOT_VIEW_ID = 2131362549;

    public boolean isFloatingButtonVisible() {
        return true;
    }

    public void onCreate(Bundle bundle) {
        registerMixin(new FloatingButtonMixin(this));
        super.onCreate(bundle);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ((FloatingButtonMixin) mixin(FloatingButtonMixin.class)).onSetContentView();
    }

    public ViewGroup getFloatingButtonParent() {
        if (getView() == null) {
            return null;
        }
        return (ViewGroup) getView().findViewById(R.id.fab_fragment_root);
    }

    public void addBusEventHandlers(List<BusEventHandler> list) {
        super.addBusEventHandlers(list);
        ((FloatingButtonMixin) mixin(FloatingButtonMixin.class)).addBusEventHandlers(list);
    }

    public void showFloatingButton(FloatingButtonMenu floatingButtonMenu, OnItemClickListener onItemClickListener, List<Pair<String, Integer>> list) {
        if (floatingButtonMenu != null) {
            FloatingButtonMixin.initDefaultMenu(getActivity(), floatingButtonMenu, list);
            floatingButtonMenu.setAnimationHandler(FloatingButtonMixin.getDefaultAnimator(floatingButtonMenu)).setOnItemClickListener(onItemClickListener);
            updateFloatingButtonMenuBottomMargin(floatingButtonMenu);
        }
    }

    /* access modifiers changed from: protected */
    public void openFloatingButton(int i) {
        ((FloatingButtonMixin) mixin(FloatingButtonMixin.class)).open(i);
    }

    private void updateFloatingButtonMenuBottomMargin(FloatingButtonMenu floatingButtonMenu) {
        if (floatingButtonMenu != null) {
            floatingButtonMenu.setFloatingButtonMargin(0, 0, getResources().getDimensionPixelSize(R.dimen.floating_button_margin_right), getResources().getDimensionPixelSize(R.dimen.floating_button_margin_bottom_no_ads));
        }
    }
}
