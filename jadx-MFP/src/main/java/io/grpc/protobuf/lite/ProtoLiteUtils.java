package io.grpc.protobuf.lite;

import com.google.common.base.Preconditions;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import io.grpc.ExperimentalApi;
import io.grpc.KnownLength;
import io.grpc.Metadata.BinaryMarshaller;
import io.grpc.MethodDescriptor.Marshaller;
import io.grpc.MethodDescriptor.PrototypeMarshaller;
import io.grpc.Status;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

@ExperimentalApi
public class ProtoLiteUtils {
    /* access modifiers changed from: private */
    public static final ThreadLocal<Reference<byte[]>> bufs = new ThreadLocal<Reference<byte[]>>() {
        /* access modifiers changed from: protected */
        public Reference<byte[]> initialValue() {
            return new WeakReference(new byte[4096]);
        }
    };
    /* access modifiers changed from: private */
    public static volatile ExtensionRegistryLite globalRegistry = ExtensionRegistryLite.getEmptyRegistry();

    public static <T extends MessageLite> Marshaller<T> marshaller(final T t) {
        final Parser parserForType = t.getParserForType();
        return new PrototypeMarshaller<T>() {
            public InputStream stream(T t) {
                return new ProtoInputStream(t, parserForType);
            }

            public T parse(InputStream inputStream) {
                if ((inputStream instanceof ProtoInputStream) && ((ProtoInputStream) inputStream).parser() == parserForType) {
                    try {
                        return ((ProtoInputStream) inputStream).message();
                    } catch (IllegalStateException unused) {
                    }
                }
                CodedInputStream codedInputStream = null;
                try {
                    if (inputStream instanceof KnownLength) {
                        int available = inputStream.available();
                        if (available > 0 && available <= 4194304) {
                            byte[] bArr = (byte[]) ((Reference) ProtoLiteUtils.bufs.get()).get();
                            if (bArr == null || bArr.length < available) {
                                bArr = new byte[available];
                                ProtoLiteUtils.bufs.set(new WeakReference(bArr));
                            }
                            int i = available;
                            while (true) {
                                if (i <= 0) {
                                    break;
                                }
                                int read = inputStream.read(bArr, available - i, i);
                                if (read == -1) {
                                    break;
                                }
                                i -= read;
                            }
                            if (i == 0) {
                                codedInputStream = CodedInputStream.newInstance(bArr, 0, available);
                            } else {
                                int i2 = available - i;
                                StringBuilder sb = new StringBuilder();
                                sb.append("size inaccurate: ");
                                sb.append(available);
                                sb.append(" != ");
                                sb.append(i2);
                                throw new RuntimeException(sb.toString());
                            }
                        } else if (available == 0) {
                            return MessageLite.this;
                        }
                    }
                    if (codedInputStream == null) {
                        codedInputStream = CodedInputStream.newInstance(inputStream);
                    }
                    codedInputStream.setSizeLimit(Integer.MAX_VALUE);
                    try {
                        return parseFrom(codedInputStream);
                    } catch (InvalidProtocolBufferException e) {
                        throw Status.INTERNAL.withDescription("Invalid protobuf byte sequence").withCause(e).asRuntimeException();
                    }
                } catch (IOException e2) {
                    throw new RuntimeException(e2);
                }
            }

            private T parseFrom(CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
                T t = (MessageLite) parserForType.parseFrom(codedInputStream, ProtoLiteUtils.globalRegistry);
                try {
                    codedInputStream.checkLastTagWas(0);
                    return t;
                } catch (InvalidProtocolBufferException e) {
                    e.setUnfinishedMessage(t);
                    throw e;
                }
            }
        };
    }

    public static <T extends MessageLite> BinaryMarshaller<T> metadataMarshaller(final T t) {
        return new BinaryMarshaller<T>() {
            public byte[] toBytes(T t) {
                return t.toByteArray();
            }

            public T parseBytes(byte[] bArr) {
                try {
                    return (MessageLite) MessageLite.this.getParserForType().parseFrom(bArr, ProtoLiteUtils.globalRegistry);
                } catch (InvalidProtocolBufferException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        };
    }

    static long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(outputStream);
        byte[] bArr = new byte[8192];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    private ProtoLiteUtils() {
    }
}
