package com.lightstep.tracer.shared;

import java.util.List;

public final class PropagatorStack<C> implements Propagator<C> {
    List<Propagator<C>> propagators;

    public SpanContext extract(C c) {
        for (int size = this.propagators.size() - 1; size >= 0; size--) {
            SpanContext extract = ((Propagator) this.propagators.get(size)).extract(c);
            if (extract != null) {
                return extract;
            }
        }
        return null;
    }

    public void inject(SpanContext spanContext, C c) {
        for (int i = 0; i < this.propagators.size(); i++) {
            ((Propagator) this.propagators.get(i)).inject(spanContext, c);
        }
    }
}
