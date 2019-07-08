package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerType extends BasicType<Integer> implements PrimitiveIntType {
    public IntegerType(Class<Integer> cls) {
        super(cls, 4);
    }

    public Integer fromResult(ResultSet resultSet, int i) throws SQLException {
        return Integer.valueOf(resultSet.getInt(i));
    }

    public Keyword getIdentifier() {
        return Keyword.INTEGER;
    }

    public int readInt(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getInt(i);
    }

    public void writeInt(PreparedStatement preparedStatement, int i, int i2) throws SQLException {
        preparedStatement.setInt(i, i2);
    }
}
