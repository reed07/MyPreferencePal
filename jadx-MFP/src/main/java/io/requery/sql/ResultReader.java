package io.requery.sql;

import io.requery.query.Expression;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

interface ResultReader<R> {
    R read(ResultSet resultSet, Set<? extends Expression<?>> set) throws SQLException;
}
