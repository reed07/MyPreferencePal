package io.requery.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

interface GeneratedResultReader {
    String[] generatedColumns();

    void read(int i, ResultSet resultSet) throws SQLException;
}
