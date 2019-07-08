package com.google.logging.type;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class HttpRequest extends GeneratedMessageV3 implements HttpRequestOrBuilder {
    public static final int CACHE_FILL_BYTES_FIELD_NUMBER = 12;
    public static final int CACHE_HIT_FIELD_NUMBER = 9;
    public static final int CACHE_LOOKUP_FIELD_NUMBER = 11;
    public static final int CACHE_VALIDATED_WITH_ORIGIN_SERVER_FIELD_NUMBER = 10;
    private static final HttpRequest DEFAULT_INSTANCE = new HttpRequest();
    public static final int LATENCY_FIELD_NUMBER = 14;
    /* access modifiers changed from: private */
    public static final Parser<HttpRequest> PARSER = new AbstractParser<HttpRequest>() {
        public HttpRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HttpRequest(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PROTOCOL_FIELD_NUMBER = 15;
    public static final int REFERER_FIELD_NUMBER = 8;
    public static final int REMOTE_IP_FIELD_NUMBER = 7;
    public static final int REQUEST_METHOD_FIELD_NUMBER = 1;
    public static final int REQUEST_SIZE_FIELD_NUMBER = 3;
    public static final int REQUEST_URL_FIELD_NUMBER = 2;
    public static final int RESPONSE_SIZE_FIELD_NUMBER = 5;
    public static final int SERVER_IP_FIELD_NUMBER = 13;
    public static final int STATUS_FIELD_NUMBER = 4;
    public static final int USER_AGENT_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public long cacheFillBytes_;
    /* access modifiers changed from: private */
    public boolean cacheHit_;
    /* access modifiers changed from: private */
    public boolean cacheLookup_;
    /* access modifiers changed from: private */
    public boolean cacheValidatedWithOriginServer_;
    /* access modifiers changed from: private */
    public Duration latency_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object protocol_;
    /* access modifiers changed from: private */
    public volatile Object referer_;
    /* access modifiers changed from: private */
    public volatile Object remoteIp_;
    /* access modifiers changed from: private */
    public volatile Object requestMethod_;
    /* access modifiers changed from: private */
    public long requestSize_;
    /* access modifiers changed from: private */
    public volatile Object requestUrl_;
    /* access modifiers changed from: private */
    public long responseSize_;
    /* access modifiers changed from: private */
    public volatile Object serverIp_;
    /* access modifiers changed from: private */
    public int status_;
    /* access modifiers changed from: private */
    public volatile Object userAgent_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements HttpRequestOrBuilder {
        private long cacheFillBytes_;
        private boolean cacheHit_;
        private boolean cacheLookup_;
        private boolean cacheValidatedWithOriginServer_;
        private SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> latencyBuilder_;
        private Duration latency_;
        private Object protocol_;
        private Object referer_;
        private Object remoteIp_;
        private Object requestMethod_;
        private long requestSize_;
        private Object requestUrl_;
        private long responseSize_;
        private Object serverIp_;
        private int status_;
        private Object userAgent_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return HttpRequestProto.internal_static_google_logging_type_HttpRequest_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpRequestProto.internal_static_google_logging_type_HttpRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpRequest.class, Builder.class);
        }

        private Builder() {
            this.requestMethod_ = "";
            this.requestUrl_ = "";
            this.userAgent_ = "";
            this.remoteIp_ = "";
            this.serverIp_ = "";
            this.referer_ = "";
            this.latency_ = null;
            this.protocol_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.requestMethod_ = "";
            this.requestUrl_ = "";
            this.userAgent_ = "";
            this.remoteIp_ = "";
            this.serverIp_ = "";
            this.referer_ = "";
            this.latency_ = null;
            this.protocol_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            HttpRequest.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.requestMethod_ = "";
            this.requestUrl_ = "";
            this.requestSize_ = 0;
            this.status_ = 0;
            this.responseSize_ = 0;
            this.userAgent_ = "";
            this.remoteIp_ = "";
            this.serverIp_ = "";
            this.referer_ = "";
            if (this.latencyBuilder_ == null) {
                this.latency_ = null;
            } else {
                this.latency_ = null;
                this.latencyBuilder_ = null;
            }
            this.cacheLookup_ = false;
            this.cacheHit_ = false;
            this.cacheValidatedWithOriginServer_ = false;
            this.cacheFillBytes_ = 0;
            this.protocol_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return HttpRequestProto.internal_static_google_logging_type_HttpRequest_descriptor;
        }

        public HttpRequest getDefaultInstanceForType() {
            return HttpRequest.getDefaultInstance();
        }

        public HttpRequest build() {
            HttpRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public HttpRequest buildPartial() {
            HttpRequest httpRequest = new HttpRequest((com.google.protobuf.GeneratedMessageV3.Builder) this);
            httpRequest.requestMethod_ = this.requestMethod_;
            httpRequest.requestUrl_ = this.requestUrl_;
            httpRequest.requestSize_ = this.requestSize_;
            httpRequest.status_ = this.status_;
            httpRequest.responseSize_ = this.responseSize_;
            httpRequest.userAgent_ = this.userAgent_;
            httpRequest.remoteIp_ = this.remoteIp_;
            httpRequest.serverIp_ = this.serverIp_;
            httpRequest.referer_ = this.referer_;
            SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.latencyBuilder_;
            if (singleFieldBuilderV3 == null) {
                httpRequest.latency_ = this.latency_;
            } else {
                httpRequest.latency_ = (Duration) singleFieldBuilderV3.build();
            }
            httpRequest.cacheLookup_ = this.cacheLookup_;
            httpRequest.cacheHit_ = this.cacheHit_;
            httpRequest.cacheValidatedWithOriginServer_ = this.cacheValidatedWithOriginServer_;
            httpRequest.cacheFillBytes_ = this.cacheFillBytes_;
            httpRequest.protocol_ = this.protocol_;
            onBuilt();
            return httpRequest;
        }

        public Builder clone() {
            return (Builder) super.clone();
        }

        public Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        public Builder clearField(FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        public Builder clearOneof(OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        public Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        public Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        public Builder mergeFrom(Message message) {
            if (message instanceof HttpRequest) {
                return mergeFrom((HttpRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HttpRequest httpRequest) {
            if (httpRequest == HttpRequest.getDefaultInstance()) {
                return this;
            }
            if (!httpRequest.getRequestMethod().isEmpty()) {
                this.requestMethod_ = httpRequest.requestMethod_;
                onChanged();
            }
            if (!httpRequest.getRequestUrl().isEmpty()) {
                this.requestUrl_ = httpRequest.requestUrl_;
                onChanged();
            }
            if (httpRequest.getRequestSize() != 0) {
                setRequestSize(httpRequest.getRequestSize());
            }
            if (httpRequest.getStatus() != 0) {
                setStatus(httpRequest.getStatus());
            }
            if (httpRequest.getResponseSize() != 0) {
                setResponseSize(httpRequest.getResponseSize());
            }
            if (!httpRequest.getUserAgent().isEmpty()) {
                this.userAgent_ = httpRequest.userAgent_;
                onChanged();
            }
            if (!httpRequest.getRemoteIp().isEmpty()) {
                this.remoteIp_ = httpRequest.remoteIp_;
                onChanged();
            }
            if (!httpRequest.getServerIp().isEmpty()) {
                this.serverIp_ = httpRequest.serverIp_;
                onChanged();
            }
            if (!httpRequest.getReferer().isEmpty()) {
                this.referer_ = httpRequest.referer_;
                onChanged();
            }
            if (httpRequest.hasLatency()) {
                mergeLatency(httpRequest.getLatency());
            }
            if (httpRequest.getCacheLookup()) {
                setCacheLookup(httpRequest.getCacheLookup());
            }
            if (httpRequest.getCacheHit()) {
                setCacheHit(httpRequest.getCacheHit());
            }
            if (httpRequest.getCacheValidatedWithOriginServer()) {
                setCacheValidatedWithOriginServer(httpRequest.getCacheValidatedWithOriginServer());
            }
            if (httpRequest.getCacheFillBytes() != 0) {
                setCacheFillBytes(httpRequest.getCacheFillBytes());
            }
            if (!httpRequest.getProtocol().isEmpty()) {
                this.protocol_ = httpRequest.protocol_;
                onChanged();
            }
            mergeUnknownFields(httpRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.logging.type.HttpRequest.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.logging.type.HttpRequest.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.logging.type.HttpRequest r3 = (com.google.logging.type.HttpRequest) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                if (r3 == 0) goto L_0x0010
                r2.mergeFrom(r3)
            L_0x0010:
                return r2
            L_0x0011:
                r3 = move-exception
                goto L_0x0021
            L_0x0013:
                r3 = move-exception
                com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch:{ all -> 0x0011 }
                com.google.logging.type.HttpRequest r4 = (com.google.logging.type.HttpRequest) r4     // Catch:{ all -> 0x0011 }
                java.io.IOException r3 = r3.unwrapIOException()     // Catch:{ all -> 0x001f }
                throw r3     // Catch:{ all -> 0x001f }
            L_0x001f:
                r3 = move-exception
                r0 = r4
            L_0x0021:
                if (r0 == 0) goto L_0x0026
                r2.mergeFrom(r0)
            L_0x0026:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.logging.type.HttpRequest.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.logging.type.HttpRequest$Builder");
        }

        public String getRequestMethod() {
            Object obj = this.requestMethod_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.requestMethod_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getRequestMethodBytes() {
            Object obj = this.requestMethod_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.requestMethod_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setRequestMethod(String str) {
            if (str != null) {
                this.requestMethod_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearRequestMethod() {
            this.requestMethod_ = HttpRequest.getDefaultInstance().getRequestMethod();
            onChanged();
            return this;
        }

        public Builder setRequestMethodBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRequest.checkByteStringIsUtf8(byteString);
                this.requestMethod_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getRequestUrl() {
            Object obj = this.requestUrl_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.requestUrl_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getRequestUrlBytes() {
            Object obj = this.requestUrl_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.requestUrl_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setRequestUrl(String str) {
            if (str != null) {
                this.requestUrl_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearRequestUrl() {
            this.requestUrl_ = HttpRequest.getDefaultInstance().getRequestUrl();
            onChanged();
            return this;
        }

        public Builder setRequestUrlBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRequest.checkByteStringIsUtf8(byteString);
                this.requestUrl_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public long getRequestSize() {
            return this.requestSize_;
        }

        public Builder setRequestSize(long j) {
            this.requestSize_ = j;
            onChanged();
            return this;
        }

        public Builder clearRequestSize() {
            this.requestSize_ = 0;
            onChanged();
            return this;
        }

        public int getStatus() {
            return this.status_;
        }

        public Builder setStatus(int i) {
            this.status_ = i;
            onChanged();
            return this;
        }

        public Builder clearStatus() {
            this.status_ = 0;
            onChanged();
            return this;
        }

        public long getResponseSize() {
            return this.responseSize_;
        }

        public Builder setResponseSize(long j) {
            this.responseSize_ = j;
            onChanged();
            return this;
        }

        public Builder clearResponseSize() {
            this.responseSize_ = 0;
            onChanged();
            return this;
        }

        public String getUserAgent() {
            Object obj = this.userAgent_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.userAgent_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getUserAgentBytes() {
            Object obj = this.userAgent_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.userAgent_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setUserAgent(String str) {
            if (str != null) {
                this.userAgent_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearUserAgent() {
            this.userAgent_ = HttpRequest.getDefaultInstance().getUserAgent();
            onChanged();
            return this;
        }

        public Builder setUserAgentBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRequest.checkByteStringIsUtf8(byteString);
                this.userAgent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getRemoteIp() {
            Object obj = this.remoteIp_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.remoteIp_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getRemoteIpBytes() {
            Object obj = this.remoteIp_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.remoteIp_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setRemoteIp(String str) {
            if (str != null) {
                this.remoteIp_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearRemoteIp() {
            this.remoteIp_ = HttpRequest.getDefaultInstance().getRemoteIp();
            onChanged();
            return this;
        }

        public Builder setRemoteIpBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRequest.checkByteStringIsUtf8(byteString);
                this.remoteIp_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getServerIp() {
            Object obj = this.serverIp_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.serverIp_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getServerIpBytes() {
            Object obj = this.serverIp_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.serverIp_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setServerIp(String str) {
            if (str != null) {
                this.serverIp_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearServerIp() {
            this.serverIp_ = HttpRequest.getDefaultInstance().getServerIp();
            onChanged();
            return this;
        }

        public Builder setServerIpBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRequest.checkByteStringIsUtf8(byteString);
                this.serverIp_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getReferer() {
            Object obj = this.referer_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.referer_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getRefererBytes() {
            Object obj = this.referer_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.referer_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setReferer(String str) {
            if (str != null) {
                this.referer_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearReferer() {
            this.referer_ = HttpRequest.getDefaultInstance().getReferer();
            onChanged();
            return this;
        }

        public Builder setRefererBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRequest.checkByteStringIsUtf8(byteString);
                this.referer_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public boolean hasLatency() {
            return (this.latencyBuilder_ == null && this.latency_ == null) ? false : true;
        }

        public Duration getLatency() {
            SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.latencyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Duration) singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.latency_;
            if (duration == null) {
                duration = Duration.getDefaultInstance();
            }
            return duration;
        }

        public Builder setLatency(Duration duration) {
            SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.latencyBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(duration);
            } else if (duration != null) {
                this.latency_ = duration;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setLatency(com.google.protobuf.Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.latencyBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.latency_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeLatency(Duration duration) {
            SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.latencyBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.latency_;
                if (duration2 != null) {
                    this.latency_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.latency_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearLatency() {
            if (this.latencyBuilder_ == null) {
                this.latency_ = null;
                onChanged();
            } else {
                this.latency_ = null;
                this.latencyBuilder_ = null;
            }
            return this;
        }

        public com.google.protobuf.Duration.Builder getLatencyBuilder() {
            onChanged();
            return (com.google.protobuf.Duration.Builder) getLatencyFieldBuilder().getBuilder();
        }

        public DurationOrBuilder getLatencyOrBuilder() {
            SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.latencyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (DurationOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.latency_;
            if (duration == null) {
                duration = Duration.getDefaultInstance();
            }
            return duration;
        }

        private SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> getLatencyFieldBuilder() {
            if (this.latencyBuilder_ == null) {
                this.latencyBuilder_ = new SingleFieldBuilderV3<>(getLatency(), getParentForChildren(), isClean());
                this.latency_ = null;
            }
            return this.latencyBuilder_;
        }

        public boolean getCacheLookup() {
            return this.cacheLookup_;
        }

        public Builder setCacheLookup(boolean z) {
            this.cacheLookup_ = z;
            onChanged();
            return this;
        }

        public Builder clearCacheLookup() {
            this.cacheLookup_ = false;
            onChanged();
            return this;
        }

        public boolean getCacheHit() {
            return this.cacheHit_;
        }

        public Builder setCacheHit(boolean z) {
            this.cacheHit_ = z;
            onChanged();
            return this;
        }

        public Builder clearCacheHit() {
            this.cacheHit_ = false;
            onChanged();
            return this;
        }

        public boolean getCacheValidatedWithOriginServer() {
            return this.cacheValidatedWithOriginServer_;
        }

        public Builder setCacheValidatedWithOriginServer(boolean z) {
            this.cacheValidatedWithOriginServer_ = z;
            onChanged();
            return this;
        }

        public Builder clearCacheValidatedWithOriginServer() {
            this.cacheValidatedWithOriginServer_ = false;
            onChanged();
            return this;
        }

        public long getCacheFillBytes() {
            return this.cacheFillBytes_;
        }

        public Builder setCacheFillBytes(long j) {
            this.cacheFillBytes_ = j;
            onChanged();
            return this;
        }

        public Builder clearCacheFillBytes() {
            this.cacheFillBytes_ = 0;
            onChanged();
            return this;
        }

        public String getProtocol() {
            Object obj = this.protocol_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.protocol_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getProtocolBytes() {
            Object obj = this.protocol_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.protocol_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setProtocol(String str) {
            if (str != null) {
                this.protocol_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearProtocol() {
            this.protocol_ = HttpRequest.getDefaultInstance().getProtocol();
            onChanged();
            return this;
        }

        public Builder setProtocolBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRequest.checkByteStringIsUtf8(byteString);
                this.protocol_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private HttpRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private HttpRequest() {
        this.memoizedIsInitialized = -1;
        this.requestMethod_ = "";
        this.requestUrl_ = "";
        this.requestSize_ = 0;
        this.status_ = 0;
        this.responseSize_ = 0;
        this.userAgent_ = "";
        this.remoteIp_ = "";
        this.serverIp_ = "";
        this.referer_ = "";
        this.cacheLookup_ = false;
        this.cacheHit_ = false;
        this.cacheValidatedWithOriginServer_ = false;
        this.cacheFillBytes_ = 0;
        this.protocol_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private HttpRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            z = true;
                            break;
                        case 10:
                            this.requestMethod_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 18:
                            this.requestUrl_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 24:
                            this.requestSize_ = codedInputStream.readInt64();
                            break;
                        case 32:
                            this.status_ = codedInputStream.readInt32();
                            break;
                        case 40:
                            this.responseSize_ = codedInputStream.readInt64();
                            break;
                        case 50:
                            this.userAgent_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 58:
                            this.remoteIp_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 66:
                            this.referer_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 72:
                            this.cacheHit_ = codedInputStream.readBool();
                            break;
                        case 80:
                            this.cacheValidatedWithOriginServer_ = codedInputStream.readBool();
                            break;
                        case 88:
                            this.cacheLookup_ = codedInputStream.readBool();
                            break;
                        case 96:
                            this.cacheFillBytes_ = codedInputStream.readInt64();
                            break;
                        case 106:
                            this.serverIp_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 114:
                            com.google.protobuf.Duration.Builder builder = null;
                            if (this.latency_ != null) {
                                builder = this.latency_.toBuilder();
                            }
                            this.latency_ = (Duration) codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            if (builder == null) {
                                break;
                            } else {
                                builder.mergeFrom(this.latency_);
                                this.latency_ = builder.buildPartial();
                                break;
                            }
                        case 122:
                            this.protocol_ = codedInputStream.readStringRequireUtf8();
                            break;
                        default:
                            if (parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                break;
                            } else {
                                z = true;
                                break;
                            }
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return HttpRequestProto.internal_static_google_logging_type_HttpRequest_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpRequestProto.internal_static_google_logging_type_HttpRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpRequest.class, Builder.class);
    }

    public String getRequestMethod() {
        Object obj = this.requestMethod_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.requestMethod_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getRequestMethodBytes() {
        Object obj = this.requestMethod_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.requestMethod_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getRequestUrl() {
        Object obj = this.requestUrl_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.requestUrl_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getRequestUrlBytes() {
        Object obj = this.requestUrl_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.requestUrl_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public long getRequestSize() {
        return this.requestSize_;
    }

    public int getStatus() {
        return this.status_;
    }

    public long getResponseSize() {
        return this.responseSize_;
    }

    public String getUserAgent() {
        Object obj = this.userAgent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.userAgent_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getUserAgentBytes() {
        Object obj = this.userAgent_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.userAgent_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getRemoteIp() {
        Object obj = this.remoteIp_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.remoteIp_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getRemoteIpBytes() {
        Object obj = this.remoteIp_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.remoteIp_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getServerIp() {
        Object obj = this.serverIp_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.serverIp_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getServerIpBytes() {
        Object obj = this.serverIp_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.serverIp_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getReferer() {
        Object obj = this.referer_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.referer_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getRefererBytes() {
        Object obj = this.referer_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.referer_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasLatency() {
        return this.latency_ != null;
    }

    public Duration getLatency() {
        Duration duration = this.latency_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    public DurationOrBuilder getLatencyOrBuilder() {
        return getLatency();
    }

    public boolean getCacheLookup() {
        return this.cacheLookup_;
    }

    public boolean getCacheHit() {
        return this.cacheHit_;
    }

    public boolean getCacheValidatedWithOriginServer() {
        return this.cacheValidatedWithOriginServer_;
    }

    public long getCacheFillBytes() {
        return this.cacheFillBytes_;
    }

    public String getProtocol() {
        Object obj = this.protocol_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.protocol_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getProtocolBytes() {
        Object obj = this.protocol_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.protocol_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = 1;
        return true;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getRequestMethodBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.requestMethod_);
        }
        if (!getRequestUrlBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.requestUrl_);
        }
        long j = this.requestSize_;
        if (j != 0) {
            codedOutputStream.writeInt64(3, j);
        }
        int i = this.status_;
        if (i != 0) {
            codedOutputStream.writeInt32(4, i);
        }
        long j2 = this.responseSize_;
        if (j2 != 0) {
            codedOutputStream.writeInt64(5, j2);
        }
        if (!getUserAgentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.userAgent_);
        }
        if (!getRemoteIpBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.remoteIp_);
        }
        if (!getRefererBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.referer_);
        }
        boolean z = this.cacheHit_;
        if (z) {
            codedOutputStream.writeBool(9, z);
        }
        boolean z2 = this.cacheValidatedWithOriginServer_;
        if (z2) {
            codedOutputStream.writeBool(10, z2);
        }
        boolean z3 = this.cacheLookup_;
        if (z3) {
            codedOutputStream.writeBool(11, z3);
        }
        long j3 = this.cacheFillBytes_;
        if (j3 != 0) {
            codedOutputStream.writeInt64(12, j3);
        }
        if (!getServerIpBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 13, this.serverIp_);
        }
        if (this.latency_ != null) {
            codedOutputStream.writeMessage(14, getLatency());
        }
        if (!getProtocolBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 15, this.protocol_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getRequestMethodBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.requestMethod_);
        }
        if (!getRequestUrlBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.requestUrl_);
        }
        long j = this.requestSize_;
        if (j != 0) {
            i2 += CodedOutputStream.computeInt64Size(3, j);
        }
        int i3 = this.status_;
        if (i3 != 0) {
            i2 += CodedOutputStream.computeInt32Size(4, i3);
        }
        long j2 = this.responseSize_;
        if (j2 != 0) {
            i2 += CodedOutputStream.computeInt64Size(5, j2);
        }
        if (!getUserAgentBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(6, this.userAgent_);
        }
        if (!getRemoteIpBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(7, this.remoteIp_);
        }
        if (!getRefererBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(8, this.referer_);
        }
        boolean z = this.cacheHit_;
        if (z) {
            i2 += CodedOutputStream.computeBoolSize(9, z);
        }
        boolean z2 = this.cacheValidatedWithOriginServer_;
        if (z2) {
            i2 += CodedOutputStream.computeBoolSize(10, z2);
        }
        boolean z3 = this.cacheLookup_;
        if (z3) {
            i2 += CodedOutputStream.computeBoolSize(11, z3);
        }
        long j3 = this.cacheFillBytes_;
        if (j3 != 0) {
            i2 += CodedOutputStream.computeInt64Size(12, j3);
        }
        if (!getServerIpBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(13, this.serverIp_);
        }
        if (this.latency_ != null) {
            i2 += CodedOutputStream.computeMessageSize(14, getLatency());
        }
        if (!getProtocolBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(15, this.protocol_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HttpRequest)) {
            return super.equals(obj);
        }
        HttpRequest httpRequest = (HttpRequest) obj;
        boolean z2 = (((((((((getRequestMethod().equals(httpRequest.getRequestMethod())) && getRequestUrl().equals(httpRequest.getRequestUrl())) && (getRequestSize() > httpRequest.getRequestSize() ? 1 : (getRequestSize() == httpRequest.getRequestSize() ? 0 : -1)) == 0) && getStatus() == httpRequest.getStatus()) && (getResponseSize() > httpRequest.getResponseSize() ? 1 : (getResponseSize() == httpRequest.getResponseSize() ? 0 : -1)) == 0) && getUserAgent().equals(httpRequest.getUserAgent())) && getRemoteIp().equals(httpRequest.getRemoteIp())) && getServerIp().equals(httpRequest.getServerIp())) && getReferer().equals(httpRequest.getReferer())) && hasLatency() == httpRequest.hasLatency();
        if (hasLatency()) {
            z2 = z2 && getLatency().equals(httpRequest.getLatency());
        }
        if (!(((((z2 && getCacheLookup() == httpRequest.getCacheLookup()) && getCacheHit() == httpRequest.getCacheHit()) && getCacheValidatedWithOriginServer() == httpRequest.getCacheValidatedWithOriginServer()) && (getCacheFillBytes() > httpRequest.getCacheFillBytes() ? 1 : (getCacheFillBytes() == httpRequest.getCacheFillBytes() ? 0 : -1)) == 0) && getProtocol().equals(httpRequest.getProtocol())) || !this.unknownFields.equals(httpRequest.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getRequestMethod().hashCode()) * 37) + 2) * 53) + getRequestUrl().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getRequestSize())) * 37) + 4) * 53) + getStatus()) * 37) + 5) * 53) + Internal.hashLong(getResponseSize())) * 37) + 6) * 53) + getUserAgent().hashCode()) * 37) + 7) * 53) + getRemoteIp().hashCode()) * 37) + 13) * 53) + getServerIp().hashCode()) * 37) + 8) * 53) + getReferer().hashCode();
        if (hasLatency()) {
            hashCode = (((hashCode * 37) + 14) * 53) + getLatency().hashCode();
        }
        int hashBoolean = (((((((((((((((((((((hashCode * 37) + 11) * 53) + Internal.hashBoolean(getCacheLookup())) * 37) + 9) * 53) + Internal.hashBoolean(getCacheHit())) * 37) + 10) * 53) + Internal.hashBoolean(getCacheValidatedWithOriginServer())) * 37) + 12) * 53) + Internal.hashLong(getCacheFillBytes())) * 37) + 15) * 53) + getProtocol().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashBoolean;
        return hashBoolean;
    }

    public static HttpRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HttpRequest) PARSER.parseFrom(byteBuffer);
    }

    public static HttpRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HttpRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HttpRequest) PARSER.parseFrom(byteString);
    }

    public static HttpRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HttpRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HttpRequest) PARSER.parseFrom(bArr);
    }

    public static HttpRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HttpRequest parseFrom(InputStream inputStream) throws IOException {
        return (HttpRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HttpRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (HttpRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HttpRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (HttpRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HttpRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(HttpRequest httpRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(httpRequest);
    }

    public Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* access modifiers changed from: protected */
    public Builder newBuilderForType(BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static HttpRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpRequest> parser() {
        return PARSER;
    }

    public Parser<HttpRequest> getParserForType() {
        return PARSER;
    }

    public HttpRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
