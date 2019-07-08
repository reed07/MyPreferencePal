package com.myfitnesspal.feature.settings.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SelectReminder_ViewBinding implements Unbinder {
    private SelectReminder target;

    @UiThread
    public SelectReminder_ViewBinding(SelectReminder selectReminder) {
        this(selectReminder, selectReminder.getWindow().getDecorView());
    }

    @UiThread
    public SelectReminder_ViewBinding(SelectReminder selectReminder, View view) {
        this.target = selectReminder;
        selectReminder.listView = (ListView) Utils.findRequiredViewAsType(view, R.id.selectReminderItemsListView, "field 'listView'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        SelectReminder selectReminder = this.target;
        if (selectReminder != null) {
            this.target = null;
            selectReminder.listView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
