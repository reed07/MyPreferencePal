package com.myfitnesspal.feature.timestamp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.util.DateTimeUtils;
import java.util.Date;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0006\u0010\u0011\u001a\u00020\u000eJ\u0006\u0010\u0012\u001a\u00020\u000e¨\u0006\u0013"}, d2 = {"Lcom/myfitnesspal/feature/timestamp/view/TimestampRowView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "setTime", "", "time", "Ljava/util/Date;", "showLock", "showTime", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: TimestampRowView.kt */
public final class TimestampRowView extends LinearLayout {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public TimestampRowView(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context);
        View.inflate(getContext(), R.layout.view_timestamp_row, this);
    }

    public TimestampRowView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context, attributeSet);
        View.inflate(getContext(), R.layout.view_timestamp_row, this);
    }

    public TimestampRowView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context, attributeSet, i);
        View.inflate(getContext(), R.layout.view_timestamp_row, this);
    }

    public TimestampRowView(@NotNull Context context, @NotNull AttributeSet attributeSet, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
        super(context, attributeSet, i, i2);
        View.inflate(getContext(), R.layout.view_timestamp_row, this);
    }

    public final void showLock() {
        TextView textView = (TextView) _$_findCachedViewById(R.id.timeValue);
        Intrinsics.checkExpressionValueIsNotNull(textView, "this.timeValue");
        textView.setVisibility(8);
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.lock);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "this.lock");
        imageView.setVisibility(0);
    }

    public final void showTime() {
        TextView textView = (TextView) _$_findCachedViewById(R.id.timeValue);
        Intrinsics.checkExpressionValueIsNotNull(textView, "this.timeValue");
        textView.setVisibility(0);
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.lock);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "this.lock");
        imageView.setVisibility(8);
    }

    public final void setTime(@Nullable Date date) {
        if (date != null) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.timeValue);
            Intrinsics.checkExpressionValueIsNotNull(textView, "timeValue");
            textView.setText(DateTimeUtils.localeFormattedTime(getContext(), date));
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.timeValue)).setText(R.string.timestamp_value_no_time);
    }
}
