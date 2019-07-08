package io.requery.sql.type;

import io.requery.sql.FieldType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PrimitiveLongType extends FieldType<Long> {
    long readLong(ResultSet resultSet, int i) throws SQLException;

    void writeLong(PreparedStatement preparedStatement, int i, long j) throws SQLException;
}
