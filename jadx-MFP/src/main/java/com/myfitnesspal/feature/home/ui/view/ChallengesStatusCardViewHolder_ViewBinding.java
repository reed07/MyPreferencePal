package com.myfitnesspal.feature.home.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.MfpImageView;

public class ChallengesStatusCardViewHolder_ViewBinding implements Unbinder {
    private ChallengesStatusCardViewHolder target;

    @UiThread
    public ChallengesStatusCardViewHolder_ViewBinding(ChallengesStatusCardViewHolder challengesStatusCardViewHolder, View view) {
        this.target = challengesStatusCardViewHolder;
        challengesStatusCardViewHolder.titleTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.hero_name, "field 'titleTextView'", TextView.class);
        challengesStatusCardViewHolder.contentTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.hero_description, "field 'contentTextView'", TextView.class);
        challengesStatusCardViewHolder.ctaContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.cta_container, "field 'ctaContainer'", ViewGroup.class);
        challengesStatusCardViewHolder.closeBtn = Utils.findRequiredView(view, R.id.close, "field 'closeBtn'");
        challengesStatusCardViewHolder.imageView = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.hero_image, "field 'imageView'", MfpImageView.class);
    }

    @CallSuper
    public void unbind() {
        ChallengesStatusCardViewHolder challengesStatusCardViewHolder = this.target;
        if (challengesStatusCardViewHolder != null) {
            this.target = null;
            challengesStatusCardViewHolder.titleTextView = null;
            challengesStatusCardViewHolder.contentTextView = null;
            challengesStatusCardViewHolder.ctaContainer = null;
            challengesStatusCardViewHolder.closeBtn = null;
            challengesStatusCardViewHolder.imageView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
