package io.requery.sql.type;

import io.requery.sql.FieldType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PrimitiveFloatType extends FieldType<Float> {
    float readFloat(ResultSet resultSet, int i) throws SQLException;

    void writeFloat(PreparedStatement preparedStatement, int i, float f) throws SQLException;
}
