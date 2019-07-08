package io.requery.sql.gen;

import com.brightcove.player.event.EventType;
import io.requery.query.Expression;
import io.requery.query.element.SelectionElement;
import io.requery.sql.Keyword;
import io.requery.sql.QueryBuilder;
import io.requery.sql.QueryBuilder.Appender;
import java.util.Set;

class SelectGenerator implements Generator<SelectionElement> {
    SelectGenerator() {
    }

    public void write(final Output output, SelectionElement selectionElement) {
        QueryBuilder builder = output.builder();
        builder.keyword(Keyword.SELECT);
        if (selectionElement.isDistinct()) {
            builder.keyword(Keyword.DISTINCT);
        }
        Set selection = selectionElement.getSelection();
        if (selection == null || selection.isEmpty()) {
            builder.append(EventType.ANY);
        } else {
            builder.commaSeparated((Iterable<? extends T>) selection, (Appender<T>) new Appender<Expression<?>>() {
                public void append(QueryBuilder queryBuilder, Expression<?> expression) {
                    output.appendColumnForSelect(expression);
                }
            });
        }
        builder.keyword(Keyword.FROM);
        output.appendTables();
    }
}
