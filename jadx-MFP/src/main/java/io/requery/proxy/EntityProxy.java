package io.requery.proxy;

import io.requery.meta.Attribute;
import io.requery.meta.Type;
import io.requery.util.Objects;
import java.util.LinkedHashMap;

public class EntityProxy<E> implements EntityStateListener, Gettable<E>, Settable<E> {
    private final E entity;
    private Object key;
    private CompositeEntityStateListener<E> listeners;
    private PropertyLoader<E> loader;
    private boolean regenerateKey;
    private final boolean stateless;
    private final Type<E> type;

    public Object syncObject() {
        return this;
    }

    public EntityProxy(E e, Type<E> type2) {
        this.entity = e;
        this.type = type2;
        this.stateless = type2.isStateless();
    }

    private PropertyState loadProperty(Attribute<E, ?> attribute) {
        if (this.stateless) {
            return null;
        }
        PropertyState state = getState(attribute);
        if (state == PropertyState.FETCH) {
            PropertyLoader<E> propertyLoader = this.loader;
            if (propertyLoader != null) {
                propertyLoader.load(this.entity, this, attribute);
            }
        }
        return state;
    }

    public <V> V get(Attribute<E, V> attribute) {
        return get(attribute, true);
    }

    public <V> V get(Attribute<E, V> attribute, boolean z) {
        PropertyState loadProperty = z ? loadProperty(attribute) : getState(attribute);
        V v = attribute.getProperty().get(this.entity);
        if (v != null) {
            return v;
        }
        if ((loadProperty != PropertyState.FETCH && !this.stateless) || attribute.getInitializer() == null) {
            return v;
        }
        V initialize = attribute.getInitializer().initialize(this, attribute);
        set(attribute, initialize, PropertyState.FETCH);
        return initialize;
    }

    public int getInt(Attribute<E, Integer> attribute) {
        IntProperty intProperty = (IntProperty) attribute.getProperty();
        loadProperty(attribute);
        return intProperty.getInt(this.entity);
    }

    public long getLong(Attribute<E, Long> attribute) {
        LongProperty longProperty = (LongProperty) attribute.getProperty();
        loadProperty(attribute);
        return longProperty.getLong(this.entity);
    }

    public short getShort(Attribute<E, Short> attribute) {
        ShortProperty shortProperty = (ShortProperty) attribute.getProperty();
        loadProperty(attribute);
        return shortProperty.getShort(this.entity);
    }

    public byte getByte(Attribute<E, Byte> attribute) {
        ByteProperty byteProperty = (ByteProperty) attribute.getProperty();
        loadProperty(attribute);
        return byteProperty.getByte(this.entity);
    }

    public float getFloat(Attribute<E, Float> attribute) {
        FloatProperty floatProperty = (FloatProperty) attribute.getProperty();
        loadProperty(attribute);
        return floatProperty.getFloat(this.entity);
    }

    public double getDouble(Attribute<E, Double> attribute) {
        DoubleProperty doubleProperty = (DoubleProperty) attribute.getProperty();
        loadProperty(attribute);
        return doubleProperty.getDouble(this.entity);
    }

    public boolean getBoolean(Attribute<E, Boolean> attribute) {
        BooleanProperty booleanProperty = (BooleanProperty) attribute.getProperty();
        loadProperty(attribute);
        return booleanProperty.getBoolean(this.entity);
    }

    public <V> void set(Attribute<E, V> attribute, V v) {
        set(attribute, v, PropertyState.MODIFIED);
    }

    public <V> void set(Attribute<E, V> attribute, V v, PropertyState propertyState) {
        attribute.getProperty().set(this.entity, v);
        setState(attribute, propertyState);
        checkRegenerateKey(attribute);
    }

    public void setObject(Attribute<E, ?> attribute, Object obj, PropertyState propertyState) {
        attribute.getProperty().set(this.entity, obj);
        setState(attribute, propertyState);
        checkRegenerateKey(attribute);
    }

    public void setInt(Attribute<E, Integer> attribute, int i, PropertyState propertyState) {
        ((IntProperty) attribute.getProperty()).setInt(this.entity, i);
        setState(attribute, propertyState);
        checkRegenerateKey(attribute);
    }

    public void setLong(Attribute<E, Long> attribute, long j, PropertyState propertyState) {
        ((LongProperty) attribute.getProperty()).setLong(this.entity, j);
        setState(attribute, propertyState);
        checkRegenerateKey(attribute);
    }

    public void setShort(Attribute<E, Short> attribute, short s, PropertyState propertyState) {
        ((ShortProperty) attribute.getProperty()).setShort(this.entity, s);
        setState(attribute, propertyState);
    }

    public void setByte(Attribute<E, Byte> attribute, byte b, PropertyState propertyState) {
        ((ByteProperty) attribute.getProperty()).setByte(this.entity, b);
        setState(attribute, propertyState);
    }

