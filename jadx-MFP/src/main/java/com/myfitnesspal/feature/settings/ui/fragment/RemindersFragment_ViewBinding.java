package com.myfitnesspal.feature.settings.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class RemindersFragment_ViewBinding implements Unbinder {
    private RemindersFragment target;

    @UiThread
    public RemindersFragment_ViewBinding(RemindersFragment remindersFragment, View view) {
        this.target = remindersFragment;
        remindersFragment.noReminders = Utils.findRequiredView(view, R.id.noRemindersContainer, "field 'noReminders'");
        remindersFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
        remindersFragment.fab = Utils.findRequiredView(view, R.id.fabActionPlus, "field 'fab'");
    }

    @CallSuper
    public void unbind() {
        RemindersFragment remindersFragment = this.target;
        if (remindersFragment != null) {
            this.target = null;
            remindersFragment.noReminders = null;
            remindersFragment.recyclerView = null;
            remindersFragment.fab = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
