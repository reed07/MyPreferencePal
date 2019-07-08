package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class TimeType extends BasicType<Time> {
    public TimeType() {
        super(Time.class, 92);
    }

    public Time fromResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getTime(i);
    }

    public Keyword getIdentifier() {
        return Keyword.TIME;
    }
}
