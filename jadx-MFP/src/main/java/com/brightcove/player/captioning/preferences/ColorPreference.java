package com.brightcove.player.captioning.preferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.brightcove.player.R;

public class ColorPreference extends ListDialogPreference {
    private ColorDrawable previewColor;
    private boolean previewEnabled;

    public ColorPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDialogLayoutResource(R.layout.grid_picker_dialog);
        setListItemLayoutResource(R.layout.color_picker_item);
    }

    public void setPreviewEnabled(boolean z) {
        if (this.previewEnabled != z) {
            this.previewEnabled = z;
            if (z) {
                setWidgetLayoutResource(R.layout.preference_color);
            } else {
                setWidgetLayoutResource(0);
            }
        }
    }

    public boolean shouldDisableDependents() {
        return Color.alpha(getValue()) == 0 || super.shouldDisableDependents();
    }

    /* access modifiers changed from: protected */
    public CharSequence getTitleAt(int i) {
        CharSequence titleAt = super.getTitleAt(i);
        if (titleAt != null) {
            return titleAt;
        }
        int valueAt = getValueAt(i);
        int red = Color.red(valueAt);
        int green = Color.green(valueAt);
        int blue = Color.blue(valueAt);
        return getContext().getString(R.string.color_custom, new Object[]{Integer.valueOf(red), Integer.valueOf(green), Integer.valueOf(blue)});
    }

    /* access modifiers changed from: protected */
    @TargetApi(16)
    public void onBindView(View view) {
        super.onBindView(view);
        if (this.previewEnabled) {
            ImageView imageView = (ImageView) view.findViewById(R.id.color_preview);
            int value = getValue();
            if (Color.alpha(value) < 255) {
                imageView.setBackgroundResource(R.drawable.transparency_tileable);
            } else {
                imageView.setBackground(null);
            }
            ColorDrawable colorDrawable = this.previewColor;
            if (colorDrawable == null) {
                this.previewColor = new ColorDrawable(value);
                imageView.setImageDrawable(this.previewColor);
            } else {
                colorDrawable.setColor(value);
            }
            CharSequence summary = getSummary();
            if (!TextUtils.isEmpty(summary)) {
                imageView.setContentDescription(summary);
            } else {
                imageView.setContentDescription(null);
            }
            imageView.setAlpha(isEnabled() ? 1.0f : 0.2f);
        }
    }

    /* access modifiers changed from: protected */
    @TargetApi(16)
    public void onBindListItem(View view, int i) {
        int valueAt = getValueAt(i);
        ImageView imageView = (ImageView) view.findViewById(R.id.color_swatch);
        if (Color.alpha(valueAt) < 255) {
            imageView.setBackgroundResource(R.drawable.transparency_tileable);
        } else {
            imageView.setBackground(null);
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof ColorDrawable) {
            ((ColorDrawable) drawable).setColor(valueAt);
        } else {
            imageView.setImageDrawable(new ColorDrawable(valueAt));
        }
        CharSequence titleAt = getTitleAt(i);
        if (titleAt != null) {
            ((TextView) view.findViewById(R.id.summary)).setText(titleAt);
        }
    }
}
