package org.apache.sanselan.common;

import java.util.ArrayList;
import org.apache.sanselan.common.IImageMetadata.IImageMetadataItem;

public class ImageMetadata implements IImageMetadata {
    protected static final String newline = System.getProperty("line.separator");
    private final ArrayList items = new ArrayList();

    public static class Item implements IImageMetadataItem {
        private final String keyword;
        private final String text;

        public Item(String str, String str2) {
            this.keyword = str;
            this.text = str2;
        }

        public String toString() {
            return toString(null);
        }

        public String toString(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.keyword);
            sb.append(": ");
            sb.append(this.text);
            String sb2 = sb.toString();
            if (str == null) {
                return sb2;
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append(sb2);
            return sb3.toString();
        }
    }

    public void add(IImageMetadataItem iImageMetadataItem) {
        this.items.add(iImageMetadataItem);
    }

    public ArrayList getItems() {
        return new ArrayList(this.items);
    }

    public String toString() {
        return toString(null);
    }

    public String toString(String str) {
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.items.size(); i++) {
            if (i > 0) {
                stringBuffer.append(newline);
            }
            IImageMetadataItem iImageMetadataItem = (IImageMetadataItem) this.items.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("\t");
            stringBuffer.append(iImageMetadataItem.toString(sb.toString()));
        }
        return stringBuffer.toString();
    }
}
