package io.grpc.internal;

import com.google.common.base.MoreObjects;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener.RpcProgress;
import io.grpc.internal.StreamListener.MessageProducer;

abstract class ForwardingClientStreamListener implements ClientStreamListener {
    /* access modifiers changed from: protected */
    public abstract ClientStreamListener delegate();

    ForwardingClientStreamListener() {
    }

    public void headersRead(Metadata metadata) {
        delegate().headersRead(metadata);
    }

    public void closed(Status status, Metadata metadata) {
        delegate().closed(status, metadata);
    }

    public void closed(Status status, RpcProgress rpcProgress, Metadata metadata) {
        delegate().closed(status, rpcProgress, metadata);
    }

    public void messagesAvailable(MessageProducer messageProducer) {
        delegate().messagesAvailable(messageProducer);
    }

    public void onReady() {
        delegate().onReady();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
