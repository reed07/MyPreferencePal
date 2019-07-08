package io.requery.sql.type;

import io.requery.sql.FieldType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PrimitiveIntType extends FieldType<Integer> {
    int readInt(ResultSet resultSet, int i) throws SQLException;

    void writeInt(PreparedStatement preparedStatement, int i, int i2) throws SQLException;
}
