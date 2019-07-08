package com.google.longrunning;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Empty;
import io.grpc.BindableService;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ExperimentalApi;
import io.grpc.MethodDescriptor;
import io.grpc.MethodDescriptor.MethodType;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServiceDescriptor;
import io.grpc.protobuf.ProtoFileDescriptorSupplier;
import io.grpc.protobuf.ProtoMethodDescriptorSupplier;
import io.grpc.protobuf.ProtoServiceDescriptorSupplier;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.ServerCalls.BidiStreamingMethod;
import io.grpc.stub.ServerCalls.ClientStreamingMethod;
import io.grpc.stub.ServerCalls.ServerStreamingMethod;
import io.grpc.stub.ServerCalls.UnaryMethod;
import io.grpc.stub.StreamObserver;

public final class OperationsGrpc {
    private static final int METHODID_CANCEL_OPERATION = 3;
    private static final int METHODID_DELETE_OPERATION = 2;
    private static final int METHODID_GET_OPERATION = 1;
    private static final int METHODID_LIST_OPERATIONS = 0;
    @ExperimentalApi
    @Deprecated
    public static final MethodDescriptor<CancelOperationRequest, Empty> METHOD_CANCEL_OPERATION = getCancelOperationMethodHelper();
    @ExperimentalApi
    @Deprecated
    public static final MethodDescriptor<DeleteOperationRequest, Empty> METHOD_DELETE_OPERATION = getDeleteOperationMethodHelper();
    @ExperimentalApi
    @Deprecated
    public static final MethodDescriptor<GetOperationRequest, Operation> METHOD_GET_OPERATION = getGetOperationMethodHelper();
    @ExperimentalApi
    @Deprecated
    public static final MethodDescriptor<ListOperationsRequest, ListOperationsResponse> METHOD_LIST_OPERATIONS = getListOperationsMethodHelper();
    public static final String SERVICE_NAME = "google.longrunning.Operations";
    private static volatile MethodDescriptor<CancelOperationRequest, Empty> getCancelOperationMethod;
    private static volatile MethodDescriptor<DeleteOperationRequest, Empty> getDeleteOperationMethod;
    private static volatile MethodDescriptor<GetOperationRequest, Operation> getGetOperationMethod;
    private static volatile MethodDescriptor<ListOperationsRequest, ListOperationsResponse> getListOperationsMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private static final class MethodHandlers<Req, Resp> implements BidiStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, UnaryMethod<Req, Resp> {
        private final int methodId;
        private final OperationsImplBase serviceImpl;

        MethodHandlers(OperationsImplBase operationsImplBase, int i) {
            this.serviceImpl = operationsImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            switch (this.methodId) {
                case 0:
                    this.serviceImpl.listOperations((ListOperationsRequest) req, streamObserver);
                    return;
                case 1:
                    this.serviceImpl.getOperation((GetOperationRequest) req, streamObserver);
                    return;
                case 2:
                    this.serviceImpl.deleteOperation((DeleteOperationRequest) req, streamObserver);
                    return;
                case 3:
                    this.serviceImpl.cancelOperation((CancelOperationRequest) req, streamObserver);
                    return;
                default:
                    throw new AssertionError();
            }
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    private static abstract class OperationsBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        OperationsBaseDescriptorSupplier() {
        }

        public FileDescriptor getFileDescriptor() {
            return OperationsProto.getDescriptor();
        }

        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("Operations");
        }
    }

    public static final class OperationsBlockingStub extends AbstractStub<OperationsBlockingStub> {
        private OperationsBlockingStub(Channel channel) {
            super(channel);
        }

        private OperationsBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public OperationsBlockingStub build(Channel channel, CallOptions callOptions) {
            return new OperationsBlockingStub(channel, callOptions);
        }

        public ListOperationsResponse listOperations(ListOperationsRequest listOperationsRequest) {
            return (ListOperationsResponse) ClientCalls.blockingUnaryCall(getChannel(), OperationsGrpc.getListOperationsMethodHelper(), getCallOptions(), listOperationsRequest);
        }

