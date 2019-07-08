package io.requery.sql.type;

import io.requery.sql.FieldType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PrimitiveShortType extends FieldType<Short> {
    short readShort(ResultSet resultSet, int i) throws SQLException;

    void writeShort(PreparedStatement preparedStatement, int i, short s) throws SQLException;
}
