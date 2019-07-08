package io.requery.converter;

import io.requery.Converter;
import io.requery.PersistenceException;
import java.net.MalformedURLException;
import java.net.URL;

public class URLConverter implements Converter<URL, String> {
    public Integer getPersistedSize() {
        return null;
    }

    public Class<URL> getMappedType() {
        return URL.class;
    }

    public Class<String> getPersistedType() {
        return String.class;
    }

    public String convertToPersisted(URL url) {
        if (url == null) {
            return null;
        }
        return url.toString();
    }

    public URL convertToMapped(Class<? extends URL> cls, String str) {
        if (str == null) {
            return null;
        }
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new PersistenceException((Throwable) e);
        }
    }
}
