package io.requery.sql.platform;

import io.requery.query.function.Function.Name;
import io.requery.query.function.Now;
import io.requery.sql.BaseType;
import io.requery.sql.Keyword;
import io.requery.sql.Mapping;
import io.requery.sql.type.VarCharType;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Derby extends Generic {

    private static class CharBitData extends BaseType<byte[]> {
        public String getIdentifierSuffix() {
            return "for bit data";
        }

        public boolean hasLength() {
            return true;
        }

        CharBitData(int i) {
            super(byte[].class, i);
        }

        public Integer getDefaultLength() {
            return Integer.valueOf(32);
        }

        public Object getIdentifier() {
            switch (getSqlType()) {
                case -3:
                    return Keyword.VARCHAR;
                case -2:
                    return "char";
                default:
                    throw new IllegalArgumentException();
            }
        }

        public byte[] read(ResultSet resultSet, int i) throws SQLException {
            byte[] bytes = resultSet.getBytes(i);
            if (resultSet.wasNull()) {
                return null;
            }
            return bytes;
        }
    }

    public boolean supportsGeneratedColumnsInPrepareStatement() {
        return false;
    }

    public boolean supportsIfExists() {
        return false;
    }

    public boolean supportsUpsert() {
        return true;
    }

    public void addMappings(Mapping mapping) {
        super.addMappings(mapping);
        mapping.replaceType(-3, new CharBitData(-3));
        mapping.replaceType(-2, new CharBitData(-2));
        mapping.replaceType(-9, new VarCharType());
        mapping.aliasFunction(new Name("current_date", true), Now.class);
    }
}
