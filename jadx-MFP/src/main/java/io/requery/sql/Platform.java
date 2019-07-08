package io.requery.sql;

import io.requery.query.Expression;
import io.requery.query.element.LimitedElement;
import io.requery.query.element.OrderByElement;
import io.requery.sql.gen.Generator;
import java.util.Map;

public interface Platform {
    void addMappings(Mapping mapping);

    GeneratedColumnDefinition generatedColumnDefinition();

    Generator<LimitedElement> limitGenerator();

    Generator<OrderByElement> orderByGenerator();

    boolean supportsAddingConstraint();

    boolean supportsGeneratedColumnsInPrepareStatement();

    boolean supportsIfExists();

    boolean supportsInlineForeignKeyReference();

    boolean supportsOnUpdateCascade();

    boolean supportsUpsert();

    Generator<Map<Expression<?>, Object>> upsertGenerator();

    VersionColumnDefinition versionColumnDefinition();
}
