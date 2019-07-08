package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VarBinaryType extends BasicType<byte[]> {
    public boolean hasLength() {
        return true;
    }

    public VarBinaryType() {
        super(byte[].class, -3);
    }

    public byte[] fromResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getBytes(i);
    }

    public Keyword getIdentifier() {
        return Keyword.VARBINARY;
    }
}
