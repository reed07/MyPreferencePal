package com.uacf.core.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

public final class ViewUtils {
    public static <T extends View> T findById(View view, int i) {
        if (view != null) {
            return view.findViewById(i);
        }
        return null;
    }

    public static void setVisible(View view, int i, boolean z) {
        setVisible(view, i, z, 8);
    }

    public static void setVisible(View view, int i, boolean z, int i2) {
        setVisible(z, i2, findById(view, i));
    }

    public static void setVisible(View... viewArr) {
        setVisible(true, viewArr);
    }

    public static void setVisible(boolean z, View... viewArr) {
        setVisible(z, 8, viewArr);
    }

    public static void setVisible(boolean z, int i, View... viewArr) {
        if (viewArr != null) {
            for (View view : viewArr) {
                if (view != null) {
                    view.setVisibility(z ? 0 : i);
                }
            }
        }
    }

    public static void setInvisible(View... viewArr) {
        setVisible(false, 4, viewArr);
    }

    public static void setGone(View view) {
        setVisible(false, view);
    }

    public static void unbindDrawables(View view) {
        try {
            if (view.getBackground() != null) {
                view.getBackground().setCallback(null);
            }
            if (view instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    unbindDrawables(((ViewGroup) view).getChildAt(i));
                }
                ((ViewGroup) view).removeAllViews();
            }
            System.gc();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public static void increaseHitRectBy(DeviceInfo deviceInfo, int i, View view) {
        increaseHitRectBy(deviceInfo.toPixels(i), view);
    }

    public static void increaseHitRectBy(DeviceInfo deviceInfo, int i, int i2, int i3, int i4, View view) {
        increaseHitRectBy(deviceInfo.toPixels(i), deviceInfo.toPixels(i2), deviceInfo.toPixels(i3), deviceInfo.toPixels(i4), view);
    }

    public static void increaseHitRectBy(int i, View view) {
        increaseHitRectBy(i, i, i, i, view);
    }

    public static void increaseHitRectBy(int i, int i2, int i3, int i4, View view) {
        View view2 = (View) view.getParent();
        if (view2 != null) {
            final View view3 = view;
            final int i5 = i;
            final int i6 = i2;
            final int i7 = i3;
            final int i8 = i4;
            final View view4 = view2;
            AnonymousClass1 r1 = new Runnable() {
                public void run() {
                    Rect rect = new Rect();
                    view3.getHitRect(rect);
                    rect.top -= i5;
                    rect.left -= i6;
                    rect.bottom += i7;
                    rect.right += i8;
                    view4.setTouchDelegate(new TouchDelegate(rect, view3));
                }
            };
            view2.post(r1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Could not increase the hit area of ");
        sb.append(view);
        sb.append("\nThere was no parent available.");
        Ln.w(sb.toString(), new Object[0]);
    }

    public static void dumpTree(View view) {
        dumpTree(view, "");
    }

    private static void dumpTree(View view, String str) {
        if (view instanceof ViewGroup) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("    ");
            String sb2 = sb.toString();
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                dumpTree(viewGroup.getChildAt(i), sb2);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static void setAlpha(View view, float f) {
        view.setAlpha(f);
    }

    public static boolean isVisible(View view) {
        return view != null && view.getVisibility() == 0;
    }

    public static void scrollOnKeyboardDetection(final View view, final ScrollView scrollView) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (view.getRootView().getHeight() - view.getHeight() > 100) {
                    ScrollView scrollView = scrollView;
                    scrollView.smoothScrollTo(0, scrollView.getMaxScrollAmount());
                }
            }
        });
    }

    public static void enableDisableView(View view, boolean z) {
        view.setEnabled(z);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                enableDisableView(viewGroup.getChildAt(i), z);
            }
        }
    }

    public static <T> List<T> findByType(View view, Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        if (view != null) {
            if (cls.isAssignableFrom(view.getClass())) {
                arrayList.add(view);
            }
            findByType(view, cls, arrayList);
        }
        return arrayList;
    }

    public static <T> T getLayoutParams(View view) {
        if (view != null) {
            return view.getLayoutParams();
        }
        return null;
    }

    private static <T> void findByType(View view, Class<T> cls, List<T> list) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                if (cls.isAssignableFrom(childAt.getClass())) {
                    list.add(childAt);
                }
                if (childAt instanceof ViewGroup) {
                    findByType(childAt, cls, list);
                }
            }
        }
    }

    public static void blockDescendantFocus(ViewGroup viewGroup) {
        if (viewGroup != null) {
            viewGroup.setDescendantFocusability(393216);
        }
    }

    public static void setPickerCommonProperties(NumberPicker numberPicker) {
        if (numberPicker != null) {
            blockDescendantFocus(numberPicker);
            numberPicker.setWrapSelectorWheel(false);
        }
    }

    public static void setOnClickListener(View view, OnClickListener onClickListener) {
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    public static boolean removeViewFromParent(View view) {
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) view.getParent()).removeView(view);
                return true;
            }
        }
        return false;
    }

    public static void updateHeightBasedOnScreenHeight(WindowManager windowManager, View view, int i) {
        updateHeightBasedOnScreenHeight(windowManager, view, i, -1, -1);
    }

    public static void updateHeightBasedOnScreenHeight(WindowManager windowManager, View view, int i, int i2, int i3) {
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i4 = displayMetrics.heightPixels / (100 / i);
        if (i2 != -1) {
            i4 = Math.max(i2, i4);
        }
        if (i3 != -1) {
            i4 = Math.min(i4, i3);
        }
        LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i4;
        view.setLayoutParams(layoutParams);
    }

    public static void scrollToView(HorizontalScrollView horizontalScrollView, View view) {
        if (horizontalScrollView != null && view != null) {
            Rect rect = new Rect();
            horizontalScrollView.getLocalVisibleRect(rect);
            if (rect.left > view.getLeft() || rect.right < view.getRight()) {
                horizontalScrollView.smoothScrollTo(view.getLeft(), 0);
            }
        }
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static void selectRadioButton(View view, int i) {
        for (RadioButton radioButton : findByType(view, RadioButton.class)) {
            radioButton.setChecked(radioButton.getId() == i);
        }
    }

    public static OnTouchListener getOnTouchListenerToExecuteClick(final Function0 function0) {
        return new OnTouchListener() {
            private float startX;
            private float startY;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        this.startX = motionEvent.getX();
                        this.startY = motionEvent.getY();
                        break;
                    case 1:
                        if (ViewUtils.clickDetected(this.startX, motionEvent.getX(), this.startY, motionEvent.getY())) {
                            function0.execute();
                            break;
                        }
                        break;
                }
                ViewParent parent = view.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        };
    }

    /* access modifiers changed from: private */
    public static boolean clickDetected(float f, float f2, float f3, float f4) {
        return Math.abs(f - f2) <= 20.0f && Math.abs(f3 - f4) <= 20.0f;
    }

    public static void setStatusBarVisible(Window window, boolean z) {
        window.getDecorView().setSystemUiVisibility(z ? 0 : 4);
    }
}
