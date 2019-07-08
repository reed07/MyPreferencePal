package com.myfitnesspal.feature.profile.ui.viewmodel;

import android.content.Context;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.OfflineData;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.OnlineData;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.uacf.taskrunner.Runner;
import com.uacf.taskrunner.Tasks.Blocking;
import com.uacf.taskrunner.Tasks.Blocking.Unchecked;
import dagger.Lazy;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;

public class MyInfoViewModel extends RunnerViewModel<Void> {
    private OfflineData offlineData;
    private OnlineData onlineData;
    private Set<Integer> seenCards = new HashSet();

    public static class OfflineDataTask extends Unchecked<OfflineData> {
        public static final String NAME = OfflineDataTask.class.getCanonicalName();
        @Inject
        Lazy<ProfileAggregatorService> aggregator;

        public OfflineDataTask() {
            MyFitnessPalApp.getInstance().component().inject(this);
        }

        static boolean loading(Runner runner) {
            return runner.running(NAME);
        }

        /* access modifiers changed from: protected */
        public OfflineData exec(Context context) throws RuntimeException {
            return ((ProfileAggregatorService) this.aggregator.get()).getOfflineData();
        }
    }

    public static class OnlineDataTask extends Blocking<OnlineData, Throwable> {
        public static final String NAME = OnlineDataTask.class.getCanonicalName();
        @Inject
        Lazy<ProfileAggregatorService> aggregator;

        OnlineDataTask() {
            MyFitnessPalApp.getInstance().component().inject(this);
        }

        static boolean loading(Runner runner) {
            return runner.running(NAME);
        }

        /* access modifiers changed from: protected */
        public OnlineData exec(Context context) throws Throwable {
            return ((ProfileAggregatorService) this.aggregator.get()).getOnlineData();
        }
    }

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int OFFLINE_DATA = ViewModelPropertyId.next();
        public static final int ONLINE_DATA = ViewModelPropertyId.next();
    }

    public MyInfoViewModel(Runner runner) {
        super(runner);
    }

    public void load(Void... voidArr) {
        loadOfflineData();
        loadOnlineData();
    }

    public void loadOfflineData() {
        if (!OfflineDataTask.loading(getRunner())) {
            getRunner().run(OfflineDataTask.NAME, new OfflineDataTask());
        }
    }

    public void loadOnlineData() {
        if (!OnlineDataTask.loading(getRunner())) {
            getRunner().run(OnlineDataTask.NAME, new OnlineDataTask());
        }
    }

    public OnlineData getOnlineData() {
        return this.onlineData;
    }

    public OfflineData getOfflineData() {
        return this.offlineData;
    }

    public void markCardSeen(int i) {
        this.seenCards.add(Integer.valueOf(i));
    }

    public boolean cardSeen(int i) {
        return this.seenCards.contains(Integer.valueOf(i));
    }

    private void updateLoadingState() {
        Runner runner = getRunner();
        if (OfflineDataTask.loading(runner) || OnlineDataTask.loading(runner)) {
            setState(State.Loading);
        } else {
            setState((this.onlineData == null || this.offlineData == null) ? State.Error : State.Loaded);
        }
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.isFrom(getRunner())) {
            if (taskDetails.successful()) {
                String taskName = taskDetails.getTaskName();
                if (OnlineDataTask.NAME.equals(taskName)) {
                    this.onlineData = (OnlineData) taskDetails.getResult();
                    notifyPropertyChanged(Property.ONLINE_DATA);
                } else if (OfflineDataTask.NAME.equals(taskName)) {
                    this.offlineData = (OfflineData) taskDetails.getResult();
                    notifyPropertyChanged(Property.OFFLINE_DATA);
                }
            }
            updateLoadingState();
        }
    }
}
