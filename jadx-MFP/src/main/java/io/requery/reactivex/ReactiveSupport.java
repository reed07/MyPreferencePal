package io.requery.reactivex;

import io.requery.BlockingEntityStore;

public final class ReactiveSupport {
    private static final TransactionListenerSupplier typeChanges = new TransactionListenerSupplier();

    private ReactiveSupport() {
    }

    public static <S> ReactiveEntityStore<S> toReactiveStore(BlockingEntityStore<S> blockingEntityStore) {
        return new WrappedEntityStore(blockingEntityStore);
    }
}
