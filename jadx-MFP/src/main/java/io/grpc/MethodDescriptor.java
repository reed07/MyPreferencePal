package io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class MethodDescriptor<ReqT, RespT> {
    private final String fullMethodName;
    private final boolean idempotent;
    private final AtomicReferenceArray<Object> rawMethodNames;
    private final Marshaller<ReqT> requestMarshaller;
    private final Marshaller<RespT> responseMarshaller;
    private final boolean safe;
    private final boolean sampledToLocalTracing;
    @Nullable
    private final Object schemaDescriptor;
    private final MethodType type;

    public static final class Builder<ReqT, RespT> {
        private String fullMethodName;
        private boolean idempotent;
        private Marshaller<ReqT> requestMarshaller;
        private Marshaller<RespT> responseMarshaller;
        private boolean safe;
        private boolean sampledToLocalTracing;
        private Object schemaDescriptor;
        private MethodType type;

        private Builder() {
        }

        public Builder<ReqT, RespT> setRequestMarshaller(Marshaller<ReqT> marshaller) {
            this.requestMarshaller = marshaller;
            return this;
        }

        public Builder<ReqT, RespT> setResponseMarshaller(Marshaller<RespT> marshaller) {
            this.responseMarshaller = marshaller;
            return this;
        }

        public Builder<ReqT, RespT> setType(MethodType methodType) {
            this.type = methodType;
            return this;
        }

        public Builder<ReqT, RespT> setFullMethodName(String str) {
            this.fullMethodName = str;
            return this;
        }

        public Builder<ReqT, RespT> setSchemaDescriptor(@Nullable Object obj) {
            this.schemaDescriptor = obj;
            return this;
        }

        @ExperimentalApi
        public Builder<ReqT, RespT> setIdempotent(boolean z) {
            this.idempotent = z;
            return this;
        }

        @ExperimentalApi
        public Builder<ReqT, RespT> setSafe(boolean z) {
            this.safe = z;
            return this;
        }

        public Builder<ReqT, RespT> setSampledToLocalTracing(boolean z) {
            this.sampledToLocalTracing = z;
            return this;
        }

        @CheckReturnValue
        public MethodDescriptor<ReqT, RespT> build() {
            MethodDescriptor methodDescriptor = new MethodDescriptor(this.type, this.fullMethodName, this.requestMarshaller, this.responseMarshaller, this.schemaDescriptor, this.idempotent, this.safe, this.sampledToLocalTracing);
            return methodDescriptor;
        }
    }

    @ExperimentalApi
    public interface Marshaller<T> {
        T parse(InputStream inputStream);

        InputStream stream(T t);
    }

    public enum MethodType {
        UNARY,
        CLIENT_STREAMING,
        SERVER_STREAMING,
        BIDI_STREAMING,
        UNKNOWN
    }

    @ExperimentalApi
    public interface PrototypeMarshaller<T> extends ReflectableMarshaller<T> {
    }

    @ExperimentalApi
    public interface ReflectableMarshaller<T> extends Marshaller<T> {
    }

    @Deprecated
    public static <RequestT, ResponseT> MethodDescriptor<RequestT, ResponseT> create(MethodType methodType, String str, Marshaller<RequestT> marshaller, Marshaller<ResponseT> marshaller2) {
        MethodDescriptor methodDescriptor = new MethodDescriptor(methodType, str, marshaller, marshaller2, null, false, false, false);
        return methodDescriptor;
    }

    private MethodDescriptor(MethodType methodType, String str, Marshaller<ReqT> marshaller, Marshaller<RespT> marshaller2, Object obj, boolean z, boolean z2, boolean z3) {
        boolean z4 = true;
        this.rawMethodNames = new AtomicReferenceArray<>(1);
        this.type = (MethodType) Preconditions.checkNotNull(methodType, "type");
        this.fullMethodName = (String) Preconditions.checkNotNull(str, "fullMethodName");
        this.requestMarshaller = (Marshaller) Preconditions.checkNotNull(marshaller, "requestMarshaller");
        this.responseMarshaller = (Marshaller) Preconditions.checkNotNull(marshaller2, "responseMarshaller");
        this.schemaDescriptor = obj;
        this.idempotent = z;
        this.safe = z2;
        this.sampledToLocalTracing = z3;
        if (z2 && methodType != MethodType.UNARY) {
            z4 = false;
        }
        Preconditions.checkArgument(z4, "Only unary methods can be specified safe");
    }

    public MethodType getType() {
        return this.type;
    }

    public String getFullMethodName() {
        return this.fullMethodName;
    }

    public RespT parseResponse(InputStream inputStream) {
        return this.responseMarshaller.parse(inputStream);
    }

    public InputStream streamRequest(ReqT reqt) {
        return this.requestMarshaller.stream(reqt);
    }

    @ExperimentalApi
    public Marshaller<ReqT> getRequestMarshaller() {
        return this.requestMarshaller;
    }

    @ExperimentalApi
    public Marshaller<RespT> getResponseMarshaller() {
        return this.responseMarshaller;
    }

    @ExperimentalApi
    public boolean isSafe() {
        return this.safe;
    }

    public boolean isSampledToLocalTracing() {
        return this.sampledToLocalTracing;
    }

    public static String generateFullMethodName(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append((String) Preconditions.checkNotNull(str, "fullServiceName"));
        sb.append("/");
        sb.append((String) Preconditions.checkNotNull(str2, "methodName"));
        return sb.toString();
    }

    @Nullable
    public static String extractFullServiceName(String str) {
        int lastIndexOf = ((String) Preconditions.checkNotNull(str, "fullMethodName")).lastIndexOf(47);
        if (lastIndexOf == -1) {
            return null;
        }
        return str.substring(0, lastIndexOf);
    }

    @CheckReturnValue
    public static <ReqT, RespT> Builder<ReqT, RespT> newBuilder() {
        return newBuilder(null, null);
    }

    @CheckReturnValue
    public static <ReqT, RespT> Builder<ReqT, RespT> newBuilder(Marshaller<ReqT> marshaller, Marshaller<RespT> marshaller2) {
        return new Builder().setRequestMarshaller(marshaller).setResponseMarshaller(marshaller2);
    }

    @CheckReturnValue
    public <NewReqT, NewRespT> Builder<NewReqT, NewRespT> toBuilder(Marshaller<NewReqT> marshaller, Marshaller<NewRespT> marshaller2) {
        return newBuilder().setRequestMarshaller(marshaller).setResponseMarshaller(marshaller2).setType(this.type).setFullMethodName(this.fullMethodName).setIdempotent(this.idempotent).setSafe(this.safe).setSampledToLocalTracing(this.sampledToLocalTracing).setSchemaDescriptor(this.schemaDescriptor);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("fullMethodName", (Object) this.fullMethodName).add("type", (Object) this.type).add("idempotent", this.idempotent).add("safe", this.safe).add("sampledToLocalTracing", this.sampledToLocalTracing).add("requestMarshaller", (Object) this.requestMarshaller).add("responseMarshaller", (Object) this.responseMarshaller).add("schemaDescriptor", this.schemaDescriptor).omitNullValues().toString();
    }
}
