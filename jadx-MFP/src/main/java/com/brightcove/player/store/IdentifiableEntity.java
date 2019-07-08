package com.brightcove.player.store;

import android.support.annotation.NonNull;
import com.brightcove.player.store.IdentifiableEntity;
import io.requery.Persistable;
import io.requery.query.Expression;
import io.requery.query.LogicalCondition;

public interface IdentifiableEntity<E extends IdentifiableEntity, T> extends Persistable {
    LogicalCondition<? extends Expression<T>, ?> getIdentityCondition();

    LogicalCondition<? extends Expression<T>, ?> getIdentityCondition(T t);

    @NonNull
    T getKey();
}
