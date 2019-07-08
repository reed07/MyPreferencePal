package com.google.android.gms.internal.measurement;

import com.mopub.common.Constants;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.text.Typography;

public final class zzyj {
    public static <T extends zzyi> String zzc(T t) {
        if (t == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            zza(null, t, new StringBuffer(), stringBuffer);
            return stringBuffer.toString();
        } catch (IllegalAccessException e) {
            String str = "Error printing proto: ";
            String valueOf = String.valueOf(e.getMessage());
            return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        } catch (InvocationTargetException e2) {
            String str2 = "Error printing proto: ";
            String valueOf2 = String.valueOf(e2.getMessage());
            return valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2);
        }
    }

    private static void zza(String str, Object obj, StringBuffer stringBuffer, StringBuffer stringBuffer2) throws IllegalAccessException, InvocationTargetException {
        Field[] fields;
        int i;
        if (obj != null) {
            if (obj instanceof zzyi) {
                int length = stringBuffer.length();
                if (str != null) {
                    stringBuffer2.append(stringBuffer);
                    stringBuffer2.append(zzgg(str));
                    stringBuffer2.append(" <\n");
                    stringBuffer.append("  ");
                }
                Class cls = obj.getClass();
                for (Field field : cls.getFields()) {
                    int modifiers = field.getModifiers();
                    String name = field.getName();
                    if (!"cachedSize".equals(name) && (modifiers & 1) == 1 && (modifiers & 8) != 8 && !name.startsWith("_") && !name.endsWith("_")) {
                        Class type = field.getType();
                        Object obj2 = field.get(obj);
                        if (!type.isArray() || type.getComponentType() == Byte.TYPE) {
                            zza(name, obj2, stringBuffer, stringBuffer2);
                        } else {
                            if (obj2 == null) {
                                i = 0;
                            } else {
                                i = Array.getLength(obj2);
                            }
                            for (int i2 = 0; i2 < i; i2++) {
                                zza(name, Array.get(obj2, i2), stringBuffer, stringBuffer2);
                            }
                        }
                    }
                }
                for (Method name2 : cls.getMethods()) {
                    String name3 = name2.getName();
                    if (name3.startsWith("set")) {
                        String substring = name3.substring(3);
                        String str2 = "has";
                        try {
                            String valueOf = String.valueOf(substring);
                            if (((Boolean) cls.getMethod(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), new Class[0]).invoke(obj, new Object[0])).booleanValue()) {
                                String str3 = "get";
                                String valueOf2 = String.valueOf(substring);
                                zza(substring, cls.getMethod(valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3), new Class[0]).invoke(obj, new Object[0]), stringBuffer, stringBuffer2);
                            }
                        } catch (NoSuchMethodException unused) {
                        }
                    }
                }
                if (str != null) {
                    stringBuffer.setLength(length);
                    stringBuffer2.append(stringBuffer);
                    stringBuffer2.append(">\n");
                }
                return;
            }
            String zzgg = zzgg(str);
            stringBuffer2.append(stringBuffer);
            stringBuffer2.append(zzgg);
            stringBuffer2.append(": ");
            if (obj instanceof String) {
                String str4 = (String) obj;
                if (!str4.startsWith(Constants.HTTP) && str4.length() > 200) {
                    str4 = String.valueOf(str4.substring(0, 200)).concat("[...]");
                }
                int length2 = str4.length();
                StringBuilder sb = new StringBuilder(length2);
                for (int i3 = 0; i3 < length2; i3++) {
                    char charAt = str4.charAt(i3);
                    if (charAt < ' ' || charAt > '~' || charAt == '\"' || charAt == '\'') {
                        sb.append(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                    } else {
                        sb.append(charAt);
                    }
                }
                String sb2 = sb.toString();
                stringBuffer2.append("\"");
                stringBuffer2.append(sb2);
                stringBuffer2.append("\"");
            } else if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                if (bArr == null) {
                    stringBuffer2.append("\"\"");
                } else {
                    stringBuffer2.append(Typography.quote);
                    for (byte b : bArr) {
                        byte b2 = b & 255;
                        if (b2 == 92 || b2 == 34) {
                            stringBuffer2.append('\\');
                            stringBuffer2.append((char) b2);
                        } else if (b2 < 32 || b2 >= Byte.MAX_VALUE) {
                            stringBuffer2.append(String.format("\\%03o", new Object[]{Integer.valueOf(b2)}));
                        } else {
                            stringBuffer2.append((char) b2);
                        }
                    }
                    stringBuffer2.append(Typography.quote);
                }
            } else {
                stringBuffer2.append(obj);
            }
            stringBuffer2.append("\n");
        }
    }

    private static String zzgg(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (i == 0) {
                stringBuffer.append(Character.toLowerCase(charAt));
            } else if (Character.isUpperCase(charAt)) {
                stringBuffer.append('_');
                stringBuffer.append(Character.toLowerCase(charAt));
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }
}
