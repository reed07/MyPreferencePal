package com.myfitnesspal.shared.injection.component;

import com.myfitnesspal.android.login.Welcome;
import com.myfitnesspal.feature.addentry.ui.activity.AddFood;
import com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView;
import com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2;
import com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity;
import com.myfitnesspal.feature.addentry.ui.activity.WaterEntryActivity;
import com.myfitnesspal.feature.addentry.ui.dialog.AddCustomFoodImprovementDialogFragment;
import com.myfitnesspal.feature.addentry.ui.dialog.EditServingsDialogFragment;
import com.myfitnesspal.feature.addentry.ui.dialog.EditServingsDialogFragmentV2;
import com.myfitnesspal.feature.addentry.ui.dialog.EditV2SearchServingsDialogFragment;
import com.myfitnesspal.feature.addentry.ui.dialog.SearchViewItemContextualDialog;
import com.myfitnesspal.feature.addfriends.ui.activity.AddFriendsContacts;
import com.myfitnesspal.feature.addfriends.ui.activity.AddFriendsFacebook;
import com.myfitnesspal.feature.addfriends.ui.activity.AddFriendsParent;
import com.myfitnesspal.feature.addfriends.ui.activity.AddFriendsSplash;
import com.myfitnesspal.feature.alexainterstitial.ui.activity.AlexaInterstitialActivity;
import com.myfitnesspal.feature.appgallery.ui.AppDetailFragment;
import com.myfitnesspal.feature.appgallery.ui.AppGalleryActivity;
import com.myfitnesspal.feature.appgallery.ui.AppsHomeFragment;
import com.myfitnesspal.feature.appgallery.ui.GoogleFitPermissionsFragment;
import com.myfitnesspal.feature.appgallery.ui.OurOtherAppsFragment;
import com.myfitnesspal.feature.appgallery.ui.XPromoInterstitialActivity;
import com.myfitnesspal.feature.appgallery.ui.XPromoInterstitialFragment;
import com.myfitnesspal.feature.barcode.ui.activity.BarcodeMatchActivity;
import com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity;
import com.myfitnesspal.feature.barcode.ui.fragment.BarcodeScanDialogFragment;
import com.myfitnesspal.feature.blog.ui.activity.BlogActivity;
import com.myfitnesspal.feature.blog.ui.fragment.BlogFragment;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengeAchievementActivity;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengeDetailActivity;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengeFriendListActivity;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengeShareActivity;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengesActivity;
import com.myfitnesspal.feature.challenges.ui.activity.InviteContactsToChallengeActivity;
import com.myfitnesspal.feature.challenges.ui.activity.InviteSourceListActivity;
import com.myfitnesspal.feature.challenges.ui.activity.JoinChallengeActivity;
import com.myfitnesspal.feature.challenges.ui.activity.UserAchievementsActivity;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengeDetailsFragment;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengeFriendsFragment;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengePrizesFragment;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengeWebViewFragment;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengesFragment;
import com.myfitnesspal.feature.challenges.ui.fragment.JoinedChallengeSummaryFragment;
import com.myfitnesspal.feature.challenges.ui.fragment.JoinedFriendsFragment;
import com.myfitnesspal.feature.challenges.ui.fragment.JoinedPrizesFragment;
import com.myfitnesspal.feature.community.ui.activity.CommunityActivity;
import com.myfitnesspal.feature.community.ui.fragment.CommunityFragment;
import com.myfitnesspal.feature.consents.ui.activity.AdConsentsActivity;
import com.myfitnesspal.feature.consents.ui.activity.ConsentsActivity;
import com.myfitnesspal.feature.consents.ui.activity.LearnMoreActivity;
import com.myfitnesspal.feature.dashboard.ui.activity.NutrientDashboardSettingsActivity;
import com.myfitnesspal.feature.dashboard.ui.fragment.CustomNutrientDashboardSelectionFragment;
import com.myfitnesspal.feature.dashboard.ui.fragment.NutrientDashboardPresetSelectionFragment;
import com.myfitnesspal.feature.debug.ui.activity.AnalyticsEventsActivity;
import com.myfitnesspal.feature.debug.ui.activity.EndpointActivity;
import com.myfitnesspal.feature.debug.ui.activity.FlagResetActivity;
import com.myfitnesspal.feature.debug.ui.activity.LocaleOverrideActivity;
import com.myfitnesspal.feature.debug.ui.activity.PaymentsDebugActivity;
import com.myfitnesspal.feature.debug.ui.activity.PremiumDebugActivity;
import com.myfitnesspal.feature.debug.ui.activity.ProgressPhotosDebugActivity;
import com.myfitnesspal.feature.debug.ui.activity.RetrieveMealFoodDebugActivity;
import com.myfitnesspal.feature.debug.ui.activity.RolloutDebugActivity;
import com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment;
import com.myfitnesspal.feature.debug.ui.fragment.AnalyticsEventsFragment;
import com.myfitnesspal.feature.debug.ui.fragment.DebugLogsFragment;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountActivity;
import com.myfitnesspal.feature.diary.ui.activity.CompleteDiaryActivity;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.diary.ui.activity.DiarySettingsActivity;
import com.myfitnesspal.feature.diary.ui.activity.EditDiaryNoteView;
import com.myfitnesspal.feature.diary.ui.activity.FriendDiary;
import com.myfitnesspal.feature.diary.ui.dialog.DiaryLongPressDialogFragment;
import com.myfitnesspal.feature.diary.ui.dialog.DiaryMoreActionsDialog;
import com.myfitnesspal.feature.diary.ui.dialog.DiaryQuickToolsDialogFragment;
import com.myfitnesspal.feature.diary.ui.dialog.ExerciseTypeDialogFragment;
import com.myfitnesspal.feature.diary.ui.dialog.FriendDiaryPasswordDialogFragment;
import com.myfitnesspal.feature.diary.ui.fragment.DiaryFragmentBase;
import com.myfitnesspal.feature.diary.ui.fragment.FriendDiaryFragment;
import com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment;
import com.myfitnesspal.feature.drawer.ui.view.LeftDrawerLayout;
import com.myfitnesspal.feature.drawer.ui.view.LeftDrawerMenu;
import com.myfitnesspal.feature.exercise.ui.activity.AddExerciseEntry;
import com.myfitnesspal.feature.exercise.ui.activity.EditCardio;
import com.myfitnesspal.feature.exercise.ui.activity.EditStrength;
import com.myfitnesspal.feature.exercise.ui.activity.ExerciseSearchActivity;
import com.myfitnesspal.feature.exercise.ui.activity.GenericExercise;
import com.myfitnesspal.feature.exercise.ui.activity.NewCardio;
import com.myfitnesspal.feature.exercise.ui.activity.NewStrength;
import com.myfitnesspal.feature.exercise.ui.dialog.ExerciseSortOrderDialogFragment;
import com.myfitnesspal.feature.exercise.ui.fragment.ExerciseSearchFragment;
import com.myfitnesspal.feature.exercise.ui.fragment.MyExercisesFragment;
import com.myfitnesspal.feature.explore.ui.activity.ExploreActivity;
import com.myfitnesspal.feature.explore.ui.fragment.PrototypeExploreFragment;
import com.myfitnesspal.feature.explore.ui.viewmodel.ExploreViewModel.ExploreDataTask;
import com.myfitnesspal.feature.fileexport.ui.activity.FileExport;
import com.myfitnesspal.feature.fileexport.ui.activity.FileExportPreview;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodNotesActivity;
import com.myfitnesspal.feature.foodeditor.ui.dialog.EditServingsDialogFragmentBase;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.BarcodeMultiAddMixin;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.FoodEditorMixin;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.MenuItemEditorMixin;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.MultiAddFoodEditorMixin;
import com.myfitnesspal.feature.foodfeedback.mixin.FoodFeedbackOptionsMixin;
import com.myfitnesspal.feature.foodfeedback.ui.activity.FoodFeedbackActivity;
import com.myfitnesspal.feature.friends.ui.activity.CommentsActivity;
import com.myfitnesspal.feature.friends.ui.activity.ComposeMessageActivity;
import com.myfitnesspal.feature.friends.ui.activity.DetailedMessageActivity;
import com.myfitnesspal.feature.friends.ui.activity.FriendsActivity;
import com.myfitnesspal.feature.friends.ui.activity.InviteFriendActivity;
import com.myfitnesspal.feature.friends.ui.activity.NewStatusOrCommentActivity;
import com.myfitnesspal.feature.friends.ui.fragment.FriendRequestsFragment;
import com.myfitnesspal.feature.friends.ui.fragment.FriendsListFragment;
import com.myfitnesspal.feature.friends.ui.fragment.LikesListFragment;
import com.myfitnesspal.feature.friends.ui.fragment.MessagesFragment;
import com.myfitnesspal.feature.gdprhelp.activity.GDPRHelpActivity;
import com.myfitnesspal.feature.goals.ui.activity.CalorieAdjustmentExplanationView;
import com.myfitnesspal.feature.goals.ui.activity.CalorieAdjustmentIntro;
import com.myfitnesspal.feature.goals.ui.activity.CustomExerciseCaloriesActivity;
import com.myfitnesspal.feature.goals.ui.activity.EditCustomMacroGoalsActivity;
import com.myfitnesspal.feature.goals.ui.activity.ExerciseCaloriesActivity;
import com.myfitnesspal.feature.goals.ui.activity.MacroGoalEditorActivity;
import com.myfitnesspal.feature.goals.ui.activity.MealGoalsActivity;
import com.myfitnesspal.feature.goals.ui.activity.UpdateGoals;
import com.myfitnesspal.feature.goals.ui.dialog.ActivityLevelDialogFragment;
import com.myfitnesspal.feature.goals.ui.dialog.ExerciseGoalsDialogFragment;
import com.myfitnesspal.feature.goals.ui.dialog.MacroNutrientEditorDialog;
import com.myfitnesspal.feature.goals.ui.dialog.NetEnergyGoalDialogFragment;
import com.myfitnesspal.feature.goals.ui.dialog.WeightGoalDialogFragment;
import com.myfitnesspal.feature.goals.ui.fragment.AdditionalNutrientGoalsFragment;
import com.myfitnesspal.feature.goals.ui.fragment.CustomGoalByDayFragment;
import com.myfitnesspal.feature.goals.ui.fragment.EatingDisorderUpdateGoalCompleteFragment;
import com.myfitnesspal.feature.goals.ui.fragment.EditCustomMacroGoalsFragment;
import com.myfitnesspal.feature.goals.ui.fragment.EditMacroGoalsByGramsFragment;
import com.myfitnesspal.feature.goals.ui.fragment.EditMacroGoalsByPercentFragment;
import com.myfitnesspal.feature.goals.ui.fragment.ExerciseCaloriesFragment;
import com.myfitnesspal.feature.goals.ui.fragment.GoalsFragment;
import com.myfitnesspal.feature.goals.ui.fragment.MealGoalsFragment;
import com.myfitnesspal.feature.goals.ui.fragment.UpdateGoalsCompleteFragment;
import com.myfitnesspal.feature.goals.ui.fragment.UpdateGoalsFragment;
import com.myfitnesspal.feature.help.ui.activity.AboutUs;
import com.myfitnesspal.feature.help.ui.activity.Faq;
import com.myfitnesspal.feature.help.ui.activity.FaqFeedbackBaseActivity;
import com.myfitnesspal.feature.help.ui.activity.NewsFeedSettings;
import com.myfitnesspal.feature.help.ui.fragment.HelpListFragment;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.home.ui.activity.HomeMessagesActivity;
import com.myfitnesspal.feature.home.ui.activity.ImageReportingActivity;
import com.myfitnesspal.feature.home.ui.activity.ImageReportingDetailsActivity;
import com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity;
import com.myfitnesspal.feature.home.ui.dialog.AppRatingDialogFragment;
import com.myfitnesspal.feature.home.ui.fragment.HomeFragment;
import com.myfitnesspal.feature.meals.ui.dialog.DeleteMealConfirmationDialogFragment;
import com.myfitnesspal.feature.meals.ui.dialog.EditMealNameDialogFragment;
import com.myfitnesspal.feature.meals.ui.dialog.MealFoodPermissionSelectionDialogFragment;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.meals.ui.mixin.MealIngredientMixin;
import com.myfitnesspal.feature.meals.ui.mixin.SharedMealViewerMixin;
import com.myfitnesspal.feature.notificationinbox.ui.activity.NotificationInboxActivity;
import com.myfitnesspal.feature.notificationinbox.ui.fragment.NotificationInboxFragment;
import com.myfitnesspal.feature.nutrition.ui.activity.FoodListsActivity;
import com.myfitnesspal.feature.nutrition.ui.activity.Nutrition;
import com.myfitnesspal.feature.nutrition.ui.fragment.GraphViewFragment;
import com.myfitnesspal.feature.nutrition.ui.view.CaloriePieLegend;
import com.myfitnesspal.feature.nutrition.ui.view.CustomBarChart;
import com.myfitnesspal.feature.nutrition.ui.view.CustomLineChart;
import com.myfitnesspal.feature.nutrition.ui.view.CustomPieChart;
import com.myfitnesspal.feature.nutrition.ui.view.MiniFoodList;
import com.myfitnesspal.feature.onboarding.ui.activity.OnboardingActivity;
import com.myfitnesspal.feature.onboarding.ui.fragment.AnimatedOnboardingFragment;
import com.myfitnesspal.feature.onboarding.ui.fragment.WelcomeScreenFragment;
import com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity;
import com.myfitnesspal.feature.payments.ui.activity.PaymentActivityBase;
import com.myfitnesspal.feature.payments.ui.activity.PaymentConfirmation;
import com.myfitnesspal.feature.permissions.PermissionsFragment;
import com.myfitnesspal.feature.premium.ui.activity.PremiumInterstitialActivity;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.premium.ui.activity.SubscriptionStatus;
import com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellFragment;
import com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellWebFragment;
import com.myfitnesspal.feature.premium.ui.fragment.SubscriptionStatusFragment;
import com.myfitnesspal.feature.profile.ui.activity.MeActivity;
import com.myfitnesspal.feature.profile.ui.fragment.MyInfoFragment;
import com.myfitnesspal.feature.profile.ui.fragment.MyItemsFragment;
import com.myfitnesspal.feature.profile.ui.fragment.ProfileFragment;
import com.myfitnesspal.feature.profile.ui.viewmodel.MyInfoViewModel.OfflineDataTask;
import com.myfitnesspal.feature.profile.ui.viewmodel.MyInfoViewModel.OnlineDataTask;
import com.myfitnesspal.feature.profile.ui.viewmodel.MyItemsViewModel.MyItemsTask;
import com.myfitnesspal.feature.progress.ui.activity.AddWeightActivity;
import com.myfitnesspal.feature.progress.ui.activity.ImportPhotoActivity;
import com.myfitnesspal.feature.progress.ui.activity.ProgressActivity;
import com.myfitnesspal.feature.progress.ui.activity.ProgressCongratsActivity;
import com.myfitnesspal.feature.progress.ui.activity.ProgressPhotosGalleryActivity;
import com.myfitnesspal.feature.progress.ui.activity.ProgressPhotosInterstitialActivity;
import com.myfitnesspal.feature.progress.ui.activity.RecommendGoal;
import com.myfitnesspal.feature.progress.ui.activity.ShareProgressActivity;
import com.myfitnesspal.feature.progress.ui.activity.StatusUpdateActivity;
import com.myfitnesspal.feature.progress.ui.dialog.GraphPeriodDialogFragment;
import com.myfitnesspal.feature.progress.ui.dialog.MeasurementTypeDialogFragment;
import com.myfitnesspal.feature.progress.ui.dialog.MeasurementValueDialogFragment;
import com.myfitnesspal.feature.progress.ui.dialog.ProgressEntryLongPressDialogFragment;
import com.myfitnesspal.feature.progress.ui.fragment.ImportPhotoFragment;
import com.myfitnesspal.feature.progress.ui.fragment.LegacyWeightPickerFragment;
import com.myfitnesspal.feature.recipes.ui.activity.CreateRecipeManuallyActivity;
import com.myfitnesspal.feature.recipes.ui.activity.IngredientMatchingActivity;
import com.myfitnesspal.feature.recipes.ui.activity.RecipeDetailsActivity;
import com.myfitnesspal.feature.recipes.ui.activity.RecipeImportBrowserActivity;
import com.myfitnesspal.feature.recipes.ui.activity.RecipeImportReviewActivity;
import com.myfitnesspal.feature.recipes.ui.activity.RecipesAndFoods;
import com.myfitnesspal.feature.recipes.ui.dialog.CreateRecipeDialogFragment;
import com.myfitnesspal.feature.recipes.ui.fragment.CreateOrEditRecipeFragment;
import com.myfitnesspal.feature.recipes.ui.fragment.MyFoodsFragment;
import com.myfitnesspal.feature.recipes.ui.fragment.MyMealsFragment;
import com.myfitnesspal.feature.recipes.ui.fragment.MyRecipesFragment;
import com.myfitnesspal.feature.recipes.ui.fragment.RecipeDetailsFragment;
import com.myfitnesspal.feature.recipes.ui.view.IngredientsContainer;
import com.myfitnesspal.feature.registration.ui.activity.AccountRestrictedActivity;
import com.myfitnesspal.feature.registration.ui.activity.FacebookLoginActivity;
import com.myfitnesspal.feature.registration.ui.activity.FinishOnboardingActivity;
import com.myfitnesspal.feature.registration.ui.activity.ForgotPasswordActivity;
import com.myfitnesspal.feature.registration.ui.activity.LoginActivity;
import com.myfitnesspal.feature.registration.ui.activity.LogoutActivity;
import com.myfitnesspal.feature.registration.ui.activity.OAuthActivity;
import com.myfitnesspal.feature.registration.ui.activity.PrefetchActivity;
import com.myfitnesspal.feature.registration.ui.activity.SignUpActivity;
import com.myfitnesspal.feature.registration.ui.activity.TermsOfUseActivity;
import com.myfitnesspal.feature.registration.ui.activity.UpdatedTermsActivity;
import com.myfitnesspal.feature.registration.ui.dialog.SignUpHeightDialogFragment;
import com.myfitnesspal.feature.registration.ui.dialog.SignUpWeightDialogFragment;
import com.myfitnesspal.feature.registration.ui.fragment.LoginPleaseWaitFragment;
import com.myfitnesspal.feature.registration.ui.fragment.LoginSignInUpButtonsFragment;
import com.myfitnesspal.feature.registration.ui.fragment.LoginUserPassFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpActivityLevelFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpCongratsFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpFragmentBase;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpGenderAgeFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpGoalTypeFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpMarketingOptInFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpUsernamePasswordEmailFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpWeeklyWeightGoalFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpWeightHeightFragment;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.MenusActivity;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.RestaurantLoggingInterstitial;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.SearchMatchActivity;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity;
import com.myfitnesspal.feature.restaurantlogging.ui.dialog.NotifyRequestMenuDialogFragment;
import com.myfitnesspal.feature.restaurantlogging.ui.view.MenusListView;
import com.myfitnesspal.feature.restaurantlogging.ui.view.VenuesListView;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivityV2;
import com.myfitnesspal.feature.search.ui.dialog.MealFilterDialog;
import com.myfitnesspal.feature.search.ui.dialog.SearchCategoryDialog;
import com.myfitnesspal.feature.search.ui.dialog.SortOrderDialog;
import com.myfitnesspal.feature.search.ui.fragment.LocalFoodSearchFragment;
import com.myfitnesspal.feature.search.ui.fragment.LocalFoodSearchFragmentV2;
import com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment;
import com.myfitnesspal.feature.search.ui.viewmodel.OnlineFoodSearchViewModel;
import com.myfitnesspal.feature.settings.ui.activity.AutoPlaySettings;
import com.myfitnesspal.feature.settings.ui.activity.ChangePasswordActivity;
import com.myfitnesspal.feature.settings.ui.activity.CustomMealNames;
import com.myfitnesspal.feature.settings.ui.activity.EditCardioExercise;
import com.myfitnesspal.feature.settings.ui.activity.EditFood;
import com.myfitnesspal.feature.settings.ui.activity.EditProfile;
import com.myfitnesspal.feature.settings.ui.activity.EditReminder;
import com.myfitnesspal.feature.settings.ui.activity.FacebookSettings;
import com.myfitnesspal.feature.settings.ui.activity.PasscodeView;
import com.myfitnesspal.feature.settings.ui.activity.PrivacyCenterActivity;
import com.myfitnesspal.feature.settings.ui.activity.RemindersActivity;
import com.myfitnesspal.feature.settings.ui.activity.SelectReminder;
import com.myfitnesspal.feature.settings.ui.activity.SettingsActivity;
import com.myfitnesspal.feature.settings.ui.activity.TroubleshootingActivity;
import com.myfitnesspal.feature.settings.ui.activity.ViewFoodActivity;
import com.myfitnesspal.feature.settings.ui.activity.WeeklyNutritionSettings;
import com.myfitnesspal.feature.settings.ui.dialog.CountryDialogFragment;
import com.myfitnesspal.feature.settings.ui.dialog.GenderDialogFragment;
import com.myfitnesspal.feature.settings.ui.dialog.HeightDialogFragment;
import com.myfitnesspal.feature.settings.ui.dialog.PinCodeDialogFragment;
import com.myfitnesspal.feature.settings.ui.dialog.UnitsDialogFragment;
import com.myfitnesspal.feature.settings.ui.fragment.DiarySettingsFragment;
import com.myfitnesspal.feature.settings.ui.fragment.EmailSettingsListFragment;
import com.myfitnesspal.feature.settings.ui.fragment.RemindersFragment;
import com.myfitnesspal.feature.settings.ui.fragment.SettingsListFragment;
import com.myfitnesspal.feature.settings.ui.fragment.SharingAndPrivacySettingsListFragment;
import com.myfitnesspal.feature.settings.ui.fragment.StepsSettingsListFragment;
import com.myfitnesspal.feature.settings.ui.fragment.WeeklyNutritionSettingsListFragment;
import com.myfitnesspal.feature.video.activity.VideoActivity;
import com.myfitnesspal.feature.walkthrough.ui.activity.WalkthroughLoggingActivity;
import com.myfitnesspal.feature.walkthrough.ui.fragment.WalkthroughFoodSearchFragment;
import com.myfitnesspal.feature.walkthrough.ui.fragment.WalkthroughServingSizeV2Fragment;
import com.myfitnesspal.shared.ui.activity.FloatingButtonMixin;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.activity.impl.DisconnectFacebook;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.DeepLinkRouterActivity;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin.AddExerciseDeepLinkMixin;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin.AddFoodDeepLinkMixin;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin.AppIndexerMixinBase;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin.ChallengesMixin;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin.DeepLinkMixinBase;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin.SendVerificationEmailMixin;
import com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin.VenueDeepLinkMixin;
import com.myfitnesspal.shared.ui.activity.impl.resourceloader.mixin.GetRecipeV2Mixin;
import com.myfitnesspal.shared.ui.activity.impl.resourceloader.mixin.MealFoodLoaderMixin;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.AccountRestrictedDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.CalorieAddErrorDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.ImageChooserDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.MealNamesDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.QuickAddCaloriesDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.WeightDialogFragment;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.fragment.NutritionFactsFragmentBase;
import com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment;
import com.myfitnesspal.shared.ui.fragment.impl.NewNutritionFactsFragment;
import com.myfitnesspal.shared.ui.mixin.BottomBarMixin;
import com.myfitnesspal.shared.ui.view.MfpWebView;
import org.jetbrains.annotations.NotNull;

