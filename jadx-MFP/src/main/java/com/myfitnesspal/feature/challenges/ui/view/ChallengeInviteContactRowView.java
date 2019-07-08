package com.myfitnesspal.feature.challenges.ui.view;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addfriends.ui.view.AddFriendsItemContacts;
import com.myfitnesspal.shared.model.v1.EmailFriend;

public class ChallengeInviteContactRowView extends AddFriendsItemContacts {
    public ChallengeInviteContactRowView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void updateUI() {
        super.updateUI();
        this.action.setText(((EmailFriend) this.friend).hasBeenInvited() ? R.string.invite_friend_invited : R.string.invite_friend);
    }
}
