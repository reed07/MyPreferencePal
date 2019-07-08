package io.requery.async;

import io.requery.BlockingEntityStore;
import io.requery.meta.QueryAttribute;
import io.requery.query.Deletion;
import io.requery.query.Expression;
import io.requery.query.Result;
import io.requery.query.Scalar;
import io.requery.query.Selection;
import io.requery.query.Tuple;
import io.requery.query.Update;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class CompletableEntityStore<T> implements CompletionStageEntityStore<T> {
    private final boolean createdExecutor;
    private final BlockingEntityStore<T> delegate;
    private final Executor executor;

    public void close() {
        try {
            if (this.createdExecutor) {
                ((ExecutorService) this.executor).shutdown();
            }
        } finally {
            this.delegate.close();
        }
    }

    public Selection<? extends Result<Tuple>> select(Expression<?>... expressionArr) {
        return this.delegate.select(expressionArr);
    }

    public <E extends T> Selection<? extends Result<E>> select(Class<E> cls, QueryAttribute<?, ?>... queryAttributeArr) {
        return this.delegate.select(cls, queryAttributeArr);
    }

    public <E extends T> Update<? extends Scalar<Integer>> update(Class<E> cls) {
        return this.delegate.update(cls);
    }

    public <E extends T> Deletion<? extends Scalar<Integer>> delete(Class<E> cls) {
        return this.delegate.delete(cls);
    }

    public <E extends T> Selection<? extends Scalar<Integer>> count(Class<E> cls) {
        return this.delegate.count(cls);
    }

    public BlockingEntityStore<T> toBlocking() {
        return this.delegate;
    }
}
