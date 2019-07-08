package io.requery.sql.gen;

import io.requery.query.element.SetOperationElement;
import io.requery.sql.Keyword;
import io.requery.sql.QueryBuilder;

class SetOperatorGenerator implements Generator<SetOperationElement> {
    SetOperatorGenerator() {
    }

    public void write(Output output, SetOperationElement setOperationElement) {
        if (setOperationElement.getInnerSetQuery() != null) {
            QueryBuilder builder = output.builder();
            switch (setOperationElement.getOperator()) {
                case UNION:
                    builder.keyword(Keyword.UNION);
                    break;
                case UNION_ALL:
                    builder.keyword(Keyword.UNION, Keyword.ALL);
                    break;
                case INTERSECT:
                    builder.keyword(Keyword.INTERSECT);
                    break;
                case EXCEPT:
                    builder.keyword(Keyword.EXCEPT);
                    break;
            }
            output.appendQuery(setOperationElement.getInnerSetQuery());
        }
    }
}
