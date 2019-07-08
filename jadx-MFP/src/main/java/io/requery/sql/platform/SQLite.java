package io.requery.sql.platform;

import io.requery.ReferentialAction;
import io.requery.meta.Attribute;
import io.requery.meta.Type;
import io.requery.query.Expression;
import io.requery.query.function.Function.Name;
import io.requery.query.function.Now;
import io.requery.sql.AutoIncrementColumnDefinition;
import io.requery.sql.BasicType;
import io.requery.sql.GeneratedColumnDefinition;
import io.requery.sql.Keyword;
import io.requery.sql.Mapping;
import io.requery.sql.QueryBuilder;
import io.requery.sql.QueryBuilder.Appender;
import io.requery.sql.gen.Generator;
import io.requery.sql.gen.LimitGenerator;
import io.requery.sql.gen.Output;
import io.requery.sql.type.PrimitiveLongType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class SQLite extends Generic {
    private final AutoIncrementColumnDefinition autoIncrementColumn = new AutoIncrementColumnDefinition("autoincrement");

    protected static class InsertOrReplace implements Generator<Map<Expression<?>, Object>> {
        protected InsertOrReplace() {
        }

        public void write(final Output output, final Map<Expression<?>, Object> map) {
            QueryBuilder builder = output.builder();
            Type declaringType = ((Attribute) map.keySet().iterator().next()).getDeclaringType();
            builder.keyword(Keyword.INSERT, Keyword.OR, Keyword.REPLACE, Keyword.INTO).tableNames(map.keySet()).openParenthesis().commaSeparated((Iterable<? extends T>) map.keySet(), (Appender<T>) new Appender<Expression<?>>() {
                public void append(QueryBuilder queryBuilder, Expression<?> expression) {
                    if (expression instanceof Attribute) {
                        Attribute attribute = (Attribute) expression;
                        if (!attribute.isForeignKey() || attribute.getDeleteAction() != ReferentialAction.CASCADE) {
                            queryBuilder.attribute(attribute);
                            return;
                        }
                        throw new IllegalStateException("replace would cause cascade");
                    }
                }
            }).closeParenthesis().space();
            builder.keyword(Keyword.SELECT).commaSeparated((Iterable<? extends T>) map.keySet(), (Appender<T>) new Appender<Expression<?>>() {
                public void append(QueryBuilder queryBuilder, Expression<?> expression) {
                    queryBuilder.aliasAttribute("next", (Attribute) expression);
                }
            }).keyword(Keyword.FROM).openParenthesis().keyword(Keyword.SELECT).commaSeparated((Iterable<? extends T>) map.keySet(), (Appender<T>) new Appender<Expression<?>>() {
                public void append(QueryBuilder queryBuilder, Expression expression) {
                    queryBuilder.append("? ").keyword(Keyword.AS).append(expression.getName());
                    output.parameters().add(expression, map.get(expression));
                }
            }).closeParenthesis().space().keyword(Keyword.AS).append("next").space().keyword(Keyword.LEFT, Keyword.JOIN).openParenthesis().keyword(Keyword.SELECT).commaSeparatedExpressions(map.keySet()).keyword(Keyword.FROM).tableName(declaringType.getName()).closeParenthesis().space().keyword(Keyword.AS).append("prev").space().keyword(Keyword.ON).aliasAttribute("prev", declaringType.getSingleKeyAttribute()).append(" = ").aliasAttribute("next", declaringType.getSingleKeyAttribute());
        }
    }

    private static class LongType extends BasicType<Long> implements PrimitiveLongType {
        LongType(Class<Long> cls) {
            super(cls, 4);
        }

        public Long fromResult(ResultSet resultSet, int i) throws SQLException {
            return Long.valueOf(resultSet.getLong(i));
        }

        public Keyword getIdentifier() {
            return Keyword.INTEGER;
        }

        public long readLong(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getLong(i);
        }

        public void writeLong(PreparedStatement preparedStatement, int i, long j) throws SQLException {
            preparedStatement.setLong(i, j);
        }
    }

    public boolean supportsAddingConstraint() {
        return false;
    }

    public boolean supportsUpsert() {
        return false;
    }

    public void addMappings(Mapping mapping) {
        super.addMappings(mapping);
        mapping.putType(Long.TYPE, new LongType(Long.TYPE));
        mapping.putType(Long.class, new LongType(Long.class));
        mapping.aliasFunction(new Name("date('now')", true), Now.class);
    }

    public GeneratedColumnDefinition generatedColumnDefinition() {
        return this.autoIncrementColumn;
    }

    public LimitGenerator limitGenerator() {
        return new LimitGenerator();
    }

    public Generator<Map<Expression<?>, Object>> upsertGenerator() {
        return new InsertOrReplace();
    }
}
