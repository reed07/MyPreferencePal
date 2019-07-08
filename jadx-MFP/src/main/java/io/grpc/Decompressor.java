package io.grpc;

import java.io.IOException;
import java.io.InputStream;

@ExperimentalApi
public interface Decompressor {
    InputStream decompress(InputStream inputStream) throws IOException;

    String getMessageEncoding();
}
