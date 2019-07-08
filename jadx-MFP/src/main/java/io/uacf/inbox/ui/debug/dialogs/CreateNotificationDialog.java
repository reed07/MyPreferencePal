package io.uacf.inbox.ui.debug.dialogs;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;
import io.uacf.core.api.UacfApiException;
import io.uacf.inbox.R;
import io.uacf.inbox.sdk.UacfNotificationSdkFactory;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotificationDialog implements OnDateSetListener, OnTimeSetListener {
    /* access modifiers changed from: private */
    public AlertDialog alertDialog;
    /* access modifiers changed from: private */
    public EditText categoryView;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public int currentExpirationDateDay;
    /* access modifiers changed from: private */
    public int currentExpirationDateHour;
    /* access modifiers changed from: private */
    public int currentExpirationDateMinute;
    /* access modifiers changed from: private */
    public int currentExpirationDateMonth;
    /* access modifiers changed from: private */
    public int currentExpirationDateYear;
    private Button expirationDateButton;
    private EditText expirationDateView;
    private Button expirationTimeButton;
    private EditText expirationTimeView;
    /* access modifiers changed from: private */
    public LinearLayout mainLayout;
    /* access modifiers changed from: private */
    public NotificationCreatedInterface notificationCreatedInterface;
    /* access modifiers changed from: private */
    public SwitchCompat prioritySwitch;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;

    private static class CreateDebugNotificationAsyncTask extends AsyncTask<Void, Void, Boolean> {
        private String category;
        private WeakReference<CreateNotificationDialog> createNotificationDialogWeakReference;
        private long expirationDateInMilliseconds;
        private boolean priority;

        public CreateDebugNotificationAsyncTask(CreateNotificationDialog createNotificationDialog, boolean z, Calendar calendar, String str) {
            this(createNotificationDialog, z, calendar == null ? 0 : calendar.getTimeInMillis(), str);
        }

        public CreateDebugNotificationAsyncTask(CreateNotificationDialog createNotificationDialog, boolean z, long j, String str) {
            this.priority = z;
            this.expirationDateInMilliseconds = j;
            this.category = str;
            this.createNotificationDialogWeakReference = new WeakReference<>(createNotificationDialog);
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            CreateNotificationDialog createNotificationDialog = (CreateNotificationDialog) this.createNotificationDialogWeakReference.get();
            if (createNotificationDialog != null) {
                createNotificationDialog.progressBar.setVisibility(0);
                createNotificationDialog.mainLayout.setVisibility(4);
            }
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(Void[] voidArr) {
            try {
                new UacfNotificationSdkFactory().newNotificationServiceInstance().createDebugNotification(this.priority, this.expirationDateInMilliseconds, this.category);
                return Boolean.TRUE;
            } catch (UacfApiException e) {
                e.printStackTrace();
                return Boolean.FALSE;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            CreateNotificationDialog createNotificationDialog = (CreateNotificationDialog) this.createNotificationDialogWeakReference.get();
            if (createNotificationDialog != null) {
                ((CreateNotificationDialog) this.createNotificationDialogWeakReference.get()).notificationCreatedInterface.notificationCreated();
                ((CreateNotificationDialog) this.createNotificationDialogWeakReference.get()).alertDialog.dismiss();
                if (bool.booleanValue()) {
                    Toast.makeText(createNotificationDialog.context, R.string.notification_created_successfully_message, 0).show();
                } else {
                    Toast.makeText(createNotificationDialog.context, R.string.notification_created_successfully_message, 0).show();
                }
            }
        }
    }

    public interface NotificationCreatedInterface {
        void notificationCreated();
    }

    public CreateNotificationDialog(Context context2, NotificationCreatedInterface notificationCreatedInterface2) {
        this.context = context2;
        this.notificationCreatedInterface = notificationCreatedInterface2;
    }

    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        this.currentExpirationDateYear = i;
        this.currentExpirationDateMonth = i2;
        this.currentExpirationDateDay = i3;
        this.expirationDateView.setText(String.format("%d/%d/%d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i)}));
    }

    public void onTimeSet(TimePicker timePicker, int i, int i2) {
        this.currentExpirationDateHour = i;
        this.currentExpirationDateMinute = i2;
        Calendar instance = Calendar.getInstance();
        instance.set(11, i);
        instance.set(12, i2);
        this.expirationTimeView.setText(new SimpleDateFormat("h:mm a").format(instance.getTime()));
    }

    public void create() {
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.dialog_create_notification, null);
        this.mainLayout = (LinearLayout) inflate.findViewById(R.id.create_notification_main_layout);
        this.prioritySwitch = (SwitchCompat) inflate.findViewById(R.id.create_notification_priority_switch);
        this.expirationDateButton = (Button) inflate.findViewById(R.id.create_notification_expiration_date_button);
        this.expirationTimeButton = (Button) inflate.findViewById(R.id.create_notification_expiration_time_button);
        this.expirationDateView = (EditText) inflate.findViewById(R.id.create_notification_expiration_date);
        this.expirationTimeView = (EditText) inflate.findViewById(R.id.create_notification_expiration_time);
        this.categoryView = (EditText) inflate.findViewById(R.id.create_notification_category);
        this.progressBar = (ProgressBar) inflate.findViewById(R.id.create_notification_progress_bar);
        Calendar instance = Calendar.getInstance();
        instance.add(6, 60);
        this.currentExpirationDateYear = instance.get(1);
        this.currentExpirationDateMonth = instance.get(2);
        this.currentExpirationDateDay = instance.get(5);
        this.currentExpirationDateHour = instance.get(11);
        this.currentExpirationDateMinute = instance.get(12);
        onDateSet(null, this.currentExpirationDateYear, this.currentExpirationDateMonth, this.currentExpirationDateDay);
        onTimeSet(null, this.currentExpirationDateHour, this.currentExpirationDateMinute);
        this.expirationDateButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Context access$000 = CreateNotificationDialog.this.context;
                CreateNotificationDialog createNotificationDialog = CreateNotificationDialog.this;
                DatePickerDialog datePickerDialog = new DatePickerDialog(access$000, createNotificationDialog, createNotificationDialog.currentExpirationDateYear, CreateNotificationDialog.this.currentExpirationDateMonth, CreateNotificationDialog.this.currentExpirationDateDay);
                datePickerDialog.show();
            }
        });
        this.expirationTimeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Context access$000 = CreateNotificationDialog.this.context;
                CreateNotificationDialog createNotificationDialog = CreateNotificationDialog.this;
                TimePickerDialog timePickerDialog = new TimePickerDialog(access$000, createNotificationDialog, createNotificationDialog.currentExpirationDateHour, CreateNotificationDialog.this.currentExpirationDateMinute, false);
                timePickerDialog.show();
            }
        });
        this.alertDialog = new Builder(this.context).setView(inflate).setPositiveButton(R.string.create, null).setNegativeButton(R.string.cancel, null).create();
        this.alertDialog.show();
        this.alertDialog.getButton(-1).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Calendar calendar;
                String str = null;
                if (CreateNotificationDialog.this.currentExpirationDateYear != 0) {
                    calendar = Calendar.getInstance();
                    calendar.set(CreateNotificationDialog.this.currentExpirationDateYear, CreateNotificationDialog.this.currentExpirationDateMonth, CreateNotificationDialog.this.currentExpirationDateDay, CreateNotificationDialog.this.currentExpirationDateHour, CreateNotificationDialog.this.currentExpirationDateMinute);
                } else {
                    calendar = null;
                }
                String obj = CreateNotificationDialog.this.categoryView.getText().toString();
                CreateNotificationDialog createNotificationDialog = CreateNotificationDialog.this;
                boolean isChecked = createNotificationDialog.prioritySwitch.isChecked();
                if (!TextUtils.isEmpty(obj)) {
                    str = obj;
                }
                new CreateDebugNotificationAsyncTask(createNotificationDialog, isChecked, calendar, str).execute(new Void[0]);
            }
        });
    }
}
