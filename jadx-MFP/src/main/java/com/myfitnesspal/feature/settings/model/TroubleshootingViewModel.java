package com.myfitnesspal.feature.settings.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.net.Uri;
import com.myfitnesspal.feature.consents.model.Resource;
import com.myfitnesspal.feature.consents.model.Resource.Loading;
import com.myfitnesspal.feature.settings.repository.TroubleshootingRepository;
import com.myfitnesspal.feature.settings.service.TroubleshootingService;
import com.myfitnesspal.feature.settings.util.FileUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0015\u001a\u00020\fJ\u0010\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u000e\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#J\b\u0010$\u001a\u00020\u001dH\u0014R \u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0015\u001a\u00020\fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R \u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000e¨\u0006%"}, d2 = {"Lcom/myfitnesspal/feature/settings/model/TroubleshootingViewModel;", "Landroid/arch/lifecycle/AndroidViewModel;", "applicationContext", "Landroid/app/Application;", "troubleshootingRepository", "Lcom/myfitnesspal/feature/settings/repository/TroubleshootingRepository;", "troubleshootingService", "Lcom/myfitnesspal/feature/settings/service/TroubleshootingService;", "(Landroid/app/Application;Lcom/myfitnesspal/feature/settings/repository/TroubleshootingRepository;Lcom/myfitnesspal/feature/settings/service/TroubleshootingService;)V", "checkDiagnosticCodeResponse", "Landroid/arch/lifecycle/MutableLiveData;", "Lcom/myfitnesspal/feature/consents/model/Resource;", "", "getCheckDiagnosticCodeResponse$app_googleRelease", "()Landroid/arch/lifecycle/MutableLiveData;", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/disposables/CompositeDisposable;", "compositeDisposable$delegate", "Lkotlin/Lazy;", "diagnosticCode", "getDiagnosticCode$app_googleRelease", "()Ljava/lang/String;", "setDiagnosticCode$app_googleRelease", "(Ljava/lang/String;)V", "uploadDiagnosticCodeResponse", "getUploadDiagnosticCodeResponse$app_googleRelease", "checkDiagnosticCode", "", "clearFile", "fileUri", "Landroid/net/Uri;", "getDiagnostics", "context", "Landroid/content/Context;", "onCleared", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: TroubleshootingViewModel.kt */
public final class TroubleshootingViewModel extends AndroidViewModel {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TroubleshootingViewModel.class), "compositeDisposable", "getCompositeDisposable()Lio/reactivex/disposables/CompositeDisposable;"))};
    @NotNull
    private final MutableLiveData<Resource<String>> checkDiagnosticCodeResponse = new MutableLiveData<>();
    private final Lazy compositeDisposable$delegate = LazyKt.lazy(TroubleshootingViewModel$compositeDisposable$2.INSTANCE);
    @NotNull
    public String diagnosticCode;
    /* access modifiers changed from: private */
    public final TroubleshootingRepository troubleshootingRepository;
    private final TroubleshootingService troubleshootingService;
    @NotNull
    private final MutableLiveData<Resource<String>> uploadDiagnosticCodeResponse = new MutableLiveData<>();

    private final CompositeDisposable getCompositeDisposable() {
        Lazy lazy = this.compositeDisposable$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (CompositeDisposable) lazy.getValue();
    }

    @Inject
    public TroubleshootingViewModel(@NotNull Application application, @NotNull TroubleshootingRepository troubleshootingRepository2, @NotNull TroubleshootingService troubleshootingService2) {
        Intrinsics.checkParameterIsNotNull(application, "applicationContext");
        Intrinsics.checkParameterIsNotNull(troubleshootingRepository2, "troubleshootingRepository");
        Intrinsics.checkParameterIsNotNull(troubleshootingService2, "troubleshootingService");
        super(application);
        this.troubleshootingRepository = troubleshootingRepository2;
        this.troubleshootingService = troubleshootingService2;
    }

    @NotNull
    public final MutableLiveData<Resource<String>> getCheckDiagnosticCodeResponse$app_googleRelease() {
        return this.checkDiagnosticCodeResponse;
    }

    @NotNull
    public final MutableLiveData<Resource<String>> getUploadDiagnosticCodeResponse$app_googleRelease() {
        return this.uploadDiagnosticCodeResponse;
    }

    @NotNull
    public final String getDiagnosticCode$app_googleRelease() {
        String str = this.diagnosticCode;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diagnosticCode");
        }
        return str;
    }

    public final void setDiagnosticCode$app_googleRelease(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.diagnosticCode = str;
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        getCompositeDisposable().clear();
    }

    public final void checkDiagnosticCode(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "diagnosticCode");
        this.diagnosticCode = str;
        this.checkDiagnosticCodeResponse.setValue(new Loading());
        getCompositeDisposable().add(this.troubleshootingRepository.checkDiagnosticCode(str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new TroubleshootingViewModel$checkDiagnosticCode$1(this), new TroubleshootingViewModel$checkDiagnosticCode$2(this)));
    }

    public final void getDiagnostics(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        ObjectRef objectRef = new ObjectRef();
        objectRef.element = null;
        this.uploadDiagnosticCodeResponse.setValue(new Loading());
        CompositeDisposable compositeDisposable = getCompositeDisposable();
        TroubleshootingService troubleshootingService2 = this.troubleshootingService;
        String str = this.diagnosticCode;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diagnosticCode");
        }
        compositeDisposable.add(troubleshootingService2.getDiagnostics(str).flatMap(new TroubleshootingViewModel$getDiagnostics$1(this, objectRef, context)).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new TroubleshootingViewModel$getDiagnostics$2(this, objectRef), new TroubleshootingViewModel$getDiagnostics$3(this, objectRef)));
    }

    /* access modifiers changed from: private */
    public final void clearFile(Uri uri) {
        File file = FileUtils.INSTANCE.getFile(uri);
        if (file != null) {
            file.delete();
        }
    }
}
