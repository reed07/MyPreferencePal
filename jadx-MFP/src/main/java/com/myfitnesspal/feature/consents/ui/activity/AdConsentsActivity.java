package com.myfitnesspal.feature.consents.ui.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.adapter.AdConsentsAdapter;
import com.myfitnesspal.feature.consents.model.AdConsentsViewModel;
import com.myfitnesspal.feature.consents.model.AdConsentsViewModel.Mode;
import com.myfitnesspal.feature.consents.util.AdConsents;
import com.myfitnesspal.feature.consents.util.AdConsentsAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.registration.util.SignUpUtil;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import dagger.Lazy;
import io.uacf.consentservices.sdk.UacfConsent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 C2\u00020\u0001:\u0001CB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010(\u001a\u00020\u0011H\u0002J\b\u0010)\u001a\u00020\u0011H\u0002J\u0018\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000fH\u0002J\b\u0010-\u001a\u00020\u0011H\u0002J*\u0010.\u001a\u0010\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u000200\u0018\u00010/2\b\b\u0002\u0010+\u001a\u00020\u000e2\b\b\u0002\u00101\u001a\u00020\u0010H\u0002J\b\u00102\u001a\u00020\u0011H\u0002J\b\u00103\u001a\u00020\u0011H\u0002J\b\u00104\u001a\u00020\u0011H\u0002J\b\u00105\u001a\u00020\u0011H\u0002J\b\u00106\u001a\u00020\u0011H\u0016J\u0012\u00107\u001a\u00020\u00112\b\u00108\u001a\u0004\u0018\u000109H\u0014J\b\u0010:\u001a\u00020\u0011H\u0002J@\u0010;\u001a\u00020\u00112\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u0002000/2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020?0>2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010+\u001a\u00020\u000eH\u0002J\b\u0010@\u001a\u00020\u0011H\u0002J\b\u0010A\u001a\u00020\u0011H\u0002J\b\u0010B\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR&\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\rX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013X\u0004¢\u0006\u0002\n\u0000R$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\t\"\u0004\b\u0018\u0010\u000bR&\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u001dX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001e\u0010\"\u001a\u00020#8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006D"}, d2 = {"Lcom/myfitnesspal/feature/consents/ui/activity/AdConsentsActivity;", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "()V", "adConsentsAdapter", "Lcom/myfitnesspal/feature/consents/adapter/AdConsentsAdapter;", "adConsentsAnalyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/consents/util/AdConsentsAnalyticsHelper;", "getAdConsentsAnalyticsHelper", "()Ldagger/Lazy;", "setAdConsentsAnalyticsHelper", "(Ldagger/Lazy;)V", "interruptionCheckboxAction", "Lkotlin/Function3;", "", "Landroid/view/View;", "", "", "learnMoreAction", "Lkotlin/Function1;", "Lio/uacf/consentservices/sdk/UacfConsent;", "premiumService", "Lcom/myfitnesspal/feature/premium/service/PremiumService;", "getPremiumService", "setPremiumService", "settingsCheckboxAction", "uaAffiliatesAction", "Landroid/view/View$OnClickListener;", "viewModel", "Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel;", "getViewModel", "()Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel;", "setViewModel", "(Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel;)V", "vmFactory", "Landroid/arch/lifecycle/ViewModelProvider$Factory;", "getVmFactory", "()Landroid/arch/lifecycle/ViewModelProvider$Factory;", "setVmFactory", "(Landroid/arch/lifecycle/ViewModelProvider$Factory;)V", "askForConfirmationInterruption", "askForConfirmationRemindMeLater", "askForConfirmationSettings", "consentId", "itemView", "disableConsentInterruptionAndFinish", "getAlertStringAndTitle", "Lkotlin/Pair;", "", "isForRemindLater", "hideProgressDialog", "initDataObservers", "initListeners", "initUI", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "revertConsentCheckStatus", "showAlertDialog", "alertMessageAndTitle", "positiveListener", "Lcom/myfitnesspal/shared/ui/dialog/AlertDialogFragmentBase$DialogPositiveListener;", "", "showErrorAlert", "showProgressDialog", "updateButtonsVisibilityAndText", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AdConsentsActivity.kt */
public final class AdConsentsActivity extends MfpActivity {
    private static final String CONSENT_SKIP_ALERT_DIALOG = "consent_skip_alert_dialog";
    public static final Companion Companion = new Companion(null);
    private static final String ERROR_DIALOG_FRAGMENT = "consent_save_error_dialog";
    private static final String MODE = "extra_mode";
    private static final String PROGRESS_DIALOG_TAG = "progress_dialog_consent_save";
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public AdConsentsAdapter adConsentsAdapter;
    @Inject
    @NotNull
    public Lazy<AdConsentsAnalyticsHelper> adConsentsAnalyticsHelper;
    private final Function3<String, View, Boolean, Unit> interruptionCheckboxAction = new AdConsentsActivity$interruptionCheckboxAction$1(this);
    private final Function1<UacfConsent, Unit> learnMoreAction = new AdConsentsActivity$learnMoreAction$1(this);
    @Inject
    @NotNull
    public Lazy<PremiumService> premiumService;
    /* access modifiers changed from: private */
    public final Function3<String, View, Boolean, Unit> settingsCheckboxAction = new AdConsentsActivity$settingsCheckboxAction$1(this);
    private final OnClickListener uaAffiliatesAction = new AdConsentsActivity$uaAffiliatesAction$1(this);
    @NotNull
    public AdConsentsViewModel viewModel;
    @Inject
    @NotNull
    public Factory vmFactory;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/myfitnesspal/feature/consents/ui/activity/AdConsentsActivity$Companion;", "", "()V", "CONSENT_SKIP_ALERT_DIALOG", "", "ERROR_DIALOG_FRAGMENT", "MODE", "PROGRESS_DIALOG_TAG", "newStartIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "mode", "Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel$Mode;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AdConsentsActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final Intent newStartIntent(@NotNull Context context, @Nullable Mode mode) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intent putExtra = new Intent(context, AdConsentsActivity.class).putExtra("extra_mode", mode);
            Intrinsics.checkExpressionValueIsNotNull(putExtra, "Intent(context, AdConsen…    .putExtra(MODE, mode)");
            return putExtra;
        }
    }

    @JvmStatic
    @NotNull
    public static final Intent newStartIntent(@NotNull Context context, @Nullable Mode mode) {
        return Companion.newStartIntent(context, mode);
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
    public static final /* synthetic */ AdConsentsAdapter access$getAdConsentsAdapter$p(AdConsentsActivity adConsentsActivity) {
        AdConsentsAdapter adConsentsAdapter2 = adConsentsActivity.adConsentsAdapter;
        if (adConsentsAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adConsentsAdapter");
        }
        return adConsentsAdapter2;
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
    public final Lazy<PremiumService> getPremiumService() {
        Lazy<PremiumService> lazy = this.premiumService;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("premiumService");
        }
        return lazy;
    }

    public final void setPremiumService(@NotNull Lazy<PremiumService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.premiumService = lazy;
    }

    @NotNull
    public final Lazy<AdConsentsAnalyticsHelper> getAdConsentsAnalyticsHelper() {
        Lazy<AdConsentsAnalyticsHelper> lazy = this.adConsentsAnalyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adConsentsAnalyticsHelper");
        }
        return lazy;
    }

    public final void setAdConsentsAnalyticsHelper(@NotNull Lazy<AdConsentsAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.adConsentsAnalyticsHelper = lazy;
    }

    @NotNull
    public final AdConsentsViewModel getViewModel() {
        AdConsentsViewModel adConsentsViewModel = this.viewModel;
        if (adConsentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return adConsentsViewModel;
    }

    public final void setViewModel(@NotNull AdConsentsViewModel adConsentsViewModel) {
        Intrinsics.checkParameterIsNotNull(adConsentsViewModel, "<set-?>");
        this.viewModel = adConsentsViewModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.ad_consents);
        FragmentActivity fragmentActivity = this;
        Factory factory = this.vmFactory;
        if (factory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
        }
        ViewModel viewModel2 = ViewModelProviders.of(fragmentActivity, factory).get(AdConsentsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel2, "ViewModelProviders.of(th…ntsViewModel::class.java)");
        this.viewModel = (AdConsentsViewModel) viewModel2;
        AdConsentsViewModel adConsentsViewModel = this.viewModel;
        if (adConsentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        Serializable serializableExtra = getIntent().getSerializableExtra("extra_mode");
        if (!(serializableExtra instanceof Mode)) {
            serializableExtra = null;
        }
        Mode mode = (Mode) serializableExtra;
        Lazy<PremiumService> lazy = this.premiumService;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("premiumService");
        }
        Object obj = lazy.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "premiumService.get()");
        adConsentsViewModel.initialize(mode, ((PremiumService) obj).isPremiumSubscribed());
        initUI();
        initListeners();
        initDataObservers();
        AdConsentsViewModel adConsentsViewModel2 = this.viewModel;
        if (adConsentsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (adConsentsViewModel2.getConsentsList().isEmpty()) {
            AdConsentsViewModel adConsentsViewModel3 = this.viewModel;
            if (adConsentsViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            UserProfile profile = getSession().getUser().getProfile();
            Intrinsics.checkExpressionValueIsNotNull(profile, "session.user.profile");
            adConsentsViewModel3.fetchConsents(profile.getCountryName());
            ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.listProgressBar);
            Intrinsics.checkExpressionValueIsNotNull(progressBar, "listProgressBar");
            progressBar.setIndeterminate(true);
            ProgressBar progressBar2 = (ProgressBar) _$_findCachedViewById(R.id.listProgressBar);
            Intrinsics.checkExpressionValueIsNotNull(progressBar2, "listProgressBar");
            progressBar2.setVisibility(0);
        }
    }

    public void onBackPressed() {
        AdConsentsViewModel adConsentsViewModel = this.viewModel;
        if (adConsentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (adConsentsViewModel.getMode() == Mode.SETTINGS) {
            super.onBackPressed();
        } else {
            askForConfirmationRemindMeLater();
        }
    }

    private final void initUI() {
        Function3<String, View, Boolean, Unit> function3;
        Function3<String, View, Boolean, Unit> function32 = this.interruptionCheckboxAction;
        AdConsentsViewModel adConsentsViewModel = this.viewModel;
        if (adConsentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (adConsentsViewModel.getMode() == Mode.SETTINGS) {
            Function3<String, View, Boolean, Unit> function33 = this.settingsCheckboxAction;
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.personalized_consent_list);
            Intrinsics.checkExpressionValueIsNotNull(recyclerView, "personalized_consent_list");
            recyclerView.setBackground(null);
            function3 = function33;
        } else {
            function3 = function32;
        }
        SignUpUtil.setupDisclaimerText((TextView) _$_findCachedViewById(R.id.terms_pp_text), getNavigationHelper(), R.string.terms_pp_text);
        List emptyList = CollectionsKt.emptyList();
        OnClickListener onClickListener = this.uaAffiliatesAction;
        Function1<UacfConsent, Unit> function1 = this.learnMoreAction;
        AdConsentsViewModel adConsentsViewModel2 = this.viewModel;
        if (adConsentsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        AdConsentsAdapter adConsentsAdapter2 = new AdConsentsAdapter(emptyList, function3, onClickListener, function1, adConsentsViewModel2.getMode());
        this.adConsentsAdapter = adConsentsAdapter2;
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.personalized_consent_list);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "personalized_consent_list");
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        ((RecyclerView) _$_findCachedViewById(R.id.personalized_consent_list)).setHasFixedSize(true);
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(R.id.personalized_consent_list);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "personalized_consent_list");
        AdConsentsAdapter adConsentsAdapter3 = this.adConsentsAdapter;
        if (adConsentsAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adConsentsAdapter");
        }
        recyclerView3.setAdapter(adConsentsAdapter3);
    }

    private final void initListeners() {
        ((TextView) _$_findCachedViewById(R.id.remind_me_later)).setOnClickListener(new AdConsentsActivity$initListeners$1(this));
        ((Button) _$_findCachedViewById(R.id.accept_btn)).setOnClickListener(new AdConsentsActivity$initListeners$2(this));
    }

    private final void initDataObservers() {
        AdConsentsViewModel adConsentsViewModel = this.viewModel;
        if (adConsentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        LifecycleOwner lifecycleOwner = this;
        adConsentsViewModel.getConsentsToShowList().observe(lifecycleOwner, new AdConsentsActivity$initDataObservers$1(this));
        AdConsentsViewModel adConsentsViewModel2 = this.viewModel;
        if (adConsentsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        adConsentsViewModel2.getUpdateAdConsentsStatus().observe(lifecycleOwner, new AdConsentsActivity$initDataObservers$2(this));
    }

    /* access modifiers changed from: private */
    public final void updateButtonsVisibilityAndText() {
        AdConsentsViewModel adConsentsViewModel = this.viewModel;
        if (adConsentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (adConsentsViewModel.getMode() == Mode.INTERRUPTION) {
            Button button = (Button) _$_findCachedViewById(R.id.accept_btn);
            Intrinsics.checkExpressionValueIsNotNull(button, "accept_btn");
            button.setVisibility(0);
            TextView textView = (TextView) _$_findCachedViewById(R.id.remind_me_later);
            Intrinsics.checkExpressionValueIsNotNull(textView, "remind_me_later");
            textView.setVisibility(0);
            Button button2 = (Button) _$_findCachedViewById(R.id.accept_btn);
            AdConsentsViewModel adConsentsViewModel2 = this.viewModel;
            if (adConsentsViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            button2.setText(adConsentsViewModel2.getAcceptButtonMode().getStringId());
        }
    }

    /* access modifiers changed from: private */
    public final void askForConfirmationInterruption() {
        Pair alertStringAndTitle$default = getAlertStringAndTitle$default(this, null, false, 3, null);
        if (alertStringAndTitle$default != null) {
            showAlertDialog$default(this, alertStringAndTitle$default, new AdConsentsActivity$askForConfirmationInterruption$$inlined$let$lambda$1(this), null, null, 12, null);
            Lazy<AdConsentsAnalyticsHelper> lazy = this.adConsentsAnalyticsHelper;
            if (lazy == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adConsentsAnalyticsHelper");
            }
            ((AdConsentsAnalyticsHelper) lazy.get()).reportInterruptionSaveAlertDisplayed();
        }
    }

    /* access modifiers changed from: private */
    public final void askForConfirmationSettings(String str, View view) {
        Pair alertStringAndTitle$default = getAlertStringAndTitle$default(this, str, false, 2, null);
        if (alertStringAndTitle$default != null) {
            showAlertDialog(alertStringAndTitle$default, new AdConsentsActivity$askForConfirmationSettings$$inlined$let$lambda$1(this, str, view), view, str);
        }
    }

    /* access modifiers changed from: private */
    public final void askForConfirmationRemindMeLater() {
        Pair alertStringAndTitle$default = getAlertStringAndTitle$default(this, null, true, 1, null);
        if (alertStringAndTitle$default != null) {
            showAlertDialog$default(this, alertStringAndTitle$default, new AdConsentsActivity$askForConfirmationRemindMeLater$$inlined$let$lambda$1(this), null, null, 12, null);
            Lazy<AdConsentsAnalyticsHelper> lazy = this.adConsentsAnalyticsHelper;
            if (lazy == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adConsentsAnalyticsHelper");
            }
            ((AdConsentsAnalyticsHelper) lazy.get()).reportInterruptionSkipAlertDisplayed();
        }
    }

    /* access modifiers changed from: private */
    public final void disableConsentInterruptionAndFinish() {
        AdConsentsViewModel adConsentsViewModel = this.viewModel;
        if (adConsentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        adConsentsViewModel.setShouldInterruptUserForAdConsents(false);
        finish();
    }

    static /* synthetic */ void showAlertDialog$default(AdConsentsActivity adConsentsActivity, Pair pair, DialogPositiveListener dialogPositiveListener, View view, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            view = null;
        }
        if ((i & 8) != 0) {
            str = "";
        }
        adConsentsActivity.showAlertDialog(pair, dialogPositiveListener, view, str);
    }

    private final void showAlertDialog(Pair<Integer, Integer> pair, DialogPositiveListener<Object> dialogPositiveListener, View view, String str) {
        AlertDialogFragment newInstance = AlertDialogFragment.Companion.newInstance();
        AlertDialogFragmentBase negativeText = ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) newInstance.setMessage(((Number) pair.getFirst()).intValue())).setTitle(((Number) pair.getSecond()).intValue())).setPositiveText(R.string.continueBtn, dialogPositiveListener)).setNegativeText(R.string.go_back, new AdConsentsActivity$showAlertDialog$1(this, newInstance, view, str));
        Intrinsics.checkExpressionValueIsNotNull(negativeText, "alertDialog.setMessage(a…      }\n                }");
        ((AlertDialogFragment) negativeText).setCancelable(false);
        showDialogFragment(newInstance, CONSENT_SKIP_ALERT_DIALOG);
    }

    static /* synthetic */ Pair getAlertStringAndTitle$default(AdConsentsActivity adConsentsActivity, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return adConsentsActivity.getAlertStringAndTitle(str, z);
    }

    private final Pair<Integer, Integer> getAlertStringAndTitle(String str, boolean z) {
        Function1 adConsentsActivity$getAlertStringAndTitle$consentIdMatchesOrDidNotAccept$1 = new AdConsentsActivity$getAlertStringAndTitle$consentIdMatchesOrDidNotAccept$1(this, str);
        Function1 function1 = AdConsentsActivity$getAlertStringAndTitle$getPair$1.INSTANCE;
        AdConsentsViewModel adConsentsViewModel = this.viewModel;
        if (adConsentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (adConsentsViewModel.getConsentsList().size() > 1 && z) {
            return new Pair<>(Integer.valueOf(R.string.alert_skip_relevant_or_all_consent), Integer.valueOf(R.string.are_you_sure));
        }
        if (((Boolean) adConsentsActivity$getAlertStringAndTitle$consentIdMatchesOrDidNotAccept$1.invoke(AdConsents.PERSONALIZED_ADS.getId())).booleanValue()) {
            return (Pair) function1.invoke(AdConsents.PERSONALIZED_ADS);
        }
        if (((Boolean) adConsentsActivity$getAlertStringAndTitle$consentIdMatchesOrDidNotAccept$1.invoke(AdConsents.LOCALIZED_ADS.getId())).booleanValue()) {
            return (Pair) function1.invoke(AdConsents.LOCALIZED_ADS);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final void revertConsentCheckStatus() {
        AdConsentsViewModel adConsentsViewModel = this.viewModel;
        if (adConsentsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        AdConsentsViewModel adConsentsViewModel2 = this.viewModel;
        if (adConsentsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        String str = (String) adConsentsViewModel2.getUpdatedConsentInSettings().getFirst();
        AdConsentsViewModel adConsentsViewModel3 = this.viewModel;
        if (adConsentsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        adConsentsViewModel.setConsentChecked(str, !((Boolean) adConsentsViewModel3.getUpdatedConsentInSettings().getSecond()).booleanValue());
    }

    /* access modifiers changed from: private */
    public final void showProgressDialog() {
        if (getSupportFragmentManager().findFragmentByTag(PROGRESS_DIALOG_TAG) == null) {
            showDialogFragment(ProgressDialogFragment.newInstance(R.string.please_wait), PROGRESS_DIALOG_TAG);
        }
    }

    /* access modifiers changed from: private */
    public final void hideProgressDialog() {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(PROGRESS_DIALOG_TAG);
        if (!(findFragmentByTag instanceof ProgressDialogFragment)) {
            findFragmentByTag = null;
        }
        ProgressDialogFragment progressDialogFragment = (ProgressDialogFragment) findFragmentByTag;
        if (progressDialogFragment != null) {
            progressDialogFragment.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public final void showErrorAlert() {
        showDialogFragment((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setMessage((int) R.string.unknown_error)).setTitle(R.string.error)).setPositiveText(R.string.ok, null), ERROR_DIALOG_FRAGMENT);
    }
}
