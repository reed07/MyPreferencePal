package com.myfitnesspal.feature.addentry.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.recipes.event.ServingInfoUpdateEvent;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpIngredient;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class EditServingsDialogFragmentV2 extends CustomLayoutBaseDialogFragment {
    List<MfpServingSize> allServings;
    boolean clickedServingSize;
    @Inject
    Lazy<FoodService> foodService;
    String[] items;
    int selectedIndex;
    /* access modifiers changed from: private */
    public MfpServingSize servingSize;
    Spinner servingSizeSpinner;
    AlertDialog servingsDialog;
    String[] suggestedServings;
    ApiResponse<MfpServingSize> suggestedServingsFromService;

    public enum InitialFocus {
        ServingAmount,
        ServingSize
    }

    public static EditServingsDialogFragmentV2 newInstance(MfpIngredient mfpIngredient, double d, MfpServingSize mfpServingSize, Serializable serializable) {
        EditServingsDialogFragmentV2 editServingsDialogFragmentV2 = new EditServingsDialogFragmentV2();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Extras.INGREDIENT, mfpIngredient);
        bundle.putDouble(Extras.NUM_OF_SERVINGS, d);
        bundle.putParcelable(Extras.SERVING_SIZE, mfpServingSize);
        bundle.putSerializable(Extras.CLICKED_SERVING_SIZE, serializable);
        editServingsDialogFragmentV2.setArguments(bundle);
        return editServingsDialogFragmentV2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        final Bundle arguments = getArguments();
        MfpIngredient mfpIngredient = (MfpIngredient) BundleUtils.getParcelable(arguments, Extras.INGREDIENT, MfpIngredient.class.getClassLoader());
        double d = arguments.getDouble(Extras.NUM_OF_SERVINGS);
        this.clickedServingSize = arguments.getSerializable(Extras.CLICKED_SERVING_SIZE) == InitialFocus.ServingSize;
        this.servingSize = (MfpServingSize) BundleUtils.getParcelable(arguments, Extras.SERVING_SIZE, MfpServingSize.class.getClassLoader());
        this.allServings = new ArrayList();
        View inflate = LayoutInflater.from(getDialogContextThemeWrapper()).inflate(R.layout.edit_food_entry_serving, null);
        this.servingSizeSpinner = (Spinner) inflate.findViewById(R.id.serving_size);
        this.servingSizeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                EditServingsDialogFragmentV2.this.selectedIndex = i;
                arguments.putInt(Extras.WEIGHT_OPTION_INDEX, i);
            }
        });
        this.items = new String[0];
        final MfpFood sanitizedFood = mfpIngredient.getSanitizedFood();
        if (CollectionUtils.notEmpty((Collection<?>) sanitizedFood.getServingSizes())) {
            List servingSizes = sanitizedFood.getServingSizes();
            this.items = new String[servingSizes.size()];
            for (int i = 0; i < servingSizes.size(); i++) {
                this.allServings.add(servingSizes.get(i));
                String[] strArr = this.items;
                StringBuilder sb = new StringBuilder();
                sb.append(((MfpServingSize) servingSizes.get(i)).getValue());
                sb.append(" ");
                sb.append(((MfpServingSize) servingSizes.get(i)).getUnit());
                strArr[i] = sb.toString();
                if (Strings.equals(((MfpServingSize) servingSizes.get(i)).getUnit(), this.servingSize.getUnit()) && ((MfpServingSize) servingSizes.get(i)).getValue().equals(this.servingSize.getValue())) {
                    this.selectedIndex = i;
                }
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(getDialogContextThemeWrapper(), R.layout.alert_dialog_spinner_item, this.items);
            arrayAdapter.setDropDownViewResource(R.layout.alert_dialog_spinner_dropdown_item);
            this.servingSizeSpinner.setAdapter(arrayAdapter);
            this.servingSizeSpinner.setEnabled(false);
            if (this.servingSizeSpinner.getSelectedView() != null) {
                this.servingSizeSpinner.getSelectedView().setEnabled(false);
            }
            ((FoodService) this.foodService.get()).getSuggestedServingsAsync(sanitizedFood.getId(), sanitizedFood.getVersion(), new Function1<ApiResponse<MfpServingSize>>() {
                public void execute(ApiResponse<MfpServingSize> apiResponse) {
                    ArrayAdapter arrayAdapter;
                    List servingSizes = sanitizedFood.getServingSizes();
                    EditServingsDialogFragmentV2.this.items = new String[servingSizes.size()];
                    EditServingsDialogFragmentV2.this.allServings.clear();
                    for (int i = 0; i < servingSizes.size(); i++) {
                        EditServingsDialogFragmentV2.this.allServings.add(servingSizes.get(i));
                        String[] strArr = EditServingsDialogFragmentV2.this.items;
                        StringBuilder sb = new StringBuilder();
                        sb.append(((MfpServingSize) servingSizes.get(i)).getValue());
                        sb.append(" ");
                        sb.append(((MfpServingSize) servingSizes.get(i)).getUnit());
                        strArr[i] = sb.toString();
                        if (Strings.equals(((MfpServingSize) servingSizes.get(i)).getUnit(), EditServingsDialogFragmentV2.this.servingSize.getUnit()) && ((MfpServingSize) servingSizes.get(i)).getValue().equals(EditServingsDialogFragmentV2.this.servingSize.getValue())) {
                            EditServingsDialogFragmentV2.this.selectedIndex = i;
                        }
                    }
                    if (apiResponse != null && CollectionUtils.notEmpty((Collection<?>) apiResponse.getItems())) {
                        EditServingsDialogFragmentV2 editServingsDialogFragmentV2 = EditServingsDialogFragmentV2.this;
                        editServingsDialogFragmentV2.suggestedServingsFromService = apiResponse;
                        editServingsDialogFragmentV2.suggestedServings = (String[]) Arrays.copyOf(editServingsDialogFragmentV2.items, servingSizes.size() + apiResponse.getItems().size());
                        EditServingsDialogFragmentV2.this.suggestedServings[servingSizes.size()] = null;
                        for (int size = servingSizes.size(); size < servingSizes.size() + apiResponse.getItems().size(); size++) {
                            EditServingsDialogFragmentV2.this.allServings.add(apiResponse.getItems().get(size - servingSizes.size()));
                            EditServingsDialogFragmentV2.this.suggestedServings[size] = ((MfpServingSize) apiResponse.getItems().get(size - servingSizes.size())).descriptionWithAmount();
                        }
                    }
                    if (EditServingsDialogFragmentV2.this.suggestedServings != null) {
                        arrayAdapter = new ArrayAdapter(EditServingsDialogFragmentV2.this.getDialogContextThemeWrapper(), R.layout.alert_dialog_spinner_item, EditServingsDialogFragmentV2.this.suggestedServings);
                    } else {
                        arrayAdapter = new ArrayAdapter(EditServingsDialogFragmentV2.this.getDialogContextThemeWrapper(), R.layout.alert_dialog_spinner_item, EditServingsDialogFragmentV2.this.items);
                    }
                    arrayAdapter.setDropDownViewResource(R.layout.alert_dialog_spinner_dropdown_item);
                    EditServingsDialogFragmentV2.this.servingSizeSpinner.setAdapter(arrayAdapter);
                    EditServingsDialogFragmentV2.this.servingSizeSpinner.setEnabled(true);
                    if (EditServingsDialogFragmentV2.this.servingSizeSpinner.getSelectedView() != null) {
                        EditServingsDialogFragmentV2.this.servingSizeSpinner.getSelectedView().setEnabled(true);
                    }
                }
            });
        }
        int i2 = this.selectedIndex;
        if (i2 != -1) {
            this.servingSizeSpinner.setSelection(i2);
        }
        final CustomLocalizedNumberEditText customLocalizedNumberEditText = (CustomLocalizedNumberEditText) inflate.findViewById(R.id.numOfServings);
        customLocalizedNumberEditText.setText(String.format("%.2f", new Object[]{Double.valueOf(d)}));
        this.servingsDialog = new MfpAlertDialogBuilder(getDialogContextThemeWrapper()).setPositiveButton((int) R.string.save, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                double tryParseDouble = NumberUtils.tryParseDouble(Strings.toString(customLocalizedNumberEditText.getText()), 1.0d);
                if (EditServingsDialogFragmentV2.this.selectedIndex > EditServingsDialogFragmentV2.this.items.length - 1) {
                    EditServingsDialogFragmentV2.this.messageBus.post(new ServingInfoUpdateEvent(Double.valueOf(tryParseDouble), (MfpServingSize) EditServingsDialogFragmentV2.this.allServings.get(EditServingsDialogFragmentV2.this.selectedIndex)));
                } else {
                    EditServingsDialogFragmentV2.this.messageBus.post(new ServingInfoUpdateEvent(Double.valueOf(tryParseDouble), EditServingsDialogFragmentV2.this.selectedIndex));
                }
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                customLocalizedNumberEditText.clearFocus();
            }
        }).setTitle((int) R.string.how_much).setView(inflate).create();
        customLocalizedNumberEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    CustomLocalizedNumberEditText customLocalizedNumberEditText = customLocalizedNumberEditText;
                    customLocalizedNumberEditText.setSelection(0, customLocalizedNumberEditText.getText().length());
                }
            }
        });
        this.servingsDialog.getWindow().setSoftInputMode(this.clickedServingSize ? 3 : 5);
        return this.servingsDialog;
    }

    public void onResume() {
        super.onResume();
        openSpinnerIfNeeded();
    }

    private void openSpinnerIfNeeded() {
        if (this.clickedServingSize) {
            new Handler().post(new Runnable() {
                public void run() {
                    if (EditServingsDialogFragmentV2.this.servingsDialog != null) {
                        EditServingsDialogFragmentV2.this.servingSizeSpinner.requestFocus();
                        EditServingsDialogFragmentV2.this.servingSizeSpinner.performClick();
                    }
                }
            });
        }
    }
}
