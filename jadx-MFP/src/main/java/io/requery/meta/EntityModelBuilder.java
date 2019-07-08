package io.requery.meta;

import io.requery.util.Objects;
import java.util.HashSet;
import java.util.Set;

public class EntityModelBuilder {
    private final String name;
    private final Set<Type<?>> types = new HashSet();

    public EntityModelBuilder(String str) {
        this.name = str;
    }

    public EntityModelBuilder addType(Type<?> type) {
        this.types.add(Objects.requireNotNull(type));
        return this;
    }

    public EntityModel build() {
        return new ImmutableEntityModel(this.name, this.types);
    }
}
