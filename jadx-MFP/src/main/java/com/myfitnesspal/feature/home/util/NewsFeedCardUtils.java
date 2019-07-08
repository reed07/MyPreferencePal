package com.myfitnesspal.feature.home.util;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.home.model.BlogEntry;
import com.myfitnesspal.feature.home.model.MfpBlogDailySummary;
import com.myfitnesspal.feature.home.model.NewsFeedDisplayActivityName;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.model.VideoEntry;
import com.myfitnesspal.shared.model.Size;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry.DataTypes;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryData;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedChallengeLink;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedChallengesStatusCardEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedHeroCardEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedImageStatusUpdateEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedMealPhotoUpdateEntry;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.FileUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import java.util.Collection;
import java.util.List;

public final class NewsFeedCardUtils {
    public static void handleDeepLink(@NonNull NavigationHelper navigationHelper, @Nullable MfpNewsFeedChallengeLink mfpNewsFeedChallengeLink) {
        if (mfpNewsFeedChallengeLink != null) {
            String deepLink = mfpNewsFeedChallengeLink.getDeepLink();
            if (Strings.notEmpty(deepLink)) {
                navigationHelper.withIntent(SharedIntents.newUriIntent(deepLink)).startActivity();
            }
        }
    }

    public static List<NewsFeedItem> pruneUnsupportedCards(List<MfpNewsFeedActivityEntry> list, ConfigService configService, LocalSettingsService localSettingsService, NewsFeedDisplayActivityName newsFeedDisplayActivityName, boolean z) {
        final boolean isChallengesNewsFeedAvailable = ChallengesUtil.isChallengesNewsFeedAvailable(configService);
        final boolean showBlogsV2 = ConfigUtils.showBlogsV2(configService);
        final boolean isProgressPhotosNewsfeedOn = ConfigUtils.isProgressPhotosNewsfeedOn(configService);
        final boolean isMealSharingEnabled = ConfigUtils.isMealSharingEnabled(configService);
        final boolean isNewsFeedVideoEnabled = ConfigUtils.isNewsFeedVideoEnabled(configService);
        final NewsFeedDisplayActivityName newsFeedDisplayActivityName2 = newsFeedDisplayActivityName;
        final LocalSettingsService localSettingsService2 = localSettingsService;
        final boolean z2 = z;
        AnonymousClass1 r0 = new ReturningFunction1<NewsFeedItem, MfpNewsFeedActivityEntry>() {
            boolean hasSeenHeroCard;

            public NewsFeedItem execute(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) throws RuntimeException {
                if (mfpNewsFeedActivityEntry == null) {
                    return null;
                }
                String type = mfpNewsFeedActivityEntry.getType();
                if (Strings.equalsIgnoreCase(type, DataTypes.NEW_FRIEND)) {
                    if (newsFeedDisplayActivityName2 == NewsFeedDisplayActivityName.Home) {
                        mfpNewsFeedActivityEntry = null;
                    }
                    return mfpNewsFeedActivityEntry;
                } else if (Strings.equalsIgnoreCase(type, DataTypes.BLOG_POST)) {
                    return null;
                } else {
                    boolean shouldShowBlogArticlesInNewsFeed = localSettingsService2.shouldShowBlogArticlesInNewsFeed();
                    boolean shouldShowBlogVideosInNewsFeed = localSettingsService2.shouldShowBlogVideosInNewsFeed();
                    MfpNewsFeedActivityEntryData entryData = mfpNewsFeedActivityEntry.getEntryData();
                    boolean z = true;
                    if (entryData instanceof MfpNewsFeedChallengesStatusCardEntry) {
                        z = isChallengesNewsFeedAvailable;
                    } else if (entryData instanceof MfpNewsFeedHeroCardEntry) {
                        if (this.hasSeenHeroCard || ((MfpNewsFeedHeroCardEntry) entryData).getCardLayout() == null) {
                            return null;
                        }
                        this.hasSeenHeroCard = true;
                    } else if (entryData instanceof BlogEntry) {
                        if (!showBlogsV2 || !shouldShowBlogArticlesInNewsFeed) {
                            z = false;
                        }
                    } else if (entryData instanceof MfpBlogDailySummary) {
                        if (showBlogsV2 || !shouldShowBlogArticlesInNewsFeed) {
                            z = false;
                        }
                    } else if (entryData instanceof MfpNewsFeedImageStatusUpdateEntry) {
                        z = entryData instanceof MfpNewsFeedMealPhotoUpdateEntry ? isMealSharingEnabled : isProgressPhotosNewsfeedOn;
                    } else if (!(entryData instanceof VideoEntry)) {
                        z = MfpNewsFeedActivityEntry.isSupportedType(type);
                    } else if (!isNewsFeedVideoEnabled || (z2 && !shouldShowBlogVideosInNewsFeed)) {
                        z = false;
                    }
                    if (!z) {
                        mfpNewsFeedActivityEntry = null;
                    }
                    return mfpNewsFeedActivityEntry;
                }
            }
        };
        return Enumerable.select((Collection<T>) list, false, (ReturningFunction1<U, T>) r0);
    }

    public static Tuple2<Size, String> getBestFitImageSizeAndUrl(String str, List<String> list) {
        Size size = null;
        if (str != null) {
            int size2 = CollectionUtils.size((Collection<?>) list);
            String str2 = null;
            int i = -1;
            for (int i2 = 0; i2 < size2; i2++) {
                String str3 = (String) list.get(i2);
                if (Strings.notEmpty(str3)) {
                    String[] split = str3.split(AvidJSONUtil.KEY_X);
                    if (split.length >= 2) {
                        int tryParseInt = NumberUtils.tryParseInt(split[0]);
                        if (tryParseInt >= NumberUtils.tryParseInt(split[1]) && tryParseInt > i) {
                            i = tryParseInt;
                            str2 = str3;
                        }
                    }
                }
            }
            if (str2 != null) {
                String lastPathSegment = Uri.parse(str).getLastPathSegment();
                size = Size.parseSize(str2);
                str = str.replace(lastPathSegment, String.format("%s-%s%s", new Object[]{FileUtils.getBasename(lastPathSegment), str2, FileUtils.getExtension(lastPathSegment)}));
            }
        }
        return Tuple.create(size, str);
    }

    public static String getBestFitImageUrl(String str, List<String> list) {
        return (String) getBestFitImageSizeAndUrl(str, list).getItem2();
    }
}
