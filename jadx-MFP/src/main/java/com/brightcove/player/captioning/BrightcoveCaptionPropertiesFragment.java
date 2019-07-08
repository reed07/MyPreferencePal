package com.brightcove.player.captioning;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.brightcove.player.R;
import com.brightcove.player.captioning.preferences.CaptionConstants;
import com.brightcove.player.captioning.preferences.ColorPreference;
import com.brightcove.player.captioning.preferences.EdgeTypePreference;
import com.brightcove.player.captioning.preferences.ListDialogPreference;
import com.brightcove.player.captioning.preferences.ListDialogPreference.OnValueChangedListener;
import com.brightcove.player.captioning.preferences.ListPreferenceWithSummary;
import com.brightcove.player.captioning.preferences.PresetPreference;
import com.brightcove.player.management.BrightcoveClosedCaptioningManager;

@TargetApi(16)
public class BrightcoveCaptionPropertiesFragment extends PreferenceFragment implements OnPreferenceChangeListener, OnValueChangedListener {
    public static final String TAG = "BrightcoveCaptionPropertiesFragment";
    private ColorPreference backgroundColor;
    private ColorPreference backgroundOpacity;
    private SwitchPreference captionsEnabled;
    private PreferenceCategory custom;
    private boolean customShowing;
    private ColorPreference edgeColor;
    private EdgeTypePreference edgeType;
    private ListPreferenceWithSummary fontSize;
    private ColorPreference foregroundColor;
    private ColorPreference foregroundOpacity;
    private PresetPreference preset;
    private BrightcoveClosedCaptioningTextView previewText;
    private PreferenceCategory standard;
    private ListPreferenceWithSummary typeface;
    private ColorPreference windowColor;
    private ColorPreference windowOpacity;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.brightcove_captioning_settings);
        setupPreferences();
        refreshShowingCustom();
        initializeListeners();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.captioning_preview, viewGroup, false);
        ((ViewGroup) inflate.findViewById(R.id.properties_fragment)).addView(super.onCreateView(layoutInflater, viewGroup, bundle), -1, -1);
        this.previewText = (BrightcoveClosedCaptioningTextView) inflate.findViewById(R.id.preview_text);
        this.previewText.setStyle(BrightcoveClosedCaptioningManager.getInstance(getActivity()).getStyle());
        updatePreferencesEnabled(PreferenceManager.getDefaultSharedPreferences(getActivity()).getBoolean(CaptionConstants.PREF_MASTER_SWITCH, false));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    private void updatePreferencesEnabled(boolean z) {
        this.custom.setEnabled(z);
        this.standard.setEnabled(z);
        this.captionsEnabled.setEnabled(true);
        if (this.previewText.getParent() != null) {
            ((ViewGroup) this.previewText.getParent()).setVisibility(z ? 0 : 4);
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference.getKey().equals(CaptionConstants.PREF_MASTER_SWITCH)) {
            updatePreferencesEnabled(((Boolean) obj).booleanValue());
        } else {
            BrightcoveCaptionStyle updateStyleByPreferenceKey = BrightcoveCaptionStyle.updateStyleByPreferenceKey(BrightcoveClosedCaptioningManager.getInstance(getActivity()).getStyle(), preference.getKey(), obj);
            this.previewText.setStyle(updateStyleByPreferenceKey);
            BrightcoveClosedCaptioningManager.getInstance(getActivity()).setStyle(updateStyleByPreferenceKey);
        }
        return true;
    }

    public void onValueChanged(ListDialogPreference listDialogPreference, int i) {
        BrightcoveCaptionStyle brightcoveCaptionStyle;
        if (listDialogPreference.getKey().equals(CaptionConstants.PREF_PRESET)) {
            brightcoveCaptionStyle = ((PresetPreference) listDialogPreference).getStyleForPreset(i);
            refreshShowingCustom();
        } else {
            brightcoveCaptionStyle = BrightcoveCaptionStyle.updateStyleByPreferenceKey(BrightcoveClosedCaptioningManager.getInstance(getActivity()).getStyle(), listDialogPreference.getKey(), Integer.valueOf(i));
        }
        this.previewText.setStyle(brightcoveCaptionStyle);
        BrightcoveClosedCaptioningManager.getInstance(getActivity()).setStyle(brightcoveCaptionStyle);
    }

    private void setupPreferences() {
        this.captionsEnabled = (SwitchPreference) findPreference(CaptionConstants.PREF_MASTER_SWITCH);
        this.fontSize = (ListPreferenceWithSummary) findPreference(CaptionConstants.PREF_FONT_SIZE);
        int[] intArray = getResources().getIntArray(R.array.captioning_preset_selector_values);
        String[] stringArray = getResources().getStringArray(R.array.captioning_preset_selector_titles);
        this.preset = (PresetPreference) findPreference(CaptionConstants.PREF_PRESET);
        this.preset.setValues(intArray);
        this.preset.setTitles(stringArray);
        this.standard = (PreferenceCategory) findPreference(CaptionConstants.PREF_STANDARD);
        this.custom = (PreferenceCategory) findPreference("custom");
        this.customShowing = true;
        int[] intArray2 = getResources().getIntArray(R.array.captioning_color_selector_values);
        String[] stringArray2 = getResources().getStringArray(R.array.captioning_color_selector_titles);
        this.foregroundColor = (ColorPreference) this.custom.findPreference(CaptionConstants.PREF_FOREGROUND_COLOR);
        this.foregroundColor.setTitles(stringArray2);
        this.foregroundColor.setValues(intArray2);
        int[] intArray3 = getResources().getIntArray(R.array.captioning_opacity_selector_values);
        String[] stringArray3 = getResources().getStringArray(R.array.captioning_opacity_selector_titles);
        this.foregroundOpacity = (ColorPreference) this.custom.findPreference(CaptionConstants.PREF_FOREGROUND_OPACITY);
        this.foregroundOpacity.setTitles(stringArray3);
        this.foregroundOpacity.setValues(intArray3);
        this.edgeColor = (ColorPreference) this.custom.findPreference(CaptionConstants.PREF_EDGE_COLOR);
        this.edgeColor.setTitles(stringArray2);
        this.edgeColor.setValues(intArray2);
        int[] iArr = new int[(intArray2.length + 1)];
        String[] strArr = new String[(stringArray2.length + 1)];
        System.arraycopy(intArray2, 0, iArr, 1, intArray2.length);
        System.arraycopy(stringArray2, 0, strArr, 1, stringArray2.length);
        iArr[0] = 0;
        strArr[0] = getString(R.string.color_none);
        this.backgroundColor = (ColorPreference) this.custom.findPreference(CaptionConstants.PREF_BACKGROUND_COLOR);
        this.backgroundColor.setTitles(strArr);
        this.backgroundColor.setValues(iArr);
        this.backgroundOpacity = (ColorPreference) this.custom.findPreference(CaptionConstants.PREF_BACKGROUND_OPACITY);
        this.backgroundOpacity.setTitles(stringArray3);
        this.backgroundOpacity.setValues(intArray3);
        this.windowColor = (ColorPreference) this.custom.findPreference(CaptionConstants.PREF_WINDOW_COLOR);
        this.windowColor.setTitles(strArr);
        this.windowColor.setValues(iArr);
        this.windowOpacity = (ColorPreference) this.custom.findPreference(CaptionConstants.PREF_WINDOW_OPACITY);
        this.windowOpacity.setTitles(stringArray3);
        this.windowOpacity.setValues(intArray3);
        this.edgeType = (EdgeTypePreference) this.custom.findPreference(CaptionConstants.PREF_EDGE_TYPE);
        this.typeface = (ListPreferenceWithSummary) this.custom.findPreference(CaptionConstants.PREF_TYPEFACE);
    }

    private void initializeListeners() {
        this.preset.setOnValueChangedListener(this);
        this.foregroundColor.setOnValueChangedListener(this);
        this.foregroundOpacity.setOnValueChangedListener(this);
        this.edgeColor.setOnValueChangedListener(this);
        this.backgroundColor.setOnValueChangedListener(this);
        this.backgroundOpacity.setOnValueChangedListener(this);
        this.windowColor.setOnValueChangedListener(this);
        this.windowOpacity.setOnValueChangedListener(this);
        this.edgeType.setOnValueChangedListener(this);
        this.typeface.setOnPreferenceChangeListener(this);
        this.fontSize.setOnPreferenceChangeListener(this);
        this.captionsEnabled.setOnPreferenceChangeListener(this);
    }

    private void refreshShowingCustom() {
        boolean z = ((float) this.preset.getValue()) == -1.0f;
        if (!z && this.customShowing) {
            getPreferenceScreen().removePreference(this.custom);
            this.customShowing = false;
        } else if (z && !this.customShowing) {
            getPreferenceScreen().addPreference(this.custom);
            this.customShowing = true;
        }
    }
}
