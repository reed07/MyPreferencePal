package io.requery.sql.platform;

import io.requery.meta.Attribute;
import io.requery.meta.Type;
import io.requery.query.Expression;
import io.requery.query.element.LimitedElement;
import io.requery.query.element.OrderByElement;
import io.requery.query.element.QueryElement;
import io.requery.query.function.Function.Name;
import io.requery.query.function.Now;
import io.requery.sql.BaseType;
import io.requery.sql.GeneratedColumnDefinition;
import io.requery.sql.Keyword;
import io.requery.sql.Mapping;
import io.requery.sql.QueryBuilder;
import io.requery.sql.gen.Generator;
import io.requery.sql.gen.OffsetFetchGenerator;
import io.requery.sql.gen.OrderByGenerator;
import io.requery.sql.gen.Output;
import io.requery.sql.gen.UpsertMergeGenerator;
import io.requery.sql.type.PrimitiveBooleanType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class SQLServer extends Generic {
    private final GeneratedColumnDefinition generatedColumnDefinition = new IdentityColumnDefinition();

    private static class BitBooleanType extends BaseType<Boolean> implements PrimitiveBooleanType {
        public Object getIdentifier() {
            return "bit";
        }

        BitBooleanType() {
            super(Boolean.class, -7);
        }

        public Boolean read(ResultSet resultSet, int i) throws SQLException {
            Boolean valueOf = Boolean.valueOf(resultSet.getBoolean(i));
            if (resultSet.wasNull()) {
                return null;
            }
            return valueOf;
        }

        public boolean readBoolean(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getBoolean(i);
        }

        public void writeBoolean(PreparedStatement preparedStatement, int i, boolean z) throws SQLException {
            preparedStatement.setBoolean(i, z);
        }
    }

    private static class IdentityColumnDefinition implements GeneratedColumnDefinition {
        public boolean postFixPrimaryKey() {
            return false;
        }

        public boolean skipTypeIdentifier() {
            return false;
        }

        private IdentityColumnDefinition() {
        }

        public void appendGeneratedSequence(QueryBuilder queryBuilder, Attribute attribute) {
            queryBuilder.keyword(Keyword.IDENTITY);
            queryBuilder.openParenthesis().value(Integer.valueOf(1)).comma().value(Integer.valueOf(1)).closeParenthesis();
        }
    }

    private static class MergeGenerator extends UpsertMergeGenerator {
        private MergeGenerator() {
        }

        public void write(Output output, Map<Expression<?>, Object> map) {
            super.write(output, map);
            output.builder().append(";");
        }
    }

    private static class OrderByOffsetFetchLimit extends OffsetFetchGenerator {
        private OrderByOffsetFetchLimit() {
        }

        public void write(QueryBuilder queryBuilder, Integer num, Integer num2) {
            super.write(queryBuilder, num, Integer.valueOf(num2 == null ? 0 : num2.intValue()));
        }
    }

    private class OrderByWithLimitGenerator extends OrderByGenerator {
        private OrderByWithLimitGenerator() {
        }

        private void forceOrderBy(QueryElement<?> queryElement) {
            if (queryElement.getLimit() == null) {
                return;
            }
            if (queryElement.getOrderByExpressions() == null || queryElement.getOrderByExpressions().isEmpty()) {
                Set entityTypes = queryElement.entityTypes();
                if (entityTypes != null && !entityTypes.isEmpty()) {
                    for (Attribute attribute : ((Type) entityTypes.iterator().next()).getAttributes()) {
                        if (attribute.isKey()) {
                            queryElement.orderBy((Expression) attribute);
                            return;
                        }
                    }
                }
            }
        }

        public void write(Output output, OrderByElement orderByElement) {
            if (orderByElement instanceof QueryElement) {
                forceOrderBy((QueryElement) orderByElement);
            }
            super.write(output, orderByElement);
        }
    }

    public boolean supportsIfExists() {
        return false;
    }

    public void addMappings(Mapping mapping) {
        super.addMappings(mapping);
        mapping.replaceType(16, new BitBooleanType());
        mapping.aliasFunction(new Name("getutcdate"), Now.class);
    }

    public GeneratedColumnDefinition generatedColumnDefinition() {
        return this.generatedColumnDefinition;
    }

    public Generator<LimitedElement> limitGenerator() {
        return new OrderByOffsetFetchLimit();
    }

    public Generator<Map<Expression<?>, Object>> upsertGenerator() {
        return new MergeGenerator();
    }

    public Generator<OrderByElement> orderByGenerator() {
        return new OrderByWithLimitGenerator();
    }
}
