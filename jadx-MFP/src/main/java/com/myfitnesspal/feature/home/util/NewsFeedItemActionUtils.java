package com.myfitnesspal.feature.home.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.friends.service.StatusAnalytics.Source;
import com.myfitnesspal.feature.friends.ui.activity.NewStatusOrCommentActivity;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.ui.adapter.NewsFeedAdapter;
import com.myfitnesspal.feature.home.ui.fragment.ImageCardActionDialogFragment;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.event.BusyEvent;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedLikeDetails;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.activity.impl.resourceloader.ResourceLoaderProxyActivity;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;

public class NewsFeedItemActionUtils {
    public static void cardCloseClick(Lazy<NewsFeedService> lazy, NewsFeedAdapter newsFeedAdapter, MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        ((NewsFeedService) lazy.get()).deleteNewsFeedEntryAsync(mfpNewsFeedActivityEntry);
        ((NewsFeedService) lazy.get()).removeCachedEntry(Uri.ACTIVITY_TIMELINE, mfpNewsFeedActivityEntry.getId());
        newsFeedAdapter.removeItem(mfpNewsFeedActivityEntry);
    }

    public static void likeClick(final MfpActivity mfpActivity, Lazy<NewsFeedService> lazy, final Bus bus, final MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, String str, String str2, final Function1<MfpNewsFeedActivityEntry> function1, final Function1<MfpNewsFeedActivityEntry> function12) {
        bus.post(new BusyEvent(1, true));
        ((NewsFeedService) lazy.get()).postLikeAsync(str, str2, new Function1<MfpNewsFeedLikeDetails>() {
            public void execute(MfpNewsFeedLikeDetails mfpNewsFeedLikeDetails) {
                mfpNewsFeedActivityEntry.setLikes(mfpNewsFeedLikeDetails);
                if (mfpActivity.hasResumed()) {
                    FunctionUtils.invokeIfValid(function1, mfpNewsFeedActivityEntry);
                    bus.post(new BusyEvent(1, false));
                }
            }
        }, new MfpV2ApiErrorCallback() {
            public void execute(ApiResponseBase apiResponseBase) {
                if (mfpActivity.hasResumed()) {
                    FunctionUtils.invokeIfValid(function12, mfpNewsFeedActivityEntry);
                    bus.post(new BusyEvent(1, false));
                    bus.post(new AlertEvent(mfpActivity.getString(R.string.failed_to_retrieve_likes)).asToast());
                }
            }
        });
    }

    public static void imageCardActionClick(NewsFeedItemActionListener newsFeedItemActionListener, FragmentManager fragmentManager, MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, String str, boolean z) {
        ImageCardActionDialogFragment.newInstance(mfpNewsFeedActivityEntry, z, newsFeedItemActionListener).show(fragmentManager, str);
    }

    public static void viewMealFoodClick(NavigationHelper navigationHelper, Context context, String str, String str2, String str3, String str4, Session session) {
        navigationHelper.withIntent(ResourceLoaderProxyActivity.newViewSharedMealIntent(context, str, str2, str3, str4, Strings.equals(str3, session.getUser().getUsername()))).withAnimations(17432576, 17432577).startActivity();
    }

    public static void updateStatusClick(NavigationHelper navigationHelper, Activity activity, Fragment fragment) {
        updateStatusClick(navigationHelper, activity, fragment, null);
    }

    public static void updateStatusClick(NavigationHelper navigationHelper, Activity activity, Fragment fragment, String str) {
        navigationHelper.withExtra(Extras.ITEM_TYPE, 17).withIntent(NewStatusOrCommentActivity.newStartIntent(activity, Source.NewsFeed));
        if (Strings.notEmpty(str)) {
            navigationHelper.withExtra(Extras.DESTINATION_USER_UID, str);
        }
        if (fragment != null) {
            navigationHelper.fromFragment(fragment);
        }
        navigationHelper.startActivity(34);
    }

    public static void statusCameraClick(@NonNull NavigationHelper navigationHelper, Activity activity) {
        navigationHelper.withExtra(Extras.ITEM_TYPE, 17).withIntent(NewStatusOrCommentActivity.newStartIntentWithImageChooserVisible(activity, Source.NewsFeed)).startActivity(34);
    }
}
