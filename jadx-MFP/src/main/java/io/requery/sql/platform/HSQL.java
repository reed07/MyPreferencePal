package io.requery.sql.platform;

import io.requery.query.element.LimitedElement;
import io.requery.query.function.Function.Name;
import io.requery.query.function.Random;
import io.requery.sql.Mapping;
import io.requery.sql.gen.Generator;
import io.requery.sql.gen.LimitGenerator;

public class HSQL extends Generic {
    public boolean supportsGeneratedColumnsInPrepareStatement() {
        return false;
    }

    public void addMappings(Mapping mapping) {
        mapping.aliasFunction(new Name("rand"), Random.class);
    }

    public Generator<LimitedElement> limitGenerator() {
        return new LimitGenerator();
    }
}
