package org.apache.commons.io;

import com.google.android.exoplayer2.C;
import java.nio.charset.Charset;

public class Charsets {
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset US_ASCII = Charset.forName(C.ASCII_NAME);
    public static final Charset UTF_16 = Charset.forName(C.UTF16_NAME);
    public static final Charset UTF_16BE = Charset.forName("UTF-16BE");
    public static final Charset UTF_16LE = Charset.forName("UTF-16LE");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
}
