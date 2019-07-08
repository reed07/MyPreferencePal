package com.myfitnesspal.feature.settings.ui.activity;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.model.TroubleshootingViewModel;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\b\u0010\u001a\u001a\u00020\u0018H\u0002J\b\u0010\u001b\u001a\u00020\u0018H\u0002J\b\u0010\u001c\u001a\u00020\u0018H\u0002J\u0012\u0010\u001d\u001a\u00020\u00182\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0018\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0004H\u0016J\u0010\u0010$\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\u0004H\u0002J\u0010\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006*"}, d2 = {"Lcom/myfitnesspal/feature/settings/ui/activity/TroubleshootingActivity;", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "()V", "diagnosticCode", "", "positiveListener", "Lcom/myfitnesspal/shared/ui/dialog/AlertDialogFragmentBase$DialogPositiveListener;", "", "responseString", "uploadedSuccessful", "", "viewModel", "Lcom/myfitnesspal/feature/settings/model/TroubleshootingViewModel;", "getViewModel", "()Lcom/myfitnesspal/feature/settings/model/TroubleshootingViewModel;", "setViewModel", "(Lcom/myfitnesspal/feature/settings/model/TroubleshootingViewModel;)V", "vmFactory", "Landroid/arch/lifecycle/ViewModelProvider$Factory;", "getVmFactory", "()Landroid/arch/lifecycle/ViewModelProvider$Factory;", "setVmFactory", "(Landroid/arch/lifecycle/ViewModelProvider$Factory;)V", "checkDiagnosticCode", "", "getAnalyticsScreenTag", "getDiagnostics", "hideProgressDialog", "initUi", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRebindDialogFragment", "dialog", "Landroid/support/v4/app/DialogFragment;", "tag", "showAlertDialog", "showProgressDialog", "stringId", "", "submit", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: TroubleshootingActivity.kt */
public final class TroubleshootingActivity extends MfpActivity {
    private static final String ALLOW_DIALOG_FRAGMENT = "allow_dialog_fragment";
    public static final Companion Companion = new Companion(null);
    private static final String DATABASE_UPLOAD_REQUESTED = "database_upload_requested";
    private static final String EMPTY_CODE_DIALOG_FRAGMENT = "empty_code_dialog_fragment";
    private static final String ERROR_DIALOG_FRAGMENT = "error_dialog_fragment";
    private static final String INVALID_CODE = "invalid_code";
    private static final String INVALID_CODE_DIALOG_FRAGMENT = "invalid_code_error_dialog_fragment";
    private static final String INVALID_UPLOAD = "invalid_upload";
    private static final String NO_INTERNET_DIALOG_FRAGMENT = "no_internet_error_dialog_fragment";
    private static final String OK = "ok";
    private static final String PROBLEM_TRANSFERRING_DATA_DIALOG_FRAGMENT = "problem_transferring_data_dialog_fragment";
    private static final String PROGRESS_DIALOG_TAG = "progress_dialog";
    private static final String REPLACEMENT_DATABASE_AVAILABLE = "replacement_database_available";
    private static final String SUCCESS_DIALOG_FRAGMENT = "success_dialog_fragment";
    private static final String TRANSFER_ABORTED_DIALOG_FRAGMENT = "transfer_aborted_error_dialog_fragment";
    private static final String UPDATE_DATA_DIALOG_FRAGMENT = "update_data_dialog_fragment";
    private static final String UPLOAD_NOT_REQUESTED = "upload_not_requested";
    private HashMap _$_findViewCache;
    private String diagnosticCode;
    private DialogPositiveListener<Object> positiveListener;
    /* access modifiers changed from: private */
    public String responseString;
    /* access modifiers changed from: private */
    public boolean uploadedSuccessful;
    @NotNull
    public TroubleshootingViewModel viewModel;
    @Inject
    @NotNull
    public Factory vmFactory;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/myfitnesspal/feature/settings/ui/activity/TroubleshootingActivity$Companion;", "", "()V", "ALLOW_DIALOG_FRAGMENT", "", "DATABASE_UPLOAD_REQUESTED", "EMPTY_CODE_DIALOG_FRAGMENT", "ERROR_DIALOG_FRAGMENT", "INVALID_CODE", "INVALID_CODE_DIALOG_FRAGMENT", "INVALID_UPLOAD", "NO_INTERNET_DIALOG_FRAGMENT", "OK", "PROBLEM_TRANSFERRING_DATA_DIALOG_FRAGMENT", "PROGRESS_DIALOG_TAG", "REPLACEMENT_DATABASE_AVAILABLE", "SUCCESS_DIALOG_FRAGMENT", "TRANSFER_ABORTED_DIALOG_FRAGMENT", "UPDATE_DATA_DIALOG_FRAGMENT", "UPLOAD_NOT_REQUESTED", "newStartIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: TroubleshootingActivity.kt */
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
            return new Intent(context, TroubleshootingActivity.class);
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
    public String getAnalyticsScreenTag() {
        return Screens.TROUBLESHOOTING;
    }

