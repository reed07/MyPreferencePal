package com.myfitnesspal.feature.home.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardRenderer;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.model.DailySummaryHeader;
import com.myfitnesspal.feature.home.model.EmptyListDisplayItem;
import com.myfitnesspal.feature.home.model.LoadingNewsFeedItem;
import com.myfitnesspal.feature.home.model.NewStatusItem;
import com.myfitnesspal.feature.home.model.NewsFeedDisplayActivityName;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.model.NewsFeedViewTypes;
import com.myfitnesspal.feature.home.model.ProfileHeader;
import com.myfitnesspal.feature.home.model.ProfileHeaderModel;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.home.ui.view.ChallengesStatusCardViewHolder;
import com.myfitnesspal.feature.home.ui.view.CommentViewHolder;
import com.myfitnesspal.feature.home.ui.view.DailySummaryViewHolder;
import com.myfitnesspal.feature.home.ui.view.EmptyItemViewHolder;
import com.myfitnesspal.feature.home.ui.view.HeroCardViewHolder;
import com.myfitnesspal.feature.home.ui.view.LegacyHeroCardViewHolder;
import com.myfitnesspal.feature.home.ui.view.LoadingViewHolder;
import com.myfitnesspal.feature.home.ui.view.MultiBlogsNewsFeedCard;
import com.myfitnesspal.feature.home.ui.view.NewStatusViewHolder;
import com.myfitnesspal.feature.home.ui.view.NewsFeedStatusCardViewHolder;
import com.myfitnesspal.feature.home.ui.view.ProfileHeaderViewHolder;
import com.myfitnesspal.feature.home.ui.view.SingleBlogNewsFeedCard;
import com.myfitnesspal.feature.home.ui.view.VideoViewHolder;
import com.myfitnesspal.feature.home.util.NewsFeedItemActionUtils;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.PremiumService.Availability;
import com.myfitnesspal.feature.profile.ui.fragment.ProfileFragment.UserType;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityComment;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry.DataTypes;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;

public class NewsFeedAdapter extends NativeAdsAdapter<RecyclerViewHolder<NewsFeedItem>> {
    public static final String EXTRA_NO_FLOW_ID = "";
    private boolean adPinnedToTop;
    private AdapterOperationListener adapterOperationListener;
    private NewsFeedItemActionListener cardActionListener;
    private final Lazy<ConfigService> configService;
    private DailySummaryViewHolder dailySummaryViewHolder;
    private int defaultSpacingPx;
    private final NewsFeedDisplayActivityName displayActivityName;
    private String flowId;
    private final Lazy<ImageService> imageService;
    private final NavigationHelper navigationHelper;
    private final Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;
    private List<NewsFeedItem> newsFeedItems;
    private Lazy<NewsFeedService> newsFeedService;
    private final Lazy<NutrientDashboardRenderer> nutrientDashboardRenderer;
    private final Lazy<PremiumService> premiumService;
    private final ProfileHeaderModel profileHeaderModel;
    private final Session session;
    private final Lazy<UserApplicationSettingsService> userApplicationSettingsService;
    private MiniUserInfo userInfo;
    private final String userName;
    private UserType userType;

    public interface AdapterOperationListener {
        void onItemAdded();

        void onItemRemoved();

        void onItemsRefreshed();
    }

    public NewsFeedAdapter(NavigationHelper navigationHelper2, Lazy<PremiumService> lazy, Lazy<ImageService> lazy2, NewsFeedItemActionListener newsFeedItemActionListener, Lazy<NewsFeedAnalyticsHelper> lazy3, Lazy<ConfigService> lazy4, Session session2, NewsFeedDisplayActivityName newsFeedDisplayActivityName, Lazy<NutrientDashboardRenderer> lazy5, Lazy<UserApplicationSettingsService> lazy6, String str, @Nullable String str2, Lazy<NewsFeedService> lazy7) {
        this(navigationHelper2, lazy, lazy2, newsFeedItemActionListener, lazy3, lazy4, session2, newsFeedDisplayActivityName, lazy5, lazy6, str, null, str2, lazy7);
    }

