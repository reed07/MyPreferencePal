package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TinyIntType extends BasicType<Byte> implements PrimitiveByteType {
    public TinyIntType(Class<Byte> cls) {
        super(cls, -6);
    }

    public Byte fromResult(ResultSet resultSet, int i) throws SQLException {
        return Byte.valueOf(resultSet.getByte(i));
    }

    public Keyword getIdentifier() {
        return Keyword.TINYINT;
    }

    public byte readByte(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getByte(i);
    }

    public void writeByte(PreparedStatement preparedStatement, int i, byte b) throws SQLException {
        preparedStatement.setByte(i, b);
    }
}
