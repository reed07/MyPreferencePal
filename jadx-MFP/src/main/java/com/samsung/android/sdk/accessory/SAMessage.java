package com.samsung.android.sdk.accessory;

import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.accessory.api.ISAMexCallback;
import com.samsung.accessory.api.ISAMexCallback.Stub;
import java.io.IOException;
import java.lang.ref.WeakReference;

public abstract class SAMessage {
    public static final String ACTION_ACCESSORY_MESSAGE_DISABLED = "com.samsung.accessory.action.MESSAGE_DISABLED";
    public static final String ACTION_ACCESSORY_MESSAGE_ENABLED = "com.samsung.accessory.action.MESSAGE_ENABLED";
    public static final String ACTION_ACCESSORY_MESSAGE_RECEIVED = "com.samsung.accessory.action.MESSAGE_RECEIVED";
    public static final int ERROR_PEER_AGENT_NOT_SUPPORTED = 1795;
    public static final int ERROR_PEER_AGENT_NO_RESPONSE = 1794;
    public static final int ERROR_PEER_AGENT_UNREACHABLE = 1793;
    public static final int ERROR_PEER_SERVICE_NOT_SUPPORTED = 1796;
    public static final int ERROR_SERVICE_NOT_SUPPORTED = 1797;
    public static final int ERROR_UNKNOWN = 1798;
    public static final String EXTRA_PEER_ACCESSORY = "com.samsung.accessory.device.extra.SAPeerAccessory";
    /* access modifiers changed from: private */
    public static final String a;
    private SAAdapter b;
    private MexCallback c;
    private Handler d;
    private String e;

    static class MexCallback extends Stub {
        private WeakReference<SAMessage> mMessageRef;

        MexCallback(SAMessage sAMessage) {
            this.mMessageRef = new WeakReference<>(sAMessage);
        }

        public void onReceived(Bundle bundle) throws RemoteException {
            SAMessage sAMessage = (SAMessage) this.mMessageRef.get();
            if (sAMessage == null) {
                Log.e(SAMessage.a, "onMessageReceived(): SAMessage referecnce is null!");
            } else {
                sAMessage.a(bundle);
            }
        }

        public void onSent(Bundle bundle) throws RemoteException {
            SAMessage sAMessage = (SAMessage) this.mMessageRef.get();
            if (sAMessage == null) {
                Log.e(SAMessage.a, "onMessageReceived(): SAMessage referecnce is null!");
            } else {
                SAMessage.c(sAMessage, bundle);
            }
        }
    }

    static class a implements Runnable {
        private WeakReference<SAMessage> a;
        private Bundle b;
        private boolean c;

        a(SAMessage sAMessage, Bundle bundle, boolean z) {
            this.a = new WeakReference<>(sAMessage);
            this.b = bundle;
            this.c = z;
        }

        public final void run() {
            SAMessage sAMessage = (SAMessage) this.a.get();
            if (sAMessage == null) {
                Log.e(SAMessage.a, "run(): SAMessage referecnce is null!");
            } else if (this.c) {
                SAMessage.a(sAMessage, this.b);
            } else {
                SAMessage.b(sAMessage, this.b);
            }
        }
    }

    static {
        StringBuilder sb = new StringBuilder("[SA_SDK]");
        sb.append(SAMessage.class.getSimpleName());
        a = sb.toString();
    }

