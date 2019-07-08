package com.myfitnesspal.shared.ui.activity;

import android.support.v4.app.DialogFragment;
import android.util.Pair;
import android.view.ViewGroup;
import com.uacf.floatingbuttonmenu.FloatingButtonMenu;
import com.uacf.floatingbuttonmenu.FloatingButtonMenu.OnItemClickListener;
import java.util.List;

public interface MfpFloatingButtonHost {
    ViewGroup getFloatingButtonParent();

    boolean isFloatingButtonVisible();

    void showDialogFragment(DialogFragment dialogFragment, String str);

    void showFloatingButton(FloatingButtonMenu floatingButtonMenu, OnItemClickListener onItemClickListener, List<Pair<String, Integer>> list);
}
