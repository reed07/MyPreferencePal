package com.myfitnesspal.feature.barcode.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.myfitnesspal.feature.barcode.ui.activity.BarcodeMatchActivity;
import com.myfitnesspal.feature.barcode.ui.activity.BarcodeMatchActivity.StartMode;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType;
import com.myfitnesspal.feature.meals.model.MealIngredientEditorBundleData;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class BarcodeUtil {

    /* renamed from: com.myfitnesspal.feature.barcode.util.BarcodeUtil$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$myfitnesspal$feature$foodeditor$ui$activity$FoodEditorType = new int[FoodEditorType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType[] r0 = com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$myfitnesspal$feature$foodeditor$ui$activity$FoodEditorType = r0
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$foodeditor$ui$activity$FoodEditorType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType r1 = com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType.DiaryFood     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$foodeditor$ui$activity$FoodEditorType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType r1 = com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType.MealIngredient     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$foodeditor$ui$activity$FoodEditorType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType r1 = com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType.RecipeIngredient     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.barcode.util.BarcodeUtil.AnonymousClass1.<clinit>():void");
        }
    }

    public static void handleScanResult(MfpActivity mfpActivity, AnalyticsService analyticsService, FoodEditorType foodEditorType, Intent intent, int i, Session session, Intent intent2, String str, String str2, @Nullable String str3, @Nullable Date date) {
        FoodEditorType foodEditorType2 = foodEditorType;
        if (foodEditorType2 != FoodEditorType.DiaryFood && foodEditorType2 != FoodEditorType.BarcodeMultiAddFood && foodEditorType2 != FoodEditorType.MealIngredient && foodEditorType2 != FoodEditorType.RecipeIngredient) {
            StringBuilder sb = new StringBuilder();
            sb.append("editorType invalid! ");
            sb.append(foodEditorType.toString());
            throw new IllegalArgumentException(sb.toString());
        } else if (intent2 != null) {
            String string = BundleUtils.getString(intent2.getExtras(), "barcode");
            if (i == -1) {
                AnalyticsService analyticsService2 = analyticsService;
                analyticsService.reportFoodLookup(CollectionUtils.nameValuePairsToMap("source", SearchSource.BARCODE_SCAN.getTitle(), "flow_id", str3));
                handleScanSuccess(mfpActivity, foodEditorType, intent, session, intent2, str, string, str2, date);
                return;
            }
            handleScanFailure(mfpActivity, foodEditorType, intent, intent2, str, string, str2, date);
        }
    }

    private static void handleScanFailure(Activity activity, FoodEditorType foodEditorType, Intent intent, Intent intent2, String str, String str2, String str3, Date date) {
        if (BundleUtils.getInt(intent2.getExtras(), "errorCode", -1) == 257) {
            activity.startActivityForResult(BarcodeMatchActivity.createStartIntent(activity, intent, null, str3, str2, str, StartMode.NoMatches, date, foodEditorType), 55);
        }
    }

    private static void handleScanSuccess(Activity activity, FoodEditorType foodEditorType, Intent intent, Session session, Intent intent2, String str, String str2, String str3, Date date) {
        Intent intent3;
        Activity activity2 = activity;
        Bundle extras = intent2.getExtras();
        Date activeDate = date == null ? session.getUser().getActiveDate() : date;
        ArrayList parcelableArrayAsList = BundleUtils.getParcelableArrayAsList(extras, Extras.FOOD_LIST, MfpFood.class.getClassLoader());
        int size = CollectionUtils.size((Collection<?>) parcelableArrayAsList);
        if (size == 1) {
            MfpFood mfpFood = (MfpFood) parcelableArrayAsList.get(0);
            int i = AnonymousClass1.$SwitchMap$com$myfitnesspal$feature$foodeditor$ui$activity$FoodEditorType[foodEditorType.ordinal()];
            int i2 = RequestCodes.FOOD_EDITOR;
            switch (i) {
                case 1:
                    Intent intent4 = intent;
                    intent3 = FoodEditorActivity.newDiaryFoodItemEditorIntent(activity, intent, mfpFood, activeDate, str3, str2, SearchSource.BARCODE_SCAN, str);
                    break;
                case 2:
                    MealIngredientEditorBundleData mealIngredientEditorBundleData = new MealIngredientEditorBundleData(mfpFood, activeDate, str3, str2, str);
                    Intent intent5 = intent;
                    intent3 = FoodEditorActivity.newMealIngredientEditorIntent(activity, intent, mealIngredientEditorBundleData);
                    break;
                case 3:
                    intent3 = FoodEditorActivity.newSelectRecipeIngredientIntent(activity, mfpFood);
                    i2 = 200;
                    break;
                default:
                    Intent intent6 = intent;
                    intent3 = FoodEditorActivity.newBarcodeMultiAddFoodItemEditorIntent(activity, intent, (MfpFood) parcelableArrayAsList.get(0), activeDate, str3, str2, str);
                    i2 = 55;
                    break;
            }
            activity.startActivityForResult(intent3, i2);
            return;
        }
        Intent intent7 = intent;
        activity.startActivityForResult(BarcodeMatchActivity.createStartIntent(activity, intent, parcelableArrayAsList, str3, str2, str, size > 1 ? StartMode.BetterMatch : StartMode.NoMatches, activeDate, foodEditorType), 55);
    }

    public static String UPCEtoEAN13(String str) {
        return UPCAToEAN13(UPCEToUPCA(str));
    }

    public static String UPCAToEAN13(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("0");
        sb.append(str);
        return sb.toString();
    }

    private static String UPCEToUPCA(String str) {
        try {
            char[] charArray = str.toCharArray();
            char c = charArray[0];
            char c2 = charArray[7];
            char[] cArr = new char[12];
            cArr[0] = c;
            cArr[11] = c2;
            cArr[1] = charArray[1];
            cArr[2] = charArray[2];
            switch (charArray[6]) {
                case '0':
                    cArr[3] = '0';
                    cArr[4] = '0';
                    cArr[5] = '0';
                    cArr[6] = '0';
                    cArr[7] = '0';
                    cArr[8] = charArray[3];
                    cArr[9] = charArray[4];
                    cArr[10] = charArray[5];
                    break;
                case '1':
                    cArr[3] = '1';
                    cArr[4] = '0';
                    cArr[5] = '0';
                    cArr[6] = '0';
                    cArr[7] = '0';
                    cArr[8] = charArray[3];
                    cArr[9] = charArray[4];
                    cArr[10] = charArray[5];
                    break;
                case '2':
                    cArr[3] = '2';
                    cArr[4] = '0';
                    cArr[5] = '0';
                    cArr[6] = '0';
                    cArr[7] = '0';
                    cArr[8] = charArray[3];
                    cArr[9] = charArray[4];
                    cArr[10] = charArray[5];
                    break;
                case '3':
                    cArr[3] = charArray[3];
                    cArr[4] = '0';
                    cArr[5] = '0';
                    cArr[6] = '0';
                    cArr[7] = '0';
                    cArr[8] = '0';
                    cArr[9] = charArray[4];
                    cArr[10] = charArray[5];
                    break;
                case '4':
                    cArr[3] = charArray[3];
                    cArr[4] = charArray[4];
                    cArr[5] = '0';
                    cArr[6] = '0';
                    cArr[7] = '0';
                    cArr[8] = '0';
                    cArr[9] = '0';
                    cArr[10] = charArray[5];
                    break;
                case '5':
                    cArr[3] = charArray[3];
                    cArr[4] = charArray[4];
                    cArr[5] = charArray[5];
                    cArr[6] = '0';
                    cArr[7] = '0';
                    cArr[8] = '0';
                    cArr[9] = '0';
                    cArr[10] = '5';
                    break;
                case '6':
                    cArr[3] = charArray[3];
                    cArr[4] = charArray[4];
                    cArr[5] = charArray[5];
                    cArr[6] = '0';
                    cArr[7] = '0';
                    cArr[8] = '0';
                    cArr[9] = '0';
                    cArr[10] = '6';
                    break;
                case '7':
                    cArr[3] = charArray[3];
                    cArr[4] = charArray[4];
                    cArr[5] = charArray[5];
                    cArr[6] = '0';
                    cArr[7] = '0';
                    cArr[8] = '0';
                    cArr[9] = '0';
                    cArr[10] = '7';
                    break;
                case '8':
                    cArr[3] = charArray[3];
                    cArr[4] = charArray[4];
                    cArr[5] = charArray[5];
                    cArr[6] = '0';
                    cArr[7] = '0';
                    cArr[8] = '0';
                    cArr[9] = '0';
                    cArr[10] = '8';
                    break;
                case '9':
                    cArr[3] = charArray[3];
                    cArr[4] = charArray[4];
                    cArr[5] = charArray[5];
                    cArr[6] = '0';
                    cArr[7] = '0';
                    cArr[8] = '0';
                    cArr[9] = '0';
                    cArr[10] = '9';
                    break;
            }
            return String.valueOf(cArr);
        } catch (Exception e) {
            Ln.e(e);
            return str;
        }
    }
}