    public NewsFeedAdapter(NavigationHelper navigationHelper2, Lazy<PremiumService> lazy, Lazy<ImageService> lazy2, NewsFeedItemActionListener newsFeedItemActionListener, Lazy<NewsFeedAnalyticsHelper> lazy3, Lazy<ConfigService> lazy4, Session session2, NewsFeedDisplayActivityName newsFeedDisplayActivityName, Lazy<UserApplicationSettingsService> lazy5, String str, ProfileHeaderModel profileHeaderModel2, @Nullable String str2) {
        this(navigationHelper2, lazy, lazy2, newsFeedItemActionListener, lazy3, lazy4, session2, newsFeedDisplayActivityName, null, lazy5, str, profileHeaderModel2, str2, null);
    }

    public NewsFeedAdapter(NavigationHelper navigationHelper2, Lazy<PremiumService> lazy, Lazy<ImageService> lazy2, NewsFeedItemActionListener newsFeedItemActionListener, Lazy<NewsFeedAnalyticsHelper> lazy3, Lazy<ConfigService> lazy4, Session session2, NewsFeedDisplayActivityName newsFeedDisplayActivityName, Lazy<UserApplicationSettingsService> lazy5, String str, @Nullable String str2) {
        this(navigationHelper2, lazy, lazy2, newsFeedItemActionListener, lazy3, lazy4, session2, newsFeedDisplayActivityName, null, lazy5, str, str2, null);
    }

    private NewsFeedAdapter(NavigationHelper navigationHelper2, Lazy<PremiumService> lazy, Lazy<ImageService> lazy2, NewsFeedItemActionListener newsFeedItemActionListener, Lazy<NewsFeedAnalyticsHelper> lazy3, Lazy<ConfigService> lazy4, Session session2, NewsFeedDisplayActivityName newsFeedDisplayActivityName, Lazy<NutrientDashboardRenderer> lazy5, Lazy<UserApplicationSettingsService> lazy6, String str, ProfileHeaderModel profileHeaderModel2, @Nullable String str2, Lazy<NewsFeedService> lazy7) {
        super(lazy);
        this.newsFeedService = lazy7;
        if (newsFeedItemActionListener != null) {
            this.navigationHelper = navigationHelper2;
            this.premiumService = lazy;
            this.imageService = lazy2;
            this.cardActionListener = newsFeedItemActionListener;
            this.newsFeedAnalyticsHelper = lazy3;
            this.configService = lazy4;
            this.session = session2;
            this.nutrientDashboardRenderer = lazy5;
            this.displayActivityName = newsFeedDisplayActivityName;
            this.userApplicationSettingsService = lazy6;
            this.userName = str;
            this.newsFeedItems = new ArrayList();
            this.profileHeaderModel = profileHeaderModel2;
            this.flowId = str2;
            this.defaultSpacingPx = MyFitnessPalApp.getInstance().getResources().getDimensionPixelSize(R.dimen.news_feed_card_marginTop);
            return;
        }
        throw new IllegalArgumentException("NewsFeedItemActionListener cannot be null");
    }

    public void setMiniUserInfoAndUserType(MiniUserInfo miniUserInfo, UserType userType2) {
        this.userInfo = miniUserInfo;
        this.userType = userType2;
    }

    public void setAdapterOperationListener(AdapterOperationListener adapterOperationListener2) {
        this.adapterOperationListener = adapterOperationListener2;
    }

    public void setNewsFeedItemActionListener(NewsFeedItemActionListener newsFeedItemActionListener) {
        this.cardActionListener = newsFeedItemActionListener;
    }

