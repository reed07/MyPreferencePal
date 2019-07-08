package com.uacf.floatingbuttonmenu;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.logging.type.LogSeverity;
import com.uacf.floatingbuttonmenu.animation.FloatingButtonAnimationHandlerBase;
import com.uacf.floatingbuttonmenu.animation.TranslateAlphaFloatingButtonAnimationHandler.Builder;
import java.util.ArrayList;
import java.util.List;

public class FloatingButtonMenu extends RelativeLayout {
    private View backgroundView;
    private OnClickListener floatButtonClickListener = new OnClickListener() {
        public void onClick(View view) {
            FloatingButtonMenu.this.floatingButtonLayout.toggleState(true);
        }
    };
    private ImageView floatingButtonBG;
    private ImageView floatingButtonFG;
    /* access modifiers changed from: private */
    public FloatingButtonLayout floatingButtonLayout;
    private int itemSpacing;
    private List<String> itemTextList;
    private int itemTextViewLayoutRes;
    private List<TextView> itemTextViewList;
    private OnItemClickListener onItemClickListener;
    private OnStateChangeListener onStateChangeListener;
    private boolean showBackground = true;
    private boolean showItemText = true;

    private static class ItemTextTag {
        final View childView;
        final int originalLeftMargin;
        final int originalTopMargin;

