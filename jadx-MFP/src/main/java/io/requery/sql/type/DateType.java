package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DateType extends BasicType<Date> {
    public DateType() {
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
            preparedStatement.setDate(i, date);
        }
    }

    public Keyword getIdentifier() {
        return Keyword.DATE;
    }
}
