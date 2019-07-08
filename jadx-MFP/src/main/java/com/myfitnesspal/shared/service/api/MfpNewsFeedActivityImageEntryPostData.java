package com.myfitnesspal.shared.service.api;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.progress.model.ImageStatusMetadata;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.List;

public class MfpNewsFeedActivityImageEntryPostData {
    private static final String TYPE_IMAGE_STATUS_UPDATE = "image_status_update";
    private static final String TYPE_MEAL_STATUS_UPDATE = "meal_status_update";
    @Expose
    private final EntryDetails item;

    private static class Artifact {
        @Expose
        private final String compositionImageId;
        @Expose
        private final String customCaption;
        @Expose
        private final String customCaptionString;
        @Expose
        private final String dataPoint;
        @Expose
        private final List<Pane> panes;

        public Artifact(String str, String str2, String str3, String str4, List<Pane> list) {
            this.compositionImageId = str;
            this.customCaption = str2;
            this.customCaptionString = str3;
            this.dataPoint = str4;
            this.panes = list;
        }
    }

    private static class Demographic {
        @Expose
        private String age;
        @Expose
        private String country;
        @Expose
        private String gender;
        @Expose
        private String height;
        @Expose
        private String language;
        @Expose
        private String startingBmi;
        @Expose
        private String startingWeight;

        public Demographic(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            this.age = str;
            this.country = str2;
            this.gender = str3;
            this.height = str4;
            this.language = str5;
            this.startingBmi = str6;
            this.startingWeight = str7;
        }
    }

    private static abstract class EntryData {
        @Expose
        private final String text;

        static class MealFoodEntryData extends EntryData {
            @Expose
            private final String foodId;
            @Expose
            private final String imageId;
            @Expose
            private final String originalImageId;

            MealFoodEntryData(String str, String str2, String str3, String str4) {
                super(str);
                this.imageId = str2;
                this.foodId = str3;
                this.originalImageId = str4;
            }
        }

        static class ProgressPhotoEntryData extends EntryData {
            @Expose
            private final String artifactDataType;
            @Expose
            private Demographic demographic;
            @Expose
            private final ImageData imageData;
            @Expose
            private final String imageId;

            public ProgressPhotoEntryData(String str, ImageStatusMetadata imageStatusMetadata) {
                super(str);
                this.artifactDataType = imageStatusMetadata.getArtifactDataType();
                this.imageId = imageStatusMetadata.getImageId();
                this.imageData = getImageDataFromMetadata(imageStatusMetadata);
                this.demographic = getDemographicFromMetadata(imageStatusMetadata);
            }

            private Demographic getDemographicFromMetadata(ImageStatusMetadata imageStatusMetadata) {
                Demographic demographic2 = new Demographic(imageStatusMetadata.getAge(), imageStatusMetadata.getCountry(), imageStatusMetadata.getGender(), imageStatusMetadata.getHeight(), imageStatusMetadata.getLanguage(), imageStatusMetadata.getStartingBmi(), imageStatusMetadata.getStartingWeight());
                return demographic2;
            }

            private ImageData getImageDataFromMetadata(ImageStatusMetadata imageStatusMetadata) {
                return new ImageData(imageStatusMetadata);
            }
        }

        static class StatusPhotoEntryData extends EntryData {
            @Expose
            private final ImageData imageData = new ImageData();
            @Expose
            private final String imageId;

            public StatusPhotoEntryData(String str, String str2) {
                super(str);
                this.imageId = str2;
            }
        }

        public EntryData(String str) {
            this.text = str;
        }
    }

    private static class EntryDetails {
        @Expose
        private final EntryData data;
        @Expose
        private final String type;

        public EntryDetails(String str, EntryData entryData) {
            this.type = str;
            this.data = entryData;
        }
    }

    private static class ImageData {
        @Expose
        private final Artifact artifact;

        public ImageData() {
            this.artifact = null;
        }

        public ImageData(ImageStatusMetadata imageStatusMetadata) {
            Artifact artifact2 = new Artifact(imageStatusMetadata.getCompositionImageId(), "", "", imageStatusMetadata.getDataPoint(), getPanesFromMetadata(imageStatusMetadata));
            this.artifact = artifact2;
        }

        private List<Pane> getPanesFromMetadata(ImageStatusMetadata imageStatusMetadata) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Pane(imageStatusMetadata.getDate1(), imageStatusMetadata.getImageId1(), imageStatusMetadata.getMeasurementValue1()));
            if (Strings.notEmpty(imageStatusMetadata.getDate2())) {
                arrayList.add(new Pane(imageStatusMetadata.getDate2(), imageStatusMetadata.getImageId2(), imageStatusMetadata.getMeasurementValue2()));
            }
            return arrayList;
        }
    }

    private static class Pane {
        @Expose
        private final String date;
        @Expose
        private final String imageId;
        @Expose
        private final String measurementValue;

        public Pane(String str, String str2, String str3) {
            this.date = str;
            this.imageId = str2;
            this.measurementValue = str3;
        }
    }

    public static MfpNewsFeedActivityImageEntryPostData newInstanceForProgressPhotoUpate(String str, ImageStatusMetadata imageStatusMetadata) {
        return new MfpNewsFeedActivityImageEntryPostData(str, imageStatusMetadata);
    }

    public static MfpNewsFeedActivityImageEntryPostData newInstanceForStatusPhotoUpate(String str, String str2) {
        return new MfpNewsFeedActivityImageEntryPostData(str, str2);
    }

    public static MfpNewsFeedActivityImageEntryPostData newInstanceForMealFoodUpdate(String str, String str2, String str3, String str4) {
        return new MfpNewsFeedActivityImageEntryPostData(str, str2, str3, str4);
    }

    private MfpNewsFeedActivityImageEntryPostData(String str, ImageStatusMetadata imageStatusMetadata) {
        this.item = new EntryDetails("image_status_update", new ProgressPhotoEntryData(str, imageStatusMetadata));
    }

    private MfpNewsFeedActivityImageEntryPostData(String str, String str2) {
        this.item = new EntryDetails("image_status_update", new StatusPhotoEntryData(str, str2));
    }

    private MfpNewsFeedActivityImageEntryPostData(String str, String str2, String str3, String str4) {
        this.item = new EntryDetails("meal_status_update", new MealFoodEntryData(str, str2, str3, str4));
    }
}
