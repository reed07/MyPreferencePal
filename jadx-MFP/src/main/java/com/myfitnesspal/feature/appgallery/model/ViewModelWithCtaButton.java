package com.myfitnesspal.feature.appgallery.model;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.util.AppStateUtil;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.uacf.taskrunner.Runner;

public abstract class ViewModelWithCtaButton extends RunnerViewModel<Void> {
    private CtaMode mode = CtaMode.Connect;

    public enum CtaMode {
        Install,
        Disconnect,
        Connect
    }

    public interface Property {
        public static final int CTA_ACTION = ViewModelPropertyId.next();
    }

    public abstract MfpPlatformApp getApp();

    /* access modifiers changed from: protected */
    public abstract Context getContext();

    public void load(Void... voidArr) {
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
    }

    protected ViewModelWithCtaButton(Runner runner) {
        super(runner);
    }

    public String getCtaText() {
        Context context = getContext();
        switch (this.mode) {
            case Install:
                return context.getString(R.string.install);
            case Disconnect:
                return context.getString(R.string.disconnect_partner_app);
            default:
                return context.getString(R.string.connect);
        }
    }

    public final CtaMode getCtaMode() {
        return this.mode;
    }

    /* access modifiers changed from: protected */
    public final void setCtaMode(CtaMode ctaMode) {
        if (this.mode != ctaMode) {
            this.mode = ctaMode;
            notifyPropertyChanged(Property.CTA_ACTION);
        }
    }

    /* access modifiers changed from: protected */
    public void refreshCtaMode() {
        Context context = getContext();
        MfpPlatformApp app = getApp();
        if (!AppStateUtil.isInstallable(app) || !AppStateUtil.canDetectInstalled(app) || AppStateUtil.isInstalled(context, app)) {
            setCtaMode(CtaMode.Connect);
        } else {
            setCtaMode(CtaMode.Install);
        }
    }
}
