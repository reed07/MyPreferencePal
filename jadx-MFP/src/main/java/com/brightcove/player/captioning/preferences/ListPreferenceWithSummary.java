package com.brightcove.player.captioning.preferences;

import android.content.Context;
import android.preference.ListPreference;
import android.util.AttributeSet;

public class ListPreferenceWithSummary extends ListPreference {
    public ListPreferenceWithSummary(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ListPreferenceWithSummary(Context context) {
        super(context);
    }

    public void setValue(String str) {
        super.setValue(str);
        setSummary(str);
    }

    public void setSummary(CharSequence charSequence) {
        super.setSummary(getEntry());
    }
}
