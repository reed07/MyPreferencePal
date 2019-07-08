package io.requery.android;

import android.os.Handler;
import android.widget.BaseAdapter;
import io.requery.meta.Type;
import io.requery.proxy.EntityProxy;
import io.requery.util.function.Function;
import java.io.Closeable;

public abstract class QueryAdapter<E> extends BaseAdapter implements Closeable {
    private final Handler handler;
    private final Function<E, EntityProxy<E>> proxyProvider;

    protected QueryAdapter() {
        this(null);
    }

    protected QueryAdapter(Type<E> type) {
        Function<E, EntityProxy<E>> function;
        if (type == null) {
            function = null;
        } else {
            function = type.getProxyProvider();
        }
        this.proxyProvider = function;
        this.handler = new Handler();
    }
}
