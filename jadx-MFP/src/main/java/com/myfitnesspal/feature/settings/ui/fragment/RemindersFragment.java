package com.myfitnesspal.feature.settings.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.task.DeleteRemindersTask;
import com.myfitnesspal.feature.settings.task.FetchRemindersTask;
import com.myfitnesspal.feature.settings.task.FetchRemindersTask.CompletedEvent;
import com.myfitnesspal.feature.settings.task.UpdateReminderTask;
import com.myfitnesspal.feature.settings.ui.activity.EditReminder;
import com.myfitnesspal.feature.settings.ui.adapter.MyRemindersAdapter;
import com.myfitnesspal.feature.settings.ui.adapter.MyRemindersAdapter.OnReminderAdapterEvents;
import com.myfitnesspal.feature.settings.ui.adapter.MyRemindersAdapter.ReminderItem;
import com.myfitnesspal.feature.settings.ui.adapter.MyRemindersAdapter.ReminderItem.Type;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;

public class RemindersFragment extends MfpFragment {
    private static final int EDIT_ITEM_ID = 101;
    private static final int FAB_SHOW_DELAY_MS = 400;
    /* access modifiers changed from: private */
    public MyRemindersAdapter adapter;
    private OnReminderAdapterEvents adapterListener = new OnReminderAdapterEvents() {
        public void onReminderClicked(ReminderObject reminderObject) {
            if (RemindersFragment.this.editActionMode != null) {
                RemindersFragment.this.adapter.toggleSelectedReminder(reminderObject);
                RemindersFragment.this.updateReminderCountTitle();
                return;
            }
            RemindersFragment.this.syncOnPause = false;
            RemindersFragment.this.finishActionMode();
            RemindersFragment.this.getNavigationHelper().withIntent(EditReminder.newStartIntentForEdit(RemindersFragment.this.getActivity(), reminderObject)).fromFragment(RemindersFragment.this).startActivity(89);
        }

        public boolean onReminderLongClicked(ReminderObject reminderObject) {
            if (RemindersFragment.this.editActionMode != null) {
                return false;
            }
            RemindersFragment.this.enableMultiSelectMode();
            RemindersFragment.this.adapter.addSelectedReminder(reminderObject);
            RemindersFragment.this.updateReminderCountTitle();
            return true;
        }

        public void onReminderActiveChanged(ReminderObject reminderObject) {
            RemindersFragment.this.setBusy(true);
            String eventNameForReminder = ((RemindersService) RemindersFragment.this.remindersService.get()).getEventNameForReminder(reminderObject);
            if (Strings.notEmpty(eventNameForReminder)) {
                AnalyticsService analyticsService = RemindersFragment.this.getAnalyticsService();
                String[] strArr = new String[2];
                strArr[0] = Attributes.CHANGED_STATUS;
                strArr[1] = reminderObject.isActive() ? Attributes.TURNED_ON : Attributes.TURNED_OFF;
                analyticsService.reportEvent(eventNameForReminder, MapUtil.createMap(strArr));
            }
            new UpdateReminderTask(RemindersFragment.this.remindersService, reminderObject).run(RemindersFragment.this.getRunner());
        }

        public void onReminderCheckedEvent(ReminderObject reminderObject, boolean z) {
            RemindersFragment.this.updateReminderCountTitle();
        }
    };
    private List<ReminderObject> currentReminders = new ArrayList();
    /* access modifiers changed from: private */
    public ActionMode editActionMode;
    @BindView(2131362547)
    View fab;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @BindView(2131363152)
    View noReminders;
    @BindView(2131363423)
    RecyclerView recyclerView;
    @Inject
    Lazy<RemindersService> remindersService;
    /* access modifiers changed from: private */
    public boolean syncOnPause = true;
    @Inject
    Lazy<SyncService> syncService;

    private class EditRemindersActionMode implements Callback {
        private static final int DELETE_ITEM_ID = 102;

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return true;
        }

