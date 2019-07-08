package com.brightcove.player.offline;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.brightcove.player.C;
import com.brightcove.player.edge.OfflineStoreManager;
import com.brightcove.player.logging.Log;
import com.google.android.exoplayer2.upstream.AssetDataSource;
import com.google.android.exoplayer2.upstream.ContentDataSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MultiDataSource implements DataSource {
    private static final String ASSET_SCHEME = "asset";
    private static final String ASSET_URI_PREFIX = "/android_asset/";
    private static final String CONTENT_SCHEME = "content";
    private static final String FILE_SCHEME = "file";
    private static final String HTTPS_SCHEME = "https";
    private static final String HTTP_SCHEME = "http";
    private static final String RTMP_SCHEME = "rtmp";
    /* access modifiers changed from: private */
    public static final String TAG = "MultiDataSource";
    @Nullable
    protected DataSource delegate;
    @NonNull
    private final Factory factory;
    @NonNull
    List<TransferListener> transferListeners;

    public static class Factory implements com.google.android.exoplayer2.upstream.DataSource.Factory {
        /* access modifiers changed from: private */
        @NonNull
        public final Context context;
        /* access modifiers changed from: private */
        @NonNull
        public final com.google.android.exoplayer2.upstream.HttpDataSource.Factory httpDataSourceFactory;
        @Nullable
        private final TransferListener listener;
        /* access modifiers changed from: private */
        @NonNull
        public final OfflineStoreManager storeManager;

        public Factory(@NonNull Context context2, @NonNull com.google.android.exoplayer2.upstream.HttpDataSource.Factory factory, @Nullable TransferListener transferListener) {
            this.context = context2.getApplicationContext();
            this.storeManager = OfflineStoreManager.getInstance(context2);
            this.httpDataSourceFactory = factory;
            this.listener = transferListener;
        }

        public Factory(@NonNull Context context2, @Nullable TransferListener transferListener) {
            this(context2, new DefaultHttpDataSourceFactory(C.HTTP_USER_AGENT, transferListener), transferListener);
        }

        public DataSource createDataSource() {
            return new MultiDataSource(this);
        }
    }

    private static class NoOpDataSource implements DataSource {
        @Nullable
        private DataSpec dataSpec;

        public void addTransferListener(TransferListener transferListener) {
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            return -1;
        }

        private NoOpDataSource() {
        }

        public long open(DataSpec dataSpec2) throws IOException {
            this.dataSpec = dataSpec2;
            return 0;
        }

        public Uri getUri() {
            DataSpec dataSpec2 = this.dataSpec;
            if (dataSpec2 == null) {
                return null;
            }
            return dataSpec2.uri;
        }

        public Map<String, List<String>> getResponseHeaders() {
            return Collections.emptyMap();
        }

        public void close() throws IOException {
            this.dataSpec = null;
        }
    }

    private static class RmtpDataSourceFactory {
        private static final Constructor<DataSource> CONSTRUCTOR;

        private RmtpDataSourceFactory() {
        }

        static {
            Constructor<DataSource> constructor = null;
            try {
                constructor = Class.forName("com.google.android.exoplayer2.ext.rtmp.RtmpDataSource").getConstructor(new Class[0]);
            } catch (Exception e) {
                Log.v(MultiDataSource.TAG, "RTMP data source support is not available", e, new Object[0]);
            } finally {
                CONSTRUCTOR = constructor;
            }
        }

        @NonNull
        public static DataSource create() {
            Constructor<DataSource> constructor = CONSTRUCTOR;
            if (constructor != null) {
                try {
                    return (DataSource) constructor.newInstance(new Object[0]);
                } catch (Exception e) {
                    Log.e(MultiDataSource.TAG, "Failed to create data source", e, new Object[0]);
                }
            }
            return new NoOpDataSource();
        }
    }

    private MultiDataSource(@NonNull Factory factory2) {
        this.factory = factory2;
        this.transferListeners = new ArrayList();
    }

    public void addTransferListener(TransferListener transferListener) {
        this.transferListeners.add(transferListener);
        DataSource dataSource = this.delegate;
        if (dataSource != null) {
            dataSource.addTransferListener(transferListener);
        }
    }

    public long open(DataSpec dataSpec) throws IOException {
        if (this.delegate == null) {
            String scheme = dataSpec.uri.getScheme();
            if (TextUtils.isEmpty(scheme) || FILE_SCHEME.equalsIgnoreCase(scheme)) {
                if (dataSpec.uri.getPath().startsWith(ASSET_URI_PREFIX)) {
                    this.delegate = new AssetDataSource(this.factory.context);
                } else {
                    this.delegate = new FileDataSource();
                }
            } else if (ASSET_SCHEME.equalsIgnoreCase(scheme)) {
                this.delegate = new AssetDataSource(this.factory.context);
            } else if ("content".equalsIgnoreCase(scheme)) {
                this.delegate = new ContentDataSource(this.factory.context);
            } else if (RTMP_SCHEME.equalsIgnoreCase(scheme)) {
                this.delegate = RmtpDataSourceFactory.create();
            } else if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                Uri findOfflineAssetUri = this.factory.storeManager.findOfflineAssetUri(dataSpec.uri);
                if (findOfflineAssetUri == null) {
                    this.delegate = this.factory.httpDataSourceFactory.createDataSource();
                } else {
                    this.delegate = new FileDataSource();
                    Log.v(TAG, "Switching to local asset: %s", findOfflineAssetUri);
                    DataSpec dataSpec2 = new DataSpec(findOfflineAssetUri, dataSpec.absoluteStreamPosition, dataSpec.position, dataSpec.length, dataSpec.key, dataSpec.flags);
                    dataSpec = dataSpec2;
                }
            }
            for (TransferListener addTransferListener : this.transferListeners) {
                this.delegate.addTransferListener(addTransferListener);
            }
            return this.delegate.open(dataSpec);
        }
        throw new IllegalStateException("Datasource is already open!");
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        DataSource dataSource = this.delegate;
        if (dataSource != null) {
            return dataSource.read(bArr, i, i2);
        }
        throw new IOException("DataSource delegate is null, was it already closed?");
    }

    public Uri getUri() {
        DataSource dataSource = this.delegate;
        if (dataSource != null) {
            return dataSource.getUri();
        }
        return null;
    }

    public Map<String, List<String>> getResponseHeaders() {
        Map<String, List<String>> emptyMap = Collections.emptyMap();
        DataSource dataSource = this.delegate;
        return dataSource != null ? dataSource.getResponseHeaders() : emptyMap;
    }

    public void close() throws IOException {
        DataSource dataSource = this.delegate;
        if (dataSource != null) {
            try {
                dataSource.close();
            } finally {
                this.delegate = null;
            }
        }
    }
}
