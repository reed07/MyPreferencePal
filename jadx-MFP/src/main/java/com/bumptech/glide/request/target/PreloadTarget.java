package com.bumptech.glide.request.target;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.transition.Transition;

public final class PreloadTarget<Z> extends SimpleTarget<Z> {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper(), new Callback() {
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((PreloadTarget) message.obj).clear();
            return true;
        }
    });
    private final RequestManager requestManager;

    public void onResourceReady(@NonNull Z z, @Nullable Transition<? super Z> transition) {
        HANDLER.obtainMessage(1, this).sendToTarget();
    }

    /* access modifiers changed from: 0000 */
    public void clear() {
        this.requestManager.clear(this);
    }
}
