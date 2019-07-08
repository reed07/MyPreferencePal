package com.myfitnesspal.feature.consents.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.mopub.common.Constants;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.adapter.ConsentsAdapter;
import com.myfitnesspal.feature.consents.model.ConsentsViewModel;
import com.myfitnesspal.feature.consents.model.ConsentsViewModel.Mode;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelper;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelperImpl;
import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountAnalyticsHelper;
import com.myfitnesspal.feature.gdprhelp.activity.GDPRHelpActivity;
import com.myfitnesspal.feature.gdprhelp.util.GDPRHelpAnalyticsHelper;
import com.myfitnesspal.feature.registration.exception.RegistrationError;
import com.myfitnesspal.feature.registration.exception.RegistrationException;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper;
import com.myfitnesspal.feature.registration.ui.view.PageIndicatorBar;
import com.myfitnesspal.feature.registration.util.SignUpUtil;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.DividerItemDecorator;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import io.uacf.consentservices.sdk.UacfConsent;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 k2\u00020\u0001:\u0001kB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010B\u001a\u00020\u0007H\u0016J\b\u0010C\u001a\u00020\bH\u0002J\b\u0010D\u001a\u00020\bH\u0002J\b\u0010E\u001a\u00020\bH\u0002J\b\u0010F\u001a\u00020\bH\u0002J\b\u0010G\u001a\u00020\bH\u0002J\b\u0010H\u001a\u00020\bH\u0016J\u0012\u0010I\u001a\u00020\b2\b\u0010J\u001a\u0004\u0018\u00010KH\u0014J\u0012\u0010L\u001a\u00020\u00072\b\u0010M\u001a\u0004\u0018\u00010NH\u0016J\u0010\u0010O\u001a\u00020\u00072\u0006\u0010P\u001a\u00020QH\u0016J\u0018\u0010R\u001a\u00020\u00072\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0016J$\u0010W\u001a\u00020\b2\u0006\u0010X\u001a\u00020Y2\u0006\u0010\"\u001a\u00020#2\n\u0010Z\u001a\u0006\u0012\u0002\b\u00030[H\u0002J$\u0010\\\u001a\u00020\b2\u001a\u0010]\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00070_\u0018\u00010^H\u0002J\u0010\u0010`\u001a\u00020\b2\u0006\u0010a\u001a\u00020bH\u0002J\u0012\u0010c\u001a\u00020\b2\b\b\u0001\u0010d\u001a\u00020eH\u0002J\b\u0010f\u001a\u00020\bH\u0002J\u0010\u0010g\u001a\u00020\b2\u0006\u0010h\u001a\u00020eH\u0002J\b\u0010i\u001a\u00020\bH\u0002J\b\u0010j\u001a\u00020\bH\u0002R&\u0010\u0003\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\f8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\f8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R \u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u001bX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u00020\u001d8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020#X.¢\u0006\u0002\n\u0000R\u001e\u0010$\u001a\u00020%8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001e\u0010*\u001a\u00020+8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u000e\u00100\u001a\u000201X\u0004¢\u0006\u0002\n\u0000R$\u00102\u001a\b\u0012\u0004\u0012\u0002030\f8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u000f\"\u0004\b5\u0010\u0011R\u001a\u00106\u001a\u000207X.¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001e\u0010<\u001a\u00020=8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010A¨\u0006l"}, d2 = {"Lcom/myfitnesspal/feature/consents/ui/activity/ConsentsActivity;", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "()V", "checkboxAction", "Lkotlin/Function3;", "Lio/uacf/consentservices/sdk/UacfConsent;", "Landroid/view/View;", "", "", "consentsAdapter", "Lcom/myfitnesspal/feature/consents/adapter/ConsentsAdapter;", "consentsAnalyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/consents/util/ConsentsAnalyticsHelper;", "getConsentsAnalyticsHelper", "()Ldagger/Lazy;", "setConsentsAnalyticsHelper", "(Ldagger/Lazy;)V", "deleteAccountAnalyticsHelper", "Lcom/myfitnesspal/feature/deleteaccount/service/DeleteAccountAnalyticsHelper;", "getDeleteAccountAnalyticsHelper", "setDeleteAccountAnalyticsHelper", "gdprHelpAnalyticsHelper", "Lcom/myfitnesspal/feature/gdprhelp/util/GDPRHelpAnalyticsHelper;", "getGdprHelpAnalyticsHelper", "setGdprHelpAnalyticsHelper", "learnMoreAction", "Lkotlin/Function2;", "loginModel", "Lcom/myfitnesspal/feature/registration/model/LoginModel;", "getLoginModel", "()Lcom/myfitnesspal/feature/registration/model/LoginModel;", "setLoginModel", "(Lcom/myfitnesspal/feature/registration/model/LoginModel;)V", "recyclerView", "Landroid/support/v7/widget/RecyclerView;", "signUpModel", "Lcom/myfitnesspal/feature/registration/model/SignUpModel;", "getSignUpModel", "()Lcom/myfitnesspal/feature/registration/model/SignUpModel;", "setSignUpModel", "(Lcom/myfitnesspal/feature/registration/model/SignUpModel;)V", "signUpService", "Lcom/myfitnesspal/feature/registration/service/SignUpService;", "getSignUpService", "()Lcom/myfitnesspal/feature/registration/service/SignUpService;", "setSignUpService", "(Lcom/myfitnesspal/feature/registration/service/SignUpService;)V", "uaAffiliatesAction", "Landroid/view/View$OnClickListener;", "updatedTermsAnalyticsHelper", "Lcom/myfitnesspal/feature/registration/service/UpdatedTermsAnalyticsHelper;", "getUpdatedTermsAnalyticsHelper", "setUpdatedTermsAnalyticsHelper", "viewModel", "Lcom/myfitnesspal/feature/consents/model/ConsentsViewModel;", "getViewModel", "()Lcom/myfitnesspal/feature/consents/model/ConsentsViewModel;", "setViewModel", "(Lcom/myfitnesspal/feature/consents/model/ConsentsViewModel;)V", "vmFactory", "Landroid/arch/lifecycle/ViewModelProvider$Factory;", "getVmFactory", "()Landroid/arch/lifecycle/ViewModelProvider$Factory;", "setVmFactory", "(Landroid/arch/lifecycle/ViewModelProvider$Factory;)V", "backPressed", "continueConsented", "disableContinueShowLoading", "enableContinueHideLoading", "hideProgressDialog", "initUi", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "onRebindDialogFragment", "dialog", "Landroid/support/v4/app/DialogFragment;", "tag", "", "postAndNotifyAdapter", "handler", "Landroid/os/Handler;", "adapter", "Landroid/support/v7/widget/RecyclerView$Adapter;", "setConsents", "consents", "", "Lkotlin/Pair;", "showErrorFromThrowable", "throwable", "", "showLoadError", "message", "", "showLoading", "showProgressDialog", "stringId", "showShouldAcceptAllDialog", "validateAcceptance", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ConsentsActivity.kt */
public final class ConsentsActivity extends MfpActivity {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String ERROR_DIALOG_FRAGMENT = "error_dialog_fragment";
    @NotNull
    public static final String EXTRA_ISO = "extra_iso";
    @NotNull
    public static final String EXTRA_MODE = "extra_mode";
    @NotNull
    public static final String EXTRA_PASSWORD = "extra_password";
    public static final int MENU_HELP_ACTION = 1212;
    @NotNull
    public static final String MUST_ACCEPT_DIALOG_FRAGMENT = "must_accept_dialog_fragment";
    @NotNull
    public static final String MUST_DELETE_DIALOG_FRAGMENT = "must_delete_dialog_fragment";
    @NotNull
    public static final String PROGRESS_DIALOG_TAG = "progress_dialog";
    private static final String SIGN_UP_ERROR_CODE = "sign_up_error_code";
    private static final String SIGN_UP_ERROR_STRING = "sign_up_error_string";
    private HashMap _$_findViewCache;
    private final Function3<UacfConsent, View, Boolean, Unit> checkboxAction = new ConsentsActivity$checkboxAction$1(this);
    private ConsentsAdapter consentsAdapter;
    @Inject
    @NotNull
    public Lazy<ConsentsAnalyticsHelper> consentsAnalyticsHelper;
    @Inject
    @NotNull
    public Lazy<DeleteAccountAnalyticsHelper> deleteAccountAnalyticsHelper;
    @Inject
    @NotNull
    public Lazy<GDPRHelpAnalyticsHelper> gdprHelpAnalyticsHelper;
    private final Function2<UacfConsent, View, Unit> learnMoreAction = new ConsentsActivity$learnMoreAction$1(this);
    @Inject
    @NotNull
    public LoginModel loginModel;
    private RecyclerView recyclerView;
    @Inject
    @NotNull
    public SignUpModel signUpModel;
    @Inject
    @NotNull
    public SignUpService signUpService;
    private final OnClickListener uaAffiliatesAction = new ConsentsActivity$uaAffiliatesAction$1(this);
    @Inject
    @NotNull
    public Lazy<UpdatedTermsAnalyticsHelper> updatedTermsAnalyticsHelper;
    @NotNull
    public ConsentsViewModel viewModel;
    @Inject
    @NotNull
    public Factory vmFactory;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/myfitnesspal/feature/consents/ui/activity/ConsentsActivity$Companion;", "", "()V", "ERROR_DIALOG_FRAGMENT", "", "EXTRA_ISO", "EXTRA_MODE", "EXTRA_PASSWORD", "MENU_HELP_ACTION", "", "MUST_ACCEPT_DIALOG_FRAGMENT", "MUST_DELETE_DIALOG_FRAGMENT", "PROGRESS_DIALOG_TAG", "SIGN_UP_ERROR_CODE", "SIGN_UP_ERROR_STRING", "newStartIntent", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "mode", "Lcom/myfitnesspal/feature/consents/model/ConsentsViewModel$Mode;", "iso", "password", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ConsentsActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ Intent newStartIntent$default(Companion companion, Context context, Mode mode, String str, String str2, int i, Object obj) {
            if ((i & 4) != 0) {
                str = "";
            }
            if ((i & 8) != 0) {
                str2 = "";
            }
            return companion.newStartIntent(context, mode, str, str2);
        }

        @JvmStatic
        public final Intent newStartIntent(@NotNull Context context, @NotNull Mode mode, @NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(mode, InternalAvidAdSessionContext.CONTEXT_MODE);
            Intrinsics.checkParameterIsNotNull(str, "iso");
            Intrinsics.checkParameterIsNotNull(str2, "password");
            return new Intent(context, ConsentsActivity.class).putExtra(ConsentsActivity.EXTRA_MODE, mode).putExtra(ConsentsActivity.EXTRA_ISO, str).putExtra("extra_password", str2);
        }
    }

    @JvmStatic
    public static final Intent newStartIntent(@NotNull Context context, @NotNull Mode mode, @NotNull String str, @NotNull String str2) {
        return Companion.newStartIntent(context, mode, str, str2);
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
    public final SignUpModel getSignUpModel() {
        SignUpModel signUpModel2 = this.signUpModel;
        if (signUpModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("signUpModel");
        }
        return signUpModel2;
    }

    public final void setSignUpModel(@NotNull SignUpModel signUpModel2) {
        Intrinsics.checkParameterIsNotNull(signUpModel2, "<set-?>");
        this.signUpModel = signUpModel2;
    }

    @NotNull
    public final LoginModel getLoginModel() {
        LoginModel loginModel2 = this.loginModel;
        if (loginModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loginModel");
        }
        return loginModel2;
    }

    public final void setLoginModel(@NotNull LoginModel loginModel2) {
        Intrinsics.checkParameterIsNotNull(loginModel2, "<set-?>");
        this.loginModel = loginModel2;
    }

    @NotNull
    public final SignUpService getSignUpService() {
        SignUpService signUpService2 = this.signUpService;
        if (signUpService2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("signUpService");
        }
        return signUpService2;
    }

    public final void setSignUpService(@NotNull SignUpService signUpService2) {
        Intrinsics.checkParameterIsNotNull(signUpService2, "<set-?>");
        this.signUpService = signUpService2;
    }

    @NotNull
    public final ConsentsViewModel getViewModel() {
        ConsentsViewModel consentsViewModel = this.viewModel;
        if (consentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return consentsViewModel;
    }

    public final void setViewModel(@NotNull ConsentsViewModel consentsViewModel) {
        Intrinsics.checkParameterIsNotNull(consentsViewModel, "<set-?>");
        this.viewModel = consentsViewModel;
    }

    @NotNull
    public final Lazy<DeleteAccountAnalyticsHelper> getDeleteAccountAnalyticsHelper() {
        Lazy<DeleteAccountAnalyticsHelper> lazy = this.deleteAccountAnalyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deleteAccountAnalyticsHelper");
        }
        return lazy;
    }

    public final void setDeleteAccountAnalyticsHelper(@NotNull Lazy<DeleteAccountAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.deleteAccountAnalyticsHelper = lazy;
    }

    @NotNull
    public final Lazy<GDPRHelpAnalyticsHelper> getGdprHelpAnalyticsHelper() {
        Lazy<GDPRHelpAnalyticsHelper> lazy = this.gdprHelpAnalyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gdprHelpAnalyticsHelper");
        }
        return lazy;
    }

    public final void setGdprHelpAnalyticsHelper(@NotNull Lazy<GDPRHelpAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.gdprHelpAnalyticsHelper = lazy;
    }

    @NotNull
    public final Lazy<ConsentsAnalyticsHelper> getConsentsAnalyticsHelper() {
        Lazy<ConsentsAnalyticsHelper> lazy = this.consentsAnalyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("consentsAnalyticsHelper");
        }
        return lazy;
    }

    public final void setConsentsAnalyticsHelper(@NotNull Lazy<ConsentsAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.consentsAnalyticsHelper = lazy;
    }

    @NotNull
    public final Lazy<UpdatedTermsAnalyticsHelper> getUpdatedTermsAnalyticsHelper() {
        Lazy<UpdatedTermsAnalyticsHelper> lazy = this.updatedTermsAnalyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updatedTermsAnalyticsHelper");
        }
        return lazy;
    }

    public final void setUpdatedTermsAnalyticsHelper(@NotNull Lazy<UpdatedTermsAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.updatedTermsAnalyticsHelper = lazy;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.consents);
        FragmentActivity fragmentActivity = this;
        Factory factory = this.vmFactory;
        if (factory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
        }
        ViewModel viewModel2 = ViewModelProviders.of(fragmentActivity, factory).get(ConsentsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel2, "ViewModelProviders.of(th…ntsViewModel::class.java)");
        this.viewModel = (ConsentsViewModel) viewModel2;
        ConsentsViewModel consentsViewModel = this.viewModel;
        if (consentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, Constants.INTENT_SCHEME);
        SignUpModel signUpModel2 = this.signUpModel;
        if (signUpModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("signUpModel");
        }
        LoginModel loginModel2 = this.loginModel;
        if (loginModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loginModel");
        }
        SignUpService signUpService2 = this.signUpService;
        if (signUpService2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("signUpService");
        }
        consentsViewModel.load(intent, signUpModel2, loginModel2, signUpService2);
        initUi();
    }

    public boolean onRebindDialogFragment(@NotNull DialogFragment dialogFragment, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(dialogFragment, "dialog");
        Intrinsics.checkParameterIsNotNull(str, "tag");
        int hashCode = str.hashCode();
        if (hashCode != 70702374) {
            if (hashCode == 1750310704 && str.equals("error_dialog_fragment")) {
                AlertDialogFragment alertDialogFragment = (AlertDialogFragment) dialogFragment;
                return true;
            }
        } else if (str.equals(MUST_ACCEPT_DIALOG_FRAGMENT)) {
            AlertDialogFragment alertDialogFragment2 = (AlertDialogFragment) dialogFragment;
            return true;
        }
        return false;
    }

    public void onBackPressed() {
        ConsentsViewModel consentsViewModel = this.viewModel;
        if (consentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (!consentsViewModel.isExistingAddMode()) {
            super.onBackPressed();
        }
    }

    private final void initUi() {
        ConsentsViewModel consentsViewModel = this.viewModel;
        if (consentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (consentsViewModel.isExistingAddMode()) {
            Activity activity = getActivity();
            if (activity != null) {
                Toolbar toolbar = ((MfpActivity) activity).getToolbar();
                Intrinsics.checkExpressionValueIsNotNull(toolbar, "(activity as MfpActivity).toolbar");
                toolbar.setNavigationIcon((Drawable) null);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.shared.ui.activity.MfpActivity");
            }
        }
        List emptyList = CollectionsKt.emptyList();
        Function3<UacfConsent, View, Boolean, Unit> function3 = this.checkboxAction;
        Function2<UacfConsent, View, Unit> function2 = this.learnMoreAction;
        OnClickListener onClickListener = this.uaAffiliatesAction;
        ConsentsViewModel consentsViewModel2 = this.viewModel;
        if (consentsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        boolean isEditMode = consentsViewModel2.isEditMode();
        Lazy<UpdatedTermsAnalyticsHelper> lazy = this.updatedTermsAnalyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updatedTermsAnalyticsHelper");
        }
        ConsentsAdapter consentsAdapter2 = new ConsentsAdapter(emptyList, function3, function2, onClickListener, isEditMode, lazy);
        this.consentsAdapter = consentsAdapter2;
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.consents_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "consents_recycler_view");
        this.recyclerView = recyclerView2;
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        Context context = this;
        recyclerView3.setLayoutManager(new LinearLayoutManager(context));
        RecyclerView recyclerView4 = this.recyclerView;
        if (recyclerView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        recyclerView4.setHasFixedSize(true);
        RecyclerView recyclerView5 = this.recyclerView;
        if (recyclerView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        recyclerView5.addItemDecoration(new DividerItemDecorator(context));
        RecyclerView recyclerView6 = this.recyclerView;
        if (recyclerView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        ConsentsAdapter consentsAdapter3 = this.consentsAdapter;
        if (consentsAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("consentsAdapter");
        }
        recyclerView6.setAdapter(consentsAdapter3);
        ConsentsViewModel consentsViewModel3 = this.viewModel;
        if (consentsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        LifecycleOwner lifecycleOwner = this;
        consentsViewModel3.getConsent().observe(lifecycleOwner, new ConsentsActivity$initUi$1(this));
        ConsentsViewModel consentsViewModel4 = this.viewModel;
        if (consentsViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        consentsViewModel4.isSingleConsent().observe(lifecycleOwner, new ConsentsActivity$initUi$2(this));
        TextView textView = (TextView) _$_findCachedViewById(R.id.terms_pp_text);
        NavigationHelper navigationHelper = getNavigationHelper();
        Lazy<UpdatedTermsAnalyticsHelper> lazy2 = this.updatedTermsAnalyticsHelper;
        if (lazy2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updatedTermsAnalyticsHelper");
        }
        Lazy<ConsentsAnalyticsHelper> lazy3 = this.consentsAnalyticsHelper;
        if (lazy3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("consentsAnalyticsHelper");
        }
        ConsentsAnalyticsHelper consentsAnalyticsHelper2 = (ConsentsAnalyticsHelper) lazy3.get();
        ConsentsViewModel consentsViewModel5 = this.viewModel;
        if (consentsViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        SignUpUtil.setupDisclaimerTextForGDPR(textView, navigationHelper, R.string.terms_pp_text, lazy2, consentsAnalyticsHelper2.getScreenNameBasedOnMode(consentsViewModel5.getMode()));
        ((Button) _$_findCachedViewById(R.id.continue_btn)).setOnClickListener(new ConsentsActivity$initUi$3(this));
        ((CheckBox) _$_findCachedViewById(R.id.accept_all)).setOnCheckedChangeListener(new ConsentsActivity$initUi$4(this));
        ((PageIndicatorBar) _$_findCachedViewById(R.id.page_indicator)).setProgressAndMax(7, 7);
        PageIndicatorBar pageIndicatorBar = (PageIndicatorBar) _$_findCachedViewById(R.id.page_indicator);
        Intrinsics.checkExpressionValueIsNotNull(pageIndicatorBar, "page_indicator");
        ConsentsViewModel consentsViewModel6 = this.viewModel;
        if (consentsViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        pageIndicatorBar.setVisibility(consentsViewModel6.isOnBoarding() ? 0 : 8);
        ConsentsViewModel consentsViewModel7 = this.viewModel;
        if (consentsViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (consentsViewModel7.isEditMode()) {
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.consents_title);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "consents_title");
            textView2.setText(getString(R.string.consent_title_edit));
            CardView cardView = (CardView) _$_findCachedViewById(R.id.continue_btn_container);
            Intrinsics.checkExpressionValueIsNotNull(cardView, "continue_btn_container");
            cardView.setVisibility(8);
            CheckBox checkBox = (CheckBox) _$_findCachedViewById(R.id.accept_all);
            Intrinsics.checkExpressionValueIsNotNull(checkBox, "accept_all");
            checkBox.setVisibility(8);
            return;
        }
        TextView textView3 = (TextView) _$_findCachedViewById(R.id.consents_title);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "consents_title");
        textView3.setText(getString(R.string.consent_title));
        CardView cardView2 = (CardView) _$_findCachedViewById(R.id.continue_btn_container);
        Intrinsics.checkExpressionValueIsNotNull(cardView2, "continue_btn_container");
        cardView2.setVisibility(0);
        CheckBox checkBox2 = (CheckBox) _$_findCachedViewById(R.id.accept_all);
        Intrinsics.checkExpressionValueIsNotNull(checkBox2, "accept_all");
        checkBox2.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public final void setConsents(List<? extends Pair<? extends UacfConsent, Boolean>> list) {
        if (list == null || !(!list.isEmpty())) {
            ConsentsViewModel consentsViewModel = this.viewModel;
            if (consentsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            showLoadError(consentsViewModel.isOnBoarding() ? R.string.gdpr_offline_error : R.string.unknown_error);
            return;
        }
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.listProgressBar);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "listProgressBar");
        progressBar.setVisibility(8);
        CheckBox checkBox = (CheckBox) _$_findCachedViewById(R.id.accept_all);
        checkBox.setOnCheckedChangeListener(null);
        ConsentsViewModel consentsViewModel2 = this.viewModel;
        if (consentsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        checkBox.setChecked(consentsViewModel2.isAllAccepted());
        checkBox.setOnCheckedChangeListener(new ConsentsActivity$setConsents$$inlined$with$lambda$1(this));
        ConsentsAdapter consentsAdapter2 = this.consentsAdapter;
        if (consentsAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("consentsAdapter");
        }
        consentsAdapter2.setConsents(list);
        Handler handler = new Handler();
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        Adapter adapter = recyclerView3.getAdapter();
        if (adapter == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(adapter, "recyclerView.adapter!!");
        postAndNotifyAdapter(handler, recyclerView2, adapter);
        setBusy(false);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0061, code lost:
        if (r0.isExistingAddMode() != false) goto L_0x0063;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void validateAcceptance() {
        /*
            r3 = this;
            com.myfitnesspal.feature.consents.model.ConsentsViewModel r0 = r3.viewModel
            if (r0 != 0) goto L_0x0009
            java.lang.String r1 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0009:
            boolean r0 = r0.isAllAccepted()
            if (r0 == 0) goto L_0x008a
            com.myfitnesspal.feature.consents.model.ConsentsViewModel r0 = r3.viewModel
            if (r0 != 0) goto L_0x0018
            java.lang.String r1 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0018:
            boolean r0 = r0.isOnBoarding()
            if (r0 == 0) goto L_0x0045
            com.myfitnesspal.feature.consents.model.ConsentsViewModel r0 = r3.viewModel
            if (r0 != 0) goto L_0x0027
            java.lang.String r1 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0027:
            com.myfitnesspal.shared.ui.component.SingleLiveEvent r0 = r0.getSingUpStatus()
            r1 = r3
            android.arch.lifecycle.LifecycleOwner r1 = (android.arch.lifecycle.LifecycleOwner) r1
            com.myfitnesspal.feature.consents.ui.activity.ConsentsActivity$validateAcceptance$1 r2 = new com.myfitnesspal.feature.consents.ui.activity.ConsentsActivity$validateAcceptance$1
            r2.<init>(r3)
            android.arch.lifecycle.Observer r2 = (android.arch.lifecycle.Observer) r2
            r0.observe(r1, r2)
            com.myfitnesspal.feature.consents.model.ConsentsViewModel r0 = r3.viewModel
            if (r0 != 0) goto L_0x0041
            java.lang.String r1 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0041:
            r0.finishSignUpFlow()
            goto L_0x007d
        L_0x0045:
            com.myfitnesspal.feature.consents.model.ConsentsViewModel r0 = r3.viewModel
            if (r0 != 0) goto L_0x004e
            java.lang.String r1 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x004e:
            boolean r0 = r0.isEditCountryMode()
            if (r0 != 0) goto L_0x0063
            com.myfitnesspal.feature.consents.model.ConsentsViewModel r0 = r3.viewModel
            if (r0 != 0) goto L_0x005d
            java.lang.String r1 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x005d:
            boolean r0 = r0.isExistingAddMode()
            if (r0 == 0) goto L_0x007d
        L_0x0063:
            com.myfitnesspal.feature.consents.model.ConsentsViewModel r0 = r3.viewModel
            if (r0 != 0) goto L_0x006c
            java.lang.String r1 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x006c:
            com.myfitnesspal.shared.ui.component.SingleLiveEvent r0 = r0.getUpdateConsentsStatus()
            r1 = r3
            android.arch.lifecycle.LifecycleOwner r1 = (android.arch.lifecycle.LifecycleOwner) r1
            com.myfitnesspal.feature.consents.ui.activity.ConsentsActivity$validateAcceptance$2 r2 = new com.myfitnesspal.feature.consents.ui.activity.ConsentsActivity$validateAcceptance$2
            r2.<init>(r3)
            android.arch.lifecycle.Observer r2 = (android.arch.lifecycle.Observer) r2
            r0.observe(r1, r2)
        L_0x007d:
            com.myfitnesspal.feature.consents.model.ConsentsViewModel r0 = r3.viewModel
            if (r0 != 0) goto L_0x0086
            java.lang.String r1 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0086:
            r0.updatedConsentsFlow()
            goto L_0x008d
        L_0x008a:
            r3.showShouldAcceptAllDialog()
        L_0x008d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.consents.ui.activity.ConsentsActivity.validateAcceptance():void");
    }

    /* access modifiers changed from: private */
    public final void showLoadError(@SuppressLint({"SupportAnnotationUsage"}) @StringRes int i) {
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.listProgressBar);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "listProgressBar");
        progressBar.setVisibility(8);
        setBusy(false);
        showDialogFragment((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setMessage(i)).setTitle(R.string.error)).setPositiveText(R.string.ok, null), "error_dialog_fragment");
    }

    /* access modifiers changed from: private */
    public final void showLoading() {
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.listProgressBar);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "listProgressBar");
        progressBar.setVisibility(0);
        setBusy(true);
    }

    private final void showShouldAcceptAllDialog() {
        int i;
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        ConsentsViewModel consentsViewModel = this.viewModel;
        if (consentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        switch (consentsViewModel.getMode()) {
            case NEW:
                i = R.string.must_accept_consents;
                break;
            case EXISTING_EDIT_COUNTRY:
                i = R.string.location_change_must_accept_consents;
                break;
            default:
                i = R.string.existing_must_accept_consents;
                break;
        }
        showDialogFragment((AlertDialogFragment) ((AlertDialogFragment) alertDialogFragment.setMessage(i)).setPositiveText(R.string.ok, null), MUST_ACCEPT_DIALOG_FRAGMENT);
        ConsentsViewModel consentsViewModel2 = this.viewModel;
        if (consentsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (consentsViewModel2.isOnBoarding()) {
            Lazy<ConsentsAnalyticsHelper> lazy = this.consentsAnalyticsHelper;
            if (lazy == null) {
                Intrinsics.throwUninitializedPropertyAccessException("consentsAnalyticsHelper");
            }
            ((ConsentsAnalyticsHelper) lazy.get()).reportOnBoardingConsentNotChecked();
        }
        ConsentsViewModel consentsViewModel3 = this.viewModel;
        if (consentsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (consentsViewModel3.isExistingAddMode()) {
            Lazy<ConsentsAnalyticsHelper> lazy2 = this.consentsAnalyticsHelper;
            if (lazy2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("consentsAnalyticsHelper");
            }
            ((ConsentsAnalyticsHelper) lazy2.get()).reportConsentInterruptionNotChecked();
        }
    }

    public boolean onPrepareOptionsMenu(@NotNull Menu menu) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        ConsentsViewModel consentsViewModel = this.viewModel;
        if (consentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (!consentsViewModel.isExistingAddMode()) {
            return super.onPrepareOptionsMenu(menu);
        }
        menu.clear();
        menu.add(0, MENU_HELP_ACTION, 0, R.string.menu_help).setEnabled(true).setIcon(R.drawable.ic_help_white).setShowAsAction(2);
        return true;
    }

    public boolean onOptionsItemSelected(@Nullable MenuItem menuItem) {
        Integer valueOf = menuItem != null ? Integer.valueOf(menuItem.getItemId()) : null;
        if (valueOf != null && valueOf.intValue() == 16908332) {
            onBackPressed();
            return true;
        } else if (valueOf == null || valueOf.intValue() != 1212) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            Lazy<GDPRHelpAnalyticsHelper> lazy = this.gdprHelpAnalyticsHelper;
            if (lazy == null) {
                Intrinsics.throwUninitializedPropertyAccessException("gdprHelpAnalyticsHelper");
            }
            ((GDPRHelpAnalyticsHelper) lazy.get()).reportHelpSee(ConsentsAnalyticsHelperImpl.CONSENT_INTERRUPTION_SEE);
            getNavigationHelper().withIntent(GDPRHelpActivity.Companion.newStartIntent(this, ConsentsAnalyticsHelperImpl.CONSENT_INTERRUPTION_SEE)).startActivity();
            return true;
        }
    }

    public boolean backPressed() {
        ConsentsViewModel consentsViewModel = this.viewModel;
        if (consentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return consentsViewModel.getBackPressStatus();
    }

    /* access modifiers changed from: private */
    public final void postAndNotifyAdapter(Handler handler, RecyclerView recyclerView2, Adapter<?> adapter) {
        handler.post(new ConsentsActivity$postAndNotifyAdapter$1(this, recyclerView2, adapter, handler));
    }

    /* access modifiers changed from: private */
    public final void disableContinueShowLoading() {
        showProgressDialog(R.string.please_wait);
        Button button = (Button) _$_findCachedViewById(R.id.continue_btn);
        Intrinsics.checkExpressionValueIsNotNull(button, "continue_btn");
        button.setEnabled(false);
    }

    /* access modifiers changed from: private */
    public final void enableContinueHideLoading() {
        hideProgressDialog();
        Button button = (Button) _$_findCachedViewById(R.id.continue_btn);
        Intrinsics.checkExpressionValueIsNotNull(button, "continue_btn");
        button.setEnabled(true);
    }

    /* access modifiers changed from: private */
    public final void showErrorFromThrowable(Throwable th) {
        RegistrationException registrationException = (RegistrationException) (!(th instanceof RegistrationException) ? null : th);
        int i = R.string.unknown_error;
        if (registrationException != null) {
            RegistrationException registrationException2 = (RegistrationException) th;
            RegistrationError reason = registrationException2.getReason();
            if (reason != null) {
                switch (reason) {
                    case SyncFailure:
                        i = R.string.unable_to_create_account;
                        break;
                    case DeviceOffline:
                        i = R.string.offline_msg;
                        break;
                    case DatabaseError:
                    case InvalidToken:
                    case NoVerticalAccount:
                        i = R.string.error_during_registration;
                        break;
                }
            }
            getAnalyticsService().reportEvent(Events.SIGN_UP_FAILED, MapUtil.createMap(SIGN_UP_ERROR_STRING, getString(i), SIGN_UP_ERROR_CODE, Strings.toString(registrationException2.getReason())));
        }
        showLoadError(i);
    }

    /* access modifiers changed from: private */
    public final void continueConsented() {
        setBusy(false);
        setResult(-1, new Intent());
        finish();
    }

    private final void showProgressDialog(int i) {
        Activity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.shared.ui.activity.MfpActivity");
        } else if (((MfpActivity) activity).getSupportFragmentManager().findFragmentByTag(PROGRESS_DIALOG_TAG) == null) {
            showDialogFragment(ProgressDialogFragment.newInstance(i), PROGRESS_DIALOG_TAG);
        }
    }

    private final void hideProgressDialog() {
        Activity activity = getActivity();
        if (activity != null) {
            Fragment findFragmentByTag = ((FragmentActivity) activity).getSupportFragmentManager().findFragmentByTag(PROGRESS_DIALOG_TAG);
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
