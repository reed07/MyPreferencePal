package com.lightstep.tracer.grpc;

import io.grpc.BindableService;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ExperimentalApi;
import io.grpc.MethodDescriptor;
import io.grpc.MethodDescriptor.MethodType;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;

public class CollectorServiceGrpc {
    @ExperimentalApi
    public static final MethodDescriptor<ReportRequest, ReportResponse> METHOD_REPORT = MethodDescriptor.create(MethodType.UNARY, MethodDescriptor.generateFullMethodName("lightstep.collector.CollectorService", "Report"), ProtoUtils.marshaller(ReportRequest.getDefaultInstance()), ProtoUtils.marshaller(ReportResponse.getDefaultInstance()));

    public static final class CollectorServiceBlockingStub extends AbstractStub<CollectorServiceBlockingStub> {
        private CollectorServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private CollectorServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public CollectorServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new CollectorServiceBlockingStub(channel, callOptions);
        }

        public ReportResponse report(ReportRequest reportRequest) {
            return (ReportResponse) ClientCalls.blockingUnaryCall(getChannel(), CollectorServiceGrpc.METHOD_REPORT, getCallOptions(), reportRequest);
        }
    }

    public static final class CollectorServiceFutureStub extends AbstractStub<CollectorServiceFutureStub> {
        private CollectorServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public CollectorServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new CollectorServiceFutureStub(channel, callOptions);
        }
    }

    public static abstract class CollectorServiceImplBase implements BindableService {
    }

    public static final class CollectorServiceStub extends AbstractStub<CollectorServiceStub> {
        private CollectorServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public CollectorServiceStub build(Channel channel, CallOptions callOptions) {
            return new CollectorServiceStub(channel, callOptions);
        }
    }

    private CollectorServiceGrpc() {
    }

    public static CollectorServiceBlockingStub newBlockingStub(Channel channel) {
        return new CollectorServiceBlockingStub(channel);
    }
}
