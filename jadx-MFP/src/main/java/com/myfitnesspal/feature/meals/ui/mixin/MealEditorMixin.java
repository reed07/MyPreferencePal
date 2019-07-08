package com.myfitnesspal.feature.meals.ui.mixin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnContextClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.stetho.websocket.CloseCodes;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.foodeditor.task.FetchFoodNotesTask;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodNotesActivity;
import com.myfitnesspal.feature.foodeditor.ui.dialog.EditNumServingsDialogFragment;
import com.myfitnesspal.feature.foodeditor.ui.dialog.EditNumServingsDialogFragment.OnServingSizeSelectedListener;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase.OnItemSavedListener;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.FoodEditorMixinBase;
import com.myfitnesspal.feature.foodeditor.ui.view.FoodEditorHint;
import com.myfitnesspal.feature.foodeditor.ui.view.FoodEditorHint.OnButtonPressedListener;
import com.myfitnesspal.feature.foodeditor.ui.view.FoodEditorHint.Type;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageServiceUtil;
import com.myfitnesspal.feature.images.ui.mixin.ImportImageMixin;
import com.myfitnesspal.feature.images.ui.mixin.ImportImageMixin.ImageChangedListener;
import com.myfitnesspal.feature.images.ui.mixin.ImportImageMixin.RemoveImageListener;
import com.myfitnesspal.feature.meals.model.MealFoodLoggedInfo;
import com.myfitnesspal.feature.meals.model.MealIngredientEditorBundleData;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.feature.meals.service.MealService.CreateMode;
import com.myfitnesspal.feature.meals.service.MealService.ImageMode;
import com.myfitnesspal.feature.meals.task.AddMealFoodToDiaryTask;
import com.myfitnesspal.feature.meals.task.CheckMealModifiedTask;
import com.myfitnesspal.feature.meals.task.CrudMealFoodTask;
import com.myfitnesspal.feature.meals.task.DeleteMealFoodTask;
import com.myfitnesspal.feature.meals.task.FetchFoodImagesTask;
import com.myfitnesspal.feature.meals.task.FetchFoodImagesTask.CompletedEvent;
import com.myfitnesspal.feature.meals.task.MealFoodNameConflictCheckTask;
import com.myfitnesspal.feature.meals.ui.dialog.DeleteMealConfirmationDialogFragment;
import com.myfitnesspal.feature.meals.ui.dialog.DeleteMealConfirmationDialogFragment.OnDeleteMealClickListener;
import com.myfitnesspal.feature.meals.ui.dialog.MealFoodPermissionSelectionDialogFragment;
import com.myfitnesspal.feature.meals.ui.dialog.MealFoodPermissionSelectionDialogFragment.OnMealFoodPermissionSelectedListener;
import com.myfitnesspal.feature.meals.util.MealShareViewHelper;
import com.myfitnesspal.feature.meals.util.MealSharingDirectionsAnalyticsHelper;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.progress.task.GenerateArtifactImageTask;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity.Extras;
import com.myfitnesspal.feature.search.ui.adapter.FoodSearchAdapter;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.FoodScreenType;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.shared.db.table.FoodNotesTable;
import com.myfitnesspal.shared.model.FoodV2Logging.Builder;
import com.myfitnesspal.shared.model.mapper.impl.MealIngredientMapper;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodPermission;
import com.myfitnesspal.shared.model.v1.FoodPermission.Permission;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.behavior.HeaderImageScrollBehavior;
import com.myfitnesspal.shared.ui.fragment.impl.NewNutritionFactsFragment;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.VersionUtils;
import com.uacf.core.util.ViewUtils;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;
import javax.inject.Inject;

public class MealEditorMixin extends FoodEditorMixinBase<MealFood> {
    private static final String ATTRIBUTE_VALUE_COPY = "copy";
    private static final String ATTRIBUTE_VALUE_EDIT = "edit";
    private static final String ATTRIBUTE_VALUE_MEAL_DETAILS = "meal_details";
    private static final String ATTRIBUTE_VALUE_NEW = "new";
    private static final String ATTRIBUTE_VALUE_SAVE = "save";
    private static final int COPY_ITEM = 1012;
    private static final float DEFAULT_NUM_SERVINGS = 1.0f;
    private static final int DELETE_ITEM = 1013;
    private static final String DELETE_MEAL_CONFIRMATION_DIALOG_TAG = "delete_meal_confirmation_dialog_tag";
    private static final int EDIT_MEAL_FOOD_ITEM = 1011;
    private static final int EDIT_MEAL_INGREDIENTS_ITEM = 1010;
    private static final int EDIT_MODE_DELAY_MILLIS = 300;
    public static final String EXTRA_CREATE = "create";
    private static final String EXTRA_EDITED_IMAGE_ORIGINAL_PATH = "edited_image_original_path";
    private static final String EXTRA_EDIT_MODE = "edit_mode_ordinal";
    private static final String EXTRA_FLOW_ID = "flow_id";
    public static final String EXTRA_FOOD_ENTRIES = "extra_food_entries";
    private static final String EXTRA_FOOD_PERMISSION = "food_permission";
    public static final String EXTRA_MEAL_FOOD_CREATION_FLOW = "meal_food_creation_flow";
    public static final String EXTRA_MEAL_FOOD_LOGGED_INFO = "extra_meal_food_logged_info";
    private static final String EXTRA_MEAL_FOOD_NAME = "extra_meal_food_name";
    private static final String EXTRA_MULTI_ADD_MODE_ON = "multi_add_mode_on";
    private static final String EXTRA_NUM_SERVINGS = "num_servings";
    private static final String EXTRA_ORIGINAL_FOOD = "original_food";
    public static final String EXTRA_REFERRER = "referrer";
    public static final String EXTRA_REPLACE = "replace";
    private static final String EXTRA_REPLACED_MEAL_FOOD = "replaced_meal_food";
    private static final String EXTRA_UPDATED_MEAL_NOTES = "extra_updated_meal_notes";
    private static final String EXTRA_WAS_MEAL_EDITED_OR_COPIED = "was_meal_edited_or_copied";
    private static final int HINT_SHOW_DELAY_MS = 500;
    private static final int MEAL_FOOD_NAME_MAX_LENGTH = 100;
    private static final String MEAL_FOOD_PERMISSION_SELECTION_DIALOG_TAG = "meal_food_permission_selection_dialog_tag";
    private static final float NUTRIENT_SCALE = 1.0f;
    private static final int REQUEST_CODE_EDIT_NOTES = 1012;
    private static final int REQUEST_CODE_MEAL_INGREDIENT_VIEW = 1011;
    public static final int REQUEST_CODE_SHARE_MEAL = 1013;
    private static final String SERVING_SIZE_DIALOG_TAG = "serving_size_dialog";
    /* access modifiers changed from: private */
    public ActionMode actionMode;
    @BindView(2131363023)
    View bottomPadding;
    private final BusEvents busEvents;
    @Inject
    Lazy<ConfigService> configService;
    /* access modifiers changed from: private */
    public EditMode editMode;
    /* access modifiers changed from: private */
    public String editedImageOriginalPath;
    private final String flowId;
    /* access modifiers changed from: private */
    public List<FoodEntry> foodEntries;
    @Inject
    Lazy<FoodNotesTable> foodNotesTable;
    @Inject
    Lazy<FoodPermissionsService> foodPermissionsService;
    private FoodSearchAdapter foodSearchAdapter;
    @Inject
    Lazy<FoodSearchActivityFactory> foodSearchRouter;
    @Inject
    Lazy<FoodService> foodService;
    /* access modifiers changed from: private */
    public HeaderViews header;
    @BindView(2131362640)
    FoodEditorHint hintPanel;
    private ImageChangedListener imageChangedListener;
    @Inject
    Lazy<ImageService> imageService;
    /* access modifiers changed from: private */
    public ImportImageMixin importImageMixin;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<MealAnalyticsHelper> mealAnalyticsHelper;
    @Inject
    Lazy<MealSharingDirectionsAnalyticsHelper> mealBrowseAnalytics;
    /* access modifiers changed from: private */
    public MealFood mealFood;
    private final MealFoodLoggedInfo mealFoodLoggedInfo;
    private final String mealFoodName;
    @Inject
    Lazy<MealUtil> mealHelperUtil;
    @Inject
    Lazy<MealIngredientMapper> mealIngredientMapper;
    @BindView(2131363035)
    TextView mealNotes;
    @BindView(2131363036)
    TextView mealNotesButton;
    @BindView(2131363038)
    View mealNotesEmpty;
    @Inject
    Lazy<MealService> mealService;
    @BindView(2131363042)
    ImageButton mealShareButton;
    /* access modifiers changed from: private */
    public MealShareViewHelper mealShareViewHelper;
    @Inject
    Lazy<MultiAddFoodHelper> multiAddFoodHelper;
    /* access modifiers changed from: private */
    public float numServings;
    private MfpNutritionalContents nutritionalContents;
    private OnDeleteMealClickListener onDeleteMealClickListener;
    private OnClickListener onIngredientClickListener;
    private OnMealFoodPermissionSelectedListener onMealFoodPermissionSelectedListener;
    private final DialogInterface.OnClickListener onMealReplaceClickListener;
    private OnServingSizeSelectedListener onServingSizeSelectedListener;
    /* access modifiers changed from: private */
    public MealFood originalFood;
    /* access modifiers changed from: private */
    public Permission permission;
    @BindView(2131363260)
    TextView permissionValueTextView;
    @BindView(2131363262)
    View permissionsTableRow;
    private final String referrer;
    private RemoveImageListener removeImageListener;
    /* access modifiers changed from: private */
    public MealFood replacedFood;
    /* access modifiers changed from: private */
    public boolean shouldReplaceMeal;
    /* access modifiers changed from: private */
    public boolean wasMealEditedOrCopied;

