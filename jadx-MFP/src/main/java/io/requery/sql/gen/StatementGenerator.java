package io.requery.sql.gen;

import io.requery.query.Expression;
import io.requery.query.element.GroupByElement;
import io.requery.query.element.LimitedElement;
import io.requery.query.element.OrderByElement;
import io.requery.query.element.QueryElement;
import io.requery.query.element.SelectionElement;
import io.requery.query.element.SetOperationElement;
import io.requery.query.element.WhereElement;
import io.requery.sql.Keyword;
import io.requery.sql.Platform;
import io.requery.sql.QueryBuilder;
import java.util.Map;

public final class StatementGenerator implements Generator<QueryElement<?>> {
    private Generator<GroupByElement> groupBy;
    private Generator<QueryElement<?>> insert = new InsertGenerator();
    private Generator<LimitedElement> limit;
    private Generator<OrderByElement> orderBy;
    private Generator<SelectionElement> select = new SelectGenerator();
    private Generator<SetOperationElement> setOperation;
    private Generator<Map<Expression<?>, Object>> update = new UpdateGenerator();
    private Generator<Map<Expression<?>, Object>> upsert;
    private Generator<WhereElement> where;

    public StatementGenerator(Platform platform) {
        this.upsert = platform.upsertGenerator();
        this.where = new WhereGenerator();
        this.groupBy = new GroupByGenerator();
        this.orderBy = platform.orderByGenerator();
        this.limit = platform.limitGenerator();
        this.setOperation = new SetOperatorGenerator();
    }

    public void write(Output output, QueryElement<?> queryElement) {
        QueryBuilder builder = output.builder();
        switch (queryElement.queryType()) {
            case SELECT:
                this.select.write(output, queryElement);
                break;
            case INSERT:
                this.insert.write(output, queryElement);
                break;
            case UPDATE:
                this.update.write(output, checkEmpty(queryElement.updateValues()));
                break;
            case UPSERT:
                this.upsert.write(output, checkEmpty(queryElement.updateValues()));
                break;
            case DELETE:
                builder.keyword(Keyword.DELETE, Keyword.FROM);
                output.appendTables();
                break;
            case TRUNCATE:
                builder.keyword(Keyword.TRUNCATE);
                output.appendTables();
                break;
        }
        this.where.write(output, queryElement);
        this.groupBy.write(output, queryElement);
        this.orderBy.write(output, queryElement);
        this.limit.write(output, queryElement);
        this.setOperation.write(output, queryElement);
    }

    private static Map<Expression<?>, Object> checkEmpty(Map<Expression<?>, Object> map) {
        if (map != null && !map.isEmpty()) {
            return map;
        }
        throw new IllegalStateException("Cannot generate update statement with an empty set of values");
    }
}
