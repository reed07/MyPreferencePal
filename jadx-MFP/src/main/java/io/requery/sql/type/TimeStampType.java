package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TimeStampType extends BasicType<Timestamp> {
    public TimeStampType() {
        super(Timestamp.class, 93);
    }

    public Timestamp fromResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getTimestamp(i);
    }

    public Keyword getIdentifier() {
        return Keyword.TIMESTAMP;
    }
}