    private class BusEvents {
        private BusEvents() {
        }

        @Subscribe
        public void onFetchFoodImagesTaskCompleted(CompletedEvent completedEvent) {
            boolean z = false;
            if (completedEvent.successful()) {
                List list = (List) completedEvent.getResult();
                if (!list.isEmpty()) {
                    MfpImage mfpImage = (MfpImage) list.get(0);
                    String imageUri = ImageServiceUtil.getImageUri((ImageService) MealEditorMixin.this.imageService.get(), mfpImage);
                    if (Strings.notEmpty(imageUri)) {
                        MealEditorMixin.this.importImageMixin.setImagePathAndUid(imageUri, mfpImage.getId());
                        z = true;
                    }
                }
            }
            MealEditorMixin.this.reportInitEvents(z);
        }

        @Subscribe
        public void onCreateMealFoodTaskCompletedEvent(CrudMealFoodTask.CompletedEvent completedEvent) {
            if (completedEvent.getFailure() == null) {
                MealEditorMixin.this.reportMealFlowCompletedEvent();
                if (MealEditorMixin.this.editMode != EditMode.EditMealFood && MealEditorMixin.this.editMode != EditMode.CopyMealFood) {
                    MealEditorMixin mealEditorMixin = MealEditorMixin.this;
                    mealEditorMixin.onItemSaved(null, -1, mealEditorMixin.getIntentExtrasForMealCreationDialog(Strings.toString(mealEditorMixin.inputMealName.getText())));
                } else if (MealEditorMixin.this.isNavigatingFromMyMealsScreen()) {
                    MealEditorMixin.this.onItemSavedListener.onItemSavedIgnoreStartIntent(-1, null);
                } else {
                    MealEditorMixin.this.mealFood = (MealFood) completedEvent.getResult();
                    MealEditorMixin.this.updateFoodEntriesMealFood();
                    MealEditorMixin.this.editMode = EditMode.Display;
                    MealEditorMixin.this.renderView();
                    MealEditorMixin.this.wasMealEditedOrCopied = true;
                }
            } else {
                MealEditorMixin.this.displayFailureMessage();
            }
        }

        @Subscribe
        public void onAddMealFoodToDiaryTaskCompletedEvent(AddMealFoodToDiaryTask.CompletedEvent completedEvent) {
            if (completedEvent.getFailure() == null) {
                ((DiaryService) MealEditorMixin.this.diaryService.get()).getDiaryDayForActiveDateSync().setJustAddedFoodEntry(((MealUtil) MealEditorMixin.this.mealHelperUtil.get()).createFoodEntryForDiaryToast(MealEditorMixin.this.mealFood, MealEditorMixin.this.getMealName()));
                MealEditorMixin.this.onItemSaved(null, -1, null);
                return;
            }
            MealEditorMixin.this.displayFailureMessage();
        }

        @Subscribe
        public void onMealFoodNameConflictCheckTaskCompletedEvent(MealFoodNameConflictCheckTask.CompletedEvent completedEvent) {
            if (completedEvent.getFailure() != null || completedEvent.getResult() == null) {
                CrudMealFoodTask.newInstanceForUpdatingExistingMeal(MealEditorMixin.this.mealService, MealEditorMixin.this.foodService, MealEditorMixin.this.foodPermissionsService, MealEditorMixin.this.getMealServiceCreateMode(), Strings.toString(MealEditorMixin.this.inputMealName.getText()), MealEditorMixin.this.foodEntries, MealEditorMixin.this.originalFood, MealEditorMixin.this.permission, MealEditorMixin.this.getImportedImageMode(), MealEditorMixin.this.getImportedImagePath(), MealEditorMixin.this.getNotes()).run(MealEditorMixin.this.activity.getRunner());
                return;
            }
            MealEditorMixin.this.replacedFood = (MealFood) completedEvent.getResult();
            MealEditorMixin.this.shouldReplaceMeal = false;
            MealEditorMixin.this.displayMealFoodNameConflictMessage();
        }

        @Subscribe
        public void onDeleteMealFoodTaskCompletedEvent(DeleteMealFoodTask.CompletedEvent completedEvent) {
            if (completedEvent.getFailure() == null) {
                MealEditorMixin.this.onItemSavedListener.onItemSavedIgnoreStartIntent(-1, null);
            }
        }

        @Subscribe
        public void onGenerateArtifactImageTaskCompletedEvent(GenerateArtifactImageTask.CompletedEvent completedEvent) {
            if (MealEditorMixin.this.mealShareViewHelper != null) {
                MealEditorMixin.this.mealShareViewHelper.onGenerateArtifactImageTaskCompletedEvent(completedEvent);
            }
        }

        @Subscribe
        public void onFetchMealNotesCompletedEvent(FetchFoodNotesTask.CompletedEvent completedEvent) {
            String str = "";
            if (completedEvent.successful() && completedEvent.getResult() != null) {
                str = (String) completedEvent.getResult();
            }
            MealEditorMixin.this.setNotes(str);
            MealEditorMixin.this.checkDisplayMealNotesHint(str);
        }

