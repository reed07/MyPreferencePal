package io.grpc;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.Codec.Gzip;
import io.grpc.Codec.Identity;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ExperimentalApi
@ThreadSafe
public final class CompressorRegistry {
    private static final CompressorRegistry DEFAULT_INSTANCE = new CompressorRegistry(new Gzip(), Identity.NONE);
    private final ConcurrentMap<String, Compressor> compressors = new ConcurrentHashMap();

    public static CompressorRegistry getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    @VisibleForTesting
    CompressorRegistry(Compressor... compressorArr) {
        for (Compressor compressor : compressorArr) {
            this.compressors.put(compressor.getMessageEncoding(), compressor);
        }
    }

    @Nullable
    public Compressor lookupCompressor(String str) {
        return (Compressor) this.compressors.get(str);
    }
}
