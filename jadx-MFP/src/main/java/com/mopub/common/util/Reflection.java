package com.mopub.common.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Reflection {

    public static class MethodBuilder {
        @Nullable
        private Class<?> mClass;
        @Nullable
        private final Object mInstance;
        private boolean mIsAccessible;
        private boolean mIsStatic;
        @NonNull
        private final String mMethodName;
        @NonNull
        private List<Class<?>> mParameterClasses = new ArrayList();
        @NonNull
        private List<Object> mParameters = new ArrayList();

        public MethodBuilder(@Nullable Object obj, @NonNull String str) {
            Preconditions.checkNotNull(str);
            this.mInstance = obj;
            this.mMethodName = str;
            this.mClass = obj != null ? obj.getClass() : null;
        }

        @NonNull
        public <T> MethodBuilder addParam(@NonNull Class<T> cls, @Nullable T t) {
            Preconditions.checkNotNull(cls);
            this.mParameterClasses.add(cls);
            this.mParameters.add(t);
            return this;
        }

        @NonNull
        public MethodBuilder addParam(@NonNull String str, @Nullable Object obj) throws ClassNotFoundException {
            Preconditions.checkNotNull(str);
            this.mParameterClasses.add(Class.forName(str));
            this.mParameters.add(obj);
            return this;
        }

        @NonNull
        public MethodBuilder setAccessible() {
            this.mIsAccessible = true;
            return this;
        }

        @NonNull
        public MethodBuilder setStatic(@NonNull Class<?> cls) {
            Preconditions.checkNotNull(cls);
            this.mIsStatic = true;
            this.mClass = cls;
            return this;
        }

        @NonNull
        public MethodBuilder setStatic(@NonNull String str) throws ClassNotFoundException {
            Preconditions.checkNotNull(str);
            this.mIsStatic = true;
            this.mClass = Class.forName(str);
            return this;
        }

        @Nullable
        public Object execute() throws Exception {
            Method declaredMethodWithTraversal = Reflection.getDeclaredMethodWithTraversal(this.mClass, this.mMethodName, (Class[]) this.mParameterClasses.toArray(new Class[this.mParameterClasses.size()]));
            if (this.mIsAccessible) {
                declaredMethodWithTraversal.setAccessible(true);
            }
            Object[] array = this.mParameters.toArray();
            if (this.mIsStatic) {
                return declaredMethodWithTraversal.invoke(null, array);
            }
            return declaredMethodWithTraversal.invoke(this.mInstance, array);
        }
    }

    @Nullable
    public static Method getDeclaredMethodWithTraversal(@Nullable Class<?> cls, @NonNull String str, @NonNull Class<?>... clsArr) throws NoSuchMethodException {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(clsArr);
        while (cls != null) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException unused) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchMethodException();
    }

    public static boolean classFound(@NonNull String str) {
        Preconditions.checkNotNull(str);
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @NonNull
    public static <T> T instantiateClassWithEmptyConstructor(@NonNull String str, @NonNull Class<? extends T> cls) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NullPointerException {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(cls);
        Constructor declaredConstructor = Class.forName(str).asSubclass(cls).getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        return declaredConstructor.newInstance(new Object[0]);
    }

    @NonNull
    public static <T> T instantiateClassWithConstructor(@NonNull String str, @NonNull Class<? extends T> cls, @NonNull Class[] clsArr, @NonNull Object[] objArr) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(clsArr);
        Preconditions.checkNotNull(objArr);
        Constructor declaredConstructor = Class.forName(str).asSubclass(cls).getDeclaredConstructor(clsArr);
        declaredConstructor.setAccessible(true);
        return declaredConstructor.newInstance(objArr);
    }

    public static Field getPrivateField(@NonNull Class cls, @NonNull String str) throws NoSuchFieldException {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField;
    }
}
