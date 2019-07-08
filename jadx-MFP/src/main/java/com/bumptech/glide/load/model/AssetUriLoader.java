package com.bumptech.glide.load.model;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.FileDescriptorAssetPathFetcher;
import com.bumptech.glide.load.data.StreamAssetPathFetcher;
import com.bumptech.glide.load.model.ModelLoader.LoadData;
import com.bumptech.glide.signature.ObjectKey;
import java.io.InputStream;

public class AssetUriLoader<Data> implements ModelLoader<Uri, Data> {
    private static final int ASSET_PREFIX_LENGTH = 22;
    private final AssetManager assetManager;
    private final AssetFetcherFactory<Data> factory;

    public interface AssetFetcherFactory<Data> {
        DataFetcher<Data> buildFetcher(AssetManager assetManager, String str);
    }

    public static class FileDescriptorFactory implements AssetFetcherFactory<ParcelFileDescriptor>, ModelLoaderFactory<Uri, ParcelFileDescriptor> {
        private final AssetManager assetManager;

        public void teardown() {
        }

        public FileDescriptorFactory(AssetManager assetManager2) {
            this.assetManager = assetManager2;
        }

        @NonNull
        public ModelLoader<Uri, ParcelFileDescriptor> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new AssetUriLoader(this.assetManager, this);
        }

        public DataFetcher<ParcelFileDescriptor> buildFetcher(AssetManager assetManager2, String str) {
            return new FileDescriptorAssetPathFetcher(assetManager2, str);
        }
    }

    public static class StreamFactory implements AssetFetcherFactory<InputStream>, ModelLoaderFactory<Uri, InputStream> {
        private final AssetManager assetManager;

        public void teardown() {
        }

        public StreamFactory(AssetManager assetManager2) {
            this.assetManager = assetManager2;
        }

        @NonNull
        public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new AssetUriLoader(this.assetManager, this);
        }

        public DataFetcher<InputStream> buildFetcher(AssetManager assetManager2, String str) {
            return new StreamAssetPathFetcher(assetManager2, str);
        }
    }

    public AssetUriLoader(AssetManager assetManager2, AssetFetcherFactory<Data> assetFetcherFactory) {
        this.assetManager = assetManager2;
        this.factory = assetFetcherFactory;
    }

    public LoadData<Data> buildLoadData(@NonNull Uri uri, int i, int i2, @NonNull Options options) {
        return new LoadData<>(new ObjectKey(uri), this.factory.buildFetcher(this.assetManager, uri.toString().substring(ASSET_PREFIX_LENGTH)));
    }

    public boolean handles(@NonNull Uri uri) {
        if (!"file".equals(uri.getScheme()) || uri.getPathSegments().isEmpty() || !"android_asset".equals(uri.getPathSegments().get(0))) {
            return false;
        }
        return true;
    }
}
