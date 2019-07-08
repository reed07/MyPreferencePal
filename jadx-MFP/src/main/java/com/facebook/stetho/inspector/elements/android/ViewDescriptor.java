package com.facebook.stetho.inspector.elements.android;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewDebug.FlagToString;
import android.view.ViewDebug.IntToString;
import com.facebook.stetho.common.ExceptionUtil;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.ReflectionUtil;
import com.facebook.stetho.common.StringUtil;
import com.facebook.stetho.common.android.ResourcesUtil;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.AttributeAccumulator;
import com.facebook.stetho.inspector.elements.ComputedStyleAccumulator;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import com.facebook.stetho.inspector.elements.StyleRuleNameAccumulator;
import com.facebook.stetho.inspector.helper.IntegerFormatter;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.constants.Constants.Ads.Keywords;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

final class ViewDescriptor extends AbstractChainedDescriptor<View> implements HighlightableDescriptor<View> {
    private static final String ACCESSIBILITY_STYLE_RULE_NAME = "Accessibility Properties";
    private static final String ID_NAME = "id";
    private static final String NONE_MAPPING = "<no mapping>";
    private static final String NONE_VALUE = "(none)";
    private static final String VIEW_STYLE_RULE_NAME = "<this_view>";
    private static final boolean sHasSupportNodeInfo = (ReflectionUtil.tryGetClassForName("android.support.v4.view.accessibility.AccessibilityNodeInfoCompat") != null);
    private final MethodInvoker mMethodInvoker;
    @GuardedBy("this")
    @Nullable
    private volatile List<ViewCSSProperty> mViewProperties;
    @Nullable
    private Pattern mWordBoundaryPattern;

    private final class FieldBackedCSSProperty extends ViewCSSProperty {
        private final Field mField;

        public FieldBackedCSSProperty(Field field, String str, ExportedProperty exportedProperty) {
            super(str, exportedProperty);
            this.mField = field;
            this.mField.setAccessible(true);
        }

        public Object getValue(View view) throws InvocationTargetException, IllegalAccessException {
            return this.mField.get(view);
        }
    }

    private final class MethodBackedCSSProperty extends ViewCSSProperty {
        private final Method mMethod;

        public MethodBackedCSSProperty(Method method, String str, ExportedProperty exportedProperty) {
            super(str, exportedProperty);
            this.mMethod = method;
            this.mMethod.setAccessible(true);
        }

        public Object getValue(View view) throws InvocationTargetException, IllegalAccessException {
            return this.mMethod.invoke(view, new Object[0]);
        }
    }

    private abstract class ViewCSSProperty {
        private final ExportedProperty mAnnotation;
        private final String mCSSName;

        public abstract Object getValue(View view) throws InvocationTargetException, IllegalAccessException;

        public ViewCSSProperty(String str, ExportedProperty exportedProperty) {
            this.mCSSName = str;
            this.mAnnotation = exportedProperty;
        }

        public final String getCSSName() {
            return this.mCSSName;
        }

        @Nullable
        public final ExportedProperty getAnnotation() {
            return this.mAnnotation;
        }
    }

    @Nullable
    public View getViewAndBoundsForHighlighting(View view, Rect rect) {
        return view;
    }

    private Pattern getWordBoundaryPattern() {
        if (this.mWordBoundaryPattern == null) {
            this.mWordBoundaryPattern = Pattern.compile("(?<=\\p{Lower})(?=\\p{Upper})");
        }
        return this.mWordBoundaryPattern;
    }

