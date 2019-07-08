package io.requery.meta;

import io.requery.util.ClassMap;
import io.requery.util.Objects;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

final class ImmutableEntityModel implements EntityModel {
    private final Map<Class<?>, Type<?>> map;
    private final String name;

    ImmutableEntityModel(String str, Set<Type<?>> set) {
        this.name = str;
        ClassMap classMap = new ClassMap();
        for (Type type : set) {
            classMap.put(type.getClassType(), type);
            classMap.put(type.getBaseType(), type);
        }
        this.map = Collections.unmodifiableMap(classMap);
    }

    public String getName() {
        return this.name;
    }

    public <T> Type<T> typeOf(Class<? extends T> cls) {
        Type<T> type = (Type) this.map.get(cls);
        if (type != null) {
            return type;
        }
        throw new NotMappedException();
    }

    public <T> boolean containsTypeOf(Class<? extends T> cls) {
        return this.map.containsKey(cls);
    }

    public Set<Type<?>> getTypes() {
        return new LinkedHashSet(this.map.values());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(" : ");
        sb.append(this.map.keySet().toString());
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof EntityModel)) {
            return false;
        }
        EntityModel entityModel = (EntityModel) obj;
        if (Objects.equals(this.name, entityModel.getName()) && getTypes().equals(entityModel.getTypes())) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hash(this.name, this.map);
    }
}
