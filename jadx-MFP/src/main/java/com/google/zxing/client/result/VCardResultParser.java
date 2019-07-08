package com.google.zxing.client.result;

import com.facebook.share.internal.ShareConstants;
import com.google.zxing.Result;
import com.mopub.common.MoPubBrowser;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VCardResultParser extends ResultParser {
    private static final Pattern BEGIN_VCARD = Pattern.compile("BEGIN:VCARD", 2);
    private static final Pattern COMMA = Pattern.compile(",");
    private static final Pattern CR_LF_SPACE_TAB = Pattern.compile("\r\n[ \t]");
    private static final Pattern EQUALS = Pattern.compile("=");
    private static final Pattern NEWLINE_ESCAPE = Pattern.compile("\\\\[nN]");
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final Pattern SEMICOLON_OR_COMMA = Pattern.compile("[;,]");
    private static final Pattern UNESCAPED_SEMICOLONS = Pattern.compile("(?<!\\\\);+");
    private static final Pattern VCARD_ESCAPES = Pattern.compile("\\\\([,;\\\\])");
    private static final Pattern VCARD_LIKE_DATE = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");

    public AddressBookParsedResult parse(Result result) {
        String[] strArr;
        String[] strArr2;
        String massagedText = getMassagedText(result);
        Matcher matcher = BEGIN_VCARD.matcher(massagedText);
        if (!matcher.find() || matcher.start() != 0) {
            return null;
        }
        List matchVCardPrefixedField = matchVCardPrefixedField("FN", massagedText, true, false);
        if (matchVCardPrefixedField == null) {
            matchVCardPrefixedField = matchVCardPrefixedField("N", massagedText, true, false);
            formatNames(matchVCardPrefixedField);
        }
        List matchSingleVCardPrefixedField = matchSingleVCardPrefixedField("NICKNAME", massagedText, true, false);
        if (matchSingleVCardPrefixedField == null) {
            strArr = null;
        } else {
            strArr = COMMA.split((CharSequence) matchSingleVCardPrefixedField.get(0));
        }
        List matchVCardPrefixedField2 = matchVCardPrefixedField("TEL", massagedText, true, false);
        List matchVCardPrefixedField3 = matchVCardPrefixedField("EMAIL", massagedText, true, false);
        List matchSingleVCardPrefixedField2 = matchSingleVCardPrefixedField("NOTE", massagedText, false, false);
        List matchVCardPrefixedField4 = matchVCardPrefixedField("ADR", massagedText, true, true);
        List matchSingleVCardPrefixedField3 = matchSingleVCardPrefixedField("ORG", massagedText, true, true);
        List matchSingleVCardPrefixedField4 = matchSingleVCardPrefixedField("BDAY", massagedText, true, false);
        List list = (matchSingleVCardPrefixedField4 == null || isLikeVCardDate((CharSequence) matchSingleVCardPrefixedField4.get(0))) ? matchSingleVCardPrefixedField4 : null;
        List matchSingleVCardPrefixedField5 = matchSingleVCardPrefixedField(ShareConstants.TITLE, massagedText, true, false);
        List matchVCardPrefixedField5 = matchVCardPrefixedField(MoPubBrowser.DESTINATION_URL_KEY, massagedText, true, false);
        List matchSingleVCardPrefixedField6 = matchSingleVCardPrefixedField("IMPP", massagedText, true, false);
        List matchSingleVCardPrefixedField7 = matchSingleVCardPrefixedField("GEO", massagedText, true, false);
        if (matchSingleVCardPrefixedField7 == null) {
            strArr2 = null;
        } else {
            strArr2 = SEMICOLON_OR_COMMA.split((CharSequence) matchSingleVCardPrefixedField7.get(0));
        }
        AddressBookParsedResult addressBookParsedResult = new AddressBookParsedResult(toPrimaryValues(matchVCardPrefixedField), strArr, null, toPrimaryValues(matchVCardPrefixedField2), toTypes(matchVCardPrefixedField2), toPrimaryValues(matchVCardPrefixedField3), toTypes(matchVCardPrefixedField3), toPrimaryValue(matchSingleVCardPrefixedField6), toPrimaryValue(matchSingleVCardPrefixedField2), toPrimaryValues(matchVCardPrefixedField4), toTypes(matchVCardPrefixedField4), toPrimaryValue(matchSingleVCardPrefixedField3), toPrimaryValue(list), toPrimaryValue(matchSingleVCardPrefixedField5), toPrimaryValues(matchVCardPrefixedField5), (strArr2 == null || strArr2.length == 2) ? strArr2 : null);
        return addressBookParsedResult;
    }

    static List<List<String>> matchVCardPrefixedField(CharSequence charSequence, String str, boolean z, boolean z2) {
        String str2;
        boolean z3;
        ArrayList arrayList;
        int indexOf;
        String str3;
        String str4 = str;
        int length = str.length();
        int i = 0;
        int i2 = 0;
        List<List<String>> list = null;
        while (i2 < length) {
            StringBuilder sb = new StringBuilder("(?:^|\n)");
            sb.append(charSequence);
            sb.append("(?:;([^:]*))?:");
            Matcher matcher = Pattern.compile(sb.toString(), 2).matcher(str4);
            if (i2 > 0) {
                i2--;
            }
            if (!matcher.find(i2)) {
                break;
            }
            int end = matcher.end(i);
            String group = matcher.group(1);
            if (group != null) {
                String[] split = SEMICOLON.split(group);
                int length2 = split.length;
                int i3 = 0;
                arrayList = null;
                z3 = false;
                str2 = null;
                while (i3 < length2) {
                    String str5 = split[i3];
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                    }
                    arrayList.add(str5);
                    String[] split2 = EQUALS.split(str5, 2);
                    if (split2.length > 1) {
                        String str6 = split2[i];
                        String str7 = split2[1];
                        if ("ENCODING".equalsIgnoreCase(str6) && "QUOTED-PRINTABLE".equalsIgnoreCase(str7)) {
                            z3 = true;
                        } else if ("CHARSET".equalsIgnoreCase(str6)) {
                            str2 = str7;
                        }
                    }
                    i3++;
                    i = 0;
                }
            } else {
                arrayList = null;
                z3 = false;
                str2 = null;
            }
            int i4 = end;
            while (true) {
                indexOf = str4.indexOf(10, i4);
                if (indexOf >= 0) {
                    if (indexOf < str.length() - 1) {
                        int i5 = indexOf + 1;
                        if (str4.charAt(i5) == ' ' || str4.charAt(i5) == 9) {
                            i4 = indexOf + 2;
                        }
                    }
                    if (!z3 || ((indexOf <= 0 || str4.charAt(indexOf - 1) != '=') && (indexOf < 2 || str4.charAt(indexOf - 2) != '='))) {
                        break;
                    }
                    i4 = indexOf + 1;
                } else {
                    break;
                }
            }
            if (indexOf < 0) {
                i2 = length;
                i = 0;
            } else if (indexOf > end) {
                if (list == null) {
                    list = new ArrayList<>(1);
                }
                if (indexOf > 0 && str4.charAt(indexOf - 1) == 13) {
                    indexOf--;
                }
                String substring = str4.substring(end, indexOf);
                if (z) {
                    substring = substring.trim();
                }
                if (z3) {
                    str3 = decodeQuotedPrintable(substring, str2);
                    if (z2) {
                        str3 = UNESCAPED_SEMICOLONS.matcher(str3).replaceAll("\n").trim();
                    }
                } else {
                    if (z2) {
                        substring = UNESCAPED_SEMICOLONS.matcher(substring).replaceAll("\n").trim();
                    }
                    str3 = VCARD_ESCAPES.matcher(NEWLINE_ESCAPE.matcher(CR_LF_SPACE_TAB.matcher(substring).replaceAll("")).replaceAll("\n")).replaceAll("$1");
                }
                if (arrayList == null) {
                    ArrayList arrayList2 = new ArrayList(1);
                    arrayList2.add(str3);
                    list.add(arrayList2);
                } else {
                    arrayList.add(0, str3);
                    list.add(arrayList);
                }
                i2 = indexOf + 1;
                i = 0;
            } else {
                i2 = indexOf + 1;
                i = 0;
            }
        }
        return list;
    }

    private static String decodeQuotedPrintable(CharSequence charSequence, String str) {
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (!(charAt == 10 || charAt == 13)) {
                if (charAt != '=') {
                    maybeAppendFragment(byteArrayOutputStream, str, sb);
                    sb.append(charAt);
                } else if (i < length - 2) {
                    char charAt2 = charSequence.charAt(i + 1);
                    if (!(charAt2 == 13 || charAt2 == 10)) {
                        i += 2;
                        char charAt3 = charSequence.charAt(i);
                        int parseHexDigit = parseHexDigit(charAt2);
                        int parseHexDigit2 = parseHexDigit(charAt3);
                        if (parseHexDigit >= 0 && parseHexDigit2 >= 0) {
                            byteArrayOutputStream.write((parseHexDigit << 4) + parseHexDigit2);
                        }
                    }
                }
            }
            i++;
        }
        maybeAppendFragment(byteArrayOutputStream, str, sb);
        return sb.toString();
    }

    private static void maybeAppendFragment(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuilder sb) {
        String str2;
        if (byteArrayOutputStream.size() > 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(byteArray, Charset.forName("UTF-8"));
            } else {
                try {
                    str2 = new String(byteArray, str);
                } catch (UnsupportedEncodingException unused) {
                    str2 = new String(byteArray, Charset.forName("UTF-8"));
                }
            }
            byteArrayOutputStream.reset();
            sb.append(str2);
        }
    }

    static List<String> matchSingleVCardPrefixedField(CharSequence charSequence, String str, boolean z, boolean z2) {
        List matchVCardPrefixedField = matchVCardPrefixedField(charSequence, str, z, z2);
        if (matchVCardPrefixedField == null || matchVCardPrefixedField.isEmpty()) {
            return null;
        }
        return (List) matchVCardPrefixedField.get(0);
    }

    private static String toPrimaryValue(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String) list.get(0);
    }

    private static String[] toPrimaryValues(Collection<List<String>> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List list : collection) {
            String str = (String) list.get(0);
            if (str != null && !str.isEmpty()) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[collection.size()]);
    }

    private static String[] toTypes(Collection<List<String>> collection) {
        String str;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List list : collection) {
            int i = 1;
            while (true) {
                if (i >= list.size()) {
                    str = null;
                    break;
                }
                String str2 = (String) list.get(i);
                int indexOf = str2.indexOf(61);
                if (indexOf < 0) {
                    str = str2;
                    break;
                } else if ("TYPE".equalsIgnoreCase(str2.substring(0, indexOf))) {
                    str = str2.substring(indexOf + 1);
                    break;
                } else {
                    i++;
                }
            }
            arrayList.add(str);
        }
        return (String[]) arrayList.toArray(new String[collection.size()]);
    }

    private static boolean isLikeVCardDate(CharSequence charSequence) {
        return charSequence == null || VCARD_LIKE_DATE.matcher(charSequence).matches();
    }

    private static void formatNames(Iterable<List<String>> iterable) {
        if (iterable != null) {
            for (List list : iterable) {
                String str = (String) list.get(0);
                String[] strArr = new String[5];
                int i = 0;
                int i2 = 0;
                while (i < 4) {
                    int indexOf = str.indexOf(59, i2);
                    if (indexOf < 0) {
                        break;
                    }
                    strArr[i] = str.substring(i2, indexOf);
                    i++;
                    i2 = indexOf + 1;
                }
                strArr[i] = str.substring(i2);
                StringBuilder sb = new StringBuilder(100);
                maybeAppendComponent(strArr, 3, sb);
                maybeAppendComponent(strArr, 1, sb);
                maybeAppendComponent(strArr, 2, sb);
                maybeAppendComponent(strArr, 0, sb);
                maybeAppendComponent(strArr, 4, sb);
                list.set(0, sb.toString().trim());
            }
        }
    }

    private static void maybeAppendComponent(String[] strArr, int i, StringBuilder sb) {
        if (strArr[i] != null && !strArr[i].isEmpty()) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(strArr[i]);
        }
    }
}
