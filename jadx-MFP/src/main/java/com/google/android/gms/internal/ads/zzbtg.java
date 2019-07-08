package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzbtg extends zzbtf<FieldDescriptorType, Object> {
    zzbtg(int i) {
        super(i, null);
    }

    public final void zzakj() {
        if (!isImmutable()) {
            for (int i = 0; i < zzaop(); i++) {
                Entry zzfy = zzfy(i);
                if (((zzbqw) zzfy.getKey()).zzamn()) {
                    zzfy.setValue(Collections.unmodifiableList((List) zzfy.getValue()));
                }
            }
            for (Entry entry : zzaoq()) {
                if (((zzbqw) entry.getKey()).zzamn()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzakj();
    }
}
