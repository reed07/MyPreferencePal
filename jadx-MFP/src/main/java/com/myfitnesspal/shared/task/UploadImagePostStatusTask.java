package com.myfitnesspal.shared.task;

import android.content.Context;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageUploadService;
import com.myfitnesspal.feature.images.service.ImageUploadService.ImageType;
import com.myfitnesspal.feature.progress.model.ImageStatusMetadata;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.model.v2.MfpProfile;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Locale;

public class UploadImagePostStatusTask extends EventedTaskBase<String, Exception> {
    private final String imagePath;
    private final Lazy<ImageService> imageService;
    private final ImageStatusMetadata imageStatusMetadata;
    private final Lazy<ImageUploadService> imageUploadService;
    private final MealFood mealFood;
    private final String mealFoodImageUid;
    private final Lazy<NewsFeedService> newsFeedService;
    private final String status;
    private final Type type;
    private final User user;
    private final String userId;

    public static class CompletedEvent extends TaskEventBase<String, Exception> {
        final String imageType;
        final String statusText;

        public CompletedEvent(String str, String str2) {
            this.statusText = str;
            this.imageType = str2;
        }

        public String getStatusText() {
            return this.statusText;
        }

        public String getImageType() {
            return this.imageType;
        }
    }

    protected enum Type {
        ProgressPhoto,
        MealFood,
        StatusPhoto
    }

    public static String getGenderString(int i) {
        return i == 1 ? "Male" : "Female";
    }

    public static UploadImagePostStatusTask newInstanceForProgressPhoto(String str, String str2, String str3, ImageStatusMetadata imageStatusMetadata2, User user2, Lazy<ImageService> lazy, Lazy<ImageUploadService> lazy2, Lazy<NewsFeedService> lazy3) {
        UploadImagePostStatusTask uploadImagePostStatusTask = new UploadImagePostStatusTask(str, str2, str3, imageStatusMetadata2, user2, lazy, lazy2, lazy3, null, null, Type.ProgressPhoto);
        return uploadImagePostStatusTask;
    }

    public static UploadImagePostStatusTask newInstanceForStatusPhoto(String str, String str2, String str3, ImageStatusMetadata imageStatusMetadata2, User user2, Lazy<ImageService> lazy, Lazy<ImageUploadService> lazy2, Lazy<NewsFeedService> lazy3) {
        UploadImagePostStatusTask uploadImagePostStatusTask = new UploadImagePostStatusTask(str, str2, str3, imageStatusMetadata2, user2, lazy, lazy2, lazy3, null, null, Type.StatusPhoto);
        return uploadImagePostStatusTask;
    }

    protected UploadImagePostStatusTask(String str, String str2, String str3, ImageStatusMetadata imageStatusMetadata2, User user2, Lazy<ImageService> lazy, Lazy<ImageUploadService> lazy2, Lazy<NewsFeedService> lazy3, MealFood mealFood2, String str4, Type type2) {
        super((TaskEventBase) new CompletedEvent(str3, getImageType(type2).getAnalyticsValue()));
        this.userId = str;
        this.imagePath = str2;
        this.status = str3;
        this.imageStatusMetadata = imageStatusMetadata2;
        this.imageService = lazy;
        this.imageUploadService = lazy2;
        this.newsFeedService = lazy3;
        this.user = user2;
        this.mealFood = mealFood2;
        this.mealFoodImageUid = str4;
        this.type = type2;
    }

    /* access modifiers changed from: protected */
    public String exec(Context context) throws Exception {
        MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry;
        MfpImage uploadImage = ((ImageUploadService) this.imageUploadService.get()).uploadImage(((ImageService) this.imageService.get()).createLocalImage(this.userId, this.imagePath), this.imagePath, getImageType(this.type));
        String id = uploadImage.getId();
        try {
            switch (this.type) {
                case ProgressPhoto:
                    collectImageStatusMetadata(id);
                    mfpNewsFeedActivityEntry = ((NewsFeedService) this.newsFeedService.get()).postNewProgressImageActivityEntrySync(this.status, this.imageStatusMetadata);
                    break;
                case StatusPhoto:
                    mfpNewsFeedActivityEntry = ((NewsFeedService) this.newsFeedService.get()).postNewStatusImageActivityEntrySync(this.status, id);
                    break;
                case MealFood:
                    mfpNewsFeedActivityEntry = ((NewsFeedService) this.newsFeedService.get()).postNewMealFoodActivityEntrySync(this.status, id, this.mealFood.getUid(), this.mealFoodImageUid);
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("unhandled type");
                    sb.append(this.type);
                    throw new IllegalStateException(sb.toString());
            }
            return mfpNewsFeedActivityEntry.getId();
        } catch (ApiException e) {
            ((ImageService) this.imageService.get()).deleteImage(uploadImage);
            throw e;
        }
    }

    private static ImageType getImageType(Type type2) {
        if (type2 == Type.MealFood) {
            return ImageType.MealPhotoArtifact;
        }
        return type2 == Type.StatusPhoto ? ImageType.StatusPhoto : ImageType.ProgressPhotoArtifact;
    }

    private void collectImageStatusMetadata(String str) {
        this.imageStatusMetadata.setImageId(str);
        this.imageStatusMetadata.setCompositionImageId(str);
        MfpProfile userProfile = this.user.getUserProfile();
        this.imageStatusMetadata.setAge(Strings.toString(Integer.valueOf(DateTimeUtils.getAgeInYears(this.user.getProfile().getDateOfBirth()))));
        this.imageStatusMetadata.setCountry(this.user.getProfile().getCountryName());
        this.imageStatusMetadata.setGender(getGenderString(this.user.getGender()));
        this.imageStatusMetadata.setHeight(Strings.toString(Float.valueOf(userProfile.getHeight().getValue())));
        this.imageStatusMetadata.setLanguage(Locale.getDefault().getDisplayLanguage());
        this.imageStatusMetadata.setStartingBmi(Strings.toString(Double.valueOf(this.user.getProfile().getCurrentBMI())));
        this.imageStatusMetadata.setStartingWeight(Strings.toString(Float.valueOf(userProfile.getStartingWeight().getValue())));
    }
}