interface UIInterface {
    void inject(Welcome welcome);

    void inject(AddFood addFood);

    void inject(AddFoodSummaryView addFoodSummaryView);

    void inject(AddFoodSummaryViewV2 addFoodSummaryViewV2);

    void inject(QuickAddActivity quickAddActivity);

    void inject(WaterEntryActivity waterEntryActivity);

    void inject(AddCustomFoodImprovementDialogFragment addCustomFoodImprovementDialogFragment);

    void inject(EditServingsDialogFragment editServingsDialogFragment);

    void inject(EditServingsDialogFragmentV2 editServingsDialogFragmentV2);

    void inject(EditV2SearchServingsDialogFragment editV2SearchServingsDialogFragment);

    void inject(SearchViewItemContextualDialog searchViewItemContextualDialog);

    void inject(AddFriendsContacts addFriendsContacts);

    void inject(AddFriendsFacebook addFriendsFacebook);

    void inject(AddFriendsParent addFriendsParent);

    void inject(AddFriendsSplash addFriendsSplash);

    void inject(AlexaInterstitialActivity alexaInterstitialActivity);

    void inject(AppDetailFragment appDetailFragment);

    void inject(AppGalleryActivity appGalleryActivity);

    void inject(AppsHomeFragment appsHomeFragment);

