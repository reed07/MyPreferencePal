package com.uacf.core.mapping;

import java.io.IOException;

public interface Mapper<TInput, TOutput> {
    TOutput mapFrom(TInput tinput) throws IOException;

    TInput reverseMap(TOutput toutput);

    TOutput tryMapFrom(TInput tinput);
}
