package com.shinobicontrols.charts;

import android.graphics.Typeface;
import android.widget.TextView;

abstract class e {
    static e a = new e() {
        /* access modifiers changed from: 0000 */
        public void a(Annotation annotation) {
            AnnotationStyle style = annotation.getStyle();
            if (style != null) {
                TextView textView = (TextView) annotation.getView();
                textView.setBackgroundColor(((Integer) style.d.a).intValue());
                textView.setTextColor(((Integer) style.a.a).intValue());
                textView.setTextSize(((Float) style.b.a).floatValue());
                textView.setTypeface((Typeface) style.c.a);
            }
        }
    };
    static e b = new e() {
        /* access modifiers changed from: 0000 */
        public void a(Annotation annotation) {
            AnnotationStyle style = annotation.getStyle();
            if (style != null) {
                annotation.getView().setBackgroundColor(((Integer) style.d.a).intValue());
            }
        }
    };
    static e c = new e() {
        /* access modifiers changed from: 0000 */
        public void a(Annotation annotation) {
        }
    };

    /* access modifiers changed from: 0000 */
    public abstract void a(Annotation annotation);

    e() {
    }
}