    public int getItemViewType(int i) {
        NewsFeedViewTypes newsFeedViewTypes;
        NewsFeedItem item = getItem(i);
        if (item instanceof DailySummaryHeader) {
            newsFeedViewTypes = NewsFeedViewTypes.DailySummary;
        } else if (item instanceof ProfileHeader) {
            newsFeedViewTypes = NewsFeedViewTypes.ProfileHeader;
        } else if (item instanceof LoadingNewsFeedItem) {
            newsFeedViewTypes = NewsFeedViewTypes.Loading;
        } else if (item instanceof EmptyListDisplayItem) {
            newsFeedViewTypes = NewsFeedViewTypes.Empty;
        } else if (item instanceof MfpNewsFeedActivityComment) {
            newsFeedViewTypes = NewsFeedViewTypes.Comment;
        } else if (item instanceof NewStatusItem) {
            newsFeedViewTypes = NewsFeedViewTypes.NewStatus;
        } else if (item instanceof MfpNewsFeedActivityEntry) {
            String type = ((MfpNewsFeedActivityEntry) item).getType();
            if (Strings.equals(type, DataTypes.HERO_CARD)) {
                newsFeedViewTypes = NewsFeedViewTypes.HeroCard;
            } else if (Strings.equals(type, DataTypes.CHALLENGE_STATUS_CARD)) {
                newsFeedViewTypes = NewsFeedViewTypes.ChallengesStatusCard;
            } else if (Strings.equals(type, DataTypes.BLOG_INDIVIDUAL_SUMMARY)) {
                newsFeedViewTypes = NewsFeedViewTypes.SingleBlogItem;
            } else if (Strings.equals(type, DataTypes.BLOG_DAILY_SUMMARY)) {
                newsFeedViewTypes = NewsFeedViewTypes.MultiBlogItem;
            } else if (Strings.equals(type, DataTypes.LEGACY_HERO_CARD)) {
                newsFeedViewTypes = NewsFeedViewTypes.LegacyHeroCard;
            } else if (Strings.equals(type, DataTypes.VIDEO)) {
                newsFeedViewTypes = NewsFeedViewTypes.Video;
            } else {
                newsFeedViewTypes = NewsFeedViewTypes.Status;
            }
        } else {
            throw new IllegalArgumentException("Unknown view type!");
        }
        return newsFeedViewTypes.ordinal();
    }

