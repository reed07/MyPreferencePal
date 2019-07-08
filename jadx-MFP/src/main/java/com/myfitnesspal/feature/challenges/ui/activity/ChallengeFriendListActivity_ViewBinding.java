package com.myfitnesspal.feature.challenges.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class ChallengeFriendListActivity_ViewBinding implements Unbinder {
    private ChallengeFriendListActivity target;

    @UiThread
    public ChallengeFriendListActivity_ViewBinding(ChallengeFriendListActivity challengeFriendListActivity) {
        this(challengeFriendListActivity, challengeFriendListActivity.getWindow().getDecorView());
    }

    @UiThread
    public ChallengeFriendListActivity_ViewBinding(ChallengeFriendListActivity challengeFriendListActivity, View view) {
        this.target = challengeFriendListActivity;
        challengeFriendListActivity.listView = (ListView) Utils.findRequiredViewAsType(view, 16908298, "field 'listView'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        ChallengeFriendListActivity challengeFriendListActivity = this.target;
        if (challengeFriendListActivity != null) {
            this.target = null;
            challengeFriendListActivity.listView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
