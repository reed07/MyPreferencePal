package com.shinobicontrols.charts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class al {
    private final HashMap<b, HashSet<a>> a = new HashMap<>();

    al() {
    }

    /* access modifiers changed from: 0000 */
    public am a(b bVar, a aVar) {
        HashSet hashSet = (HashSet) this.a.get(bVar);
        if (hashSet == null) {
            hashSet = new HashSet();
            this.a.put(bVar, hashSet);
        }
        return a(hashSet, aVar);
    }

    /* access modifiers changed from: 0000 */
    public void a(ag<?> agVar) {
        a(agVar.a(), agVar);
    }

    private am a(final HashSet<a> hashSet, final a aVar) {
        hashSet.add(aVar);
        return new am() {
            public void a() {
                hashSet.remove(aVar);
            }
        };
    }

    private void a(b bVar, ag<?> agVar) {
        HashSet hashSet = (HashSet) this.a.get(bVar);
        if (hashSet != null && hashSet.size() > 0) {
            agVar.a((Set<? extends a>) hashSet);
        }
    }
}