        @Subscribe
        public void onCheckModifiedCompletedEvent(CheckMealModifiedTask.CompletedEvent completedEvent) {
            if (!completedEvent.successful() || !((Boolean) completedEvent.getResult()).booleanValue()) {
                MealEditorMixin.this.finishBackPressed();
                return;
            }
            ((LegacyAlertMixin) MealEditorMixin.this.activity.mixin(LegacyAlertMixin.class)).showAlertDialogWithTitleAndListeners(MealEditorMixin.this.getString(R.string.unsaved_changes, new Object[0]), MealEditorMixin.this.getString(R.string.meal_sharing_unsaved_changes_dialog_message, new Object[0]), MealEditorMixin.this.getString(R.string.meal_sharing_unsaved_changes_stay, new Object[0]), null, MealEditorMixin.this.getString(R.string.meal_sharing_unsaved_changes_leave, new Object[0]), new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    MealEditorMixin.this.getActivity().finish();
                }
            });
        }
    }

    private enum EditMode {
        Create(R.string.create_new_meal, true, true, true, true),
        Display(R.string.add_meal, false, false, false, true),
        DisplayReadOnly(R.string.meal_details, false, false, false, false),
        SaveAs(R.string.save_as_meal, false, true, true, true),
        EditMealFood(R.string.edit_meal, true, true, true, true),
        CopyMealFood(R.string.create_new_meal, true, true, true, true);
        
        private final boolean canAddRemoveMealIngredients;
        private final boolean canEditImage;
        /* access modifiers changed from: private */
        public final boolean canEditMealFood;
        private final boolean canEditNotes;
        private final int titleResId;

        private EditMode(int i, boolean z, boolean z2, boolean z3, boolean z4) {
            this.titleResId = i;
            this.canAddRemoveMealIngredients = z;
            this.canEditMealFood = z2;
            this.canEditImage = z3;
            this.canEditNotes = z4;
        }

        public int getTitleResId() {
            return this.titleResId;
        }

        public boolean canAddRemoveMealIngredients() {
            return this.canAddRemoveMealIngredients;
        }

        public boolean canEditMealFood() {
            return this.canEditMealFood;
        }

        public boolean canEditImage() {
            return this.canEditImage;
        }

        public boolean canEditNotes(MealFood mealFood, Session session) {
            boolean z = false;
            if (!this.canEditNotes) {
                return false;
            }
            if (mealFood == null || mealFood.getOwnerUserMasterId() == 0 || mealFood.getOwnerUserMasterId() == session.getUser().getMasterDatabaseId()) {
                z = true;
            }
            return z;
        }
    }

    static class HeaderViews {
        @BindView(2131362622)
        protected View addButton;
        @BindView(2131362623)
        protected View buttonContainer;
        @BindView(2131362624)
        protected View container;
        @BindView(2131362619)
        protected View editButton;
        @BindView(2131362621)
        protected ImageView image;
        @BindView(2131362625)
        protected View progress;

        HeaderViews() {
        }

        /* access modifiers changed from: 0000 */
        public void updateButtonState(EditMode editMode, String str) {
            boolean z = Strings.isEmpty(str) && editMode != EditMode.DisplayReadOnly;
            ViewUtils.setVisible(z, this.addButton);
            ViewUtils.setVisible(!z && editMode.canEditImage(), this.editButton);
        }
    }

    public class HeaderViews_ViewBinding implements Unbinder {
        private HeaderViews target;

        @UiThread
        public HeaderViews_ViewBinding(HeaderViews headerViews, View view) {
            this.target = headerViews;
            headerViews.buttonContainer = Utils.findRequiredView(view, R.id.foodEditorImageButtonContainer, "field 'buttonContainer'");
            headerViews.editButton = Utils.findRequiredView(view, R.id.foodEditorEditButton, "field 'editButton'");
            headerViews.container = Utils.findRequiredView(view, R.id.foodEditorImageContainer, "field 'container'");
            headerViews.progress = Utils.findRequiredView(view, R.id.foodEditorImageProgress, "field 'progress'");
            headerViews.image = (ImageView) Utils.findRequiredViewAsType(view, R.id.foodEditorImage, "field 'image'", ImageView.class);
            headerViews.addButton = Utils.findRequiredView(view, R.id.foodEditorImageButton, "field 'addButton'");
        }

        @CallSuper
        public void unbind() {
            HeaderViews headerViews = this.target;
            if (headerViews != null) {
                this.target = null;
                headerViews.buttonContainer = null;
                headerViews.editButton = null;
                headerViews.container = null;
                headerViews.progress = null;
                headerViews.image = null;
                headerViews.addButton = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    private class MultiAddActionMode implements Callback {
        private static final int ACTION_DELETE_ALL = 1111;

        private MultiAddActionMode() {
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MealEditorMixin.this.actionMode = actionMode;
            ((MultiAddFoodHelper) MealEditorMixin.this.multiAddFoodHelper.get()).enable();
            MealEditorMixin.this.updateActionModeTitle();
            MealEditorMixin.this.updateItemsCheckBoxVisibility();
            MealEditorMixin.this.showFabIfNeeded();
            return true;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            menu.clear();
            menu.add(0, ACTION_DELETE_ALL, 0, R.string.delete).setIcon(R.drawable.ic_delete_white_24dp).setShowAsAction(2);
            return true;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (menuItem.getItemId() != ACTION_DELETE_ALL) {
                return false;
            }
            MealEditorMixin.this.deleteSelectedItems();
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            ((MultiAddFoodHelper) MealEditorMixin.this.multiAddFoodHelper.get()).disable();
            MealEditorMixin.this.actionMode = null;
            MealEditorMixin.this.updateItemsCheckBoxVisibility();
            MealEditorMixin.this.showFabIfNeeded();
        }
    }

    public float getNutrientScale() {
        return 1.0f;
    }

    public MealEditorMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener, Intent intent, Bundle bundle, View view) {
        MfpActivity mfpActivity2 = mfpActivity;
        OnItemSavedListener onItemSavedListener2 = onItemSavedListener;
        Intent intent2 = intent;
        Bundle bundle2 = bundle;
        View view2 = view;
        this(mfpActivity2, onItemSavedListener2, intent2, bundle2, view2, (MealFood) BundleUtils.getParcelable("extra_food", null, MealFood.class.getClassLoader(), bundle, intent.getExtras()));
    }

    public MealEditorMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener, Intent intent, Bundle bundle, View view, MealFood mealFood2) {
        super(mfpActivity, onItemSavedListener, intent, bundle, view);
        this.busEvents = new BusEvents();
        this.header = new HeaderViews();
        this.onServingSizeSelectedListener = new OnServingSizeSelectedListener() {
            public void onServingSizeSelected(float f) {
                MealEditorMixin.this.numServings = f;
                MealEditorMixin mealEditorMixin = MealEditorMixin.this;
                mealEditorMixin.foodEntries = ((MealUtil) mealEditorMixin.mealHelperUtil.get()).getFoodEntriesFromIngredients(MealEditorMixin.this.mealFood, f);
                MealEditorMixin.this.renderItemsListFromCurrentFoodEntries();
            }
        };
        this.imageChangedListener = new ImageChangedListener() {
            public void onChanged(String str) {
                MealEditorMixin.this.header.updateButtonState(MealEditorMixin.this.editMode, str);
            }

            public void onImportStarted() {
                ((MealAnalyticsHelper) MealEditorMixin.this.mealAnalyticsHelper.get()).reportImageImportStarted();
            }

            public void onImportFinished(String str) {
                ((MealAnalyticsHelper) MealEditorMixin.this.mealAnalyticsHelper.get()).reportImageImportCompleted();
            }
        };
        this.removeImageListener = new RemoveImageListener() {
            public boolean canRemovePhoto() {
                return Strings.notEmpty(MealEditorMixin.this.importImageMixin.getVisibleImagePath());
            }

            public void onRemoveMenuItemSelected() {
                String access$1100 = MealEditorMixin.this.getVisibleImagePath();
                if (Strings.notEmpty(access$1100)) {
                    MealEditorMixin.this.wasMealEditedOrCopied = true;
                    MealEditorMixin.this.editedImageOriginalPath = access$1100;
                    MealEditorMixin.this.importImageMixin.setImagePathAndUid(null, null);
                    ((MealAnalyticsHelper) MealEditorMixin.this.mealAnalyticsHelper.get()).reportImageRemoved();
                }
            }
        };
        this.onMealReplaceClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MealEditorMixin.this.shouldReplaceMeal = true;
                CrudMealFoodTask.newInstanceWithFoodToDelete(MealEditorMixin.this.mealService, MealEditorMixin.this.foodService, MealEditorMixin.this.foodPermissionsService, MealEditorMixin.this.editMode == EditMode.EditMealFood ? CreateMode.Update : CreateMode.Copy, Strings.toString(MealEditorMixin.this.inputMealName.getText()), MealEditorMixin.this.foodEntries, MealEditorMixin.this.originalFood, MealEditorMixin.this.permission, MealEditorMixin.this.getImportedImageMode(), MealEditorMixin.this.getImportedImagePath(), MealEditorMixin.this.replacedFood, MealEditorMixin.this.getNotes()).run(MealEditorMixin.this.activity.getRunner());
            }
        };
        this.onIngredientClickListener = new OnClickListener() {
            public void onClick(View view) {
                FoodEntry foodEntry = (FoodEntry) view.getTag();
                int indexOf = MealEditorMixin.this.foodEntries.indexOf(foodEntry);
                if (indexOf != -1) {
                    MfpActivity access$2300 = MealEditorMixin.this.activity;
                    MfpActivity access$2200 = MealEditorMixin.this.activity;
                    MealIngredientEditorBundleData mealIngredientEditorBundleData = new MealIngredientEditorBundleData(((MealUtil) MealEditorMixin.this.mealHelperUtil.get()).getMfpFoodFromFoodEntry(foodEntry), foodEntry.getDate(), foodEntry.getMealName(), null, null, indexOf, true);
                    access$2300.startActivityForResult(FoodEditorActivity.newMealIngredientEditorIntent(access$2200, null, mealIngredientEditorBundleData), CloseCodes.UNEXPECTED_CONDITION);
                }
            }
        };
        this.onDeleteMealClickListener = new OnDeleteMealClickListener() {
            public void onDeleteMealClick(boolean z) {
                MealEditorMixin.this.startDeleteMealTask();
                ((LocalSettingsService) MealEditorMixin.this.localSettingsService.get()).setShowDeleteMealConfirmationDialog(!z);
            }
        };
        this.onMealFoodPermissionSelectedListener = new OnMealFoodPermissionSelectedListener() {
            public void onMealFoodPermissionSelected(Permission permission) {
                MealEditorMixin.this.permission = permission;
                MealEditorMixin.this.permissionValueTextView.setText(permission.getNameResId());
            }
        };
        component().inject(this);
        Bundle extras = intent.getExtras();
        this.mealFood = mealFood2;
        this.foodEntries = BundleUtils.getParcelableArrayList(EXTRA_FOOD_ENTRIES, FoodEntry.class.getClassLoader(), bundle, extras);
        this.mealFoodName = BundleUtils.getString(EXTRA_MEAL_FOOD_NAME, "", bundle, extras);
        this.referrer = BundleUtils.getString("referrer", "", bundle, extras);
        this.mealFoodLoggedInfo = (MealFoodLoggedInfo) BundleUtils.getParcelable(EXTRA_MEAL_FOOD_LOGGED_INFO, null, MealFoodLoggedInfo.class.getClassLoader(), bundle, extras);
        this.flowId = BundleUtils.getString(bundle, "flow_id", UUID.randomUUID().toString());
        this.editMode = (EditMode) BundleUtils.getSerializable(bundle, EXTRA_EDIT_MODE, getEditModeBasedOnFields(), EditMode.class.getClassLoader());
        this.originalFood = (MealFood) BundleUtils.getParcelable(bundle, EXTRA_ORIGINAL_FOOD, MealFood.class.getClassLoader());
        this.wasMealEditedOrCopied = BundleUtils.getBoolean(bundle, EXTRA_WAS_MEAL_EDITED_OR_COPIED);
        this.replacedFood = (MealFood) BundleUtils.getParcelable(bundle, EXTRA_REPLACED_MEAL_FOOD, MealFood.class.getClassLoader());
        this.editedImageOriginalPath = BundleUtils.getString(bundle, EXTRA_EDITED_IMAGE_ORIGINAL_PATH);
        this.numServings = BundleUtils.getFloat(bundle, EXTRA_NUM_SERVINGS, 1.0f);
        this.permission = (Permission) BundleUtils.getSerializable(bundle, EXTRA_FOOD_PERMISSION, getDefaultPermission(), Permission.class.getClassLoader());
        init(bundle);
    }

    private void init(Bundle bundle) {
        this.importImageMixin = (ImportImageMixin) this.activity.mixin(ImportImageMixin.class);
        boolean z = false;
        View inflate = LayoutInflater.from(this.activity).inflate(R.layout.food_editor_image, this.headerContainer, false);
        this.headerContainer.addView(inflate);
        ButterKnife.bind((Object) this.header, inflate);
        initCustomScrollingBehavior();
        boolean z2 = true;
        ViewUtils.setVisible(this.header.container);
        this.importImageMixin.initializeViews(this.header.image, this.header.progress);
        this.importImageMixin.setRemoveImageListener(this.removeImageListener);
        this.importImageMixin.setImageChangedListener(this.imageChangedListener);
        if (CollectionUtils.isEmpty((Collection<?>) this.foodEntries)) {
            this.foodEntries = new ArrayList();
        }
        if (Strings.notEmpty(this.mealFoodName)) {
            this.inputMealName.setText(this.mealFoodName);
        }
        this.mealNotesContainer.setVisibility(0);
        this.mealNotesContainer.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                MealEditorMixin.lambda$init$0(MealEditorMixin.this, view);
            }
        });
        String string = BundleUtils.getString(bundle, EXTRA_UPDATED_MEAL_NOTES);
        if (Strings.notEmpty(string)) {
            setNotes(string);
        } else {
            reloadNotes();
        }
        if ((this.editMode == EditMode.Display || this.editMode == EditMode.DisplayReadOnly) && CollectionUtils.isEmpty((Collection<?>) this.foodEntries)) {
            this.foodEntries = ((MealUtil) this.mealHelperUtil.get()).getFoodEntriesFromIngredients(this.mealFood, this.numServings);
        }
        if (this.mealFood != null) {
            if (Strings.isEmpty(this.importImageMixin.getVisibleImagePath())) {
                new FetchFoodImagesTask((ImageService) this.imageService.get(), this.mealFood).run(this.activity.getRunner());
                z2 = false;
            } else {
                z = true;
            }
        }
        if (z2) {
            reportInitEvents(z);
        }
        this.nutritionalContents = ((MealUtil) this.mealHelperUtil.get()).getNutritionalContents(this.foodEntries);
        initializeItemsListFromCurrentFoodEntries();
        initListeners();
        showMultiAddModeIfNeeded();
        this.timestampPickerMixin.setScreenType(FoodScreenType.MEAL);
    }

    public static /* synthetic */ void lambda$init$0(MealEditorMixin mealEditorMixin, View view) {
        if (mealEditorMixin.editMode.canEditNotes(mealEditorMixin.mealFood, mealEditorMixin.getMfpActivity().getSession())) {
            mealEditorMixin.showFoodNotesActivity();
        }
    }

    /* access modifiers changed from: protected */
    public NewNutritionFactsFragment getNutritionFactsFragment(MfpNutritionalContents mfpNutritionalContents, int i, float f) {
        return NewNutritionFactsFragment.newInstanceForMealFood(mfpNutritionalContents, 0, i, (double) f);
    }

    /* access modifiers changed from: private */
    public void reportInitEvents(boolean z) {
        reportScreenViewed(z);
        reportMealFlowStartedEvent(z);
    }

    private void reportScreenViewed(boolean z) {
        if (isCreateOrSaveAsMode()) {
            ((MealAnalyticsHelper) this.mealAnalyticsHelper.get()).reportScreenViewedForCreateOrSaveAsMode(this.editMode == EditMode.Create, this.permission);
        } else {
            ((MealAnalyticsHelper) this.mealAnalyticsHelper.get()).reportScreenViewedForOtherModes(CollectionUtils.size((Collection<?>) this.foodEntries), z, this.permission);
        }
    }

    private EditMode getEditModeBasedOnFields() {
        MealFood mealFood2 = this.mealFood;
        if (mealFood2 == null || !Strings.notEmpty(mealFood2.brandAndDescription())) {
            if (!CollectionUtils.notEmpty((Collection<?>) this.foodEntries) || this.mealFood != null) {
                return EditMode.Create;
            }
            return EditMode.SaveAs;
        } else if (this.mealFood.getOwnerUserMasterId() != ((Session) this.session.get()).getUser().getMasterDatabaseId() || this.mealFood.isAutoGeneratedMeal()) {
            return EditMode.DisplayReadOnly;
        } else {
            return EditMode.Display;
        }
    }

    public void onResume() {
        super.onResume();
        this.activity.getMessageBus().register(this.busEvents);
        showFabIfNeeded();
        showSoftInputIfNeeded();
    }

    public void onPause() {
        super.onPause();
        this.activity.getMessageBus().unregister(this.busEvents);
        hideSoftInput();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("extra_food", this.mealFood);
        bundle.putParcelableArrayList(EXTRA_FOOD_ENTRIES, (ArrayList) this.foodEntries);
        bundle.putBoolean("multi_add_mode_on", this.actionMode != null);
        bundle.putString(EXTRA_MEAL_FOOD_NAME, Strings.toString(this.inputMealName.getText()));
        bundle.putString("flow_id", this.flowId);
        bundle.putString("referrer", this.referrer);
        bundle.putParcelable(EXTRA_MEAL_FOOD_LOGGED_INFO, this.mealFoodLoggedInfo);
        bundle.putSerializable(EXTRA_EDIT_MODE, this.editMode);
        bundle.putBoolean(EXTRA_WAS_MEAL_EDITED_OR_COPIED, this.wasMealEditedOrCopied);
        bundle.putParcelable(EXTRA_REPLACED_MEAL_FOOD, this.replacedFood);
        bundle.putString(EXTRA_EDITED_IMAGE_ORIGINAL_PATH, this.editedImageOriginalPath);
        bundle.putParcelable(EXTRA_ORIGINAL_FOOD, this.originalFood);
        bundle.putSerializable(EXTRA_FOOD_PERMISSION, this.permission);
        bundle.putString(EXTRA_UPDATED_MEAL_NOTES, getNotes());
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(1001);
        if (disableCheckMarkOption() && findItem != null) {
            findItem.setEnabled(false).setIcon(R.drawable.ic_check_disabled_white_24dp);
        }
        if (!(this.editMode == EditMode.Display || this.editMode == EditMode.DisplayReadOnly || findItem == null)) {
            findItem.setIcon(null);
            if (enableSaveOption()) {
                findItem.setTitle(R.string.save);
                findItem.setEnabled(true);
            } else {
                findItem.setTitle("");
                findItem.setEnabled(false);
            }
        }
        if (this.editMode.canAddRemoveMealIngredients() && CollectionUtils.notEmpty((Collection<?>) this.foodEntries)) {
            MenuItemCompat.setShowAsAction(menu.add(0, EDIT_MEAL_INGREDIENTS_ITEM, 0, R.string.edit).setIcon(R.drawable.ic_edit_white_24dp), 2);
        }
        if (this.editMode == EditMode.Display) {
            menu.add(0, CloseCodes.UNEXPECTED_CONDITION, 0, R.string.edit_meal).setShowAsAction(0);
            menu.add(0, 1012, 0, R.string.copy_meal).setShowAsAction(0);
            if (isNavigatingFromMyMealsScreen()) {
                menu.add(0, 1013, 0, R.string.delete_meal).setShowAsAction(0);
            }
        }
        return true;
    }

    private void reloadNotes() {
        new FetchFoodNotesTask((FoodNotesTable) this.foodNotesTable.get(), this.mealFood).run(getRunner());
    }

    private boolean disableCheckMarkOption() {
        return CollectionUtils.isEmpty((Collection<?>) this.foodEntries) || (this.editMode.canEditMealFood() && Strings.isEmpty((Object) this.inputMealName.getText()));
    }

    private boolean enableSaveOption() {
        return CollectionUtils.notEmpty((Collection<?>) this.foodEntries) && Strings.notEmpty((Object) this.inputMealName.getText());
    }

    private void switchToEditModeIfInDisplayMode() {
        if (this.editMode == EditMode.Display || this.editMode == EditMode.DisplayReadOnly) {
            switchToEditMode();
        }
    }

    private void switchToEditMode() {
        this.editMode = EditMode.EditMealFood;
        this.originalFood = this.mealFood;
        reportMealFlowStartedEvent(hasVisibleImagePath());
        getMfpActivity().supportInvalidateOptionsMenu();
        renderView();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        hideHintPanel();
        switch (menuItem.getItemId()) {
            case EDIT_MEAL_INGREDIENTS_ITEM /*1010*/:
                hideSoftInput();
                getHandler().postDelayed(new Runnable() {
                    public final void run() {
                        MealEditorMixin.this.showMultiAddMode();
                    }
                }, 300);
                return true;
            case CloseCodes.UNEXPECTED_CONDITION /*1011*/:
                switchToEditMode();
                return true;
            case 1012:
                this.editMode = EditMode.CopyMealFood;
                this.originalFood = this.mealFood;
                reportMealFlowStartedEvent(hasVisibleImagePath());
                renderView();
                return true;
            case 1013:
                handleDeleteMealOperation();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean processActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            if (i == 49) {
                updateMealWithNewItems(BundleUtils.getParcelableArrayList(EXTRA_FOOD_ENTRIES, FoodEntry.class.getClassLoader(), intent.getExtras()));
                showMultiAddModeIfNeeded();
                return true;
            } else if (i == 1011) {
                FoodEntry foodEntry = (FoodEntry) BundleUtils.getParcelable(intent.getExtras(), "food_entry", FoodEntry.class.getClassLoader());
                int i3 = BundleUtils.getInt(intent.getExtras(), FoodEditorMixinBase.EXTRA_MEAL_INGREDIENT_INDEX);
                if (!(foodEntry == null || i3 == Integer.MIN_VALUE || i3 >= CollectionUtils.size((Collection<?>) this.foodEntries))) {
                    this.foodEntries.set(i3, foodEntry);
                    renderItemsListFromCurrentFoodEntries();
                }
                return true;
            } else if (i == 1012) {
                setNotes(BundleUtils.getString(intent.getExtras(), FoodNotesActivity.EXTRA_NOTES, getNotes()));
            }
        }
        if (i == 1013 && Strings.isEmpty(getNotes())) {
            reloadNotes();
        }
        return super.processActivityResult(i, i2, intent);
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (Strings.equals(DELETE_MEAL_CONFIRMATION_DIALOG_TAG, str)) {
            ((DeleteMealConfirmationDialogFragment) dialogFragment).setDeleteMealClickListener(this.onDeleteMealClickListener);
            return true;
        } else if (Strings.equals(SERVING_SIZE_DIALOG_TAG, str)) {
            ((EditNumServingsDialogFragment) dialogFragment).setOnServingSizeSelectedListener(this.onServingSizeSelectedListener);
            return true;
        } else if (!Strings.equals(MEAL_FOOD_PERMISSION_SELECTION_DIALOG_TAG, str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        } else {
            ((MealFoodPermissionSelectionDialogFragment) dialogFragment).setOnMealFoodPermissionSelectedListener(this.onMealFoodPermissionSelectedListener);
            return true;
        }
    }

    public boolean backPressed() {
        if (this.hintPanel.visible()) {
            this.hintPanel.animateOut();
            return true;
        }
        switch (this.editMode) {
            case CopyMealFood:
            case EditMealFood:
            case SaveAs:
            case Create:
                CheckMealModifiedTask checkMealModifiedTask = new CheckMealModifiedTask(this.mealService, (FoodNotesTable) this.foodNotesTable.get(), this.mealFood, this.editMode.canEditMealFood ? Strings.toString(this.inputMealName.getText()) : this.mealFood.getDescription(), this.foodEntries, this.permission, getImportedImagePath(), getNotes());
                checkMealModifiedTask.setDedupeMode(DedupeMode.UseExisting).run(getRunner());
                return true;
            default:
                finishBackPressed();
                return true;
        }
    }

    /* access modifiers changed from: private */
    public void finishBackPressed() {
        if (!this.wasMealEditedOrCopied || !isNavigatingFromFoodSearchScreen()) {
            getActivity().finish();
        } else {
            this.onItemSavedListener.onItemSavedOverrideStartIntent(-1, null, ((FoodSearchActivityFactory) this.foodSearchRouter.get()).getFoodSearchActivityIntent(this.activity, new Extras().setMealEdited(true)));
        }
    }

    public String getFoodItemName() {
        MealFood mealFood2 = this.mealFood;
        return mealFood2 != null ? mealFood2.brandAndDescription() : "";
    }

    public void saveItemToTarget() {
        saveItemToTarget(this.mealFood);
    }

    public void saveItemToTarget(MealFood mealFood2) {
        TimeValue timeValue;
        if (validateMealNameAndShowAlertIfNeeded()) {
            hideSoftInput();
            String strings = Strings.toString(this.inputMealName.getText());
            if (BundleUtils.getBoolean(EXTRA_MEAL_FOOD_CREATION_FLOW, Boolean.valueOf(false), this.intent.getExtras()).booleanValue()) {
                if (((MultiAddFoodHelper) this.multiAddFoodHelper.get()).isMultiAddModeOn()) {
                    handleSaveInMultiAdd();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_FOOD_ENTRIES, (ArrayList) this.foodEntries);
                    this.activity.setResult(-1, intent);
                    this.activity.finish();
                }
            } else if (isCreateOrSaveAsMode() || this.editMode == EditMode.CopyMealFood) {
                runMealFoodNameConflictCheckTask(strings);
            } else if (this.editMode == EditMode.EditMealFood) {
                MealFood mealFood3 = this.originalFood;
                if (mealFood3 == null || Strings.equals(strings, mealFood3.getDescription())) {
                    String importedImagePath = getImportedImagePath();
                    if (Strings.notEmpty(this.editedImageOriginalPath) && !this.editedImageOriginalPath.equals(importedImagePath)) {
                        ((MealAnalyticsHelper) this.mealAnalyticsHelper.get()).reportImageEditCompleted();
                    }
                    CrudMealFoodTask.newInstanceForUpdatingExistingMeal(this.mealService, this.foodService, this.foodPermissionsService, CreateMode.Update, strings, this.foodEntries, this.originalFood, this.permission, getImportedImageMode(), importedImagePath, getNotes()).run(this.activity.getRunner());
                } else {
                    runMealFoodNameConflictCheckTask(strings);
                }
            } else if (((MultiAddFoodHelper) this.multiAddFoodHelper.get()).isMultiAddModeOn()) {
                handleSaveInMultiAdd();
            } else {
                if (this.timestampPickerMixin.isFeatureEnabled()) {
                    TimeValue fromTimestampOption = TimeValue.Companion.fromTimestampOption(this.timestampPickerMixin.getSelectedOption());
                    for (FoodEntry entryTimeAndUpdateLoggedAt : this.foodEntries) {
                        entryTimeAndUpdateLoggedAt.setEntryTimeAndUpdateLoggedAt(this.timestampPickerMixin.getTimestamp());
                    }
                    timeValue = fromTimestampOption;
                } else {
                    timeValue = null;
                }
                AddMealFoodToDiaryTask addMealFoodToDiaryTask = new AddMealFoodToDiaryTask(this.mealService, this.foodEntries, getMealName(), this.mealFoodLoggedInfo, mealFood2, getImportedImagePath(), timeValue, this.searchVersion);
                addMealFoodToDiaryTask.run(this.activity.getRunner());
                ((LocalSettingsService) this.localSettingsService.get()).removeRecentsDeletedFoodOriginalUid(mealFood2.getOriginalUid());
            }
        }
    }

    public void renderView() {
        rebindUiFromDataModels();
    }

    private void rebindUiFromDataModels() {
        this.activity.setTitle(getTitleResId());
        this.activity.getWindow().setSoftInputMode(16);
        boolean canEditMealFood = this.editMode.canEditMealFood();
        boolean isMealSharingEnabled = ConfigUtils.isMealSharingEnabled((ConfigService) this.configService.get());
        ViewUtils.setVisible(canEditMealFood, this.inputMealNameContainer);
        ViewUtils.setVisible(!canEditMealFood, this.foodDescRowView);
        this.header.updateButtonState(this.editMode, this.importImageMixin.getVisibleImagePath());
        if (canEditMealFood) {
            this.inputMealName.requestFocus();
            MealFood mealFood2 = this.originalFood;
            if (mealFood2 != null) {
                String brandAndDescription = mealFood2.brandAndDescription();
                if (this.editMode == EditMode.CopyMealFood) {
                    brandAndDescription = String.format("%s 2", new Object[]{brandAndDescription});
                }
                this.inputMealName.setText(brandAndDescription);
                this.inputMealName.setSelection(brandAndDescription.length());
            }
        } else {
            setName(this.mealFood.brandAndDescription());
        }
        ViewUtils.setGone(this.dateRowView);
        ViewUtils.setGone(this.servingSizeRowView);
        if (canEditMealFood || this.editMode == EditMode.DisplayReadOnly) {
            ViewUtils.setGone(this.mealRowView);
        }
        if (this.editMode == EditMode.Display) {
            ViewUtils.setVisible(this.noOfServingsRowView);
        } else {
            ViewUtils.setGone(this.noOfServingsRowView);
            ViewUtils.setGone(this.timestampRowView);
        }
        ViewUtils.setVisible(this.fabActionPlus);
        ViewUtils.setVisible(canEditMealFood && isMealSharingEnabled, this.permissionsTableRow);
        ViewUtils.setVisible(!canEditMealFood && isMealSharingEnabled && this.editMode != EditMode.DisplayReadOnly, this.mealShareButton);
        renderNutritionDetails(this.nutritionalContents);
        showFabIfNeeded();
        this.activity.invalidateOptionsMenu();
        setNumServingsText();
        setupPermissionsTableRow();
        setNotes(getNotes());
    }

    private void handleSaveInMultiAdd() {
        int i;
        String str = "";
        MealFoodLoggedInfo mealFoodLoggedInfo2 = this.mealFoodLoggedInfo;
        if (mealFoodLoggedInfo2 != null) {
            str = mealFoodLoggedInfo2.getSearchQuery();
            i = this.mealFoodLoggedInfo.getResultsListPosition();
        } else {
            i = 0;
        }
        this.mealFood.setIngredients(((MealUtil) this.mealHelperUtil.get()).getMealIngredientsFromFoodEntries(this.foodEntries));
        MultiAddFoodHelper multiAddFoodHelper2 = (MultiAddFoodHelper) this.multiAddFoodHelper.get();
        MealFood mealFood2 = this.mealFood;
        multiAddFoodHelper2.addAndLogItem(mealFood2, Builder.fromFood(mealFood2).searchTerm(str).index(i).source(getSource()).requestId(this.requestId).build());
        this.onItemSavedListener.onItemSavedIgnoreStartIntent(-1, null);
    }

    public void setImageUri(String str) {
        this.importImageMixin.setImagePathAndUid(str, null);
    }

    public void setNotes(String str) {
        this.mealNotes.setText(str);
        ViewUtils.setVisible(this.mealNotesContainer);
        boolean canEditNotes = this.editMode.canEditNotes(this.mealFood, getMfpActivity().getSession());
        if (!Strings.isEmpty(str)) {
            ViewUtils.setGone(this.mealNotesEmpty);
            ViewUtils.setVisible(this.mealNotes);
            this.mealNotesButton.setText(R.string.edit);
        } else if (!canEditNotes) {
            ViewUtils.setGone(this.mealNotesContainer);
        } else {
            ViewUtils.setGone(this.mealNotes);
            ViewUtils.setVisible(this.mealNotesEmpty);
            this.mealNotesButton.setText(R.string.add);
        }
        ViewUtils.setVisible(canEditNotes, this.mealNotesButton);
    }

    public String getNotes() {
        return this.mealNotes.getText().toString();
    }

    public List<FoodEntry> getFoodEntries() {
        return this.foodEntries;
    }

    /* access modifiers changed from: protected */
    public void onServingsCountClick() {
        EditNumServingsDialogFragment newInstance = EditNumServingsDialogFragment.newInstance(this.numServings, true);
        newInstance.setOnServingSizeSelectedListener(this.onServingSizeSelectedListener);
        this.activity.showDialogFragment(newInstance, SERVING_SIZE_DIALOG_TAG);
    }

    private int getTitleResId() {
        MealFood mealFood2 = this.mealFood;
        if (mealFood2 == null || !mealFood2.isAutoGeneratedMeal()) {
            return this.editMode.getTitleResId();
        }
        return R.string.add_meal;
    }

    private void runMealFoodNameConflictCheckTask(String str) {
        new MealFoodNameConflictCheckTask(this.mealService, str).run(this.activity.getRunner());
    }

    private void reportMealFlowStartedEvent(boolean z) {
        if (this.editMode.canEditMealFood()) {
            Tuple2 startScreenAndTypeForEditMode = getStartScreenAndTypeForEditMode();
            ((MealAnalyticsHelper) this.mealAnalyticsHelper.get()).reportMealFlowStartedEvent((String) startScreenAndTypeForEditMode.getItem1(), this.flowId, (String) startScreenAndTypeForEditMode.getItem2(), z, Strings.notEmpty(getNotes()), this.permission);
        }
    }

    /* access modifiers changed from: private */
    public void reportMealFlowCompletedEvent() {
        if (this.editMode.canEditMealFood()) {
            Tuple2 startScreenAndTypeForEditMode = getStartScreenAndTypeForEditMode();
            ((MealAnalyticsHelper) this.mealAnalyticsHelper.get()).reportMealFlowCompletedEvent((String) startScreenAndTypeForEditMode.getItem1(), this.flowId, (String) startScreenAndTypeForEditMode.getItem2(), CollectionUtils.size((Collection<?>) this.foodEntries), hasVisibleImagePath(), Strings.notEmpty(getNotes()), this.permission);
        }
    }

    private Tuple2<String, String> getStartScreenAndTypeForEditMode() {
        switch (this.editMode) {
            case CopyMealFood:
                return Tuple.create(ATTRIBUTE_VALUE_MEAL_DETAILS, ATTRIBUTE_VALUE_COPY);
            case EditMealFood:
                return Tuple.create(ATTRIBUTE_VALUE_MEAL_DETAILS, "edit");
            case SaveAs:
                return Tuple.create(this.referrer, ATTRIBUTE_VALUE_SAVE);
            case Create:
                return Tuple.create(this.referrer, ATTRIBUTE_VALUE_NEW);
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unhandled edit mode type: ");
                sb.append(this.editMode);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    private void initListeners() {
        this.fabActionPlus.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin) = (r1v0 'this' com.myfitnesspal.feature.meals.ui.mixin.-$$Lambda$MealEditorMixin$LRsLXHJdFd4ZUQhZhTKn__t2jcw A[THIS]) com.myfitnesspal.feature.meals.ui.mixin.-$$Lambda$MealEditorMixin$LRsLXHJdFd4ZUQhZhTKn__t2jcw.f$0 com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin), (r2v0 'view' android.view.View) com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin.lambda$initListeners$2(com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin, android.view.View):void type: STATIC in method: com.myfitnesspal.feature.meals.ui.mixin.-$$Lambda$MealEditorMixin$LRsLXHJdFd4ZUQhZhTKn__t2jcw.onClick(android.view.View):void, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                    	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                    Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                    	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                    	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                    	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                    	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                    	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                    	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                    	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                    	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                    	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 38 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 63 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin r0 = com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin), (r2 I:android.view.View) com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin.lambda$initListeners$2(com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin, android.view.View):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.meals.ui.mixin.$$Lambda$MealEditorMixin$LRsLXHJdFd4ZUQhZhTKn__t2jcw.onClick(android.view.View):void");
            }
        });
        if (this.header.addButton != null) {
            this.header.addButton.setOnClickListener(new OnClickListener() {
                public final void onClick(View view) {
                    MealEditorMixin.lambda$initListeners$3(MealEditorMixin.this, view);
                }
            });
        }
        if (this.header.editButton != null) {
            this.header.editButton.setOnClickListener(new OnClickListener() {
                public final void onClick(View view) {
                    MealEditorMixin.lambda$initListeners$4(MealEditorMixin.this, view);
                }
            });
        }
        if (this.header.container != null) {
            this.header.container.setOnLongClickListener(new OnLongClickListener() {
                public final boolean onLongClick(View view) {
                    return MealEditorMixin.lambda$initListeners$5(MealEditorMixin.this, view);
                }
            });
            if (VERSION.SDK_INT >= 23) {
                this.header.container.setOnContextClickListener(new OnContextClickListener() {
                    public final boolean onContextClick(View view) {
                        return MealEditorMixin.lambda$initListeners$6(MealEditorMixin.this, view);
                    }
                });
            }
        }
        this.inputMealName.addTextChangedListener(new TextWatcher() {
            boolean wasTextBlankBefore;

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                this.wasTextBlankBefore = Strings.isEmpty((Object) charSequence);
            }

            public void afterTextChanged(Editable editable) {
                if (Strings.isEmpty((Object) editable) != this.wasTextBlankBefore) {
                    MealEditorMixin.this.activity.invalidateOptionsMenu();
                }
            }
        });
        this.mealShareButton.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                MealEditorMixin.lambda$initListeners$7(MealEditorMixin.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initListeners$3(MealEditorMixin mealEditorMixin, View view) {
        mealEditorMixin.switchToEditModeIfInDisplayMode();
        mealEditorMixin.importImageMixin.showImportDialog();
    }

    public static /* synthetic */ void lambda$initListeners$4(MealEditorMixin mealEditorMixin, View view) {
        mealEditorMixin.switchToEditModeIfInDisplayMode();
        ((MealAnalyticsHelper) mealEditorMixin.mealAnalyticsHelper.get()).reportImageEditTapped();
        mealEditorMixin.editedImageOriginalPath = mealEditorMixin.getImportedImagePath();
        mealEditorMixin.importImageMixin.showImportDialog();
    }

    public static /* synthetic */ boolean lambda$initListeners$5(MealEditorMixin mealEditorMixin, View view) {
        if (!mealEditorMixin.editMode.canEditImage()) {
            return false;
        }
        ((MealAnalyticsHelper) mealEditorMixin.mealAnalyticsHelper.get()).reportImageEditTapped();
        mealEditorMixin.editedImageOriginalPath = mealEditorMixin.getImportedImagePath();
        mealEditorMixin.importImageMixin.showImportDialog();
        return true;
    }

    public static /* synthetic */ boolean lambda$initListeners$6(MealEditorMixin mealEditorMixin, View view) {
        if (!mealEditorMixin.editMode.canEditImage()) {
            return false;
        }
        ((MealAnalyticsHelper) mealEditorMixin.mealAnalyticsHelper.get()).reportImageEditTapped();
        mealEditorMixin.editedImageOriginalPath = mealEditorMixin.getImportedImagePath();
        mealEditorMixin.importImageMixin.showImportDialog();
        return true;
    }

    public static /* synthetic */ void lambda$initListeners$7(MealEditorMixin mealEditorMixin, View view) {
        ((MealAnalyticsHelper) mealEditorMixin.mealAnalyticsHelper.get()).reportShareMealStarted(mealEditorMixin.hasVisibleImagePath());
        mealEditorMixin.initMealShare();
    }

    private void initMealShare() {
        MealShareViewHelper mealShareViewHelper2 = new MealShareViewHelper(this.activity, this.mealFood, this.nutritionalContents, this.session, this.userEnergyService, this.mealAnalyticsHelper, getVisibleImagePath(), this.importImageMixin.getVisibleImageUid());
        this.mealShareViewHelper = mealShareViewHelper2;
        this.mealShareViewHelper.initMealShare();
    }

    private void addMealIngredientsFromCurrentFoodEntries() {
        if (this.mealItemsContainer != null) {
            boolean isEmpty = CollectionUtils.isEmpty((Collection<?>) this.foodEntries);
            ViewUtils.setVisible(!isEmpty, this.mealItemsContainer);
            this.mealItemsList.removeAllViews();
            this.activity.invalidateOptionsMenu();
            if (!isEmpty) {
                boolean canAddRemoveMealIngredientsAndActionModeOn = canAddRemoveMealIngredientsAndActionModeOn();
                for (int i = 0; i < this.foodEntries.size(); i++) {
                    View view = this.foodSearchAdapter.getView(i, null, this.mealItemsList);
                    this.mealItemsList.addView(view);
                    ViewUtils.setVisible(canAddRemoveMealIngredientsAndActionModeOn, (CompoundButton) ViewUtils.findById(view, R.id.multiSelectCheckBox));
                    ViewUtils.findById(view, R.id.generic_list_item_divider).setVisibility(8);
                    view.setTag(this.foodSearchAdapter.getItem(i));
                    view.setOnClickListener(this.onIngredientClickListener);
                }
            }
        }
    }

    private void updateMealWithNewItems(List<FoodEntry> list) {
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            this.foodEntries.addAll(list);
        }
        renderItemsListFromCurrentFoodEntries();
    }

    private Permission getDefaultPermission() {
        MealFood mealFood2 = this.mealFood;
        FoodPermission foodPermission = mealFood2 != null ? mealFood2.getFoodPermission() : null;
        if (foodPermission != null) {
            return Permission.get(foodPermission.getPermissionValue());
        }
        return (!isCreateOrSaveAsMode() || !ConfigUtils.isMealSharingEnabled((ConfigService) this.configService.get())) ? Permission.Private : Permission.Public;
    }

    private void setupPermissionsTableRow() {
        this.permissionValueTextView.setText(this.permission.getNameResId());
        this.permissionsTableRow.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                MealEditorMixin.lambda$setupPermissionsTableRow$8(MealEditorMixin.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$setupPermissionsTableRow$8(MealEditorMixin mealEditorMixin, View view) {
        MealFoodPermissionSelectionDialogFragment newInstance = MealFoodPermissionSelectionDialogFragment.newInstance(mealEditorMixin.permission);
        newInstance.setOnMealFoodPermissionSelectedListener(mealEditorMixin.onMealFoodPermissionSelectedListener);
        mealEditorMixin.activity.showDialogFragment(newInstance, MEAL_FOOD_PERMISSION_SELECTION_DIALOG_TAG);
    }

    private void initializeItemsListFromCurrentFoodEntries() {
        FoodSearchAdapter foodSearchAdapter2 = new FoodSearchAdapter(this.activity, this.imageService, this.userEnergyService, this.mealHelperUtil, this.localizedStringsUtil, this.multiAddFoodHelper, false);
        this.foodSearchAdapter = foodSearchAdapter2;
        this.foodSearchAdapter.addAll(this.foodEntries);
        addMealIngredientsFromCurrentFoodEntries();
    }

    private boolean validateMealNameAndShowAlertIfNeeded() {
        if (!this.editMode.canEditMealFood() || Strings.length((Object) this.inputMealName.getText()) <= 100) {
            return true;
        }
        ((LegacyAlertMixin) this.activity.mixin(LegacyAlertMixin.class)).showAlertDialogWithTitleAndListeners(getString(R.string.nameTooLong, new Object[0]), getString(R.string.longMealNameValidation, new Object[0]), getString(R.string.dismiss, new Object[0]), null, null, null);
        return false;
    }

    /* access modifiers changed from: private */
    public String getString(@StringRes int i, Object... objArr) {
        return this.activity.getString(i, objArr);
    }

    private boolean isCreateOrSaveAsMode() {
        return this.editMode == EditMode.Create || this.editMode == EditMode.SaveAs;
    }

    private boolean canAddRemoveMealIngredientsAndActionModeOn() {
        return this.editMode.canAddRemoveMealIngredients() && this.actionMode != null;
    }

    /* access modifiers changed from: private */
    public void displayFailureMessage() {
        ((LegacyAlertMixin) this.activity.mixin(LegacyAlertMixin.class)).showAlertDialogWithTitleAndListeners(getString(R.string.error, new Object[0]), getString(R.string.generic_error_msg, new Object[0]), getString(R.string.dismiss, new Object[0]), null, null, null);
    }

    /* access modifiers changed from: private */
    public void displayMealFoodNameConflictMessage() {
        ((LegacyAlertMixin) this.activity.mixin(LegacyAlertMixin.class)).showAlertDialogWithTitleAndListeners(getString(R.string.replace_meal_alert_title, new Object[0]), getString(R.string.replace_meal_alert_message, new Object[0]), getString(R.string.ok, new Object[0]), this.onMealReplaceClickListener, getString(R.string.cancel, new Object[0]), null);
    }

    /* access modifiers changed from: private */
    public void renderItemsListFromCurrentFoodEntries() {
        this.nutritionalContents = ((MealUtil) this.mealHelperUtil.get()).getNutritionalContents(this.foodEntries);
        renderNutritionDetails(this.nutritionalContents);
        this.foodSearchAdapter.clear();
        this.foodSearchAdapter.addAll(this.foodEntries);
        addMealIngredientsFromCurrentFoodEntries();
        setNumServingsText();
    }

    /* access modifiers changed from: private */
    public Bundle getIntentExtrasForMealCreationDialog(String str) {
        Bundle bundle = new Bundle();
        if (this.editMode == EditMode.SaveAs) {
            bundle.putString("operation", this.shouldReplaceMeal ? EXTRA_REPLACE : "create");
            bundle.putString("meal_food", str);
        }
        return bundle;
    }

    /* access modifiers changed from: private */
    public void showMultiAddMode() {
        if (this.actionMode != null) {
            ((MultiAddFoodHelper) this.multiAddFoodHelper.get()).enable();
            updateItemsCheckBoxVisibility();
            return;
        }
        this.activity.startActionMode(new MultiAddActionMode());
        MaterialUtils.cleanActionModeDoneText(this.activity);
    }

    /* access modifiers changed from: private */
    public void updateActionModeTitle() {
        if (this.actionMode != null) {
            int i = ((MultiAddFoodHelper) this.multiAddFoodHelper.get()).totalItemCount();
            this.actionMode.setTitle(this.activity.getString(i > 0 ? R.string.number_selected : R.string.select_item, new Object[]{Integer.valueOf(i)}));
            this.actionMode.invalidate();
        }
    }

    /* access modifiers changed from: private */
    public void updateItemsCheckBoxVisibility() {
        boolean canAddRemoveMealIngredientsAndActionModeOn = canAddRemoveMealIngredientsAndActionModeOn();
        for (int i = 0; i < this.mealItemsList.getChildCount(); i++) {
            ViewUtils.setVisible(canAddRemoveMealIngredientsAndActionModeOn, (CompoundButton) ViewUtils.findById(this.mealItemsList.getChildAt(i), R.id.multiSelectCheckBox));
        }
    }

    private void handleDeleteMealOperation() {
        if (!((LocalSettingsService) this.localSettingsService.get()).showDeleteMealConfirmationDialog()) {
            startDeleteMealTask();
            return;
        }
        DeleteMealConfirmationDialogFragment newInstance = DeleteMealConfirmationDialogFragment.newInstance();
        newInstance.setDeleteMealClickListener(this.onDeleteMealClickListener);
        this.activity.showDialogFragment(newInstance, DELETE_MEAL_CONFIRMATION_DIALOG_TAG);
    }

    /* access modifiers changed from: private */
    public void startDeleteMealTask() {
        new DeleteMealFoodTask(this.mealService, this.mealFood).run(this.activity.getRunner());
        getAnalytics().reportFoodDeletion("meal", 1);
    }

    /* access modifiers changed from: private */
    public void deleteSelectedItems() {
        for (DiaryEntryCellModel diaryEntryCellModel : new ArrayList(((MultiAddFoodHelper) this.multiAddFoodHelper.get()).getAllSelectedItems())) {
            if (diaryEntryCellModel instanceof FoodEntry) {
                FoodEntry foodEntry = (FoodEntry) diaryEntryCellModel;
                ((MultiAddFoodHelper) this.multiAddFoodHelper.get()).removeItem(diaryEntryCellModel);
                ListIterator listIterator = this.foodEntries.listIterator();
                while (listIterator.hasNext()) {
                    FoodEntry foodEntry2 = (FoodEntry) listIterator.next();
                    if (Strings.equals(foodEntry2.getDescription(), foodEntry.getDescription()) && foodEntry2.getCaloriesValue() == foodEntry.getCaloriesValue() && foodEntry2.getQuantity() == foodEntry.getQuantity()) {
                        listIterator.remove();
                    }
                }
            }
        }
        ActionMode actionMode2 = this.actionMode;
        if (actionMode2 != null) {
            actionMode2.finish();
            ((MultiAddFoodHelper) this.multiAddFoodHelper.get()).disable();
        }
        renderItemsListFromCurrentFoodEntries();
    }

    private void setNumServingsText() {
        this.noOfServingsTextView.setText(NumberUtils.localeStringFromDouble((double) this.numServings));
    }

    /* access modifiers changed from: private */
    public void showFabIfNeeded() {
        if (this.actionMode != null || !this.editMode.canAddRemoveMealIngredients()) {
            this.fabActionPlus.hide();
            ViewUtils.setGone(this.bottomPadding);
            return;
        }
        this.fabActionPlus.show();
        ViewUtils.setVisible(this.bottomPadding);
    }

    private void showMultiAddModeIfNeeded() {
        if (canAddRemoveMealIngredientsAndActionModeOn()) {
            getHandler().post(new Runnable() {
                public final void run() {
                    MealEditorMixin.this.showMultiAddMode();
                }
            });
        }
    }

    private void showSoftInputIfNeeded() {
        if (!Strings.isEmpty((Object) this.inputMealName.getText()) || !this.editMode.canEditMealFood()) {
            hideSoftInput();
        } else {
            this.activity.getImmHelper().showSoftInput();
        }
    }

    private void hideSoftInput() {
        this.activity.getImmHelper().hideSoftInput();
    }

    /* access modifiers changed from: private */
    public String getImportedImagePath() {
        return this.importImageMixin.getImportedImagePath();
    }

    /* access modifiers changed from: private */
    public String getVisibleImagePath() {
        return this.importImageMixin.getVisibleImagePath();
    }

    private boolean hasVisibleImagePath() {
        return Strings.notEmpty(getVisibleImagePath());
    }

    /* access modifiers changed from: private */
    public ImageMode getImportedImageMode() {
        if (!Strings.isEmpty(this.importImageMixin.getVisibleImagePath()) || (!Strings.notEmpty(this.editedImageOriginalPath) && !this.shouldReplaceMeal)) {
            return ImageMode.Associate;
        }
        return ImageMode.Disassociate;
    }

    private void initCustomScrollingBehavior() {
        if (VersionUtils.isLollipopOrHigher()) {
            ((LayoutParams) ((ViewGroup) this.activity.findViewById(R.id.content_frame)).getLayoutParams()).setBehavior(new HeaderImageScrollBehavior(this.activity, this.header.container, R.id.nested_scroll_parent, this.header.buttonContainer));
        }
    }

    /* access modifiers changed from: private */
    public void updateFoodEntriesMealFood() {
        for (FoodEntry mealFood2 : this.foodEntries) {
            mealFood2.setMealFood(this.mealFood);
        }
    }

    /* access modifiers changed from: private */
    public boolean isNavigatingFromMyMealsScreen() {
        return Strings.equals(this.referrer, MealAnalyticsHelper.VALUE_MEAL_RECIPES_FOODS);
    }

    private boolean isNavigatingFromFoodSearchScreen() {
        return Strings.equals(this.referrer, "food_search");
    }

    private void hideHintPanel() {
        if (this.hintPanel.visible()) {
            this.hintPanel.animateOut();
        }
    }

    /* access modifiers changed from: private */
    public void showFoodNotesActivity() {
        switchToEditModeIfInDisplayMode();
        MfpActivity mfpActivity = getMfpActivity();
        mfpActivity.getNavigationHelper().withIntent(FoodNotesActivity.newStartIntent(mfpActivity, getNotes())).startActivity(1012);
    }

    /* access modifiers changed from: private */
    public void checkDisplayMealNotesHint(String str) {
        if (ConfigUtils.isMealNotesFoodEditorTipEnabled((ConfigService) this.configService.get()) && ((LocalSettingsService) this.localSettingsService.get()).getShowFoodEditorMealNotesHint() && Strings.isEmpty(str)) {
            MealFood mealFood2 = this.mealFood;
            if (mealFood2 != null && this.editMode.canEditNotes(mealFood2, (Session) this.session.get()) && !this.hintPanel.alreadyDisplayed()) {
                getHandler().postDelayed(new Runnable() {
                    public final void run() {
                        MealEditorMixin.lambda$checkDisplayMealNotesHint$10(MealEditorMixin.this);
                    }
                }, 500);
            }
        }
    }

    public static /* synthetic */ void lambda$checkDisplayMealNotesHint$10(MealEditorMixin mealEditorMixin) {
        if (mealEditorMixin.getMfpActivity().isEnabled()) {
            mealEditorMixin.hintPanel.setOnButtonPressedListener(new OnButtonPressedListener() {
                public void onButtonPressed(FoodEditorHint foodEditorHint, Type type) {
                    ((LocalSettingsService) MealEditorMixin.this.localSettingsService.get()).setShowFoodEditorMealNotesHint(false);
                    ((MealSharingDirectionsAnalyticsHelper) MealEditorMixin.this.mealBrowseAnalytics.get()).reportFoodEditorNotesPromptTapped();
                    MealEditorMixin.this.showFoodNotesActivity();
                }

                public void onClosePressed(FoodEditorHint foodEditorHint) {
                    ((LocalSettingsService) MealEditorMixin.this.localSettingsService.get()).setShowFoodEditorMealNotesHint(false);
                    ((MealSharingDirectionsAnalyticsHelper) MealEditorMixin.this.mealBrowseAnalytics.get()).reportFoodEditorNotesClosed();
                }
            });
            mealEditorMixin.hintPanel.animateIn(Type.MealNotes);
            ((MealSharingDirectionsAnalyticsHelper) mealEditorMixin.mealBrowseAnalytics.get()).reportFoodEditorNotesPromptDisplayed();
        }
    }

    /* access modifiers changed from: private */
    public CreateMode getMealServiceCreateMode() {
        if (this.editMode != null) {
            switch (this.editMode) {
                case CopyMealFood:
                case SaveAs:
                    return CreateMode.Copy;
                case EditMealFood:
                    return CreateMode.Update;
                case Create:
                case Display:
                    return CreateMode.Create;
            }
        }
        return CreateMode.Create;
    }
}