    void inject(GoogleFitPermissionsFragment googleFitPermissionsFragment);

    void inject(OurOtherAppsFragment ourOtherAppsFragment);

    void inject(XPromoInterstitialActivity xPromoInterstitialActivity);

    void inject(XPromoInterstitialFragment xPromoInterstitialFragment);

    void inject(BarcodeMatchActivity barcodeMatchActivity);

    void inject(BarcodeScannerActivity barcodeScannerActivity);

    void inject(BarcodeScanDialogFragment barcodeScanDialogFragment);

    void inject(BlogActivity blogActivity);

    void inject(BlogFragment blogFragment);

    void inject(ChallengeAchievementActivity challengeAchievementActivity);

    void inject(ChallengeDetailActivity challengeDetailActivity);

    void inject(ChallengeFriendListActivity challengeFriendListActivity);

    void inject(ChallengeShareActivity challengeShareActivity);

    void inject(ChallengesActivity challengesActivity);

    void inject(InviteContactsToChallengeActivity inviteContactsToChallengeActivity);

    void inject(InviteSourceListActivity inviteSourceListActivity);

    void inject(JoinChallengeActivity joinChallengeActivity);

    void inject(UserAchievementsActivity userAchievementsActivity);

    void inject(ChallengeDetailsFragment challengeDetailsFragment);

