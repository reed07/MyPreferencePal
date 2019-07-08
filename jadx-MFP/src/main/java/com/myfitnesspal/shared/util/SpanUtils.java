package com.myfitnesspal.shared.util;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.util.InternalURLSpan;

public final class SpanUtils {
    public static Spanned fromHtml(String str) {
        if (VERSION.SDK_INT >= 24) {
            return Html.fromHtml(str, 0);
        }
        return Html.fromHtml(str);
    }

    public static void setSpannableTextOn(TextView textView, int i, int i2, OnClickListener onClickListener) {
        Context context = textView.getContext();
        String string = context.getString(i);
        String string2 = context.getString(i2, new Object[]{string});
        int indexOf = string2.indexOf(string);
        SpannableString spannableString = new SpannableString(string2);
        if (indexOf != -1) {
            spannableString.setSpan(new InternalURLSpan(onClickListener, context.getResources().getColor(R.color.blue)), indexOf, string.length() + indexOf, 33);
        }
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
