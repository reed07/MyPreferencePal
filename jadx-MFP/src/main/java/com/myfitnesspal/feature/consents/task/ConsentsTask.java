package com.myfitnesspal.feature.consents.task;

import android.content.Context;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.Strings;

public class ConsentsTask extends EventedTaskBase<Boolean, ApiException> {
    private final ConsentsService consentsService;
    private final CountryService countryService;
    private final Session session;

    public static class CompletedEvent extends TaskEventBase<Boolean, ApiException> {
    }

    public ConsentsTask(Session session2, ConsentsService consentsService2, CountryService countryService2) {
        super((TaskEventBase) new CompletedEvent());
        this.session = session2;
        this.consentsService = consentsService2;
        this.countryService = countryService2;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        Session session2 = this.session;
        if (session2 != null) {
            User user = session2.getUser();
            if (user != null && user.hasMasterDatabaseId() && user.isLoggedIn() && Strings.notEmpty(user.getProfile().getCountryName())) {
                String countryName = user.getProfile().getCountryName();
                if (Strings.notEmpty(countryName) && Strings.notEmpty(this.countryService.getShortNameFromLongName(this.session.getUser().getProfile().getCountryName()))) {
                    return (Boolean) this.consentsService.isConsentsRequired(this.countryService.getShortNameFromLongName(countryName)).onErrorReturn($$Lambda$ConsentsTask$W1kWd3JPWDH2VyGrRryc7uSvajo.INSTANCE).blockingGet();
                }
            }
        }
        return Boolean.valueOf(false);
    }
}
