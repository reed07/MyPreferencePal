package com.myfitnesspal.feature.settings.ui.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mopub.common.Constants;
import com.myfitnesspal.android.R;
import com.myfitnesspal.android.databinding.ChangePasswordBinding;
import com.myfitnesspal.feature.settings.model.ChangePasswordViewModel;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.util.SpanUtils;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0016J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0014H\u0002J\b\u0010 \u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\""}, d2 = {"Lcom/myfitnesspal/feature/settings/ui/activity/ChangePasswordActivity;", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "()V", "learnMoreAction", "Landroid/view/View$OnClickListener;", "viewModel", "Lcom/myfitnesspal/feature/settings/model/ChangePasswordViewModel;", "getViewModel", "()Lcom/myfitnesspal/feature/settings/model/ChangePasswordViewModel;", "setViewModel", "(Lcom/myfitnesspal/feature/settings/model/ChangePasswordViewModel;)V", "vmFactory", "Landroid/arch/lifecycle/ViewModelProvider$Factory;", "getVmFactory", "()Landroid/arch/lifecycle/ViewModelProvider$Factory;", "setVmFactory", "(Landroid/arch/lifecycle/ViewModelProvider$Factory;)V", "backPressed", "", "continuePasswordChanged", "", "initUi", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRebindDialogFragment", "dialog", "Landroid/support/v4/app/DialogFragment;", "tag", "", "showLoadError", "showLoading", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ChangePasswordActivity.kt */
public final class ChangePasswordActivity extends MfpActivity {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String ERROR_DIALOG_FRAGMENT = "error_dialog_fragment";
    private HashMap _$_findViewCache;
    private final OnClickListener learnMoreAction = new ChangePasswordActivity$learnMoreAction$1(this);
    @NotNull
    public ChangePasswordViewModel viewModel;
    @Inject
    @NotNull
    public Factory vmFactory;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/feature/settings/ui/activity/ChangePasswordActivity$Companion;", "", "()V", "ERROR_DIALOG_FRAGMENT", "", "newStartIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ChangePasswordActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final Intent newStartIntent(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return new Intent(context, ChangePasswordActivity.class);
        }
    }

    @JvmStatic
    @NotNull
    public static final Intent newStartIntent(@NotNull Context context) {
        return Companion.newStartIntent(context);
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
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @NotNull
    public final Factory getVmFactory() {
        Factory factory = this.vmFactory;
        if (factory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
        }
        return factory;
    }

    public final void setVmFactory(@NotNull Factory factory) {
        Intrinsics.checkParameterIsNotNull(factory, "<set-?>");
        this.vmFactory = factory;
    }

    @NotNull
    public final ChangePasswordViewModel getViewModel() {
        ChangePasswordViewModel changePasswordViewModel = this.viewModel;
        if (changePasswordViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return changePasswordViewModel;
    }

    public final void setViewModel(@NotNull ChangePasswordViewModel changePasswordViewModel) {
        Intrinsics.checkParameterIsNotNull(changePasswordViewModel, "<set-?>");
        this.viewModel = changePasswordViewModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        FragmentActivity fragmentActivity = this;
        Factory factory = this.vmFactory;
        if (factory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
        }
        ViewModel viewModel2 = ViewModelProviders.of(fragmentActivity, factory).get(ChangePasswordViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel2, "ViewModelProviders.of(th…ordViewModel::class.java)");
        this.viewModel = (ChangePasswordViewModel) viewModel2;
        ChangePasswordViewModel changePasswordViewModel = this.viewModel;
        if (changePasswordViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, Constants.INTENT_SCHEME);
        changePasswordViewModel.load(intent);
        ChangePasswordBinding inflate = ChangePasswordBinding.inflate(getLayoutInflater());
        Intrinsics.checkExpressionValueIsNotNull(inflate, "this");
        ChangePasswordViewModel changePasswordViewModel2 = this.viewModel;
        if (changePasswordViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        inflate.setViewmodel(changePasswordViewModel2);
        inflate.setLifecycleOwner(this);
        setContentView(inflate.getRoot());
        initUi();
    }

    public boolean onRebindDialogFragment(@NotNull DialogFragment dialogFragment, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(dialogFragment, "dialog");
        Intrinsics.checkParameterIsNotNull(str, "tag");
        if (str.hashCode() != 1750310704 || !str.equals("error_dialog_fragment")) {
            return false;
        }
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) dialogFragment;
        return true;
    }

    public void onBackPressed() {
        ChangePasswordViewModel changePasswordViewModel = this.viewModel;
        if (changePasswordViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (!changePasswordViewModel.getBackPress()) {
            ChangePasswordViewModel changePasswordViewModel2 = this.viewModel;
            if (changePasswordViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            changePasswordViewModel2.onBackPressed();
            super.onBackPressed();
        }
    }

    public boolean backPressed() {
        ChangePasswordViewModel changePasswordViewModel = this.viewModel;
        if (changePasswordViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        changePasswordViewModel.onBackPressed();
        ChangePasswordViewModel changePasswordViewModel2 = this.viewModel;
        if (changePasswordViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return changePasswordViewModel2.getBackPress();
    }

    private final void initUi() {
        SpanUtils.setSpannableTextOn((TextView) _$_findCachedViewById(R.id.learnMore), R.string.change_password_learn_more, R.string.change_password_explanation, this.learnMoreAction);
        ChangePasswordViewModel changePasswordViewModel = this.viewModel;
        if (changePasswordViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        LifecycleOwner lifecycleOwner = this;
        changePasswordViewModel.getPasswordUpdateStatus().observe(lifecycleOwner, new ChangePasswordActivity$initUi$1(this));
        ChangePasswordViewModel changePasswordViewModel2 = this.viewModel;
        if (changePasswordViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        changePasswordViewModel2.getInputError().observe(lifecycleOwner, new ChangePasswordActivity$initUi$2(this));
    }

    /* access modifiers changed from: private */
    public final void continuePasswordChanged() {
        setBusy(false);
        setResult(-1, new Intent());
        finish();
    }

    /* access modifiers changed from: private */
    public final void showLoading() {
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.changingProgressBar);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "changingProgressBar");
        progressBar.setVisibility(0);
        setBusy(true);
    }

    /* access modifiers changed from: private */
    public final void showLoadError() {
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.changingProgressBar);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "changingProgressBar");
        progressBar.setVisibility(8);
        setBusy(false);
        showDialogFragment((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setMessage((int) R.string.change_password_invalid_password)).setTitle(R.string.error)).setNegativeText(R.string.dismiss, null), "error_dialog_fragment");
    }
}
