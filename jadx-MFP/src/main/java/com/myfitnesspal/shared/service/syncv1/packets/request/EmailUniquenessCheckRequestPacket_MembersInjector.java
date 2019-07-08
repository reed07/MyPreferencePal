package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.service.syncv1.ApiDeviceTokenProvider;
import com.myfitnesspal.shared.validation.Validator;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class EmailUniquenessCheckRequestPacket_MembersInjector implements MembersInjector<EmailUniquenessCheckRequestPacket> {
    private final Provider<ApiDeviceTokenProvider> apiDeviceTokenProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<Validator> emailValidatorProvider;
    private final Provider<byte[]> uuidBytesProvider;
    private final Provider<Long> versionCodeProvider;

    public EmailUniquenessCheckRequestPacket_MembersInjector(Provider<Long> provider, Provider<ApiDeviceTokenProvider> provider2, Provider<byte[]> provider3, Provider<AppSettings> provider4, Provider<Validator> provider5) {
        this.versionCodeProvider = provider;
        this.apiDeviceTokenProvider = provider2;
        this.uuidBytesProvider = provider3;
        this.appSettingsProvider = provider4;
        this.emailValidatorProvider = provider5;
    }

    public static MembersInjector<EmailUniquenessCheckRequestPacket> create(Provider<Long> provider, Provider<ApiDeviceTokenProvider> provider2, Provider<byte[]> provider3, Provider<AppSettings> provider4, Provider<Validator> provider5) {
        EmailUniquenessCheckRequestPacket_MembersInjector emailUniquenessCheckRequestPacket_MembersInjector = new EmailUniquenessCheckRequestPacket_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return emailUniquenessCheckRequestPacket_MembersInjector;
    }

    public void injectMembers(EmailUniquenessCheckRequestPacket emailUniquenessCheckRequestPacket) {
        ApiRequestPacketImpl_MembersInjector.injectVersionCode(emailUniquenessCheckRequestPacket, ((Long) this.versionCodeProvider.get()).longValue());
        ApiRequestPacketImpl_MembersInjector.injectApiDeviceTokenProvider(emailUniquenessCheckRequestPacket, (ApiDeviceTokenProvider) this.apiDeviceTokenProvider.get());
        ApiRequestPacketImpl_MembersInjector.injectUuidBytes(emailUniquenessCheckRequestPacket, (byte[]) this.uuidBytesProvider.get());
        ApiRequestPacketImpl_MembersInjector.injectAppSettings(emailUniquenessCheckRequestPacket, (AppSettings) this.appSettingsProvider.get());
        injectEmailValidator(emailUniquenessCheckRequestPacket, (Validator) this.emailValidatorProvider.get());
    }

    public static void injectEmailValidator(EmailUniquenessCheckRequestPacket emailUniquenessCheckRequestPacket, Validator validator) {
        emailUniquenessCheckRequestPacket.emailValidator = validator;
    }
}
