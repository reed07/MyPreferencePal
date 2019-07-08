package io.requery.sql.platform;

import io.requery.query.Expression;
import io.requery.query.element.LimitedElement;
import io.requery.query.element.OrderByElement;
import io.requery.sql.GeneratedColumnDefinition;
import io.requery.sql.IdentityColumnDefinition;
import io.requery.sql.Mapping;
import io.requery.sql.Platform;
import io.requery.sql.UserVersionColumnDefinition;
import io.requery.sql.VersionColumnDefinition;
import io.requery.sql.gen.Generator;
import io.requery.sql.gen.LimitGenerator;
import io.requery.sql.gen.OffsetFetchGenerator;
import io.requery.sql.gen.OrderByGenerator;
import io.requery.sql.gen.UpsertMergeGenerator;
import java.util.Map;

public class Generic implements Platform {
    private final GeneratedColumnDefinition generatedColumnDefinition = new IdentityColumnDefinition();
    private final LimitGenerator limitDefinition = new OffsetFetchGenerator();
    private final Generator<OrderByElement> orderByDefinition = new OrderByGenerator();
    private final Generator<Map<Expression<?>, Object>> upsertDefinition = new UpsertMergeGenerator();
    private final VersionColumnDefinition versionColumnDefinition = new UserVersionColumnDefinition();

    public void addMappings(Mapping mapping) {
    }

    public boolean supportsAddingConstraint() {
        return true;
    }

    public boolean supportsGeneratedColumnsInPrepareStatement() {
        return true;
    }

    public boolean supportsIfExists() {
        return true;
    }

    public boolean supportsInlineForeignKeyReference() {
        return false;
    }

    public boolean supportsOnUpdateCascade() {
        return true;
    }

    public boolean supportsUpsert() {
        return true;
    }

    public GeneratedColumnDefinition generatedColumnDefinition() {
        return this.generatedColumnDefinition;
    }

    public Generator<LimitedElement> limitGenerator() {
        return this.limitDefinition;
    }

    public VersionColumnDefinition versionColumnDefinition() {
        return this.versionColumnDefinition;
    }

    public Generator<Map<Expression<?>, Object>> upsertGenerator() {
        return this.upsertDefinition;
    }

    public Generator<OrderByElement> orderByGenerator() {
        return this.orderByDefinition;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
