package com.myfitnesspal.feature.restaurantlogging.ui.viewmodel;

import com.myfitnesspal.feature.restaurantlogging.model.MfpMenu;
import com.myfitnesspal.feature.restaurantlogging.service.VenueService;
import com.myfitnesspal.feature.restaurantlogging.task.GetMenusTask;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.List;

public class MenusViewModel extends RunnerViewModel<String> {
    private List<MfpMenu> menus;
    private final Lazy<VenueService> venueService;

    public interface Property extends com.myfitnesspal.feature.appgallery.model.ViewModelWithCtaButton.Property {
        public static final int MENUS_LIST_FETCHED = ViewModelPropertyId.next();
        public static final int MENUS_LIST_FETCH_FAILED = ViewModelPropertyId.next();
    }

    public MenusViewModel(Runner runner, Lazy<VenueService> lazy) {
        super(runner);
        this.venueService = lazy;
    }

    public void load(String... strArr) {
        new GetMenusTask(this.venueService, strArr[0]).run(getRunner());
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (!taskDetails.matches(getRunner(), GetMenusTask.class)) {
            return;
        }
        if (!taskDetails.successful()) {
            setError(taskDetails.getFailure());
            notifyPropertyChanged(Property.MENUS_LIST_FETCH_FAILED);
            return;
        }
        this.menus = (List) taskDetails.getResult();
        setState(State.Loaded);
        notifyPropertyChanged(Property.MENUS_LIST_FETCHED);
    }

    public List<MfpMenu> getMenus() {
        return this.menus;
    }
}
