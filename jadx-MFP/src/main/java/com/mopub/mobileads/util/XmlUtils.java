package com.mopub.mobileads.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtils {

    public interface NodeProcessor<T> {
        T process(Node node);
    }

    private XmlUtils() {
    }

    public static Node getFirstMatchingChildNode(Node node, String str) {
        return getFirstMatchingChildNode(node, str, null, null);
    }

    public static Node getFirstMatchingChildNode(Node node, String str, String str2, List<String> list) {
        if (node == null || str == null) {
            return null;
        }
        List matchingChildNodes = getMatchingChildNodes(node, str, str2, list);
        if (matchingChildNodes == null || matchingChildNodes.isEmpty()) {
            return null;
        }
        return (Node) matchingChildNodes.get(0);
    }

    public static List<Node> getMatchingChildNodes(Node node, String str) {
        return getMatchingChildNodes(node, str, null, null);
    }

    public static List<Node> getMatchingChildNodes(Node node, String str, String str2, List<String> list) {
        if (node == null || str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeName().equals(str) && nodeMatchesAttributeFilter(item, str2, list)) {
                arrayList.add(item);
            }
        }
        return arrayList;
    }

    public static boolean nodeMatchesAttributeFilter(Node node, String str, List<String> list) {
        if (str == null || list == null) {
            return true;
        }
        NamedNodeMap attributes = node.getAttributes();
        if (attributes != null) {
            Node namedItem = attributes.getNamedItem(str);
            if (namedItem != null && list.contains(namedItem.getNodeValue())) {
                return true;
            }
        }
        return false;
    }

    public static String getNodeValue(Node node) {
        if (node == null || node.getFirstChild() == null || node.getFirstChild().getNodeValue() == null) {
            return null;
        }
        return node.getFirstChild().getNodeValue().trim();
    }

    public static Integer getAttributeValueAsInt(Node node, String str) {
        if (node == null || str == null) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(getAttributeValue(node, str)));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static String getAttributeValue(Node node, String str) {
        if (node == null || str == null) {
            return null;
        }
        Node namedItem = node.getAttributes().getNamedItem(str);
        if (namedItem != null) {
            return namedItem.getNodeValue();
        }
        return null;
    }

    public static <T> List<T> getListFromDocument(Document document, String str, String str2, String str3, NodeProcessor<T> nodeProcessor) {
        List list;
        ArrayList arrayList = new ArrayList();
        if (document == null) {
            return arrayList;
        }
        NodeList elementsByTagName = document.getElementsByTagName(str);
        if (elementsByTagName == null) {
            return arrayList;
        }
        if (str3 == null) {
            list = null;
        } else {
            list = Arrays.asList(new String[]{str3});
        }
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            Node item = elementsByTagName.item(i);
            if (item != null && nodeMatchesAttributeFilter(item, str2, list)) {
                Object process = nodeProcessor.process(item);
                if (process != null) {
                    arrayList.add(process);
                }
            }
        }
        return arrayList;
    }

    public static <T> T getFirstMatchFromDocument(Document document, String str, String str2, String str3, NodeProcessor<T> nodeProcessor) {
        List list;
        if (document == null) {
            return null;
        }
        NodeList elementsByTagName = document.getElementsByTagName(str);
        if (elementsByTagName == null) {
            return null;
        }
        if (str3 == null) {
            list = null;
        } else {
            list = Arrays.asList(new String[]{str3});
        }
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            Node item = elementsByTagName.item(i);
            if (item != null && nodeMatchesAttributeFilter(item, str2, list)) {
                T process = nodeProcessor.process(item);
                if (process != null) {
                    return process;
                }
            }
        }
        return null;
    }

    public static String getFirstMatchingStringData(Document document, String str) {
        return getFirstMatchingStringData(document, str, null, null);
    }

    public static String getFirstMatchingStringData(Document document, String str, String str2, String str3) {
        return (String) getFirstMatchFromDocument(document, str, str2, str3, new NodeProcessor<String>() {
            public String process(Node node) {
                return XmlUtils.getNodeValue(node);
            }
        });
    }

    public static List<String> getStringDataAsList(Document document, String str) {
        return getStringDataAsList(document, str, null, null);
    }

    public static List<String> getStringDataAsList(Document document, String str, String str2, String str3) {
        return getListFromDocument(document, str, str2, str3, new NodeProcessor<String>() {
            public String process(Node node) {
                return XmlUtils.getNodeValue(node);
            }
        });
    }

    public static List<Node> getNodesWithElementAndAttribute(Document document, String str, String str2, String str3) {
        return getListFromDocument(document, str, str2, str3, new NodeProcessor<Node>() {
            public Node process(Node node) {
                return node;
            }
        });
    }
}
