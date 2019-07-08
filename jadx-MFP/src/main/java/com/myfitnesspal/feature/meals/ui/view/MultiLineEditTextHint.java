package com.myfitnesspal.feature.meals.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;

public class MultiLineEditTextHint extends FrameLayout {
    private EditText hintEdit;
    private TextWatcher lineSpacingTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            float lineSpacingExtra = MultiLineEditTextHint.this.realEdit.getLineSpacingExtra();
            float lineSpacingMultiplier = MultiLineEditTextHint.this.realEdit.getLineSpacingMultiplier();
            MultiLineEditTextHint.this.realEdit.setLineSpacing(BitmapDescriptorFactory.HUE_RED, 1.0f);
            MultiLineEditTextHint.this.realEdit.setLineSpacing(lineSpacingExtra, lineSpacingMultiplier);
        }
    };
    private Drawable originalBackground;
    /* access modifiers changed from: private */
    public EditText realEdit;
    private TextWatcher textWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            MultiLineEditTextHint.this.updateStyle();
        }
    };

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        private final String editTextContent;

        private SavedState(Parcelable parcelable, String str) {
            super(parcelable);
            this.editTextContent = str;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.editTextContent = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.editTextContent);
        }

        public String getEditTextContent() {
            return this.editTextContent;
        }
    }

    public MultiLineEditTextHint(@NonNull Context context) {
        super(context);
        init(null);
    }

    public MultiLineEditTextHint(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public MultiLineEditTextHint(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    public EditText getEditText() {
        return this.realEdit;
    }

    public Editable getText() {
        return this.realEdit.getText();
    }

    public void setText(CharSequence charSequence) {
        this.realEdit.setText(charSequence);
    }

    public void setHint(String str) {
        this.hintEdit.setHint(str);
    }

    public void setSelection(int i) {
        this.realEdit.setSelection(i);
    }

    private void init(AttributeSet attributeSet) {
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.gravity = 48;
        this.hintEdit = new EditText(getContext());
        this.hintEdit.setLayoutParams(layoutParams);
        this.hintEdit.setHintTextColor(ContextCompat.getColor(getContext(), R.color.black_hint_text));
        this.hintEdit.setTextSize(0, getResources().getDimension(R.dimen.meal_browse_notes_text_size));
        setFocusable(false);
        this.realEdit = new EditText(getContext());
        this.realEdit.setLayoutParams(new LayoutParams(layoutParams));
        this.realEdit.addTextChangedListener(this.textWatcher);
        this.realEdit.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        this.realEdit.setTextSize(0, getResources().getDimension(R.dimen.meal_browse_notes_text_size));
        this.originalBackground = this.realEdit.getBackground();
        setPropertiesFromAttributes(attributeSet);
        setClickable(true);
        setFocusable(false);
        addView(this.hintEdit);
        addView(this.realEdit);
        this.hintEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    MultiLineEditTextHint.this.realEdit.requestFocus();
                }
            }
        });
        this.hintEdit.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent == null || motionEvent.getAction() != 0) {
                    return false;
                }
                MultiLineEditTextHint.this.realEdit.requestFocus();
                return true;
            }
        });
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MultiLineEditTextHint.this.realEdit.requestFocus();
            }
        });
        updateStyle();
    }

    private void setPropertiesFromAttributes(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.MultiLineEditTextHint, 0, 0);
        try {
            this.hintEdit.setHint(obtainStyledAttributes.getString(0));
            float f = obtainStyledAttributes.getFloat(1, 1.0f);
            this.hintEdit.setLineSpacing(BitmapDescriptorFactory.HUE_RED, f);
            this.realEdit.setLineSpacing(BitmapDescriptorFactory.HUE_RED, f);
            this.realEdit.addTextChangedListener(this.lineSpacingTextWatcher);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: private */
    public void updateStyle() {
        if (Strings.isEmpty(this.realEdit.getText().toString())) {
            this.realEdit.setBackground(null);
            ViewUtils.setVisible(this.hintEdit);
            return;
        }
        this.realEdit.setBackground(this.originalBackground);
        ViewUtils.setGone(this.hintEdit);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.realEdit.getText().toString());
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.realEdit.setText(savedState.getEditTextContent());
    }
}
