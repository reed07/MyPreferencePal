package com.myfitnesspal.feature.community.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.community.ui.fragment.CommunityFragment;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class CommunityActivity extends MfpActivity {
    private CommunityFragment fragment;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, CommunityActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.fragment_container);
        initFragment(bundle);
    }

    public boolean backPressed() {
        return this.fragment.backPressed();
    }

    private void initFragment(Bundle bundle) {
        String tag = tag(CommunityFragment.class);
        if (bundle != null) {
            this.fragment = (CommunityFragment) getSupportFragmentManager().findFragmentByTag(tag);
        }
        if (this.fragment == null) {
            this.fragment = CommunityFragment.newInstance(getIntent().getExtras());
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, this.fragment, tag).commit();
    }
}
