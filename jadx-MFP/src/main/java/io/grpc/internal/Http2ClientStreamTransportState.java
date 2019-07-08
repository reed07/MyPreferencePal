package io.grpc.internal;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import io.grpc.InternalMetadata;
import io.grpc.InternalMetadata.TrustedAsciiMarshaller;
import io.grpc.InternalStatus;
import io.grpc.Metadata;
import io.grpc.Metadata.Key;
import io.grpc.Status;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

public abstract class Http2ClientStreamTransportState extends TransportState {
    private static final Key<Integer> HTTP2_STATUS = InternalMetadata.keyOf(":status", HTTP_STATUS_MARSHALLER);
    private static final TrustedAsciiMarshaller<Integer> HTTP_STATUS_MARSHALLER = new TrustedAsciiMarshaller<Integer>() {
        public byte[] toAsciiString(Integer num) {
            throw new UnsupportedOperationException();
        }

        public Integer parseAsciiString(byte[] bArr) {
            if (bArr.length >= 3) {
                return Integer.valueOf(((bArr[0] - 48) * 100) + ((bArr[1] - 48) * 10) + (bArr[2] - 48));
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Malformed status code ");
            sb.append(new String(bArr, InternalMetadata.US_ASCII));
            throw new NumberFormatException(sb.toString());
        }
    };
    private Charset errorCharset = Charsets.UTF_8;
    private boolean headersReceived;
    private Status transportError;
    private Metadata transportErrorMetadata;

    /* access modifiers changed from: protected */
    public abstract void http2ProcessingFailed(Status status, boolean z, Metadata metadata);

    public /* bridge */ /* synthetic */ void deframerClosed(boolean z) {
        super.deframerClosed(z);
    }

    protected Http2ClientStreamTransportState(int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer) {
        super(i, statsTraceContext, transportTracer);
    }

    /* access modifiers changed from: protected */
    public void transportHeadersReceived(Metadata metadata) {
        Preconditions.checkNotNull(metadata, "headers");
        Status status = this.transportError;
        if (status != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("headers: ");
            sb.append(metadata);
            this.transportError = status.augmentDescription(sb.toString());
            return;
        }
        try {
            if (this.headersReceived) {
                this.transportError = Status.INTERNAL.withDescription("Received headers twice");
                return;
            }
            Integer num = (Integer) metadata.get(HTTP2_STATUS);
            if (num == null || num.intValue() < 100 || num.intValue() >= 200) {
                this.headersReceived = true;
                this.transportError = validateInitialMetadata(metadata);
                if (this.transportError != null) {
                    Status status2 = this.transportError;
                    if (status2 != null) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("headers: ");
                        sb2.append(metadata);
                        this.transportError = status2.augmentDescription(sb2.toString());
                        this.transportErrorMetadata = metadata;
                        this.errorCharset = extractCharset(metadata);
                    }
                    return;
                }
                stripTransportDetails(metadata);
                inboundHeadersReceived(metadata);
                Status status3 = this.transportError;
                if (status3 != null) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("headers: ");
                    sb3.append(metadata);
                    this.transportError = status3.augmentDescription(sb3.toString());
                    this.transportErrorMetadata = metadata;
                    this.errorCharset = extractCharset(metadata);
                }
                return;
            }
            Status status4 = this.transportError;
            if (status4 != null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("headers: ");
                sb4.append(metadata);
                this.transportError = status4.augmentDescription(sb4.toString());
                this.transportErrorMetadata = metadata;
                this.errorCharset = extractCharset(metadata);
            }
        } finally {
            Status status5 = this.transportError;
            if (status5 != null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append("headers: ");
                sb5.append(metadata);
                this.transportError = status5.augmentDescription(sb5.toString());
                this.transportErrorMetadata = metadata;
                this.errorCharset = extractCharset(metadata);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void transportDataReceived(ReadableBuffer readableBuffer, boolean z) {
        Status status = this.transportError;
        if (status != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("DATA-----------------------------\n");
            sb.append(ReadableBuffers.readAsString(readableBuffer, this.errorCharset));
            this.transportError = status.augmentDescription(sb.toString());
            readableBuffer.close();
            if (this.transportError.getDescription().length() > 1000 || z) {
                http2ProcessingFailed(this.transportError, false, this.transportErrorMetadata);
            }
        } else if (!this.headersReceived) {
            http2ProcessingFailed(Status.INTERNAL.withDescription("headers not received before payload"), false, new Metadata());
        } else {
            inboundDataReceived(readableBuffer);
            if (z) {
                this.transportError = Status.INTERNAL.withDescription("Received unexpected EOS on DATA frame from server.");
                this.transportErrorMetadata = new Metadata();
                transportReportStatus(this.transportError, false, this.transportErrorMetadata);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void transportTrailersReceived(Metadata metadata) {
        Preconditions.checkNotNull(metadata, "trailers");
        if (this.transportError == null && !this.headersReceived) {
            this.transportError = validateInitialMetadata(metadata);
            if (this.transportError != null) {
                this.transportErrorMetadata = metadata;
            }
        }
        Status status = this.transportError;
        if (status != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("trailers: ");
            sb.append(metadata);
            this.transportError = status.augmentDescription(sb.toString());
            http2ProcessingFailed(this.transportError, false, this.transportErrorMetadata);
            return;
        }
        Status statusFromTrailers = statusFromTrailers(metadata);
        stripTransportDetails(metadata);
        inboundTrailersReceived(metadata, statusFromTrailers);
    }

    private Status statusFromTrailers(Metadata metadata) {
        Status status;
        Status status2 = (Status) metadata.get(InternalStatus.CODE_KEY);
        if (status2 != null) {
            return status2.withDescription((String) metadata.get(InternalStatus.MESSAGE_KEY));
        }
        if (this.headersReceived) {
            return Status.UNKNOWN.withDescription("missing GRPC status in response");
        }
        Integer num = (Integer) metadata.get(HTTP2_STATUS);
        if (num != null) {
            status = GrpcUtil.httpStatusToGrpcStatus(num.intValue());
        } else {
            status = Status.INTERNAL.withDescription("missing HTTP status code");
        }
        return status.augmentDescription("missing GRPC status, inferred error from HTTP status code");
    }

    @Nullable
    private Status validateInitialMetadata(Metadata metadata) {
        Integer num = (Integer) metadata.get(HTTP2_STATUS);
        if (num == null) {
            return Status.INTERNAL.withDescription("Missing HTTP status code");
        }
        String str = (String) metadata.get(GrpcUtil.CONTENT_TYPE_KEY);
        if (GrpcUtil.isGrpcContentType(str)) {
            return null;
        }
        Status httpStatusToGrpcStatus = GrpcUtil.httpStatusToGrpcStatus(num.intValue());
        StringBuilder sb = new StringBuilder();
        sb.append("invalid content-type: ");
        sb.append(str);
        return httpStatusToGrpcStatus.augmentDescription(sb.toString());
    }

    private static Charset extractCharset(Metadata metadata) {
        String str = (String) metadata.get(GrpcUtil.CONTENT_TYPE_KEY);
        if (str != null) {
            String[] split = str.split("charset=", 2);
            try {
                return Charset.forName(split[split.length - 1].trim());
            } catch (Exception unused) {
            }
        }
        return Charsets.UTF_8;
    }

    private static void stripTransportDetails(Metadata metadata) {
        metadata.discardAll(HTTP2_STATUS);
        metadata.discardAll(InternalStatus.CODE_KEY);
        metadata.discardAll(InternalStatus.MESSAGE_KEY);
    }
}