    void inject(ChallengeFriendsFragment challengeFriendsFragment);

    void inject(ChallengePrizesFragment challengePrizesFragment);

    void inject(ChallengeWebViewFragment challengeWebViewFragment);

    void inject(ChallengesFragment challengesFragment);

    void inject(JoinedChallengeSummaryFragment joinedChallengeSummaryFragment);

    void inject(JoinedFriendsFragment joinedFriendsFragment);

    void inject(JoinedPrizesFragment joinedPrizesFragment);

    void inject(CommunityActivity communityActivity);

    void inject(CommunityFragment communityFragment);

    void inject(AdConsentsActivity adConsentsActivity);

    void inject(ConsentsActivity consentsActivity);

    void inject(LearnMoreActivity learnMoreActivity);

    void inject(NutrientDashboardSettingsActivity nutrientDashboardSettingsActivity);

    void inject(CustomNutrientDashboardSelectionFragment customNutrientDashboardSelectionFragment);

    void inject(NutrientDashboardPresetSelectionFragment nutrientDashboardPresetSelectionFragment);

    void inject(AnalyticsEventsActivity analyticsEventsActivity);

    void inject(EndpointActivity endpointActivity);

    void inject(FlagResetActivity flagResetActivity);

    void inject(LocaleOverrideActivity localeOverrideActivity);

