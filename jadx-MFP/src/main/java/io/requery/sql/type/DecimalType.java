package io.requery.sql.type;

import io.requery.sql.BasicType;
import io.requery.sql.Keyword;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DecimalType extends BasicType<BigDecimal> {
    public DecimalType() {
        super(BigDecimal.class, 3);
    }

    public BigDecimal fromResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getBigDecimal(i);
    }

    public Keyword getIdentifier() {
        return Keyword.DECIMAL;
    }
}
