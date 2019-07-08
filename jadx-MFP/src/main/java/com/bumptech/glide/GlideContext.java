package com.bumptech.glide;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.ViewTarget;
import java.util.Map;
import java.util.Map.Entry;

public class GlideContext extends ContextWrapper {
    @VisibleForTesting
    static final TransitionOptions<?, ?> DEFAULT_TRANSITION_OPTIONS = new GenericTransitionOptions();
    private final ArrayPool arrayPool;
    private final RequestOptions defaultRequestOptions;
    private final Map<Class<?>, TransitionOptions<?, ?>> defaultTransitionOptions;
    private final Engine engine;
    private final ImageViewTargetFactory imageViewTargetFactory;
    private final int logLevel;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Registry registry;

    public GlideContext(@NonNull Context context, @NonNull ArrayPool arrayPool2, @NonNull Registry registry2, @NonNull ImageViewTargetFactory imageViewTargetFactory2, @NonNull RequestOptions requestOptions, @NonNull Map<Class<?>, TransitionOptions<?, ?>> map, @NonNull Engine engine2, int i) {
        super(context.getApplicationContext());
        this.arrayPool = arrayPool2;
        this.registry = registry2;
        this.imageViewTargetFactory = imageViewTargetFactory2;
        this.defaultRequestOptions = requestOptions;
        this.defaultTransitionOptions = map;
        this.engine = engine2;
        this.logLevel = i;
    }

    public RequestOptions getDefaultRequestOptions() {
        return this.defaultRequestOptions;
    }

    @NonNull
    public <T> TransitionOptions<?, T> getDefaultTransitionOptions(@NonNull Class<T> cls) {
        TransitionOptions<?, T> transitionOptions = (TransitionOptions) this.defaultTransitionOptions.get(cls);
        if (transitionOptions == null) {
            for (Entry entry : this.defaultTransitionOptions.entrySet()) {
                if (((Class) entry.getKey()).isAssignableFrom(cls)) {
                    transitionOptions = (TransitionOptions) entry.getValue();
                }
            }
        }
        return transitionOptions == null ? DEFAULT_TRANSITION_OPTIONS : transitionOptions;
    }

    @NonNull
    public <X> ViewTarget<ImageView, X> buildImageViewTarget(@NonNull ImageView imageView, @NonNull Class<X> cls) {
        return this.imageViewTargetFactory.buildTarget(imageView, cls);
    }

    @NonNull
    public Handler getMainHandler() {
        return this.mainHandler;
    }

    @NonNull
    public Engine getEngine() {
        return this.engine;
    }

    @NonNull
    public Registry getRegistry() {
        return this.registry;
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    @NonNull
    public ArrayPool getArrayPool() {
        return this.arrayPool;
    }
}
