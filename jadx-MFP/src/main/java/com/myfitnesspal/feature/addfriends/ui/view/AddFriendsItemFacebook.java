package com.myfitnesspal.feature.addfriends.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import com.myfitnesspal.shared.model.v1.FacebookFriend;
import com.uacf.core.util.Strings;
import java.net.MalformedURLException;
import java.net.URL;

public class AddFriendsItemFacebook extends AddFriendsItem<FacebookFriend> {
    public AddFriendsItemFacebook(Context context) {
        this(context, null);
    }

    public AddFriendsItemFacebook(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AddFriendsItemFacebook(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setFriend(FacebookFriend facebookFriend) {
        String str;
        super.setFriend(facebookFriend);
        try {
            str = Strings.toString(new URL(String.format("https://graph.facebook.com/%s/picture", new Object[]{facebookFriend.getId()})));
        } catch (MalformedURLException unused) {
            str = null;
        }
        setImage(str);
        setPrimaryText(facebookFriend.getName());
        showFriendsInCommon(0);
    }
}
