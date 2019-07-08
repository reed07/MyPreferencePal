package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Xml;
import com.brightcove.player.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentList;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTimelineElement;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase;
import com.google.android.exoplayer2.upstream.ParsingLoadable.Parser;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public class DashManifestParser extends DefaultHandler implements Parser<DashManifest> {
    private static final Pattern CEA_608_ACCESSIBILITY_PATTERN = Pattern.compile("CC([1-4])=.*");
    private static final Pattern CEA_708_ACCESSIBILITY_PATTERN = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    private static final Pattern FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final String TAG = "MpdParser";
    private final String contentId;
    private final XmlPullParserFactory xmlParserFactory;

    protected static final class RepresentationInfo {
        public final String baseUrl;
        public final ArrayList<SchemeData> drmSchemeDatas;
        public final String drmSchemeType;
        public final Format format;
        public final ArrayList<Descriptor> inbandEventStreams;
        public final long revisionId;
        public final SegmentBase segmentBase;

        public RepresentationInfo(Format format2, String str, SegmentBase segmentBase2, String str2, ArrayList<SchemeData> arrayList, ArrayList<Descriptor> arrayList2, long j) {
            this.format = format2;
            this.baseUrl = str;
            this.segmentBase = segmentBase2;
            this.drmSchemeType = str2;
            this.drmSchemeDatas = arrayList;
            this.inbandEventStreams = arrayList2;
            this.revisionId = j;
        }
    }

    public DashManifestParser() {
        this(null);
    }

    public DashManifestParser(String str) {
        this.contentId = str;
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    public DashManifest parse(Uri uri, InputStream inputStream) throws IOException {
        try {
            XmlPullParser newPullParser = this.xmlParserFactory.newPullParser();
            newPullParser.setInput(inputStream, null);
            if (newPullParser.next() == 2 && "MPD".equals(newPullParser.getName())) {
                return parseMediaPresentationDescription(newPullParser, uri.toString());
            }
            throw new ParserException("inputStream does not contain a valid media presentation description");
        } catch (XmlPullParserException e) {
            throw new ParserException((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0184 A[LOOP:0: B:20:0x006c->B:67:0x0184, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0144 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.manifest.DashManifest parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser r35, java.lang.String r36) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r34 = this;
            r0 = r35
            java.lang.String r1 = "availabilityStartTime"
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r5 = parseDateTime(r0, r1, r2)
            java.lang.String r1 = "mediaPresentationDuration"
            long r7 = parseDuration(r0, r1, r2)
            java.lang.String r1 = "minBufferTime"
            long r9 = parseDuration(r0, r1, r2)
            java.lang.String r1 = "type"
            r4 = 0
            java.lang.String r1 = r0.getAttributeValue(r4, r1)
            r12 = 0
            if (r1 == 0) goto L_0x002e
            java.lang.String r13 = "dynamic"
            boolean r1 = r13.equals(r1)
            if (r1 == 0) goto L_0x002e
            r1 = 1
            goto L_0x002f
        L_0x002e:
            r1 = 0
        L_0x002f:
            if (r1 == 0) goto L_0x0038
            java.lang.String r13 = "minimumUpdatePeriod"
            long r13 = parseDuration(r0, r13, r2)
            goto L_0x0039
        L_0x0038:
            r13 = r2
        L_0x0039:
            if (r1 == 0) goto L_0x0042
            java.lang.String r15 = "timeShiftBufferDepth"
            long r15 = parseDuration(r0, r15, r2)
            goto L_0x0043
        L_0x0042:
            r15 = r2
        L_0x0043:
            if (r1 == 0) goto L_0x004c
            java.lang.String r4 = "suggestedPresentationDelay"
            long r18 = parseDuration(r0, r4, r2)
            goto L_0x004e
        L_0x004c:
            r18 = r2
        L_0x004e:
            java.lang.String r4 = "publishTime"
            long r20 = parseDateTime(r0, r4, r2)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            if (r1 == 0) goto L_0x005e
            r22 = r2
            goto L_0x0060
        L_0x005e:
            r22 = 0
        L_0x0060:
            r11 = r36
            r2 = r22
            r22 = 0
            r25 = 0
            r26 = 0
            r27 = 0
        L_0x006c:
            r35.next()
            r28 = r15
            java.lang.String r15 = "BaseURL"
            boolean r15 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r15)
            if (r15 == 0) goto L_0x008c
            if (r12 != 0) goto L_0x0084
            java.lang.String r11 = parseBaseUrl(r0, r11)
            r32 = r13
            r12 = 1
            goto L_0x013c
        L_0x0084:
            r30 = r2
            r36 = r12
            r32 = r13
            goto L_0x0138
        L_0x008c:
            java.lang.String r15 = "ProgramInformation"
            boolean r15 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r15)
            if (r15 == 0) goto L_0x009e
            com.google.android.exoplayer2.source.dash.manifest.ProgramInformation r15 = r34.parseProgramInformation(r35)
            r32 = r13
            r25 = r15
            goto L_0x013c
        L_0x009e:
            java.lang.String r15 = "UTCTiming"
            boolean r15 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r15)
            if (r15 == 0) goto L_0x00b0
            com.google.android.exoplayer2.source.dash.manifest.UtcTimingElement r15 = r34.parseUtcTiming(r35)
            r32 = r13
            r26 = r15
            goto L_0x013c
        L_0x00b0:
            java.lang.String r15 = "Location"
            boolean r15 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r15)
            if (r15 == 0) goto L_0x00c6
            java.lang.String r15 = r35.nextText()
            android.net.Uri r15 = android.net.Uri.parse(r15)
            r32 = r13
            r27 = r15
            goto L_0x013c
        L_0x00c6:
            java.lang.String r15 = "Period"
            boolean r15 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r15)
            if (r15 == 0) goto L_0x012f
            if (r22 != 0) goto L_0x012f
            r15 = r34
            r36 = r12
            android.util.Pair r12 = r15.parsePeriod(r0, r11, r2)
            r30 = r2
            java.lang.Object r2 = r12.first
            com.google.android.exoplayer2.source.dash.manifest.Period r2 = (com.google.android.exoplayer2.source.dash.manifest.Period) r2
            r32 = r13
            long r13 = r2.startMs
            r23 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r3 = (r13 > r23 ? 1 : (r13 == r23 ? 0 : -1))
            if (r3 != 0) goto L_0x010b
            if (r1 == 0) goto L_0x00f0
            r22 = 1
            goto L_0x012a
        L_0x00f0:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unable to determine start of period "
            r1.append(r2)
            int r2 = r4.size()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x010b:
            java.lang.Object r3 = r12.second
            java.lang.Long r3 = (java.lang.Long) r3
            long r12 = r3.longValue()
            r23 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r3 = (r12 > r23 ? 1 : (r12 == r23 ? 0 : -1))
            if (r3 != 0) goto L_0x0122
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            goto L_0x0125
        L_0x0122:
            long r14 = r2.startMs
            long r12 = r12 + r14
        L_0x0125:
            r4.add(r2)
            r30 = r12
        L_0x012a:
            r12 = r36
            r2 = r30
            goto L_0x013c
        L_0x012f:
            r30 = r2
            r36 = r12
            r32 = r13
            maybeSkipTag(r35)
        L_0x0138:
            r12 = r36
            r2 = r30
        L_0x013c:
            java.lang.String r13 = "MPD"
            boolean r13 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r0, r13)
            if (r13 == 0) goto L_0x0184
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r0 != 0) goto L_0x015e
            int r0 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0153
            r7 = r2
            goto L_0x015e
        L_0x0153:
            if (r1 == 0) goto L_0x0156
            goto L_0x015e
        L_0x0156:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "Unable to determine duration of static manifest."
            r0.<init>(r1)
            throw r0
        L_0x015e:
            boolean r0 = r4.isEmpty()
            if (r0 != 0) goto L_0x017c
            r23 = r4
            r4 = r34
            r11 = r1
            r12 = r32
            r14 = r28
            r16 = r18
            r18 = r20
            r20 = r25
            r21 = r26
            r22 = r27
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r0 = r4.buildMediaPresentationDescription(r5, r7, r9, r11, r12, r14, r16, r18, r20, r21, r22, r23)
            return r0
        L_0x017c:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "No periods found."
            r0.<init>(r1)
            throw r0
        L_0x0184:
            r15 = r28
            r13 = r32
            goto L_0x006c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser, java.lang.String):com.google.android.exoplayer2.source.dash.manifest.DashManifest");
    }

    /* access modifiers changed from: protected */
    public DashManifest buildMediaPresentationDescription(long j, long j2, long j3, boolean z, long j4, long j5, long j6, long j7, ProgramInformation programInformation, UtcTimingElement utcTimingElement, Uri uri, List<Period> list) {
        DashManifest dashManifest = new DashManifest(j, j2, j3, z, j4, j5, j6, j7, programInformation, utcTimingElement, uri, list);
        return dashManifest;
    }

    /* access modifiers changed from: protected */
    public UtcTimingElement parseUtcTiming(XmlPullParser xmlPullParser) {
        return buildUtcTimingElement(xmlPullParser.getAttributeValue(null, "schemeIdUri"), xmlPullParser.getAttributeValue(null, "value"));
    }

    /* access modifiers changed from: protected */
    public UtcTimingElement buildUtcTimingElement(String str, String str2) {
        return new UtcTimingElement(str, str2);
    }

    /* access modifiers changed from: protected */
    public Pair<Period, Long> parsePeriod(XmlPullParser xmlPullParser, String str, long j) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "id");
        long parseDuration = parseDuration(xmlPullParser, TtmlNode.START, j);
        long parseDuration2 = parseDuration(xmlPullParser, "duration", -9223372036854775807L);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        boolean z = false;
        SegmentBase segmentBase = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                if (!z) {
                    str = parseBaseUrl(xmlPullParser, str);
                    z = true;
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "AdaptationSet")) {
                arrayList.add(parseAdaptationSet(xmlPullParser, str, segmentBase));
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "EventStream")) {
                arrayList2.add(parseEventStream(xmlPullParser));
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentBase")) {
                segmentBase = parseSegmentBase(xmlPullParser, null);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentList")) {
                segmentBase = parseSegmentList(xmlPullParser, null);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentTemplate")) {
                segmentBase = parseSegmentTemplate(xmlPullParser, null);
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Period"));
        return Pair.create(buildPeriod(attributeValue, parseDuration, arrayList, arrayList2), Long.valueOf(parseDuration2));
    }

    /* access modifiers changed from: protected */
    public Period buildPeriod(String str, long j, List<AdaptationSet> list, List<EventStream> list2) {
        Period period = new Period(str, j, list, list2);
        return period;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x028b A[LOOP:0: B:1:0x006a->B:57:0x028b, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0252 A[EDGE_INSN: B:58:0x0252->B:51:0x0252 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.manifest.AdaptationSet parseAdaptationSet(org.xmlpull.v1.XmlPullParser r40, java.lang.String r41, com.google.android.exoplayer2.source.dash.manifest.SegmentBase r42) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r39 = this;
            r15 = r39
            r14 = r40
            java.lang.String r0 = "id"
            r1 = -1
            int r16 = parseInt(r14, r0, r1)
            int r0 = r39.parseContentType(r40)
            java.lang.String r2 = "mimeType"
            r13 = 0
            java.lang.String r17 = r14.getAttributeValue(r13, r2)
            java.lang.String r2 = "codecs"
            java.lang.String r18 = r14.getAttributeValue(r13, r2)
            java.lang.String r2 = "width"
            int r19 = parseInt(r14, r2, r1)
            java.lang.String r2 = "height"
            int r20 = parseInt(r14, r2, r1)
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r21 = parseFrameRate(r14, r2)
            java.lang.String r2 = "audioSamplingRate"
            int r22 = parseInt(r14, r2, r1)
            java.lang.String r2 = "lang"
            java.lang.String r2 = r14.getAttributeValue(r13, r2)
            java.lang.String r3 = "label"
            java.lang.String r23 = r14.getAttributeValue(r13, r3)
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r24 = 0
            r7 = r41
            r28 = r42
            r5 = r0
            r6 = r2
            r29 = r13
            r25 = 0
            r26 = 0
            r27 = -1
        L_0x006a:
            r40.next()
            java.lang.String r0 = "BaseURL"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r0 == 0) goto L_0x00a2
            if (r25 != 0) goto L_0x0090
            java.lang.String r0 = parseBaseUrl(r14, r7)
            r1 = 1
            r32 = r0
            r31 = r6
            r6 = r8
            r34 = r9
            r35 = r10
            r7 = r11
            r37 = r12
            r38 = r13
            r1 = r14
            r25 = 1
            r8 = r5
            goto L_0x024a
        L_0x0090:
            r2 = r5
            r31 = r6
            r32 = r7
            r6 = r8
            r34 = r9
            r35 = r10
            r7 = r11
            r37 = r12
            r38 = r13
            r1 = r14
            goto L_0x0249
        L_0x00a2:
            java.lang.String r0 = "ContentProtection"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r0 == 0) goto L_0x00d3
            android.util.Pair r0 = r39.parseContentProtection(r40)
            java.lang.Object r1 = r0.first
            if (r1 == 0) goto L_0x00b8
            java.lang.Object r1 = r0.first
            r29 = r1
            java.lang.String r29 = (java.lang.String) r29
        L_0x00b8:
            java.lang.Object r1 = r0.second
            if (r1 == 0) goto L_0x00c1
            java.lang.Object r0 = r0.second
            r12.add(r0)
        L_0x00c1:
            r31 = r6
            r32 = r7
            r6 = r8
            r34 = r9
            r35 = r10
            r7 = r11
            r37 = r12
            r38 = r13
            r1 = r14
            r8 = r5
            goto L_0x024a
        L_0x00d3:
            java.lang.String r0 = "ContentComponent"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r0 == 0) goto L_0x00ff
            java.lang.String r0 = "lang"
            java.lang.String r0 = r14.getAttributeValue(r13, r0)
            java.lang.String r6 = checkLanguageConsistency(r6, r0)
            int r0 = r39.parseContentType(r40)
            int r0 = checkContentTypeConsistency(r5, r0)
            r31 = r6
            r32 = r7
            r6 = r8
            r34 = r9
            r35 = r10
            r7 = r11
            r37 = r12
            r38 = r13
            r1 = r14
            r8 = r0
            goto L_0x024a
        L_0x00ff:
            java.lang.String r0 = "Role"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r0 == 0) goto L_0x011f
            int r0 = r39.parseRole(r40)
            r26 = r26 | r0
            r31 = r6
            r32 = r7
            r6 = r8
            r34 = r9
            r35 = r10
            r7 = r11
            r37 = r12
            r38 = r13
            r1 = r14
            r8 = r5
            goto L_0x024a
        L_0x011f:
            java.lang.String r0 = "AudioChannelConfiguration"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r0 == 0) goto L_0x013d
            int r27 = r39.parseAudioChannelConfiguration(r40)
            r31 = r6
            r32 = r7
            r6 = r8
            r34 = r9
            r35 = r10
            r7 = r11
            r37 = r12
            r38 = r13
            r1 = r14
            r8 = r5
            goto L_0x024a
        L_0x013d:
            java.lang.String r0 = "Accessibility"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r0 == 0) goto L_0x0160
            java.lang.String r0 = "Accessibility"
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r0 = parseDescriptor(r14, r0)
            r10.add(r0)
            r2 = r5
            r31 = r6
            r32 = r7
            r6 = r8
            r34 = r9
            r35 = r10
            r7 = r11
            r37 = r12
            r38 = r13
            r1 = r14
            goto L_0x0249
        L_0x0160:
            java.lang.String r0 = "SupplementalProperty"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r0 == 0) goto L_0x0183
            java.lang.String r0 = "SupplementalProperty"
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r0 = parseDescriptor(r14, r0)
            r9.add(r0)
            r2 = r5
            r31 = r6
            r32 = r7
            r6 = r8
            r34 = r9
            r35 = r10
            r7 = r11
            r37 = r12
            r38 = r13
            r1 = r14
            goto L_0x0249
        L_0x0183:
            java.lang.String r0 = "Representation"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r0 == 0) goto L_0x01d6
            r0 = r39
            r1 = r40
            r2 = r7
            r3 = r23
            r4 = r17
            r30 = r5
            r5 = r18
            r31 = r6
            r6 = r19
            r32 = r7
            r7 = r20
            r33 = r8
            r8 = r21
            r34 = r9
            r9 = r27
            r35 = r10
            r10 = r22
            r36 = r11
            r11 = r31
            r37 = r12
            r12 = r26
            r38 = r13
            r13 = r35
            r14 = r28
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r0 = r0.parseRepresentation(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            com.google.android.exoplayer2.Format r1 = r0.format
            int r1 = r15.getContentType(r1)
            r2 = r30
            int r1 = checkContentTypeConsistency(r2, r1)
            r6 = r33
            r6.add(r0)
            r8 = r1
            r7 = r36
            r1 = r40
            goto L_0x024a
        L_0x01d6:
            r2 = r5
            r31 = r6
            r32 = r7
            r6 = r8
            r34 = r9
            r35 = r10
            r36 = r11
            r37 = r12
            r38 = r13
            java.lang.String r0 = "SegmentBase"
            r1 = r40
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r1, r0)
            if (r0 == 0) goto L_0x01fe
            r0 = r28
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r0 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase) r0
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r0 = r15.parseSegmentBase(r1, r0)
            r28 = r0
            r8 = r2
            r7 = r36
            goto L_0x024a
        L_0x01fe:
            java.lang.String r0 = "SegmentList"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r1, r0)
            if (r0 == 0) goto L_0x0214
            r0 = r28
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r0 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentList) r0
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r0 = r15.parseSegmentList(r1, r0)
            r28 = r0
            r8 = r2
            r7 = r36
            goto L_0x024a
        L_0x0214:
            java.lang.String r0 = "SegmentTemplate"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r1, r0)
            if (r0 == 0) goto L_0x022a
            r0 = r28
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r0 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate) r0
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r0 = r15.parseSegmentTemplate(r1, r0)
            r28 = r0
            r8 = r2
            r7 = r36
            goto L_0x024a
        L_0x022a:
            java.lang.String r0 = "InbandEventStream"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r1, r0)
            if (r0 == 0) goto L_0x023e
            java.lang.String r0 = "InbandEventStream"
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r0 = parseDescriptor(r1, r0)
            r7 = r36
            r7.add(r0)
            goto L_0x0249
        L_0x023e:
            r7 = r36
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r40)
            if (r0 == 0) goto L_0x0249
            r39.parseAdaptationSetChild(r40)
        L_0x0249:
            r8 = r2
        L_0x024a:
            java.lang.String r0 = "AdaptationSet"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r1, r0)
            if (r0 == 0) goto L_0x028b
            java.util.ArrayList r9 = new java.util.ArrayList
            int r0 = r6.size()
            r9.<init>(r0)
            r10 = 0
        L_0x025c:
            int r0 = r6.size()
            if (r10 >= r0) goto L_0x027c
            java.lang.Object r0 = r6.get(r10)
            r1 = r0
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r1 = (com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.RepresentationInfo) r1
            java.lang.String r2 = r15.contentId
            r0 = r39
            r3 = r29
            r4 = r37
            r5 = r7
            com.google.android.exoplayer2.source.dash.manifest.Representation r0 = r0.buildRepresentation(r1, r2, r3, r4, r5)
            r9.add(r0)
            int r10 = r10 + 1
            goto L_0x025c
        L_0x027c:
            r0 = r39
            r1 = r16
            r2 = r8
            r3 = r9
            r4 = r35
            r5 = r34
            com.google.android.exoplayer2.source.dash.manifest.AdaptationSet r0 = r0.buildAdaptationSet(r1, r2, r3, r4, r5)
            return r0
        L_0x028b:
            r14 = r1
            r11 = r7
            r5 = r8
            r7 = r32
            r9 = r34
            r10 = r35
            r12 = r37
            r13 = r38
            r8 = r6
            r6 = r31
            goto L_0x006a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseAdaptationSet(org.xmlpull.v1.XmlPullParser, java.lang.String, com.google.android.exoplayer2.source.dash.manifest.SegmentBase):com.google.android.exoplayer2.source.dash.manifest.AdaptationSet");
    }

    /* access modifiers changed from: protected */
    public AdaptationSet buildAdaptationSet(int i, int i2, List<Representation> list, List<Descriptor> list2, List<Descriptor> list3) {
        AdaptationSet adaptationSet = new AdaptationSet(i, i2, list, list2, list3);
        return adaptationSet;
    }

    /* access modifiers changed from: protected */
    public int parseContentType(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "contentType");
        if (TextUtils.isEmpty(attributeValue)) {
            return -1;
        }
        if (MimeTypes.BASE_TYPE_AUDIO.equals(attributeValue)) {
            return 1;
        }
        if ("video".equals(attributeValue)) {
            return 2;
        }
        if ("text".equals(attributeValue)) {
            return 3;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public int getContentType(Format format) {
        String str = format.sampleMimeType;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (MimeTypes.isVideo(str)) {
            return 2;
        }
        if (MimeTypes.isAudio(str)) {
            return 1;
        }
        if (mimeTypeIsRawText(str)) {
            return 3;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.util.Pair<java.lang.String, com.google.android.exoplayer2.drm.DrmInitData.SchemeData> parseContentProtection(org.xmlpull.v1.XmlPullParser r17) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r16 = this;
            r0 = r17
            java.lang.String r1 = "schemeIdUri"
            r2 = 0
            java.lang.String r1 = r0.getAttributeValue(r2, r1)
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x00a0
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r1)
            r5 = -1
            int r6 = r1.hashCode()
            r7 = 489446379(0x1d2c5beb, float:2.281153E-21)
            if (r6 == r7) goto L_0x003c
            r7 = 755418770(0x2d06c692, float:7.66111E-12)
            if (r6 == r7) goto L_0x0031
            r7 = 1812765994(0x6c0c9d2a, float:6.799672E26)
            if (r6 == r7) goto L_0x0026
            goto L_0x0047
        L_0x0026:
            java.lang.String r6 = "urn:mpeg:dash:mp4protection:2011"
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L_0x0047
            r1 = 0
            goto L_0x0048
        L_0x0031:
            java.lang.String r6 = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed"
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L_0x0047
            r1 = 2
            goto L_0x0048
        L_0x003c:
            java.lang.String r6 = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95"
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L_0x0047
            r1 = 1
            goto L_0x0048
        L_0x0047:
            r1 = -1
        L_0x0048:
            switch(r1) {
                case 0: goto L_0x005a;
                case 1: goto L_0x0053;
                case 2: goto L_0x004c;
                default: goto L_0x004b;
            }
        L_0x004b:
            goto L_0x00a0
        L_0x004c:
            java.util.UUID r1 = com.google.android.exoplayer2.C.WIDEVINE_UUID
            r5 = r2
            r6 = r5
            r7 = r6
            r8 = 0
            goto L_0x00a5
        L_0x0053:
            java.util.UUID r1 = com.google.android.exoplayer2.C.PLAYREADY_UUID
            r5 = r2
            r6 = r5
            r7 = r6
            r8 = 0
            goto L_0x00a5
        L_0x005a:
            java.lang.String r1 = "value"
            java.lang.String r1 = r0.getAttributeValue(r2, r1)
            java.lang.String r5 = "default_KID"
            java.lang.String r5 = com.google.android.exoplayer2.util.XmlPullParserUtil.getAttributeValueIgnorePrefix(r0, r5)
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x009b
            java.lang.String r6 = "00000000-0000-0000-0000-000000000000"
            boolean r6 = r6.equals(r5)
            if (r6 != 0) goto L_0x009b
            java.lang.String r6 = "\\s+"
            java.lang.String[] r5 = r5.split(r6)
            int r6 = r5.length
            java.util.UUID[] r6 = new java.util.UUID[r6]
            r7 = 0
        L_0x007f:
            int r8 = r5.length
            if (r7 >= r8) goto L_0x008d
            r8 = r5[r7]
            java.util.UUID r8 = java.util.UUID.fromString(r8)
            r6[r7] = r8
            int r7 = r7 + 1
            goto L_0x007f
        L_0x008d:
            java.util.UUID r5 = com.google.android.exoplayer2.C.COMMON_PSSH_UUID
            byte[] r5 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.buildPsshAtom(r5, r6, r2)
            java.util.UUID r6 = com.google.android.exoplayer2.C.COMMON_PSSH_UUID
            r7 = r2
            r8 = 0
            r15 = r6
            r6 = r1
            r1 = r15
            goto L_0x00a5
        L_0x009b:
            r6 = r1
            r1 = r2
            r5 = r1
            r7 = r5
            goto L_0x00a4
        L_0x00a0:
            r1 = r2
            r5 = r1
            r6 = r5
            r7 = r6
        L_0x00a4:
            r8 = 0
        L_0x00a5:
            r17.next()
            java.lang.String r9 = "ms:laurl"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r9)
            if (r9 == 0) goto L_0x00bc
            java.lang.String r7 = "licenseUrl"
            java.lang.String r7 = r0.getAttributeValue(r2, r7)
            r10 = r1
            r13 = r5
            r11 = r7
            r14 = r8
            goto L_0x0140
        L_0x00bc:
            java.lang.String r9 = "widevine:license"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r9)
            if (r9 == 0) goto L_0x00de
            java.lang.String r8 = "robustness_level"
            java.lang.String r8 = r0.getAttributeValue(r2, r8)
            if (r8 == 0) goto L_0x00d7
            java.lang.String r9 = "HW"
            boolean r8 = r8.startsWith(r9)
            if (r8 == 0) goto L_0x00d7
            r8 = 1
            goto L_0x00d8
        L_0x00d7:
            r8 = 0
        L_0x00d8:
            r10 = r1
            r13 = r5
            r11 = r7
            r14 = r8
            goto L_0x0140
        L_0x00de:
            r9 = 4
            if (r5 != 0) goto L_0x010e
            java.lang.String r10 = "pssh"
            boolean r10 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTagIgnorePrefix(r0, r10)
            if (r10 == 0) goto L_0x010e
            int r10 = r17.next()
            if (r10 != r9) goto L_0x010e
            java.lang.String r1 = r17.getText()
            byte[] r1 = android.util.Base64.decode(r1, r4)
            java.util.UUID r5 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.parseUuid(r1)
            if (r5 != 0) goto L_0x0109
            java.lang.String r1 = "MpdParser"
            java.lang.String r9 = "Skipping malformed cenc:pssh data"
            com.google.android.exoplayer2.util.Log.w(r1, r9)
            r13 = r2
            r10 = r5
            r11 = r7
            r14 = r8
            goto L_0x0140
        L_0x0109:
            r13 = r1
            r10 = r5
            r11 = r7
            r14 = r8
            goto L_0x0140
        L_0x010e:
            if (r5 != 0) goto L_0x0139
            java.util.UUID r10 = com.google.android.exoplayer2.C.PLAYREADY_UUID
            boolean r10 = r10.equals(r1)
            if (r10 == 0) goto L_0x0139
            java.lang.String r10 = "mspr:pro"
            boolean r10 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r10)
            if (r10 == 0) goto L_0x0139
            int r10 = r17.next()
            if (r10 != r9) goto L_0x0139
            java.util.UUID r5 = com.google.android.exoplayer2.C.PLAYREADY_UUID
            java.lang.String r9 = r17.getText()
            byte[] r9 = android.util.Base64.decode(r9, r4)
            byte[] r5 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.buildPsshAtom(r5, r9)
            r10 = r1
            r13 = r5
            r11 = r7
            r14 = r8
            goto L_0x0140
        L_0x0139:
            maybeSkipTag(r17)
            r10 = r1
            r13 = r5
            r11 = r7
            r14 = r8
        L_0x0140:
            java.lang.String r1 = "ContentProtection"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r0, r1)
            if (r1 == 0) goto L_0x015a
            if (r10 == 0) goto L_0x0154
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r0 = new com.google.android.exoplayer2.drm.DrmInitData$SchemeData
            java.lang.String r12 = "video/mp4"
            r9 = r0
            r9.<init>(r10, r11, r12, r13, r14)
            goto L_0x0155
        L_0x0154:
            r0 = r2
        L_0x0155:
            android.util.Pair r0 = android.util.Pair.create(r6, r0)
            return r0
        L_0x015a:
            r1 = r10
            r7 = r11
            r5 = r13
            r8 = r14
            goto L_0x00a5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseContentProtection(org.xmlpull.v1.XmlPullParser):android.util.Pair");
    }

    /* access modifiers changed from: protected */
    public int parseRole(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", null);
        String parseString2 = parseString(xmlPullParser, "value", null);
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Role"));
        return (!"urn:mpeg:dash:role:2011".equals(parseString) || !C.DASH_ROLE_MAIN_VALUE.equals(parseString2)) ? 0 : 1;
    }

    /* access modifiers changed from: protected */
    public void parseAdaptationSetChild(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        maybeSkipTag(xmlPullParser);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0174 A[LOOP:0: B:1:0x0059->B:43:0x0174, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0139 A[EDGE_INSN: B:44:0x0139->B:37:0x0139 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.RepresentationInfo parseRepresentation(org.xmlpull.v1.XmlPullParser r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, int r28, int r29, float r30, int r31, int r32, java.lang.String r33, int r34, java.util.List<com.google.android.exoplayer2.source.dash.manifest.Descriptor> r35, com.google.android.exoplayer2.source.dash.manifest.SegmentBase r36) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r22 = this;
            r15 = r22
            r0 = r23
            java.lang.String r1 = "id"
            r2 = 0
            java.lang.String r1 = r0.getAttributeValue(r2, r1)
            java.lang.String r3 = "bandwidth"
            r4 = -1
            int r9 = parseInt(r0, r3, r4)
            java.lang.String r3 = "mimeType"
            r4 = r26
            java.lang.String r3 = parseString(r0, r3, r4)
            java.lang.String r4 = "codecs"
            r5 = r27
            java.lang.String r13 = parseString(r0, r4, r5)
            java.lang.String r4 = "width"
            r5 = r28
            int r4 = parseInt(r0, r4, r5)
            java.lang.String r5 = "height"
            r6 = r29
            int r5 = parseInt(r0, r5, r6)
            r6 = r30
            float r6 = parseFrameRate(r0, r6)
            java.lang.String r7 = "audioSamplingRate"
            r8 = r32
            int r8 = parseInt(r0, r7, r8)
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r7 = 0
            r16 = r31
            r10 = r36
            r17 = r2
            r2 = r24
        L_0x0059:
            r23.next()
            r26 = r13
            java.lang.String r13 = "BaseURL"
            boolean r13 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r13)
            if (r13 == 0) goto L_0x007b
            if (r7 != 0) goto L_0x0077
            java.lang.String r2 = parseBaseUrl(r0, r2)
            r7 = 1
            r13 = r16
            r18 = r17
            r16 = r2
            r17 = r10
            goto L_0x0131
        L_0x0077:
            r24 = r2
            goto L_0x0129
        L_0x007b:
            java.lang.String r13 = "AudioChannelConfiguration"
            boolean r13 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r13)
            if (r13 == 0) goto L_0x008f
            int r13 = r22.parseAudioChannelConfiguration(r23)
            r16 = r2
            r18 = r17
            r17 = r10
            goto L_0x0131
        L_0x008f:
            java.lang.String r13 = "SegmentBase"
            boolean r13 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r13)
            if (r13 == 0) goto L_0x00a7
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r10 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase) r10
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r10 = r15.parseSegmentBase(r0, r10)
            r13 = r16
            r18 = r17
            r16 = r2
            r17 = r10
            goto L_0x0131
        L_0x00a7:
            java.lang.String r13 = "SegmentList"
            boolean r13 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r13)
            if (r13 == 0) goto L_0x00bf
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r10 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentList) r10
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r10 = r15.parseSegmentList(r0, r10)
            r13 = r16
            r18 = r17
            r16 = r2
            r17 = r10
            goto L_0x0131
        L_0x00bf:
            java.lang.String r13 = "SegmentTemplate"
            boolean r13 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r13)
            if (r13 == 0) goto L_0x00d6
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r10 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate) r10
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r10 = r15.parseSegmentTemplate(r0, r10)
            r13 = r16
            r18 = r17
            r16 = r2
            r17 = r10
            goto L_0x0131
        L_0x00d6:
            java.lang.String r13 = "ContentProtection"
            boolean r13 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r13)
            if (r13 == 0) goto L_0x0100
            android.util.Pair r13 = r22.parseContentProtection(r23)
            r24 = r2
            java.lang.Object r2 = r13.first
            if (r2 == 0) goto L_0x00ee
            java.lang.Object r2 = r13.first
            r17 = r2
            java.lang.String r17 = (java.lang.String) r17
        L_0x00ee:
            java.lang.Object r2 = r13.second
            if (r2 == 0) goto L_0x00f7
            java.lang.Object r2 = r13.second
            r14.add(r2)
        L_0x00f7:
            r13 = r16
            r18 = r17
            r16 = r24
            r17 = r10
            goto L_0x0131
        L_0x0100:
            r24 = r2
            java.lang.String r2 = "InbandEventStream"
            boolean r2 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r2)
            if (r2 == 0) goto L_0x0114
            java.lang.String r2 = "InbandEventStream"
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r2 = parseDescriptor(r0, r2)
            r12.add(r2)
            goto L_0x0129
        L_0x0114:
            java.lang.String r2 = "SupplementalProperty"
            boolean r2 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r2)
            if (r2 == 0) goto L_0x0126
            java.lang.String r2 = "SupplementalProperty"
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r2 = parseDescriptor(r0, r2)
            r11.add(r2)
            goto L_0x0129
        L_0x0126:
            maybeSkipTag(r23)
        L_0x0129:
            r13 = r16
            r18 = r17
            r16 = r24
            r17 = r10
        L_0x0131:
            java.lang.String r2 = "Representation"
            boolean r2 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r0, r2)
            if (r2 == 0) goto L_0x0174
            r0 = r22
            r2 = r25
            r7 = r13
            r10 = r33
            r19 = r11
            r11 = r34
            r20 = r12
            r12 = r35
            r13 = r26
            r21 = r14
            r14 = r19
            com.google.android.exoplayer2.Format r0 = r0.buildFormat(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            if (r17 == 0) goto L_0x0157
            r1 = r17
            goto L_0x015c
        L_0x0157:
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r1 = new com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase
            r1.<init>()
        L_0x015c:
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r2 = new com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo
            r3 = -1
            r23 = r2
            r24 = r0
            r25 = r16
            r26 = r1
            r27 = r18
            r28 = r21
            r29 = r20
            r30 = r3
            r23.<init>(r24, r25, r26, r27, r28, r29, r30)
            return r2
        L_0x0174:
            r2 = r16
            r10 = r17
            r17 = r18
            r16 = r13
            r13 = r26
            goto L_0x0059
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseRepresentation(org.xmlpull.v1.XmlPullParser, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, float, int, int, java.lang.String, int, java.util.List, com.google.android.exoplayer2.source.dash.manifest.SegmentBase):com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo");
    }

    /* access modifiers changed from: protected */
    public Format buildFormat(String str, String str2, String str3, int i, int i2, float f, int i3, int i4, int i5, String str4, int i6, List<Descriptor> list, String str5, List<Descriptor> list2) {
        String str6;
        String str7 = str3;
        String sampleMimeType = getSampleMimeType(str3, str5);
        if (sampleMimeType != null) {
            str6 = MimeTypes.AUDIO_E_AC3.equals(sampleMimeType) ? parseEac3SupplementalProperties(list2) : sampleMimeType;
            if (MimeTypes.isVideo(str6)) {
                return Format.createVideoContainerFormat(str, str2, str3, str6, str5, i5, i, i2, f, null, i6);
            }
            if (MimeTypes.isAudio(str6)) {
                return Format.createAudioContainerFormat(str, str2, str3, str6, str5, i5, i3, i4, null, i6, str4);
            }
            if (mimeTypeIsRawText(str6)) {
                int i7 = MimeTypes.APPLICATION_CEA608.equals(str6) ? parseCea608AccessibilityChannel(list) : MimeTypes.APPLICATION_CEA708.equals(str6) ? parseCea708AccessibilityChannel(list) : -1;
                return Format.createTextContainerFormat(str, str2, str3, str6, str5, i5, i6, str4, i7);
            }
        } else {
            str6 = sampleMimeType;
        }
        return Format.createContainerFormat(str, str2, str3, str6, str5, i5, i6, str4);
    }

    /* access modifiers changed from: protected */
    public Representation buildRepresentation(RepresentationInfo representationInfo, String str, String str2, ArrayList<SchemeData> arrayList, ArrayList<Descriptor> arrayList2) {
        Format format;
        Format format2 = representationInfo.format;
        if (representationInfo.drmSchemeType != null) {
            str2 = representationInfo.drmSchemeType;
        }
        ArrayList<SchemeData> arrayList3 = representationInfo.drmSchemeDatas;
        arrayList3.addAll(arrayList);
        if (!arrayList3.isEmpty()) {
            filterRedundantIncompleteSchemeDatas(arrayList3);
            format = format2.copyWithDrmInitData(new DrmInitData(str2, (List<SchemeData>) arrayList3));
        } else {
            format = format2;
        }
        ArrayList<Descriptor> arrayList4 = representationInfo.inbandEventStreams;
        arrayList4.addAll(arrayList2);
        return Representation.newInstance(str, representationInfo.revisionId, format, representationInfo.baseUrl, representationInfo.segmentBase, arrayList4);
    }

    /* access modifiers changed from: protected */
    public SingleSegmentBase parseSegmentBase(XmlPullParser xmlPullParser, SingleSegmentBase singleSegmentBase) throws XmlPullParserException, IOException {
        long j;
        long j2;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SingleSegmentBase singleSegmentBase2 = singleSegmentBase;
        long parseLong = parseLong(xmlPullParser2, "timescale", singleSegmentBase2 != null ? singleSegmentBase2.timescale : 1);
        long j3 = 0;
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", singleSegmentBase2 != null ? singleSegmentBase2.presentationTimeOffset : 0);
        long j4 = singleSegmentBase2 != null ? singleSegmentBase2.indexStart : 0;
        if (singleSegmentBase2 != null) {
            j3 = singleSegmentBase2.indexLength;
        }
        RangedUri rangedUri = null;
        String attributeValue = xmlPullParser2.getAttributeValue(null, "indexRange");
        if (attributeValue != null) {
            String[] split = attributeValue.split("-");
            j2 = Long.parseLong(split[0]);
            j = (Long.parseLong(split[1]) - j2) + 1;
        } else {
            j = j3;
            j2 = j4;
        }
        if (singleSegmentBase2 != null) {
            rangedUri = singleSegmentBase2.initialization;
        }
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri = parseInitialization(xmlPullParser);
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentBase"));
        return buildSingleSegmentBase(rangedUri, parseLong, parseLong2, j2, j);
    }

    /* access modifiers changed from: protected */
    public SingleSegmentBase buildSingleSegmentBase(RangedUri rangedUri, long j, long j2, long j3, long j4) {
        SingleSegmentBase singleSegmentBase = new SingleSegmentBase(rangedUri, j, j2, j3, j4);
        return singleSegmentBase;
    }

    /* access modifiers changed from: protected */
    public SegmentList parseSegmentList(XmlPullParser xmlPullParser, SegmentList segmentList) throws XmlPullParserException, IOException {
        List list;
        List list2;
        RangedUri rangedUri;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentList segmentList2 = segmentList;
        long j = 1;
        long parseLong = parseLong(xmlPullParser2, "timescale", segmentList2 != null ? segmentList2.timescale : 1);
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", segmentList2 != null ? segmentList2.presentationTimeOffset : 0);
        long parseLong3 = parseLong(xmlPullParser2, "duration", segmentList2 != null ? segmentList2.duration : -9223372036854775807L);
        String str = "startNumber";
        if (segmentList2 != null) {
            j = segmentList2.startNumber;
        }
        long parseLong4 = parseLong(xmlPullParser2, str, j);
        List list3 = null;
        RangedUri rangedUri2 = null;
        List list4 = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri2 = parseInitialization(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentTimeline")) {
                list4 = parseSegmentTimeline(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentURL")) {
                if (list3 == null) {
                    list3 = new ArrayList();
                }
                list3.add(parseSegmentUrl(xmlPullParser));
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentList"));
        if (segmentList2 != null) {
            if (rangedUri2 == null) {
                rangedUri2 = segmentList2.initialization;
            }
            if (list4 == null) {
                list4 = segmentList2.segmentTimeline;
            }
            if (list3 == null) {
                list3 = segmentList2.mediaSegments;
            }
            list = list3;
            rangedUri = rangedUri2;
            list2 = list4;
        } else {
            list = list3;
            rangedUri = rangedUri2;
            list2 = list4;
        }
        return buildSegmentList(rangedUri, parseLong, parseLong2, parseLong4, parseLong3, list2, list);
    }

    /* access modifiers changed from: protected */
    public SegmentList buildSegmentList(RangedUri rangedUri, long j, long j2, long j3, long j4, List<SegmentTimelineElement> list, List<RangedUri> list2) {
        SegmentList segmentList = new SegmentList(rangedUri, j, j2, j3, j4, list, list2);
        return segmentList;
    }

    /* access modifiers changed from: protected */
    public SegmentTemplate parseSegmentTemplate(XmlPullParser xmlPullParser, SegmentTemplate segmentTemplate) throws XmlPullParserException, IOException {
        List list;
        RangedUri rangedUri;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentTemplate segmentTemplate2 = segmentTemplate;
        long j = 1;
        long parseLong = parseLong(xmlPullParser2, "timescale", segmentTemplate2 != null ? segmentTemplate2.timescale : 1);
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", segmentTemplate2 != null ? segmentTemplate2.presentationTimeOffset : 0);
        long parseLong3 = parseLong(xmlPullParser2, "duration", segmentTemplate2 != null ? segmentTemplate2.duration : -9223372036854775807L);
        String str = "startNumber";
        if (segmentTemplate2 != null) {
            j = segmentTemplate2.startNumber;
        }
        long parseLong4 = parseLong(xmlPullParser2, str, j);
        RangedUri rangedUri2 = null;
        UrlTemplate parseUrlTemplate = parseUrlTemplate(xmlPullParser2, "media", segmentTemplate2 != null ? segmentTemplate2.mediaTemplate : null);
        UrlTemplate parseUrlTemplate2 = parseUrlTemplate(xmlPullParser2, "initialization", segmentTemplate2 != null ? segmentTemplate2.initializationTemplate : null);
        List list2 = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri2 = parseInitialization(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentTimeline")) {
                list2 = parseSegmentTimeline(xmlPullParser);
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentTemplate"));
        if (segmentTemplate2 != null) {
            if (rangedUri2 == null) {
                rangedUri2 = segmentTemplate2.initialization;
            }
            if (list2 == null) {
                list2 = segmentTemplate2.segmentTimeline;
            }
            list = list2;
            rangedUri = rangedUri2;
        } else {
            list = list2;
            rangedUri = rangedUri2;
        }
        return buildSegmentTemplate(rangedUri, parseLong, parseLong2, parseLong4, parseLong3, list, parseUrlTemplate2, parseUrlTemplate);
    }

    /* access modifiers changed from: protected */
    public SegmentTemplate buildSegmentTemplate(RangedUri rangedUri, long j, long j2, long j3, long j4, List<SegmentTimelineElement> list, UrlTemplate urlTemplate, UrlTemplate urlTemplate2) {
        SegmentTemplate segmentTemplate = new SegmentTemplate(rangedUri, j, j2, j3, j4, list, urlTemplate, urlTemplate2);
        return segmentTemplate;
    }

    /* access modifiers changed from: protected */
    public EventStream parseEventStream(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", "");
        String parseString2 = parseString(xmlPullParser, "value", "");
        long parseLong = parseLong(xmlPullParser, "timescale", 1);
        ArrayList arrayList = new ArrayList();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Event")) {
                arrayList.add(parseEvent(xmlPullParser, parseString, parseString2, parseLong, byteArrayOutputStream));
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "EventStream"));
        long[] jArr = new long[arrayList.size()];
        EventMessage[] eventMessageArr = new EventMessage[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            Pair pair = (Pair) arrayList.get(i);
            jArr[i] = ((Long) pair.first).longValue();
            eventMessageArr[i] = (EventMessage) pair.second;
        }
        return buildEventStream(parseString, parseString2, parseLong, jArr, eventMessageArr);
    }

    /* access modifiers changed from: protected */
    public EventStream buildEventStream(String str, String str2, long j, long[] jArr, EventMessage[] eventMessageArr) {
        EventStream eventStream = new EventStream(str, str2, j, jArr, eventMessageArr);
        return eventStream;
    }

    /* access modifiers changed from: protected */
    public Pair<Long, EventMessage> parseEvent(XmlPullParser xmlPullParser, String str, String str2, long j, ByteArrayOutputStream byteArrayOutputStream) throws IOException, XmlPullParserException {
        byte[] bArr;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        long parseLong = parseLong(xmlPullParser2, "id", 0);
        long parseLong2 = parseLong(xmlPullParser2, "duration", -9223372036854775807L);
        long parseLong3 = parseLong(xmlPullParser2, "presentationTime", 0);
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(parseLong2, 1000, j);
        long scaleLargeTimestamp2 = Util.scaleLargeTimestamp(parseLong3, 1000000, j);
        String parseString = parseString(xmlPullParser2, "messageData", null);
        byte[] parseEventObject = parseEventObject(xmlPullParser2, byteArrayOutputStream);
        Long valueOf = Long.valueOf(scaleLargeTimestamp2);
        if (parseString == null) {
            bArr = parseEventObject;
        } else {
            bArr = Util.getUtf8Bytes(parseString);
        }
        return Pair.create(valueOf, buildEvent(str, str2, parseLong, scaleLargeTimestamp, bArr));
    }

    /* access modifiers changed from: protected */
    public byte[] parseEventObject(XmlPullParser xmlPullParser, ByteArrayOutputStream byteArrayOutputStream) throws XmlPullParserException, IOException {
        byteArrayOutputStream.reset();
        XmlSerializer newSerializer = Xml.newSerializer();
        newSerializer.setOutput(byteArrayOutputStream, "UTF-8");
        xmlPullParser.nextToken();
        while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Event")) {
            switch (xmlPullParser.getEventType()) {
                case 0:
                    newSerializer.startDocument(null, Boolean.valueOf(false));
                    break;
                case 1:
                    newSerializer.endDocument();
                    break;
                case 2:
                    newSerializer.startTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                    for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                        newSerializer.attribute(xmlPullParser.getAttributeNamespace(i), xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i));
                    }
                    break;
                case 3:
                    newSerializer.endTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                    break;
                case 4:
                    newSerializer.text(xmlPullParser.getText());
                    break;
                case 5:
                    newSerializer.cdsect(xmlPullParser.getText());
                    break;
                case 6:
                    newSerializer.entityRef(xmlPullParser.getText());
                    break;
                case 7:
                    newSerializer.ignorableWhitespace(xmlPullParser.getText());
                    break;
                case 8:
                    newSerializer.processingInstruction(xmlPullParser.getText());
                    break;
                case 9:
                    newSerializer.comment(xmlPullParser.getText());
                    break;
                case 10:
                    newSerializer.docdecl(xmlPullParser.getText());
                    break;
            }
            xmlPullParser.nextToken();
        }
        newSerializer.flush();
        return byteArrayOutputStream.toByteArray();
    }

    /* access modifiers changed from: protected */
    public EventMessage buildEvent(String str, String str2, long j, long j2, byte[] bArr) {
        EventMessage eventMessage = new EventMessage(str, str2, j2, j, bArr);
        return eventMessage;
    }

    /* access modifiers changed from: protected */
    public List<SegmentTimelineElement> parseSegmentTimeline(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        long j = 0;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "S")) {
                j = parseLong(xmlPullParser, "t", j);
                long parseLong = parseLong(xmlPullParser, "d", -9223372036854775807L);
                int parseInt = parseInt(xmlPullParser, "r", 0) + 1;
                for (int i = 0; i < parseInt; i++) {
                    arrayList.add(buildSegmentTimelineElement(j, parseLong));
                    j += parseLong;
                }
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "SegmentTimeline"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public SegmentTimelineElement buildSegmentTimelineElement(long j, long j2) {
        return new SegmentTimelineElement(j, j2);
    }

    /* access modifiers changed from: protected */
    public UrlTemplate parseUrlTemplate(XmlPullParser xmlPullParser, String str, UrlTemplate urlTemplate) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue != null ? UrlTemplate.compile(attributeValue) : urlTemplate;
    }

    /* access modifiers changed from: protected */
    public RangedUri parseInitialization(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, "sourceURL", "range");
    }

    /* access modifiers changed from: protected */
    public RangedUri parseSegmentUrl(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, "media", "mediaRange");
    }

    /* access modifiers changed from: protected */
    public RangedUri parseRangedUrl(XmlPullParser xmlPullParser, String str, String str2) {
        long j;
        long j2;
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        String attributeValue2 = xmlPullParser.getAttributeValue(null, str2);
        if (attributeValue2 != null) {
            String[] split = attributeValue2.split("-");
            long parseLong = Long.parseLong(split[0]);
            if (split.length == 2) {
                j = (Long.parseLong(split[1]) - parseLong) + 1;
                j2 = parseLong;
            } else {
                j = -1;
                j2 = parseLong;
            }
        } else {
            j = -1;
            j2 = 0;
        }
        return buildRangedUri(attributeValue, j2, j);
    }

    /* access modifiers changed from: protected */
    public RangedUri buildRangedUri(String str, long j, long j2) {
        RangedUri rangedUri = new RangedUri(str, j, j2);
        return rangedUri;
    }

    /* access modifiers changed from: protected */
    public ProgramInformation parseProgramInformation(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        String str;
        String str2 = null;
        String parseString = parseString(xmlPullParser, "moreInformationURL", null);
        String parseString2 = parseString(xmlPullParser, Http.LANG, null);
        String str3 = null;
        String str4 = null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Title")) {
                str2 = xmlPullParser.nextText();
                str = str4;
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "Source")) {
                str3 = xmlPullParser.nextText();
                str = str4;
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "Copyright")) {
                str = xmlPullParser.nextText();
            } else {
                maybeSkipTag(xmlPullParser);
                str = str4;
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "ProgramInformation")) {
                ProgramInformation programInformation = new ProgramInformation(str2, str3, str, parseString, parseString2);
                return programInformation;
            }
            str4 = str;
        }
    }

    /* access modifiers changed from: protected */
    public int parseAudioChannelConfiguration(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", null);
        int i = -1;
        if ("urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(parseString)) {
            i = parseInt(xmlPullParser, "value", -1);
        } else if ("tag:dolby.com,2014:dash:audio_channel_configuration:2011".equals(parseString)) {
            i = parseDolbyChannelConfiguration(xmlPullParser);
        }
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "AudioChannelConfiguration"));
        return i;
    }

    public static void maybeSkipTag(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        if (XmlPullParserUtil.isStartTag(xmlPullParser)) {
            int i = 1;
            while (i != 0) {
                xmlPullParser.next();
                if (XmlPullParserUtil.isStartTag(xmlPullParser)) {
                    i++;
                } else if (XmlPullParserUtil.isEndTag(xmlPullParser)) {
                    i--;
                }
            }
        }
    }

    private static void filterRedundantIncompleteSchemeDatas(ArrayList<SchemeData> arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            SchemeData schemeData = (SchemeData) arrayList.get(size);
            if (!schemeData.hasData()) {
                int i = 0;
                while (true) {
                    if (i >= arrayList.size()) {
                        break;
                    } else if (((SchemeData) arrayList.get(i)).canReplace(schemeData)) {
                        arrayList.remove(size);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    private static String getSampleMimeType(String str, String str2) {
        if (MimeTypes.isAudio(str)) {
            return MimeTypes.getAudioMediaMimeType(str2);
        }
        if (MimeTypes.isVideo(str)) {
            return MimeTypes.getVideoMediaMimeType(str2);
        }
        if (mimeTypeIsRawText(str)) {
            return str;
        }
        if (!MimeTypes.APPLICATION_MP4.equals(str)) {
            if (MimeTypes.APPLICATION_RAWCC.equals(str) && str2 != null) {
                if (str2.contains("cea708")) {
                    return MimeTypes.APPLICATION_CEA708;
                }
                if (str2.contains("eia608") || str2.contains("cea608")) {
                    return MimeTypes.APPLICATION_CEA608;
                }
            }
            return null;
        } else if (str2 != null) {
            if (str2.startsWith("stpp")) {
                return MimeTypes.APPLICATION_TTML;
            }
            if (str2.startsWith("wvtt")) {
                return MimeTypes.APPLICATION_MP4VTT;
            }
        }
        return null;
    }

    private static boolean mimeTypeIsRawText(String str) {
        return MimeTypes.isText(str) || MimeTypes.APPLICATION_TTML.equals(str) || MimeTypes.APPLICATION_MP4VTT.equals(str) || MimeTypes.APPLICATION_CEA708.equals(str) || MimeTypes.APPLICATION_CEA608.equals(str);
    }

    private static String checkLanguageConsistency(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        Assertions.checkState(str.equals(str2));
        return str;
    }

    private static int checkContentTypeConsistency(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        Assertions.checkState(i == i2);
        return i;
    }

    protected static Descriptor parseDescriptor(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", "");
        String parseString2 = parseString(xmlPullParser, "value", null);
        String parseString3 = parseString(xmlPullParser, "id", null);
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, str));
        return new Descriptor(parseString, parseString2, parseString3);
    }

    protected static int parseCea608AccessibilityChannel(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = (Descriptor) list.get(i);
            if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
                Matcher matcher = CEA_608_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to parse CEA-608 channel number from: ");
                sb.append(descriptor.value);
                Log.w(str, sb.toString());
            }
        }
        return -1;
    }

    protected static int parseCea708AccessibilityChannel(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = (Descriptor) list.get(i);
            if ("urn:scte:dash:cc:cea-708:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
                Matcher matcher = CEA_708_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to parse CEA-708 service block number from: ");
                sb.append(descriptor.value);
                Log.w(str, sb.toString());
            }
        }
        return -1;
    }

    protected static String parseEac3SupplementalProperties(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = (Descriptor) list.get(i);
            if ("tag:dolby.com,2014:dash:DolbyDigitalPlusExtensionType:2014".equals(descriptor.schemeIdUri) && "ec+3".equals(descriptor.value)) {
                return MimeTypes.AUDIO_E_AC3_JOC;
            }
        }
        return MimeTypes.AUDIO_E_AC3;
    }

    protected static float parseFrameRate(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "frameRate");
        if (attributeValue == null) {
            return f;
        }
        Matcher matcher = FRAME_RATE_PATTERN.matcher(attributeValue);
        if (!matcher.matches()) {
            return f;
        }
        int parseInt = Integer.parseInt(matcher.group(1));
        String group = matcher.group(2);
        return !TextUtils.isEmpty(group) ? ((float) parseInt) / ((float) Integer.parseInt(group)) : (float) parseInt;
    }

    protected static long parseDuration(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return j;
        }
        return Util.parseXsDuration(attributeValue);
    }

    protected static long parseDateTime(XmlPullParser xmlPullParser, String str, long j) throws ParserException {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return j;
        }
        return Util.parseXsDateTime(attributeValue);
    }

    protected static String parseBaseUrl(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        xmlPullParser.next();
        return UriUtil.resolve(str, xmlPullParser.getText());
    }

    protected static int parseInt(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? i : Integer.parseInt(attributeValue);
    }

    protected static long parseLong(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Long.parseLong(attributeValue);
    }

    protected static String parseString(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? str2 : attributeValue;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0057 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x005e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static int parseDolbyChannelConfiguration(org.xmlpull.v1.XmlPullParser r5) {
        /*
            java.lang.String r0 = "value"
            r1 = 0
            java.lang.String r5 = r5.getAttributeValue(r1, r0)
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r5)
            r0 = -1
            if (r5 != 0) goto L_0x0010
            return r0
        L_0x0010:
            int r1 = r5.hashCode()
            r2 = 1596796(0x185d7c, float:2.237588E-39)
            r3 = 2
            r4 = 1
            if (r1 == r2) goto L_0x0049
            r2 = 2937391(0x2cd22f, float:4.116161E-39)
            if (r1 == r2) goto L_0x003f
            r2 = 3094035(0x2f3613, float:4.335666E-39)
            if (r1 == r2) goto L_0x0035
            r2 = 3133436(0x2fcffc, float:4.390879E-39)
            if (r1 == r2) goto L_0x002b
            goto L_0x0053
        L_0x002b:
            java.lang.String r1 = "fa01"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x0053
            r5 = 3
            goto L_0x0054
        L_0x0035:
            java.lang.String r1 = "f801"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x0053
            r5 = 2
            goto L_0x0054
        L_0x003f:
            java.lang.String r1 = "a000"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x0053
            r5 = 1
            goto L_0x0054
        L_0x0049:
            java.lang.String r1 = "4000"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x0053
            r5 = 0
            goto L_0x0054
        L_0x0053:
            r5 = -1
        L_0x0054:
            switch(r5) {
                case 0: goto L_0x005e;
                case 1: goto L_0x005d;
                case 2: goto L_0x005b;
                case 3: goto L_0x0058;
                default: goto L_0x0057;
            }
        L_0x0057:
            return r0
        L_0x0058:
            r5 = 8
            return r5
        L_0x005b:
            r5 = 6
            return r5
        L_0x005d:
            return r3
        L_0x005e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseDolbyChannelConfiguration(org.xmlpull.v1.XmlPullParser):int");
    }
}
