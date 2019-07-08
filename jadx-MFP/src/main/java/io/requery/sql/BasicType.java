package io.requery.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BasicType<T> extends BaseType<T> {
    private final boolean checkNull;

    public abstract T fromResult(ResultSet resultSet, int i) throws SQLException;

    public abstract Keyword getIdentifier();

    protected BasicType(Class<T> cls, int i) {
        super(cls, i);
        this.checkNull = !cls.isPrimitive();
    }

    public T read(ResultSet resultSet, int i) throws SQLException {
        T fromResult = fromResult(resultSet, i);
        if (!this.checkNull || !resultSet.wasNull()) {
            return fromResult;
        }
        return null;
    }
}
