package com.myfitnesspal.feature.appgallery.model;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.model.ViewModelWithCtaButton.CtaMode;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.appgallery.service.QuerySingleAppTask;
import com.myfitnesspal.feature.appgallery.util.AppGalleryUtil;
import com.myfitnesspal.feature.appgallery.util.AppStateUtil;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.mixin.GoogleFitMixin;
import com.myfitnesspal.feature.externalsync.impl.shealth.mixin.SHealthMixin;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.model.v2.MfpAppImage;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.ArrayList;

public class AppDetailViewModel extends ViewModelWithCtaButton {
    private MfpPlatformApp app;
    private final Lazy<AppGalleryService> appGalleryService;
    private final Context context;
    private final GoogleFitClient googleFit;
    private final SHealthConnection samsungHealth;
    private boolean valid;

    public AppDetailViewModel(MfpActivity mfpActivity, MfpPlatformApp mfpPlatformApp, Runner runner, Lazy<AppGalleryService> lazy) {
        super(runner);
        this.context = mfpActivity.getApplicationContext();
        this.valid = mfpPlatformApp != null;
        if (!this.valid) {
            mfpPlatformApp = new MfpPlatformApp();
        }
        this.app = mfpPlatformApp;
        this.appGalleryService = lazy;
        this.googleFit = ((GoogleFitMixin) mfpActivity.mixin(GoogleFitMixin.class)).getClient();
        this.samsungHealth = ((SHealthMixin) mfpActivity.mixin(SHealthMixin.class)).getConnection();
        refreshCtaMode();
    }

    public void load(Void... voidArr) {
        setState(State.Loading);
        new QuerySingleAppTask(String.valueOf(this.app.getId()), this.appGalleryService).run(getRunner());
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.isFrom(getRunner()) && QuerySingleAppTask.matches(taskDetails)) {
            if (taskDetails.getFailure() == null) {
                this.app = (MfpPlatformApp) taskDetails.getResult();
                refreshCtaMode();
                setState(State.Loaded);
                return;
            }
            setError(taskDetails.getFailure());
        }
    }

    public String getActivityTitle() {
        return Strings.toStringOrDefaultIfEmpty(getName(), this.context.getString(R.string.apps_gallery_header));
    }

    public boolean isValid() {
        return this.valid;
    }

    public ArrayList<MfpAppImage> getScreenshots() {
        return this.app.getScreenshotImages();
    }

    public String getName() {
        return Strings.trimmed((Object) this.app.getName());
    }

    public String getPartnerId() {
        return this.app.getClientId();
    }

    public String getPartnerName() {
        return this.app.getClientName();
    }

    public String getCompanyName() {
        return String.format(this.context.getString(R.string.app_by), new Object[]{Strings.trimmed((Object) this.app.getClientName())});
    }

    public String getDescription() {
        return Strings.trimmed((Object) this.app.getAppLongDescription());
    }

    public String getPurchaseUri() {
        return this.app.getPurchaseUrl();
    }

    public String getIconUri() {
        MfpAppImage iconImage = this.app.getIconImage();
        if (iconImage != null) {
            return iconImage.getFilename();
        }
        return null;
    }

    public MfpPlatformApp getApp() {
        return this.app;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.context;
    }

    public boolean isPurchasable() {
        return this.app.isPurchasable() && Strings.notEmpty(this.app.getPurchaseUrl());
    }

    /* access modifiers changed from: protected */
    public void refreshCtaMode() {
        if (AppStateUtil.isConnected(this.app) || (AppGalleryUtil.isGoogleFit(this.app) && AppGalleryUtil.isGoogleFitConnected(this.googleFit)) || (AppGalleryUtil.isSHealth(this.app) && this.samsungHealth.isPaired())) {
            setCtaMode(CtaMode.Disconnect);
        } else {
            super.refreshCtaMode();
        }
    }
}
