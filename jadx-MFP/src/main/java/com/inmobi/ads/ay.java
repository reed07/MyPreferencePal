package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inmobi.commons.core.a.a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: NativeScrollableDataSourceFactory */
final class ay {
    @Nullable
    public static ax a(int i, @NonNull ao aoVar, @NonNull au auVar) {
        switch (i) {
            case 0:
                return new ap(aoVar, auVar);
            case 1:
                try {
                    return new NativeRecyclerViewAdapter(aoVar, auVar);
                } catch (NoClassDefFoundError e) {
                    Logger.a(InternalLogLevel.ERROR, "InMobi", "Error rendering ad! RecyclerView not found. Please check if the recyclerview support library was included");
                    a.a().a(new com.inmobi.commons.core.e.a(e));
                    return null;
                }
            default:
                return null;
        }
    }
}
