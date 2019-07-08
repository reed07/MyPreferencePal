package com.myfitnesspal.feature.blog.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.blog.ui.fragment.BlogFragment;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class BlogActivity extends MfpActivity {
    public static final String MEDIUM = "medium";
    public static final String SOURCE = "source";
    private BlogFragment fragment;

    public String getAnalyticsScreenTag() {
        return Screens.BLOG;
    }

    public static Intent newStartIntent(Context context, String str, String str2, String str3) {
        return newStartIntent(context).putExtra("url", str).putExtra("source", str2).putExtra("medium", str3);
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, BlogActivity.class);
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
        String tag = tag(BlogFragment.class);
        if (bundle != null) {
            this.fragment = (BlogFragment) getSupportFragmentManager().findFragmentByTag(tag);
        }
        if (this.fragment == null) {
            Bundle extras = getIntent().getExtras();
            this.fragment = BlogFragment.newInstance(extras.getString("url", ""), extras.getString("source", ""), extras.getString("medium", ""));
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, this.fragment, tag).commit();
    }
}
