package io.requery.proxy;

import io.requery.meta.Attribute;
import io.requery.query.Expression;
import io.requery.query.MutableTuple;
import java.util.Map;
import java.util.Map.Entry;

public class CompositeKey<T> extends MutableTuple {
    public CompositeKey(Map<? extends Attribute<T, ?>, ?> map) {
        super(map.size());
        if (!map.isEmpty()) {
            int i = 0;
            for (Entry entry : map.entrySet()) {
                int i2 = i + 1;
                set(i, (Expression) entry.getKey(), entry.getValue());
                i = i2;
            }
            return;
        }
        throw new IllegalArgumentException();
    }
}
