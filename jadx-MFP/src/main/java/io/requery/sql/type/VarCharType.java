package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VarCharType extends BasicType<String> {
    public boolean hasLength() {
        return true;
    }

    public VarCharType() {
        super(String.class, 12);
    }

    public String fromResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(i);
    }

    public String read(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(i);
    }

    public Keyword getIdentifier() {
        return Keyword.VARCHAR;
    }

    public Integer getDefaultLength() {
        return Integer.valueOf(255);
    }
}
