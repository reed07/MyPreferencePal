package com.myfitnesspal.feature.progress.ui.viewmodel;

import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.progress.model.StepsProgressModel;
import com.myfitnesspal.feature.progress.service.ProgressService;
import com.myfitnesspal.feature.progress.task.GetMeasurementGraphDataTask;
import com.myfitnesspal.feature.progress.task.GetStepsGraphDataTask;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.model.ProgressEntryViewModel;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.util.Measurements;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;

public class ProgressViewModel extends RunnerViewModel<Void> {
    private long currentTaskId = -1;
    private DataType dataType;
    @Inject
    Lazy<DiaryService> diaryService;
    @Inject
    Lazy<UserHeightService> heightService;
    private List<ProgressEntryViewModel> normalEntryData;
    @Inject
    Lazy<ProgressService> progressService;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<StepService> stepService;
    private StepsProgressModel stepsEntryData;
    @Inject
    Lazy<UserWeightService> weightService;

    private enum DataType {
        Normal,
        Steps
    }

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int NORMAL_ENTRY_DATA = ViewModelPropertyId.next();
        public static final int STEPS_ENTRY_DATA = ViewModelPropertyId.next();
    }

    public ProgressViewModel(Runner runner) {
        super(runner);
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public void load(String str, int i) {
        getRunner().cancel(this.currentTaskId);
        if (Measurements.isSteps(str)) {
            this.dataType = DataType.Steps;
            this.normalEntryData = null;
            this.currentTaskId = new GetStepsGraphDataTask(this.stepService, this.session, i).run(getRunner());
            return;
        }
        this.dataType = DataType.Normal;
        this.stepsEntryData = null;
        GetMeasurementGraphDataTask getMeasurementGraphDataTask = new GetMeasurementGraphDataTask(this.progressService, this.weightService, this.heightService, str, i);
        this.currentTaskId = getMeasurementGraphDataTask.run(getRunner());
    }

    public void load(Void... voidArr) {
        throw new UnsupportedOperationException("see load(String measurementType, int period)");
    }

    public List<ProgressEntryViewModel> getNormalEntryModel() {
        return this.normalEntryData;
    }

    public StepsProgressModel getStepsEntryModel() {
        return this.stepsEntryData;
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.matches(getRunner(), GetStepsGraphDataTask.class)) {
            onStepTaskCompleted(taskDetails);
        } else if (taskDetails.matches(getRunner(), GetMeasurementGraphDataTask.class)) {
            onMeasurementTaskCompleted(taskDetails);
        }
    }

    private void onStepTaskCompleted(TaskDetails taskDetails) {
        if (!taskDetails.successful()) {
            setError(taskDetails.getFailure());
            return;
        }
        this.stepsEntryData = (StepsProgressModel) taskDetails.getResult();
        notifyPropertyChanged(Property.STEPS_ENTRY_DATA);
        setState(State.Loaded);
    }

    private void onMeasurementTaskCompleted(TaskDetails taskDetails) {
        if (!taskDetails.successful()) {
            setError(taskDetails.getFailure());
            return;
        }
        this.normalEntryData = (List) taskDetails.getResult();
        notifyPropertyChanged(Property.NORMAL_ENTRY_DATA);
        setState(State.Loaded);
    }
}
