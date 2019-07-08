package com.shinobicontrols.charts;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.shinobicontrols.charts.Annotation.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotationsManager {
    private final v a;
    private final List<Annotation> b;
    private final Map<Annotation, am> c = new HashMap();
    private final a d = new a();

    private class a implements a {
        private a() {
        }

        public void a(Annotation annotation) {
            AnnotationsManager.this.h(annotation);
        }
    }

    AnnotationsManager(v vVar) {
        this.a = vVar;
        this.b = new ArrayList();
    }

    public List<Annotation> getAnnotations() {
        return Collections.unmodifiableList(this.b);
    }

    public void removeAnnotation(Annotation annotation) {
        a(annotation);
    }

    public void removeAllAnnotations() {
        a((List<Annotation>) new ArrayList<Annotation>(this.b));
    }

    public void removeAllAnnotations(Axis<?, ?> axis) {
        ArrayList arrayList = new ArrayList();
        for (Annotation annotation : this.b) {
            if (annotation.getXAxis() == axis || annotation.getYAxis() == axis) {
                arrayList.add(annotation);
            }
        }
        a((List<Annotation>) arrayList);
    }

    private void a(Annotation annotation) {
        this.a.b.b(annotation.getView(), annotation.getPosition());
        this.b.remove(annotation);
        b(annotation);
    }

    private void a(List<Annotation> list) {
        for (Annotation a2 : list) {
            a(a2);
        }
    }

    private void b(Annotation annotation) {
        am amVar = (am) this.c.get(annotation);
        if (amVar != null) {
            amVar.a();
            this.c.remove(annotation);
        }
    }

    public Annotation addTextAnnotation(String str, Object obj, Object obj2, Axis<?, ?> axis, Axis<?, ?> axis2) {
        this.a.o();
        TextView textView = new TextView(this.a.getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        int a2 = at.a(this.a.getResources().getDisplayMetrics().density, 6.0f);
        textView.setPadding(a2, a2, a2, a2);
        textView.setText(str);
        Annotation annotation = new Annotation(textView, axis, axis2, e.a);
        annotation.setXValue(obj);
        annotation.setYValue(obj2);
        c(annotation);
        return annotation;
    }

    public Annotation addViewAnnotation(View view, Object obj, Object obj2, Axis<?, ?> axis, Axis<?, ?> axis2) {
        this.a.o();
        if (view != null) {
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new LayoutParams(-2, -2));
            }
            Annotation annotation = new Annotation(view, axis, axis2, e.c);
            annotation.setXValue(obj);
            annotation.setYValue(obj2);
            c(annotation);
            return annotation;
        }
        throw new IllegalArgumentException("Annotation cannot have a null View.");
    }

    public Annotation addHorizontalLineAnnotation(Object obj, float f, Axis<?, ?> axis, Axis<?, ?> axis2) {
        this.a.o();
        int a2 = at.a(this.a.getResources().getDisplayMetrics().density, f);
        View view = new View(this.a.getContext());
        view.setLayoutParams(new LayoutParams(-1, a2));
        Annotation annotation = new Annotation(view, axis, axis2, e.b);
        annotation.setYValue(obj);
        c(annotation);
        return annotation;
    }

    public Annotation addVerticalLineAnnotation(Object obj, float f, Axis<?, ?> axis, Axis<?, ?> axis2) {
        this.a.o();
        int a2 = at.a(this.a.getResources().getDisplayMetrics().density, f);
        View view = new View(this.a.getContext());
        view.setLayoutParams(new LayoutParams(a2, -1));
        Annotation annotation = new Annotation(view, axis, axis2, e.b);
        annotation.setXValue(obj);
        c(annotation);
        return annotation;
    }

    public Annotation addHorizontalBandAnnotation(Range<?> range, Axis<?, ?> axis, Axis<?, ?> axis2) {
        this.a.o();
        View view = new View(this.a.getContext());
        view.setLayoutParams(new LayoutParams(-1, 0));
        Annotation annotation = new Annotation(view, axis, axis2, e.b);
        annotation.setYRange(range);
        c(annotation);
        return annotation;
    }

    public Annotation addVerticalBandAnnotation(Range<?> range, Axis<?, ?> axis, Axis<?, ?> axis2) {
        this.a.o();
        View view = new View(this.a.getContext());
        view.setLayoutParams(new LayoutParams(0, -1));
        Annotation annotation = new Annotation(view, axis, axis2, e.b);
        annotation.setXRange(range);
        c(annotation);
        return annotation;
    }

    public Annotation addBoxAnnotation(Range<?> range, Range<?> range2, Axis<?, ?> axis, Axis<?, ?> axis2) {
        this.a.o();
        View view = new View(this.a.getContext());
        view.setLayoutParams(new LayoutParams(0, 0));
        Annotation annotation = new Annotation(view, axis, axis2, e.b);
        annotation.setXRange(range);
        annotation.setYRange(range2);
        c(annotation);
        return annotation;
    }

    private AnnotationStyle c() {
        AnnotationStyle annotationStyle = new AnnotationStyle();
        annotationStyle.a(this.a.p().g());
        return annotationStyle;
    }

    private void c(Annotation annotation) {
        annotation.setStyle(c());
        annotation.b();
        d(annotation);
        this.b.add(annotation);
        this.a.b.a(annotation.getView(), annotation.getPosition());
    }

    private void d(Annotation annotation) {
        this.c.put(annotation, annotation.a(this.d));
    }

    /* access modifiers changed from: 0000 */
    public void a(int i, int i2, Position position) {
        int size = this.b.size();
        for (int i3 = 0; i3 < size; i3++) {
            Annotation annotation = (Annotation) this.b.get(i3);
            if (annotation.getPosition() == position) {
                LayoutParams layoutParams = annotation.getView().getLayoutParams();
                annotation.a(ViewGroup.getChildMeasureSpec(i, 0, a(layoutParams, annotation)), ViewGroup.getChildMeasureSpec(i2, 0, b(layoutParams, annotation)));
            }
        }
    }

    private int a(LayoutParams layoutParams, Annotation annotation) {
        return layoutParams.width == 0 ? e(annotation) : layoutParams.width;
    }

    private int b(LayoutParams layoutParams, Annotation annotation) {
        return layoutParams.height == 0 ? f(annotation) : layoutParams.height;
    }

    private int e(Annotation annotation) {
        if (annotation.getXRange() == null) {
            return 0;
        }
        double translatePoint = annotation.getXAxis().translatePoint(annotation.getXRange().getMinimum());
        double translatePoint2 = annotation.getXAxis().translatePoint(annotation.getXRange().getMaximum());
        return (int) Math.round(annotation.getXAxis().n.a(translatePoint2, this.a.b.g, this.a.b.h) - annotation.getXAxis().n.a(translatePoint, this.a.b.g, this.a.b.h));
    }

    private int f(Annotation annotation) {
        if (annotation.getYRange() == null) {
            return 0;
        }
        return (int) Math.round(annotation.getYAxis().n.a(annotation.getYAxis().translatePoint(annotation.getYRange().getMinimum()), this.a.b.g, this.a.b.h) - annotation.getYAxis().n.a(annotation.getYAxis().translatePoint(annotation.getYRange().getMaximum()), this.a.b.g, this.a.b.h));
    }

    /* access modifiers changed from: 0000 */
    public void a(int i, int i2, int i3, int i4, Position position) {
        int size = this.b.size();
        for (int i5 = 0; i5 < size; i5++) {
            Annotation annotation = (Annotation) this.b.get(i5);
            if (annotation.getPosition() == position) {
                g(annotation);
                annotation.a(i, i2, i3, i4);
            }
        }
    }

    private void g(Annotation annotation) {
        if (annotation.getXAxis().b != this.a || annotation.getYAxis().b != this.a) {
            throw new IllegalStateException(annotation.getView().getContext().getString(R.string.AnnotationMustBeOnSameChart));
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        if (this.b.size() > 0) {
            this.a.b.g();
            this.a.b.h();
        }
    }

    /* access modifiers changed from: private */
    public void h(Annotation annotation) {
        this.a.b.b(annotation.getView(), annotation.getPosition() == Position.IN_FRONT_OF_DATA ? Position.BEHIND_DATA : Position.IN_FRONT_OF_DATA);
        this.a.b.a(annotation.getView(), annotation.getPosition());
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            ((Annotation) this.b.get(i)).b();
        }
    }
}