    protected SAMessage(SAAgent sAAgent) {
        if (sAAgent != null) {
            this.b = SAAdapter.a(sAAgent.getApplicationContext());
            this.c = new MexCallback(this);
            this.d = sAAgent.a;
            String a2 = sAAgent.a(this);
            if (a2 != null) {
                try {
                    a(a2);
                    return;
                } catch (c e2) {
                    String str = a;
                    StringBuilder sb = new StringBuilder("Failed to create SAMessage instance: ");
                    sb.append(e2.getMessage());
                    Log.e(str, sb.toString());
                }
            }
            return;
        }
        Log.e(a, "SAMessage() - empty agent instance!");
        throw new IllegalArgumentException("Message creation failed! - invalid agent instance supplied");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00f4, code lost:
        switch(r8) {
            case -1800: goto L_0x00fd;
            case -1799: goto L_0x00fa;
            default: goto L_0x00f7;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f7, code lost:
        r0 = "Send Message Failed - internal error!";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00fa, code lost:
        r0 = "Send Message Failed - Message timed out!";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00fd, code lost:
        r0 = "Send Message Failed - Peer Agent is invalid!";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0104, code lost:
        throw new java.io.IOException(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(com.samsung.android.sdk.accessory.SAPeerAgent r21, byte[] r22, boolean r23) throws java.io.IOException {
        /*
            r20 = this;
            r1 = r20
            r0 = r21
            r7 = r22
            if (r0 == 0) goto L_0x01ac
            if (r7 == 0) goto L_0x019d
            int r2 = r7.length
            if (r2 == 0) goto L_0x018e
            int r2 = r7.length
            int r3 = r21.getMaxAllowedDataSize()
            if (r2 > r3) goto L_0x0154
            java.lang.String r2 = r1.e
            if (r2 == 0) goto L_0x0145
            boolean r2 = com.samsung.android.sdk.accessory.i.h()
            if (r2 != 0) goto L_0x0021
            r2 = 1797(0x705, float:2.518E-42)
            goto L_0x0025
        L_0x0021:
            int r2 = r21.a()
        L_0x0025:
            r3 = 1792(0x700, float:2.511E-42)
            if (r2 == r3) goto L_0x002e
            r3 = -1
            r1.onError(r0, r3, r2)
            return r3
        L_0x002e:
            r8 = -1801(0xfffffffffffff8f7, float:NaN)
            com.samsung.android.sdk.accessory.SAPeerAccessory r2 = r21.getAccessory()
            long r2 = r2.getId()
            java.lang.String r4 = r1.e
            java.lang.String r5 = r21.getPeerId()
            java.lang.String r9 = a(r4, r2, r5)
            if (r23 == 0) goto L_0x004e
            com.samsung.android.sdk.accessory.SAPeerAccessory r2 = r21.getAccessory()
            int r2 = r2.d()
            r6 = r2
            goto L_0x0050
        L_0x004e:
            r2 = 0
            r6 = 0
        L_0x0050:
            java.lang.String r2 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Sending data:"
            r3.<init>(r4)
            int r4 = r7.length
            r3.append(r4)
            java.lang.String r4 = " bytes"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r2, r3)
            com.samsung.android.sdk.accessory.e r15 = new com.samsung.android.sdk.accessory.e
            r2 = 2
            r15.<init>(r2, r9)
            int r3 = com.samsung.android.sdk.accessory.i.b()     // Catch:{ IOException -> 0x011e, all -> 0x011b }
            int r4 = com.samsung.android.sdk.accessory.i.c()     // Catch:{ IOException -> 0x011e, all -> 0x011b }
            com.samsung.android.sdk.accessory.SAPeerAccessory r2 = r21.getAccessory()     // Catch:{ IOException -> 0x011e, all -> 0x011b }
            int r5 = r2.c()     // Catch:{ IOException -> 0x011e, all -> 0x011b }
            r2 = r15
            r7 = r22
            r2.a(r3, r4, r5, r6, r7)     // Catch:{ IOException -> 0x011e, all -> 0x011b }
        L_0x0085:
            com.samsung.android.sdk.accessory.d r2 = r15.a()     // Catch:{ IOException -> 0x011e, all -> 0x011b }
            if (r2 != 0) goto L_0x008f
            r15.d()
            return r8
        L_0x008f:
            com.samsung.android.sdk.accessory.SAAdapter r10 = r1.b     // Catch:{ c -> 0x010a, all -> 0x0107 }
            java.lang.String r11 = r1.e     // Catch:{ c -> 0x010a, all -> 0x0107 }
            java.lang.String r12 = r21.getPeerId()     // Catch:{ c -> 0x010a, all -> 0x0107 }
            com.samsung.android.sdk.accessory.SAPeerAccessory r3 = r21.getAccessory()     // Catch:{ c -> 0x010a, all -> 0x0107 }
            long r13 = r3.getId()     // Catch:{ c -> 0x010a, all -> 0x0107 }
            byte[] r3 = r2.d()     // Catch:{ c -> 0x010a, all -> 0x0107 }
            int r17 = r2.e()     // Catch:{ c -> 0x010a, all -> 0x0107 }
            int r18 = r2.f()     // Catch:{ c -> 0x010a, all -> 0x0107 }
            int r19 = r2.g()     // Catch:{ c -> 0x010a, all -> 0x0107 }
            r4 = r15
            r15 = r3
            r16 = r23
            int r8 = r10.a(r11, r12, r13, r15, r16, r17, r18, r19)     // Catch:{ c -> 0x0105 }
            if (r8 <= 0) goto L_0x00f4
            java.lang.String r3 = a     // Catch:{ c -> 0x0105 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ c -> 0x0105 }
            java.lang.String r6 = java.lang.String.valueOf(r9)     // Catch:{ c -> 0x0105 }
            r5.<init>(r6)     // Catch:{ c -> 0x0105 }
            java.lang.String r6 = "-> msg<"
            r5.append(r6)     // Catch:{ c -> 0x0105 }
            r5.append(r8)     // Catch:{ c -> 0x0105 }
            java.lang.String r6 = ">["
            r5.append(r6)     // Catch:{ c -> 0x0105 }
            com.samsung.android.sdk.accessory.d r6 = r4.b()     // Catch:{ c -> 0x0105 }
            int r6 = r6.e()     // Catch:{ c -> 0x0105 }
            r5.append(r6)     // Catch:{ c -> 0x0105 }
            java.lang.String r6 = "] sent: "
            r5.append(r6)     // Catch:{ c -> 0x0105 }
            int r6 = r4.c()     // Catch:{ c -> 0x0105 }
            r5.append(r6)     // Catch:{ c -> 0x0105 }
            java.lang.String r5 = r5.toString()     // Catch:{ c -> 0x0105 }
            android.util.Log.d(r3, r5)     // Catch:{ c -> 0x0105 }
            r2.h()     // Catch:{ IOException -> 0x0119 }
            r15 = r4
            goto L_0x0085
        L_0x00f4:
            switch(r8) {
                case -1800: goto L_0x00fd;
                case -1799: goto L_0x00fa;
                default: goto L_0x00f7;
            }
        L_0x00f7:
            java.lang.String r0 = "Send Message Failed - internal error!"
            goto L_0x00ff
        L_0x00fa:
            java.lang.String r0 = "Send Message Failed - Message timed out!"
            goto L_0x00ff
        L_0x00fd:
            java.lang.String r0 = "Send Message Failed - Peer Agent is invalid!"
        L_0x00ff:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ c -> 0x0105 }
            r3.<init>(r0)     // Catch:{ c -> 0x0105 }
            throw r3     // Catch:{ c -> 0x0105 }
        L_0x0105:
            r0 = move-exception
            goto L_0x010c
        L_0x0107:
            r0 = move-exception
            r4 = r15
            goto L_0x0115
        L_0x010a:
            r0 = move-exception
            r4 = r15
        L_0x010c:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x0114 }
            java.lang.String r5 = "Send Message Failed"
            r3.<init>(r5, r0)     // Catch:{ all -> 0x0114 }
            throw r3     // Catch:{ all -> 0x0114 }
        L_0x0114:
            r0 = move-exception
        L_0x0115:
            r2.h()     // Catch:{ IOException -> 0x0119 }
            throw r0     // Catch:{ IOException -> 0x0119 }
        L_0x0119:
            r0 = move-exception
            goto L_0x0120
        L_0x011b:
            r0 = move-exception
            r4 = r15
            goto L_0x0141
        L_0x011e:
            r0 = move-exception
            r4 = r15
        L_0x0120:
            java.lang.String r2 = a     // Catch:{ all -> 0x0140 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0140 }
            java.lang.String r5 = "Send Message Failed! <"
            r3.<init>(r5)     // Catch:{ all -> 0x0140 }
            r3.append(r8)     // Catch:{ all -> 0x0140 }
            java.lang.String r5 = " "
            r3.append(r5)     // Catch:{ all -> 0x0140 }
            java.lang.String r5 = r0.getLocalizedMessage()     // Catch:{ all -> 0x0140 }
            r3.append(r5)     // Catch:{ all -> 0x0140 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0140 }
            android.util.Log.e(r2, r3)     // Catch:{ all -> 0x0140 }
            throw r0     // Catch:{ all -> 0x0140 }
        L_0x0140:
            r0 = move-exception
        L_0x0141:
            r4.d()
            throw r0
        L_0x0145:
            java.lang.String r0 = a
            java.lang.String r2 = "Send: agentId not retrieved!"
            android.util.Log.e(r0, r2)
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r2 = "Failed to send message - Agent info empty!"
            r0.<init>(r2)
            throw r0
        L_0x0154:
            java.lang.String r2 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Send: Data too big:"
            r3.<init>(r4)
            int r4 = r7.length
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r2, r3)
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Data Too long..! Data size:"
            r3.<init>(r4)
            int r4 = r7.length
            r3.append(r4)
            java.lang.String r4 = "Max allowed Size:"
            r3.append(r4)
            int r0 = r21.getMaxAllowedDataSize()
            r3.append(r0)
            java.lang.String r0 = " .Please check SAPeerAgent.getMaxAllowedDataSize()"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x018e:
            java.lang.String r0 = a
            java.lang.String r2 = "Send: invalid data length 0"
            android.util.Log.e(r0, r2)
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Invalid data length 0"
            r0.<init>(r2)
            throw r0
        L_0x019d:
            java.lang.String r0 = a
            java.lang.String r2 = "Send: data null"
            android.util.Log.e(r0, r2)
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Invalid data to send!"
            r0.<init>(r2)
            throw r0
        L_0x01ac:
            java.lang.String r0 = a
            java.lang.String r2 = "Send: peerAgent null"
            android.util.Log.e(r0, r2)
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Send Message Failed! - Peer Agent is invalid!"
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.accessory.SAMessage.a(com.samsung.android.sdk.accessory.SAPeerAgent, byte[], boolean):int");
    }

    private static String a(String str, long j, String str2) {
        StringBuilder sb = new StringBuilder(40);
        sb.append(str);
        sb.append("_");
        sb.append(j);
        sb.append("_");
        sb.append(str2);
        return sb.toString();
    }

    private void a(long j, int i, int i2) throws IOException {
        try {
            this.b.a(j, i, i2);
        } catch (c e2) {
            String str = a;
            StringBuilder sb = new StringBuilder("Ack failed! ");
            sb.append(e2);
            Log.e(str, sb.toString());
            throw new IOException("Send Failed", e2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0082, code lost:
        if (r1 == 3) goto L_0x0084;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008d A[SYNTHETIC, Splitter:B:29:0x008d] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x010e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(com.samsung.android.sdk.accessory.SAMessage r14, android.os.Bundle r15) {
        /*
            java.lang.String r0 = r14.e
            if (r0 != 0) goto L_0x000c
            java.lang.String r14 = a
            java.lang.String r15 = "onMessageReceived(): Agent info empty!"
        L_0x0008:
            android.util.Log.e(r14, r15)
            return
        L_0x000c:
            java.lang.Class<com.samsung.android.sdk.accessory.SAPeerAgent> r0 = com.samsung.android.sdk.accessory.SAPeerAgent.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            r15.setClassLoader(r0)
            java.lang.String r0 = "com.samsung.accessory.adapter.extra.READ_BYTES"
            byte[] r0 = r15.getByteArray(r0)
            java.lang.String r1 = "com.samsung.accessory.adapter.extra.READ_LENGHT"
            int r6 = r15.getInt(r1)
            java.lang.String r1 = "com.samsung.accessory.adapter.extra.READ_OFFSET"
            int r5 = r15.getInt(r1)
            java.lang.String r1 = "fragmentIndex"
            int r3 = r15.getInt(r1)
            java.lang.String r1 = "peerAgent"
            android.os.Parcelable r1 = r15.getParcelable(r1)
            r7 = r1
            com.samsung.android.sdk.accessory.SAPeerAgent r7 = (com.samsung.android.sdk.accessory.SAPeerAgent) r7
            java.lang.String r1 = "transactionId"
            int r15 = r15.getInt(r1)
            if (r7 == 0) goto L_0x0112
            com.samsung.android.sdk.accessory.SAPeerAccessory r1 = r7.getAccessory()
            if (r1 != 0) goto L_0x0046
            goto L_0x0112
        L_0x0046:
            com.samsung.android.sdk.accessory.SAPeerAccessory r1 = r7.getAccessory()
            long r8 = r1.getId()
            java.lang.String r1 = r7.getPeerId()
            java.lang.String r2 = r14.e
            java.lang.String r10 = a(r1, r8, r2)
            r11 = -1
            r12 = 1798(0x706, float:2.52E-42)
            r13 = 2
            com.samsung.android.sdk.accessory.SAPeerAccessory r1 = r7.getAccessory()     // Catch:{ IOException -> 0x00bf }
            int r2 = r1.a()     // Catch:{ IOException -> 0x00bf }
            r1 = r10
            r4 = r0
            int r1 = com.samsung.android.sdk.accessory.g.b(r1, r2, r3, r4, r5, r6)     // Catch:{ IOException -> 0x00bf }
            r2 = 1
            if (r1 != r2) goto L_0x0081
            byte[] r2 = com.samsung.android.sdk.accessory.g.a(r10)     // Catch:{ IOException -> 0x007e, all -> 0x007a }
            if (r2 != 0) goto L_0x0074
            goto L_0x0084
        L_0x0074:
            r14.onReceive(r7, r2)     // Catch:{ IOException -> 0x007e, all -> 0x007a }
            r11 = 1792(0x700, float:2.511E-42)
            goto L_0x0086
        L_0x007a:
            r15 = move-exception
            r11 = r1
            goto L_0x0107
        L_0x007e:
            r2 = move-exception
            r11 = r1
            goto L_0x00c0
        L_0x0081:
            r2 = 3
            if (r1 != r2) goto L_0x0086
        L_0x0084:
            r11 = 1798(0x706, float:2.52E-42)
        L_0x0086:
            com.samsung.android.sdk.accessory.SAAdapter r2 = r14.b
            r2.b(r0)
            if (r11 <= 0) goto L_0x00b7
            r14.a(r8, r15, r11)     // Catch:{ IOException -> 0x0093 }
            goto L_0x00b7
        L_0x0091:
            r14 = move-exception
            goto L_0x00b1
        L_0x0093:
            r14 = move-exception
            java.lang.String r15 = a     // Catch:{ all -> 0x0091 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0091 }
            java.lang.String r2 = "Failed to send message status! "
            r0.<init>(r2)     // Catch:{ all -> 0x0091 }
            java.lang.String r14 = r14.getLocalizedMessage()     // Catch:{ all -> 0x0091 }
            r0.append(r14)     // Catch:{ all -> 0x0091 }
            java.lang.String r14 = r0.toString()     // Catch:{ all -> 0x0091 }
            android.util.Log.e(r15, r14)     // Catch:{ all -> 0x0091 }
            if (r1 == r13) goto L_0x0100
            com.samsung.android.sdk.accessory.g.b(r10)
            return
        L_0x00b1:
            if (r1 == r13) goto L_0x00b6
            com.samsung.android.sdk.accessory.g.b(r10)
        L_0x00b6:
            throw r14
        L_0x00b7:
            if (r1 == r13) goto L_0x0100
            com.samsung.android.sdk.accessory.g.b(r10)
            goto L_0x0100
        L_0x00bd:
            r15 = move-exception
            goto L_0x0107
        L_0x00bf:
            r2 = move-exception
        L_0x00c0:
            java.lang.String r1 = a     // Catch:{ all -> 0x00bd }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bd }
            java.lang.String r4 = "Error in onRead! "
            r3.<init>(r4)     // Catch:{ all -> 0x00bd }
            r3.append(r2)     // Catch:{ all -> 0x00bd }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x00bd }
            android.util.Log.e(r1, r2)     // Catch:{ all -> 0x00bd }
            com.samsung.android.sdk.accessory.SAAdapter r1 = r14.b
            r1.b(r0)
            r14.a(r8, r15, r12)     // Catch:{ IOException -> 0x00e3 }
            if (r11 == r13) goto L_0x0100
            com.samsung.android.sdk.accessory.g.b(r10)
            return
        L_0x00e1:
            r14 = move-exception
            goto L_0x0101
        L_0x00e3:
            r14 = move-exception
            java.lang.String r15 = a     // Catch:{ all -> 0x00e1 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e1 }
            java.lang.String r1 = "Failed to send message status! "
            r0.<init>(r1)     // Catch:{ all -> 0x00e1 }
            java.lang.String r14 = r14.getLocalizedMessage()     // Catch:{ all -> 0x00e1 }
            r0.append(r14)     // Catch:{ all -> 0x00e1 }
            java.lang.String r14 = r0.toString()     // Catch:{ all -> 0x00e1 }
            android.util.Log.e(r15, r14)     // Catch:{ all -> 0x00e1 }
            if (r11 == r13) goto L_0x0100
            com.samsung.android.sdk.accessory.g.b(r10)
        L_0x0100:
            return
        L_0x0101:
            if (r11 == r13) goto L_0x0106
            com.samsung.android.sdk.accessory.g.b(r10)
        L_0x0106:
            throw r14
        L_0x0107:
            com.samsung.android.sdk.accessory.SAAdapter r14 = r14.b
            r14.b(r0)
            if (r11 == r13) goto L_0x0111
            com.samsung.android.sdk.accessory.g.b(r10)
        L_0x0111:
            throw r15
        L_0x0112:
            java.lang.String r14 = a
            java.lang.String r15 = "onMessageReceived(): PeerAgent is null!"
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.accessory.SAMessage.a(com.samsung.android.sdk.accessory.SAMessage, android.os.Bundle):void");
    }

    static /* synthetic */ void b(SAMessage sAMessage, Bundle bundle) {
        bundle.setClassLoader(SAPeerAgent.class.getClassLoader());
        SAPeerAgent sAPeerAgent = (SAPeerAgent) bundle.getParcelable("peerAgent");
        int i = bundle.getInt("transactionId");
        int i2 = bundle.getInt("errorcode");
        if (i2 == 1792) {
            sAMessage.onSent(sAPeerAgent, i);
        } else {
            sAMessage.onError(sAPeerAgent, i, i2);
        }
    }

    static /* synthetic */ void c(SAMessage sAMessage, Bundle bundle) {
        Handler handler = sAMessage.d;
        if (handler != null) {
            handler.post(new a(sAMessage, bundle, false));
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a() {
        try {
            if (this.e != null) {
                this.b.d(this.e);
            }
        } catch (c e2) {
            String str = a;
            StringBuilder sb = new StringBuilder("Failed to un-register Mex callback! ");
            sb.append(e2.getLocalizedMessage());
            Log.e(str, sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(Bundle bundle) {
        Handler handler = this.d;
        if (handler != null) {
            handler.post(new a(this, bundle, true));
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str) throws c {
        String str2 = this.e;
        if (str2 != null && !str.equalsIgnoreCase(str2)) {
            this.b.d(this.e);
        }
        this.e = str;
        this.b.a(str, (ISAMexCallback) this.c);
    }

    /* access modifiers changed from: protected */
    public abstract void onError(SAPeerAgent sAPeerAgent, int i, int i2);

    /* access modifiers changed from: protected */
    public abstract void onReceive(SAPeerAgent sAPeerAgent, byte[] bArr);

    /* access modifiers changed from: protected */
    public abstract void onSent(SAPeerAgent sAPeerAgent, int i);

    public int secureSend(SAPeerAgent sAPeerAgent, byte[] bArr) throws IOException {
        return a(sAPeerAgent, bArr, true);
    }

    public int send(SAPeerAgent sAPeerAgent, byte[] bArr) throws IOException {
        return a(sAPeerAgent, bArr, false);
    }
}
