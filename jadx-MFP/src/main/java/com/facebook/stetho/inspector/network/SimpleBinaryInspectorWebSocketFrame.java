package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorWebSocketFrame;
import java.io.UnsupportedEncodingException;

public class SimpleBinaryInspectorWebSocketFrame implements InspectorWebSocketFrame {
    private final byte[] mPayload;
    private final String mRequestId;

    public boolean mask() {
        return false;
    }

    public int opcode() {
        return 2;
    }

    public SimpleBinaryInspectorWebSocketFrame(String str, byte[] bArr) {
        this.mRequestId = str;
        this.mPayload = bArr;
    }

    public String requestId() {
        return this.mRequestId;
    }

    public String payloadData() {
        try {
            return new String(this.mPayload, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
