package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class JavaDateType extends BasicType<Date> {
    public JavaDateType() {
        super(Date.class, 91);
    }

    public Date fromResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getDate(i);
    }

    public void write(PreparedStatement preparedStatement, int i, Date date) throws SQLException {
        int sqlType = getSqlType();
        if (date == null) {
            preparedStatement.setNull(i, sqlType);
        } else {
            preparedStatement.setDate(i, new java.sql.Date(date.getTime()));
        }
    }

    public Keyword getIdentifier() {
        return Keyword.DATE;
    }
}
