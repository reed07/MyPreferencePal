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

public class HeroCardViewHolder_ViewBinding implements Unbinder {
    private HeroCardViewHolder target;

    @UiThread
    public HeroCardViewHolder_ViewBinding(HeroCardViewHolder heroCardViewHolder, View view) {
        this.target = heroCardViewHolder;
        heroCardViewHolder.titleTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.hero_name, "field 'titleTextView'", TextView.class);
        heroCardViewHolder.contentTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.hero_description, "field 'contentTextView'", TextView.class);
        heroCardViewHolder.ctaContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.cta_container, "field 'ctaContainer'", ViewGroup.class);
        heroCardViewHolder.closeBtn = Utils.findRequiredView(view, R.id.close, "field 'closeBtn'");
        heroCardViewHolder.imageView = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.hero_image, "field 'imageView'", MfpImageView.class);
    }

    @CallSuper
    public void unbind() {
        HeroCardViewHolder heroCardViewHolder = this.target;
        if (heroCardViewHolder != null) {
            this.target = null;
            heroCardViewHolder.titleTextView = null;
            heroCardViewHolder.contentTextView = null;
            heroCardViewHolder.ctaContainer = null;
            heroCardViewHolder.closeBtn = null;
            heroCardViewHolder.imageView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