    void inject(PaymentsDebugActivity paymentsDebugActivity);

    void inject(PremiumDebugActivity premiumDebugActivity);

    void inject(ProgressPhotosDebugActivity progressPhotosDebugActivity);

    void inject(RetrieveMealFoodDebugActivity retrieveMealFoodDebugActivity);

    void inject(RolloutDebugActivity rolloutDebugActivity);

    void inject(AdvancedDebuggingFragment advancedDebuggingFragment);

    void inject(AnalyticsEventsFragment analyticsEventsFragment);

    void inject(DebugLogsFragment debugLogsFragment);

    void inject(DeleteAccountActivity deleteAccountActivity);

    void inject(CompleteDiaryActivity completeDiaryActivity);

    void inject(Diary diary);

    void inject(DiarySettingsActivity diarySettingsActivity);

    void inject(EditDiaryNoteView editDiaryNoteView);

    void inject(FriendDiary friendDiary);

    void inject(DiaryLongPressDialogFragment diaryLongPressDialogFragment);

    void inject(DiaryMoreActionsDialog diaryMoreActionsDialog);

    void inject(DiaryQuickToolsDialogFragment diaryQuickToolsDialogFragment);

    void inject(ExerciseTypeDialogFragment exerciseTypeDialogFragment);

    void inject(FriendDiaryPasswordDialogFragment friendDiaryPasswordDialogFragment);

