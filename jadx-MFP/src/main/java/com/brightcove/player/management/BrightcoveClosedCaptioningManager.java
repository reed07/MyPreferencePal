package com.brightcove.player.management;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.brightcove.player.captioning.BrightcoveCaptionStyle;
import com.brightcove.player.captioning.preferences.CaptionConstants;

public class BrightcoveClosedCaptioningManager {
    public static final String TAG = "BrightcoveClosedCaptioningManager";
    private static BrightcoveClosedCaptioningManager instance;
    private final Context context;

    public static BrightcoveClosedCaptioningManager getInstance(Context context2) {
        if (instance == null) {
            instance = new BrightcoveClosedCaptioningManager(context2);
        }
        return instance;
    }

    private BrightcoveClosedCaptioningManager(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public BrightcoveCaptionStyle getStyle() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        int i = defaultSharedPreferences.getInt(CaptionConstants.PREF_PRESET, 0);
        String string = defaultSharedPreferences.getString(CaptionConstants.PREF_FONT_SIZE, "1.0");
        if (i != -1) {
            return BrightcoveCaptionStyle.createCaptionStyleFromPreset(string, i);
        }
        return BrightcoveCaptionStyle.builder().preset(defaultSharedPreferences.getInt(CaptionConstants.PREF_PRESET, -1)).fontSize(defaultSharedPreferences.getString(CaptionConstants.PREF_FONT_SIZE, "1.0")).typeface(defaultSharedPreferences.getString(CaptionConstants.PREF_TYPEFACE, "sans-serif")).foregroundColor(defaultSharedPreferences.getInt(CaptionConstants.PREF_FOREGROUND_COLOR, -1)).foregroundOpacity(defaultSharedPreferences.getInt(CaptionConstants.PREF_FOREGROUND_OPACITY, -1)).edgeType(defaultSharedPreferences.getInt(CaptionConstants.PREF_EDGE_TYPE, 0)).edgeColor(defaultSharedPreferences.getInt(CaptionConstants.PREF_EDGE_COLOR, -16777216)).backgroundColor(defaultSharedPreferences.getInt(CaptionConstants.PREF_BACKGROUND_COLOR, -16777216)).backgroundOpacity(defaultSharedPreferences.getInt(CaptionConstants.PREF_BACKGROUND_OPACITY, -1)).windowColor(defaultSharedPreferences.getInt(CaptionConstants.PREF_WINDOW_COLOR, 0)).windowOpacity(defaultSharedPreferences.getInt(CaptionConstants.PREF_WINDOW_OPACITY, 0)).build();
    }

    public void setStyle(BrightcoveCaptionStyle brightcoveCaptionStyle) {
        Log.d(TAG, "Writing CaptionsStyle to SharedPreferences");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        if (brightcoveCaptionStyle.preset() != -1) {
            defaultSharedPreferences.edit().putInt(CaptionConstants.PREF_PRESET, brightcoveCaptionStyle.preset()).apply();
        } else {
            defaultSharedPreferences.edit().putString(CaptionConstants.PREF_FONT_SIZE, brightcoveCaptionStyle.fontSize()).putString(CaptionConstants.PREF_TYPEFACE, brightcoveCaptionStyle.typeface()).putInt(CaptionConstants.PREF_FOREGROUND_COLOR, brightcoveCaptionStyle.foregroundColor()).putInt(CaptionConstants.PREF_FOREGROUND_OPACITY, brightcoveCaptionStyle.foregroundOpacity()).putInt(CaptionConstants.PREF_EDGE_TYPE, brightcoveCaptionStyle.edgeType()).putInt(CaptionConstants.PREF_EDGE_COLOR, brightcoveCaptionStyle.edgeColor()).putInt(CaptionConstants.PREF_BACKGROUND_COLOR, brightcoveCaptionStyle.backgroundColor()).putInt(CaptionConstants.PREF_BACKGROUND_OPACITY, brightcoveCaptionStyle.backgroundOpacity()).putInt(CaptionConstants.PREF_WINDOW_COLOR, brightcoveCaptionStyle.windowColor()).putInt(CaptionConstants.PREF_WINDOW_OPACITY, brightcoveCaptionStyle.windowOpacity()).apply();
        }
    }
}
