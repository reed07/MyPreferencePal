package com.myfitnesspal.feature.appgallery.model;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.api.ExerciseTrackingAppRecommendation;
import com.myfitnesspal.feature.appgallery.util.AppStateUtil;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import com.myfitnesspal.shared.model.v2.MfpAppImage;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.uacf.core.util.Strings;

public class XPromoViewModel extends BaseViewModel {
    private MfpPlatformApp app;
    private Context context;
    private ViewMode mode = ViewMode.Install;
    private ExerciseTrackingAppRecommendation model;

    public interface Property {
        public static final int VIEW_MODE = "VIEW_MODE".hashCode();
    }

    public enum ViewMode {
        Install,
        Connect,
        Done
    }

    public int getActivityTitle() {
        return R.string.better_way_to_track;
    }

    public XPromoViewModel(Context context2, ExerciseTrackingAppRecommendation exerciseTrackingAppRecommendation) {
        this.context = context2.getApplicationContext();
        this.model = exerciseTrackingAppRecommendation;
        this.app = (MfpPlatformApp) exerciseTrackingAppRecommendation.getApps().get(0);
        refresh();
    }

    public void refresh() {
        if (!AppStateUtil.isInstalled(this.context, this.app)) {
            setMode(ViewMode.Install);
        } else if (!AppStateUtil.isConnected(this.app)) {
            setMode(ViewMode.Connect);
        } else {
            setMode(ViewMode.Done);
        }
    }

    public ViewMode getViewMode() {
        return this.mode;
    }

    public String getIconUri() {
        MfpAppImage iconImage = this.app.getIconImage();
        if (iconImage != null) {
            return iconImage.getFilename();
        }
        return null;
    }

    public String getTitle() {
        return Strings.toString(this.model.getHeadline());
    }

    public String getBody() {
        return Strings.toString(this.model.getDescription());
    }

    public int getCtaLabel() {
        return this.mode == ViewMode.Install ? R.string.install : R.string.connect;
    }

    public String getExerciseId() {
        return this.model.getExercise().getId();
    }

    public String getAppName() {
        return this.app.getName();
    }

    public MfpPlatformApp getApp() {
        return this.app;
    }

    private void setMode(ViewMode viewMode) {
        if (viewMode != this.mode) {
            this.mode = viewMode;
            notifyPropertyChanged(Property.VIEW_MODE);
        }
    }
}
