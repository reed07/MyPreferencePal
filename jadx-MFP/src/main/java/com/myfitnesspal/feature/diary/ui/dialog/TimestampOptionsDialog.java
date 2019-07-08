package com.myfitnesspal.feature.diary.ui.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.timestamp.model.TimestampOption;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000  2\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\bH\u0016J\u0010\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u000fH\u0002J\u001a\u0010\u001c\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0017R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR5\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\b0\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006!"}, d2 = {"Lcom/myfitnesspal/feature/diary/ui/dialog/TimestampOptionsDialog;", "Landroid/support/design/widget/BottomSheetDialogFragment;", "()V", "bottomSheetBehavior", "Landroid/support/design/widget/BottomSheetBehavior;", "Landroid/view/View;", "onDismiss", "Lkotlin/Function0;", "", "getOnDismiss", "()Lkotlin/jvm/functions/Function0;", "setOnDismiss", "(Lkotlin/jvm/functions/Function0;)V", "onOptionSelect", "Lkotlin/Function1;", "Lcom/myfitnesspal/feature/timestamp/model/TimestampOption;", "Lkotlin/ParameterName;", "name", "option", "getOnOptionSelect", "()Lkotlin/jvm/functions/Function1;", "setOnOptionSelect", "(Lkotlin/jvm/functions/Function1;)V", "dialog", "Landroid/content/DialogInterface;", "onStart", "selectTimeOption", "selectOption", "setupDialog", "Landroid/app/Dialog;", "style", "", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: TimestampOptionsDialog.kt */
public final class TimestampOptionsDialog extends BottomSheetDialogFragment {
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_LAST_TIME = "last_time";
    @NotNull
    public static final String FRAGMENT_TAG = "time_options_picker";
    private HashMap _$_findViewCache;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    @NotNull
    private Function0<Unit> onDismiss = TimestampOptionsDialog$onDismiss$1.INSTANCE;
    @NotNull
    private Function1<? super TimestampOption, Unit> onOptionSelect = TimestampOptionsDialog$onOptionSelect$1.INSTANCE;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/feature/diary/ui/dialog/TimestampOptionsDialog$Companion;", "", "()V", "EXTRA_LAST_TIME", "", "FRAGMENT_TAG", "newInstance", "Lcom/myfitnesspal/feature/diary/ui/dialog/TimestampOptionsDialog;", "lastTimeString", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: TimestampOptionsDialog.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final TimestampOptionsDialog newInstance(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "lastTimeString");
            TimestampOptionsDialog timestampOptionsDialog = new TimestampOptionsDialog();
            Bundle bundle = new Bundle();
            bundle.putString(TimestampOptionsDialog.EXTRA_LAST_TIME, str);
            timestampOptionsDialog.setArguments(bundle);
            return timestampOptionsDialog;
        }
    }

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
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @NotNull
    public final Function1<TimestampOption, Unit> getOnOptionSelect() {
        return this.onOptionSelect;
    }

    public final void setOnOptionSelect(@NotNull Function1<? super TimestampOption, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "<set-?>");
        this.onOptionSelect = function1;
    }

    @NotNull
    public final Function0<Unit> getOnDismiss() {
        return this.onDismiss;
    }

    public final void setOnDismiss(@NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "<set-?>");
        this.onDismiss = function0;
    }

    @SuppressLint({"RestrictedApi"})
    public void setupDialog(@Nullable Dialog dialog, int i) {
        super.setupDialog(dialog, i);
        if (dialog != null) {
            Object obj = null;
            View inflate = LayoutInflater.from(dialog.getContext()).inflate(R.layout.bottom_sheet_timestamp_options, null);
            dialog.setContentView(inflate);
            Intrinsics.checkExpressionValueIsNotNull(inflate, Param.CONTENT);
            ViewParent parent = inflate.getParent();
            if (parent != null) {
                this.bottomSheetBehavior = BottomSheetBehavior.from((View) parent);
                ((TextView) dialog.findViewById(R.id.setTime)).setOnClickListener(new TimestampOptionsDialog$setupDialog$$inlined$apply$lambda$1(this));
                ((TextView) dialog.findViewById(R.id.setCurrentTime)).setOnClickListener(new TimestampOptionsDialog$setupDialog$$inlined$apply$lambda$2(this));
                ((TextView) dialog.findViewById(R.id.setNoTime)).setOnClickListener(new TimestampOptionsDialog$setupDialog$$inlined$apply$lambda$3(this));
                Bundle arguments = getArguments();
                if (arguments != null) {
                    obj = arguments.get(EXTRA_LAST_TIME);
                }
                if (!Strings.isEmpty(obj)) {
                    TextView textView = (TextView) dialog.findViewById(R.id.useLastTime);
                    Intrinsics.checkExpressionValueIsNotNull(textView, "useLastTime");
                    textView.setText(getString(R.string.timestamp_select_last_time, obj));
                    ((TextView) dialog.findViewById(R.id.useLastTime)).setOnClickListener(new TimestampOptionsDialog$setupDialog$$inlined$apply$lambda$4(this));
                    return;
                }
                TextView textView2 = (TextView) dialog.findViewById(R.id.useLastTime);
                Intrinsics.checkExpressionValueIsNotNull(textView2, "useLastTime");
                textView2.setVisibility(8);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.View");
        }
    }

    public void onStart() {
        super.onStart();
        BottomSheetBehavior<View> bottomSheetBehavior2 = this.bottomSheetBehavior;
        if (bottomSheetBehavior2 != null) {
            bottomSheetBehavior2.setState(3);
        }
    }

    public void onDismiss(@Nullable DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        this.onDismiss.invoke();
    }

    /* access modifiers changed from: private */
    public final void selectTimeOption(TimestampOption timestampOption) {
        this.onOptionSelect.invoke(timestampOption);
        dismiss();
    }
}
