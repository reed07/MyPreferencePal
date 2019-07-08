package com.shinobicontrols.charts;

import com.shinobicontrols.charts.Title.CentersOn;

public final class MainTitleStyle extends TitleStyle {
    final dj<Boolean> a = new dj<>(Boolean.valueOf(false));
    final dj<CentersOn> b = new dj<>(CentersOn.PLOTTING_AREA);

    /* access modifiers changed from: 0000 */
    public void a(MainTitleStyle mainTitleStyle) {
        super.a(mainTitleStyle);
        this.a.b(Boolean.valueOf(mainTitleStyle.getOverlapsChart()));
        this.b.b(mainTitleStyle.getCentersOn());
    }

    public CentersOn getCentersOn() {
        return (CentersOn) this.b.a;
    }

    public void setCentersOn(CentersOn centersOn) {
        this.b.a(centersOn);
    }

    public boolean getOverlapsChart() {
        return ((Boolean) this.a.a).booleanValue();
    }

    public void setOverlapsChart(boolean z) {
        this.a.a(Boolean.valueOf(z));
    }
}
