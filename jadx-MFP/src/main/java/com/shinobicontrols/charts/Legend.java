package com.shinobicontrols.charts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;
import java.util.List;

public class Legend extends LinearLayout {
    public static final int ALL = -1;
    public static final int VARIABLE = -2;
    bg a = bg.a(this);
    private final List<av> b = new ArrayList();
    private final ax c;
    private Placement d = Placement.OUTSIDE_PLOT_AREA;
    private Position e = Position.TOP_RIGHT;
    private LegendStyle f;
    private final Title g;
    private int h;
    private au i;
    private int j = -2;
    private final float k = getResources().getDisplayMetrics().density;

    public enum Placement {
        INSIDE_PLOT_AREA,
        ON_PLOT_AREA_BORDER,
        OUTSIDE_PLOT_AREA
    }

    public enum Position {
        BOTTOM_CENTER(81),
        BOTTOM_LEFT(83),
        BOTTOM_RIGHT(85),
        MIDDLE_LEFT(19),
        MIDDLE_RIGHT(21),
        TOP_CENTER(49),
        TOP_LEFT(51),
        TOP_RIGHT(53);
        
        private final int a;

        private Position(int i) {
            this.a = i;
        }

        /* access modifiers changed from: 0000 */
        public int a() {
            return this.a;
        }
    }

    public enum SymbolAlignment {
        LEFT,
        RIGHT
    }

    Legend(Context context) {
        super(context);
        setOrientation(1);
        this.g = a(context);
        this.c = b(context);
        addView(this.g);
        addView(this.c);
    }

    private Title a(Context context) {
        Title title = new Title(context);
        title.setVisibility(8);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        title.setLayoutParams(layoutParams);
        return title;
    }

    private ax b(Context context) {
        ax axVar = new ax(context);
        axVar.setLayoutParams(new LayoutParams(-2, -2));
        return axVar;
    }

    public int getMaxSeriesPerRow() {
        return this.j;
    }

    public Placement getPlacement() {
        return this.d;
    }

    public Position getPosition() {
        return this.e;
    }

    public LegendStyle getStyle() {
        return this.f;
    }

    public String getTitle() {
        return this.g.getText().toString();
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.b.clear();
        this.b.addAll(this.i.a(this.f));
        this.c.a(this.b, a(this.j, this.b.size()));
        super.setVisibility(d());
        b();
        invalidate();
        requestLayout();
    }

    private int a(int i2, int i3) {
        if (i2 >= 0) {
            return i2;
        }
        if (i2 == -1 || this.e == Position.TOP_CENTER || this.e == Position.BOTTOM_CENTER) {
            return i3;
        }
        return 1;
    }

    private void b() {
        c();
        this.g.a((TitleStyle) this.f.a());
        this.c.a(this.f);
        int a2 = at.a(this.k, this.f.getPadding());
        setPadding(a2, a2, a2, a2);
        a(this.f.getRowVerticalMargin());
    }

    @SuppressLint({"NewApi"})
    private void c() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(this.f.getBackgroundColor());
        gradientDrawable.setStroke(at.a(this.k, this.f.getBorderWidth()), this.f.getBorderColor());
        gradientDrawable.setCornerRadius(this.f.getCornerRadius());
        if (VERSION.SDK_INT >= 16) {
            setBackground(gradientDrawable);
        } else {
            setBackgroundDrawable(gradientDrawable);
        }
    }

    private void a(float f2) {
        int a2 = at.a(this.k, f2 / 2.0f);
        if (this.c.getChildCount() > 0) {
            Title title = this.g;
            if (title != null && title.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) this.g.getLayoutParams();
                layoutParams.bottomMargin += a2;
                ((LayoutParams) this.c.getLayoutParams()).topMargin = a2;
            }
        }
    }

    private int d() {
        if (e()) {
            return 8;
        }
        return this.h;
    }

    private boolean e() {
        return this.b.isEmpty() && this.g.getVisibility() == 8;
    }

    public void setMaxSeriesPerRow(int i2) {
        this.j = i2;
        a();
    }

    public void setPlacement(Placement placement) {
        this.d = placement;
        this.a = bg.a(this);
        a();
    }

    public void setPosition(Position position) {
        this.e = position;
        this.a = bg.a(this);
        a();
    }

    public void setStyle(LegendStyle legendStyle) {
        this.f = legendStyle;
    }

    public void setTitle(String str) {
        this.g.setText(str);
        if (Axis.a(str)) {
            this.g.setVisibility(8);
        } else {
            this.g.setVisibility(0);
        }
        a();
    }

    public void setVisibility(int i2) {
        this.h = i2;
        super.setVisibility(d());
    }

    /* access modifiers changed from: 0000 */
    public void a(au auVar) {
        this.i = auVar;
    }
}
