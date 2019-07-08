package io.requery.proxy;

import io.requery.meta.Attribute;
import io.requery.query.Result;
import io.requery.util.ObservableList;
import io.requery.util.ObservableSet;
import io.requery.util.function.Supplier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CollectionInitializer<E, V> implements Initializer<E, V>, QueryInitializer<E, V> {
    public V initialize(EntityProxy<E> entityProxy, Attribute<E, V> attribute) {
        return initialize(entityProxy, attribute, null);
    }

    public <U> V initialize(EntityProxy<E> entityProxy, Attribute<E, V> attribute, Supplier<? extends Result<U>> supplier) {
        Result result;
        Object obj;
        Class<List> classType = attribute.getClassType();
        CollectionChanges collectionChanges = new CollectionChanges(entityProxy, attribute);
        if (supplier == null) {
            result = null;
        } else {
            result = (Result) supplier.get();
        }
        if (classType == Set.class) {
            Set hashSet = attribute.getOrderByAttribute() == null ? new HashSet() : new LinkedHashSet();
            if (result != null) {
                result.collect(hashSet);
            }
            obj = new ObservableSet(hashSet, collectionChanges);
        } else if (classType == List.class) {
            ArrayList arrayList = new ArrayList();
            if (result != null) {
                result.collect(arrayList);
            }
            obj = new ObservableList(arrayList, collectionChanges);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unsupported collection type ");
            sb.append(classType);
            throw new IllegalStateException(sb.toString());
        }
        return attribute.getClassType().cast(obj);
    }
}
