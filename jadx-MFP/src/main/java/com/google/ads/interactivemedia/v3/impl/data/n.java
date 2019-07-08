package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.agb;

/* compiled from: IMASDK */
final class n extends ab {
    private final agb<ad> obstructions;

    private n(agb<ad> agb) {
        this.obstructions = agb;
    }

    /* access modifiers changed from: 0000 */
    public final agb<ad> obstructions() {
        return this.obstructions;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.obstructions);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 34);
        sb.append("ObstructionListData{obstructions=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ab)) {
            return false;
        }
        return this.obstructions.equals(((ab) obj).obstructions());
    }

    public final int hashCode() {
        return this.obstructions.hashCode() ^ 1000003;
    }

    /* synthetic */ n(agb agb, f fVar) {
        this(agb);
    }
}