    @NotNull
    public static final /* synthetic */ String access$getResponseString$p(TroubleshootingActivity troubleshootingActivity) {
        String str = troubleshootingActivity.responseString;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("responseString");
        }
        return str;
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
    public final TroubleshootingViewModel getViewModel() {
        TroubleshootingViewModel troubleshootingViewModel = this.viewModel;
        if (troubleshootingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return troubleshootingViewModel;
    }

    public final void setViewModel(@NotNull TroubleshootingViewModel troubleshootingViewModel) {
        Intrinsics.checkParameterIsNotNull(troubleshootingViewModel, "<set-?>");
        this.viewModel = troubleshootingViewModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.troubleshooting);
        FragmentActivity fragmentActivity = this;
        Factory factory = this.vmFactory;
        if (factory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
        }
        ViewModel viewModel2 = ViewModelProviders.of(fragmentActivity, factory).get(TroubleshootingViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel2, "ViewModelProviders.of(th…ingViewModel::class.java)");
        this.viewModel = (TroubleshootingViewModel) viewModel2;
        initUi();
    }

    public boolean onRebindDialogFragment(@NotNull DialogFragment dialogFragment, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(dialogFragment, "dialog");
        Intrinsics.checkParameterIsNotNull(str, "tag");
        if (!ALLOW_DIALOG_FRAGMENT.equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) dialogFragment;
        DialogPositiveListener<Object> dialogPositiveListener = this.positiveListener;
        if (dialogPositiveListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("positiveListener");
        }
        alertDialogFragment.setPositiveListener(dialogPositiveListener);
        return true;
    }

