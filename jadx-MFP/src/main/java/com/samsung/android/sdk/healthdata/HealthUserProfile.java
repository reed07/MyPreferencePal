package com.samsung.android.sdk.healthdata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.samsung.android.sdk.internal.healthdata.ErrorUtil;

public class HealthUserProfile {
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    public static final String USER_PROFILE_KEY_BIRTH_DATE = "birth_date";
    public static final String USER_PROFILE_KEY_GENDER = "gender";
    public static final String USER_PROFILE_KEY_HEIGHT = "height";
    public static final String USER_PROFILE_KEY_IMAGE = "image";
    public static final String USER_PROFILE_KEY_USER_ID = "user_id";
    public static final String USER_PROFILE_KEY_USER_NAME = "name";
    public static final String USER_PROFILE_KEY_WEIGHT = "weight";
    private final String a;
    private final float b;
    private final float c;
    private final String d;
    private final int e;
    private final String f;
    private final Bitmap g;

    private HealthUserProfile(String str, float f2, float f3, String str2, int i, String str3, Bitmap bitmap) {
        this.a = str;
        this.b = f2;
        this.c = f3;
        this.d = str2;
        this.e = i;
        this.f = str3;
        this.g = bitmap;
    }

    public static HealthUserProfile getProfile(HealthDataStore healthDataStore) {
        Bitmap decodeByteArray;
        try {
            Bundle userProfile2 = HealthDataStore.getInterface(healthDataStore).getUserProfile2(healthDataStore.a().getPackageName());
            if (userProfile2 != null) {
                String string = userProfile2.getString(USER_PROFILE_KEY_BIRTH_DATE);
                float f2 = userProfile2.getFloat("height", BitmapDescriptorFactory.HUE_RED);
                float f3 = userProfile2.getFloat("weight", BitmapDescriptorFactory.HUE_RED);
                String string2 = userProfile2.getString("user_id");
                int i = userProfile2.getInt("gender", 0);
                String string3 = userProfile2.getString("name");
                byte[] byteArray = userProfile2.getByteArray("image");
                if (byteArray == null) {
                    decodeByteArray = null;
                } else {
                    decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                }
                HealthUserProfile healthUserProfile = new HealthUserProfile(string, f2, f3, string2, i, string3, decodeByteArray);
                return healthUserProfile;
            }
            throw new IllegalStateException("profileBundle is null");
        } catch (RemoteException e2) {
            throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e2));
        }
    }

    public String getBirthDate() {
        return this.a;
    }

    public float getHeight() {
        return this.b;
    }

    public float getWeight() {
        return this.c;
    }

    public String getUserId() {
        return this.d;
    }

    public int getGender() {
        return this.e;
    }

    public String getUserName() {
        return this.f;
    }

    public Bitmap getImage() {
        return this.g;
    }
}
