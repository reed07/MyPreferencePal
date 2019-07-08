package com.myfitnesspal.shared.deeplink;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import com.myfitnesspal.android.R;
import com.myfitnesspal.android.login.Welcome;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.addentry.ui.activity.WaterEntryActivity;
import com.myfitnesspal.feature.addentry.ui.activity.WaterEntryActivity.Mode;
import com.myfitnesspal.feature.addfriends.ui.activity.AddFriendsContacts;
import com.myfitnesspal.feature.addfriends.ui.activity.AddFriendsParent;
import com.myfitnesspal.feature.appgallery.ui.AppGalleryActivity;
import com.myfitnesspal.feature.appgallery.ui.OurOtherAppsActivity;
import com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity;
import com.myfitnesspal.feature.blog.ui.activity.BlogActivity;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengesActivity;
import com.myfitnesspal.feature.community.ui.activity.CommunityActivity;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.fileexport.ui.activity.FileExport;
import com.myfitnesspal.feature.fileexport.ui.activity.FileExport.ExportMode;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.friends.ui.activity.CommentsActivity;
import com.myfitnesspal.feature.friends.ui.activity.ComposeMessageActivity.Source;
import com.myfitnesspal.feature.friends.ui.activity.DetailedMessageActivity;
import com.myfitnesspal.feature.friends.ui.activity.FriendsActivity;
import com.myfitnesspal.feature.goals.ui.activity.Goals;
import com.myfitnesspal.feature.help.ui.activity.EmailSettings;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.home.ui.activity.HomeMessagesActivity;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelperImpl;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.premium.ui.activity.SubscriptionStatus;
import com.myfitnesspal.feature.profile.ui.activity.ProfileView;
import com.myfitnesspal.feature.progress.ui.activity.ProgressActivity;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData.ActionType;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData.StartScreen;
import com.myfitnesspal.feature.recipes.ui.activity.RecipeImportBrowserActivity;
import com.myfitnesspal.feature.registration.ui.activity.OAuthActivity;
import com.myfitnesspal.feature.registration.ui.activity.TermsOfUseActivity;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity;
import com.myfitnesspal.feature.settings.ui.activity.ChangePasswordActivity;
import com.myfitnesspal.feature.settings.ui.activity.EditReminder;
import com.myfitnesspal.feature.settings.ui.activity.StepsSettings;
import com.myfitnesspal.framework.deeplink.Dispatcher;
import com.myfitnesspal.framework.deeplink.Route;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.DeepLinkProxyActivity;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.DeepLinkRouterActivity;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.UriUtils;