    void inject(DiaryFragmentBase diaryFragmentBase);

    void inject(FriendDiaryFragment friendDiaryFragment);

    void inject(UserDiaryFragment userDiaryFragment);

    void inject(LeftDrawerLayout leftDrawerLayout);

    void inject(LeftDrawerMenu leftDrawerMenu);

    void inject(AddExerciseEntry addExerciseEntry);

    void inject(EditCardio editCardio);

    void inject(EditStrength editStrength);

    void inject(ExerciseSearchActivity exerciseSearchActivity);

    void inject(GenericExercise genericExercise);

    void inject(NewCardio newCardio);

    void inject(NewStrength newStrength);

    void inject(ExerciseSortOrderDialogFragment exerciseSortOrderDialogFragment);

    void inject(ExerciseSearchFragment exerciseSearchFragment);

    void inject(MyExercisesFragment myExercisesFragment);

    void inject(ExploreActivity exploreActivity);

    void inject(PrototypeExploreFragment prototypeExploreFragment);

    void inject(ExploreDataTask exploreDataTask);

    void inject(FileExport fileExport);

    void inject(FileExportPreview fileExportPreview);

    void inject(FoodEditorActivity foodEditorActivity);

    void inject(FoodNotesActivity foodNotesActivity);

    void inject(EditServingsDialogFragmentBase editServingsDialogFragmentBase);

    void inject(BarcodeMultiAddMixin barcodeMultiAddMixin);

    void inject(FoodEditorMixin foodEditorMixin);

    void inject(MenuItemEditorMixin menuItemEditorMixin);

    void inject(MultiAddFoodEditorMixin multiAddFoodEditorMixin);

    void inject(FoodFeedbackOptionsMixin foodFeedbackOptionsMixin);

    void inject(FoodFeedbackActivity foodFeedbackActivity);

    void inject(CommentsActivity commentsActivity);

    void inject(ComposeMessageActivity composeMessageActivity);

    void inject(DetailedMessageActivity detailedMessageActivity);

    void inject(FriendsActivity friendsActivity);

    void inject(InviteFriendActivity inviteFriendActivity);

    void inject(NewStatusOrCommentActivity newStatusOrCommentActivity);

    void inject(FriendRequestsFragment friendRequestsFragment);

    void inject(FriendsListFragment friendsListFragment);

    void inject(LikesListFragment likesListFragment);

    void inject(MessagesFragment messagesFragment);

    void inject(GDPRHelpActivity gDPRHelpActivity);

    void inject(CalorieAdjustmentExplanationView calorieAdjustmentExplanationView);

    void inject(CalorieAdjustmentIntro calorieAdjustmentIntro);

    void inject(CustomExerciseCaloriesActivity customExerciseCaloriesActivity);

    void inject(EditCustomMacroGoalsActivity editCustomMacroGoalsActivity);

    void inject(ExerciseCaloriesActivity exerciseCaloriesActivity);

    void inject(MacroGoalEditorActivity macroGoalEditorActivity);

    void inject(MealGoalsActivity mealGoalsActivity);

    void inject(UpdateGoals updateGoals);

    void inject(ActivityLevelDialogFragment activityLevelDialogFragment);

    void inject(ExerciseGoalsDialogFragment exerciseGoalsDialogFragment);

    void inject(MacroNutrientEditorDialog macroNutrientEditorDialog);

    void inject(NetEnergyGoalDialogFragment netEnergyGoalDialogFragment);

    void inject(WeightGoalDialogFragment weightGoalDialogFragment);

    void inject(AdditionalNutrientGoalsFragment additionalNutrientGoalsFragment);

    void inject(CustomGoalByDayFragment customGoalByDayFragment);

    void inject(EatingDisorderUpdateGoalCompleteFragment eatingDisorderUpdateGoalCompleteFragment);

    void inject(EditCustomMacroGoalsFragment editCustomMacroGoalsFragment);

    void inject(EditMacroGoalsByGramsFragment editMacroGoalsByGramsFragment);

    void inject(EditMacroGoalsByPercentFragment editMacroGoalsByPercentFragment);

    void inject(ExerciseCaloriesFragment exerciseCaloriesFragment);

    void inject(GoalsFragment goalsFragment);

    void inject(MealGoalsFragment mealGoalsFragment);

    void inject(UpdateGoalsCompleteFragment updateGoalsCompleteFragment);

    void inject(UpdateGoalsFragment updateGoalsFragment);

    void inject(AboutUs aboutUs);

    void inject(Faq faq);

    void inject(FaqFeedbackBaseActivity faqFeedbackBaseActivity);

    void inject(NewsFeedSettings newsFeedSettings);

