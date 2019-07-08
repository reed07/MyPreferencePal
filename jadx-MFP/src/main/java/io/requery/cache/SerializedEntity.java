package io.requery.cache;

import io.requery.meta.Attribute;
import io.requery.meta.Type;
import io.requery.proxy.EntityProxy;
import io.requery.proxy.PropertyState;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class SerializedEntity<E> implements Serializable {
    private transient E entity;
    private final Class<E> entityClass;

    public SerializedEntity(Class<E> cls, E e) {
        this.entityClass = cls;
        this.entity = e;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        Type type = SerializationContext.getType(this.entityClass);
        this.entity = type.getFactory().get();
        EntityProxy entityProxy = (EntityProxy) type.getProxyProvider().apply(this.entity);
        for (Attribute attribute : type.getAttributes()) {
            if (attribute.isAssociation()) {
                entityProxy.setState(attribute, PropertyState.FETCH);
            } else {
                entityProxy.setObject(attribute, objectInputStream.readObject(), PropertyState.LOADED);
            }
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Type type = SerializationContext.getType(this.entityClass);
        EntityProxy entityProxy = (EntityProxy) type.getProxyProvider().apply(this.entity);
        for (Attribute attribute : type.getAttributes()) {
            if (!attribute.isAssociation()) {
                objectOutputStream.writeObject(entityProxy.get(attribute, false));
            }
        }
    }

    public E getEntity() {
        return this.entity;
    }
}
