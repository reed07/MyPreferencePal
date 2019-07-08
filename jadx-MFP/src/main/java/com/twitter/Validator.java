package com.twitter;

import com.twitter.Extractor.Entity;
import java.text.Normalizer;
import java.text.Normalizer.Form;

public class Validator {
    private Extractor extractor = new Extractor();
    protected int shortUrlLength = 23;
    protected int shortUrlLengthHttps = 23;

    public int getTweetLength(String str) {
        String normalize = Normalizer.normalize(str, Form.NFC);
        int codePointCount = normalize.codePointCount(0, normalize.length());
        for (Entity entity : this.extractor.extractURLsWithIndices(normalize)) {
            codePointCount = codePointCount + (entity.start - entity.end) + (entity.value.toLowerCase().startsWith("https://") ? this.shortUrlLengthHttps : this.shortUrlLength);
        }
        return codePointCount;
    }
}
