package com.google.ads.interactivemedia.v3.impl.data;

import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: IMASDK */
public abstract class ac {
    public abstract ab build();

    public abstract ac obstructions(List<ad> list);

    public ac views(Collection<View> collection) {
        ArrayList arrayList = new ArrayList();
        for (View view : collection) {
            arrayList.add(ad.builder().view(view).build());
        }
        return obstructions(arrayList);
    }
}
