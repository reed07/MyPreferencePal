package kotlin.reflect.jvm.internal.impl.metadata;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.brightcove.player.C;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractParser;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString.Output;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringArrayList;
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder;
import kotlin.reflect.jvm.internal.impl.protobuf.Parser;
import kotlin.reflect.jvm.internal.impl.protobuf.ProtocolStringList;

public final class ProtoBuf {

    public static final class Annotation extends GeneratedMessageLite implements AnnotationOrBuilder {
        public static Parser<Annotation> PARSER = new AbstractParser<Annotation>() {
            public Annotation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Annotation(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Annotation defaultInstance = new Annotation(true);
        /* access modifiers changed from: private */
        public List<Argument> argument_;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int id_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;

        public static final class Argument extends GeneratedMessageLite implements ArgumentOrBuilder {
            public static Parser<Argument> PARSER = new AbstractParser<Argument>() {
                public Argument parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Argument(codedInputStream, extensionRegistryLite);
                }
            };
            private static final Argument defaultInstance = new Argument(true);
            /* access modifiers changed from: private */
            public int bitField0_;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            /* access modifiers changed from: private */
            public int nameId_;
            /* access modifiers changed from: private */
            public final ByteString unknownFields;
            /* access modifiers changed from: private */
            public Value value_;

            public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<Argument, Builder> implements ArgumentOrBuilder {
                private int bitField0_;
                private int nameId_;
                private Value value_ = Value.getDefaultInstance();

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

                public Argument getDefaultInstanceForType() {
                    return Argument.getDefaultInstance();
                }

                public Argument build() {
                    Argument buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public Argument buildPartial() {
                    Argument argument = new Argument((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                    int i = this.bitField0_;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    argument.nameId_ = this.nameId_;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    argument.value_ = this.value_;
                    argument.bitField0_ = i2;
                    return argument;
                }

                public Builder mergeFrom(Argument argument) {
                    if (argument == Argument.getDefaultInstance()) {
                        return this;
                    }
                    if (argument.hasNameId()) {
                        setNameId(argument.getNameId());
                    }
                    if (argument.hasValue()) {
                        mergeValue(argument.getValue());
                    }
                    setUnknownFields(getUnknownFields().concat(argument.unknownFields));
                    return this;
                }

                public final boolean isInitialized() {
                    if (hasNameId() && hasValue() && getValue().isInitialized()) {
                        return true;
                    }
                    return false;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    Argument argument;
                    Argument argument2 = null;
                    try {
                        Argument argument3 = (Argument) Argument.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (argument3 != null) {
                            mergeFrom(argument3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        argument = (Argument) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        argument2 = argument;
                    }
                    if (argument2 != null) {
                        mergeFrom(argument2);
                    }
                    throw th;
                }

                public boolean hasNameId() {
                    return (this.bitField0_ & 1) == 1;
                }

                public Builder setNameId(int i) {
                    this.bitField0_ |= 1;
                    this.nameId_ = i;
                    return this;
                }

                public boolean hasValue() {
                    return (this.bitField0_ & 2) == 2;
                }

                public Value getValue() {
                    return this.value_;
                }

                public Builder mergeValue(Value value) {
                    if ((this.bitField0_ & 2) != 2 || this.value_ == Value.getDefaultInstance()) {
                        this.value_ = value;
                    } else {
                        this.value_ = Value.newBuilder(this.value_).mergeFrom(value).buildPartial();
                    }
                    this.bitField0_ |= 2;
                    return this;
                }
            }

            public static final class Value extends GeneratedMessageLite implements ValueOrBuilder {
                public static Parser<Value> PARSER = new AbstractParser<Value>() {
                    public Value parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                        return new Value(codedInputStream, extensionRegistryLite);
                    }
                };
                private static final Value defaultInstance = new Value(true);
                /* access modifiers changed from: private */
                public Annotation annotation_;
                /* access modifiers changed from: private */
                public int arrayDimensionCount_;
                /* access modifiers changed from: private */
                public List<Value> arrayElement_;
                /* access modifiers changed from: private */
                public int bitField0_;
                /* access modifiers changed from: private */
                public int classId_;
                /* access modifiers changed from: private */
                public double doubleValue_;
                /* access modifiers changed from: private */
                public int enumValueId_;
                /* access modifiers changed from: private */
                public int flags_;
                /* access modifiers changed from: private */
                public float floatValue_;
                /* access modifiers changed from: private */
                public long intValue_;
                private byte memoizedIsInitialized;
                private int memoizedSerializedSize;
                /* access modifiers changed from: private */
                public int stringValue_;
                /* access modifiers changed from: private */
                public Type type_;
                /* access modifiers changed from: private */
                public final ByteString unknownFields;

                public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<Value, Builder> implements ValueOrBuilder {
                    private Annotation annotation_ = Annotation.getDefaultInstance();
                    private int arrayDimensionCount_;
                    private List<Value> arrayElement_ = Collections.emptyList();
                    private int bitField0_;
                    private int classId_;
                    private double doubleValue_;
                    private int enumValueId_;
                    private int flags_;
                    private float floatValue_;
                    private long intValue_;
                    private int stringValue_;
                    private Type type_ = Type.BYTE;

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

                    public Value getDefaultInstanceForType() {
                        return Value.getDefaultInstance();
                    }

                    public Value build() {
                        Value buildPartial = buildPartial();
                        if (buildPartial.isInitialized()) {
                            return buildPartial;
                        }
                        throw newUninitializedMessageException(buildPartial);
                    }

                    public Value buildPartial() {
                        Value value = new Value((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                        int i = this.bitField0_;
                        int i2 = 1;
                        if ((i & 1) != 1) {
                            i2 = 0;
                        }
                        value.type_ = this.type_;
                        if ((i & 2) == 2) {
                            i2 |= 2;
                        }
                        value.intValue_ = this.intValue_;
                        if ((i & 4) == 4) {
                            i2 |= 4;
                        }
                        value.floatValue_ = this.floatValue_;
                        if ((i & 8) == 8) {
                            i2 |= 8;
                        }
                        value.doubleValue_ = this.doubleValue_;
                        if ((i & 16) == 16) {
                            i2 |= 16;
                        }
                        value.stringValue_ = this.stringValue_;
                        if ((i & 32) == 32) {
                            i2 |= 32;
                        }
                        value.classId_ = this.classId_;
                        if ((i & 64) == 64) {
                            i2 |= 64;
                        }
                        value.enumValueId_ = this.enumValueId_;
                        if ((i & 128) == 128) {
                            i2 |= 128;
                        }
                        value.annotation_ = this.annotation_;
                        if ((this.bitField0_ & 256) == 256) {
                            this.arrayElement_ = Collections.unmodifiableList(this.arrayElement_);
                            this.bitField0_ &= -257;
                        }
                        value.arrayElement_ = this.arrayElement_;
                        if ((i & 512) == 512) {
                            i2 |= 256;
                        }
                        value.arrayDimensionCount_ = this.arrayDimensionCount_;
                        if ((i & 1024) == 1024) {
                            i2 |= 512;
                        }
                        value.flags_ = this.flags_;
                        value.bitField0_ = i2;
                        return value;
                    }

                    public Builder mergeFrom(Value value) {
                        if (value == Value.getDefaultInstance()) {
                            return this;
                        }
                        if (value.hasType()) {
                            setType(value.getType());
                        }
                        if (value.hasIntValue()) {
                            setIntValue(value.getIntValue());
                        }
                        if (value.hasFloatValue()) {
                            setFloatValue(value.getFloatValue());
                        }
                        if (value.hasDoubleValue()) {
                            setDoubleValue(value.getDoubleValue());
                        }
                        if (value.hasStringValue()) {
                            setStringValue(value.getStringValue());
                        }
                        if (value.hasClassId()) {
                            setClassId(value.getClassId());
                        }
                        if (value.hasEnumValueId()) {
                            setEnumValueId(value.getEnumValueId());
                        }
                        if (value.hasAnnotation()) {
                            mergeAnnotation(value.getAnnotation());
                        }
                        if (!value.arrayElement_.isEmpty()) {
                            if (this.arrayElement_.isEmpty()) {
                                this.arrayElement_ = value.arrayElement_;
                                this.bitField0_ &= -257;
                            } else {
                                ensureArrayElementIsMutable();
                                this.arrayElement_.addAll(value.arrayElement_);
                            }
                        }
                        if (value.hasArrayDimensionCount()) {
                            setArrayDimensionCount(value.getArrayDimensionCount());
                        }
                        if (value.hasFlags()) {
                            setFlags(value.getFlags());
                        }
                        setUnknownFields(getUnknownFields().concat(value.unknownFields));
                        return this;
                    }

                    public final boolean isInitialized() {
                        if (hasAnnotation() && !getAnnotation().isInitialized()) {
                            return false;
                        }
                        for (int i = 0; i < getArrayElementCount(); i++) {
                            if (!getArrayElement(i).isInitialized()) {
                                return false;
                            }
                        }
                        return true;
                    }

                    public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                        Value value;
                        Value value2 = null;
                        try {
                            Value value3 = (Value) Value.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                            if (value3 != null) {
                                mergeFrom(value3);
                            }
                            return this;
                        } catch (InvalidProtocolBufferException e) {
                            value = (Value) e.getUnfinishedMessage();
                            throw e;
                        } catch (Throwable th) {
                            th = th;
                            value2 = value;
                        }
                        if (value2 != null) {
                            mergeFrom(value2);
                        }
                        throw th;
                    }

                    public Builder setType(Type type) {
                        if (type != null) {
                            this.bitField0_ |= 1;
                            this.type_ = type;
                            return this;
                        }
                        throw new NullPointerException();
                    }

                    public Builder setIntValue(long j) {
                        this.bitField0_ |= 2;
                        this.intValue_ = j;
                        return this;
                    }

                    public Builder setFloatValue(float f) {
                        this.bitField0_ |= 4;
                        this.floatValue_ = f;
                        return this;
                    }

                    public Builder setDoubleValue(double d) {
                        this.bitField0_ |= 8;
                        this.doubleValue_ = d;
                        return this;
                    }

                    public Builder setStringValue(int i) {
                        this.bitField0_ |= 16;
                        this.stringValue_ = i;
                        return this;
                    }

                    public Builder setClassId(int i) {
                        this.bitField0_ |= 32;
                        this.classId_ = i;
                        return this;
                    }

                    public Builder setEnumValueId(int i) {
                        this.bitField0_ |= 64;
                        this.enumValueId_ = i;
                        return this;
                    }

                    public boolean hasAnnotation() {
                        return (this.bitField0_ & 128) == 128;
                    }

                    public Annotation getAnnotation() {
                        return this.annotation_;
                    }

                    public Builder mergeAnnotation(Annotation annotation) {
                        if ((this.bitField0_ & 128) != 128 || this.annotation_ == Annotation.getDefaultInstance()) {
                            this.annotation_ = annotation;
                        } else {
                            this.annotation_ = Annotation.newBuilder(this.annotation_).mergeFrom(annotation).buildPartial();
                        }
                        this.bitField0_ |= 128;
                        return this;
                    }

                    private void ensureArrayElementIsMutable() {
                        if ((this.bitField0_ & 256) != 256) {
                            this.arrayElement_ = new ArrayList(this.arrayElement_);
                            this.bitField0_ |= 256;
                        }
                    }

                    public int getArrayElementCount() {
                        return this.arrayElement_.size();
                    }

                    public Value getArrayElement(int i) {
                        return (Value) this.arrayElement_.get(i);
                    }

                    public Builder setArrayDimensionCount(int i) {
                        this.bitField0_ |= 512;
                        this.arrayDimensionCount_ = i;
                        return this;
                    }

                    public Builder setFlags(int i) {
                        this.bitField0_ |= 1024;
                        this.flags_ = i;
                        return this;
                    }
                }

                public enum Type implements EnumLite {
                    BYTE(0, 0),
                    CHAR(1, 1),
                    SHORT(2, 2),
                    INT(3, 3),
                    LONG(4, 4),
                    FLOAT(5, 5),
                    DOUBLE(6, 6),
                    BOOLEAN(7, 7),
                    STRING(8, 8),
                    CLASS(9, 9),
                    ENUM(10, 10),
                    ANNOTATION(11, 11),
                    ARRAY(12, 12);
                    
                    private static EnumLiteMap<Type> internalValueMap;
                    private final int value;

                    static {
                        internalValueMap = new EnumLiteMap<Type>() {
                            public Type findValueByNumber(int i) {
                                return Type.valueOf(i);
                            }
                        };
                    }

                    public final int getNumber() {
                        return this.value;
                    }

                    public static Type valueOf(int i) {
                        switch (i) {
                            case 0:
                                return BYTE;
                            case 1:
                                return CHAR;
                            case 2:
                                return SHORT;
                            case 3:
                                return INT;
                            case 4:
                                return LONG;
                            case 5:
                                return FLOAT;
                            case 6:
                                return DOUBLE;
                            case 7:
                                return BOOLEAN;
                            case 8:
                                return STRING;
                            case 9:
                                return CLASS;
                            case 10:
                                return ENUM;
                            case 11:
                                return ANNOTATION;
                            case 12:
                                return ARRAY;
                            default:
                                return null;
                        }
                    }

                    private Type(int i, int i2) {
                        this.value = i2;
                    }
                }

                private Value(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
                    super(builder);
                    this.memoizedIsInitialized = -1;
                    this.memoizedSerializedSize = -1;
                    this.unknownFields = builder.getUnknownFields();
                }

                private Value(boolean z) {
                    this.memoizedIsInitialized = -1;
                    this.memoizedSerializedSize = -1;
                    this.unknownFields = ByteString.EMPTY;
                }

                public static Value getDefaultInstance() {
                    return defaultInstance;
                }

                public Value getDefaultInstanceForType() {
                    return defaultInstance;
                }

                private Value(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            switch (readTag) {
                                case 0:
                                    z = true;
                                    break;
                                case 8:
                                    int readEnum = codedInputStream.readEnum();
                                    Type valueOf = Type.valueOf(readEnum);
                                    if (valueOf != null) {
                                        this.bitField0_ |= 1;
                                        this.type_ = valueOf;
                                        break;
                                    } else {
                                        newInstance.writeRawVarint32(readTag);
                                        newInstance.writeRawVarint32(readEnum);
                                        break;
                                    }
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.intValue_ = codedInputStream.readSInt64();
                                    break;
                                case 29:
                                    this.bitField0_ |= 4;
                                    this.floatValue_ = codedInputStream.readFloat();
                                    break;
                                case 33:
                                    this.bitField0_ |= 8;
                                    this.doubleValue_ = codedInputStream.readDouble();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.stringValue_ = codedInputStream.readInt32();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.classId_ = codedInputStream.readInt32();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.enumValueId_ = codedInputStream.readInt32();
                                    break;
                                case 66:
                                    Builder builder = null;
                                    if ((this.bitField0_ & 128) == 128) {
                                        builder = this.annotation_.toBuilder();
                                    }
                                    this.annotation_ = (Annotation) codedInputStream.readMessage(Annotation.PARSER, extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.annotation_);
                                        this.annotation_ = builder.buildPartial();
                                    }
                                    this.bitField0_ |= 128;
                                    break;
                                case 74:
                                    if (!(z2 & true)) {
                                        this.arrayElement_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.arrayElement_.add(codedInputStream.readMessage(PARSER, extensionRegistryLite));
                                    break;
                                case 80:
                                    this.bitField0_ |= 512;
                                    this.flags_ = codedInputStream.readInt32();
                                    break;
                                case 88:
                                    this.bitField0_ |= 256;
                                    this.arrayDimensionCount_ = codedInputStream.readInt32();
                                    break;
                                default:
                                    if (parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                                        break;
                                    } else {
                                        z = true;
                                        break;
                                    }
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                        } catch (Throwable th) {
                            if (z2 & true) {
                                this.arrayElement_ = Collections.unmodifiableList(this.arrayElement_);
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
                        this.arrayElement_ = Collections.unmodifiableList(this.arrayElement_);
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

                public Parser<Value> getParserForType() {
                    return PARSER;
                }

                public boolean hasType() {
                    return (this.bitField0_ & 1) == 1;
                }

                public Type getType() {
                    return this.type_;
                }

                public boolean hasIntValue() {
                    return (this.bitField0_ & 2) == 2;
                }

                public long getIntValue() {
                    return this.intValue_;
                }

                public boolean hasFloatValue() {
                    return (this.bitField0_ & 4) == 4;
                }

                public float getFloatValue() {
                    return this.floatValue_;
                }

                public boolean hasDoubleValue() {
                    return (this.bitField0_ & 8) == 8;
                }

                public double getDoubleValue() {
                    return this.doubleValue_;
                }

                public boolean hasStringValue() {
                    return (this.bitField0_ & 16) == 16;
                }

                public int getStringValue() {
                    return this.stringValue_;
                }

                public boolean hasClassId() {
                    return (this.bitField0_ & 32) == 32;
                }

                public int getClassId() {
                    return this.classId_;
                }

                public boolean hasEnumValueId() {
                    return (this.bitField0_ & 64) == 64;
                }

                public int getEnumValueId() {
                    return this.enumValueId_;
                }

                public boolean hasAnnotation() {
                    return (this.bitField0_ & 128) == 128;
                }

                public Annotation getAnnotation() {
                    return this.annotation_;
                }

                public List<Value> getArrayElementList() {
                    return this.arrayElement_;
                }

                public int getArrayElementCount() {
                    return this.arrayElement_.size();
                }

                public Value getArrayElement(int i) {
                    return (Value) this.arrayElement_.get(i);
                }

                public boolean hasArrayDimensionCount() {
                    return (this.bitField0_ & 256) == 256;
                }

                public int getArrayDimensionCount() {
                    return this.arrayDimensionCount_;
                }

                public boolean hasFlags() {
                    return (this.bitField0_ & 512) == 512;
                }

                public int getFlags() {
                    return this.flags_;
                }

                private void initFields() {
                    this.type_ = Type.BYTE;
                    this.intValue_ = 0;
                    this.floatValue_ = BitmapDescriptorFactory.HUE_RED;
                    this.doubleValue_ = 0.0d;
                    this.stringValue_ = 0;
                    this.classId_ = 0;
                    this.enumValueId_ = 0;
                    this.annotation_ = Annotation.getDefaultInstance();
                    this.arrayElement_ = Collections.emptyList();
                    this.arrayDimensionCount_ = 0;
                    this.flags_ = 0;
                }

                public final boolean isInitialized() {
                    byte b = this.memoizedIsInitialized;
                    if (b == 1) {
                        return true;
                    }
                    if (b == 0) {
                        return false;
                    }
                    if (!hasAnnotation() || getAnnotation().isInitialized()) {
                        for (int i = 0; i < getArrayElementCount(); i++) {
                            if (!getArrayElement(i).isInitialized()) {
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
                    if ((this.bitField0_ & 1) == 1) {
                        codedOutputStream.writeEnum(1, this.type_.getNumber());
                    }
                    if ((this.bitField0_ & 2) == 2) {
                        codedOutputStream.writeSInt64(2, this.intValue_);
                    }
                    if ((this.bitField0_ & 4) == 4) {
                        codedOutputStream.writeFloat(3, this.floatValue_);
                    }
                    if ((this.bitField0_ & 8) == 8) {
                        codedOutputStream.writeDouble(4, this.doubleValue_);
                    }
                    if ((this.bitField0_ & 16) == 16) {
                        codedOutputStream.writeInt32(5, this.stringValue_);
                    }
                    if ((this.bitField0_ & 32) == 32) {
                        codedOutputStream.writeInt32(6, this.classId_);
                    }
                    if ((this.bitField0_ & 64) == 64) {
                        codedOutputStream.writeInt32(7, this.enumValueId_);
                    }
                    if ((this.bitField0_ & 128) == 128) {
                        codedOutputStream.writeMessage(8, this.annotation_);
                    }
                    for (int i = 0; i < this.arrayElement_.size(); i++) {
                        codedOutputStream.writeMessage(9, (MessageLite) this.arrayElement_.get(i));
                    }
                    if ((this.bitField0_ & 512) == 512) {
                        codedOutputStream.writeInt32(10, this.flags_);
                    }
                    if ((this.bitField0_ & 256) == 256) {
                        codedOutputStream.writeInt32(11, this.arrayDimensionCount_);
                    }
                    codedOutputStream.writeRawBytes(this.unknownFields);
                }

                public int getSerializedSize() {
                    int i = this.memoizedSerializedSize;
                    if (i != -1) {
                        return i;
                    }
                    int computeEnumSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeEnumSize(1, this.type_.getNumber()) + 0 : 0;
                    if ((this.bitField0_ & 2) == 2) {
                        computeEnumSize += CodedOutputStream.computeSInt64Size(2, this.intValue_);
                    }
                    if ((this.bitField0_ & 4) == 4) {
                        computeEnumSize += CodedOutputStream.computeFloatSize(3, this.floatValue_);
                    }
                    if ((this.bitField0_ & 8) == 8) {
                        computeEnumSize += CodedOutputStream.computeDoubleSize(4, this.doubleValue_);
                    }
                    if ((this.bitField0_ & 16) == 16) {
                        computeEnumSize += CodedOutputStream.computeInt32Size(5, this.stringValue_);
                    }
                    if ((this.bitField0_ & 32) == 32) {
                        computeEnumSize += CodedOutputStream.computeInt32Size(6, this.classId_);
                    }
                    if ((this.bitField0_ & 64) == 64) {
                        computeEnumSize += CodedOutputStream.computeInt32Size(7, this.enumValueId_);
                    }
                    if ((this.bitField0_ & 128) == 128) {
                        computeEnumSize += CodedOutputStream.computeMessageSize(8, this.annotation_);
                    }
                    for (int i2 = 0; i2 < this.arrayElement_.size(); i2++) {
                        computeEnumSize += CodedOutputStream.computeMessageSize(9, (MessageLite) this.arrayElement_.get(i2));
                    }
                    if ((this.bitField0_ & 512) == 512) {
                        computeEnumSize += CodedOutputStream.computeInt32Size(10, this.flags_);
                    }
                    if ((this.bitField0_ & 256) == 256) {
                        computeEnumSize += CodedOutputStream.computeInt32Size(11, this.arrayDimensionCount_);
                    }
                    int size = computeEnumSize + this.unknownFields.size();
                    this.memoizedSerializedSize = size;
                    return size;
                }

                public static Builder newBuilder() {
                    return Builder.create();
                }

                public Builder newBuilderForType() {
                    return newBuilder();
                }

                public static Builder newBuilder(Value value) {
                    return newBuilder().mergeFrom(value);
                }

                public Builder toBuilder() {
                    return newBuilder(this);
                }
            }

            public interface ValueOrBuilder extends MessageLiteOrBuilder {
            }

            private Argument(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
                super(builder);
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = builder.getUnknownFields();
            }

            private Argument(boolean z) {
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = ByteString.EMPTY;
            }

            public static Argument getDefaultInstance() {
                return defaultInstance;
            }

            public Argument getDefaultInstanceForType() {
                return defaultInstance;
            }

            private Argument(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                initFields();
                Output newOutput = ByteString.newOutput();
                CodedOutputStream newInstance = CodedOutputStream.newInstance(newOutput, 1);
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag == 0) {
                            z = true;
                        } else if (readTag == 8) {
                            this.bitField0_ |= 1;
                            this.nameId_ = codedInputStream.readInt32();
                        } else if (readTag == 18) {
                            Builder builder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                builder = this.value_.toBuilder();
                            }
                            this.value_ = (Value) codedInputStream.readMessage(Value.PARSER, extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.value_);
                                this.value_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                            z = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
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

            public Parser<Argument> getParserForType() {
                return PARSER;
            }

            public boolean hasNameId() {
                return (this.bitField0_ & 1) == 1;
            }

            public int getNameId() {
                return this.nameId_;
            }

            public boolean hasValue() {
                return (this.bitField0_ & 2) == 2;
            }

            public Value getValue() {
                return this.value_;
            }

            private void initFields() {
                this.nameId_ = 0;
                this.value_ = Value.getDefaultInstance();
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                if (!hasNameId()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                } else if (!hasValue()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                } else if (!getValue().isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                } else {
                    this.memoizedIsInitialized = 1;
                    return true;
                }
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                getSerializedSize();
                if ((this.bitField0_ & 1) == 1) {
                    codedOutputStream.writeInt32(1, this.nameId_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    codedOutputStream.writeMessage(2, this.value_);
                }
                codedOutputStream.writeRawBytes(this.unknownFields);
            }

            public int getSerializedSize() {
                int i = this.memoizedSerializedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if ((this.bitField0_ & 1) == 1) {
                    i2 = 0 + CodedOutputStream.computeInt32Size(1, this.nameId_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    i2 += CodedOutputStream.computeMessageSize(2, this.value_);
                }
                int size = i2 + this.unknownFields.size();
                this.memoizedSerializedSize = size;
                return size;
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder(Argument argument) {
                return newBuilder().mergeFrom(argument);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }
        }

        public interface ArgumentOrBuilder extends MessageLiteOrBuilder {
        }

        public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<Annotation, Builder> implements AnnotationOrBuilder {
            private List<Argument> argument_ = Collections.emptyList();
            private int bitField0_;
            private int id_;

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

            public Annotation getDefaultInstanceForType() {
                return Annotation.getDefaultInstance();
            }

            public Annotation build() {
                Annotation buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Annotation buildPartial() {
                Annotation annotation = new Annotation((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                int i = 1;
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                annotation.id_ = this.id_;
                if ((this.bitField0_ & 2) == 2) {
                    this.argument_ = Collections.unmodifiableList(this.argument_);
                    this.bitField0_ &= -3;
                }
                annotation.argument_ = this.argument_;
                annotation.bitField0_ = i;
                return annotation;
            }

            public Builder mergeFrom(Annotation annotation) {
                if (annotation == Annotation.getDefaultInstance()) {
                    return this;
                }
                if (annotation.hasId()) {
                    setId(annotation.getId());
                }
                if (!annotation.argument_.isEmpty()) {
                    if (this.argument_.isEmpty()) {
                        this.argument_ = annotation.argument_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureArgumentIsMutable();
                        this.argument_.addAll(annotation.argument_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(annotation.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                if (!hasId()) {
                    return false;
                }
                for (int i = 0; i < getArgumentCount(); i++) {
                    if (!getArgument(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Annotation annotation;
                Annotation annotation2 = null;
                try {
                    Annotation annotation3 = (Annotation) Annotation.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (annotation3 != null) {
                        mergeFrom(annotation3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    annotation = (Annotation) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    annotation2 = annotation;
                }
                if (annotation2 != null) {
                    mergeFrom(annotation2);
                }
                throw th;
            }

            public boolean hasId() {
                return (this.bitField0_ & 1) == 1;
            }

            public Builder setId(int i) {
                this.bitField0_ |= 1;
                this.id_ = i;
                return this;
            }

            private void ensureArgumentIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.argument_ = new ArrayList(this.argument_);
                    this.bitField0_ |= 2;
                }
            }

            public int getArgumentCount() {
                return this.argument_.size();
            }

            public Argument getArgument(int i) {
                return (Argument) this.argument_.get(i);
            }
        }

        private Annotation(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Annotation(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Annotation getDefaultInstance() {
            return defaultInstance;
        }

        public Annotation getDefaultInstanceForType() {
            return defaultInstance;
        }

        private Annotation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    } else if (readTag == 8) {
                        this.bitField0_ |= 1;
                        this.id_ = codedInputStream.readInt32();
                    } else if (readTag == 18) {
                        if (!(z2 & true)) {
                            this.argument_ = new ArrayList();
                            z2 |= true;
                        }
                        this.argument_.add(codedInputStream.readMessage(Argument.PARSER, extensionRegistryLite));
                    } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.argument_ = Collections.unmodifiableList(this.argument_);
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
                this.argument_ = Collections.unmodifiableList(this.argument_);
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

        public Parser<Annotation> getParserForType() {
            return PARSER;
        }

        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getId() {
            return this.id_;
        }

        public List<Argument> getArgumentList() {
            return this.argument_;
        }

        public int getArgumentCount() {
            return this.argument_.size();
        }

        public Argument getArgument(int i) {
            return (Argument) this.argument_.get(i);
        }

        private void initFields() {
            this.id_ = 0;
            this.argument_ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasId()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (int i = 0; i < getArgumentCount(); i++) {
                if (!getArgument(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.id_);
            }
            for (int i = 0; i < this.argument_.size(); i++) {
                codedOutputStream.writeMessage(2, (MessageLite) this.argument_.get(i));
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.id_) + 0 : 0;
            for (int i2 = 0; i2 < this.argument_.size(); i2++) {
                computeInt32Size += CodedOutputStream.computeMessageSize(2, (MessageLite) this.argument_.get(i2));
            }
            int size = computeInt32Size + this.unknownFields.size();
            this.memoizedSerializedSize = size;
            return size;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Annotation annotation) {
            return newBuilder().mergeFrom(annotation);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface AnnotationOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class Class extends ExtendableMessage<Class> implements ClassOrBuilder {
        public static Parser<Class> PARSER = new AbstractParser<Class>() {
            public Class parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Class(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Class defaultInstance = new Class(true);
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int companionObjectName_;
        /* access modifiers changed from: private */
        public List<Constructor> constructor_;
        /* access modifiers changed from: private */
        public List<EnumEntry> enumEntry_;
        /* access modifiers changed from: private */
        public int flags_;
        /* access modifiers changed from: private */
        public int fqName_;
        /* access modifiers changed from: private */
        public List<Function> function_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int nestedClassNameMemoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<Integer> nestedClassName_;
        /* access modifiers changed from: private */
        public List<Property> property_;
        private int sealedSubclassFqNameMemoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<Integer> sealedSubclassFqName_;
        private int supertypeIdMemoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<Integer> supertypeId_;
        /* access modifiers changed from: private */
        public List<Type> supertype_;
        /* access modifiers changed from: private */
        public List<TypeAlias> typeAlias_;
        /* access modifiers changed from: private */
        public List<TypeParameter> typeParameter_;
        /* access modifiers changed from: private */
        public TypeTable typeTable_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;
        /* access modifiers changed from: private */
        public VersionRequirementTable versionRequirementTable_;
        /* access modifiers changed from: private */
        public List<Integer> versionRequirement_;

        public static final class Builder extends ExtendableBuilder<Class, Builder> implements ClassOrBuilder {
            private int bitField0_;
            private int companionObjectName_;
            private List<Constructor> constructor_ = Collections.emptyList();
            private List<EnumEntry> enumEntry_ = Collections.emptyList();
            private int flags_ = 6;
            private int fqName_;
            private List<Function> function_ = Collections.emptyList();
            private List<Integer> nestedClassName_ = Collections.emptyList();
            private List<Property> property_ = Collections.emptyList();
            private List<Integer> sealedSubclassFqName_ = Collections.emptyList();
            private List<Integer> supertypeId_ = Collections.emptyList();
            private List<Type> supertype_ = Collections.emptyList();
            private List<TypeAlias> typeAlias_ = Collections.emptyList();
            private List<TypeParameter> typeParameter_ = Collections.emptyList();
            private TypeTable typeTable_ = TypeTable.getDefaultInstance();
            private VersionRequirementTable versionRequirementTable_ = VersionRequirementTable.getDefaultInstance();
            private List<Integer> versionRequirement_ = Collections.emptyList();

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

            public Class getDefaultInstanceForType() {
                return Class.getDefaultInstance();
            }

            public Class build() {
                Class buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Class buildPartial() {
                Class classR = new Class((ExtendableBuilder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                classR.flags_ = this.flags_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                classR.fqName_ = this.fqName_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                classR.companionObjectName_ = this.companionObjectName_;
                if ((this.bitField0_ & 8) == 8) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    this.bitField0_ &= -9;
                }
                classR.typeParameter_ = this.typeParameter_;
                if ((this.bitField0_ & 16) == 16) {
                    this.supertype_ = Collections.unmodifiableList(this.supertype_);
                    this.bitField0_ &= -17;
                }
                classR.supertype_ = this.supertype_;
                if ((this.bitField0_ & 32) == 32) {
                    this.supertypeId_ = Collections.unmodifiableList(this.supertypeId_);
                    this.bitField0_ &= -33;
                }
                classR.supertypeId_ = this.supertypeId_;
                if ((this.bitField0_ & 64) == 64) {
                    this.nestedClassName_ = Collections.unmodifiableList(this.nestedClassName_);
                    this.bitField0_ &= -65;
                }
                classR.nestedClassName_ = this.nestedClassName_;
                if ((this.bitField0_ & 128) == 128) {
                    this.constructor_ = Collections.unmodifiableList(this.constructor_);
                    this.bitField0_ &= -129;
                }
                classR.constructor_ = this.constructor_;
                if ((this.bitField0_ & 256) == 256) {
                    this.function_ = Collections.unmodifiableList(this.function_);
                    this.bitField0_ &= -257;
                }
                classR.function_ = this.function_;
                if ((this.bitField0_ & 512) == 512) {
                    this.property_ = Collections.unmodifiableList(this.property_);
                    this.bitField0_ &= -513;
                }
                classR.property_ = this.property_;
                if ((this.bitField0_ & 1024) == 1024) {
                    this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                    this.bitField0_ &= -1025;
                }
                classR.typeAlias_ = this.typeAlias_;
                if ((this.bitField0_ & 2048) == 2048) {
                    this.enumEntry_ = Collections.unmodifiableList(this.enumEntry_);
                    this.bitField0_ &= -2049;
                }
                classR.enumEntry_ = this.enumEntry_;
                if ((this.bitField0_ & 4096) == 4096) {
                    this.sealedSubclassFqName_ = Collections.unmodifiableList(this.sealedSubclassFqName_);
                    this.bitField0_ &= -4097;
                }
                classR.sealedSubclassFqName_ = this.sealedSubclassFqName_;
                if ((i & 8192) == 8192) {
                    i2 |= 8;
                }
                classR.typeTable_ = this.typeTable_;
                if ((this.bitField0_ & C.DASH_ROLE_CAPTION_FLAG) == 16384) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= -16385;
                }
                classR.versionRequirement_ = this.versionRequirement_;
                if ((i & 32768) == 32768) {
                    i2 |= 16;
                }
                classR.versionRequirementTable_ = this.versionRequirementTable_;
                classR.bitField0_ = i2;
                return classR;
            }

            public Builder mergeFrom(Class classR) {
                if (classR == Class.getDefaultInstance()) {
                    return this;
                }
                if (classR.hasFlags()) {
                    setFlags(classR.getFlags());
                }
                if (classR.hasFqName()) {
                    setFqName(classR.getFqName());
                }
                if (classR.hasCompanionObjectName()) {
                    setCompanionObjectName(classR.getCompanionObjectName());
                }
                if (!classR.typeParameter_.isEmpty()) {
                    if (this.typeParameter_.isEmpty()) {
                        this.typeParameter_ = classR.typeParameter_;
                        this.bitField0_ &= -9;
                    } else {
                        ensureTypeParameterIsMutable();
                        this.typeParameter_.addAll(classR.typeParameter_);
                    }
                }
                if (!classR.supertype_.isEmpty()) {
                    if (this.supertype_.isEmpty()) {
                        this.supertype_ = classR.supertype_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureSupertypeIsMutable();
                        this.supertype_.addAll(classR.supertype_);
                    }
                }
                if (!classR.supertypeId_.isEmpty()) {
                    if (this.supertypeId_.isEmpty()) {
                        this.supertypeId_ = classR.supertypeId_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureSupertypeIdIsMutable();
                        this.supertypeId_.addAll(classR.supertypeId_);
                    }
                }
                if (!classR.nestedClassName_.isEmpty()) {
                    if (this.nestedClassName_.isEmpty()) {
                        this.nestedClassName_ = classR.nestedClassName_;
                        this.bitField0_ &= -65;
                    } else {
                        ensureNestedClassNameIsMutable();
                        this.nestedClassName_.addAll(classR.nestedClassName_);
                    }
                }
                if (!classR.constructor_.isEmpty()) {
                    if (this.constructor_.isEmpty()) {
                        this.constructor_ = classR.constructor_;
                        this.bitField0_ &= -129;
                    } else {
                        ensureConstructorIsMutable();
                        this.constructor_.addAll(classR.constructor_);
                    }
                }
                if (!classR.function_.isEmpty()) {
                    if (this.function_.isEmpty()) {
                        this.function_ = classR.function_;
                        this.bitField0_ &= -257;
                    } else {
                        ensureFunctionIsMutable();
                        this.function_.addAll(classR.function_);
                    }
                }
                if (!classR.property_.isEmpty()) {
                    if (this.property_.isEmpty()) {
                        this.property_ = classR.property_;
                        this.bitField0_ &= -513;
                    } else {
                        ensurePropertyIsMutable();
                        this.property_.addAll(classR.property_);
                    }
                }
                if (!classR.typeAlias_.isEmpty()) {
                    if (this.typeAlias_.isEmpty()) {
                        this.typeAlias_ = classR.typeAlias_;
                        this.bitField0_ &= -1025;
                    } else {
                        ensureTypeAliasIsMutable();
                        this.typeAlias_.addAll(classR.typeAlias_);
                    }
                }
                if (!classR.enumEntry_.isEmpty()) {
                    if (this.enumEntry_.isEmpty()) {
                        this.enumEntry_ = classR.enumEntry_;
                        this.bitField0_ &= -2049;
                    } else {
                        ensureEnumEntryIsMutable();
                        this.enumEntry_.addAll(classR.enumEntry_);
                    }
                }
                if (!classR.sealedSubclassFqName_.isEmpty()) {
                    if (this.sealedSubclassFqName_.isEmpty()) {
                        this.sealedSubclassFqName_ = classR.sealedSubclassFqName_;
                        this.bitField0_ &= -4097;
                    } else {
                        ensureSealedSubclassFqNameIsMutable();
                        this.sealedSubclassFqName_.addAll(classR.sealedSubclassFqName_);
                    }
                }
                if (classR.hasTypeTable()) {
                    mergeTypeTable(classR.getTypeTable());
                }
                if (!classR.versionRequirement_.isEmpty()) {
                    if (this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = classR.versionRequirement_;
                        this.bitField0_ &= -16385;
                    } else {
                        ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(classR.versionRequirement_);
                    }
                }
                if (classR.hasVersionRequirementTable()) {
                    mergeVersionRequirementTable(classR.getVersionRequirementTable());
                }
                mergeExtensionFields(classR);
                setUnknownFields(getUnknownFields().concat(classR.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                if (!hasFqName()) {
                    return false;
                }
                for (int i = 0; i < getTypeParameterCount(); i++) {
                    if (!getTypeParameter(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getSupertypeCount(); i2++) {
                    if (!getSupertype(i2).isInitialized()) {
                        return false;
                    }
                }
                for (int i3 = 0; i3 < getConstructorCount(); i3++) {
                    if (!getConstructor(i3).isInitialized()) {
                        return false;
                    }
                }
                for (int i4 = 0; i4 < getFunctionCount(); i4++) {
                    if (!getFunction(i4).isInitialized()) {
                        return false;
                    }
                }
                for (int i5 = 0; i5 < getPropertyCount(); i5++) {
                    if (!getProperty(i5).isInitialized()) {
                        return false;
                    }
                }
                for (int i6 = 0; i6 < getTypeAliasCount(); i6++) {
                    if (!getTypeAlias(i6).isInitialized()) {
                        return false;
                    }
                }
                for (int i7 = 0; i7 < getEnumEntryCount(); i7++) {
                    if (!getEnumEntry(i7).isInitialized()) {
                        return false;
                    }
                }
                if ((!hasTypeTable() || getTypeTable().isInitialized()) && extensionsAreInitialized()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Class classR;
                Class classR2 = null;
                try {
                    Class classR3 = (Class) Class.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (classR3 != null) {
                        mergeFrom(classR3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    classR = (Class) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    classR2 = classR;
                }
                if (classR2 != null) {
                    mergeFrom(classR2);
                }
                throw th;
            }

            public Builder setFlags(int i) {
                this.bitField0_ |= 1;
                this.flags_ = i;
                return this;
            }

            public boolean hasFqName() {
                return (this.bitField0_ & 2) == 2;
            }

            public Builder setFqName(int i) {
                this.bitField0_ |= 2;
                this.fqName_ = i;
                return this;
            }

            public Builder setCompanionObjectName(int i) {
                this.bitField0_ |= 4;
                this.companionObjectName_ = i;
                return this;
            }

            private void ensureTypeParameterIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.typeParameter_ = new ArrayList(this.typeParameter_);
                    this.bitField0_ |= 8;
                }
            }

            public int getTypeParameterCount() {
                return this.typeParameter_.size();
            }

            public TypeParameter getTypeParameter(int i) {
                return (TypeParameter) this.typeParameter_.get(i);
            }

            private void ensureSupertypeIsMutable() {
                if ((this.bitField0_ & 16) != 16) {
                    this.supertype_ = new ArrayList(this.supertype_);
                    this.bitField0_ |= 16;
                }
            }

            public int getSupertypeCount() {
                return this.supertype_.size();
            }

            public Type getSupertype(int i) {
                return (Type) this.supertype_.get(i);
            }

            private void ensureSupertypeIdIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.supertypeId_ = new ArrayList(this.supertypeId_);
                    this.bitField0_ |= 32;
                }
            }

            private void ensureNestedClassNameIsMutable() {
                if ((this.bitField0_ & 64) != 64) {
                    this.nestedClassName_ = new ArrayList(this.nestedClassName_);
                    this.bitField0_ |= 64;
                }
            }

            private void ensureConstructorIsMutable() {
                if ((this.bitField0_ & 128) != 128) {
                    this.constructor_ = new ArrayList(this.constructor_);
                    this.bitField0_ |= 128;
                }
            }

            public int getConstructorCount() {
                return this.constructor_.size();
            }

            public Constructor getConstructor(int i) {
                return (Constructor) this.constructor_.get(i);
            }

            private void ensureFunctionIsMutable() {
                if ((this.bitField0_ & 256) != 256) {
                    this.function_ = new ArrayList(this.function_);
                    this.bitField0_ |= 256;
                }
            }

            public int getFunctionCount() {
                return this.function_.size();
            }

            public Function getFunction(int i) {
                return (Function) this.function_.get(i);
            }

            private void ensurePropertyIsMutable() {
                if ((this.bitField0_ & 512) != 512) {
                    this.property_ = new ArrayList(this.property_);
                    this.bitField0_ |= 512;
                }
            }

            public int getPropertyCount() {
                return this.property_.size();
            }

            public Property getProperty(int i) {
                return (Property) this.property_.get(i);
            }

            private void ensureTypeAliasIsMutable() {
                if ((this.bitField0_ & 1024) != 1024) {
                    this.typeAlias_ = new ArrayList(this.typeAlias_);
                    this.bitField0_ |= 1024;
                }
            }

            public int getTypeAliasCount() {
                return this.typeAlias_.size();
            }

            public TypeAlias getTypeAlias(int i) {
                return (TypeAlias) this.typeAlias_.get(i);
            }

            private void ensureEnumEntryIsMutable() {
                if ((this.bitField0_ & 2048) != 2048) {
                    this.enumEntry_ = new ArrayList(this.enumEntry_);
                    this.bitField0_ |= 2048;
                }
            }

            public int getEnumEntryCount() {
                return this.enumEntry_.size();
            }

            public EnumEntry getEnumEntry(int i) {
                return (EnumEntry) this.enumEntry_.get(i);
            }

            private void ensureSealedSubclassFqNameIsMutable() {
                if ((this.bitField0_ & 4096) != 4096) {
                    this.sealedSubclassFqName_ = new ArrayList(this.sealedSubclassFqName_);
                    this.bitField0_ |= 4096;
                }
            }

            public boolean hasTypeTable() {
                return (this.bitField0_ & 8192) == 8192;
            }

            public TypeTable getTypeTable() {
                return this.typeTable_;
            }

            public Builder mergeTypeTable(TypeTable typeTable) {
                if ((this.bitField0_ & 8192) != 8192 || this.typeTable_ == TypeTable.getDefaultInstance()) {
                    this.typeTable_ = typeTable;
                } else {
                    this.typeTable_ = TypeTable.newBuilder(this.typeTable_).mergeFrom(typeTable).buildPartial();
                }
                this.bitField0_ |= 8192;
                return this;
            }

            private void ensureVersionRequirementIsMutable() {
                if ((this.bitField0_ & C.DASH_ROLE_CAPTION_FLAG) != 16384) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= C.DASH_ROLE_CAPTION_FLAG;
                }
            }

            public Builder mergeVersionRequirementTable(VersionRequirementTable versionRequirementTable) {
                if ((this.bitField0_ & 32768) != 32768 || this.versionRequirementTable_ == VersionRequirementTable.getDefaultInstance()) {
                    this.versionRequirementTable_ = versionRequirementTable;
                } else {
                    this.versionRequirementTable_ = VersionRequirementTable.newBuilder(this.versionRequirementTable_).mergeFrom(versionRequirementTable).buildPartial();
                }
                this.bitField0_ |= 32768;
                return this;
            }
        }

        public enum Kind implements EnumLite {
            CLASS(0, 0),
            INTERFACE(1, 1),
            ENUM_CLASS(2, 2),
            ENUM_ENTRY(3, 3),
            ANNOTATION_CLASS(4, 4),
            OBJECT(5, 5),
            COMPANION_OBJECT(6, 6);
            
            private static EnumLiteMap<Kind> internalValueMap;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<Kind>() {
                    public Kind findValueByNumber(int i) {
                        return Kind.valueOf(i);
                    }
                };
            }

            public final int getNumber() {
                return this.value;
            }

            public static Kind valueOf(int i) {
                switch (i) {
                    case 0:
                        return CLASS;
                    case 1:
                        return INTERFACE;
                    case 2:
                        return ENUM_CLASS;
                    case 3:
                        return ENUM_ENTRY;
                    case 4:
                        return ANNOTATION_CLASS;
                    case 5:
                        return OBJECT;
                    case 6:
                        return COMPANION_OBJECT;
                    default:
                        return null;
                }
            }

            private Kind(int i, int i2) {
                this.value = i2;
            }
        }

        private Class(ExtendableBuilder<Class, ?> extendableBuilder) {
            super(extendableBuilder);
            this.supertypeIdMemoizedSerializedSize = -1;
            this.nestedClassNameMemoizedSerializedSize = -1;
            this.sealedSubclassFqNameMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private Class(boolean z) {
            this.supertypeIdMemoizedSerializedSize = -1;
            this.nestedClassNameMemoizedSerializedSize = -1;
            this.sealedSubclassFqNameMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Class getDefaultInstance() {
            return defaultInstance;
        }

        public Class getDefaultInstanceForType() {
            return defaultInstance;
        }

        private Class(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            CodedInputStream codedInputStream2 = codedInputStream;
            ExtensionRegistryLite extensionRegistryLite2 = extensionRegistryLite;
            this.supertypeIdMemoizedSerializedSize = -1;
            this.nestedClassNameMemoizedSerializedSize = -1;
            this.sealedSubclassFqNameMemoizedSerializedSize = -1;
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
                    switch (readTag) {
                        case 0:
                            z = true;
                            break;
                        case 8:
                            this.bitField0_ |= 1;
                            this.flags_ = codedInputStream.readInt32();
                            break;
                        case 16:
                            if (!(z2 & true)) {
                                this.supertypeId_ = new ArrayList();
                                z2 |= true;
                            }
                            this.supertypeId_.add(Integer.valueOf(codedInputStream.readInt32()));
                            break;
                        case 18:
                            int pushLimit = codedInputStream2.pushLimit(codedInputStream.readRawVarint32());
                            if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                                this.supertypeId_ = new ArrayList();
                                z2 |= true;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.supertypeId_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream2.popLimit(pushLimit);
                            break;
                        case 24:
                            this.bitField0_ |= 2;
                            this.fqName_ = codedInputStream.readInt32();
                            break;
                        case 32:
                            this.bitField0_ |= 4;
                            this.companionObjectName_ = codedInputStream.readInt32();
                            break;
                        case 42:
                            if (!(z2 & true)) {
                                this.typeParameter_ = new ArrayList();
                                z2 |= true;
                            }
                            this.typeParameter_.add(codedInputStream2.readMessage(TypeParameter.PARSER, extensionRegistryLite2));
                            break;
                        case 50:
                            if (!(z2 & true)) {
                                this.supertype_ = new ArrayList();
                                z2 |= true;
                            }
                            this.supertype_.add(codedInputStream2.readMessage(Type.PARSER, extensionRegistryLite2));
                            break;
                        case 56:
                            if (!(z2 & true)) {
                                this.nestedClassName_ = new ArrayList();
                                z2 |= true;
                            }
                            this.nestedClassName_.add(Integer.valueOf(codedInputStream.readInt32()));
                            break;
                        case 58:
                            int pushLimit2 = codedInputStream2.pushLimit(codedInputStream.readRawVarint32());
                            if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                                this.nestedClassName_ = new ArrayList();
                                z2 |= true;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.nestedClassName_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream2.popLimit(pushLimit2);
                            break;
                        case 66:
                            if (!(z2 & true)) {
                                this.constructor_ = new ArrayList();
                                z2 |= true;
                            }
                            this.constructor_.add(codedInputStream2.readMessage(Constructor.PARSER, extensionRegistryLite2));
                            break;
                        case 74:
                            if (!(z2 & true)) {
                                this.function_ = new ArrayList();
                                z2 |= true;
                            }
                            this.function_.add(codedInputStream2.readMessage(Function.PARSER, extensionRegistryLite2));
                            break;
                        case 82:
                            if (!(z2 & true)) {
                                this.property_ = new ArrayList();
                                z2 |= true;
                            }
                            this.property_.add(codedInputStream2.readMessage(Property.PARSER, extensionRegistryLite2));
                            break;
                        case 90:
                            if (!(z2 & true)) {
                                this.typeAlias_ = new ArrayList();
                                z2 |= true;
                            }
                            this.typeAlias_.add(codedInputStream2.readMessage(TypeAlias.PARSER, extensionRegistryLite2));
                            break;
                        case 106:
                            if (!(z2 & true)) {
                                this.enumEntry_ = new ArrayList();
                                z2 |= true;
                            }
                            this.enumEntry_.add(codedInputStream2.readMessage(EnumEntry.PARSER, extensionRegistryLite2));
                            break;
                        case 128:
                            if (!(z2 & true)) {
                                this.sealedSubclassFqName_ = new ArrayList();
                                z2 |= true;
                            }
                            this.sealedSubclassFqName_.add(Integer.valueOf(codedInputStream.readInt32()));
                            break;
                        case 130:
                            int pushLimit3 = codedInputStream2.pushLimit(codedInputStream.readRawVarint32());
                            if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                                this.sealedSubclassFqName_ = new ArrayList();
                                z2 |= true;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.sealedSubclassFqName_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream2.popLimit(pushLimit3);
                            break;
                        case 242:
                            Builder builder = (this.bitField0_ & 8) == 8 ? this.typeTable_.toBuilder() : null;
                            this.typeTable_ = (TypeTable) codedInputStream2.readMessage(TypeTable.PARSER, extensionRegistryLite2);
                            if (builder != null) {
                                builder.mergeFrom(this.typeTable_);
                                this.typeTable_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 8;
                            break;
                        case 248:
                            if (!(z2 & true)) {
                                this.versionRequirement_ = new ArrayList();
                                z2 |= true;
                            }
                            this.versionRequirement_.add(Integer.valueOf(codedInputStream.readInt32()));
                            break;
                        case Callback.DEFAULT_SWIPE_ANIMATION_DURATION /*250*/:
                            int pushLimit4 = codedInputStream2.pushLimit(codedInputStream.readRawVarint32());
                            if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                                this.versionRequirement_ = new ArrayList();
                                z2 |= true;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.versionRequirement_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream2.popLimit(pushLimit4);
                            break;
                        case 258:
                            Builder builder2 = (this.bitField0_ & 16) == 16 ? this.versionRequirementTable_.toBuilder() : null;
                            this.versionRequirementTable_ = (VersionRequirementTable) codedInputStream2.readMessage(VersionRequirementTable.PARSER, extensionRegistryLite2);
                            if (builder2 != null) {
                                builder2.mergeFrom(this.versionRequirementTable_);
                                this.versionRequirementTable_ = builder2.buildPartial();
                            }
                            this.bitField0_ |= 16;
                            break;
                        default:
                            if (parseUnknownField(codedInputStream2, newInstance, extensionRegistryLite2, readTag)) {
                                break;
                            } else {
                                z = true;
                                break;
                            }
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (z2 & true) {
                        this.supertypeId_ = Collections.unmodifiableList(this.supertypeId_);
                    }
                    if (z2 & true) {
                        this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    }
                    if (z2 & true) {
                        this.supertype_ = Collections.unmodifiableList(this.supertype_);
                    }
                    if (z2 & true) {
                        this.nestedClassName_ = Collections.unmodifiableList(this.nestedClassName_);
                    }
                    if (z2 & true) {
                        this.constructor_ = Collections.unmodifiableList(this.constructor_);
                    }
                    if (z2 & true) {
                        this.function_ = Collections.unmodifiableList(this.function_);
                    }
                    if (z2 & true) {
                        this.property_ = Collections.unmodifiableList(this.property_);
                    }
                    if (z2 & true) {
                        this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                    }
                    if (z2 & true) {
                        this.enumEntry_ = Collections.unmodifiableList(this.enumEntry_);
                    }
                    if (z2 & true) {
                        this.sealedSubclassFqName_ = Collections.unmodifiableList(this.sealedSubclassFqName_);
                    }
                    if (z2 & true) {
                        this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    }
                    try {
                        newInstance.flush();
                    } catch (IOException unused) {
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        this.unknownFields = newOutput.toByteString();
                        throw th4;
                    }
                    this.unknownFields = newOutput.toByteString();
                    makeExtensionsImmutable();
                    throw th2;
                }
            }
            if (z2 & true) {
                this.supertypeId_ = Collections.unmodifiableList(this.supertypeId_);
            }
            if (z2 & true) {
                this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
            }
            if (z2 & true) {
                this.supertype_ = Collections.unmodifiableList(this.supertype_);
            }
            if (z2 & true) {
                this.nestedClassName_ = Collections.unmodifiableList(this.nestedClassName_);
            }
            if (z2 & true) {
                this.constructor_ = Collections.unmodifiableList(this.constructor_);
            }
            if (z2 & true) {
                this.function_ = Collections.unmodifiableList(this.function_);
            }
            if (z2 & true) {
                this.property_ = Collections.unmodifiableList(this.property_);
            }
            if (z2 & true) {
                this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
            }
            if (z2 & true) {
                this.enumEntry_ = Collections.unmodifiableList(this.enumEntry_);
            }
            if (z2 & true) {
                this.sealedSubclassFqName_ = Collections.unmodifiableList(this.sealedSubclassFqName_);
            }
            if (z2 & true) {
                this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
            }
            try {
                newInstance.flush();
            } catch (IOException unused2) {
            } catch (Throwable th5) {
                Throwable th6 = th5;
                this.unknownFields = newOutput.toByteString();
                throw th6;
            }
            this.unknownFields = newOutput.toByteString();
            makeExtensionsImmutable();
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<Class> getParserForType() {
            return PARSER;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFlags() {
            return this.flags_;
        }

        public boolean hasFqName() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getFqName() {
            return this.fqName_;
        }

        public boolean hasCompanionObjectName() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getCompanionObjectName() {
            return this.companionObjectName_;
        }

        public List<TypeParameter> getTypeParameterList() {
            return this.typeParameter_;
        }

        public int getTypeParameterCount() {
            return this.typeParameter_.size();
        }

        public TypeParameter getTypeParameter(int i) {
            return (TypeParameter) this.typeParameter_.get(i);
        }

        public List<Type> getSupertypeList() {
            return this.supertype_;
        }

        public int getSupertypeCount() {
            return this.supertype_.size();
        }

        public Type getSupertype(int i) {
            return (Type) this.supertype_.get(i);
        }

        public List<Integer> getSupertypeIdList() {
            return this.supertypeId_;
        }

        public List<Integer> getNestedClassNameList() {
            return this.nestedClassName_;
        }

        public List<Constructor> getConstructorList() {
            return this.constructor_;
        }

        public int getConstructorCount() {
            return this.constructor_.size();
        }

        public Constructor getConstructor(int i) {
            return (Constructor) this.constructor_.get(i);
        }

        public List<Function> getFunctionList() {
            return this.function_;
        }

        public int getFunctionCount() {
            return this.function_.size();
        }

        public Function getFunction(int i) {
            return (Function) this.function_.get(i);
        }

        public List<Property> getPropertyList() {
            return this.property_;
        }

        public int getPropertyCount() {
            return this.property_.size();
        }

        public Property getProperty(int i) {
            return (Property) this.property_.get(i);
        }

        public List<TypeAlias> getTypeAliasList() {
            return this.typeAlias_;
        }

        public int getTypeAliasCount() {
            return this.typeAlias_.size();
        }

        public TypeAlias getTypeAlias(int i) {
            return (TypeAlias) this.typeAlias_.get(i);
        }

        public List<EnumEntry> getEnumEntryList() {
            return this.enumEntry_;
        }

        public int getEnumEntryCount() {
            return this.enumEntry_.size();
        }

        public EnumEntry getEnumEntry(int i) {
            return (EnumEntry) this.enumEntry_.get(i);
        }

        public List<Integer> getSealedSubclassFqNameList() {
            return this.sealedSubclassFqName_;
        }

        public boolean hasTypeTable() {
            return (this.bitField0_ & 8) == 8;
        }

        public TypeTable getTypeTable() {
            return this.typeTable_;
        }

        public List<Integer> getVersionRequirementList() {
            return this.versionRequirement_;
        }

        public boolean hasVersionRequirementTable() {
            return (this.bitField0_ & 16) == 16;
        }

        public VersionRequirementTable getVersionRequirementTable() {
            return this.versionRequirementTable_;
        }

        private void initFields() {
            this.flags_ = 6;
            this.fqName_ = 0;
            this.companionObjectName_ = 0;
            this.typeParameter_ = Collections.emptyList();
            this.supertype_ = Collections.emptyList();
            this.supertypeId_ = Collections.emptyList();
            this.nestedClassName_ = Collections.emptyList();
            this.constructor_ = Collections.emptyList();
            this.function_ = Collections.emptyList();
            this.property_ = Collections.emptyList();
            this.typeAlias_ = Collections.emptyList();
            this.enumEntry_ = Collections.emptyList();
            this.sealedSubclassFqName_ = Collections.emptyList();
            this.typeTable_ = TypeTable.getDefaultInstance();
            this.versionRequirement_ = Collections.emptyList();
            this.versionRequirementTable_ = VersionRequirementTable.getDefaultInstance();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasFqName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (int i = 0; i < getTypeParameterCount(); i++) {
                if (!getTypeParameter(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < getSupertypeCount(); i2++) {
                if (!getSupertype(i2).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for (int i3 = 0; i3 < getConstructorCount(); i3++) {
                if (!getConstructor(i3).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for (int i4 = 0; i4 < getFunctionCount(); i4++) {
                if (!getFunction(i4).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for (int i5 = 0; i5 < getPropertyCount(); i5++) {
                if (!getProperty(i5).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for (int i6 = 0; i6 < getTypeAliasCount(); i6++) {
                if (!getTypeAlias(i6).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for (int i7 = 0; i7 < getEnumEntryCount(); i7++) {
                if (!getEnumEntry(i7).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if (hasTypeTable() && !getTypeTable().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else {
                this.memoizedIsInitialized = 1;
                return true;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.flags_);
            }
            if (getSupertypeIdList().size() > 0) {
                codedOutputStream.writeRawVarint32(18);
                codedOutputStream.writeRawVarint32(this.supertypeIdMemoizedSerializedSize);
            }
            for (int i = 0; i < this.supertypeId_.size(); i++) {
                codedOutputStream.writeInt32NoTag(((Integer) this.supertypeId_.get(i)).intValue());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(3, this.fqName_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt32(4, this.companionObjectName_);
            }
            for (int i2 = 0; i2 < this.typeParameter_.size(); i2++) {
                codedOutputStream.writeMessage(5, (MessageLite) this.typeParameter_.get(i2));
            }
            for (int i3 = 0; i3 < this.supertype_.size(); i3++) {
                codedOutputStream.writeMessage(6, (MessageLite) this.supertype_.get(i3));
            }
            if (getNestedClassNameList().size() > 0) {
                codedOutputStream.writeRawVarint32(58);
                codedOutputStream.writeRawVarint32(this.nestedClassNameMemoizedSerializedSize);
            }
            for (int i4 = 0; i4 < this.nestedClassName_.size(); i4++) {
                codedOutputStream.writeInt32NoTag(((Integer) this.nestedClassName_.get(i4)).intValue());
            }
            for (int i5 = 0; i5 < this.constructor_.size(); i5++) {
                codedOutputStream.writeMessage(8, (MessageLite) this.constructor_.get(i5));
            }
            for (int i6 = 0; i6 < this.function_.size(); i6++) {
                codedOutputStream.writeMessage(9, (MessageLite) this.function_.get(i6));
            }
            for (int i7 = 0; i7 < this.property_.size(); i7++) {
                codedOutputStream.writeMessage(10, (MessageLite) this.property_.get(i7));
            }
            for (int i8 = 0; i8 < this.typeAlias_.size(); i8++) {
                codedOutputStream.writeMessage(11, (MessageLite) this.typeAlias_.get(i8));
            }
            for (int i9 = 0; i9 < this.enumEntry_.size(); i9++) {
                codedOutputStream.writeMessage(13, (MessageLite) this.enumEntry_.get(i9));
            }
            if (getSealedSubclassFqNameList().size() > 0) {
                codedOutputStream.writeRawVarint32(130);
                codedOutputStream.writeRawVarint32(this.sealedSubclassFqNameMemoizedSerializedSize);
            }
            for (int i10 = 0; i10 < this.sealedSubclassFqName_.size(); i10++) {
                codedOutputStream.writeInt32NoTag(((Integer) this.sealedSubclassFqName_.get(i10)).intValue());
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeMessage(30, this.typeTable_);
            }
            for (int i11 = 0; i11 < this.versionRequirement_.size(); i11++) {
                codedOutputStream.writeInt32(31, ((Integer) this.versionRequirement_.get(i11)).intValue());
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeMessage(32, this.versionRequirementTable_);
            }
            newExtensionWriter.writeUntil(19000, codedOutputStream);
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.flags_) + 0 : 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.supertypeId_.size(); i3++) {
                i2 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.supertypeId_.get(i3)).intValue());
            }
            int i4 = computeInt32Size + i2;
            if (!getSupertypeIdList().isEmpty()) {
                i4 = i4 + 1 + CodedOutputStream.computeInt32SizeNoTag(i2);
            }
            this.supertypeIdMemoizedSerializedSize = i2;
            if ((this.bitField0_ & 2) == 2) {
                i4 += CodedOutputStream.computeInt32Size(3, this.fqName_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i4 += CodedOutputStream.computeInt32Size(4, this.companionObjectName_);
            }
            int i5 = i4;
            for (int i6 = 0; i6 < this.typeParameter_.size(); i6++) {
                i5 += CodedOutputStream.computeMessageSize(5, (MessageLite) this.typeParameter_.get(i6));
            }
            for (int i7 = 0; i7 < this.supertype_.size(); i7++) {
                i5 += CodedOutputStream.computeMessageSize(6, (MessageLite) this.supertype_.get(i7));
            }
            int i8 = 0;
            for (int i9 = 0; i9 < this.nestedClassName_.size(); i9++) {
                i8 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.nestedClassName_.get(i9)).intValue());
            }
            int i10 = i5 + i8;
            if (!getNestedClassNameList().isEmpty()) {
                i10 = i10 + 1 + CodedOutputStream.computeInt32SizeNoTag(i8);
            }
            this.nestedClassNameMemoizedSerializedSize = i8;
            for (int i11 = 0; i11 < this.constructor_.size(); i11++) {
                i10 += CodedOutputStream.computeMessageSize(8, (MessageLite) this.constructor_.get(i11));
            }
            for (int i12 = 0; i12 < this.function_.size(); i12++) {
                i10 += CodedOutputStream.computeMessageSize(9, (MessageLite) this.function_.get(i12));
            }
            for (int i13 = 0; i13 < this.property_.size(); i13++) {
                i10 += CodedOutputStream.computeMessageSize(10, (MessageLite) this.property_.get(i13));
            }
            for (int i14 = 0; i14 < this.typeAlias_.size(); i14++) {
                i10 += CodedOutputStream.computeMessageSize(11, (MessageLite) this.typeAlias_.get(i14));
            }
            for (int i15 = 0; i15 < this.enumEntry_.size(); i15++) {
                i10 += CodedOutputStream.computeMessageSize(13, (MessageLite) this.enumEntry_.get(i15));
            }
            int i16 = 0;
            for (int i17 = 0; i17 < this.sealedSubclassFqName_.size(); i17++) {
                i16 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.sealedSubclassFqName_.get(i17)).intValue());
            }
            int i18 = i10 + i16;
            if (!getSealedSubclassFqNameList().isEmpty()) {
                i18 = i18 + 2 + CodedOutputStream.computeInt32SizeNoTag(i16);
            }
            this.sealedSubclassFqNameMemoizedSerializedSize = i16;
            if ((this.bitField0_ & 8) == 8) {
                i18 += CodedOutputStream.computeMessageSize(30, this.typeTable_);
            }
            int i19 = 0;
            for (int i20 = 0; i20 < this.versionRequirement_.size(); i20++) {
                i19 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.versionRequirement_.get(i20)).intValue());
            }
            int size = i18 + i19 + (getVersionRequirementList().size() * 2);
            if ((this.bitField0_ & 16) == 16) {
                size += CodedOutputStream.computeMessageSize(32, this.versionRequirementTable_);
            }
            int extensionsSerializedSize = size + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public static Class parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Class) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Class classR) {
            return newBuilder().mergeFrom(classR);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface ClassOrBuilder extends ExtendableMessageOrBuilder {
    }

    public static final class Constructor extends ExtendableMessage<Constructor> implements ConstructorOrBuilder {
        public static Parser<Constructor> PARSER = new AbstractParser<Constructor>() {
            public Constructor parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Constructor(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Constructor defaultInstance = new Constructor(true);
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int flags_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;
        /* access modifiers changed from: private */
        public List<ValueParameter> valueParameter_;
        /* access modifiers changed from: private */
        public List<Integer> versionRequirement_;

        public static final class Builder extends ExtendableBuilder<Constructor, Builder> implements ConstructorOrBuilder {
            private int bitField0_;
            private int flags_ = 6;
            private List<ValueParameter> valueParameter_ = Collections.emptyList();
            private List<Integer> versionRequirement_ = Collections.emptyList();

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

            public Constructor getDefaultInstanceForType() {
                return Constructor.getDefaultInstance();
            }

            public Constructor build() {
                Constructor buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Constructor buildPartial() {
                Constructor constructor = new Constructor((ExtendableBuilder) this);
                int i = 1;
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                constructor.flags_ = this.flags_;
                if ((this.bitField0_ & 2) == 2) {
                    this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                    this.bitField0_ &= -3;
                }
                constructor.valueParameter_ = this.valueParameter_;
                if ((this.bitField0_ & 4) == 4) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= -5;
                }
                constructor.versionRequirement_ = this.versionRequirement_;
                constructor.bitField0_ = i;
                return constructor;
            }

            public Builder mergeFrom(Constructor constructor) {
                if (constructor == Constructor.getDefaultInstance()) {
                    return this;
                }
                if (constructor.hasFlags()) {
                    setFlags(constructor.getFlags());
                }
                if (!constructor.valueParameter_.isEmpty()) {
                    if (this.valueParameter_.isEmpty()) {
                        this.valueParameter_ = constructor.valueParameter_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureValueParameterIsMutable();
                        this.valueParameter_.addAll(constructor.valueParameter_);
                    }
                }
                if (!constructor.versionRequirement_.isEmpty()) {
                    if (this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = constructor.versionRequirement_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(constructor.versionRequirement_);
                    }
                }
                mergeExtensionFields(constructor);
                setUnknownFields(getUnknownFields().concat(constructor.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getValueParameterCount(); i++) {
                    if (!getValueParameter(i).isInitialized()) {
                        return false;
                    }
                }
                if (!extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Constructor constructor;
                Constructor constructor2 = null;
                try {
                    Constructor constructor3 = (Constructor) Constructor.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (constructor3 != null) {
                        mergeFrom(constructor3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    constructor = (Constructor) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    constructor2 = constructor;
                }
                if (constructor2 != null) {
                    mergeFrom(constructor2);
                }
                throw th;
            }

            public Builder setFlags(int i) {
                this.bitField0_ |= 1;
                this.flags_ = i;
                return this;
            }

            private void ensureValueParameterIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.valueParameter_ = new ArrayList(this.valueParameter_);
                    this.bitField0_ |= 2;
                }
            }

            public int getValueParameterCount() {
                return this.valueParameter_.size();
            }

            public ValueParameter getValueParameter(int i) {
                return (ValueParameter) this.valueParameter_.get(i);
            }

            private void ensureVersionRequirementIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= 4;
                }
            }
        }

        private Constructor(ExtendableBuilder<Constructor, ?> extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private Constructor(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Constructor getDefaultInstance() {
            return defaultInstance;
        }

        public Constructor getDefaultInstanceForType() {
            return defaultInstance;
        }

        private Constructor(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    } else if (readTag == 8) {
                        this.bitField0_ |= 1;
                        this.flags_ = codedInputStream.readInt32();
                    } else if (readTag == 18) {
                        if (!(z2 & true)) {
                            this.valueParameter_ = new ArrayList();
                            z2 |= true;
                        }
                        this.valueParameter_.add(codedInputStream.readMessage(ValueParameter.PARSER, extensionRegistryLite));
                    } else if (readTag == 248) {
                        if (!(z2 & true)) {
                            this.versionRequirement_ = new ArrayList();
                            z2 |= true;
                        }
                        this.versionRequirement_.add(Integer.valueOf(codedInputStream.readInt32()));
                    } else if (readTag == 250) {
                        int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                        if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                            this.versionRequirement_ = new ArrayList();
                            z2 |= true;
                        }
                        while (codedInputStream.getBytesUntilLimit() > 0) {
                            this.versionRequirement_.add(Integer.valueOf(codedInputStream.readInt32()));
                        }
                        codedInputStream.popLimit(pushLimit);
                    } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                    }
                    if (z2 & true) {
                        this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
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
                this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
            }
            if (z2 & true) {
                this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
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

        public Parser<Constructor> getParserForType() {
            return PARSER;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFlags() {
            return this.flags_;
        }

        public List<ValueParameter> getValueParameterList() {
            return this.valueParameter_;
        }

        public int getValueParameterCount() {
            return this.valueParameter_.size();
        }

        public ValueParameter getValueParameter(int i) {
            return (ValueParameter) this.valueParameter_.get(i);
        }

        public List<Integer> getVersionRequirementList() {
            return this.versionRequirement_;
        }

        private void initFields() {
            this.flags_ = 6;
            this.valueParameter_ = Collections.emptyList();
            this.versionRequirement_ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getValueParameterCount(); i++) {
                if (!getValueParameter(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.flags_);
            }
            for (int i = 0; i < this.valueParameter_.size(); i++) {
                codedOutputStream.writeMessage(2, (MessageLite) this.valueParameter_.get(i));
            }
            for (int i2 = 0; i2 < this.versionRequirement_.size(); i2++) {
                codedOutputStream.writeInt32(31, ((Integer) this.versionRequirement_.get(i2)).intValue());
            }
            newExtensionWriter.writeUntil(19000, codedOutputStream);
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.flags_) + 0 : 0;
            for (int i2 = 0; i2 < this.valueParameter_.size(); i2++) {
                computeInt32Size += CodedOutputStream.computeMessageSize(2, (MessageLite) this.valueParameter_.get(i2));
            }
            int i3 = 0;
            for (int i4 = 0; i4 < this.versionRequirement_.size(); i4++) {
                i3 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.versionRequirement_.get(i4)).intValue());
            }
            int size = computeInt32Size + i3 + (getVersionRequirementList().size() * 2) + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = size;
            return size;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Constructor constructor) {
            return newBuilder().mergeFrom(constructor);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface ConstructorOrBuilder extends ExtendableMessageOrBuilder {
    }

    public static final class Contract extends GeneratedMessageLite implements ContractOrBuilder {
        public static Parser<Contract> PARSER = new AbstractParser<Contract>() {
            public Contract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Contract(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Contract defaultInstance = new Contract(true);
        /* access modifiers changed from: private */
        public List<Effect> effect_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;

        public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<Contract, Builder> implements ContractOrBuilder {
            private int bitField0_;
            private List<Effect> effect_ = Collections.emptyList();

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

            public Contract getDefaultInstanceForType() {
                return Contract.getDefaultInstance();
            }

            public Contract build() {
                Contract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Contract buildPartial() {
                Contract contract = new Contract((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                if ((this.bitField0_ & 1) == 1) {
                    this.effect_ = Collections.unmodifiableList(this.effect_);
                    this.bitField0_ &= -2;
                }
                contract.effect_ = this.effect_;
                return contract;
            }

            public Builder mergeFrom(Contract contract) {
                if (contract == Contract.getDefaultInstance()) {
                    return this;
                }
                if (!contract.effect_.isEmpty()) {
                    if (this.effect_.isEmpty()) {
                        this.effect_ = contract.effect_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureEffectIsMutable();
                        this.effect_.addAll(contract.effect_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(contract.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getEffectCount(); i++) {
                    if (!getEffect(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Contract contract;
                Contract contract2 = null;
                try {
                    Contract contract3 = (Contract) Contract.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (contract3 != null) {
                        mergeFrom(contract3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    contract = (Contract) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    contract2 = contract;
                }
                if (contract2 != null) {
                    mergeFrom(contract2);
                }
                throw th;
            }

            private void ensureEffectIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.effect_ = new ArrayList(this.effect_);
                    this.bitField0_ |= 1;
                }
            }

            public int getEffectCount() {
                return this.effect_.size();
            }

            public Effect getEffect(int i) {
                return (Effect) this.effect_.get(i);
            }
        }

        private Contract(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Contract(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Contract getDefaultInstance() {
            return defaultInstance;
        }

        public Contract getDefaultInstanceForType() {
            return defaultInstance;
        }

        private Contract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (!z2 || !true) {
                            this.effect_ = new ArrayList();
                            z2 |= true;
                        }
                        this.effect_.add(codedInputStream.readMessage(Effect.PARSER, extensionRegistryLite));
                    } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.effect_ = Collections.unmodifiableList(this.effect_);
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
            if (z2 && true) {
                this.effect_ = Collections.unmodifiableList(this.effect_);
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

        public Parser<Contract> getParserForType() {
            return PARSER;
        }

        public int getEffectCount() {
            return this.effect_.size();
        }

        public Effect getEffect(int i) {
            return (Effect) this.effect_.get(i);
        }

        private void initFields() {
            this.effect_ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getEffectCount(); i++) {
                if (!getEffect(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.effect_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.effect_.get(i));
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.effect_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.effect_.get(i3));
            }
            int size = i2 + this.unknownFields.size();
            this.memoizedSerializedSize = size;
            return size;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Contract contract) {
            return newBuilder().mergeFrom(contract);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface ContractOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class Effect extends GeneratedMessageLite implements EffectOrBuilder {
        public static Parser<Effect> PARSER = new AbstractParser<Effect>() {
            public Effect parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Effect(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Effect defaultInstance = new Effect(true);
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public Expression conclusionOfConditionalEffect_;
        /* access modifiers changed from: private */
        public List<Expression> effectConstructorArgument_;
        /* access modifiers changed from: private */
        public EffectType effectType_;
        /* access modifiers changed from: private */
        public InvocationKind kind_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;

        public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<Effect, Builder> implements EffectOrBuilder {
            private int bitField0_;
            private Expression conclusionOfConditionalEffect_ = Expression.getDefaultInstance();
            private List<Expression> effectConstructorArgument_ = Collections.emptyList();
            private EffectType effectType_ = EffectType.RETURNS_CONSTANT;
            private InvocationKind kind_ = InvocationKind.AT_MOST_ONCE;

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

            public Effect getDefaultInstanceForType() {
                return Effect.getDefaultInstance();
            }

            public Effect build() {
                Effect buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Effect buildPartial() {
                Effect effect = new Effect((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                effect.effectType_ = this.effectType_;
                if ((this.bitField0_ & 2) == 2) {
                    this.effectConstructorArgument_ = Collections.unmodifiableList(this.effectConstructorArgument_);
                    this.bitField0_ &= -3;
                }
                effect.effectConstructorArgument_ = this.effectConstructorArgument_;
                if ((i & 4) == 4) {
                    i2 |= 2;
                }
                effect.conclusionOfConditionalEffect_ = this.conclusionOfConditionalEffect_;
                if ((i & 8) == 8) {
                    i2 |= 4;
                }
                effect.kind_ = this.kind_;
                effect.bitField0_ = i2;
                return effect;
            }

            public Builder mergeFrom(Effect effect) {
                if (effect == Effect.getDefaultInstance()) {
                    return this;
                }
                if (effect.hasEffectType()) {
                    setEffectType(effect.getEffectType());
                }
                if (!effect.effectConstructorArgument_.isEmpty()) {
                    if (this.effectConstructorArgument_.isEmpty()) {
                        this.effectConstructorArgument_ = effect.effectConstructorArgument_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureEffectConstructorArgumentIsMutable();
                        this.effectConstructorArgument_.addAll(effect.effectConstructorArgument_);
                    }
                }
                if (effect.hasConclusionOfConditionalEffect()) {
                    mergeConclusionOfConditionalEffect(effect.getConclusionOfConditionalEffect());
                }
                if (effect.hasKind()) {
                    setKind(effect.getKind());
                }
                setUnknownFields(getUnknownFields().concat(effect.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getEffectConstructorArgumentCount(); i++) {
                    if (!getEffectConstructorArgument(i).isInitialized()) {
                        return false;
                    }
                }
                if (!hasConclusionOfConditionalEffect() || getConclusionOfConditionalEffect().isInitialized()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Effect effect;
                Effect effect2 = null;
                try {
                    Effect effect3 = (Effect) Effect.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (effect3 != null) {
                        mergeFrom(effect3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    effect = (Effect) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    effect2 = effect;
                }
                if (effect2 != null) {
                    mergeFrom(effect2);
                }
                throw th;
            }

            public Builder setEffectType(EffectType effectType) {
                if (effectType != null) {
                    this.bitField0_ |= 1;
                    this.effectType_ = effectType;
                    return this;
                }
                throw new NullPointerException();
            }

            private void ensureEffectConstructorArgumentIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.effectConstructorArgument_ = new ArrayList(this.effectConstructorArgument_);
                    this.bitField0_ |= 2;
                }
            }

            public int getEffectConstructorArgumentCount() {
                return this.effectConstructorArgument_.size();
            }

            public Expression getEffectConstructorArgument(int i) {
                return (Expression) this.effectConstructorArgument_.get(i);
            }

            public boolean hasConclusionOfConditionalEffect() {
                return (this.bitField0_ & 4) == 4;
            }

            public Expression getConclusionOfConditionalEffect() {
                return this.conclusionOfConditionalEffect_;
            }

            public Builder mergeConclusionOfConditionalEffect(Expression expression) {
                if ((this.bitField0_ & 4) != 4 || this.conclusionOfConditionalEffect_ == Expression.getDefaultInstance()) {
                    this.conclusionOfConditionalEffect_ = expression;
                } else {
                    this.conclusionOfConditionalEffect_ = Expression.newBuilder(this.conclusionOfConditionalEffect_).mergeFrom(expression).buildPartial();
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setKind(InvocationKind invocationKind) {
                if (invocationKind != null) {
                    this.bitField0_ |= 8;
                    this.kind_ = invocationKind;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        public enum EffectType implements EnumLite {
            RETURNS_CONSTANT(0, 0),
            CALLS(1, 1),
            RETURNS_NOT_NULL(2, 2);
            
            private static EnumLiteMap<EffectType> internalValueMap;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<EffectType>() {
                    public EffectType findValueByNumber(int i) {
                        return EffectType.valueOf(i);
                    }
                };
            }

            public final int getNumber() {
                return this.value;
            }

            public static EffectType valueOf(int i) {
                switch (i) {
                    case 0:
                        return RETURNS_CONSTANT;
                    case 1:
                        return CALLS;
                    case 2:
                        return RETURNS_NOT_NULL;
                    default:
                        return null;
                }
            }

            private EffectType(int i, int i2) {
                this.value = i2;
            }
        }

        public enum InvocationKind implements EnumLite {
            AT_MOST_ONCE(0, 0),
            EXACTLY_ONCE(1, 1),
            AT_LEAST_ONCE(2, 2);
            
            private static EnumLiteMap<InvocationKind> internalValueMap;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<InvocationKind>() {
                    public InvocationKind findValueByNumber(int i) {
                        return InvocationKind.valueOf(i);
                    }
                };
            }

            public final int getNumber() {
                return this.value;
            }

            public static InvocationKind valueOf(int i) {
                switch (i) {
                    case 0:
                        return AT_MOST_ONCE;
                    case 1:
                        return EXACTLY_ONCE;
                    case 2:
                        return AT_LEAST_ONCE;
                    default:
                        return null;
                }
            }

            private InvocationKind(int i, int i2) {
                this.value = i2;
            }
        }

        private Effect(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Effect(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Effect getDefaultInstance() {
            return defaultInstance;
        }

        public Effect getDefaultInstanceForType() {
            return defaultInstance;
        }

        private Effect(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    } else if (readTag == 8) {
                        int readEnum = codedInputStream.readEnum();
                        EffectType valueOf = EffectType.valueOf(readEnum);
                        if (valueOf == null) {
                            newInstance.writeRawVarint32(readTag);
                            newInstance.writeRawVarint32(readEnum);
                        } else {
                            this.bitField0_ |= 1;
                            this.effectType_ = valueOf;
                        }
                    } else if (readTag == 18) {
                        if (!(z2 & true)) {
                            this.effectConstructorArgument_ = new ArrayList();
                            z2 |= true;
                        }
                        this.effectConstructorArgument_.add(codedInputStream.readMessage(Expression.PARSER, extensionRegistryLite));
                    } else if (readTag == 26) {
                        Builder builder = null;
                        if ((this.bitField0_ & 2) == 2) {
                            builder = this.conclusionOfConditionalEffect_.toBuilder();
                        }
                        this.conclusionOfConditionalEffect_ = (Expression) codedInputStream.readMessage(Expression.PARSER, extensionRegistryLite);
                        if (builder != null) {
                            builder.mergeFrom(this.conclusionOfConditionalEffect_);
                            this.conclusionOfConditionalEffect_ = builder.buildPartial();
                        }
                        this.bitField0_ |= 2;
                    } else if (readTag == 32) {
                        int readEnum2 = codedInputStream.readEnum();
                        InvocationKind valueOf2 = InvocationKind.valueOf(readEnum2);
                        if (valueOf2 == null) {
                            newInstance.writeRawVarint32(readTag);
                            newInstance.writeRawVarint32(readEnum2);
                        } else {
                            this.bitField0_ |= 4;
                            this.kind_ = valueOf2;
                        }
                    } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.effectConstructorArgument_ = Collections.unmodifiableList(this.effectConstructorArgument_);
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
                this.effectConstructorArgument_ = Collections.unmodifiableList(this.effectConstructorArgument_);
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

        public Parser<Effect> getParserForType() {
            return PARSER;
        }

        public boolean hasEffectType() {
            return (this.bitField0_ & 1) == 1;
        }

        public EffectType getEffectType() {
            return this.effectType_;
        }

        public int getEffectConstructorArgumentCount() {
            return this.effectConstructorArgument_.size();
        }

        public Expression getEffectConstructorArgument(int i) {
            return (Expression) this.effectConstructorArgument_.get(i);
        }

        public boolean hasConclusionOfConditionalEffect() {
            return (this.bitField0_ & 2) == 2;
        }

        public Expression getConclusionOfConditionalEffect() {
            return this.conclusionOfConditionalEffect_;
        }

        public boolean hasKind() {
            return (this.bitField0_ & 4) == 4;
        }

        public InvocationKind getKind() {
            return this.kind_;
        }

        private void initFields() {
            this.effectType_ = EffectType.RETURNS_CONSTANT;
            this.effectConstructorArgument_ = Collections.emptyList();
            this.conclusionOfConditionalEffect_ = Expression.getDefaultInstance();
            this.kind_ = InvocationKind.AT_MOST_ONCE;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getEffectConstructorArgumentCount(); i++) {
                if (!getEffectConstructorArgument(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if (!hasConclusionOfConditionalEffect() || getConclusionOfConditionalEffect().isInitialized()) {
                this.memoizedIsInitialized = 1;
                return true;
            }
            this.memoizedIsInitialized = 0;
            return false;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeEnum(1, this.effectType_.getNumber());
            }
            for (int i = 0; i < this.effectConstructorArgument_.size(); i++) {
                codedOutputStream.writeMessage(2, (MessageLite) this.effectConstructorArgument_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(3, this.conclusionOfConditionalEffect_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeEnum(4, this.kind_.getNumber());
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeEnumSize(1, this.effectType_.getNumber()) + 0 : 0;
            for (int i2 = 0; i2 < this.effectConstructorArgument_.size(); i2++) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, (MessageLite) this.effectConstructorArgument_.get(i2));
            }
            if ((this.bitField0_ & 2) == 2) {
                computeEnumSize += CodedOutputStream.computeMessageSize(3, this.conclusionOfConditionalEffect_);
            }
            if ((this.bitField0_ & 4) == 4) {
                computeEnumSize += CodedOutputStream.computeEnumSize(4, this.kind_.getNumber());
            }
            int size = computeEnumSize + this.unknownFields.size();
            this.memoizedSerializedSize = size;
            return size;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Effect effect) {
            return newBuilder().mergeFrom(effect);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface EffectOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class EnumEntry extends ExtendableMessage<EnumEntry> implements EnumEntryOrBuilder {
        public static Parser<EnumEntry> PARSER = new AbstractParser<EnumEntry>() {
            public EnumEntry parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new EnumEntry(codedInputStream, extensionRegistryLite);
            }
        };
        private static final EnumEntry defaultInstance = new EnumEntry(true);
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int name_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;

        public static final class Builder extends ExtendableBuilder<EnumEntry, Builder> implements EnumEntryOrBuilder {
            private int bitField0_;
            private int name_;

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

            public EnumEntry getDefaultInstanceForType() {
                return EnumEntry.getDefaultInstance();
            }

            public EnumEntry build() {
                EnumEntry buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public EnumEntry buildPartial() {
                EnumEntry enumEntry = new EnumEntry((ExtendableBuilder) this);
                int i = 1;
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                enumEntry.name_ = this.name_;
                enumEntry.bitField0_ = i;
                return enumEntry;
            }

            public Builder mergeFrom(EnumEntry enumEntry) {
                if (enumEntry == EnumEntry.getDefaultInstance()) {
                    return this;
                }
                if (enumEntry.hasName()) {
                    setName(enumEntry.getName());
                }
                mergeExtensionFields(enumEntry);
                setUnknownFields(getUnknownFields().concat(enumEntry.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                return extensionsAreInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                EnumEntry enumEntry;
                EnumEntry enumEntry2 = null;
                try {
                    EnumEntry enumEntry3 = (EnumEntry) EnumEntry.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (enumEntry3 != null) {
                        mergeFrom(enumEntry3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    enumEntry = (EnumEntry) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    enumEntry2 = enumEntry;
                }
                if (enumEntry2 != null) {
                    mergeFrom(enumEntry2);
                }
                throw th;
            }

            public Builder setName(int i) {
                this.bitField0_ |= 1;
                this.name_ = i;
                return this;
            }
        }

        private EnumEntry(ExtendableBuilder<EnumEntry, ?> extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private EnumEntry(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static EnumEntry getDefaultInstance() {
            return defaultInstance;
        }

        public EnumEntry getDefaultInstanceForType() {
            return defaultInstance;
        }

        private EnumEntry(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            Output newOutput = ByteString.newOutput();
            CodedOutputStream newInstance = CodedOutputStream.newInstance(newOutput, 1);
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 8) {
                        this.bitField0_ |= 1;
                        this.name_ = codedInputStream.readInt32();
                    } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
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

        public Parser<EnumEntry> getParserForType() {
            return PARSER;
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getName() {
            return this.name_;
        }

        private void initFields() {
            this.name_ = 0;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.name_);
            }
            newExtensionWriter.writeUntil(200, codedOutputStream);
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, this.name_);
            }
            int extensionsSerializedSize = i2 + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(EnumEntry enumEntry) {
            return newBuilder().mergeFrom(enumEntry);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface EnumEntryOrBuilder extends ExtendableMessageOrBuilder {
    }

    public static final class Expression extends GeneratedMessageLite implements ExpressionOrBuilder {
        public static Parser<Expression> PARSER = new AbstractParser<Expression>() {
            public Expression parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Expression(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Expression defaultInstance = new Expression(true);
        /* access modifiers changed from: private */
        public List<Expression> andArgument_;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ConstantValue constantValue_;
        /* access modifiers changed from: private */
        public int flags_;
        /* access modifiers changed from: private */
        public int isInstanceTypeId_;
        /* access modifiers changed from: private */
        public Type isInstanceType_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<Expression> orArgument_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;
        /* access modifiers changed from: private */
        public int valueParameterReference_;

        public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<Expression, Builder> implements ExpressionOrBuilder {
            private List<Expression> andArgument_ = Collections.emptyList();
            private int bitField0_;
            private ConstantValue constantValue_ = ConstantValue.TRUE;
            private int flags_;
            private int isInstanceTypeId_;
            private Type isInstanceType_ = Type.getDefaultInstance();
            private List<Expression> orArgument_ = Collections.emptyList();
            private int valueParameterReference_;

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

            public Expression getDefaultInstanceForType() {
                return Expression.getDefaultInstance();
            }

            public Expression build() {
                Expression buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Expression buildPartial() {
                Expression expression = new Expression((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                expression.flags_ = this.flags_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                expression.valueParameterReference_ = this.valueParameterReference_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                expression.constantValue_ = this.constantValue_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                expression.isInstanceType_ = this.isInstanceType_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                expression.isInstanceTypeId_ = this.isInstanceTypeId_;
                if ((this.bitField0_ & 32) == 32) {
                    this.andArgument_ = Collections.unmodifiableList(this.andArgument_);
                    this.bitField0_ &= -33;
                }
                expression.andArgument_ = this.andArgument_;
                if ((this.bitField0_ & 64) == 64) {
                    this.orArgument_ = Collections.unmodifiableList(this.orArgument_);
                    this.bitField0_ &= -65;
                }
                expression.orArgument_ = this.orArgument_;
                expression.bitField0_ = i2;
                return expression;
            }

            public Builder mergeFrom(Expression expression) {
                if (expression == Expression.getDefaultInstance()) {
                    return this;
                }
                if (expression.hasFlags()) {
                    setFlags(expression.getFlags());
                }
                if (expression.hasValueParameterReference()) {
                    setValueParameterReference(expression.getValueParameterReference());
                }
                if (expression.hasConstantValue()) {
                    setConstantValue(expression.getConstantValue());
                }
                if (expression.hasIsInstanceType()) {
                    mergeIsInstanceType(expression.getIsInstanceType());
                }
                if (expression.hasIsInstanceTypeId()) {
                    setIsInstanceTypeId(expression.getIsInstanceTypeId());
                }
                if (!expression.andArgument_.isEmpty()) {
                    if (this.andArgument_.isEmpty()) {
                        this.andArgument_ = expression.andArgument_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureAndArgumentIsMutable();
                        this.andArgument_.addAll(expression.andArgument_);
                    }
                }
                if (!expression.orArgument_.isEmpty()) {
                    if (this.orArgument_.isEmpty()) {
                        this.orArgument_ = expression.orArgument_;
                        this.bitField0_ &= -65;
                    } else {
                        ensureOrArgumentIsMutable();
                        this.orArgument_.addAll(expression.orArgument_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(expression.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                if (hasIsInstanceType() && !getIsInstanceType().isInitialized()) {
                    return false;
                }
                for (int i = 0; i < getAndArgumentCount(); i++) {
                    if (!getAndArgument(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getOrArgumentCount(); i2++) {
                    if (!getOrArgument(i2).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Expression expression;
                Expression expression2 = null;
                try {
                    Expression expression3 = (Expression) Expression.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (expression3 != null) {
                        mergeFrom(expression3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    expression = (Expression) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    expression2 = expression;
                }
                if (expression2 != null) {
                    mergeFrom(expression2);
                }
                throw th;
            }

            public Builder setFlags(int i) {
                this.bitField0_ |= 1;
                this.flags_ = i;
                return this;
            }

            public Builder setValueParameterReference(int i) {
                this.bitField0_ |= 2;
                this.valueParameterReference_ = i;
                return this;
            }

            public Builder setConstantValue(ConstantValue constantValue) {
                if (constantValue != null) {
                    this.bitField0_ |= 4;
                    this.constantValue_ = constantValue;
                    return this;
                }
                throw new NullPointerException();
            }

            public boolean hasIsInstanceType() {
                return (this.bitField0_ & 8) == 8;
            }

            public Type getIsInstanceType() {
                return this.isInstanceType_;
            }

            public Builder mergeIsInstanceType(Type type) {
                if ((this.bitField0_ & 8) != 8 || this.isInstanceType_ == Type.getDefaultInstance()) {
                    this.isInstanceType_ = type;
                } else {
                    this.isInstanceType_ = Type.newBuilder(this.isInstanceType_).mergeFrom(type).buildPartial();
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setIsInstanceTypeId(int i) {
                this.bitField0_ |= 16;
                this.isInstanceTypeId_ = i;
                return this;
            }

            private void ensureAndArgumentIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.andArgument_ = new ArrayList(this.andArgument_);
                    this.bitField0_ |= 32;
                }
            }

            public int getAndArgumentCount() {
                return this.andArgument_.size();
            }

            public Expression getAndArgument(int i) {
                return (Expression) this.andArgument_.get(i);
            }

            private void ensureOrArgumentIsMutable() {
                if ((this.bitField0_ & 64) != 64) {
                    this.orArgument_ = new ArrayList(this.orArgument_);
                    this.bitField0_ |= 64;
                }
            }

            public int getOrArgumentCount() {
                return this.orArgument_.size();
            }

            public Expression getOrArgument(int i) {
                return (Expression) this.orArgument_.get(i);
            }
        }

        public enum ConstantValue implements EnumLite {
            TRUE(0, 0),
            FALSE(1, 1),
            NULL(2, 2);
            
            private static EnumLiteMap<ConstantValue> internalValueMap;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<ConstantValue>() {
                    public ConstantValue findValueByNumber(int i) {
                        return ConstantValue.valueOf(i);
                    }
                };
            }

            public final int getNumber() {
                return this.value;
            }

            public static ConstantValue valueOf(int i) {
                switch (i) {
                    case 0:
                        return TRUE;
                    case 1:
                        return FALSE;
                    case 2:
                        return NULL;
                    default:
                        return null;
                }
            }

            private ConstantValue(int i, int i2) {
                this.value = i2;
            }
        }

        private Expression(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Expression(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Expression getDefaultInstance() {
            return defaultInstance;
        }

        public Expression getDefaultInstanceForType() {
            return defaultInstance;
        }

        private Expression(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    } else if (readTag == 8) {
                        this.bitField0_ |= 1;
                        this.flags_ = codedInputStream.readInt32();
                    } else if (readTag == 16) {
                        this.bitField0_ |= 2;
                        this.valueParameterReference_ = codedInputStream.readInt32();
                    } else if (readTag == 24) {
                        int readEnum = codedInputStream.readEnum();
                        ConstantValue valueOf = ConstantValue.valueOf(readEnum);
                        if (valueOf == null) {
                            newInstance.writeRawVarint32(readTag);
                            newInstance.writeRawVarint32(readEnum);
                        } else {
                            this.bitField0_ |= 4;
                            this.constantValue_ = valueOf;
                        }
                    } else if (readTag == 34) {
                        Builder builder = null;
                        if ((this.bitField0_ & 8) == 8) {
                            builder = this.isInstanceType_.toBuilder();
                        }
                        this.isInstanceType_ = (Type) codedInputStream.readMessage(Type.PARSER, extensionRegistryLite);
                        if (builder != null) {
                            builder.mergeFrom(this.isInstanceType_);
                            this.isInstanceType_ = builder.buildPartial();
                        }
                        this.bitField0_ |= 8;
                    } else if (readTag == 40) {
                        this.bitField0_ |= 16;
                        this.isInstanceTypeId_ = codedInputStream.readInt32();
                    } else if (readTag == 50) {
                        if (!(z2 & true)) {
                            this.andArgument_ = new ArrayList();
                            z2 |= true;
                        }
                        this.andArgument_.add(codedInputStream.readMessage(PARSER, extensionRegistryLite));
                    } else if (readTag == 58) {
                        if (!(z2 & true)) {
                            this.orArgument_ = new ArrayList();
                            z2 |= true;
                        }
                        this.orArgument_.add(codedInputStream.readMessage(PARSER, extensionRegistryLite));
                    } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.andArgument_ = Collections.unmodifiableList(this.andArgument_);
                    }
                    if (z2 & true) {
                        this.orArgument_ = Collections.unmodifiableList(this.orArgument_);
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
                this.andArgument_ = Collections.unmodifiableList(this.andArgument_);
            }
            if (z2 & true) {
                this.orArgument_ = Collections.unmodifiableList(this.orArgument_);
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

        public Parser<Expression> getParserForType() {
            return PARSER;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFlags() {
            return this.flags_;
        }

        public boolean hasValueParameterReference() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getValueParameterReference() {
            return this.valueParameterReference_;
        }

        public boolean hasConstantValue() {
            return (this.bitField0_ & 4) == 4;
        }

        public ConstantValue getConstantValue() {
            return this.constantValue_;
        }

        public boolean hasIsInstanceType() {
            return (this.bitField0_ & 8) == 8;
        }

        public Type getIsInstanceType() {
            return this.isInstanceType_;
        }

        public boolean hasIsInstanceTypeId() {
            return (this.bitField0_ & 16) == 16;
        }

        public int getIsInstanceTypeId() {
            return this.isInstanceTypeId_;
        }

        public int getAndArgumentCount() {
            return this.andArgument_.size();
        }

        public Expression getAndArgument(int i) {
            return (Expression) this.andArgument_.get(i);
        }

        public int getOrArgumentCount() {
            return this.orArgument_.size();
        }

        public Expression getOrArgument(int i) {
            return (Expression) this.orArgument_.get(i);
        }

        private void initFields() {
            this.flags_ = 0;
            this.valueParameterReference_ = 0;
            this.constantValue_ = ConstantValue.TRUE;
            this.isInstanceType_ = Type.getDefaultInstance();
            this.isInstanceTypeId_ = 0;
            this.andArgument_ = Collections.emptyList();
            this.orArgument_ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasIsInstanceType() || getIsInstanceType().isInitialized()) {
                for (int i = 0; i < getAndArgumentCount(); i++) {
                    if (!getAndArgument(i).isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getOrArgumentCount(); i2++) {
                    if (!getOrArgument(i2).isInitialized()) {
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
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.flags_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.valueParameterReference_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeEnum(3, this.constantValue_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeMessage(4, this.isInstanceType_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeInt32(5, this.isInstanceTypeId_);
            }
            for (int i = 0; i < this.andArgument_.size(); i++) {
                codedOutputStream.writeMessage(6, (MessageLite) this.andArgument_.get(i));
            }
            for (int i2 = 0; i2 < this.orArgument_.size(); i2++) {
                codedOutputStream.writeMessage(7, (MessageLite) this.orArgument_.get(i2));
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.flags_) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                computeInt32Size += CodedOutputStream.computeInt32Size(2, this.valueParameterReference_);
            }
            if ((this.bitField0_ & 4) == 4) {
                computeInt32Size += CodedOutputStream.computeEnumSize(3, this.constantValue_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                computeInt32Size += CodedOutputStream.computeMessageSize(4, this.isInstanceType_);
            }
            if ((this.bitField0_ & 16) == 16) {
                computeInt32Size += CodedOutputStream.computeInt32Size(5, this.isInstanceTypeId_);
            }
            int i2 = computeInt32Size;
            for (int i3 = 0; i3 < this.andArgument_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(6, (MessageLite) this.andArgument_.get(i3));
            }
            for (int i4 = 0; i4 < this.orArgument_.size(); i4++) {
                i2 += CodedOutputStream.computeMessageSize(7, (MessageLite) this.orArgument_.get(i4));
            }
            int size = i2 + this.unknownFields.size();
            this.memoizedSerializedSize = size;
            return size;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Expression expression) {
            return newBuilder().mergeFrom(expression);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface ExpressionOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class Function extends ExtendableMessage<Function> implements FunctionOrBuilder {
        public static Parser<Function> PARSER = new AbstractParser<Function>() {
            public Function parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Function(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Function defaultInstance = new Function(true);
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public Contract contract_;
        /* access modifiers changed from: private */
        public int flags_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int name_;
        /* access modifiers changed from: private */
        public int oldFlags_;
        /* access modifiers changed from: private */
        public int receiverTypeId_;
        /* access modifiers changed from: private */
        public Type receiverType_;
        /* access modifiers changed from: private */
        public int returnTypeId_;
        /* access modifiers changed from: private */
        public Type returnType_;
        /* access modifiers changed from: private */
        public List<TypeParameter> typeParameter_;
        /* access modifiers changed from: private */
        public TypeTable typeTable_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;
        /* access modifiers changed from: private */
        public List<ValueParameter> valueParameter_;
        /* access modifiers changed from: private */
        public List<Integer> versionRequirement_;

        public static final class Builder extends ExtendableBuilder<Function, Builder> implements FunctionOrBuilder {
            private int bitField0_;
            private Contract contract_ = Contract.getDefaultInstance();
            private int flags_ = 6;
            private int name_;
            private int oldFlags_ = 6;
            private int receiverTypeId_;
            private Type receiverType_ = Type.getDefaultInstance();
            private int returnTypeId_;
            private Type returnType_ = Type.getDefaultInstance();
            private List<TypeParameter> typeParameter_ = Collections.emptyList();
            private TypeTable typeTable_ = TypeTable.getDefaultInstance();
            private List<ValueParameter> valueParameter_ = Collections.emptyList();
            private List<Integer> versionRequirement_ = Collections.emptyList();

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

            public Function getDefaultInstanceForType() {
                return Function.getDefaultInstance();
            }

            public Function build() {
                Function buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Function buildPartial() {
                Function function = new Function((ExtendableBuilder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                function.flags_ = this.flags_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                function.oldFlags_ = this.oldFlags_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                function.name_ = this.name_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                function.returnType_ = this.returnType_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                function.returnTypeId_ = this.returnTypeId_;
                if ((this.bitField0_ & 32) == 32) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    this.bitField0_ &= -33;
                }
                function.typeParameter_ = this.typeParameter_;
                if ((i & 64) == 64) {
                    i2 |= 32;
                }
                function.receiverType_ = this.receiverType_;
                if ((i & 128) == 128) {
                    i2 |= 64;
                }
                function.receiverTypeId_ = this.receiverTypeId_;
                if ((this.bitField0_ & 256) == 256) {
                    this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                    this.bitField0_ &= -257;
                }
                function.valueParameter_ = this.valueParameter_;
                if ((i & 512) == 512) {
                    i2 |= 128;
                }
                function.typeTable_ = this.typeTable_;
                if ((this.bitField0_ & 1024) == 1024) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= -1025;
                }
                function.versionRequirement_ = this.versionRequirement_;
                if ((i & 2048) == 2048) {
                    i2 |= 256;
                }
                function.contract_ = this.contract_;
                function.bitField0_ = i2;
                return function;
            }

            public Builder mergeFrom(Function function) {
                if (function == Function.getDefaultInstance()) {
                    return this;
                }
                if (function.hasFlags()) {
                    setFlags(function.getFlags());
                }
                if (function.hasOldFlags()) {
                    setOldFlags(function.getOldFlags());
                }
                if (function.hasName()) {
                    setName(function.getName());
                }
                if (function.hasReturnType()) {
                    mergeReturnType(function.getReturnType());
                }
                if (function.hasReturnTypeId()) {
                    setReturnTypeId(function.getReturnTypeId());
                }
                if (!function.typeParameter_.isEmpty()) {
                    if (this.typeParameter_.isEmpty()) {
                        this.typeParameter_ = function.typeParameter_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureTypeParameterIsMutable();
                        this.typeParameter_.addAll(function.typeParameter_);
                    }
                }
                if (function.hasReceiverType()) {
                    mergeReceiverType(function.getReceiverType());
                }
                if (function.hasReceiverTypeId()) {
                    setReceiverTypeId(function.getReceiverTypeId());
                }
                if (!function.valueParameter_.isEmpty()) {
                    if (this.valueParameter_.isEmpty()) {
                        this.valueParameter_ = function.valueParameter_;
                        this.bitField0_ &= -257;
                    } else {
                        ensureValueParameterIsMutable();
                        this.valueParameter_.addAll(function.valueParameter_);
                    }
                }
                if (function.hasTypeTable()) {
                    mergeTypeTable(function.getTypeTable());
                }
                if (!function.versionRequirement_.isEmpty()) {
                    if (this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = function.versionRequirement_;
                        this.bitField0_ &= -1025;
                    } else {
                        ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(function.versionRequirement_);
                    }
                }
                if (function.hasContract()) {
                    mergeContract(function.getContract());
                }
                mergeExtensionFields(function);
                setUnknownFields(getUnknownFields().concat(function.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                if (!hasName()) {
                    return false;
                }
                if (hasReturnType() && !getReturnType().isInitialized()) {
                    return false;
                }
                for (int i = 0; i < getTypeParameterCount(); i++) {
                    if (!getTypeParameter(i).isInitialized()) {
                        return false;
                    }
                }
                if (hasReceiverType() && !getReceiverType().isInitialized()) {
                    return false;
                }
                for (int i2 = 0; i2 < getValueParameterCount(); i2++) {
                    if (!getValueParameter(i2).isInitialized()) {
                        return false;
                    }
                }
                if (hasTypeTable() && !getTypeTable().isInitialized()) {
                    return false;
                }
                if ((!hasContract() || getContract().isInitialized()) && extensionsAreInitialized()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Function function;
                Function function2 = null;
                try {
                    Function function3 = (Function) Function.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (function3 != null) {
                        mergeFrom(function3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    function = (Function) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    function2 = function;
                }
                if (function2 != null) {
                    mergeFrom(function2);
                }
                throw th;
            }

            public Builder setFlags(int i) {
                this.bitField0_ |= 1;
                this.flags_ = i;
                return this;
            }

            public Builder setOldFlags(int i) {
                this.bitField0_ |= 2;
                this.oldFlags_ = i;
                return this;
            }

            public boolean hasName() {
                return (this.bitField0_ & 4) == 4;
            }

            public Builder setName(int i) {
                this.bitField0_ |= 4;
                this.name_ = i;
                return this;
            }

            public boolean hasReturnType() {
                return (this.bitField0_ & 8) == 8;
            }

            public Type getReturnType() {
                return this.returnType_;
            }

            public Builder mergeReturnType(Type type) {
                if ((this.bitField0_ & 8) != 8 || this.returnType_ == Type.getDefaultInstance()) {
                    this.returnType_ = type;
                } else {
                    this.returnType_ = Type.newBuilder(this.returnType_).mergeFrom(type).buildPartial();
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setReturnTypeId(int i) {
                this.bitField0_ |= 16;
                this.returnTypeId_ = i;
                return this;
            }

            private void ensureTypeParameterIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.typeParameter_ = new ArrayList(this.typeParameter_);
                    this.bitField0_ |= 32;
                }
            }

            public int getTypeParameterCount() {
                return this.typeParameter_.size();
            }

            public TypeParameter getTypeParameter(int i) {
                return (TypeParameter) this.typeParameter_.get(i);
            }

            public boolean hasReceiverType() {
                return (this.bitField0_ & 64) == 64;
            }

            public Type getReceiverType() {
                return this.receiverType_;
            }

            public Builder mergeReceiverType(Type type) {
                if ((this.bitField0_ & 64) != 64 || this.receiverType_ == Type.getDefaultInstance()) {
                    this.receiverType_ = type;
                } else {
                    this.receiverType_ = Type.newBuilder(this.receiverType_).mergeFrom(type).buildPartial();
                }
                this.bitField0_ |= 64;
                return this;
            }

            public Builder setReceiverTypeId(int i) {
                this.bitField0_ |= 128;
                this.receiverTypeId_ = i;
                return this;
            }

            private void ensureValueParameterIsMutable() {
                if ((this.bitField0_ & 256) != 256) {
                    this.valueParameter_ = new ArrayList(this.valueParameter_);
                    this.bitField0_ |= 256;
                }
            }

            public int getValueParameterCount() {
                return this.valueParameter_.size();
            }

            public ValueParameter getValueParameter(int i) {
                return (ValueParameter) this.valueParameter_.get(i);
            }

            public boolean hasTypeTable() {
                return (this.bitField0_ & 512) == 512;
            }

            public TypeTable getTypeTable() {
                return this.typeTable_;
            }

            public Builder mergeTypeTable(TypeTable typeTable) {
                if ((this.bitField0_ & 512) != 512 || this.typeTable_ == TypeTable.getDefaultInstance()) {
                    this.typeTable_ = typeTable;
                } else {
                    this.typeTable_ = TypeTable.newBuilder(this.typeTable_).mergeFrom(typeTable).buildPartial();
                }
                this.bitField0_ |= 512;
                return this;
            }

            private void ensureVersionRequirementIsMutable() {
                if ((this.bitField0_ & 1024) != 1024) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= 1024;
                }
            }

            public boolean hasContract() {
                return (this.bitField0_ & 2048) == 2048;
            }

            public Contract getContract() {
                return this.contract_;
            }

            public Builder mergeContract(Contract contract) {
                if ((this.bitField0_ & 2048) != 2048 || this.contract_ == Contract.getDefaultInstance()) {
                    this.contract_ = contract;
                } else {
                    this.contract_ = Contract.newBuilder(this.contract_).mergeFrom(contract).buildPartial();
                }
                this.bitField0_ |= 2048;
                return this;
            }
        }

        private Function(ExtendableBuilder<Function, ?> extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private Function(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Function getDefaultInstance() {
            return defaultInstance;
        }

        public Function getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARNING: type inference failed for: r9v0 */
        /* JADX WARNING: type inference failed for: r9v1, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder] */
        /* JADX WARNING: type inference failed for: r9v2, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder] */
        /* JADX WARNING: type inference failed for: r9v5, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder] */
        /* JADX WARNING: type inference failed for: r9v6, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder] */
        /* JADX WARNING: type inference failed for: r9v9, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable$Builder] */
        /* JADX WARNING: type inference failed for: r9v10, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable$Builder] */
        /* JADX WARNING: type inference failed for: r9v18, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Contract$Builder] */
        /* JADX WARNING: type inference failed for: r9v19, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Contract$Builder] */
        /* JADX WARNING: type inference failed for: r9v20 */
        /* JADX WARNING: type inference failed for: r9v21 */
        /* JADX WARNING: type inference failed for: r9v22 */
        /* JADX WARNING: type inference failed for: r9v23 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r9v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable$Builder, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Contract$Builder]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable$Builder, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Contract$Builder]
  mth insns count: 226
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
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 5 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private Function(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream r12, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite r13) throws kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException {
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
                r5 = 1024(0x400, float:1.435E-42)
                r6 = 256(0x100, float:3.59E-43)
                r7 = 32
                if (r3 != 0) goto L_0x01f4
                int r8 = r12.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r9 = 0
                switch(r8) {
                    case 0: goto L_0x019b;
                    case 8: goto L_0x018d;
                    case 16: goto L_0x017f;
                    case 26: goto L_0x0154;
                    case 34: goto L_0x013a;
                    case 42: goto L_0x0111;
                    case 50: goto L_0x00f7;
                    case 56: goto L_0x00e9;
                    case 64: goto L_0x00db;
                    case 72: goto L_0x00ce;
                    case 242: goto L_0x00a3;
                    case 248: goto L_0x0087;
                    case 250: goto L_0x0054;
                    case 258: goto L_0x002c;
                    default: goto L_0x0026;
                }     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
            L_0x0026:
                boolean r5 = r11.parseUnknownField(r12, r2, r13, r8)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x019e
            L_0x002c:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8 = r8 & r6
                if (r8 != r6) goto L_0x0037
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Contract r8 = r11.contract_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Contract$Builder r9 = r8.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
            L_0x0037:
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Contract> r8 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r8 = r12.readMessage(r8, r13)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Contract r8 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract) r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.contract_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                if (r9 == 0) goto L_0x004e
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Contract r8 = r11.contract_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r9.mergeFrom(r8)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Contract r8 = r9.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.contract_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
            L_0x004e:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8 = r8 | r6
                r11.bitField0_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x0016
            L_0x0054:
                int r8 = r12.readRawVarint32()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                int r8 = r12.pushLimit(r8)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r9 = r4 & 1024(0x400, float:1.435E-42)
                if (r9 == r5) goto L_0x006f
                int r9 = r12.getBytesUntilLimit()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                if (r9 <= 0) goto L_0x006f
                java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r9.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.versionRequirement_ = r9     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r4 = r4 | 1024(0x400, float:1.435E-42)
            L_0x006f:
                int r9 = r12.getBytesUntilLimit()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                if (r9 <= 0) goto L_0x0083
                java.util.List<java.lang.Integer> r9 = r11.versionRequirement_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                int r10 = r12.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r9.add(r10)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x006f
            L_0x0083:
                r12.popLimit(r8)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x0016
            L_0x0087:
                r8 = r4 & 1024(0x400, float:1.435E-42)
                if (r8 == r5) goto L_0x0094
                java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.versionRequirement_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r4 = r4 | 1024(0x400, float:1.435E-42)
            L_0x0094:
                java.util.List<java.lang.Integer> r8 = r11.versionRequirement_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                int r9 = r12.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8.add(r9)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x0016
            L_0x00a3:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r10 = 128(0x80, float:1.794E-43)
                r8 = r8 & r10
                if (r8 != r10) goto L_0x00b0
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable r8 = r11.typeTable_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable$Builder r9 = r8.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
            L_0x00b0:
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable> r8 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r8 = r12.readMessage(r8, r13)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable r8 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable) r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.typeTable_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                if (r9 == 0) goto L_0x00c7
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable r8 = r11.typeTable_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r9.mergeFrom(r8)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable r8 = r9.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.typeTable_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
            L_0x00c7:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8 = r8 | r10
                r11.bitField0_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x0016
            L_0x00ce:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8 = r8 | r1
                r11.bitField0_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                int r8 = r12.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.flags_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x0016
            L_0x00db:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8 = r8 | 64
                r11.bitField0_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                int r8 = r12.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.receiverTypeId_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x0016
            L_0x00e9:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8 = r8 | 16
                r11.bitField0_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                int r8 = r12.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.returnTypeId_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x0016
            L_0x00f7:
                r8 = r4 & 256(0x100, float:3.59E-43)
                if (r8 == r6) goto L_0x0104
                java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.valueParameter_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r4 = r4 | 256(0x100, float:3.59E-43)
            L_0x0104:
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter> r8 = r11.valueParameter_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter> r9 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r9 = r12.readMessage(r9, r13)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8.add(r9)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x0016
            L_0x0111:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8 = r8 & r7
                if (r8 != r7) goto L_0x011c
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r8 = r11.receiverType_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder r9 = r8.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
            L_0x011c:
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type> r8 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r8 = r12.readMessage(r8, r13)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r8 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type) r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.receiverType_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                if (r9 == 0) goto L_0x0133
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r8 = r11.receiverType_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r9.mergeFrom(r8)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r8 = r9.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.receiverType_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
            L_0x0133:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8 = r8 | r7
                r11.bitField0_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x0016
            L_0x013a:
                r8 = r4 & 32
                if (r8 == r7) goto L_0x0147
                java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.typeParameter_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r4 = r4 | 32
            L_0x0147:
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeParameter> r8 = r11.typeParameter_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeParameter> r9 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r9 = r12.readMessage(r9, r13)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8.add(r9)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x0016
            L_0x0154:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r10 = 8
                r8 = r8 & r10
                if (r8 != r10) goto L_0x0161
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r8 = r11.returnType_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder r9 = r8.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
            L_0x0161:
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type> r8 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r8 = r12.readMessage(r8, r13)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r8 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type) r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.returnType_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                if (r9 == 0) goto L_0x0178
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r8 = r11.returnType_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r9.mergeFrom(r8)     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r8 = r9.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.returnType_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
            L_0x0178:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8 = r8 | r10
                r11.bitField0_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x0016
            L_0x017f:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8 = r8 | 4
                r11.bitField0_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                int r8 = r12.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.name_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x0016
            L_0x018d:
                int r8 = r11.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r8 = r8 | 2
                r11.bitField0_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                int r8 = r12.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                r11.oldFlags_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01b4, IOException -> 0x01a5 }
                goto L_0x0016
            L_0x019b:
                r3 = 1
                goto L_0x0016
            L_0x019e:
                if (r5 != 0) goto L_0x0016
                r3 = 1
                goto L_0x0016
            L_0x01a3:
                r12 = move-exception
                goto L_0x01ba
            L_0x01a5:
                r12 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r13 = new kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x01a3 }
                java.lang.String r12 = r12.getMessage()     // Catch:{ all -> 0x01a3 }
                r13.<init>(r12)     // Catch:{ all -> 0x01a3 }
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r12 = r13.setUnfinishedMessage(r11)     // Catch:{ all -> 0x01a3 }
                throw r12     // Catch:{ all -> 0x01a3 }
            L_0x01b4:
                r12 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r12 = r12.setUnfinishedMessage(r11)     // Catch:{ all -> 0x01a3 }
                throw r12     // Catch:{ all -> 0x01a3 }
            L_0x01ba:
                r13 = r4 & 32
                if (r13 != r7) goto L_0x01c6
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeParameter> r13 = r11.typeParameter_
                java.util.List r13 = java.util.Collections.unmodifiableList(r13)
                r11.typeParameter_ = r13
            L_0x01c6:
                r13 = r4 & 256(0x100, float:3.59E-43)
                if (r13 != r6) goto L_0x01d2
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter> r13 = r11.valueParameter_
                java.util.List r13 = java.util.Collections.unmodifiableList(r13)
                r11.valueParameter_ = r13
            L_0x01d2:
                r13 = r4 & 1024(0x400, float:1.435E-42)
                if (r13 != r5) goto L_0x01de
                java.util.List<java.lang.Integer> r13 = r11.versionRequirement_
                java.util.List r13 = java.util.Collections.unmodifiableList(r13)
                r11.versionRequirement_ = r13
            L_0x01de:
                r2.flush()     // Catch:{ IOException -> 0x01e1, all -> 0x01e8 }
            L_0x01e1:
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r13 = r0.toByteString()
                r11.unknownFields = r13
                goto L_0x01f0
            L_0x01e8:
                r12 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r13 = r0.toByteString()
                r11.unknownFields = r13
                throw r12
            L_0x01f0:
                r11.makeExtensionsImmutable()
                throw r12
            L_0x01f4:
                r12 = r4 & 32
                if (r12 != r7) goto L_0x0200
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeParameter> r12 = r11.typeParameter_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r11.typeParameter_ = r12
            L_0x0200:
                r12 = r4 & 256(0x100, float:3.59E-43)
                if (r12 != r6) goto L_0x020c
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter> r12 = r11.valueParameter_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r11.valueParameter_ = r12
            L_0x020c:
                r12 = r4 & 1024(0x400, float:1.435E-42)
                if (r12 != r5) goto L_0x0218
                java.util.List<java.lang.Integer> r12 = r11.versionRequirement_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r11.versionRequirement_ = r12
            L_0x0218:
                r2.flush()     // Catch:{ IOException -> 0x021b, all -> 0x0222 }
            L_0x021b:
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r12 = r0.toByteString()
                r11.unknownFields = r12
                goto L_0x022a
            L_0x0222:
                r12 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r13 = r0.toByteString()
                r11.unknownFields = r13
                throw r12
            L_0x022a:
                r11.makeExtensionsImmutable()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.<init>(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite):void");
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<Function> getParserForType() {
            return PARSER;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFlags() {
            return this.flags_;
        }

        public boolean hasOldFlags() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getOldFlags() {
            return this.oldFlags_;
        }

        public boolean hasName() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getName() {
            return this.name_;
        }

        public boolean hasReturnType() {
            return (this.bitField0_ & 8) == 8;
        }

        public Type getReturnType() {
            return this.returnType_;
        }

        public boolean hasReturnTypeId() {
            return (this.bitField0_ & 16) == 16;
        }

        public int getReturnTypeId() {
            return this.returnTypeId_;
        }

        public List<TypeParameter> getTypeParameterList() {
            return this.typeParameter_;
        }

        public int getTypeParameterCount() {
            return this.typeParameter_.size();
        }

        public TypeParameter getTypeParameter(int i) {
            return (TypeParameter) this.typeParameter_.get(i);
        }

        public boolean hasReceiverType() {
            return (this.bitField0_ & 32) == 32;
        }

        public Type getReceiverType() {
            return this.receiverType_;
        }

        public boolean hasReceiverTypeId() {
            return (this.bitField0_ & 64) == 64;
        }

        public int getReceiverTypeId() {
            return this.receiverTypeId_;
        }

        public List<ValueParameter> getValueParameterList() {
            return this.valueParameter_;
        }

        public int getValueParameterCount() {
            return this.valueParameter_.size();
        }

        public ValueParameter getValueParameter(int i) {
            return (ValueParameter) this.valueParameter_.get(i);
        }

        public boolean hasTypeTable() {
            return (this.bitField0_ & 128) == 128;
        }

        public TypeTable getTypeTable() {
            return this.typeTable_;
        }

        public List<Integer> getVersionRequirementList() {
            return this.versionRequirement_;
        }

        public boolean hasContract() {
            return (this.bitField0_ & 256) == 256;
        }

        public Contract getContract() {
            return this.contract_;
        }

        private void initFields() {
            this.flags_ = 6;
            this.oldFlags_ = 6;
            this.name_ = 0;
            this.returnType_ = Type.getDefaultInstance();
            this.returnTypeId_ = 0;
            this.typeParameter_ = Collections.emptyList();
            this.receiverType_ = Type.getDefaultInstance();
            this.receiverTypeId_ = 0;
            this.valueParameter_ = Collections.emptyList();
            this.typeTable_ = TypeTable.getDefaultInstance();
            this.versionRequirement_ = Collections.emptyList();
            this.contract_ = Contract.getDefaultInstance();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasReturnType() || getReturnType().isInitialized()) {
                for (int i = 0; i < getTypeParameterCount(); i++) {
                    if (!getTypeParameter(i).isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    }
                }
                if (!hasReceiverType() || getReceiverType().isInitialized()) {
                    for (int i2 = 0; i2 < getValueParameterCount(); i2++) {
                        if (!getValueParameter(i2).isInitialized()) {
                            this.memoizedIsInitialized = 0;
                            return false;
                        }
                    }
                    if (hasTypeTable() && !getTypeTable().isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    } else if (hasContract() && !getContract().isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    } else if (!extensionsAreInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    } else {
                        this.memoizedIsInitialized = 1;
                        return true;
                    }
                } else {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            } else {
                this.memoizedIsInitialized = 0;
                return false;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(1, this.oldFlags_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt32(2, this.name_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeMessage(3, this.returnType_);
            }
            for (int i = 0; i < this.typeParameter_.size(); i++) {
                codedOutputStream.writeMessage(4, (MessageLite) this.typeParameter_.get(i));
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeMessage(5, this.receiverType_);
            }
            for (int i2 = 0; i2 < this.valueParameter_.size(); i2++) {
                codedOutputStream.writeMessage(6, (MessageLite) this.valueParameter_.get(i2));
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeInt32(7, this.returnTypeId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeInt32(8, this.receiverTypeId_);
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(9, this.flags_);
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeMessage(30, this.typeTable_);
            }
            for (int i3 = 0; i3 < this.versionRequirement_.size(); i3++) {
                codedOutputStream.writeInt32(31, ((Integer) this.versionRequirement_.get(i3)).intValue());
            }
            if ((this.bitField0_ & 256) == 256) {
                codedOutputStream.writeMessage(32, this.contract_);
            }
            newExtensionWriter.writeUntil(19000, codedOutputStream);
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeInt32Size = (this.bitField0_ & 2) == 2 ? CodedOutputStream.computeInt32Size(1, this.oldFlags_) + 0 : 0;
            if ((this.bitField0_ & 4) == 4) {
                computeInt32Size += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            if ((this.bitField0_ & 8) == 8) {
                computeInt32Size += CodedOutputStream.computeMessageSize(3, this.returnType_);
            }
            int i2 = computeInt32Size;
            for (int i3 = 0; i3 < this.typeParameter_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(4, (MessageLite) this.typeParameter_.get(i3));
            }
            if ((this.bitField0_ & 32) == 32) {
                i2 += CodedOutputStream.computeMessageSize(5, this.receiverType_);
            }
            for (int i4 = 0; i4 < this.valueParameter_.size(); i4++) {
                i2 += CodedOutputStream.computeMessageSize(6, (MessageLite) this.valueParameter_.get(i4));
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += CodedOutputStream.computeInt32Size(7, this.returnTypeId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                i2 += CodedOutputStream.computeInt32Size(8, this.receiverTypeId_);
            }
            if ((this.bitField0_ & 1) == 1) {
                i2 += CodedOutputStream.computeInt32Size(9, this.flags_);
            }
            if ((this.bitField0_ & 128) == 128) {
                i2 += CodedOutputStream.computeMessageSize(30, this.typeTable_);
            }
            int i5 = 0;
            for (int i6 = 0; i6 < this.versionRequirement_.size(); i6++) {
                i5 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.versionRequirement_.get(i6)).intValue());
            }
            int size = i2 + i5 + (getVersionRequirementList().size() * 2);
            if ((this.bitField0_ & 256) == 256) {
                size += CodedOutputStream.computeMessageSize(32, this.contract_);
            }
            int extensionsSerializedSize = size + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public static Function parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Function) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Function function) {
            return newBuilder().mergeFrom(function);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface FunctionOrBuilder extends ExtendableMessageOrBuilder {
    }

    public enum MemberKind implements EnumLite {
        DECLARATION(0, 0),
        FAKE_OVERRIDE(1, 1),
        DELEGATION(2, 2),
        SYNTHESIZED(3, 3);
        
        private static EnumLiteMap<MemberKind> internalValueMap;
        private final int value;

        static {
            internalValueMap = new EnumLiteMap<MemberKind>() {
                public MemberKind findValueByNumber(int i) {
                    return MemberKind.valueOf(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        public static MemberKind valueOf(int i) {
            switch (i) {
                case 0:
                    return DECLARATION;
                case 1:
                    return FAKE_OVERRIDE;
                case 2:
                    return DELEGATION;
                case 3:
                    return SYNTHESIZED;
                default:
                    return null;
            }
        }

        private MemberKind(int i, int i2) {
            this.value = i2;
        }
    }

    public enum Modality implements EnumLite {
        FINAL(0, 0),
        OPEN(1, 1),
        ABSTRACT(2, 2),
        SEALED(3, 3);
        
        private static EnumLiteMap<Modality> internalValueMap;
        private final int value;

        static {
            internalValueMap = new EnumLiteMap<Modality>() {
                public Modality findValueByNumber(int i) {
                    return Modality.valueOf(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        public static Modality valueOf(int i) {
            switch (i) {
                case 0:
                    return FINAL;
                case 1:
                    return OPEN;
                case 2:
                    return ABSTRACT;
                case 3:
                    return SEALED;
                default:
                    return null;
            }
        }

        private Modality(int i, int i2) {
            this.value = i2;
        }
    }

    public static final class Package extends ExtendableMessage<Package> implements PackageOrBuilder {
        public static Parser<Package> PARSER = new AbstractParser<Package>() {
            public Package parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Package(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Package defaultInstance = new Package(true);
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public List<Function> function_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<Property> property_;
        /* access modifiers changed from: private */
        public List<TypeAlias> typeAlias_;
        /* access modifiers changed from: private */
        public TypeTable typeTable_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;
        /* access modifiers changed from: private */
        public VersionRequirementTable versionRequirementTable_;

        public static final class Builder extends ExtendableBuilder<Package, Builder> implements PackageOrBuilder {
            private int bitField0_;
            private List<Function> function_ = Collections.emptyList();
            private List<Property> property_ = Collections.emptyList();
            private List<TypeAlias> typeAlias_ = Collections.emptyList();
            private TypeTable typeTable_ = TypeTable.getDefaultInstance();
            private VersionRequirementTable versionRequirementTable_ = VersionRequirementTable.getDefaultInstance();

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

            public Package getDefaultInstanceForType() {
                return Package.getDefaultInstance();
            }

            public Package build() {
                Package buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Package buildPartial() {
                Package packageR = new Package((ExtendableBuilder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) == 1) {
                    this.function_ = Collections.unmodifiableList(this.function_);
                    this.bitField0_ &= -2;
                }
                packageR.function_ = this.function_;
                if ((this.bitField0_ & 2) == 2) {
                    this.property_ = Collections.unmodifiableList(this.property_);
                    this.bitField0_ &= -3;
                }
                packageR.property_ = this.property_;
                if ((this.bitField0_ & 4) == 4) {
                    this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                    this.bitField0_ &= -5;
                }
                packageR.typeAlias_ = this.typeAlias_;
                if ((i & 8) != 8) {
                    i2 = 0;
                }
                packageR.typeTable_ = this.typeTable_;
                if ((i & 16) == 16) {
                    i2 |= 2;
                }
                packageR.versionRequirementTable_ = this.versionRequirementTable_;
                packageR.bitField0_ = i2;
                return packageR;
            }

            public Builder mergeFrom(Package packageR) {
                if (packageR == Package.getDefaultInstance()) {
                    return this;
                }
                if (!packageR.function_.isEmpty()) {
                    if (this.function_.isEmpty()) {
                        this.function_ = packageR.function_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureFunctionIsMutable();
                        this.function_.addAll(packageR.function_);
                    }
                }
                if (!packageR.property_.isEmpty()) {
                    if (this.property_.isEmpty()) {
                        this.property_ = packageR.property_;
                        this.bitField0_ &= -3;
                    } else {
                        ensurePropertyIsMutable();
                        this.property_.addAll(packageR.property_);
                    }
                }
                if (!packageR.typeAlias_.isEmpty()) {
                    if (this.typeAlias_.isEmpty()) {
                        this.typeAlias_ = packageR.typeAlias_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureTypeAliasIsMutable();
                        this.typeAlias_.addAll(packageR.typeAlias_);
                    }
                }
                if (packageR.hasTypeTable()) {
                    mergeTypeTable(packageR.getTypeTable());
                }
                if (packageR.hasVersionRequirementTable()) {
                    mergeVersionRequirementTable(packageR.getVersionRequirementTable());
                }
                mergeExtensionFields(packageR);
                setUnknownFields(getUnknownFields().concat(packageR.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getFunctionCount(); i++) {
                    if (!getFunction(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getPropertyCount(); i2++) {
                    if (!getProperty(i2).isInitialized()) {
                        return false;
                    }
                }
                for (int i3 = 0; i3 < getTypeAliasCount(); i3++) {
                    if (!getTypeAlias(i3).isInitialized()) {
                        return false;
                    }
                }
                if ((!hasTypeTable() || getTypeTable().isInitialized()) && extensionsAreInitialized()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Package packageR;
                Package packageR2 = null;
                try {
                    Package packageR3 = (Package) Package.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (packageR3 != null) {
                        mergeFrom(packageR3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    packageR = (Package) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    packageR2 = packageR;
                }
                if (packageR2 != null) {
                    mergeFrom(packageR2);
                }
                throw th;
            }

            private void ensureFunctionIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.function_ = new ArrayList(this.function_);
                    this.bitField0_ |= 1;
                }
            }

            public int getFunctionCount() {
                return this.function_.size();
            }

            public Function getFunction(int i) {
                return (Function) this.function_.get(i);
            }

            private void ensurePropertyIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.property_ = new ArrayList(this.property_);
                    this.bitField0_ |= 2;
                }
            }

            public int getPropertyCount() {
                return this.property_.size();
            }

            public Property getProperty(int i) {
                return (Property) this.property_.get(i);
            }

            private void ensureTypeAliasIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.typeAlias_ = new ArrayList(this.typeAlias_);
                    this.bitField0_ |= 4;
                }
            }

            public int getTypeAliasCount() {
                return this.typeAlias_.size();
            }

            public TypeAlias getTypeAlias(int i) {
                return (TypeAlias) this.typeAlias_.get(i);
            }

            public boolean hasTypeTable() {
                return (this.bitField0_ & 8) == 8;
            }

            public TypeTable getTypeTable() {
                return this.typeTable_;
            }

            public Builder mergeTypeTable(TypeTable typeTable) {
                if ((this.bitField0_ & 8) != 8 || this.typeTable_ == TypeTable.getDefaultInstance()) {
                    this.typeTable_ = typeTable;
                } else {
                    this.typeTable_ = TypeTable.newBuilder(this.typeTable_).mergeFrom(typeTable).buildPartial();
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeVersionRequirementTable(VersionRequirementTable versionRequirementTable) {
                if ((this.bitField0_ & 16) != 16 || this.versionRequirementTable_ == VersionRequirementTable.getDefaultInstance()) {
                    this.versionRequirementTable_ = versionRequirementTable;
                } else {
                    this.versionRequirementTable_ = VersionRequirementTable.newBuilder(this.versionRequirementTable_).mergeFrom(versionRequirementTable).buildPartial();
                }
                this.bitField0_ |= 16;
                return this;
            }
        }

        private Package(ExtendableBuilder<Package, ?> extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private Package(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Package getDefaultInstance() {
            return defaultInstance;
        }

        public Package getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARNING: type inference failed for: r9v0 */
        /* JADX WARNING: type inference failed for: r9v1, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable$Builder] */
        /* JADX WARNING: type inference failed for: r9v2, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable$Builder] */
        /* JADX WARNING: type inference failed for: r9v3, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable$Builder] */
        /* JADX WARNING: type inference failed for: r9v4, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable$Builder] */
        /* JADX WARNING: type inference failed for: r9v5 */
        /* JADX WARNING: type inference failed for: r9v6 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r9v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable$Builder, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable$Builder]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable$Builder, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable$Builder]
  mth insns count: 145
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
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private Package(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream r11, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite r12) throws kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException {
            /*
                r10 = this;
                r10.<init>()
                r0 = -1
                r10.memoizedIsInitialized = r0
                r10.memoizedSerializedSize = r0
                r10.initFields()
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString$Output r0 = kotlin.reflect.jvm.internal.impl.protobuf.ByteString.newOutput()
                r1 = 1
                kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream r2 = kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream.newInstance(r0, r1)
                r3 = 0
                r4 = 0
            L_0x0016:
                r5 = 4
                r6 = 2
                if (r3 != 0) goto L_0x012f
                int r7 = r11.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                if (r7 == 0) goto L_0x00db
                r8 = 26
                if (r7 == r8) goto L_0x00c1
                r8 = 34
                if (r7 == r8) goto L_0x00a7
                r8 = 42
                if (r7 == r8) goto L_0x008d
                r8 = 242(0xf2, float:3.39E-43)
                r9 = 0
                if (r7 == r8) goto L_0x0065
                r8 = 258(0x102, float:3.62E-43)
                if (r7 == r8) goto L_0x003d
                boolean r5 = r10.parseUnknownField(r11, r2, r12, r7)     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                if (r5 != 0) goto L_0x0016
                r3 = 1
                goto L_0x0016
            L_0x003d:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r7 = r7 & r6
                if (r7 != r6) goto L_0x0048
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable r7 = r10.versionRequirementTable_     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable$Builder r9 = r7.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
            L_0x0048:
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable> r7 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r7 = r11.readMessage(r7, r12)     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable r7 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable) r7     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r10.versionRequirementTable_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                if (r9 == 0) goto L_0x005f
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable r7 = r10.versionRequirementTable_     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r9.mergeFrom(r7)     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable r7 = r9.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r10.versionRequirementTable_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
            L_0x005f:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r7 = r7 | r6
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                goto L_0x0016
            L_0x0065:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r7 = r7 & r1
                if (r7 != r1) goto L_0x0070
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable r7 = r10.typeTable_     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable$Builder r9 = r7.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
            L_0x0070:
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable> r7 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r7 = r11.readMessage(r7, r12)     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable r7 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable) r7     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r10.typeTable_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                if (r9 == 0) goto L_0x0087
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable r7 = r10.typeTable_     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r9.mergeFrom(r7)     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable r7 = r9.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r10.typeTable_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
            L_0x0087:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r7 = r7 | r1
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                goto L_0x0016
            L_0x008d:
                r7 = r4 & 4
                if (r7 == r5) goto L_0x009a
                java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r7.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r10.typeAlias_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r4 = r4 | 4
            L_0x009a:
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeAlias> r7 = r10.typeAlias_     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeAlias> r8 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r8 = r11.readMessage(r8, r12)     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r7.add(r8)     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                goto L_0x0016
            L_0x00a7:
                r7 = r4 & 2
                if (r7 == r6) goto L_0x00b4
                java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r7.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r10.property_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r4 = r4 | 2
            L_0x00b4:
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property> r7 = r10.property_     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property> r8 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r8 = r11.readMessage(r8, r12)     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r7.add(r8)     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                goto L_0x0016
            L_0x00c1:
                r7 = r4 & 1
                if (r7 == r1) goto L_0x00ce
                java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r7.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r10.function_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r4 = r4 | 1
            L_0x00ce:
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function> r7 = r10.function_     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function> r8 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r8 = r11.readMessage(r8, r12)     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                r7.add(r8)     // Catch:{ InvalidProtocolBufferException -> 0x00ef, IOException -> 0x00e0 }
                goto L_0x0016
            L_0x00db:
                r3 = 1
                goto L_0x0016
            L_0x00de:
                r11 = move-exception
                goto L_0x00f5
            L_0x00e0:
                r11 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r12 = new kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x00de }
                java.lang.String r11 = r11.getMessage()     // Catch:{ all -> 0x00de }
                r12.<init>(r11)     // Catch:{ all -> 0x00de }
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r11 = r12.setUnfinishedMessage(r10)     // Catch:{ all -> 0x00de }
                throw r11     // Catch:{ all -> 0x00de }
            L_0x00ef:
                r11 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r11 = r11.setUnfinishedMessage(r10)     // Catch:{ all -> 0x00de }
                throw r11     // Catch:{ all -> 0x00de }
            L_0x00f5:
                r12 = r4 & 1
                if (r12 != r1) goto L_0x0101
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function> r12 = r10.function_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r10.function_ = r12
            L_0x0101:
                r12 = r4 & 2
                if (r12 != r6) goto L_0x010d
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property> r12 = r10.property_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r10.property_ = r12
            L_0x010d:
                r12 = r4 & 4
                if (r12 != r5) goto L_0x0119
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeAlias> r12 = r10.typeAlias_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r10.typeAlias_ = r12
            L_0x0119:
                r2.flush()     // Catch:{ IOException -> 0x011c, all -> 0x0123 }
            L_0x011c:
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r12 = r0.toByteString()
                r10.unknownFields = r12
                goto L_0x012b
            L_0x0123:
                r11 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r12 = r0.toByteString()
                r10.unknownFields = r12
                throw r11
            L_0x012b:
                r10.makeExtensionsImmutable()
                throw r11
            L_0x012f:
                r11 = r4 & 1
                if (r11 != r1) goto L_0x013b
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function> r11 = r10.function_
                java.util.List r11 = java.util.Collections.unmodifiableList(r11)
                r10.function_ = r11
            L_0x013b:
                r11 = r4 & 2
                if (r11 != r6) goto L_0x0147
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property> r11 = r10.property_
                java.util.List r11 = java.util.Collections.unmodifiableList(r11)
                r10.property_ = r11
            L_0x0147:
                r11 = r4 & 4
                if (r11 != r5) goto L_0x0153
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeAlias> r11 = r10.typeAlias_
                java.util.List r11 = java.util.Collections.unmodifiableList(r11)
                r10.typeAlias_ = r11
            L_0x0153:
                r2.flush()     // Catch:{ IOException -> 0x0156, all -> 0x015d }
            L_0x0156:
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r11 = r0.toByteString()
                r10.unknownFields = r11
                goto L_0x0165
            L_0x015d:
                r11 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r12 = r0.toByteString()
                r10.unknownFields = r12
                throw r11
            L_0x0165:
                r10.makeExtensionsImmutable()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.<init>(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite):void");
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<Package> getParserForType() {
            return PARSER;
        }

        public List<Function> getFunctionList() {
            return this.function_;
        }

        public int getFunctionCount() {
            return this.function_.size();
        }

        public Function getFunction(int i) {
            return (Function) this.function_.get(i);
        }

        public List<Property> getPropertyList() {
            return this.property_;
        }

        public int getPropertyCount() {
            return this.property_.size();
        }

        public Property getProperty(int i) {
            return (Property) this.property_.get(i);
        }

        public List<TypeAlias> getTypeAliasList() {
            return this.typeAlias_;
        }

        public int getTypeAliasCount() {
            return this.typeAlias_.size();
        }

        public TypeAlias getTypeAlias(int i) {
            return (TypeAlias) this.typeAlias_.get(i);
        }

        public boolean hasTypeTable() {
            return (this.bitField0_ & 1) == 1;
        }

        public TypeTable getTypeTable() {
            return this.typeTable_;
        }

        public boolean hasVersionRequirementTable() {
            return (this.bitField0_ & 2) == 2;
        }

        public VersionRequirementTable getVersionRequirementTable() {
            return this.versionRequirementTable_;
        }

        private void initFields() {
            this.function_ = Collections.emptyList();
            this.property_ = Collections.emptyList();
            this.typeAlias_ = Collections.emptyList();
            this.typeTable_ = TypeTable.getDefaultInstance();
            this.versionRequirementTable_ = VersionRequirementTable.getDefaultInstance();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getFunctionCount(); i++) {
                if (!getFunction(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < getPropertyCount(); i2++) {
                if (!getProperty(i2).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for (int i3 = 0; i3 < getTypeAliasCount(); i3++) {
                if (!getTypeAlias(i3).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if (hasTypeTable() && !getTypeTable().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else {
                this.memoizedIsInitialized = 1;
                return true;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            for (int i = 0; i < this.function_.size(); i++) {
                codedOutputStream.writeMessage(3, (MessageLite) this.function_.get(i));
            }
            for (int i2 = 0; i2 < this.property_.size(); i2++) {
                codedOutputStream.writeMessage(4, (MessageLite) this.property_.get(i2));
            }
            for (int i3 = 0; i3 < this.typeAlias_.size(); i3++) {
                codedOutputStream.writeMessage(5, (MessageLite) this.typeAlias_.get(i3));
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeMessage(30, this.typeTable_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(32, this.versionRequirementTable_);
            }
            newExtensionWriter.writeUntil(200, codedOutputStream);
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.function_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.function_.get(i3));
            }
            for (int i4 = 0; i4 < this.property_.size(); i4++) {
                i2 += CodedOutputStream.computeMessageSize(4, (MessageLite) this.property_.get(i4));
            }
            for (int i5 = 0; i5 < this.typeAlias_.size(); i5++) {
                i2 += CodedOutputStream.computeMessageSize(5, (MessageLite) this.typeAlias_.get(i5));
            }
            if ((this.bitField0_ & 1) == 1) {
                i2 += CodedOutputStream.computeMessageSize(30, this.typeTable_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeMessageSize(32, this.versionRequirementTable_);
            }
            int extensionsSerializedSize = i2 + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public static Package parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Package) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Package packageR) {
            return newBuilder().mergeFrom(packageR);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public static final class PackageFragment extends ExtendableMessage<PackageFragment> implements PackageFragmentOrBuilder {
        public static Parser<PackageFragment> PARSER = new AbstractParser<PackageFragment>() {
            public PackageFragment parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PackageFragment(codedInputStream, extensionRegistryLite);
            }
        };
        private static final PackageFragment defaultInstance = new PackageFragment(true);
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public List<Class> class__;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public Package package_;
        /* access modifiers changed from: private */
        public QualifiedNameTable qualifiedNames_;
        /* access modifiers changed from: private */
        public StringTable strings_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;

        public static final class Builder extends ExtendableBuilder<PackageFragment, Builder> implements PackageFragmentOrBuilder {
            private int bitField0_;
            private List<Class> class__ = Collections.emptyList();
            private Package package_ = Package.getDefaultInstance();
            private QualifiedNameTable qualifiedNames_ = QualifiedNameTable.getDefaultInstance();
            private StringTable strings_ = StringTable.getDefaultInstance();

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

            public PackageFragment getDefaultInstanceForType() {
                return PackageFragment.getDefaultInstance();
            }

            public PackageFragment build() {
                PackageFragment buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public PackageFragment buildPartial() {
                PackageFragment packageFragment = new PackageFragment((ExtendableBuilder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                packageFragment.strings_ = this.strings_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                packageFragment.qualifiedNames_ = this.qualifiedNames_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                packageFragment.package_ = this.package_;
                if ((this.bitField0_ & 8) == 8) {
                    this.class__ = Collections.unmodifiableList(this.class__);
                    this.bitField0_ &= -9;
                }
                packageFragment.class__ = this.class__;
                packageFragment.bitField0_ = i2;
                return packageFragment;
            }

            public Builder mergeFrom(PackageFragment packageFragment) {
                if (packageFragment == PackageFragment.getDefaultInstance()) {
                    return this;
                }
                if (packageFragment.hasStrings()) {
                    mergeStrings(packageFragment.getStrings());
                }
                if (packageFragment.hasQualifiedNames()) {
                    mergeQualifiedNames(packageFragment.getQualifiedNames());
                }
                if (packageFragment.hasPackage()) {
                    mergePackage(packageFragment.getPackage());
                }
                if (!packageFragment.class__.isEmpty()) {
                    if (this.class__.isEmpty()) {
                        this.class__ = packageFragment.class__;
                        this.bitField0_ &= -9;
                    } else {
                        ensureClass_IsMutable();
                        this.class__.addAll(packageFragment.class__);
                    }
                }
                mergeExtensionFields(packageFragment);
                setUnknownFields(getUnknownFields().concat(packageFragment.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                if (hasQualifiedNames() && !getQualifiedNames().isInitialized()) {
                    return false;
                }
                if (hasPackage() && !getPackage().isInitialized()) {
                    return false;
                }
                for (int i = 0; i < getClass_Count(); i++) {
                    if (!getClass_(i).isInitialized()) {
                        return false;
                    }
                }
                if (!extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PackageFragment packageFragment;
                PackageFragment packageFragment2 = null;
                try {
                    PackageFragment packageFragment3 = (PackageFragment) PackageFragment.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (packageFragment3 != null) {
                        mergeFrom(packageFragment3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    packageFragment = (PackageFragment) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    packageFragment2 = packageFragment;
                }
                if (packageFragment2 != null) {
                    mergeFrom(packageFragment2);
                }
                throw th;
            }

            public Builder mergeStrings(StringTable stringTable) {
                if ((this.bitField0_ & 1) != 1 || this.strings_ == StringTable.getDefaultInstance()) {
                    this.strings_ = stringTable;
                } else {
                    this.strings_ = StringTable.newBuilder(this.strings_).mergeFrom(stringTable).buildPartial();
                }
                this.bitField0_ |= 1;
                return this;
            }

            public boolean hasQualifiedNames() {
                return (this.bitField0_ & 2) == 2;
            }

            public QualifiedNameTable getQualifiedNames() {
                return this.qualifiedNames_;
            }

            public Builder mergeQualifiedNames(QualifiedNameTable qualifiedNameTable) {
                if ((this.bitField0_ & 2) != 2 || this.qualifiedNames_ == QualifiedNameTable.getDefaultInstance()) {
                    this.qualifiedNames_ = qualifiedNameTable;
                } else {
                    this.qualifiedNames_ = QualifiedNameTable.newBuilder(this.qualifiedNames_).mergeFrom(qualifiedNameTable).buildPartial();
                }
                this.bitField0_ |= 2;
                return this;
            }

            public boolean hasPackage() {
                return (this.bitField0_ & 4) == 4;
            }

            public Package getPackage() {
                return this.package_;
            }

            public Builder mergePackage(Package packageR) {
                if ((this.bitField0_ & 4) != 4 || this.package_ == Package.getDefaultInstance()) {
                    this.package_ = packageR;
                } else {
                    this.package_ = Package.newBuilder(this.package_).mergeFrom(packageR).buildPartial();
                }
                this.bitField0_ |= 4;
                return this;
            }

            private void ensureClass_IsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.class__ = new ArrayList(this.class__);
                    this.bitField0_ |= 8;
                }
            }

            public int getClass_Count() {
                return this.class__.size();
            }

            public Class getClass_(int i) {
                return (Class) this.class__.get(i);
            }
        }

        private PackageFragment(ExtendableBuilder<PackageFragment, ?> extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private PackageFragment(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static PackageFragment getDefaultInstance() {
            return defaultInstance;
        }

        public PackageFragment getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARNING: type inference failed for: r8v0 */
        /* JADX WARNING: type inference failed for: r8v1, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable$Builder] */
        /* JADX WARNING: type inference failed for: r8v2, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable$Builder] */
        /* JADX WARNING: type inference failed for: r8v3, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable$Builder] */
        /* JADX WARNING: type inference failed for: r8v4, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable$Builder] */
        /* JADX WARNING: type inference failed for: r8v5, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package$Builder] */
        /* JADX WARNING: type inference failed for: r8v6, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package$Builder] */
        /* JADX WARNING: type inference failed for: r8v7 */
        /* JADX WARNING: type inference failed for: r8v8 */
        /* JADX WARNING: type inference failed for: r8v9 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r8v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable$Builder, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable$Builder, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package$Builder]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable$Builder, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable$Builder, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package$Builder]
  mth insns count: 123
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
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 4 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private PackageFragment(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream r10, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite r11) throws kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException {
            /*
                r9 = this;
                r9.<init>()
                r0 = -1
                r9.memoizedIsInitialized = r0
                r9.memoizedSerializedSize = r0
                r9.initFields()
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString$Output r0 = kotlin.reflect.jvm.internal.impl.protobuf.ByteString.newOutput()
                r1 = 1
                kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream r2 = kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream.newInstance(r0, r1)
                r3 = 0
                r4 = 0
            L_0x0016:
                r5 = 8
                if (r3 != 0) goto L_0x010a
                int r6 = r10.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                if (r6 == 0) goto L_0x00ce
                r7 = 10
                r8 = 0
                if (r6 == r7) goto L_0x00a5
                r7 = 18
                if (r6 == r7) goto L_0x007b
                r7 = 26
                if (r6 == r7) goto L_0x0052
                r7 = 34
                if (r6 == r7) goto L_0x0039
                boolean r5 = r9.parseUnknownField(r10, r2, r11, r6)     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                if (r5 != 0) goto L_0x0016
                r3 = 1
                goto L_0x0016
            L_0x0039:
                r6 = r4 & 8
                if (r6 == r5) goto L_0x0046
                java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r6.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r9.class__ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r4 = r4 | 8
            L_0x0046:
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class> r6 = r9.class__     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class> r7 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r7 = r10.readMessage(r7, r11)     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r6.add(r7)     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                goto L_0x0016
            L_0x0052:
                int r6 = r9.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r7 = 4
                r6 = r6 & r7
                if (r6 != r7) goto L_0x005e
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package r6 = r9.package_     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package$Builder r8 = r6.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
            L_0x005e:
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package> r6 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r6 = r10.readMessage(r6, r11)     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package r6 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package) r6     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r9.package_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                if (r8 == 0) goto L_0x0075
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package r6 = r9.package_     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r8.mergeFrom(r6)     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package r6 = r8.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r9.package_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
            L_0x0075:
                int r6 = r9.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r6 = r6 | r7
                r9.bitField0_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                goto L_0x0016
            L_0x007b:
                int r6 = r9.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r7 = 2
                r6 = r6 & r7
                if (r6 != r7) goto L_0x0087
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable r6 = r9.qualifiedNames_     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable$Builder r8 = r6.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
            L_0x0087:
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable> r6 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r6 = r10.readMessage(r6, r11)     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable r6 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable) r6     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r9.qualifiedNames_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                if (r8 == 0) goto L_0x009e
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable r6 = r9.qualifiedNames_     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r8.mergeFrom(r6)     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$QualifiedNameTable r6 = r8.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r9.qualifiedNames_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
            L_0x009e:
                int r6 = r9.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r6 = r6 | r7
                r9.bitField0_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                goto L_0x0016
            L_0x00a5:
                int r6 = r9.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r6 = r6 & r1
                if (r6 != r1) goto L_0x00b0
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable r6 = r9.strings_     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable$Builder r8 = r6.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
            L_0x00b0:
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable> r6 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r6 = r10.readMessage(r6, r11)     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable r6 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable) r6     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r9.strings_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                if (r8 == 0) goto L_0x00c7
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable r6 = r9.strings_     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r8.mergeFrom(r6)     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$StringTable r6 = r8.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r9.strings_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
            L_0x00c7:
                int r6 = r9.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                r6 = r6 | r1
                r9.bitField0_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00e2, IOException -> 0x00d3 }
                goto L_0x0016
            L_0x00ce:
                r3 = 1
                goto L_0x0016
            L_0x00d1:
                r10 = move-exception
                goto L_0x00e8
            L_0x00d3:
                r10 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r11 = new kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x00d1 }
                java.lang.String r10 = r10.getMessage()     // Catch:{ all -> 0x00d1 }
                r11.<init>(r10)     // Catch:{ all -> 0x00d1 }
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r10 = r11.setUnfinishedMessage(r9)     // Catch:{ all -> 0x00d1 }
                throw r10     // Catch:{ all -> 0x00d1 }
            L_0x00e2:
                r10 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r10 = r10.setUnfinishedMessage(r9)     // Catch:{ all -> 0x00d1 }
                throw r10     // Catch:{ all -> 0x00d1 }
            L_0x00e8:
                r11 = r4 & 8
                if (r11 != r5) goto L_0x00f4
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class> r11 = r9.class__
                java.util.List r11 = java.util.Collections.unmodifiableList(r11)
                r9.class__ = r11
            L_0x00f4:
                r2.flush()     // Catch:{ IOException -> 0x00f7, all -> 0x00fe }
            L_0x00f7:
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r11 = r0.toByteString()
                r9.unknownFields = r11
                goto L_0x0106
            L_0x00fe:
                r10 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r11 = r0.toByteString()
                r9.unknownFields = r11
                throw r10
            L_0x0106:
                r9.makeExtensionsImmutable()
                throw r10
            L_0x010a:
                r10 = r4 & 8
                if (r10 != r5) goto L_0x0116
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class> r10 = r9.class__
                java.util.List r10 = java.util.Collections.unmodifiableList(r10)
                r9.class__ = r10
            L_0x0116:
                r2.flush()     // Catch:{ IOException -> 0x0119, all -> 0x0120 }
            L_0x0119:
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r10 = r0.toByteString()
                r9.unknownFields = r10
                goto L_0x0128
            L_0x0120:
                r10 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r11 = r0.toByteString()
                r9.unknownFields = r11
                throw r10
            L_0x0128:
                r9.makeExtensionsImmutable()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.<init>(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite):void");
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<PackageFragment> getParserForType() {
            return PARSER;
        }

        public boolean hasStrings() {
            return (this.bitField0_ & 1) == 1;
        }

        public StringTable getStrings() {
            return this.strings_;
        }

        public boolean hasQualifiedNames() {
            return (this.bitField0_ & 2) == 2;
        }

        public QualifiedNameTable getQualifiedNames() {
            return this.qualifiedNames_;
        }

        public boolean hasPackage() {
            return (this.bitField0_ & 4) == 4;
        }

        public Package getPackage() {
            return this.package_;
        }

        public List<Class> getClass_List() {
            return this.class__;
        }

        public int getClass_Count() {
            return this.class__.size();
        }

        public Class getClass_(int i) {
            return (Class) this.class__.get(i);
        }

        private void initFields() {
            this.strings_ = StringTable.getDefaultInstance();
            this.qualifiedNames_ = QualifiedNameTable.getDefaultInstance();
            this.package_ = Package.getDefaultInstance();
            this.class__ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (hasQualifiedNames() && !getQualifiedNames().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasPackage() || getPackage().isInitialized()) {
                for (int i = 0; i < getClass_Count(); i++) {
                    if (!getClass_(i).isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    }
                }
                if (!extensionsAreInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            } else {
                this.memoizedIsInitialized = 0;
                return false;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeMessage(1, this.strings_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(2, this.qualifiedNames_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeMessage(3, this.package_);
            }
            for (int i = 0; i < this.class__.size(); i++) {
                codedOutputStream.writeMessage(4, (MessageLite) this.class__.get(i));
            }
            newExtensionWriter.writeUntil(200, codedOutputStream);
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeMessageSize(1, this.strings_) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                computeMessageSize += CodedOutputStream.computeMessageSize(2, this.qualifiedNames_);
            }
            if ((this.bitField0_ & 4) == 4) {
                computeMessageSize += CodedOutputStream.computeMessageSize(3, this.package_);
            }
            for (int i2 = 0; i2 < this.class__.size(); i2++) {
                computeMessageSize += CodedOutputStream.computeMessageSize(4, (MessageLite) this.class__.get(i2));
            }
            int extensionsSerializedSize = computeMessageSize + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public static PackageFragment parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PackageFragment) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(PackageFragment packageFragment) {
            return newBuilder().mergeFrom(packageFragment);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface PackageFragmentOrBuilder extends ExtendableMessageOrBuilder {
    }

    public interface PackageOrBuilder extends ExtendableMessageOrBuilder {
    }

    public static final class Property extends ExtendableMessage<Property> implements PropertyOrBuilder {
        public static Parser<Property> PARSER = new AbstractParser<Property>() {
            public Property parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Property(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Property defaultInstance = new Property(true);
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int flags_;
        /* access modifiers changed from: private */
        public int getterFlags_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int name_;
        /* access modifiers changed from: private */
        public int oldFlags_;
        /* access modifiers changed from: private */
        public int receiverTypeId_;
        /* access modifiers changed from: private */
        public Type receiverType_;
        /* access modifiers changed from: private */
        public int returnTypeId_;
        /* access modifiers changed from: private */
        public Type returnType_;
        /* access modifiers changed from: private */
        public int setterFlags_;
        /* access modifiers changed from: private */
        public ValueParameter setterValueParameter_;
        /* access modifiers changed from: private */
        public List<TypeParameter> typeParameter_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;
        /* access modifiers changed from: private */
        public List<Integer> versionRequirement_;

        public static final class Builder extends ExtendableBuilder<Property, Builder> implements PropertyOrBuilder {
            private int bitField0_;
            private int flags_ = 518;
            private int getterFlags_;
            private int name_;
            private int oldFlags_ = 2054;
            private int receiverTypeId_;
            private Type receiverType_ = Type.getDefaultInstance();
            private int returnTypeId_;
            private Type returnType_ = Type.getDefaultInstance();
            private int setterFlags_;
            private ValueParameter setterValueParameter_ = ValueParameter.getDefaultInstance();
            private List<TypeParameter> typeParameter_ = Collections.emptyList();
            private List<Integer> versionRequirement_ = Collections.emptyList();

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

            public Property getDefaultInstanceForType() {
                return Property.getDefaultInstance();
            }

            public Property build() {
                Property buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Property buildPartial() {
                Property property = new Property((ExtendableBuilder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                property.flags_ = this.flags_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                property.oldFlags_ = this.oldFlags_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                property.name_ = this.name_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                property.returnType_ = this.returnType_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                property.returnTypeId_ = this.returnTypeId_;
                if ((this.bitField0_ & 32) == 32) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    this.bitField0_ &= -33;
                }
                property.typeParameter_ = this.typeParameter_;
                if ((i & 64) == 64) {
                    i2 |= 32;
                }
                property.receiverType_ = this.receiverType_;
                if ((i & 128) == 128) {
                    i2 |= 64;
                }
                property.receiverTypeId_ = this.receiverTypeId_;
                if ((i & 256) == 256) {
                    i2 |= 128;
                }
                property.setterValueParameter_ = this.setterValueParameter_;
                if ((i & 512) == 512) {
                    i2 |= 256;
                }
                property.getterFlags_ = this.getterFlags_;
                if ((i & 1024) == 1024) {
                    i2 |= 512;
                }
                property.setterFlags_ = this.setterFlags_;
                if ((this.bitField0_ & 2048) == 2048) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= -2049;
                }
                property.versionRequirement_ = this.versionRequirement_;
                property.bitField0_ = i2;
                return property;
            }

            public Builder mergeFrom(Property property) {
                if (property == Property.getDefaultInstance()) {
                    return this;
                }
                if (property.hasFlags()) {
                    setFlags(property.getFlags());
                }
                if (property.hasOldFlags()) {
                    setOldFlags(property.getOldFlags());
                }
                if (property.hasName()) {
                    setName(property.getName());
                }
                if (property.hasReturnType()) {
                    mergeReturnType(property.getReturnType());
                }
                if (property.hasReturnTypeId()) {
                    setReturnTypeId(property.getReturnTypeId());
                }
                if (!property.typeParameter_.isEmpty()) {
                    if (this.typeParameter_.isEmpty()) {
                        this.typeParameter_ = property.typeParameter_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureTypeParameterIsMutable();
                        this.typeParameter_.addAll(property.typeParameter_);
                    }
                }
                if (property.hasReceiverType()) {
                    mergeReceiverType(property.getReceiverType());
                }
                if (property.hasReceiverTypeId()) {
                    setReceiverTypeId(property.getReceiverTypeId());
                }
                if (property.hasSetterValueParameter()) {
                    mergeSetterValueParameter(property.getSetterValueParameter());
                }
                if (property.hasGetterFlags()) {
                    setGetterFlags(property.getGetterFlags());
                }
                if (property.hasSetterFlags()) {
                    setSetterFlags(property.getSetterFlags());
                }
                if (!property.versionRequirement_.isEmpty()) {
                    if (this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = property.versionRequirement_;
                        this.bitField0_ &= -2049;
                    } else {
                        ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(property.versionRequirement_);
                    }
                }
                mergeExtensionFields(property);
                setUnknownFields(getUnknownFields().concat(property.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                if (!hasName()) {
                    return false;
                }
                if (hasReturnType() && !getReturnType().isInitialized()) {
                    return false;
                }
                for (int i = 0; i < getTypeParameterCount(); i++) {
                    if (!getTypeParameter(i).isInitialized()) {
                        return false;
                    }
                }
                if (hasReceiverType() && !getReceiverType().isInitialized()) {
                    return false;
                }
                if ((!hasSetterValueParameter() || getSetterValueParameter().isInitialized()) && extensionsAreInitialized()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Property property;
                Property property2 = null;
                try {
                    Property property3 = (Property) Property.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (property3 != null) {
                        mergeFrom(property3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    property = (Property) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    property2 = property;
                }
                if (property2 != null) {
                    mergeFrom(property2);
                }
                throw th;
            }

            public Builder setFlags(int i) {
                this.bitField0_ |= 1;
                this.flags_ = i;
                return this;
            }

            public Builder setOldFlags(int i) {
                this.bitField0_ |= 2;
                this.oldFlags_ = i;
                return this;
            }

            public boolean hasName() {
                return (this.bitField0_ & 4) == 4;
            }

            public Builder setName(int i) {
                this.bitField0_ |= 4;
                this.name_ = i;
                return this;
            }

            public boolean hasReturnType() {
                return (this.bitField0_ & 8) == 8;
            }

            public Type getReturnType() {
                return this.returnType_;
            }

            public Builder mergeReturnType(Type type) {
                if ((this.bitField0_ & 8) != 8 || this.returnType_ == Type.getDefaultInstance()) {
                    this.returnType_ = type;
                } else {
                    this.returnType_ = Type.newBuilder(this.returnType_).mergeFrom(type).buildPartial();
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setReturnTypeId(int i) {
                this.bitField0_ |= 16;
                this.returnTypeId_ = i;
                return this;
            }

            private void ensureTypeParameterIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.typeParameter_ = new ArrayList(this.typeParameter_);
                    this.bitField0_ |= 32;
                }
            }

            public int getTypeParameterCount() {
                return this.typeParameter_.size();
            }

            public TypeParameter getTypeParameter(int i) {
                return (TypeParameter) this.typeParameter_.get(i);
            }

            public boolean hasReceiverType() {
                return (this.bitField0_ & 64) == 64;
            }

            public Type getReceiverType() {
                return this.receiverType_;
            }

            public Builder mergeReceiverType(Type type) {
                if ((this.bitField0_ & 64) != 64 || this.receiverType_ == Type.getDefaultInstance()) {
                    this.receiverType_ = type;
                } else {
                    this.receiverType_ = Type.newBuilder(this.receiverType_).mergeFrom(type).buildPartial();
                }
                this.bitField0_ |= 64;
                return this;
            }

            public Builder setReceiverTypeId(int i) {
                this.bitField0_ |= 128;
                this.receiverTypeId_ = i;
                return this;
            }

            public boolean hasSetterValueParameter() {
                return (this.bitField0_ & 256) == 256;
            }

            public ValueParameter getSetterValueParameter() {
                return this.setterValueParameter_;
            }

            public Builder mergeSetterValueParameter(ValueParameter valueParameter) {
                if ((this.bitField0_ & 256) != 256 || this.setterValueParameter_ == ValueParameter.getDefaultInstance()) {
                    this.setterValueParameter_ = valueParameter;
                } else {
                    this.setterValueParameter_ = ValueParameter.newBuilder(this.setterValueParameter_).mergeFrom(valueParameter).buildPartial();
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder setGetterFlags(int i) {
                this.bitField0_ |= 512;
                this.getterFlags_ = i;
                return this;
            }

            public Builder setSetterFlags(int i) {
                this.bitField0_ |= 1024;
                this.setterFlags_ = i;
                return this;
            }

            private void ensureVersionRequirementIsMutable() {
                if ((this.bitField0_ & 2048) != 2048) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= 2048;
                }
            }
        }

        private Property(ExtendableBuilder<Property, ?> extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private Property(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Property getDefaultInstance() {
            return defaultInstance;
        }

        public Property getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARNING: type inference failed for: r8v0 */
        /* JADX WARNING: type inference failed for: r8v1, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder] */
        /* JADX WARNING: type inference failed for: r8v2, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder] */
        /* JADX WARNING: type inference failed for: r8v5, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder] */
        /* JADX WARNING: type inference failed for: r8v6, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder] */
        /* JADX WARNING: type inference failed for: r8v7, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter$Builder] */
        /* JADX WARNING: type inference failed for: r8v8, types: [kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter$Builder] */
        /* JADX WARNING: type inference failed for: r8v16 */
        /* JADX WARNING: type inference failed for: r8v17 */
        /* JADX WARNING: type inference failed for: r8v18 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r8v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter$Builder]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter$Builder]
  mth insns count: 199
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
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 4 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private Property(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream r11, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite r12) throws kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException {
            /*
                r10 = this;
                r10.<init>()
                r0 = -1
                r10.memoizedIsInitialized = r0
                r10.memoizedSerializedSize = r0
                r10.initFields()
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString$Output r0 = kotlin.reflect.jvm.internal.impl.protobuf.ByteString.newOutput()
                r1 = 1
                kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream r2 = kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream.newInstance(r0, r1)
                r3 = 0
                r4 = 0
            L_0x0016:
                r5 = 2048(0x800, float:2.87E-42)
                r6 = 32
                if (r3 != 0) goto L_0x01bd
                int r7 = r11.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r8 = 0
                switch(r7) {
                    case 0: goto L_0x0170;
                    case 8: goto L_0x0162;
                    case 16: goto L_0x0154;
                    case 26: goto L_0x0129;
                    case 34: goto L_0x010f;
                    case 42: goto L_0x00e6;
                    case 50: goto L_0x00bb;
                    case 56: goto L_0x00ad;
                    case 64: goto L_0x009f;
                    case 72: goto L_0x0091;
                    case 80: goto L_0x0084;
                    case 88: goto L_0x0078;
                    case 248: goto L_0x005d;
                    case 250: goto L_0x002a;
                    default: goto L_0x0024;
                }     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
            L_0x0024:
                boolean r5 = r10.parseUnknownField(r11, r2, r12, r7)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0173
            L_0x002a:
                int r7 = r11.readRawVarint32()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                int r7 = r11.pushLimit(r7)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r8 = r4 & 2048(0x800, float:2.87E-42)
                if (r8 == r5) goto L_0x0045
                int r8 = r11.getBytesUntilLimit()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                if (r8 <= 0) goto L_0x0045
                java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r8.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.versionRequirement_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r4 = r4 | 2048(0x800, float:2.87E-42)
            L_0x0045:
                int r8 = r11.getBytesUntilLimit()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                if (r8 <= 0) goto L_0x0059
                java.util.List<java.lang.Integer> r8 = r10.versionRequirement_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                int r9 = r11.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r8.add(r9)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0045
            L_0x0059:
                r11.popLimit(r7)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0016
            L_0x005d:
                r7 = r4 & 2048(0x800, float:2.87E-42)
                if (r7 == r5) goto L_0x006a
                java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.versionRequirement_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r4 = r4 | 2048(0x800, float:2.87E-42)
            L_0x006a:
                java.util.List<java.lang.Integer> r7 = r10.versionRequirement_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                int r8 = r11.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7.add(r8)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0016
            L_0x0078:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7 = r7 | r1
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                int r7 = r11.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.flags_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0016
            L_0x0084:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7 = r7 | 64
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                int r7 = r11.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.receiverTypeId_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0016
            L_0x0091:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7 = r7 | 16
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                int r7 = r11.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.returnTypeId_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0016
            L_0x009f:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7 = r7 | 512(0x200, float:7.175E-43)
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                int r7 = r11.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.setterFlags_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0016
            L_0x00ad:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7 = r7 | 256(0x100, float:3.59E-43)
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                int r7 = r11.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.getterFlags_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0016
            L_0x00bb:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r9 = 128(0x80, float:1.794E-43)
                r7 = r7 & r9
                if (r7 != r9) goto L_0x00c8
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter r7 = r10.setterValueParameter_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter$Builder r8 = r7.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
            L_0x00c8:
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter> r7 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r7 = r11.readMessage(r7, r12)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter r7 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter) r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.setterValueParameter_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                if (r8 == 0) goto L_0x00df
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter r7 = r10.setterValueParameter_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r8.mergeFrom(r7)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter r7 = r8.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.setterValueParameter_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
            L_0x00df:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7 = r7 | r9
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0016
            L_0x00e6:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7 = r7 & r6
                if (r7 != r6) goto L_0x00f1
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r7 = r10.receiverType_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder r8 = r7.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
            L_0x00f1:
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type> r7 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r7 = r11.readMessage(r7, r12)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r7 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type) r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.receiverType_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                if (r8 == 0) goto L_0x0108
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r7 = r10.receiverType_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r8.mergeFrom(r7)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r7 = r8.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.receiverType_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
            L_0x0108:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7 = r7 | r6
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0016
            L_0x010f:
                r7 = r4 & 32
                if (r7 == r6) goto L_0x011c
                java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.typeParameter_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r4 = r4 | 32
            L_0x011c:
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeParameter> r7 = r10.typeParameter_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeParameter> r8 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r8 = r11.readMessage(r8, r12)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7.add(r8)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0016
            L_0x0129:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r9 = 8
                r7 = r7 & r9
                if (r7 != r9) goto L_0x0136
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r7 = r10.returnType_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type$Builder r8 = r7.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
            L_0x0136:
                kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type> r7 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r7 = r11.readMessage(r7, r12)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r7 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type) r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.returnType_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                if (r8 == 0) goto L_0x014d
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r7 = r10.returnType_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r8.mergeFrom(r7)     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r7 = r8.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.returnType_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
            L_0x014d:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7 = r7 | r9
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0016
            L_0x0154:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7 = r7 | 4
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                int r7 = r11.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.name_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0016
            L_0x0162:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r7 = r7 | 2
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                int r7 = r11.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                r10.oldFlags_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x0189, IOException -> 0x017a }
                goto L_0x0016
            L_0x0170:
                r3 = 1
                goto L_0x0016
            L_0x0173:
                if (r5 != 0) goto L_0x0016
                r3 = 1
                goto L_0x0016
            L_0x0178:
                r11 = move-exception
                goto L_0x018f
            L_0x017a:
                r11 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r12 = new kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x0178 }
                java.lang.String r11 = r11.getMessage()     // Catch:{ all -> 0x0178 }
                r12.<init>(r11)     // Catch:{ all -> 0x0178 }
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r11 = r12.setUnfinishedMessage(r10)     // Catch:{ all -> 0x0178 }
                throw r11     // Catch:{ all -> 0x0178 }
            L_0x0189:
                r11 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException r11 = r11.setUnfinishedMessage(r10)     // Catch:{ all -> 0x0178 }
                throw r11     // Catch:{ all -> 0x0178 }
            L_0x018f:
                r12 = r4 & 32
                if (r12 != r6) goto L_0x019b
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeParameter> r12 = r10.typeParameter_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r10.typeParameter_ = r12
            L_0x019b:
                r12 = r4 & 2048(0x800, float:2.87E-42)
                if (r12 != r5) goto L_0x01a7
                java.util.List<java.lang.Integer> r12 = r10.versionRequirement_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r10.versionRequirement_ = r12
            L_0x01a7:
                r2.flush()     // Catch:{ IOException -> 0x01aa, all -> 0x01b1 }
            L_0x01aa:
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r12 = r0.toByteString()
                r10.unknownFields = r12
                goto L_0x01b9
            L_0x01b1:
                r11 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r12 = r0.toByteString()
                r10.unknownFields = r12
                throw r11
            L_0x01b9:
                r10.makeExtensionsImmutable()
                throw r11
            L_0x01bd:
                r11 = r4 & 32
                if (r11 != r6) goto L_0x01c9
                java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeParameter> r11 = r10.typeParameter_
                java.util.List r11 = java.util.Collections.unmodifiableList(r11)
                r10.typeParameter_ = r11
            L_0x01c9:
                r11 = r4 & 2048(0x800, float:2.87E-42)
                if (r11 != r5) goto L_0x01d5
                java.util.List<java.lang.Integer> r11 = r10.versionRequirement_
                java.util.List r11 = java.util.Collections.unmodifiableList(r11)
                r10.versionRequirement_ = r11
            L_0x01d5:
                r2.flush()     // Catch:{ IOException -> 0x01d8, all -> 0x01df }
            L_0x01d8:
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r11 = r0.toByteString()
                r10.unknownFields = r11
                goto L_0x01e7
            L_0x01df:
                r11 = move-exception
                kotlin.reflect.jvm.internal.impl.protobuf.ByteString r12 = r0.toByteString()
                r10.unknownFields = r12
                throw r11
            L_0x01e7:
                r10.makeExtensionsImmutable()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.<init>(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite):void");
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<Property> getParserForType() {
            return PARSER;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFlags() {
            return this.flags_;
        }

        public boolean hasOldFlags() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getOldFlags() {
            return this.oldFlags_;
        }

        public boolean hasName() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getName() {
            return this.name_;
        }

        public boolean hasReturnType() {
            return (this.bitField0_ & 8) == 8;
        }

        public Type getReturnType() {
            return this.returnType_;
        }

        public boolean hasReturnTypeId() {
            return (this.bitField0_ & 16) == 16;
        }

        public int getReturnTypeId() {
            return this.returnTypeId_;
        }

        public List<TypeParameter> getTypeParameterList() {
            return this.typeParameter_;
        }

        public int getTypeParameterCount() {
            return this.typeParameter_.size();
        }

        public TypeParameter getTypeParameter(int i) {
            return (TypeParameter) this.typeParameter_.get(i);
        }

        public boolean hasReceiverType() {
            return (this.bitField0_ & 32) == 32;
        }

        public Type getReceiverType() {
            return this.receiverType_;
        }

        public boolean hasReceiverTypeId() {
            return (this.bitField0_ & 64) == 64;
        }

        public int getReceiverTypeId() {
            return this.receiverTypeId_;
        }

        public boolean hasSetterValueParameter() {
            return (this.bitField0_ & 128) == 128;
        }

        public ValueParameter getSetterValueParameter() {
            return this.setterValueParameter_;
        }

        public boolean hasGetterFlags() {
            return (this.bitField0_ & 256) == 256;
        }

        public int getGetterFlags() {
            return this.getterFlags_;
        }

        public boolean hasSetterFlags() {
            return (this.bitField0_ & 512) == 512;
        }

        public int getSetterFlags() {
            return this.setterFlags_;
        }

        public List<Integer> getVersionRequirementList() {
            return this.versionRequirement_;
        }

        private void initFields() {
            this.flags_ = 518;
            this.oldFlags_ = 2054;
            this.name_ = 0;
            this.returnType_ = Type.getDefaultInstance();
            this.returnTypeId_ = 0;
            this.typeParameter_ = Collections.emptyList();
            this.receiverType_ = Type.getDefaultInstance();
            this.receiverTypeId_ = 0;
            this.setterValueParameter_ = ValueParameter.getDefaultInstance();
            this.getterFlags_ = 0;
            this.setterFlags_ = 0;
            this.versionRequirement_ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasReturnType() || getReturnType().isInitialized()) {
                for (int i = 0; i < getTypeParameterCount(); i++) {
                    if (!getTypeParameter(i).isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    }
                }
                if (hasReceiverType() && !getReceiverType().isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                } else if (hasSetterValueParameter() && !getSetterValueParameter().isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                } else if (!extensionsAreInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                } else {
                    this.memoizedIsInitialized = 1;
                    return true;
                }
            } else {
                this.memoizedIsInitialized = 0;
                return false;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(1, this.oldFlags_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt32(2, this.name_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeMessage(3, this.returnType_);
            }
            for (int i = 0; i < this.typeParameter_.size(); i++) {
                codedOutputStream.writeMessage(4, (MessageLite) this.typeParameter_.get(i));
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeMessage(5, this.receiverType_);
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeMessage(6, this.setterValueParameter_);
            }
            if ((this.bitField0_ & 256) == 256) {
                codedOutputStream.writeInt32(7, this.getterFlags_);
            }
            if ((this.bitField0_ & 512) == 512) {
                codedOutputStream.writeInt32(8, this.setterFlags_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeInt32(9, this.returnTypeId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeInt32(10, this.receiverTypeId_);
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(11, this.flags_);
            }
            for (int i2 = 0; i2 < this.versionRequirement_.size(); i2++) {
                codedOutputStream.writeInt32(31, ((Integer) this.versionRequirement_.get(i2)).intValue());
            }
            newExtensionWriter.writeUntil(19000, codedOutputStream);
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeInt32Size = (this.bitField0_ & 2) == 2 ? CodedOutputStream.computeInt32Size(1, this.oldFlags_) + 0 : 0;
            if ((this.bitField0_ & 4) == 4) {
                computeInt32Size += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            if ((this.bitField0_ & 8) == 8) {
                computeInt32Size += CodedOutputStream.computeMessageSize(3, this.returnType_);
            }
            int i2 = computeInt32Size;
            for (int i3 = 0; i3 < this.typeParameter_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(4, (MessageLite) this.typeParameter_.get(i3));
            }
            if ((this.bitField0_ & 32) == 32) {
                i2 += CodedOutputStream.computeMessageSize(5, this.receiverType_);
            }
            if ((this.bitField0_ & 128) == 128) {
                i2 += CodedOutputStream.computeMessageSize(6, this.setterValueParameter_);
            }
            if ((this.bitField0_ & 256) == 256) {
                i2 += CodedOutputStream.computeInt32Size(7, this.getterFlags_);
            }
            if ((this.bitField0_ & 512) == 512) {
                i2 += CodedOutputStream.computeInt32Size(8, this.setterFlags_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += CodedOutputStream.computeInt32Size(9, this.returnTypeId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                i2 += CodedOutputStream.computeInt32Size(10, this.receiverTypeId_);
            }
            if ((this.bitField0_ & 1) == 1) {
                i2 += CodedOutputStream.computeInt32Size(11, this.flags_);
            }
            int i4 = 0;
            for (int i5 = 0; i5 < this.versionRequirement_.size(); i5++) {
                i4 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.versionRequirement_.get(i5)).intValue());
            }
            int size = i2 + i4 + (getVersionRequirementList().size() * 2) + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = size;
            return size;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Property property) {
            return newBuilder().mergeFrom(property);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface PropertyOrBuilder extends ExtendableMessageOrBuilder {
    }

    public static final class QualifiedNameTable extends GeneratedMessageLite implements QualifiedNameTableOrBuilder {
        public static Parser<QualifiedNameTable> PARSER = new AbstractParser<QualifiedNameTable>() {
            public QualifiedNameTable parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new QualifiedNameTable(codedInputStream, extensionRegistryLite);
            }
        };
        private static final QualifiedNameTable defaultInstance = new QualifiedNameTable(true);
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<QualifiedName> qualifiedName_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;

        public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<QualifiedNameTable, Builder> implements QualifiedNameTableOrBuilder {
            private int bitField0_;
            private List<QualifiedName> qualifiedName_ = Collections.emptyList();

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

            public QualifiedNameTable getDefaultInstanceForType() {
                return QualifiedNameTable.getDefaultInstance();
            }

            public QualifiedNameTable build() {
                QualifiedNameTable buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public QualifiedNameTable buildPartial() {
                QualifiedNameTable qualifiedNameTable = new QualifiedNameTable((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                if ((this.bitField0_ & 1) == 1) {
                    this.qualifiedName_ = Collections.unmodifiableList(this.qualifiedName_);
                    this.bitField0_ &= -2;
                }
                qualifiedNameTable.qualifiedName_ = this.qualifiedName_;
                return qualifiedNameTable;
            }

            public Builder mergeFrom(QualifiedNameTable qualifiedNameTable) {
                if (qualifiedNameTable == QualifiedNameTable.getDefaultInstance()) {
                    return this;
                }
                if (!qualifiedNameTable.qualifiedName_.isEmpty()) {
                    if (this.qualifiedName_.isEmpty()) {
                        this.qualifiedName_ = qualifiedNameTable.qualifiedName_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureQualifiedNameIsMutable();
                        this.qualifiedName_.addAll(qualifiedNameTable.qualifiedName_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(qualifiedNameTable.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getQualifiedNameCount(); i++) {
                    if (!getQualifiedName(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                QualifiedNameTable qualifiedNameTable;
                QualifiedNameTable qualifiedNameTable2 = null;
                try {
                    QualifiedNameTable qualifiedNameTable3 = (QualifiedNameTable) QualifiedNameTable.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (qualifiedNameTable3 != null) {
                        mergeFrom(qualifiedNameTable3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    qualifiedNameTable = (QualifiedNameTable) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    qualifiedNameTable2 = qualifiedNameTable;
                }
                if (qualifiedNameTable2 != null) {
                    mergeFrom(qualifiedNameTable2);
                }
                throw th;
            }

            private void ensureQualifiedNameIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.qualifiedName_ = new ArrayList(this.qualifiedName_);
                    this.bitField0_ |= 1;
                }
            }

            public int getQualifiedNameCount() {
                return this.qualifiedName_.size();
            }

            public QualifiedName getQualifiedName(int i) {
                return (QualifiedName) this.qualifiedName_.get(i);
            }
        }

        public static final class QualifiedName extends GeneratedMessageLite implements QualifiedNameOrBuilder {
            public static Parser<QualifiedName> PARSER = new AbstractParser<QualifiedName>() {
                public QualifiedName parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new QualifiedName(codedInputStream, extensionRegistryLite);
                }
            };
            private static final QualifiedName defaultInstance = new QualifiedName(true);
            /* access modifiers changed from: private */
            public int bitField0_;
            /* access modifiers changed from: private */
            public Kind kind_;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            /* access modifiers changed from: private */
            public int parentQualifiedName_;
            /* access modifiers changed from: private */
            public int shortName_;
            /* access modifiers changed from: private */
            public final ByteString unknownFields;

            public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<QualifiedName, Builder> implements QualifiedNameOrBuilder {
                private int bitField0_;
                private Kind kind_ = Kind.PACKAGE;
                private int parentQualifiedName_ = -1;
                private int shortName_;

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

                public QualifiedName getDefaultInstanceForType() {
                    return QualifiedName.getDefaultInstance();
                }

                public QualifiedName build() {
                    QualifiedName buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public QualifiedName buildPartial() {
                    QualifiedName qualifiedName = new QualifiedName((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                    int i = this.bitField0_;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    qualifiedName.parentQualifiedName_ = this.parentQualifiedName_;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    qualifiedName.shortName_ = this.shortName_;
                    if ((i & 4) == 4) {
                        i2 |= 4;
                    }
                    qualifiedName.kind_ = this.kind_;
                    qualifiedName.bitField0_ = i2;
                    return qualifiedName;
                }

                public Builder mergeFrom(QualifiedName qualifiedName) {
                    if (qualifiedName == QualifiedName.getDefaultInstance()) {
                        return this;
                    }
                    if (qualifiedName.hasParentQualifiedName()) {
                        setParentQualifiedName(qualifiedName.getParentQualifiedName());
                    }
                    if (qualifiedName.hasShortName()) {
                        setShortName(qualifiedName.getShortName());
                    }
                    if (qualifiedName.hasKind()) {
                        setKind(qualifiedName.getKind());
                    }
                    setUnknownFields(getUnknownFields().concat(qualifiedName.unknownFields));
                    return this;
                }

                public final boolean isInitialized() {
                    return hasShortName();
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    QualifiedName qualifiedName;
                    QualifiedName qualifiedName2 = null;
                    try {
                        QualifiedName qualifiedName3 = (QualifiedName) QualifiedName.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (qualifiedName3 != null) {
                            mergeFrom(qualifiedName3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        qualifiedName = (QualifiedName) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        qualifiedName2 = qualifiedName;
                    }
                    if (qualifiedName2 != null) {
                        mergeFrom(qualifiedName2);
                    }
                    throw th;
                }

                public Builder setParentQualifiedName(int i) {
                    this.bitField0_ |= 1;
                    this.parentQualifiedName_ = i;
                    return this;
                }

                public boolean hasShortName() {
                    return (this.bitField0_ & 2) == 2;
                }

                public Builder setShortName(int i) {
                    this.bitField0_ |= 2;
                    this.shortName_ = i;
                    return this;
                }

                public Builder setKind(Kind kind) {
                    if (kind != null) {
                        this.bitField0_ |= 4;
                        this.kind_ = kind;
                        return this;
                    }
                    throw new NullPointerException();
                }
            }

            public enum Kind implements EnumLite {
                CLASS(0, 0),
                PACKAGE(1, 1),
                LOCAL(2, 2);
                
                private static EnumLiteMap<Kind> internalValueMap;
                private final int value;

                static {
                    internalValueMap = new EnumLiteMap<Kind>() {
                        public Kind findValueByNumber(int i) {
                            return Kind.valueOf(i);
                        }
                    };
                }

                public final int getNumber() {
                    return this.value;
                }

                public static Kind valueOf(int i) {
                    switch (i) {
                        case 0:
                            return CLASS;
                        case 1:
                            return PACKAGE;
                        case 2:
                            return LOCAL;
                        default:
                            return null;
                    }
                }

                private Kind(int i, int i2) {
                    this.value = i2;
                }
            }

            private QualifiedName(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
                super(builder);
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = builder.getUnknownFields();
            }

            private QualifiedName(boolean z) {
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = ByteString.EMPTY;
            }

            public static QualifiedName getDefaultInstance() {
                return defaultInstance;
            }

            public QualifiedName getDefaultInstanceForType() {
                return defaultInstance;
            }

            private QualifiedName(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                initFields();
                Output newOutput = ByteString.newOutput();
                CodedOutputStream newInstance = CodedOutputStream.newInstance(newOutput, 1);
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag == 0) {
                            z = true;
                        } else if (readTag == 8) {
                            this.bitField0_ |= 1;
                            this.parentQualifiedName_ = codedInputStream.readInt32();
                        } else if (readTag == 16) {
                            this.bitField0_ |= 2;
                            this.shortName_ = codedInputStream.readInt32();
                        } else if (readTag == 24) {
                            int readEnum = codedInputStream.readEnum();
                            Kind valueOf = Kind.valueOf(readEnum);
                            if (valueOf == null) {
                                newInstance.writeRawVarint32(readTag);
                                newInstance.writeRawVarint32(readEnum);
                            } else {
                                this.bitField0_ |= 4;
                                this.kind_ = valueOf;
                            }
                        } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                            z = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
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

            public Parser<QualifiedName> getParserForType() {
                return PARSER;
            }

            public boolean hasParentQualifiedName() {
                return (this.bitField0_ & 1) == 1;
            }

            public int getParentQualifiedName() {
                return this.parentQualifiedName_;
            }

            public boolean hasShortName() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getShortName() {
                return this.shortName_;
            }

            public boolean hasKind() {
                return (this.bitField0_ & 4) == 4;
            }

            public Kind getKind() {
                return this.kind_;
            }

            private void initFields() {
                this.parentQualifiedName_ = -1;
                this.shortName_ = 0;
                this.kind_ = Kind.PACKAGE;
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                if (!hasShortName()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                getSerializedSize();
                if ((this.bitField0_ & 1) == 1) {
                    codedOutputStream.writeInt32(1, this.parentQualifiedName_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    codedOutputStream.writeInt32(2, this.shortName_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    codedOutputStream.writeEnum(3, this.kind_.getNumber());
                }
                codedOutputStream.writeRawBytes(this.unknownFields);
            }

            public int getSerializedSize() {
                int i = this.memoizedSerializedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if ((this.bitField0_ & 1) == 1) {
                    i2 = 0 + CodedOutputStream.computeInt32Size(1, this.parentQualifiedName_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    i2 += CodedOutputStream.computeInt32Size(2, this.shortName_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    i2 += CodedOutputStream.computeEnumSize(3, this.kind_.getNumber());
                }
                int size = i2 + this.unknownFields.size();
                this.memoizedSerializedSize = size;
                return size;
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder(QualifiedName qualifiedName) {
                return newBuilder().mergeFrom(qualifiedName);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }
        }

        public interface QualifiedNameOrBuilder extends MessageLiteOrBuilder {
        }

        private QualifiedNameTable(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private QualifiedNameTable(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static QualifiedNameTable getDefaultInstance() {
            return defaultInstance;
        }

        public QualifiedNameTable getDefaultInstanceForType() {
            return defaultInstance;
        }

        private QualifiedNameTable(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (!z2 || !true) {
                            this.qualifiedName_ = new ArrayList();
                            z2 |= true;
                        }
                        this.qualifiedName_.add(codedInputStream.readMessage(QualifiedName.PARSER, extensionRegistryLite));
                    } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.qualifiedName_ = Collections.unmodifiableList(this.qualifiedName_);
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
            if (z2 && true) {
                this.qualifiedName_ = Collections.unmodifiableList(this.qualifiedName_);
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

        public Parser<QualifiedNameTable> getParserForType() {
            return PARSER;
        }

        public int getQualifiedNameCount() {
            return this.qualifiedName_.size();
        }

        public QualifiedName getQualifiedName(int i) {
            return (QualifiedName) this.qualifiedName_.get(i);
        }

        private void initFields() {
            this.qualifiedName_ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getQualifiedNameCount(); i++) {
                if (!getQualifiedName(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.qualifiedName_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.qualifiedName_.get(i));
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.qualifiedName_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.qualifiedName_.get(i3));
            }
            int size = i2 + this.unknownFields.size();
            this.memoizedSerializedSize = size;
            return size;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(QualifiedNameTable qualifiedNameTable) {
            return newBuilder().mergeFrom(qualifiedNameTable);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface QualifiedNameTableOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class StringTable extends GeneratedMessageLite implements StringTableOrBuilder {
        public static Parser<StringTable> PARSER = new AbstractParser<StringTable>() {
            public StringTable parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new StringTable(codedInputStream, extensionRegistryLite);
            }
        };
        private static final StringTable defaultInstance = new StringTable(true);
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public LazyStringList string_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;

        public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<StringTable, Builder> implements StringTableOrBuilder {
            private int bitField0_;
            private LazyStringList string_ = LazyStringArrayList.EMPTY;

            private void maybeForceBuilderInitialization() {
            }

            public final boolean isInitialized() {
                return true;
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

            public StringTable getDefaultInstanceForType() {
                return StringTable.getDefaultInstance();
            }

            public StringTable build() {
                StringTable buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public StringTable buildPartial() {
                StringTable stringTable = new StringTable((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                if ((this.bitField0_ & 1) == 1) {
                    this.string_ = this.string_.getUnmodifiableView();
                    this.bitField0_ &= -2;
                }
                stringTable.string_ = this.string_;
                return stringTable;
            }

            public Builder mergeFrom(StringTable stringTable) {
                if (stringTable == StringTable.getDefaultInstance()) {
                    return this;
                }
                if (!stringTable.string_.isEmpty()) {
                    if (this.string_.isEmpty()) {
                        this.string_ = stringTable.string_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureStringIsMutable();
                        this.string_.addAll(stringTable.string_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(stringTable.unknownFields));
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                StringTable stringTable;
                StringTable stringTable2 = null;
                try {
                    StringTable stringTable3 = (StringTable) StringTable.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (stringTable3 != null) {
                        mergeFrom(stringTable3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    stringTable = (StringTable) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    stringTable2 = stringTable;
                }
                if (stringTable2 != null) {
                    mergeFrom(stringTable2);
                }
                throw th;
            }

            private void ensureStringIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.string_ = new LazyStringArrayList(this.string_);
                    this.bitField0_ |= 1;
                }
            }
        }

        private StringTable(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private StringTable(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static StringTable getDefaultInstance() {
            return defaultInstance;
        }

        public StringTable getDefaultInstanceForType() {
            return defaultInstance;
        }

        private StringTable(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (!z2 || !true) {
                            this.string_ = new LazyStringArrayList();
                            z2 |= true;
                        }
                        this.string_.add(readBytes);
                    } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.string_ = this.string_.getUnmodifiableView();
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
            if (z2 && true) {
                this.string_ = this.string_.getUnmodifiableView();
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

        public Parser<StringTable> getParserForType() {
            return PARSER;
        }

        public ProtocolStringList getStringList() {
            return this.string_;
        }

        public String getString(int i) {
            return (String) this.string_.get(i);
        }

        private void initFields() {
            this.string_ = LazyStringArrayList.EMPTY;
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
            getSerializedSize();
            for (int i = 0; i < this.string_.size(); i++) {
                codedOutputStream.writeBytes(1, this.string_.getByteString(i));
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.string_.size(); i3++) {
                i2 += CodedOutputStream.computeBytesSizeNoTag(this.string_.getByteString(i3));
            }
            int size = 0 + i2 + (getStringList().size() * 1) + this.unknownFields.size();
            this.memoizedSerializedSize = size;
            return size;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(StringTable stringTable) {
            return newBuilder().mergeFrom(stringTable);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface StringTableOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class Type extends ExtendableMessage<Type> implements TypeOrBuilder {
        public static Parser<Type> PARSER = new AbstractParser<Type>() {
            public Type parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Type(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Type defaultInstance = new Type(true);
        /* access modifiers changed from: private */
        public int abbreviatedTypeId_;
        /* access modifiers changed from: private */
        public Type abbreviatedType_;
        /* access modifiers changed from: private */
        public List<Argument> argument_;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int className_;
        /* access modifiers changed from: private */
        public int flags_;
        /* access modifiers changed from: private */
        public int flexibleTypeCapabilitiesId_;
        /* access modifiers changed from: private */
        public int flexibleUpperBoundId_;
        /* access modifiers changed from: private */
        public Type flexibleUpperBound_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public boolean nullable_;
        /* access modifiers changed from: private */
        public int outerTypeId_;
        /* access modifiers changed from: private */
        public Type outerType_;
        /* access modifiers changed from: private */
        public int typeAliasName_;
        /* access modifiers changed from: private */
        public int typeParameterName_;
        /* access modifiers changed from: private */
        public int typeParameter_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;

        public static final class Argument extends GeneratedMessageLite implements ArgumentOrBuilder {
            public static Parser<Argument> PARSER = new AbstractParser<Argument>() {
                public Argument parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Argument(codedInputStream, extensionRegistryLite);
                }
            };
            private static final Argument defaultInstance = new Argument(true);
            /* access modifiers changed from: private */
            public int bitField0_;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            /* access modifiers changed from: private */
            public Projection projection_;
            /* access modifiers changed from: private */
            public int typeId_;
            /* access modifiers changed from: private */
            public Type type_;
            /* access modifiers changed from: private */
            public final ByteString unknownFields;

            public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<Argument, Builder> implements ArgumentOrBuilder {
                private int bitField0_;
                private Projection projection_ = Projection.INV;
                private int typeId_;
                private Type type_ = Type.getDefaultInstance();

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

                public Argument getDefaultInstanceForType() {
                    return Argument.getDefaultInstance();
                }

                public Argument build() {
                    Argument buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public Argument buildPartial() {
                    Argument argument = new Argument((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                    int i = this.bitField0_;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    argument.projection_ = this.projection_;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    argument.type_ = this.type_;
                    if ((i & 4) == 4) {
                        i2 |= 4;
                    }
                    argument.typeId_ = this.typeId_;
                    argument.bitField0_ = i2;
                    return argument;
                }

                public Builder mergeFrom(Argument argument) {
                    if (argument == Argument.getDefaultInstance()) {
                        return this;
                    }
                    if (argument.hasProjection()) {
                        setProjection(argument.getProjection());
                    }
                    if (argument.hasType()) {
                        mergeType(argument.getType());
                    }
                    if (argument.hasTypeId()) {
                        setTypeId(argument.getTypeId());
                    }
                    setUnknownFields(getUnknownFields().concat(argument.unknownFields));
                    return this;
                }

                public final boolean isInitialized() {
                    return !hasType() || getType().isInitialized();
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    Argument argument;
                    Argument argument2 = null;
                    try {
                        Argument argument3 = (Argument) Argument.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (argument3 != null) {
                            mergeFrom(argument3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        argument = (Argument) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        argument2 = argument;
                    }
                    if (argument2 != null) {
                        mergeFrom(argument2);
                    }
                    throw th;
                }

                public Builder setProjection(Projection projection) {
                    if (projection != null) {
                        this.bitField0_ |= 1;
                        this.projection_ = projection;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public boolean hasType() {
                    return (this.bitField0_ & 2) == 2;
                }

                public Type getType() {
                    return this.type_;
                }

                public Builder mergeType(Type type) {
                    if ((this.bitField0_ & 2) != 2 || this.type_ == Type.getDefaultInstance()) {
                        this.type_ = type;
                    } else {
                        this.type_ = Type.newBuilder(this.type_).mergeFrom(type).buildPartial();
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder setTypeId(int i) {
                    this.bitField0_ |= 4;
                    this.typeId_ = i;
                    return this;
                }
            }

            public enum Projection implements EnumLite {
                IN(0, 0),
                OUT(1, 1),
                INV(2, 2),
                STAR(3, 3);
                
                private static EnumLiteMap<Projection> internalValueMap;
                private final int value;

                static {
                    internalValueMap = new EnumLiteMap<Projection>() {
                        public Projection findValueByNumber(int i) {
                            return Projection.valueOf(i);
                        }
                    };
                }

                public final int getNumber() {
                    return this.value;
                }

                public static Projection valueOf(int i) {
                    switch (i) {
                        case 0:
                            return IN;
                        case 1:
                            return OUT;
                        case 2:
                            return INV;
                        case 3:
                            return STAR;
                        default:
                            return null;
                    }
                }

                private Projection(int i, int i2) {
                    this.value = i2;
                }
            }

            private Argument(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
                super(builder);
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = builder.getUnknownFields();
            }

            private Argument(boolean z) {
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = ByteString.EMPTY;
            }

            public static Argument getDefaultInstance() {
                return defaultInstance;
            }

            public Argument getDefaultInstanceForType() {
                return defaultInstance;
            }

            private Argument(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                initFields();
                Output newOutput = ByteString.newOutput();
                CodedOutputStream newInstance = CodedOutputStream.newInstance(newOutput, 1);
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag == 0) {
                            z = true;
                        } else if (readTag == 8) {
                            int readEnum = codedInputStream.readEnum();
                            Projection valueOf = Projection.valueOf(readEnum);
                            if (valueOf == null) {
                                newInstance.writeRawVarint32(readTag);
                                newInstance.writeRawVarint32(readEnum);
                            } else {
                                this.bitField0_ |= 1;
                                this.projection_ = valueOf;
                            }
                        } else if (readTag == 18) {
                            Builder builder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                builder = this.type_.toBuilder();
                            }
                            this.type_ = (Type) codedInputStream.readMessage(Type.PARSER, extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.type_);
                                this.type_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (readTag == 24) {
                            this.bitField0_ |= 4;
                            this.typeId_ = codedInputStream.readInt32();
                        } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                            z = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
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

            public Parser<Argument> getParserForType() {
                return PARSER;
            }

            public boolean hasProjection() {
                return (this.bitField0_ & 1) == 1;
            }

            public Projection getProjection() {
                return this.projection_;
            }

            public boolean hasType() {
                return (this.bitField0_ & 2) == 2;
            }

            public Type getType() {
                return this.type_;
            }

            public boolean hasTypeId() {
                return (this.bitField0_ & 4) == 4;
            }

            public int getTypeId() {
                return this.typeId_;
            }

            private void initFields() {
                this.projection_ = Projection.INV;
                this.type_ = Type.getDefaultInstance();
                this.typeId_ = 0;
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                if (!hasType() || getType().isInitialized()) {
                    this.memoizedIsInitialized = 1;
                    return true;
                }
                this.memoizedIsInitialized = 0;
                return false;
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                getSerializedSize();
                if ((this.bitField0_ & 1) == 1) {
                    codedOutputStream.writeEnum(1, this.projection_.getNumber());
                }
                if ((this.bitField0_ & 2) == 2) {
                    codedOutputStream.writeMessage(2, this.type_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    codedOutputStream.writeInt32(3, this.typeId_);
                }
                codedOutputStream.writeRawBytes(this.unknownFields);
            }

            public int getSerializedSize() {
                int i = this.memoizedSerializedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if ((this.bitField0_ & 1) == 1) {
                    i2 = 0 + CodedOutputStream.computeEnumSize(1, this.projection_.getNumber());
                }
                if ((this.bitField0_ & 2) == 2) {
                    i2 += CodedOutputStream.computeMessageSize(2, this.type_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    i2 += CodedOutputStream.computeInt32Size(3, this.typeId_);
                }
                int size = i2 + this.unknownFields.size();
                this.memoizedSerializedSize = size;
                return size;
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder(Argument argument) {
                return newBuilder().mergeFrom(argument);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }
        }

        public interface ArgumentOrBuilder extends MessageLiteOrBuilder {
        }

        public static final class Builder extends ExtendableBuilder<Type, Builder> implements TypeOrBuilder {
            private int abbreviatedTypeId_;
            private Type abbreviatedType_ = Type.getDefaultInstance();
            private List<Argument> argument_ = Collections.emptyList();
            private int bitField0_;
            private int className_;
            private int flags_;
            private int flexibleTypeCapabilitiesId_;
            private int flexibleUpperBoundId_;
            private Type flexibleUpperBound_ = Type.getDefaultInstance();
            private boolean nullable_;
            private int outerTypeId_;
            private Type outerType_ = Type.getDefaultInstance();
            private int typeAliasName_;
            private int typeParameterName_;
            private int typeParameter_;

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

            public Type getDefaultInstanceForType() {
                return Type.getDefaultInstance();
            }

            public Type build() {
                Type buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Type buildPartial() {
                Type type = new Type((ExtendableBuilder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) == 1) {
                    this.argument_ = Collections.unmodifiableList(this.argument_);
                    this.bitField0_ &= -2;
                }
                type.argument_ = this.argument_;
                if ((i & 2) != 2) {
                    i2 = 0;
                }
                type.nullable_ = this.nullable_;
                if ((i & 4) == 4) {
                    i2 |= 2;
                }
                type.flexibleTypeCapabilitiesId_ = this.flexibleTypeCapabilitiesId_;
                if ((i & 8) == 8) {
                    i2 |= 4;
                }
                type.flexibleUpperBound_ = this.flexibleUpperBound_;
                if ((i & 16) == 16) {
                    i2 |= 8;
                }
                type.flexibleUpperBoundId_ = this.flexibleUpperBoundId_;
                if ((i & 32) == 32) {
                    i2 |= 16;
                }
                type.className_ = this.className_;
                if ((i & 64) == 64) {
                    i2 |= 32;
                }
                type.typeParameter_ = this.typeParameter_;
                if ((i & 128) == 128) {
                    i2 |= 64;
                }
                type.typeParameterName_ = this.typeParameterName_;
                if ((i & 256) == 256) {
                    i2 |= 128;
                }
                type.typeAliasName_ = this.typeAliasName_;
                if ((i & 512) == 512) {
                    i2 |= 256;
                }
                type.outerType_ = this.outerType_;
                if ((i & 1024) == 1024) {
                    i2 |= 512;
                }
                type.outerTypeId_ = this.outerTypeId_;
                if ((i & 2048) == 2048) {
                    i2 |= 1024;
                }
                type.abbreviatedType_ = this.abbreviatedType_;
                if ((i & 4096) == 4096) {
                    i2 |= 2048;
                }
                type.abbreviatedTypeId_ = this.abbreviatedTypeId_;
                if ((i & 8192) == 8192) {
                    i2 |= 4096;
                }
                type.flags_ = this.flags_;
                type.bitField0_ = i2;
                return type;
            }

            public Builder mergeFrom(Type type) {
                if (type == Type.getDefaultInstance()) {
                    return this;
                }
                if (!type.argument_.isEmpty()) {
                    if (this.argument_.isEmpty()) {
                        this.argument_ = type.argument_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureArgumentIsMutable();
                        this.argument_.addAll(type.argument_);
                    }
                }
                if (type.hasNullable()) {
                    setNullable(type.getNullable());
                }
                if (type.hasFlexibleTypeCapabilitiesId()) {
                    setFlexibleTypeCapabilitiesId(type.getFlexibleTypeCapabilitiesId());
                }
                if (type.hasFlexibleUpperBound()) {
                    mergeFlexibleUpperBound(type.getFlexibleUpperBound());
                }
                if (type.hasFlexibleUpperBoundId()) {
                    setFlexibleUpperBoundId(type.getFlexibleUpperBoundId());
                }
                if (type.hasClassName()) {
                    setClassName(type.getClassName());
                }
                if (type.hasTypeParameter()) {
                    setTypeParameter(type.getTypeParameter());
                }
                if (type.hasTypeParameterName()) {
                    setTypeParameterName(type.getTypeParameterName());
                }
                if (type.hasTypeAliasName()) {
                    setTypeAliasName(type.getTypeAliasName());
                }
                if (type.hasOuterType()) {
                    mergeOuterType(type.getOuterType());
                }
                if (type.hasOuterTypeId()) {
                    setOuterTypeId(type.getOuterTypeId());
                }
                if (type.hasAbbreviatedType()) {
                    mergeAbbreviatedType(type.getAbbreviatedType());
                }
                if (type.hasAbbreviatedTypeId()) {
                    setAbbreviatedTypeId(type.getAbbreviatedTypeId());
                }
                if (type.hasFlags()) {
                    setFlags(type.getFlags());
                }
                mergeExtensionFields(type);
                setUnknownFields(getUnknownFields().concat(type.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getArgumentCount(); i++) {
                    if (!getArgument(i).isInitialized()) {
                        return false;
                    }
                }
                if (hasFlexibleUpperBound() && !getFlexibleUpperBound().isInitialized()) {
                    return false;
                }
                if (hasOuterType() && !getOuterType().isInitialized()) {
                    return false;
                }
                if ((!hasAbbreviatedType() || getAbbreviatedType().isInitialized()) && extensionsAreInitialized()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Type type;
                Type type2 = null;
                try {
                    Type type3 = (Type) Type.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (type3 != null) {
                        mergeFrom(type3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    type = (Type) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    type2 = type;
                }
                if (type2 != null) {
                    mergeFrom(type2);
                }
                throw th;
            }

            private void ensureArgumentIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.argument_ = new ArrayList(this.argument_);
                    this.bitField0_ |= 1;
                }
            }

            public int getArgumentCount() {
                return this.argument_.size();
            }

            public Argument getArgument(int i) {
                return (Argument) this.argument_.get(i);
            }

            public Builder setNullable(boolean z) {
                this.bitField0_ |= 2;
                this.nullable_ = z;
                return this;
            }

            public Builder setFlexibleTypeCapabilitiesId(int i) {
                this.bitField0_ |= 4;
                this.flexibleTypeCapabilitiesId_ = i;
                return this;
            }

            public boolean hasFlexibleUpperBound() {
                return (this.bitField0_ & 8) == 8;
            }

            public Type getFlexibleUpperBound() {
                return this.flexibleUpperBound_;
            }

            public Builder mergeFlexibleUpperBound(Type type) {
                if ((this.bitField0_ & 8) != 8 || this.flexibleUpperBound_ == Type.getDefaultInstance()) {
                    this.flexibleUpperBound_ = type;
                } else {
                    this.flexibleUpperBound_ = Type.newBuilder(this.flexibleUpperBound_).mergeFrom(type).buildPartial();
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setFlexibleUpperBoundId(int i) {
                this.bitField0_ |= 16;
                this.flexibleUpperBoundId_ = i;
                return this;
            }

            public Builder setClassName(int i) {
                this.bitField0_ |= 32;
                this.className_ = i;
                return this;
            }

            public Builder setTypeParameter(int i) {
                this.bitField0_ |= 64;
                this.typeParameter_ = i;
                return this;
            }

            public Builder setTypeParameterName(int i) {
                this.bitField0_ |= 128;
                this.typeParameterName_ = i;
                return this;
            }

            public Builder setTypeAliasName(int i) {
                this.bitField0_ |= 256;
                this.typeAliasName_ = i;
                return this;
            }

            public boolean hasOuterType() {
                return (this.bitField0_ & 512) == 512;
            }

            public Type getOuterType() {
                return this.outerType_;
            }

            public Builder mergeOuterType(Type type) {
                if ((this.bitField0_ & 512) != 512 || this.outerType_ == Type.getDefaultInstance()) {
                    this.outerType_ = type;
                } else {
                    this.outerType_ = Type.newBuilder(this.outerType_).mergeFrom(type).buildPartial();
                }
                this.bitField0_ |= 512;
                return this;
            }

            public Builder setOuterTypeId(int i) {
                this.bitField0_ |= 1024;
                this.outerTypeId_ = i;
                return this;
            }

            public boolean hasAbbreviatedType() {
                return (this.bitField0_ & 2048) == 2048;
            }

            public Type getAbbreviatedType() {
                return this.abbreviatedType_;
            }

            public Builder mergeAbbreviatedType(Type type) {
                if ((this.bitField0_ & 2048) != 2048 || this.abbreviatedType_ == Type.getDefaultInstance()) {
                    this.abbreviatedType_ = type;
                } else {
                    this.abbreviatedType_ = Type.newBuilder(this.abbreviatedType_).mergeFrom(type).buildPartial();
                }
                this.bitField0_ |= 2048;
                return this;
            }

            public Builder setAbbreviatedTypeId(int i) {
                this.bitField0_ |= 4096;
                this.abbreviatedTypeId_ = i;
                return this;
            }

            public Builder setFlags(int i) {
                this.bitField0_ |= 8192;
                this.flags_ = i;
                return this;
            }
        }

        private Type(ExtendableBuilder<Type, ?> extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private Type(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Type getDefaultInstance() {
            return defaultInstance;
        }

        public Type getDefaultInstanceForType() {
            return defaultInstance;
        }

        private Type(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    Builder builder = null;
                    switch (readTag) {
                        case 0:
                            z = true;
                            break;
                        case 8:
                            this.bitField0_ |= 4096;
                            this.flags_ = codedInputStream.readInt32();
                            break;
                        case 18:
                            if (!z2 || !true) {
                                this.argument_ = new ArrayList();
                                z2 |= true;
                            }
                            this.argument_.add(codedInputStream.readMessage(Argument.PARSER, extensionRegistryLite));
                            break;
                        case 24:
                            this.bitField0_ |= 1;
                            this.nullable_ = codedInputStream.readBool();
                            break;
                        case 32:
                            this.bitField0_ |= 2;
                            this.flexibleTypeCapabilitiesId_ = codedInputStream.readInt32();
                            break;
                        case 42:
                            if ((this.bitField0_ & 4) == 4) {
                                builder = this.flexibleUpperBound_.toBuilder();
                            }
                            this.flexibleUpperBound_ = (Type) codedInputStream.readMessage(PARSER, extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.flexibleUpperBound_);
                                this.flexibleUpperBound_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                            break;
                        case 48:
                            this.bitField0_ |= 16;
                            this.className_ = codedInputStream.readInt32();
                            break;
                        case 56:
                            this.bitField0_ |= 32;
                            this.typeParameter_ = codedInputStream.readInt32();
                            break;
                        case 64:
                            this.bitField0_ |= 8;
                            this.flexibleUpperBoundId_ = codedInputStream.readInt32();
                            break;
                        case 72:
                            this.bitField0_ |= 64;
                            this.typeParameterName_ = codedInputStream.readInt32();
                            break;
                        case 82:
                            if ((this.bitField0_ & 256) == 256) {
                                builder = this.outerType_.toBuilder();
                            }
                            this.outerType_ = (Type) codedInputStream.readMessage(PARSER, extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.outerType_);
                                this.outerType_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 256;
                            break;
                        case 88:
                            this.bitField0_ |= 512;
                            this.outerTypeId_ = codedInputStream.readInt32();
                            break;
                        case 96:
                            this.bitField0_ |= 128;
                            this.typeAliasName_ = codedInputStream.readInt32();
                            break;
                        case 106:
                            if ((this.bitField0_ & 1024) == 1024) {
                                builder = this.abbreviatedType_.toBuilder();
                            }
                            this.abbreviatedType_ = (Type) codedInputStream.readMessage(PARSER, extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.abbreviatedType_);
                                this.abbreviatedType_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 1024;
                            break;
                        case 112:
                            this.bitField0_ |= 2048;
                            this.abbreviatedTypeId_ = codedInputStream.readInt32();
                            break;
                        default:
                            if (parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                                break;
                            } else {
                                z = true;
                                break;
                            }
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.argument_ = Collections.unmodifiableList(this.argument_);
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
            if (z2 && true) {
                this.argument_ = Collections.unmodifiableList(this.argument_);
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

        public Parser<Type> getParserForType() {
            return PARSER;
        }

        public List<Argument> getArgumentList() {
            return this.argument_;
        }

        public int getArgumentCount() {
            return this.argument_.size();
        }

        public Argument getArgument(int i) {
            return (Argument) this.argument_.get(i);
        }

        public boolean hasNullable() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean getNullable() {
            return this.nullable_;
        }

        public boolean hasFlexibleTypeCapabilitiesId() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getFlexibleTypeCapabilitiesId() {
            return this.flexibleTypeCapabilitiesId_;
        }

        public boolean hasFlexibleUpperBound() {
            return (this.bitField0_ & 4) == 4;
        }

        public Type getFlexibleUpperBound() {
            return this.flexibleUpperBound_;
        }

        public boolean hasFlexibleUpperBoundId() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getFlexibleUpperBoundId() {
            return this.flexibleUpperBoundId_;
        }

        public boolean hasClassName() {
            return (this.bitField0_ & 16) == 16;
        }

        public int getClassName() {
            return this.className_;
        }

        public boolean hasTypeParameter() {
            return (this.bitField0_ & 32) == 32;
        }

        public int getTypeParameter() {
            return this.typeParameter_;
        }

        public boolean hasTypeParameterName() {
            return (this.bitField0_ & 64) == 64;
        }

        public int getTypeParameterName() {
            return this.typeParameterName_;
        }

        public boolean hasTypeAliasName() {
            return (this.bitField0_ & 128) == 128;
        }

        public int getTypeAliasName() {
            return this.typeAliasName_;
        }

        public boolean hasOuterType() {
            return (this.bitField0_ & 256) == 256;
        }

        public Type getOuterType() {
            return this.outerType_;
        }

        public boolean hasOuterTypeId() {
            return (this.bitField0_ & 512) == 512;
        }

        public int getOuterTypeId() {
            return this.outerTypeId_;
        }

        public boolean hasAbbreviatedType() {
            return (this.bitField0_ & 1024) == 1024;
        }

        public Type getAbbreviatedType() {
            return this.abbreviatedType_;
        }

        public boolean hasAbbreviatedTypeId() {
            return (this.bitField0_ & 2048) == 2048;
        }

        public int getAbbreviatedTypeId() {
            return this.abbreviatedTypeId_;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 4096) == 4096;
        }

        public int getFlags() {
            return this.flags_;
        }

        private void initFields() {
            this.argument_ = Collections.emptyList();
            this.nullable_ = false;
            this.flexibleTypeCapabilitiesId_ = 0;
            this.flexibleUpperBound_ = getDefaultInstance();
            this.flexibleUpperBoundId_ = 0;
            this.className_ = 0;
            this.typeParameter_ = 0;
            this.typeParameterName_ = 0;
            this.typeAliasName_ = 0;
            this.outerType_ = getDefaultInstance();
            this.outerTypeId_ = 0;
            this.abbreviatedType_ = getDefaultInstance();
            this.abbreviatedTypeId_ = 0;
            this.flags_ = 0;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getArgumentCount(); i++) {
                if (!getArgument(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if (hasFlexibleUpperBound() && !getFlexibleUpperBound().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (hasOuterType() && !getOuterType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (hasAbbreviatedType() && !getAbbreviatedType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else {
                this.memoizedIsInitialized = 1;
                return true;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 4096) == 4096) {
                codedOutputStream.writeInt32(1, this.flags_);
            }
            for (int i = 0; i < this.argument_.size(); i++) {
                codedOutputStream.writeMessage(2, (MessageLite) this.argument_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBool(3, this.nullable_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(4, this.flexibleTypeCapabilitiesId_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeMessage(5, this.flexibleUpperBound_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeInt32(6, this.className_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeInt32(7, this.typeParameter_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeInt32(8, this.flexibleUpperBoundId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeInt32(9, this.typeParameterName_);
            }
            if ((this.bitField0_ & 256) == 256) {
                codedOutputStream.writeMessage(10, this.outerType_);
            }
            if ((this.bitField0_ & 512) == 512) {
                codedOutputStream.writeInt32(11, this.outerTypeId_);
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeInt32(12, this.typeAliasName_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                codedOutputStream.writeMessage(13, this.abbreviatedType_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                codedOutputStream.writeInt32(14, this.abbreviatedTypeId_);
            }
            newExtensionWriter.writeUntil(200, codedOutputStream);
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeInt32Size = (this.bitField0_ & 4096) == 4096 ? CodedOutputStream.computeInt32Size(1, this.flags_) + 0 : 0;
            for (int i2 = 0; i2 < this.argument_.size(); i2++) {
                computeInt32Size += CodedOutputStream.computeMessageSize(2, (MessageLite) this.argument_.get(i2));
            }
            if ((this.bitField0_ & 1) == 1) {
                computeInt32Size += CodedOutputStream.computeBoolSize(3, this.nullable_);
            }
            if ((this.bitField0_ & 2) == 2) {
                computeInt32Size += CodedOutputStream.computeInt32Size(4, this.flexibleTypeCapabilitiesId_);
            }
            if ((this.bitField0_ & 4) == 4) {
                computeInt32Size += CodedOutputStream.computeMessageSize(5, this.flexibleUpperBound_);
            }
            if ((this.bitField0_ & 16) == 16) {
                computeInt32Size += CodedOutputStream.computeInt32Size(6, this.className_);
            }
            if ((this.bitField0_ & 32) == 32) {
                computeInt32Size += CodedOutputStream.computeInt32Size(7, this.typeParameter_);
            }
            if ((this.bitField0_ & 8) == 8) {
                computeInt32Size += CodedOutputStream.computeInt32Size(8, this.flexibleUpperBoundId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                computeInt32Size += CodedOutputStream.computeInt32Size(9, this.typeParameterName_);
            }
            if ((this.bitField0_ & 256) == 256) {
                computeInt32Size += CodedOutputStream.computeMessageSize(10, this.outerType_);
            }
            if ((this.bitField0_ & 512) == 512) {
                computeInt32Size += CodedOutputStream.computeInt32Size(11, this.outerTypeId_);
            }
            if ((this.bitField0_ & 128) == 128) {
                computeInt32Size += CodedOutputStream.computeInt32Size(12, this.typeAliasName_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                computeInt32Size += CodedOutputStream.computeMessageSize(13, this.abbreviatedType_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                computeInt32Size += CodedOutputStream.computeInt32Size(14, this.abbreviatedTypeId_);
            }
            int extensionsSerializedSize = computeInt32Size + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Type type) {
            return newBuilder().mergeFrom(type);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public static final class TypeAlias extends ExtendableMessage<TypeAlias> implements TypeAliasOrBuilder {
        public static Parser<TypeAlias> PARSER = new AbstractParser<TypeAlias>() {
            public TypeAlias parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TypeAlias(codedInputStream, extensionRegistryLite);
            }
        };
        private static final TypeAlias defaultInstance = new TypeAlias(true);
        /* access modifiers changed from: private */
        public List<Annotation> annotation_;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int expandedTypeId_;
        /* access modifiers changed from: private */
        public Type expandedType_;
        /* access modifiers changed from: private */
        public int flags_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int name_;
        /* access modifiers changed from: private */
        public List<TypeParameter> typeParameter_;
        /* access modifiers changed from: private */
        public int underlyingTypeId_;
        /* access modifiers changed from: private */
        public Type underlyingType_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;
        /* access modifiers changed from: private */
        public List<Integer> versionRequirement_;

        public static final class Builder extends ExtendableBuilder<TypeAlias, Builder> implements TypeAliasOrBuilder {
            private List<Annotation> annotation_ = Collections.emptyList();
            private int bitField0_;
            private int expandedTypeId_;
            private Type expandedType_ = Type.getDefaultInstance();
            private int flags_ = 6;
            private int name_;
            private List<TypeParameter> typeParameter_ = Collections.emptyList();
            private int underlyingTypeId_;
            private Type underlyingType_ = Type.getDefaultInstance();
            private List<Integer> versionRequirement_ = Collections.emptyList();

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

            public TypeAlias getDefaultInstanceForType() {
                return TypeAlias.getDefaultInstance();
            }

            public TypeAlias build() {
                TypeAlias buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public TypeAlias buildPartial() {
                TypeAlias typeAlias = new TypeAlias((ExtendableBuilder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                typeAlias.flags_ = this.flags_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                typeAlias.name_ = this.name_;
                if ((this.bitField0_ & 4) == 4) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    this.bitField0_ &= -5;
                }
                typeAlias.typeParameter_ = this.typeParameter_;
                if ((i & 8) == 8) {
                    i2 |= 4;
                }
                typeAlias.underlyingType_ = this.underlyingType_;
                if ((i & 16) == 16) {
                    i2 |= 8;
                }
                typeAlias.underlyingTypeId_ = this.underlyingTypeId_;
                if ((i & 32) == 32) {
                    i2 |= 16;
                }
                typeAlias.expandedType_ = this.expandedType_;
                if ((i & 64) == 64) {
                    i2 |= 32;
                }
                typeAlias.expandedTypeId_ = this.expandedTypeId_;
                if ((this.bitField0_ & 128) == 128) {
                    this.annotation_ = Collections.unmodifiableList(this.annotation_);
                    this.bitField0_ &= -129;
                }
                typeAlias.annotation_ = this.annotation_;
                if ((this.bitField0_ & 256) == 256) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= -257;
                }
                typeAlias.versionRequirement_ = this.versionRequirement_;
                typeAlias.bitField0_ = i2;
                return typeAlias;
            }

            public Builder mergeFrom(TypeAlias typeAlias) {
                if (typeAlias == TypeAlias.getDefaultInstance()) {
                    return this;
                }
                if (typeAlias.hasFlags()) {
                    setFlags(typeAlias.getFlags());
                }
                if (typeAlias.hasName()) {
                    setName(typeAlias.getName());
                }
                if (!typeAlias.typeParameter_.isEmpty()) {
                    if (this.typeParameter_.isEmpty()) {
                        this.typeParameter_ = typeAlias.typeParameter_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureTypeParameterIsMutable();
                        this.typeParameter_.addAll(typeAlias.typeParameter_);
                    }
                }
                if (typeAlias.hasUnderlyingType()) {
                    mergeUnderlyingType(typeAlias.getUnderlyingType());
                }
                if (typeAlias.hasUnderlyingTypeId()) {
                    setUnderlyingTypeId(typeAlias.getUnderlyingTypeId());
                }
                if (typeAlias.hasExpandedType()) {
                    mergeExpandedType(typeAlias.getExpandedType());
                }
                if (typeAlias.hasExpandedTypeId()) {
                    setExpandedTypeId(typeAlias.getExpandedTypeId());
                }
                if (!typeAlias.annotation_.isEmpty()) {
                    if (this.annotation_.isEmpty()) {
                        this.annotation_ = typeAlias.annotation_;
                        this.bitField0_ &= -129;
                    } else {
                        ensureAnnotationIsMutable();
                        this.annotation_.addAll(typeAlias.annotation_);
                    }
                }
                if (!typeAlias.versionRequirement_.isEmpty()) {
                    if (this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = typeAlias.versionRequirement_;
                        this.bitField0_ &= -257;
                    } else {
                        ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(typeAlias.versionRequirement_);
                    }
                }
                mergeExtensionFields(typeAlias);
                setUnknownFields(getUnknownFields().concat(typeAlias.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                if (!hasName()) {
                    return false;
                }
                for (int i = 0; i < getTypeParameterCount(); i++) {
                    if (!getTypeParameter(i).isInitialized()) {
                        return false;
                    }
                }
                if (hasUnderlyingType() && !getUnderlyingType().isInitialized()) {
                    return false;
                }
                if (hasExpandedType() && !getExpandedType().isInitialized()) {
                    return false;
                }
                for (int i2 = 0; i2 < getAnnotationCount(); i2++) {
                    if (!getAnnotation(i2).isInitialized()) {
                        return false;
                    }
                }
                if (!extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                TypeAlias typeAlias;
                TypeAlias typeAlias2 = null;
                try {
                    TypeAlias typeAlias3 = (TypeAlias) TypeAlias.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (typeAlias3 != null) {
                        mergeFrom(typeAlias3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    typeAlias = (TypeAlias) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    typeAlias2 = typeAlias;
                }
                if (typeAlias2 != null) {
                    mergeFrom(typeAlias2);
                }
                throw th;
            }

            public Builder setFlags(int i) {
                this.bitField0_ |= 1;
                this.flags_ = i;
                return this;
            }

            public boolean hasName() {
                return (this.bitField0_ & 2) == 2;
            }

            public Builder setName(int i) {
                this.bitField0_ |= 2;
                this.name_ = i;
                return this;
            }

            private void ensureTypeParameterIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.typeParameter_ = new ArrayList(this.typeParameter_);
                    this.bitField0_ |= 4;
                }
            }

            public int getTypeParameterCount() {
                return this.typeParameter_.size();
            }

            public TypeParameter getTypeParameter(int i) {
                return (TypeParameter) this.typeParameter_.get(i);
            }

            public boolean hasUnderlyingType() {
                return (this.bitField0_ & 8) == 8;
            }

            public Type getUnderlyingType() {
                return this.underlyingType_;
            }

            public Builder mergeUnderlyingType(Type type) {
                if ((this.bitField0_ & 8) != 8 || this.underlyingType_ == Type.getDefaultInstance()) {
                    this.underlyingType_ = type;
                } else {
                    this.underlyingType_ = Type.newBuilder(this.underlyingType_).mergeFrom(type).buildPartial();
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setUnderlyingTypeId(int i) {
                this.bitField0_ |= 16;
                this.underlyingTypeId_ = i;
                return this;
            }

            public boolean hasExpandedType() {
                return (this.bitField0_ & 32) == 32;
            }

            public Type getExpandedType() {
                return this.expandedType_;
            }

            public Builder mergeExpandedType(Type type) {
                if ((this.bitField0_ & 32) != 32 || this.expandedType_ == Type.getDefaultInstance()) {
                    this.expandedType_ = type;
                } else {
                    this.expandedType_ = Type.newBuilder(this.expandedType_).mergeFrom(type).buildPartial();
                }
                this.bitField0_ |= 32;
                return this;
            }

            public Builder setExpandedTypeId(int i) {
                this.bitField0_ |= 64;
                this.expandedTypeId_ = i;
                return this;
            }

            private void ensureAnnotationIsMutable() {
                if ((this.bitField0_ & 128) != 128) {
                    this.annotation_ = new ArrayList(this.annotation_);
                    this.bitField0_ |= 128;
                }
            }

            public int getAnnotationCount() {
                return this.annotation_.size();
            }

            public Annotation getAnnotation(int i) {
                return (Annotation) this.annotation_.get(i);
            }

            private void ensureVersionRequirementIsMutable() {
                if ((this.bitField0_ & 256) != 256) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= 256;
                }
            }
        }

        private TypeAlias(ExtendableBuilder<TypeAlias, ?> extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private TypeAlias(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static TypeAlias getDefaultInstance() {
            return defaultInstance;
        }

        public TypeAlias getDefaultInstanceForType() {
            return defaultInstance;
        }

        private TypeAlias(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    Builder builder = null;
                    switch (readTag) {
                        case 0:
                            z = true;
                            break;
                        case 8:
                            this.bitField0_ |= 1;
                            this.flags_ = codedInputStream.readInt32();
                            break;
                        case 16:
                            this.bitField0_ |= 2;
                            this.name_ = codedInputStream.readInt32();
                            break;
                        case 26:
                            if (!(z2 & true)) {
                                this.typeParameter_ = new ArrayList();
                                z2 |= true;
                            }
                            this.typeParameter_.add(codedInputStream.readMessage(TypeParameter.PARSER, extensionRegistryLite));
                            break;
                        case 34:
                            if ((this.bitField0_ & 4) == 4) {
                                builder = this.underlyingType_.toBuilder();
                            }
                            this.underlyingType_ = (Type) codedInputStream.readMessage(Type.PARSER, extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.underlyingType_);
                                this.underlyingType_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                            break;
                        case 40:
                            this.bitField0_ |= 8;
                            this.underlyingTypeId_ = codedInputStream.readInt32();
                            break;
                        case 50:
                            if ((this.bitField0_ & 16) == 16) {
                                builder = this.expandedType_.toBuilder();
                            }
                            this.expandedType_ = (Type) codedInputStream.readMessage(Type.PARSER, extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.expandedType_);
                                this.expandedType_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 16;
                            break;
                        case 56:
                            this.bitField0_ |= 32;
                            this.expandedTypeId_ = codedInputStream.readInt32();
                            break;
                        case 66:
                            if (!(z2 & true)) {
                                this.annotation_ = new ArrayList();
                                z2 |= true;
                            }
                            this.annotation_.add(codedInputStream.readMessage(Annotation.PARSER, extensionRegistryLite));
                            break;
                        case 248:
                            if (!(z2 & true)) {
                                this.versionRequirement_ = new ArrayList();
                                z2 |= true;
                            }
                            this.versionRequirement_.add(Integer.valueOf(codedInputStream.readInt32()));
                            break;
                        case Callback.DEFAULT_SWIPE_ANIMATION_DURATION /*250*/:
                            int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                                this.versionRequirement_ = new ArrayList();
                                z2 |= true;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.versionRequirement_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream.popLimit(pushLimit);
                            break;
                        default:
                            if (parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                                break;
                            } else {
                                z = true;
                                break;
                            }
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    }
                    if (z2 & true) {
                        this.annotation_ = Collections.unmodifiableList(this.annotation_);
                    }
                    if (z2 & true) {
                        this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
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
                this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
            }
            if (z2 & true) {
                this.annotation_ = Collections.unmodifiableList(this.annotation_);
            }
            if (z2 & true) {
                this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
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

        public Parser<TypeAlias> getParserForType() {
            return PARSER;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFlags() {
            return this.flags_;
        }

        public boolean hasName() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getName() {
            return this.name_;
        }

        public List<TypeParameter> getTypeParameterList() {
            return this.typeParameter_;
        }

        public int getTypeParameterCount() {
            return this.typeParameter_.size();
        }

        public TypeParameter getTypeParameter(int i) {
            return (TypeParameter) this.typeParameter_.get(i);
        }

        public boolean hasUnderlyingType() {
            return (this.bitField0_ & 4) == 4;
        }

        public Type getUnderlyingType() {
            return this.underlyingType_;
        }

        public boolean hasUnderlyingTypeId() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getUnderlyingTypeId() {
            return this.underlyingTypeId_;
        }

        public boolean hasExpandedType() {
            return (this.bitField0_ & 16) == 16;
        }

        public Type getExpandedType() {
            return this.expandedType_;
        }

        public boolean hasExpandedTypeId() {
            return (this.bitField0_ & 32) == 32;
        }

        public int getExpandedTypeId() {
            return this.expandedTypeId_;
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

        public List<Integer> getVersionRequirementList() {
            return this.versionRequirement_;
        }

        private void initFields() {
            this.flags_ = 6;
            this.name_ = 0;
            this.typeParameter_ = Collections.emptyList();
            this.underlyingType_ = Type.getDefaultInstance();
            this.underlyingTypeId_ = 0;
            this.expandedType_ = Type.getDefaultInstance();
            this.expandedTypeId_ = 0;
            this.annotation_ = Collections.emptyList();
            this.versionRequirement_ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (int i = 0; i < getTypeParameterCount(); i++) {
                if (!getTypeParameter(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if (hasUnderlyingType() && !getUnderlyingType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasExpandedType() || getExpandedType().isInitialized()) {
                for (int i2 = 0; i2 < getAnnotationCount(); i2++) {
                    if (!getAnnotation(i2).isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    }
                }
                if (!extensionsAreInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            } else {
                this.memoizedIsInitialized = 0;
                return false;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.flags_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.name_);
            }
            for (int i = 0; i < this.typeParameter_.size(); i++) {
                codedOutputStream.writeMessage(3, (MessageLite) this.typeParameter_.get(i));
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeMessage(4, this.underlyingType_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeInt32(5, this.underlyingTypeId_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeMessage(6, this.expandedType_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeInt32(7, this.expandedTypeId_);
            }
            for (int i2 = 0; i2 < this.annotation_.size(); i2++) {
                codedOutputStream.writeMessage(8, (MessageLite) this.annotation_.get(i2));
            }
            for (int i3 = 0; i3 < this.versionRequirement_.size(); i3++) {
                codedOutputStream.writeInt32(31, ((Integer) this.versionRequirement_.get(i3)).intValue());
            }
            newExtensionWriter.writeUntil(200, codedOutputStream);
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.flags_) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                computeInt32Size += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            int i2 = computeInt32Size;
            for (int i3 = 0; i3 < this.typeParameter_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.typeParameter_.get(i3));
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeMessageSize(4, this.underlyingType_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeInt32Size(5, this.underlyingTypeId_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += CodedOutputStream.computeMessageSize(6, this.expandedType_);
            }
            if ((this.bitField0_ & 32) == 32) {
                i2 += CodedOutputStream.computeInt32Size(7, this.expandedTypeId_);
            }
            for (int i4 = 0; i4 < this.annotation_.size(); i4++) {
                i2 += CodedOutputStream.computeMessageSize(8, (MessageLite) this.annotation_.get(i4));
            }
            int i5 = 0;
            for (int i6 = 0; i6 < this.versionRequirement_.size(); i6++) {
                i5 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.versionRequirement_.get(i6)).intValue());
            }
            int size = i2 + i5 + (getVersionRequirementList().size() * 2) + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = size;
            return size;
        }

        public static TypeAlias parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TypeAlias) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(TypeAlias typeAlias) {
            return newBuilder().mergeFrom(typeAlias);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface TypeAliasOrBuilder extends ExtendableMessageOrBuilder {
    }

    public interface TypeOrBuilder extends ExtendableMessageOrBuilder {
    }

    public static final class TypeParameter extends ExtendableMessage<TypeParameter> implements TypeParameterOrBuilder {
        public static Parser<TypeParameter> PARSER = new AbstractParser<TypeParameter>() {
            public TypeParameter parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TypeParameter(codedInputStream, extensionRegistryLite);
            }
        };
        private static final TypeParameter defaultInstance = new TypeParameter(true);
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int id_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int name_;
        /* access modifiers changed from: private */
        public boolean reified_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;
        private int upperBoundIdMemoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<Integer> upperBoundId_;
        /* access modifiers changed from: private */
        public List<Type> upperBound_;
        /* access modifiers changed from: private */
        public Variance variance_;

        public static final class Builder extends ExtendableBuilder<TypeParameter, Builder> implements TypeParameterOrBuilder {
            private int bitField0_;
            private int id_;
            private int name_;
            private boolean reified_;
            private List<Integer> upperBoundId_ = Collections.emptyList();
            private List<Type> upperBound_ = Collections.emptyList();
            private Variance variance_ = Variance.INV;

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

            public TypeParameter getDefaultInstanceForType() {
                return TypeParameter.getDefaultInstance();
            }

            public TypeParameter build() {
                TypeParameter buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public TypeParameter buildPartial() {
                TypeParameter typeParameter = new TypeParameter((ExtendableBuilder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                typeParameter.id_ = this.id_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                typeParameter.name_ = this.name_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                typeParameter.reified_ = this.reified_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                typeParameter.variance_ = this.variance_;
                if ((this.bitField0_ & 16) == 16) {
                    this.upperBound_ = Collections.unmodifiableList(this.upperBound_);
                    this.bitField0_ &= -17;
                }
                typeParameter.upperBound_ = this.upperBound_;
                if ((this.bitField0_ & 32) == 32) {
                    this.upperBoundId_ = Collections.unmodifiableList(this.upperBoundId_);
                    this.bitField0_ &= -33;
                }
                typeParameter.upperBoundId_ = this.upperBoundId_;
                typeParameter.bitField0_ = i2;
                return typeParameter;
            }

            public Builder mergeFrom(TypeParameter typeParameter) {
                if (typeParameter == TypeParameter.getDefaultInstance()) {
                    return this;
                }
                if (typeParameter.hasId()) {
                    setId(typeParameter.getId());
                }
                if (typeParameter.hasName()) {
                    setName(typeParameter.getName());
                }
                if (typeParameter.hasReified()) {
                    setReified(typeParameter.getReified());
                }
                if (typeParameter.hasVariance()) {
                    setVariance(typeParameter.getVariance());
                }
                if (!typeParameter.upperBound_.isEmpty()) {
                    if (this.upperBound_.isEmpty()) {
                        this.upperBound_ = typeParameter.upperBound_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureUpperBoundIsMutable();
                        this.upperBound_.addAll(typeParameter.upperBound_);
                    }
                }
                if (!typeParameter.upperBoundId_.isEmpty()) {
                    if (this.upperBoundId_.isEmpty()) {
                        this.upperBoundId_ = typeParameter.upperBoundId_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureUpperBoundIdIsMutable();
                        this.upperBoundId_.addAll(typeParameter.upperBoundId_);
                    }
                }
                mergeExtensionFields(typeParameter);
                setUnknownFields(getUnknownFields().concat(typeParameter.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                if (!hasId() || !hasName()) {
                    return false;
                }
                for (int i = 0; i < getUpperBoundCount(); i++) {
                    if (!getUpperBound(i).isInitialized()) {
                        return false;
                    }
                }
                if (!extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                TypeParameter typeParameter;
                TypeParameter typeParameter2 = null;
                try {
                    TypeParameter typeParameter3 = (TypeParameter) TypeParameter.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (typeParameter3 != null) {
                        mergeFrom(typeParameter3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    typeParameter = (TypeParameter) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    typeParameter2 = typeParameter;
                }
                if (typeParameter2 != null) {
                    mergeFrom(typeParameter2);
                }
                throw th;
            }

            public boolean hasId() {
                return (this.bitField0_ & 1) == 1;
            }

            public Builder setId(int i) {
                this.bitField0_ |= 1;
                this.id_ = i;
                return this;
            }

            public boolean hasName() {
                return (this.bitField0_ & 2) == 2;
            }

            public Builder setName(int i) {
                this.bitField0_ |= 2;
                this.name_ = i;
                return this;
            }

            public Builder setReified(boolean z) {
                this.bitField0_ |= 4;
                this.reified_ = z;
                return this;
            }

            public Builder setVariance(Variance variance) {
                if (variance != null) {
                    this.bitField0_ |= 8;
                    this.variance_ = variance;
                    return this;
                }
                throw new NullPointerException();
            }

            private void ensureUpperBoundIsMutable() {
                if ((this.bitField0_ & 16) != 16) {
                    this.upperBound_ = new ArrayList(this.upperBound_);
                    this.bitField0_ |= 16;
                }
            }

            public int getUpperBoundCount() {
                return this.upperBound_.size();
            }

            public Type getUpperBound(int i) {
                return (Type) this.upperBound_.get(i);
            }

            private void ensureUpperBoundIdIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.upperBoundId_ = new ArrayList(this.upperBoundId_);
                    this.bitField0_ |= 32;
                }
            }
        }

        public enum Variance implements EnumLite {
            IN(0, 0),
            OUT(1, 1),
            INV(2, 2);
            
            private static EnumLiteMap<Variance> internalValueMap;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<Variance>() {
                    public Variance findValueByNumber(int i) {
                        return Variance.valueOf(i);
                    }
                };
            }

            public final int getNumber() {
                return this.value;
            }

            public static Variance valueOf(int i) {
                switch (i) {
                    case 0:
                        return IN;
                    case 1:
                        return OUT;
                    case 2:
                        return INV;
                    default:
                        return null;
                }
            }

            private Variance(int i, int i2) {
                this.value = i2;
            }
        }

        private TypeParameter(ExtendableBuilder<TypeParameter, ?> extendableBuilder) {
            super(extendableBuilder);
            this.upperBoundIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private TypeParameter(boolean z) {
            this.upperBoundIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static TypeParameter getDefaultInstance() {
            return defaultInstance;
        }

        public TypeParameter getDefaultInstanceForType() {
            return defaultInstance;
        }

        private TypeParameter(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.upperBoundIdMemoizedSerializedSize = -1;
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
                    } else if (readTag == 8) {
                        this.bitField0_ |= 1;
                        this.id_ = codedInputStream.readInt32();
                    } else if (readTag == 16) {
                        this.bitField0_ |= 2;
                        this.name_ = codedInputStream.readInt32();
                    } else if (readTag == 24) {
                        this.bitField0_ |= 4;
                        this.reified_ = codedInputStream.readBool();
                    } else if (readTag == 32) {
                        int readEnum = codedInputStream.readEnum();
                        Variance valueOf = Variance.valueOf(readEnum);
                        if (valueOf == null) {
                            newInstance.writeRawVarint32(readTag);
                            newInstance.writeRawVarint32(readEnum);
                        } else {
                            this.bitField0_ |= 8;
                            this.variance_ = valueOf;
                        }
                    } else if (readTag == 42) {
                        if (!(z2 & true)) {
                            this.upperBound_ = new ArrayList();
                            z2 |= true;
                        }
                        this.upperBound_.add(codedInputStream.readMessage(Type.PARSER, extensionRegistryLite));
                    } else if (readTag == 48) {
                        if (!(z2 & true)) {
                            this.upperBoundId_ = new ArrayList();
                            z2 |= true;
                        }
                        this.upperBoundId_.add(Integer.valueOf(codedInputStream.readInt32()));
                    } else if (readTag == 50) {
                        int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                        if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                            this.upperBoundId_ = new ArrayList();
                            z2 |= true;
                        }
                        while (codedInputStream.getBytesUntilLimit() > 0) {
                            this.upperBoundId_.add(Integer.valueOf(codedInputStream.readInt32()));
                        }
                        codedInputStream.popLimit(pushLimit);
                    } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.upperBound_ = Collections.unmodifiableList(this.upperBound_);
                    }
                    if (z2 & true) {
                        this.upperBoundId_ = Collections.unmodifiableList(this.upperBoundId_);
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
                this.upperBound_ = Collections.unmodifiableList(this.upperBound_);
            }
            if (z2 & true) {
                this.upperBoundId_ = Collections.unmodifiableList(this.upperBoundId_);
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

        public Parser<TypeParameter> getParserForType() {
            return PARSER;
        }

        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getId() {
            return this.id_;
        }

        public boolean hasName() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getName() {
            return this.name_;
        }

        public boolean hasReified() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean getReified() {
            return this.reified_;
        }

        public boolean hasVariance() {
            return (this.bitField0_ & 8) == 8;
        }

        public Variance getVariance() {
            return this.variance_;
        }

        public List<Type> getUpperBoundList() {
            return this.upperBound_;
        }

        public int getUpperBoundCount() {
            return this.upperBound_.size();
        }

        public Type getUpperBound(int i) {
            return (Type) this.upperBound_.get(i);
        }

        public List<Integer> getUpperBoundIdList() {
            return this.upperBoundId_;
        }

        private void initFields() {
            this.id_ = 0;
            this.name_ = 0;
            this.reified_ = false;
            this.variance_ = Variance.INV;
            this.upperBound_ = Collections.emptyList();
            this.upperBoundId_ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasId()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else {
                for (int i = 0; i < getUpperBoundCount(); i++) {
                    if (!getUpperBound(i).isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    }
                }
                if (!extensionsAreInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.name_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBool(3, this.reified_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeEnum(4, this.variance_.getNumber());
            }
            for (int i = 0; i < this.upperBound_.size(); i++) {
                codedOutputStream.writeMessage(5, (MessageLite) this.upperBound_.get(i));
            }
            if (getUpperBoundIdList().size() > 0) {
                codedOutputStream.writeRawVarint32(50);
                codedOutputStream.writeRawVarint32(this.upperBoundIdMemoizedSerializedSize);
            }
            for (int i2 = 0; i2 < this.upperBoundId_.size(); i2++) {
                codedOutputStream.writeInt32NoTag(((Integer) this.upperBoundId_.get(i2)).intValue());
            }
            newExtensionWriter.writeUntil(1000, codedOutputStream);
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.id_) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                computeInt32Size += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            if ((this.bitField0_ & 4) == 4) {
                computeInt32Size += CodedOutputStream.computeBoolSize(3, this.reified_);
            }
            if ((this.bitField0_ & 8) == 8) {
                computeInt32Size += CodedOutputStream.computeEnumSize(4, this.variance_.getNumber());
            }
            int i2 = computeInt32Size;
            for (int i3 = 0; i3 < this.upperBound_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(5, (MessageLite) this.upperBound_.get(i3));
            }
            int i4 = 0;
            for (int i5 = 0; i5 < this.upperBoundId_.size(); i5++) {
                i4 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.upperBoundId_.get(i5)).intValue());
            }
            int i6 = i2 + i4;
            if (!getUpperBoundIdList().isEmpty()) {
                i6 = i6 + 1 + CodedOutputStream.computeInt32SizeNoTag(i4);
            }
            this.upperBoundIdMemoizedSerializedSize = i4;
            int extensionsSerializedSize = i6 + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(TypeParameter typeParameter) {
            return newBuilder().mergeFrom(typeParameter);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface TypeParameterOrBuilder extends ExtendableMessageOrBuilder {
    }

    public static final class TypeTable extends GeneratedMessageLite implements TypeTableOrBuilder {
        public static Parser<TypeTable> PARSER = new AbstractParser<TypeTable>() {
            public TypeTable parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TypeTable(codedInputStream, extensionRegistryLite);
            }
        };
        private static final TypeTable defaultInstance = new TypeTable(true);
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int firstNullable_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<Type> type_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;

        public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<TypeTable, Builder> implements TypeTableOrBuilder {
            private int bitField0_;
            private int firstNullable_ = -1;
            private List<Type> type_ = Collections.emptyList();

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

            public TypeTable getDefaultInstanceForType() {
                return TypeTable.getDefaultInstance();
            }

            public TypeTable build() {
                TypeTable buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public TypeTable buildPartial() {
                TypeTable typeTable = new TypeTable((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) == 1) {
                    this.type_ = Collections.unmodifiableList(this.type_);
                    this.bitField0_ &= -2;
                }
                typeTable.type_ = this.type_;
                if ((i & 2) != 2) {
                    i2 = 0;
                }
                typeTable.firstNullable_ = this.firstNullable_;
                typeTable.bitField0_ = i2;
                return typeTable;
            }

            public Builder mergeFrom(TypeTable typeTable) {
                if (typeTable == TypeTable.getDefaultInstance()) {
                    return this;
                }
                if (!typeTable.type_.isEmpty()) {
                    if (this.type_.isEmpty()) {
                        this.type_ = typeTable.type_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureTypeIsMutable();
                        this.type_.addAll(typeTable.type_);
                    }
                }
                if (typeTable.hasFirstNullable()) {
                    setFirstNullable(typeTable.getFirstNullable());
                }
                setUnknownFields(getUnknownFields().concat(typeTable.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getTypeCount(); i++) {
                    if (!getType(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                TypeTable typeTable;
                TypeTable typeTable2 = null;
                try {
                    TypeTable typeTable3 = (TypeTable) TypeTable.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (typeTable3 != null) {
                        mergeFrom(typeTable3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    typeTable = (TypeTable) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    typeTable2 = typeTable;
                }
                if (typeTable2 != null) {
                    mergeFrom(typeTable2);
                }
                throw th;
            }

            private void ensureTypeIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.type_ = new ArrayList(this.type_);
                    this.bitField0_ |= 1;
                }
            }

            public int getTypeCount() {
                return this.type_.size();
            }

            public Type getType(int i) {
                return (Type) this.type_.get(i);
            }

            public Builder setFirstNullable(int i) {
                this.bitField0_ |= 2;
                this.firstNullable_ = i;
                return this;
            }
        }

        private TypeTable(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private TypeTable(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static TypeTable getDefaultInstance() {
            return defaultInstance;
        }

        public TypeTable getDefaultInstanceForType() {
            return defaultInstance;
        }

        private TypeTable(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (!z2 || !true) {
                            this.type_ = new ArrayList();
                            z2 |= true;
                        }
                        this.type_.add(codedInputStream.readMessage(Type.PARSER, extensionRegistryLite));
                    } else if (readTag == 16) {
                        this.bitField0_ |= 1;
                        this.firstNullable_ = codedInputStream.readInt32();
                    } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.type_ = Collections.unmodifiableList(this.type_);
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
            if (z2 && true) {
                this.type_ = Collections.unmodifiableList(this.type_);
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

        public Parser<TypeTable> getParserForType() {
            return PARSER;
        }

        public List<Type> getTypeList() {
            return this.type_;
        }

        public int getTypeCount() {
            return this.type_.size();
        }

        public Type getType(int i) {
            return (Type) this.type_.get(i);
        }

        public boolean hasFirstNullable() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFirstNullable() {
            return this.firstNullable_;
        }

        private void initFields() {
            this.type_ = Collections.emptyList();
            this.firstNullable_ = -1;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getTypeCount(); i++) {
                if (!getType(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.type_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.type_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(2, this.firstNullable_);
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.type_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.type_.get(i3));
            }
            if ((this.bitField0_ & 1) == 1) {
                i2 += CodedOutputStream.computeInt32Size(2, this.firstNullable_);
            }
            int size = i2 + this.unknownFields.size();
            this.memoizedSerializedSize = size;
            return size;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(TypeTable typeTable) {
            return newBuilder().mergeFrom(typeTable);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface TypeTableOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class ValueParameter extends ExtendableMessage<ValueParameter> implements ValueParameterOrBuilder {
        public static Parser<ValueParameter> PARSER = new AbstractParser<ValueParameter>() {
            public ValueParameter parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ValueParameter(codedInputStream, extensionRegistryLite);
            }
        };
        private static final ValueParameter defaultInstance = new ValueParameter(true);
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int flags_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int name_;
        /* access modifiers changed from: private */
        public int typeId_;
        /* access modifiers changed from: private */
        public Type type_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;
        /* access modifiers changed from: private */
        public int varargElementTypeId_;
        /* access modifiers changed from: private */
        public Type varargElementType_;

        public static final class Builder extends ExtendableBuilder<ValueParameter, Builder> implements ValueParameterOrBuilder {
            private int bitField0_;
            private int flags_;
            private int name_;
            private int typeId_;
            private Type type_ = Type.getDefaultInstance();
            private int varargElementTypeId_;
            private Type varargElementType_ = Type.getDefaultInstance();

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

            public ValueParameter getDefaultInstanceForType() {
                return ValueParameter.getDefaultInstance();
            }

            public ValueParameter build() {
                ValueParameter buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public ValueParameter buildPartial() {
                ValueParameter valueParameter = new ValueParameter((ExtendableBuilder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                valueParameter.flags_ = this.flags_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                valueParameter.name_ = this.name_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                valueParameter.type_ = this.type_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                valueParameter.typeId_ = this.typeId_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                valueParameter.varargElementType_ = this.varargElementType_;
                if ((i & 32) == 32) {
                    i2 |= 32;
                }
                valueParameter.varargElementTypeId_ = this.varargElementTypeId_;
                valueParameter.bitField0_ = i2;
                return valueParameter;
            }

            public Builder mergeFrom(ValueParameter valueParameter) {
                if (valueParameter == ValueParameter.getDefaultInstance()) {
                    return this;
                }
                if (valueParameter.hasFlags()) {
                    setFlags(valueParameter.getFlags());
                }
                if (valueParameter.hasName()) {
                    setName(valueParameter.getName());
                }
                if (valueParameter.hasType()) {
                    mergeType(valueParameter.getType());
                }
                if (valueParameter.hasTypeId()) {
                    setTypeId(valueParameter.getTypeId());
                }
                if (valueParameter.hasVarargElementType()) {
                    mergeVarargElementType(valueParameter.getVarargElementType());
                }
                if (valueParameter.hasVarargElementTypeId()) {
                    setVarargElementTypeId(valueParameter.getVarargElementTypeId());
                }
                mergeExtensionFields(valueParameter);
                setUnknownFields(getUnknownFields().concat(valueParameter.unknownFields));
                return this;
            }

            public final boolean isInitialized() {
                if (!hasName()) {
                    return false;
                }
                if (hasType() && !getType().isInitialized()) {
                    return false;
                }
                if ((!hasVarargElementType() || getVarargElementType().isInitialized()) && extensionsAreInitialized()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ValueParameter valueParameter;
                ValueParameter valueParameter2 = null;
                try {
                    ValueParameter valueParameter3 = (ValueParameter) ValueParameter.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (valueParameter3 != null) {
                        mergeFrom(valueParameter3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    valueParameter = (ValueParameter) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    valueParameter2 = valueParameter;
                }
                if (valueParameter2 != null) {
                    mergeFrom(valueParameter2);
                }
                throw th;
            }

            public Builder setFlags(int i) {
                this.bitField0_ |= 1;
                this.flags_ = i;
                return this;
            }

            public boolean hasName() {
                return (this.bitField0_ & 2) == 2;
            }

            public Builder setName(int i) {
                this.bitField0_ |= 2;
                this.name_ = i;
                return this;
            }

            public boolean hasType() {
                return (this.bitField0_ & 4) == 4;
            }

            public Type getType() {
                return this.type_;
            }

            public Builder mergeType(Type type) {
                if ((this.bitField0_ & 4) != 4 || this.type_ == Type.getDefaultInstance()) {
                    this.type_ = type;
                } else {
                    this.type_ = Type.newBuilder(this.type_).mergeFrom(type).buildPartial();
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setTypeId(int i) {
                this.bitField0_ |= 8;
                this.typeId_ = i;
                return this;
            }

            public boolean hasVarargElementType() {
                return (this.bitField0_ & 16) == 16;
            }

            public Type getVarargElementType() {
                return this.varargElementType_;
            }

            public Builder mergeVarargElementType(Type type) {
                if ((this.bitField0_ & 16) != 16 || this.varargElementType_ == Type.getDefaultInstance()) {
                    this.varargElementType_ = type;
                } else {
                    this.varargElementType_ = Type.newBuilder(this.varargElementType_).mergeFrom(type).buildPartial();
                }
                this.bitField0_ |= 16;
                return this;
            }

            public Builder setVarargElementTypeId(int i) {
                this.bitField0_ |= 32;
                this.varargElementTypeId_ = i;
                return this;
            }
        }

        private ValueParameter(ExtendableBuilder<ValueParameter, ?> extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private ValueParameter(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static ValueParameter getDefaultInstance() {
            return defaultInstance;
        }

        public ValueParameter getDefaultInstanceForType() {
            return defaultInstance;
        }

        private ValueParameter(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            Output newOutput = ByteString.newOutput();
            CodedOutputStream newInstance = CodedOutputStream.newInstance(newOutput, 1);
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 8) {
                        this.bitField0_ |= 1;
                        this.flags_ = codedInputStream.readInt32();
                    } else if (readTag != 16) {
                        Builder builder = null;
                        if (readTag == 26) {
                            if ((this.bitField0_ & 4) == 4) {
                                builder = this.type_.toBuilder();
                            }
                            this.type_ = (Type) codedInputStream.readMessage(Type.PARSER, extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.type_);
                                this.type_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (readTag == 34) {
                            if ((this.bitField0_ & 16) == 16) {
                                builder = this.varargElementType_.toBuilder();
                            }
                            this.varargElementType_ = (Type) codedInputStream.readMessage(Type.PARSER, extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.varargElementType_);
                                this.varargElementType_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 16;
                        } else if (readTag == 40) {
                            this.bitField0_ |= 8;
                            this.typeId_ = codedInputStream.readInt32();
                        } else if (readTag == 48) {
                            this.bitField0_ |= 32;
                            this.varargElementTypeId_ = codedInputStream.readInt32();
                        } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                            z = true;
                        }
                    } else {
                        this.bitField0_ |= 2;
                        this.name_ = codedInputStream.readInt32();
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
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

        public Parser<ValueParameter> getParserForType() {
            return PARSER;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFlags() {
            return this.flags_;
        }

        public boolean hasName() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getName() {
            return this.name_;
        }

        public boolean hasType() {
            return (this.bitField0_ & 4) == 4;
        }

        public Type getType() {
            return this.type_;
        }

        public boolean hasTypeId() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getTypeId() {
            return this.typeId_;
        }

        public boolean hasVarargElementType() {
            return (this.bitField0_ & 16) == 16;
        }

        public Type getVarargElementType() {
            return this.varargElementType_;
        }

        public boolean hasVarargElementTypeId() {
            return (this.bitField0_ & 32) == 32;
        }

        public int getVarargElementTypeId() {
            return this.varargElementTypeId_;
        }

        private void initFields() {
            this.flags_ = 0;
            this.name_ = 0;
            this.type_ = Type.getDefaultInstance();
            this.typeId_ = 0;
            this.varargElementType_ = Type.getDefaultInstance();
            this.varargElementTypeId_ = 0;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (hasType() && !getType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (hasVarargElementType() && !getVarargElementType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else {
                this.memoizedIsInitialized = 1;
                return true;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.flags_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.name_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeMessage(3, this.type_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeMessage(4, this.varargElementType_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeInt32(5, this.typeId_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeInt32(6, this.varargElementTypeId_);
            }
            newExtensionWriter.writeUntil(200, codedOutputStream);
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, this.flags_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeMessageSize(3, this.type_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += CodedOutputStream.computeMessageSize(4, this.varargElementType_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeInt32Size(5, this.typeId_);
            }
            if ((this.bitField0_ & 32) == 32) {
                i2 += CodedOutputStream.computeInt32Size(6, this.varargElementTypeId_);
            }
            int extensionsSerializedSize = i2 + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ValueParameter valueParameter) {
            return newBuilder().mergeFrom(valueParameter);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface ValueParameterOrBuilder extends ExtendableMessageOrBuilder {
    }

    public static final class VersionRequirement extends GeneratedMessageLite implements VersionRequirementOrBuilder {
        public static Parser<VersionRequirement> PARSER = new AbstractParser<VersionRequirement>() {
            public VersionRequirement parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new VersionRequirement(codedInputStream, extensionRegistryLite);
            }
        };
        private static final VersionRequirement defaultInstance = new VersionRequirement(true);
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int errorCode_;
        /* access modifiers changed from: private */
        public Level level_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int message_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;
        /* access modifiers changed from: private */
        public int versionFull_;
        /* access modifiers changed from: private */
        public VersionKind versionKind_;
        /* access modifiers changed from: private */
        public int version_;

        public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<VersionRequirement, Builder> implements VersionRequirementOrBuilder {
            private int bitField0_;
            private int errorCode_;
            private Level level_ = Level.ERROR;
            private int message_;
            private int versionFull_;
            private VersionKind versionKind_ = VersionKind.LANGUAGE_VERSION;
            private int version_;

            private void maybeForceBuilderInitialization() {
            }

            public final boolean isInitialized() {
                return true;
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

            public VersionRequirement getDefaultInstanceForType() {
                return VersionRequirement.getDefaultInstance();
            }

            public VersionRequirement build() {
                VersionRequirement buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public VersionRequirement buildPartial() {
                VersionRequirement versionRequirement = new VersionRequirement((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                versionRequirement.version_ = this.version_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                versionRequirement.versionFull_ = this.versionFull_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                versionRequirement.level_ = this.level_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                versionRequirement.errorCode_ = this.errorCode_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                versionRequirement.message_ = this.message_;
                if ((i & 32) == 32) {
                    i2 |= 32;
                }
                versionRequirement.versionKind_ = this.versionKind_;
                versionRequirement.bitField0_ = i2;
                return versionRequirement;
            }

            public Builder mergeFrom(VersionRequirement versionRequirement) {
                if (versionRequirement == VersionRequirement.getDefaultInstance()) {
                    return this;
                }
                if (versionRequirement.hasVersion()) {
                    setVersion(versionRequirement.getVersion());
                }
                if (versionRequirement.hasVersionFull()) {
                    setVersionFull(versionRequirement.getVersionFull());
                }
                if (versionRequirement.hasLevel()) {
                    setLevel(versionRequirement.getLevel());
                }
                if (versionRequirement.hasErrorCode()) {
                    setErrorCode(versionRequirement.getErrorCode());
                }
                if (versionRequirement.hasMessage()) {
                    setMessage(versionRequirement.getMessage());
                }
                if (versionRequirement.hasVersionKind()) {
                    setVersionKind(versionRequirement.getVersionKind());
                }
                setUnknownFields(getUnknownFields().concat(versionRequirement.unknownFields));
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                VersionRequirement versionRequirement;
                VersionRequirement versionRequirement2 = null;
                try {
                    VersionRequirement versionRequirement3 = (VersionRequirement) VersionRequirement.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (versionRequirement3 != null) {
                        mergeFrom(versionRequirement3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    versionRequirement = (VersionRequirement) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    versionRequirement2 = versionRequirement;
                }
                if (versionRequirement2 != null) {
                    mergeFrom(versionRequirement2);
                }
                throw th;
            }

            public Builder setVersion(int i) {
                this.bitField0_ |= 1;
                this.version_ = i;
                return this;
            }

            public Builder setVersionFull(int i) {
                this.bitField0_ |= 2;
                this.versionFull_ = i;
                return this;
            }

            public Builder setLevel(Level level) {
                if (level != null) {
                    this.bitField0_ |= 4;
                    this.level_ = level;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setErrorCode(int i) {
                this.bitField0_ |= 8;
                this.errorCode_ = i;
                return this;
            }

            public Builder setMessage(int i) {
                this.bitField0_ |= 16;
                this.message_ = i;
                return this;
            }

            public Builder setVersionKind(VersionKind versionKind) {
                if (versionKind != null) {
                    this.bitField0_ |= 32;
                    this.versionKind_ = versionKind;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        public enum Level implements EnumLite {
            WARNING(0, 0),
            ERROR(1, 1),
            HIDDEN(2, 2);
            
            private static EnumLiteMap<Level> internalValueMap;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<Level>() {
                    public Level findValueByNumber(int i) {
                        return Level.valueOf(i);
                    }
                };
            }

            public final int getNumber() {
                return this.value;
            }

            public static Level valueOf(int i) {
                switch (i) {
                    case 0:
                        return WARNING;
                    case 1:
                        return ERROR;
                    case 2:
                        return HIDDEN;
                    default:
                        return null;
                }
            }

            private Level(int i, int i2) {
                this.value = i2;
            }
        }

        public enum VersionKind implements EnumLite {
            LANGUAGE_VERSION(0, 0),
            COMPILER_VERSION(1, 1),
            API_VERSION(2, 2);
            
            private static EnumLiteMap<VersionKind> internalValueMap;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<VersionKind>() {
                    public VersionKind findValueByNumber(int i) {
                        return VersionKind.valueOf(i);
                    }
                };
            }

            public final int getNumber() {
                return this.value;
            }

            public static VersionKind valueOf(int i) {
                switch (i) {
                    case 0:
                        return LANGUAGE_VERSION;
                    case 1:
                        return COMPILER_VERSION;
                    case 2:
                        return API_VERSION;
                    default:
                        return null;
                }
            }

            private VersionKind(int i, int i2) {
                this.value = i2;
            }
        }

        private VersionRequirement(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private VersionRequirement(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static VersionRequirement getDefaultInstance() {
            return defaultInstance;
        }

        public VersionRequirement getDefaultInstanceForType() {
            return defaultInstance;
        }

        private VersionRequirement(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            Output newOutput = ByteString.newOutput();
            CodedOutputStream newInstance = CodedOutputStream.newInstance(newOutput, 1);
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 8) {
                        this.bitField0_ |= 1;
                        this.version_ = codedInputStream.readInt32();
                    } else if (readTag == 16) {
                        this.bitField0_ |= 2;
                        this.versionFull_ = codedInputStream.readInt32();
                    } else if (readTag == 24) {
                        int readEnum = codedInputStream.readEnum();
                        Level valueOf = Level.valueOf(readEnum);
                        if (valueOf == null) {
                            newInstance.writeRawVarint32(readTag);
                            newInstance.writeRawVarint32(readEnum);
                        } else {
                            this.bitField0_ |= 4;
                            this.level_ = valueOf;
                        }
                    } else if (readTag == 32) {
                        this.bitField0_ |= 8;
                        this.errorCode_ = codedInputStream.readInt32();
                    } else if (readTag == 40) {
                        this.bitField0_ |= 16;
                        this.message_ = codedInputStream.readInt32();
                    } else if (readTag == 48) {
                        int readEnum2 = codedInputStream.readEnum();
                        VersionKind valueOf2 = VersionKind.valueOf(readEnum2);
                        if (valueOf2 == null) {
                            newInstance.writeRawVarint32(readTag);
                            newInstance.writeRawVarint32(readEnum2);
                        } else {
                            this.bitField0_ |= 32;
                            this.versionKind_ = valueOf2;
                        }
                    } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
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

        public Parser<VersionRequirement> getParserForType() {
            return PARSER;
        }

        public boolean hasVersion() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getVersion() {
            return this.version_;
        }

        public boolean hasVersionFull() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getVersionFull() {
            return this.versionFull_;
        }

        public boolean hasLevel() {
            return (this.bitField0_ & 4) == 4;
        }

        public Level getLevel() {
            return this.level_;
        }

        public boolean hasErrorCode() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getErrorCode() {
            return this.errorCode_;
        }

        public boolean hasMessage() {
            return (this.bitField0_ & 16) == 16;
        }

        public int getMessage() {
            return this.message_;
        }

        public boolean hasVersionKind() {
            return (this.bitField0_ & 32) == 32;
        }

        public VersionKind getVersionKind() {
            return this.versionKind_;
        }

        private void initFields() {
            this.version_ = 0;
            this.versionFull_ = 0;
            this.level_ = Level.ERROR;
            this.errorCode_ = 0;
            this.message_ = 0;
            this.versionKind_ = VersionKind.LANGUAGE_VERSION;
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
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.version_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.versionFull_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeEnum(3, this.level_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeInt32(4, this.errorCode_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeInt32(5, this.message_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeEnum(6, this.versionKind_.getNumber());
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, this.version_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeInt32Size(2, this.versionFull_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeEnumSize(3, this.level_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeInt32Size(4, this.errorCode_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += CodedOutputStream.computeInt32Size(5, this.message_);
            }
            if ((this.bitField0_ & 32) == 32) {
                i2 += CodedOutputStream.computeEnumSize(6, this.versionKind_.getNumber());
            }
            int size = i2 + this.unknownFields.size();
            this.memoizedSerializedSize = size;
            return size;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(VersionRequirement versionRequirement) {
            return newBuilder().mergeFrom(versionRequirement);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface VersionRequirementOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class VersionRequirementTable extends GeneratedMessageLite implements VersionRequirementTableOrBuilder {
        public static Parser<VersionRequirementTable> PARSER = new AbstractParser<VersionRequirementTable>() {
            public VersionRequirementTable parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new VersionRequirementTable(codedInputStream, extensionRegistryLite);
            }
        };
        private static final VersionRequirementTable defaultInstance = new VersionRequirementTable(true);
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<VersionRequirement> requirement_;
        /* access modifiers changed from: private */
        public final ByteString unknownFields;

        public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder<VersionRequirementTable, Builder> implements VersionRequirementTableOrBuilder {
            private int bitField0_;
            private List<VersionRequirement> requirement_ = Collections.emptyList();

            private void maybeForceBuilderInitialization() {
            }

            public final boolean isInitialized() {
                return true;
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

            public VersionRequirementTable getDefaultInstanceForType() {
                return VersionRequirementTable.getDefaultInstance();
            }

            public VersionRequirementTable build() {
                VersionRequirementTable buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public VersionRequirementTable buildPartial() {
                VersionRequirementTable versionRequirementTable = new VersionRequirementTable((kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder) this);
                if ((this.bitField0_ & 1) == 1) {
                    this.requirement_ = Collections.unmodifiableList(this.requirement_);
                    this.bitField0_ &= -2;
                }
                versionRequirementTable.requirement_ = this.requirement_;
                return versionRequirementTable;
            }

            public Builder mergeFrom(VersionRequirementTable versionRequirementTable) {
                if (versionRequirementTable == VersionRequirementTable.getDefaultInstance()) {
                    return this;
                }
                if (!versionRequirementTable.requirement_.isEmpty()) {
                    if (this.requirement_.isEmpty()) {
                        this.requirement_ = versionRequirementTable.requirement_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureRequirementIsMutable();
                        this.requirement_.addAll(versionRequirementTable.requirement_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(versionRequirementTable.unknownFields));
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                VersionRequirementTable versionRequirementTable;
                VersionRequirementTable versionRequirementTable2 = null;
                try {
                    VersionRequirementTable versionRequirementTable3 = (VersionRequirementTable) VersionRequirementTable.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (versionRequirementTable3 != null) {
                        mergeFrom(versionRequirementTable3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    versionRequirementTable = (VersionRequirementTable) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    versionRequirementTable2 = versionRequirementTable;
                }
                if (versionRequirementTable2 != null) {
                    mergeFrom(versionRequirementTable2);
                }
                throw th;
            }

            private void ensureRequirementIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.requirement_ = new ArrayList(this.requirement_);
                    this.bitField0_ |= 1;
                }
            }
        }

        private VersionRequirementTable(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private VersionRequirementTable(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static VersionRequirementTable getDefaultInstance() {
            return defaultInstance;
        }

        public VersionRequirementTable getDefaultInstanceForType() {
            return defaultInstance;
        }

        private VersionRequirementTable(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (!z2 || !true) {
                            this.requirement_ = new ArrayList();
                            z2 |= true;
                        }
                        this.requirement_.add(codedInputStream.readMessage(VersionRequirement.PARSER, extensionRegistryLite));
                    } else if (!parseUnknownField(codedInputStream, newInstance, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.requirement_ = Collections.unmodifiableList(this.requirement_);
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
            if (z2 && true) {
                this.requirement_ = Collections.unmodifiableList(this.requirement_);
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

        public Parser<VersionRequirementTable> getParserForType() {
            return PARSER;
        }

        public List<VersionRequirement> getRequirementList() {
            return this.requirement_;
        }

        public int getRequirementCount() {
            return this.requirement_.size();
        }

        private void initFields() {
            this.requirement_ = Collections.emptyList();
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
            getSerializedSize();
            for (int i = 0; i < this.requirement_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.requirement_.get(i));
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.requirement_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.requirement_.get(i3));
            }
            int size = i2 + this.unknownFields.size();
            this.memoizedSerializedSize = size;
            return size;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(VersionRequirementTable versionRequirementTable) {
            return newBuilder().mergeFrom(versionRequirementTable);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }
    }

    public interface VersionRequirementTableOrBuilder extends MessageLiteOrBuilder {
    }

    public enum Visibility implements EnumLite {
        INTERNAL(0, 0),
        PRIVATE(1, 1),
        PROTECTED(2, 2),
        PUBLIC(3, 3),
        PRIVATE_TO_THIS(4, 4),
        LOCAL(5, 5);
        
        private static EnumLiteMap<Visibility> internalValueMap;
        private final int value;

        static {
            internalValueMap = new EnumLiteMap<Visibility>() {
                public Visibility findValueByNumber(int i) {
                    return Visibility.valueOf(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        public static Visibility valueOf(int i) {
            switch (i) {
                case 0:
                    return INTERNAL;
                case 1:
                    return PRIVATE;
                case 2:
                    return PROTECTED;
                case 3:
                    return PUBLIC;
                case 4:
                    return PRIVATE_TO_THIS;
                case 5:
                    return LOCAL;
                default:
                    return null;
            }
        }

        private Visibility(int i, int i2) {
            this.value = i2;
        }
    }
}
