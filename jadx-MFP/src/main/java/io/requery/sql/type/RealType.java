package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RealType extends BasicType<Double> implements PrimitiveDoubleType {
    public RealType(Class<Double> cls) {
        super(cls, 7);
    }

    public Double fromResult(ResultSet resultSet, int i) throws SQLException {
        return Double.valueOf(resultSet.getDouble(i));
    }

    public Keyword getIdentifier() {
        return Keyword.REAL;
    }

    public double readDouble(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getDouble(i);
    }

    public void writeDouble(PreparedStatement preparedStatement, int i, double d) throws SQLException {
        preparedStatement.setDouble(i, d);
    }
}
