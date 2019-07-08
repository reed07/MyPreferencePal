package com.myfitnesspal.shared.service.newsfeed;

import com.myfitnesspal.feature.home.model.NewsFeedRequestData;
import com.myfitnesspal.feature.home.model.NewsFeedRequestData.FetchType;
import com.myfitnesspal.feature.home.ui.viewmodel.NewsFeedViewModel.NewsFeedType;
import com.myfitnesspal.feature.progress.model.ImageStatusMetadata;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.MfpApiUtil;
import com.myfitnesspal.shared.api.request.MfpNewsFeedActivityCommentPostData;
import com.myfitnesspal.shared.api.request.MfpNewsFeedActivityEntryPostData;
import com.myfitnesspal.shared.api.request.MfpNewsFeedActivityItemPostData;
import com.myfitnesspal.shared.api.request.MfpNewsFeedLikesPostData;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.model.v15.LikesDetailObject;
import com.myfitnesspal.shared.model.v15.LikingUser;
import com.myfitnesspal.shared.model.v15.StatusUpdateObject;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityComment;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityConversation;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryData;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryListContainer;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedHeroCardEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedLikeDetails;
import com.myfitnesspal.shared.service.api.MfpNewsFeedActivityImageEntryPostData;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.caching.Cache;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;

public class NewsFeedServiceImpl implements NewsFeedService {
    private final Provider<MfpInformationApi> api;
    private final Provider<MfpV2Api> apiV2Provider;
    private final Cache<MfpNewsFeedActivityEntryListContainer> feedCache;
    /* access modifiers changed from: private */
    public final Lazy<Session> session;

    public NewsFeedServiceImpl(Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2, Lazy<Session> lazy, Cache<MfpNewsFeedActivityEntryListContainer> cache) {
        this.api = provider;
        this.apiV2Provider = provider2;
        this.session = lazy;
        this.feedCache = cache;
    }

    public MfpNewsFeedActivityEntryListContainer getCachedFeed(String str) {
        return (MfpNewsFeedActivityEntryListContainer) this.feedCache.get(getFeedCacheKey(str));
    }

    public MfpNewsFeedActivityEntryListContainer getUserTimelineCachedFeed(String str) {
        return getCachedFeed(getUserTimelineCacheKey(str));
    }

    public void putCachedFeed(String str, MfpNewsFeedActivityEntryListContainer mfpNewsFeedActivityEntryListContainer) {
        this.feedCache.put(getFeedCacheKey(str), mfpNewsFeedActivityEntryListContainer);
    }

    public MfpNewsFeedActivityEntryListContainer getCachedHomeNewsFeed() {
        return getCachedFeed(Uri.ACTIVITY_TIMELINE);
    }

    public void putCachedHomeNewsFeed(MfpNewsFeedActivityEntryListContainer mfpNewsFeedActivityEntryListContainer) {
        putCachedFeed(Uri.ACTIVITY_TIMELINE, mfpNewsFeedActivityEntryListContainer);
    }

    public void putUserTimelineCachedFeed(String str, MfpNewsFeedActivityEntryListContainer mfpNewsFeedActivityEntryListContainer) {
        putCachedFeed(getUserTimelineCacheKey(str), mfpNewsFeedActivityEntryListContainer);
    }

    private String getUserTimelineCacheKey(String str) {
        return String.format("%s/%s", new Object[]{Uri.USER_TIMELINE, str});
    }

    public MfpNewsFeedActivityEntryListContainer fetchFeedV2(NewsFeedRequestData newsFeedRequestData, NewsFeedType newsFeedType) throws ApiException {
        ApiResponse apiResponse;
        MfpV2Api mfpV2Api = (MfpV2Api) ((MfpV2Api) this.apiV2Provider.get()).withOutputType(API_RESPONSE_MAPPER.class);
        String userId = newsFeedRequestData.getUserId();
        if (Strings.notEmpty(userId)) {
            apiResponse = (ApiResponse) mfpV2Api.get(newsFeedRequestData.getUrl(), "max_items", Integer.valueOf(newsFeedRequestData.getMaxItems()), "user_id", userId);
        } else {
            apiResponse = (ApiResponse) mfpV2Api.get(newsFeedRequestData.getUrl(), "max_items", Integer.valueOf(newsFeedRequestData.getMaxItems()));
        }
        updateCacheWithNewEntries(apiResponse.getItems(), Strings.toString(mfpV2Api.getResponseHeaders().get("link")), newsFeedRequestData.getFetchType(), userId, newsFeedType);
        return getCachedNewsFeedBasedOnType(newsFeedType, userId);
    }