    void inject(HelpListFragment helpListFragment);

    void inject(HomeActivity homeActivity);

    void inject(HomeMessagesActivity homeMessagesActivity);

    void inject(ImageReportingActivity imageReportingActivity);

    void inject(ImageReportingDetailsActivity imageReportingDetailsActivity);

    void inject(PrototypeHomeActivity prototypeHomeActivity);

    void inject(AppRatingDialogFragment appRatingDialogFragment);

    void inject(HomeFragment homeFragment);

    void inject(DeleteMealConfirmationDialogFragment deleteMealConfirmationDialogFragment);

    void inject(EditMealNameDialogFragment editMealNameDialogFragment);

    void inject(MealFoodPermissionSelectionDialogFragment mealFoodPermissionSelectionDialogFragment);

    void inject(MealEditorMixin mealEditorMixin);

    void inject(MealIngredientMixin mealIngredientMixin);

    void inject(SharedMealViewerMixin sharedMealViewerMixin);

    void inject(NotificationInboxActivity notificationInboxActivity);

    void inject(NotificationInboxFragment notificationInboxFragment);

    void inject(FoodListsActivity foodListsActivity);

    void inject(Nutrition nutrition);

    void inject(GraphViewFragment graphViewFragment);

    void inject(CaloriePieLegend caloriePieLegend);

    void inject(CustomBarChart customBarChart);

    void inject(CustomLineChart customLineChart);

    void inject(CustomPieChart customPieChart);

    void inject(MiniFoodList miniFoodList);

    void inject(OnboardingActivity onboardingActivity);

    void inject(AnimatedOnboardingFragment animatedOnboardingFragment);

    void inject(WelcomeScreenFragment welcomeScreenFragment);

    void inject(GooglePlayPaymentActivity googlePlayPaymentActivity);

    void inject(PaymentActivityBase paymentActivityBase);

    void inject(PaymentConfirmation paymentConfirmation);

    void inject(PermissionsFragment permissionsFragment);

    void inject(PremiumInterstitialActivity premiumInterstitialActivity);

    void inject(PremiumUpsellActivity premiumUpsellActivity);

    void inject(SubscriptionStatus subscriptionStatus);

    void inject(PremiumUpsellFragment premiumUpsellFragment);

    void inject(PremiumUpsellWebFragment premiumUpsellWebFragment);

    void inject(SubscriptionStatusFragment subscriptionStatusFragment);

    void inject(MeActivity meActivity);

    void inject(MyInfoFragment myInfoFragment);

    void inject(MyItemsFragment myItemsFragment);

    void inject(ProfileFragment profileFragment);

    void inject(OfflineDataTask offlineDataTask);

    void inject(OnlineDataTask onlineDataTask);

    void inject(MyItemsTask myItemsTask);

    void inject(AddWeightActivity addWeightActivity);

    void inject(ImportPhotoActivity importPhotoActivity);

    void inject(ProgressActivity progressActivity);

    void inject(ProgressCongratsActivity progressCongratsActivity);

    void inject(ProgressPhotosGalleryActivity progressPhotosGalleryActivity);

    void inject(ProgressPhotosInterstitialActivity progressPhotosInterstitialActivity);

    void inject(RecommendGoal recommendGoal);

    void inject(ShareProgressActivity shareProgressActivity);

    void inject(StatusUpdateActivity statusUpdateActivity);

    void inject(GraphPeriodDialogFragment graphPeriodDialogFragment);

    void inject(MeasurementTypeDialogFragment measurementTypeDialogFragment);

    void inject(MeasurementValueDialogFragment measurementValueDialogFragment);

    void inject(ProgressEntryLongPressDialogFragment progressEntryLongPressDialogFragment);

    void inject(ImportPhotoFragment importPhotoFragment);

    void inject(LegacyWeightPickerFragment legacyWeightPickerFragment);

    void inject(CreateRecipeManuallyActivity createRecipeManuallyActivity);

    void inject(IngredientMatchingActivity ingredientMatchingActivity);

    void inject(RecipeDetailsActivity recipeDetailsActivity);

    void inject(RecipeImportBrowserActivity recipeImportBrowserActivity);

    void inject(RecipeImportReviewActivity recipeImportReviewActivity);

    void inject(RecipesAndFoods recipesAndFoods);

    void inject(CreateRecipeDialogFragment createRecipeDialogFragment);

    void inject(CreateOrEditRecipeFragment createOrEditRecipeFragment);

    void inject(MyFoodsFragment myFoodsFragment);

    void inject(MyMealsFragment myMealsFragment);

    void inject(MyRecipesFragment myRecipesFragment);

    void inject(RecipeDetailsFragment recipeDetailsFragment);

    void inject(IngredientsContainer ingredientsContainer);

    void inject(AccountRestrictedActivity accountRestrictedActivity);

    void inject(FacebookLoginActivity facebookLoginActivity);

    void inject(FinishOnboardingActivity finishOnboardingActivity);

    void inject(ForgotPasswordActivity forgotPasswordActivity);

    void inject(LoginActivity loginActivity);

    void inject(LogoutActivity logoutActivity);

    void inject(OAuthActivity oAuthActivity);

    void inject(PrefetchActivity prefetchActivity);

    void inject(SignUpActivity signUpActivity);

    void inject(TermsOfUseActivity termsOfUseActivity);

    void inject(UpdatedTermsActivity updatedTermsActivity);

    void inject(SignUpHeightDialogFragment signUpHeightDialogFragment);

    void inject(SignUpWeightDialogFragment signUpWeightDialogFragment);

    void inject(LoginPleaseWaitFragment loginPleaseWaitFragment);

    void inject(LoginSignInUpButtonsFragment loginSignInUpButtonsFragment);

