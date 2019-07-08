package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BinaryType extends BasicType<byte[]> {
    public BinaryType() {
        super(byte[].class, -2);
    }

    public byte[] fromResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getBytes(i);
    }

    public Keyword getIdentifier() {
        return Keyword.BINARY;
    }
}
