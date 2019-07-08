package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class LegacyTokenHelper {
    public static final String APPLICATION_ID_KEY = "com.facebook.TokenCachingStrategy.ApplicationId";
    public static final String DECLINED_PERMISSIONS_KEY = "com.facebook.TokenCachingStrategy.DeclinedPermissions";
    public static final String DEFAULT_CACHE_KEY = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
    public static final String EXPIRATION_DATE_KEY = "com.facebook.TokenCachingStrategy.ExpirationDate";
    private static final long INVALID_BUNDLE_MILLISECONDS = Long.MIN_VALUE;
    private static final String IS_SSO_KEY = "com.facebook.TokenCachingStrategy.IsSSO";
    private static final String JSON_VALUE = "value";
    private static final String JSON_VALUE_ENUM_TYPE = "enumType";
    private static final String JSON_VALUE_TYPE = "valueType";
    public static final String LAST_REFRESH_DATE_KEY = "com.facebook.TokenCachingStrategy.LastRefreshDate";
    public static final String PERMISSIONS_KEY = "com.facebook.TokenCachingStrategy.Permissions";
    private static final String TAG = "LegacyTokenHelper";
    public static final String TOKEN_KEY = "com.facebook.TokenCachingStrategy.Token";
    public static final String TOKEN_SOURCE_KEY = "com.facebook.TokenCachingStrategy.AccessTokenSource";
    private static final String TYPE_BOOLEAN = "bool";
    private static final String TYPE_BOOLEAN_ARRAY = "bool[]";
    private static final String TYPE_BYTE = "byte";
    private static final String TYPE_BYTE_ARRAY = "byte[]";
    private static final String TYPE_CHAR = "char";
    private static final String TYPE_CHAR_ARRAY = "char[]";
    private static final String TYPE_DOUBLE = "double";
    private static final String TYPE_DOUBLE_ARRAY = "double[]";
    private static final String TYPE_ENUM = "enum";
    private static final String TYPE_FLOAT = "float";
    private static final String TYPE_FLOAT_ARRAY = "float[]";
    private static final String TYPE_INTEGER = "int";
    private static final String TYPE_INTEGER_ARRAY = "int[]";
    private static final String TYPE_LONG = "long";
    private static final String TYPE_LONG_ARRAY = "long[]";
    private static final String TYPE_SHORT = "short";
    private static final String TYPE_SHORT_ARRAY = "short[]";
    private static final String TYPE_STRING = "string";
    private static final String TYPE_STRING_LIST = "stringList";
    private SharedPreferences cache;
    private String cacheKey;

    public LegacyTokenHelper(Context context) {
        this(context, null);
    }

    public LegacyTokenHelper(Context context, String str) {
        Validate.notNull(context, "context");
        if (Utility.isNullOrEmpty(str)) {
            str = DEFAULT_CACHE_KEY;
        }
        this.cacheKey = str;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.cache = context.getSharedPreferences(this.cacheKey, 0);
    }

    public Bundle load() {
        Bundle bundle = new Bundle();
        for (String str : this.cache.getAll().keySet()) {
            try {
                deserializeKey(str, bundle);
            } catch (JSONException e) {
                LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                String str2 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Error reading cached value for key: '");
                sb.append(str);
                sb.append("' -- ");
                sb.append(e);
                Logger.log(loggingBehavior, 5, str2, sb.toString());
                return null;
            }
        }
        return bundle;
    }

    public void save(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        Editor edit = this.cache.edit();
        for (String str : bundle.keySet()) {
            try {
                serializeKey(str, bundle, edit);
            } catch (JSONException e) {
                LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                String str2 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Error processing value for key: '");
                sb.append(str);
                sb.append("' -- ");
                sb.append(e);
                Logger.log(loggingBehavior, 5, str2, sb.toString());
                return;
            }
        }
        edit.apply();
    }

    public void clear() {
        this.cache.edit().clear().apply();
    }

    public static boolean hasTokenInformation(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        String string = bundle.getString(TOKEN_KEY);
        if (string == null || string.length() == 0 || bundle.getLong(EXPIRATION_DATE_KEY, 0) == 0) {
            return false;
        }
        return true;
    }

    public static String getToken(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getString(TOKEN_KEY);
    }

    public static void putToken(Bundle bundle, String str) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(str, "value");
        bundle.putString(TOKEN_KEY, str);
    }

    public static Date getExpirationDate(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return getDate(bundle, EXPIRATION_DATE_KEY);
    }

    public static void putExpirationDate(Bundle bundle, Date date) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(date, "value");
        putDate(bundle, EXPIRATION_DATE_KEY, date);
    }

    public static long getExpirationMilliseconds(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getLong(EXPIRATION_DATE_KEY);
    }

    public static void putExpirationMilliseconds(Bundle bundle, long j) {
        Validate.notNull(bundle, "bundle");
        bundle.putLong(EXPIRATION_DATE_KEY, j);
    }

    public static Set<String> getPermissions(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        ArrayList stringArrayList = bundle.getStringArrayList(PERMISSIONS_KEY);
        if (stringArrayList == null) {
            return null;
        }
        return new HashSet(stringArrayList);
    }

    public static void putPermissions(Bundle bundle, Collection<String> collection) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(collection, "value");
        bundle.putStringArrayList(PERMISSIONS_KEY, new ArrayList(collection));
    }

    public static void putDeclinedPermissions(Bundle bundle, Collection<String> collection) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(collection, "value");
        bundle.putStringArrayList(DECLINED_PERMISSIONS_KEY, new ArrayList(collection));
    }

    public static AccessTokenSource getSource(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        if (bundle.containsKey(TOKEN_SOURCE_KEY)) {
            return (AccessTokenSource) bundle.getSerializable(TOKEN_SOURCE_KEY);
        }
        return bundle.getBoolean(IS_SSO_KEY) ? AccessTokenSource.FACEBOOK_APPLICATION_WEB : AccessTokenSource.WEB_VIEW;
    }

    public static void putSource(Bundle bundle, AccessTokenSource accessTokenSource) {
        Validate.notNull(bundle, "bundle");
        bundle.putSerializable(TOKEN_SOURCE_KEY, accessTokenSource);
    }

    public static Date getLastRefreshDate(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return getDate(bundle, LAST_REFRESH_DATE_KEY);
    }

    public static void putLastRefreshDate(Bundle bundle, Date date) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(date, "value");
        putDate(bundle, LAST_REFRESH_DATE_KEY, date);
    }

    public static long getLastRefreshMilliseconds(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getLong(LAST_REFRESH_DATE_KEY);
    }

    public static void putLastRefreshMilliseconds(Bundle bundle, long j) {
        Validate.notNull(bundle, "bundle");
        bundle.putLong(LAST_REFRESH_DATE_KEY, j);
    }

    public static String getApplicationId(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getString(APPLICATION_ID_KEY);
    }

    public static void putApplicationId(Bundle bundle, String str) {
        Validate.notNull(bundle, "bundle");
        bundle.putString(APPLICATION_ID_KEY, str);
    }

    static Date getDate(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        long j = bundle.getLong(str, Long.MIN_VALUE);
        if (j == Long.MIN_VALUE) {
            return null;
        }
        return new Date(j);
    }

    static void putDate(Bundle bundle, String str, Date date) {
        bundle.putLong(str, date.getTime());
    }

    private void serializeKey(String str, Bundle bundle, Editor editor) throws JSONException {
        Object obj;
        Object obj2 = bundle.get(str);
        if (obj2 != null) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = null;
            if (obj2 instanceof Byte) {
                obj = TYPE_BYTE;
                jSONObject.put("value", ((Byte) obj2).intValue());
            } else if (obj2 instanceof Short) {
                obj = TYPE_SHORT;
                jSONObject.put("value", ((Short) obj2).intValue());
            } else if (obj2 instanceof Integer) {
                obj = TYPE_INTEGER;
                jSONObject.put("value", ((Integer) obj2).intValue());
            } else if (obj2 instanceof Long) {
                obj = TYPE_LONG;
                jSONObject.put("value", ((Long) obj2).longValue());
            } else if (obj2 instanceof Float) {
                obj = TYPE_FLOAT;
                jSONObject.put("value", ((Float) obj2).doubleValue());
            } else if (obj2 instanceof Double) {
                obj = TYPE_DOUBLE;
                jSONObject.put("value", ((Double) obj2).doubleValue());
            } else if (obj2 instanceof Boolean) {
                obj = TYPE_BOOLEAN;
                jSONObject.put("value", ((Boolean) obj2).booleanValue());
            } else if (obj2 instanceof Character) {
                obj = TYPE_CHAR;
                jSONObject.put("value", obj2.toString());
            } else if (obj2 instanceof String) {
                obj = TYPE_STRING;
                jSONObject.put("value", (String) obj2);
            } else if (obj2 instanceof Enum) {
                obj = TYPE_ENUM;
                jSONObject.put("value", obj2.toString());
                jSONObject.put(JSON_VALUE_ENUM_TYPE, obj2.getClass().getName());
            } else {
                JSONArray jSONArray2 = new JSONArray();
                int i = 0;
                if (obj2 instanceof byte[]) {
                    String str2 = TYPE_BYTE_ARRAY;
                    byte[] bArr = (byte[]) obj2;
                    int length = bArr.length;
                    while (i < length) {
                        jSONArray2.put(bArr[i]);
                        i++;
                    }
                    Object obj3 = str2;
                    jSONArray = jSONArray2;
                    obj = obj3;
                } else if (obj2 instanceof short[]) {
                    String str3 = TYPE_SHORT_ARRAY;
                    short[] sArr = (short[]) obj2;
                    int length2 = sArr.length;
                    while (i < length2) {
                        jSONArray2.put(sArr[i]);
                        i++;
                    }
                    Object obj4 = str3;
                    jSONArray = jSONArray2;
                    obj = obj4;
                } else if (obj2 instanceof int[]) {
                    String str4 = TYPE_INTEGER_ARRAY;
                    int[] iArr = (int[]) obj2;
                    int length3 = iArr.length;
                    while (i < length3) {
                        jSONArray2.put(iArr[i]);
                        i++;
                    }
                    Object obj5 = str4;
                    jSONArray = jSONArray2;
                    obj = obj5;
                } else if (obj2 instanceof long[]) {
                    String str5 = TYPE_LONG_ARRAY;
                    long[] jArr = (long[]) obj2;
                    int length4 = jArr.length;
                    while (i < length4) {
                        jSONArray2.put(jArr[i]);
                        i++;
                    }
                    Object obj6 = str5;
                    jSONArray = jSONArray2;
                    obj = obj6;
                } else if (obj2 instanceof float[]) {
                    String str6 = TYPE_FLOAT_ARRAY;
                    float[] fArr = (float[]) obj2;
                    int length5 = fArr.length;
                    while (i < length5) {
                        jSONArray2.put((double) fArr[i]);
                        i++;
                    }
                    Object obj7 = str6;
                    jSONArray = jSONArray2;
                    obj = obj7;
                } else if (obj2 instanceof double[]) {
                    String str7 = TYPE_DOUBLE_ARRAY;
                    double[] dArr = (double[]) obj2;
                    int length6 = dArr.length;
                    while (i < length6) {
                        jSONArray2.put(dArr[i]);
                        i++;
                    }
                    Object obj8 = str7;
                    jSONArray = jSONArray2;
                    obj = obj8;
                } else if (obj2 instanceof boolean[]) {
                    String str8 = TYPE_BOOLEAN_ARRAY;
                    boolean[] zArr = (boolean[]) obj2;
                    int length7 = zArr.length;
                    while (i < length7) {
                        jSONArray2.put(zArr[i]);
                        i++;
                    }
                    Object obj9 = str8;
                    jSONArray = jSONArray2;
                    obj = obj9;
                } else if (obj2 instanceof char[]) {
                    String str9 = TYPE_CHAR_ARRAY;
                    char[] cArr = (char[]) obj2;
                    int length8 = cArr.length;
                    while (i < length8) {
                        jSONArray2.put(String.valueOf(cArr[i]));
                        i++;
                    }
                    Object obj10 = str9;
                    jSONArray = jSONArray2;
                    obj = obj10;
                } else if (obj2 instanceof List) {
                    String str10 = TYPE_STRING_LIST;
                    for (Object obj11 : (List) obj2) {
                        if (obj11 == null) {
                            obj11 = JSONObject.NULL;
                        }
                        jSONArray2.put(obj11);
                    }
                    Object obj12 = str10;
                    jSONArray = jSONArray2;
                    obj = obj12;
                } else {
                    obj = null;
                }
            }
            if (obj != null) {
                jSONObject.put(JSON_VALUE_TYPE, obj);
                if (jSONArray != null) {
                    jSONObject.putOpt("value", jSONArray);
                }
                editor.putString(str, jSONObject.toString());
            }
        }
    }

    private void deserializeKey(String str, Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject(this.cache.getString(str, "{}"));
        String string = jSONObject.getString(JSON_VALUE_TYPE);
        if (string.equals(TYPE_BOOLEAN)) {
            bundle.putBoolean(str, jSONObject.getBoolean("value"));
            return;
        }
        int i = 0;
        if (string.equals(TYPE_BOOLEAN_ARRAY)) {
            JSONArray jSONArray = jSONObject.getJSONArray("value");
            boolean[] zArr = new boolean[jSONArray.length()];
            while (i < zArr.length) {
                zArr[i] = jSONArray.getBoolean(i);
                i++;
            }
            bundle.putBooleanArray(str, zArr);
        } else if (string.equals(TYPE_BYTE)) {
            bundle.putByte(str, (byte) jSONObject.getInt("value"));
        } else if (string.equals(TYPE_BYTE_ARRAY)) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("value");
            byte[] bArr = new byte[jSONArray2.length()];
            while (i < bArr.length) {
                bArr[i] = (byte) jSONArray2.getInt(i);
                i++;
            }
            bundle.putByteArray(str, bArr);
        } else if (string.equals(TYPE_SHORT)) {
            bundle.putShort(str, (short) jSONObject.getInt("value"));
        } else if (string.equals(TYPE_SHORT_ARRAY)) {
            JSONArray jSONArray3 = jSONObject.getJSONArray("value");
            short[] sArr = new short[jSONArray3.length()];
            while (i < sArr.length) {
                sArr[i] = (short) jSONArray3.getInt(i);
                i++;
            }
            bundle.putShortArray(str, sArr);
        } else if (string.equals(TYPE_INTEGER)) {
            bundle.putInt(str, jSONObject.getInt("value"));
        } else if (string.equals(TYPE_INTEGER_ARRAY)) {
            JSONArray jSONArray4 = jSONObject.getJSONArray("value");
            int[] iArr = new int[jSONArray4.length()];
            while (i < iArr.length) {
                iArr[i] = jSONArray4.getInt(i);
                i++;
            }
            bundle.putIntArray(str, iArr);
        } else if (string.equals(TYPE_LONG)) {
            bundle.putLong(str, jSONObject.getLong("value"));
        } else if (string.equals(TYPE_LONG_ARRAY)) {
            JSONArray jSONArray5 = jSONObject.getJSONArray("value");
            long[] jArr = new long[jSONArray5.length()];
            while (i < jArr.length) {
                jArr[i] = jSONArray5.getLong(i);
                i++;
            }
            bundle.putLongArray(str, jArr);
        } else if (string.equals(TYPE_FLOAT)) {
            bundle.putFloat(str, (float) jSONObject.getDouble("value"));
        } else if (string.equals(TYPE_FLOAT_ARRAY)) {
            JSONArray jSONArray6 = jSONObject.getJSONArray("value");
            float[] fArr = new float[jSONArray6.length()];
            while (i < fArr.length) {
                fArr[i] = (float) jSONArray6.getDouble(i);
                i++;
            }
            bundle.putFloatArray(str, fArr);
        } else if (string.equals(TYPE_DOUBLE)) {
            bundle.putDouble(str, jSONObject.getDouble("value"));
        } else if (string.equals(TYPE_DOUBLE_ARRAY)) {
            JSONArray jSONArray7 = jSONObject.getJSONArray("value");
            double[] dArr = new double[jSONArray7.length()];
            while (i < dArr.length) {
                dArr[i] = jSONArray7.getDouble(i);
                i++;
            }
            bundle.putDoubleArray(str, dArr);
        } else if (string.equals(TYPE_CHAR)) {
            String string2 = jSONObject.getString("value");
            if (string2 != null && string2.length() == 1) {
                bundle.putChar(str, string2.charAt(0));
            }
        } else if (string.equals(TYPE_CHAR_ARRAY)) {
            JSONArray jSONArray8 = jSONObject.getJSONArray("value");
            char[] cArr = new char[jSONArray8.length()];
            for (int i2 = 0; i2 < cArr.length; i2++) {
                String string3 = jSONArray8.getString(i2);
                if (string3 != null && string3.length() == 1) {
                    cArr[i2] = string3.charAt(0);
                }
            }
            bundle.putCharArray(str, cArr);
        } else if (string.equals(TYPE_STRING)) {
            bundle.putString(str, jSONObject.getString("value"));
        } else if (string.equals(TYPE_STRING_LIST)) {
            JSONArray jSONArray9 = jSONObject.getJSONArray("value");
            int length = jSONArray9.length();
            ArrayList arrayList = new ArrayList(length);
            while (i < length) {
                Object obj = jSONArray9.get(i);
                arrayList.add(i, obj == JSONObject.NULL ? null : (String) obj);
                i++;
            }
            bundle.putStringArrayList(str, arrayList);
        } else if (string.equals(TYPE_ENUM)) {
            try {
                bundle.putSerializable(str, Enum.valueOf(Class.forName(jSONObject.getString(JSON_VALUE_ENUM_TYPE)), jSONObject.getString("value")));
            } catch (ClassNotFoundException | IllegalArgumentException unused) {
            }
        }
    }
}
