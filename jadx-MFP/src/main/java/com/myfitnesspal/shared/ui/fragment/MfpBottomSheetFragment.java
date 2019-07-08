package com.myfitnesspal.shared.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.android.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010#\u001a\u00020\u00102\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u0005H\u0002J\u0012\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J&\u0010,\u001a\u0004\u0018\u00010\u00052\u0006\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u0001002\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0010\u00101\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u0005H\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R4\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014R\u0014\u0010\u001d\u001a\u00020\u00168VX\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0018R\u0012\u0010\u001f\u001a\u00020 X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u00062"}, d2 = {"Lcom/myfitnesspal/shared/ui/fragment/MfpBottomSheetFragment;", "Landroid/support/design/widget/BottomSheetDialogFragment;", "()V", "bottomSheetBehavior", "Landroid/support/design/widget/BottomSheetBehavior;", "Landroid/view/View;", "getBottomSheetBehavior", "()Landroid/support/design/widget/BottomSheetBehavior;", "setBottomSheetBehavior", "(Landroid/support/design/widget/BottomSheetBehavior;)V", "contentResId", "", "getContentResId", "()I", "negativeAction", "Lkotlin/Function0;", "", "getNegativeAction", "()Lkotlin/jvm/functions/Function0;", "setNegativeAction", "(Lkotlin/jvm/functions/Function0;)V", "negativeButton", "Landroid/widget/ImageView;", "getNegativeButton", "()Landroid/widget/ImageView;", "value", "positiveAction", "getPositiveAction", "setPositiveAction", "positiveButton", "getPositiveButton", "title", "", "getTitle", "()Ljava/lang/String;", "handlePositiveButtonVisibility", "action", "containerView", "initViews", "view", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "setupBottomSheetBehavior", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: MfpBottomSheetFragment.kt */
public abstract class MfpBottomSheetFragment extends BottomSheetDialogFragment {
    private HashMap _$_findViewCache;
    @NotNull
    public BottomSheetBehavior<View> bottomSheetBehavior;
    @NotNull
    private Function0<Unit> negativeAction = new MfpBottomSheetFragment$negativeAction$1(this);
    @Nullable
    private Function0<Unit> positiveAction;

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

    public abstract int getContentResId();

    @NotNull
    public abstract String getTitle();

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @NotNull
    public ImageView getPositiveButton() {
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.bottomSheetPositiveButton);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "bottomSheetPositiveButton");
        return imageView;
    }

    @NotNull
    public ImageView getNegativeButton() {
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.bottomSheetNegativeButton);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "bottomSheetNegativeButton");
        return imageView;
    }

    @NotNull
    public Function0<Unit> getNegativeAction() {
        return this.negativeAction;
    }

    public void setNegativeAction(@NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "<set-?>");
        this.negativeAction = function0;
    }

    @Nullable
    public Function0<Unit> getPositiveAction() {
        return this.positiveAction;
    }

    public void setPositiveAction(@Nullable Function0<Unit> function0) {
        this.positiveAction = function0;
        handlePositiveButtonVisibility$default(this, function0, null, 2, null);
    }

    @NotNull
    public BottomSheetBehavior<View> getBottomSheetBehavior() {
        BottomSheetBehavior<View> bottomSheetBehavior2 = this.bottomSheetBehavior;
        if (bottomSheetBehavior2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomSheetBehavior");
        }
        return bottomSheetBehavior2;
    }

    public void setBottomSheetBehavior(@NotNull BottomSheetBehavior<View> bottomSheetBehavior2) {
        Intrinsics.checkParameterIsNotNull(bottomSheetBehavior2, "<set-?>");
        this.bottomSheetBehavior = bottomSheetBehavior2;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.bottom_sheet_mfp_base, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, Promotion.ACTION_VIEW);
        initViews(inflate);
        return inflate;
    }

    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        Intrinsics.checkExpressionValueIsNotNull(onCreateDialog, "super.onCreateDialog(savedInstanceState)");
        onCreateDialog.setOnShowListener(new MfpBottomSheetFragment$onCreateDialog$1(this, onCreateDialog));
        return onCreateDialog;
    }

    private final void initViews(View view) {
        TextView textView = (TextView) view.findViewById(R.id.bottomSheetTitle);
        Intrinsics.checkExpressionValueIsNotNull(textView, "view.bottomSheetTitle");
        textView.setText(getTitle());
        ((ImageView) view.findViewById(R.id.bottomSheetNegativeButton)).setOnClickListener(new MfpBottomSheetFragment$initViews$1(this));
        handlePositiveButtonVisibility(getPositiveAction(), view);
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.bottomSheetViewStub);
        Intrinsics.checkExpressionValueIsNotNull(viewStub, "view.bottomSheetViewStub");
        viewStub.setLayoutResource(getContentResId());
        ((ViewStub) view.findViewById(R.id.bottomSheetViewStub)).inflate();
    }

    /* access modifiers changed from: private */
    public final void setupBottomSheetBehavior(View view) {
        BottomSheetBehavior from = BottomSheetBehavior.from(view);
        Intrinsics.checkExpressionValueIsNotNull(from, "BottomSheetBehavior.from(view)");
        setBottomSheetBehavior(from);
        getBottomSheetBehavior().setState(3);
    }

    static /* synthetic */ void handlePositiveButtonVisibility$default(MfpBottomSheetFragment mfpBottomSheetFragment, Function0 function0, View view, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                view = mfpBottomSheetFragment.getView();
            }
            mfpBottomSheetFragment.handlePositiveButtonVisibility(function0, view);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: handlePositiveButtonVisibility");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void handlePositiveButtonVisibility(kotlin.jvm.functions.Function0<kotlin.Unit> r3, android.view.View r4) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x002f
            if (r4 == 0) goto L_0x0012
            int r0 = com.myfitnesspal.android.R.id.bottomSheetPositiveButton
            android.view.View r0 = r4.findViewById(r0)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            if (r0 == 0) goto L_0x0012
            r1 = 0
            r0.setVisibility(r1)
        L_0x0012:
            if (r4 == 0) goto L_0x002b
            int r0 = com.myfitnesspal.android.R.id.bottomSheetPositiveButton
            android.view.View r0 = r4.findViewById(r0)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            if (r0 == 0) goto L_0x002b
            com.myfitnesspal.shared.ui.fragment.MfpBottomSheetFragment$handlePositiveButtonVisibility$1$1 r1 = new com.myfitnesspal.shared.ui.fragment.MfpBottomSheetFragment$handlePositiveButtonVisibility$1$1
            r1.<init>(r3)
            android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
            r0.setOnClickListener(r1)
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            goto L_0x002c
        L_0x002b:
            r3 = 0
        L_0x002c:
            if (r3 == 0) goto L_0x002f
            goto L_0x0045
        L_0x002f:
            r3 = r2
            com.myfitnesspal.shared.ui.fragment.MfpBottomSheetFragment r3 = (com.myfitnesspal.shared.ui.fragment.MfpBottomSheetFragment) r3
            if (r4 == 0) goto L_0x0043
            int r3 = com.myfitnesspal.android.R.id.bottomSheetPositiveButton
            android.view.View r3 = r4.findViewById(r3)
            android.widget.ImageView r3 = (android.widget.ImageView) r3
            if (r3 == 0) goto L_0x0043
            r4 = 8
            r3.setVisibility(r4)
        L_0x0043:
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
        L_0x0045:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.ui.fragment.MfpBottomSheetFragment.handlePositiveButtonVisibility(kotlin.jvm.functions.Function0, android.view.View):void");
    }
}
