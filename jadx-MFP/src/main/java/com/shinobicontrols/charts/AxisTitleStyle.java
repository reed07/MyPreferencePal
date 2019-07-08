package com.shinobicontrols.charts;

import com.shinobicontrols.charts.Title.Orientation;

public final class AxisTitleStyle extends TitleStyle {
    final dj<Orientation> a = new dj<>(Orientation.HORIZONTAL);

    public Orientation getOrientation() {
        return (Orientation) this.a.a;
    }

    public void setOrientation(Orientation orientation) {
        this.a.a(orientation);
    }

    /* access modifiers changed from: 0000 */
    public void a(AxisTitleStyle axisTitleStyle) {
        super.a(axisTitleStyle);
        this.a.b(axisTitleStyle.getOrientation());
    }
}
