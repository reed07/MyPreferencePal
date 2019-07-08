package com.brightcove.player.analytics;

import com.brightcove.player.store.MapConverter;
import com.myfitnesspal.shared.db.table.InstalledDatasetsTable.Columns;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import io.requery.Persistable;
import io.requery.meta.AttributeBuilder;
import io.requery.meta.QueryAttribute;
import io.requery.meta.Type;
import io.requery.meta.TypeBuilder;
import io.requery.proxy.EntityProxy;
import io.requery.proxy.IntProperty;
import io.requery.proxy.LongProperty;
import io.requery.proxy.PreInsertListener;
import io.requery.proxy.PreUpdateListener;
import io.requery.proxy.Property;
import io.requery.proxy.PropertyState;
import io.requery.util.function.Function;
import io.requery.util.function.Supplier;
import java.util.Map;

public class AnalyticsEvent extends AbstractAnalyticsEvent implements Persistable {
    public static final Type<AnalyticsEvent> $TYPE = new TypeBuilder(AnalyticsEvent.class, "AnalyticsEvent").setBaseType(AbstractAnalyticsEvent.class).setCacheable(true).setImmutable(false).setReadOnly(false).setStateless(false).setView(false).setFactory(new Supplier<AnalyticsEvent>() {
        public AnalyticsEvent get() {
            return new AnalyticsEvent();
        }
    }).setProxyProvider(new Function<AnalyticsEvent, EntityProxy<AnalyticsEvent>>() {
        public EntityProxy<AnalyticsEvent> apply(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.$proxy;
        }
    }).addAttribute(ATTEMPTS_MADE).addAttribute(PRIORITY).addAttribute(PARAMETERS).addAttribute(CREATE_TIME).addAttribute(UPDATE_TIME).addAttribute(TYPE).addAttribute(KEY).build();
    public static final QueryAttribute<AnalyticsEvent, Integer> ATTEMPTS_MADE = new AttributeBuilder("attemptsMade", Integer.TYPE).setProperty(new IntProperty<AnalyticsEvent>() {
        public Integer get(AnalyticsEvent analyticsEvent) {
            return Integer.valueOf(analyticsEvent.attemptsMade);
        }

        public void set(AnalyticsEvent analyticsEvent, Integer num) {
            analyticsEvent.attemptsMade = num.intValue();
        }

        public int getInt(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.attemptsMade;
        }

        public void setInt(AnalyticsEvent analyticsEvent, int i) {
            analyticsEvent.attemptsMade = i;
        }
    }).setPropertyName("attemptsMade").setPropertyState(new Property<AnalyticsEvent, PropertyState>() {
        public PropertyState get(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.$attemptsMade_state;
        }

        public void set(AnalyticsEvent analyticsEvent, PropertyState propertyState) {
            analyticsEvent.$attemptsMade_state = propertyState;
        }
    }).setGenerated(false).setReadOnly(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<AnalyticsEvent, Long> CREATE_TIME = new AttributeBuilder("createTime", Long.TYPE).setProperty(new LongProperty<AnalyticsEvent>() {
        public Long get(AnalyticsEvent analyticsEvent) {
            return Long.valueOf(analyticsEvent.createTime);
        }

        public void set(AnalyticsEvent analyticsEvent, Long l) {
            analyticsEvent.createTime = l.longValue();
        }

        public long getLong(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.createTime;
        }

        public void setLong(AnalyticsEvent analyticsEvent, long j) {
            analyticsEvent.createTime = j;
        }
    }).setPropertyName("createTime").setPropertyState(new Property<AnalyticsEvent, PropertyState>() {
        public PropertyState get(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.$createTime_state;
        }

        public void set(AnalyticsEvent analyticsEvent, PropertyState propertyState) {
            analyticsEvent.$createTime_state = propertyState;
        }
    }).setGenerated(false).setReadOnly(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<AnalyticsEvent, Long> KEY = new AttributeBuilder(IpcUtil.KEY_CODE, Long.class).setProperty(new Property<AnalyticsEvent, Long>() {
        public Long get(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.key;
        }

        public void set(AnalyticsEvent analyticsEvent, Long l) {
            analyticsEvent.key = l;
        }
    }).setPropertyName(IpcUtil.KEY_CODE).setPropertyState(new Property<AnalyticsEvent, PropertyState>() {
        public PropertyState get(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.$key_state;
        }

        public void set(AnalyticsEvent analyticsEvent, PropertyState propertyState) {
            analyticsEvent.$key_state = propertyState;
        }
    }).setKey(true).setGenerated(true).setReadOnly(true).setLazy(false).setNullable(true).setUnique(false).build();
    public static final QueryAttribute<AnalyticsEvent, Map<String, String>> PARAMETERS = new AttributeBuilder("parameters", Map.class).setProperty(new Property<AnalyticsEvent, Map<String, String>>() {
        public Map<String, String> get(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.parameters;
        }

        public void set(AnalyticsEvent analyticsEvent, Map<String, String> map) {
            analyticsEvent.parameters = map;
        }
    }).setPropertyName("parameters").setPropertyState(new Property<AnalyticsEvent, PropertyState>() {
        public PropertyState get(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.$parameters_state;
        }

        public void set(AnalyticsEvent analyticsEvent, PropertyState propertyState) {
            analyticsEvent.$parameters_state = propertyState;
        }
    }).setGenerated(false).setReadOnly(false).setLazy(false).setNullable(true).setUnique(false).setConverter(new MapConverter()).build();
    public static final QueryAttribute<AnalyticsEvent, Integer> PRIORITY = new AttributeBuilder(Columns.PRIORITY, Integer.TYPE).setProperty(new IntProperty<AnalyticsEvent>() {
        public Integer get(AnalyticsEvent analyticsEvent) {
            return Integer.valueOf(analyticsEvent.priority);
        }

        public void set(AnalyticsEvent analyticsEvent, Integer num) {
            analyticsEvent.priority = num.intValue();
        }

        public int getInt(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.priority;
        }

        public void setInt(AnalyticsEvent analyticsEvent, int i) {
            analyticsEvent.priority = i;
        }
    }).setPropertyName(Columns.PRIORITY).setPropertyState(new Property<AnalyticsEvent, PropertyState>() {
        public PropertyState get(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.$priority_state;
        }

        public void set(AnalyticsEvent analyticsEvent, PropertyState propertyState) {
            analyticsEvent.$priority_state = propertyState;
        }
    }).setGenerated(false).setReadOnly(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<AnalyticsEvent, String> TYPE = new AttributeBuilder("type", String.class).setProperty(new Property<AnalyticsEvent, String>() {
        public String get(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.type;
        }

        public void set(AnalyticsEvent analyticsEvent, String str) {
            analyticsEvent.type = str;
        }
    }).setPropertyName("type").setPropertyState(new Property<AnalyticsEvent, PropertyState>() {
        public PropertyState get(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.$type_state;
        }

        public void set(AnalyticsEvent analyticsEvent, PropertyState propertyState) {
            analyticsEvent.$type_state = propertyState;
        }
    }).setGenerated(false).setReadOnly(false).setLazy(false).setNullable(true).setUnique(false).build();
    public static final QueryAttribute<AnalyticsEvent, Long> UPDATE_TIME = new AttributeBuilder("updateTime", Long.TYPE).setProperty(new LongProperty<AnalyticsEvent>() {
        public Long get(AnalyticsEvent analyticsEvent) {
            return Long.valueOf(analyticsEvent.updateTime);
        }

        public void set(AnalyticsEvent analyticsEvent, Long l) {
            analyticsEvent.updateTime = l.longValue();
        }

        public long getLong(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.updateTime;
        }

        public void setLong(AnalyticsEvent analyticsEvent, long j) {
            analyticsEvent.updateTime = j;
        }
    }).setPropertyName("updateTime").setPropertyState(new Property<AnalyticsEvent, PropertyState>() {
        public PropertyState get(AnalyticsEvent analyticsEvent) {
            return analyticsEvent.$updateTime_state;
        }

        public void set(AnalyticsEvent analyticsEvent, PropertyState propertyState) {
            analyticsEvent.$updateTime_state = propertyState;
        }
    }).setGenerated(false).setReadOnly(false).setLazy(false).setNullable(false).setUnique(false).build();
    /* access modifiers changed from: private */
    public PropertyState $attemptsMade_state;
    /* access modifiers changed from: private */
    public PropertyState $createTime_state;
    /* access modifiers changed from: private */
    public PropertyState $key_state;
    /* access modifiers changed from: private */
    public PropertyState $parameters_state;
    /* access modifiers changed from: private */
    public PropertyState $priority_state;
    /* access modifiers changed from: private */
    public final transient EntityProxy<AnalyticsEvent> $proxy = new EntityProxy<>(this, $TYPE);
    /* access modifiers changed from: private */
    public PropertyState $type_state;
    /* access modifiers changed from: private */
    public PropertyState $updateTime_state;

    public AnalyticsEvent() {
        this.$proxy.modifyListeners().addPreInsertListener(new PreInsertListener<AnalyticsEvent>() {
            public void preInsert(AnalyticsEvent analyticsEvent) {
                AnalyticsEvent.this.onBeforeInsert();
            }
        });
        this.$proxy.modifyListeners().addPreUpdateListener(new PreUpdateListener<AnalyticsEvent>() {
            public void preUpdate(AnalyticsEvent analyticsEvent) {
                AnalyticsEvent.this.onBeforeUpdate();
            }
        });
    }

    public Long getKey() {
        return (Long) this.$proxy.get(KEY);
    }

    public Map<String, String> getParameters() {
        return (Map) this.$proxy.get(PARAMETERS);
    }

    public void setParameters(Map<String, String> map) {
        this.$proxy.set(PARAMETERS, map);
    }

    public long getCreateTime() {
        return ((Long) this.$proxy.get(CREATE_TIME)).longValue();
    }

    public void setCreateTime(long j) {
        this.$proxy.set(CREATE_TIME, Long.valueOf(j));
    }

    public long getUpdateTime() {
        return ((Long) this.$proxy.get(UPDATE_TIME)).longValue();
    }

    public void setUpdateTime(long j) {
        this.$proxy.set(UPDATE_TIME, Long.valueOf(j));
    }

    public int getAttemptsMade() {
        return ((Integer) this.$proxy.get(ATTEMPTS_MADE)).intValue();
    }

    public void setAttemptsMade(int i) {
        this.$proxy.set(ATTEMPTS_MADE, Integer.valueOf(i));
    }

    public int getPriority() {
        return ((Integer) this.$proxy.get(PRIORITY)).intValue();
    }

    public void setPriority(int i) {
        this.$proxy.set(PRIORITY, Integer.valueOf(i));
    }

    public String getType() {
        return (String) this.$proxy.get(TYPE);
    }

    public void setType(String str) {
        this.$proxy.set(TYPE, str);
    }

    public boolean equals(Object obj) {
        return (obj instanceof AnalyticsEvent) && ((AnalyticsEvent) obj).$proxy.equals(this.$proxy);
    }

    public int hashCode() {
        return this.$proxy.hashCode();
    }

    public String toString() {
        return this.$proxy.toString();
    }
}
