package io.requery.sql.type;

import io.requery.sql.FieldType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PrimitiveByteType extends FieldType<Byte> {
    byte readByte(ResultSet resultSet, int i) throws SQLException;

    void writeByte(PreparedStatement preparedStatement, int i, byte b) throws SQLException;
}
