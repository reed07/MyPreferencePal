package com.brightcove.player.analytics;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.brightcove.player.store.BaseEntity;
import com.brightcove.player.store.IdentifiableEntity;
import io.requery.Convert;
import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.query.Expression;
import io.requery.query.LogicalCondition;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.Map;

@Entity
public abstract class AbstractAnalyticsEvent extends BaseEntity implements IdentifiableEntity<AnalyticsEvent, Long> {
    public static final int CRITICAL = 2;
    public static final int HIGH = 1;
    public static final int LOW = -1;
    public static final int NORMAL = 0;
    @NonNull
    int attemptsMade;
    @Key
    @Generated
    Long key;
    @NonNull
    @Convert
    Map<String, String> parameters;
    int priority;
    @NonNull
    String type;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Priority {
    }

    public LogicalCondition<? extends Expression<Long>, ?> getIdentityCondition(Long l) {
        return (LogicalCondition) AnalyticsEvent.KEY.eq((Object) l);
    }

    public LogicalCondition<? extends Expression<Long>, ?> getIdentityCondition() {
        return getIdentityCondition((Long) getKey());
    }

    public static AnalyticsEvent create(int i, @NonNull String str, @Nullable Map<String, String> map) {
        Map map2;
        if (!TextUtils.isEmpty(str)) {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent();
            analyticsEvent.setPriority(i);
            analyticsEvent.setType(str);
            if (map == null) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(map);
            }
            analyticsEvent.setParameters(map2);
            return analyticsEvent;
        }
        throw new IllegalArgumentException("Analytics event type is empty");
    }

    public static AnalyticsEvent create(@NonNull String str, @Nullable Map<String, String> map) {
        return create(0, str, map);
    }
}
