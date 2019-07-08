package io.requery.sql.platform;

import io.requery.meta.Attribute;
import io.requery.meta.Type;
import io.requery.query.Expression;
import io.requery.sql.AutoIncrementColumnDefinition;
import io.requery.sql.GeneratedColumnDefinition;
import io.requery.sql.Keyword;
import io.requery.sql.QueryBuilder;
import io.requery.sql.QueryBuilder.Appender;
import io.requery.sql.gen.Generator;
import io.requery.sql.gen.LimitGenerator;
import io.requery.sql.gen.Output;
import java.util.Map;
import java.util.Set;

public class H2 extends Generic {
    private final AutoIncrementColumnDefinition autoIncrementColumn = new AutoIncrementColumnDefinition();

    private static class UpsertMergeDual implements Generator<Map<Expression<?>, Object>> {
        private UpsertMergeDual() {
        }

        public void write(final Output output, final Map<Expression<?>, Object> map) {
            QueryBuilder builder = output.builder();
            Type declaringType = ((Attribute) map.keySet().iterator().next()).getDeclaringType();
            Set keyAttributes = declaringType.getKeyAttributes();
            if (keyAttributes.isEmpty()) {
                keyAttributes = declaringType.getAttributes();
            }
            builder.keyword(Keyword.MERGE).keyword(Keyword.INTO).tableNames(map.keySet()).openParenthesis().commaSeparatedExpressions(map.keySet()).closeParenthesis().space().keyword(Keyword.KEY).openParenthesis().commaSeparatedAttributes(keyAttributes).closeParenthesis().space().keyword(Keyword.SELECT).commaSeparated((Iterable<? extends T>) map.keySet(), (Appender<T>) new Appender<Expression<?>>() {
                public void append(QueryBuilder queryBuilder, Expression expression) {
                    queryBuilder.append("?");
                    output.parameters().add(expression, map.get(expression));
                }
            }).space().keyword(Keyword.FROM).append("DUAL");
        }
    }

    public GeneratedColumnDefinition generatedColumnDefinition() {
        return this.autoIncrementColumn;
    }

    public LimitGenerator limitGenerator() {
        return new LimitGenerator();
    }

    public Generator<Map<Expression<?>, Object>> upsertGenerator() {
        return new UpsertMergeDual();
    }
}
