package com.myfitnesspal.feature.drawer.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.feature.appgallery.ui.AppGalleryActivity;
import com.myfitnesspal.feature.appgallery.ui.OurOtherAppsActivity;
import com.myfitnesspal.feature.blog.service.BlogService;
import com.myfitnesspal.feature.blog.ui.activity.BlogActivity;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengesActivity;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.community.service.CommunityService;
import com.myfitnesspal.feature.community.ui.activity.CommunityActivity;
import com.myfitnesspal.feature.debug.ui.activity.AdvancedDebuggingActivity;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.friends.ui.activity.FriendsActivity;
import com.myfitnesspal.feature.friends.ui.activity.MessagesActivity;
import com.myfitnesspal.feature.goals.ui.activity.Goals;
import com.myfitnesspal.feature.help.ui.activity.FeedbackActivity;
import com.myfitnesspal.feature.help.ui.activity.Help;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.nutrition.ui.activity.Nutrition;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.progress.ui.activity.ProgressActivity;
import com.myfitnesspal.feature.recipes.ui.activity.RecipesAndFoods;
import com.myfitnesspal.feature.settings.ui.activity.LegacySettingsActivity;
import com.myfitnesspal.feature.settings.ui.activity.PrivacyCenterActivity;
import com.myfitnesspal.feature.settings.ui.activity.RemindersActivity;
import com.myfitnesspal.feature.settings.ui.activity.StepsSettings;
import com.myfitnesspal.feature.uashop.util.UAShopHelper;
import com.myfitnesspal.shared.constants.Constants.ABTest.ExplorePremiumCopyTest201610;
import com.myfitnesspal.shared.constants.Constants.ABTest.UAShopAndroid201511;
import com.myfitnesspal.shared.model.InAppNotifications;
import com.myfitnesspal.shared.notification.InAppNotificationManager;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.navigation.HasNavigationId;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;

public class LeftDrawerAdapter extends ArrayAdapter<LeftDrawerItem> {
    private final Lazy<BlogService> blogService;
    private final Lazy<CommunityService> communityService;
    private final Lazy<ConfigService> configService;
    private final Lazy<InAppNotificationManager> inAppNotificationManager;
    private final Lazy<LocalSettingsService> localSettingsService;
    private final Lazy<PremiumService> premiumService;
    private int selectedPosition = -1;

    public LeftDrawerAdapter(Context context, Lazy<ConfigService> lazy, Lazy<PremiumService> lazy2, Lazy<InAppNotificationManager> lazy3, Lazy<BlogService> lazy4, Lazy<CommunityService> lazy5, Lazy<LocalSettingsService> lazy6) {
        super(context, R.layout.sliding_menu_item, R.id.text);
        this.configService = lazy;
        this.premiumService = lazy2;
        this.inAppNotificationManager = lazy3;
        this.blogService = lazy4;
        this.communityService = lazy5;
        this.localSettingsService = lazy6;
        resetDrawerItems();
    }

    public void refresh() {
        resetDrawerItems();
    }