        private ItemTextTag(int i, int i2, View view) {
            this.originalLeftMargin = i;
            this.originalTopMargin = i2;
            this.childView = view;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public interface OnStateChangeListener {
        void onMenuStateChanged(int i);
    }

    public FloatingButtonMenu(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        LayoutInflater.from(getContext()).inflate(R.layout.floating_button_menu, this);
        this.itemTextList = new ArrayList();
        this.itemTextViewList = new ArrayList();
        this.floatingButtonLayout = (FloatingButtonLayout) findViewById(R.id.floating_button_layout);
        this.floatingButtonFG = (ImageView) findViewById(R.id.floating_button_fg);
        this.floatingButtonBG = (ImageView) findViewById(R.id.floating_button_bg);
        this.backgroundView = findViewById(R.id.background_view);
        this.floatingButtonLayout.setFloatingButton(this.floatingButtonBG);
        this.floatingButtonBG.setOnClickListener(this.floatButtonClickListener);
        this.backgroundView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        setAnimationHandler(new Builder(this).build());
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        applyAttrs(attributeSet);
    }

    private void applyAttrs(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.FloatingButton, 0, 0);
            float f = obtainStyledAttributes.getFloat(R.styleable.FloatingButton_fromDegrees, 180.0f);
            float f2 = obtainStyledAttributes.getFloat(R.styleable.FloatingButton_toDegrees, 270.0f);
            int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.FloatingButton_rad, LogSeverity.NOTICE_VALUE);
            int dimensionPixelOffset2 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.FloatingButton_button_width, 0);
            int dimensionPixelOffset3 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.FloatingButton_button_height, 0);
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.FloatingButton_fg, 0);
            int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.FloatingButton_bg, 0);
            int color = obtainStyledAttributes.getColor(R.styleable.FloatingButton_background_color, 0);
            boolean z = obtainStyledAttributes.getBoolean(R.styleable.FloatingButton_show_background, true);
            this.itemTextViewLayoutRes = obtainStyledAttributes.getResourceId(R.styleable.FloatingButton_item_text_layout, R.layout.item_text_default_layout);
            this.itemSpacing = obtainStyledAttributes.getResourceId(R.styleable.FloatingButton_child_spacing, getResources().getDimensionPixelSize(R.dimen.default_item_spacing));
            if (obtainStyledAttributes.hasValue(R.styleable.FloatingButton_button_margin)) {
                setFloatingButtonMargin(obtainStyledAttributes.getDimensionPixelOffset(R.styleable.FloatingButton_button_margin, 0));
            } else {
                setFloatingButtonMargin(obtainStyledAttributes.getDimensionPixelOffset(R.styleable.FloatingButton_button_margin_left, 0), obtainStyledAttributes.getDimensionPixelOffset(R.styleable.FloatingButton_button_margin_top, 0), obtainStyledAttributes.getDimensionPixelOffset(R.styleable.FloatingButton_button_margin_right, 0), obtainStyledAttributes.getDimensionPixelOffset(R.styleable.FloatingButton_button_margin_bottom, 0));
            }
            setArc(f, f2).setFloatingButtonSize(dimensionPixelOffset2, dimensionPixelOffset3).setRadius(dimensionPixelOffset).setButtonForeground(resourceId).setButtonBackground(resourceId2).setBackground(color).setShowBackground(z);
            obtainStyledAttributes.recycle();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && isExpanded() && this.floatingButtonLayout.isPointOutsideFloatingButtonLayout((int) motionEvent.getX(), (int) motionEvent.getY())) {
            close();
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i != 4 || !isExpanded()) {
            return super.onKeyPreIme(i, keyEvent);
        }
        if (keyEvent.getAction() == 1) {
            close();
        }
        return true;
    }

    public Rect computeChildFrame(float f, int i, int i2, int i3) {
        return this.floatingButtonLayout.computeChildFrame(f, i, i2, i3);
    }

    public void open() {
        open(true);
    }

    public void open(boolean z) {
        this.floatingButtonLayout.setState(true, z);
    }

    public void close() {
        close(true);
    }

    public void close(boolean z) {
        close(z, -1);
    }

    public void close(boolean z, int i) {
        this.floatingButtonLayout.setState(false, z, i);
    }

    private void setOnClickListenerForChild(View view, final int i) {
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FloatingButtonMenu.this.close(true, i);
            }
        });
    }

    public FloatingButtonMenu addItem(View view, String str) {
        int menuChildCount = getMenuChildCount();
        this.floatingButtonLayout.addView(view);
        setOnClickListenerForChild(view, menuChildCount);
        view.setVisibility(isExpanded() ? 0 : 4);
        this.itemTextList.add(str);
        removeItemText();
        return this;
    }

    private void removeItemText() {
        for (View removeView : this.itemTextViewList) {
            removeView(removeView);
        }
        this.itemTextViewList.clear();
    }

    public void showItemText() {
        if (shouldShowItemText()) {
            if (this.itemTextViewList.isEmpty()) {
                addItemText();
            }
            setItemTextPlacement();
            toggleItemTextVisibility(true);
        }
    }

    public void hideItemText() {
        if (shouldShowItemText()) {
            toggleItemTextVisibility(false);
        }
    }

    private boolean shouldShowItemText() {
        Resources resources = getContext().getResources();
        boolean z = resources.getBoolean(R.bool.isPhone);
        return this.showItemText && (!z || (z && resources.getConfiguration().orientation != 2));
    }

    private void toggleItemTextVisibility(boolean z) {
        for (View view : this.itemTextViewList) {
            view.startAnimation(getAlphaAnimation(z));
            view.setVisibility(z ? 0 : 4);
        }
    }

    private Animation getAlphaAnimation(boolean z) {
        AlphaAnimation alphaAnimation = z ? new AlphaAnimation(BitmapDescriptorFactory.HUE_RED, 1.0f) : new AlphaAnimation(1.0f, BitmapDescriptorFactory.HUE_RED);
        alphaAnimation.setDuration(z ? 500 : 200);
        return alphaAnimation;
    }

    private void addItemText() {
        int menuChildCount = getMenuChildCount();
        LayoutInflater from = LayoutInflater.from(getContext());
        for (int i = 0; i < menuChildCount; i++) {
            View menuChildAt = getMenuChildAt(i);
            TextView textView = (TextView) from.inflate(this.itemTextViewLayoutRes, this, false);
            textView.setText((CharSequence) this.itemTextList.get(i));
            textView.setVisibility(4);
            LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
            textView.setTag(new ItemTextTag(layoutParams.leftMargin, layoutParams.topMargin, menuChildAt));
            addView(textView);
            this.itemTextViewList.add(textView);
        }
    }

    private void setItemTextPlacement() {
        for (final View view : this.itemTextViewList) {
            view.post(new Runnable() {
                public void run() {
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                    ItemTextTag itemTextTag = (ItemTextTag) view.getTag();
                    View view = itemTextTag.childView;
                    layoutParams.leftMargin = ((itemTextTag.originalLeftMargin + view.getLeft()) - view.getMeasuredWidth()) - layoutParams.rightMargin;
                    layoutParams.topMargin = ((itemTextTag.originalTopMargin + view.getTop()) - view.getMeasuredHeight()) - layoutParams.bottomMargin;
                    view.setLayoutParams(layoutParams);
                }
            });
        }
    }

    public FloatingButtonMenu setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
        return this;
    }

    public FloatingButtonMenu setAnimationHandler(FloatingButtonAnimationHandlerBase floatingButtonAnimationHandlerBase) {
        this.floatingButtonLayout.setAnimationHandler(floatingButtonAnimationHandlerBase);
        return this;
    }

    public FloatingButtonMenu setOnStateChangeListener(OnStateChangeListener onStateChangeListener2) {
        this.onStateChangeListener = onStateChangeListener2;
        return this;
    }

    public FloatingButtonMenu setArc(float f, float f2) {
        this.floatingButtonLayout.setArc(f, f2);
        return this;
    }

    private FloatingButtonMenu setFloatingButtonSize(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return this;
        }
        setParams(i, i2, this.floatingButtonFG);
        setParams(i, i2, this.floatingButtonBG);
        return this;
    }

    private void setParams(int i, int i2, View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        view.setLayoutParams(layoutParams);
    }

    public FloatingButtonMenu setFloatingButtonMargin(int i) {
        return setFloatingButtonMargin(i, i, i, i);
    }

    public FloatingButtonMenu setFloatingButtonMargin(int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = (LayoutParams) this.floatingButtonFG.getLayoutParams();
        if (layoutParams.leftMargin == i && layoutParams.topMargin == i2 && layoutParams.rightMargin == i3 && layoutParams.bottomMargin == i4) {
            return this;
        }
        setMargins(this.floatingButtonFG, i, i2, i3, i4);
        setMargins(this.floatingButtonBG, i, i2, i3, i4);
        this.floatingButtonLayout.requestLayout();
        return this;
    }

    private void setMargins(View view, int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutParams.leftMargin = i;
        layoutParams.rightMargin = i3;
        layoutParams.topMargin = i2;
        layoutParams.bottomMargin = i4;
        view.setLayoutParams(layoutParams);
    }

    public FloatingButtonMenu setRadius(int i) {
        this.floatingButtonLayout.setRadius(i);
        return this;
    }

    public FloatingButtonMenu setButtonForeground(int i) {
        this.floatingButtonFG.setImageResource(i);
        return this;
    }

    public FloatingButtonMenu setButtonBackground(int i) {
        this.floatingButtonBG.setImageResource(i);
        return this;
    }

    public FloatingButtonMenu setBackground(int i) {
        this.backgroundView.setBackgroundColor(i);
        return this;
    }

    public FloatingButtonMenu setShowBackground(boolean z) {
        if (this.showBackground == z) {
            return this;
        }
        this.showBackground = z;
        int i = 4;
        if (this.showBackground) {
            View view = this.backgroundView;
            if (isExpanded()) {
                i = 0;
            }
            view.setVisibility(i);
        } else {
            this.backgroundView.setVisibility(4);
        }
        return this;
    }

    public float getToDegrees() {
        return this.floatingButtonLayout.getToDegrees();
    }

    public float getFromDegrees() {
        return this.floatingButtonLayout.getFromDegrees();
    }

    public Point getFloatingButtonTopCornerCoor() {
        return this.floatingButtonLayout.getFloatingButtonTopCornerCoordinates();
    }

    public boolean isExpanded() {
        return this.floatingButtonLayout.isExpanded();
    }

    public int getRadius() {
        return this.floatingButtonLayout.getRadius();
    }

    public int getMenuChildCount() {
        return this.floatingButtonLayout.getChildCount();
    }

    public View getMenuChildAt(int i) {
        return this.floatingButtonLayout.getChildAt(i);
    }

    public OnStateChangeListener getOnStateChangeListener() {
        return this.onStateChangeListener;
    }

    public View getBackgroundView() {
        return this.backgroundView;
    }

    public boolean shouldShowBackground() {
        return this.showBackground;
    }

    public View getFloatingButtonView() {
        return this.floatingButtonFG;
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }
}
