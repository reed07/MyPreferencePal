package io.requery.sql;

import io.requery.meta.Attribute;
import io.requery.query.Expression;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

class BuildableEntityResultReader<E extends S, S> implements ResultReader<E> {
    private Attribute<?, ?>[] attributes;
    private final EntityReader<E, S> reader;

    BuildableEntityResultReader(EntityReader<E, S> entityReader, Attribute<?, ?>[] attributeArr) {
        this.reader = entityReader;
        this.attributes = attributeArr;
    }

    public E read(ResultSet resultSet, Set<? extends Expression<?>> set) throws SQLException {
        return this.reader.fromBuilder(resultSet, this.attributes);
    }
}
