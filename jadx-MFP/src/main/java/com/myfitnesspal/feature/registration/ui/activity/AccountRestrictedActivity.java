package com.myfitnesspal.feature.registration.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.model.FullScreenWebViewIntentExtras;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.Strings;
import javax.inject.Inject;

public class AccountRestrictedActivity extends FullScreenWebView {
    @Inject
    NavigationHelper navigationHelper;

    public static Intent newStartIntent(Context context, ApiUrlProvider apiUrlProvider, int i) {
        return new Intent(context, AccountRestrictedActivity.class).putExtra("web_view_intent_extras", new FullScreenWebViewIntentExtras().setTitle(context.getString(R.string.account_restricted)).setUrl(Uri.parse(apiUrlProvider.getAccountRestrictedUrl()).buildUpon().appendQueryParameter("user_status", Strings.toString(Integer.valueOf(i))).build().toString()).setHandleAllClicksExternally(true));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    public void onUpPressed() {
        logUserOut();
    }

    public void onBackPressed() {
        logUserOut();
    }

    private void logUserOut() {
        getSession().logoutAndNavigateToLoginActivity();
    }
}
