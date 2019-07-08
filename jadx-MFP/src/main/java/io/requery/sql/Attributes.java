package io.requery.sql;

import io.requery.meta.Attribute;
import io.requery.meta.QueryAttribute;
import io.requery.proxy.EntityProxy;
import io.requery.util.function.Predicate;
import io.requery.util.function.Supplier;
import java.util.Collection;
import java.util.LinkedHashSet;

final class Attributes {
    Attributes() {
    }

    static <E, V> QueryAttribute<E, V> query(Attribute attribute) {
        if (attribute instanceof Supplier) {
            return get((Supplier) attribute);
        }
        return (QueryAttribute) attribute;
    }

    static <E, V> QueryAttribute<E, V> get(Supplier supplier) {
        return query((Attribute) supplier.get());
    }

    static <E> Attribute<E, ?>[] newArray(int i) {
        return new Attribute[i];
    }

    static <E> Attribute<E, ?>[] toArray(Collection<Attribute<E, ?>> collection, Predicate<Attribute<E, ?>> predicate) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Attribute attribute : collection) {
            if (predicate == null || predicate.test(attribute)) {
                linkedHashSet.add(attribute);
            }
        }
        return (Attribute[]) linkedHashSet.toArray(new Attribute[linkedHashSet.size()]);
    }

    static Object replaceKeyReference(Object obj, Attribute attribute) {
        if (obj == null) {
            return obj;
        }
        QueryAttribute queryAttribute = get(attribute.getReferencedAttribute());
        return ((EntityProxy) queryAttribute.getDeclaringType().getProxyProvider().apply(obj)).get(queryAttribute, false);
    }
}
