package com.uacf.core.tasks;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class TaskDisposer {
    private List<Task> tasks = new ArrayList();

    public void add(@NonNull Task task) {
        synchronized (this) {
            this.tasks.add(task);
        }
    }

    public void dispose() {
        synchronized (this) {
            for (Task cancel : this.tasks) {
                cancel.cancel();
            }
            this.tasks.clear();
        }
    }
}
