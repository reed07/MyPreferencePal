package io.requery.rx;

import io.requery.EntityStore;
import javax.annotation.ParametersAreNonnullByDefault;
import rx.Single;

@ParametersAreNonnullByDefault
public abstract class SingleEntityStore<T> implements EntityStore<T, Single<?>>, RxQueryable<T> {
}
