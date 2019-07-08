package com.myfitnesspal.shared.ui.widget;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import com.myfitnesspal.feature.home.ui.adapter.NewsFeedAdapter.AdapterOperationListener;
import com.uacf.core.database.DatabaseTableImpl;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CatchIOOBLinearLayoutManager extends LinearLayoutManager implements AdapterOperationListener {
    private static final String ADDED_ENTRY = "added_entry";
    private static final int MAX_ENTRIES = 10;
    private static final String REFRESHED_ITEMS = "refreshed_items";
    private static final String REMOVED_ENTRY = "removed_entry";
    private final Activity activity;
    private final RecordedOperations recordedOperations = new RecordedOperations(10);

    private class RecordedOperations {
        private final int maxSize;
        private final List<String> recentOperations;

        public RecordedOperations(int i) {
            this.maxSize = i;
            this.recentOperations = new ArrayList(i);
        }

        public void addOperation(String str) {
            if (this.recentOperations.size() == this.maxSize) {
                this.recentOperations.remove(0);
            }
            this.recentOperations.add(str);
        }

        public String toString() {
            return String.format("Recent Operations [%s]", new Object[]{Strings.join(DatabaseTableImpl.CREATE_TABLE_DELIMITER, (Collection<T>) this.recentOperations)});
        }
    }

    public CatchIOOBLinearLayoutManager(Activity activity2) {
        super(activity2);
        this.activity = activity2;
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("IOOB in RecyclerView. Activity : ");
            sb.append(this.activity.getLocalClassName());
            sb.append(" ");
            sb.append(this.recordedOperations.toString());
            Ln.e(new IndexOutOfBoundsException(sb.toString()), e, new Object[0]);
        }
    }

    public void onItemAdded() {
        this.recordedOperations.addOperation(ADDED_ENTRY);
    }

    public void onItemRemoved() {
        this.recordedOperations.addOperation(REMOVED_ENTRY);
    }

    public void onItemsRefreshed() {
        this.recordedOperations.addOperation(REFRESHED_ITEMS);
    }
}
