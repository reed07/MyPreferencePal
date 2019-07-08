package com.myfitnesspal.feature.recipes.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.facebook.appevents.AppEventsConstants;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;

public class RecipeImporterShareProxy extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String str = "android.intent.action.VIEW";
        startActivity(new Intent(str, Uri.parse("mfp://myfitnesspal/recipe_parser").buildUpon().appendQueryParameter("url", Strings.toString(ExtrasUtils.getString(getIntent(), "android.intent.extra.TEXT"))).appendQueryParameter("version", AppEventsConstants.EVENT_PARAM_VALUE_YES).build()));
        finish();
    }
}
