package com.myfitnesspal.shared.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.db.DatasetManager;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.Exercise;

public class QueryStockExerciseByMasterIdTask extends Unchecked<Exercise> {
    private DatasetManager datasetManager;
    private DbConnectionManager dbConnectionManager;
    private long masterId;

    public static class CompletedEvent extends TaskEventBase.Unchecked<Exercise> {
    }

    public QueryStockExerciseByMasterIdTask(long j, DbConnectionManager dbConnectionManager2, DatasetManager datasetManager2) {
        super((TaskEventBase) new CompletedEvent());
        this.masterId = j;
        this.dbConnectionManager = dbConnectionManager2;
        this.datasetManager = datasetManager2;
    }

    /* access modifiers changed from: protected */
    public Exercise exec(Context context) throws RuntimeException {
        return this.dbConnectionManager.genericDbAdapter().fetchStockExerciseByMasterId(this.masterId, this.datasetManager.stockDataset());
    }
}
