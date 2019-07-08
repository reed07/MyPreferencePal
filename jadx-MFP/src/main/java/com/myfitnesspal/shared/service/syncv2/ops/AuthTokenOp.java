package com.myfitnesspal.shared.service.syncv2.ops;

import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.LoginModel.FacebookData;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleException.UacfScheduleFailedException;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;
import dagger.Lazy;

public class AuthTokenOp extends UacfScheduleOpBase {
    private Lazy<AuthTokenProvider> authTokens;
    private Lazy<LoginModel> loginModel;
    private Lazy<Session> session;
    private Lazy<SignUpModel> signUpModel;

    public AuthTokenOp(Lazy<AuthTokenProvider> lazy, Lazy<SignUpModel> lazy2, Lazy<LoginModel> lazy3, Lazy<Session> lazy4) {
        this.authTokens = lazy;
        this.signUpModel = lazy2;
        this.loginModel = lazy3;
        this.session = lazy4;
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        try {
            AuthTokenProvider authTokenProvider = (AuthTokenProvider) this.authTokens.get();
            String domainUserId = authTokenProvider.getDomainUserId();
            String accessToken = authTokenProvider.getAccessToken();
            if (Strings.isEmpty(domainUserId) || Strings.isEmpty(accessToken)) {
                String email = ((Session) this.session.get()).getUser().getUserV1().getEmail();
                if (Strings.notEmpty(email)) {
                    FacebookData facebookData = ((LoginModel) this.loginModel.get()).getFacebookData();
                    if (facebookData.isValid()) {
                        accessToken = authTokenProvider.loginWithFacebook(facebookData.getUserId(), facebookData.getAccessToken());
                    } else {
                        String password = ((SignUpModel) this.signUpModel.get()).getPassword();
                        if (Strings.notEmpty(password)) {
                            accessToken = authTokenProvider.login(email, password);
                        }
                    }
                    if (Strings.isEmpty(accessToken)) {
                        throw new UacfScheduleFailedException(0, "failed to retrieve token");
                    }
                } else {
                    throw new UacfScheduleFailedException(0, "empty email address??");
                }
            }
            return Result.completed();
        } catch (Exception e) {
            Ln.e(e);
            return Result.retry(new UacfScheduleException(e));
        }
    }
}
