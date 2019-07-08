package com.inmobi.ads;

import com.mopub.mobileads.VastExtensionXmlManager;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* compiled from: IasVastHelper */
class x {
    private static final String a = "x";

    x() {
    }

    static void a(Document document, bx bxVar) {
        List<Node> a2 = bu.a(document, VastExtensionXmlManager.AVID);
        if (a2 != null) {
            for (Node a3 : a2) {
                a(a3, bxVar);
            }
        }
    }

    private static void a(Node node, bx bxVar) {
        while (true) {
            if (!node.hasChildNodes()) {
                break;
            }
            String nodeName = node.getNodeName();
            char c = 65535;
            int hashCode = nodeName.hashCode();
            if (hashCode != -2077435339) {
                if (hashCode != -1320080837) {
                    if (hashCode != 2021392) {
                        if (hashCode == 1561251035 && nodeName.equals("JavaScriptResource")) {
                            c = 3;
                        }
                    } else if (nodeName.equals(VastExtensionXmlManager.AVID)) {
                        c = 0;
                    }
                } else if (nodeName.equals(VastExtensionXmlManager.VERIFICATION)) {
                    c = 2;
                }
            } else if (nodeName.equals(VastExtensionXmlManager.AD_VERIFICATIONS)) {
                c = 1;
            }
            switch (c) {
                case 0:
                    node = bu.a(node, VastExtensionXmlManager.AD_VERIFICATIONS);
                    if (node == null) {
                        break;
                    } else {
                        continue;
                    }
                case 1:
                    if (node.hasChildNodes()) {
                        NodeList childNodes = node.getChildNodes();
                        int length = childNodes.getLength();
                        for (int i = 0; i < length; i++) {
                            a(childNodes.item(i), bxVar);
                        }
                        return;
                    }
                    break;
                case 2:
                    node = bu.a(node, "JavaScriptResource");
                    if (node == null) {
                        break;
                    } else {
                        continue;
                    }
                case 3:
                    String a2 = bu.a(node);
                    if (a2 != null) {
                        bxVar.a(new NativeTracker(a2, 0, TrackerEventType.TRACKER_EVENT_TYPE_IAS, null));
                        break;
                    }
                    break;
            }
        }
    }
}
