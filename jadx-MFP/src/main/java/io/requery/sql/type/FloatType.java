package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FloatType extends BasicType<Float> implements PrimitiveFloatType {
    public FloatType(Class<Float> cls) {
        super(cls, 6);
    }

    public Float fromResult(ResultSet resultSet, int i) throws SQLException {
        return Float.valueOf(resultSet.getFloat(i));
    }

    public Keyword getIdentifier() {
        return Keyword.FLOAT;
    }

    public float readFloat(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getFloat(i);
    }

    public void writeFloat(PreparedStatement preparedStatement, int i, float f) throws SQLException {
        preparedStatement.setFloat(i, f);
    }
}
