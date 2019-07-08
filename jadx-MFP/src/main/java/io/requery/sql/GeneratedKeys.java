package io.requery.sql;

import io.requery.meta.Attribute;
import io.requery.proxy.EntityProxy;
import io.requery.proxy.PropertyState;
import io.requery.proxy.Settable;
import java.util.ArrayList;

class GeneratedKeys<E> extends ArrayList<Object> implements Settable<E> {
    private EntityProxy<E> proxy;

    GeneratedKeys() {
    }

    GeneratedKeys(EntityProxy<E> entityProxy) {
        this.proxy = entityProxy;
    }

    public void setObject(Attribute<E, ?> attribute, Object obj, PropertyState propertyState) {
        EntityProxy<E> entityProxy = this.proxy;
        if (entityProxy != null) {
            entityProxy.setObject(attribute, obj, propertyState);
        }
        add(obj);
    }

    public void setBoolean(Attribute<E, Boolean> attribute, boolean z, PropertyState propertyState) {
        EntityProxy<E> entityProxy = this.proxy;
        if (entityProxy != null) {
            entityProxy.setBoolean(attribute, z, propertyState);
        }
        add(Boolean.valueOf(z));
    }

    public void setDouble(Attribute<E, Double> attribute, double d, PropertyState propertyState) {
        EntityProxy<E> entityProxy = this.proxy;
        if (entityProxy != null) {
            entityProxy.setDouble(attribute, d, propertyState);
        }
        add(Double.valueOf(d));
    }

    public void setFloat(Attribute<E, Float> attribute, float f, PropertyState propertyState) {
        EntityProxy<E> entityProxy = this.proxy;
        if (entityProxy != null) {
            entityProxy.setFloat(attribute, f, propertyState);
        }
        add(Float.valueOf(f));
    }

    public void setByte(Attribute<E, Byte> attribute, byte b, PropertyState propertyState) {
        EntityProxy<E> entityProxy = this.proxy;
        if (entityProxy != null) {
            entityProxy.setByte(attribute, b, propertyState);
        }
        add(Byte.valueOf(b));
    }

    public void setShort(Attribute<E, Short> attribute, short s, PropertyState propertyState) {
        EntityProxy<E> entityProxy = this.proxy;
        if (entityProxy != null) {
            entityProxy.setShort(attribute, s, propertyState);
        }
        add(Short.valueOf(s));
    }

    public void setInt(Attribute<E, Integer> attribute, int i, PropertyState propertyState) {
        EntityProxy<E> entityProxy = this.proxy;
        if (entityProxy != null) {
            entityProxy.setInt(attribute, i, propertyState);
        }
        add(Integer.valueOf(i));
    }

    public void setLong(Attribute<E, Long> attribute, long j, PropertyState propertyState) {
        EntityProxy<E> entityProxy = this.proxy;
        if (entityProxy != null) {
            entityProxy.setLong(attribute, j, propertyState);
        }
        add(Long.valueOf(j));
    }
}
