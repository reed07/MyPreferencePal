package android.support.v7.preference;

import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v14.preference.SwitchPreference;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class PreferenceInflater {
    private static final HashMap<String, Constructor> CONSTRUCTOR_MAP = new HashMap<>();
    private static final Class<?>[] CONSTRUCTOR_SIGNATURE = {Context.class, AttributeSet.class};
    private static final String EXTRA_TAG_NAME = "extra";
    private static final String INTENT_TAG_NAME = "intent";
    private static final String TAG = "PreferenceInflater";
    private final Object[] mConstructorArgs = new Object[2];
    private final Context mContext;
    private String[] mDefaultPackages;
    private PreferenceManager mPreferenceManager;

    public PreferenceInflater(Context context, PreferenceManager preferenceManager) {
        this.mContext = context;
        init(preferenceManager);
    }

    private void init(PreferenceManager preferenceManager) {
        this.mPreferenceManager = preferenceManager;
        StringBuilder sb = new StringBuilder();
        sb.append(Preference.class.getPackage().getName());
        sb.append(".");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(SwitchPreference.class.getPackage().getName());
        sb2.append(".");
        setDefaultPackages(new String[]{sb.toString(), sb2.toString()});
    }

    public void setDefaultPackages(String[] strArr) {
        this.mDefaultPackages = strArr;
    }

    public String[] getDefaultPackages() {
        return this.mDefaultPackages;
    }

    public Context getContext() {
        return this.mContext;
    }

    public Preference inflate(int i, @Nullable PreferenceGroup preferenceGroup) {
        XmlResourceParser xml = getContext().getResources().getXml(i);
        try {
            return inflate((XmlPullParser) xml, preferenceGroup);
        } finally {
            xml.close();
        }
    }

    public Preference inflate(XmlPullParser xmlPullParser, @Nullable PreferenceGroup preferenceGroup) {
        int next;
        PreferenceGroup onMergeRoots;
        synchronized (this.mConstructorArgs) {
            AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
            this.mConstructorArgs[0] = this.mContext;
            do {
                try {
                    next = xmlPullParser.next();
                    if (next == 2) {
                        break;
                    }
                } catch (InflateException e) {
                    throw e;
                } catch (XmlPullParserException e2) {
                    InflateException inflateException = new InflateException(e2.getMessage());
                    inflateException.initCause(e2);
                    throw inflateException;
                } catch (IOException e3) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(xmlPullParser.getPositionDescription());
                    sb.append(": ");
                    sb.append(e3.getMessage());
                    InflateException inflateException2 = new InflateException(sb.toString());
                    inflateException2.initCause(e3);
                    throw inflateException2;
                }
            } while (next != 1);
            if (next == 2) {
                onMergeRoots = onMergeRoots(preferenceGroup, (PreferenceGroup) createItemFromTag(xmlPullParser.getName(), asAttributeSet));
                rInflate(xmlPullParser, onMergeRoots, asAttributeSet);
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(xmlPullParser.getPositionDescription());
                sb2.append(": No start tag found!");
                throw new InflateException(sb2.toString());
            }
        }
        return onMergeRoots;
    }

    @NonNull
    private PreferenceGroup onMergeRoots(PreferenceGroup preferenceGroup, @NonNull PreferenceGroup preferenceGroup2) {
        if (preferenceGroup != null) {
            return preferenceGroup;
        }
        preferenceGroup2.onAttachedToHierarchy(this.mPreferenceManager);
        return preferenceGroup2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0077, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0078, code lost:
        r1 = new java.lang.StringBuilder();
        r1.append(r11.getPositionDescription());
        r1.append(": Error inflating class ");
        r1.append(r9);
        r0 = new android.view.InflateException(r1.toString());
        r0.initCause(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0098, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0099, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009a, code lost:
        throw r9;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0077 A[ExcHandler: Exception (r10v4 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:2:0x000b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.support.v7.preference.Preference createItem(@android.support.annotation.NonNull java.lang.String r9, @android.support.annotation.Nullable java.lang.String[] r10, android.util.AttributeSet r11) throws java.lang.ClassNotFoundException, android.view.InflateException {
        /*
            r8 = this;
            java.util.HashMap<java.lang.String, java.lang.reflect.Constructor> r0 = CONSTRUCTOR_MAP
            java.lang.Object r0 = r0.get(r9)
            java.lang.reflect.Constructor r0 = (java.lang.reflect.Constructor) r0
            r1 = 1
            if (r0 != 0) goto L_0x006c
            android.content.Context r0 = r8.mContext     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            if (r10 == 0) goto L_0x005a
            int r2 = r10.length     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            if (r2 != 0) goto L_0x0017
            goto L_0x005a
        L_0x0017:
            int r2 = r10.length     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            r3 = 0
            r4 = 0
            r5 = r4
        L_0x001b:
            if (r3 >= r2) goto L_0x0037
            r6 = r10[r3]     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0033, Exception -> 0x0077 }
            r7.<init>()     // Catch:{ ClassNotFoundException -> 0x0033, Exception -> 0x0077 }
            r7.append(r6)     // Catch:{ ClassNotFoundException -> 0x0033, Exception -> 0x0077 }
            r7.append(r9)     // Catch:{ ClassNotFoundException -> 0x0033, Exception -> 0x0077 }
            java.lang.String r6 = r7.toString()     // Catch:{ ClassNotFoundException -> 0x0033, Exception -> 0x0077 }
            java.lang.Class r4 = r0.loadClass(r6)     // Catch:{ ClassNotFoundException -> 0x0033, Exception -> 0x0077 }
            goto L_0x0037
        L_0x0033:
            r5 = move-exception
            int r3 = r3 + 1
            goto L_0x001b
        L_0x0037:
            if (r4 != 0) goto L_0x005e
            if (r5 != 0) goto L_0x0059
            android.view.InflateException r10 = new android.view.InflateException     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            r0.<init>()     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            java.lang.String r1 = r11.getPositionDescription()     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            r0.append(r1)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            java.lang.String r1 = ": Error inflating class "
            r0.append(r1)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            r0.append(r9)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            java.lang.String r0 = r0.toString()     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            r10.<init>(r0)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            throw r10     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
        L_0x0059:
            throw r5     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
        L_0x005a:
            java.lang.Class r4 = r0.loadClass(r9)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
        L_0x005e:
            java.lang.Class<?>[] r10 = CONSTRUCTOR_SIGNATURE     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            java.lang.reflect.Constructor r0 = r4.getConstructor(r10)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            r0.setAccessible(r1)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            java.util.HashMap<java.lang.String, java.lang.reflect.Constructor> r10 = CONSTRUCTOR_MAP     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            r10.put(r9, r0)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
        L_0x006c:
            java.lang.Object[] r10 = r8.mConstructorArgs     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            r10[r1] = r11     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            java.lang.Object r10 = r0.newInstance(r10)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            android.support.v7.preference.Preference r10 = (android.support.v7.preference.Preference) r10     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0077 }
            return r10
        L_0x0077:
            r10 = move-exception
            android.view.InflateException r0 = new android.view.InflateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r11 = r11.getPositionDescription()
            r1.append(r11)
            java.lang.String r11 = ": Error inflating class "
            r1.append(r11)
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            r0.<init>(r9)
            r0.initCause(r10)
            throw r0
        L_0x0099:
            r9 = move-exception
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.preference.PreferenceInflater.createItem(java.lang.String, java.lang.String[], android.util.AttributeSet):android.support.v7.preference.Preference");
    }

    /* access modifiers changed from: protected */
    public Preference onCreateItem(String str, AttributeSet attributeSet) throws ClassNotFoundException {
        return createItem(str, this.mDefaultPackages, attributeSet);
    }

    private Preference createItemFromTag(String str, AttributeSet attributeSet) {
        try {
            if (-1 == str.indexOf(46)) {
                return onCreateItem(str, attributeSet);
            }
            return createItem(str, null, attributeSet);
        } catch (InflateException e) {
            throw e;
        } catch (ClassNotFoundException e2) {
            StringBuilder sb = new StringBuilder();
            sb.append(attributeSet.getPositionDescription());
            sb.append(": Error inflating class (not found)");
            sb.append(str);
            InflateException inflateException = new InflateException(sb.toString());
            inflateException.initCause(e2);
            throw inflateException;
        } catch (Exception e3) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(attributeSet.getPositionDescription());
            sb2.append(": Error inflating class ");
            sb2.append(str);
            InflateException inflateException2 = new InflateException(sb2.toString());
            inflateException2.initCause(e3);
            throw inflateException2;
        }
    }

    private void rInflate(XmlPullParser xmlPullParser, Preference preference, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                return;
            }
            if (next == 2) {
                String name = xmlPullParser.getName();
                if ("intent".equals(name)) {
                    try {
                        preference.setIntent(Intent.parseIntent(getContext().getResources(), xmlPullParser, attributeSet));
                    } catch (IOException e) {
                        XmlPullParserException xmlPullParserException = new XmlPullParserException("Error parsing preference");
                        xmlPullParserException.initCause(e);
                        throw xmlPullParserException;
                    }
                } else if (EXTRA_TAG_NAME.equals(name)) {
                    getContext().getResources().parseBundleExtra(EXTRA_TAG_NAME, attributeSet, preference.getExtras());
                    try {
                        skipCurrentTag(xmlPullParser);
                    } catch (IOException e2) {
                        XmlPullParserException xmlPullParserException2 = new XmlPullParserException("Error parsing preference");
                        xmlPullParserException2.initCause(e2);
                        throw xmlPullParserException2;
                    }
                } else {
                    Preference createItemFromTag = createItemFromTag(name, attributeSet);
                    ((PreferenceGroup) preference).addItemFromInflater(createItemFromTag);
                    rInflate(xmlPullParser, createItemFromTag, attributeSet);
                }
            }
        }
    }

    private static void skipCurrentTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlPullParser.getDepth() <= depth) {
                return;
            }
        }
    }
}
