package com.myfitnesspal.feature.settings.task;

import android.content.Context;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitStepsUtils;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.externalsync.impl.shealth.util.SHealthUtil;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.uacf.core.util.CollectionUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LoadStepsSourcesTask extends EventedTaskBase<LoadResult, ApiException> {
    private final Lazy<AppGalleryService> appGalleryService;
    private final GoogleFitClient googleFitClient;
    private final SHealthConnection sHealthConnection;
    private final Session session;
    private final Lazy<StepService> stepService;

    public static class Event extends TaskEventBase<LoadResult, ApiException> {
    }

    public static class LoadResult {
        public List<MfpPlatformApp> apps;
        public List<MfpStepSource> stepSources;
    }

    public LoadStepsSourcesTask(Session session2, Lazy<AppGalleryService> lazy, Lazy<StepService> lazy2, GoogleFitClient googleFitClient2, SHealthConnection sHealthConnection2) {
        super((TaskEventBase) new Event());
        this.session = session2;
        this.appGalleryService = lazy;
        this.stepService = lazy2;
        this.googleFitClient = googleFitClient2;
        this.sHealthConnection = sHealthConnection2;
    }

    /* access modifiers changed from: protected */
    public LoadResult exec(Context context) throws ApiException {
        LoadResult loadResult = new LoadResult();
        loadResult.apps = ((AppGalleryService) this.appGalleryService.get()).getAllAppsFromCache();
        if (CollectionUtils.isEmpty((Collection<?>) loadResult.apps)) {
            loadResult.apps = ((AppGalleryService) this.appGalleryService.get()).getAppList("all");
        }
        loadResult.stepSources = ((StepService) this.stepService.get()).checkAndUpdateDeviceIdForClientSideStepSources(getEnabledClientSideStepSources());
        return loadResult;
    }

    private List<MfpStepSource> getEnabledClientSideStepSources() {
        ArrayList arrayList = new ArrayList();
        if (this.googleFitClient.isEnabled()) {
            arrayList.add(GoogleFitStepsUtils.createGoogleFitStepSource(this.session));
        }
        if (this.sHealthConnection.isPaired()) {
            arrayList.add(SHealthUtil.createStepSource(this.session));
        }
        return arrayList;
    }
}
