package com.facebook.ads.internal.view.g;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.List;

public class d extends Adapter<f> {
    private final List<String> a;
    private final int b;

    d(List<String> list, int i) {
        this.a = list;
        this.b = i;
    }

    /* renamed from: a */
    public f onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new f(new e(viewGroup.getContext()));
    }

    /* renamed from: a */
    public void onBindViewHolder(f fVar, int i) {
        String str = (String) this.a.get(i);
        MarginLayoutParams marginLayoutParams = new MarginLayoutParams(-2, -1);
        marginLayoutParams.setMargins(i == 0 ? this.b * 4 : this.b, 0, i >= getItemCount() + -1 ? this.b * 4 : this.b, 0);
        fVar.a().setLayoutParams(marginLayoutParams);
        fVar.a().a(str);
    }

    public int getItemCount() {
        return this.a.size();
    }
}
