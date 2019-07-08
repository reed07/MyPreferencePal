package com.brightcove.player.mediacontroller.buttons;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View.OnClickListener;

public class ButtonState {
    private String contentDescription;
    private String eventType;
    private OnClickListener handler;
    private Drawable image;
    private CharSequence text;

    public ButtonState(Context context, int i, int i2, Drawable drawable, String str) {
        this(context, i, i2, drawable, str, null);
    }

    public ButtonState(Context context, int i, int i2, Drawable drawable, OnClickListener onClickListener) {
        this(context, i, i2, drawable, null, onClickListener);
    }

    public ButtonState(Context context, int i, int i2, Drawable drawable, String str, OnClickListener onClickListener) {
        this.text = context.getResources().getString(i);
        this.contentDescription = context.getResources().getString(i2);
        this.image = drawable;
        this.eventType = str;
        this.handler = onClickListener;
    }

    public Drawable getImage() {
        return this.image;
    }

    public CharSequence getText() {
        return this.text;
    }

    public String getContentDescription() {
        return this.contentDescription;
    }

    public String getEventType() {
        return this.eventType;
    }

    public OnClickListener getHandler() {
        return this.handler;
    }
}
