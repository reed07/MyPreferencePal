package com.myfitnesspal.feature.goals.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.Strings;
import java.util.List;

public class SaveMealNamesTask extends EventedTaskBase<Boolean, Exception> {
    private static final String TASK_NAME_BASE = "SaveMealNamesTask";
    private final List<String> mealNames;
    private final Session session;

    public static class CompletedEvent extends TaskEventBase<Boolean, Exception> {
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public SaveMealNamesTask(Session session2, MealNames mealNames2) {
        super(CompletedEvent.class);
        this.session = session2;
        this.mealNames = mealNames2.getNames();
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws Exception {
        User user = this.session.getUser();
        List<String> list = this.mealNames;
        user.setProperty("meal_names", Strings.arrayOfStringsToCSV((String[]) list.toArray(new String[list.size()])));
        user.updatePropertyNamed("meal_names");
        return Boolean.valueOf(true);
    }
}
