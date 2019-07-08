package com.brightcove.player.dash;

import android.util.Base64;
import android.util.Pair;
import com.brightcove.player.C;
import com.brightcove.player.util.MediaSourceUtil;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.google.android.exoplayer2.source.dash.manifest.Descriptor;
import com.google.android.exoplayer2.source.dash.manifest.RangedUri;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTimelineElement;
import com.google.android.exoplayer2.source.dash.manifest.UrlTemplate;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class BrightcoveDashManifestParser extends DashManifestParser {
    /* access modifiers changed from: protected */
    public Pair<String, SchemeData> parseContentProtection(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        SchemeData schemeData = null;
        String attributeValue = xmlPullParser.getAttributeValue(null, "value");
        UUID uuid = null;
        byte[] bArr = null;
        boolean z = false;
        boolean z2 = false;
        do {
            xmlPullParser.next();
            boolean z3 = true;
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "cenc:pssh") && xmlPullParser.next() == 4) {
                byte[] decode = Base64.decode(xmlPullParser.getText(), 0);
                bArr = decode;
                uuid = PsshAtomUtil.parseUuid(decode);
                z = true;
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "widevine:license")) {
                String attributeValue2 = xmlPullParser.getAttributeValue(null, "robustness_level");
                if (attributeValue2 == null || !attributeValue2.startsWith("HW")) {
                    z3 = false;
                }
                z2 = z3;
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "ContentProtection"));
        if (!z) {
            return Pair.create(null, null);
        }
        if (uuid != null) {
            schemeData = new SchemeData(uuid, MimeTypes.VIDEO_MP4, bArr, z2);
        }
        return Pair.create(attributeValue, schemeData);
    }

    /* access modifiers changed from: protected */
    public Format buildFormat(String str, String str2, String str3, int i, int i2, float f, int i3, int i4, int i5, String str4, int i6, List<Descriptor> list, String str5, List<Descriptor> list2) {
        return super.buildFormat(str, str2, str3, i, i2, f, i3, i4, i5, str4, i6, list, str5, list2);
    }

    /* access modifiers changed from: protected */
    public int parseRole(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", null);
        String parseString2 = parseString(xmlPullParser, "value", null);
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Role"));
        return ((!"urn:mpeg:dash:role:2011".equals(parseString) || !C.DASH_ROLE_MAIN_VALUE.equals(parseString2)) ? 0 : 1) | MediaSourceUtil.getBrightcoveRoleFlag(parseString2);
    }

    /* access modifiers changed from: protected */
    public SegmentTemplate buildSegmentTemplate(RangedUri rangedUri, long j, long j2, long j3, long j4, List<SegmentTimelineElement> list, UrlTemplate urlTemplate, UrlTemplate urlTemplate2) {
        BrightcoveSegmentTemplate brightcoveSegmentTemplate = new BrightcoveSegmentTemplate(rangedUri, j, j2, j3, j4, list, urlTemplate, urlTemplate2);
        return brightcoveSegmentTemplate;
    }
}
