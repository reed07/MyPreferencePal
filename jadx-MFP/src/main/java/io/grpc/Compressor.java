package io.grpc;

import java.io.IOException;
import java.io.OutputStream;

@ExperimentalApi
public interface Compressor {
    OutputStream compress(OutputStream outputStream) throws IOException;

    String getMessageEncoding();
}
