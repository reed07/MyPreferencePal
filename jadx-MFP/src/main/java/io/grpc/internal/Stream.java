package io.grpc.internal;

import io.grpc.Compressor;
import java.io.InputStream;

public interface Stream {
    void flush();

    void request(int i);

    void setCompressor(Compressor compressor);

    void writeMessage(InputStream inputStream);
}
