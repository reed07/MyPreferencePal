package com.myfitnesspal.feature.deleteaccount.service;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import javax.inject.Provider;

public class DeleteAccountServiceImpl implements DeleteAccountService {
    private final Provider<MfpV2Api> mfpV2ApiProvider;
    private final Lazy<Session> session;

    public DeleteAccountServiceImpl(Provider<MfpV2Api> provider, Lazy<Session> lazy) {
        this.mfpV2ApiProvider = provider;
        this.session = lazy;
    }

    public boolean deleteAccount() {
        try {
            ((MfpV2Api) this.mfpV2ApiProvider.get()).delete(Uri.DELETE_ACCOUNT);
            ((Session) this.session.get()).logout();
            return true;
        } catch (ApiException unused) {
            return false;
        }
    }
}
