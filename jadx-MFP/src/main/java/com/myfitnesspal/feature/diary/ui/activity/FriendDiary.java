package com.myfitnesspal.feature.diary.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.model.FriendDiaryBundleInfo;
import com.myfitnesspal.feature.diary.ui.fragment.FriendDiaryFragment;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.MiniUserInfoDto;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.BottomBarMixin;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.uacf.core.util.ExtrasUtils;
import java.util.Date;

public class FriendDiary extends MfpActivity {
    private static final String FRIEND_DIARY_FRAGMENT_TAG = "friend_diary_fragment";

    public int getCustomToolbarLayoutResId() {
        return R.layout.friend_diary_toolbar;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, FriendDiary.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        registerMixin(new BottomBarMixin(this));
        super.onCreate(bundle);
        setContentView((int) R.layout.diary);
        component().inject(this);
        ((BottomBarMixin) mixin(BottomBarMixin.class)).setEnabled(false);
        FriendDiaryBundleInfo friendDiaryBundleInfoFromIntent = getFriendDiaryBundleInfoFromIntent();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager.findFragmentByTag(FRIEND_DIARY_FRAGMENT_TAG) == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, FriendDiaryFragment.newInstance(friendDiaryBundleInfoFromIntent), FRIEND_DIARY_FRAGMENT_TAG).commit();
        }
        loadProfileImage(friendDiaryBundleInfoFromIntent);
    }

    private FriendDiaryBundleInfo getFriendDiaryBundleInfoFromIntent() {
        long j;
        String str;
        String str2;
        String str3;
        Intent intent = getIntent();
        MiniUserInfoDto miniUserInfoDto = (MiniUserInfoDto) ExtrasUtils.getParcelable(intent, Extras.MINI_USER_INFO, MiniUserInfoDto.class.getClassLoader());
        if (miniUserInfoDto != null) {
            String username = miniUserInfoDto.getUsername();
            str3 = username;
            str2 = miniUserInfoDto.getUid();
            str = miniUserInfoDto.getImageURL();
            j = miniUserInfoDto.getMasterId();
        } else {
            str3 = ExtrasUtils.getString(intent, Extras.USER_NAME, "");
            str2 = ExtrasUtils.getString(intent, Extras.USER_UID, "");
            str = ExtrasUtils.getString(intent, "image_url", "");
            j = Long.MIN_VALUE;
        }
        long j2 = ExtrasUtils.getLong(intent, "data", -1);
        FriendDiaryBundleInfo friendDiaryBundleInfo = new FriendDiaryBundleInfo(str3, str2, str, j, ExtrasUtils.getString(intent, Extras.PASS, ""), j2 != -1 ? new Date(j2) : new Date());
        return friendDiaryBundleInfo;
    }

    private void loadProfileImage(FriendDiaryBundleInfo friendDiaryBundleInfo) {
        ((MfpImageView) findViewById(R.id.profile_image)).setUrl(friendDiaryBundleInfo.getUserImageUrl());
    }

    public void setTitle(CharSequence charSequence) {
        TextView textView = (TextView) findById(R.id.title);
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
