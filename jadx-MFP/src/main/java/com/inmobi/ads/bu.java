package com.inmobi.ads;

import android.os.SystemClock;
import android.webkit.URLUtil;
import com.brightcove.player.event.EventType;
import com.google.logging.type.LogSeverity;
import com.inmobi.a.n;
import com.inmobi.ads.c.C0043c;
import com.inmobi.ads.c.k;
import com.inmobi.commons.core.a.a;
import com.inmobi.commons.core.network.c;
import com.inmobi.commons.core.network.d;
import com.inmobi.commons.core.network.e;
import com.mopub.mobileads.VastResourceXmlManager;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* compiled from: VastHelper */
public class bu {
    private static final String a = "bu";
    private static final Map<String, TrackerEventType> d;
    private k b;
    private final String c = "Progressive";
    private int e = 0;
    private bx f;

    static {
        HashMap hashMap = new HashMap();
        d = hashMap;
        hashMap.put("Error", TrackerEventType.TRACKER_EVENT_TYPE_ERROR);
        d.put("Impression", TrackerEventType.TRACKER_EVENT_TYPE_RENDER);
        d.put("ClickTracking", TrackerEventType.TRACKER_EVENT_TYPE_CLICK);
        d.put("creativeView", TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW);
        d.put(TtmlNode.START, TrackerEventType.TRACKER_EVENT_TYPE_PLAY);
        d.put("firstQuartile", TrackerEventType.TRACKER_EVENT_TYPE_Q1);
        d.put("midpoint", TrackerEventType.TRACKER_EVENT_TYPE_Q2);
        d.put("thirdQuartile", TrackerEventType.TRACKER_EVENT_TYPE_Q3);
        d.put("complete", TrackerEventType.TRACKER_EVENT_TYPE_Q4);
        d.put("mute", TrackerEventType.TRACKER_EVENT_TYPE_MUTE);
        d.put("unmute", TrackerEventType.TRACKER_EVENT_TYPE_UNMUTE);
        d.put(EventType.PAUSE, TrackerEventType.TRACKER_EVENT_TYPE_PAUSE);
        d.put("resume", TrackerEventType.TRACKER_EVENT_TYPE_RESUME);
        d.put("fullscreen", TrackerEventType.TRACKER_EVENT_TYPE_FULLSCREEN);
        d.put("exitFullscreen", TrackerEventType.TRACKER_EVENT_TYPE_EXIT_FULLSCREEN);
    }

    public bu(k kVar) {
        this.b = kVar;
        this.f = new bx(this.b);
    }

