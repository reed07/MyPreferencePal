package com.myfitnesspal.feature.addfriends.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class AddFriendsParent_ViewBinding implements Unbinder {
    private AddFriendsParent target;

    @UiThread
    public AddFriendsParent_ViewBinding(AddFriendsParent addFriendsParent) {
        this(addFriendsParent, addFriendsParent.getWindow().getDecorView());
    }

    @UiThread
    public AddFriendsParent_ViewBinding(AddFriendsParent addFriendsParent, View view) {
        this.target = addFriendsParent;
        addFriendsParent.btnFb = Utils.findRequiredView(view, R.id.add_friends_facebook, "field 'btnFb'");
        addFriendsParent.btnContacts = Utils.findRequiredView(view, R.id.add_friends_contacts, "field 'btnContacts'");
        addFriendsParent.btnEmail = Utils.findRequiredView(view, R.id.add_friend_email, "field 'btnEmail'");
    }

    @CallSuper
    public void unbind() {
        AddFriendsParent addFriendsParent = this.target;
        if (addFriendsParent != null) {
            this.target = null;
            addFriendsParent.btnFb = null;
            addFriendsParent.btnContacts = null;
            addFriendsParent.btnEmail = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
