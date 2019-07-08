package com.uacf.gear.bridge;

import com.samsung.android.sdk.accessory.SASocket;

public class UacfPeerSocket extends SASocket {
    private UacfGearBridgeBase uacfGearBridgeBase;

    public void onError(int i, String str, int i2) {
    }

    public UacfPeerSocket() {
        super(UacfPeerSocket.class.getName());
    }

    /* access modifiers changed from: 0000 */
    public void setUacfGearBridgeBase(UacfGearBridgeBase uacfGearBridgeBase2) {
        this.uacfGearBridgeBase = uacfGearBridgeBase2;
    }

    public void onReceive(int i, byte[] bArr) {
        this.uacfGearBridgeBase.onPeerSocketReceive(this, i, bArr);
    }

    /* access modifiers changed from: protected */
    public void onServiceConnectionLost(int i) {
        this.uacfGearBridgeBase.onPeerSocketDisconnected(this, i);
    }

    public static String convertErrorCodeToErrorName(int i) {
        if (i == 2048) {
            return "ERROR_FATAL";
        }
        switch (i) {
            case 512:
                return "CONNECTION_LOST_UNKNOWN_REASON";
            case SASocket.CONNECTION_LOST_PEER_DISCONNECTED /*513*/:
                return "CONNECTION_LOST_PEER_DISCONNECTED";
            default:
                switch (i) {
                    case SASocket.CONNECTION_LOST_DEVICE_DETACHED /*521*/:
                        return "CONNECTION_LOST_DEVICE_DETACHED";
                    case SASocket.CONNECTION_LOST_RETRANSMISSION_FAILED /*522*/:
                        return "CONNECTION_LOST_RETRANSMISSION_FAILED";
                    case SASocket.CONNECTION_LOST_RECEIVE_LIMIT_VIOLATED /*523*/:
                        return "CONNECTION_LOST_RECEIVE_LIMIT_VIOLATED";
                    default:
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unknown SASocket error code. errorCode=");
                        sb.append(i);
                        return sb.toString();
                }
        }
    }
}
