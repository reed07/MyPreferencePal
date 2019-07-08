package com.myfitnesspal.feature.appgallery.model;

import android.content.Context;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.appgallery.service.QueryAppListTask;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelLifecycle;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.model.v2.MfpPlatformAppOptions.AppType;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OurOtherAppsViewModel extends RunnerViewModel<Void> implements ViewModelLifecycle {
    private Lazy<AppGalleryService> appGalleryService;
    private Context context;
    private List<OurOtherAppsItemViewModel> modelList = new ArrayList();

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int APP_LIST = ViewModelPropertyId.next();
    }

    public void onPause() {
    }

    public OurOtherAppsViewModel(Context context2, Runner runner, Lazy<AppGalleryService> lazy) {
        super(runner);
        this.context = context2.getApplicationContext();
        this.appGalleryService = lazy;
    }

    public void onResume() {
        loadIfNotLoaded(new Void[0]);
    }

    public void load(Void... voidArr) {
        new QueryAppListTask(AppType.UACF, this.appGalleryService).run(getRunner());
        setState(State.Loading);
    }

    public List<OurOtherAppsItemViewModel> getModelList() {
        return this.modelList;
    }

    private void setModelList(List<MfpPlatformApp> list) {
        List<OurOtherAppsItemViewModel> list2;
        if (list == null) {
            list2 = new ArrayList<>();
        } else {
            list2 = initModelList(list);
        }
        this.modelList = list2;
        notifyPropertyChanged(Property.APP_LIST);
    }

    private List<OurOtherAppsItemViewModel> initModelList(List<MfpPlatformApp> list) {
        return Enumerable.select((Collection<T>) list, (ReturningFunction1<U, T>) new ReturningFunction1() {
            public final Object execute(Object obj) {
                return OurOtherAppsViewModel.lambda$initModelList$0(OurOtherAppsViewModel.this, (MfpPlatformApp) obj);
            }
        });
    }

    public static /* synthetic */ OurOtherAppsItemViewModel lambda$initModelList$0(OurOtherAppsViewModel ourOtherAppsViewModel, MfpPlatformApp mfpPlatformApp) throws RuntimeException {
        return new OurOtherAppsItemViewModel(ourOtherAppsViewModel.context, mfpPlatformApp, ourOtherAppsViewModel.getRunner());
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.isFrom(getRunner()) && QueryAppListTask.matches(taskDetails)) {
            if (taskDetails.getFailure() == null) {
                setModelList((List) taskDetails.getResult());
                setState(State.Loaded);
                return;
            }
            setError(taskDetails.getFailure());
        }
    }
}
