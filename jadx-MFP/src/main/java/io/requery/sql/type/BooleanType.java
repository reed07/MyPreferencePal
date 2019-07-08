package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooleanType extends BasicType<Boolean> implements PrimitiveBooleanType {
    public BooleanType(Class<Boolean> cls) {
        super(cls, 16);
    }

    public Boolean fromResult(ResultSet resultSet, int i) throws SQLException {
        return Boolean.valueOf(resultSet.getBoolean(i));
    }

    public Keyword getIdentifier() {
        return Keyword.BOOLEAN;
    }

    public boolean readBoolean(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getBoolean(i);
    }

    public void writeBoolean(PreparedStatement preparedStatement, int i, boolean z) throws SQLException {
        preparedStatement.setBoolean(i, z);
    }
}
