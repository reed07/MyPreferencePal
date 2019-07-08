package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClobType extends BasicType<Clob> {
    public ClobType() {
        super(Clob.class, 2005);
    }

    public Clob fromResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getClob(i);
    }

    public Keyword getIdentifier() {
        return Keyword.CLOB;
    }
}
