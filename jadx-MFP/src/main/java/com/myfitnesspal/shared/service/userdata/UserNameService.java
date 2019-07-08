package com.myfitnesspal.shared.service.userdata;

import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.model.v15.UsernameSuggestionObject;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import com.myfitnesspal.shared.service.syncv1.packets.PacketPayloadExtractor;
import com.myfitnesspal.shared.service.syncv1.packets.request.UsernameSuggestionRequestPacket;
import com.uacf.core.util.Function1;
import com.uacf.core.util.ReturningFunction1;
import javax.inject.Inject;
import javax.inject.Provider;

public class UserNameService {
    private final Provider<MfpInformationApi> api;

    @Inject
    public UserNameService(Provider<MfpInformationApi> provider) {
        this.api = provider;
    }

    public void fetchSuggestedUserNames(String str, Function1<UsernameSuggestionObject> function1, ApiErrorCallback apiErrorCallback) {
        ((MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new UsernameSuggestionRequestPacket(str))).postAsync(function1, apiErrorCallback, (ReturningFunction1<TTransform, T>) new PacketPayloadExtractor<TTransform,T>(PacketTypes.SuggestUsernameResponse), new Object[0]);
    }
}
