package com.myfitnesspal.feature.help.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri.Builder;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.SharedConstants.Uri;
import com.myfitnesspal.shared.model.FullScreenWebViewIntentExtras;
import com.uacf.core.util.ExtrasUtils;

public class FeedbackActivity extends FaqFeedbackBaseActivity {
    private static final String EXTRA_IS_BETA = "is_beta";

    public String getBaseUrlRelativePath() {
        return Uri.BASE_BETA_FEEDBACK_RELATIVE_PATH;
    }

    public static Intent newStartIntentForBetaFeedback(Context context) {
        return new Intent(context, FeedbackActivity.class).putExtra("web_view_intent_extras", new FullScreenWebViewIntentExtras().setTitle(context.getString(R.string.beta_feedback))).putExtra(EXTRA_IS_BETA, true);
    }

    public String getUrl() {
        Builder baseUrlBuilder = getBaseUrlBuilder();
        if (ExtrasUtils.getBoolean(getIntent(), EXTRA_IS_BETA)) {
            baseUrlBuilder.appendQueryParameter("beta", "true");
        }
        return baseUrlBuilder.toString();
    }
}
