package com.myfitnesspal.feature.meals.ui.mixin;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase.OnItemSavedListener;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.model.SuggestedMealAnalyticsDetails;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.feature.meals.service.MealService.CreateMode;
import com.myfitnesspal.feature.meals.service.MealService.ImageMode;
import com.myfitnesspal.feature.meals.task.CrudMealFoodTask;
import com.myfitnesspal.feature.meals.task.CrudMealFoodTask.CompletedEvent;
import com.myfitnesspal.feature.meals.task.MealFoodNameConflictCheckTask;
import com.myfitnesspal.feature.meals.ui.dialog.EditMealNameDialogFragment;
import com.myfitnesspal.feature.meals.ui.dialog.EditMealNameDialogFragment.OnNewMealNameSetListener;
import com.myfitnesspal.feature.profile.ui.activity.ProfileView;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.model.v1.FoodPermission.Permission;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.task.DownloadImageTask;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.ui.view.BlueClickableSpanNoUnderline;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class SharedMealViewerMixin extends EditorMixinBase<MealFood> {
    private static final String EDIT_MEAL_NAME_DIALOG_TAG = "edit_meal_name_dialog_tag";
    public static final String EXTRA_EVENT_TYPE = "report_event_type";
    public static final String EXTRA_FOOD_ID = "food_id";
    public static final String EXTRA_IS_CURRENT_USERS_MEAL = "is_current_users_meal";
    public static final String EXTRA_MEAL_FOOD = "meal_food";
    public static final String EXTRA_MEAL_IMAGE_ID = "meal_image_id";
    public static final String EXTRA_MEAL_NOTES = "meal_notes";
    public static final String EXTRA_MEAL_OWNER_UID = "meal_owner_uid";
    public static final String EXTRA_MEAL_OWNER_USERNAME = "meal_owner_username";
    public static final String EXTRA_SHOW_TOAST_ON_ADD = "show_toast_on_add";
    public static final String EXTRA_SUGGESTED_MEAL_ANALYTICS_DETAILS = "suggested_meal_analytics_details";
    private static final String MYFITNESSPAL_UID = "98017515277757";
    private static final String PROGRESS_DIALOG_TAG = "progress_dialog_tag";
    /* access modifiers changed from: private */
    public final MfpActivity activity;
    private final BusEvents busEvents;
    @BindView(2131362634)
    View foodDescRowView;
    @BindView(2131364007)
    TextView foodDescriptionTextView;
    private final String foodId;
    @Inject
    Lazy<FoodPermissionsService> foodPermissionsService;
    @Inject
    Lazy<FoodService> foodService;
    @Inject
    Lazy<ImageService> imageService;
    /* access modifiers changed from: private */
    public final String imageUrl;
    /* access modifiers changed from: private */
    public final Intent intent;
    private final SuggestedMealAnalyticsDetails mealAnalyticsDetails;
    @Inject
    Lazy<MealAnalyticsHelper> mealAnalyticsHelper;
    private MealEditorMixin mealEditorMixin;
    /* access modifiers changed from: private */
    public MealFood mealFood;
    private String mealNotes;
    @Inject
    Lazy<MealService> mealService;
    @BindView(2131363151)
    View noOfServingsRowView;
    /* access modifiers changed from: private */
    public final OnItemSavedListener onItemSavedListener;
    /* access modifiers changed from: private */
    public OnNewMealNameSetListener onNewMealNameSetListener = new OnNewMealNameSetListener() {
        public void onNewMealNameSet(String str) {
            SharedMealViewerMixin.this.showProgressDialogFragment();
            SharedMealViewerMixin.this.runMealFoodNameConflictCheckTask(str);
        }

        public void onCancelled() {
            SharedMealViewerMixin.this.dismissProgressDialogFragment();
        }
    };
    private final View parentView;
    @BindView(2131362650)
    Button saveButton;
    private final Bundle savedState;
    @BindView(2131363605)
    View servingSizeRowView;
    @Inject
    Lazy<ApiUrlProvider> urlProvider;
    /* access modifiers changed from: private */
    public String validMealName;

    private class BusEvents {
        private BusEvents() {
        }

        @Subscribe
        public void onCreateMealFoodTaskCompletedEvent(CompletedEvent completedEvent) {
            SharedMealViewerMixin.this.dismissProgressDialogFragment();
            if (completedEvent.getFailure() == null) {
                if (SharedMealViewerMixin.this.intent.getBooleanExtra(SharedMealViewerMixin.EXTRA_SHOW_TOAST_ON_ADD, true)) {
                    Toast.makeText(SharedMealViewerMixin.this.activity, SharedMealViewerMixin.this.activity.getString(R.string.meal_saved, new Object[]{((MealFood) completedEvent.getResult()).getDescription()}), 0).show();
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable("meal_food", SharedMealViewerMixin.this.mealFood);
                SharedMealViewerMixin.this.onItemSavedListener.onItemSaved(-1, bundle);
                ((MealAnalyticsHelper) SharedMealViewerMixin.this.mealAnalyticsHelper.get()).reportFriendsMealSaved();
                return;
            }
            ((MealAnalyticsHelper) SharedMealViewerMixin.this.mealAnalyticsHelper.get()).reportFriendsMealSaveFailed();
            SharedMealViewerMixin.this.displayFailureMessage();
        }

        @Subscribe
        public void onDownloadImageTaskCompletedEvent(DownloadImageTask.CompletedEvent completedEvent) {
            SharedMealViewerMixin.this.runCreateMealFoodTask((String) completedEvent.getResult());
        }

        @Subscribe
        public void onMealFoodNameConflictCheckTaskCompletedEvent(final MealFoodNameConflictCheckTask.CompletedEvent completedEvent) {
            if (completedEvent.getFailure() != null || completedEvent.getResult() == null) {
                SharedMealViewerMixin.this.validMealName = completedEvent.getMealName();
                if (Strings.notEmpty(SharedMealViewerMixin.this.imageUrl)) {
                    new DownloadImageTask(SharedMealViewerMixin.this.urlProvider, SharedMealViewerMixin.this.imageUrl).run(SharedMealViewerMixin.this.activity.getRunner());
                } else {
                    SharedMealViewerMixin.this.runCreateMealFoodTask(null);
                }
            } else {
                SharedMealViewerMixin.this.dismissProgressDialogFragment();
                SharedMealViewerMixin.this.getHandler().post(new Runnable() {
                    public void run() {
                        EditMealNameDialogFragment newInstance = EditMealNameDialogFragment.newInstance(((MealFood) completedEvent.getResult()).getDescription());
                        newInstance.setOnNewMealNameSetListener(SharedMealViewerMixin.this.onNewMealNameSetListener);
                        SharedMealViewerMixin.this.activity.showDialogFragment(newInstance, SharedMealViewerMixin.EDIT_MEAL_NAME_DIALOG_TAG);
                    }
                });
            }
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    public SharedMealViewerMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener2, Intent intent2, Bundle bundle, View view) {
        super(mfpActivity);
        ButterKnife.bind((Object) this, view);
        component().inject(this);
        this.activity = mfpActivity;
        this.onItemSavedListener = onItemSavedListener2;
        this.intent = intent2;
        this.savedState = bundle;
        this.parentView = view;
        this.busEvents = new BusEvents();
        this.foodId = ExtrasUtils.getString(intent2, "food_id");
        this.imageUrl = ((ImageService) this.imageService.get()).getStableImageUrlById(ExtrasUtils.getString(intent2, EXTRA_MEAL_IMAGE_ID));
        this.mealNotes = ExtrasUtils.getString(intent2, EXTRA_MEAL_NOTES);
        this.mealAnalyticsDetails = (SuggestedMealAnalyticsDetails) ExtrasUtils.getParcelable(intent2, EXTRA_SUGGESTED_MEAL_ANALYTICS_DETAILS, SuggestedMealAnalyticsDetails.class.getClassLoader());
        this.mealFood = (MealFood) BundleUtils.getParcelable("meal_food", null, MealFood.class.getClassLoader(), bundle, intent2.getExtras());
        init();
    }

    private void init() {
        this.activity.setTitle(R.string.meal_details);
        initSaveButton();
        if (this.mealFood == null) {
            ((MealAnalyticsHelper) this.mealAnalyticsHelper.get()).reportFriendsMealViewFailed();
            new MfpAlertDialogBuilder(this.activity).setMessage((int) R.string.unable_display_shared_meal).setPositiveButton((int) R.string.ok, (OnClickListener) new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedMealViewerMixin.this.onItemSavedListener.onItemSaveFailed(-1, null);
                }
            }).show();
        }
    }

    private void initSaveButton() {
        ViewUtils.setVisible(true, this.saveButton);
        this.saveButton.setText(R.string.meal_collections_save_for_later_button);
        this.saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedMealViewerMixin.this.saveItemToTarget();
            }
        });
    }

    public void onResume() {
        super.onResume();
        this.activity.getMessageBus().register(this.busEvents);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("meal_food", this.mealFood);
    }

    public void onPause() {
        super.onPause();
        this.activity.getMessageBus().unregister(this.busEvents);
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!Strings.equals(EDIT_MEAL_NAME_DIALOG_TAG, str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((EditMealNameDialogFragment) dialogFragment).setOnNewMealNameSetListener(this.onNewMealNameSetListener);
        return true;
    }

    public boolean processActivityResult(int i, int i2, Intent intent2) {
        if (this.mealEditorMixin.processActivityResult(i, i2, intent2)) {
            return true;
        }
        return super.processActivityResult(i, i2, intent2);
    }

    public void renderView() {
        boolean z = this.mealFood != null;
        toggleDefaultVisibleViewsVisibility(z);
        if (z) {
            MealEditorMixin mealEditorMixin2 = new MealEditorMixin(this.activity, this.onItemSavedListener, this.intent, this.savedState, this.parentView, this.mealFood);
            this.mealEditorMixin = mealEditorMixin2;
            this.mealEditorMixin.renderView();
            this.mealEditorMixin.setImageUri(this.imageUrl);
            this.mealEditorMixin.setNotes(this.mealNotes);
            setUsernameText();
        }
    }

    private void setUsernameText() {
        ViewUtils.setVisible(this.foodDescriptionTextView);
        final String string = ExtrasUtils.getString(this.intent, EXTRA_MEAL_OWNER_USERNAME);
        String string2 = this.activity.getString(R.string.created_by_username, new Object[]{ExtrasUtils.getString(this.intent, EXTRA_MEAL_OWNER_USERNAME)});
        if (isMealMadeByMyFitnessPal()) {
            this.foodDescriptionTextView.setText(string2);
        } else {
            int indexOf = string2.indexOf(string);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string2);
            spannableStringBuilder.setSpan(new BlueClickableSpanNoUnderline(this.activity) {
                public void onClick(View view) {
                    ((MealAnalyticsHelper) SharedMealViewerMixin.this.mealAnalyticsHelper.get()).reportMealProfileTapped();
                    SharedMealViewerMixin.this.activity.getNavigationHelper().withIntent(ProfileView.newStartIntent(SharedMealViewerMixin.this.activity, string, ExtrasUtils.getString(SharedMealViewerMixin.this.intent, SharedMealViewerMixin.EXTRA_MEAL_OWNER_UID))).startActivity();
                }
            }, indexOf, string.length() + indexOf, 33);
            this.foodDescriptionTextView.setText(spannableStringBuilder);
        }
        this.foodDescriptionTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void saveItemToTarget(MealFood mealFood2) {
        runMealFoodNameConflictCheckTask(this.mealFood.getDescription());
    }

    public void saveItemToTarget() {
        saveItemToTarget(this.mealFood);
    }

    public boolean backPressed() {
        return super.backPressed();
    }

    /* access modifiers changed from: private */
    public void runMealFoodNameConflictCheckTask(String str) {
        showProgressDialogFragment();
        new MealFoodNameConflictCheckTask(this.mealService, str).run(this.activity.getRunner());
    }

    /* access modifiers changed from: private */
    public void runCreateMealFoodTask(String str) {
        CrudMealFoodTask.newInstanceForCopyingSharedMeal(this.mealService, this.foodService, this.foodPermissionsService, CreateMode.Create, this.validMealName, this.mealEditorMixin.getFoodEntries(), null, Permission.Private, Strings.notEmpty(str) ? ImageMode.Associate : null, str, this.mealFood.getMasterDatabaseId(), this.mealFood.getUid(), this.mealEditorMixin.getNotes()).run(this.activity.getRunner());
    }

    private void toggleDefaultVisibleViewsVisibility(boolean z) {
        ViewUtils.setVisible(z, this.foodDescRowView, this.noOfServingsRowView, this.servingSizeRowView);
    }

    /* access modifiers changed from: private */
    public void showProgressDialogFragment() {
        DialogFragment currentProgressDialogFragment = getCurrentProgressDialogFragment();
        if (currentProgressDialogFragment == null) {
            currentProgressDialogFragment = ProgressDialogFragment.newInstance(R.string.please_wait);
        }
        if (!currentProgressDialogFragment.isAdded()) {
            currentProgressDialogFragment.setCancelable(false);
            currentProgressDialogFragment.show(this.activity.getSupportFragmentManager(), PROGRESS_DIALOG_TAG);
        }
    }

    /* access modifiers changed from: private */
    public void dismissProgressDialogFragment() {
        DialogFragment currentProgressDialogFragment = getCurrentProgressDialogFragment();
        if (currentProgressDialogFragment != null) {
            currentProgressDialogFragment.dismiss();
        }
    }

    private DialogFragment getCurrentProgressDialogFragment() {
        return (DialogFragment) this.activity.getSupportFragmentManager().findFragmentByTag(PROGRESS_DIALOG_TAG);
    }

    private boolean isMealMadeByMyFitnessPal() {
        return MYFITNESSPAL_UID.equals(ExtrasUtils.getString(this.intent, EXTRA_MEAL_OWNER_UID));
    }

    private boolean isCurrentUsersMeal() {
        return ExtrasUtils.getBoolean(this.intent, EXTRA_IS_CURRENT_USERS_MEAL);
    }

    /* access modifiers changed from: private */
    public void displayFailureMessage() {
        new MfpAlertDialogBuilder(this.activity).setTitle((int) R.string.error).setMessage((int) R.string.generic_error_msg).setPositiveButton((int) R.string.dismiss, (OnClickListener) null).show();
    }
}
