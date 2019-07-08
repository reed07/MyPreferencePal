package io.requery.query;

import io.requery.util.function.Supplier;
import javax.annotation.CheckReturnValue;

public interface Return<R> extends Aliasable<Return<R>>, Supplier<R> {
    @CheckReturnValue
    R get();
}
