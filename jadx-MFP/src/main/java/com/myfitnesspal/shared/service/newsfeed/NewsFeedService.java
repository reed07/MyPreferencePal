package com.myfitnesspal.shared.service.newsfeed;

import com.myfitnesspal.feature.home.model.NewsFeedRequestData;
import com.myfitnesspal.feature.home.ui.viewmodel.NewsFeedViewModel.NewsFeedType;
import com.myfitnesspal.feature.progress.model.ImageStatusMetadata;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityComment;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryListContainer;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedLikeDetails;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;

public interface NewsFeedService {
    void deleteComment(String str, String str2) throws ApiException;

    void deleteNewsFeedEntryAsync(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry);

    MfpNewsFeedActivityEntryListContainer fetchFeedV2(NewsFeedRequestData newsFeedRequestData, NewsFeedType newsFeedType) throws ApiException;

    void fetchFeedV2Async(String str, int i, Function1<MfpNewsFeedActivityEntryListContainer> function1, MfpV2ApiErrorCallback mfpV2ApiErrorCallback);

    MfpNewsFeedActivityEntryListContainer getCachedFeed(String str);

    MfpNewsFeedActivityEntryListContainer getCachedHomeNewsFeed();

    void getLikesAsync(String str, Function1<MfpNewsFeedLikeDetails> function1, MfpV2ApiErrorCallback mfpV2ApiErrorCallback);

    void getLikesAsyncForConversationEntry(String str, String str2, Function1<MfpNewsFeedLikeDetails> function1, MfpV2ApiErrorCallback mfpV2ApiErrorCallback);

    MfpNewsFeedActivityEntry getNewsFeedEntry(String str) throws ApiException;

    MfpNewsFeedActivityEntryListContainer getUserTimelineCachedFeed(String str);

    MfpNewsFeedActivityComment postComment(String str, String str2, String str3) throws ApiException;

    void postLikeAsync(String str, String str2, Function1<MfpNewsFeedLikeDetails> function1, MfpV2ApiErrorCallback mfpV2ApiErrorCallback);

    MfpNewsFeedLikeDetails postLikeForConversationEntryComment(String str, String str2, String str3) throws ApiException;

    void postNewActivityEntryAsync(String str, String str2, Function0 function0, MfpV2ApiErrorCallback mfpV2ApiErrorCallback);

    MfpNewsFeedActivityEntry postNewMealFoodActivityEntrySync(String str, String str2, String str3, String str4) throws ApiException;

    MfpNewsFeedActivityEntry postNewProgressImageActivityEntrySync(String str, ImageStatusMetadata imageStatusMetadata) throws ApiException;

    MfpNewsFeedActivityEntry postNewStatusImageActivityEntrySync(String str, String str2) throws ApiException;

    void putCachedFeed(String str, MfpNewsFeedActivityEntryListContainer mfpNewsFeedActivityEntryListContainer);

    void putCachedHomeNewsFeed(MfpNewsFeedActivityEntryListContainer mfpNewsFeedActivityEntryListContainer);

    void putUserTimelineCachedFeed(String str, MfpNewsFeedActivityEntryListContainer mfpNewsFeedActivityEntryListContainer);

    void removeCachedEntry(String str, String str2);

    void removeCachedEntry(String str, String str2, String str3);
}
