package io.requery.sql.platform;

import io.requery.meta.Attribute;
import io.requery.query.Expression;
import io.requery.query.function.Function.Name;
import io.requery.query.function.Random;
import io.requery.sql.AutoIncrementColumnDefinition;
import io.requery.sql.GeneratedColumnDefinition;
import io.requery.sql.Keyword;
import io.requery.sql.Mapping;
import io.requery.sql.QueryBuilder;
import io.requery.sql.QueryBuilder.Appender;
import io.requery.sql.gen.Generator;
import io.requery.sql.gen.LimitGenerator;
import io.requery.sql.gen.Output;
import java.util.Map;

public class MySQL extends Generic {
    private final AutoIncrementColumnDefinition autoIncrementColumn = new AutoIncrementColumnDefinition();

    private static class UpsertOnDuplicateKeyUpdate implements Generator<Map<Expression<?>, Object>> {
        private UpsertOnDuplicateKeyUpdate() {
        }

        public void write(final Output output, final Map<Expression<?>, Object> map) {
            output.builder().keyword(Keyword.INSERT, Keyword.INTO).tableNames(map.keySet()).openParenthesis().commaSeparatedExpressions(map.keySet()).closeParenthesis().space().keyword(Keyword.VALUES).openParenthesis().commaSeparated((Iterable<? extends T>) map.keySet(), (Appender<T>) new Appender<Expression<?>>() {
                public void append(QueryBuilder queryBuilder, Expression expression) {
                    queryBuilder.append("?");
                    output.parameters().add(expression, map.get(expression));
                }
            }).closeParenthesis().space().keyword(Keyword.ON, Keyword.DUPLICATE, Keyword.KEY, Keyword.UPDATE).commaSeparated((Iterable<? extends T>) map.keySet(), (Appender<T>) new Appender<Expression<?>>() {
                public void append(QueryBuilder queryBuilder, Expression<?> expression) {
                    Attribute attribute = (Attribute) expression;
                    queryBuilder.attribute(attribute).append("=").append("values").openParenthesis().attribute(attribute).closeParenthesis().space();
                }
            });
        }
    }

    public void addMappings(Mapping mapping) {
        mapping.aliasFunction(new Name("rand"), Random.class);
    }

    public GeneratedColumnDefinition generatedColumnDefinition() {
        return this.autoIncrementColumn;
    }

    public LimitGenerator limitGenerator() {
        return new LimitGenerator();
    }

    public Generator<Map<Expression<?>, Object>> upsertGenerator() {
        return new UpsertOnDuplicateKeyUpdate();
    }
}
