package com.myfitnesspal.feature.home.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.home.ui.activity.ImageReportingActivity;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedImageStatusUpdateEntry;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.DialogListTextItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.BundleUtils;
import java.util.ArrayList;

public class ImageCardActionDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String EXTRA_ENTRY = "activity_entry";
    private static final String EXTRA_FOR_LOGGED_USER = "for_logged_user";
    private final OnItemClickListener cardOnItemClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (ImageCardActionDialogFragment.this.isNewsfeedCardForLoggedInUser) {
                ImageCardActionDialogFragment.this.newsFeedItemActionListener.onCardCloseClick(ImageCardActionDialogFragment.this.entry);
                return;
            }
            ImageCardActionDialogFragment.this.navigationHelper.withContext(ImageCardActionDialogFragment.this.getContext()).withIntent(ImageReportingActivity.newStartIntent(view.getContext(), ((MfpNewsFeedImageStatusUpdateEntry) ImageCardActionDialogFragment.this.entry.getEntryData()).getImageId(), ImageCardActionDialogFragment.this.entry.getId(), ImageCardActionDialogFragment.this.entry.getOwner().getUserId(), 1, HomeActivity.newStartIntent(ImageCardActionDialogFragment.this.getActivity()))).startActivity(189);
        }
    };
    /* access modifiers changed from: private */
    public MfpNewsFeedActivityEntry entry;
    /* access modifiers changed from: private */
    public boolean isNewsfeedCardForLoggedInUser;
    /* access modifiers changed from: private */
    public NewsFeedItemActionListener newsFeedItemActionListener;

    public static ImageCardActionDialogFragment newInstance(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, boolean z, NewsFeedItemActionListener newsFeedItemActionListener2) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("activity_entry", mfpNewsFeedActivityEntry);
        bundle.putBoolean(EXTRA_FOR_LOGGED_USER, z);
        ImageCardActionDialogFragment imageCardActionDialogFragment = new ImageCardActionDialogFragment();
        imageCardActionDialogFragment.setArguments(bundle);
        imageCardActionDialogFragment.newsFeedItemActionListener = newsFeedItemActionListener2;
        return imageCardActionDialogFragment;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        this.isNewsfeedCardForLoggedInUser = BundleUtils.getBoolean(getArguments(), EXTRA_FOR_LOGGED_USER);
        this.entry = (MfpNewsFeedActivityEntry) BundleUtils.getParcelable(getArguments(), "activity_entry", MfpNewsFeedActivityEntry.class.getClassLoader());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DialogListTextItem(getString(this.isNewsfeedCardForLoggedInUser ? R.string.remove_image : R.string.report_image)));
        return new MfpAlertDialogBuilder(getContext()).setItems(arrayList, this.cardOnItemClickListener).setCanceledOnTouchOutside(true).create();
    }

    public void onDestroy() {
        super.onDestroy();
        this.newsFeedItemActionListener = null;
    }
}
