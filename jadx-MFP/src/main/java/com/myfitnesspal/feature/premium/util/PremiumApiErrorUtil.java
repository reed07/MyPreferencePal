package com.myfitnesspal.feature.premium.util;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.PremiumAPIException;
import com.myfitnesspal.shared.event.AlertEvent;
import com.squareup.otto.Bus;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class PremiumApiErrorUtil {
    private Context context;
    private Bus messageBus;

    public PremiumApiErrorUtil(Context context2, Bus bus) {
        this.messageBus = bus;
        this.context = context2;
    }

    public void showAlertForApiError(Exception exc, String str) {
        this.messageBus.post(new AlertEvent(getErrorMessageForApiError(exc, str)));
    }

    public void showAlertForApiError(List<Exception> list, String str) {
        this.messageBus.post(new AlertEvent(getErrorMessageForApiError(list, str)));
    }

    public String getErrorMessageForApiError(Exception exc, String str) {
        boolean z = false;
        if (exc instanceof ApiException) {
            ApiException apiException = (ApiException) exc;
            if (apiException.getStatusCode() == PremiumAPIException.HTTP_ERROR_CODE && Strings.equals(apiException.getApiError().getError(), PremiumAPIException.API_ERROR_CODE)) {
                z = true;
            }
        }
        return z ? this.context.getString(R.string.premium_role_rejection) : str;
    }

    public String getErrorMessageForApiError(List<Exception> list, String str) {
        Exception exc;
        if (CollectionUtils.size((Collection<?>) list) > 0) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                exc = (Exception) it.next();
                if (exc instanceof ApiException) {
                    break;
                }
            }
        }
        exc = null;
        return exc == null ? str : getErrorMessageForApiError(exc, str);
    }
}
