package io.requery.android;

import io.requery.sql.GenericMapping;
import io.requery.sql.Platform;

public class DefaultMapping extends GenericMapping {
    public DefaultMapping(Platform platform) {
        super(platform);
        addConverter(new UriConverter(), new Class[0]);
    }
}
