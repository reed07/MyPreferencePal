package com.uacf.core.mapping;

import com.uacf.core.mapping.Mapper2;
import java.io.IOException;

public interface Mapper2<TMapper extends Mapper2, TInput> {
    <TOutput> TOutput mapFrom(TInput tinput) throws IOException;

    <TOutput> TInput reverseMap(TOutput toutput);

    <TOutput> TOutput tryMapFrom(TInput tinput);

    <TOutput> TMapper withType(Class<? extends TOutput> cls);
}
