package io.uacf.net.retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;

public class NullOnEmptyConverterFactory extends Factory {
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        final Converter nextResponseBodyConverter = retrofit.nextResponseBodyConverter(this, type, annotationArr);
        return new Converter<ResponseBody, Object>() {
            public Object convert(ResponseBody responseBody) throws IOException {
                if (responseBody.contentLength() == 0) {
                    return null;
                }
                return nextResponseBodyConverter.convert(responseBody);
            }
        };
    }
}
