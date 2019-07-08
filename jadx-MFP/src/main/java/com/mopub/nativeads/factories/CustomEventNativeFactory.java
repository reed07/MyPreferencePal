package com.mopub.nativeads.factories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.nativeads.CustomEventNative;
import com.mopub.nativeads.MoPubCustomEventNative;
import java.lang.reflect.Constructor;

public class CustomEventNativeFactory {
    protected static CustomEventNativeFactory instance = new CustomEventNativeFactory();

    public static CustomEventNative create(@Nullable String str) throws Exception {
        if (str == null) {
            return new MoPubCustomEventNative();
        }
        return instance.internalCreate(Class.forName(str).asSubclass(CustomEventNative.class));
    }

    @Deprecated
    public static void setInstance(@NonNull CustomEventNativeFactory customEventNativeFactory) {
        Preconditions.checkNotNull(customEventNativeFactory);
        instance = customEventNativeFactory;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public CustomEventNative internalCreate(@NonNull Class<? extends CustomEventNative> cls) throws Exception {
        Preconditions.checkNotNull(cls);
        Constructor declaredConstructor = cls.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        return (CustomEventNative) declaredConstructor.newInstance(new Object[0]);
    }
}
