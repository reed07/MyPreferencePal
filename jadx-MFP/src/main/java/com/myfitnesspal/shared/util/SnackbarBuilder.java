package com.myfitnesspal.shared.util;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;

public class SnackbarBuilder {
    private static final long SNACKBAR_DEFAULT_DELAY = 500;
    private String actionMessage;
    private int actionTextColorResId;
    private int backgroundColorResId;
    private Callback callback;
    private final Context context;
    private int duration;
    private Handler handler;
    private String message;
    private OnClickListener onActionMessageClickListener;
    private Runnable showSnackbarRunnable = new Runnable() {
        public void run() {
            SnackbarBuilder.this.show();
        }
    };
    private final View view;

    public SnackbarBuilder(View view2) {
        this.view = view2;
        this.context = view2.getContext();
    }

    public SnackbarBuilder setMessage(String str) {
        this.message = str;
        return this;
    }

    public SnackbarBuilder setMessage(@StringRes int i) {
        setMessage(getString(i));
        return this;
    }

    public SnackbarBuilder setAction(String str, OnClickListener onClickListener) {
        this.actionMessage = str;
        this.onActionMessageClickListener = onClickListener;
        return this;
    }

    public SnackbarBuilder setAction(@StringRes int i, OnClickListener onClickListener) {
        setAction(getString(i), onClickListener);
        return this;
    }

    public SnackbarBuilder setDuration(int i) {
        this.duration = i;
        return this;
    }

    public SnackbarBuilder setActionTextColorResId(int i) {
        this.actionTextColorResId = i;
        return this;
    }

    public SnackbarBuilder setBackgroundColorResId(int i) {
        this.backgroundColorResId = i;
        return this;
    }

    public SnackbarBuilder setCallback(Callback callback2) {
        this.callback = callback2;
        return this;
    }

    public Snackbar build() {
        Snackbar action = Snackbar.make(this.view, (CharSequence) this.message, this.duration).setAction((CharSequence) this.actionMessage, this.onActionMessageClickListener);
        int i = this.actionTextColorResId;
        if (i != 0) {
            action.setActionTextColor(ContextCompat.getColor(this.context, i));
        }
        if (this.backgroundColorResId != 0) {
            action.getView().setBackgroundColor(ContextCompat.getColor(this.context, this.backgroundColorResId));
        }
        Callback callback2 = this.callback;
        if (callback2 != null) {
            action.setCallback(callback2);
        }
        return action;
    }

    public void show() {
        build().show();
    }

    public void showWithDelay() {
        showWithDelay(SNACKBAR_DEFAULT_DELAY);
    }

    public void showWithDelay(long j) {
        if (this.handler == null) {
            this.handler = new Handler();
        }
        this.handler.removeCallbacks(this.showSnackbarRunnable);
        this.handler.postDelayed(this.showSnackbarRunnable, j);
    }

    private String getString(int i) {
        return this.context.getString(i);
    }
}
