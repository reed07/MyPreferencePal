package com.myfitnesspal.feature.home.listener;

import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.uacf.core.util.Function1;

public interface NewsFeedItemActionListener {
    void onCameraClick();

    void onCardCloseClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry);

    void onCommentLikeClick(String str, boolean z, Function1<Integer> function1);

    void onCommentLikeCountClick(String str);

    void onCommentLongClick(String str, int i);

    void onImageCardActionClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, boolean z);

    void onLikeClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, String str, String str2, Function1<MfpNewsFeedActivityEntry> function1, Function1<MfpNewsFeedActivityEntry> function12);

    void onUpdateStatusClick();

    void onViewMealClick(String str, String str2, String str3, String str4);
}
