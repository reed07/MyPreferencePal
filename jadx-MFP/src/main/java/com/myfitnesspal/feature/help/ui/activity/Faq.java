package com.myfitnesspal.feature.help.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri.Builder;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.constants.SharedConstants.Uri;
import com.myfitnesspal.shared.model.FullScreenWebViewIntentExtras;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import javax.inject.Inject;

public class Faq extends FaqFeedbackBaseActivity {
    @Inject
    ConfigService configService;

    public String getBaseUrlRelativePath() {
        return Uri.BASE_FAQ_RELATIVE_PATH;
    }

    public static Intent newStartIntent(Context context) {
        return newStartIntent(context, 100, context.getString(R.string.faq));
    }

    public static Intent newStartIntent(Context context, int i, String str) {
        return new Intent(context, Faq.class).putExtra("web_view_intent_extras", new FullScreenWebViewIntentExtras().setTitle(str)).putExtra("tag", i);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public String getUrl() {
        String str = null;
        if (!ConnectivityUtil.isOnline()) {
            return null;
        }
        Builder baseUrlBuilder = getBaseUrlBuilder();
        int i = ExtrasUtils.getInt(getIntent(), "tag", 100);
        if (i != 100) {
            str = this.configService.getFaqUrl(i);
        }
        if (Strings.notEmpty(str)) {
            baseUrlBuilder.appendQueryParameter(Http.ARTICLE, str);
        }
        return baseUrlBuilder.toString();
    }
}
