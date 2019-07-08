package kotlin.reflect.jvm.internal.impl.metadata.jvm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractParser;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString.Output;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringArrayList;
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder;
import kotlin.reflect.jvm.internal.impl.protobuf.Parser;
import kotlin.reflect.jvm.internal.impl.protobuf.ProtocolStringList;

public final class JvmModuleProtoBuf {

    public static final class Module extends GeneratedMessageLite implements ModuleOrBuilder {
        public static Parser<Module> PARSER = new AbstractParser<Module>() {
            public Module parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Module(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Module defaultInstance = new Module(true);
        /* access modifiers changed from: private */
        public List<Annotation> annotation_;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public LazyStringList jvmPackageName_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<PackageParts> metadataParts_;
        /* access modifiers changed from: private */
        public List<PackageParts> packageParts_;
        /* access modifiers changed from: private */
        public QualifiedNameTable qualifiedNameTable_;
        /* access modifiers changed from: private */
        public StringTable stringTable_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;

        public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<Module, Builder> implements ModuleOrBuilder {
            private List<Annotation> annotation_ = Collections.emptyList();
            private int bitField0_;
            private LazyStringList jvmPackageName_ = LazyStringArrayList.EMPTY;
            private List<PackageParts> metadataParts_ = Collections.emptyList();
            private List<PackageParts> packageParts_ = Collections.emptyList();
            private QualifiedNameTable qualifiedNameTable_ = QualifiedNameTable.getDefaultInstance();
            private StringTable stringTable_ = StringTable.getDefaultInstance();

            private void maybeForceBuilderInitialization() {
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Module getDefaultInstanceForType() {
                return Module.getDefaultInstance();
            }

            public Module build() {
                Module buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Module buildPartial() {
                Module module = new Module((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) == 1) {
                    this.packageParts_ = Collections.unmodifiableList(this.packageParts_);
                    this.bitField0_ &= -2;
                }
                module.packageParts_ = this.packageParts_;
                if ((this.bitField0_ & 2) == 2) {
                    this.metadataParts_ = Collections.unmodifiableList(this.metadataParts_);
                    this.bitField0_ &= -3;
                }
                module.metadataParts_ = this.metadataParts_;
                if ((this.bitField0_ & 4) == 4) {
                    this.jvmPackageName_ = this.jvmPackageName_.getUnmodifiableView();
                    this.bitField0_ &= -5;
                }
                module.jvmPackageName_ = this.jvmPackageName_;
                if ((i & 8) != 8) {
                    i2 = 0;
                }
                module.stringTable_ = this.stringTable_;
                if ((i & 16) == 16) {
                    i2 |= 2;
                }
                module.qualifiedNameTable_ = this.qualifiedNameTable_;
                if ((this.bitField0_ & 32) == 32) {
                    this.annotation_ = Collections.unmodifiableList(this.annotation_);
                    this.bitField0_ &= -33;
                }
                module.annotation_ = this.annotation_;
                module.bitField0_ = i2;
                return module;
            }

            public Builder mergeFrom(Module module) {
                if (module == Module.getDefaultInstance()) {
                    return this;
                }
                if (!module.packageParts_.isEmpty()) {
                    if (this.packageParts_.isEmpty()) {
                        this.packageParts_ = module.packageParts_;
                        this.bitField0_ &= -2;
                    } else {
                        ensurePackagePartsIsMutable();
                        this.packageParts_.addAll(module.packageParts_);
                    }
                }
                if (!module.metadataParts_.isEmpty()) {
                    if (this.metadataParts_.isEmpty()) {
                        this.metadataParts_ = module.metadataParts_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureMetadataPartsIsMutable();
                        this.metadataParts_.addAll(module.metadataParts_);
                    }
                }
                if (!module.jvmPackageName_.isEmpty()) {
                    if (this.jvmPackageName_.isEmpty()) {
                        this.jvmPackageName_ = module.jvmPackageName_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureJvmPackageNameIsMutable();
                        this.jvmPackageName_.addAll(module.jvmPackageName_);
                    }
                }
                if (module.hasStringTable()) {
                    mergeStringTable(module.getStringTable());
                }
                if (module.hasQualifiedNameTable()) {
                    mergeQualifiedNameTable(module.getQualifiedNameTable());
                }
                if (!module.annotation_.isEmpty()) {
                    if (this.annotation_.isEmpty()) {
                        this.annotation_ = module.annotation_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureAnnotationIsMutable();
                        this.annotation_.addAll(module.annotation_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(module.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getPackagePartsCount(); i++) {
                    if (!getPackageParts(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getMetadataPartsCount(); i2++) {
                    if (!getMetadataParts(i2).isInitialized()) {
                        return false;
                    }
                }
                if (hasQualifiedNameTable() && !getQualifiedNameTable().isInitialized()) {
                    return false;
                }
                for (int i3 = 0; i3 < getAnnotationCount(); i3++) {
                    if (!getAnnotation(i3).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Module module;
                Module module2 = null;
                try {
                    Module module3 = (Module) Module.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (module3 != null) {
                        mergeFrom(module3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    module = (Module) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    module2 = module;
                }
                if (module2 != null) {
                    mergeFrom(module2);
                }
                throw th;
            }

            private void ensurePackagePartsIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.packageParts_ = new ArrayList(this.packageParts_);
                    this.bitField0_ |= 1;
                }
            }

            public int getPackagePartsCount() {
                return this.packageParts_.size();
            }

            public PackageParts getPackageParts(int i) {
                return (PackageParts) this.packageParts_.get(i);
            }

            private void ensureMetadataPartsIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.metadataParts_ = new ArrayList(this.metadataParts_);
                    this.bitField0_ |= 2;
                }
            }

            public int getMetadataPartsCount() {
                return this.metadataParts_.size();
            }

            public PackageParts getMetadataParts(int i) {
                return (PackageParts) this.metadataParts_.get(i);
            }

            private void ensureJvmPackageNameIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.jvmPackageName_ = new LazyStringArrayList(this.jvmPackageName_);
                    this.bitField0_ |= 4;
                }
            }

            public Builder mergeStringTable(StringTable stringTable) {
                if ((this.bitField0_ & 8) != 8 || this.stringTable_ == StringTable.getDefaultInstance()) {
                    this.stringTable_ = stringTable;
                } else {
                    this.stringTable_ = StringTable.newBuilder(this.stringTable_).mergeFrom(stringTable).buildPartial();
                }
                this.bitField0_ |= 8;
                return this;
            }

            public boolean hasQualifiedNameTable() {
                return (this.bitField0_ & 16) == 16;
            }

            public QualifiedNameTable getQualifiedNameTable() {
                return this.qualifiedNameTable_;
            }

            public Builder mergeQualifiedNameTable(QualifiedNameTable qualifiedNameTable) {
                if ((this.bitField0_ & 16) != 16 || this.qualifiedNameTable_ == QualifiedNameTable.getDefaultInstance()) {
                    this.qualifiedNameTable_ = qualifiedNameTable;
                } else {
                    this.qualifiedNameTable_ = QualifiedNameTable.newBuilder(this.qualifiedNameTable_).mergeFrom(qualifiedNameTable).buildPartial();
                }
                this.bitField0_ |= 16;
                return this;
            }

            private void ensureAnnotationIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.annotation_ = new ArrayList(this.annotation_);
                    this.bitField0_ |= 32;
                }
            }

            public int getAnnotationCount() {
                return this.annotation_.size();
            }

            public Annotation getAnnotation(int i) {
                return (Annotation) this.annotation_.get(i);
            }
        }

        private Module(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Module(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Module getDefaultInstance() {
            return defaultInstance;
        }

        public Module getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARNING: type inference failed for: r10v0 */
        /* JADX WARNING: type inference failed for: r10v1, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable$Builder] */
        /* JADX WARNING: type inference failed for: r10v2, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable$Builder] */
        /* JADX WARNING: type inference failed for: r10v3, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable$Builder] */
        /* JADX WARNING: type inference failed for: r10v4, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable$Builder] */
        /* JADX WARNING: type inference failed for: r10v5 */
        /* JADX WARNING: type inference failed for: r10v6 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r10v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable$Builder, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable$Builder]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable$Builder, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable$Builder]
  mth insns count: 165
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private Module(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream r12, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite r13) throws kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException {
            /*
                r11 = this;
                r11.<init>()
                r0 = -1
                r11.memoizedIsInitialized = r0
                r11.memoizedSerializedSize = r0
                r11.initFields()
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString$Output r0 = kotlin.reflect.jvm.internal.impl.protobuf.ByteString.newOutput()
                r1 = 1
                kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream r2 = kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream.newInstance(r0, r1)
                r3 = 0
                r4 = 0
            L_0x0016:
                r5 = 32
                r6 = 4
                r7 = 2
                if (r3 != 0) goto L_0x0159
                int r8 = r12.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                if (r8 == 0) goto L_0x00f9
                r9 = 10
                if (r8 == r9) goto L_0x00df
                r9 = 18
                if (r8 == r9) goto L_0x00c5
                r9 = 26
                if (r8 == r9) goto L_0x00ad
                r9 = 34
                r10 = 0
                if (r8 == r9) goto L_0x0084
                r9 = 42
                if (r8 == r9) goto L_0x005c
                r9 = 50
                if (r8 == r9) goto L_0x0043
                boolean r5 = r11.parseUnknownField(r12, r2, r13, r8)     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                if (r5 != 0) goto L_0x0016
                r3 = 1
                goto L_0x0016
            L_0x0043:
                r8 = r4 & 32
                if (r8 == r5) goto L_0x0050
                java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r8.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r11.annotation_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r4 = r4 | 32
            L_0x0050:
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation> r8 = r11.annotation_     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation> r9 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r9 = r12.readMessage(r9, r13)     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r8.add(r9)     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                goto L_0x0016
            L_0x005c:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r8 = r8 & r7
                if (r8 != r7) goto L_0x0067
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable r8 = r11.qualifiedNameTable_     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable$Builder r10 = r8.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
            L_0x0067:
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable> r8 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r8 = r12.readMessage(r8, r13)     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable r8 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable) r8     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r11.qualifiedNameTable_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                if (r10 == 0) goto L_0x007e
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable r8 = r11.qualifiedNameTable_     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r10.mergeFrom(r8)     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable r8 = r10.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r11.qualifiedNameTable_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
            L_0x007e:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r8 = r8 | r7
                r11.bitField0_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                goto L_0x0016
            L_0x0084:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r8 = r8 & r1
                if (r8 != r1) goto L_0x008f
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable r8 = r11.stringTable_     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable$Builder r10 = r8.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
            L_0x008f:
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable> r8 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r8 = r12.readMessage(r8, r13)     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable r8 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable) r8     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r11.stringTable_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                if (r10 == 0) goto L_0x00a6
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable r8 = r11.stringTable_     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r10.mergeFrom(r8)     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable r8 = r10.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r11.stringTable_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
            L_0x00a6:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r8 = r8 | r1
                r11.bitField0_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                goto L_0x0016
            L_0x00ad:
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r8 = r12.readBytes()     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r9 = r4 & 4
                if (r9 == r6) goto L_0x00be
                kotlin.reflect.jvm.internal.impl.protobuf.LazyStringArrayList r9 = new kotlin.reflect.jvm.internal.impl.protobuf.LazyStringArrayList     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r9.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r11.jvmPackageName_ = r9     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r4 = r4 | 4
            L_0x00be:
                kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList r9 = r11.jvmPackageName_     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r9.add(r8)     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                goto L_0x0016
            L_0x00c5:
                r8 = r4 & 2
                if (r8 == r7) goto L_0x00d2
                java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r8.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r11.metadataParts_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r4 = r4 | 2
            L_0x00d2:
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$PackageParts> r8 = r11.metadataParts_     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$PackageParts> r9 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.PackageParts.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r9 = r12.readMessage(r9, r13)     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r8.add(r9)     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                goto L_0x0016
            L_0x00df:
                r8 = r4 & 1
                if (r8 == r1) goto L_0x00ec
                java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r8.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r11.packageParts_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r4 = r4 | 1
            L_0x00ec:
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$PackageParts> r8 = r11.packageParts_     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$PackageParts> r9 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.PackageParts.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r9 = r12.readMessage(r9, r13)     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                r8.add(r9)     // Catch:{ InvalidProtocolBufferException -> 0x010d, IOException -> 0x00fe }
                goto L_0x0016
            L_0x00f9:
                r3 = 1
                goto L_0x0016
            L_0x00fc:
                r12 = move-exception
                goto L_0x0113
            L_0x00fe:
                r12 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r13 = new kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x00fc }
                java.lang.String r12 = r12.getMessage()     // Catch:{ all -> 0x00fc }
                r13.<init>(r12)     // Catch:{ all -> 0x00fc }
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r12 = r13.setUnfinishedMessage(r11)     // Catch:{ all -> 0x00fc }
                throw r12     // Catch:{ all -> 0x00fc }
            L_0x010d:
                r12 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r12 = r12.setUnfinishedMessage(r11)     // Catch:{ all -> 0x00fc }
                throw r12     // Catch:{ all -> 0x00fc }
            L_0x0113:
                r13 = r4 & 1
                if (r13 != r1) goto L_0x011f
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$PackageParts> r13 = r11.packageParts_
                java.util.List r13 = java.util.Collections.unmodifiableList(r13)
                r11.packageParts_ = r13
            L_0x011f:
                r13 = r4 & 2
                if (r13 != r7) goto L_0x012b
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$PackageParts> r13 = r11.metadataParts_
                java.util.List r13 = java.util.Collections.unmodifiableList(r13)
                r11.metadataParts_ = r13
            L_0x012b:
                r13 = r4 & 4
                if (r13 != r6) goto L_0x0137
                kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList r13 = r11.jvmPackageName_
                kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList r13 = r13.getUnmodifiableView()
                r11.jvmPackageName_ = r13
            L_0x0137:
                r13 = r4 & 32
                if (r13 != r5) goto L_0x0143
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation> r13 = r11.annotation_
                java.util.List r13 = java.util.Collections.unmodifiableList(r13)
                r11.annotation_ = r13
            L_0x0143:
                r2.flush()     // Catch:{ IOException -> 0x0146, all -> 0x014d }
            L_0x0146:
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r13 = r0.toByteString()
                r11.unknownFields = r13
                goto L_0x0155
            L_0x014d:
                r12 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r13 = r0.toByteString()
                r11.unknownFields = r13
                throw r12
            L_0x0155:
                r11.makeExtensionsImmutable()
                throw r12
            L_0x0159:
                r12 = r4 & 1
                if (r12 != r1) goto L_0x0165
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$PackageParts> r12 = r11.packageParts_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r11.packageParts_ = r12
            L_0x0165:
                r12 = r4 & 2
                if (r12 != r7) goto L_0x0171
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$PackageParts> r12 = r11.metadataParts_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r11.metadataParts_ = r12
            L_0x0171:
                r12 = r4 & 4
                if (r12 != r6) goto L_0x017d
                kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList r12 = r11.jvmPackageName_
                kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList r12 = r12.getUnmodifiableView()
                r11.jvmPackageName_ = r12
            L_0x017d:
                r12 = r4 & 32
                if (r12 != r5) goto L_0x0189
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation> r12 = r11.annotation_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r11.annotation_ = r12
            L_0x0189:
                r2.flush()     // Catch:{ IOException -> 0x018c, all -> 0x0193 }
            L_0x018c:
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r12 = r0.toByteString()
                r11.unknownFields = r12
                goto L_0x019b
            L_0x0193:
                r12 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r13 = r0.toByteString()
                r11.unknownFields = r13
                throw r12
            L_0x019b:
                r11.makeExtensionsImmutable()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.Module.<init>(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite):void");
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<Module> getParserForType() {
            return PARSER;
        }

        public List<PackageParts> getPackagePartsList() {
            return this.packageParts_;
        }

        public int getPackagePartsCount() {
            return this.packageParts_.size();
        }

        public PackageParts getPackageParts(int i) {
            return (PackageParts) this.packageParts_.get(i);
        }

        public List<PackageParts> getMetadataPartsList() {
            return this.metadataParts_;
        }

        public int getMetadataPartsCount() {
            return this.metadataParts_.size();
        }

        public PackageParts getMetadataParts(int i) {
            return (PackageParts) this.metadataParts_.get(i);
        }

        public ProtocolStringList getJvmPackageNameList() {
            return this.jvmPackageName_;
        }

        public boolean hasStringTable() {
            return (this.bitField0_ & 1) == 1;
        }

        public StringTable getStringTable() {
            return this.stringTable_;
        }

        public boolean hasQualifiedNameTable() {
            return (this.bitField0_ & 2) == 2;
        }

        public QualifiedNameTable getQualifiedNameTable() {
            return this.qualifiedNameTable_;
        }

        public List<Annotation> getAnnotationList() {
            return this.annotation_;
        }

        public int getAnnotationCount() {
            return this.annotation_.size();
        }

        public Annotation getAnnotation(int i) {
            return (Annotation) this.annotation_.get(i);
        }

        private void initFields() {
            this.packageParts_ = Collections.emptyList();
            this.metadataParts_ = Collections.emptyList();
            this.jvmPackageName_ = LazyStringArrayList.EMPTY;
            this.stringTable_ = StringTable.getDefaultInstance();
            this.qualifiedNameTable_ = QualifiedNameTable.getDefaultInstance();
            this.annotation_ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getPackagePartsCount(); i++) {
                if (!getPackageParts(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < getMetadataPartsCount(); i2++) {
                if (!getMetadataParts(i2).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if (!hasQualifiedNameTable() || getQualifiedNameTable().isInitialized()) {
                for (int i3 = 0; i3 < getAnnotationCount(); i3++) {
                    if (!getAnnotation(i3).isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    }
                }
                this.memoizedIsInitialized = 1;
                return true;
            }
            this.memoizedIsInitialized = 0;
            return false;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.packageParts_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.packageParts_.get(i));
            }
            for (int i2 = 0; i2 < this.metadataParts_.size(); i2++) {
                codedOutputStream.writeMessage(2, (MessageLite) this.metadataParts_.get(i2));
            }
            for (int i3 = 0; i3 < this.jvmPackageName_.size(); i3++) {
                codedOutputStream.writeBytes(3, this.jvmPackageName_.getByteString(i3));
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeMessage(4, this.stringTable_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(5, this.qualifiedNameTable_);
            }
            for (int i4 = 0; i4 < this.annotation_.size(); i4++) {
                codedOutputStream.writeMessage(6, (MessageLite) this.annotation_.get(i4));
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.packageParts_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.packageParts_.get(i3));
            }
            for (int i4 = 0; i4 < this.metadataParts_.size(); i4++) {
                i2 += CodedOutputStream.computeMessageSize(2, (MessageLite) this.metadataParts_.get(i4));
            }
            int i5 = 0;
            for (int i6 = 0; i6 < this.jvmPackageName_.size(); i6++) {
                i5 += CodedOutputStream.computeBytesSizeNoTag(this.jvmPackageName_.getByteString(i6));
            }
            int size = i2 + i5 + (getJvmPackageNameList().size() * 1);
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeMessageSize(4, this.stringTable_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeMessageSize(5, this.qualifiedNameTable_);
            }
            for (int i7 = 0; i7 < this.annotation_.size(); i7++) {
                size += CodedOutputStream.computeMessageSize(6, (MessageLite) this.annotation_.get(i7));
            }
            int size2 = size + this.unknownFields.size();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static Module parseFrom(InputStream inputStream) throws IOException {
            return (Module) PARSER.parseFrom(inputStream);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Module module) {
            return newBuilder().mergeFrom(module);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface ModuleOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class PackageParts extends GeneratedMessageLite implements PackagePartsOrBuilder {
        public static Parser<PackageParts> PARSER = new AbstractParser<PackageParts>() {
            public PackageParts parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PackageParts(codedInputStream, extensionRegistryLite);
            }
        };
        private static final PackageParts defaultInstance = new PackageParts(true);
        /* access modifiers changed from: private */
        public int bitField0_;
        private int classWithJvmPackageNamePackageIdMemoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<Integer> classWithJvmPackageNamePackageId_;
        /* access modifiers changed from: private */
        public LazyStringList classWithJvmPackageNameShortName_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int multifileFacadeShortNameIdMemoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<Integer> multifileFacadeShortNameId_;
        /* access modifiers changed from: private */
        public LazyStringList multifileFacadeShortName_;
        /* access modifiers changed from: private */
        public Object packageFqName_;
        /* access modifiers changed from: private */
        public LazyStringList shortClassName_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;

        public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<PackageParts, Builder> implements PackagePartsOrBuilder {
            private int bitField0_;
            private List<Integer> classWithJvmPackageNamePackageId_ = Collections.emptyList();
            private LazyStringList classWithJvmPackageNameShortName_ = LazyStringArrayList.EMPTY;
            private List<Integer> multifileFacadeShortNameId_ = Collections.emptyList();
            private LazyStringList multifileFacadeShortName_ = LazyStringArrayList.EMPTY;
            private Object packageFqName_ = "";
            private LazyStringList shortClassName_ = LazyStringArrayList.EMPTY;

            private void maybeForceBuilderInitialization() {
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public PackageParts getDefaultInstanceForType() {
                return PackageParts.getDefaultInstance();
            }

            public PackageParts build() {
                PackageParts buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public PackageParts buildPartial() {
                PackageParts packageParts = new PackageParts((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                int i = 1;
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                packageParts.packageFqName_ = this.packageFqName_;
                if ((this.bitField0_ & 2) == 2) {
                    this.shortClassName_ = this.shortClassName_.getUnmodifiableView();
                    this.bitField0_ &= -3;
                }
                packageParts.shortClassName_ = this.shortClassName_;
                if ((this.bitField0_ & 4) == 4) {
                    this.multifileFacadeShortNameId_ = Collections.unmodifiableList(this.multifileFacadeShortNameId_);
                    this.bitField0_ &= -5;
                }
                packageParts.multifileFacadeShortNameId_ = this.multifileFacadeShortNameId_;
                if ((this.bitField0_ & 8) == 8) {
                    this.multifileFacadeShortName_ = this.multifileFacadeShortName_.getUnmodifiableView();
                    this.bitField0_ &= -9;
                }
                packageParts.multifileFacadeShortName_ = this.multifileFacadeShortName_;
                if ((this.bitField0_ & 16) == 16) {
                    this.classWithJvmPackageNameShortName_ = this.classWithJvmPackageNameShortName_.getUnmodifiableView();
                    this.bitField0_ &= -17;
                }
                packageParts.classWithJvmPackageNameShortName_ = this.classWithJvmPackageNameShortName_;
                if ((this.bitField0_ & 32) == 32) {
                    this.classWithJvmPackageNamePackageId_ = Collections.unmodifiableList(this.classWithJvmPackageNamePackageId_);
                    this.bitField0_ &= -33;
                }
                packageParts.classWithJvmPackageNamePackageId_ = this.classWithJvmPackageNamePackageId_;
                packageParts.bitField0_ = i;
                return packageParts;
            }

            public Builder mergeFrom(PackageParts packageParts) {
                if (packageParts == PackageParts.getDefaultInstance()) {
                    return this;
                }
                if (packageParts.hasPackageFqName()) {
                    this.bitField0_ |= 1;
                    this.packageFqName_ = packageParts.packageFqName_;
                }
                if (!packageParts.shortClassName_.isEmpty()) {
                    if (this.shortClassName_.isEmpty()) {
                        this.shortClassName_ = packageParts.shortClassName_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureShortClassNameIsMutable();
                        this.shortClassName_.addAll(packageParts.shortClassName_);
                    }
                }
                if (!packageParts.multifileFacadeShortNameId_.isEmpty()) {
                    if (this.multifileFacadeShortNameId_.isEmpty()) {
                        this.multifileFacadeShortNameId_ = packageParts.multifileFacadeShortNameId_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureMultifileFacadeShortNameIdIsMutable();
                        this.multifileFacadeShortNameId_.addAll(packageParts.multifileFacadeShortNameId_);
                    }
                }
                if (!packageParts.multifileFacadeShortName_.isEmpty()) {
                    if (this.multifileFacadeShortName_.isEmpty()) {
                        this.multifileFacadeShortName_ = packageParts.multifileFacadeShortName_;
                        this.bitField0_ &= -9;
                    } else {
                        ensureMultifileFacadeShortNameIsMutable();
                        this.multifileFacadeShortName_.addAll(packageParts.multifileFacadeShortName_);
                    }
                }
                if (!packageParts.classWithJvmPackageNameShortName_.isEmpty()) {
                    if (this.classWithJvmPackageNameShortName_.isEmpty()) {
                        this.classWithJvmPackageNameShortName_ = packageParts.classWithJvmPackageNameShortName_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureClassWithJvmPackageNameShortNameIsMutable();
                        this.classWithJvmPackageNameShortName_.addAll(packageParts.classWithJvmPackageNameShortName_);
                    }
                }
                if (!packageParts.classWithJvmPackageNamePackageId_.isEmpty()) {
                    if (this.classWithJvmPackageNamePackageId_.isEmpty()) {
                        this.classWithJvmPackageNamePackageId_ = packageParts.classWithJvmPackageNamePackageId_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureClassWithJvmPackageNamePackageIdIsMutable();
                        this.classWithJvmPackageNamePackageId_.addAll(packageParts.classWithJvmPackageNamePackageId_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(packageParts.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                return hasPackageFqName();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PackageParts packageParts;
                PackageParts packageParts2 = null;
                try {
                    PackageParts packageParts3 = (PackageParts) PackageParts.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (packageParts3 != null) {
                        mergeFrom(packageParts3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    packageParts = (PackageParts) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    packageParts2 = packageParts;
                }
                if (packageParts2 != null) {
                    mergeFrom(packageParts2);
                }
                throw th;
            }

            public boolean hasPackageFqName() {
                return (this.bitField0_ & 1) == 1;
            }

            private void ensureShortClassNameIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.shortClassName_ = new LazyStringArrayList(this.shortClassName_);
                    this.bitField0_ |= 2;
                }
            }

            private void ensureMultifileFacadeShortNameIdIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.multifileFacadeShortNameId_ = new ArrayList(this.multifileFacadeShortNameId_);
                    this.bitField0_ |= 4;
                }
            }

            private void ensureMultifileFacadeShortNameIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.multifileFacadeShortName_ = new LazyStringArrayList(this.multifileFacadeShortName_);
                    this.bitField0_ |= 8;
                }
            }

            private void ensureClassWithJvmPackageNameShortNameIsMutable() {
                if ((this.bitField0_ & 16) != 16) {
                    this.classWithJvmPackageNameShortName_ = new LazyStringArrayList(this.classWithJvmPackageNameShortName_);
                    this.bitField0_ |= 16;
                }
            }

            private void ensureClassWithJvmPackageNamePackageIdIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.classWithJvmPackageNamePackageId_ = new ArrayList(this.classWithJvmPackageNamePackageId_);
                    this.bitField0_ |= 32;
                }
            }
        }

        private PackageParts(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.multifileFacadeShortNameIdMemoizedSerializedSize = -1;
            this.classWithJvmPackageNamePackageIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private PackageParts(boolean z) {
            this.multifileFacadeShortNameIdMemoizedSerializedSize = -1;
            this.classWithJvmPackageNamePackageIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static PackageParts getDefaultInstance() {
            return defaultInstance;
        }

        public PackageParts getDefaultInstanceForType() {
            return defaultInstance;
        }

        private PackageParts(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.multifileFacadeShortNameIdMemoizedSerializedSize = -1;
            this.classWithJvmPackageNamePackageIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            Output newOutput = ByteString.newOutput();
            CodedOutputStream newInstance = CodedOutputStream.newInstance(newOutput, 1);
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 10) {
                        ByteString readBytes = codedInputStream.readBytes();
                        this.bitField0_ |= 1;
                        this.packageFqName_ = readBytes;
                    } else if (readTag == 18) {
                        ByteString readBytes2 = codedInputStream.readBytes();
                        if (!(z2 & true)) {
                            this.shortClassName_ = new LazyStringArrayList();
                            z2 |= true;
                        }
                        this.shortClassName_.add(readBytes2);
                    } else if (readTag == 24) {
                        if (!(z2 & true)) {
                            this.multifileFacadeShortNameId_ = new ArrayList();
                            z2 |= true;
                        }
                        this.multifileFacadeShortNameId_.add(Integer.valueOf(codedInputStream.readInt32()));
                    } else if (readTag == 26) {
                        int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                        if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                            this.multifileFacadeShortNameId_ = new ArrayList();
                            z2 |= true;
                        }
                        while (codedInputStream.getBytesUntilLimit() > 0) {
                            this.multifileFacadeShortNameId_.add(Integer.valueOf(codedInputStream.readInt32()));
                        }
                        codedInputStream.popLimit(pushLimit);
                    } else if (readTag == 34) {
                        ByteString readBytes3 = codedInputStream.readBytes();
                        if (!(z2 & true)) {
                            this.multifileFacadeShortName_ = new LazyStringArrayList();
                            z2 |= true;
                        }
                        this.multifileFacadeShortName_.add(readBytes3);
                    } else if (readTag == 42) {
                        ByteString readBytes4 = codedInputStream.readBytes();
                        if (!(z2 & true)) {
                            this.classWithJvmPackageNameShortName_ = new LazyStringArrayList();
                            z2 |= true;
                        }
                        this.classWithJvmPackageNameShortName_.add(readBytes4);
                    } else if (readTag == 48) {
                        if (!(z2 & true)) {
                            this.classWithJvmPackageNamePackageId_ = new ArrayList();
                            z2 |= true;
                        }
                        this.classWithJvmPackageNamePackageId_.add(Integer.valueOf(codedInputStream.readInt32()));
                    } else if (readTag == 50) {
                        int pushLimit2 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                        if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                            this.classWithJvmPackageNamePackageId_ = new ArrayList();
                            z2 |= true;
                        }
                        while (codedInputStream.getBytesUntilLimit() > 0) {
                            this.classWithJvmPackageNamePackageId_.add(Integer.valueOf(codedInputStream.readInt32()));
                        }
                        codedInputStream.popLimit(pushLimit2);
                    } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.shortClassName_ = this.shortClassName_.getUnmodifiableView();
                    }
                    if (z2 & true) {
                        this.multifileFacadeShortNameId_ = Collections.unmodifiableList(this.multifileFacadeShortNameId_);
                    }
                    if (z2 & true) {
                        this.multifileFacadeShortName_ = this.multifileFacadeShortName_.getUnmodifiableView();
                    }
                    if (z2 & true) {
                        this.classWithJvmPackageNameShortName_ = this.classWithJvmPackageNameShortName_.getUnmodifiableView();
                    }
                    if (z2 & true) {
                        this.classWithJvmPackageNamePackageId_ = Collections.unmodifiableList(this.classWithJvmPackageNamePackageId_);
                    }
                    try {
                        newInstance.flush();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.unknownFields = newOutput.toByteString();
                        throw th2;
                    }
                    this.unknownFields = newOutput.toByteString();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.shortClassName_ = this.shortClassName_.getUnmodifiableView();
            }
            if (z2 & true) {
                this.multifileFacadeShortNameId_ = Collections.unmodifiableList(this.multifileFacadeShortNameId_);
            }
            if (z2 & true) {
                this.multifileFacadeShortName_ = this.multifileFacadeShortName_.getUnmodifiableView();
            }
            if (z2 & true) {
                this.classWithJvmPackageNameShortName_ = this.classWithJvmPackageNameShortName_.getUnmodifiableView();
            }
            if (z2 & true) {
                this.classWithJvmPackageNamePackageId_ = Collections.unmodifiableList(this.classWithJvmPackageNamePackageId_);
            }
            try {
                newInstance.flush();
            } catch (IOException unused2) {
            } catch (Throwable th3) {
                this.unknownFields = newOutput.toByteString();
                throw th3;
            }
            this.unknownFields = newOutput.toByteString();
            makeExtensionsImmutable();
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<PackageParts> getParserForType() {
            return PARSER;
        }

        public boolean hasPackageFqName() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getPackageFqName() {
            Object obj = this.packageFqName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.packageFqName_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getPackageFqNameBytes() {
            Object obj = this.packageFqName_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.packageFqName_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public ProtocolStringList getShortClassNameList() {
            return this.shortClassName_;
        }

        public List<Integer> getMultifileFacadeShortNameIdList() {
            return this.multifileFacadeShortNameId_;
        }

        public ProtocolStringList getMultifileFacadeShortNameList() {
            return this.multifileFacadeShortName_;
        }

        public ProtocolStringList getClassWithJvmPackageNameShortNameList() {
            return this.classWithJvmPackageNameShortName_;
        }

        public List<Integer> getClassWithJvmPackageNamePackageIdList() {
            return this.classWithJvmPackageNamePackageId_;
        }

        private void initFields() {
            this.packageFqName_ = "";
            this.shortClassName_ = LazyStringArrayList.EMPTY;
            this.multifileFacadeShortNameId_ = Collections.emptyList();
            this.multifileFacadeShortName_ = LazyStringArrayList.EMPTY;
            this.classWithJvmPackageNameShortName_ = LazyStringArrayList.EMPTY;
            this.classWithJvmPackageNamePackageId_ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasPackageFqName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getPackageFqNameBytes());
            }
            for (int i = 0; i < this.shortClassName_.size(); i++) {
                codedOutputStream.writeBytes(2, this.shortClassName_.getByteString(i));
            }
            if (getMultifileFacadeShortNameIdList().size() > 0) {
                codedOutputStream.writeRawVarint32(26);
                codedOutputStream.writeRawVarint32(this.multifileFacadeShortNameIdMemoizedSerializedSize);
            }
            for (int i2 = 0; i2 < this.multifileFacadeShortNameId_.size(); i2++) {
                codedOutputStream.writeInt32NoTag(((Integer) this.multifileFacadeShortNameId_.get(i2)).intValue());
            }
            for (int i3 = 0; i3 < this.multifileFacadeShortName_.size(); i3++) {
                codedOutputStream.writeBytes(4, this.multifileFacadeShortName_.getByteString(i3));
            }
            for (int i4 = 0; i4 < this.classWithJvmPackageNameShortName_.size(); i4++) {
                codedOutputStream.writeBytes(5, this.classWithJvmPackageNameShortName_.getByteString(i4));
            }
            if (getClassWithJvmPackageNamePackageIdList().size() > 0) {
                codedOutputStream.writeRawVarint32(50);
                codedOutputStream.writeRawVarint32(this.classWithJvmPackageNamePackageIdMemoizedSerializedSize);
            }
            for (int i5 = 0; i5 < this.classWithJvmPackageNamePackageId_.size(); i5++) {
                codedOutputStream.writeInt32NoTag(((Integer) this.classWithJvmPackageNamePackageId_.get(i5)).intValue());
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getPackageFqNameBytes()) + 0 : 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.shortClassName_.size(); i3++) {
                i2 += CodedOutputStream.computeBytesSizeNoTag(this.shortClassName_.getByteString(i3));
            }
            int size = computeBytesSize + i2 + (getShortClassNameList().size() * 1);
            int i4 = 0;
            for (int i5 = 0; i5 < this.multifileFacadeShortNameId_.size(); i5++) {
                i4 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.multifileFacadeShortNameId_.get(i5)).intValue());
            }
            int i6 = size + i4;
            if (!getMultifileFacadeShortNameIdList().isEmpty()) {
                i6 = i6 + 1 + CodedOutputStream.computeInt32SizeNoTag(i4);
            }
            this.multifileFacadeShortNameIdMemoizedSerializedSize = i4;
            int i7 = 0;
            for (int i8 = 0; i8 < this.multifileFacadeShortName_.size(); i8++) {
                i7 += CodedOutputStream.computeBytesSizeNoTag(this.multifileFacadeShortName_.getByteString(i8));
            }
            int size2 = i6 + i7 + (getMultifileFacadeShortNameList().size() * 1);
            int i9 = 0;
            for (int i10 = 0; i10 < this.classWithJvmPackageNameShortName_.size(); i10++) {
                i9 += CodedOutputStream.computeBytesSizeNoTag(this.classWithJvmPackageNameShortName_.getByteString(i10));
            }
            int size3 = size2 + i9 + (getClassWithJvmPackageNameShortNameList().size() * 1);
            int i11 = 0;
            for (int i12 = 0; i12 < this.classWithJvmPackageNamePackageId_.size(); i12++) {
                i11 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.classWithJvmPackageNamePackageId_.get(i12)).intValue());
            }
            int i13 = size3 + i11;
            if (!getClassWithJvmPackageNamePackageIdList().isEmpty()) {
                i13 = i13 + 1 + CodedOutputStream.computeInt32SizeNoTag(i11);
            }
            this.classWithJvmPackageNamePackageIdMemoizedSerializedSize = i11;
            int size4 = i13 + this.unknownFields.size();
            this.memoizedSerializedSize = size4;
            return size4;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(PackageParts packageParts) {
            return newBuilder().mergeFrom(packageParts);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface PackagePartsOrBuilder extends MessageLiteOrBuilder {
    }
}
