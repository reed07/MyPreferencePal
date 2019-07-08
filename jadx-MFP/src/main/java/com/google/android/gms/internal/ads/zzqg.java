package com.google.android.gms.internal.ads;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class zzqg {
    public static boolean zzc(XmlPullParser xmlPullParser, String str) throws XmlPullParserException {
        return xmlPullParser.getEventType() == 3 && xmlPullParser.getName().equals(str);
    }

    public static boolean zzd(XmlPullParser xmlPullParser, String str) throws XmlPullParserException {
        return zzf(xmlPullParser) && xmlPullParser.getName().equals(str);
    }

    public static boolean zzf(XmlPullParser xmlPullParser) throws XmlPullParserException {
        return xmlPullParser.getEventType() == 2;
    }
}
