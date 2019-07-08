package com.myfitnesspal.feature.addfriends.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import com.myfitnesspal.shared.model.v1.EmailFriend;
import com.uacf.core.util.Strings;

public class AddFriendsItemContacts extends AddFriendsItem<EmailFriend> {
    public AddFriendsItemContacts(Context context) {
        this(context, null);
    }

    public AddFriendsItemContacts(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AddFriendsItemContacts(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setFriend(EmailFriend emailFriend) {
        super.setFriend(emailFriend);
        setImage(emailFriend.getThumbnailUrl());
        setPrimaryText(Strings.toString(emailFriend.getName()));
        showFriendsInCommon(0);
    }
}
