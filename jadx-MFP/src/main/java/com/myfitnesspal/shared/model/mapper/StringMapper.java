package com.myfitnesspal.shared.model.mapper;

import com.uacf.core.mapping.Mapper2;
import com.uacf.core.util.Strings;
import java.io.IOException;

public class StringMapper implements Mapper2<StringMapper, String> {
    public <TOutput> StringMapper withType(Class<? extends TOutput> cls) {
        return this;
    }

    private static String str(Object obj) {
        return Strings.toString(obj);
    }

    public <TOutput> String reverseMap(TOutput toutput) {
        return str(toutput);
    }

    public <TOutput> TOutput mapFrom(String str) throws IOException {
        return str(str);
    }

    public <TOutput> TOutput tryMapFrom(String str) {
        return str(str);
    }

    public <TOutput> TOutput mapFrom(String str, Class<? extends TOutput> cls) throws IOException {
        return str(str);
    }

    public <TOutput> TOutput tryMapFrom(String str, Class<? extends TOutput> cls) {
        return str(str);
    }
}