        public Operation getOperation(GetOperationRequest getOperationRequest) {
            return (Operation) ClientCalls.blockingUnaryCall(getChannel(), OperationsGrpc.getGetOperationMethodHelper(), getCallOptions(), getOperationRequest);
        }

        public Empty deleteOperation(DeleteOperationRequest deleteOperationRequest) {
            return (Empty) ClientCalls.blockingUnaryCall(getChannel(), OperationsGrpc.getDeleteOperationMethodHelper(), getCallOptions(), deleteOperationRequest);
        }

        public Empty cancelOperation(CancelOperationRequest cancelOperationRequest) {
            return (Empty) ClientCalls.blockingUnaryCall(getChannel(), OperationsGrpc.getCancelOperationMethodHelper(), getCallOptions(), cancelOperationRequest);
        }
    }

    private static final class OperationsFileDescriptorSupplier extends OperationsBaseDescriptorSupplier {
        OperationsFileDescriptorSupplier() {
        }
    }

    public static final class OperationsFutureStub extends AbstractStub<OperationsFutureStub> {
        private OperationsFutureStub(Channel channel) {
            super(channel);
        }

        private OperationsFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public OperationsFutureStub build(Channel channel, CallOptions callOptions) {
            return new OperationsFutureStub(channel, callOptions);
        }

        public ListenableFuture<ListOperationsResponse> listOperations(ListOperationsRequest listOperationsRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(OperationsGrpc.getListOperationsMethodHelper(), getCallOptions()), listOperationsRequest);
        }

