package io.requery.sql.type;

import io.requery.sql.FieldType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PrimitiveBooleanType extends FieldType<Boolean> {
    boolean readBoolean(ResultSet resultSet, int i) throws SQLException;

    void writeBoolean(PreparedStatement preparedStatement, int i, boolean z) throws SQLException;
}
