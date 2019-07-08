package com.myfitnesspal.feature.debug.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.analytics.AnalyticsServiceWithHistory;
import com.myfitnesspal.shared.service.analytics.MfpEventHistoryItem;
import com.myfitnesspal.shared.service.analytics.MultiAnalyticsService;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase.EditListAdapter;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase.RowViewHolder;
import com.myfitnesspal.shared.ui.view.EmptyStateView.Type;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AnalyticsEventsFragment extends MfpEditableFragmentBase<MfpEventHistoryItem> {
    private List<MfpEventHistoryItem> eventsContainer;

    /* access modifiers changed from: protected */
    public boolean addToFilteredList(MfpEventHistoryItem mfpEventHistoryItem, String str) {
        return true;
    }

    public OnClickListener getEmptyStatePrimaryButtonListener() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isReadOnly() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onItemClicked(MfpEventHistoryItem mfpEventHistoryItem) {
    }

    /* access modifiers changed from: protected */
    public boolean wantsSwipeToRefresh() {
        return true;
    }

    public static AnalyticsEventsFragment newInstance(int i) {
        AnalyticsEventsFragment analyticsEventsFragment = new AnalyticsEventsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Extras.SELECTED_INDEX, i);
        analyticsEventsFragment.setArguments(bundle);
        return analyticsEventsFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public void onResume() {
        super.onResume();
        fetchData();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getListView().setChoiceMode(1);
    }

    public Type getEmptyStateViewType() {
        return Type.AnalyticEvent;
    }

    /* access modifiers changed from: protected */
    public EditListAdapter<MfpEventHistoryItem> recreateAdapter() {
        initContainerIfNecessary();
        return new EditListAdapter<MfpEventHistoryItem>(this, getActivity()) {
            /* access modifiers changed from: protected */
            public void configureView(MfpEventHistoryItem mfpEventHistoryItem, RowViewHolder rowViewHolder, int i) {
                rowViewHolder.title.setText(Strings.toString(mfpEventHistoryItem.getEventName()));
                Map attributes = mfpEventHistoryItem.getAttributes();
                int size = CollectionUtils.size(attributes);
                String str = "%s, %s %s";
                Object[] objArr = new Object[3];
                objArr[0] = DateTimeUtils.format(Format.newIso8601DateTimeFormat().toPattern(), mfpEventHistoryItem.getTimestamp());
                objArr[1] = Integer.valueOf(size);
                objArr[2] = size == 1 ? "attribute" : "attributes";
                String format = String.format(str, objArr);
                boolean z = AnalyticsEventsFragment.this.getListView().isItemChecked(i) && size > 0;
                rowViewHolder.summary.setSingleLine(!z);
                StringBuilder sb = new StringBuilder(format);
                if (z) {
                    for (String str2 : attributes.keySet()) {
                        sb.append("\n");
                        sb.append("    ");
                        sb.append(str2);
                        sb.append(" = \"");
                        sb.append((String) attributes.get(str2));
                        sb.append("\"");
                    }
                }
                rowViewHolder.summary.setText(sb.toString());
                ViewUtils.setVisible(true, rowViewHolder.summary);
            }
        };
    }

    /* access modifiers changed from: protected */
    public List<MfpEventHistoryItem> getItems() {
        return new ArrayList(this.eventsContainer);
    }

    private void initContainerIfNecessary() {
        if (this.eventsContainer == null) {
            this.eventsContainer = new ArrayList();
        }
    }

    /* access modifiers changed from: protected */
    public void onListItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ListView listView = (ListView) adapterView;
        super.onListItemClick(listView, view, i, j);
        listView.setItemChecked(i, true);
        ListViewUtils.notifyDataSetChanged(listView);
    }

    /* access modifiers changed from: protected */
    public void fetchData() {
        if (!isLoading()) {
            initContainerIfNecessary();
            this.eventsContainer.clear();
            setLoading(true);
            AnalyticsServiceWithHistory findAnalyticsServiceWithHistory = findAnalyticsServiceWithHistory();
            if (findAnalyticsServiceWithHistory != null) {
                List eventHistory = findAnalyticsServiceWithHistory.getEventHistory();
                if (eventHistory != null) {
                    this.eventsContainer.addAll(eventHistory);
                }
            }
            setLoading(false);
        }
    }

    private AnalyticsServiceWithHistory findAnalyticsServiceWithHistory() {
        AnalyticsService analyticsService = getAnalyticsService();
        if (analyticsService instanceof MultiAnalyticsService) {
            List services = ((MultiAnalyticsService) analyticsService).getServices();
            int i = BundleUtils.getInt(getArguments(), Extras.SELECTED_INDEX, -1);
            if (i >= 0 && i < CollectionUtils.size((Collection<?>) services)) {
                analyticsService = (AnalyticsService) services.get(i);
            }
        }
        if (analyticsService instanceof AnalyticsServiceWithHistory) {
            return (AnalyticsServiceWithHistory) analyticsService;
        }
        return null;
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItemCompat.setShowAsAction(menu.add(0, 1002, 0, R.string.delete).setIcon(R.drawable.ic_delete_white_24dp), 2);
    }

    /* access modifiers changed from: protected */
    public void onActionDeleteClicked(List<MfpEventHistoryItem> list) {
        AnalyticsServiceWithHistory findAnalyticsServiceWithHistory = findAnalyticsServiceWithHistory();
        if (findAnalyticsServiceWithHistory != null) {
            findAnalyticsServiceWithHistory.clearHistory();
            this.eventsContainer.clear();
            refresh();
        }
    }

    /* access modifiers changed from: protected */
    public void onScrolledToBottom() {
        fetchData();
    }
}
