package com.facebook.ads.internal.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class ab extends View {
    private aa a;

    public ab(Context context, aa aaVar) {
        super(context);
        this.a = aaVar;
        setLayoutParams(new LayoutParams(0, 0));
    }

    public void onWindowVisibilityChanged(int i) {
        aa aaVar = this.a;
        if (aaVar != null) {
            aaVar.a(i);
        }
    }
}
