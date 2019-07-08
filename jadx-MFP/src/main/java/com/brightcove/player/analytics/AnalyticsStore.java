package com.brightcove.player.analytics;

import android.content.Context;
import android.support.annotation.NonNull;
import com.brightcove.player.store.BaseStore;
import io.requery.meta.QueryAttribute;
import io.requery.query.Condition;
import io.requery.query.Limit;
import io.requery.reactivex.ReactiveResult;
import io.requery.reactivex.ReactiveScalar;
import java.util.List;

final class AnalyticsStore extends BaseStore {
    private static volatile AnalyticsStore INSTANCE = null;
    private static final int STORE_VERSION = 2;
    private static final String TAG = "AnalyticsStore";

    public static AnalyticsStore getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            synchronized (AnalyticsStore.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AnalyticsStore(context);
                }
            }
        }
        return INSTANCE;
    }

    private AnalyticsStore(@NonNull Context context) {
        super(context, Models.DEFAULT, TAG, 2);
    }

    @NonNull
    public List<AnalyticsEvent> getCriticalEvents(int i) {
        return ((ReactiveResult) ((Limit) this.dataStore.select(AnalyticsEvent.class, new QueryAttribute[0]).where((Condition) AnalyticsEvent.PRIORITY.eq((Object) Integer.valueOf(2))).orderBy(AnalyticsEvent.ATTEMPTS_MADE.asc(), AnalyticsEvent.KEY.asc())).limit(i).get()).toList();
    }

    @NonNull
    public List<AnalyticsEvent> getBacklog(int i) {
        return ((ReactiveResult) ((Limit) this.dataStore.select(AnalyticsEvent.class, new QueryAttribute[0]).orderBy(AnalyticsEvent.ATTEMPTS_MADE.asc(), AnalyticsEvent.PRIORITY.desc(), AnalyticsEvent.KEY.asc())).limit(i).get()).toList();
    }

    public long deleteNonEssentialEvents() {
        return (long) ((Integer) ((ReactiveScalar) this.dataStore.delete(AnalyticsEvent.class).where((Condition) AnalyticsEvent.PRIORITY.lessThan(Integer.valueOf(1))).get()).value()).intValue();
    }
}
