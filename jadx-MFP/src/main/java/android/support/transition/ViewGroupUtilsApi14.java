package android.support.transition;

import android.animation.LayoutTransition;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ViewGroupUtilsApi14 {
    private static Method sCancelMethod;
    private static boolean sCancelMethodFetched;
    private static LayoutTransition sEmptyLayoutTransition;
    private static Field sLayoutSuppressedField;
    private static boolean sLayoutSuppressedFieldFetched;

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void suppressLayout(@android.support.annotation.NonNull android.view.ViewGroup r5, boolean r6) {
        /*
            android.animation.LayoutTransition r0 = sEmptyLayoutTransition
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 != 0) goto L_0x002a
            android.support.transition.ViewGroupUtilsApi14$1 r0 = new android.support.transition.ViewGroupUtilsApi14$1
            r0.<init>()
            sEmptyLayoutTransition = r0
            android.animation.LayoutTransition r0 = sEmptyLayoutTransition
            r4 = 2
            r0.setAnimator(r4, r3)
            android.animation.LayoutTransition r0 = sEmptyLayoutTransition
            r0.setAnimator(r2, r3)
            android.animation.LayoutTransition r0 = sEmptyLayoutTransition
            r0.setAnimator(r1, r3)
            android.animation.LayoutTransition r0 = sEmptyLayoutTransition
            r4 = 3
            r0.setAnimator(r4, r3)
            android.animation.LayoutTransition r0 = sEmptyLayoutTransition
            r4 = 4
            r0.setAnimator(r4, r3)
        L_0x002a:
            if (r6 == 0) goto L_0x004a
            android.animation.LayoutTransition r6 = r5.getLayoutTransition()
            if (r6 == 0) goto L_0x0044
            boolean r0 = r6.isRunning()
            if (r0 == 0) goto L_0x003b
            cancelLayoutTransition(r6)
        L_0x003b:
            android.animation.LayoutTransition r0 = sEmptyLayoutTransition
            if (r6 == r0) goto L_0x0044
            int r0 = android.support.transition.R.id.transition_layout_save
            r5.setTag(r0, r6)
        L_0x0044:
            android.animation.LayoutTransition r6 = sEmptyLayoutTransition
            r5.setLayoutTransition(r6)
            goto L_0x009c
        L_0x004a:
            r5.setLayoutTransition(r3)
            boolean r6 = sLayoutSuppressedFieldFetched
            if (r6 != 0) goto L_0x006a
            java.lang.Class<android.view.ViewGroup> r6 = android.view.ViewGroup.class
            java.lang.String r0 = "mLayoutSuppressed"
            java.lang.reflect.Field r6 = r6.getDeclaredField(r0)     // Catch:{ NoSuchFieldException -> 0x0061 }
            sLayoutSuppressedField = r6     // Catch:{ NoSuchFieldException -> 0x0061 }
            java.lang.reflect.Field r6 = sLayoutSuppressedField     // Catch:{ NoSuchFieldException -> 0x0061 }
            r6.setAccessible(r1)     // Catch:{ NoSuchFieldException -> 0x0061 }
            goto L_0x0068
        L_0x0061:
            java.lang.String r6 = "ViewGroupUtilsApi14"
            java.lang.String r0 = "Failed to access mLayoutSuppressed field by reflection"
            android.util.Log.i(r6, r0)
        L_0x0068:
            sLayoutSuppressedFieldFetched = r1
        L_0x006a:
            java.lang.reflect.Field r6 = sLayoutSuppressedField
            if (r6 == 0) goto L_0x0085
            boolean r6 = r6.getBoolean(r5)     // Catch:{ IllegalAccessException -> 0x007e }
            if (r6 == 0) goto L_0x007c
            java.lang.reflect.Field r0 = sLayoutSuppressedField     // Catch:{ IllegalAccessException -> 0x007a }
            r0.setBoolean(r5, r2)     // Catch:{ IllegalAccessException -> 0x007a }
            goto L_0x007c
        L_0x007a:
            r2 = r6
            goto L_0x007e
        L_0x007c:
            r2 = r6
            goto L_0x0085
        L_0x007e:
            java.lang.String r6 = "ViewGroupUtilsApi14"
            java.lang.String r0 = "Failed to get mLayoutSuppressed field by reflection"
            android.util.Log.i(r6, r0)
        L_0x0085:
            if (r2 == 0) goto L_0x008a
            r5.requestLayout()
        L_0x008a:
            int r6 = android.support.transition.R.id.transition_layout_save
            java.lang.Object r6 = r5.getTag(r6)
            android.animation.LayoutTransition r6 = (android.animation.LayoutTransition) r6
            if (r6 == 0) goto L_0x009c
            int r0 = android.support.transition.R.id.transition_layout_save
            r5.setTag(r0, r3)
            r5.setLayoutTransition(r6)
        L_0x009c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.ViewGroupUtilsApi14.suppressLayout(android.view.ViewGroup, boolean):void");
    }

    private static void cancelLayoutTransition(LayoutTransition layoutTransition) {
        if (!sCancelMethodFetched) {
            try {
                sCancelMethod = LayoutTransition.class.getDeclaredMethod("cancel", new Class[0]);
                sCancelMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
                Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
            }
            sCancelMethodFetched = true;
        }
        Method method = sCancelMethod;
        if (method != null) {
            try {
                method.invoke(layoutTransition, new Object[0]);
            } catch (IllegalAccessException unused2) {
                Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
            } catch (InvocationTargetException unused3) {
                Log.i("ViewGroupUtilsApi14", "Failed to invoke cancel method by reflection");
            }
        }
    }

    private ViewGroupUtilsApi14() {
    }
}
