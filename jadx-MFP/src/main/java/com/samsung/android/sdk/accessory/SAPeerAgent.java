package com.samsung.android.sdk.accessory;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.exoplayer2.source.ExtractorMediaSource;

public class SAPeerAgent implements Parcelable {
    public static final Creator<SAPeerAgent> CREATOR = new Creator<SAPeerAgent>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new SAPeerAgent(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new SAPeerAgent[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private String d;
    private SAPeerAccessory e;
    private long f;
    private int g = 0;
    private int h = 1;

    public SAPeerAgent(Parcel parcel) {
        int readInt = parcel.readInt();
        StringBuilder sb = new StringBuilder("Peeragent:Framework version:");
        sb.append(readInt);
        Log.v("[SA_SDK]SAPeerAgent", sb.toString());
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = (SAPeerAccessory) parcel.readParcelable(SAPeerAccessory.class.getClassLoader());
        if (readInt >= 9) {
            this.g = parcel.readInt();
            this.h = parcel.readInt();
        }
    }

    /* access modifiers changed from: 0000 */
    public final int a() {
        int i = this.g;
        if (i == 1) {
            return 1792;
        }
        if (i == 2) {
            return SAMessage.ERROR_PEER_SERVICE_NOT_SUPPORTED;
        }
        if (i == 0) {
            return SAMessage.ERROR_PEER_AGENT_NOT_SUPPORTED;
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public final void a(long j) {
        this.f = j;
    }

    /* access modifiers changed from: 0000 */
    public final int b() {
        SAPeerAccessory sAPeerAccessory = this.e;
        if (sAPeerAccessory != null) {
            return sAPeerAccessory.b();
        }
        return 65530;
    }

    /* access modifiers changed from: 0000 */
    public final long c() {
        return this.f;
    }

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r7 instanceof com.samsung.android.sdk.accessory.SAPeerAgent
            if (r1 == 0) goto L_0x00bb
            com.samsung.android.sdk.accessory.SAPeerAgent r7 = (com.samsung.android.sdk.accessory.SAPeerAgent) r7
            java.lang.String r1 = r6.getPeerId()
            if (r1 != 0) goto L_0x0028
            java.lang.String r1 = "[SA_SDK]SAPeerAgent"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Invalid peerAgent instance.Peer ID - this:null PeerAgent:"
            r2.<init>(r3)
        L_0x0019:
            java.lang.String r7 = r7.getPeerId()
        L_0x001d:
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            android.util.Log.w(r1, r7)
            return r0
        L_0x0028:
            java.lang.String r1 = r6.a
            java.lang.String r2 = r7.getPeerId()
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0048
            java.lang.String r1 = "[SA_SDK]SAPeerAgent"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Invalid peerAgent instance.Peer ID - this:"
            r2.<init>(r3)
            java.lang.String r3 = r6.a
            r2.append(r3)
            java.lang.String r3 = " PeerAgent:"
            r2.append(r3)
            goto L_0x0019
        L_0x0048:
            java.lang.String r1 = r6.getContainerId()
            if (r1 != 0) goto L_0x0062
            java.lang.String r1 = r7.getContainerId()
            if (r1 == 0) goto L_0x0082
            java.lang.String r1 = "[SA_SDK]SAPeerAgent"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Invalid peerAgent instance.Container ID - this:null PeerAgent:"
            r2.<init>(r3)
        L_0x005d:
            java.lang.String r7 = r7.getContainerId()
            goto L_0x001d
        L_0x0062:
            java.lang.String r1 = r6.b
            java.lang.String r2 = r7.getContainerId()
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0082
            java.lang.String r1 = "[SA_SDK]SAPeerAgent"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Invalid peerAgent instance.Container ID - this:"
            r2.<init>(r3)
            java.lang.String r3 = r6.b
            r2.append(r3)
            java.lang.String r3 = " PeerAgent:"
            r2.append(r3)
            goto L_0x005d
        L_0x0082:
            com.samsung.android.sdk.accessory.SAPeerAccessory r1 = r7.getAccessory()
            long r1 = r1.getId()
            com.samsung.android.sdk.accessory.SAPeerAccessory r3 = r6.getAccessory()
            long r3 = r3.getId()
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x00b9
            java.lang.String r1 = "[SA_SDK]SAPeerAgent"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Invalid peerAgent instance.Accessory ID - this:"
            r2.<init>(r3)
            com.samsung.android.sdk.accessory.SAPeerAccessory r3 = r6.getAccessory()
            java.lang.String r3 = r3.getAccessoryId()
            r2.append(r3)
            java.lang.String r3 = " PeerAgent:"
            r2.append(r3)
            com.samsung.android.sdk.accessory.SAPeerAccessory r7 = r7.getAccessory()
            java.lang.String r7 = r7.getAccessoryId()
            goto L_0x001d
        L_0x00b9:
            r7 = 1
            return r7
        L_0x00bb:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.accessory.SAPeerAgent.equals(java.lang.Object):boolean");
    }

    public SAPeerAccessory getAccessory() {
        return this.e;
    }

    public long getAccessoryId() {
        return this.e.getId();
    }

    public String getAppName() {
        return this.c;
    }

    public String getContainerId() {
        return this.b;
    }

    public int getMaxAllowedDataSize() {
        SAPeerAccessory sAPeerAccessory = this.e;
        return sAPeerAccessory != null ? sAPeerAccessory.a() : ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES;
    }

    public int getMaxAllowedMessageSize() {
        SAPeerAccessory sAPeerAccessory = this.e;
        return sAPeerAccessory != null ? sAPeerAccessory.a() : ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES;
    }

    public String getPeerId() {
        return this.a;
    }

    public String getProfileVersion() {
        return this.d;
    }

    public int hashCode() {
        int hashCode = (this.a.hashCode() + 527) * 31;
        String str = this.b;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        SAPeerAccessory sAPeerAccessory = this.e;
        if (sAPeerAccessory != null) {
            i = (int) (sAPeerAccessory.getId() ^ (this.e.getId() >>> 32));
        }
        return hashCode2 + i;
    }

    public boolean isFeatureEnabled(int i) {
        if (i == 0) {
            return true;
        }
        switch (i) {
            case 2:
                return this.g == 1;
            case 3:
                return this.h == 1;
            default:
                return false;
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder sb = new StringBuilder("PeerAgent - id:");
        sb.append(this.a);
        sb.append(" ");
        stringBuffer.append(sb.toString());
        StringBuilder sb2 = new StringBuilder("containerId:");
        sb2.append(this.b);
        sb2.append(" ");
        stringBuffer.append(sb2.toString());
        StringBuilder sb3 = new StringBuilder("FriendlyName:");
        sb3.append(this.c);
        sb3.append(" ");
        stringBuffer.append(sb3.toString());
        StringBuilder sb4 = new StringBuilder("Profile Version:");
        sb4.append(this.d);
        sb4.append(" ");
        stringBuffer.append(sb4.toString());
        StringBuilder sb5 = new StringBuilder(String.valueOf(this.e.toString()));
        sb5.append(" ");
        stringBuffer.append(sb5.toString());
        StringBuilder sb6 = new StringBuilder("MexSupport:");
        sb6.append(this.g);
        sb6.append(" ");
        stringBuffer.append(sb6.toString());
        StringBuilder sb7 = new StringBuilder("SocketSupport:");
        sb7.append(this.h);
        stringBuffer.append(sb7.toString());
        return stringBuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(9);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeParcelable(this.e, i);
        Log.v("[SA_SDK]SAPeerAgent", "mCompatibilityVersion = 0");
        if ((i.e() == 1 && i.g()) || i.h()) {
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
        }
    }
}
