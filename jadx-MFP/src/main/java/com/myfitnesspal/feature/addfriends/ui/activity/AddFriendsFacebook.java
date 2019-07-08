package com.myfitnesspal.feature.addfriends.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addfriends.service.FacebookFriendOnMfpService;
import com.myfitnesspal.feature.addfriends.ui.view.AddFriendsItemFacebook;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v1.FacebookFriend;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;

public class AddFriendsFacebook extends AddFriendsBase<FacebookFriendOnMfpService, FacebookFriend> {
    @Inject
    Lazy<FacebookFriendOnMfpService> facebookFriendService;

    /* access modifiers changed from: protected */
    public int getEmptyTextId() {
        return R.string.addfriends_empty_facebook;
    }

    /* access modifiers changed from: protected */
    public int getMfpHeaderForMoreThanOneFriend() {
        return R.string.addfriends_header_facebook_N;
    }

    /* access modifiers changed from: protected */
    public int getMfpHeaderForOneFriend() {
        return R.string.addfriends_header_facebook_1;
    }

    /* access modifiers changed from: protected */
    public int getNonMfpHeader() {
        return R.string.addfriends_invite_facebook;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, AddFriendsFacebook.class);
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        ((FacebookFriendOnMfpService) this.facebookFriendService.get()).setActivityContext(this);
        this.justJoinedFbFriendsNames = ExtrasUtils.getStringArrayList(getIntent(), Extras.USERNAMES);
        setTitle(R.string.addfriends_title_facebook);
        if (bundle != null) {
            if (CollectionUtils.isEmpty((Collection<?>) this.justJoinedFbFriendsNames)) {
                this.justJoinedFbFriendsNames = BundleUtils.getStringArrayList(bundle, Extras.JUST_JOINED_FB_FRIENDS);
            }
            this.nonMfpFriends = BundleUtils.getParcelableArrayAsList(bundle, Extras.NON_MFP_FRIENDS, FacebookFriend.class.getClassLoader());
            this.mfpFriends = BundleUtils.getParcelableArrayAsList(bundle, Extras.MFP_FRIENDS, FacebookFriend.class.getClassLoader());
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (CollectionUtils.notEmpty((Collection<?>) this.justJoinedFbFriendsNames)) {
            bundle.putStringArrayList(Extras.JUST_JOINED_FB_FRIENDS, (ArrayList) this.justJoinedFbFriendsNames);
        }
        if (CollectionUtils.notEmpty((Collection<?>) this.mfpFriends)) {
            bundle.putParcelableArray(Extras.MFP_FRIENDS, (Parcelable[]) this.mfpFriends.toArray(new FacebookFriend[this.mfpFriends.size()]));
        }
        if (CollectionUtils.notEmpty((Collection<?>) this.nonMfpFriends)) {
            bundle.putParcelableArray(Extras.NON_MFP_FRIENDS, (Parcelable[]) this.nonMfpFriends.toArray(new FacebookFriend[this.nonMfpFriends.size()]));
        }
    }

    /* access modifiers changed from: protected */
    public FacebookFriendOnMfpService getService() {
        return (FacebookFriendOnMfpService) this.facebookFriendService.get();
    }

    /* access modifiers changed from: protected */
    public View getFriendView(FacebookFriend facebookFriend, View view) {
        if (!(view instanceof AddFriendsItemFacebook)) {
            view = new AddFriendsItemFacebook(this);
        }
        AddFriendsItemFacebook addFriendsItemFacebook = (AddFriendsItemFacebook) view;
        addFriendsItemFacebook.setFriend(facebookFriend);
        return addFriendsItemFacebook;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1000) {
            this.ignoreRefresh = true;
        }
    }
}
