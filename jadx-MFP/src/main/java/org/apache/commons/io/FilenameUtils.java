package org.apache.commons.io;

import com.brightcove.player.event.EventType;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

public class FilenameUtils {
    public static final String EXTENSION_SEPARATOR_STR = Character.toString('.');
    private static final char OTHER_SEPARATOR;
    private static final char SYSTEM_SEPARATOR = File.separatorChar;

    static {
        if (isSystemWindows()) {
            OTHER_SEPARATOR = '/';
        } else {
            OTHER_SEPARATOR = '\\';
        }
    }

    static boolean isSystemWindows() {
        return SYSTEM_SEPARATOR == '\\';
    }

    public static int indexOfLastSeparator(String str) {
        if (str == null) {
            return -1;
        }
        return Math.max(str.lastIndexOf(47), str.lastIndexOf(92));
    }

    public static int indexOfExtension(String str) {
        int i = -1;
        if (str == null) {
            return -1;
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (indexOfLastSeparator(str) <= lastIndexOf) {
            i = lastIndexOf;
        }
        return i;
    }

    public static String getExtension(String str) {
        if (str == null) {
            return null;
        }
        int indexOfExtension = indexOfExtension(str);
        if (indexOfExtension == -1) {
            return "";
        }
        return str.substring(indexOfExtension + 1);
    }

    public static boolean wildcardMatch(String str, String str2) {
        return wildcardMatch(str, str2, IOCase.SENSITIVE);
    }

    public static boolean wildcardMatch(String str, String str2, IOCase iOCase) {
        boolean z;
        int i;
        boolean z2;
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        if (iOCase == null) {
            iOCase = IOCase.SENSITIVE;
        }
        String[] splitOnTokens = splitOnTokens(str2);
        Stack stack = new Stack();
        boolean z3 = false;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (stack.size() > 0) {
                int[] iArr = (int[]) stack.pop();
                int i4 = iArr[0];
                i = iArr[1];
                i3 = i4;
                z = true;
            } else {
                int i5 = i2;
                z = z3;
                i = i5;
            }
            while (i3 < splitOnTokens.length) {
                if (splitOnTokens[i3].equals("?")) {
                    i++;
                    if (i > str.length()) {
                        break;
                    }
                    z2 = false;
                } else if (!splitOnTokens[i3].equals(EventType.ANY)) {
                    if (!z) {
                        if (!iOCase.checkRegionMatches(str, i, splitOnTokens[i3])) {
                            break;
                        }
                    } else {
                        i = iOCase.checkIndexOf(str, i, splitOnTokens[i3]);
                        if (i == -1) {
                            break;
                        }
                        int checkIndexOf = iOCase.checkIndexOf(str, i + 1, splitOnTokens[i3]);
                        if (checkIndexOf >= 0) {
                            stack.push(new int[]{i3, checkIndexOf});
                        }
                    }
                    i += splitOnTokens[i3].length();
                    z2 = false;
                } else if (i3 == splitOnTokens.length - 1) {
                    i = str.length();
                    z2 = true;
                } else {
                    z2 = true;
                }
                i3++;
            }
            if (i3 == splitOnTokens.length && i == str.length()) {
                return true;
            }
            if (stack.size() <= 0) {
                return false;
            }
            boolean z4 = z;
            i2 = i;
            z3 = z4;
        }
    }

    static String[] splitOnTokens(String str) {
        if (str.indexOf(63) == -1 && str.indexOf(42) == -1) {
            return new String[]{str};
        }
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '?' || charArray[i] == '*') {
                if (sb.length() != 0) {
                    arrayList.add(sb.toString());
                    sb.setLength(0);
                }
                if (charArray[i] == '?') {
                    arrayList.add("?");
                } else if (arrayList.isEmpty() || (i > 0 && !((String) arrayList.get(arrayList.size() - 1)).equals(EventType.ANY))) {
                    arrayList.add(EventType.ANY);
                }
            } else {
                sb.append(charArray[i]);
            }
        }
        if (sb.length() != 0) {
            arrayList.add(sb.toString());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
