package com.myfitnesspal.feature.settings.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.net.Uri;
import android.view.View.OnFocusChangeListener;
import com.mopub.common.Constants;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.model.Resource;
import com.myfitnesspal.feature.consents.model.Resource.Loading;
import com.myfitnesspal.feature.consents.model.Resource.Success;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.component.SingleLiveEvent;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.BundleUtils;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\t\u0018\u0000 M2\u00020\u0001:\u0001MBM\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0003¢\u0006\u0002\u0010\u000eJ\u0006\u00101\u001a\u000202J\u0006\u00103\u001a\u000204J\u000e\u00105\u001a\n 7*\u0004\u0018\u00010606J\u0006\u00108\u001a\u000204J!\u00109\u001a\n 7*\u0004\u0018\u00010:0:2\u0006\u0010;\u001a\u00020$H@ø\u0001\u0000¢\u0006\u0002\u0010<J\b\u0010=\u001a\u00020\u0010H\u0002J\b\u0010>\u001a\u00020\u0010H\u0002J\b\u0010?\u001a\u00020\u0010H\u0002J\u000e\u0010@\u001a\u0002022\u0006\u0010A\u001a\u00020BJ\u0010\u0010C\u001a\u0002022\b\u0010D\u001a\u0004\u0018\u00010EJ\u0006\u0010F\u001a\u000202J\b\u0010G\u001a\u000202H\u0014J\u0019\u0010H\u001a\u00020\u00102\u0006\u0010;\u001a\u00020$H@ø\u0001\u0000¢\u0006\u0002\u0010<J\b\u0010I\u001a\u000202H\u0002J\b\u0010J\u001a\u000202H\u0002J\u0010\u0010K\u001a\u0002022\b\u0010D\u001a\u0004\u0018\u00010EJ\b\u0010L\u001a\u000202H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR,\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0\u001f0\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0018\"\u0004\b\"\u0010\u001aR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u0016X\u000e¢\u0006\u0002\n\u0000R&\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100'0&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020$0\u0016X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006N"}, d2 = {"Lcom/myfitnesspal/feature/settings/model/ChangePasswordViewModel;", "Landroid/arch/lifecycle/ViewModel;", "countryService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/install/CountryService;", "api", "Ljavax/inject/Provider;", "Lcom/myfitnesspal/shared/api/v1/MfpInformationApi;", "authTokens", "Lcom/myfitnesspal/shared/api/auth/AuthTokenProvider;", "session", "Lcom/myfitnesspal/shared/service/session/Session;", "localSettingsService", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "(Ldagger/Lazy;Ljavax/inject/Provider;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;)V", "backPress", "", "getBackPress", "()Z", "setBackPress", "(Z)V", "enabledToChange", "Landroid/arch/lifecycle/MutableLiveData;", "getEnabledToChange", "()Landroid/arch/lifecycle/MutableLiveData;", "setEnabledToChange", "(Landroid/arch/lifecycle/MutableLiveData;)V", "forcedStatus", "getForcedStatus", "setForcedStatus", "inputError", "Lkotlin/Pair;", "", "getInputError", "setInputError", "newPassword", "", "passwordUpdateStatus", "Lcom/myfitnesspal/shared/ui/component/SingleLiveEvent;", "Lcom/myfitnesspal/feature/consents/model/Resource;", "getPasswordUpdateStatus", "()Lcom/myfitnesspal/shared/ui/component/SingleLiveEvent;", "setPasswordUpdateStatus", "(Lcom/myfitnesspal/shared/ui/component/SingleLiveEvent;)V", "retypePassword", "uiScope", "Lkotlinx/coroutines/CoroutineScope;", "viewModelJob", "Lkotlinx/coroutines/Job;", "changePassword", "", "getNewOnFocusChangeListener", "Landroid/view/View$OnFocusChangeListener;", "getPasswordNoticeUri", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "getRetypeOnFocusChangeListener", "grefreshTokensByRelogin", "", "password", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isNewPasswordValid", "isPasswordsMatch", "isPasswordsValid", "load", "intent", "Landroid/content/Intent;", "newPasswordTextChanged", "text", "", "onBackPressed", "onCleared", "patchUser", "retypePasswordMatch", "retypePasswordMissmatch", "retypePasswordTextChanged", "updateUserPassword", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ChangePasswordViewModel.kt */
public final class ChangePasswordViewModel extends ViewModel {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EXTRA_FORCED_PASSWORD_CHANGE = "extra_forced_password_change";
    /* access modifiers changed from: private */
    public final Provider<MfpInformationApi> api;
    /* access modifiers changed from: private */
    public final Lazy<AuthTokenProvider> authTokens;
    private boolean backPress;
    private final Lazy<CountryService> countryService;
    @NotNull
    private MutableLiveData<Boolean> enabledToChange = new MutableLiveData<>();
    @NotNull
    private MutableLiveData<Boolean> forcedStatus = new MutableLiveData<>();
    @NotNull
    private MutableLiveData<Pair<Integer, Integer>> inputError = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final Lazy<LocalSettingsService> localSettingsService;
    private MutableLiveData<String> newPassword = new MutableLiveData<>();
    @NotNull
    private SingleLiveEvent<Resource<Boolean>> passwordUpdateStatus = new SingleLiveEvent<>();
    private MutableLiveData<String> retypePassword = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final Lazy<Session> session;
    private final CoroutineScope uiScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getMain().plus(this.viewModelJob));
    private final Job viewModelJob = JobKt__JobKt.Job$default(null, 1, null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/feature/settings/model/ChangePasswordViewModel$Companion;", "", "()V", "EXTRA_FORCED_PASSWORD_CHANGE", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ChangePasswordViewModel.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public ChangePasswordViewModel(@NotNull Lazy<CountryService> lazy, @NotNull Provider<MfpInformationApi> provider, @NotNull Lazy<AuthTokenProvider> lazy2, @NotNull Lazy<Session> lazy3, @NotNull Lazy<LocalSettingsService> lazy4) {
        Intrinsics.checkParameterIsNotNull(lazy, "countryService");
        Intrinsics.checkParameterIsNotNull(provider, "api");
        Intrinsics.checkParameterIsNotNull(lazy2, "authTokens");
        Intrinsics.checkParameterIsNotNull(lazy3, "session");
        Intrinsics.checkParameterIsNotNull(lazy4, "localSettingsService");
        this.countryService = lazy;
        this.api = provider;
        this.authTokens = lazy2;
        this.session = lazy3;
        this.localSettingsService = lazy4;
        this.enabledToChange.setValue(Boolean.valueOf(false));
    }

    @NotNull
    public final MutableLiveData<Boolean> getEnabledToChange() {
        return this.enabledToChange;
    }

    public final void setEnabledToChange(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.enabledToChange = mutableLiveData;
    }

    public final boolean getBackPress() {
        return this.backPress;
    }

    public final void setBackPress(boolean z) {
        this.backPress = z;
    }

    @NotNull
    public final SingleLiveEvent<Resource<Boolean>> getPasswordUpdateStatus() {
        return this.passwordUpdateStatus;
    }

    public final void setPasswordUpdateStatus(@NotNull SingleLiveEvent<Resource<Boolean>> singleLiveEvent) {
        Intrinsics.checkParameterIsNotNull(singleLiveEvent, "<set-?>");
        this.passwordUpdateStatus = singleLiveEvent;
    }

    @NotNull
    public final MutableLiveData<Pair<Integer, Integer>> getInputError() {
        return this.inputError;
    }

    public final void setInputError(@NotNull MutableLiveData<Pair<Integer, Integer>> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.inputError = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<Boolean> getForcedStatus() {
        return this.forcedStatus;
    }

    public final void setForcedStatus(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.forcedStatus = mutableLiveData;
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        this.viewModelJob.cancel();
    }

    public final void load(@NotNull Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, Constants.INTENT_SCHEME);
        this.forcedStatus.setValue(Boolean.valueOf(BundleUtils.getBoolean(intent.getExtras(), EXTRA_FORCED_PASSWORD_CHANGE, false)));
    }

    public final Uri getPasswordNoticeUri() {
        return Uri.parse("https://content.myfitnesspal.com/security-information/password-notice.html").buildUpon().appendQueryParameter("locale", ((CountryService) this.countryService.get()).getCurrentLocaleIdentifier()).build();
    }

    @NotNull
    public final OnFocusChangeListener getNewOnFocusChangeListener() {
        return new ChangePasswordViewModel$getNewOnFocusChangeListener$1(this);
    }

    @NotNull
    public final OnFocusChangeListener getRetypeOnFocusChangeListener() {
        return new ChangePasswordViewModel$getRetypeOnFocusChangeListener$1(this);
    }

    public final void newPasswordTextChanged(@Nullable CharSequence charSequence) {
        MutableLiveData<String> mutableLiveData = this.newPassword;
        if (charSequence == null) {
            Intrinsics.throwNpe();
        }
        mutableLiveData.setValue(StringsKt.trim(charSequence).toString());
        if (isNewPasswordValid()) {
            this.inputError.setValue(TuplesKt.to(Integer.valueOf(R.id.newPassword), Integer.valueOf(-1)));
        }
        CharSequence charSequence2 = (CharSequence) this.retypePassword.getValue();
        if (charSequence2 == null || charSequence2.length() == 0) {
            return;
        }
        if (!isPasswordsValid()) {
            retypePasswordMissmatch();
        } else {
            retypePasswordMatch();
        }
    }

    public final void retypePasswordTextChanged(@Nullable CharSequence charSequence) {
        MutableLiveData<String> mutableLiveData = this.retypePassword;
        if (charSequence == null) {
            Intrinsics.throwNpe();
        }
        mutableLiveData.setValue(StringsKt.trim(charSequence).toString());
        if (isPasswordsValid()) {
            retypePasswordMatch();
        } else {
            retypePasswordMissmatch();
        }
    }

    public final void changePassword() {
        updateUserPassword();
    }

    private final void updateUserPassword() {
        Object value = this.newPassword.getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "newPassword.value!!");
        String str = (String) value;
        this.passwordUpdateStatus.setValue(new Loading());
        BuildersKt__Builders_commonKt.launch$default(this.uiScope, null, null, new ChangePasswordViewModel$updateUserPassword$1(this, str, null), 3, null);
    }

    public final void onBackPressed() {
        if (((Boolean) this.forcedStatus.getValue()) != null && this.passwordUpdateStatus.getValue() != null && (!Intrinsics.areEqual((Object) (Resource) this.passwordUpdateStatus.getValue(), (Object) new Success(Boolean.valueOf(true))))) {
            Object obj = this.localSettingsService.get();
            Intrinsics.checkExpressionValueIsNotNull(obj, "localSettingsService.get()");
            ((LocalSettingsService) obj).setPasswordResetDateTime(DateTimeUtils.getDateTimeFromNow(10, 24));
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object patchUser(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.myfitnesspal.feature.settings.model.ChangePasswordViewModel$patchUser$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.myfitnesspal.feature.settings.model.ChangePasswordViewModel$patchUser$1 r0 = (com.myfitnesspal.feature.settings.model.ChangePasswordViewModel$patchUser$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.myfitnesspal.feature.settings.model.ChangePasswordViewModel$patchUser$1 r0 = new com.myfitnesspal.feature.settings.model.ChangePasswordViewModel$patchUser$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L_0x003e;
                case 1: goto L_0x002c;
                default: goto L_0x0024;
            }
        L_0x0024:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x002c:
            java.lang.Object r5 = r0.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$0
            com.myfitnesspal.feature.settings.model.ChangePasswordViewModel r5 = (com.myfitnesspal.feature.settings.model.ChangePasswordViewModel) r5
            boolean r5 = r6 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L_0x0039
            goto L_0x005e
        L_0x0039:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r5 = r6.exception
            throw r5
        L_0x003e:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0064
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            com.myfitnesspal.feature.settings.model.ChangePasswordViewModel$patchUser$2 r2 = new com.myfitnesspal.feature.settings.model.ChangePasswordViewModel$patchUser$2
            r3 = 0
            r2.<init>(r4, r5, r3)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r4
            r0.L$1 = r5
            r5 = 1
            r0.label = r5
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r2, r0)
            if (r6 != r1) goto L_0x005e
            return r1
        L_0x005e:
            java.lang.String r5 = "withContext(Dispatchers.…)\n        }.await()\n    }"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r5)
            return r6
        L_0x0064:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r5 = r6.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.settings.model.ChangePasswordViewModel.patchUser(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final /* synthetic */ Object grefreshTokensByRelogin(@NotNull String str, @NotNull Continuation<Object> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ChangePasswordViewModel$grefreshTokensByRelogin$2(this, str, null), continuation);
    }

    /* access modifiers changed from: private */
    public final void retypePasswordMissmatch() {
        this.inputError.setValue(TuplesKt.to(Integer.valueOf(R.id.retypePassword), Integer.valueOf(R.string.change_password_passwords_no_match)));
        this.enabledToChange.setValue(Boolean.valueOf(false));
    }

    private final void retypePasswordMatch() {
        this.inputError.setValue(TuplesKt.to(Integer.valueOf(R.id.retypePassword), Integer.valueOf(-1)));
        this.enabledToChange.setValue(Boolean.valueOf(true));
    }

    /* access modifiers changed from: private */
    public final boolean isNewPasswordValid() {
        String str = (String) this.newPassword.getValue();
        return str != null && str.length() >= 8;
    }

    /* access modifiers changed from: private */
    public final boolean isPasswordsMatch() {
        String str = (String) this.newPassword.getValue();
        if (str != null) {
            return str.equals(this.retypePassword.getValue());
        }
        return false;
    }

    private final boolean isPasswordsValid() {
        return isNewPasswordValid() && isPasswordsMatch();
    }
}
