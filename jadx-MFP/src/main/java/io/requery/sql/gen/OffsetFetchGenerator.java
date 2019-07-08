package io.requery.sql.gen;

import io.requery.query.element.LimitedElement;
import io.requery.sql.Keyword;
import io.requery.sql.QueryBuilder;

public class OffsetFetchGenerator extends LimitGenerator {
    public void write(Output output, LimitedElement limitedElement) {
        QueryBuilder builder = output.builder();
        Integer limit = limitedElement.getLimit();
        if (limit != null && limit.intValue() > 0) {
            write(builder, limit, limitedElement.getOffset());
        }
    }

    /* access modifiers changed from: protected */
    public void write(QueryBuilder queryBuilder, Integer num, Integer num2) {
        if (num2 != null) {
            QueryBuilder value = queryBuilder.keyword(Keyword.OFFSET).value(num2);
            Keyword[] keywordArr = new Keyword[1];
            keywordArr[0] = num2.intValue() > 1 ? Keyword.ROWS : Keyword.ROW;
            QueryBuilder value2 = value.keyword(keywordArr).keyword(Keyword.FETCH, Keyword.NEXT).value(num);
            Keyword[] keywordArr2 = new Keyword[1];
            keywordArr2[0] = num.intValue() > 1 ? Keyword.ROWS : Keyword.ROW;
            value2.keyword(keywordArr2).keyword(Keyword.ONLY);
        } else if (num != null) {
            QueryBuilder value3 = queryBuilder.keyword(Keyword.FETCH, Keyword.FIRST).value(num);
            Keyword[] keywordArr3 = new Keyword[1];
            keywordArr3[0] = num.intValue() > 1 ? Keyword.ROWS : Keyword.ROW;
            value3.keyword(keywordArr3).keyword(Keyword.ONLY);
        }
    }
}
