package com.brightcove.player.captioning.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.brightcove.player.R;
import com.brightcove.player.captioning.BrightcoveCaptionStyle;
import com.brightcove.player.captioning.BrightcoveClosedCaptioningTextView;

public class PresetPreference extends ListDialogPreference {
    private static final float DEFAULT_FONT_SIZE = 32.0f;
    public static final float PRESET_CUSTOM = -1.0f;

    public PresetPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDialogLayoutResource(R.layout.grid_picker_dialog);
        setListItemLayoutResource(R.layout.preset_picker_item);
    }

    public BrightcoveCaptionStyle getStyleForPreset(int i) {
        if (i != -1) {
            return BrightcoveCaptionStyle.createCaptionStyleFromPreset(PreferenceManager.getDefaultSharedPreferences(getContext()).getString(CaptionConstants.PREF_FONT_SIZE, "1.0"), i);
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        return BrightcoveCaptionStyle.builder().preset(defaultSharedPreferences.getInt(CaptionConstants.PREF_PRESET, -1)).fontSize(defaultSharedPreferences.getString(CaptionConstants.PREF_FONT_SIZE, "1.0")).typeface(defaultSharedPreferences.getString(CaptionConstants.PREF_TYPEFACE, "sans-serif")).foregroundColor(defaultSharedPreferences.getInt(CaptionConstants.PREF_FOREGROUND_COLOR, -1)).foregroundOpacity(defaultSharedPreferences.getInt(CaptionConstants.PREF_FOREGROUND_OPACITY, -1)).edgeType(defaultSharedPreferences.getInt(CaptionConstants.PREF_EDGE_TYPE, 0)).edgeColor(defaultSharedPreferences.getInt(CaptionConstants.PREF_EDGE_COLOR, -16777216)).backgroundColor(defaultSharedPreferences.getInt(CaptionConstants.PREF_BACKGROUND_COLOR, -16777216)).backgroundOpacity(defaultSharedPreferences.getInt(CaptionConstants.PREF_BACKGROUND_OPACITY, -1)).windowColor(defaultSharedPreferences.getInt(CaptionConstants.PREF_WINDOW_COLOR, 0)).windowOpacity(defaultSharedPreferences.getInt(CaptionConstants.PREF_WINDOW_OPACITY, 0)).build();
    }

    public boolean shouldDisableDependents() {
        return ((float) getValue()) != -1.0f || super.shouldDisableDependents();
    }

    /* access modifiers changed from: protected */
    public void onBindListItem(View view, int i) {
        BrightcoveClosedCaptioningTextView brightcoveClosedCaptioningTextView = (BrightcoveClosedCaptioningTextView) view.findViewById(R.id.preview);
        brightcoveClosedCaptioningTextView.setStyle(getStyleForPreset(getValueAt(i)));
        brightcoveClosedCaptioningTextView.setTextSize(getContext().getResources().getDisplayMetrics().density * DEFAULT_FONT_SIZE);
        CharSequence titleAt = getTitleAt(i);
        if (titleAt != null) {
            ((TextView) view.findViewById(R.id.summary)).setText(titleAt);
        }
    }
}
