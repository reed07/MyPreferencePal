package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.console.CLog;
import com.facebook.stetho.inspector.protocol.module.Console.MessageLevel;
import com.facebook.stetho.inspector.protocol.module.Console.MessageSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.InflaterOutputStream;
import javax.annotation.Nullable;

public class DecompressionHelper {
    static final String DEFLATE_ENCODING = "deflate";
    static final String GZIP_ENCODING = "gzip";

    public static InputStream teeInputWithDecompression(NetworkPeerManager networkPeerManager, String str, InputStream inputStream, OutputStream outputStream, @Nullable String str2, ResponseHandler responseHandler) throws IOException {
        CountingOutputStream countingOutputStream;
        OutputStream outputStream2;
        if (str2 != null) {
            boolean equals = GZIP_ENCODING.equals(str2);
            boolean equals2 = DEFLATE_ENCODING.equals(str2);
            if (equals || equals2) {
                CountingOutputStream countingOutputStream2 = new CountingOutputStream(outputStream);
                if (equals) {
                    outputStream2 = GunzippingOutputStream.create(countingOutputStream2);
                    countingOutputStream = countingOutputStream2;
                } else if (equals2) {
                    outputStream2 = new InflaterOutputStream(countingOutputStream2);
                    countingOutputStream = countingOutputStream2;
                } else {
                    outputStream2 = outputStream;
                    countingOutputStream = countingOutputStream2;
                }
                ResponseHandlingInputStream responseHandlingInputStream = new ResponseHandlingInputStream(inputStream, str, outputStream2, countingOutputStream, networkPeerManager, responseHandler);
                return responseHandlingInputStream;
            }
            MessageLevel messageLevel = MessageLevel.WARNING;
            MessageSource messageSource = MessageSource.NETWORK;
            StringBuilder sb = new StringBuilder();
            sb.append("Unsupported Content-Encoding in response for request #");
            sb.append(str);
            sb.append(": ");
            sb.append(str2);
            CLog.writeToConsole(networkPeerManager, messageLevel, messageSource, sb.toString());
        }
        outputStream2 = outputStream;
        countingOutputStream = null;
        ResponseHandlingInputStream responseHandlingInputStream2 = new ResponseHandlingInputStream(inputStream, str, outputStream2, countingOutputStream, networkPeerManager, responseHandler);
        return responseHandlingInputStream2;
    }
}
