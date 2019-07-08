package dagger;

import lanchon.dexpatcher.annotation.DexIgnore;

@DexIgnore
public interface Lazy<T> {
    T get();
}
