package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SmallIntType extends BasicType<Short> implements PrimitiveShortType {
    public SmallIntType(Class<Short> cls) {
        super(cls, 5);
    }

    public Short fromResult(ResultSet resultSet, int i) throws SQLException {
        return Short.valueOf(resultSet.getShort(i));
    }

    public Keyword getIdentifier() {
        return Keyword.SMALLINT;
    }

    public short readShort(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getShort(i);
    }

    public void writeShort(PreparedStatement preparedStatement, int i, short s) throws SQLException {
        preparedStatement.setShort(i, s);
    }
}
