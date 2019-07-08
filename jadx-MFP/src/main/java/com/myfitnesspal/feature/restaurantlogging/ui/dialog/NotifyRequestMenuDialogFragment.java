package com.myfitnesspal.feature.restaurantlogging.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.restaurantlogging.model.RequestMenuItem;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingAnalyticsHelper;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.CheckableListItem;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.BundleUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class NotifyRequestMenuDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String EMAIL = "email";
    private static final String NONE = "none";
    private static final String PUSH_NOTIFICATION_KEY = "push_notification";
    private static final String VENUE = "VENUE";
    /* access modifiers changed from: private */
    public int indexSelectedItem;
    @Inject
    Lazy<RestaurantLoggingAnalyticsHelper> restaurantLoggingAnalyticsHelper;

    public static NotifyRequestMenuDialogFragment newInstance(Venue venue) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(VENUE, venue);
        NotifyRequestMenuDialogFragment notifyRequestMenuDialogFragment = new NotifyRequestMenuDialogFragment();
        notifyRequestMenuDialogFragment.setArguments(bundle);
        return notifyRequestMenuDialogFragment;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        component().inject(this);
        final ArrayList arrayList = new ArrayList();
        arrayList.add(new RequestMenuItem(getString(R.string.push_notification), PUSH_NOTIFICATION_KEY));
        arrayList.add(new RequestMenuItem(getString(R.string.email_text), "email"));
        arrayList.add(new RequestMenuItem(getString(R.string.dont_notify_me), "none"));
        return new MfpAlertDialogBuilder(getContext()).setTitle((int) R.string.how_notify).setSingleChoiceItems((List<? extends CheckableListItem>) arrayList, (OnItemClickListener) new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                NotifyRequestMenuDialogFragment notifyRequestMenuDialogFragment = NotifyRequestMenuDialogFragment.this;
                notifyRequestMenuDialogFragment.setItemState((CheckableListItem) arrayList.get(notifyRequestMenuDialogFragment.indexSelectedItem), false);
                NotifyRequestMenuDialogFragment.this.setItemState((CheckableListItem) arrayList.get(i), true);
                NotifyRequestMenuDialogFragment.this.indexSelectedItem = i;
            }
        }).setPositiveButton((int) R.string.ok, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                NotifyRequestMenuDialogFragment notifyRequestMenuDialogFragment = NotifyRequestMenuDialogFragment.this;
                notifyRequestMenuDialogFragment.reportRequestMenuEvent((RequestMenuItem) arrayList.get(notifyRequestMenuDialogFragment.indexSelectedItem));
                Intent intent = new Intent();
                intent.putExtra(Extras.SHOW_RECEIVED_MENU_REQUEST_SNACKBAR, true);
                FragmentActivity activity = NotifyRequestMenuDialogFragment.this.getActivity();
                activity.setResult(-1, intent);
                activity.finish();
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) null).setDismissOnItemClick(false).create();
    }

    /* access modifiers changed from: private */
    public void reportRequestMenuEvent(RequestMenuItem requestMenuItem) {
        ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportRequestedMenu(requestMenuItem, (Venue) BundleUtils.getParcelable(getArguments(), VENUE, Venue.class.getClassLoader()));
    }

    /* access modifiers changed from: private */
    public void setItemState(CheckableListItem checkableListItem, boolean z) {
        checkableListItem.setState(z);
    }
}
