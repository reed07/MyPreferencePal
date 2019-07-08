package io.grpc.okhttp;

import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.internal.TransportFrameUtil;
import io.grpc.okhttp.internal.framed.Header;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;

class Utils {
    private static final Logger log = Logger.getLogger(Utils.class.getName());

    public static Metadata convertHeaders(List<Header> list) {
        return InternalMetadata.newMetadata(convertHeadersToArray(list));
    }

    public static Metadata convertTrailers(List<Header> list) {
        return InternalMetadata.newMetadata(convertHeadersToArray(list));
    }

    @CheckReturnValue
    private static byte[][] convertHeadersToArray(List<Header> list) {
        byte[][] bArr = new byte[(list.size() * 2)][];
        int i = 0;
        for (Header header : list) {
            int i2 = i + 1;
            bArr[i] = header.name.toByteArray();
            i = i2 + 1;
            bArr[i2] = header.value.toByteArray();
        }
        return TransportFrameUtil.toRawSerializedHeaders(bArr);
    }

    private Utils() {
    }
}
