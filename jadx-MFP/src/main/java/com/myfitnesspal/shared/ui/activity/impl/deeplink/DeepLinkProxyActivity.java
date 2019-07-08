package com.myfitnesspal.shared.ui.activity.impl.deeplink;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin.AddExerciseDeepLinkMixin;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin.AddFoodDeepLinkMixin;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin.ChallengesMixin;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin.SendVerificationEmailMixin;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin.VenueDeepLinkMixin;
import java.util.regex.Pattern;

public class DeepLinkProxyActivity extends MfpActivity {
    private static final Pattern ADD_EXERCISE_PATH = Pattern.compile("/add_exercise.*");
    private static final Pattern ADD_FOOD_1_PATH = Pattern.compile("/addfood.*");
    private static final Pattern ADD_FOOD_2_PATH = Pattern.compile("/add_food.*");
    private static final Pattern CHALLENGE_DETAILS = Pattern.compile("/challenges/.*");
    private static final Pattern RESTAURANT_LOGGING_VENUE = Pattern.compile("/restaurant_logging/venue/.*");
    private static final Pattern SEND_VERIFICATION_EMAIL = Pattern.compile("/resend_email_verification");

    public boolean showToolbar() {
        return false;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, DeepLinkProxyActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        RunnerLifecycleMixin createMixinForUri = createMixinForUri(getIntent().getData());
        if (createMixinForUri != null) {
            registerMixin(createMixinForUri);
        }
        super.onCreate(bundle);
        setContentView((int) R.layout.progress_overlay_activity);
        if (createMixinForUri == null) {
            finish();
        }
    }

    private RunnerLifecycleMixin createMixinForUri(Uri uri) {
        if (uri != null) {
            String path = uri.getPath();
            if (ADD_FOOD_1_PATH.matcher(path).matches() || ADD_FOOD_2_PATH.matcher(path).matches()) {
                return new AddFoodDeepLinkMixin(this);
            }
            if (ADD_EXERCISE_PATH.matcher(path).matches()) {
                return new AddExerciseDeepLinkMixin(this);
            }
            if (RESTAURANT_LOGGING_VENUE.matcher(path).matches()) {
                return new VenueDeepLinkMixin(this);
            }
            if (SEND_VERIFICATION_EMAIL.matcher(path).matches()) {
                return new SendVerificationEmailMixin(this);
            }
            if (CHALLENGE_DETAILS.matcher(path).matches()) {
                return new ChallengesMixin(this);
            }
        }
        return null;
    }
}
