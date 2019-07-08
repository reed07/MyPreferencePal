package io.requery.sql.platform;

import io.requery.meta.Attribute;
import io.requery.query.Expression;
import io.requery.query.function.Function.Name;
import io.requery.query.function.Now;
import io.requery.query.function.Random;
import io.requery.sql.BaseType;
import io.requery.sql.GeneratedColumnDefinition;
import io.requery.sql.IdentityColumnDefinition;
import io.requery.sql.Keyword;
import io.requery.sql.Mapping;
import io.requery.sql.QueryBuilder;
import io.requery.sql.QueryBuilder.Appender;
import io.requery.sql.gen.Generator;
import io.requery.sql.gen.Output;
import io.requery.sql.gen.UpsertMergeGenerator;
import io.requery.sql.type.PrimitiveBooleanType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Oracle extends Generic {
    private final OracleIdentityColumnDefinition generatedColumn = new OracleIdentityColumnDefinition();
    private final UpsertMergeGenerator upsertMergeWriter = new UpsertMergeDual();

    private static class NumericBooleanType extends BaseType<Boolean> implements PrimitiveBooleanType {
        public String getIdentifier() {
            return "number";
        }

        public boolean hasLength() {
            return true;
        }

        NumericBooleanType() {
            super(Boolean.class, 2);
        }

        public Integer getDefaultLength() {
            return Integer.valueOf(1);
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

    private static class OracleIdentityColumnDefinition extends IdentityColumnDefinition {
        private OracleIdentityColumnDefinition() {
        }

        public void appendGeneratedSequence(QueryBuilder queryBuilder, Attribute attribute) {
            queryBuilder.keyword(Keyword.GENERATED, Keyword.ALWAYS, Keyword.AS, Keyword.IDENTITY);
            queryBuilder.openParenthesis().keyword(Keyword.START, Keyword.WITH).value(Integer.valueOf(1)).keyword(Keyword.INCREMENT, Keyword.BY).value(Integer.valueOf(1)).closeParenthesis().space();
        }
    }

    private static class RawType extends BaseType<byte[]> {
        public String getIdentifier() {
            return "raw";
        }

        RawType(int i) {
            super(byte[].class, i);
        }

        public boolean hasLength() {
            return getSqlType() == -3;
        }

        public byte[] read(ResultSet resultSet, int i) throws SQLException {
            byte[] bytes = resultSet.getBytes(i);
            if (resultSet.wasNull()) {
                return null;
            }
            return bytes;
        }
    }

    private static class UpsertMergeDual extends UpsertMergeGenerator {
        private UpsertMergeDual() {
        }

        /* access modifiers changed from: protected */
        public void appendUsing(final Output output, final Map<Expression<?>, Object> map) {
            output.builder().openParenthesis().keyword(Keyword.SELECT).commaSeparated((Iterable<? extends T>) map.keySet(), (Appender<T>) new Appender<Expression<?>>() {
                public void append(QueryBuilder queryBuilder, Expression expression) {
                    queryBuilder.append("? ");
                    output.parameters().add(expression, map.get(expression));
                    queryBuilder.append(expression.getName());
                }
            }).space().keyword(Keyword.FROM).append("DUAL ").closeParenthesis().append(" val ");
        }
    }

    public boolean supportsIfExists() {
        return false;
    }

    public boolean supportsOnUpdateCascade() {
        return false;
    }

    public void addMappings(Mapping mapping) {
        super.addMappings(mapping);
        mapping.replaceType(-2, new RawType(-2));
        mapping.replaceType(-3, new RawType(-3));
        mapping.replaceType(16, new NumericBooleanType());
        mapping.aliasFunction(new Name("dbms_random.value", true), Random.class);
        mapping.aliasFunction(new Name("current_date", true), Now.class);
    }

    public GeneratedColumnDefinition generatedColumnDefinition() {
        return this.generatedColumn;
    }

    public Generator<Map<Expression<?>, Object>> upsertGenerator() {
        return this.upsertMergeWriter;
    }
}