    private static d b(String str) {
        c cVar = new c(HttpConstants.METHOD_GET, str);
        cVar.u = false;
        cVar.A = false;
        cVar.t = true;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        d a2 = new e(cVar).a();
        try {
            n.a().a(cVar.g());
            n.a().b(a2.c());
            n.a().c(SystemClock.elapsedRealtime() - elapsedRealtime);
        } catch (Exception e2) {
            new StringBuilder("Error in setting request-response data size. ").append(e2.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        return a2;
    }

    private static int c(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            a.a().a(new com.inmobi.commons.core.e.a(e2));
            return 0;
        }
    }

    static Node a(Node node, String str) {
        List b2 = b(node, str);
        if (b2.size() > 0) {
            return (Node) b2.get(0);
        }
        return null;
    }

    private static List<Node> b(Node node, String str) {
        ArrayList arrayList = new ArrayList();
        if (node == null || str == null) {
            return arrayList;
        }
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1 && str.equals(item.getNodeName())) {
                arrayList.add(item);
            }
        }
        return arrayList;
    }

    private static String c(Node node, String str) {
        return a(a(node, str));
    }

    private static Node a(List<Node> list, String str) {
        Node node = null;
        if (list == null) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            node = a((Node) list.get(i), str);
            if (node != null) {
                break;
            }
        }
        return node;
    }

    static List<Node> a(Document document, String str) {
        if (document == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        NodeList elementsByTagName = document.getElementsByTagName(str);
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            arrayList.add(elementsByTagName.item(i));
        }
        if (elementsByTagName.getLength() == 0) {
            return null;
        }
        return arrayList;
    }

    static String a(Node node) {
        String str;
        if (node == null) {
            return null;
        }
        try {
            str = node.getTextContent();
        } catch (DOMException e2) {
            new StringBuilder("Error getting node value; ").append(e2.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e2));
            str = null;
        }
        if (str != null) {
            return str.trim();
        }
        return null;
    }

    public final bx a(String str) {
        if (str == null || str.isEmpty()) {
            a(303);
            return this.f;
        }
        try {
            Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(str)));
            Node a2 = a((Node) parse, "VAST");
            if (a2 == null) {
                a(101);
                return this.f;
            }
            Node a3 = a(a2, "Ad");
            if (a3 == null) {
                a(303);
                return this.f;
            }
            Node a4 = a(a3, "Wrapper");
            if (a4 != null) {
                this.e++;
                if (this.e > this.b.a) {
                    a(302);
                    return this.f;
                } else if (!b(a4)) {
                    a(101);
                    return this.f;
                } else {
                    List a5 = a(parse, "TrackingEvents");
                    if (a5 != null) {
                        a(b((Node) a5.get(0), "Tracking"));
                    }
                    x.a(parse, this.f);
                    ag.a(parse, this.f);
                    a(TrackerEventType.TRACKER_EVENT_TYPE_CLICK, a(parse, "ClickTracking"));
                    String c2 = c(a4, "VASTAdTagURI");
                    if (c2 == null) {
                        a(101);
                        return this.f;
                    } else if (c2 == null || c2.isEmpty()) {
                        a((int) LogSeverity.NOTICE_VALUE);
                    } else {
                        d b2 = b(c2);
                        if (b2.a()) {
                            a(301);
                        } else {
                            a(b2.b());
                        }
                    }
                }
            } else {
                Node a6 = a(a3, "InLine");
                if (a6 == null) {
                    a(101);
                    return this.f;
                } else if (!b(a6)) {
                    a(101);
                    return this.f;
                } else {
                    Node a7 = a(a6, "Creatives");
                    if (a7 == null) {
                        a(101);
                        return this.f;
                    }
                    List b3 = b(a7, "Creative");
                    if (b3.isEmpty()) {
                        a(101);
                        return this.f;
                    }
                    Node a8 = a(b3, "Linear");
                    if (a8 == null) {
                        a((int) RequestCodes.EDIT_RECIPE_INGREDIENT);
                        return this.f;
                    }
                    List b4 = b(a8, "Duration");
                    if (b4.isEmpty()) {
                        a(101);
                        return this.f;
                    }
                    String a9 = a((Node) b4.get(0));
                    if (a9 == null || a9.isEmpty() || !a9.matches("\\d*:[0-5][0-9]:[0-5][0-9](:[0-9][0-9][0-9])?")) {
                        a(101);
                        return this.f;
                    }
                    this.f.b = a9;
                    Node a10 = a(a8, "MediaFiles");
                    if (a10 == null) {
                        a(101);
                        return this.f;
                    }
                    Node a11 = a(a8, "VideoClicks");
                    this.f.c = c(a11, "ClickThrough");
                    a(TrackerEventType.TRACKER_EVENT_TYPE_CLICK, b(a11, "ClickTracking"));
                    a(b(a(a8, "TrackingEvents"), "Tracking"));
                    x.a(parse, this.f);
                    ag.a(parse, this.f);
                    List b5 = b(a10, "MediaFile");
                    if (b5.isEmpty()) {
                        a(401);
                        return this.f;
                    }
                    C0043c cVar = this.b.d;
                    for (int i = 0; i < b5.size(); i++) {
                        Element element = (Element) b5.get(i);
                        String a12 = a((Node) element);
                        if (a12 != null && !a12.trim().isEmpty()) {
                            String attribute = element.getAttribute("delivery");
                            String attribute2 = element.getAttribute("type");
                            int c3 = c(element.getAttribute("bitrate"));
                            if ((!cVar.a || c3 > 0) && attribute != null && attribute.trim().equalsIgnoreCase("Progressive")) {
                                ArrayList<String> arrayList = this.b.e;
                                if (attribute2 != null) {
                                    int i2 = 0;
                                    while (true) {
                                        if (i2 >= arrayList.size()) {
                                            break;
                                        } else if (attribute2.equalsIgnoreCase((String) arrayList.get(i2))) {
                                            this.f.a.add(new bv(a12, attribute, attribute2, c3));
                                            break;
                                        } else {
                                            i2++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.f.a.isEmpty()) {
                        a(403);
                    }
                    List b6 = b(a7, "Creative");
                    if (b6.isEmpty()) {
                        a(101);
                        return this.f;
                    }
                    Node a13 = a(b6, "CompanionAds");
                    if (a13 == null) {
                        return this.f;
                    }
                    List<Node> a14 = a(parse, "CompanionAdTracking");
                    HashMap hashMap = new HashMap();
                    if (a14 != null) {
                        for (Node b7 : a14) {
                            for (Node node : b(b7, "TrackingEvents")) {
                                NamedNodeMap attributes = node.getAttributes();
                                Node namedItem = (attributes == null || attributes.getLength() <= 0) ? null : attributes.getNamedItem("id");
                                if (namedItem != null) {
                                    String nodeValue = namedItem.getNodeValue();
                                    for (Node node2 : b(node, "Tracking")) {
                                        if ("closeEndCard".equals(((Element) node2).getAttribute("event"))) {
                                            String a15 = a(node2);
                                            if (!URLUtil.isValidUrl(a15)) {
                                                StringBuilder sb = new StringBuilder("Malformed URL: ");
                                                sb.append(a15);
                                                sb.append("; Discarding this tracker");
                                            } else {
                                                hashMap.put(nodeValue, new NativeTracker(a15, 0, TrackerEventType.TRACKER_EVENT_TYPE_END_CARD_CLOSE, null));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    List b8 = b(a13, "Companion");
                    boolean z = false;
                    for (int i3 = 0; i3 < b8.size(); i3++) {
                        Element element2 = (Element) b8.get(i3);
                        int c4 = c(element2.getAttribute("width"));
                        int c5 = c(element2.getAttribute("height"));
                        if (!(c4 == 0 || c5 == 0)) {
                            String c6 = c(element2, "CompanionClickThrough");
                            if (!URLUtil.isValidUrl(c6)) {
                                c6 = null;
                            }
                            String attribute3 = element2.hasAttribute("id") ? element2.getAttribute("id") : null;
                            bt btVar = new bt(c4, c5, c6, attribute3);
                            Node a16 = a((Node) element2, VastResourceXmlManager.STATIC_RESOURCE);
                            if (a16 != null) {
                                String a17 = a(a16);
                                String attribute4 = ((Element) a16).getAttribute(VastResourceXmlManager.CREATIVE_TYPE);
                                if (attribute4 != null && !attribute4.trim().isEmpty()) {
                                    if (d(attribute4)) {
                                        btVar.a(new a(1, a17));
                                    } else {
                                        z = true;
                                    }
                                }
                            }
                            Node a18 = a((Node) element2, VastResourceXmlManager.HTML_RESOURCE);
                            if (a18 != null) {
                                btVar.a(new a(2, a(a18)));
                            }
                            Node a19 = a((Node) element2, VastResourceXmlManager.IFRAME_RESOURCE);
                            if (a19 != null) {
                                btVar.a(new a(3, a(a19)));
                            }
                            List<a> list = btVar.c;
                            if (!(list == null || list.size() == 0)) {
                                a(b(element2, "CompanionClickTracking"), btVar);
                                for (Node node3 : b(a((Node) element2, "TrackingEvents"), "Tracking")) {
                                    String attribute5 = ((Element) node3).getAttribute("event");
                                    if (d.containsKey(attribute5)) {
                                        String a20 = a(node3);
                                        if (!URLUtil.isValidUrl(a20)) {
                                            StringBuilder sb2 = new StringBuilder("Malformed URL: ");
                                            sb2.append(a20);
                                            sb2.append("; Discarding this tracker");
                                        } else {
                                            btVar.a(new NativeTracker(a20, 0, (TrackerEventType) d.get(attribute5), null));
                                        }
                                    }
                                }
                                if (attribute3 != null && hashMap.containsKey(attribute3)) {
                                    btVar.a((NativeTracker) hashMap.get(attribute3));
                                }
                                this.f.e.add(btVar);
                            }
                        }
                    }
                    int size = this.f.e.size();
                    if (size == 0 && z) {
                        b(604);
                    } else if (b8.size() > 0 && size == 0) {
                        b((int) LogSeverity.CRITICAL_VALUE);
                    }
                }
            }
            return this.f;
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e2) {
            a(100);
            a.a().a(new com.inmobi.commons.core.e.a(e2));
            return this.f;
        }
    }

    private static boolean d(String str) {
        int size = bt.f.size();
        for (int i = 0; i < size; i++) {
            if (str.equalsIgnoreCase((String) bt.f.get(i))) {
                return true;
            }
        }
        return false;
    }

    private static void a(List<Node> list, bt btVar) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Node node = (Node) list.get(i);
                if (1 == node.getNodeType()) {
                    String a2 = a(node);
                    if (!URLUtil.isValidUrl(a2)) {
                        StringBuilder sb = new StringBuilder("Malformed URL: ");
                        sb.append(a2);
                        sb.append("; Discarding this tracker");
                    } else {
                        btVar.a(new NativeTracker(a2, 0, TrackerEventType.TRACKER_EVENT_TYPE_CLICK, null));
                    }
                }
            }
        }
    }

    private boolean b(Node node) {
        if (node == null) {
            return false;
        }
        a(TrackerEventType.TRACKER_EVENT_TYPE_ERROR, b(node, "Error"));
        List b2 = b(node, "Impression");
        if (b2.isEmpty()) {
            return false;
        }
        return a(TrackerEventType.TRACKER_EVENT_TYPE_RENDER, b2);
    }

    private void a(List<Node> list) {
        if (list != null && list.size() != 0) {
            for (Node node : list) {
                String attribute = ((Element) node).getAttribute("event");
                if (d.containsKey(attribute)) {
                    a((TrackerEventType) d.get(attribute), node);
                }
            }
        }
    }

    private boolean a(TrackerEventType trackerEventType, List<Node> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Node node = (Node) list.get(i);
                if (node.getNodeType() == 1 && !a(trackerEventType, node)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean a(TrackerEventType trackerEventType, Node node) {
        String a2 = a(node);
        if (!URLUtil.isValidUrl(a2)) {
            StringBuilder sb = new StringBuilder("Malformed URL ");
            sb.append(a2);
            sb.append(" Discarding this tracker");
            return trackerEventType != TrackerEventType.TRACKER_EVENT_TYPE_RENDER;
        }
        this.f.a(new NativeTracker(a2, 0, trackerEventType, null));
        return true;
    }

    private void a(int i) {
        this.f.f = i;
        b(i);
    }

    private void b(int i) {
        com.inmobi.rendering.a.c a2 = com.inmobi.rendering.a.c.a();
        HashMap hashMap = new HashMap();
        hashMap.put("[ERRORCODE]", String.valueOf(i));
        for (NativeTracker nativeTracker : this.f.d) {
            if (nativeTracker.b == TrackerEventType.TRACKER_EVENT_TYPE_ERROR) {
                a2.a(com.inmobi.commons.core.utilities.d.a(nativeTracker.a, (Map<String, String>) hashMap), nativeTracker.c);
            }
        }
    }
}
