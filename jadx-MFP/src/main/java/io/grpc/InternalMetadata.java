package io.grpc;

import com.google.android.exoplayer2.C;
import com.google.common.io.BaseEncoding;
import io.grpc.Metadata.Key;
import java.nio.charset.Charset;

@Internal
public final class InternalMetadata {
    @Internal
    public static final BaseEncoding BASE64_ENCODING_OMIT_PADDING = Metadata.BASE64_ENCODING_OMIT_PADDING;
    @Internal
    public static final Charset US_ASCII = Charset.forName(C.ASCII_NAME);

    @Internal
    public interface TrustedAsciiMarshaller<T> extends TrustedAsciiMarshaller<T> {
    }

    @Internal
    public static <T> Key<T> keyOf(String str, TrustedAsciiMarshaller<T> trustedAsciiMarshaller) {
        boolean z = false;
        if (str != null && !str.isEmpty() && str.charAt(0) == ':') {
            z = true;
        }
        return Key.of(str, z, (TrustedAsciiMarshaller<T>) trustedAsciiMarshaller);
    }

    @Internal
    public static Metadata newMetadata(byte[]... bArr) {
        return new Metadata(bArr);
    }

    @Internal
    public static byte[][] serialize(Metadata metadata) {
        return metadata.serialize();
    }

    @Internal
    public static int headerCount(Metadata metadata) {
        return metadata.headerCount();
    }
}
