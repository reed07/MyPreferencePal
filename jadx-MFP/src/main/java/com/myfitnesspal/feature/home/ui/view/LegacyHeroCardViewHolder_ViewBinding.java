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

public class LegacyHeroCardViewHolder_ViewBinding implements Unbinder {
    private LegacyHeroCardViewHolder target;

    @UiThread
    public LegacyHeroCardViewHolder_ViewBinding(LegacyHeroCardViewHolder legacyHeroCardViewHolder, View view) {
        this.target = legacyHeroCardViewHolder;
        legacyHeroCardViewHolder.titleTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.hero_name, "field 'titleTextView'", TextView.class);
        legacyHeroCardViewHolder.contentTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.hero_description, "field 'contentTextView'", TextView.class);
        legacyHeroCardViewHolder.ctaContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.cta_container, "field 'ctaContainer'", ViewGroup.class);
        legacyHeroCardViewHolder.closeBtn = Utils.findRequiredView(view, R.id.close, "field 'closeBtn'");
        legacyHeroCardViewHolder.imageView = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.hero_image, "field 'imageView'", MfpImageView.class);
    }

    @CallSuper
    public void unbind() {
        LegacyHeroCardViewHolder legacyHeroCardViewHolder = this.target;
        if (legacyHeroCardViewHolder != null) {
            this.target = null;
            legacyHeroCardViewHolder.titleTextView = null;
            legacyHeroCardViewHolder.contentTextView = null;
            legacyHeroCardViewHolder.ctaContainer = null;
            legacyHeroCardViewHolder.closeBtn = null;
            legacyHeroCardViewHolder.imageView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
