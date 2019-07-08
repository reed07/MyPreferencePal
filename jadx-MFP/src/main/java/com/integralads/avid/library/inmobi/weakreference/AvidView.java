package com.integralads.avid.library.inmobi.weakreference;

import android.view.View;

public class AvidView<T extends View> extends ObjectWrapper<T> {
    public AvidView(T t) {
        super(t);
    }
}