    public boolean contains(LeftDrawerItemType leftDrawerItemType) {
        for (int i = 0; i < getCount(); i++) {
            if (((LeftDrawerItem) getItem(i)).getType() == leftDrawerItemType) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        TextView textView = (TextView) ViewUtils.findById(view2, R.id.text);
        ImageView imageView = (ImageView) ViewUtils.findById(view2, R.id.icon);
        ViewUtils.setVisible(i == 0, ViewUtils.findById(view2, R.id.top_padding));
        LeftDrawerItem leftDrawerItem = (LeftDrawerItem) getItem(i);
        textView.setText(leftDrawerItem.getTitleId());
        imageView.setImageResource(leftDrawerItem.getIconId());
        boolean contextMatchesItem = contextMatchesItem(view2.getContext(), leftDrawerItem);
        if (contextMatchesItem) {
            this.selectedPosition = i;
            view2.setBackgroundResource(R.color.black_overlay_10_percent);
        } else {
            view2.setBackgroundResource(R.drawable.list_item_bg_selector);
        }
        imageView.setEnabled(contextMatchesItem);
        View findById = ViewUtils.findById(view2, R.id.drawer_item_decoration_container);
        TextView textView2 = (TextView) ViewUtils.findById(findById, R.id.drawer_notification_count);
        TextView textView3 = (TextView) ViewUtils.findById(findById, R.id.text_badge);
        findById.setVisibility(8);
        textView2.setVisibility(8);
        textView3.setVisibility(8);
        if (itemHasNotifications(leftDrawerItem)) {
            ViewUtils.setVisible(true, findById);
            ViewUtils.setVisible(true, textView2);
            textView2.setText(Strings.toString(Integer.valueOf(getNotificationCountFor(leftDrawerItem))));
        }
        if (leftDrawerItem.getBadgeId() > 0) {
            ViewUtils.setVisible(true, findById);
            ViewUtils.setVisible(true, textView3);
            textView3.setText(leftDrawerItem.getBadgeId());
        }
        return view2;
    }

    public long getItemId(int i) {
        return (long) ((LeftDrawerItem) getItem(i)).getType().ordinal();
    }

    public boolean positionIsSelectable(int i) {
        int i2 = this.selectedPosition;
        return i2 == -1 || i2 != i;
    }

    private void resetDrawerItems() {
        clear();
        ArrayList arrayList = new ArrayList();
        BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
        if (((PremiumService) this.premiumService.get()).isPremiumAvailable()) {
            String variant = ((ConfigService) this.configService.get()).getVariant(ExplorePremiumCopyTest201610.NAME);
            int i = R.string.explore_premium;
            if (Strings.equals(variant, ExplorePremiumCopyTest201610.GO)) {
                i = R.string.go_premium;
            } else if (Strings.equals(variant, ExplorePremiumCopyTest201610.UPGRADE)) {
                i = R.string.upgrade_to_premium;
            }
            if (((PremiumService) this.premiumService.get()).isPremiumSubscribed()) {
                i = R.string.premium_features;
            }
            arrayList.add(new LeftDrawerItem().withTitleId(i).withType(LeftDrawerItemType.Premium).withIconId(R.drawable.ic_premium_black));
        }
        String variant2 = ((ConfigService) this.configService.get()).getVariant(UAShopAndroid201511.NAME, Boolean.FALSE.toString());
        if (variant2.equals(UAShopAndroid201511.ATF)) {
            arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_ua_shop).withType(LeftDrawerItemType.UaShop).withIconId(R.drawable.ic_ua_black).withMatchClass(UAShopHelper.class));
        }
        arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_home).withType(LeftDrawerItemType.Home).withIconId(R.drawable.ic_home_black).withMatchClass(HomeActivity.class));
        arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_diary).withType(LeftDrawerItemType.Diary).withIconId(R.drawable.ic_diary_black).withMatchClass(Diary.class));
        arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_progress).withType(LeftDrawerItemType.Progress).withIconId(R.drawable.ic_progress_black).withMatchClass(ProgressActivity.class));
        arrayList.add(new LeftDrawerItem().withTitleId(R.string.goals).withType(LeftDrawerItemType.Goals).withIconId(R.drawable.ic_goals_black).withMatchClass(Goals.class));
        if (ChallengesUtil.isChallengesAvailable((ConfigService) this.configService.get())) {
            arrayList.add(new LeftDrawerItem().withTitleId(R.string.challenges).withType(LeftDrawerItemType.Challenges).withIconId(R.drawable.ic_challenges_black).withMatchClass(ChallengesActivity.class));
        }
        arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_nutrition).withType(LeftDrawerItemType.Nutrition).withIconId(R.drawable.ic_nutrition_black).withMatchClass(Nutrition.class));
        arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_recipes_and_foods_new).withType(LeftDrawerItemType.RecipesAndFoods).withIconId(R.drawable.ic_recipes_black).withMatchClass(RecipesAndFoods.class));
        arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_app_gallery).withType(LeftDrawerItemType.AppGallery).withIconId(R.drawable.ic_apps_black).withMatchClass(AppGalleryActivity.class));
        arrayList.add(new LeftDrawerItem().withTitleId(R.string.steps).withType(LeftDrawerItemType.Steps).withIconId(R.drawable.ic_steps_black).withMatchClass(StepsSettings.class));
        if (((BlogService) this.blogService.get()).isBlogEnabled()) {
            arrayList.add(new LeftDrawerItem().withTitleId(R.string.blog).withType(LeftDrawerItemType.Blog).withIconId(R.drawable.ic_blog_black).withMatchClass(BlogActivity.class));
        }
        if (((CommunityService) this.communityService.get()).isCommunityEnabled()) {
            arrayList.add(new LeftDrawerItem().withTitleId(R.string.community).withType(LeftDrawerItemType.Community).withIconId(R.drawable.ic_community_black).withMatchClass(CommunityActivity.class));
        }
        arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_reminders).withType(LeftDrawerItemType.Reminders).withIconId(R.drawable.ic_reminder_black).withMatchClass(RemindersActivity.class));
        arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_friends).withType(LeftDrawerItemType.Friends).withIconId(R.drawable.ic_friends_black).withMatchClass(FriendsActivity.class));
        arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_messages).withType(LeftDrawerItemType.Messages).withIconId(R.drawable.ic_email_black).withMatchClass(MessagesActivity.class));
        if (variant2.equals(UAShopAndroid201511.BTF)) {
            arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_ua_shop).withType(LeftDrawerItemType.UaShop).withIconId(R.drawable.ic_ua_black).withMatchClass(UAShopHelper.class));
        }
        if (ConfigUtils.isXPromoOurAppsEnabled((ConfigService) this.configService.get())) {
            arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_uacf_apps).withType(LeftDrawerItemType.UaApps).withIconId(R.drawable.ic_other_apps_black).withMatchClass(OurOtherAppsActivity.class));
        }
        arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_privacy_center).withType(LeftDrawerItemType.PrivacyCenter).withIconId(R.drawable.ic_privacy).withMatchClass(PrivacyCenterActivity.class));
        arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_settings).withType(LeftDrawerItemType.Settings).withIconId(R.drawable.ic_settings_black).withMatchClass(LegacySettingsActivity.class));
        if (buildConfiguration.isBetaBuild()) {
            arrayList.add(new LeftDrawerItem().withTitleId(R.string.beta_feedback).withType(LeftDrawerItemType.BetaFeedback).withIconId(R.drawable.ic_beta_feedback).withMatchClass(FeedbackActivity.class));
        }
        arrayList.add(new LeftDrawerItem().withTitleId(R.string.menu_help).withType(LeftDrawerItemType.Help).withIconId(R.drawable.ic_help_black).withMatchClass(Help.class));
        if (buildConfiguration.isDebug() || buildConfiguration.isQABuild()) {
            arrayList.add(new LeftDrawerItem().withTitleId(R.string.advanced_debugging).withType(LeftDrawerItemType.DebugMenu).withIconId(R.drawable.ic_debugging_black).withMatchClass(AdvancedDebuggingActivity.class));
        }
        addAll(arrayList);
    }

    private int getNotificationCountFor(LeftDrawerItem leftDrawerItem) {
        InAppNotifications fetchInAppNotifications = fetchInAppNotifications();
        switch (leftDrawerItem.getType()) {
            case Messages:
                return fetchInAppNotifications.getMessageCount();
            case Friends:
                return fetchInAppNotifications.getFriendRequestCount();
            case Challenges:
                return leftDrawerItem.getNotificationCount();
            default:
                return 0;
        }
    }

    private boolean itemHasNotifications(LeftDrawerItem leftDrawerItem) {
        InAppNotifications fetchInAppNotifications = fetchInAppNotifications();
        switch (leftDrawerItem.getType()) {
            case Messages:
                if (fetchInAppNotifications.getMessageCount() <= 0) {
                    return false;
                }
                leftDrawerItem.setNotificationCount(fetchInAppNotifications.getMessageCount());
                return true;
            case Friends:
                if (fetchInAppNotifications.getFriendRequestCount() <= 0) {
                    return false;
                }
                leftDrawerItem.setNotificationCount(fetchInAppNotifications.getFriendRequestCount());
                return true;
            case Challenges:
                if (((LocalSettingsService) this.localSettingsService.get()).getUnseenNewChallenges() <= 0) {
                    return false;
                }
                leftDrawerItem.setNotificationCount(((LocalSettingsService) this.localSettingsService.get()).getUnseenNewChallenges());
                return true;
            default:
                return false;
        }
    }

    private InAppNotifications fetchInAppNotifications() {
        return (InAppNotifications) ((InAppNotificationManager) this.inAppNotificationManager.get()).getCurrentInAppNotifications().blockingGet();
    }

    private boolean contextMatchesItem(@NonNull Context context, @NonNull LeftDrawerItem leftDrawerItem) {
        if (!(context instanceof HasNavigationId) || !leftDrawerItem.matchesNavigationId(((HasNavigationId) context).getNavigationId())) {
            return leftDrawerItem.matchesClass(context);
        }
        return true;
    }
}