    private final void initUi() {
        TroubleshootingViewModel troubleshootingViewModel = this.viewModel;
        if (troubleshootingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        LifecycleOwner lifecycleOwner = this;
        troubleshootingViewModel.getCheckDiagnosticCodeResponse$app_googleRelease().observe(lifecycleOwner, new TroubleshootingActivity$initUi$1(this));
        TroubleshootingViewModel troubleshootingViewModel2 = this.viewModel;
        if (troubleshootingViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        troubleshootingViewModel2.getUploadDiagnosticCodeResponse$app_googleRelease().observe(lifecycleOwner, new TroubleshootingActivity$initUi$2(this));
        ((Button) _$_findCachedViewById(R.id.submit)).setOnClickListener(new TroubleshootingActivity$initUi$3(this));
        this.positiveListener = new TroubleshootingActivity$initUi$4(this);
    }

    /* access modifiers changed from: private */
    public final void submit() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.code);
        Intrinsics.checkExpressionValueIsNotNull(editText, "code");
        this.diagnosticCode = editText.getText().toString();
        String str = this.diagnosticCode;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diagnosticCode");
        }
        CharSequence charSequence = str;
        if (charSequence == null || charSequence.length() == 0) {
            showDialogFragment(((AlertDialogFragment) new AlertDialogFragment().setMessage(getString(R.string.please_enter_diag_code))).setNegativeText(R.string.dismiss, null), EMPTY_CODE_DIALOG_FRAGMENT);
        } else if (!ConnectivityUtil.isOnline()) {
            showDialogFragment(((AlertDialogFragment) new AlertDialogFragment().setMessage(getString(R.string.internet_connection_required))).setNegativeText(R.string.dismiss, null), NO_INTERNET_DIALOG_FRAGMENT);
        } else {
            EditText editText2 = (EditText) _$_findCachedViewById(R.id.code);
            Intrinsics.checkExpressionValueIsNotNull(editText2, "code");
            checkDiagnosticCode(editText2.getText().toString());
        }
    }

    private final void checkDiagnosticCode(String str) {
        TroubleshootingViewModel troubleshootingViewModel = this.viewModel;
        if (troubleshootingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        troubleshootingViewModel.checkDiagnosticCode(str);
    }

    /* access modifiers changed from: private */
    public final void getDiagnostics() {
        TroubleshootingViewModel troubleshootingViewModel = this.viewModel;
        if (troubleshootingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        troubleshootingViewModel.getDiagnostics(this);
    }

    /* access modifiers changed from: private */
    public final void showProgressDialog(int i) {
        Activity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v4.app.FragmentActivity");
        } else if (((FragmentActivity) activity).getSupportFragmentManager().findFragmentByTag("progress_dialog") == null) {
            showDialogFragment(ProgressDialogFragment.newInstance(i), "progress_dialog");
        }
    }

    /* access modifiers changed from: private */
    public final void showAlertDialog(String str) {
        switch (str.hashCode()) {
            case -578190254:
                if (str.equals(REPLACEMENT_DATABASE_AVAILABLE)) {
                    showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.new_data_available)).setMessage(getString(R.string.updateData_request))).setPositiveText(R.string.allow, null)).setNegativeText(R.string.cancel, null), UPDATE_DATA_DIALOG_FRAGMENT);
                    return;
                }
                return;
            case -112900791:
                if (str.equals(INVALID_UPLOAD)) {
                    showDialogFragment(((AlertDialogFragment) new AlertDialogFragment().setMessage(getString(R.string.problem_transferring_data))).setNegativeText(R.string.dismiss, null), PROBLEM_TRANSFERRING_DATA_DIALOG_FRAGMENT);
                    return;
                }
                return;
            case 3548:
                if (str.equals(OK)) {
                    showDialogFragment(((AlertDialogFragment) new AlertDialogFragment().setMessage(getString(R.string.data_transferred_successfully))).setNegativeText(R.string.dismiss, null), SUCCESS_DIALOG_FRAGMENT);
                    return;
                }
                return;
            case 526718773:
                if (str.equals(INVALID_CODE)) {
                    showDialogFragment(((AlertDialogFragment) new AlertDialogFragment().setMessage(getString(R.string.invalidorExpired_diagnosticCode))).setNegativeText(R.string.dismiss, null), INVALID_CODE_DIALOG_FRAGMENT);
                    return;
                }
                return;
            case 770238708:
                if (str.equals(DATABASE_UPLOAD_REQUESTED)) {
                    AlertDialogFragment alertDialogFragment = (AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.data_requested)).setMessage(getString(R.string.request_for_diagnosticData));
                    DialogPositiveListener<Object> dialogPositiveListener = this.positiveListener;
                    if (dialogPositiveListener == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("positiveListener");
                    }
                    showDialogFragment(((AlertDialogFragment) alertDialogFragment.setPositiveText(R.string.allow, dialogPositiveListener)).setNegativeText(R.string.cancel, null), ALLOW_DIALOG_FRAGMENT);
                    return;
                }
                return;
            case 1015995204:
                if (str.equals(UPLOAD_NOT_REQUESTED)) {
                    showDialogFragment(((AlertDialogFragment) new AlertDialogFragment().setMessage(getString(R.string.data_no_longer_being_requested))).setNegativeText(R.string.dismiss, null), TRANSFER_ABORTED_DIALOG_FRAGMENT);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public final void hideProgressDialog() {
        Activity activity = getActivity();
        if (activity != null) {
            Fragment findFragmentByTag = ((FragmentActivity) activity).getSupportFragmentManager().findFragmentByTag("progress_dialog");
            if (!(findFragmentByTag instanceof ProgressDialogFragment)) {
                findFragmentByTag = null;
            }
            ProgressDialogFragment progressDialogFragment = (ProgressDialogFragment) findFragmentByTag;
            if (progressDialogFragment != null) {
                progressDialogFragment.dismiss();
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.support.v4.app.FragmentActivity");
    }
}