    void inject(LoginUserPassFragment loginUserPassFragment);

    void inject(SignUpActivityLevelFragment signUpActivityLevelFragment);

    void inject(SignUpCongratsFragment signUpCongratsFragment);

    void inject(SignUpFragmentBase signUpFragmentBase);

    void inject(SignUpGenderAgeFragment signUpGenderAgeFragment);

    void inject(SignUpGoalTypeFragment signUpGoalTypeFragment);

    void inject(SignUpMarketingOptInFragment signUpMarketingOptInFragment);

    void inject(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment);

    void inject(SignUpWeeklyWeightGoalFragment signUpWeeklyWeightGoalFragment);

    void inject(SignUpWeightHeightFragment signUpWeightHeightFragment);

    void inject(MenusActivity menusActivity);

    void inject(RestaurantLoggingInterstitial restaurantLoggingInterstitial);

    void inject(SearchMatchActivity searchMatchActivity);

    void inject(VenuesActivity venuesActivity);

    void inject(NotifyRequestMenuDialogFragment notifyRequestMenuDialogFragment);

    void inject(MenusListView menusListView);

    void inject(VenuesListView venuesListView);

    void inject(FoodSearchActivity foodSearchActivity);

    void inject(FoodSearchActivityV2 foodSearchActivityV2);

    void inject(MealFilterDialog mealFilterDialog);

    void inject(SearchCategoryDialog searchCategoryDialog);

    void inject(SortOrderDialog sortOrderDialog);

    void inject(LocalFoodSearchFragment localFoodSearchFragment);

    void inject(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2);

    void inject(OnlineFoodSearchFragment onlineFoodSearchFragment);

    void inject(@NotNull OnlineFoodSearchViewModel onlineFoodSearchViewModel);

    void inject(AutoPlaySettings autoPlaySettings);

    void inject(ChangePasswordActivity changePasswordActivity);

    void inject(CustomMealNames customMealNames);

    void inject(EditCardioExercise editCardioExercise);

    void inject(EditFood editFood);

    void inject(EditProfile editProfile);

    void inject(EditReminder editReminder);

    void inject(FacebookSettings facebookSettings);

    void inject(PasscodeView passcodeView);

    void inject(PrivacyCenterActivity privacyCenterActivity);

    void inject(RemindersActivity remindersActivity);

    void inject(SelectReminder selectReminder);

    void inject(SettingsActivity settingsActivity);

    void inject(TroubleshootingActivity troubleshootingActivity);

    void inject(ViewFoodActivity viewFoodActivity);

    void inject(WeeklyNutritionSettings weeklyNutritionSettings);

    void inject(CountryDialogFragment countryDialogFragment);

    void inject(GenderDialogFragment genderDialogFragment);

    void inject(HeightDialogFragment heightDialogFragment);

    void inject(PinCodeDialogFragment pinCodeDialogFragment);

    void inject(UnitsDialogFragment unitsDialogFragment);

    void inject(DiarySettingsFragment diarySettingsFragment);

    void inject(EmailSettingsListFragment emailSettingsListFragment);

    void inject(RemindersFragment remindersFragment);

    void inject(SettingsListFragment settingsListFragment);

    void inject(SharingAndPrivacySettingsListFragment sharingAndPrivacySettingsListFragment);

    void inject(StepsSettingsListFragment stepsSettingsListFragment);

    void inject(WeeklyNutritionSettingsListFragment weeklyNutritionSettingsListFragment);

    void inject(VideoActivity videoActivity);

    void inject(WalkthroughLoggingActivity walkthroughLoggingActivity);

    void inject(WalkthroughFoodSearchFragment walkthroughFoodSearchFragment);

    void inject(WalkthroughServingSizeV2Fragment walkthroughServingSizeV2Fragment);

    void inject(FloatingButtonMixin floatingButtonMixin);

    void inject(MfpActivity mfpActivity);

    void inject(DisconnectFacebook disconnectFacebook);

    void inject(FullScreenWebView fullScreenWebView);

    void inject(DeepLinkRouterActivity deepLinkRouterActivity);

    void inject(AddExerciseDeepLinkMixin addExerciseDeepLinkMixin);

    void inject(AddFoodDeepLinkMixin addFoodDeepLinkMixin);

    void inject(AppIndexerMixinBase appIndexerMixinBase);

    void inject(ChallengesMixin challengesMixin);

    void inject(DeepLinkMixinBase deepLinkMixinBase);

    void inject(SendVerificationEmailMixin sendVerificationEmailMixin);

    void inject(VenueDeepLinkMixin venueDeepLinkMixin);

    void inject(GetRecipeV2Mixin getRecipeV2Mixin);

    void inject(MealFoodLoaderMixin mealFoodLoaderMixin);

    void inject(CustomLayoutBaseDialogFragment customLayoutBaseDialogFragment);

    void inject(AccountRestrictedDialogFragment accountRestrictedDialogFragment);

    void inject(CalorieAddErrorDialogFragment calorieAddErrorDialogFragment);

    void inject(ImageChooserDialogFragment imageChooserDialogFragment);

    void inject(MealNamesDialogFragment mealNamesDialogFragment);

    void inject(QuickAddCaloriesDialogFragment quickAddCaloriesDialogFragment);

    void inject(WeightDialogFragment weightDialogFragment);

    void inject(MfpFragment mfpFragment);

    void inject(NutritionFactsFragmentBase nutritionFactsFragmentBase);

    void inject(ExternalWebViewFragment externalWebViewFragment);

    void inject(NewNutritionFactsFragment newNutritionFactsFragment);

    void inject(BottomBarMixin bottomBarMixin);

    void inject(MfpWebView mfpWebView);
}
