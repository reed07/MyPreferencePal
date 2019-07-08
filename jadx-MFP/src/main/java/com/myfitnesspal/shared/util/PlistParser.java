package com.myfitnesspal.shared.util;

import android.util.Xml;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class PlistParser {
    private HashMap<String, Object> pListMap = new HashMap<>();

    public ArrayList<Object> getParsedList() {
        return (ArrayList) this.pListMap.get("array");
    }

    public HashMap<String, Object> getParsedDict() {
        return this.pListMap;
    }

    public PlistParser(InputStream inputStream) {
        if (inputStream != null) {
            parse(inputStream);
        }
    }

    public PlistParser(Reader reader) {
        if (reader != null) {
            parse(reader);
        }
    }

    public String getConfiguration(String str) {
        return (String) getConfigurationObject(str);
    }

    public String getConfigurationWithDefault(String str, String str2) {
        String configuration = getConfiguration(str);
        return configuration == null ? str2 : configuration;
    }

    public Integer getConfigurationInteger(String str) {
        return (Integer) getConfigurationObject(str);
    }

    public Integer getConfigurationIntegerWithDefault(String str, Integer num) {
        Integer configurationInteger = getConfigurationInteger(str);
        return configurationInteger == null ? num : configurationInteger;
    }

    public void parse(Reader reader) {
        XmlPullParser newPullParser = Xml.newPullParser();
        try {
            newPullParser.setInput(reader);
            Stack stack = new Stack();
            HashMap<String, Object> hashMap = null;
            String str = null;
            ArrayList arrayList = null;
            boolean z = false;
            boolean z2 = false;
            int i = 0;
            for (int eventType = newPullParser.getEventType(); eventType != 1 && !z; eventType = newPullParser.next()) {
                if (eventType != 0) {
                    switch (eventType) {
                        case 2:
                            String name = newPullParser.getName();
                            if (hashMap == null) {
                                hashMap = this.pListMap;
                            }
                            if (!name.equalsIgnoreCase("dict")) {
                                if (!name.equalsIgnoreCase(IpcUtil.KEY_CODE)) {
                                    if (!name.equalsIgnoreCase("integer")) {
                                        if (!name.equalsIgnoreCase("string")) {
                                            if (!name.equalsIgnoreCase("array")) {
                                                break;
                                            } else {
                                                ArrayList arrayList2 = new ArrayList();
                                                hashMap.put(name, arrayList2);
                                                arrayList = arrayList2;
                                                i = newPullParser.getDepth();
                                                z2 = true;
                                                str = name;
                                                break;
                                            }
                                        } else if (z2 && newPullParser.getDepth() == i + 1) {
                                            arrayList.add(newPullParser.nextText());
                                            break;
                                        } else {
                                            hashMap.put(str, newPullParser.nextText());
                                            break;
                                        }
                                    } else {
                                        hashMap.put(str, new Integer(newPullParser.nextText()));
                                        break;
                                    }
                                } else {
                                    str = newPullParser.nextText();
                                    break;
                                }
                            } else if (str != null) {
                                if (!z2) {
                                    HashMap<String, Object> hashMap2 = new HashMap<>();
                                    hashMap.put(str, hashMap2);
                                    stack.push(hashMap);
                                    hashMap = hashMap2;
                                    break;
                                } else {
                                    HashMap<String, Object> hashMap3 = new HashMap<>();
                                    arrayList.add(hashMap3);
                                    stack.push(hashMap);
                                    hashMap = hashMap3;
                                    break;
                                }
                            } else {
                                this.pListMap.clear();
                                hashMap = this.pListMap;
                                break;
                            }
                        case 3:
                            String name2 = newPullParser.getName();
                            if (!name2.equalsIgnoreCase("dict")) {
                                if (!name2.equalsIgnoreCase("array")) {
                                    if (!name2.equalsIgnoreCase("plist")) {
                                        break;
                                    } else {
                                        z = true;
                                        break;
                                    }
                                } else {
                                    arrayList = null;
                                    z2 = false;
                                    break;
                                }
                            } else if (stack.empty()) {
                                break;
                            } else {
                                hashMap = (HashMap) stack.pop();
                                break;
                            }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parse(InputStream inputStream) {
        XmlPullParser newPullParser = Xml.newPullParser();
        try {
            newPullParser.setInput(inputStream, null);
            Stack stack = new Stack();
            HashMap<String, Object> hashMap = null;
            String str = null;
            ArrayList arrayList = null;
            boolean z = false;
            boolean z2 = false;
            int i = 0;
            for (int eventType = newPullParser.getEventType(); eventType != 1 && !z; eventType = newPullParser.next()) {
                if (eventType != 0) {
                    switch (eventType) {
                        case 2:
                            String name = newPullParser.getName();
                            if (hashMap == null) {
                                hashMap = this.pListMap;
                            }
                            if (!name.equalsIgnoreCase("dict")) {
                                if (!name.equalsIgnoreCase(IpcUtil.KEY_CODE)) {
                                    if (!name.equalsIgnoreCase("integer")) {
                                        if (!name.equalsIgnoreCase("string")) {
                                            if (!name.equalsIgnoreCase("array")) {
                                                break;
                                            } else {
                                                ArrayList arrayList2 = new ArrayList();
                                                hashMap.put(name, arrayList2);
                                                arrayList = arrayList2;
                                                i = newPullParser.getDepth();
                                                z2 = true;
                                                str = name;
                                                break;
                                            }
                                        } else if (z2 && newPullParser.getDepth() == i + 1) {
                                            arrayList.add(newPullParser.nextText());
                                            break;
                                        } else {
                                            hashMap.put(str, newPullParser.nextText());
                                            break;
                                        }
                                    } else {
                                        hashMap.put(str, new Integer(newPullParser.nextText()));
                                        break;
                                    }
                                } else {
                                    str = newPullParser.nextText();
                                    break;
                                }
                            } else if (str != null) {
                                if (!z2) {
                                    HashMap<String, Object> hashMap2 = new HashMap<>();
                                    hashMap.put(str, hashMap2);
                                    stack.push(hashMap);
                                    hashMap = hashMap2;
                                    break;
                                } else {
                                    HashMap<String, Object> hashMap3 = new HashMap<>();
                                    arrayList.add(hashMap3);
                                    stack.push(hashMap);
                                    hashMap = hashMap3;
                                    break;
                                }
                            } else {
                                this.pListMap.clear();
                                hashMap = this.pListMap;
                                break;
                            }
                        case 3:
                            String name2 = newPullParser.getName();
                            if (!name2.equalsIgnoreCase("dict")) {
                                if (!name2.equalsIgnoreCase("array")) {
                                    if (!name2.equalsIgnoreCase("plist")) {
                                        break;
                                    } else {
                                        z = true;
                                        break;
                                    }
                                } else {
                                    arrayList = null;
                                    z2 = false;
                                    break;
                                }
                            } else if (stack.empty()) {
                                break;
                            } else {
                                hashMap = (HashMap) stack.pop();
                                break;
                            }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public Object getConfigurationObject(String str) {
        String[] split = str.split("\\.");
        if (split.length > 1) {
            HashMap<String, Object> hashMap = this.pListMap;
            for (String str2 : split) {
                Object obj = hashMap.get(str2);
                if (!(obj instanceof HashMap)) {
                    return obj;
                }
                hashMap = (HashMap) obj;
            }
        }
        return this.pListMap.get(str);
    }

    public static String toJsonString(String str) {
        return toJsonString(new BufferedReader(new StringReader(str)));
    }

    public static String toJsonString(BufferedReader bufferedReader) {
        StringBuilder sb = new StringBuilder();
        try {
            XmlPullParser newPullParser = Xml.newPullParser();
            try {
                newPullParser.setInput(bufferedReader);
                char c = 65535;
                for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                    if (eventType != 0) {
                        switch (eventType) {
                            case 2:
                                String name = newPullParser.getName();
                                if (!name.equalsIgnoreCase("dict")) {
                                    if (!name.equalsIgnoreCase("array")) {
                                        if (!name.equalsIgnoreCase(IpcUtil.KEY_CODE)) {
                                            if (!name.equals("string") && !name.equals("integer") && !name.equals("data") && !name.equals("date")) {
                                                if (!name.equals("real")) {
                                                    if (!name.equals("true") && !name.equals("false")) {
                                                        break;
                                                    } else {
                                                        if (c == 4) {
                                                            sb.append(",");
                                                        }
                                                        String valueOf = String.valueOf(name.equals("true"));
                                                        sb.append(" \"");
                                                        sb.append(replaceSpecialCharacters(valueOf));
                                                        sb.append("\" ");
                                                        c = 4;
                                                        break;
                                                    }
                                                }
                                            }
                                            if (c == 4) {
                                                sb.append(",");
                                            }
                                            String nextText = newPullParser.nextText();
                                            sb.append(" \"");
                                            sb.append(replaceSpecialCharacters(nextText));
                                            sb.append("\" ");
                                            c = 4;
                                            break;
                                        } else {
                                            String nextText2 = newPullParser.nextText();
                                            if (sb.charAt(sb.length() - 1) != '{') {
                                                sb.append(",");
                                            }
                                            sb.append(" \"");
                                            sb.append(replaceSpecialCharacters(nextText2));
                                            sb.append("\" ");
                                            sb.append(":");
                                            c = 3;
                                            break;
                                        }
                                    } else {
                                        sb.append("[");
                                        c = 2;
                                        break;
                                    }
                                } else {
                                    if (sb.length() > 1 && sb.charAt(sb.length() - 1) == '}') {
                                        sb.append(",");
                                    }
                                    sb.append("{");
                                    c = 1;
                                    break;
                                }
                                break;
                            case 3:
                                String name2 = newPullParser.getName();
                                if (!name2.equalsIgnoreCase("dict")) {
                                    if (!name2.equalsIgnoreCase("array")) {
                                        if (name2.equals("string")) {
                                            break;
                                        } else {
                                            name2.equals("integer");
                                            break;
                                        }
                                    } else {
                                        sb.append("]");
                                        break;
                                    }
                                } else {
                                    sb.append("}");
                                    break;
                                }
                        }
                    }
                }
            } catch (XmlPullParserException e) {
                Ln.e(e);
            }
        } catch (Exception e2) {
            Ln.e(e2);
        }
        return sb.toString();
    }

    private static String replaceSpecialCharacters(String str) {
        return str.replace("\"", "\\\"").replace("\n", "\\n");
    }

    public static JSONObject parsePlistReaderToJSONObject(BufferedReader bufferedReader) {
        String jsonString = toJsonString(bufferedReader);
        if (Strings.notEmpty(jsonString) && jsonString.startsWith("{")) {
            try {
                return new JSONObject(jsonString);
            } catch (JSONException e) {
                Ln.e(e);
            }
        }
        return null;
    }
}
