package com.facebook.ads.internal.w.b;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.r.a;

public class g {
    public static void a(final OnClickListener onClickListener, final OnClickListener onClickListener2, @Nullable Context context) {
        if (context == null || !(context instanceof Activity)) {
            onClickListener.onClick(null, 0);
        } else {
            new Builder(context).setTitle(a.f(context)).setMessage(a.g(context)).setPositiveButton(a.h(context), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    onClickListener.onClick(dialogInterface, i);
                }
            }).setNegativeButton(a.i(context), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    onClickListener2.onClick(dialogInterface, i);
                }
            }).show();
        }
    }
}
