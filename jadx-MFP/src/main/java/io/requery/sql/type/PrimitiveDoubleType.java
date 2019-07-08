package io.requery.sql.type;

import io.requery.sql.FieldType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PrimitiveDoubleType extends FieldType<Double> {
    double readDouble(ResultSet resultSet, int i) throws SQLException;

    void writeDouble(PreparedStatement preparedStatement, int i, double d) throws SQLException;
}
