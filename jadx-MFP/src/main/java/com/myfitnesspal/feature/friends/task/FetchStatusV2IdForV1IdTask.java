package com.myfitnesspal.feature.friends.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpCorrelationIdDetails;
import com.myfitnesspal.shared.service.id.IdService;
import com.myfitnesspal.shared.service.id.IdService.Scope;
import com.uacf.core.util.CollectionUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FetchStatusV2IdForV1IdTask extends EventedTaskBase<String, ApiException> {
    private final Lazy<IdService> idService;
    private final String statusId;

    public static class CompletedEvent extends TaskEventBase<String, ApiException> {
    }

    public FetchStatusV2IdForV1IdTask(Lazy<IdService> lazy, String str) {
        super(CompletedEvent.class);
        this.idService = lazy;
        this.statusId = str;
    }

    /* access modifiers changed from: protected */
    public String exec(Context context) throws ApiException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MfpCorrelationIdDetails(this.statusId, Scope.ACTIVITY_ENTRY));
        List v2IdsFromV1Ids = ((IdService) this.idService.get()).getV2IdsFromV1Ids(arrayList);
        if (CollectionUtils.notEmpty((Collection<?>) v2IdsFromV1Ids)) {
            return ((MfpCorrelationIdDetails) v2IdsFromV1Ids.get(0)).getId();
        }
        return null;
    }
}
