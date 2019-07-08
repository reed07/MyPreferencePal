package com.myfitnesspal.feature.addfriends.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class AddFriendsBase_ViewBinding implements Unbinder {
    private AddFriendsBase target;

    @UiThread
    public AddFriendsBase_ViewBinding(AddFriendsBase addFriendsBase) {
        this(addFriendsBase, addFriendsBase.getWindow().getDecorView());
    }

    @UiThread
    public AddFriendsBase_ViewBinding(AddFriendsBase addFriendsBase, View view) {
        this.target = addFriendsBase;
        addFriendsBase.list = (ListView) Utils.findRequiredViewAsType(view, 16908298, "field 'list'", ListView.class);
        addFriendsBase.empty = (TextView) Utils.findRequiredViewAsType(view, 16908292, "field 'empty'", TextView.class);
        addFriendsBase.switcher = (ViewSwitcher) Utils.findRequiredViewAsType(view, R.id.switcher, "field 'switcher'", ViewSwitcher.class);
    }

    @CallSuper
    public void unbind() {
        AddFriendsBase addFriendsBase = this.target;
        if (addFriendsBase != null) {
            this.target = null;
            addFriendsBase.list = null;
            addFriendsBase.empty = null;
            addFriendsBase.switcher = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
