package io.requery.converter;

import io.requery.Converter;
import java.net.URI;

public class URIConverter implements Converter<URI, String> {
    public Integer getPersistedSize() {
        return null;
    }

    public Class<URI> getMappedType() {
        return URI.class;
    }

    public Class<String> getPersistedType() {
        return String.class;
    }

    public String convertToPersisted(URI uri) {
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }

    public URI convertToMapped(Class<? extends URI> cls, String str) {
        if (str == null) {
            return null;
        }
        return URI.create(str);
    }
}
