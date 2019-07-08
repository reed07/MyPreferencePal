package com.myfitnesspal.feature.addfriends.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import dagger.Lazy;
import javax.inject.Inject;

public class AddFriendsSplash extends MfpActivity {
    @Inject
    Lazy<GlobalSettingsService> globalSettingsService;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, AddFriendsSplash.class);
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.add_friends_splash);
        setTitle(R.string.addfriends_title);
        findById(R.id.btn_add_from_facebook).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddFriendsSplash.this.getNavigationHelper().withIntent(AddFriendsFacebook.newStartIntent(AddFriendsSplash.this)).startActivity();
            }
        });
        findById(R.id.btn_add_from_contacts).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddFriendsSplash.this.getNavigationHelper().withIntent(AddFriendsContacts.newStartIntent(AddFriendsSplash.this)).startActivity();
            }
        });
        findById(R.id.btn_maybe_later).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddFriendsSplash.lambda$onCreate$2(AddFriendsSplash.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$onCreate$2(AddFriendsSplash addFriendsSplash, View view) {
        ((LocalSettingsService) addFriendsSplash.localSettingsService.get()).setShowInvitePromotionView(false);
        ((GlobalSettingsService) addFriendsSplash.globalSettingsService.get()).setShowInvitationPromotinalScreen(addFriendsSplash.getSession().getUser().getUsername(), false);
        addFriendsSplash.getNavigationHelper().setResult(-1).done();
    }
}
