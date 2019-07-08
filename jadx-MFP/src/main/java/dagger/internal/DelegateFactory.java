package dagger.internal;

import javax.inject.Provider;

public final class DelegateFactory<T> implements Factory<T> {
    private Provider<T> delegate;

    public T get() {
        Provider<T> provider = this.delegate;
        if (provider != null) {
            return provider.get();
        }
        throw new IllegalStateException();
    }

    public void setDelegatedProvider(Provider<T> provider) {
        if (provider == null) {
            throw new IllegalArgumentException();
        } else if (this.delegate == null) {
            this.delegate = provider;
        } else {
            throw new IllegalStateException();
        }
    }
}
