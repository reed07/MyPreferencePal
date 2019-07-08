package com.myfitnesspal.feature.settings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.ui.adapter.SelectReminderItemAdapter;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;

public class SelectReminder extends MfpActivity {
    @BindView(2131363589)
    ListView listView;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<RemindersService> remindersService;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, SelectReminder.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setTitle(R.string.select_reminder);
        setContentView((int) R.layout.select_reminder);
        bindEventListeners();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        refreshList();
    }

    private void refreshList() {
        int i;
        List availableDefaultReminders = ((RemindersService) this.remindersService.get()).availableDefaultReminders();
        String string = ExtrasUtils.getString(getIntent(), Extras.REMINDER_DESCRIPTION);
        int i2 = 0;
        while (true) {
            if (i2 >= availableDefaultReminders.size()) {
                i = 0;
                break;
            } else if (Strings.equalsIgnoreCase(((ReminderObject) availableDefaultReminders.get(i2)).description(this, this.localizedStringsUtil, getMealNames()), string)) {
                i = i2;
                break;
            } else {
                i2++;
            }
        }
        ListView listView2 = this.listView;
        SelectReminderItemAdapter selectReminderItemAdapter = new SelectReminderItemAdapter(this, R.layout.select_reminder_item, availableDefaultReminders, i, this.localizedStringsUtil, getMealNames());
        listView2.setAdapter(selectReminderItemAdapter);
    }

    private MealNames getMealNames() {
        return getSession().getUser().getMealNames();
    }

    private void bindEventListeners() {
        this.listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                SelectReminder.this.setResult(-1, new Intent().putExtra("reminder", (ReminderObject) SelectReminder.this.listView.getAdapter().getItem(i)));
                SelectReminder.this.finish();
            }
        });
    }
}
