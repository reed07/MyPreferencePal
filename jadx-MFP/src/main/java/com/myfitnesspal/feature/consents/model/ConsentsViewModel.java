package com.myfitnesspal.feature.consents.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.mopub.common.Constants;
import com.myfitnesspal.feature.consents.model.Resource.Loading;
import com.myfitnesspal.feature.consents.model.Resource.Success;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.consents.ui.activity.ConsentsActivity;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelper;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.component.SingleLiveEvent;
import com.uacf.core.util.BundleUtils;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.uacf.consentservices.sdk.UacfConsent;
import io.uacf.consentservices.sdk.UacfConsentServiceSdk;
import io.uacf.consentservices.sdk.UacfConsentServiceSdkFactory;
import io.uacf.consentservices.sdk.UacfConsentStatus;
import io.uacf.core.consents.UacfUserConsentStatus;
import io.uacf.core.consents.UacfUserConsentStatusProvider;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001:\u0001cB/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010A\u001a\u00020BH\u0002J\u0006\u0010C\u001a\u00020DJ\u0006\u0010E\u001a\u00020\u000eJ$\u0010F\u001a \u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u000e0#0\"0!0 J&\u0010G\u001a\"\u0012\f\u0012\n I*\u0004\u0018\u00010'0'0Hj\u0010\u0012\f\u0012\n I*\u0004\u0018\u00010'0'`JJ\u000e\u0010K\u001a\b\u0012\u0004\u0012\u00020M0LH\u0002J\u0006\u0010N\u001a\u00020=J\u0012\u0010O\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0!0;J\u0012\u0010P\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0!0;J\u0006\u0010Q\u001a\u00020\u000eJ\u0006\u0010R\u001a\u00020\u000eJ\u0006\u0010S\u001a\u00020\u000eJ\u0006\u0010T\u001a\u00020\u000eJ\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000e0 J&\u0010U\u001a\u00020D2\u0006\u0010V\u001a\u00020W2\u0006\u00106\u001a\u0002072\u0006\u0010.\u001a\u00020/2\u0006\u00108\u001a\u000209J\u000e\u0010X\u001a\u00020D2\u0006\u0010Y\u001a\u00020\u000eJ\u0016\u0010Z\u001a\u00020D2\u0006\u0010[\u001a\u00020$2\u0006\u0010Y\u001a\u00020\u000eJ\b\u0010\\\u001a\u00020DH\u0014J\u0010\u0010]\u001a\u00020D2\u0006\u0010^\u001a\u00020MH\u0002J(\u0010_\u001a\u00020D2\u0006\u0010V\u001a\u00020W2\u0006\u00106\u001a\u0002072\u0006\u0010.\u001a\u00020/2\u0006\u00108\u001a\u000209H\u0002J\b\u0010`\u001a\u00020BH\u0002J\b\u0010a\u001a\u00020DH\u0002J\u0006\u0010b\u001a\u00020DR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0014\u001a\u0004\b\u001c\u0010\u001dR,\u0010\u001f\u001a \u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u000e0#0\"0!0 X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R \u0010%\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u000e0#0\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000e0 X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020'X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u000e¢\u0006\u0002\n\u0000R\u001a\u00100\u001a\u000201X.¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u0004\u0018\u000107X\u000e¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u000109X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0!0;X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0!0;X\u000e¢\u0006\u0002\n\u0000¨\u0006d"}, d2 = {"Lcom/myfitnesspal/feature/consents/model/ConsentsViewModel;", "Landroid/arch/lifecycle/AndroidViewModel;", "applicationContext", "Landroid/app/Application;", "sessions", "Lcom/myfitnesspal/shared/service/session/Session;", "consentsService", "Lcom/myfitnesspal/feature/consents/service/ConsentsService;", "countryService", "Lcom/myfitnesspal/shared/service/install/CountryService;", "consentsAnalyticsHelper", "Lcom/myfitnesspal/feature/consents/util/ConsentsAnalyticsHelper;", "(Landroid/app/Application;Lcom/myfitnesspal/shared/service/session/Session;Lcom/myfitnesspal/feature/consents/service/ConsentsService;Lcom/myfitnesspal/shared/service/install/CountryService;Lcom/myfitnesspal/feature/consents/util/ConsentsAnalyticsHelper;)V", "backPress", "", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/disposables/CompositeDisposable;", "compositeDisposable$delegate", "Lkotlin/Lazy;", "consentFactory", "Lio/uacf/consentservices/sdk/UacfConsentServiceSdkFactory;", "getConsentFactory", "()Lio/uacf/consentservices/sdk/UacfConsentServiceSdkFactory;", "consentFactory$delegate", "consentServiceSdk", "Lio/uacf/consentservices/sdk/UacfConsentServiceSdk;", "getConsentServiceSdk", "()Lio/uacf/consentservices/sdk/UacfConsentServiceSdk;", "consentServiceSdk$delegate", "consents", "Landroid/arch/lifecycle/MutableLiveData;", "Lcom/myfitnesspal/feature/consents/model/Resource;", "", "Lkotlin/Pair;", "Lio/uacf/consentservices/sdk/UacfConsent;", "consentsList", "consentsMatrixVersion", "", "isAllAccepted", "()Z", "setAllAccepted", "(Z)V", "isSingleConsent", "iso", "loginModel", "Lcom/myfitnesspal/feature/registration/model/LoginModel;", "mode", "Lcom/myfitnesspal/feature/consents/model/ConsentsViewModel$Mode;", "getMode", "()Lcom/myfitnesspal/feature/consents/model/ConsentsViewModel$Mode;", "setMode", "(Lcom/myfitnesspal/feature/consents/model/ConsentsViewModel$Mode;)V", "signUpModel", "Lcom/myfitnesspal/feature/registration/model/SignUpModel;", "signUpService", "Lcom/myfitnesspal/feature/registration/service/SignUpService;", "signUpStatus", "Lcom/myfitnesspal/shared/ui/component/SingleLiveEvent;", "totalNumberOfConsents", "", "uacfUserconsentStatus", "Lio/uacf/core/consents/UacfUserConsentStatus;", "updateConsentsStatus", "finishSignUpCompletable", "Lio/reactivex/Completable;", "finishSignUpFlow", "", "getBackPressStatus", "getConsent", "getConsentIds", "Ljava/util/ArrayList;", "kotlin.jvm.PlatformType", "Lkotlin/collections/ArrayList;", "getConsentStatus", "Lio/reactivex/Single;", "Lio/uacf/consentservices/sdk/UacfConsentStatus;", "getNumberOfConsents", "getSingUpStatus", "getUpdateConsentsStatus", "isEditCountryMode", "isEditMode", "isExistingAddMode", "isOnBoarding", "load", "intent", "Landroid/content/Intent;", "markAllConsentsAsChecked", "isChecked", "markConsentAsChecked", "consent", "onCleared", "setConsentsMap", "consentStatus", "setUpModePerMode", "updateConsentStatus", "updateUacfUserConsentStatus", "updatedConsentsFlow", "Mode", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ConsentsViewModel.kt */
public final class ConsentsViewModel extends AndroidViewModel {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ConsentsViewModel.class), "consentFactory", "getConsentFactory()Lio/uacf/consentservices/sdk/UacfConsentServiceSdkFactory;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ConsentsViewModel.class), "consentServiceSdk", "getConsentServiceSdk()Lio/uacf/consentservices/sdk/UacfConsentServiceSdk;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ConsentsViewModel.class), "compositeDisposable", "getCompositeDisposable()Lio/reactivex/disposables/CompositeDisposable;"))};
    /* access modifiers changed from: private */
    public final Application applicationContext;
    /* access modifiers changed from: private */
    public boolean backPress;
    private final Lazy compositeDisposable$delegate = LazyKt.lazy(ConsentsViewModel$compositeDisposable$2.INSTANCE);
    private final Lazy consentFactory$delegate = LazyKt.lazy(ConsentsViewModel$consentFactory$2.INSTANCE);
    private final Lazy consentServiceSdk$delegate = LazyKt.lazy(new ConsentsViewModel$consentServiceSdk$2(this));
    /* access modifiers changed from: private */
    public MutableLiveData<Resource<List<Pair<UacfConsent, Boolean>>>> consents = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final ConsentsAnalyticsHelper consentsAnalyticsHelper;
    /* access modifiers changed from: private */
    public List<? extends Pair<? extends UacfConsent, Boolean>> consentsList = CollectionsKt.emptyList();
    /* access modifiers changed from: private */
    public String consentsMatrixVersion = "0";
    /* access modifiers changed from: private */
    public final ConsentsService consentsService;
    private final CountryService countryService;
    private boolean isAllAccepted;
    private MutableLiveData<Boolean> isSingleConsent = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public String iso = "";
    /* access modifiers changed from: private */
    public LoginModel loginModel;
    @NotNull
    public Mode mode;
    private final Session sessions;
    /* access modifiers changed from: private */
    public SignUpModel signUpModel;
    /* access modifiers changed from: private */
    public SignUpService signUpService;
    /* access modifiers changed from: private */
    public SingleLiveEvent<Resource<Boolean>> signUpStatus = new SingleLiveEvent<>();
    /* access modifiers changed from: private */
    public int totalNumberOfConsents;
    private UacfUserConsentStatus uacfUserconsentStatus;
    /* access modifiers changed from: private */
    public SingleLiveEvent<Resource<Boolean>> updateConsentsStatus = new SingleLiveEvent<>();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/consents/model/ConsentsViewModel$Mode;", "", "(Ljava/lang/String;I)V", "NEW", "EXISTING_ADD", "EXISTING_EDIT_COUNTRY", "EXISTING_EDIT_CONSENT", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ConsentsViewModel.kt */
    public enum Mode {
        NEW,
        EXISTING_ADD,
        EXISTING_EDIT_COUNTRY,
        EXISTING_EDIT_CONSENT
    }

    private final CompositeDisposable getCompositeDisposable() {
        Lazy lazy = this.compositeDisposable$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (CompositeDisposable) lazy.getValue();
    }

    /* access modifiers changed from: private */
    public final UacfConsentServiceSdkFactory getConsentFactory() {
        Lazy lazy = this.consentFactory$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (UacfConsentServiceSdkFactory) lazy.getValue();
    }

    /* access modifiers changed from: private */
    public final UacfConsentServiceSdk getConsentServiceSdk() {
        Lazy lazy = this.consentServiceSdk$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (UacfConsentServiceSdk) lazy.getValue();
    }

    @Inject
    public ConsentsViewModel(@NotNull Application application, @NotNull Session session, @NotNull ConsentsService consentsService2, @NotNull CountryService countryService2, @NotNull ConsentsAnalyticsHelper consentsAnalyticsHelper2) {
        Intrinsics.checkParameterIsNotNull(application, "applicationContext");
        Intrinsics.checkParameterIsNotNull(session, "sessions");
        Intrinsics.checkParameterIsNotNull(consentsService2, "consentsService");
        Intrinsics.checkParameterIsNotNull(countryService2, "countryService");
        Intrinsics.checkParameterIsNotNull(consentsAnalyticsHelper2, "consentsAnalyticsHelper");
        super(application);
        this.applicationContext = application;
        this.sessions = session;
        this.consentsService = consentsService2;
        this.countryService = countryService2;
        this.consentsAnalyticsHelper = consentsAnalyticsHelper2;
    }

    public final boolean isAllAccepted() {
        return this.isAllAccepted;
    }

    public final void setAllAccepted(boolean z) {
        this.isAllAccepted = z;
    }

    @NotNull
    public final Mode getMode() {
        Mode mode2 = this.mode;
        if (mode2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(InternalAvidAdSessionContext.CONTEXT_MODE);
        }
        return mode2;
    }

    public final void setMode(@NotNull Mode mode2) {
        Intrinsics.checkParameterIsNotNull(mode2, "<set-?>");
        this.mode = mode2;
    }

    @NotNull
    public final MutableLiveData<Resource<List<Pair<UacfConsent, Boolean>>>> getConsent() {
        return this.consents;
    }

    @NotNull
    public final SingleLiveEvent<Resource<Boolean>> getSingUpStatus() {
        return this.signUpStatus;
    }

    @NotNull
    public final SingleLiveEvent<Resource<Boolean>> getUpdateConsentsStatus() {
        return this.updateConsentsStatus;
    }

    @NotNull
    public final MutableLiveData<Boolean> isSingleConsent() {
        return this.isSingleConsent;
    }

    public final boolean getBackPressStatus() {
        return this.backPress;
    }

    public final int getNumberOfConsents() {
        return this.consentsList.size();
    }

    @NotNull
    public final ArrayList<String> getConsentIds() {
        Iterable<Pair> iterable = this.consentsList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Pair first : iterable) {
            arrayList.add(((UacfConsent) first.getFirst()).getId());
        }
        return new ArrayList<>((List) arrayList);
    }

    public final boolean isOnBoarding() {
        Mode mode2 = this.mode;
        if (mode2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(InternalAvidAdSessionContext.CONTEXT_MODE);
        }
        return mode2 == Mode.NEW;
    }

    public final boolean isEditMode() {
        Mode mode2 = this.mode;
        if (mode2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(InternalAvidAdSessionContext.CONTEXT_MODE);
        }
        return mode2 == Mode.EXISTING_EDIT_CONSENT;
    }

    public final boolean isEditCountryMode() {
        Mode mode2 = this.mode;
        if (mode2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(InternalAvidAdSessionContext.CONTEXT_MODE);
        }
        return mode2 == Mode.EXISTING_EDIT_COUNTRY;
    }

    public final boolean isExistingAddMode() {
        Mode mode2 = this.mode;
        if (mode2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(InternalAvidAdSessionContext.CONTEXT_MODE);
        }
        return mode2 == Mode.EXISTING_ADD;
    }

    public final void load(@NotNull Intent intent, @NotNull SignUpModel signUpModel2, @NotNull LoginModel loginModel2, @NotNull SignUpService signUpService2) {
        Intrinsics.checkParameterIsNotNull(intent, Constants.INTENT_SCHEME);
        Intrinsics.checkParameterIsNotNull(signUpModel2, "signUpModel");
        Intrinsics.checkParameterIsNotNull(loginModel2, "loginModel");
        Intrinsics.checkParameterIsNotNull(signUpService2, "signUpService");
        setUpModePerMode(intent, signUpModel2, loginModel2, signUpService2);
        this.consents.setValue(new Loading());
        this.isSingleConsent.setValue(Boolean.valueOf(false));
        getCompositeDisposable().add(getConsentStatus().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ConsentsViewModel$load$1(this), new ConsentsViewModel$load$2(this)));
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        getCompositeDisposable().clear();
        super.onCleared();
    }

    public final void finishSignUpFlow() {
        this.backPress = true;
        this.signUpStatus.setValue(new Loading());
        getCompositeDisposable().add(finishSignUpCompletable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ConsentsViewModel$finishSignUpFlow$1(this), new ConsentsViewModel$finishSignUpFlow$2(this)));
    }

    public final void updatedConsentsFlow() {
        this.backPress = true;
        this.updateConsentsStatus.setValue(new Loading());
        getCompositeDisposable().add(updateConsentStatus().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ConsentsViewModel$updatedConsentsFlow$1(this), new ConsentsViewModel$updatedConsentsFlow$2(this)));
    }

    private final Completable finishSignUpCompletable() {
        SignUpModel signUpModel2 = this.signUpModel;
        if (signUpModel2 != null) {
            signUpModel2.setConsentsMatrixVersion(this.consentsMatrixVersion);
            signUpModel2.setNumberOfConsents(getNumberOfConsents());
            signUpModel2.setAcceptedConsentsId(getConsentIds());
        }
        Completable fromCallable = Completable.fromCallable(new ConsentsViewModel$finishSignUpCompletable$2(this));
        Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Completable.fromCallable…nError.Unknown)\n        }");
        return fromCallable;
    }

    private final Single<UacfConsentStatus> getConsentStatus() {
        Single<UacfConsentStatus> fromCallable = Single.fromCallable(new ConsentsViewModel$getConsentStatus$1(this));
        Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Single.fromCallable {\n  …tStatus { iso }\n        }");
        return fromCallable;
    }

    private final Completable updateConsentStatus() {
        updateUacfUserConsentStatus();
        return this.consentsService.updateConsentStatus(this.uacfUserconsentStatus);
    }

    /* access modifiers changed from: private */
    public final void setConsentsMap(UacfConsentStatus uacfConsentStatus) {
        if (uacfConsentStatus != null) {
            UacfUserConsentStatusProvider uacfUserConsentStatus = getConsentServiceSdk().getUacfUserConsentStatus(uacfConsentStatus);
            if (uacfUserConsentStatus != null) {
                this.uacfUserconsentStatus = (UacfUserConsentStatus) uacfUserConsentStatus;
                String consentMatrixVersion = uacfConsentStatus.getConsentMatrixVersion();
                if (consentMatrixVersion == null) {
                    consentMatrixVersion = "0";
                }
                this.consentsMatrixVersion = consentMatrixVersion;
                List consents2 = uacfConsentStatus.getConsents();
                if (consents2 != null) {
                    Iterable iterable = consents2;
                    Collection arrayList = new ArrayList();
                    Iterator it = iterable.iterator();
                    while (true) {
                        boolean z = false;
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        UacfConsent uacfConsent = (UacfConsent) next;
                        if (uacfConsent != null) {
                            z = uacfConsent.isRequired();
                        }
                        if (z) {
                            arrayList.add(next);
                        }
                    }
                    Iterable iterable2 = (List) arrayList;
                    Collection arrayList2 = new ArrayList();
                    Iterator it2 = iterable2.iterator();
                    while (true) {
                        boolean z2 = true;
                        if (!it2.hasNext()) {
                            break;
                        }
                        Object next2 = it2.next();
                        UacfConsent uacfConsent2 = (UacfConsent) next2;
                        this.totalNumberOfConsents++;
                        Mode mode2 = this.mode;
                        if (mode2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(InternalAvidAdSessionContext.CONTEXT_MODE);
                        }
                        if (mode2 == Mode.EXISTING_EDIT_CONSENT) {
                            Intrinsics.checkExpressionValueIsNotNull(uacfConsent2, "it");
                            z2 = uacfConsent2.isAccepted();
                        } else {
                            Intrinsics.checkExpressionValueIsNotNull(uacfConsent2, "it");
                            if (uacfConsent2.isAccepted()) {
                                z2 = false;
                            }
                        }
                        if (z2) {
                            arrayList2.add(next2);
                        }
                    }
                    Iterable<UacfConsent> iterable3 = (List) arrayList2;
                    Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
                    for (UacfConsent uacfConsent3 : iterable3) {
                        Mode mode3 = this.mode;
                        if (mode3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(InternalAvidAdSessionContext.CONTEXT_MODE);
                        }
                        arrayList3.add(TuplesKt.to(uacfConsent3, Boolean.valueOf(mode3 == Mode.EXISTING_EDIT_CONSENT)));
                    }
                    this.consentsList = (List) arrayList3;
                    this.isSingleConsent.setValue(Boolean.valueOf(this.consentsList.isEmpty()));
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type io.uacf.core.consents.UacfUserConsentStatus");
        }
    }

    private final void setUpModePerMode(Intent intent, SignUpModel signUpModel2, LoginModel loginModel2, SignUpService signUpService2) {
        String str;
        Serializable serializable = BundleUtils.getSerializable(intent.getExtras(), ConsentsActivity.EXTRA_MODE, Mode.class.getClassLoader());
        Intrinsics.checkExpressionValueIsNotNull(serializable, "BundleUtils.getSerializa…::class.java.classLoader)");
        this.mode = (Mode) serializable;
        this.signUpModel = signUpModel2;
        this.loginModel = loginModel2;
        this.signUpService = signUpService2;
        Mode mode2 = this.mode;
        if (mode2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(InternalAvidAdSessionContext.CONTEXT_MODE);
        }
        switch (mode2) {
            case NEW:
                signUpModel2.setPassword(BundleUtils.getString(intent.getExtras(), "extra_password"));
                str = BundleUtils.getString(intent.getExtras(), ConsentsActivity.EXTRA_ISO, this.countryService.getCurrentCountry().getShortName());
                Intrinsics.checkExpressionValueIsNotNull(str, "BundleUtils.getString(in…currentCountry.shortName)");
                break;
            case EXISTING_ADD:
            case EXISTING_EDIT_CONSENT:
                CountryService countryService2 = this.countryService;
                UserProfile profile = this.sessions.getUser().getProfile();
                Intrinsics.checkExpressionValueIsNotNull(profile, "sessions.user.profile");
                str = countryService2.getShortNameFromLongName(profile.getCountryName());
                break;
            case EXISTING_EDIT_COUNTRY:
                str = BundleUtils.getString(intent.getExtras(), ConsentsActivity.EXTRA_ISO, this.countryService.getCurrentCountry().getShortName());
                Intrinsics.checkExpressionValueIsNotNull(str, "BundleUtils.getString(in…currentCountry.shortName)");
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        this.iso = str;
    }

    private final void updateUacfUserConsentStatus() {
        for (Pair pair : this.consentsList) {
            UacfConsent uacfConsent = (UacfConsent) pair.component1();
            boolean booleanValue = ((Boolean) pair.component2()).booleanValue();
            UacfUserConsentStatus uacfUserConsentStatus = this.uacfUserconsentStatus;
            if (uacfUserConsentStatus != null) {
                uacfUserConsentStatus.setConsentStatus(uacfConsent.getId(), booleanValue);
            }
        }
    }

    public final void markAllConsentsAsChecked(boolean z) {
        this.isAllAccepted = z;
        Iterable<Pair> iterable = this.consentsList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Pair copy$default : iterable) {
            arrayList.add(Pair.copy$default(copy$default, null, Boolean.valueOf(this.isAllAccepted), 1, null));
        }
        this.consentsList = (List) arrayList;
        this.consents.setValue(new Success(this.consentsList));
    }

    public final void markConsentAsChecked(@NotNull UacfConsent uacfConsent, boolean z) {
        Intrinsics.checkParameterIsNotNull(uacfConsent, "consent");
        Iterable<Pair> iterable = this.consentsList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        boolean z2 = false;
        int i = 0;
        for (Pair pair : iterable) {
            if (Intrinsics.areEqual((Object) ((UacfConsent) pair.getFirst()).getId(), (Object) uacfConsent.getId())) {
                if (z) {
                    i++;
                }
                pair = Pair.copy$default(pair, null, Boolean.valueOf(z), 1, null);
            } else if (((Boolean) pair.getSecond()).booleanValue()) {
                i++;
            }
            arrayList.add(pair);
        }
        this.consentsList = (List) arrayList;
        if (i == this.consentsList.size()) {
            z2 = true;
        }
        this.isAllAccepted = z2;
        this.consents.setValue(new Success(this.consentsList));
    }
}
