package io.requery.android;

import android.os.Handler;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import io.requery.meta.Type;
import io.requery.proxy.EntityProxy;
import io.requery.util.function.Function;
import java.io.Closeable;

public abstract class QueryRecyclerAdapter<E, VH extends ViewHolder> extends Adapter<VH> implements Closeable {
    private final Handler handler;
    private final Function<E, EntityProxy<E>> proxyProvider;

    protected QueryRecyclerAdapter() {
        this(null);
    }

    protected QueryRecyclerAdapter(Type<E> type) {
        Function<E, EntityProxy<E>> function;
        setHasStableIds(true);
        if (type == null) {
            function = null;
        } else {
            function = type.getProxyProvider();
        }
        this.proxyProvider = function;
        this.handler = new Handler();
    }
}