    private void updateCacheWithNewEntries(final List<MfpNewsFeedActivityEntry> list, String str, FetchType fetchType, String str2, NewsFeedType newsFeedType) {
        MfpNewsFeedActivityEntryListContainer cachedNewsFeedBasedOnType = getCachedNewsFeedBasedOnType(newsFeedType, str2);
        if (cachedNewsFeedBasedOnType == null) {
            cachedNewsFeedBasedOnType = new MfpNewsFeedActivityEntryListContainer(null, null);
        }
        cachedNewsFeedBasedOnType.setLink(str);
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            List<MfpNewsFeedActivityEntry> entries = cachedNewsFeedBasedOnType.getEntries();
            if (entries == null) {
                entries = new ArrayList<>();
            }
            switch (fetchType) {
                case OlderItems:
                    entries.addAll(list);
                    list = entries;
                    break;
                case NewItems:
                    final Date createdAt = ((MfpNewsFeedActivityEntry) list.get(list.size() - 1)).getCreatedAt();
                    list.addAll((List) Enumerable.where((Collection<T>) entries, (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, MfpNewsFeedActivityEntry>() {
                        public Boolean execute(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
                            return Boolean.valueOf(mfpNewsFeedActivityEntry.getCreatedAt().before(createdAt) && !list.contains(mfpNewsFeedActivityEntry));
                        }
                    }));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported fetch type");
            }
            cachedNewsFeedBasedOnType.setEntries(list);
        }
        if (newsFeedType == NewsFeedType.Home) {
            putCachedHomeNewsFeed(cachedNewsFeedBasedOnType);
        } else {
            putUserTimelineCachedFeed(str2, cachedNewsFeedBasedOnType);
        }
    }

    private MfpNewsFeedActivityEntryListContainer getCachedNewsFeedBasedOnType(NewsFeedType newsFeedType, String str) {
        return newsFeedType == NewsFeedType.Home ? getCachedHomeNewsFeed() : getUserTimelineCachedFeed(str);
    }

    public void fetchFeedV2Async(String str, int i, final Function1<MfpNewsFeedActivityEntryListContainer> function1, final MfpV2ApiErrorCallback mfpV2ApiErrorCallback) {
        ((MfpV2Api) ((MfpV2Api) this.apiV2Provider.get()).withOutputType(API_RESPONSE_MAPPER.class)).getAsync(str, (Function2<T, Map<String, List<String>>>) new Function2<ApiResponse<MfpNewsFeedActivityEntry>, Map<String, List<String>>>() {
            public void execute(ApiResponse<MfpNewsFeedActivityEntry> apiResponse, Map<String, List<String>> map) {
                FunctionUtils.invokeIfValid(function1, new MfpNewsFeedActivityEntryListContainer(apiResponse.getItems(), Strings.toString(map.get("link"))));
            }
        }, (ApiErrorCallback) new ApiErrorCallback() {
            public void execute(ApiException apiException) {
                FunctionUtils.invokeIfValid(mfpV2ApiErrorCallback, MfpApiUtil.mapException(apiException));
            }
        }, "max_items", Integer.valueOf(i));
    }

    public MfpNewsFeedActivityEntry postNewProgressImageActivityEntrySync(String str, ImageStatusMetadata imageStatusMetadata) throws ApiException {
        return postActivityEntrySyncWithData(MfpNewsFeedActivityImageEntryPostData.newInstanceForProgressPhotoUpate(str, imageStatusMetadata));
    }

    public MfpNewsFeedActivityEntry postNewStatusImageActivityEntrySync(String str, String str2) throws ApiException {
        return postActivityEntrySyncWithData(MfpNewsFeedActivityImageEntryPostData.newInstanceForStatusPhotoUpate(str, str2));
    }

    public MfpNewsFeedActivityEntry postNewMealFoodActivityEntrySync(String str, String str2, String str3, String str4) throws ApiException {
        return postActivityEntrySyncWithData(MfpNewsFeedActivityImageEntryPostData.newInstanceForMealFoodUpdate(str, str2, str3, str4));
    }

