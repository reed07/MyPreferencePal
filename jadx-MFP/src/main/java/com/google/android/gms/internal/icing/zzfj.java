package com.google.android.gms.internal.icing;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzfj extends zzfi<FieldDescriptorType, Object> {
    zzfj(int i) {
        super(i, null);
    }

    public final void zzaj() {
        if (!isImmutable()) {
            for (int i = 0; i < zzdh(); i++) {
                Entry zzaj = zzaj(i);
                if (((zzde) zzaj.getKey()).zzbn()) {
                    zzaj.setValue(Collections.unmodifiableList((List) zzaj.getValue()));
                }
            }
            for (Entry entry : zzdi()) {
                if (((zzde) entry.getKey()).zzbn()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzaj();
    }
}
