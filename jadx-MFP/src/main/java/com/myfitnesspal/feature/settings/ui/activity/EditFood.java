package com.myfitnesspal.feature.settings.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.validation.Validator;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.inject.Named;

public class EditFood extends MfpActivity {
    protected static final int ACTION_SAVE = 990;
    private String alertMsg;
    private Food customFood;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @BindView(2131362391)
    EditText editTxtBrand;
    @BindView(2131362393)
    CustomLocalizedNumberEditText editTxtCalcium;
    @BindView(2131362394)
    CustomLocalizedNumberEditText editTxtCalories;
    @BindView(2131362397)
    CustomLocalizedNumberEditText editTxtCholesterol;
    @BindView(2131362398)
    EditText editTxtDescription;
    @BindView(2131362399)
    CustomLocalizedNumberEditText editTxtDietaryFibers;
    @BindView(2131362404)
    CustomLocalizedNumberEditText editTxtIron;
    @BindView(2131362406)
    CustomLocalizedNumberEditText editTxtMonounsaturatedFat;
    @BindView(2131362408)
    CustomLocalizedNumberEditText editTxtPolyunsaturatedFat;
    @BindView(2131362409)
    CustomLocalizedNumberEditText editTxtPotassium;
    @BindView(2131362410)
    CustomLocalizedNumberEditText editTxtProtein;
    @BindView(2131362414)
    CustomLocalizedNumberEditText editTxtSaturatedFat;
    @BindView(2131362416)
    EditText editTxtServingSize;
    @BindView(2131362418)
    CustomLocalizedNumberEditText editTxtServingsPerContainer;
    @BindView(2131362420)
    CustomLocalizedNumberEditText editTxtSodium;
    @BindView(2131362421)
    CustomLocalizedNumberEditText editTxtSugars;
    @BindView(2131362422)
    CustomLocalizedNumberEditText editTxtTotalCarbohydrates;
    @BindView(2131362423)
    CustomLocalizedNumberEditText editTxtTotalFat;
    @BindView(2131362425)
    CustomLocalizedNumberEditText editTxtTransFat;
    @BindView(2131362427)
    CustomLocalizedNumberEditText editTxtVitaminA;
    @BindView(2131362428)
    CustomLocalizedNumberEditText editTxtVitaminC;
    @BindView(2131362058)
    TextView energyTxt;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private NutritionalValues nutritionValues;
    private String oldServingSize = "";
    private Resources resources;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    @Inject
    @Named("servingSizeValidator")
    Validator validator;
    private ArrayList<String> valueStrings;

