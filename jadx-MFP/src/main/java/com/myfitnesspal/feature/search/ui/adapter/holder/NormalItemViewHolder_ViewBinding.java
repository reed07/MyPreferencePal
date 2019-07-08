package com.myfitnesspal.feature.search.ui.adapter.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class NormalItemViewHolder_ViewBinding implements Unbinder {
    private NormalItemViewHolder target;

    @UiThread
    public NormalItemViewHolder_ViewBinding(NormalItemViewHolder normalItemViewHolder, View view) {
        this.target = normalItemViewHolder;
        normalItemViewHolder.imageContainer = Utils.findRequiredView(view, R.id.image_container, "field 'imageContainer'");
        normalItemViewHolder.imageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.image_view, "field 'imageView'", ImageView.class);
        normalItemViewHolder.progress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progress, "field 'progress'", ProgressBar.class);
        normalItemViewHolder.foodDescriptionTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.text_primary, "field 'foodDescriptionTxt'", TextView.class);
        normalItemViewHolder.foodDetailsTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.text_secondary, "field 'foodDetailsTxt'", TextView.class);
        normalItemViewHolder.caloriesTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.txtCalories, "field 'caloriesTxt'", TextView.class);
        normalItemViewHolder.multiSelectCheckBox = (CompoundButton) Utils.findRequiredViewAsType(view, R.id.multiSelectCheckBox, "field 'multiSelectCheckBox'", CompoundButton.class);
    }

    @CallSuper
    public void unbind() {
        NormalItemViewHolder normalItemViewHolder = this.target;
        if (normalItemViewHolder != null) {
            this.target = null;
            normalItemViewHolder.imageContainer = null;
            normalItemViewHolder.imageView = null;
            normalItemViewHolder.progress = null;
            normalItemViewHolder.foodDescriptionTxt = null;
            normalItemViewHolder.foodDetailsTxt = null;
            normalItemViewHolder.caloriesTxt = null;
            normalItemViewHolder.multiSelectCheckBox = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
