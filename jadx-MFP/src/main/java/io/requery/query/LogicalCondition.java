package io.requery.query;

public interface LogicalCondition<L, R> extends AndOr<LogicalCondition<LogicalCondition<L, R>, Condition<?, ?>>>, Condition<L, R> {
}
