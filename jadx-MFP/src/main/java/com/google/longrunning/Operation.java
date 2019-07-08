package com.google.longrunning;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.Internal;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Operation extends GeneratedMessageV3 implements OperationOrBuilder {
    private static final Operation DEFAULT_INSTANCE = new Operation();
    public static final int DONE_FIELD_NUMBER = 3;
    public static final int ERROR_FIELD_NUMBER = 4;
    public static final int METADATA_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Parser<Operation> PARSER = new AbstractParser<Operation>() {
        public Operation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Operation(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int RESPONSE_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public boolean done_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public Any metadata_;
    /* access modifiers changed from: private */
    public volatile Object name_;
    /* access modifiers changed from: private */
    public int resultCase_;
    /* access modifiers changed from: private */
    public Object result_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements OperationOrBuilder {
        private boolean done_;
        private SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> errorBuilder_;
        private SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> metadataBuilder_;
        private Any metadata_;
        private Object name_;
        private SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> responseBuilder_;
        private int resultCase_;
        private Object result_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return OperationsProto.internal_static_google_longrunning_Operation_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return OperationsProto.internal_static_google_longrunning_Operation_fieldAccessorTable.ensureFieldAccessorsInitialized(Operation.class, Builder.class);
        }

        private Builder() {
            this.resultCase_ = 0;
            this.name_ = "";
            this.metadata_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.resultCase_ = 0;
            this.name_ = "";
            this.metadata_ = null;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            Operation.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            this.done_ = false;
            this.resultCase_ = 0;
            this.result_ = null;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return OperationsProto.internal_static_google_longrunning_Operation_descriptor;
        }

        public Operation getDefaultInstanceForType() {
            return Operation.getDefaultInstance();
        }

        public Operation build() {
            Operation buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Operation buildPartial() {
            Operation operation = new Operation((com.google.protobuf.GeneratedMessageV3.Builder) this);
            operation.name_ = this.name_;
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                operation.metadata_ = this.metadata_;
            } else {
                operation.metadata_ = (Any) singleFieldBuilderV3.build();
            }
            operation.done_ = this.done_;
            if (this.resultCase_ == 4) {
                SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> singleFieldBuilderV32 = this.errorBuilder_;
                if (singleFieldBuilderV32 == null) {
                    operation.result_ = this.result_;
                } else {
                    operation.result_ = singleFieldBuilderV32.build();
                }
            }
            if (this.resultCase_ == 5) {
                SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV33 = this.responseBuilder_;
                if (singleFieldBuilderV33 == null) {
                    operation.result_ = this.result_;
                } else {
                    operation.result_ = singleFieldBuilderV33.build();
                }
            }
            operation.resultCase_ = this.resultCase_;
            onBuilt();
            return operation;
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
            if (message instanceof Operation) {
                return mergeFrom((Operation) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Operation operation) {
            if (operation == Operation.getDefaultInstance()) {
                return this;
            }
            if (!operation.getName().isEmpty()) {
                this.name_ = operation.name_;
                onChanged();
            }
            if (operation.hasMetadata()) {
                mergeMetadata(operation.getMetadata());
            }
            if (operation.getDone()) {
                setDone(operation.getDone());
            }
            switch (operation.getResultCase()) {
                case ERROR:
                    mergeError(operation.getError());
                    break;
                case RESPONSE:
                    mergeResponse(operation.getResponse());
                    break;
            }
            mergeUnknownFields(operation.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.longrunning.Operation.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.longrunning.Operation.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.longrunning.Operation r3 = (com.google.longrunning.Operation) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.longrunning.Operation r4 = (com.google.longrunning.Operation) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.longrunning.Operation.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.longrunning.Operation$Builder");
        }

        public ResultCase getResultCase() {
            return ResultCase.forNumber(this.resultCase_);
        }

        public Builder clearResult() {
            this.resultCase_ = 0;
            this.result_ = null;
            onChanged();
            return this;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setName(String str) {
            if (str != null) {
                this.name_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearName() {
            this.name_ = Operation.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                Operation.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public boolean hasMetadata() {
            return (this.metadataBuilder_ == null && this.metadata_ == null) ? false : true;
        }

        public Any getMetadata() {
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Any) singleFieldBuilderV3.getMessage();
            }
            Any any = this.metadata_;
            if (any == null) {
                any = Any.getDefaultInstance();
            }
            return any;
        }

        public Builder setMetadata(Any any) {
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(any);
            } else if (any != null) {
                this.metadata_ = any;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setMetadata(com.google.protobuf.Any.Builder builder) {
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.metadata_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeMetadata(Any any) {
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Any any2 = this.metadata_;
                if (any2 != null) {
                    this.metadata_ = Any.newBuilder(any2).mergeFrom(any).buildPartial();
                } else {
                    this.metadata_ = any;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(any);
            }
            return this;
        }

        public Builder clearMetadata() {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
                onChanged();
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        public com.google.protobuf.Any.Builder getMetadataBuilder() {
            onChanged();
            return (com.google.protobuf.Any.Builder) getMetadataFieldBuilder().getBuilder();
        }

        public AnyOrBuilder getMetadataOrBuilder() {
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (AnyOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Any any = this.metadata_;
            if (any == null) {
                any = Any.getDefaultInstance();
            }
            return any;
        }

        private SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> getMetadataFieldBuilder() {
            if (this.metadataBuilder_ == null) {
                this.metadataBuilder_ = new SingleFieldBuilderV3<>(getMetadata(), getParentForChildren(), isClean());
                this.metadata_ = null;
            }
            return this.metadataBuilder_;
        }

        public boolean getDone() {
            return this.done_;
        }

        public Builder setDone(boolean z) {
            this.done_ = z;
            onChanged();
            return this;
        }

        public Builder clearDone() {
            this.done_ = false;
            onChanged();
            return this;
        }

        public boolean hasError() {
            return this.resultCase_ == 4;
        }

        public Status getError() {
            SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.resultCase_ == 4) {
                    return (Status) this.result_;
                }
                return Status.getDefaultInstance();
            } else if (this.resultCase_ == 4) {
                return (Status) singleFieldBuilderV3.getMessage();
            } else {
                return Status.getDefaultInstance();
            }
        }

        public Builder setError(Status status) {
            SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(status);
            } else if (status != null) {
                this.result_ = status;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.resultCase_ = 4;
            return this;
        }

        public Builder setError(com.google.rpc.Status.Builder builder) {
            SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.result_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.resultCase_ = 4;
            return this;
        }

        public Builder mergeError(Status status) {
            SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.resultCase_ != 4 || this.result_ == Status.getDefaultInstance()) {
                    this.result_ = status;
                } else {
                    this.result_ = Status.newBuilder((Status) this.result_).mergeFrom(status).buildPartial();
                }
                onChanged();
            } else {
                if (this.resultCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(status);
                }
                this.errorBuilder_.setMessage(status);
            }
            this.resultCase_ = 4;
            return this;
        }

        public Builder clearError() {
            if (this.errorBuilder_ != null) {
                if (this.resultCase_ == 4) {
                    this.resultCase_ = 0;
                    this.result_ = null;
                }
                this.errorBuilder_.clear();
            } else if (this.resultCase_ == 4) {
                this.resultCase_ = 0;
                this.result_ = null;
                onChanged();
            }
            return this;
        }

        public com.google.rpc.Status.Builder getErrorBuilder() {
            return (com.google.rpc.Status.Builder) getErrorFieldBuilder().getBuilder();
        }

        public StatusOrBuilder getErrorOrBuilder() {
            if (this.resultCase_ == 4) {
                SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (StatusOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
            }
            if (this.resultCase_ == 4) {
                return (Status) this.result_;
            }
            return Status.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> getErrorFieldBuilder() {
            if (this.errorBuilder_ == null) {
                if (this.resultCase_ != 4) {
                    this.result_ = Status.getDefaultInstance();
                }
                this.errorBuilder_ = new SingleFieldBuilderV3<>((Status) this.result_, getParentForChildren(), isClean());
                this.result_ = null;
            }
            this.resultCase_ = 4;
            onChanged();
            return this.errorBuilder_;
        }

        public boolean hasResponse() {
            return this.resultCase_ == 5;
        }

        public Any getResponse() {
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.resultCase_ == 5) {
                    return (Any) this.result_;
                }
                return Any.getDefaultInstance();
            } else if (this.resultCase_ == 5) {
                return (Any) singleFieldBuilderV3.getMessage();
            } else {
                return Any.getDefaultInstance();
            }
        }

        public Builder setResponse(Any any) {
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(any);
            } else if (any != null) {
                this.result_ = any;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.resultCase_ = 5;
            return this;
        }

        public Builder setResponse(com.google.protobuf.Any.Builder builder) {
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.result_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.resultCase_ = 5;
            return this;
        }

        public Builder mergeResponse(Any any) {
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.resultCase_ != 5 || this.result_ == Any.getDefaultInstance()) {
                    this.result_ = any;
                } else {
                    this.result_ = Any.newBuilder((Any) this.result_).mergeFrom(any).buildPartial();
                }
                onChanged();
            } else {
                if (this.resultCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(any);
                }
                this.responseBuilder_.setMessage(any);
            }
            this.resultCase_ = 5;
            return this;
        }

        public Builder clearResponse() {
            if (this.responseBuilder_ != null) {
                if (this.resultCase_ == 5) {
                    this.resultCase_ = 0;
                    this.result_ = null;
                }
                this.responseBuilder_.clear();
            } else if (this.resultCase_ == 5) {
                this.resultCase_ = 0;
                this.result_ = null;
                onChanged();
            }
            return this;
        }

        public com.google.protobuf.Any.Builder getResponseBuilder() {
            return (com.google.protobuf.Any.Builder) getResponseFieldBuilder().getBuilder();
        }

        public AnyOrBuilder getResponseOrBuilder() {
            if (this.resultCase_ == 5) {
                SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (AnyOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
            }
            if (this.resultCase_ == 5) {
                return (Any) this.result_;
            }
            return Any.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> getResponseFieldBuilder() {
            if (this.responseBuilder_ == null) {
                if (this.resultCase_ != 5) {
                    this.result_ = Any.getDefaultInstance();
                }
                this.responseBuilder_ = new SingleFieldBuilderV3<>((Any) this.result_, getParentForChildren(), isClean());
                this.result_ = null;
            }
            this.resultCase_ = 5;
            onChanged();
            return this.responseBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public enum ResultCase implements EnumLite {
        ERROR(4),
        RESPONSE(5),
        RESULT_NOT_SET(0);
        
        private final int value;

        private ResultCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ResultCase valueOf(int i) {
            return forNumber(i);
        }

        public static ResultCase forNumber(int i) {
            if (i == 0) {
                return RESULT_NOT_SET;
            }
            switch (i) {
                case 4:
                    return ERROR;
                case 5:
                    return RESPONSE;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    private Operation(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.resultCase_ = 0;
        this.memoizedIsInitialized = -1;
    }

    private Operation() {
        this.resultCase_ = 0;
        this.memoizedIsInitialized = -1;
        this.name_ = "";
        this.done_ = false;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r5v1, types: [com.google.protobuf.Any$Builder] */
    /* JADX WARNING: type inference failed for: r5v2, types: [com.google.protobuf.Any$Builder] */
    /* JADX WARNING: type inference failed for: r5v3, types: [com.google.rpc.Status$Builder] */
    /* JADX WARNING: type inference failed for: r5v4, types: [com.google.rpc.Status$Builder] */
    /* JADX WARNING: type inference failed for: r5v5, types: [com.google.protobuf.Any$Builder] */
    /* JADX WARNING: type inference failed for: r5v6, types: [com.google.protobuf.Any$Builder] */
    /* JADX WARNING: type inference failed for: r5v7 */
    /* JADX WARNING: type inference failed for: r5v8 */
    /* JADX WARNING: type inference failed for: r5v9 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r5v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.rpc.Status$Builder, com.google.protobuf.Any$Builder]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], com.google.protobuf.Any$Builder, com.google.rpc.Status$Builder]
  mth insns count: 90
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Operation(com.google.protobuf.CodedInputStream r7, com.google.protobuf.ExtensionRegistryLite r8) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
            r6 = this;
            r6.<init>()
            if (r8 == 0) goto L_0x00e0
            com.google.protobuf.UnknownFieldSet$Builder r0 = com.google.protobuf.UnknownFieldSet.newBuilder()
            r1 = 0
        L_0x000a:
            if (r1 != 0) goto L_0x00d6
            int r2 = r7.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            r3 = 1
            if (r2 == 0) goto L_0x00b6
            r4 = 10
            if (r2 == r4) goto L_0x00ae
            r4 = 18
            r5 = 0
            if (r2 == r4) goto L_0x0089
            r4 = 24
            if (r2 == r4) goto L_0x0082
            r4 = 34
            if (r2 == r4) goto L_0x0059
            r4 = 42
            if (r2 == r4) goto L_0x0030
            boolean r2 = r6.parseUnknownFieldProto3(r7, r0, r8, r2)     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            if (r2 != 0) goto L_0x000a
            r1 = 1
            goto L_0x000a
        L_0x0030:
            int r2 = r6.resultCase_     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            r3 = 5
            if (r2 != r3) goto L_0x003d
            java.lang.Object r2 = r6.result_     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            com.google.protobuf.Any r2 = (com.google.protobuf.Any) r2     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            com.google.protobuf.Any$Builder r5 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
        L_0x003d:
            com.google.protobuf.Parser r2 = com.google.protobuf.Any.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            com.google.protobuf.MessageLite r2 = r7.readMessage(r2, r8)     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            r6.result_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            if (r5 == 0) goto L_0x0056
            java.lang.Object r2 = r6.result_     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            com.google.protobuf.Any r2 = (com.google.protobuf.Any) r2     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            r5.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            com.google.protobuf.Any r2 = r5.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            r6.result_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
        L_0x0056:
            r6.resultCase_ = r3     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            goto L_0x000a
        L_0x0059:
            int r2 = r6.resultCase_     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            r3 = 4
            if (r2 != r3) goto L_0x0066
            java.lang.Object r2 = r6.result_     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            com.google.rpc.Status r2 = (com.google.rpc.Status) r2     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            com.google.rpc.Status$Builder r5 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
        L_0x0066:
            com.google.protobuf.Parser r2 = com.google.rpc.Status.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            com.google.protobuf.MessageLite r2 = r7.readMessage(r2, r8)     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            r6.result_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            if (r5 == 0) goto L_0x007f
            java.lang.Object r2 = r6.result_     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            com.google.rpc.Status r2 = (com.google.rpc.Status) r2     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            r5.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            com.google.rpc.Status r2 = r5.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            r6.result_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
        L_0x007f:
            r6.resultCase_ = r3     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            goto L_0x000a
        L_0x0082:
            boolean r2 = r7.readBool()     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            r6.done_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            goto L_0x000a
        L_0x0089:
            com.google.protobuf.Any r2 = r6.metadata_     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            if (r2 == 0) goto L_0x0093
            com.google.protobuf.Any r2 = r6.metadata_     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            com.google.protobuf.Any$Builder r5 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
        L_0x0093:
            com.google.protobuf.Parser r2 = com.google.protobuf.Any.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            com.google.protobuf.MessageLite r2 = r7.readMessage(r2, r8)     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            com.google.protobuf.Any r2 = (com.google.protobuf.Any) r2     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            r6.metadata_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            if (r5 == 0) goto L_0x000a
            com.google.protobuf.Any r2 = r6.metadata_     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            r5.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            com.google.protobuf.Any r2 = r5.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            r6.metadata_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            goto L_0x000a
        L_0x00ae:
            java.lang.String r2 = r7.readStringRequireUtf8()     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            r6.name_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x00c6, IOException -> 0x00bb }
            goto L_0x000a
        L_0x00b6:
            r1 = 1
            goto L_0x000a
        L_0x00b9:
            r7 = move-exception
            goto L_0x00cc
        L_0x00bb:
            r7 = move-exception
            com.google.protobuf.InvalidProtocolBufferException r8 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x00b9 }
            r8.<init>(r7)     // Catch:{ all -> 0x00b9 }
            com.google.protobuf.InvalidProtocolBufferException r7 = r8.setUnfinishedMessage(r6)     // Catch:{ all -> 0x00b9 }
            throw r7     // Catch:{ all -> 0x00b9 }
        L_0x00c6:
            r7 = move-exception
            com.google.protobuf.InvalidProtocolBufferException r7 = r7.setUnfinishedMessage(r6)     // Catch:{ all -> 0x00b9 }
            throw r7     // Catch:{ all -> 0x00b9 }
        L_0x00cc:
            com.google.protobuf.UnknownFieldSet r8 = r0.build()
            r6.unknownFields = r8
            r6.makeExtensionsImmutable()
            throw r7
        L_0x00d6:
            com.google.protobuf.UnknownFieldSet r7 = r0.build()
            r6.unknownFields = r7
            r6.makeExtensionsImmutable()
            return
        L_0x00e0:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.longrunning.Operation.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public static final Descriptor getDescriptor() {
        return OperationsProto.internal_static_google_longrunning_Operation_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return OperationsProto.internal_static_google_longrunning_Operation_fieldAccessorTable.ensureFieldAccessorsInitialized(Operation.class, Builder.class);
    }

    public ResultCase getResultCase() {
        return ResultCase.forNumber(this.resultCase_);
    }

    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.name_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasMetadata() {
        return this.metadata_ != null;
    }

    public Any getMetadata() {
        Any any = this.metadata_;
        return any == null ? Any.getDefaultInstance() : any;
    }

    public AnyOrBuilder getMetadataOrBuilder() {
        return getMetadata();
    }

    public boolean getDone() {
        return this.done_;
    }

    public boolean hasError() {
        return this.resultCase_ == 4;
    }

    public Status getError() {
        if (this.resultCase_ == 4) {
            return (Status) this.result_;
        }
        return Status.getDefaultInstance();
    }

    public StatusOrBuilder getErrorOrBuilder() {
        if (this.resultCase_ == 4) {
            return (Status) this.result_;
        }
        return Status.getDefaultInstance();
    }

    public boolean hasResponse() {
        return this.resultCase_ == 5;
    }

    public Any getResponse() {
        if (this.resultCase_ == 5) {
            return (Any) this.result_;
        }
        return Any.getDefaultInstance();
    }

    public AnyOrBuilder getResponseOrBuilder() {
        if (this.resultCase_ == 5) {
            return (Any) this.result_;
        }
        return Any.getDefaultInstance();
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.metadata_ != null) {
            codedOutputStream.writeMessage(2, getMetadata());
        }
        boolean z = this.done_;
        if (z) {
            codedOutputStream.writeBool(3, z);
        }
        if (this.resultCase_ == 4) {
            codedOutputStream.writeMessage(4, (Status) this.result_);
        }
        if (this.resultCase_ == 5) {
            codedOutputStream.writeMessage(5, (Any) this.result_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getNameBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
        }
        if (this.metadata_ != null) {
            i2 += CodedOutputStream.computeMessageSize(2, getMetadata());
        }
        boolean z = this.done_;
        if (z) {
            i2 += CodedOutputStream.computeBoolSize(3, z);
        }
        if (this.resultCase_ == 4) {
            i2 += CodedOutputStream.computeMessageSize(4, (Status) this.result_);
        }
        if (this.resultCase_ == 5) {
            i2 += CodedOutputStream.computeMessageSize(5, (Any) this.result_);
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
        if (!(obj instanceof Operation)) {
            return super.equals(obj);
        }
        Operation operation = (Operation) obj;
        boolean z2 = (getName().equals(operation.getName())) && hasMetadata() == operation.hasMetadata();
        if (hasMetadata()) {
            z2 = z2 && getMetadata().equals(operation.getMetadata());
        }
        boolean z3 = (z2 && getDone() == operation.getDone()) && getResultCase().equals(operation.getResultCase());
        if (!z3) {
            return false;
        }
        switch (this.resultCase_) {
            case 4:
                if (z3 && getError().equals(operation.getError())) {
                    z3 = true;
                    break;
                } else {
                    z3 = false;
                    break;
                }
                break;
            case 5:
                if (z3 && getResponse().equals(operation.getResponse())) {
                    z3 = true;
                    break;
                } else {
                    z3 = false;
                    break;
                }
                break;
        }
        if (!z3 || !this.unknownFields.equals(operation.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (hasMetadata()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getMetadata().hashCode();
        }
        int hashBoolean = (((hashCode * 37) + 3) * 53) + Internal.hashBoolean(getDone());
        switch (this.resultCase_) {
            case 4:
                hashBoolean = (((hashBoolean * 37) + 4) * 53) + getError().hashCode();
                break;
            case 5:
                hashBoolean = (((hashBoolean * 37) + 5) * 53) + getResponse().hashCode();
                break;
        }
        int hashCode2 = (hashBoolean * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Operation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Operation) PARSER.parseFrom(byteBuffer);
    }

    public static Operation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Operation) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Operation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Operation) PARSER.parseFrom(byteString);
    }

    public static Operation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Operation) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Operation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Operation) PARSER.parseFrom(bArr);
    }

    public static Operation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Operation) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Operation parseFrom(InputStream inputStream) throws IOException {
        return (Operation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Operation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Operation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Operation parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Operation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Operation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Operation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Operation parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Operation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Operation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Operation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Operation operation) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(operation);
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

    public static Operation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Operation> parser() {
        return PARSER;
    }

    public Parser<Operation> getParserForType() {
        return PARSER;
    }

    public Operation getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
