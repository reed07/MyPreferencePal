package com.myfitnesspal.feature.consents.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.Strings;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0014\u001a\u00020\u0012H\u0002J\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010J\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u0010J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u0018H\u0002J\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u001aH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/myfitnesspal/feature/consents/model/ConfirmLocationViewModel;", "Landroid/arch/lifecycle/AndroidViewModel;", "applicationContext", "Landroid/app/Application;", "sessions", "Lcom/myfitnesspal/shared/service/session/Session;", "countryService", "Lcom/myfitnesspal/shared/service/install/CountryService;", "(Landroid/app/Application;Lcom/myfitnesspal/shared/service/session/Session;Lcom/myfitnesspal/shared/service/install/CountryService;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/disposables/CompositeDisposable;", "compositeDisposable$delegate", "Lkotlin/Lazy;", "countries", "Landroid/arch/lifecycle/MutableLiveData;", "", "", "country", "getCleanCountryName", "getCountries", "getCountry", "getFriendsAndAppsInfo", "Lio/reactivex/Single;", "load", "", "onCleared", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ConfirmLocationViewModel.kt */
public final class ConfirmLocationViewModel extends AndroidViewModel {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ConfirmLocationViewModel.class), "compositeDisposable", "getCompositeDisposable()Lio/reactivex/disposables/CompositeDisposable;"))};
    private final Application applicationContext;
    private final Lazy compositeDisposable$delegate = LazyKt.lazy(ConfirmLocationViewModel$compositeDisposable$2.INSTANCE);
    private final MutableLiveData<List<String>> countries = new MutableLiveData<>();
    private MutableLiveData<String> country = new MutableLiveData<>();
    private final CountryService countryService;
    private final Session sessions;

    private final CompositeDisposable getCompositeDisposable() {
        Lazy lazy = this.compositeDisposable$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (CompositeDisposable) lazy.getValue();
    }

    @Inject
    public ConfirmLocationViewModel(@NotNull Application application, @NotNull Session session, @NotNull CountryService countryService2) {
        Intrinsics.checkParameterIsNotNull(application, "applicationContext");
        Intrinsics.checkParameterIsNotNull(session, "sessions");
        Intrinsics.checkParameterIsNotNull(countryService2, "countryService");
        super(application);
        this.applicationContext = application;
        this.sessions = session;
        this.countryService = countryService2;
    }

    @NotNull
    public final MutableLiveData<String> getCountry() {
        return this.country;
    }

    @NotNull
    public final MutableLiveData<List<String>> getCountries() {
        return this.countries;
    }

    public final void load() {
        this.country.setValue(getCleanCountryName());
        getCompositeDisposable().add(getFriendsAndAppsInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(ConfirmLocationViewModel$load$1.INSTANCE, ConfirmLocationViewModel$load$2.INSTANCE));
    }

    private final String getCleanCountryName() {
        UserProfile profile = this.sessions.getUser().getProfile();
        Intrinsics.checkExpressionValueIsNotNull(profile, "sessions.user.profile");
        String countryName = profile.getCountryName();
        if (Strings.equalsIgnoreCase(countryName, "Republic of Macedonia")) {
            countryName = this.applicationContext.getString(R.string.country_MK);
        }
        Intrinsics.checkExpressionValueIsNotNull(countryName, "countryName");
        return countryName;
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        getCompositeDisposable().clear();
        super.onCleared();
    }

    private final Single<String> getFriendsAndAppsInfo() {
        Single<String> fromCallable = Single.fromCallable(ConfirmLocationViewModel$getFriendsAndAppsInfo$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Single.fromCallable { \"From Callable\" }");
        return fromCallable;
    }
}
