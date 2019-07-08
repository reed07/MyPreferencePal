package dagger.android;

import dagger.BindsInstance;

public interface AndroidInjector<T> {

    public static abstract class Builder<T> implements Factory<T> {
        public abstract AndroidInjector<T> build();

        @BindsInstance
        public abstract void seedInstance(T t);

        public final AndroidInjector<T> create(T t) {
            seedInstance(t);
            return build();
        }
    }

    public interface Factory<T> {
        AndroidInjector<T> create(T t);
    }

    void inject(T t);
}
