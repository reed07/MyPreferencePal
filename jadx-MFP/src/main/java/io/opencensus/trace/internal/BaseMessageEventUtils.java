package io.opencensus.trace.internal;

import io.opencensus.internal.Utils;
import io.opencensus.trace.BaseMessageEvent;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.MessageEvent.Type;
import io.opencensus.trace.NetworkEvent;

public final class BaseMessageEventUtils {
    public static MessageEvent asMessageEvent(BaseMessageEvent baseMessageEvent) {
        Type type;
        Utils.checkNotNull(baseMessageEvent, "event");
        if (baseMessageEvent instanceof MessageEvent) {
            return (MessageEvent) baseMessageEvent;
        }
        NetworkEvent networkEvent = (NetworkEvent) baseMessageEvent;
        if (networkEvent.getType() == NetworkEvent.Type.RECV) {
            type = Type.RECEIVED;
        } else {
            type = Type.SENT;
        }
        return MessageEvent.builder(type, networkEvent.getMessageId()).setUncompressedMessageSize(networkEvent.getUncompressedMessageSize()).setCompressedMessageSize(networkEvent.getCompressedMessageSize()).build();
    }

    public static NetworkEvent asNetworkEvent(BaseMessageEvent baseMessageEvent) {
        NetworkEvent.Type type;
        Utils.checkNotNull(baseMessageEvent, "event");
        if (baseMessageEvent instanceof NetworkEvent) {
            return (NetworkEvent) baseMessageEvent;
        }
        MessageEvent messageEvent = (MessageEvent) baseMessageEvent;
        if (messageEvent.getType() == Type.RECEIVED) {
            type = NetworkEvent.Type.RECV;
        } else {
            type = NetworkEvent.Type.SENT;
        }
        return NetworkEvent.builder(type, messageEvent.getMessageId()).setUncompressedMessageSize(messageEvent.getUncompressedMessageSize()).setCompressedMessageSize(messageEvent.getCompressedMessageSize()).build();
    }

    private BaseMessageEventUtils() {
    }
}