    public static Intent newStartIntent(Context context, Food food) {
        return new Intent(context, EditFood.class).putExtra("food", food);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.edit_profile_food);
        component().inject(this);
        this.customFood = (Food) ExtrasUtils.getParcelable(getIntent(), "food", Food.class.getClassLoader());
        addEventListeners();
        this.resources = getResources();
        this.nutritionValues = this.customFood.getNutritionalValues();
        if (this.nutritionValues == null) {
            this.nutritionValues = new NutritionalValues();
            this.nutritionValues.initAsBlank();
        }
        this.editTxtBrand.setHint(R.string.optionalField);
        setupValueStrings();
        loadInfo();
    }

    private void setupValueStrings() {
        ArrayList<String> arrayList = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            if (this.nutritionValues.valueIsNullForNutrientIndex(i)) {
                arrayList.add("");
            } else {
                arrayList.add(Strings.initStringWithFormattedFloat(this.nutritionValues.valueForNutrientIndex(i), 1));
            }
        }
        this.valueStrings = arrayList;
    }

    private void loadInfo() {
        String str;
        try {
            FoodPortion defaultPortion = this.customFood.defaultPortion();
            if (defaultPortion != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(defaultPortion.getIsFraction() ? FoodPortion.GetFraction(defaultPortion.getAmount()) : NumberUtils.localeStringFromFloat(defaultPortion.getAmount()));
                sb.append(" ");
                sb.append(defaultPortion.getDescription());
                str = sb.toString();
            } else {
                str = "";
            }
            this.oldServingSize = str;
            String brand = this.customFood.getBrand();
            EditText editText = this.editTxtBrand;
            if (Strings.isEmpty(brand)) {
                brand = "";
            }
            editText.setText(brand);
            this.editTxtBrand.setSelection(this.editTxtBrand.getText().length());
            this.editTxtDescription.setText(this.customFood.getDescription());
            this.editTxtDescription.setSelection(this.editTxtDescription.getText().length());
            this.editTxtServingSize.setText(this.oldServingSize);
            this.editTxtServingSize.setSelection(this.editTxtServingSize.getText().length());
            this.editTxtServingsPerContainer.setText(NumberUtils.localeStringFromFloat(this.customFood.servingsPerContainer()));
            this.editTxtServingsPerContainer.setSelection(this.editTxtServingsPerContainer.getText().length());
            this.editTxtTotalFat.setText((CharSequence) this.valueStrings.get(1));
            this.editTxtTotalFat.setSelection(this.editTxtTotalFat.getText().length());
            this.editTxtSaturatedFat.setText((CharSequence) this.valueStrings.get(2));
            this.editTxtSaturatedFat.setSelection(this.editTxtSaturatedFat.getText().length());
            this.editTxtPolyunsaturatedFat.setText((CharSequence) this.valueStrings.get(3));
            this.editTxtPolyunsaturatedFat.setSelection(this.editTxtPolyunsaturatedFat.getText().length());
            this.editTxtMonounsaturatedFat.setText((CharSequence) this.valueStrings.get(4));
            this.editTxtMonounsaturatedFat.setSelection(this.editTxtMonounsaturatedFat.getText().length());
            this.editTxtTransFat.setText((CharSequence) this.valueStrings.get(5));
            this.editTxtTransFat.setSelection(this.editTxtTransFat.getText().length());
            this.editTxtCholesterol.setText((CharSequence) this.valueStrings.get(6));
            this.editTxtCholesterol.setSelection(this.editTxtCholesterol.getText().length());
            this.editTxtSodium.setText((CharSequence) this.valueStrings.get(7));
            this.editTxtSodium.setSelection(this.editTxtSodium.getText().length());
            this.editTxtPotassium.setText((CharSequence) this.valueStrings.get(8));
            this.editTxtPotassium.setSelection(this.editTxtPotassium.getText().length());
            this.editTxtTotalCarbohydrates.setText((CharSequence) this.valueStrings.get(9));
            this.editTxtTotalCarbohydrates.setSelection(this.editTxtTotalCarbohydrates.getText().length());
            this.editTxtDietaryFibers.setText((CharSequence) this.valueStrings.get(10));
            this.editTxtDietaryFibers.setSelection(this.editTxtDietaryFibers.getText().length());
            this.editTxtSugars.setText((CharSequence) this.valueStrings.get(11));
            this.editTxtSugars.setSelection(this.editTxtSugars.getText().length());
            this.editTxtProtein.setText((CharSequence) this.valueStrings.get(12));
            this.editTxtProtein.setSelection(this.editTxtProtein.getText().length());
            this.editTxtVitaminA.setText((CharSequence) this.valueStrings.get(13));
            this.editTxtVitaminA.setSelection(this.editTxtVitaminA.getText().length());
            this.editTxtVitaminC.setText((CharSequence) this.valueStrings.get(14));
            this.editTxtVitaminC.setSelection(this.editTxtVitaminC.getText().length());
            this.editTxtCalcium.setText((CharSequence) this.valueStrings.get(15));
            this.editTxtCalcium.setSelection(this.editTxtCalcium.getText().length());
            this.editTxtIron.setText((CharSequence) this.valueStrings.get(16));
            this.editTxtIron.setSelection(this.editTxtIron.getText().length());
            this.energyTxt.setText(((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnitString());
            this.editTxtCalories.setText(((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy((String) this.valueStrings.get(0)));
            this.editTxtCalories.setSelection(this.editTxtCalories.getText().length());
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    private void addEventListeners() {
        this.editTxtBrand.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtBrand.setSelection(EditFood.this.editTxtBrand.getText().length());
                }
            }
        });
        this.editTxtDescription.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtDescription.setSelection(EditFood.this.editTxtDescription.getText().length());
                }
            }
        });
        this.editTxtServingSize.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtServingSize.setSelection(EditFood.this.editTxtServingSize.getText().length());
                }
            }
        });
        this.editTxtServingsPerContainer.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtServingsPerContainer.setSelection(EditFood.this.editTxtServingsPerContainer.getText().length());
                }
            }
        });
        this.editTxtCalories.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtCalories.setSelection(EditFood.this.editTxtCalories.getText().length());
                }
            }
        });
        this.editTxtTotalFat.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtTotalFat.setSelection(EditFood.this.editTxtTotalFat.getText().length());
                }
            }
        });
        this.editTxtSaturatedFat.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtSaturatedFat.setSelection(EditFood.this.editTxtSaturatedFat.getText().length());
                }
            }
        });
        this.editTxtPolyunsaturatedFat.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtPolyunsaturatedFat.setSelection(EditFood.this.editTxtPolyunsaturatedFat.getText().length());
                }
            }
        });
        this.editTxtMonounsaturatedFat.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtMonounsaturatedFat.setSelection(EditFood.this.editTxtMonounsaturatedFat.getText().length());
                }
            }
        });
        this.editTxtTransFat.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtTransFat.setSelection(EditFood.this.editTxtTransFat.getText().length());
                }
            }
        });
        this.editTxtCholesterol.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtCholesterol.setSelection(EditFood.this.editTxtCholesterol.getText().length());
                }
            }
        });
        this.editTxtSodium.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtSodium.setSelection(EditFood.this.editTxtSodium.getText().length());
                }
            }
        });
        this.editTxtPotassium.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtPotassium.setSelection(EditFood.this.editTxtPotassium.getText().length());
                }
            }
        });
        this.editTxtTotalCarbohydrates.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtTotalCarbohydrates.setSelection(EditFood.this.editTxtTotalCarbohydrates.getText().length());
                }
            }
        });
        this.editTxtDietaryFibers.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtDietaryFibers.setSelection(EditFood.this.editTxtDietaryFibers.getText().length());
                }
            }
        });
        this.editTxtSugars.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtSugars.setSelection(EditFood.this.editTxtSugars.getText().length());
                }
            }
        });
        this.editTxtProtein.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtProtein.setSelection(EditFood.this.editTxtProtein.getText().length());
                }
            }
        });
        this.editTxtVitaminA.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtVitaminA.setSelection(EditFood.this.editTxtVitaminA.getText().length());
                }
            }
        });
        this.editTxtVitaminC.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtVitaminC.setSelection(EditFood.this.editTxtVitaminC.getText().length());
                }
            }
        });
        this.editTxtCalcium.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtCalcium.setSelection(EditFood.this.editTxtCalcium.getText().length());
                }
            }
        });
        this.editTxtIron.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditFood.this.editTxtIron.setSelection(EditFood.this.editTxtIron.getText().length());
                }
            }
        });
    }

    private boolean isValidServingSizeName(String str) {
        if (Strings.isEmpty(str)) {
            return false;
        }
        try {
            Double.valueOf(str);
            return false;
        } catch (NumberFormatException unused) {
            return true;
        }
    }

    private boolean validateNutritionalInfoFields() {
        try {
            String strings = Strings.toString(this.editTxtServingSize.getText());
            if (!isValidServingSizeName(strings)) {
                this.alertMsg = this.resources.getString(R.string.enterServingSizeMsg);
                return false;
            } else if (!Strings.equalsIgnoreCase(this.oldServingSize, strings) && NotValidServingSize(strings)) {
                return false;
            } else {
                CustomLocalizedNumberEditText customLocalizedNumberEditText = (CustomLocalizedNumberEditText) findViewById(R.id.editTxtServingsPerContainer);
                String strings2 = Strings.toString(((CustomLocalizedNumberEditText) findViewById(R.id.editTxtCalories)).getText());
                if (Strings.isEmpty(strings2)) {
                    this.alertMsg = ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.ENTER_NUMBER_OF, this.userEnergyService.get());
                    return false;
                } else if (this.editTxtDescription.getText().toString().trim().equals("")) {
                    this.alertMsg = this.resources.getString(R.string.enter_valid_description_msg);
                    return false;
                } else {
                    try {
                        NumberUtils.localeFloatFromString(strings2.trim());
                        try {
                            String strings3 = Strings.toString(customLocalizedNumberEditText.getText());
                            if (NumberUtils.localeFloatFromString(strings3) <= BitmapDescriptorFactory.HUE_RED) {
                                this.alertMsg = this.resources.getString(R.string.ZeroenteredInServingsPerContainerMsg);
                                return false;
                            } else if (this.validator.validate(strings3)) {
                                return true;
                            } else {
                                this.alertMsg = this.resources.getString(R.string.enterServingsPerContainerMsg);
                                return false;
                            }
                        } catch (NumberFormatException e) {
                            this.alertMsg = this.resources.getString(R.string.enterServingsPerContainerMsg);
                            Ln.e(e);
                            return false;
                        }
                    } catch (Exception unused) {
                        this.alertMsg = ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.ENTER_NUMBER_OF, this.userEnergyService.get());
                        return false;
                    }
                }
            }
        } catch (NotFoundException e2) {
            Ln.e(e2);
            return false;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:27|28) */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r10.alertMsg = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c9, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00cb, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00cd, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00cf, code lost:
        com.uacf.core.util.Ln.e(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00d2, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00d3, code lost:
        com.uacf.core.util.Ln.e(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00d6, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00d7, code lost:
        com.uacf.core.util.Ln.e(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00da, code lost:
        return true;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0080 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:42:0x00b0 */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c9 A[ExcHandler: NotFoundException (r11v3 'e' android.content.res.Resources$NotFoundException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00cd A[ExcHandler: PatternSyntaxException (r11v1 'e' java.util.regex.PatternSyntaxException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:40:0x00a3=Splitter:B:40:0x00a3, B:16:0x0057=Splitter:B:16:0x0057} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean NotValidServingSize(java.lang.String r11) {
        /*
            r10 = this;
            r0 = 1
            android.content.res.Resources r1 = r10.resources     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            r2 = 2131887151(0x7f12042f, float:1.94089E38)
            java.lang.String r1 = r1.getString(r2)     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            java.lang.String r2 = "^-?\\d+((\\.\\d+)|(/\\d+)|(\\s\\d+/\\d+)|(\\s\\d+))?"
            java.lang.String r11 = r11.trim()     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            java.lang.String r3 = "\\p{Alpha}"
            r4 = 2
            java.util.regex.Pattern r3 = java.util.regex.Pattern.compile(r3, r4)     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            java.util.regex.Matcher r3 = r3.matcher(r11)     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            boolean r3 = r3.find()     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            if (r3 != 0) goto L_0x0024
            r10.alertMsg = r1     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            return r0
        L_0x0024:
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2, r4)     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            java.util.regex.Matcher r2 = r2.matcher(r11)     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            boolean r3 = r2.find()     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            if (r3 == 0) goto L_0x00c6
            java.lang.String r2 = r2.group()     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            int r3 = r2.length()     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            if (r3 == 0) goto L_0x00c3
            r3 = 1065353216(0x3f800000, float:1.0)
            boolean r11 = r2.equalsIgnoreCase(r11)     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            if (r11 == 0) goto L_0x0048
            r10.alertMsg = r1     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            goto L_0x00c8
        L_0x0048:
            java.lang.String r11 = " "
            java.lang.String[] r11 = r2.split(r11)     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            int r5 = r11.length     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            r6 = 0
            r7 = 0
            if (r5 <= r0) goto L_0x0083
            r2 = r11[r7]     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            r11 = r11[r0]     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            java.lang.Integer.parseInt(r2)     // Catch:{ ParseException -> 0x0080 }
            java.lang.String r2 = "/"
            java.lang.String[] r11 = r11.split(r2)     // Catch:{ ParseException -> 0x0080 }
            int r2 = r11.length     // Catch:{ ParseException -> 0x0080 }
            if (r2 != r4) goto L_0x007d
            r2 = r11[r7]     // Catch:{ ParseException -> 0x0080 }
            float r2 = com.uacf.core.util.NumberUtils.localeFloatFromString(r2)     // Catch:{ ParseException -> 0x0080 }
            r11 = r11[r0]     // Catch:{ ParseException -> 0x0080 }
            float r11 = com.uacf.core.util.NumberUtils.localeFloatFromString(r11)     // Catch:{ ParseException -> 0x0080 }
            int r11 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r11 == 0) goto L_0x007a
            int r11 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r11 != 0) goto L_0x0078
            goto L_0x007a
        L_0x0078:
            r0 = 0
            goto L_0x00c8
        L_0x007a:
            r10.alertMsg = r1     // Catch:{ ParseException -> 0x0080 }
            goto L_0x00c8
        L_0x007d:
            r10.alertMsg = r1     // Catch:{ ParseException -> 0x0080 }
            goto L_0x00c8
        L_0x0080:
            r10.alertMsg = r1     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            goto L_0x00c8
        L_0x0083:
            java.lang.String r11 = "/"
            java.lang.String[] r11 = r2.split(r11)     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            int r4 = r11.length     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            r8 = 0
            if (r4 != r0) goto L_0x00a3
            float r11 = com.uacf.core.util.NumberUtils.localeFloatFromString(r2)     // Catch:{ Exception -> 0x009c }
            double r2 = (double) r11     // Catch:{ Exception -> 0x009c }
            int r11 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r11 > 0) goto L_0x009a
            r10.alertMsg = r1     // Catch:{ Exception -> 0x009c }
            goto L_0x00c8
        L_0x009a:
            r0 = 0
            goto L_0x00c8
        L_0x009c:
            r11 = move-exception
            r10.alertMsg = r1     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            com.uacf.core.util.Ln.e(r11)     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            goto L_0x00c8
        L_0x00a3:
            r2 = r11[r7]     // Catch:{ NumberFormatException -> 0x00b0, PatternSyntaxException -> 0x00cd, NotFoundException -> 0x00c9 }
            float r6 = com.uacf.core.util.NumberUtils.localeFloatFromString(r2)     // Catch:{ NumberFormatException -> 0x00b0, PatternSyntaxException -> 0x00cd, NotFoundException -> 0x00c9 }
            r11 = r11[r0]     // Catch:{ NumberFormatException -> 0x00b0, PatternSyntaxException -> 0x00cd, NotFoundException -> 0x00c9 }
            float r3 = com.uacf.core.util.NumberUtils.localeFloatFromString(r11)     // Catch:{ NumberFormatException -> 0x00b0, PatternSyntaxException -> 0x00cd, NotFoundException -> 0x00c9 }
            goto L_0x00b3
        L_0x00b0:
            r10.alertMsg = r1     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            r7 = 1
        L_0x00b3:
            double r4 = (double) r6     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            int r11 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r11 == 0) goto L_0x00c0
            double r2 = (double) r3     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            int r11 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r11 != 0) goto L_0x00be
            goto L_0x00c0
        L_0x00be:
            r0 = r7
            goto L_0x00c8
        L_0x00c0:
            r10.alertMsg = r1     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            goto L_0x00c8
        L_0x00c3:
            r10.alertMsg = r1     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
            goto L_0x00c8
        L_0x00c6:
            r10.alertMsg = r1     // Catch:{ PatternSyntaxException -> 0x00cd, NumberFormatException -> 0x00cb, NotFoundException -> 0x00c9 }
        L_0x00c8:
            return r0
        L_0x00c9:
            r11 = move-exception
            goto L_0x00cf
        L_0x00cb:
            r11 = move-exception
            goto L_0x00d3
        L_0x00cd:
            r11 = move-exception
            goto L_0x00d7
        L_0x00cf:
            com.uacf.core.util.Ln.e(r11)
            return r0
        L_0x00d3:
            com.uacf.core.util.Ln.e(r11)
            return r0
        L_0x00d7:
            com.uacf.core.util.Ln.e(r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.settings.ui.activity.EditFood.NotValidServingSize(java.lang.String):boolean");
    }

    private void updateFood() {
        try {
            buildCustomFood();
            setResult(-1);
            finish();
        } catch (SQLiteException e) {
            Ln.e(e);
        }
    }

    private void buildCustomFood() {
        try {
            String strings = Strings.toString(((EditText) findViewById(R.id.editTxtDescription)).getText());
            String strings2 = Strings.toString(((EditText) findViewById(R.id.editTxtBrand)).getText());
            String strings3 = Strings.toString(this.editTxtServingSize.getText());
            float localeFloatFromString = NumberUtils.localeFloatFromString(Strings.toString(this.editTxtServingsPerContainer.getText()));
            populateNutritionalValues();
            Food.createCustomFoodWithDescription(strings, strings2, strings3, localeFloatFromString, this.nutritionValues, this.customFood, null, getSession(), (DbConnectionManager) this.dbConnectionManager.get());
        } catch (NumberFormatException e) {
            Ln.e(e);
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:95:0x017d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void populateNutritionalValues() {
        /*
            r4 = this;
            com.myfitnesspal.shared.model.v1.NutritionalValues r0 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r1 = 0
            dagger.Lazy<com.myfitnesspal.shared.service.userdata.UserEnergyService> r2 = r4.userEnergyService     // Catch:{ Exception -> 0x0185 }
            java.lang.Object r2 = r2.get()     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.service.userdata.UserEnergyService r2 = (com.myfitnesspal.shared.service.userdata.UserEnergyService) r2     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r3 = r4.editTxtCalories     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r3 = r3.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r3 = com.uacf.core.util.Strings.toString(r3)     // Catch:{ Exception -> 0x0185 }
            float r2 = r2.getCalories(r3)     // Catch:{ Exception -> 0x0185 }
            r0.setNutrientIndex(r1, r2)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtTotalFat     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            r1 = 0
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x002c }
            goto L_0x002d
        L_0x002c:
            r0 = 0
        L_0x002d:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 1
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtSaturatedFat     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x0042 }
            goto L_0x0043
        L_0x0042:
            r0 = 0
        L_0x0043:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 2
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtPolyunsaturatedFat     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x0058 }
            goto L_0x0059
        L_0x0058:
            r0 = 0
        L_0x0059:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 3
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtMonounsaturatedFat     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x006e }
            goto L_0x006f
        L_0x006e:
            r0 = 0
        L_0x006f:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 4
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtTransFat     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x0084:
            r0 = 0
        L_0x0085:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 5
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtCholesterol     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x009a }
            goto L_0x009b
        L_0x009a:
            r0 = 0
        L_0x009b:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 6
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtSodium     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x00b0 }
            goto L_0x00b1
        L_0x00b0:
            r0 = 0
        L_0x00b1:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 7
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtPotassium     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x00c6 }
            goto L_0x00c7
        L_0x00c6:
            r0 = 0
        L_0x00c7:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 8
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtTotalCarbohydrates     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x00dd }
            goto L_0x00de
        L_0x00dd:
            r0 = 0
        L_0x00de:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 9
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtDietaryFibers     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x00f4 }
            goto L_0x00f5
        L_0x00f4:
            r0 = 0
        L_0x00f5:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 10
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtSugars     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x010b }
            goto L_0x010c
        L_0x010b:
            r0 = 0
        L_0x010c:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 11
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtProtein     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x0122 }
            goto L_0x0123
        L_0x0122:
            r0 = 0
        L_0x0123:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 12
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtVitaminA     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x0139 }
            goto L_0x013a
        L_0x0139:
            r0 = 0
        L_0x013a:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 13
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtVitaminC     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x0150 }
            goto L_0x0151
        L_0x0150:
            r0 = 0
        L_0x0151:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 14
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtCalcium     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r0 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x0167 }
            goto L_0x0168
        L_0x0167:
            r0 = 0
        L_0x0168:
            com.myfitnesspal.shared.model.v1.NutritionalValues r2 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r3 = 15
            r2.setNutrientIndex(r3, r0)     // Catch:{ Exception -> 0x0185 }
            com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText r0 = r4.editTxtIron     // Catch:{ Exception -> 0x0185 }
            android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ Exception -> 0x0185 }
            float r1 = com.uacf.core.util.NumberUtils.localeFloatFromString(r0)     // Catch:{ NumberFormatException -> 0x017d }
        L_0x017d:
            com.myfitnesspal.shared.model.v1.NutritionalValues r0 = r4.nutritionValues     // Catch:{ Exception -> 0x0185 }
            r2 = 16
            r0.setNutrientIndex(r2, r1)     // Catch:{ Exception -> 0x0185 }
            goto L_0x0189
        L_0x0185:
            r0 = move-exception
            com.uacf.core.util.Ln.e(r0)
        L_0x0189:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.settings.ui.activity.EditFood.populateNutritionalValues():void");
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuItemCompat.setShowAsAction(menu.add(0, ACTION_SAVE, 0, R.string.save), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != ACTION_SAVE) {
            return super.onOptionsItemSelected(menuItem);
        }
        try {
            if (validateNutritionalInfoFields()) {
                updateFood();
                scheduleSync();
            } else {
                new MfpAlertDialogBuilder(this).setMessage((CharSequence) this.alertMsg).setCancelable(true).setPositiveButton((int) R.string.dismiss, (OnClickListener) new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).setTitle((int) R.string.alert).show();
            }
        } catch (NotFoundException e) {
            Ln.e(e);
        }
        return true;
    }
}
