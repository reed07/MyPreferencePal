package com.myfitnesspal.feature.settings.ui.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.search.ui.adapter.MultiSelectEnabledAdapter;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRemindersAdapter extends Adapter<ViewHolderBase> implements MultiSelectEnabledAdapter {
    /* access modifiers changed from: private */
    public boolean enableMultiSelect;
    private List<ReminderItem> items;
    /* access modifiers changed from: private */
    public OnReminderAdapterEvents listener;
    /* access modifiers changed from: private */
    public final Lazy<LocalizedStringsUtil> localizedStringsUtil;
    /* access modifiers changed from: private */
    public final MealNames mealNames;
    /* access modifiers changed from: private */
    public final Lazy<RemindersService> remindersService;
    /* access modifiers changed from: private */
    public Map<Long, ReminderObject> selectedReminders = new HashMap();

    public interface OnReminderAdapterEvents {
        void onReminderActiveChanged(ReminderObject reminderObject);

        void onReminderCheckedEvent(ReminderObject reminderObject, boolean z);

        void onReminderClicked(ReminderObject reminderObject);

        boolean onReminderLongClicked(ReminderObject reminderObject);
    }

    public static class ReminderItem {
        private Object data;
        private Type type;

        public enum Type {
            Header,
            Reminder
        }

        public ReminderItem(Type type2, Object obj) {
            this.data = obj;
            this.type = type2;
        }

        public <T> T getData() {
            return this.data;
        }

        public Type getType() {
            return this.type;
        }
    }

    static class ReminderViewHolder extends ViewHolderBase {
        @BindView(2131362848)
        CompoundButton isActive;
        /* access modifiers changed from: private */
        public MyRemindersAdapter owner;
        @BindView(2131363593)
        CheckBox reminderCheckBox;
        @BindView(2131364030)
        TextView txtReminderDescription;
        @BindView(2131364031)
        TextView txtReminderTime;

        ReminderViewHolder(MyRemindersAdapter myRemindersAdapter, ViewGroup viewGroup) {
            super(R.layout.reminder_entry, viewGroup);
            this.owner = myRemindersAdapter;
        }

        public void setData(ReminderItem reminderItem, int i) {
            final ReminderObject reminderObject = (ReminderObject) reminderItem.getData();
            ViewUtils.setVisible(this.owner.enableMultiSelect, this.reminderCheckBox);
            boolean containsKey = this.owner.selectedReminders.containsKey(Long.valueOf(reminderObject.getLocalId()));
            this.reminderCheckBox.setOnCheckedChangeListener(null);
            this.isActive.setOnCheckedChangeListener(null);
            if (!this.owner.enableMultiSelect) {
                this.reminderCheckBox.setChecked(false);
                ViewUtils.setGone(this.reminderCheckBox);
                ViewUtils.setVisible(this.isActive);
            } else {
                this.reminderCheckBox.setChecked(containsKey);
                ViewUtils.setVisible(this.reminderCheckBox);
                ViewUtils.setGone(this.isActive);
            }
            if (!containsKey || !this.owner.enableMultiSelect) {
                this.itemView.setBackground(null);
            } else {
                this.itemView.setBackgroundColor(this.itemView.getResources().getColor(R.color.custom_toggle_selected));
            }
            this.txtReminderDescription.setText(reminderObject.getDisplayDescription(this.context, this.owner.localizedStringsUtil, this.owner.remindersService, this.owner.mealNames));
            this.txtReminderTime.setText(reminderObject.getReminderFrequencyOrTimeInterval(this.context));
            this.isActive.setEnabled(true ^ this.owner.enableMultiSelect);
            this.isActive.setOnCheckedChangeListener(null);
            this.isActive.setChecked(reminderObject.isActive());
            this.reminderCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        ReminderViewHolder.this.owner.selectedReminders.put(Long.valueOf(reminderObject.getLocalId()), reminderObject);
                    } else {
                        ReminderViewHolder.this.owner.selectedReminders.remove(Long.valueOf(reminderObject.getLocalId()));
                    }
                    ReminderViewHolder.this.owner.notifyDataSetChanged();
                    ReminderViewHolder.this.owner.listener.onReminderCheckedEvent(reminderObject, z);
                }
            });
            this.isActive.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    reminderObject.setIsActive(z);
                    ReminderViewHolder.this.owner.listener.onReminderActiveChanged(reminderObject);
                }
            });
            getParent().setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ReminderViewHolder.this.owner.listener.onReminderClicked(reminderObject);
                }
            });
            getParent().setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View view) {
                    return ReminderViewHolder.this.owner.listener.onReminderLongClicked(reminderObject);
                }
            });
        }
    }

    public class ReminderViewHolder_ViewBinding implements Unbinder {
        private ReminderViewHolder target;

        @UiThread
        public ReminderViewHolder_ViewBinding(ReminderViewHolder reminderViewHolder, View view) {
            this.target = reminderViewHolder;
            reminderViewHolder.reminderCheckBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.select_reminder, "field 'reminderCheckBox'", CheckBox.class);
            reminderViewHolder.isActive = (CompoundButton) Utils.findRequiredViewAsType(view, R.id.is_active, "field 'isActive'", CompoundButton.class);
            reminderViewHolder.txtReminderDescription = (TextView) Utils.findRequiredViewAsType(view, R.id.txtReminderDescription, "field 'txtReminderDescription'", TextView.class);
            reminderViewHolder.txtReminderTime = (TextView) Utils.findRequiredViewAsType(view, R.id.txtReminderTime, "field 'txtReminderTime'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ReminderViewHolder reminderViewHolder = this.target;
            if (reminderViewHolder != null) {
                this.target = null;
                reminderViewHolder.reminderCheckBox = null;
                reminderViewHolder.isActive = null;
                reminderViewHolder.txtReminderDescription = null;
                reminderViewHolder.txtReminderTime = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    static class SectionViewHolder extends ViewHolderBase {
        @BindView(2131363782)
        TextView text;

        SectionViewHolder(ViewGroup viewGroup) {
            super(R.layout.reminders_section_header, viewGroup);
        }

        public void setData(ReminderItem reminderItem, int i) {
            this.text.setText((String) reminderItem.getData());
        }
    }

    public class SectionViewHolder_ViewBinding implements Unbinder {
        private SectionViewHolder target;

        @UiThread
        public SectionViewHolder_ViewBinding(SectionViewHolder sectionViewHolder, View view) {
            this.target = sectionViewHolder;
            sectionViewHolder.text = (TextView) Utils.findRequiredViewAsType(view, R.id.text, "field 'text'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            SectionViewHolder sectionViewHolder = this.target;
            if (sectionViewHolder != null) {
                this.target = null;
                sectionViewHolder.text = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    static abstract class ViewHolderBase extends RecyclerViewHolder<ReminderItem> {
        ViewHolderBase(int i, ViewGroup viewGroup) {
            super(i, viewGroup);
        }
    }

    public MyRemindersAdapter(Lazy<RemindersService> lazy, Lazy<LocalizedStringsUtil> lazy2, OnReminderAdapterEvents onReminderAdapterEvents, MealNames mealNames2) {
        this.remindersService = lazy;
        this.localizedStringsUtil = lazy2;
        this.listener = onReminderAdapterEvents;
        this.mealNames = mealNames2;
    }

    public void setItems(List<ReminderItem> list) {
        this.items = list;
        notifyDataSetChanged();
    }

    public void onReminderIdChanged(long j, long j2) {
        if (j != -1 && j2 != -1) {
            if (this.selectedReminders.containsKey(Long.valueOf(j))) {
                ReminderObject reminderObject = (ReminderObject) this.selectedReminders.remove(Long.valueOf(j));
                reminderObject.setLocalId(j2);
                this.selectedReminders.put(Long.valueOf(j), reminderObject);
            }
            for (ReminderItem reminderItem : this.items) {
                if (reminderItem.getType() == Type.Reminder) {
                    ReminderObject reminderObject2 = (ReminderObject) reminderItem.getData();
                    if (reminderObject2.getLocalId() == j) {
                        reminderObject2.setLocalId(j2);
                    }
                }
            }
        }
    }

    public List<ReminderObject> getSelectedReminders() {
        return new ArrayList(this.selectedReminders.values());
    }

    public void addSelectedReminder(ReminderObject reminderObject) {
        this.selectedReminders.put(Long.valueOf(reminderObject.getLocalId()), reminderObject);
        notifyDataSetChanged();
    }

    public void toggleSelectedReminder(ReminderObject reminderObject) {
        if (this.selectedReminders.containsKey(Long.valueOf(reminderObject.getLocalId()))) {
            this.selectedReminders.remove(Long.valueOf(reminderObject.getLocalId()));
        } else {
            this.selectedReminders.put(Long.valueOf(reminderObject.getLocalId()), reminderObject);
        }
        notifyDataSetChanged();
    }

    public void onBindViewHolder(ViewHolderBase viewHolderBase, int i) {
        viewHolderBase.setData(this.items.get(i), i);
    }

    public ViewHolderBase onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == Type.Header.ordinal()) {
            return new SectionViewHolder(viewGroup);
        }
        return new ReminderViewHolder(this, viewGroup);
    }

    public int getItemCount() {
        return CollectionUtils.size((Collection<?>) this.items);
    }

    public void setMultiSelectMode(boolean z) {
        if (this.enableMultiSelect != z) {
            if (!z) {
                this.selectedReminders.clear();
            }
            this.enableMultiSelect = z;
            notifyDataSetChanged();
        }
    }

    public int getItemViewType(int i) {
        return ((ReminderItem) this.items.get(i)).getType().ordinal();
    }
}
