package com.myfitnesspal.shared.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;

public class CustomToggle extends LinearLayout {
    private boolean isLeftSelected;
    @BindView(2131362903)
    Button leftToggle;
    private OnToggleStateChangeListener onToggleStateChangeListener;
    @BindView(2131363492)
    Button rightToggle;

    public interface OnToggleStateChangeListener {
        void onToggleStateChanged(boolean z);
    }

    public CustomToggle(Context context) {
        super(context);
        init(context);
    }

    public CustomToggle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    @TargetApi(21)
    public CustomToggle(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    public void setOnToggleStateChangeListener(OnToggleStateChangeListener onToggleStateChangeListener2) {
        this.onToggleStateChangeListener = onToggleStateChangeListener2;
    }

    public void setLeftToggleText(@StringRes int i) {
        this.leftToggle.setText(i);
    }

    public void setRightToggleText(@StringRes int i) {
        this.rightToggle.setText(i);
    }

    public boolean isLeftSelected() {
        return this.isLeftSelected;
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_toggle, this, true);
        ButterKnife.bind((View) this);
        this.isLeftSelected = true;
        toggleState(true);
        setListeners();
    }

    private void setListeners() {
        this.leftToggle.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                CustomToggle.this.toggleState(true);
            }
        });
        this.rightToggle.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                CustomToggle.this.toggleState(false);
            }
        });
    }

    public void toggleState(boolean z) {
        this.isLeftSelected = z;
        this.leftToggle.setSelected(z);
        this.rightToggle.setSelected(!z);
        OnToggleStateChangeListener onToggleStateChangeListener2 = this.onToggleStateChangeListener;
        if (onToggleStateChangeListener2 != null) {
            onToggleStateChangeListener2.onToggleStateChanged(z);
        }
    }
}
