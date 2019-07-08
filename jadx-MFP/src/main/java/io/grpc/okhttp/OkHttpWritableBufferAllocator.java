package io.grpc.okhttp;

import com.google.android.exoplayer2.source.ExtractorMediaSource;
import io.grpc.internal.WritableBuffer;
import io.grpc.internal.WritableBufferAllocator;
import okio.Buffer;

class OkHttpWritableBufferAllocator implements WritableBufferAllocator {
    OkHttpWritableBufferAllocator() {
    }

    public WritableBuffer allocate(int i) {
        return new OkHttpWritableBuffer(new Buffer(), Math.min(ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES, Math.max(4096, i)));
    }
}
