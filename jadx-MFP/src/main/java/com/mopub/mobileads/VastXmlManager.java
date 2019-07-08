package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.common.util.DeviceUtils.ForceOrientation;
import com.mopub.mobileads.util.XmlUtils;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

class VastXmlManager {
    @Nullable
    private Document mVastDoc;

    VastXmlManager() {
    }

    /* access modifiers changed from: 0000 */
    public void parseVastXml(@NonNull String str) throws ParserConfigurationException, IOException, SAXException {
        Preconditions.checkNotNull(str, "xmlString cannot be null");
        String replaceFirst = str.replaceFirst("<\\?.*\\?>", "");
        StringBuilder sb = new StringBuilder();
        sb.append("<MPMoVideoXMLDocRoot>");
        sb.append(replaceFirst);
        sb.append("</MPMoVideoXMLDocRoot>");
        String sb2 = sb.toString();
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setCoalescing(true);
        this.mVastDoc = newInstance.newDocumentBuilder().parse(new InputSource(new StringReader(sb2)));
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastAdXmlManager> getAdXmlManagers() {
        ArrayList arrayList = new ArrayList();
        Document document = this.mVastDoc;
        if (document == null) {
            return arrayList;
        }
        NodeList elementsByTagName = document.getElementsByTagName("Ad");
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            arrayList.add(new VastAdXmlManager(elementsByTagName.item(i)));
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public VastTracker getErrorTracker() {
        Document document = this.mVastDoc;
        if (document == null) {
            return null;
        }
        String firstMatchingStringData = XmlUtils.getFirstMatchingStringData(document, "Error");
        if (TextUtils.isEmpty(firstMatchingStringData)) {
            return null;
        }
        return new VastTracker(firstMatchingStringData);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastTracker> getMoPubImpressionTrackers() {
        List<String> stringDataAsList = XmlUtils.getStringDataAsList(this.mVastDoc, "MP_TRACKING_URL");
        ArrayList arrayList = new ArrayList(stringDataAsList.size());
        for (String vastTracker : stringDataAsList) {
            arrayList.add(new VastTracker(vastTracker));
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getCustomCtaText() {
        String firstMatchingStringData = XmlUtils.getFirstMatchingStringData(this.mVastDoc, "MoPubCtaText");
        if (firstMatchingStringData == null || firstMatchingStringData.length() > 15) {
            return null;
        }
        return firstMatchingStringData;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getCustomSkipText() {
        String firstMatchingStringData = XmlUtils.getFirstMatchingStringData(this.mVastDoc, "MoPubSkipText");
        if (firstMatchingStringData == null || firstMatchingStringData.length() > 8) {
            return null;
        }
        return firstMatchingStringData;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getCustomCloseIconUrl() {
        return XmlUtils.getFirstMatchingStringData(this.mVastDoc, "MoPubCloseIcon");
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public ForceOrientation getCustomForceOrientation() {
        return ForceOrientation.getForceOrientation(XmlUtils.getFirstMatchingStringData(this.mVastDoc, "MoPubForceOrientation"));
    }
}
