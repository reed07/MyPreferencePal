package com.myfitnesspal.feature.addentry.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.event.EditServingsDialogCloseEvent;
import com.myfitnesspal.feature.addentry.util.EditableServingV2;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.FragmentUtil;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import javax.inject.Inject;

public class EditV2SearchServingsDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String SHOW_KEYBOARD = "show_keyboard";
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @Inject
    Lazy<CountryService> countryService;
    List<MfpServingSize> currentServingSizes;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MfpFood food;
    @Inject
    Lazy<FoodService> foodService;
    private EditText numOfServingsView;
    private int selectedIndex;
    private Spinner servingSizeSpinner;
    private AlertDialog servingsDialog;
    private String[] suggestedServings;

    public static EditV2SearchServingsDialogFragment newInstance() {
        return newInstance(true);
    }

    public static EditV2SearchServingsDialogFragment newInstance(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(SHOW_KEYBOARD, z);
        EditV2SearchServingsDialogFragment editV2SearchServingsDialogFragment = new EditV2SearchServingsDialogFragment();
        editV2SearchServingsDialogFragment.setArguments(bundle);
        return editV2SearchServingsDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        EditableServingV2 editableServingV2 = (EditableServingV2) FragmentUtil.getTargetFragmentOrParentActivity(this, EditableServingV2.class);
        this.food = editableServingV2.getFood();
        View inflate = LayoutInflater.from(getDialogContextThemeWrapper()).inflate(R.layout.edit_food_entry_serving, null);
        this.servingSizeSpinner = (Spinner) inflate.findViewById(R.id.serving_size);
        List servingSizes = editableServingV2.getFood().getServingSizes();
        String[] strArr = new String[servingSizes.size()];
        for (int i = 0; i < servingSizes.size(); i++) {
            MfpServingSize mfpServingSize = (MfpServingSize) servingSizes.get(i);
            strArr[i] = mfpServingSize.descriptionWithAmount();
            MfpServingSize servingSize = editableServingV2.getServingSize();
            if (servingSize != null && Strings.equalsIgnoreCase(mfpServingSize.descriptionWithAmount(), servingSize.descriptionWithAmount())) {
                this.selectedIndex = i;
                this.servingSizeSpinner.setSelection(i);
            }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getDialogContextThemeWrapper(), R.layout.alert_dialog_spinner_item, strArr);
        arrayAdapter.setDropDownViewResource(R.layout.alert_dialog_spinner_dropdown_item);
        this.servingSizeSpinner.setAdapter(arrayAdapter);
        int i2 = this.selectedIndex;
        if (i2 != -1) {
            this.servingSizeSpinner.setSelection(i2);
        }
        this.servingSizeSpinner.setEnabled(false);
        if (this.servingSizeSpinner.getSelectedView() != null) {
            this.servingSizeSpinner.getSelectedView().setEnabled(false);
        }
        this.disposable.add(((FoodService) this.foodService.get()).fetchSuggestedServings(editableServingV2.getFood().getId(), editableServingV2.getFood().getVersion()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doFinally(new Action() {
            public final void run() {
                EditV2SearchServingsDialogFragment.lambda$onCreateDialog$0(EditV2SearchServingsDialogFragment.this);
            }
        }).subscribe((Consumer<? super T>) new Consumer(editableServingV2) {
            private final /* synthetic */ EditableServingV2 f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                EditV2SearchServingsDialogFragment.lambda$onCreateDialog$1(EditV2SearchServingsDialogFragment.this, this.f$1, (List) obj);
            }
        }));
        this.numOfServingsView = (EditText) inflate.findViewById(R.id.numOfServings);
        this.numOfServingsView.setText(NumberUtils.localeStringFromFloat(editableServingV2.getServings()));
        this.servingsDialog = new MfpAlertDialogBuilder(getDialogContextThemeWrapper()).setPositiveButton((int) R.string.save, (OnClickListener) new OnClickListener(editableServingV2, servingSizes) {
            private final /* synthetic */ EditableServingV2 f$1;
            private final /* synthetic */ List f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                EditV2SearchServingsDialogFragment.lambda$onCreateDialog$2(EditV2SearchServingsDialogFragment.this, this.f$1, this.f$2, dialogInterface, i);
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener(editableServingV2) {
            private final /* synthetic */ EditableServingV2 f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                EditV2SearchServingsDialogFragment.lambda$onCreateDialog$3(EditV2SearchServingsDialogFragment.this, this.f$1, dialogInterface, i);
            }
        }).setOnCancelListener(new OnCancelListener() {
            public final void onCancel(DialogInterface dialogInterface) {
                EditV2SearchServingsDialogFragment.lambda$onCreateDialog$4(EditV2SearchServingsDialogFragment.this, dialogInterface);
            }
        }).setTitle((int) R.string.how_much).setView(inflate).create();
        this.numOfServingsView.setOnFocusChangeListener(new OnFocusChangeListener() {
            public final void onFocusChange(View view, boolean z) {
                EditV2SearchServingsDialogFragment.lambda$onCreateDialog$5(EditV2SearchServingsDialogFragment.this, view, z);
            }
        });
        return this.servingsDialog;
    }

    public static /* synthetic */ void lambda$onCreateDialog$0(EditV2SearchServingsDialogFragment editV2SearchServingsDialogFragment) throws Exception {
        Spinner spinner = editV2SearchServingsDialogFragment.servingSizeSpinner;
        if (spinner != null) {
            spinner.setEnabled(true);
            if (editV2SearchServingsDialogFragment.servingSizeSpinner.getSelectedView() != null) {
                editV2SearchServingsDialogFragment.servingSizeSpinner.getSelectedView().setEnabled(true);
            }
        }
    }

    public static /* synthetic */ void lambda$onCreateDialog$1(EditV2SearchServingsDialogFragment editV2SearchServingsDialogFragment, EditableServingV2 editableServingV2, List list) throws Exception {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(editableServingV2.getFood().getServingSizes());
        linkedHashSet.addAll(list);
        editV2SearchServingsDialogFragment.currentServingSizes = new ArrayList(linkedHashSet);
        ArrayList arrayList = new ArrayList();
        for (MfpServingSize descriptionWithAmount : editV2SearchServingsDialogFragment.currentServingSizes) {
            arrayList.add(descriptionWithAmount.descriptionWithAmount());
        }
        int i = 0;
        editV2SearchServingsDialogFragment.suggestedServings = (String[]) arrayList.toArray(new String[0]);
        ArrayAdapter arrayAdapter = new ArrayAdapter(editV2SearchServingsDialogFragment.getDialogContextThemeWrapper(), R.layout.alert_dialog_spinner_item, editV2SearchServingsDialogFragment.suggestedServings);
        arrayAdapter.setDropDownViewResource(R.layout.alert_dialog_spinner_dropdown_item);
        editV2SearchServingsDialogFragment.servingSizeSpinner.setAdapter(arrayAdapter);
        if (editableServingV2.getServingSize() != null) {
            while (true) {
                String[] strArr = editV2SearchServingsDialogFragment.suggestedServings;
                if (i < strArr.length) {
                    if (Strings.equalsIgnoreCase(strArr[i], editableServingV2.getServingSize().descriptionWithAmount())) {
                        editV2SearchServingsDialogFragment.selectedIndex = i;
                        editV2SearchServingsDialogFragment.servingSizeSpinner.setSelection(i);
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static /* synthetic */ void lambda$onCreateDialog$2(EditV2SearchServingsDialogFragment editV2SearchServingsDialogFragment, EditableServingV2 editableServingV2, List list, DialogInterface dialogInterface, int i) {
        try {
            int selectedItemPosition = editV2SearchServingsDialogFragment.servingSizeSpinner.getSelectedItemPosition();
            if (editableServingV2 != null) {
                float localeFloatFromString = NumberUtils.localeFloatFromString(NumberUtils.normalizeInputString(Strings.trimmed((Object) editV2SearchServingsDialogFragment.numOfServingsView.getText())));
                if (selectedItemPosition > CollectionUtils.size((Collection<?>) editableServingV2.getFood().getServingSizes()) - 1) {
                    MfpServingSize mfpServingSize = (MfpServingSize) editV2SearchServingsDialogFragment.currentServingSizes.get(selectedItemPosition);
                    editableServingV2.setServingSize(mfpServingSize);
                    editableServingV2.setServingSizeIndex(selectedItemPosition);
                    editableServingV2.populateFoodData(localeFloatFromString);
                    editV2SearchServingsDialogFragment.messageBus.post(new EditServingsDialogCloseEvent(mfpServingSize, localeFloatFromString, true, selectedItemPosition));
                } else {
                    editableServingV2.setServingSize((MfpServingSize) list.get(selectedItemPosition));
                    editableServingV2.setServingSizeIndex(selectedItemPosition);
                    editableServingV2.populateFoodData(localeFloatFromString);
                    dialogInterface.cancel();
                    editV2SearchServingsDialogFragment.messageBus.post(new EditServingsDialogCloseEvent((MfpServingSize) list.get(selectedItemPosition), localeFloatFromString, false, selectedItemPosition));
                    editV2SearchServingsDialogFragment.numOfServingsView.clearFocus();
                    editableServingV2.hideSoftInput();
                }
            }
            editV2SearchServingsDialogFragment.reportServingSizeLookupEvent(selectedItemPosition);
        } catch (NumberFormatException | ParseException e) {
            dialogInterface.cancel();
            if (editableServingV2 != null) {
                editableServingV2.showDialogFragment(Dialogs.INVALID_INPUT);
            }
            Ln.e(e);
        } catch (Exception e2) {
            Ln.e(e2);
        }
    }

    public static /* synthetic */ void lambda$onCreateDialog$3(EditV2SearchServingsDialogFragment editV2SearchServingsDialogFragment, EditableServingV2 editableServingV2, DialogInterface dialogInterface, int i) {
        try {
            dialogInterface.cancel();
            editV2SearchServingsDialogFragment.numOfServingsView.clearFocus();
            if (editableServingV2 != null) {
                editableServingV2.hideSoftInput();
            }
            editV2SearchServingsDialogFragment.reportServingSizeLookupEvent(-1);
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public static /* synthetic */ void lambda$onCreateDialog$4(EditV2SearchServingsDialogFragment editV2SearchServingsDialogFragment, DialogInterface dialogInterface) {
        editV2SearchServingsDialogFragment.numOfServingsView.clearFocus();
        editV2SearchServingsDialogFragment.reportServingSizeLookupEvent(-1);
    }

    public static /* synthetic */ void lambda$onCreateDialog$5(EditV2SearchServingsDialogFragment editV2SearchServingsDialogFragment, View view, boolean z) {
        if (z) {
            EditText editText = editV2SearchServingsDialogFragment.numOfServingsView;
            editText.setSelection(0, editText.getText().length());
        }
    }

    public void onResume() {
        super.onResume();
        if (BundleUtils.getBoolean(getArguments(), SHOW_KEYBOARD)) {
            this.servingsDialog.getWindow().setSoftInputMode(5);
            EditText editText = this.numOfServingsView;
            if (editText != null) {
                editText.requestFocus();
                return;
            }
            return;
        }
        new Handler().post(new Runnable() {
            public final void run() {
                EditV2SearchServingsDialogFragment.this.servingSizeSpinner.performClick();
            }
        });
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.disposable.dispose();
        this.servingSizeSpinner = null;
        this.numOfServingsView = null;
    }

    private void reportServingSizeLookupEvent(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("locale", ((CountryService) this.countryService.get()).getCurrentLocaleIdentifierForV2());
        hashMap.put("food_id", Strings.toString(this.food.getId()));
        hashMap.put(Attributes.FOOD_VERSION_ID, Strings.toString(this.food.getVersion()));
        hashMap.put("serving_size_index", Strings.toString(Integer.valueOf(i)));
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEventAndReport(Events.SERVING_SIZE_LOOKUP, hashMap);
    }
}