public class Routes {
    @Route(pattern = "/authorize/?(\\?.*)?", requiresAuthentication = false)
    public void handleAuthorizeRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, OAuthActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/welcome/index/?(\\?.*)?")
    public void handleWelcomeRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, Welcome.newStartIntent(context), bundle);
    }

    @Route(pattern = "/user/.*/status/\\d*/?(\\?.*)?", requiresAuthentication = true)
    public void handleViewStatus(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        try {
            launch(context, dispatcher, uri, CommentsActivity.newStartIntent(context, Long.valueOf(Long.parseLong(uri.getLastPathSegment())).longValue()), bundle);
        } catch (NumberFormatException unused) {
            Ln.w("Failed to parse the status_id from the end of the deep link.", new Object[0]);
            Toast.makeText(dispatcher.getContext(), R.string.failed_to_load_app_data, 0).show();
        }
    }

    @Route(pattern = "/restaurant_logging/?(\\?.*)?", requiresAuthentication = true)
    public void handleRestaurantLogging(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, VenuesActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/restaurant_logging/venue/.*/?(\\?.*)?", requiresAuthentication = true)
    public void handleRestaurantLoggingVenue(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, DeepLinkProxyActivity.newStartIntent(context), bundle);
    }

    @Route(order = 1, pattern = "/user/.*/messages/?(\\?.*)?", requiresAuthentication = true)
    public void handleViewMessage(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, HomeMessagesActivity.newStartIntentForInbox(context), bundle);
    }

    @Route(pattern = "/user/.*/messages/\\d+/?(\\?.*)?", requiresAuthentication = true)
    public void handleMessages(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        try {
            launch(context, dispatcher, uri, DetailedMessageActivity.newStartIntent(context, Long.valueOf(Long.parseLong(uri.getLastPathSegment())).longValue(), Source.NOTIFICATIONS), bundle);
        } catch (NumberFormatException unused) {
            Ln.w("Failed to parse the message_id from the end of the deep link.", new Object[0]);
            Toast.makeText(dispatcher.getContext(), R.string.failed_to_load_app_data, 0).show();
        }
    }

    @Route(pattern = "/user/.*/friends/requests/?(\\?.*)?", requiresAuthentication = true)
    public void handleFriendRequests(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, FriendsActivity.newStartIntentForRequests(context), bundle);
    }

    @Route(pattern = "/user/.*/friends/requests/\\d*/?(\\?.*)?", requiresAuthentication = true)
    public void handleSpecificFriendRequest(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, FriendsActivity.newStartIntentForRequests(context), bundle);
    }

    @Route(pattern = "/user/.*/diary/add/?(\\?.*)?", requiresAuthentication = true)
    public void handleAddToDiary(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, HomeActivity.newStartIntentWithFabShowing(context), bundle);
    }

    @Route(pattern = "/user/.*/diary/?(\\?.*)?", requiresAuthentication = true)
    public void handleDiary(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, Diary.newStartIntentWithDate(context, UriUtils.getQueryParams(uri).getString("date")), bundle);
    }

    @Route(pattern = "/user/.*/friends/?(\\?.*)?", requiresAuthentication = true)
    public void handleAddFriendsWithUserId(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, AddFriendsParent.newStartIntent(context), bundle);
    }

    @Route(pattern = "/friends/?(\\?.*)?", requiresAuthentication = true)
    public void handleFriends(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, FriendsActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/user/.*/fb_friends/?(\\?.*)?", requiresAuthentication = true)
    public void handleAddFBFriends(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, AddFriendsParent.newStartIntent(context).putExtra(Extras.FACEBOOK_REDIRECT, true), bundle);
    }

    @Route(pattern = "/user/.*/contact_friends/?(\\?.*)?", requiresAuthentication = true)
    public void handleAddContactFriends(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, AddFriendsContacts.newStartIntent(context), bundle);
    }

    @Route(pattern = "/user/.*/apps/?(\\?.*)?", requiresAuthentication = true)
    public void handleAppsWithUserId(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, AppGalleryActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/apps/?(\\?.*)?", requiresAuthentication = true)
    public void handleApps(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, AppGalleryActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/log_weight/?(\\?.*)?", requiresAuthentication = true)
    public void handleLogWeight(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        Intent newStartIntent = ProgressActivity.newStartIntent(context, Measurement.WEIGHT);
        newStartIntent.putExtra(Extras.ADD_OR_EDIT_ENTRY_ON_START, true);
        launch(context, dispatcher, uri, newStartIntent, bundle);
    }

    @Route(pattern = "/user/.*/progress/?(\\?.*)?", requiresAuthentication = true)
    public void handleProgress(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        Intent newStartIntent = ProgressActivity.newStartIntent(context, Measurement.WEIGHT);
        newStartIntent.putExtra(Extras.ADD_OR_EDIT_ENTRY_ON_START, true);
        launch(context, dispatcher, uri, newStartIntent, bundle);
    }

    @Route(pattern = "/user/.*/reminders/?(\\?.*)?", requiresAuthentication = true)
    public void handleReminders(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, EditReminder.newStartIntentForAdd(context), bundle);
    }

    @Route(pattern = "/user/.*/add_reminder/?(\\?.*)?", requiresAuthentication = true)
    public void handleAddReminder(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, EditReminder.newStartIntentForAdd(context), bundle);
    }

    @Route(pattern = "/user/.*/email_settings/?(\\?.*)?", requiresAuthentication = true)
    public void handleEmailSettings(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, EmailSettings.newStartIntent(context), bundle);
    }

    @Route(pattern = "/resend_email_verification/?(\\?.*)?", requiresAuthentication = true)
    public void handleResendEmailVerification(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, DeepLinkProxyActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/user/.*/steps_gallery/?(\\?.*)?")
    public void handleStepsGallery(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, AppGalleryActivity.newStartIntentForHardwareTrackers(context), bundle);
    }

    @Route(pattern = "/user/.*/platform_app/[a-zA-Z0-9]+/?(\\?.*)?")
    public void handlePlatformApp(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, AppGalleryActivity.newStartIntent(context, Strings.toString(uri.getLastPathSegment())), bundle);
    }

    @Route(pattern = "/user/.*/step_settings/?(\\?.*)?")
    public void handleStepSettings(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, StepsSettings.newStartIntent(context), bundle);
    }

    @Route(pattern = "/user/.*/my_goals/?(\\?.*)?", requiresAuthentication = true)
    public void handleGoalsRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, Goals.newStartIntent(context), bundle);
    }

    @Route(pattern = "/user/.*/home/?(\\?.*)?", requiresAuthentication = true)
    public void handleHomeRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, HomeActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/user/.*/profile/.*/?(\\?.*)?", requiresAuthentication = true)
    public void handleProfile(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, ProfileView.newStartIntent(context, uri.getLastPathSegment()), bundle);
    }

    @Route(pattern = "/tos/?(\\?.*)?", requiresAuthentication = false)
    public void handleTOSRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, TermsOfUseActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/recipe_parser/?(\\?.)?", requiresAuthentication = true)
    public void handleRecipeParserRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, RecipeImportBrowserActivity.newStartIntentWithRecipeUri(context, BundleUtils.getString(UriUtils.getQueryParams(uri), "url"), RecipeAnalyticsIntentData.create(StartScreen.Deeplink, ActionType.Create)), bundle);
    }

    @Route(pattern = "/blog/?(\\?.*)?", requiresAuthentication = true)
    public void handleBlogRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, BlogActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/forum/?(\\?.)?", requiresAuthentication = true)
    public void handleForumRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, CommunityActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/password_change/?(\\?.)?", requiresAuthentication = true)
    public void handleChangePasswordRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, ChangePasswordActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/premium/?(\\?.*)?", requiresAuthentication = true)
    public void handlePremiumRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, newPremiumFeatureListOrUpsellIntent(context), bundle);
    }

    @Route(order = 1, pattern = "/user/.*/premium/account/?(\\?.*)?", requiresAuthentication = true)
    public void handleViewPremiumAccount(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, SubscriptionStatus.newStartIntent(context), bundle);
    }

    @Route(pattern = "/user/.*/premium/?(\\?.*)?", requiresAuthentication = true)
    public void handlePremiumFeatureListOrUpsell(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, newPremiumFeatureListOrUpsellIntent(context), bundle);
    }

    @Route(pattern = "/uacf_apps/?(\\?.*)?", requiresAuthentication = true)
    public void handleUACFAppsRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, OurOtherAppsActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/challenges/?(\\?.*)?", requiresAuthentication = true)
    public void handleChallengesRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, ChallengesActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/challenges/.*/?(\\?.*)?")
    public void handleChallengeDetailRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, DeepLinkProxyActivity.newStartIntent(context), bundle);
    }

    @Route(order = 2, pattern = "/user/.*/?(\\?.*)?")
    public void handleUserProfileRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, ProfileView.newStartIntent(context, uri.getLastPathSegment()), bundle);
    }

    @Route(order = 9999, pattern = "/.*", requiresAuthentication = true)
    public void catchAllRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, HomeActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/addfood/?\\.*", requiresAuthentication = false)
    public void handleOldAddFoodRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, DeepLinkProxyActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/add_food/?\\.*", requiresAuthentication = false)
    public void handleAddFoodRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, DeepLinkProxyActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/add_exercise/?\\.*", requiresAuthentication = true)
    public void handleAddExerciseRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, DeepLinkProxyActivity.newStartIntent(context), bundle);
    }

    @Route(pattern = "/under_armour_account_faq/?\\.*", requiresAuthentication = false)
    public void handleUnderArmourAccountFaq(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        AnalyticsService analyticsService = MyFitnessPalApp.getInstance().component().analyticsService();
        if (analyticsService != null) {
            analyticsService.reportEvent(Events.JOIN_SCREEN_UA_ACCOUNT_FAQ_CLICK);
        }
        Bundle queryParams = UriUtils.getQueryParams(uri);
        Context context2 = context;
        Dispatcher dispatcher2 = dispatcher;
        launch(context2, dispatcher2, UriUtils.appendAllQueryParams(Uri.parse("https://account.underarmour.com/help"), queryParams), SharedIntents.newUriIntent("https://account.underarmour.com/help"), bundle);
    }

    @Route(pattern = "/file_export/?\\.*", requiresAuthentication = true)
    public void handleFileExportRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, FileExport.createIntentForFileExport(context, ExportMode.Normal), bundle);
    }

    @Route(pattern = "/create_meal/?\\.*", requiresAuthentication = true)
    public void handleCreateMealRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, FoodEditorActivity.newMealItemEditorIntent(context), bundle);
    }

    @Route(pattern = "/diary/add/food/?\\.*", requiresAuthentication = true)
    public void handleDiaryAddFoodRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        Intent intent;
        Bundle queryParams = UriUtils.getQueryParams(uri);
        if (queryParams.containsKey("meal_index")) {
            FoodSearchActivity.Extras mealIndex = new FoodSearchActivity.Extras().setMealIndex(NumberUtils.tryParseInt(queryParams.getString("meal_index"), 0));
            if (context instanceof DeepLinkRouterActivity) {
                intent = ((DeepLinkRouterActivity) context).getFoodSearchRouter().getFoodSearchActivityIntent(context, mealIndex);
            } else {
                intent = FoodSearchActivity.newStartIntent(context, mealIndex);
            }
            launch(context, dispatcher, uri, intent, bundle);
            return;
        }
        launch(context, dispatcher, uri, HomeActivity.newStartIntentDiaryAddDeeplinkSelectMeal(context), bundle);
    }

    @Route(pattern = "/diary/add/water/?\\.*", requiresAuthentication = true)
    public void handleDiaryAddWaterRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, WaterEntryActivity.newStartIntent(context, Mode.Add), bundle);
    }

    @Route(pattern = "/diary/?\\.*", requiresAuthentication = true)
    public void handleDiaryRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, Diary.newStartIntent(context), bundle);
    }

    @Route(pattern = "/weeklydigest/?\\.*", requiresAuthentication = true)
    public void handleWeeklyDigestRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, FullScreenWebView.newStartIntentForWeeklyDigest(context, context.getString(R.string.weekly_digest), true, uri.getQueryParameter("url")), bundle);
    }

    @Route(pattern = "/year_in_review/?\\.*", requiresAuthentication = true)
    public void handleYearInReviewRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        String string = context.getString(R.string.year_in_review);
        StringBuilder sb = new StringBuilder();
        sb.append("https://www.myfitnesspal.com/year-in-review/");
        sb.append(uri.getQueryParameter("year"));
        launch(context, dispatcher, uri, FullScreenWebView.newStartIntentForYearInReview(context, string, true, sb.toString()), bundle);
    }

    @Route(pattern = "/barcode_scanner/?(\\?.*)?", requiresAuthentication = true)
    public void handleBarcodeScannerRoute(Context context, Dispatcher dispatcher, Uri uri, Bundle bundle) {
        launch(context, dispatcher, uri, BarcodeScannerActivity.newStartIntent(context).putExtra(Extras.ADD_TO_MEAL_ON_SUCCESS, true).putExtra(Extras.SEARCH_ON_NO_MATCH, true).putExtra(Extras.STARTED_FROM_WIDGET_OR_NOTIFICATION, true), bundle);
    }

    private void launch(Context context, Dispatcher dispatcher, Uri uri, Intent intent, Bundle bundle) {
        Ln.d("Routing: ", uri.toString());
        dispatcher.withIntent(intent).withExtras(bundle).dispatch(context, uri);
    }

    private Intent newPremiumFeatureListOrUpsellIntent(Context context) {
        PremiumService premiumService = MyFitnessPalApp.getInstance().component().premiumService();
        if (premiumService == null || !premiumService.isPremiumSubscribed()) {
            return PremiumUpsellActivity.newStartIntent(context, PaymentAnalyticsHelperImpl.ANALYTICS_DEEP_LINK);
        }
        return PremiumUpsellActivity.newStartIntent(context, PaymentAnalyticsHelperImpl.ANALYTICS_DEEP_LINK, UpsellDisplayMode.FeatureScreen);
    }

    private ConfigService getConfigService() {
        return MyFitnessPalApp.getInstance().component().configService();
    }
}
