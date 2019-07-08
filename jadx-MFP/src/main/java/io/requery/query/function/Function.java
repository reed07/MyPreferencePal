package io.requery.query.function;

import io.requery.query.Expression;
import io.requery.query.ExpressionType;
import io.requery.query.FieldExpression;
import io.requery.query.NamedExpression;
import io.requery.util.Objects;
import java.util.Collection;

public abstract class Function<V> extends FieldExpression<V> {
    private String alias;
    private final Name name;
    private final Class<V> type;

    private static class ArgumentExpression<X> implements Expression<X> {
        private final Class<X> type;

        public Expression<X> getInnerExpression() {
            return null;
        }

        public String getName() {
            return "";
        }

        ArgumentExpression(Class<X> cls) {
            this.type = cls;
        }

        public Class<X> getClassType() {
            return this.type;
        }

        public ExpressionType getExpressionType() {
            return ExpressionType.FUNCTION;
        }
    }

    public static class Name {
        private final boolean isConstant;
        private final String name;

        public Name(String str) {
            this(str, false);
        }

        public Name(String str, boolean z) {
            this.name = str;
            this.isConstant = z;
        }

        public String getName() {
            return this.name;
        }

        public boolean isConstant() {
            return this.isConstant;
        }

        public String toString() {
            return this.name;
        }
    }

    public abstract Object[] arguments();

    public /* bridge */ /* synthetic */ Object eq(Expression expression) {
        return super.eq(expression);
    }

    public /* bridge */ /* synthetic */ Object eq(Object obj) {
        return super.eq(obj);
    }

    public /* bridge */ /* synthetic */ Object equal(Expression expression) {
        return super.equal(expression);
    }

    public /* bridge */ /* synthetic */ Object equal(Object obj) {
        return super.equal(obj);
    }

    public /* bridge */ /* synthetic */ Object in(Collection collection) {
        return super.in(collection);
    }

    public /* bridge */ /* synthetic */ Object isNull() {
        return super.isNull();
    }

    public /* bridge */ /* synthetic */ Object lessThan(Object obj) {
        return super.lessThan(obj);
    }

    public /* bridge */ /* synthetic */ Object ne(Object obj) {
        return super.ne(obj);
    }

    public /* bridge */ /* synthetic */ Object notNull() {
        return super.notNull();
    }

    public Function(String str, Class<V> cls) {
        this.name = new Name(str);
        this.type = cls;
    }

    public ExpressionType getExpressionType() {
        return ExpressionType.FUNCTION;
    }

    public Function<V> as(String str) {
        this.alias = str;
        return this;
    }

    public String getAlias() {
        return this.alias;
    }

    public Class<V> getClassType() {
        return this.type;
    }

    public String getName() {
        return this.name.toString();
    }

    public Name getFunctionName() {
        return this.name;
    }

    public Expression<?> expressionForArgument(int i) {
        Object obj = arguments()[i];
        if (obj instanceof Expression) {
            return (Expression) obj;
        }
        if (obj == null) {
            return NamedExpression.of("null", this.type);
        }
        return new ArgumentExpression(obj.getClass());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Function)) {
            return false;
        }
        Function function = (Function) obj;
        if (!Objects.equals(getName(), function.getName()) || !Objects.equals(getClassType(), function.getClassType()) || !Objects.equals(getAlias(), function.getAlias()) || !Objects.equals(arguments(), function.arguments())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hash(getName(), getClassType(), getAlias(), arguments());
    }
}
