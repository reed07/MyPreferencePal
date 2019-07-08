package io.requery.sql;

import io.requery.util.Objects;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseType<T> implements FieldType<T> {
    private final int sqlType;
    private final Class<T> type;

    public Integer getDefaultLength() {
        return null;
    }

    public abstract Object getIdentifier();

    public String getIdentifierSuffix() {
        return null;
    }

    public boolean hasLength() {
        return false;
    }

    protected BaseType(Class<T> cls, int i) {
        this.type = cls;
        this.sqlType = i;
    }

    public T read(ResultSet resultSet, int i) throws SQLException {
        T cast = this.type.cast(resultSet.getObject(i));
        if (resultSet.wasNull()) {
            return null;
        }
        return cast;
    }

    public void write(PreparedStatement preparedStatement, int i, T t) throws SQLException {
        if (t == null) {
            preparedStatement.setNull(i, this.sqlType);
        } else {
            preparedStatement.setObject(i, t, this.sqlType);
        }
    }

    public int getSqlType() {
        return this.sqlType;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof FieldType)) {
            return false;
        }
        FieldType fieldType = (FieldType) obj;
        if (Objects.equals(getIdentifier(), fieldType.getIdentifier()) && getSqlType() == fieldType.getSqlType() && hasLength() == fieldType.hasLength() && Objects.equals(getIdentifierSuffix(), fieldType.getIdentifierSuffix()) && Objects.equals(getDefaultLength(), fieldType.getDefaultLength())) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hash(getIdentifier(), Integer.valueOf(getSqlType()), getDefaultLength(), getIdentifierSuffix());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getIdentifier());
        if (hasLength()) {
            sb.append("(");
            sb.append(getDefaultLength());
            sb.append(")");
        }
        if (getIdentifierSuffix() != null) {
            sb.append(" ");
            sb.append(getIdentifierSuffix());
        }
        return sb.toString();
    }
}
