package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.input.XmlStreamReader;

public class XmlStreamWriter extends Writer {
    static final Pattern ENCODING_PATTERN = XmlStreamReader.ENCODING_PATTERN;
    private final String defaultEncoding;
    private String encoding;
    private final OutputStream out;
    private Writer writer;
    private StringWriter xmlPrologWriter;

    public void close() throws IOException {
        if (this.writer == null) {
            this.encoding = this.defaultEncoding;
            this.writer = new OutputStreamWriter(this.out, this.encoding);
            this.writer.write(this.xmlPrologWriter.toString());
        }
        this.writer.close();
    }

    public void flush() throws IOException {
        Writer writer2 = this.writer;
        if (writer2 != null) {
            writer2.flush();
        }
    }

    private void detectEncoding(char[] cArr, int i, int i2) throws IOException {
        StringBuffer buffer = this.xmlPrologWriter.getBuffer();
        int length = buffer.length() + i2 > 4096 ? 4096 - buffer.length() : i2;
        this.xmlPrologWriter.write(cArr, i, length);
        if (buffer.length() >= 5) {
            if (buffer.substring(0, 5).equals("<?xml")) {
                int indexOf = buffer.indexOf("?>");
                if (indexOf > 0) {
                    Matcher matcher = ENCODING_PATTERN.matcher(buffer.substring(0, indexOf));
                    if (matcher.find()) {
                        this.encoding = matcher.group(1).toUpperCase();
                        String str = this.encoding;
                        this.encoding = str.substring(1, str.length() - 1);
                    } else {
                        this.encoding = this.defaultEncoding;
                    }
                } else if (buffer.length() >= 4096) {
                    this.encoding = this.defaultEncoding;
                }
            } else {
                this.encoding = this.defaultEncoding;
            }
            String str2 = this.encoding;
            if (str2 != null) {
                this.xmlPrologWriter = null;
                this.writer = new OutputStreamWriter(this.out, str2);
                this.writer.write(buffer.toString());
                if (i2 > length) {
                    this.writer.write(cArr, i + length, i2 - length);
                }
            }
        }
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        if (this.xmlPrologWriter != null) {
            detectEncoding(cArr, i, i2);
        } else {
            this.writer.write(cArr, i, i2);
        }
    }
}
