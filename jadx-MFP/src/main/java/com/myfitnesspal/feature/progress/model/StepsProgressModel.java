package com.myfitnesspal.feature.progress.model;

import com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.service.steps.StepService;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Tuple2;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class StepsProgressModel {
    private int average = 0;
    private int count = 0;
    private SortedMap<Date, Integer> graphViewData = new TreeMap();
    private List<Tuple2<Date, Integer>> listViewData;
    private int max = 0;
    private int stepGoal = 0;

    public StepsProgressModel(List<MfpExerciseMetadataForSteps> list, Lazy<StepService> lazy) {
        initGraphViewData(list, lazy);
        initListViewData();
    }

    public SortedMap<Date, Integer> getGraphViewData() {
        return this.graphViewData;
    }

    public List<Tuple2<Date, Integer>> getListViewData() {
        return this.listViewData;
    }

    public int getMax() {
        return this.max;
    }

    public int getAverage() {
        return this.average;
    }

    public int getCount() {
        return this.count;
    }

    public int getStepGoal() {
        return this.stepGoal;
    }

    private void initGraphViewData(List<MfpExerciseMetadataForSteps> list, Lazy<StepService> lazy) {
        Calendar instance = Calendar.getInstance();
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            int i = 0;
            for (MfpExerciseMetadataForSteps mfpExerciseMetadataForSteps : list) {
                if (mfpExerciseMetadataForSteps != null) {
                    int steps = mfpExerciseMetadataForSteps.getSteps();
                    if (steps > this.max) {
                        this.max = steps;
                    }
                    MfpStepSource stepSource = ((StepService) lazy.get()).getStepSource(mfpExerciseMetadataForSteps.getClientId());
                    int stepGoal2 = (stepSource == null || stepSource.getStepGoal() <= 0) ? 0 : stepSource.getStepGoal();
                    if (stepGoal2 > this.stepGoal) {
                        this.stepGoal = stepGoal2;
                    }
                    this.graphViewData.put(mfpExerciseMetadataForSteps.getDate(), Integer.valueOf(steps));
                    this.count++;
                    i += steps;
                }
            }
            Date time = Calendar.getInstance().getTime();
            if (!this.graphViewData.isEmpty()) {
                time = (Date) this.graphViewData.keySet().iterator().next();
            }
            Calendar instance2 = Calendar.getInstance();
            while (time.before(instance.getTime())) {
                if (!this.graphViewData.containsKey(time)) {
                    this.graphViewData.put(time, Integer.valueOf(0));
                }
                instance2.setTimeInMillis(time.getTime());
                instance2.add(5, 1);
                time = instance2.getTime();
            }
            int i2 = this.count;
            if (i2 > 0) {
                this.average = i / i2;
                return;
            }
            return;
        }
        Ln.e("No steps data in database.", new Object[0]);
    }

    private void initListViewData() {
        this.listViewData = new ArrayList();
        ListIterator listIterator = new ArrayList(this.graphViewData.entrySet()).listIterator(CollectionUtils.size((Map<?, ?>) this.graphViewData));
        while (listIterator.hasPrevious()) {
            Entry entry = (Entry) listIterator.previous();
            this.listViewData.add(Tuple2.create(entry.getKey(), entry.getValue()));
        }
    }
}
