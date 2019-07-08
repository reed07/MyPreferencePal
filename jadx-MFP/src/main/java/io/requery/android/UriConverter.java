package io.requery.android;

import android.net.Uri;
import io.requery.Converter;

public class UriConverter implements Converter<Uri, String> {
    public Integer getPersistedSize() {
        return null;
    }

    public Class<Uri> getMappedType() {
        return Uri.class;
    }

    public Class<String> getPersistedType() {
        return String.class;
    }

    public String convertToPersisted(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }

    public Uri convertToMapped(Class<? extends Uri> cls, String str) {
        if (str == null) {
            return null;
        }
        return Uri.parse(str);
    }
}