    public RecyclerViewHolder<NewsFeedItem> onCreateViewHolder(ViewGroup viewGroup, int i) {
        NewsFeedViewTypes newsFeedViewTypes = NewsFeedViewTypes.values()[i];
        switch (newsFeedViewTypes) {
            case DailySummary:
                DailySummaryViewHolder dailySummaryViewHolder2 = new DailySummaryViewHolder(viewGroup, this.nutrientDashboardRenderer);
                this.dailySummaryViewHolder = dailySummaryViewHolder2;
                return dailySummaryViewHolder2;
            case NewStatus:
                return new NewStatusViewHolder(viewGroup, this.newsFeedAnalyticsHelper, this.cardActionListener, this.session.getUser().getImageUrl());
            case ProfileHeader:
                ProfileHeaderViewHolder profileHeaderViewHolder = new ProfileHeaderViewHolder(viewGroup, this.navigationHelper, this.configService, this.userInfo, this.userType, this.userName, this.profileHeaderModel, this.session);
                return profileHeaderViewHolder;
            case Loading:
                return new LoadingViewHolder(viewGroup);
            case Empty:
                return new EmptyItemViewHolder(viewGroup);
            case HeroCard:
                return new HeroCardViewHolder(viewGroup, this.navigationHelper, this.cardActionListener, this.newsFeedAnalyticsHelper);
            case SingleBlogItem:
                return new SingleBlogNewsFeedCard(viewGroup, this.navigationHelper, this.newsFeedAnalyticsHelper);
            case MultiBlogItem:
                return new MultiBlogsNewsFeedCard(viewGroup, this.navigationHelper, this.newsFeedAnalyticsHelper);
            case Status:
                NewsFeedStatusCardViewHolder newsFeedStatusCardViewHolder = new NewsFeedStatusCardViewHolder(viewGroup, this.imageService, this.configService, this.cardActionListener, this.navigationHelper, this.newsFeedAnalyticsHelper, this.displayActivityName, this.userName);
                return newsFeedStatusCardViewHolder;
            case Comment:
                return new CommentViewHolder(viewGroup, this.navigationHelper, this.cardActionListener);
            case ChallengesStatusCard:
                return new ChallengesStatusCardViewHolder(viewGroup, this.navigationHelper, this.cardActionListener, this.newsFeedAnalyticsHelper);
            case LegacyHeroCard:
                LegacyHeroCardViewHolder legacyHeroCardViewHolder = new LegacyHeroCardViewHolder(viewGroup, this.navigationHelper, this.cardActionListener, this.newsFeedAnalyticsHelper, this.userName);
                return legacyHeroCardViewHolder;
            case Video:
                VideoViewHolder videoViewHolder = new VideoViewHolder(viewGroup, this.navigationHelper, this.newsFeedAnalyticsHelper, this.userApplicationSettingsService, this.configService, this.flowId);
                return videoViewHolder;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown view type: ");
                sb.append(newsFeedViewTypes);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    private void adjustTopMarginForAds(View view, int i) {
        int i2 = 0;
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        if (!(ConfigUtils.isAppNavMeEnabled((ConfigService) this.configService.get()) && this.adPinnedToTop && ((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.AdFree) != Availability.Available) || i != 0) {
            i2 = this.defaultSpacingPx;
        }
        if (i2 != marginLayoutParams.topMargin) {
            marginLayoutParams.topMargin = i2;
            view.requestLayout();
        }
    }

    public void onBindViewHolder(RecyclerViewHolder<NewsFeedItem> recyclerViewHolder, int i) {
        NewsFeedItem item = getItem(i);
        if (recyclerViewHolder instanceof ProfileHeaderViewHolder) {
            ((ProfileHeaderViewHolder) recyclerViewHolder).setUserType(this.userType);
        }
        recyclerViewHolder.setData(item, i);
        adjustTopMarginForAds(recyclerViewHolder.itemView, i);
        if (item instanceof MfpNewsFeedActivityEntry) {
            ((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).reportNewsFeedEntryViewed((MfpNewsFeedActivityEntry) item, this.flowId, i);
        }
    }

    public int getItemCount() {
        return this.newsFeedItems.size();
    }

    public void onPause() {
        DailySummaryViewHolder dailySummaryViewHolder2 = this.dailySummaryViewHolder;
        if (dailySummaryViewHolder2 != null) {
            dailySummaryViewHolder2.onPause();
        }
    }

    public void onResume(MfpFragment mfpFragment) {
        DailySummaryViewHolder dailySummaryViewHolder2 = this.dailySummaryViewHolder;
        if (dailySummaryViewHolder2 != null) {
            dailySummaryViewHolder2.onResume();
        }
    }

    public void resetDailySummary() {
        DailySummaryViewHolder dailySummaryViewHolder2 = this.dailySummaryViewHolder;
        if (dailySummaryViewHolder2 != null) {
            dailySummaryViewHolder2.reset();
        }
    }

    public void onCardCloseClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        NewsFeedItemActionUtils.cardCloseClick(this.newsFeedService, this, mfpNewsFeedActivityEntry);
    }

    public void addItem(NewsFeedItem newsFeedItem) {
        this.newsFeedItems.add(newsFeedItem);
        notifyItemInserted(this.newsFeedItems.size() - 1);
        AdapterOperationListener adapterOperationListener2 = this.adapterOperationListener;
        if (adapterOperationListener2 != null) {
            adapterOperationListener2.onItemAdded();
        }
    }

    public void refreshItems(List<NewsFeedItem> list) {
        this.newsFeedItems = new ArrayList(list);
        notifyDataSetChanged();
        AdapterOperationListener adapterOperationListener2 = this.adapterOperationListener;
        if (adapterOperationListener2 != null) {
            adapterOperationListener2.onItemsRefreshed();
        }
    }

    public void removeItem(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        int indexOf = this.newsFeedItems.indexOf(mfpNewsFeedActivityEntry);
        if (indexOf != -1) {
            removeItem(indexOf);
        }
    }

    public void removeItem(int i) {
        this.newsFeedItems.remove(i);
        notifyItemRemoved(i);
        AdapterOperationListener adapterOperationListener2 = this.adapterOperationListener;
        if (adapterOperationListener2 != null) {
            adapterOperationListener2.onItemRemoved();
        }
    }

    public void onViewAttachedToWindow(RecyclerViewHolder<NewsFeedItem> recyclerViewHolder) {
        super.onViewAttachedToWindow(recyclerViewHolder);
        if (recyclerViewHolder instanceof VideoViewHolder) {
            ((VideoViewHolder) recyclerViewHolder).start();
        }
    }

    public void onViewDetachedFromWindow(RecyclerViewHolder<NewsFeedItem> recyclerViewHolder) {
        super.onViewDetachedFromWindow(recyclerViewHolder);
        if (recyclerViewHolder instanceof VideoViewHolder) {
            ((VideoViewHolder) recyclerViewHolder).stop();
        }
    }

    public NewsFeedItem getItem(int i) {
        return (NewsFeedItem) this.newsFeedItems.get(i);
    }

    public void setAdPinnedToTop(boolean z) {
        if (z != this.adPinnedToTop) {
            this.adPinnedToTop = z;
            notifyDataSetChanged();
        }
    }
}
