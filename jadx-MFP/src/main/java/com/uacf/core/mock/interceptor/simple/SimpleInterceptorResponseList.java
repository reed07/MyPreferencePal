package com.uacf.core.mock.interceptor.simple;

import java.util.List;

public final class SimpleInterceptorResponseList {
    int index = -1;
    final List<SimpleInterceptorResponse> responses;

    public SimpleInterceptorResponseList(List<SimpleInterceptorResponse> list) {
        this.responses = list;
    }

    public SimpleInterceptorResponse next() {
        this.index++;
        if (this.index >= this.responses.size()) {
            this.index = 0;
        }
        return (SimpleInterceptorResponse) this.responses.get(this.index);
    }
}
