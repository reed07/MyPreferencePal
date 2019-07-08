package io.grpc.internal;

import com.google.common.base.MoreObjects;
import io.grpc.InternalChannelz.ServerStats;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.Server;
import io.grpc.Status;
import io.grpc.internal.StreamListener.MessageProducer;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ServerImpl extends Server implements InternalInstrumented<ServerStats> {
    private static final ServerStreamListener NOOP_LISTENER = new NoopListener();
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(ServerImpl.class.getName());
    private final InternalLogId logId;
    private final InternalServer transportServer;

    private static final class NoopListener implements ServerStreamListener {
        public void closed(Status status) {
        }

        public void halfClosed() {
        }

        public void onReady() {
        }

        private NoopListener() {
        }

        public void messagesAvailable(MessageProducer messageProducer) {
            while (true) {
                InputStream next = messageProducer.next();
                if (next != null) {
                    try {
                        next.close();
                    } catch (IOException e) {
                        while (true) {
                            InputStream next2 = messageProducer.next();
                            if (next2 != null) {
                                try {
                                    next2.close();
                                } catch (IOException e2) {
                                    ServerImpl.log.log(Level.WARNING, "Exception closing stream", e2);
                                }
                            } else {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("transportServer", (Object) this.transportServer).toString();
    }
}
