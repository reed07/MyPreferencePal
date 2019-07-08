package com.inmobi.ads;

import android.text.TextUtils;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.mopub.mobileads.VastExtensionXmlManager;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* compiled from: MoatVastHelper */
class ag {
    private static final String a = "ag";

    ag() {
    }

    static void a(Document document, bx bxVar) {
        List a2 = bu.a(document, VastExtensionXmlManager.VERIFICATION);
        if (a2 != null) {
            StringBuilder sb = new StringBuilder();
            Iterator it = a2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Element element = (Element) ((Node) it.next());
                NodeList childNodes = element.getChildNodes();
                if (VastExtensionXmlManager.MOAT.equals(element.getAttribute(VastExtensionXmlManager.VENDOR)) && childNodes.getLength() > 0) {
                    for (int i = 0; i < childNodes.getLength(); i++) {
                        Node item = childNodes.item(i);
                        if ("ViewableImpression".equals(item.getNodeName())) {
                            String a3 = a(item);
                            if (!TextUtils.isEmpty(a3)) {
                                sb.append(a3);
                                sb.append(";");
                                InternalLogLevel internalLogLevel = InternalLogLevel.INTERNAL;
                                String str = a;
                                StringBuilder sb2 = new StringBuilder("Found Moat Verification tag in VAST with value : ");
                                sb2.append(a3);
                                Logger.a(internalLogLevel, str, sb2.toString());
                            }
                        }
                    }
                }
            }
            if (sb.length() != 0) {
                InternalLogLevel internalLogLevel2 = InternalLogLevel.INTERNAL;
                String str2 = a;
                StringBuilder sb3 = new StringBuilder("Moat VastIDs in VAST : ");
                sb3.append(sb.toString());
                Logger.a(internalLogLevel2, str2, sb3.toString());
                bxVar.a(new NativeTracker(sb.toString(), 0, TrackerEventType.TRACKER_EVENT_TYPE_MOAT, null));
            }
        }
    }

    private static String a(Node node) {
        StringWriter stringWriter = new StringWriter();
        try {
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("omit-xml-declaration", "yes");
            newTransformer.transform(new DOMSource(node), new StreamResult(stringWriter));
        } catch (TransformerException e) {
            InternalLogLevel internalLogLevel = InternalLogLevel.INTERNAL;
            String str = a;
            StringBuilder sb = new StringBuilder("Exception while converting Moat node to string : ");
            sb.append(e.getMessage());
            Logger.a(internalLogLevel, str, sb.toString());
        }
        return stringWriter.toString();
    }
}