        private EditRemindersActionMode() {
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            menu.add(0, 102, 0, R.string.delete).setIcon(R.drawable.ic_delete_white_24dp);
            RemindersFragment.this.updateFabVisibilityBasedOnActionMode();
            return true;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (menuItem.getItemId() == 102) {
                RemindersFragment.this.deleteSelectedReminders();
                actionMode.finish();
                RemindersFragment.this.getActivity().supportInvalidateOptionsMenu();
            }
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            RemindersFragment.this.editActionMode = null;
            RemindersFragment.this.adapter.setMultiSelectMode(false);
            RemindersFragment.this.updateFabVisibilityBasedOnActionMode();
        }
    }

    public String getAnalyticsScreenTag() {
        return Screens.REMINDERS;
    }

    public static RemindersFragment newInstance() {
        return new RemindersFragment();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.my_reminders, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.adapter = new MyRemindersAdapter(this.remindersService, this.localizedStringsUtil, this.adapterListener, getMealNames());
        this.recyclerView.setAdapter(this.adapter);
        MaterialUtils.addDecoratorForRemovingFabOverlapOnLastItem(this.recyclerView);
        initListeners();
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public void onResume() {
        super.onResume();
        this.syncOnPause = true;
        reloadData();
    }

    public void onPause() {
        super.onPause();
        if (this.syncOnPause) {
            ((SyncService) this.syncService.get()).enqueue(SyncType.Incremental);
        }
    }

    /* access modifiers changed from: private */
    public void finishActionMode() {
        ActionMode actionMode = this.editActionMode;
        if (actionMode != null) {
            actionMode.finish();
            this.editActionMode = null;
        }
    }

    private void initListeners() {
        this.fab.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                RemindersFragment.this.syncOnPause = false;
                RemindersFragment.this.finishActionMode();
                RemindersFragment.this.getNavigationHelper().withIntent(EditReminder.newStartIntentForAdd(RemindersFragment.this.getActivity())).fromFragment(RemindersFragment.this).startActivity(89);
            }
        });
    }

    private void reloadData() {
        setBusy(true);
        new FetchRemindersTask(this.remindersService).run(getRunner());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        getActivity().supportInvalidateOptionsMenu();
        if (i == 89 && i2 != 0) {
            if (i2 == 2 && intent != null) {
                this.adapter.onReminderIdChanged(intent.getLongExtra(EditReminder.EXTRA_ORIGINAL_ID, -1), intent.getLongExtra(EditReminder.EXTRA_NEW_ID, -1));
            }
            reloadData();
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (!ViewUtils.isVisible(this.noReminders)) {
            MenuItemCompat.setShowAsAction(menu.add(0, 101, 0, R.string.edit).setIcon(R.drawable.ic_edit_white_24dp), 2);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 101) {
            return super.onOptionsItemSelected(menuItem);
        }
        enableMultiSelectMode();
        return true;
    }

    public void onPageHidden() {
        super.onPageHidden();
        finishActionMode();
    }

    /* access modifiers changed from: private */
    public void enableMultiSelectMode() {
        MfpActivity mfpActivity = (MfpActivity) getActivity();
        this.editActionMode = getActivity().startActionMode(new EditRemindersActionMode());
        MaterialUtils.cleanActionModeDoneText(mfpActivity);
        this.adapter.setMultiSelectMode(true);
    }

    private void reloadAdapter() {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        Enumerable.forEach((Collection<T>) this.currentReminders, (Function1<T>) new Function1<ReminderObject>() {
            public void execute(ReminderObject reminderObject) {
                String mealName = reminderObject.getMealName();
                switch (reminderObject.getReminderType()) {
                    case 1:
                        if (Strings.isEmpty(mealName) || Strings.equalsIgnoreCase(mealName, RemindersFragment.this.getString(R.string.any_item_for_1_day)) || Strings.equalsIgnoreCase(mealName, RemindersFragment.this.getString(R.string.any_item_for_3_day)) || Strings.equalsIgnoreCase(mealName, RemindersFragment.this.getString(R.string.any_item_for_7_day))) {
                            arrayList3.add(reminderObject);
                            return;
                        } else {
                            arrayList.add(reminderObject);
                            return;
                        }
                    case 2:
                    case 3:
                        arrayList3.add(reminderObject);
                        return;
                    case 4:
                        arrayList2.add(reminderObject);
                        return;
                    default:
                        return;
                }
            }
        });
        ArrayList arrayList4 = new ArrayList();
        addReminderSection(arrayList4, R.string.meals_section_header, arrayList);
        addReminderSection(arrayList4, R.string.weight_section_header, arrayList2);
        addReminderSection(arrayList4, R.string.general_section_header, arrayList3);
        boolean notEmpty = CollectionUtils.notEmpty((Collection<?>) arrayList4);
        ViewUtils.setVisible(notEmpty, this.recyclerView);
        ViewUtils.setVisible(!notEmpty, this.noReminders);
        this.adapter.setItems(arrayList4);
        this.recyclerView.setAdapter(this.adapter);
        getActivity().supportInvalidateOptionsMenu();
    }

    /* access modifiers changed from: private */
    public void updateReminderCountTitle() {
        if (this.editActionMode != null) {
            int size = this.adapter.getSelectedReminders().size();
            if (size == 0) {
                this.editActionMode.setTitle("");
                return;
            }
            this.editActionMode.setTitle(getString(R.string.number_selected, Integer.valueOf(size)));
        }
    }

    private void addReminderSection(List<ReminderItem> list, int i, List<ReminderObject> list2) {
        if (CollectionUtils.notEmpty((Collection<?>) list2)) {
            list.add(new ReminderItem(Type.Header, getString(i)));
            for (ReminderObject reminderItem : list2) {
                list.add(new ReminderItem(Type.Reminder, reminderItem));
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateFabVisibilityBasedOnActionMode() {
        this.fab.postDelayed(new Runnable() {
            public void run() {
                if (RemindersFragment.this.editActionMode == null) {
                    ViewUtils.setVisible(true, RemindersFragment.this.fab);
                    MaterialUtils.applyPaddingToFixedFooter(RemindersFragment.this.getActivity());
                    return;
                }
                ViewUtils.setVisible(false, RemindersFragment.this.fab);
            }
        }, 400);
    }

    /* access modifiers changed from: private */
    public void deleteSelectedReminders() {
        List<ReminderObject> selectedReminders = this.adapter.getSelectedReminders();
        this.currentReminders.removeAll(selectedReminders);
        reloadAdapter();
        new DeleteRemindersTask(this.remindersService, (List<ReminderObject>) new ArrayList<ReminderObject>(selectedReminders)).run(getRunner());
        if (CollectionUtils.notEmpty((Collection<?>) selectedReminders)) {
            for (ReminderObject eventNameForReminder : selectedReminders) {
                String eventNameForReminder2 = ((RemindersService) this.remindersService.get()).getEventNameForReminder(eventNameForReminder);
                if (Strings.notEmpty(eventNameForReminder2)) {
                    getAnalyticsService().reportEvent(eventNameForReminder2, MapUtil.createMap(Attributes.CHANGED_STATUS, "deleted"));
                }
            }
        }
        selectedReminders.clear();
    }

    /* access modifiers changed from: private */
    public String sortKey(ReminderObject reminderObject) {
        StringBuilder sb = new StringBuilder();
        sb.append(reminderObject.getDisplayDescription(getActivity(), this.localizedStringsUtil, this.remindersService, getMealNames()));
        sb.append("-");
        sb.append(reminderObject.getWallClockTime());
        return sb.toString();
    }

    @Subscribe
    public void onFetchRemindersTask(CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            setBusy(false);
            if (completedEvent.successful()) {
                this.currentReminders = (List) completedEvent.getResult();
                Collections.sort(this.currentReminders, new Comparator<ReminderObject>() {
                    public int compare(ReminderObject reminderObject, ReminderObject reminderObject2) {
                        return RemindersFragment.this.sortKey(reminderObject).compareTo(RemindersFragment.this.sortKey(reminderObject2));
                    }
                });
                reloadAdapter();
            }
        }
    }

    private MealNames getMealNames() {
        return getSession().getUser().getMealNames();
    }

    @Subscribe
    public void onDeleteRemindersTask(DeleteRemindersTask.CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            setBusy(false);
        }
    }

    @Subscribe
    public void onUpdateReminderTask(UpdateReminderTask.CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            setBusy(false);
            this.adapter.onReminderIdChanged(completedEvent.getOriginalId(), completedEvent.getNewId());
            reloadData();
        }
    }
}