    public void setFloat(Attribute<E, Float> attribute, float f, PropertyState propertyState) {
        ((FloatProperty) attribute.getProperty()).setFloat(this.entity, f);
        setState(attribute, propertyState);
    }

    public void setDouble(Attribute<E, Double> attribute, double d, PropertyState propertyState) {
        ((DoubleProperty) attribute.getProperty()).setDouble(this.entity, d);
        setState(attribute, propertyState);
    }

    public void setBoolean(Attribute<E, Boolean> attribute, boolean z, PropertyState propertyState) {
        ((BooleanProperty) attribute.getProperty()).setBoolean(this.entity, z);
        setState(attribute, propertyState);
    }

    private void checkRegenerateKey(Attribute<E, ?> attribute) {
        if (attribute.isKey()) {
            this.regenerateKey = true;
        }
    }

    public void setState(Attribute<E, ?> attribute, PropertyState propertyState) {
        if (!this.stateless) {
            attribute.getPropertyState().set(this.entity, propertyState);
        }
    }

    public PropertyState getState(Attribute<E, ?> attribute) {
        if (this.stateless) {
            return null;
        }
        PropertyState propertyState = (PropertyState) attribute.getPropertyState().get(this.entity);
        if (propertyState == null) {
            propertyState = PropertyState.FETCH;
        }
        return propertyState;
    }

    public Object key() {
        if (this.regenerateKey || this.key == null) {
            if (this.type.getSingleKeyAttribute() != null) {
                this.key = getKey(this.type.getSingleKeyAttribute());
            } else if (this.type.getKeyAttributes().size() > 1) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(this.type.getKeyAttributes().size());
                for (Attribute attribute : this.type.getKeyAttributes()) {
                    linkedHashMap.put(attribute, getKey(attribute));
                }
                this.key = new CompositeKey(linkedHashMap);
            } else {
                this.key = this;
            }
        }
        return this.key;
    }

    public Object getKey(Attribute<E, ?> attribute) {
        if (!attribute.isAssociation()) {
            return get(attribute, false);
        }
        Attribute attribute2 = (Attribute) attribute.getReferencedAttribute().get();
        Object obj = get(attribute, false);
        Object obj2 = null;
        if (obj == null) {
            return null;
        }
        EntityProxy entityProxy = (EntityProxy) attribute2.getDeclaringType().getProxyProvider().apply(obj);
        if (entityProxy != null) {
            obj2 = entityProxy.get(attribute2, false);
        }
        return obj2;
    }

    public boolean isLinked() {
        boolean z;
        synchronized (syncObject()) {
            z = this.loader != null;
        }
        return z;
    }

    public void link(PropertyLoader<E> propertyLoader) {
        synchronized (syncObject()) {
            this.loader = propertyLoader;
        }
    }

    public void unlink() {
        synchronized (syncObject()) {
            this.loader = null;
        }
    }

    public Type<E> type() {
        return this.type;
    }

    public EntityStateEventListenable<E> modifyListeners() {
        if (this.listeners == null) {
            this.listeners = new CompositeEntityStateListener<>(this.entity);
        }
        return this.listeners;
    }

    private EntityStateListener stateListener() {
        CompositeEntityStateListener<E> compositeEntityStateListener = this.listeners;
        return compositeEntityStateListener == null ? EntityStateListener.EMPTY : compositeEntityStateListener;
    }

    public void preUpdate() {
        stateListener().preUpdate();
    }

    public void postUpdate() {
        stateListener().postUpdate();
    }

    public void preInsert() {
        stateListener().preInsert();
    }

    public void postInsert() {
        stateListener().postInsert();
    }

    public void postLoad() {
        stateListener().postLoad();
    }

    public boolean equals(Object obj) {
        if (obj instanceof EntityProxy) {
            EntityProxy entityProxy = (EntityProxy) obj;
            if (entityProxy.entity.getClass().equals(this.entity.getClass())) {
                for (Attribute attribute : this.type.getAttributes()) {
                    if (!attribute.isAssociation() && !Objects.equals(get(attribute, false), entityProxy.get(attribute, false))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 31;
        for (Attribute attribute : this.type.getAttributes()) {
            if (!attribute.isAssociation()) {
                i = (i * 31) + Objects.hashCode(get(attribute, false));
            }
        }
        return i;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.type.getName());
        sb.append(" [");
        int i = 0;
        for (Attribute attribute : this.type.getAttributes()) {
            if (i > 0) {
                sb.append(", ");
            }
            Object obj = get(attribute, false);
            if (obj == null) {
                str = "null";
            } else {
                str = obj.toString();
            }
            sb.append(str);
            i++;
        }
        sb.append("]");
        return sb.toString();
    }
}
