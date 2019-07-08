package org.apache.sanselan.formats.tiff.constants;

public class TagConstantsUtils implements TiffDirectoryConstants {
    public static TagInfo[] mergeTagLists(TagInfo[][] tagInfoArr) {
        int i = 0;
        for (TagInfo[] length : tagInfoArr) {
            i += length.length;
        }
        TagInfo[] tagInfoArr2 = new TagInfo[i];
        int i2 = 0;
        for (int i3 = 0; i3 < tagInfoArr.length; i3++) {
            System.arraycopy(tagInfoArr[i3], 0, tagInfoArr2, i2, tagInfoArr[i3].length);
            i2 += tagInfoArr[i3].length;
        }
        return tagInfoArr2;
    }
}
