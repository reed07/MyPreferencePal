package com.myfitnesspal.feature.meals.util;

import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.progress.task.GenerateArtifactImageTask;
import com.myfitnesspal.feature.progress.task.GenerateArtifactImageTask.CompletedEvent;
import com.myfitnesspal.feature.progress.ui.activity.StatusUpdateActivity;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v2.MfpEnergy;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.io.File;

public class MealShareViewHelper {
    private static final String DASH = "-";
    private static final String PROGRESS_DIALOG_TAG = "progress_dialog_tag";
    private static final String SHARE_ARTIFACT_FILE_NAME = "mfp-meal-photo-share-artifact-%d.png";
    private final MfpActivity activity;
    private final Lazy<MealAnalyticsHelper> mealAnalyticsHelper;
    private final MealFood mealFood;
    private final MfpNutritionalContents nutritionalContents;
    private final Lazy<Session> session;
    private final Lazy<UserEnergyService> userEnergyService;
    private final String visibleImagePath;
    private final String visibleImageUid;

    public MealShareViewHelper(MfpActivity mfpActivity, MealFood mealFood2, MfpNutritionalContents mfpNutritionalContents, Lazy<Session> lazy, Lazy<UserEnergyService> lazy2, Lazy<MealAnalyticsHelper> lazy3, String str, String str2) {
        this.activity = mfpActivity;
        this.mealFood = mealFood2;
        this.nutritionalContents = mfpNutritionalContents;
        this.session = lazy;
        this.userEnergyService = lazy2;
        this.mealAnalyticsHelper = lazy3;
        this.visibleImagePath = str;
        this.visibleImageUid = str2;
    }

    public void initMealShare() {
        View inflate = LayoutInflater.from(this.activity).inflate(R.layout.meal_share_artifact, null);
        final ImageView imageView = (ImageView) ViewUtils.findById(inflate, R.id.image_view);
        TextView textView = (TextView) ViewUtils.findById(inflate, R.id.meal_title);
        TextView textView2 = (TextView) ViewUtils.findById(inflate, R.id.created_by);
        View findById = ViewUtils.findById(inflate, R.id.nutritionFactsSimple);
        final View findById2 = ViewUtils.findById(inflate, R.id.artifact_container);
        int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(R.dimen.meal_share_artifact_size);
        textView.setText(this.mealFood.getDescription());
        textView2.setText(this.activity.getString(R.string.created_by_username, new Object[]{((Session) this.session.get()).getUser().getUsername()}));
        setupNutritionInfo(findById);
        inflate.setDrawingCacheEnabled(true);
        inflate.measure(MeasureSpec.makeMeasureSpec(dimensionPixelSize, 1073741824), MeasureSpec.makeMeasureSpec(dimensionPixelSize, 1073741824));
        inflate.layout(0, 0, inflate.getMeasuredWidth(), inflate.getMeasuredHeight());
        showProgressDialogFragment();
        Glide.with((FragmentActivity) this.activity).load(this.visibleImagePath).apply(new RequestOptions().centerCrop()).listener(new RequestListener<Drawable>() {
            boolean hasTriggeredOnce = false;

            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                if (!this.hasTriggeredOnce) {
                    this.hasTriggeredOnce = true;
                    imageView.setImageResource(R.drawable.meal_photo_default_bg);
                    MealShareViewHelper.this.createArtifactImage(findById2);
                }
                return true;
            }

            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                if (!this.hasTriggeredOnce) {
                    this.hasTriggeredOnce = true;
                    imageView.setImageDrawable(drawable);
                    MealShareViewHelper.this.createArtifactImage(findById2);
                }
                return true;
            }
        }).into(imageView);
    }

    public void onGenerateArtifactImageTaskCompletedEvent(CompletedEvent completedEvent) {
        dismissProgressDialogFragment();
        if (completedEvent.getFailure() == null) {
            this.activity.getNavigationHelper().withIntent(StatusUpdateActivity.newStartIntentForMealFoodShare(this.activity, Uri.fromFile(new File((String) completedEvent.getResult())), this.mealFood, this.visibleImageUid)).startActivity(MealEditorMixin.REQUEST_CODE_SHARE_MEAL);
            return;
        }
        new MfpAlertDialogBuilder(this.activity).setMessage((int) R.string.unable_share_meal).setPositiveButton((int) R.string.ok, (OnClickListener) null).show();
        ((MealAnalyticsHelper) this.mealAnalyticsHelper.get()).reportShareMealFailedDuringArtifactGeneration();
    }

    private void setupNutritionInfo(View view) {
        view.setBackground(null);
        setEnergy(view, this.nutritionalContents.getEnergy());
        setValueLabel(view, R.id.txtTotalFat, this.nutritionalContents.getFat().doubleValue());
        setValueLabel(view, R.id.txtTotalCarbs, this.nutritionalContents.getCarbohydrates().doubleValue());
        setValueLabel(view, R.id.txtProtein, this.nutritionalContents.getProtein().doubleValue());
    }

    private void setValueLabel(View view, int i, double d) {
        String str = DASH;
        if (d >= 0.0d) {
            str = Strings.initStringWithFormattedFloat((float) d, 1);
        }
        ((TextView) view.findViewById(i)).setText(str);
    }

    private void setEnergy(View view, MfpEnergy mfpEnergy) {
        String str;
        String string = this.activity.getString(((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyStringId());
        float caloriesValue = mfpEnergy.getCaloriesValue();
        ((TextView) view.findViewById(R.id.calories)).setText(string);
        TextView textView = (TextView) view.findViewById(R.id.txtCalories);
        double d = (double) caloriesValue;
        if (d <= MfpNutritionalContents.DEFAULT_VALUE.doubleValue()) {
            str = DASH;
        } else {
            str = ((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy(d);
        }
        textView.setText(str);
    }

    private void showProgressDialogFragment() {
        ProgressDialogFragment newInstance = ProgressDialogFragment.newInstance(R.string.please_wait);
        newInstance.setCancelable(false);
        this.activity.showDialogFragment(newInstance, PROGRESS_DIALOG_TAG);
    }

    private void dismissProgressDialogFragment() {
        DialogFragment dialogFragment = (DialogFragment) this.activity.getSupportFragmentManager().findFragmentByTag(PROGRESS_DIALOG_TAG);
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void createArtifactImage(View view) {
        new GenerateArtifactImageTask(String.format(SHARE_ARTIFACT_FILE_NAME, new Object[]{Long.valueOf(System.currentTimeMillis())}), view).run(this.activity.getRunner());
    }
}
