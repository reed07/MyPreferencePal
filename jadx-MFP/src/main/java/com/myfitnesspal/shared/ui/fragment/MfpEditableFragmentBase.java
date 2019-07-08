package com.myfitnesspal.shared.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.event.BusyEvent;
import com.myfitnesspal.shared.service.syncv2.StartSyncEvent;
import com.myfitnesspal.shared.ui.view.EmptyStateView;
import com.myfitnesspal.shared.ui.view.EmptyStateView.Type;
import com.myfitnesspal.shared.ui.view.GlideHideProgressListener;
import com.myfitnesspal.shared.ui.view.SwipeRefreshListView;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.ListViewUtils.TopBottomScrollListener;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class MfpEditableFragmentBase<T> extends MfpFragment {
    protected static final int ACTION_ADD = 1004;
    protected static final int ACTION_COMPLETE = 1003;
    protected static final int ACTION_DELETE = 1002;
    protected static final int ACTION_EDIT = 1001;
    protected static final int ACTION_SORT = 1005;
    private final String IS_CHANGING_CONFIGURATION = "is_changing_configuration";
    /* access modifiers changed from: private */
    public ActionMode actionMode;
    @BindView(2131362012)
    protected Button addItemFooterButton;
    /* access modifiers changed from: private */
    public final Set<Integer> checkedItems = new HashSet();
    @BindView(2131362459)
    EmptyStateView emptyListState;
    private String filterString;
    private boolean hideMenuItems;
    private boolean isChangingConfiguration = false;
    /* access modifiers changed from: private */
    public boolean isEditing = false;
    private boolean isLoading = false;
    /* access modifiers changed from: private */
    public boolean isScrolledToTop = true;
    private EditListAdapter<T> listAdapter;
    @BindView(16908298)
    ListView listView;
    private final OnItemClickListener onListItemClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            MfpEditableFragmentBase.this.onListItemClick(adapterView, view, i, j);
        }
    };
    @Nullable
    @BindView(2131363426)
    SwipeRefreshLayout refreshLayout;

    private class EditActionMode implements Callback {
        private EditActionMode() {
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MfpEditableFragmentBase.this.actionMode = actionMode;
            MenuItemCompat.setShowAsAction(menu.add(0, 1002, 0, R.string.delete).setIcon(R.drawable.ic_delete_white_24dp), 2);
            MfpEditableFragmentBase.this.setActionModeTitle();
            return true;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            menu.findItem(1002).setVisible(MfpEditableFragmentBase.this.checkedItems.size() > 0);
            return true;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case 1002:
                    MfpEditableFragmentBase.this.onActionDeleteClickedInternal();
                    actionMode.finish();
                    break;
                case 1003:
                    MfpEditableFragmentBase.this.onActionCompleteClicked();
                    actionMode.finish();
                    break;
                default:
                    return false;
            }
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            MfpEditableFragmentBase.this.doneEditing();
            ListViewUtils.notifyDataSetChanged(MfpEditableFragmentBase.this.listView);
            MfpEditableFragmentBase.this.actionModeDestroyed();
        }
    }

    public static abstract class EditListAdapter<T> extends ArrayAdapter<T> {
        /* access modifiers changed from: private */
        public final MfpEditableFragmentBase fragment;
        private final LayoutInflater inflater = ((LayoutInflater) getContext().getSystemService("layout_inflater"));

        /* access modifiers changed from: protected */
        public abstract void configureView(T t, RowViewHolder rowViewHolder, int i);

        public void onListViewRecreated(ListView listView) {
        }

        protected EditListAdapter(MfpEditableFragmentBase mfpEditableFragmentBase, Context context) {
            super(context, -1);
            this.fragment = mfpEditableFragmentBase;
        }

        /* access modifiers changed from: private */
        public void refill(List<T> list) {
            setNotifyOnChange(false);
            clear();
            setNotifyOnChange(true);
            addAll(list);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.inflater.inflate(R.layout.generic_list_item_with_checkbox, null);
                RowViewHolder rowViewHolder = new RowViewHolder();
                rowViewHolder.image = (ImageView) ViewUtils.findById(view, R.id.image_view);
                rowViewHolder.checkbox = (CheckBox) ViewUtils.findById(view, R.id.multiSelectCheckBox);
                rowViewHolder.title = (TextView) ViewUtils.findById(view, R.id.text_primary);
                rowViewHolder.summary = (TextView) ViewUtils.findById(view, R.id.text_secondary);
                rowViewHolder.calories = (TextView) ViewUtils.findById(view, R.id.txtCalories);
                rowViewHolder.imageContainer = ViewUtils.findById(view, R.id.image_container);
                rowViewHolder.progress = (ProgressBar) ViewUtils.findById(view, R.id.progress);
                rowViewHolder.imageLoadListener = new GlideHideProgressListener();
                view.setTag(rowViewHolder);
            }
            RowViewHolder rowViewHolder2 = (RowViewHolder) view.getTag();
            Object item = getItem(i);
            ViewUtils.setVisible(this.fragment.isEditing, rowViewHolder2.checkbox);
            rowViewHolder2.checkbox.setOnCheckedChangeListener(null);
            ViewUtils.setGone(rowViewHolder2.summary);
            ViewUtils.setGone(rowViewHolder2.calories);
            if (this.fragment.isEditing) {
                rowViewHolder2.checkbox.setChecked(this.fragment.checkedItems.contains(Integer.valueOf(i)));
                rowViewHolder2.checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        if (z) {
                            EditListAdapter.this.fragment.checkedItems.add(Integer.valueOf(i));
                        } else {
                            EditListAdapter.this.fragment.checkedItems.remove(Integer.valueOf(i));
                        }
                        EditListAdapter.this.fragment.actionMode.invalidate();
                        EditListAdapter.this.fragment.setActionModeTitle();
                    }
                });
            }
            configureView(item, rowViewHolder2, i);
            return view;
        }

        /* access modifiers changed from: protected */
        public final MfpEditableFragmentBase getFragment() {
            return this.fragment;
        }

        /* access modifiers changed from: protected */
        public final LayoutInflater getInflater() {
            return this.inflater;
        }
    }

    public static class RowViewHolder {
        public TextView calories;
        public CheckBox checkbox;
        public ImageView image;
        public View imageContainer;
        public GlideHideProgressListener imageLoadListener;
        public ProgressBar progress;
        public TextView summary;
        public TextView title;
    }

    /* access modifiers changed from: protected */
    public void actionModeDestroyed() {
    }

    /* access modifiers changed from: protected */
    public abstract boolean addToFilteredList(T t, String str);

    /* access modifiers changed from: protected */
    public List<T> applySort(List<T> list) {
        return list;
    }

    /* access modifiers changed from: protected */
    public boolean disableGenericItemClickHandling() {
        return false;
    }

    /* access modifiers changed from: protected */
    public OnClickListener getEmptyStatePrimaryButtonListener() {
        return null;
    }

    /* access modifiers changed from: protected */
    public OnClickListener getEmptyStateSecondaryButtonListener() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract Type getEmptyStateViewType();

    /* access modifiers changed from: protected */
    public abstract List<T> getItems();

    /* access modifiers changed from: protected */
    public int getOptionsMenuShowActionFor(int i) {
        return 2;
    }

    /* access modifiers changed from: protected */
    public boolean isReadOnly() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onActionAddClicked() {
    }

    /* access modifiers changed from: protected */
    public void onActionCompleteClicked() {
    }

    /* access modifiers changed from: protected */
    public void onActionDeleteClicked(List<T> list) {
    }

    /* access modifiers changed from: protected */
    public void onActionSortClicked() {
    }

    /* access modifiers changed from: protected */
    public void onItemClicked(T t) {
    }

    /* access modifiers changed from: protected */
    public void onScrolledToBottom() {
    }

    /* access modifiers changed from: protected */
    public abstract EditListAdapter<T> recreateAdapter();

    /* access modifiers changed from: protected */
    public boolean showFooterButton() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean wantsAddMenuItem() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean wantsSortMenuItem() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean wantsSwipeToRefresh() {
        return false;
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.editable_fragment, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        MaterialUtils.enableAppBarScrollIfLollipop(getActivity(), this.listView);
        this.isChangingConfiguration = BundleUtils.getBoolean(bundle, "is_changing_configuration");
        this.isEditing = BundleUtils.getBoolean(bundle, Extras.IS_EDITING);
        String string = BundleUtils.getString(bundle, Extras.CHECKED_ITEMS);
        this.checkedItems.clear();
        if (Strings.notEmpty(string)) {
            for (String tryParseInt : string.split(",")) {
                this.checkedItems.add(Integer.valueOf(NumberUtils.tryParseInt(tryParseInt)));
            }
        }
        if (this.isEditing) {
            startEditing();
        }
        if (this.refreshLayout != null) {
            updateSwipeToRefreshEnabled();
            this.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                public void onRefresh() {
                    MfpEditableFragmentBase.this.fetchData();
                }
            });
            ((SwipeRefreshListView) this.listView).setRefreshLayout(this.refreshLayout);
        }
        if (!disableGenericItemClickHandling()) {
            this.listView.setOnItemClickListener(this.onListItemClickListener);
        }
        EditListAdapter<T> editListAdapter = this.listAdapter;
        if (editListAdapter != null) {
            editListAdapter.onListViewRecreated(getListView());
        }
        ListViewUtils.addScrollTopBottomListener(this.listView, new TopBottomScrollListener() {
            public void onScrolledToTopChanged(boolean z) {
                MfpEditableFragmentBase.this.isScrolledToTop = z;
                MfpEditableFragmentBase.this.updateSwipeToRefreshEnabled();
            }

            public void onScrolledToBottom() {
                MfpEditableFragmentBase.this.onScrolledToBottom();
            }
        });
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (!this.hideMenuItems) {
            if (wantsAddMenuItem()) {
                MenuItemCompat.setShowAsAction(menu.add(0, 1004, 0, R.string.add).setIcon(R.drawable.ic_add_white_24dp), getOptionsMenuShowActionFor(1004));
            }
            if (!isReadOnly() && !ListViewUtils.isEmpty(this.listView.getAdapter())) {
                if (wantsSortMenuItem()) {
                    MenuItemCompat.setShowAsAction(menu.add(0, 1005, 0, R.string.sort_order), getOptionsMenuShowActionFor(1005));
                }
                MenuItemCompat.setShowAsAction(menu.add(0, 1001, 1, R.string.edit).setIcon(R.drawable.ic_edit_white_24dp), getOptionsMenuShowActionFor(1001));
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(Extras.IS_EDITING, this.isEditing);
        bundle.putBoolean("is_changing_configuration", true);
        bundle.putString(Extras.CHECKED_ITEMS, Strings.join(",", (Collection<T>) this.checkedItems));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 1001:
                onActionEditClicked();
                invalidateOptionsMenuInternal();
                return true;
            case 1002:
                onActionDeleteClickedInternal();
                return true;
            case 1003:
                onActionCompleteClicked();
                invalidateOptionsMenuInternal();
                return true;
            case 1004:
                onActionAddClicked();
                invalidateOptionsMenuInternal();
                return true;
            case 1005:
                onActionSortClicked();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            invalidateOptionsMenuInternal();
        } else if (this.isEditing && !this.isChangingConfiguration) {
            this.actionMode.finish();
        }
        this.isChangingConfiguration = false;
    }

    public void setFilterStrings(String str) {
        if (!Strings.equalsIgnoreCase(str, this.filterString)) {
            this.filterString = str;
            refresh();
        }
    }

    private void invalidateOptionsMenuInternal() {
        if (isEnabled()) {
            getActivity().supportInvalidateOptionsMenu();
        }
    }

    /* access modifiers changed from: protected */
    public void onListItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        onItemClicked(this.listView.getAdapter().getItem(i));
    }

    public final void refresh() {
        if (getView() != null) {
            if (this.listAdapter == null) {
                this.listAdapter = recreateAdapter();
            }
            ListAdapter adapter = this.listView.getAdapter();
            EditListAdapter<T> editListAdapter = this.listAdapter;
            if (adapter != editListAdapter) {
                this.listView.setAdapter(editListAdapter);
            }
            notifyChanged(applySort(applyFilter()));
        }
    }

    @Subscribe
    public void onSyncFinishedEvent(UacfScheduleFinishedInfo uacfScheduleFinishedInfo) {
        refresh();
    }

    /* access modifiers changed from: protected */
    public void onActionEditClicked() {
        this.checkedItems.clear();
        startEditing();
    }

    /* access modifiers changed from: private */
    public void onActionDeleteClickedInternal() {
        onActionDeleteClicked(Enumerable.select((Collection<T>) this.checkedItems, (ReturningFunction1<U, T>) new ReturningFunction1<T, Integer>() {
            public T execute(Integer num) {
                return MfpEditableFragmentBase.this.listView.getAdapter().getItem(num.intValue());
            }
        }));
        postEvent(new StartSyncEvent());
        refresh();
    }

    /* access modifiers changed from: protected */
    public boolean isLoading() {
        return this.isLoading;
    }

    /* access modifiers changed from: protected */
    public void setLoading(boolean z) {
        this.isLoading = z;
        SwipeRefreshLayout swipeRefreshLayout = this.refreshLayout;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(z);
        }
        refresh();
        updateViewVisibility();
        updateSwipeToRefreshEnabled();
        postEvent(new BusyEvent(z));
    }

    /* access modifiers changed from: protected */
    public ListView getListView() {
        return this.listView;
    }

    private void startEditing() {
        this.refreshLayout.setEnabled(false);
        getActivity().startActionMode(new EditActionMode());
        this.isEditing = true;
        updateSwipeToRefreshEnabled();
        ListViewUtils.notifyDataSetChanged(this.listView);
    }

    /* access modifiers changed from: private */
    public void doneEditing() {
        this.refreshLayout.setEnabled(wantsSwipeToRefresh());
        this.isEditing = false;
        this.checkedItems.clear();
        updateSwipeToRefreshEnabled();
        ListViewUtils.notifyDataSetChanged(this.listView);
    }

    /* access modifiers changed from: protected */
    public void fetchData() {
        refresh();
    }

    public void hideMenuItems(boolean z) {
        this.hideMenuItems = z;
    }

    /* access modifiers changed from: private */
    public void setActionModeTitle() {
        if (this.actionMode != null) {
            int size = this.checkedItems.size();
            this.actionMode.setTitle(getString(size == 0 ? R.string.select_item : R.string.number_selected, Integer.valueOf(size)));
        }
    }

    /* access modifiers changed from: private */
    public void updateSwipeToRefreshEnabled() {
        SwipeRefreshLayout swipeRefreshLayout = this.refreshLayout;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setEnabled(wantsSwipeToRefresh() && this.isScrolledToTop && !this.isLoading && !this.isEditing);
        }
    }

    private void updateViewVisibility() {
        EditListAdapter<T> editListAdapter = this.listAdapter;
        if (editListAdapter == null || editListAdapter.getCount() != 0) {
            showLoadedState();
        } else if (this.isLoading) {
            showLoadingState();
        } else {
            showEmptyState();
        }
    }

    private void showEmptyState() {
        setupEmptyState();
        ViewUtils.setGone(this.listView);
        ViewUtils.setVisible(this.emptyListState);
        ViewUtils.setGone(this.addItemFooterButton);
    }

    private void showLoadedState() {
        ViewUtils.setVisible(this.listView);
        ViewUtils.setGone(this.emptyListState);
        ViewUtils.setVisible(showFooterButton(), this.addItemFooterButton);
    }

    private void showLoadingState() {
        ViewUtils.setGone(this.listView);
        ViewUtils.setGone(this.emptyListState);
        ViewUtils.setGone(this.addItemFooterButton);
    }

    private void setupEmptyState() {
        boolean notEmpty = Strings.notEmpty(this.filterString);
        this.emptyListState.initializeForType(getEmptyStateViewType(), getEmptyStatePrimaryButtonListener(), getEmptyStateSecondaryButtonListener());
        this.emptyListState.toggleTitleVisibility(!notEmpty);
        if (notEmpty) {
            this.emptyListState.setMessageText(R.string.no_menus_available);
        }
    }

    /* access modifiers changed from: protected */
    public List<T> applyFilter() {
        ArrayList arrayList = new ArrayList();
        List items = getItems();
        if (!Strings.isEmpty(this.filterString)) {
            String lowerCase = this.filterString.toLowerCase();
            for (Object next : items) {
                if (addToFilteredList(next, lowerCase)) {
                    arrayList.add(next);
                }
            }
        } else if (CollectionUtils.notEmpty((Collection<?>) items)) {
            arrayList.addAll(items);
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void notifyChanged(List<T> list) {
        this.listAdapter.refill(list);
        updateViewVisibility();
        invalidateOptionsMenuInternal();
    }
}
