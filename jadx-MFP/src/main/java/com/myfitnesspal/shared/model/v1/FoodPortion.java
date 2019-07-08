package com.myfitnesspal.shared.model.v1;

import android.net.ParseException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.request.FoodInsightResponseData;
import com.myfitnesspal.shared.db.table.FoodPortionsTable.Columns;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FoodPortion implements Parcelable {
    public static final Creator<FoodPortion> CREATOR = new Creator<FoodPortion>() {
        public FoodPortion createFromParcel(Parcel parcel) {
            return new FoodPortion(parcel);
        }

        public FoodPortion[] newArray(int i) {
            return new FoodPortion[i];
        }
    };
    public static final Double DEFAULT_NUTRITION_MULTIPLIER = Double.valueOf(1.0d);
    @Expose
    private float amount;
    private String cachedDescriptionWithAmount;
    @Expose
    private String description;
    @Expose
    private float gramWeight;
    @Expose
    private boolean isFraction;
    @Expose
    private Double nutritionMultiplier;
    @Expose
    private int weightIndex;

    public int describeContents() {
        return 0;
    }

    public static FoodPortion getDefaultFoodPortion() {
        FoodPortion foodPortion = new FoodPortion(0, 1.0f, "serving", 1.0f, false);
        return foodPortion;
    }

    public FoodPortion() {
        this.nutritionMultiplier = DEFAULT_NUTRITION_MULTIPLIER;
    }

    public FoodPortion(int i, float f, String str, float f2, boolean z) {
        this.nutritionMultiplier = DEFAULT_NUTRITION_MULTIPLIER;
        this.weightIndex = i;
        this.amount = f;
        this.description = str;
        this.gramWeight = f2;
        this.isFraction = z;
    }

    public FoodPortion(FoodPortion foodPortion) {
        this.nutritionMultiplier = DEFAULT_NUTRITION_MULTIPLIER;
        this.weightIndex = foodPortion.weightIndex;
        this.amount = foodPortion.amount;
        this.description = foodPortion.description;
        this.gramWeight = foodPortion.gramWeight;
        this.isFraction = foodPortion.isFraction;
        this.cachedDescriptionWithAmount = foodPortion.cachedDescriptionWithAmount;
        this.nutritionMultiplier = foodPortion.nutritionMultiplier;
    }

    public FoodPortion(CursorMapper cursorMapper) {
        this.nutritionMultiplier = DEFAULT_NUTRITION_MULTIPLIER;
        this.weightIndex = cursorMapper.getInt("weight_index");
        this.amount = cursorMapper.getFloat("amount");
        this.description = cursorMapper.getString("description");
        this.gramWeight = cursorMapper.getFloat(Columns.GRAM_WEIGHT);
        this.isFraction = cursorMapper.getInt(Columns.IS_FRACTION) != 0;
        this.nutritionMultiplier = Double.valueOf(cursorMapper.getDouble(Columns.NUTRITION_MULTIPLIER));
    }

    private FoodPortion(Parcel parcel) {
        this.nutritionMultiplier = DEFAULT_NUTRITION_MULTIPLIER;
        this.weightIndex = parcel.readInt();
        this.amount = parcel.readFloat();
        this.description = parcel.readString();
        this.gramWeight = parcel.readFloat();
        this.isFraction = parcel.readByte() != 0;
        this.cachedDescriptionWithAmount = parcel.readString();
        this.nutritionMultiplier = Double.valueOf(parcel.readDouble());
    }

    public int getWeightIndex() {
        return this.weightIndex;
    }

    public void setWeightIndex(int i) {
        this.weightIndex = i;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float f) {
        this.amount = f;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public float getGramWeight() {
        return this.gramWeight;
    }

    public void setGramWeight(float f) {
        this.gramWeight = f;
    }

    public boolean getIsFraction() {
        return this.isFraction;
    }

    public void setIsFraction(boolean z) {
        this.isFraction = z;
    }

    public Double getNutritionMultiplier() {
        return this.nutritionMultiplier;
    }

    public void setNutritionMultiplier(Double d) {
        this.nutritionMultiplier = d;
    }

    public static String GetFraction(float f) {
        int i;
        String str;
        try {
            int floor = (int) Math.floor((double) f);
            float localeFloatFromString = f - NumberUtils.localeFloatFromString(Integer.toString(floor));
            int i2 = 0;
            int i3 = 1;
            int i4 = 1;
            int i5 = 1;
            int i6 = 0;
            while (true) {
                float f2 = 1.0f / localeFloatFromString;
                float floor2 = (float) Math.floor((double) f2);
                int i7 = (int) ((((float) floor) * floor2) + ((float) i4));
                int i8 = (int) ((((float) i5) * floor2) + ((float) i6));
                if (((float) i7) > 256.0f) {
                    break;
                } else if (((float) i8) > 256.0f) {
                    break;
                } else {
                    localeFloatFromString = f2 - floor2;
                    int i9 = i7;
                    i4 = floor;
                    floor = i9;
                    int i10 = i8;
                    i6 = i5;
                    i5 = i10;
                }
            }
            float localeFloatFromString2 = NumberUtils.localeFloatFromString(Integer.toString(floor)) / NumberUtils.localeFloatFromString(Integer.toString(i5));
            if (localeFloatFromString2 > 256.0f) {
                floor = 256;
            } else if (localeFloatFromString2 < 0.00390625f) {
                floor = 1;
                i3 = 256;
            } else {
                i3 = i5;
            }
            if (floor > i3) {
                i2 = floor % i3;
                i = (floor - i2) / i3;
            } else {
                i = 0;
            }
            StringBuilder sb = new StringBuilder();
            if (i == 0) {
                str = Integer.toString(floor);
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Integer.toString(i));
                sb2.append(" ");
                sb2.append(Integer.toString(i2));
                str = sb2.toString();
            }
            sb.append(str);
            sb.append("/");
            sb.append(Integer.toString(i3));
            return sb.toString().trim();
        } catch (NumberFormatException e) {
            Ln.e(e);
            return "";
        }
    }

    public String descriptionWithAmount() {
        String str = this.cachedDescriptionWithAmount;
        if (str == null || str.equals("")) {
            this.cachedDescriptionWithAmount = initString(new String(), this.amount);
        }
        return this.cachedDescriptionWithAmount;
    }

    public String initString(String str, float f) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(formattedServings(f));
            sb.append(" ");
            sb.append(this.description);
            return sb.toString();
        } catch (Exception e) {
            Ln.e(e);
            return str;
        }
    }

    public static Boolean analyzeServingSizeIsFraction(String str, Float f, Boolean bool) {
        try {
            Float.valueOf(BitmapDescriptorFactory.HUE_RED);
            Boolean valueOf = Boolean.valueOf(false);
            char decimalSeparator = new DecimalFormatSymbols(Locale.getDefault()).getDecimalSeparator();
            StringBuilder sb = new StringBuilder();
            sb.append("^-?\\d+((\\");
            sb.append(decimalSeparator);
            sb.append("\\d+)|(/\\d+)|(\\s\\d+/\\d+)|(\\s\\d+))?");
            Matcher matcher = Pattern.compile(sb.toString(), 34).matcher(str);
            if (matcher.find()) {
                String group = matcher.group(0);
                if (group.length() != 0) {
                    String[] split = group.split(" ");
                    if (split.length > 1) {
                        String str2 = split[0];
                        String str3 = split[1];
                        try {
                            int parseInt = Integer.parseInt(str2);
                            String[] split2 = str3.split("/");
                            float localeFloatFromString = NumberUtils.localeFloatFromString(split2[0]);
                            float localeFloatFromString2 = split2.length > 1 ? NumberUtils.localeFloatFromString(split2[1]) : 1.0f;
                            if (localeFloatFromString2 != BitmapDescriptorFactory.HUE_RED) {
                                valueOf = Boolean.valueOf(true);
                                Float.valueOf(((float) parseInt) + (localeFloatFromString / localeFloatFromString2));
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        String[] split3 = group.split("/");
                        if (split3.length == 1) {
                            try {
                                Float.valueOf(NumberUtils.localeFloatFromString(group));
                                valueOf = Boolean.valueOf(false);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        } else {
                            try {
                                Float.valueOf(NumberUtils.localeFloatFromString(split3[0]) / NumberUtils.localeFloatFromString(split3[1]));
                                valueOf = Boolean.valueOf(true);
                            } catch (Exception e3) {
                                Ln.e(e3);
                            }
                        }
                    }
                }
            }
            return valueOf;
        } catch (PatternSyntaxException e4) {
            Ln.e(e4);
            return Boolean.valueOf(false);
        } catch (NumberFormatException e5) {
            Ln.e(e5);
            return Boolean.valueOf(false);
        }
    }

    public static Float analyzeServingSizeAmount(String str) {
        try {
            Float valueOf = Float.valueOf(BitmapDescriptorFactory.HUE_RED);
            char decimalSeparator = new DecimalFormatSymbols(Locale.getDefault()).getDecimalSeparator();
            StringBuilder sb = new StringBuilder();
            sb.append("^-?\\d+((\\");
            sb.append(decimalSeparator);
            sb.append("\\d+)|(/\\d+)|(\\s\\d+/\\d+)|(\\s\\d+))?");
            Matcher matcher = Pattern.compile(sb.toString(), 34).matcher(str);
            if (matcher.find()) {
                String group = matcher.group(0);
                if (group.length() != 0) {
                    String[] split = group.split(" ");
                    if (split.length > 1) {
                        String str2 = split[0];
                        String str3 = split[1];
                        try {
                            int parseInt = Integer.parseInt(str2);
                            String[] split2 = str3.split("/");
                            float localeFloatFromString = NumberUtils.localeFloatFromString(split2[0]);
                            float localeFloatFromString2 = split2.length > 1 ? NumberUtils.localeFloatFromString(split2[1]) : 1.0f;
                            if (localeFloatFromString2 != BitmapDescriptorFactory.HUE_RED) {
                                valueOf = Float.valueOf(((float) parseInt) + (localeFloatFromString / localeFloatFromString2));
                            }
                        } catch (ParseException e) {
                            Ln.e(e);
                        }
                    } else {
                        String[] split3 = group.split("/");
                        if (split3.length == 1) {
                            try {
                                valueOf = Float.valueOf(NumberUtils.localeFloatFromString(group));
                            } catch (Exception e2) {
                                Ln.e(e2);
                            }
                        } else {
                            try {
                                valueOf = Float.valueOf(NumberUtils.localeFloatFromString(split3[0]) / NumberUtils.localeFloatFromString(split3[1]));
                            } catch (Exception e3) {
                                Ln.e(e3);
                            }
                        }
                    }
                }
            }
            return valueOf;
        } catch (PatternSyntaxException e4) {
            Ln.e(e4);
            return Float.valueOf(BitmapDescriptorFactory.HUE_RED);
        } catch (NumberFormatException e5) {
            Ln.e(e5);
            return Float.valueOf(BitmapDescriptorFactory.HUE_RED);
        }
    }

    public static String analyzeServingSizeDescription(String str) {
        try {
            char decimalSeparator = new DecimalFormatSymbols(Locale.getDefault()).getDecimalSeparator();
            StringBuilder sb = new StringBuilder();
            sb.append("^-?\\d+((\\");
            sb.append(decimalSeparator);
            sb.append("\\d+)|(/\\d+)|(\\s\\d+/\\d+)|(\\s\\d+))?");
            String sb2 = sb.toString();
            String str2 = "";
            Matcher matcher = Pattern.compile(sb2, 34).matcher(str);
            if (matcher.find()) {
                try {
                    str2 = str.substring(matcher.end(0), str.length()).trim();
                } catch (Exception e) {
                    Ln.e(e);
                }
            }
            return str2;
        } catch (PatternSyntaxException e2) {
            Ln.e(e2);
            return "";
        }
    }

    public String formattedServings(float f) {
        if (this.isFraction) {
            return mixedFractionStringFor(f);
        }
        return NumberUtils.localeStringFromFloatWithExactFractionDigits(f, 1);
    }

    public static String mixedFractionStringFor(float f) {
        String str;
        double d = (double) f;
        if (d < 0.0d) {
            return FoodInsightResponseData.NEGATIVE;
        }
        if (d == 0.0d) {
            return "0";
        }
        if (d >= 1.0d) {
            float floor = (float) (d - Math.floor(d));
            str = String.valueOf((int) f);
            f = floor;
        } else {
            str = "";
        }
        String fractionStringFor = fractionStringFor(f);
        if (fractionStringFor.length() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" ");
            sb.append(fractionStringFor);
            str = sb.toString();
        }
        return str.equals(" ") ? "" : str;
    }

    public static String fractionStringFor(float f) {
        String[] strArr = {"", "⅛", "¼", "⅜", "½", "⅝", "¾", "⅞"};
        double d = (double) f;
        if (d >= 0.333d && d <= 0.334d) {
            return "⅓";
        }
        if (d >= 0.666d && d <= 0.667d) {
            return "⅔";
        }
        double d2 = d * 8.0d;
        if (Math.abs(d2 - ((double) Math.round(d2))) < 0.01d) {
            int round = (int) Math.round(d2);
            if (round >= 0 && round < 8) {
                return strArr[round];
            }
        }
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        float f2 = 100.0f;
        while (i2 <= 9) {
            int i4 = i2 + 1;
            float f3 = f2;
            int i5 = i3;
            int i6 = i;
            for (int i7 = i4; i7 <= 9; i7++) {
                if (my_gcd(i2, i7) == 1) {
                    float abs = Math.abs(f - (((float) i2) / ((float) i7)));
                    if (abs < f3) {
                        i6 = i2;
                        i5 = i7;
                        f3 = abs;
                    }
                }
            }
            i = i6;
            i3 = i5;
            i2 = i4;
            f2 = f3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append("/");
        sb.append(i3);
        return sb.toString();
    }

    static int my_gcd(int i, int i2) {
        int i3 = i2;
        int i4 = i;
        int i5 = i3;
        while (i5 != 0) {
            int i6 = i4 % i5;
            i4 = i5;
            i5 = i6;
        }
        return i4;
    }

    public String newDescriptionStringWithAmount(float f) {
        return String.format("%s %s", new Object[]{formattedServings(f), this.description});
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.weightIndex);
        parcel.writeFloat(this.amount);
        parcel.writeString(this.description);
        parcel.writeFloat(this.gramWeight);
        parcel.writeByte(this.isFraction ? (byte) 1 : 0);
        parcel.writeString(this.cachedDescriptionWithAmount);
        parcel.writeDouble(this.nutritionMultiplier.doubleValue());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FoodPortion)) {
            return false;
        }
        FoodPortion foodPortion = (FoodPortion) obj;
        if (!(this.weightIndex == foodPortion.weightIndex && Float.compare(this.amount, foodPortion.amount) == 0 && Strings.equals(this.description, foodPortion.description) && Float.compare(this.gramWeight, foodPortion.gramWeight) == 0 && this.isFraction == foodPortion.isFraction && Double.compare(this.nutritionMultiplier.doubleValue(), foodPortion.nutritionMultiplier.doubleValue()) == 0 && Strings.equals(this.cachedDescriptionWithAmount, foodPortion.cachedDescriptionWithAmount))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int floatToIntBits = ((((((((527 + this.weightIndex) * 31) + Float.floatToIntBits(this.amount)) * 31) + Strings.toString(this.description).hashCode()) * 31) + Float.floatToIntBits(this.gramWeight)) * 31) + (this.isFraction ? 1 : 0);
        long doubleToLongBits = Double.doubleToLongBits(getNutritionMultiplier().doubleValue());
        return (((floatToIntBits * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + Strings.toString(this.cachedDescriptionWithAmount).hashCode();
    }
}
