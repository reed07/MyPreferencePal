package io.grpc;

import java.io.Closeable;

@ExperimentalApi
public abstract class BinaryLog implements Closeable {
    public abstract Channel wrapChannel(Channel channel);
}
