package com.myfitnesspal.shared.service.syncv1;

import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.db.DbConnectionManager;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SynchronizationRequest_MembersInjector implements MembersInjector<SynchronizationRequest> {
    private final Provider<ApiDeviceTokenProvider> apiDeviceTokenProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<BinaryEncoder> encoderProvider;
    private final Provider<LoginModel> loginModelProvider;
    private final Provider<SignUpModel> signUpModelProvider;
    private final Provider<SyncPointerService> syncPointerServiceProvider;
    private final Provider<byte[]> uuidBytesProvider;

    public SynchronizationRequest_MembersInjector(Provider<ApiDeviceTokenProvider> provider, Provider<SyncPointerService> provider2, Provider<SignUpModel> provider3, Provider<LoginModel> provider4, Provider<BinaryEncoder> provider5, Provider<byte[]> provider6, Provider<AppSettings> provider7, Provider<DbConnectionManager> provider8) {
        this.apiDeviceTokenProvider = provider;
        this.syncPointerServiceProvider = provider2;
        this.signUpModelProvider = provider3;
        this.loginModelProvider = provider4;
        this.encoderProvider = provider5;
        this.uuidBytesProvider = provider6;
        this.appSettingsProvider = provider7;
        this.dbConnectionManagerProvider = provider8;
    }

    public static MembersInjector<SynchronizationRequest> create(Provider<ApiDeviceTokenProvider> provider, Provider<SyncPointerService> provider2, Provider<SignUpModel> provider3, Provider<LoginModel> provider4, Provider<BinaryEncoder> provider5, Provider<byte[]> provider6, Provider<AppSettings> provider7, Provider<DbConnectionManager> provider8) {
        SynchronizationRequest_MembersInjector synchronizationRequest_MembersInjector = new SynchronizationRequest_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return synchronizationRequest_MembersInjector;
    }

    public void injectMembers(SynchronizationRequest synchronizationRequest) {
        injectApiDeviceTokenProvider(synchronizationRequest, (ApiDeviceTokenProvider) this.apiDeviceTokenProvider.get());
        injectSyncPointerService(synchronizationRequest, DoubleCheck.lazy(this.syncPointerServiceProvider));
        injectSignUpModel(synchronizationRequest, DoubleCheck.lazy(this.signUpModelProvider));
        injectLoginModel(synchronizationRequest, DoubleCheck.lazy(this.loginModelProvider));
        injectEncoder(synchronizationRequest, (BinaryEncoder) this.encoderProvider.get());
        injectUuidBytes(synchronizationRequest, (byte[]) this.uuidBytesProvider.get());
        injectAppSettings(synchronizationRequest, DoubleCheck.lazy(this.appSettingsProvider));
        injectDbConnectionManager(synchronizationRequest, DoubleCheck.lazy(this.dbConnectionManagerProvider));
    }

    public static void injectApiDeviceTokenProvider(SynchronizationRequest synchronizationRequest, ApiDeviceTokenProvider apiDeviceTokenProvider2) {
        synchronizationRequest.apiDeviceTokenProvider = apiDeviceTokenProvider2;
    }

    public static void injectSyncPointerService(SynchronizationRequest synchronizationRequest, Lazy<SyncPointerService> lazy) {
        synchronizationRequest.syncPointerService = lazy;
    }

    public static void injectSignUpModel(SynchronizationRequest synchronizationRequest, Lazy<SignUpModel> lazy) {
        synchronizationRequest.signUpModel = lazy;
    }

    public static void injectLoginModel(SynchronizationRequest synchronizationRequest, Lazy<LoginModel> lazy) {
        synchronizationRequest.loginModel = lazy;
    }

    public static void injectEncoder(SynchronizationRequest synchronizationRequest, BinaryEncoder binaryEncoder) {
        synchronizationRequest.encoder = binaryEncoder;
    }

    public static void injectUuidBytes(SynchronizationRequest synchronizationRequest, byte[] bArr) {
        synchronizationRequest.uuidBytes = bArr;
    }

    public static void injectAppSettings(SynchronizationRequest synchronizationRequest, Lazy<AppSettings> lazy) {
        synchronizationRequest.appSettings = lazy;
    }

    public static void injectDbConnectionManager(SynchronizationRequest synchronizationRequest, Lazy<DbConnectionManager> lazy) {
        synchronizationRequest.dbConnectionManager = lazy;
    }
}