        public ListenableFuture<Operation> getOperation(GetOperationRequest getOperationRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(OperationsGrpc.getGetOperationMethodHelper(), getCallOptions()), getOperationRequest);
        }

        public ListenableFuture<Empty> deleteOperation(DeleteOperationRequest deleteOperationRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(OperationsGrpc.getDeleteOperationMethodHelper(), getCallOptions()), deleteOperationRequest);
        }

        public ListenableFuture<Empty> cancelOperation(CancelOperationRequest cancelOperationRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(OperationsGrpc.getCancelOperationMethodHelper(), getCallOptions()), cancelOperationRequest);
        }
    }

    public static abstract class OperationsImplBase implements BindableService {
        public void listOperations(ListOperationsRequest listOperationsRequest, StreamObserver<ListOperationsResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(OperationsGrpc.getListOperationsMethodHelper(), streamObserver);
        }

        public void getOperation(GetOperationRequest getOperationRequest, StreamObserver<Operation> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(OperationsGrpc.getGetOperationMethodHelper(), streamObserver);
        }

        public void deleteOperation(DeleteOperationRequest deleteOperationRequest, StreamObserver<Empty> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(OperationsGrpc.getDeleteOperationMethodHelper(), streamObserver);
        }

        public void cancelOperation(CancelOperationRequest cancelOperationRequest, StreamObserver<Empty> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(OperationsGrpc.getCancelOperationMethodHelper(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(OperationsGrpc.getServiceDescriptor()).addMethod(OperationsGrpc.getListOperationsMethodHelper(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).addMethod(OperationsGrpc.getGetOperationMethodHelper(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 1))).addMethod(OperationsGrpc.getDeleteOperationMethodHelper(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 2))).addMethod(OperationsGrpc.getCancelOperationMethodHelper(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 3))).build();
        }
    }

    private static final class OperationsMethodDescriptorSupplier extends OperationsBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        OperationsMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }

    public static final class OperationsStub extends AbstractStub<OperationsStub> {
        private OperationsStub(Channel channel) {
            super(channel);
        }

        private OperationsStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public OperationsStub build(Channel channel, CallOptions callOptions) {
            return new OperationsStub(channel, callOptions);
        }

        public void listOperations(ListOperationsRequest listOperationsRequest, StreamObserver<ListOperationsResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(OperationsGrpc.getListOperationsMethodHelper(), getCallOptions()), listOperationsRequest, streamObserver);
        }

        public void getOperation(GetOperationRequest getOperationRequest, StreamObserver<Operation> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(OperationsGrpc.getGetOperationMethodHelper(), getCallOptions()), getOperationRequest, streamObserver);
        }

        public void deleteOperation(DeleteOperationRequest deleteOperationRequest, StreamObserver<Empty> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(OperationsGrpc.getDeleteOperationMethodHelper(), getCallOptions()), deleteOperationRequest, streamObserver);
        }

        public void cancelOperation(CancelOperationRequest cancelOperationRequest, StreamObserver<Empty> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(OperationsGrpc.getCancelOperationMethodHelper(), getCallOptions()), cancelOperationRequest, streamObserver);
        }
    }

    private OperationsGrpc() {
    }

    @ExperimentalApi
    public static MethodDescriptor<ListOperationsRequest, ListOperationsResponse> getListOperationsMethod() {
        return getListOperationsMethodHelper();
    }

    /* access modifiers changed from: private */
    public static MethodDescriptor<ListOperationsRequest, ListOperationsResponse> getListOperationsMethodHelper() {
        MethodDescriptor<ListOperationsRequest, ListOperationsResponse> methodDescriptor = getListOperationsMethod;
        if (methodDescriptor == null) {
            synchronized (OperationsGrpc.class) {
                methodDescriptor = getListOperationsMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ListOperations")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ListOperationsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListOperationsResponse.getDefaultInstance())).setSchemaDescriptor(new OperationsMethodDescriptorSupplier("ListOperations")).build();
                    getListOperationsMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @ExperimentalApi
    public static MethodDescriptor<GetOperationRequest, Operation> getGetOperationMethod() {
        return getGetOperationMethodHelper();
    }

    /* access modifiers changed from: private */
    public static MethodDescriptor<GetOperationRequest, Operation> getGetOperationMethodHelper() {
        MethodDescriptor<GetOperationRequest, Operation> methodDescriptor = getGetOperationMethod;
        if (methodDescriptor == null) {
            synchronized (OperationsGrpc.class) {
                methodDescriptor = getGetOperationMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetOperation")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GetOperationRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).setSchemaDescriptor(new OperationsMethodDescriptorSupplier("GetOperation")).build();
                    getGetOperationMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @ExperimentalApi
    public static MethodDescriptor<DeleteOperationRequest, Empty> getDeleteOperationMethod() {
        return getDeleteOperationMethodHelper();
    }

    /* access modifiers changed from: private */
    public static MethodDescriptor<DeleteOperationRequest, Empty> getDeleteOperationMethodHelper() {
        MethodDescriptor<DeleteOperationRequest, Empty> methodDescriptor = getDeleteOperationMethod;
        if (methodDescriptor == null) {
            synchronized (OperationsGrpc.class) {
                methodDescriptor = getDeleteOperationMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "DeleteOperation")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(DeleteOperationRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).setSchemaDescriptor(new OperationsMethodDescriptorSupplier("DeleteOperation")).build();
                    getDeleteOperationMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @ExperimentalApi
    public static MethodDescriptor<CancelOperationRequest, Empty> getCancelOperationMethod() {
        return getCancelOperationMethodHelper();
    }

    /* access modifiers changed from: private */
    public static MethodDescriptor<CancelOperationRequest, Empty> getCancelOperationMethodHelper() {
        MethodDescriptor<CancelOperationRequest, Empty> methodDescriptor = getCancelOperationMethod;
        if (methodDescriptor == null) {
            synchronized (OperationsGrpc.class) {
                methodDescriptor = getCancelOperationMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CancelOperation")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(CancelOperationRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).setSchemaDescriptor(new OperationsMethodDescriptorSupplier("CancelOperation")).build();
                    getCancelOperationMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static OperationsStub newStub(Channel channel) {
        return new OperationsStub(channel);
    }

    public static OperationsBlockingStub newBlockingStub(Channel channel) {
        return new OperationsBlockingStub(channel);
    }

    public static OperationsFutureStub newFutureStub(Channel channel) {
        return new OperationsFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (OperationsGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new OperationsFileDescriptorSupplier()).addMethod(getListOperationsMethodHelper()).addMethod(getGetOperationMethodHelper()).addMethod(getDeleteOperationMethodHelper()).addMethod(getCancelOperationMethodHelper()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
