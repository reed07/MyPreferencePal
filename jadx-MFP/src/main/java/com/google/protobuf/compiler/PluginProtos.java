package com.google.protobuf.compiler;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.DescriptorProtos.FileDescriptorProto;
import com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PluginProtos {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    /* access modifiers changed from: private */
    public static final FieldAccessorTable internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor, new String[]{"FileToGenerate", "Parameter", "ProtoFile", "CompilerVersion"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor = ((Descriptor) internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor.getNestedTypes().get(0));
    /* access modifiers changed from: private */
    public static final FieldAccessorTable internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor, new String[]{"Name", "InsertionPoint", "Content"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    /* access modifiers changed from: private */
    public static final FieldAccessorTable internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor, new String[]{"Error", "File"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_google_protobuf_compiler_Version_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    /* access modifiers changed from: private */
    public static final FieldAccessorTable internal_static_google_protobuf_compiler_Version_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_compiler_Version_descriptor, new String[]{"Major", "Minor", "Patch", "Suffix"});

    public static final class CodeGeneratorRequest extends GeneratedMessageV3 implements CodeGeneratorRequestOrBuilder {
        public static final int COMPILER_VERSION_FIELD_NUMBER = 3;
        private static final CodeGeneratorRequest DEFAULT_INSTANCE = new CodeGeneratorRequest();
        public static final int FILE_TO_GENERATE_FIELD_NUMBER = 1;
        public static final int PARAMETER_FIELD_NUMBER = 2;
        @Deprecated
        public static final Parser<CodeGeneratorRequest> PARSER = new AbstractParser<CodeGeneratorRequest>() {
            public CodeGeneratorRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CodeGeneratorRequest(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PROTO_FILE_FIELD_NUMBER = 15;
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public Version compilerVersion_;
        /* access modifiers changed from: private */
        public LazyStringList fileToGenerate_;
        private byte memoizedIsInitialized;
        /* access modifiers changed from: private */
        public volatile Object parameter_;
        /* access modifiers changed from: private */
        public List<FileDescriptorProto> protoFile_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements CodeGeneratorRequestOrBuilder {
            private int bitField0_;
            private SingleFieldBuilderV3<Version, Builder, VersionOrBuilder> compilerVersionBuilder_;
            private Version compilerVersion_;
            private LazyStringList fileToGenerate_;
            private Object parameter_;
            private RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> protoFileBuilder_;
            private List<FileDescriptorProto> protoFile_;

            public static final Descriptor getDescriptor() {
                return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(CodeGeneratorRequest.class, Builder.class);
            }

            private Builder() {
                this.fileToGenerate_ = LazyStringArrayList.EMPTY;
                this.parameter_ = "";
                this.protoFile_ = Collections.emptyList();
                this.compilerVersion_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.fileToGenerate_ = LazyStringArrayList.EMPTY;
                this.parameter_ = "";
                this.protoFile_ = Collections.emptyList();
                this.compilerVersion_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (CodeGeneratorRequest.alwaysUseFieldBuilders) {
                    getProtoFileFieldBuilder();
                    getCompilerVersionFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                this.fileToGenerate_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                this.parameter_ = "";
                this.bitField0_ &= -3;
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.protoFile_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                SingleFieldBuilderV3<Version, Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.compilerVersion_ = null;
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -9;
                return this;
            }

            public Descriptor getDescriptorForType() {
                return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
            }

            public CodeGeneratorRequest getDefaultInstanceForType() {
                return CodeGeneratorRequest.getDefaultInstance();
            }

            public CodeGeneratorRequest build() {
                CodeGeneratorRequest buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public CodeGeneratorRequest buildPartial() {
                CodeGeneratorRequest codeGeneratorRequest = new CodeGeneratorRequest((com.google.protobuf.GeneratedMessageV3.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) == 1) {
                    this.fileToGenerate_ = this.fileToGenerate_.getUnmodifiableView();
                    this.bitField0_ &= -2;
                }
                codeGeneratorRequest.fileToGenerate_ = this.fileToGenerate_;
                if ((i & 2) != 2) {
                    i2 = 0;
                }
                codeGeneratorRequest.parameter_ = this.parameter_;
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.protoFile_ = Collections.unmodifiableList(this.protoFile_);
                        this.bitField0_ &= -5;
                    }
                    codeGeneratorRequest.protoFile_ = this.protoFile_;
                } else {
                    codeGeneratorRequest.protoFile_ = repeatedFieldBuilderV3.build();
                }
                if ((i & 8) == 8) {
                    i2 |= 2;
                }
                SingleFieldBuilderV3<Version, Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    codeGeneratorRequest.compilerVersion_ = this.compilerVersion_;
                } else {
                    codeGeneratorRequest.compilerVersion_ = (Version) singleFieldBuilderV3.build();
                }
                codeGeneratorRequest.bitField0_ = i2;
                onBuilt();
                return codeGeneratorRequest;
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
                if (message instanceof CodeGeneratorRequest) {
                    return mergeFrom((CodeGeneratorRequest) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CodeGeneratorRequest codeGeneratorRequest) {
                if (codeGeneratorRequest == CodeGeneratorRequest.getDefaultInstance()) {
                    return this;
                }
                if (!codeGeneratorRequest.fileToGenerate_.isEmpty()) {
                    if (this.fileToGenerate_.isEmpty()) {
                        this.fileToGenerate_ = codeGeneratorRequest.fileToGenerate_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureFileToGenerateIsMutable();
                        this.fileToGenerate_.addAll(codeGeneratorRequest.fileToGenerate_);
                    }
                    onChanged();
                }
                if (codeGeneratorRequest.hasParameter()) {
                    this.bitField0_ |= 2;
                    this.parameter_ = codeGeneratorRequest.parameter_;
                    onChanged();
                }
                if (this.protoFileBuilder_ == null) {
                    if (!codeGeneratorRequest.protoFile_.isEmpty()) {
                        if (this.protoFile_.isEmpty()) {
                            this.protoFile_ = codeGeneratorRequest.protoFile_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureProtoFileIsMutable();
                            this.protoFile_.addAll(codeGeneratorRequest.protoFile_);
                        }
                        onChanged();
                    }
                } else if (!codeGeneratorRequest.protoFile_.isEmpty()) {
                    if (this.protoFileBuilder_.isEmpty()) {
                        this.protoFileBuilder_.dispose();
                        RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = null;
                        this.protoFileBuilder_ = null;
                        this.protoFile_ = codeGeneratorRequest.protoFile_;
                        this.bitField0_ &= -5;
                        if (CodeGeneratorRequest.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getProtoFileFieldBuilder();
                        }
                        this.protoFileBuilder_ = repeatedFieldBuilderV3;
                    } else {
                        this.protoFileBuilder_.addAllMessages(codeGeneratorRequest.protoFile_);
                    }
                }
                if (codeGeneratorRequest.hasCompilerVersion()) {
                    mergeCompilerVersion(codeGeneratorRequest.getCompilerVersion());
                }
                mergeUnknownFields(codeGeneratorRequest.unknownFields);
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getProtoFileCount(); i++) {
                    if (!getProtoFile(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequest.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.google.protobuf.compiler.PluginProtos$CodeGeneratorRequest> r1 = com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequest.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0011 }
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0011 }
                    com.google.protobuf.compiler.PluginProtos$CodeGeneratorRequest r3 = (com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequest) r3     // Catch:{ InvalidProtocolBufferException -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.mergeFrom(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.compiler.PluginProtos$CodeGeneratorRequest r4 = (com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequest) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.mergeFrom(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequest.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.compiler.PluginProtos$CodeGeneratorRequest$Builder");
            }

            private void ensureFileToGenerateIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.fileToGenerate_ = new LazyStringArrayList(this.fileToGenerate_);
                    this.bitField0_ |= 1;
                }
            }

            public ProtocolStringList getFileToGenerateList() {
                return this.fileToGenerate_.getUnmodifiableView();
            }

            public int getFileToGenerateCount() {
                return this.fileToGenerate_.size();
            }

            public String getFileToGenerate(int i) {
                return (String) this.fileToGenerate_.get(i);
            }

            public ByteString getFileToGenerateBytes(int i) {
                return this.fileToGenerate_.getByteString(i);
            }

            public Builder setFileToGenerate(int i, String str) {
                if (str != null) {
                    ensureFileToGenerateIsMutable();
                    this.fileToGenerate_.set(i, str);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addFileToGenerate(String str) {
                if (str != null) {
                    ensureFileToGenerateIsMutable();
                    this.fileToGenerate_.add(str);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addAllFileToGenerate(Iterable<String> iterable) {
                ensureFileToGenerateIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, (List<? super T>) this.fileToGenerate_);
                onChanged();
                return this;
            }

            public Builder clearFileToGenerate() {
                this.fileToGenerate_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                onChanged();
                return this;
            }

            public Builder addFileToGenerateBytes(ByteString byteString) {
                if (byteString != null) {
                    ensureFileToGenerateIsMutable();
                    this.fileToGenerate_.add(byteString);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public boolean hasParameter() {
                return (this.bitField0_ & 2) == 2;
            }

            public String getParameter() {
                Object obj = this.parameter_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.parameter_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getParameterBytes() {
                Object obj = this.parameter_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.parameter_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setParameter(String str) {
                if (str != null) {
                    this.bitField0_ |= 2;
                    this.parameter_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearParameter() {
                this.bitField0_ &= -3;
                this.parameter_ = CodeGeneratorRequest.getDefaultInstance().getParameter();
                onChanged();
                return this;
            }

            public Builder setParameterBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.parameter_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private void ensureProtoFileIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.protoFile_ = new ArrayList(this.protoFile_);
                    this.bitField0_ |= 4;
                }
            }

            public List<FileDescriptorProto> getProtoFileList() {
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.protoFile_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            public int getProtoFileCount() {
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.protoFile_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public FileDescriptorProto getProtoFile(int i) {
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return (FileDescriptorProto) this.protoFile_.get(i);
                }
                return (FileDescriptorProto) repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setProtoFile(int i, FileDescriptorProto fileDescriptorProto) {
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, fileDescriptorProto);
                } else if (fileDescriptorProto != null) {
                    ensureProtoFileIsMutable();
                    this.protoFile_.set(i, fileDescriptorProto);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setProtoFile(int i, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder builder) {
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtoFileIsMutable();
                    this.protoFile_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addProtoFile(FileDescriptorProto fileDescriptorProto) {
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(fileDescriptorProto);
                } else if (fileDescriptorProto != null) {
                    ensureProtoFileIsMutable();
                    this.protoFile_.add(fileDescriptorProto);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addProtoFile(int i, FileDescriptorProto fileDescriptorProto) {
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, fileDescriptorProto);
                } else if (fileDescriptorProto != null) {
                    ensureProtoFileIsMutable();
                    this.protoFile_.add(i, fileDescriptorProto);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addProtoFile(com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder builder) {
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtoFileIsMutable();
                    this.protoFile_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addProtoFile(int i, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder builder) {
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtoFileIsMutable();
                    this.protoFile_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllProtoFile(Iterable<? extends FileDescriptorProto> iterable) {
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtoFileIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.protoFile_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearProtoFile() {
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.protoFile_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeProtoFile(int i) {
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtoFileIsMutable();
                    this.protoFile_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder getProtoFileBuilder(int i) {
                return (com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder) getProtoFileFieldBuilder().getBuilder(i);
            }

            public FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int i) {
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return (FileDescriptorProtoOrBuilder) this.protoFile_.get(i);
                }
                return (FileDescriptorProtoOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            public List<? extends FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList() {
                RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.protoFile_);
            }

            public com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder addProtoFileBuilder() {
                return (com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder) getProtoFileFieldBuilder().addBuilder(FileDescriptorProto.getDefaultInstance());
            }

            public com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder addProtoFileBuilder(int i) {
                return (com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder) getProtoFileFieldBuilder().addBuilder(i, FileDescriptorProto.getDefaultInstance());
            }

            public List<com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder> getProtoFileBuilderList() {
                return getProtoFileFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<FileDescriptorProto, com.google.protobuf.DescriptorProtos.FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> getProtoFileFieldBuilder() {
                if (this.protoFileBuilder_ == null) {
                    this.protoFileBuilder_ = new RepeatedFieldBuilderV3<>(this.protoFile_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                    this.protoFile_ = null;
                }
                return this.protoFileBuilder_;
            }

            public boolean hasCompilerVersion() {
                return (this.bitField0_ & 8) == 8;
            }

            public Version getCompilerVersion() {
                SingleFieldBuilderV3<Version, Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (Version) singleFieldBuilderV3.getMessage();
                }
                Version version = this.compilerVersion_;
                if (version == null) {
                    version = Version.getDefaultInstance();
                }
                return version;
            }

            public Builder setCompilerVersion(Version version) {
                SingleFieldBuilderV3<Version, Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(version);
                } else if (version != null) {
                    this.compilerVersion_ = version;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setCompilerVersion(Builder builder) {
                SingleFieldBuilderV3<Version, Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.compilerVersion_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeCompilerVersion(Version version) {
                SingleFieldBuilderV3<Version, Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 8) == 8) {
                        Version version2 = this.compilerVersion_;
                        if (!(version2 == null || version2 == Version.getDefaultInstance())) {
                            this.compilerVersion_ = Version.newBuilder(this.compilerVersion_).mergeFrom(version).buildPartial();
                            onChanged();
                        }
                    }
                    this.compilerVersion_ = version;
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(version);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder clearCompilerVersion() {
                SingleFieldBuilderV3<Version, Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.compilerVersion_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -9;
                return this;
            }

            public Builder getCompilerVersionBuilder() {
                this.bitField0_ |= 8;
                onChanged();
                return (Builder) getCompilerVersionFieldBuilder().getBuilder();
            }

            public VersionOrBuilder getCompilerVersionOrBuilder() {
                SingleFieldBuilderV3<Version, Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (VersionOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Version version = this.compilerVersion_;
                if (version == null) {
                    version = Version.getDefaultInstance();
                }
                return version;
            }

            private SingleFieldBuilderV3<Version, Builder, VersionOrBuilder> getCompilerVersionFieldBuilder() {
                if (this.compilerVersionBuilder_ == null) {
                    this.compilerVersionBuilder_ = new SingleFieldBuilderV3<>(getCompilerVersion(), getParentForChildren(), isClean());
                    this.compilerVersion_ = null;
                }
                return this.compilerVersionBuilder_;
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        private CodeGeneratorRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private CodeGeneratorRequest() {
            this.memoizedIsInitialized = -1;
            this.fileToGenerate_ = LazyStringArrayList.EMPTY;
            this.parameter_ = "";
            this.protoFile_ = Collections.emptyList();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CodeGeneratorRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag == 0) {
                            z = true;
                        } else if (readTag == 10) {
                            ByteString readBytes = codedInputStream.readBytes();
                            if (!z2 || !true) {
                                this.fileToGenerate_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.fileToGenerate_.add(readBytes);
                        } else if (readTag == 18) {
                            ByteString readBytes2 = codedInputStream.readBytes();
                            this.bitField0_ |= 1;
                            this.parameter_ = readBytes2;
                        } else if (readTag == 26) {
                            Builder builder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                builder = this.compilerVersion_.toBuilder();
                            }
                            this.compilerVersion_ = (Version) codedInputStream.readMessage(Version.PARSER, extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.compilerVersion_);
                                this.compilerVersion_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (readTag == 122) {
                            if (!(z2 & true)) {
                                this.protoFile_ = new ArrayList();
                                z2 |= true;
                            }
                            this.protoFile_.add(codedInputStream.readMessage(FileDescriptorProto.PARSER, extensionRegistryLite));
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            z = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 && true) {
                            this.fileToGenerate_ = this.fileToGenerate_.getUnmodifiableView();
                        }
                        if (z2 & true) {
                            this.protoFile_ = Collections.unmodifiableList(this.protoFile_);
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 && true) {
                    this.fileToGenerate_ = this.fileToGenerate_.getUnmodifiableView();
                }
                if (z2 & true) {
                    this.protoFile_ = Collections.unmodifiableList(this.protoFile_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
                return;
            }
            throw new NullPointerException();
        }

        public static final Descriptor getDescriptor() {
            return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(CodeGeneratorRequest.class, Builder.class);
        }

        public ProtocolStringList getFileToGenerateList() {
            return this.fileToGenerate_;
        }

        public int getFileToGenerateCount() {
            return this.fileToGenerate_.size();
        }

        public String getFileToGenerate(int i) {
            return (String) this.fileToGenerate_.get(i);
        }

        public ByteString getFileToGenerateBytes(int i) {
            return this.fileToGenerate_.getByteString(i);
        }

        public boolean hasParameter() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getParameter() {
            Object obj = this.parameter_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.parameter_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getParameterBytes() {
            Object obj = this.parameter_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parameter_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public List<FileDescriptorProto> getProtoFileList() {
            return this.protoFile_;
        }

        public List<? extends FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList() {
            return this.protoFile_;
        }

        public int getProtoFileCount() {
            return this.protoFile_.size();
        }

        public FileDescriptorProto getProtoFile(int i) {
            return (FileDescriptorProto) this.protoFile_.get(i);
        }

        public FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int i) {
            return (FileDescriptorProtoOrBuilder) this.protoFile_.get(i);
        }

        public boolean hasCompilerVersion() {
            return (this.bitField0_ & 2) == 2;
        }

        public Version getCompilerVersion() {
            Version version = this.compilerVersion_;
            return version == null ? Version.getDefaultInstance() : version;
        }

        public VersionOrBuilder getCompilerVersionOrBuilder() {
            Version version = this.compilerVersion_;
            return version == null ? Version.getDefaultInstance() : version;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getProtoFileCount(); i++) {
                if (!getProtoFile(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.fileToGenerate_.size(); i++) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.fileToGenerate_.getRaw(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.parameter_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(3, getCompilerVersion());
            }
            for (int i2 = 0; i2 < this.protoFile_.size(); i2++) {
                codedOutputStream.writeMessage(15, (MessageLite) this.protoFile_.get(i2));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.fileToGenerate_.size(); i3++) {
                i2 += computeStringSizeNoTag(this.fileToGenerate_.getRaw(i3));
            }
            int size = i2 + 0 + (getFileToGenerateList().size() * 1);
            if ((this.bitField0_ & 1) == 1) {
                size += GeneratedMessageV3.computeStringSize(2, this.parameter_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeMessageSize(3, getCompilerVersion());
            }
            for (int i4 = 0; i4 < this.protoFile_.size(); i4++) {
                size += CodedOutputStream.computeMessageSize(15, (MessageLite) this.protoFile_.get(i4));
            }
            int serializedSize = size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CodeGeneratorRequest)) {
                return super.equals(obj);
            }
            CodeGeneratorRequest codeGeneratorRequest = (CodeGeneratorRequest) obj;
            boolean z2 = (getFileToGenerateList().equals(codeGeneratorRequest.getFileToGenerateList())) && hasParameter() == codeGeneratorRequest.hasParameter();
            if (hasParameter()) {
                z2 = z2 && getParameter().equals(codeGeneratorRequest.getParameter());
            }
            boolean z3 = (z2 && getProtoFileList().equals(codeGeneratorRequest.getProtoFileList())) && hasCompilerVersion() == codeGeneratorRequest.hasCompilerVersion();
            if (hasCompilerVersion()) {
                z3 = z3 && getCompilerVersion().equals(codeGeneratorRequest.getCompilerVersion());
            }
            if (!z3 || !this.unknownFields.equals(codeGeneratorRequest.unknownFields)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (getFileToGenerateCount() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + getFileToGenerateList().hashCode();
            }
            if (hasParameter()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getParameter().hashCode();
            }
            if (getProtoFileCount() > 0) {
                hashCode = (((hashCode * 37) + 15) * 53) + getProtoFileList().hashCode();
            }
            if (hasCompilerVersion()) {
                hashCode = (((hashCode * 37) + 3) * 53) + getCompilerVersion().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static CodeGeneratorRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CodeGeneratorRequest) PARSER.parseFrom(byteBuffer);
        }

        public static CodeGeneratorRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CodeGeneratorRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CodeGeneratorRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CodeGeneratorRequest) PARSER.parseFrom(byteString);
        }

        public static CodeGeneratorRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CodeGeneratorRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CodeGeneratorRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CodeGeneratorRequest) PARSER.parseFrom(bArr);
        }

        public static CodeGeneratorRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CodeGeneratorRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CodeGeneratorRequest parseFrom(InputStream inputStream) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CodeGeneratorRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CodeGeneratorRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CodeGeneratorRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CodeGeneratorRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CodeGeneratorRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CodeGeneratorRequest codeGeneratorRequest) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(codeGeneratorRequest);
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

        public static CodeGeneratorRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CodeGeneratorRequest> parser() {
            return PARSER;
        }

        public Parser<CodeGeneratorRequest> getParserForType() {
            return PARSER;
        }

        public CodeGeneratorRequest getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public interface CodeGeneratorRequestOrBuilder extends MessageOrBuilder {
        Version getCompilerVersion();

        VersionOrBuilder getCompilerVersionOrBuilder();

        String getFileToGenerate(int i);

        ByteString getFileToGenerateBytes(int i);

        int getFileToGenerateCount();

        List<String> getFileToGenerateList();

        String getParameter();

        ByteString getParameterBytes();

        FileDescriptorProto getProtoFile(int i);

        int getProtoFileCount();

        List<FileDescriptorProto> getProtoFileList();

        FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int i);

        List<? extends FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList();

        boolean hasCompilerVersion();

        boolean hasParameter();
    }

    public static final class CodeGeneratorResponse extends GeneratedMessageV3 implements CodeGeneratorResponseOrBuilder {
        private static final CodeGeneratorResponse DEFAULT_INSTANCE = new CodeGeneratorResponse();
        public static final int ERROR_FIELD_NUMBER = 1;
        public static final int FILE_FIELD_NUMBER = 15;
        @Deprecated
        public static final Parser<CodeGeneratorResponse> PARSER = new AbstractParser<CodeGeneratorResponse>() {
            public CodeGeneratorResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CodeGeneratorResponse(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public volatile Object error_;
        /* access modifiers changed from: private */
        public List<File> file_;
        private byte memoizedIsInitialized;

        public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements CodeGeneratorResponseOrBuilder {
            private int bitField0_;
            private Object error_;
            private RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> fileBuilder_;
            private List<File> file_;

            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptor getDescriptor() {
                return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(CodeGeneratorResponse.class, Builder.class);
            }

            private Builder() {
                this.error_ = "";
                this.file_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.error_ = "";
                this.file_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (CodeGeneratorResponse.alwaysUseFieldBuilders) {
                    getFileFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                this.error_ = "";
                this.bitField0_ &= -2;
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.file_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Descriptor getDescriptorForType() {
                return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
            }

            public CodeGeneratorResponse getDefaultInstanceForType() {
                return CodeGeneratorResponse.getDefaultInstance();
            }

            public CodeGeneratorResponse build() {
                CodeGeneratorResponse buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public CodeGeneratorResponse buildPartial() {
                CodeGeneratorResponse codeGeneratorResponse = new CodeGeneratorResponse((com.google.protobuf.GeneratedMessageV3.Builder) this);
                int i = 1;
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                codeGeneratorResponse.error_ = this.error_;
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.file_ = Collections.unmodifiableList(this.file_);
                        this.bitField0_ &= -3;
                    }
                    codeGeneratorResponse.file_ = this.file_;
                } else {
                    codeGeneratorResponse.file_ = repeatedFieldBuilderV3.build();
                }
                codeGeneratorResponse.bitField0_ = i;
                onBuilt();
                return codeGeneratorResponse;
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
                if (message instanceof CodeGeneratorResponse) {
                    return mergeFrom((CodeGeneratorResponse) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CodeGeneratorResponse codeGeneratorResponse) {
                if (codeGeneratorResponse == CodeGeneratorResponse.getDefaultInstance()) {
                    return this;
                }
                if (codeGeneratorResponse.hasError()) {
                    this.bitField0_ |= 1;
                    this.error_ = codeGeneratorResponse.error_;
                    onChanged();
                }
                if (this.fileBuilder_ == null) {
                    if (!codeGeneratorResponse.file_.isEmpty()) {
                        if (this.file_.isEmpty()) {
                            this.file_ = codeGeneratorResponse.file_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureFileIsMutable();
                            this.file_.addAll(codeGeneratorResponse.file_);
                        }
                        onChanged();
                    }
                } else if (!codeGeneratorResponse.file_.isEmpty()) {
                    if (this.fileBuilder_.isEmpty()) {
                        this.fileBuilder_.dispose();
                        RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = null;
                        this.fileBuilder_ = null;
                        this.file_ = codeGeneratorResponse.file_;
                        this.bitField0_ &= -3;
                        if (CodeGeneratorResponse.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getFileFieldBuilder();
                        }
                        this.fileBuilder_ = repeatedFieldBuilderV3;
                    } else {
                        this.fileBuilder_.addAllMessages(codeGeneratorResponse.file_);
                    }
                }
                mergeUnknownFields(codeGeneratorResponse.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse> r1 = com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0011 }
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0011 }
                    com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse r3 = (com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse) r3     // Catch:{ InvalidProtocolBufferException -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.mergeFrom(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse r4 = (com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.mergeFrom(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$Builder");
            }

            public boolean hasError() {
                return (this.bitField0_ & 1) == 1;
            }

            public String getError() {
                Object obj = this.error_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.error_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getErrorBytes() {
                Object obj = this.error_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.error_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setError(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.error_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearError() {
                this.bitField0_ &= -2;
                this.error_ = CodeGeneratorResponse.getDefaultInstance().getError();
                onChanged();
                return this;
            }

            public Builder setErrorBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.error_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private void ensureFileIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.file_ = new ArrayList(this.file_);
                    this.bitField0_ |= 2;
                }
            }

            public List<File> getFileList() {
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.file_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            public int getFileCount() {
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.file_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public File getFile(int i) {
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return (File) this.file_.get(i);
                }
                return (File) repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setFile(int i, File file) {
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, file);
                } else if (file != null) {
                    ensureFileIsMutable();
                    this.file_.set(i, file);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setFile(int i, Builder builder) {
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFileIsMutable();
                    this.file_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addFile(File file) {
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(file);
                } else if (file != null) {
                    ensureFileIsMutable();
                    this.file_.add(file);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addFile(int i, File file) {
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, file);
                } else if (file != null) {
                    ensureFileIsMutable();
                    this.file_.add(i, file);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addFile(Builder builder) {
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFileIsMutable();
                    this.file_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addFile(int i, Builder builder) {
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFileIsMutable();
                    this.file_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllFile(Iterable<? extends File> iterable) {
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFileIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.file_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearFile() {
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.file_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeFile(int i) {
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFileIsMutable();
                    this.file_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Builder getFileBuilder(int i) {
                return (Builder) getFileFieldBuilder().getBuilder(i);
            }

            public FileOrBuilder getFileOrBuilder(int i) {
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return (FileOrBuilder) this.file_.get(i);
                }
                return (FileOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            public List<? extends FileOrBuilder> getFileOrBuilderList() {
                RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.file_);
            }

            public Builder addFileBuilder() {
                return (Builder) getFileFieldBuilder().addBuilder(File.getDefaultInstance());
            }

            public Builder addFileBuilder(int i) {
                return (Builder) getFileFieldBuilder().addBuilder(i, File.getDefaultInstance());
            }

            public List<Builder> getFileBuilderList() {
                return getFileFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<File, Builder, FileOrBuilder> getFileFieldBuilder() {
                if (this.fileBuilder_ == null) {
                    this.fileBuilder_ = new RepeatedFieldBuilderV3<>(this.file_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                    this.file_ = null;
                }
                return this.fileBuilder_;
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static final class File extends GeneratedMessageV3 implements FileOrBuilder {
            public static final int CONTENT_FIELD_NUMBER = 15;
            private static final File DEFAULT_INSTANCE = new File();
            public static final int INSERTION_POINT_FIELD_NUMBER = 2;
            public static final int NAME_FIELD_NUMBER = 1;
            @Deprecated
            public static final Parser<File> PARSER = new AbstractParser<File>() {
                public File parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new File(codedInputStream, extensionRegistryLite);
                }
            };
            private static final long serialVersionUID = 0;
            /* access modifiers changed from: private */
            public int bitField0_;
            /* access modifiers changed from: private */
            public volatile Object content_;
            /* access modifiers changed from: private */
            public volatile Object insertionPoint_;
            private byte memoizedIsInitialized;
            /* access modifiers changed from: private */
            public volatile Object name_;

            public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements FileOrBuilder {
                private int bitField0_;
                private Object content_;
                private Object insertionPoint_;
                private Object name_;

                public final boolean isInitialized() {
                    return true;
                }

                public static final Descriptor getDescriptor() {
                    return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor;
                }

                /* access modifiers changed from: protected */
                public FieldAccessorTable internalGetFieldAccessorTable() {
                    return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable.ensureFieldAccessorsInitialized(File.class, Builder.class);
                }

                private Builder() {
                    this.name_ = "";
                    this.insertionPoint_ = "";
                    this.content_ = "";
                    maybeForceBuilderInitialization();
                }

                private Builder(BuilderParent builderParent) {
                    super(builderParent);
                    this.name_ = "";
                    this.insertionPoint_ = "";
                    this.content_ = "";
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    File.alwaysUseFieldBuilders;
                }

                public Builder clear() {
                    super.clear();
                    this.name_ = "";
                    this.bitField0_ &= -2;
                    this.insertionPoint_ = "";
                    this.bitField0_ &= -3;
                    this.content_ = "";
                    this.bitField0_ &= -5;
                    return this;
                }

                public Descriptor getDescriptorForType() {
                    return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor;
                }

                public File getDefaultInstanceForType() {
                    return File.getDefaultInstance();
                }

                public File build() {
                    File buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public File buildPartial() {
                    File file = new File((com.google.protobuf.GeneratedMessageV3.Builder) this);
                    int i = this.bitField0_;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    file.name_ = this.name_;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    file.insertionPoint_ = this.insertionPoint_;
                    if ((i & 4) == 4) {
                        i2 |= 4;
                    }
                    file.content_ = this.content_;
                    file.bitField0_ = i2;
                    onBuilt();
                    return file;
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
                    if (message instanceof File) {
                        return mergeFrom((File) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(File file) {
                    if (file == File.getDefaultInstance()) {
                        return this;
                    }
                    if (file.hasName()) {
                        this.bitField0_ |= 1;
                        this.name_ = file.name_;
                        onChanged();
                    }
                    if (file.hasInsertionPoint()) {
                        this.bitField0_ |= 2;
                        this.insertionPoint_ = file.insertionPoint_;
                        onChanged();
                    }
                    if (file.hasContent()) {
                        this.bitField0_ |= 4;
                        this.content_ = file.content_;
                        onChanged();
                    }
                    mergeUnknownFields(file.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser<com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$File> r1 = com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0011 }
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0011 }
                        com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$File r3 = (com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File) r3     // Catch:{ InvalidProtocolBufferException -> 0x0011 }
                        if (r3 == 0) goto L_0x000e
                        r2.mergeFrom(r3)
                    L_0x000e:
                        return r2
                    L_0x000f:
                        r3 = move-exception
                        goto L_0x001f
                    L_0x0011:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch:{ all -> 0x000f }
                        com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$File r4 = (com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File) r4     // Catch:{ all -> 0x000f }
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch:{ all -> 0x001d }
                        throw r3     // Catch:{ all -> 0x001d }
                    L_0x001d:
                        r3 = move-exception
                        r0 = r4
                    L_0x001f:
                        if (r0 == 0) goto L_0x0024
                        r2.mergeFrom(r0)
                    L_0x0024:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$File$Builder");
                }

                public boolean hasName() {
                    return (this.bitField0_ & 1) == 1;
                }

                public String getName() {
                    Object obj = this.name_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.name_ = stringUtf8;
                    }
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
                        this.bitField0_ |= 1;
                        this.name_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder clearName() {
                    this.bitField0_ &= -2;
                    this.name_ = File.getDefaultInstance().getName();
                    onChanged();
                    return this;
                }

                public Builder setNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.bitField0_ |= 1;
                        this.name_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public boolean hasInsertionPoint() {
                    return (this.bitField0_ & 2) == 2;
                }

                public String getInsertionPoint() {
                    Object obj = this.insertionPoint_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.insertionPoint_ = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getInsertionPointBytes() {
                    Object obj = this.insertionPoint_;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.insertionPoint_ = copyFromUtf8;
                    return copyFromUtf8;
                }

                public Builder setInsertionPoint(String str) {
                    if (str != null) {
                        this.bitField0_ |= 2;
                        this.insertionPoint_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder clearInsertionPoint() {
                    this.bitField0_ &= -3;
                    this.insertionPoint_ = File.getDefaultInstance().getInsertionPoint();
                    onChanged();
                    return this;
                }

                public Builder setInsertionPointBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.bitField0_ |= 2;
                        this.insertionPoint_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public boolean hasContent() {
                    return (this.bitField0_ & 4) == 4;
                }

                public String getContent() {
                    Object obj = this.content_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.content_ = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getContentBytes() {
                    Object obj = this.content_;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.content_ = copyFromUtf8;
                    return copyFromUtf8;
                }

                public Builder setContent(String str) {
                    if (str != null) {
                        this.bitField0_ |= 4;
                        this.content_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder clearContent() {
                    this.bitField0_ &= -5;
                    this.content_ = File.getDefaultInstance().getContent();
                    onChanged();
                    return this;
                }

                public Builder setContentBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.bitField0_ |= 4;
                        this.content_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }

            private File(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = -1;
            }

            private File() {
                this.memoizedIsInitialized = -1;
                this.name_ = "";
                this.insertionPoint_ = "";
                this.content_ = "";
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private File(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistryLite != null) {
                    com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag == 0) {
                                z = true;
                            } else if (readTag == 10) {
                                ByteString readBytes = codedInputStream.readBytes();
                                this.bitField0_ = 1 | this.bitField0_;
                                this.name_ = readBytes;
                            } else if (readTag == 18) {
                                ByteString readBytes2 = codedInputStream.readBytes();
                                this.bitField0_ |= 2;
                                this.insertionPoint_ = readBytes2;
                            } else if (readTag == 122) {
                                ByteString readBytes3 = codedInputStream.readBytes();
                                this.bitField0_ |= 4;
                                this.content_ = readBytes3;
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                z = true;
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
                return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable.ensureFieldAccessorsInitialized(File.class, Builder.class);
            }

            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            public String getName() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.name_ = stringUtf8;
                }
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

            public boolean hasInsertionPoint() {
                return (this.bitField0_ & 2) == 2;
            }

            public String getInsertionPoint() {
                Object obj = this.insertionPoint_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.insertionPoint_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getInsertionPointBytes() {
                Object obj = this.insertionPoint_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.insertionPoint_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public boolean hasContent() {
                return (this.bitField0_ & 4) == 4;
            }

            public String getContent() {
                Object obj = this.content_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.content_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getContentBytes() {
                Object obj = this.content_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.content_ = copyFromUtf8;
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
                if ((this.bitField0_ & 1) == 1) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.insertionPoint_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    GeneratedMessageV3.writeString(codedOutputStream, 15, this.content_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if ((this.bitField0_ & 1) == 1) {
                    i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    i2 += GeneratedMessageV3.computeStringSize(2, this.insertionPoint_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    i2 += GeneratedMessageV3.computeStringSize(15, this.content_);
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
                if (!(obj instanceof File)) {
                    return super.equals(obj);
                }
                File file = (File) obj;
                boolean z2 = hasName() == file.hasName();
                if (hasName()) {
                    z2 = z2 && getName().equals(file.getName());
                }
                boolean z3 = z2 && hasInsertionPoint() == file.hasInsertionPoint();
                if (hasInsertionPoint()) {
                    z3 = z3 && getInsertionPoint().equals(file.getInsertionPoint());
                }
                boolean z4 = z3 && hasContent() == file.hasContent();
                if (hasContent()) {
                    z4 = z4 && getContent().equals(file.getContent());
                }
                if (!z4 || !this.unknownFields.equals(file.unknownFields)) {
                    z = false;
                }
                return z;
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = 779 + getDescriptor().hashCode();
                if (hasName()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getName().hashCode();
                }
                if (hasInsertionPoint()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + getInsertionPoint().hashCode();
                }
                if (hasContent()) {
                    hashCode = (((hashCode * 37) + 15) * 53) + getContent().hashCode();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static File parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (File) PARSER.parseFrom(byteBuffer);
            }

            public static File parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (File) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static File parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (File) PARSER.parseFrom(byteString);
            }

            public static File parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (File) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static File parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (File) PARSER.parseFrom(bArr);
            }

            public static File parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (File) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static File parseFrom(InputStream inputStream) throws IOException {
                return (File) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static File parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (File) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static File parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (File) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static File parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (File) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static File parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (File) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static File parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (File) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(File file) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(file);
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

            public static File getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<File> parser() {
                return PARSER;
            }

            public Parser<File> getParserForType() {
                return PARSER;
            }

            public File getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public interface FileOrBuilder extends MessageOrBuilder {
            String getContent();

            ByteString getContentBytes();

            String getInsertionPoint();

            ByteString getInsertionPointBytes();

            String getName();

            ByteString getNameBytes();

            boolean hasContent();

            boolean hasInsertionPoint();

            boolean hasName();
        }

        private CodeGeneratorResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private CodeGeneratorResponse() {
            this.memoizedIsInitialized = -1;
            this.error_ = "";
            this.file_ = Collections.emptyList();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CodeGeneratorResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag == 0) {
                            z = true;
                        } else if (readTag == 10) {
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ = 1 | this.bitField0_;
                            this.error_ = readBytes;
                        } else if (readTag == 122) {
                            if (!(z2 & true)) {
                                this.file_ = new ArrayList();
                                z2 |= true;
                            }
                            this.file_.add(codedInputStream.readMessage(File.PARSER, extensionRegistryLite));
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            z = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.file_ = Collections.unmodifiableList(this.file_);
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.file_ = Collections.unmodifiableList(this.file_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
                return;
            }
            throw new NullPointerException();
        }

        public static final Descriptor getDescriptor() {
            return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(CodeGeneratorResponse.class, Builder.class);
        }

        public boolean hasError() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getError() {
            Object obj = this.error_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.error_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getErrorBytes() {
            Object obj = this.error_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.error_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public List<File> getFileList() {
            return this.file_;
        }

        public List<? extends FileOrBuilder> getFileOrBuilderList() {
            return this.file_;
        }

        public int getFileCount() {
            return this.file_.size();
        }

        public File getFile(int i) {
            return (File) this.file_.get(i);
        }

        public FileOrBuilder getFileOrBuilder(int i) {
            return (FileOrBuilder) this.file_.get(i);
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
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.error_);
            }
            for (int i = 0; i < this.file_.size(); i++) {
                codedOutputStream.writeMessage(15, (MessageLite) this.file_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (this.bitField0_ & 1) == 1 ? GeneratedMessageV3.computeStringSize(1, this.error_) + 0 : 0;
            for (int i2 = 0; i2 < this.file_.size(); i2++) {
                computeStringSize += CodedOutputStream.computeMessageSize(15, (MessageLite) this.file_.get(i2));
            }
            int serializedSize = computeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CodeGeneratorResponse)) {
                return super.equals(obj);
            }
            CodeGeneratorResponse codeGeneratorResponse = (CodeGeneratorResponse) obj;
            boolean z2 = hasError() == codeGeneratorResponse.hasError();
            if (hasError()) {
                z2 = z2 && getError().equals(codeGeneratorResponse.getError());
            }
            if (!(z2 && getFileList().equals(codeGeneratorResponse.getFileList())) || !this.unknownFields.equals(codeGeneratorResponse.unknownFields)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasError()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getError().hashCode();
            }
            if (getFileCount() > 0) {
                hashCode = (((hashCode * 37) + 15) * 53) + getFileList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static CodeGeneratorResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CodeGeneratorResponse) PARSER.parseFrom(byteBuffer);
        }

        public static CodeGeneratorResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CodeGeneratorResponse) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CodeGeneratorResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CodeGeneratorResponse) PARSER.parseFrom(byteString);
        }

        public static CodeGeneratorResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CodeGeneratorResponse) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CodeGeneratorResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CodeGeneratorResponse) PARSER.parseFrom(bArr);
        }

        public static CodeGeneratorResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CodeGeneratorResponse) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CodeGeneratorResponse parseFrom(InputStream inputStream) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CodeGeneratorResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CodeGeneratorResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CodeGeneratorResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CodeGeneratorResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CodeGeneratorResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CodeGeneratorResponse codeGeneratorResponse) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(codeGeneratorResponse);
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

        public static CodeGeneratorResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CodeGeneratorResponse> parser() {
            return PARSER;
        }

        public Parser<CodeGeneratorResponse> getParserForType() {
            return PARSER;
        }

        public CodeGeneratorResponse getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public interface CodeGeneratorResponseOrBuilder extends MessageOrBuilder {
        String getError();

        ByteString getErrorBytes();

        File getFile(int i);

        int getFileCount();

        List<File> getFileList();

        FileOrBuilder getFileOrBuilder(int i);

        List<? extends FileOrBuilder> getFileOrBuilderList();

        boolean hasError();
    }

    public static final class Version extends GeneratedMessageV3 implements VersionOrBuilder {
        private static final Version DEFAULT_INSTANCE = new Version();
        public static final int MAJOR_FIELD_NUMBER = 1;
        public static final int MINOR_FIELD_NUMBER = 2;
        @Deprecated
        public static final Parser<Version> PARSER = new AbstractParser<Version>() {
            public Version parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Version(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PATCH_FIELD_NUMBER = 3;
        public static final int SUFFIX_FIELD_NUMBER = 4;
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int major_;
        private byte memoizedIsInitialized;
        /* access modifiers changed from: private */
        public int minor_;
        /* access modifiers changed from: private */
        public int patch_;
        /* access modifiers changed from: private */
        public volatile Object suffix_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements VersionOrBuilder {
            private int bitField0_;
            private int major_;
            private int minor_;
            private int patch_;
            private Object suffix_;

            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptor getDescriptor() {
                return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return PluginProtos.internal_static_google_protobuf_compiler_Version_fieldAccessorTable.ensureFieldAccessorsInitialized(Version.class, Builder.class);
            }

            private Builder() {
                this.suffix_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.suffix_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                Version.alwaysUseFieldBuilders;
            }

            public Builder clear() {
                super.clear();
                this.major_ = 0;
                this.bitField0_ &= -2;
                this.minor_ = 0;
                this.bitField0_ &= -3;
                this.patch_ = 0;
                this.bitField0_ &= -5;
                this.suffix_ = "";
                this.bitField0_ &= -9;
                return this;
            }

            public Descriptor getDescriptorForType() {
                return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
            }

            public Version getDefaultInstanceForType() {
                return Version.getDefaultInstance();
            }

            public Version build() {
                Version buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Version buildPartial() {
                Version version = new Version((com.google.protobuf.GeneratedMessageV3.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                version.major_ = this.major_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                version.minor_ = this.minor_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                version.patch_ = this.patch_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                version.suffix_ = this.suffix_;
                version.bitField0_ = i2;
                onBuilt();
                return version;
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
                if (message instanceof Version) {
                    return mergeFrom((Version) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Version version) {
                if (version == Version.getDefaultInstance()) {
                    return this;
                }
                if (version.hasMajor()) {
                    setMajor(version.getMajor());
                }
                if (version.hasMinor()) {
                    setMinor(version.getMinor());
                }
                if (version.hasPatch()) {
                    setPatch(version.getPatch());
                }
                if (version.hasSuffix()) {
                    this.bitField0_ |= 8;
                    this.suffix_ = version.suffix_;
                    onChanged();
                }
                mergeUnknownFields(version.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.compiler.PluginProtos.Version.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.google.protobuf.compiler.PluginProtos$Version> r1 = com.google.protobuf.compiler.PluginProtos.Version.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0011 }
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0011 }
                    com.google.protobuf.compiler.PluginProtos$Version r3 = (com.google.protobuf.compiler.PluginProtos.Version) r3     // Catch:{ InvalidProtocolBufferException -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.mergeFrom(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.compiler.PluginProtos$Version r4 = (com.google.protobuf.compiler.PluginProtos.Version) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.mergeFrom(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.compiler.PluginProtos.Version.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.compiler.PluginProtos$Version$Builder");
            }

            public boolean hasMajor() {
                return (this.bitField0_ & 1) == 1;
            }

            public int getMajor() {
                return this.major_;
            }

            public Builder setMajor(int i) {
                this.bitField0_ |= 1;
                this.major_ = i;
                onChanged();
                return this;
            }

            public Builder clearMajor() {
                this.bitField0_ &= -2;
                this.major_ = 0;
                onChanged();
                return this;
            }

            public boolean hasMinor() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getMinor() {
                return this.minor_;
            }

            public Builder setMinor(int i) {
                this.bitField0_ |= 2;
                this.minor_ = i;
                onChanged();
                return this;
            }

            public Builder clearMinor() {
                this.bitField0_ &= -3;
                this.minor_ = 0;
                onChanged();
                return this;
            }

            public boolean hasPatch() {
                return (this.bitField0_ & 4) == 4;
            }

            public int getPatch() {
                return this.patch_;
            }

            public Builder setPatch(int i) {
                this.bitField0_ |= 4;
                this.patch_ = i;
                onChanged();
                return this;
            }

            public Builder clearPatch() {
                this.bitField0_ &= -5;
                this.patch_ = 0;
                onChanged();
                return this;
            }

            public boolean hasSuffix() {
                return (this.bitField0_ & 8) == 8;
            }

            public String getSuffix() {
                Object obj = this.suffix_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.suffix_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getSuffixBytes() {
                Object obj = this.suffix_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.suffix_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setSuffix(String str) {
                if (str != null) {
                    this.bitField0_ |= 8;
                    this.suffix_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearSuffix() {
                this.bitField0_ &= -9;
                this.suffix_ = Version.getDefaultInstance().getSuffix();
                onChanged();
                return this;
            }

            public Builder setSuffixBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 8;
                    this.suffix_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        private Version(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private Version() {
            this.memoizedIsInitialized = -1;
            this.major_ = 0;
            this.minor_ = 0;
            this.patch_ = 0;
            this.suffix_ = "";
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Version(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag == 0) {
                            z = true;
                        } else if (readTag == 8) {
                            this.bitField0_ |= 1;
                            this.major_ = codedInputStream.readInt32();
                        } else if (readTag == 16) {
                            this.bitField0_ |= 2;
                            this.minor_ = codedInputStream.readInt32();
                        } else if (readTag == 24) {
                            this.bitField0_ |= 4;
                            this.patch_ = codedInputStream.readInt32();
                        } else if (readTag == 34) {
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 8;
                            this.suffix_ = readBytes;
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            z = true;
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
            return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return PluginProtos.internal_static_google_protobuf_compiler_Version_fieldAccessorTable.ensureFieldAccessorsInitialized(Version.class, Builder.class);
        }

        public boolean hasMajor() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getMajor() {
            return this.major_;
        }

        public boolean hasMinor() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getMinor() {
            return this.minor_;
        }

        public boolean hasPatch() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getPatch() {
            return this.patch_;
        }

        public boolean hasSuffix() {
            return (this.bitField0_ & 8) == 8;
        }

        public String getSuffix() {
            Object obj = this.suffix_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.suffix_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getSuffixBytes() {
            Object obj = this.suffix_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.suffix_ = copyFromUtf8;
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
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.major_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.minor_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt32(3, this.patch_);
            }
            if ((this.bitField0_ & 8) == 8) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.suffix_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, this.major_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeInt32Size(2, this.minor_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeInt32Size(3, this.patch_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += GeneratedMessageV3.computeStringSize(4, this.suffix_);
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
            if (!(obj instanceof Version)) {
                return super.equals(obj);
            }
            Version version = (Version) obj;
            boolean z2 = hasMajor() == version.hasMajor();
            if (hasMajor()) {
                z2 = z2 && getMajor() == version.getMajor();
            }
            boolean z3 = z2 && hasMinor() == version.hasMinor();
            if (hasMinor()) {
                z3 = z3 && getMinor() == version.getMinor();
            }
            boolean z4 = z3 && hasPatch() == version.hasPatch();
            if (hasPatch()) {
                z4 = z4 && getPatch() == version.getPatch();
            }
            boolean z5 = z4 && hasSuffix() == version.hasSuffix();
            if (hasSuffix()) {
                z5 = z5 && getSuffix().equals(version.getSuffix());
            }
            if (!z5 || !this.unknownFields.equals(version.unknownFields)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasMajor()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getMajor();
            }
            if (hasMinor()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getMinor();
            }
            if (hasPatch()) {
                hashCode = (((hashCode * 37) + 3) * 53) + getPatch();
            }
            if (hasSuffix()) {
                hashCode = (((hashCode * 37) + 4) * 53) + getSuffix().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static Version parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Version) PARSER.parseFrom(byteBuffer);
        }

        public static Version parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Version) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Version parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Version) PARSER.parseFrom(byteString);
        }

        public static Version parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Version) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Version parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Version) PARSER.parseFrom(bArr);
        }

        public static Version parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Version) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Version parseFrom(InputStream inputStream) throws IOException {
            return (Version) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Version parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Version) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Version parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Version) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Version parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Version) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Version parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Version) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Version parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Version) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Version version) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(version);
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

        public static Version getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Version> parser() {
            return PARSER;
        }

        public Parser<Version> getParserForType() {
            return PARSER;
        }

        public Version getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public interface VersionOrBuilder extends MessageOrBuilder {
        int getMajor();

        int getMinor();

        int getPatch();

        String getSuffix();

        ByteString getSuffixBytes();

        boolean hasMajor();

        boolean hasMinor();

        boolean hasPatch();

        boolean hasSuffix();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private PluginProtos() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {DescriptorProtos.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n%google/protobuf/compiler/plugin.proto\u0012\u0018google.protobuf.compiler\u001a google/protobuf/descriptor.proto\"F\n\u0007Version\u0012\r\n\u0005major\u0018\u0001 \u0001(\u0005\u0012\r\n\u0005minor\u0018\u0002 \u0001(\u0005\u0012\r\n\u0005patch\u0018\u0003 \u0001(\u0005\u0012\u000e\n\u0006suffix\u0018\u0004 \u0001(\t\"º\u0001\n\u0014CodeGeneratorRequest\u0012\u0018\n\u0010file_to_generate\u0018\u0001 \u0003(\t\u0012\u0011\n\tparameter\u0018\u0002 \u0001(\t\u00128\n\nproto_file\u0018\u000f \u0003(\u000b2$.google.protobuf.FileDescriptorProto\u0012;\n\u0010compiler_version\u0018\u0003 \u0001(\u000b2!.google.protobuf.compiler.Version\"ª\u0001\n\u0015CodeGeneratorResponse\u0012\r\n\u0005error\u0018\u0001 \u0001(\t\u0012B\n\u0004file\u0018\u000f \u0003(\u000b24.google.protobuf.compiler.CodeGeneratorResponse.File\u001a>\n\u0004File\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0017\n\u000finsertion_point\u0018\u0002 \u0001(\t\u0012\u000f\n\u0007content\u0018\u000f \u0001(\tBg\n\u001ccom.google.protobuf.compilerB\fPluginProtosZ9github.com/golang/protobuf/protoc-gen-go/plugin;plugin_go"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                PluginProtos.descriptor = fileDescriptor;
                return null;
            }
        });
        DescriptorProtos.getDescriptor();
    }
}
