package io.requery.sql.platform;

import com.brightcove.player.event.AbstractEvent;
import io.requery.meta.Attribute;
import io.requery.meta.Type;
import io.requery.query.Expression;
import io.requery.sql.BaseType;
import io.requery.sql.GeneratedColumnDefinition;
import io.requery.sql.Keyword;
import io.requery.sql.Mapping;
import io.requery.sql.QueryBuilder;
import io.requery.sql.QueryBuilder.Appender;
import io.requery.sql.VersionColumnDefinition;
import io.requery.sql.gen.Generator;
import io.requery.sql.gen.LimitGenerator;
import io.requery.sql.gen.Output;
import io.requery.sql.type.VarCharType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class PostgresSQL extends Generic {
    private final SerialColumnDefinition serialColumnDefinition = new SerialColumnDefinition();
    private final VersionColumnDefinition versionColumnDefinition = new SystemVersionColumnDefinition();

    private static class ByteArrayType extends BaseType<byte[]> {
        public String getIdentifier() {
            return "bytea";
        }

        ByteArrayType(int i) {
            super(byte[].class, i);
        }

        public byte[] read(ResultSet resultSet, int i) throws SQLException {
            byte[] bytes = resultSet.getBytes(i);
            if (resultSet.wasNull()) {
                return null;
            }
            return bytes;
        }
    }

    private static class SerialColumnDefinition implements GeneratedColumnDefinition {
        public boolean postFixPrimaryKey() {
            return false;
        }

        public boolean skipTypeIdentifier() {
            return true;
        }

        private SerialColumnDefinition() {
        }

        public void appendGeneratedSequence(QueryBuilder queryBuilder, Attribute attribute) {
            queryBuilder.append("serial");
        }
    }

    private static class SystemVersionColumnDefinition implements VersionColumnDefinition {
        public String columnName() {
            return "xmin";
        }

        public boolean createColumn() {
            return false;
        }

        private SystemVersionColumnDefinition() {
        }
    }

    private static class UUIDType extends BaseType<UUID> {
        public String getIdentifier() {
            return AbstractEvent.UUID;
        }

        UUIDType() {
            super(UUID.class, 2000);
        }

        public void write(PreparedStatement preparedStatement, int i, UUID uuid) throws SQLException {
            preparedStatement.setObject(i, uuid);
        }
    }

    private static class UpsertOnConflictDoUpdate implements Generator<Map<Expression<?>, Object>> {
        private UpsertOnConflictDoUpdate() {
        }

        public void write(final Output output, final Map<Expression<?>, Object> map) {
            QueryBuilder builder = output.builder();
            Type declaringType = ((Attribute) map.keySet().iterator().next()).getDeclaringType();
            builder.keyword(Keyword.INSERT, Keyword.INTO).tableNames(map.keySet()).openParenthesis().commaSeparatedExpressions(map.keySet()).closeParenthesis().space().keyword(Keyword.VALUES).openParenthesis().commaSeparated((Iterable<? extends T>) map.keySet(), (Appender<T>) new Appender<Expression<?>>() {
                public void append(QueryBuilder queryBuilder, Expression expression) {
                    queryBuilder.append("?");
                    output.parameters().add(expression, map.get(expression));
                }
            }).closeParenthesis().space().keyword(Keyword.ON, Keyword.CONFLICT).openParenthesis().commaSeparatedAttributes(declaringType.getKeyAttributes()).closeParenthesis().space().keyword(Keyword.DO, Keyword.UPDATE, Keyword.SET).commaSeparated((Iterable<? extends T>) map.keySet(), (Appender<T>) new Appender<Expression<?>>() {
                public void append(QueryBuilder queryBuilder, Expression<?> expression) {
                    queryBuilder.attribute((Attribute) expression);
                    StringBuilder sb = new StringBuilder();
                    sb.append("= EXCLUDED.");
                    sb.append(expression.getName());
                    queryBuilder.append(sb.toString());
                }
            });
        }
    }

    public boolean supportsInlineForeignKeyReference() {
        return true;
    }

    public GeneratedColumnDefinition generatedColumnDefinition() {
        return this.serialColumnDefinition;
    }

    public void addMappings(Mapping mapping) {
        super.addMappings(mapping);
        mapping.replaceType(-2, new ByteArrayType(-2));
        mapping.replaceType(-3, new ByteArrayType(-3));
        mapping.replaceType(-9, new VarCharType());
        mapping.putType(UUID.class, new UUIDType());
    }

    public LimitGenerator limitGenerator() {
        return new LimitGenerator();
    }

    public VersionColumnDefinition versionColumnDefinition() {
        return this.versionColumnDefinition;
    }

    public Generator<Map<Expression<?>, Object>> upsertGenerator() {
        return new UpsertOnConflictDoUpdate();
    }
}
