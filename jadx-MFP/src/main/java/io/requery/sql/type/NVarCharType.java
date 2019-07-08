package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NVarCharType extends BasicType<String> {
    public boolean hasLength() {
        return true;
    }

    public NVarCharType() {
        super(String.class, -9);
    }

    public String fromResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getNString(i);
    }

    public Keyword getIdentifier() {
        return Keyword.NVARCHAR;
    }

    public Integer getDefaultLength() {
        return Integer.valueOf(255);
    }
}
