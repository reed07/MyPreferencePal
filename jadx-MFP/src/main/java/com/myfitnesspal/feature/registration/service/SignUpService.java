package com.myfitnesspal.feature.registration.service;

import com.myfitnesspal.feature.registration.exception.RegistrationException;
import com.myfitnesspal.feature.registration.model.IdmEmailUniquenessCheck;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v15.EmailUniquenessCheckObject;
import com.myfitnesspal.shared.model.v15.UsernameValidationObject;

public interface SignUpService {
    String getRegionFromCountryCode(String str) throws ApiException;

    void signUp(SignUpModel signUpModel, LoginModel loginModel) throws RegistrationException;

    IdmEmailUniquenessCheck validateEmailAddressIdm(String str) throws ApiException;

    EmailUniquenessCheckObject validateEmailAddressLegacy(String str) throws ApiException;

    UsernameValidationObject validateUsername(String str) throws ApiException;
}
