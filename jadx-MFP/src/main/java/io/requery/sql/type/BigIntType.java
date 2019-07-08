package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BigIntType extends BasicType<Long> implements PrimitiveLongType {
    public BigIntType(Class<Long> cls) {
        super(cls, -5);
    }

    public Long fromResult(ResultSet resultSet, int i) throws SQLException {
        return Long.valueOf(resultSet.getLong(i));
    }

    public Keyword getIdentifier() {
        return Keyword.BIGINT;
    }

    public long readLong(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getLong(i);
    }

    public void writeLong(PreparedStatement preparedStatement, int i, long j) throws SQLException {
        preparedStatement.setLong(i, j);
    }
}
