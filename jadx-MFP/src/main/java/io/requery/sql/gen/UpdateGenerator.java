package io.requery.sql.gen;

import io.requery.query.Expression;
import io.requery.query.Operator;
import io.requery.sql.Keyword;
import io.requery.sql.QueryBuilder;
import java.util.Map;
import java.util.Map.Entry;

class UpdateGenerator implements Generator<Map<Expression<?>, Object>> {
    UpdateGenerator() {
    }

    public void write(Output output, Map<Expression<?>, Object> map) {
        QueryBuilder builder = output.builder();
        int i = 0;
        builder.keyword(Keyword.UPDATE);
        output.appendTables();
        builder.keyword(Keyword.SET);
        for (Entry entry : map.entrySet()) {
            if (i > 0) {
                builder.append(",");
            }
            output.appendColumn((Expression) entry.getKey());
            output.appendOperator(Operator.EQUAL);
            output.appendConditionValue((Expression) entry.getKey(), entry.getValue());
            i++;
        }
    }
}
