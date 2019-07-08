package dagger.internal;

import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import dagger.Lazy;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Provider;

public final class MapProviderFactory<K, V> implements Lazy<Map<K, Provider<V>>>, Factory<Map<K, Provider<V>>> {
    private final Map<K, Provider<V>> contributingMap;

    public static final class Builder<K, V> {
        private final LinkedHashMap<K, Provider<V>> map;

        private Builder(int i) {
            this.map = DaggerCollections.newLinkedHashMapWithExpectedSize(i);
        }

        public Builder<K, V> put(K k, Provider<V> provider) {
            this.map.put(Preconditions.checkNotNull(k, IpcUtil.KEY_CODE), Preconditions.checkNotNull(provider, "provider"));
            return this;
        }

        public MapProviderFactory<K, V> build() {
            return new MapProviderFactory<>(this.map);
        }
    }

    public static <K, V> Builder<K, V> builder(int i) {
        return new Builder<>(i);
    }

    private MapProviderFactory(Map<K, Provider<V>> map) {
        this.contributingMap = Collections.unmodifiableMap(map);
    }

    public Map<K, Provider<V>> get() {
        return this.contributingMap;
    }
}
