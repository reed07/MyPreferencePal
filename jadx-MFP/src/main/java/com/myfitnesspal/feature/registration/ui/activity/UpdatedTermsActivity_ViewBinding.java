package com.myfitnesspal.feature.registration.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class UpdatedTermsActivity_ViewBinding implements Unbinder {
    private UpdatedTermsActivity target;

    @UiThread
    public UpdatedTermsActivity_ViewBinding(UpdatedTermsActivity updatedTermsActivity) {
        this(updatedTermsActivity, updatedTermsActivity.getWindow().getDecorView());
    }

    @UiThread
    public UpdatedTermsActivity_ViewBinding(UpdatedTermsActivity updatedTermsActivity, View view) {
        this.target = updatedTermsActivity;
        updatedTermsActivity.disclaimerTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.updated_terms_disclaimer, "field 'disclaimerTextView'", TextView.class);
        updatedTermsActivity.titleTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.updated_terms_title, "field 'titleTextView'", TextView.class);
        updatedTermsActivity.summaryTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.updated_terms_summary, "field 'summaryTextView'", TextView.class);
        updatedTermsActivity.acceptBtn = (Button) Utils.findRequiredViewAsType(view, R.id.accept_btn, "field 'acceptBtn'", Button.class);
        updatedTermsActivity.contentContainer = Utils.findRequiredView(view, R.id.content_container, "field 'contentContainer'");
        updatedTermsActivity.buttonContainer = Utils.findRequiredView(view, R.id.button_container, "field 'buttonContainer'");
        updatedTermsActivity.loadingView = Utils.findRequiredView(view, R.id.loading, "field 'loadingView'");
        updatedTermsActivity.errorDisclaimerTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.error_disclaimer, "field 'errorDisclaimerTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        UpdatedTermsActivity updatedTermsActivity = this.target;
        if (updatedTermsActivity != null) {
            this.target = null;
            updatedTermsActivity.disclaimerTextView = null;
            updatedTermsActivity.titleTextView = null;
            updatedTermsActivity.summaryTextView = null;
            updatedTermsActivity.acceptBtn = null;
            updatedTermsActivity.contentContainer = null;
            updatedTermsActivity.buttonContainer = null;
            updatedTermsActivity.loadingView = null;
            updatedTermsActivity.errorDisclaimerTextView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
