package android.support.v14.preference;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.preference.internal.AbstractMultiSelectListPreference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MultiSelectListPreferenceDialogFragment extends PreferenceDialogFragment {
    CharSequence[] mEntries;
    CharSequence[] mEntryValues;
    Set<String> mNewValues = new HashSet();
    boolean mPreferenceChanged;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            AbstractMultiSelectListPreference listPreference = getListPreference();
            if (listPreference.getEntries() == null || listPreference.getEntryValues() == null) {
                throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
            }
            this.mNewValues.clear();
            this.mNewValues.addAll(listPreference.getValues());
            this.mPreferenceChanged = false;
            this.mEntries = listPreference.getEntries();
            this.mEntryValues = listPreference.getEntryValues();
            return;
        }
        this.mNewValues.clear();
        this.mNewValues.addAll(bundle.getStringArrayList("MultiSelectListPreferenceDialogFragment.values"));
        this.mPreferenceChanged = bundle.getBoolean("MultiSelectListPreferenceDialogFragment.changed", false);
        this.mEntries = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragment.entries");
        this.mEntryValues = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragment.entryValues");
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("MultiSelectListPreferenceDialogFragment.values", new ArrayList(this.mNewValues));
        bundle.putBoolean("MultiSelectListPreferenceDialogFragment.changed", this.mPreferenceChanged);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragment.entries", this.mEntries);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragment.entryValues", this.mEntryValues);
    }

    private AbstractMultiSelectListPreference getListPreference() {
        return (AbstractMultiSelectListPreference) getPreference();
    }

    /* access modifiers changed from: protected */
    public void onPrepareDialogBuilder(Builder builder) {
        super.onPrepareDialogBuilder(builder);
        int length = this.mEntryValues.length;
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            zArr[i] = this.mNewValues.contains(this.mEntryValues[i].toString());
        }
        builder.setMultiChoiceItems(this.mEntries, zArr, new OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                if (z) {
                    MultiSelectListPreferenceDialogFragment multiSelectListPreferenceDialogFragment = MultiSelectListPreferenceDialogFragment.this;
                    multiSelectListPreferenceDialogFragment.mPreferenceChanged = MultiSelectListPreferenceDialogFragment.this.mNewValues.add(MultiSelectListPreferenceDialogFragment.this.mEntryValues[i].toString()) | multiSelectListPreferenceDialogFragment.mPreferenceChanged;
                    return;
                }
                MultiSelectListPreferenceDialogFragment multiSelectListPreferenceDialogFragment2 = MultiSelectListPreferenceDialogFragment.this;
                multiSelectListPreferenceDialogFragment2.mPreferenceChanged = MultiSelectListPreferenceDialogFragment.this.mNewValues.remove(MultiSelectListPreferenceDialogFragment.this.mEntryValues[i].toString()) | multiSelectListPreferenceDialogFragment2.mPreferenceChanged;
            }
        });
    }

    public void onDialogClosed(boolean z) {
        AbstractMultiSelectListPreference listPreference = getListPreference();
        if (z && this.mPreferenceChanged) {
            Set<String> set = this.mNewValues;
            if (listPreference.callChangeListener(set)) {
                listPreference.setValues(set);
            }
        }
        this.mPreferenceChanged = false;
    }
}
