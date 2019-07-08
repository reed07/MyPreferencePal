package io.requery.sql.gen;

import io.requery.query.element.LimitedElement;
import io.requery.sql.Keyword;
import io.requery.sql.QueryBuilder;

public class LimitGenerator implements Generator<LimitedElement> {
    public void write(Output output, LimitedElement limitedElement) {
        QueryBuilder builder = output.builder();
        Integer limit = limitedElement.getLimit();
        if (limit != null && limit.intValue() > 0) {
            Integer offset = limitedElement.getOffset();
            builder.keyword(Keyword.LIMIT).value(limit);
            if (offset != null) {
                builder.keyword(Keyword.OFFSET).value(offset);
            }
        }
    }
}