    private List<ViewCSSProperty> getViewProperties() {
        Method[] declaredMethods;
        Field[] declaredFields;
        if (this.mViewProperties == null) {
            synchronized (this) {
                if (this.mViewProperties == null) {
                    ArrayList arrayList = new ArrayList();
                    for (Method method : View.class.getDeclaredMethods()) {
                        ExportedProperty exportedProperty = (ExportedProperty) method.getAnnotation(ExportedProperty.class);
                        if (exportedProperty != null) {
                            arrayList.add(new MethodBackedCSSProperty(method, convertViewPropertyNameToCSSName(method.getName()), exportedProperty));
                        }
                    }
                    for (Field field : View.class.getDeclaredFields()) {
                        ExportedProperty exportedProperty2 = (ExportedProperty) field.getAnnotation(ExportedProperty.class);
                        if (exportedProperty2 != null) {
                            arrayList.add(new FieldBackedCSSProperty(field, convertViewPropertyNameToCSSName(field.getName()), exportedProperty2));
                        }
                    }
                    Collections.sort(arrayList, new Comparator<ViewCSSProperty>() {
                        public int compare(ViewCSSProperty viewCSSProperty, ViewCSSProperty viewCSSProperty2) {
                            return viewCSSProperty.getCSSName().compareTo(viewCSSProperty2.getCSSName());
                        }
                    });
                    this.mViewProperties = Collections.unmodifiableList(arrayList);
                }
            }
        }
        return this.mViewProperties;
    }

    public ViewDescriptor() {
        this(new MethodInvoker());
    }

    public ViewDescriptor(MethodInvoker methodInvoker) {
        this.mMethodInvoker = methodInvoker;
    }

    /* access modifiers changed from: protected */
    public String onGetNodeName(View view) {
        String name = view.getClass().getName();
        return StringUtil.removePrefix(name, "android.view.", StringUtil.removePrefix(name, "android.widget."));
    }

    /* access modifiers changed from: protected */
    public void onGetAttributes(View view, AttributeAccumulator attributeAccumulator) {
        String idAttribute = getIdAttribute(view);
        if (idAttribute != null) {
            attributeAccumulator.store("id", idAttribute);
        }
    }

    /* access modifiers changed from: protected */
    public void onSetAttributesAsText(View view, String str) {
        for (Entry entry : parseSetAttributesAsTextArg(str).entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append("set");
            sb.append(capitalize((String) entry.getKey()));
            this.mMethodInvoker.invoke(view, sb.toString(), (String) entry.getValue());
        }
    }

    @Nullable
    private static String getIdAttribute(View view) {
        int id = view.getId();
        if (id == -1) {
            return null;
        }
        return ResourcesUtil.getIdStringQuietly(view, view.getResources(), id);
    }

    @Nullable
    public Object getElementToHighlightAtPosition(View view, int i, int i2, Rect rect) {
        rect.set(0, 0, view.getWidth(), view.getHeight());
        return view;
    }

    /* access modifiers changed from: protected */
    public void onGetStyleRuleNames(View view, StyleRuleNameAccumulator styleRuleNameAccumulator) {
        styleRuleNameAccumulator.store(VIEW_STYLE_RULE_NAME, false);
        if (sHasSupportNodeInfo) {
            styleRuleNameAccumulator.store(ACCESSIBILITY_STYLE_RULE_NAME, false);
        }
    }