    public void postNewActivityEntryAsync(String str, final String str2, final Function0 function0, final MfpV2ApiErrorCallback mfpV2ApiErrorCallback) {
        ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiV2Provider.get()).withOutputType(API_RESPONSE_MAPPER.class)).withJsonBody(new MfpNewsFeedActivityEntryPostData(str, str2))).postAsync(Uri.ACTIVITY, (Function1<T>) new Function1<ApiResponse<MfpNewsFeedActivityEntry>>() {
            public void execute(ApiResponse<MfpNewsFeedActivityEntry> apiResponse) {
                MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) apiResponse.getItem();
                if (mfpNewsFeedActivityEntry != null) {
                    MfpNewsFeedActivityEntryListContainer userTimelineCachedFeed = NewsFeedServiceImpl.this.getUserTimelineCachedFeed(str2);
                    if (userTimelineCachedFeed != null) {
                        List entries = userTimelineCachedFeed.getEntries();
                        if (!CollectionUtils.isEmpty((Collection<?>) entries)) {
                            entries.add(0, mfpNewsFeedActivityEntry);
                            NewsFeedServiceImpl.this.putUserTimelineCachedFeed(str2, userTimelineCachedFeed);
                        }
                    }
                }
                FunctionUtils.invokeIfValid(function0);
            }
        }, (ApiErrorCallback) new ApiErrorCallback() {
            public void execute(ApiException apiException) {
                FunctionUtils.invokeIfValid(mfpV2ApiErrorCallback, MfpApiUtil.mapException(apiException));
            }
        }, new Object[0]);
    }

    public MfpNewsFeedActivityEntry getNewsFeedEntry(String str) throws ApiException {
        return (MfpNewsFeedActivityEntry) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.apiV2Provider.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(String.format(Uri.ACTIVITY_ENTRY, new Object[]{Strings.toString(str)}))).getItem();
    }

    public void deleteNewsFeedEntryAsync(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        boolean z = mfpNewsFeedActivityEntry.getEntryData() instanceof MfpNewsFeedHeroCardEntry;
        final String idFromCard = getIdFromCard(mfpNewsFeedActivityEntry);
        MfpV2Api mfpV2Api = (MfpV2Api) ((MfpV2Api) this.apiV2Provider.get()).withOutputType(ApiResponseBase.class);
        String format = String.format(z ? Uri.HERO_CARDS_COOLDOWN : Uri.ACTIVITY_ENTRY, new Object[]{idFromCard});
        AnonymousClass6 r3 = new Function1<ApiResponseBase>() {
            public void execute(ApiResponseBase apiResponseBase) {
                MfpNewsFeedActivityEntryListContainer cachedFeed = NewsFeedServiceImpl.this.getCachedFeed(Uri.ACTIVITY_TIMELINE);
                if (cachedFeed != null && CollectionUtils.notEmpty((Collection<?>) cachedFeed.getEntries())) {
                    int indexOf = Enumerable.indexOf(cachedFeed.getEntries(), idFromCard, new ReturningFunction1<String, MfpNewsFeedActivityEntry>() {
                        public String execute(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
                            return NewsFeedServiceImpl.this.getIdFromCard(mfpNewsFeedActivityEntry);
                        }
                    });
                    if (indexOf >= 0) {
                        cachedFeed.getEntries().remove(indexOf);
                        NewsFeedServiceImpl.this.putCachedFeed(Uri.ACTIVITY_TIMELINE, cachedFeed);
                    }
                }
            }
        };
        AnonymousClass7 r6 = new ApiErrorCallback() {
            public void execute(ApiException apiException) {
            }
        };
        if (z) {
            mfpV2Api.postAsync(format, (Function1<T>) r3, (ApiErrorCallback) r6, new Object[0]);
        } else {
            mfpV2Api.deleteAsync(format, r3, r6);
        }
    }

    public MfpNewsFeedActivityComment postComment(String str, String str2, String str3) throws ApiException {
        MfpNewsFeedActivityComment mfpNewsFeedActivityComment = (MfpNewsFeedActivityComment) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiV2Provider.get()).withOutputType(MfpNewsFeedActivityComment.API_RESPONSE_MAPPER.class)).withJsonBody(new MfpNewsFeedActivityItemPostData(new MfpNewsFeedActivityCommentPostData(str2, str3)))).post(String.format(Uri.ACTIVITY_CONVERSATION_ENTRY, new Object[]{Strings.toString(str)}), new Object[0])).getItem();
        saveCommentToCache(mfpNewsFeedActivityComment, str);
        return mfpNewsFeedActivityComment;
    }

    public void deleteComment(String str, String str2) throws ApiException {
        ((MfpV2Api) ((MfpV2Api) this.apiV2Provider.get()).withOutputType(ApiResponseBase.class)).delete(String.format(Uri.ACTIVITY_CONVERSATION_ENTRY_COMMENT, new Object[]{Strings.toString(str), Strings.toString(str2)}));
        removeCommentFromCache(str, str2);
    }

    public void getLikesAsync(String str, final Function1<MfpNewsFeedLikeDetails> function1, final MfpV2ApiErrorCallback mfpV2ApiErrorCallback) {
        ((MfpV2Api) ((MfpV2Api) this.apiV2Provider.get()).withOutputType(MfpNewsFeedLikeDetails.API_RESPONSE_MAPPER.class)).getAsync(String.format(Uri.ACTIVITY_LIKES, new Object[]{str}), (Function1<T>) new Function1<ApiResponse<MfpNewsFeedLikeDetails>>() {
            public void execute(ApiResponse<MfpNewsFeedLikeDetails> apiResponse) {
                FunctionUtils.invokeIfValid(function1, apiResponse.getItem());
            }
        }, (ApiErrorCallback) new ApiErrorCallback() {
            public void execute(ApiException apiException) {
                FunctionUtils.invokeIfValid(mfpV2ApiErrorCallback, MfpApiUtil.mapException(apiException));
            }
        });
    }

    public void getLikesAsyncForConversationEntry(String str, String str2, final Function1<MfpNewsFeedLikeDetails> function1, final MfpV2ApiErrorCallback mfpV2ApiErrorCallback) {
        ((MfpV2Api) ((MfpV2Api) this.apiV2Provider.get()).withOutputType(MfpNewsFeedLikeDetails.API_RESPONSE_MAPPER.class)).getAsync(String.format(Uri.ACTIVITY_CONVERSATION_ENTRY_COMMENT_LIKES, new Object[]{str, str2}), (Function1<T>) new Function1<ApiResponse<MfpNewsFeedLikeDetails>>() {
            public void execute(ApiResponse<MfpNewsFeedLikeDetails> apiResponse) {
                FunctionUtils.invokeIfValid(function1, apiResponse.getItem());
            }
        }, (ApiErrorCallback) new ApiErrorCallback() {
            public void execute(ApiException apiException) {
                FunctionUtils.invokeIfValid(mfpV2ApiErrorCallback, MfpApiUtil.mapException(apiException));
            }
        });
    }

    public void postLikeAsync(final String str, String str2, final Function1<MfpNewsFeedLikeDetails> function1, final MfpV2ApiErrorCallback mfpV2ApiErrorCallback) {
        ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiV2Provider.get()).withOutputType(MfpNewsFeedLikeDetails.API_RESPONSE_MAPPER.class)).withJsonBody(new MfpNewsFeedActivityItemPostData(new MfpNewsFeedLikesPostData(str2)))).postAsync(String.format(Uri.ACTIVITY_LIKES, new Object[]{str}), (Function1<T>) new Function1<ApiResponse<MfpNewsFeedLikeDetails>>() {
            public void execute(ApiResponse<MfpNewsFeedLikeDetails> apiResponse) {
                MfpNewsFeedActivityEntryListContainer cachedFeed = NewsFeedServiceImpl.this.getCachedFeed(Uri.ACTIVITY_TIMELINE);
                if (cachedFeed != null && CollectionUtils.notEmpty((Collection<?>) cachedFeed.getEntries())) {
                    MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) Enumerable.firstOrDefault(cachedFeed.getEntries(), new ReturningFunction1<Boolean, MfpNewsFeedActivityEntry>() {
                        public Boolean execute(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
                            return Boolean.valueOf(Strings.equals(mfpNewsFeedActivityEntry.getId(), str));
                        }
                    });
                    if (mfpNewsFeedActivityEntry != null) {
                        mfpNewsFeedActivityEntry.setLikes((MfpNewsFeedLikeDetails) apiResponse.getItem());
                        NewsFeedServiceImpl.this.putCachedFeed(Uri.ACTIVITY_TIMELINE, cachedFeed);
                    }
                }
                FunctionUtils.invokeIfValid(function1, apiResponse.getItem());
            }
        }, (ApiErrorCallback) new ApiErrorCallback() {
            public void execute(ApiException apiException) {
                FunctionUtils.invokeIfValid(mfpV2ApiErrorCallback, MfpApiUtil.mapException(apiException));
            }
        }, new Object[0]);
    }

    public MfpNewsFeedLikeDetails postLikeForConversationEntryComment(String str, String str2, String str3) throws ApiException {
        MfpNewsFeedLikeDetails mfpNewsFeedLikeDetails = (MfpNewsFeedLikeDetails) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiV2Provider.get()).withOutputType(MfpNewsFeedLikeDetails.API_RESPONSE_MAPPER.class)).withJsonBody(new MfpNewsFeedActivityItemPostData(new MfpNewsFeedLikesPostData(str3)))).post(String.format(Uri.ACTIVITY_CONVERSATION_ENTRY_COMMENT_LIKES, new Object[]{str, str2}), new Object[0])).getItem();
        saveCommentLikesToCache(mfpNewsFeedLikeDetails, str, str2);
        return mfpNewsFeedLikeDetails;
    }

    public void removeCachedEntry(String str, String str2) {
        removeCachedEntry(str, str2, null);
    }

    public void removeCachedEntry(String str, String str2, String str3) {
        if (!Strings.isEmpty(str2)) {
            if (!Strings.isEmpty(str3)) {
                str = getUserTimelineCacheKey(str3);
            }
            MfpNewsFeedActivityEntryListContainer cachedFeed = getCachedFeed(str);
            if (cachedFeed != null) {
                List entries = cachedFeed.getEntries();
                if (CollectionUtils.notEmpty((Collection<?>) entries)) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= CollectionUtils.size((Collection<?>) entries)) {
                            break;
                        } else if (Strings.equals(((MfpNewsFeedActivityEntry) entries.get(i2)).getId(), str2)) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        entries.remove(i);
                        putCachedFeed(str, cachedFeed);
                    }
                }
            }
        }
    }

    private MfpNewsFeedActivityEntry postActivityEntrySyncWithData(MfpNewsFeedActivityImageEntryPostData mfpNewsFeedActivityImageEntryPostData) throws ApiException {
        return (MfpNewsFeedActivityEntry) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiV2Provider.get()).withOutputType(API_RESPONSE_MAPPER.class)).withJsonBody(mfpNewsFeedActivityImageEntryPostData)).post(Uri.ACTIVITY, new Object[0])).getItem();
    }

    private String getFeedCacheKey(String str) {
        return String.format("%s::%s", new Object[]{Strings.toString(str), ((Session) this.session.get()).getUser().getUserId()});
    }

    private List<MfpNewsFeedActivityEntry> mapV1FeedToV2Feed(List<StatusUpdateObject> list) {
        return Enumerable.select((Collection<T>) list, false, (ReturningFunction1<U, T>) new ReturningFunction1<MfpNewsFeedActivityEntry, StatusUpdateObject>() {
            public MfpNewsFeedActivityEntry execute(StatusUpdateObject statusUpdateObject) {
                return NewsFeedServiceImpl.this.mapV1EntryToV2Entry(statusUpdateObject);
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ea  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry mapV1EntryToV2Entry(com.myfitnesspal.shared.model.v15.StatusUpdateObject r9) {
        /*
            r8 = this;
            com.myfitnesspal.shared.util.RichText r0 = r9.getMessageBody()
            java.lang.String r1 = r0.baseString
            java.lang.String r1 = com.uacf.core.util.Strings.toString(r1)
            java.lang.String r2 = r9.getItemClass()
            int r3 = r2.hashCode()
            r4 = -411205036(0xffffffffe77d8254, float:-1.19716285E24)
            r5 = 1
            r6 = 0
            if (r3 == r4) goto L_0x0047
            r4 = -144931940(0xfffffffff75c839c, float:-4.4725573E33)
            if (r3 == r4) goto L_0x003d
            r4 = 66024355(0x3ef73a3, float:1.4073706E-36)
            if (r3 == r4) goto L_0x0033
            r4 = 2120967672(0x7e6b65f8, float:7.822451E37)
            if (r3 == r4) goto L_0x0029
            goto L_0x0051
        L_0x0029:
            java.lang.String r3 = "Exercise"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0051
            r2 = 1
            goto L_0x0052
        L_0x0033:
            java.lang.String r3 = "Diary"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0051
            r2 = 3
            goto L_0x0052
        L_0x003d:
            java.lang.String r3 = "Measurement"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0051
            r2 = 2
            goto L_0x0052
        L_0x0047:
            java.lang.String r3 = "ConsecutiveLogins"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0051
            r2 = 0
            goto L_0x0052
        L_0x0051:
            r2 = -1
        L_0x0052:
            switch(r2) {
                case 0: goto L_0x00ea;
                case 1: goto L_0x00ce;
                case 2: goto L_0x00b2;
                case 3: goto L_0x0062;
                default: goto L_0x0055;
            }
        L_0x0055:
            java.lang.String r2 = "status_update"
            com.myfitnesspal.shared.model.v2.MfpNewsFeedStatusUpdateEntry r0 = new com.myfitnesspal.shared.model.v2.MfpNewsFeedStatusUpdateEntry
            r0.<init>()
            com.myfitnesspal.shared.model.v2.MfpNewsFeedStatusUpdateEntry r0 = r0.setText(r1)
            goto L_0x00f5
        L_0x0062:
            java.lang.String r2 = "diary_complete"
            java.util.ArrayList<com.myfitnesspal.shared.util.RichTextAttribute> r3 = r0.attributes
            boolean r3 = com.uacf.core.util.CollectionUtils.notEmpty(r3)
            if (r3 == 0) goto L_0x00a3
            java.util.ArrayList<com.myfitnesspal.shared.util.RichTextAttribute> r0 = r0.attributes
            java.util.Iterator r0 = r0.iterator()
        L_0x0072:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x00a3
            java.lang.Object r3 = r0.next()
            com.myfitnesspal.shared.util.RichTextAttribute r3 = (com.myfitnesspal.shared.util.RichTextAttribute) r3
            com.myfitnesspal.shared.util.RichAttributeType r4 = r3.attributeType
            com.myfitnesspal.shared.util.RichAttributeType r7 = com.myfitnesspal.shared.util.RichAttributeType.DIARY_LINK
            if (r4 != r7) goto L_0x0072
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r4 = r3.startOffset
            java.lang.String r4 = r1.substring(r6, r4)
            r0.append(r4)
            int r4 = r3.startOffset
            int r3 = r3.length
            int r4 = r4 + r3
            java.lang.String r1 = r1.substring(r4)
            r0.append(r1)
            java.lang.String r1 = r0.toString()
            goto L_0x00a4
        L_0x00a3:
            r5 = 0
        L_0x00a4:
            com.myfitnesspal.shared.model.v2.MfpNewsFeedDiaryCompleteEntry r0 = new com.myfitnesspal.shared.model.v2.MfpNewsFeedDiaryCompleteEntry
            r0.<init>()
            com.myfitnesspal.shared.model.v2.MfpNewsFeedDiaryCompleteEntry r0 = r0.setText(r1)
            com.myfitnesspal.shared.model.v2.MfpNewsFeedDiaryCompleteEntry r0 = r0.setShowDiary(r5)
            goto L_0x00f5
        L_0x00b2:
            java.lang.String r2 = "weight_loss"
            com.myfitnesspal.shared.model.v2.MfpNewsFeedWeightLossEntry r0 = new com.myfitnesspal.shared.model.v2.MfpNewsFeedWeightLossEntry
            r0.<init>()
            com.myfitnesspal.shared.model.v2.MfpNewsFeedWeightLossEntry r0 = r0.setText(r1)
            java.lang.String r1 = r9.getPartnerAppId()
            com.myfitnesspal.shared.model.v2.MfpNewsFeedWeightLossEntry r0 = r0.setApplicationId(r1)
            java.lang.String r1 = r9.getPartnerAppName()
            com.myfitnesspal.shared.model.v2.MfpNewsFeedWeightLossEntry r0 = r0.setApplicationName(r1)
            goto L_0x00f5
        L_0x00ce:
            java.lang.String r2 = "exercise"
            com.myfitnesspal.shared.model.v2.MfpNewsFeedExerciseEntry r0 = new com.myfitnesspal.shared.model.v2.MfpNewsFeedExerciseEntry
            r0.<init>()
            com.myfitnesspal.shared.model.v2.MfpNewsFeedExerciseEntry r0 = r0.setText(r1)
            java.lang.String r1 = r9.getPartnerAppId()
            com.myfitnesspal.shared.model.v2.MfpNewsFeedExerciseEntry r0 = r0.setApplicationId(r1)
            java.lang.String r1 = r9.getPartnerAppName()
            com.myfitnesspal.shared.model.v2.MfpNewsFeedExerciseEntry r0 = r0.setApplicationName(r1)
            goto L_0x00f5
        L_0x00ea:
            java.lang.String r2 = "log_in_streak"
            com.myfitnesspal.shared.model.v2.MfpNewsFeedLogInStreakEntry r0 = new com.myfitnesspal.shared.model.v2.MfpNewsFeedLogInStreakEntry
            r0.<init>()
            com.myfitnesspal.shared.model.v2.MfpNewsFeedLogInStreakEntry r0 = r0.setText(r1)
        L_0x00f5:
            com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry r1 = new com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry
            r1.<init>()
            r1.setType(r2)
            r1.setEntryData(r0)
            java.lang.String r2 = r9.getUid()
            r1.setId(r2)
            long r2 = r9.getMasterId()
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = com.uacf.core.util.Strings.toString(r2)
            r1.setCorrelationId(r2)
            java.util.Date r2 = r9.getCreatedAt()
            r1.setCreatedAt(r2)
            boolean r2 = r9.isCommentable()
            r1.setLikesEnabled(r2)
            boolean r2 = r9.isCommentable()
            r1.setConversationEnabled(r2)
            boolean r2 = r9.isCommentable()
            r1.setIsCommentableByUser(r2)
            java.lang.String r2 = r9.getCreatingUserUid()
            dagger.Lazy<com.myfitnesspal.shared.service.session.Session> r3 = r8.session
            java.lang.Object r3 = r3.get()
            com.myfitnesspal.shared.service.session.Session r3 = (com.myfitnesspal.shared.service.session.Session) r3
            com.myfitnesspal.shared.model.User r3 = r3.getUser()
            java.lang.String r3 = r3.getUid()
            boolean r2 = com.uacf.core.util.Strings.equals(r2, r3)
            r1.setIsRemovableByUser(r2)
            java.util.Map r0 = r1.mapDataFromEntryData(r0)
            r1.setData(r0)
            com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant r0 = new com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant
            r0.<init>()
            java.lang.String r2 = r9.getCreatingUserUsername()
            com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant r0 = r0.setDisplayName(r2)
            java.lang.String r2 = r9.getCreatingUserUid()
            com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant r0 = r0.setUserId(r2)
            java.lang.String r2 = r9.getCreatingUserUsername()
            com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant r0 = r0.setUsername(r2)
            java.lang.String r2 = r9.getCreatingUserImageUrl()
            com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant r0 = r0.setProfilePhotoUrl(r2)
            java.lang.String r2 = "none"
            com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant r0 = r0.setProfileVisibility(r2)
            r1.setOwner(r0)
            com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityConversation r0 = new com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityConversation
            r0.<init>()
            java.util.List r2 = r9.getComments()
            com.myfitnesspal.shared.service.newsfeed.NewsFeedServiceImpl$15 r3 = new com.myfitnesspal.shared.service.newsfeed.NewsFeedServiceImpl$15
            r3.<init>()
            java.util.List r2 = com.uacf.core.util.Enumerable.select(r2, r3)
            r0.setComments(r2)
            int r2 = com.uacf.core.util.CollectionUtils.size(r2)
            r0.setCount(r2)
            r1.setConversation(r0)
            com.myfitnesspal.shared.model.v15.LikesDetailObject r9 = r9.getLikesDetail()
            com.myfitnesspal.shared.model.v2.MfpNewsFeedLikeDetails r9 = r8.mapV1LikesToV2Likes(r9)
            r1.setLikes(r9)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.newsfeed.NewsFeedServiceImpl.mapV1EntryToV2Entry(com.myfitnesspal.shared.model.v15.StatusUpdateObject):com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry");
    }

    private void saveCommentToCache(MfpNewsFeedActivityComment mfpNewsFeedActivityComment, final String str) {
        MfpNewsFeedActivityEntryListContainer cachedFeed = getCachedFeed(Uri.ACTIVITY_TIMELINE);
        if (cachedFeed != null && CollectionUtils.notEmpty((Collection<?>) cachedFeed.getEntries())) {
            MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) Enumerable.firstOrDefault(cachedFeed.getEntries(), new ReturningFunction1<Boolean, MfpNewsFeedActivityEntry>() {
                public Boolean execute(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
                    return Boolean.valueOf(Strings.equals(mfpNewsFeedActivityEntry.getId(), str));
                }
            });
            if (mfpNewsFeedActivityEntry != null) {
                MfpNewsFeedActivityConversation conversation = mfpNewsFeedActivityEntry.getConversation();
                if (conversation == null) {
                    conversation = new MfpNewsFeedActivityConversation(0, (List<MfpNewsFeedActivityComment>) new ArrayList<MfpNewsFeedActivityComment>());
                    mfpNewsFeedActivityEntry.setConversation(conversation);
                }
                List comments = conversation.getComments();
                if (comments == null) {
                    comments = new ArrayList();
                    conversation.setComments(comments);
                }
                comments.add(mfpNewsFeedActivityComment);
                conversation.setCount(conversation.getCount() + 1);
                putCachedFeed(Uri.ACTIVITY_TIMELINE, cachedFeed);
            }
        }
    }

    private void saveCommentLikesToCache(MfpNewsFeedLikeDetails mfpNewsFeedLikeDetails, final String str, final String str2) {
        MfpNewsFeedActivityEntryListContainer cachedFeed = getCachedFeed(Uri.ACTIVITY_TIMELINE);
        if (cachedFeed != null && CollectionUtils.notEmpty((Collection<?>) cachedFeed.getEntries())) {
            MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) Enumerable.firstOrDefault(cachedFeed.getEntries(), new ReturningFunction1<Boolean, MfpNewsFeedActivityEntry>() {
                public Boolean execute(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
                    return Boolean.valueOf(Strings.equals(mfpNewsFeedActivityEntry.getId(), str));
                }
            });
            if (mfpNewsFeedActivityEntry != null && mfpNewsFeedActivityEntry.getConversation() != null) {
                MfpNewsFeedActivityComment mfpNewsFeedActivityComment = (MfpNewsFeedActivityComment) Enumerable.firstOrDefault(mfpNewsFeedActivityEntry.getConversation().getComments(), new ReturningFunction1<Boolean, MfpNewsFeedActivityComment>() {
                    public Boolean execute(MfpNewsFeedActivityComment mfpNewsFeedActivityComment) {
                        return Boolean.valueOf(Strings.equals(mfpNewsFeedActivityComment.getId(), str2));
                    }
                });
                if (mfpNewsFeedActivityComment != null) {
                    mfpNewsFeedActivityComment.setLikes(mfpNewsFeedLikeDetails);
                    putCachedFeed(Uri.ACTIVITY_TIMELINE, cachedFeed);
                }
            }
        }
    }

    private void removeCommentFromCache(final String str, String str2) {
        MfpNewsFeedActivityEntryListContainer cachedFeed = getCachedFeed(Uri.ACTIVITY_TIMELINE);
        if (cachedFeed != null && CollectionUtils.notEmpty((Collection<?>) cachedFeed.getEntries())) {
            MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) Enumerable.firstOrDefault(cachedFeed.getEntries(), new ReturningFunction1<Boolean, MfpNewsFeedActivityEntry>() {
                public Boolean execute(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
                    return Boolean.valueOf(Strings.equals(mfpNewsFeedActivityEntry.getId(), str));
                }
            });
            if (mfpNewsFeedActivityEntry != null) {
                MfpNewsFeedActivityConversation conversation = mfpNewsFeedActivityEntry.getConversation();
                if (conversation != null) {
                    List comments = conversation.getComments();
                    int indexOf = Enumerable.indexOf(comments, str2, new ReturningFunction1<String, MfpNewsFeedActivityComment>() {
                        public String execute(MfpNewsFeedActivityComment mfpNewsFeedActivityComment) {
                            return mfpNewsFeedActivityComment.getId();
                        }
                    });
                    if (indexOf >= 0) {
                        comments.remove(indexOf);
                        conversation.setCount(conversation.getCount() - 1);
                        putCachedFeed(Uri.ACTIVITY_TIMELINE, cachedFeed);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public MfpNewsFeedLikeDetails mapV1LikesToV2Likes(LikesDetailObject likesDetailObject) {
        if (likesDetailObject == null) {
            return null;
        }
        return new MfpNewsFeedLikeDetails().setCount(likesDetailObject.getTotalNumberOfLikes()).setUserLiked(likesDetailObject.currentUserHasLiked()).setParticipants(Enumerable.select((Collection<T>) likesDetailObject.getLikingUsers(), (ReturningFunction1<U, T>) new ReturningFunction1<MfpNewsFeedActivityParticipant, LikingUser>() {
            public MfpNewsFeedActivityParticipant execute(LikingUser likingUser) {
                return new MfpNewsFeedActivityParticipant().setUserId(likingUser.getUid()).setDisplayName(likingUser.getUsername());
            }
        }));
    }

    /* access modifiers changed from: private */
    public String getIdFromCard(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        MfpNewsFeedActivityEntryData entryData = mfpNewsFeedActivityEntry.getEntryData();
        return Strings.toString(entryData instanceof MfpNewsFeedHeroCardEntry ? Integer.valueOf(((MfpNewsFeedHeroCardEntry) entryData).getHeroCardId()) : mfpNewsFeedActivityEntry.getId());
    }
}
