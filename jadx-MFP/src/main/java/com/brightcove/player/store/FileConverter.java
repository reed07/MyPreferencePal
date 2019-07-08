package com.brightcove.player.store;

import io.requery.Converter;
import java.io.File;

public class FileConverter implements Converter<File, String> {
    public Integer getPersistedSize() {
        return null;
    }

    public Class<File> getMappedType() {
        return File.class;
    }

    public Class<String> getPersistedType() {
        return String.class;
    }

    public String convertToPersisted(File file) {
        if (file == null) {
            return null;
        }
        return file.getAbsolutePath();
    }

    public File convertToMapped(Class<? extends File> cls, String str) {
        if (str == null) {
            return null;
        }
        return new File(str);
    }
}
