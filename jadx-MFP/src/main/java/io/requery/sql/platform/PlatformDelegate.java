package io.requery.sql.platform;

import io.requery.query.Expression;
import io.requery.query.element.LimitedElement;
import io.requery.query.element.OrderByElement;
import io.requery.sql.GeneratedColumnDefinition;
import io.requery.sql.Mapping;
import io.requery.sql.Platform;
import io.requery.sql.VersionColumnDefinition;
import io.requery.sql.gen.Generator;
import java.sql.Connection;
import java.util.Map;

public class PlatformDelegate implements Platform {
    private final Platform platform;

    public PlatformDelegate(Connection connection) {
        this.platform = new PlatformFromConnection().apply(connection);
    }

    public void addMappings(Mapping mapping) {
        this.platform.addMappings(mapping);
    }

    public boolean supportsIfExists() {
        return this.platform.supportsIfExists();
    }

    public boolean supportsInlineForeignKeyReference() {
        return this.platform.supportsInlineForeignKeyReference();
    }

    public boolean supportsAddingConstraint() {
        return this.platform.supportsAddingConstraint();
    }

    public boolean supportsGeneratedColumnsInPrepareStatement() {
        return this.platform.supportsGeneratedColumnsInPrepareStatement();
    }

    public boolean supportsOnUpdateCascade() {
        return this.platform.supportsOnUpdateCascade();
    }

    public boolean supportsUpsert() {
        return this.platform.supportsUpsert();
    }

    public GeneratedColumnDefinition generatedColumnDefinition() {
        return this.platform.generatedColumnDefinition();
    }

    public Generator<LimitedElement> limitGenerator() {
        return this.platform.limitGenerator();
    }

    public VersionColumnDefinition versionColumnDefinition() {
        return this.platform.versionColumnDefinition();
    }

    public Generator<Map<Expression<?>, Object>> upsertGenerator() {
        return this.platform.upsertGenerator();
    }

    public Generator<OrderByElement> orderByGenerator() {
        return this.platform.orderByGenerator();
    }

    public String toString() {
        return this.platform.toString();
    }
}