    /* access modifiers changed from: protected */
    public void onGetStyles(View view, String str, StyleAccumulator styleAccumulator) {
        if (VIEW_STYLE_RULE_NAME.equals(str)) {
            List viewProperties = getViewProperties();
            int size = viewProperties.size();
            for (int i = 0; i < size; i++) {
                ViewCSSProperty viewCSSProperty = (ViewCSSProperty) viewProperties.get(i);
                try {
                    getStyleFromValue(view, viewCSSProperty.getCSSName(), viewCSSProperty.getValue(view), viewCSSProperty.getAnnotation(), styleAccumulator);
                } catch (Exception e) {
                    if ((e instanceof IllegalAccessException) || (e instanceof InvocationTargetException)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("failed to get style property ");
                        sb.append(viewCSSProperty.getCSSName());
                        sb.append(" of element= ");
                        sb.append(view.toString());
                        LogUtil.e((Throwable) e, sb.toString());
                    } else {
                        throw ExceptionUtil.propagate(e);
                    }
                }
            }
        } else if (ACCESSIBILITY_STYLE_RULE_NAME.equals(str) && sHasSupportNodeInfo) {
            boolean ignored = AccessibilityNodeInfoWrapper.getIgnored(view);
            getStyleFromValue(view, "ignored", Boolean.valueOf(ignored), null, styleAccumulator);
            if (ignored) {
                getStyleFromValue(view, "ignored-reasons", AccessibilityNodeInfoWrapper.getIgnoredReasons(view), null, styleAccumulator);
            }
            View view2 = view;
            getStyleFromValue(view2, "focusable", Boolean.valueOf(!ignored), null, styleAccumulator);
            if (!ignored) {
                View view3 = view;
                StyleAccumulator styleAccumulator2 = styleAccumulator;
                getStyleFromValue(view3, "focusable-reasons", AccessibilityNodeInfoWrapper.getFocusableReasons(view), null, styleAccumulator2);
                View view4 = view;
                getStyleFromValue(view4, "focused", Boolean.valueOf(AccessibilityNodeInfoWrapper.getIsAccessibilityFocused(view)), null, styleAccumulator);
                getStyleFromValue(view3, "description", AccessibilityNodeInfoWrapper.getDescription(view), null, styleAccumulator2);
                getStyleFromValue(view4, "actions", AccessibilityNodeInfoWrapper.getActions(view), null, styleAccumulator);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onGetComputedStyles(View view, ComputedStyleAccumulator computedStyleAccumulator) {
        computedStyleAccumulator.store(TtmlNode.LEFT, Integer.toString(view.getLeft()));
        computedStyleAccumulator.store(Keywords.POSITION_TOP, Integer.toString(view.getTop()));
        computedStyleAccumulator.store(TtmlNode.RIGHT, Integer.toString(view.getRight()));
        computedStyleAccumulator.store("bottom", Integer.toString(view.getBottom()));
    }

    private static boolean canIntBeMappedToString(@Nullable ExportedProperty exportedProperty) {
        return (exportedProperty == null || exportedProperty.mapping() == null || exportedProperty.mapping().length <= 0) ? false : true;
    }

    private static String mapIntToStringUsingAnnotation(int i, @Nullable ExportedProperty exportedProperty) {
        IntToString[] mapping;
        if (canIntBeMappedToString(exportedProperty)) {
            for (IntToString intToString : exportedProperty.mapping()) {
                if (intToString.from() == i) {
                    return intToString.to();
                }
            }
            return NONE_MAPPING;
        }
        throw new IllegalStateException("Cannot map using this annotation");
    }

    private static boolean canFlagsBeMappedToString(@Nullable ExportedProperty exportedProperty) {
        return (exportedProperty == null || exportedProperty.flagMapping() == null || exportedProperty.flagMapping().length <= 0) ? false : true;
    }

    private static String mapFlagsToStringUsingAnnotation(int i, @Nullable ExportedProperty exportedProperty) {
        FlagToString[] flagMapping;
        if (canFlagsBeMappedToString(exportedProperty)) {
            StringBuilder sb = null;
            boolean z = false;
            for (FlagToString flagToString : exportedProperty.flagMapping()) {
                if (flagToString.outputIf() == ((flagToString.mask() & i) == flagToString.equals())) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    if (z) {
                        sb.append(" | ");
                    }
                    sb.append(flagToString.name());
                    z = true;
                }
            }
            if (z) {
                return sb.toString();
            }
            return NONE_MAPPING;
        }
        throw new IllegalStateException("Cannot map using this annotation");
    }

    private String convertViewPropertyNameToCSSName(String str) {
        String[] split = getWordBoundaryPattern().split(str);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (!split[i].equals("get") && !split[i].equals("m")) {
                sb.append(split[i].toLowerCase());
                if (i < split.length - 1) {
                    sb.append('-');
                }
            }
        }
        return sb.toString();
    }

    private void getStyleFromValue(View view, String str, Object obj, @Nullable ExportedProperty exportedProperty, StyleAccumulator styleAccumulator) {
        if (str.equals("id")) {
            getIdStyle(view, styleAccumulator);
        } else if (obj instanceof Integer) {
            getStyleFromInteger(str, (Integer) obj, exportedProperty, styleAccumulator);
        } else {
            boolean z = true;
            if (obj instanceof Float) {
                String valueOf = String.valueOf(obj);
                if (((Float) obj).floatValue() != BitmapDescriptorFactory.HUE_RED) {
                    z = false;
                }
                styleAccumulator.store(str, valueOf, z);
            } else if (obj instanceof Boolean) {
                styleAccumulator.store(str, String.valueOf(obj), false);
            } else if (obj instanceof Short) {
                String valueOf2 = String.valueOf(obj);
                if (((Short) obj).shortValue() != 0) {
                    z = false;
                }
                styleAccumulator.store(str, valueOf2, z);
            } else if (obj instanceof Long) {
                String valueOf3 = String.valueOf(obj);
                if (((Long) obj).longValue() != 0) {
                    z = false;
                }
                styleAccumulator.store(str, valueOf3, z);
            } else if (obj instanceof Double) {
                String valueOf4 = String.valueOf(obj);
                if (((Double) obj).doubleValue() != 0.0d) {
                    z = false;
                }
                styleAccumulator.store(str, valueOf4, z);
            } else if (obj instanceof Byte) {
                String valueOf5 = String.valueOf(obj);
                if (((Byte) obj).byteValue() != 0) {
                    z = false;
                }
                styleAccumulator.store(str, valueOf5, z);
            } else if (obj instanceof Character) {
                String valueOf6 = String.valueOf(obj);
                if (((Character) obj).charValue() != 0) {
                    z = false;
                }
                styleAccumulator.store(str, valueOf6, z);
            } else if (obj instanceof CharSequence) {
                String valueOf7 = String.valueOf(obj);
                if (((CharSequence) obj).length() != 0) {
                    z = false;
                }
                styleAccumulator.store(str, valueOf7, z);
            } else {
                getStylesFromObject(view, str, obj, exportedProperty, styleAccumulator);
            }
        }
    }

    private void getIdStyle(View view, StyleAccumulator styleAccumulator) {
        String idAttribute = getIdAttribute(view);
        if (idAttribute == null) {
            styleAccumulator.store("id", NONE_VALUE, false);
        } else {
            styleAccumulator.store("id", idAttribute, false);
        }
    }

    private void getStyleFromInteger(String str, Integer num, @Nullable ExportedProperty exportedProperty, StyleAccumulator styleAccumulator) {
        String format = IntegerFormatter.getInstance().format(num, exportedProperty);
        if (canIntBeMappedToString(exportedProperty)) {
            StringBuilder sb = new StringBuilder();
            sb.append(format);
            sb.append(" (");
            sb.append(mapIntToStringUsingAnnotation(num.intValue(), exportedProperty));
            sb.append(")");
            styleAccumulator.store(str, sb.toString(), false);
        } else if (canFlagsBeMappedToString(exportedProperty)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(format);
            sb2.append(" (");
            sb2.append(mapFlagsToStringUsingAnnotation(num.intValue(), exportedProperty));
            sb2.append(")");
            styleAccumulator.store(str, sb2.toString(), false);
        } else {
            Boolean valueOf = Boolean.valueOf(true);
            if (num.intValue() != 0 || canFlagsBeMappedToString(exportedProperty) || canIntBeMappedToString(exportedProperty)) {
                valueOf = Boolean.valueOf(false);
            }
            styleAccumulator.store(str, format, valueOf.booleanValue());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006a, code lost:
        if (r7.equals("topMargin") != false) goto L_0x0078;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void getStylesFromObject(android.view.View r14, java.lang.String r15, java.lang.Object r16, @javax.annotation.Nullable android.view.ViewDebug.ExportedProperty r17, com.facebook.stetho.inspector.elements.StyleAccumulator r18) {
        /*
            r13 = this;
            r1 = r16
            if (r17 == 0) goto L_0x00e5
            boolean r0 = r17.deepExport()
            if (r0 == 0) goto L_0x00e5
            if (r1 != 0) goto L_0x000e
            goto L_0x00e5
        L_0x000e:
            java.lang.Class r0 = r16.getClass()
            java.lang.reflect.Field[] r0 = r0.getFields()
            int r2 = r0.length
            r3 = 0
            r4 = 0
        L_0x0019:
            if (r4 >= r2) goto L_0x00e3
            r5 = r0[r4]
            int r6 = r5.getModifiers()
            boolean r6 = java.lang.reflect.Modifier.isStatic(r6)
            if (r6 == 0) goto L_0x002a
            r6 = r13
            goto L_0x00bb
        L_0x002a:
            r6 = 1
            r5.setAccessible(r6)     // Catch:{ IllegalAccessException -> 0x00bf }
            java.lang.Object r10 = r5.get(r1)     // Catch:{ IllegalAccessException -> 0x00bf }
            java.lang.String r7 = r5.getName()
            r8 = -1
            int r9 = r7.hashCode()
            r11 = -599904534(0xffffffffdc3e2eea, float:-2.14127313E17)
            if (r9 == r11) goto L_0x006d
            r11 = -414179485(0xffffffffe7501f63, float:-9.828312E23)
            if (r9 == r11) goto L_0x0064
            r6 = 1928835221(0x72f7b095, float:9.812003E30)
            if (r9 == r6) goto L_0x005a
            r6 = 2064613305(0x7b0f7fb9, float:7.45089E35)
            if (r9 == r6) goto L_0x0050
            goto L_0x0077
        L_0x0050:
            java.lang.String r6 = "bottomMargin"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x0077
            r6 = 0
            goto L_0x0078
        L_0x005a:
            java.lang.String r6 = "leftMargin"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x0077
            r6 = 2
            goto L_0x0078
        L_0x0064:
            java.lang.String r9 = "topMargin"
            boolean r9 = r7.equals(r9)
            if (r9 == 0) goto L_0x0077
            goto L_0x0078
        L_0x006d:
            java.lang.String r6 = "rightMargin"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x0077
            r6 = 3
            goto L_0x0078
        L_0x0077:
            r6 = -1
        L_0x0078:
            switch(r6) {
                case 0: goto L_0x0091;
                case 1: goto L_0x008c;
                case 2: goto L_0x0087;
                case 3: goto L_0x0082;
                default: goto L_0x007b;
            }
        L_0x007b:
            java.lang.String r6 = r17.prefix()
            if (r6 != 0) goto L_0x0096
            goto L_0x00a5
        L_0x0082:
            java.lang.String r6 = "margin-right"
            r9 = r6
            r6 = r13
            goto L_0x00ab
        L_0x0087:
            java.lang.String r6 = "margin-left"
            r9 = r6
            r6 = r13
            goto L_0x00ab
        L_0x008c:
            java.lang.String r6 = "margin-top"
            r9 = r6
            r6 = r13
            goto L_0x00ab
        L_0x0091:
            java.lang.String r6 = "margin-bottom"
            r9 = r6
            r6 = r13
            goto L_0x00ab
        L_0x0096:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r6)
            r8.append(r7)
            java.lang.String r7 = r8.toString()
        L_0x00a5:
            r6 = r13
            java.lang.String r7 = r13.convertViewPropertyNameToCSSName(r7)
            r9 = r7
        L_0x00ab:
            java.lang.Class<android.view.ViewDebug$ExportedProperty> r7 = android.view.ViewDebug.ExportedProperty.class
            java.lang.annotation.Annotation r5 = r5.getAnnotation(r7)
            r11 = r5
            android.view.ViewDebug$ExportedProperty r11 = (android.view.ViewDebug.ExportedProperty) r11
            r7 = r13
            r8 = r14
            r12 = r18
            r7.getStyleFromValue(r8, r9, r10, r11, r12)
        L_0x00bb:
            int r4 = r4 + 1
            goto L_0x0019
        L_0x00bf:
            r0 = move-exception
            r6 = r13
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "failed to get property of name: \""
            r2.append(r3)
            r3 = r15
            r2.append(r15)
            java.lang.String r3 = "\" of object: "
            r2.append(r3)
            java.lang.String r1 = java.lang.String.valueOf(r16)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            com.facebook.stetho.common.LogUtil.e(r0, r1)
            return
        L_0x00e3:
            r6 = r13
            return
        L_0x00e5:
            r6 = r13
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.inspector.elements.android.ViewDescriptor.getStylesFromObject(android.view.View, java.lang.String, java.lang.Object, android.view.ViewDebug$ExportedProperty, com.facebook.stetho.inspector.elements.StyleAccumulator):void");
    }

    private static String capitalize(String str) {
        if (str == null || str.length() == 0 || Character.isTitleCase(str.charAt(0))) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(0, Character.toTitleCase(sb.charAt(0)));
        return sb.toString();
    }
}
