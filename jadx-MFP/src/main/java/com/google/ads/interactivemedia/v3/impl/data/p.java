package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: IMASDK */
final class p extends ad {
    private final boolean attached;
    private final v bounds;
    private final boolean hidden;
    private final String type;

    private p(boolean z, v vVar, boolean z2, String str) {
        this.attached = z;
        this.bounds = vVar;
        this.hidden = z2;
        this.type = str;
    }

    /* access modifiers changed from: 0000 */
    public final boolean attached() {
        return this.attached;
    }

    /* access modifiers changed from: 0000 */
    public final v bounds() {
        return this.bounds;
    }

    /* access modifiers changed from: 0000 */
    public final boolean hidden() {
        return this.hidden;
    }

    /* access modifiers changed from: 0000 */
    public final String type() {
        return this.type;
    }

    public final String toString() {
        boolean z = this.attached;
        String valueOf = String.valueOf(this.bounds);
        boolean z2 = this.hidden;
        String str = this.type;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 61 + String.valueOf(str).length());
        sb.append("ObstructionData{attached=");
        sb.append(z);
        sb.append(", bounds=");
        sb.append(valueOf);
        sb.append(", hidden=");
        sb.append(z2);
        sb.append(", type=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ad)) {
            return false;
        }
        ad adVar = (ad) obj;
        return this.attached == adVar.attached() && this.bounds.equals(adVar.bounds()) && this.hidden == adVar.hidden() && this.type.equals(adVar.type());
    }

    public final int hashCode() {
        int i = 1231;
        int hashCode = ((((this.attached ? 1231 : 1237) ^ 1000003) * 1000003) ^ this.bounds.hashCode()) * 1000003;
        if (!this.hidden) {
            i = 1237;
        }
        return ((hashCode ^ i) * 1000003) ^ this.type.hashCode();
    }

    /* synthetic */ p(boolean z, v vVar, boolean z2, String str, f fVar) {
        this(z, vVar, z2, str);
    }
}
