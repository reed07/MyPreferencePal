package com.brightcove.player.captioning.preferences;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.preference.DialogPreference;
import android.preference.Preference.BaseSavedState;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

public abstract class ListDialogPreference extends DialogPreference {
    private CharSequence[] entryTitles;
    /* access modifiers changed from: private */
    public int[] entryValues;
    /* access modifiers changed from: private */
    public int listItemLayout;
    private OnValueChangedListener mOnValueChangedListener;
    private int value;
    private int valueIndex;
    private boolean valueSet;

    private class ListPreferenceAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public boolean hasStableIds() {
            return true;
        }

        private ListPreferenceAdapter() {
        }

        public int getCount() {
            return ListDialogPreference.this.entryValues.length;
        }

        public Integer getItem(int i) {
            return Integer.valueOf(ListDialogPreference.this.entryValues[i]);
        }

        public long getItemId(int i) {
            return (long) ListDialogPreference.this.entryValues[i];
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                if (this.mInflater == null) {
                    this.mInflater = LayoutInflater.from(viewGroup.getContext());
                }
                view = this.mInflater.inflate(ListDialogPreference.this.listItemLayout, viewGroup, false);
            }
            ListDialogPreference.this.onBindListItem(view, i);
            return view;
        }
    }

    public interface OnValueChangedListener {
        void onValueChanged(ListDialogPreference listDialogPreference, int i);
    }

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public int value;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.value = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.value);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void onBindListItem(View view, int i);

    public ListDialogPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnValueChangedListener(OnValueChangedListener onValueChangedListener) {
        this.mOnValueChangedListener = onValueChangedListener;
    }

    public void setListItemLayoutResource(int i) {
        this.listItemLayout = i;
    }

    public void setValues(int[] iArr) {
        this.entryValues = iArr;
        if (this.valueSet && this.valueIndex == -1) {
            this.valueIndex = getIndexForValue(this.value);
        }
    }

    public void setTitles(CharSequence[] charSequenceArr) {
        this.entryTitles = charSequenceArr;
    }

    /* access modifiers changed from: protected */
    public CharSequence getTitleAt(int i) {
        CharSequence[] charSequenceArr = this.entryTitles;
        if (charSequenceArr == null || charSequenceArr.length <= i) {
            return null;
        }
        return charSequenceArr[i];
    }

    /* access modifiers changed from: protected */
    public int getValueAt(int i) {
        return this.entryValues[i];
    }

    public CharSequence getSummary() {
        int i = this.valueIndex;
        if (i >= 0) {
            return getTitleAt(i);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @TargetApi(16)
    public void onPrepareDialogBuilder(Builder builder) {
        super.onPrepareDialogBuilder(builder);
        Context context = getContext();
        View inflate = LayoutInflater.from(context).inflate(getDialogLayoutResource(), null);
        AbsListView absListView = (AbsListView) inflate.findViewById(16908298);
        absListView.setAdapter(new ListPreferenceAdapter());
        absListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = (int) j;
                if (ListDialogPreference.this.callChangeListener(Integer.valueOf(i2))) {
                    ListDialogPreference.this.setValue(i2);
                }
                Dialog dialog = ListDialogPreference.this.getDialog();
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        int indexForValue = getIndexForValue(this.value);
        if (indexForValue != -1) {
            absListView.setSelection(indexForValue);
        }
        builder.setView(inflate);
        builder.setPositiveButton(null, null);
    }

    /* access modifiers changed from: protected */
    public int getIndexForValue(int i) {
        int[] iArr = this.entryValues;
        if (iArr != null) {
            int length = iArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (iArr[i2] == i) {
                    return i2;
                }
            }
        }
        return -1;
    }

    public void setValue(int i) {
        boolean z = this.value != i;
        if (z || !this.valueSet) {
            this.value = i;
            this.valueIndex = getIndexForValue(i);
            this.valueSet = true;
            persistInt(i);
            if (z) {
                notifyDependencyChange(shouldDisableDependents());
                notifyChanged();
            }
            OnValueChangedListener onValueChangedListener = this.mOnValueChangedListener;
            if (onValueChangedListener != null) {
                onValueChangedListener.onValueChanged(this, i);
            }
        }
    }

    public int getValue() {
        return this.value;
    }

    /* access modifiers changed from: protected */
    public Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, 0));
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(boolean z, Object obj) {
        setValue(z ? getPersistedInt(this.value) : ((Integer) obj).intValue());
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (isPersistent()) {
            return onSaveInstanceState;
        }
        SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.value = getValue();
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setValue(savedState.value);
    }
}
