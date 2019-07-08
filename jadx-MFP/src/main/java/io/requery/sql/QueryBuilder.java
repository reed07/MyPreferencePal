package io.requery.sql;

import io.requery.meta.Attribute;
import io.requery.meta.Type;
import io.requery.query.Expression;
import io.requery.query.ExpressionType;
import io.requery.util.function.Function;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nonnull;

public class QueryBuilder implements CharSequence {
    private final Options options;
    private final StringBuilder sb = new StringBuilder(32);

    /* renamed from: io.requery.sql.QueryBuilder$4 reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$io$requery$query$ExpressionType = new int[ExpressionType.values().length];

        static {
            try {
                $SwitchMap$io$requery$query$ExpressionType[ExpressionType.ATTRIBUTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public interface Appender<T> {
        void append(QueryBuilder queryBuilder, T t);
    }

    public static class Options {
        /* access modifiers changed from: private */
        public final Function<String, String> columnTransformer;
        /* access modifiers changed from: private */
        public final boolean lowercaseKeywords;
        /* access modifiers changed from: private */
        public final boolean quoteColumnNames;
        /* access modifiers changed from: private */
        public final boolean quoteTableNames;
        /* access modifiers changed from: private */
        public final String quotedIdentifier;
        /* access modifiers changed from: private */
        public final Function<String, String> tableTransformer;

        public Options(String str, boolean z, Function<String, String> function, Function<String, String> function2, boolean z2, boolean z3) {
            if (str.equals(" ")) {
                str = "\"";
            }
            this.quotedIdentifier = str;
            this.tableTransformer = function;
            this.columnTransformer = function2;
            this.lowercaseKeywords = z;
            this.quoteTableNames = z2;
            this.quoteColumnNames = z3;
        }
    }

    public QueryBuilder(Options options2) {
        this.options = options2;
    }

    @Nonnull
    public String toString() {
        return this.sb.toString();
    }

    public int length() {
        return this.sb.length();
    }

    public char charAt(int i) {
        return this.sb.charAt(i);
    }

    public CharSequence subSequence(int i, int i2) {
        return this.sb.subSequence(i, i2);
    }

    public QueryBuilder keyword(Keyword... keywordArr) {
        for (Keyword keyword : keywordArr) {
            this.sb.append(this.options.lowercaseKeywords ? keyword.toString().toLowerCase(Locale.ROOT) : keyword);
            this.sb.append(" ");
        }
        return this;
    }

    public QueryBuilder appendIdentifier(String str, String str2) {
        return append(str2, false).append(str, false).append(str2);
    }

    public QueryBuilder appendQuoted(String str) {
        return appendIdentifier(str, "'");
    }

    public QueryBuilder tableName(Object obj) {
        String obj2 = obj.toString();
        if (this.options.tableTransformer != null) {
            obj2 = (String) this.options.tableTransformer.apply(obj2);
        }
        if (this.options.quoteTableNames) {
            appendIdentifier(obj2, this.options.quotedIdentifier);
        } else {
            append(obj2);
        }
        return space();
    }

    public QueryBuilder tableNames(Iterable<Expression<?>> iterable) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Expression expression : iterable) {
            if (expression.getExpressionType() == ExpressionType.ATTRIBUTE) {
                linkedHashSet.add(((Attribute) expression).getDeclaringType());
            }
        }
        return commaSeparated((Iterable<? extends T>) linkedHashSet, (Appender<T>) new Appender<Type<?>>() {
            public void append(QueryBuilder queryBuilder, Type<?> type) {
                QueryBuilder.this.tableName(type.getName());
            }
        });
    }

    public QueryBuilder attribute(Attribute attribute) {
        String name = this.options.columnTransformer == null ? attribute.getName() : (String) this.options.columnTransformer.apply(attribute.getName());
        if (this.options.quoteColumnNames) {
            appendIdentifier(name, this.options.quotedIdentifier);
        } else {
            append(name);
        }
        return space();
    }

    public QueryBuilder aliasAttribute(String str, Attribute attribute) {
        append(str);
        append(".");
        return attribute(attribute);
    }

    public QueryBuilder append(Object obj) {
        return append(obj, false);
    }

    public QueryBuilder value(Object obj) {
        return append(obj, true);
    }

    public QueryBuilder append(Object obj, boolean z) {
        if (obj == null) {
            keyword(Keyword.NULL);
        } else if (obj instanceof String[]) {
            commaSeparated(Arrays.asList((String[]) obj));
        } else if (obj instanceof Keyword) {
            this.sb.append(this.options.lowercaseKeywords ? obj.toString().toLowerCase(Locale.ROOT) : obj.toString());
        } else {
            this.sb.append(obj.toString());
        }
        if (z) {
            this.sb.append(" ");
        }
        return this;
    }

    public <T> QueryBuilder appendWhereConditions(Set<Attribute<T, ?>> set) {
        int i = 0;
        for (Attribute attribute : set) {
            if (i > 0) {
                keyword(Keyword.AND);
                space();
            }
            attribute(attribute);
            space();
            append("=?");
            space();
            i++;
        }
        return this;
    }

    public QueryBuilder commaSeparatedExpressions(Iterable<Expression<?>> iterable) {
        return commaSeparated(iterable, (Appender<T>) new Appender<Expression<?>>() {
            public void append(QueryBuilder queryBuilder, Expression<?> expression) {
                if (AnonymousClass4.$SwitchMap$io$requery$query$ExpressionType[expression.getExpressionType().ordinal()] != 1) {
                    queryBuilder.append(expression.getName()).space();
                } else {
                    queryBuilder.attribute((Attribute) expression);
                }
            }
        });
    }

    public QueryBuilder commaSeparatedAttributes(Iterable<? extends Attribute<?, ?>> iterable) {
        return commaSeparated(iterable, (Appender<T>) new Appender<Attribute<?, ?>>() {
            public void append(QueryBuilder queryBuilder, Attribute<?, ?> attribute) {
                queryBuilder.attribute(attribute);
            }
        });
    }

    public <T> QueryBuilder commaSeparated(Iterable<? extends T> iterable) {
        return commaSeparated(iterable, null);
    }

    public <T> QueryBuilder commaSeparated(Iterable<? extends T> iterable, Appender<T> appender) {
        return commaSeparated(iterable.iterator(), appender);
    }

    public <T> QueryBuilder commaSeparated(Iterator<? extends T> it, Appender<T> appender) {
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            if (i > 0) {
                comma();
            }
            if (appender == null) {
                append(next);
            } else {
                appender.append(this, next);
            }
            i++;
        }
        return this;
    }

    public QueryBuilder openParenthesis() {
        this.sb.append("(");
        return this;
    }

    public QueryBuilder closeParenthesis() {
        StringBuilder sb2 = this.sb;
        if (sb2.charAt(sb2.length() - 1) == ' ') {
            StringBuilder sb3 = this.sb;
            sb3.setCharAt(sb3.length() - 1, ')');
        } else {
            this.sb.append(')');
        }
        return this;
    }

    public QueryBuilder space() {
        StringBuilder sb2 = this.sb;
        if (sb2.charAt(sb2.length() - 1) != ' ') {
            this.sb.append(" ");
        }
        return this;
    }

    public QueryBuilder comma() {
        StringBuilder sb2 = this.sb;
        if (sb2.charAt(sb2.length() - 1) == ' ') {
            StringBuilder sb3 = this.sb;
            sb3.setCharAt(sb3.length() - 1, ',');
        } else {
            this.sb.append(',');
        }
        space();
        return this;
    }
}
