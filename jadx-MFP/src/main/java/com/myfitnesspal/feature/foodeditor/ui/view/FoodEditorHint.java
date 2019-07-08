package com.myfitnesspal.feature.foodeditor.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;

public class FoodEditorHint extends FrameLayout {
    private static final String EXTRA_DISPLAYED = "extra_displayed";
    private static final String EXTRA_PARENT_STATE = "extra_parent_state";
    /* access modifiers changed from: private */
    public Animation active;
    @BindView(2131362636)
    TextView button;
    @BindView(2131362637)
    View close;
    @BindView(2131362638)
    View container;
    private boolean displayed;
    /* access modifiers changed from: private */
    public OnButtonPressedListener listener;
    @BindView(2131362639)
    TextView message;
    @BindView(2131362641)
    TextView title;
    /* access modifiers changed from: private */
    public Type type;
    /* access modifiers changed from: private */
    public boolean visible = false;

    public interface OnButtonPressedListener {
        void onButtonPressed(FoodEditorHint foodEditorHint, Type type);

        void onClosePressed(FoodEditorHint foodEditorHint);
    }

    public enum Type {
        MealNotes(R.string.meal_sharing_editor_notes_tip_title, R.string.meal_sharing_editor_notes_tip_message, R.string.meal_sharing_editor_notes_tip_button_add_now),
        BrowseNavigation(R.string.meal_sharing_browse_navigation_tip_title, R.string.meal_sharing_browse_navigation_tip_message, R.string.ok);
        
        /* access modifiers changed from: private */
        public final int button;
        /* access modifiers changed from: private */
        public final int message;
        /* access modifiers changed from: private */
        public final int title;

        private Type(int i, int i2, int i3) {
            this.title = i;
            this.message = i2;
            this.button = i3;
        }
    }

    public FoodEditorHint(@NonNull Context context) {
        super(context);
        init();
    }

    public FoodEditorHint(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public FoodEditorHint(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_PARENT_STATE, super.onSaveInstanceState());
        bundle.putBoolean(EXTRA_DISPLAYED, this.displayed);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable(EXTRA_PARENT_STATE));
            this.displayed = bundle.getBoolean(EXTRA_DISPLAYED, false);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void setOnButtonPressedListener(OnButtonPressedListener onButtonPressedListener) {
        this.listener = onButtonPressedListener;
    }

    public boolean visible() {
        return this.visible && getVisibility() == 0;
    }

    public boolean alreadyDisplayed() {
        return this.displayed;
    }

    public void animateIn(Type type2) {
        this.type = type2;
        this.title.setText(type2.title);
        this.message.setText(type2.message);
        this.button.setText(type2.button);
        startAnimation(R.anim.hint_pane_slide_in);
        this.displayed = true;
        this.visible = true;
    }

    public void animateOut() {
        startAnimation(R.anim.hint_pane_slide_out);
        this.visible = false;
    }

    private void startAnimation(int i) {
        cancelAnimation();
        setVisibility(0);
        this.active = AnimationUtils.loadAnimation(getContext(), i);
        this.active.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (animation == FoodEditorHint.this.active) {
                    FoodEditorHint.this.active = null;
                }
                if (!FoodEditorHint.this.visible) {
                    FoodEditorHint.this.setVisibility(8);
                }
            }
        });
        this.container.startAnimation(this.active);
    }

    private void cancelAnimation() {
        Animation animation = this.active;
        if (animation != null) {
            animation.cancel();
            this.active = null;
        }
    }

    private void init() {
        ViewCompat.setElevation(this, getResources().getDimension(R.dimen.default_material_elevation));
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.food_editor_hint, this, false);
        inflate.setLayoutParams(new LayoutParams(-1, -1));
        addView(inflate);
        ButterKnife.bind((View) this);
        this.button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (FoodEditorHint.this.listener != null) {
                    OnButtonPressedListener access$500 = FoodEditorHint.this.listener;
                    FoodEditorHint foodEditorHint = FoodEditorHint.this;
                    access$500.onButtonPressed(foodEditorHint, foodEditorHint.type);
                }
                FoodEditorHint.this.animateOut();
            }
        });
        this.close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (FoodEditorHint.this.listener != null) {
                    FoodEditorHint.this.listener.onClosePressed(FoodEditorHint.this);
                }
                FoodEditorHint.this.animateOut();
            }
        });
        this.container.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FoodEditorHint.this.animateOut();
            }
        });
    }
}
