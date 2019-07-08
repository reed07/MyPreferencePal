package com.myfitnesspal.feature.registration.service;

import com.myfitnesspal.feature.registration.exception.RegistrationException;
import com.myfitnesspal.feature.registration.model.LoginModel.FacebookData;

public interface SignInService {
    void signIn(String str, String str2) throws RegistrationException;

    void signInToFacebook(String str, FacebookData facebookData) throws RegistrationException;

    boolean verifyFacebookSignIn(String str, String str2);

    boolean verifySignIn(String str, String str2);
}
