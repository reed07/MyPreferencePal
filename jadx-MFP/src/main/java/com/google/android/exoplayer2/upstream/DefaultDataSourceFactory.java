package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DataSource.Factory;

public final class DefaultDataSourceFactory implements Factory {
    private final Factory baseDataSourceFactory;
    private final Context context;
    @Nullable
    private final TransferListener listener;

    public DefaultDataSourceFactory(Context context2, String str) {
        this(context2, str, (TransferListener) null);
    }

    public DefaultDataSourceFactory(Context context2, String str, @Nullable TransferListener transferListener) {
        this(context2, transferListener, (Factory) new DefaultHttpDataSourceFactory(str, transferListener));
    }

    public DefaultDataSourceFactory(Context context2, Factory factory) {
        this(context2, (TransferListener) null, factory);
    }

    public DefaultDataSourceFactory(Context context2, @Nullable TransferListener transferListener, Factory factory) {
        this.context = context2.getApplicationContext();
        this.listener = transferListener;
        this.baseDataSourceFactory = factory;
    }

    public DefaultDataSource createDataSource() {
        DefaultDataSource defaultDataSource = new DefaultDataSource(this.context, this.baseDataSourceFactory.createDataSource());
        TransferListener transferListener = this.listener;
        if (transferListener != null) {
            defaultDataSource.addTransferListener(transferListener);
        }
        return defaultDataSource;
    }
}
