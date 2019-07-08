package io.requery.sql;

import io.requery.query.Expression;
import io.requery.query.MutableTuple;
import io.requery.query.Tuple;
import io.requery.util.Objects;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

class TupleResultReader implements ResultReader<Tuple> {
    private final RuntimeConfiguration configuration;

    TupleResultReader(RuntimeConfiguration runtimeConfiguration) {
        this.configuration = (RuntimeConfiguration) Objects.requireNotNull(runtimeConfiguration);
    }

    public Tuple read(ResultSet resultSet, Set<? extends Expression<?>> set) throws SQLException {
        MutableTuple mutableTuple = new MutableTuple(set.size());
        Mapping mapping = this.configuration.getMapping();
        int i = 1;
        for (Expression expression : set) {
            mutableTuple.set(i - 1, expression, mapping.read(expression, resultSet, i));
            i++;
        }
        return mutableTuple;
    }
}
