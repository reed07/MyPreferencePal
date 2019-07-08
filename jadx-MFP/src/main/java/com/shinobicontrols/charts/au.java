package com.shinobicontrols.charts;

import android.annotation.SuppressLint;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class au {
    private final v a;
    private final float b;

    au(ShinobiChart shinobiChart) {
        if (shinobiChart instanceof v) {
            this.a = (v) shinobiChart;
            this.b = this.a.getResources().getDisplayMetrics().density;
            return;
        }
        throw new IllegalStateException("Unable to retrieve LegendItems from Chart");
    }

    /* access modifiers changed from: 0000 */
    public List<av> a(LegendStyle legendStyle) {
        if (this.a.l()) {
            return b(legendStyle);
        }
        return c(legendStyle);
    }

    private List<av> b(LegendStyle legendStyle) {
        ArrayList arrayList = new ArrayList();
        if (this.a.getSeries().size() > 0) {
            Series series = (Series) this.a.getSeries().get(0);
            if (series.isShownInLegend() && (series instanceof PieDonutSeries)) {
                PieDonutSeries pieDonutSeries = (PieDonutSeries) series;
                int length = pieDonutSeries.n.c.length;
                for (int i = 0; i < length; i++) {
                    arrayList.add(new av(a(pieDonutSeries.a(i), b(i)), new ay(this.a.getContext(), pieDonutSeries.a(i, this.b)), pieDonutSeries, i));
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private List<av> c(LegendStyle legendStyle) {
        ArrayList arrayList = new ArrayList();
        List series = this.a.getSeries();
        for (int i = 0; i < series.size(); i++) {
            Series series2 = (Series) series.get(i);
            if (series2.isShownInLegend()) {
                arrayList.add(new av(a(series2.getTitle(), a(i)), new ay(this.a.getContext(), series2.a(this.b)), series2, -1));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @SuppressLint({"DefaultLocale"})
    private String a(int i) {
        return String.format("%s %d", new Object[]{"Series", Integer.valueOf(i + 1)});
    }

    @SuppressLint({"DefaultLocale"})
    private String b(int i) {
        return String.format("%s %d", new Object[]{"Slice", Integer.valueOf(i + 1)});
    }

    private TextView a(String str, String str2) {
        TextView textView = new TextView(this.a.getContext());
        textView.setText(b(str, str2));
        int a2 = at.a(this.b, 5.0f);
        textView.setPadding(a2, a2, a2, a2);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    private String b(String str, String str2) {
        return Axis.a(str) ? str2 : str;
    }
}
