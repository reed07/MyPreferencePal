package com.myfitnesspal.shared.extension;

import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View.OnClickListener;
import android.widget.TextView;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\b\u001a\u00020\t\u001a&\u0010\u0007\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\r\u001aC\u0010\u000e\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042*\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u00110\u0010\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u0011¢\u0006\u0002\u0010\u0012\u001a(\u0010\u0013\u001a\u00020\u0001*\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\t2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00150\r¨\u0006\u0016"}, d2 = {"addSpan", "", "Landroid/text/SpannableStringBuilder;", "text", "", "spannable", "Landroid/text/style/CharacterStyle;", "setColorSpan", "color", "", "Landroid/widget/TextView;", "originalText", "spanToColorMap", "", "setImageSpan", "phraseToImage", "", "Lkotlin/Pair;", "(Landroid/widget/TextView;Ljava/lang/String;[Lkotlin/Pair;)V", "setLinkSpan", "spanToListenerMap", "Landroid/view/View$OnClickListener;", "app_googleRelease"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "SpannableUtil")
/* compiled from: SpannableExt.kt */
public final class SpannableUtil {
    public static final void setLinkSpan(@NotNull TextView textView, @StringRes int i, @NotNull Map<Integer, ? extends OnClickListener> map) {
        Intrinsics.checkParameterIsNotNull(textView, "receiver$0");
        Intrinsics.checkParameterIsNotNull(map, "spanToListenerMap");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView.getContext().getString(i));
        for (Entry entry : map.entrySet()) {
            String string = textView.getContext().getString(((Number) entry.getKey()).intValue());
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(entry.key)");
            addSpan(spannableStringBuilder, string, new SpannableUtil$setLinkSpan$1(entry));
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableStringBuilder);
    }

    public static final void setColorSpan(@NotNull TextView textView, @NotNull String str, @NotNull Map<String, Integer> map) {
        Intrinsics.checkParameterIsNotNull(textView, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "originalText");
        Intrinsics.checkParameterIsNotNull(map, "spanToColorMap");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        for (Entry entry : map.entrySet()) {
            addSpan(spannableStringBuilder, (String) entry.getKey(), new ForegroundColorSpan(((Number) entry.getValue()).intValue()));
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableStringBuilder);
    }

    public static final void setImageSpan(@NotNull TextView textView, @NotNull String str, @NotNull Pair<String, Integer>... pairArr) {
        Intrinsics.checkParameterIsNotNull(textView, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "originalText");
        Intrinsics.checkParameterIsNotNull(pairArr, "phraseToImage");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        for (Pair<String, Integer> pair : pairArr) {
            addSpan(spannableStringBuilder, (String) pair.getFirst(), new ImageSpan(textView.getContext(), ((Number) pair.getSecond()).intValue(), 1));
        }
        textView.setText(spannableStringBuilder);
    }

    public static final void setColorSpan(@NotNull SpannableStringBuilder spannableStringBuilder, @NotNull String str, @ColorInt int i) {
        Intrinsics.checkParameterIsNotNull(spannableStringBuilder, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "text");
        addSpan(spannableStringBuilder, str, new ForegroundColorSpan(i));
    }

    private static final void addSpan(@NotNull SpannableStringBuilder spannableStringBuilder, String str, CharacterStyle characterStyle) {
        String spannableStringBuilder2 = spannableStringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(spannableStringBuilder2, "this.toString()");
        int indexOf$default = StringsKt.indexOf$default((CharSequence) spannableStringBuilder2, str, 0, false, 6, (Object) null);
        if (indexOf$default != -1) {
            spannableStringBuilder.setSpan(characterStyle, indexOf$default, str.length() + indexOf$default, 33);
        }
    }
}
