package io.requery.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Nullable;

public interface FieldType<T> {
    @Nullable
    Integer getDefaultLength();

    Object getIdentifier();

    @Nullable
    String getIdentifierSuffix();

    int getSqlType();

    boolean hasLength();

    @Nullable
    T read(ResultSet resultSet, int i) throws SQLException;

    void write(PreparedStatement preparedStatement, int i, @Nullable T t) throws SQLException;
}
