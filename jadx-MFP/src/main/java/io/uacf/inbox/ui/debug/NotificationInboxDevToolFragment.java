package io.uacf.inbox.ui.debug;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Switch;
import io.uacf.inbox.R;
import io.uacf.inbox.sdk.UacfNotificationSdk;
import io.uacf.inbox.sdk.UacfNotificationSdkFactory;
import io.uacf.inbox.ui.debug.adapters.InboxCursorRecyclerViewAdapter;
import io.uacf.inbox.ui.debug.adapters.InboxCursorRecyclerViewAdapter.NotificationCheckedCallback;
import io.uacf.inbox.ui.debug.dialogs.CreateNotificationDialog;
import io.uacf.inbox.ui.debug.dialogs.CreateNotificationDialog.NotificationCreatedInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NotificationInboxDevToolFragment extends Fragment {
    /* access modifiers changed from: private */
    public static int MAX_PRIORITY_COUNT_MAX_VALUE = 9;
    /* access modifiers changed from: private */
    public static int MAX_PRIORITY_COUNT_MIN_VALUE = 0;
    public static String TAG = "NIDevToolFragment";
    private InboxCursorRecyclerViewAdapter adapter;
    /* access modifiers changed from: private */
    public LinearLayout buttonLayout;
    /* access modifiers changed from: private */
    public Map<String, Integer> checkedNotifications;
    private FloatingActionButton createFab;
    private Button delete;
    private LayoutManager layoutManager;
    private Button markExpired;
    private Button markPending;
    private Button markRead;
    private Button markUnread;
    /* access modifiers changed from: private */
    public Button maxPriorityCount;
    private NotificationCheckedCallback notificationCheckedCallback = new NotificationCheckedCallback() {
        public void notificationChecked(boolean z, String str, int i) {
            if (z) {
                NotificationInboxDevToolFragment.this.checkedNotifications.put(str, Integer.valueOf(i));
            } else {
                NotificationInboxDevToolFragment.this.checkedNotifications.remove(str);
            }
            NotificationInboxDevToolFragment.this.buttonLayout.setVisibility(NotificationInboxDevToolFragment.this.checkedNotifications.size() > 0 ? 0 : 8);
        }
    };
    /* access modifiers changed from: private */
    public NotificationCreatedInterface notificationCreatedInterface = new NotificationCreatedInterface() {
        public void notificationCreated() {
            NotificationInboxDevToolFragment.this.resetAdapter();
        }
    };
    private UacfNotificationSdk notificationSdk;
    private Switch onePriorityPerCategorySwitch;
    private RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_notification_inbox, viewGroup, false);
        this.notificationSdk = new UacfNotificationSdkFactory().newSdkInstance();
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.list);
        this.swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.swipeContainer);
        this.buttonLayout = (LinearLayout) inflate.findViewById(R.id.button_layout);
        this.delete = (Button) inflate.findViewById(R.id.delete);
        this.markExpired = (Button) inflate.findViewById(R.id.markExpired);
        this.markPending = (Button) inflate.findViewById(R.id.markPending);
        this.markRead = (Button) inflate.findViewById(R.id.markRead);
        this.markUnread = (Button) inflate.findViewById(R.id.markUnread);
        this.createFab = (FloatingActionButton) inflate.findViewById(R.id.create_fab);
        this.onePriorityPerCategorySwitch = (Switch) inflate.findViewById(R.id.one_priority_per_category);
        this.maxPriorityCount = (Button) inflate.findViewById(R.id.max_priority_count);
        this.recyclerView.setHasFixedSize(true);
        this.checkedNotifications = new HashMap();
        initList();
        this.swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                NotificationInboxDevToolFragment.this.resetAdapter();
                NotificationInboxDevToolFragment.this.swipeRefreshLayout.setRefreshing(false);
            }
        });
        this.createFab.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new CreateNotificationDialog(NotificationInboxDevToolFragment.this.getActivity(), NotificationInboxDevToolFragment.this.notificationCreatedInterface).create();
            }
        });
        this.onePriorityPerCategorySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                UacfNotificationSdk.getSettings().setLimitPriorityToOnePerCategory(z);
                NotificationInboxDevToolFragment.this.resetAdapter();
            }
        });
        this.maxPriorityCount.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FrameLayout frameLayout = new FrameLayout(NotificationInboxDevToolFragment.this.getActivity());
                final NumberPicker numberPicker = new NumberPicker(NotificationInboxDevToolFragment.this.getActivity());
                numberPicker.setFocusable(false);
                numberPicker.setMaxValue(NotificationInboxDevToolFragment.MAX_PRIORITY_COUNT_MAX_VALUE);
                numberPicker.setMinValue(NotificationInboxDevToolFragment.MAX_PRIORITY_COUNT_MIN_VALUE);
                numberPicker.setValue(UacfNotificationSdk.getSettings().getMaxPriorityCount());
                LayoutParams layoutParams = new LayoutParams(-2, -2);
                layoutParams.gravity = 17;
                frameLayout.addView(numberPicker, layoutParams);
                new Builder(NotificationInboxDevToolFragment.this.getActivity()).setView((View) frameLayout).setTitle(R.string.max_priority_count_title).setPositiveButton(R.string.set, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UacfNotificationSdk.getSettings().setMaxPriorityCount(numberPicker.getValue());
                        NotificationInboxDevToolFragment.this.maxPriorityCount.setText(String.valueOf(numberPicker.getValue()));
                        NotificationInboxDevToolFragment.this.resetAdapter();
                    }
                }).show();
            }
        });
        this.onePriorityPerCategorySwitch.setChecked(UacfNotificationSdk.getSettings().getLimitPriorityToOnePerCategory());
        this.maxPriorityCount.setText(String.valueOf(UacfNotificationSdk.getSettings().getMaxPriorityCount()));
        this.delete.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NotificationInboxDevToolFragment.this.deleteNotifications();
            }
        });
        this.markExpired.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NotificationInboxDevToolFragment.this.expireNotifications();
            }
        });
        this.markPending.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NotificationInboxDevToolFragment.this.updateNotificationStates("PENDING");
            }
        });
        this.markRead.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NotificationInboxDevToolFragment.this.updateNotificationStates("READ");
            }
        });
        this.markUnread.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NotificationInboxDevToolFragment.this.updateNotificationStates("UNREAD");
            }
        });
        return inflate;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        getActivity().setTitle(R.string.dev_tool_title);
    }

    /* access modifiers changed from: private */
    public void updateNotificationStates(String str) {
        this.notificationSdk.updateNotifications((List<String>) new ArrayList<String>(this.checkedNotifications.keySet()), str);
        this.adapter.refreshCursor();
        Iterator it = this.checkedNotifications.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            this.notificationSdk.reportNotificationRead((String) entry.getKey(), ((Integer) entry.getValue()).intValue(), this.adapter.getItemCount());
            it.remove();
            this.adapter.notifyItemChanged(((Integer) entry.getValue()).intValue());
        }
    }

    /* access modifiers changed from: private */
    public void deleteNotifications() {
        this.notificationSdk.deleteNotifications(new ArrayList(this.checkedNotifications.keySet()));
        this.adapter.refreshCursor();
        Iterator it = this.checkedNotifications.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            it.remove();
            this.adapter.notifyItemRemoved(((Integer) entry.getValue()).intValue());
        }
    }

    /* access modifiers changed from: private */
    public void expireNotifications() {
        this.notificationSdk.expireNotifications(new ArrayList(this.checkedNotifications.keySet()));
        this.adapter.refreshCursor();
        Iterator it = this.checkedNotifications.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            it.remove();
            this.adapter.notifyItemRemoved(((Integer) entry.getValue()).intValue());
        }
    }

    private void initList() {
        this.layoutManager = new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.adapter = new InboxCursorRecyclerViewAdapter(getActivity(), this.notificationCheckedCallback, this.checkedNotifications);
        this.recyclerView.setAdapter(this.adapter);
    }

    /* access modifiers changed from: private */
    public void resetAdapter() {
        this.adapter.refreshCursor();
        this.adapter.notifyDataSetChanged();
    }
}
