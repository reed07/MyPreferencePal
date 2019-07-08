package com.brightcove.player.captioning;

import android.util.Log;
import android.util.Xml;
import com.brightcove.player.model.Block;
import com.brightcove.player.model.Element;
import com.brightcove.player.model.Length;
import com.brightcove.player.model.Region;
import com.brightcove.player.model.Region.DisplayAlign;
import com.brightcove.player.model.Span;
import com.brightcove.player.model.StyledElement;
import com.brightcove.player.model.StyledElement.FontStyle;
import com.brightcove.player.model.StyledElement.FontWeight;
import com.brightcove.player.model.StyledElement.TextAlign;
import com.brightcove.player.model.StyledElement.TextDecoration;
import com.brightcove.player.model.TTMLDocument;
import com.brightcove.player.util.StringUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class TTMLParser {
    private static final int HOURS = 3600000;
    private static final int MINUTES = 60000;
    private static final int SECONDS = 1000;
    private static final String TAG = "TTMLParser";
    private static final Pattern timeFormatPattern = Pattern.compile("^(\\d{2}+):(\\d{2}+):(\\d{2}+)[:.](\\d{2,3}+)$");

    public static class Attributes {
        public static final String BEGIN = "begin";
        public static final String BG_COLOR = "backgroundColor";
        public static final String COLOR = "color";
        public static final String DISPLAY_ALIGN = "displayAlign";
        public static final String END = "end";
        public static final String EXTENT = "extent";
        public static final String FONT_FAMILY = "fontFamily";
        public static final String FONT_SIZE = "fontSize";
        public static final String FONT_STYLE = "fontStyle";
        public static final String FONT_WEIGHT = "fontWeight";
        public static final String ID = "id";
        public static final String ORIGIN = "origin";
        public static final String REGION = "region";
        public static final String STYLE = "style";
        public static final String TEXT_ALIGN = "textAlign";
        public static final String TEXT_DECORATION = "textDecoration";
    }

    public static class Namespaces {
        public static final String DEFAULT = null;
        public static final String TTM = "http://www.w3.org/ns/ttml#metadata";
        public static final String TTS = "http://www.w3.org/ns/ttml#styling";
        public static final String XML = "http://www.w3.org/XML/1998/namespace";
    }

    public static class Tags {
        public static final String BODY = "body";
        public static final String BR = "br";
        public static final String CAPTION = "p";
        public static final String HEAD = "head";
        public static final String LAYOUT = "layout";
        public static final String REGION = "region";
        public static final String ROOT = "tt";
        public static final String SPAN = "span";
        public static final String STYLE = "style";
        public static final String STYLING = "styling";
    }

    private static int calculateDuration(int i, int i2, int i3, int i4) {
        return (i * HOURS) + (i2 * MINUTES) + (i3 * 1000) + i4;
    }

    private TTMLParser() {
    }

    public static TTMLDocument parseXml(InputStream inputStream, String str) throws XmlPullParserException, IOException {
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            newPullParser.setInput(inputStream, str);
            newPullParser.nextTag();
            return parseRoot(newPullParser);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static TTMLDocument parseXml(Reader reader) throws XmlPullParserException, IOException {
        try {
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            newPullParser.setInput(reader);
            newPullParser.nextTag();
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("detected XML encoding for TTML is: ");
            sb.append(newPullParser.getInputEncoding());
            Log.i(str, sb.toString());
            return parseRoot(newPullParser);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public static int parseTimeValue(String str) {
        if (!StringUtil.isEmpty(str)) {
            try {
                Matcher matcher = timeFormatPattern.matcher(str);
                if (matcher.find() && matcher.groupCount() >= 4) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    String group3 = matcher.group(3);
                    String group4 = matcher.group(4);
                    if (!StringUtil.isEmpty(group) && !StringUtil.isEmpty(group2) && !StringUtil.isEmpty(group3) && !StringUtil.isEmpty(group4)) {
                        return calculateDuration(Integer.parseInt(group), Integer.parseInt(group2), Integer.parseInt(group3), Integer.parseInt(group4));
                    }
                }
            } catch (NumberFormatException unused) {
                String str2 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("got invalid time format for caption: ");
                sb.append(str);
                Log.w(str2, sb.toString());
            }
        }
        return -1;
    }

    private static TTMLDocument parseRoot(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Map hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        xmlPullParser.require(2, Namespaces.DEFAULT, "tt");
        Map map = null;
        Block block = null;
        while (xmlPullParser.next() != 1) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name.equals("styling")) {
                    map = parseStyling(xmlPullParser);
                } else if (name.equals("layout")) {
                    hashMap = parseLayout(xmlPullParser);
                } else if (name.equals("body")) {
                    block = new Block();
                    parseStyledElementAttributes(block, xmlPullParser);
                    parseBlockAttributes(block, xmlPullParser);
                } else if (name.equals("p")) {
                    arrayList.add(parseCaption(xmlPullParser));
                }
            }
        }
        xmlPullParser.require(1, Namespaces.DEFAULT, null);
        return new TTMLDocument(hashMap, map, block, arrayList);
    }

    private static Map<String, StyledElement> parseStyling(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, Namespaces.DEFAULT, "styling");
        HashMap hashMap = new HashMap();
        while (true) {
            if (xmlPullParser.next() == 3 && xmlPullParser.getName().equals("styling")) {
                xmlPullParser.require(3, Namespaces.DEFAULT, "styling");
                return hashMap;
            } else if (xmlPullParser.getEventType() == 2 && xmlPullParser.getName().equals("style")) {
                StyledElement parseStyle = parseStyle(xmlPullParser);
                hashMap.put(parseStyle.getID(), parseStyle);
            }
        }
    }

    private static Map<String, Region> parseLayout(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, Namespaces.DEFAULT, "layout");
        HashMap hashMap = new HashMap();
        while (true) {
            if (xmlPullParser.next() == 3 && xmlPullParser.getName().equals("layout")) {
                xmlPullParser.require(3, Namespaces.DEFAULT, "layout");
                return hashMap;
            } else if (xmlPullParser.getEventType() == 2 && xmlPullParser.getName().equals("region")) {
                Region parseRegion = parseRegion(xmlPullParser);
                hashMap.put(parseRegion.getID(), parseRegion);
            }
        }
    }

    private static StyledElement parseStyle(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, Namespaces.DEFAULT, "style");
        StyledElement styledElement = new StyledElement();
        parseID(styledElement, xmlPullParser);
        parseStyledElementAttributes(styledElement, xmlPullParser);
        return styledElement;
    }

    private static BrightcoveClosedCaption parseCaption(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, Namespaces.DEFAULT, "p");
        BrightcoveClosedCaption brightcoveClosedCaption = new BrightcoveClosedCaption();
        parseStyledElementAttributes(brightcoveClosedCaption, xmlPullParser);
        parseBlockAttributes(brightcoveClosedCaption, xmlPullParser);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Span span = new Span();
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (xmlPullParser.next() == 3 && xmlPullParser.getName().equals("p")) {
                break;
            }
            int eventType = xmlPullParser.getEventType();
            if (eventType == 4) {
                String text = xmlPullParser.getText();
                if (!StringUtil.isEmpty(text)) {
                    if (span == null) {
                        span = new Span();
                    }
                    sb.append(text);
                }
            } else if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (name.equals("span")) {
                    if (span != null) {
                        span.setText(getText(sb));
                        arrayList2.add(span);
                        sb.delete(0, sb.length());
                    }
                    span = new Span();
                    parseStyledElementAttributes(span, xmlPullParser);
                } else if (name.equals("br")) {
                    if (sb.length() > 0) {
                        if (span == null) {
                            span = new Span();
                        }
                        span.setText(getText(sb));
                        arrayList2.add(span);
                        sb.delete(0, sb.length());
                        span = new Span((StyledElement) span);
                    }
                    arrayList.add(arrayList2);
                    arrayList2 = new ArrayList();
                }
            } else if (eventType == 3 && xmlPullParser.getName().equals("span")) {
                span.setText(getText(sb));
                arrayList2.add(span);
                span = null;
                sb.delete(0, sb.length());
            }
        }
        if (sb.length() > 0) {
            if (span == null) {
                span = new Span();
            }
            span.setText(getText(sb));
            arrayList2.add(span);
            arrayList.add(arrayList2);
        } else {
            arrayList.add(arrayList2);
        }
        brightcoveClosedCaption.setLines(arrayList);
        return brightcoveClosedCaption;
    }

    private static String getText(StringBuilder sb) {
        return sb.toString();
    }

    private static Region parseRegion(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, Namespaces.DEFAULT, "region");
        Region region = new Region();
        parseID(region, xmlPullParser);
        parseStyledElementAttributes(region, xmlPullParser);
        parsePositionalAttributes(region, xmlPullParser);
        if (xmlPullParser.nextTag() == 2 && xmlPullParser.getName().equals("style")) {
            parseStyledElementAttributes(region, xmlPullParser);
            parsePositionalAttributes(region, xmlPullParser);
        }
        return region;
    }

    private static void parseID(Element element, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (element != null) {
            String attributeValue = xmlPullParser.getAttributeValue(Namespaces.XML, "id");
            if (!StringUtil.isEmpty(attributeValue)) {
                element.setID(attributeValue);
            }
        }
    }

    private static void parseBlockAttributes(Block block, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (block != null) {
            String attributeValue = xmlPullParser.getAttributeValue(Namespaces.DEFAULT, Attributes.BEGIN);
            String attributeValue2 = xmlPullParser.getAttributeValue(Namespaces.DEFAULT, "end");
            int parseTimeValue = parseTimeValue(attributeValue);
            int parseTimeValue2 = parseTimeValue(attributeValue2);
            String attributeValue3 = xmlPullParser.getAttributeValue(Namespaces.DEFAULT, "region");
            block.setBeginTime(parseTimeValue);
            block.setEndTime(parseTimeValue2);
            if (!StringUtil.isEmpty(attributeValue3)) {
                block.setRegion(attributeValue3);
            }
        }
    }

    private static void parseStyledElementAttributes(StyledElement styledElement, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (styledElement != null) {
            String attributeValue = xmlPullParser.getAttributeValue(Namespaces.DEFAULT, "style");
            if (!StringUtil.isEmpty(attributeValue)) {
                styledElement.setStyleName(attributeValue);
            }
            String attributeValue2 = xmlPullParser.getAttributeValue(Namespaces.TTS, "color");
            if (!StringUtil.isEmpty(attributeValue2)) {
                styledElement.setColor(attributeValue2);
            }
            String attributeValue3 = xmlPullParser.getAttributeValue(Namespaces.TTS, "backgroundColor");
            if (!StringUtil.isEmpty(attributeValue3)) {
                styledElement.setBackgroundColor(attributeValue3);
            }
            String attributeValue4 = xmlPullParser.getAttributeValue(Namespaces.TTS, "fontStyle");
            if (!StringUtil.isEmpty(attributeValue4)) {
                styledElement.setFontStyle(FontStyle.valueOf(attributeValue4.toUpperCase(Locale.getDefault())));
            }
            String attributeValue5 = xmlPullParser.getAttributeValue(Namespaces.TTS, "textAlign");
            if (!StringUtil.isEmpty(attributeValue5)) {
                styledElement.setTextAlign(TextAlign.valueOf(attributeValue5.toUpperCase(Locale.getDefault())));
            }
            String attributeValue6 = xmlPullParser.getAttributeValue(Namespaces.TTS, "fontWeight");
            if (!StringUtil.isEmpty(attributeValue6)) {
                styledElement.setFontWeight(FontWeight.valueOf(attributeValue6.toUpperCase(Locale.getDefault())));
            }
            String attributeValue7 = xmlPullParser.getAttributeValue(Namespaces.TTS, "textDecoration");
            if (!StringUtil.isEmpty(attributeValue7)) {
                styledElement.setTextDecoration(TextDecoration.valueOf(attributeValue7.toUpperCase(Locale.getDefault())));
            }
            String attributeValue8 = xmlPullParser.getAttributeValue(Namespaces.TTS, "fontSize");
            if (!StringUtil.isEmpty(attributeValue8)) {
                styledElement.setFontSize(new Length(attributeValue8));
            }
            String attributeValue9 = xmlPullParser.getAttributeValue(Namespaces.TTS, "fontFamily");
            if (!StringUtil.isEmpty(attributeValue9)) {
                styledElement.setFontFamily(attributeValue9);
            }
        }
    }

    private static void parsePositionalAttributes(Region region, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (region != null) {
            String attributeValue = xmlPullParser.getAttributeValue(Namespaces.TTS, "origin");
            String attributeValue2 = xmlPullParser.getAttributeValue(Namespaces.TTS, "extent");
            String attributeValue3 = xmlPullParser.getAttributeValue(Namespaces.TTS, "displayAlign");
            if (!StringUtil.isEmpty(attributeValue)) {
                String[] split = attributeValue.split(" ");
                region.setOriginX(new Length(split[0]));
                region.setOriginY(new Length(split[1]));
            }
            if (!StringUtil.isEmpty(attributeValue2)) {
                String[] split2 = attributeValue2.split(" ");
                region.setExtentX(new Length(split2[0]));
                region.setExtentY(new Length(split2[1]));
            }
            if (!StringUtil.isEmpty(attributeValue3)) {
                region.setDisplayAlign(DisplayAlign.fromString(attributeValue3));
            }
        }
    }
}
