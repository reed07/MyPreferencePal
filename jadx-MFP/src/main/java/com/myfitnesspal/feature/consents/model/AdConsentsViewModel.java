package com.myfitnesspal.feature.consents.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.consents.util.AdConsents;
import com.myfitnesspal.feature.consents.util.AdConsentsAnalyticsHelper;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.component.SingleLiveEvent;
import com.uacf.core.mapping.GsonMappableIso8601Date;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.uacf.consentservices.sdk.UacfConsent;
import io.uacf.consentservices.sdk.UacfConsentServiceSdk;
import io.uacf.consentservices.sdk.UacfConsentServiceSdkFactory;
import io.uacf.core.consents.UacfUserConsentStatus;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0002STB'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010B\u001a\u00020%2\u0006\u0010C\u001a\u00020=J\u0010\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010=J\u0018\u0010G\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#0\"J\u0018\u0010H\u001a\u00020E2\b\u0010/\u001a\u0004\u0018\u0001002\u0006\u0010.\u001a\u00020%J\b\u0010I\u001a\u00020EH\u0014J\u0006\u0010J\u001a\u00020EJ\u0016\u0010K\u001a\u00020E2\u0006\u0010C\u001a\u00020=2\u0006\u0010L\u001a\u00020%J\u000e\u0010M\u001a\u00020E2\u0006\u0010N\u001a\u00020%J\u0010\u0010O\u001a\u00020E2\b\b\u0002\u0010P\u001a\u00020%J\b\u0010Q\u001a\u00020EH\u0002J\b\u0010R\u001a\u00020EH\u0002R\u001a\u0010\u000b\u001a\u00020\fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0002¢\u0006\f\n\u0004\b \u0010\u0016\u001a\u0004\b\u001e\u0010\u001fR,\u0010!\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#0\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R)\u0010*\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#0\"0+¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u000e\u0010.\u001a\u00020%X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u000200X.¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u0010\u00105\u001a\u0004\u0018\u000106X\u000e¢\u0006\u0002\n\u0000R\u001d\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0908¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R&\u0010<\u001a\u000e\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020%0#X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010A¨\u0006U"}, d2 = {"Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel;", "Landroid/arch/lifecycle/AndroidViewModel;", "applicationContext", "Landroid/app/Application;", "consentService", "Lcom/myfitnesspal/feature/consents/service/ConsentsService;", "localSettingsService", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "adConsentsAnalyticsHelper", "Lcom/myfitnesspal/feature/consents/util/AdConsentsAnalyticsHelper;", "(Landroid/app/Application;Lcom/myfitnesspal/feature/consents/service/ConsentsService;Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;Lcom/myfitnesspal/feature/consents/util/AdConsentsAnalyticsHelper;)V", "acceptButtonMode", "Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel$ButtonMode;", "getAcceptButtonMode", "()Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel$ButtonMode;", "setAcceptButtonMode", "(Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel$ButtonMode;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/disposables/CompositeDisposable;", "compositeDisposable$delegate", "Lkotlin/Lazy;", "consentFactory", "Lio/uacf/consentservices/sdk/UacfConsentServiceSdkFactory;", "getConsentFactory", "()Lio/uacf/consentservices/sdk/UacfConsentServiceSdkFactory;", "consentFactory$delegate", "consentServiceSdk", "Lio/uacf/consentservices/sdk/UacfConsentServiceSdk;", "getConsentServiceSdk", "()Lio/uacf/consentservices/sdk/UacfConsentServiceSdk;", "consentServiceSdk$delegate", "consentsList", "", "Lkotlin/Pair;", "Lio/uacf/consentservices/sdk/UacfConsent;", "", "getConsentsList", "()Ljava/util/List;", "setConsentsList", "(Ljava/util/List;)V", "consentsToShowList", "Landroid/arch/lifecycle/MutableLiveData;", "getConsentsToShowList", "()Landroid/arch/lifecycle/MutableLiveData;", "isPremiumUser", "mode", "Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel$Mode;", "getMode", "()Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel$Mode;", "setMode", "(Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel$Mode;)V", "uacfUserConsentStatus", "Lio/uacf/core/consents/UacfUserConsentStatus;", "updateAdConsentsStatus", "Lcom/myfitnesspal/shared/ui/component/SingleLiveEvent;", "Lcom/myfitnesspal/feature/consents/model/Resource;", "getUpdateAdConsentsStatus", "()Lcom/myfitnesspal/shared/ui/component/SingleLiveEvent;", "updatedConsentInSettings", "", "getUpdatedConsentInSettings", "()Lkotlin/Pair;", "setUpdatedConsentInSettings", "(Lkotlin/Pair;)V", "didNotAcceptConsent", "consentId", "fetchConsents", "", "country", "getCheckedConsents", "initialize", "onCleared", "setAllConsentsAsChecked", "setConsentChecked", "isChecked", "setShouldInterruptUserForAdConsents", "value", "updateAdConsents", "asyncSave", "updateAdConsentsLastSeen", "updateConsentsToShowAndAcceptButtonMode", "ButtonMode", "Mode", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AdConsentsViewModel.kt */
public final class AdConsentsViewModel extends AndroidViewModel {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(AdConsentsViewModel.class), "consentFactory", "getConsentFactory()Lio/uacf/consentservices/sdk/UacfConsentServiceSdkFactory;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(AdConsentsViewModel.class), "consentServiceSdk", "getConsentServiceSdk()Lio/uacf/consentservices/sdk/UacfConsentServiceSdk;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(AdConsentsViewModel.class), "compositeDisposable", "getCompositeDisposable()Lio/reactivex/disposables/CompositeDisposable;"))};
    @NotNull
    public ButtonMode acceptButtonMode;
    /* access modifiers changed from: private */
    public final AdConsentsAnalyticsHelper adConsentsAnalyticsHelper;
    /* access modifiers changed from: private */
    public final Application applicationContext;
    private final Lazy compositeDisposable$delegate = LazyKt.lazy(AdConsentsViewModel$compositeDisposable$2.INSTANCE);
    private final Lazy consentFactory$delegate = LazyKt.lazy(AdConsentsViewModel$consentFactory$2.INSTANCE);
    /* access modifiers changed from: private */
    public final ConsentsService consentService;
    private final Lazy consentServiceSdk$delegate = LazyKt.lazy(new AdConsentsViewModel$consentServiceSdk$2(this));
    @NotNull
    private List<? extends Pair<? extends UacfConsent, Boolean>> consentsList = CollectionsKt.emptyList();
    @NotNull
    private final MutableLiveData<List<Pair<UacfConsent, Boolean>>> consentsToShowList = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public boolean isPremiumUser;
    /* access modifiers changed from: private */
    public final LocalSettingsService localSettingsService;
    @NotNull
    public Mode mode;
    /* access modifiers changed from: private */
    public UacfUserConsentStatus uacfUserConsentStatus;
    @NotNull
    private final SingleLiveEvent<Resource<Boolean>> updateAdConsentsStatus = new SingleLiveEvent<>();
    @NotNull
    private Pair<String, Boolean> updatedConsentInSettings = new Pair<>("", Boolean.valueOf(false));

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel$ButtonMode;", "", "stringId", "", "(Ljava/lang/String;II)V", "getStringId", "()I", "ACCEPT_ALL", "I_ACCEPT", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AdConsentsViewModel.kt */
    public enum ButtonMode {
        ACCEPT_ALL(R.string.accept_all),
        I_ACCEPT(R.string.i_accept_caps);
        
        private final int stringId;

        protected ButtonMode(int i) {
            this.stringId = i;
        }

        public final int getStringId() {
            return this.stringId;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel$Mode;", "", "(Ljava/lang/String;I)V", "INTERRUPTION", "SETTINGS", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AdConsentsViewModel.kt */
    public enum Mode {
        INTERRUPTION,
        SETTINGS
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
    public AdConsentsViewModel(@NotNull Application application, @NotNull ConsentsService consentsService, @NotNull LocalSettingsService localSettingsService2, @NotNull AdConsentsAnalyticsHelper adConsentsAnalyticsHelper2) {
        Intrinsics.checkParameterIsNotNull(application, "applicationContext");
        Intrinsics.checkParameterIsNotNull(consentsService, "consentService");
        Intrinsics.checkParameterIsNotNull(localSettingsService2, "localSettingsService");
        Intrinsics.checkParameterIsNotNull(adConsentsAnalyticsHelper2, "adConsentsAnalyticsHelper");
        super(application);
        this.applicationContext = application;
        this.consentService = consentsService;
        this.localSettingsService = localSettingsService2;
        this.adConsentsAnalyticsHelper = adConsentsAnalyticsHelper2;
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
    public final ButtonMode getAcceptButtonMode() {
        ButtonMode buttonMode = this.acceptButtonMode;
        if (buttonMode == null) {
            Intrinsics.throwUninitializedPropertyAccessException("acceptButtonMode");
        }
        return buttonMode;
    }

    public final void setAcceptButtonMode(@NotNull ButtonMode buttonMode) {
        Intrinsics.checkParameterIsNotNull(buttonMode, "<set-?>");
        this.acceptButtonMode = buttonMode;
    }

    @NotNull
    public final List<Pair<UacfConsent, Boolean>> getConsentsList() {
        return this.consentsList;
    }

    public final void setConsentsList(@NotNull List<? extends Pair<? extends UacfConsent, Boolean>> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.consentsList = list;
    }

    @NotNull
    public final Pair<String, Boolean> getUpdatedConsentInSettings() {
        return this.updatedConsentInSettings;
    }

    public final void setUpdatedConsentInSettings(@NotNull Pair<String, Boolean> pair) {
        Intrinsics.checkParameterIsNotNull(pair, "<set-?>");
        this.updatedConsentInSettings = pair;
    }

    @NotNull
    public final MutableLiveData<List<Pair<UacfConsent, Boolean>>> getConsentsToShowList() {
        return this.consentsToShowList;
    }

    @NotNull
    public final SingleLiveEvent<Resource<Boolean>> getUpdateAdConsentsStatus() {
        return this.updateAdConsentsStatus;
    }

    public final void initialize(@Nullable Mode mode2, boolean z) {
        if (mode2 == null || mode2 == null) {
            mode2 = Mode.INTERRUPTION;
        }
        this.mode = mode2;
        this.isPremiumUser = z;
    }

    public static /* synthetic */ void updateAdConsents$default(AdConsentsViewModel adConsentsViewModel, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        adConsentsViewModel.updateAdConsents(z);
    }

    public final void updateAdConsents(boolean z) {
        UacfUserConsentStatus uacfUserConsentStatus2 = this.uacfUserConsentStatus;
        if (uacfUserConsentStatus2 != null) {
            BooleanRef booleanRef = new BooleanRef();
            booleanRef.element = false;
            for (Pair pair : this.consentsList) {
                UacfConsent uacfConsent = (UacfConsent) pair.component1();
                boolean booleanValue = ((Boolean) pair.component2()).booleanValue();
                uacfUserConsentStatus2.setConsentStatus(uacfConsent.getId(), booleanValue);
                if (Intrinsics.areEqual((Object) uacfConsent.getId(), (Object) AdConsents.PERSONALIZED_ADS.getId()) && booleanValue) {
                    booleanRef.element = booleanValue;
                }
            }
            if (!z) {
                getCompositeDisposable().add(this.consentService.updateConsentStatus(uacfUserConsentStatus2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new AdConsentsViewModel$updateAdConsents$1(this, booleanRef), new AdConsentsViewModel$updateAdConsents$2(this)));
            } else {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO().plus(new AdConsentsViewModel$updateAdConsents$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key, this)), null, new AdConsentsViewModel$updateAdConsents$4(this, uacfUserConsentStatus2, booleanRef, null), 2, null);
            }
        }
    }

    @NotNull
    public final List<Pair<UacfConsent, Boolean>> getCheckedConsents() {
        Iterable iterable = this.consentsList;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            if (((Boolean) ((Pair) next).getSecond()).booleanValue()) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    public final boolean didNotAcceptConsent(@NotNull String str) {
        boolean z;
        boolean z2;
        Intrinsics.checkParameterIsNotNull(str, "consentId");
        Iterable checkedConsents = getCheckedConsents();
        if (!(checkedConsents instanceof Collection) || !((Collection) checkedConsents).isEmpty()) {
            Iterator it = checkedConsents.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (Intrinsics.areEqual((Object) ((UacfConsent) ((Pair) it.next()).getFirst()).getId(), (Object) str)) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
        } else {
            z = true;
        }
        if (!z) {
            return false;
        }
        Iterable iterable = this.consentsList;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it2 = iterable.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (Intrinsics.areEqual((Object) ((UacfConsent) ((Pair) it2.next()).getFirst()).getId(), (Object) str)) {
                        z2 = true;
                        break;
                    }
                } else {
                    z2 = false;
                    break;
                }
            }
        } else {
            z2 = false;
        }
        if (z2) {
            return true;
        }
        return false;
    }

    public final void setConsentChecked(@NotNull String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "consentId");
        Iterable<Pair> iterable = this.consentsList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Pair pair : iterable) {
            if (Intrinsics.areEqual((Object) ((UacfConsent) pair.getFirst()).getId(), (Object) str)) {
                pair = Pair.copy$default(pair, null, Boolean.valueOf(z), 1, null);
            }
            arrayList.add(pair);
        }
        this.consentsList = (List) arrayList;
        updateConsentsToShowAndAcceptButtonMode();
    }

    public final void setAllConsentsAsChecked() {
        Iterable<Pair> iterable = this.consentsList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Pair copy$default : iterable) {
            arrayList.add(Pair.copy$default(copy$default, null, Boolean.valueOf(true), 1, null));
        }
        this.consentsList = (List) arrayList;
        updateConsentsToShowAndAcceptButtonMode();
    }

    public final void setShouldInterruptUserForAdConsents(boolean z) {
        this.localSettingsService.setShouldInterruptUserForAdConsents(z);
    }

    public final void fetchConsents(@Nullable String str) {
        if (str != null) {
            getCompositeDisposable().add(this.consentService.getConsentStatusFromCountryName(str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new AdConsentsViewModel$fetchConsents$$inlined$let$lambda$1(this), AdConsentsViewModel$fetchConsents$1$2.INSTANCE));
        }
    }

    /* access modifiers changed from: private */
    public final void updateAdConsentsLastSeen() {
        UacfUserConsentStatus uacfUserConsentStatus2 = this.uacfUserConsentStatus;
        if (uacfUserConsentStatus2 != null) {
            Calendar instance = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(instance, "Calendar.getInstance()");
            uacfUserConsentStatus2.setAdConsentsLastSeenDate(GsonMappableIso8601Date.newInstance(instance.getTime()));
            getCompositeDisposable().add(this.consentService.updateConsentStatus(this.uacfUserConsentStatus).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(AdConsentsViewModel$updateAdConsentsLastSeen$1$1.INSTANCE, AdConsentsViewModel$updateAdConsentsLastSeen$1$2.INSTANCE));
        }
    }

    /* access modifiers changed from: private */
    public final void updateConsentsToShowAndAcceptButtonMode() {
        ButtonMode buttonMode;
        Mode mode2 = this.mode;
        if (mode2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(InternalAvidAdSessionContext.CONTEXT_MODE);
        }
        if (mode2 == Mode.INTERRUPTION) {
            if (this.consentsList.size() <= 1 || !getCheckedConsents().isEmpty()) {
                buttonMode = ButtonMode.I_ACCEPT;
            } else {
                buttonMode = ButtonMode.ACCEPT_ALL;
            }
            this.acceptButtonMode = buttonMode;
        }
        this.consentsToShowList.setValue(this.consentsList);
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        getCompositeDisposable().clear();
        super.onCleared();
    }
}
