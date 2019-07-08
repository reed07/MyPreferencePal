package com.myfitnesspal.feature.settings.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class HeightDialogFragment extends CustomLayoutBaseDialogFragment {
    @Inject
    Lazy<Session> session;
    @Inject
    UserHeightService userHeightService;

    public static class HeightChangedEvent {
    }

    public static HeightDialogFragment newInstance() {
        return new HeightDialogFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        final ContextThemeWrapper dialogContextThemeWrapper = getDialogContextThemeWrapper();
        View inflate = LayoutInflater.from(dialogContextThemeWrapper).inflate(R.layout.edit_height_dialog, null);
        final CustomLocalizedNumberEditText customLocalizedNumberEditText = (CustomLocalizedNumberEditText) inflate.findViewById(R.id.txtHeight);
        TextView textView = (TextView) inflate.findViewById(R.id.txtUnit);
        final NumberPicker numberPicker = (NumberPicker) inflate.findViewById(R.id.picker1);
        final NumberPicker numberPicker2 = (NumberPicker) inflate.findViewById(R.id.picker2);
        View findViewById = inflate.findViewById(R.id.picker_container);
        final Length userCurrentHeightUnit = this.userHeightService.getUserCurrentHeightUnit();
        String[] currentHeight = this.userHeightService.getCurrentHeight();
        if (userCurrentHeightUnit == Length.CENTIMETERS) {
            customLocalizedNumberEditText.setText(currentHeight[0]);
            customLocalizedNumberEditText.setSelection(customLocalizedNumberEditText.getText().length());
            textView.setText(dialogContextThemeWrapper.getString(R.string.cm));
            customLocalizedNumberEditText.setVisibility(0);
            findViewById.setVisibility(8);
        } else {
            int[] intArray = dialogContextThemeWrapper.getResources().getIntArray(R.array.heightFeetArray);
            int[] intArray2 = dialogContextThemeWrapper.getResources().getIntArray(R.array.heightInchesArray);
            setNumberPickerParams(numberPicker, intArray);
            setNumberPickerParams(numberPicker2, intArray2);
            int tryParseInt = NumberUtils.tryParseInt(currentHeight[0]);
            if (tryParseInt < 0 || tryParseInt > 5) {
                tryParseInt = intArray[0];
            }
            int tryParseInt2 = NumberUtils.tryParseInt(currentHeight[1]);
            if (tryParseInt2 < 0 || tryParseInt2 > 11) {
                tryParseInt2 = intArray2[0];
            }
            numberPicker.setValue(tryParseInt);
            numberPicker2.setValue(tryParseInt2);
            textView.setVisibility(8);
            customLocalizedNumberEditText.setVisibility(8);
        }
        MfpAlertDialogBuilder view = new MfpAlertDialogBuilder(dialogContextThemeWrapper).setTitle((int) R.string.height_header).setView(inflate);
        AnonymousClass1 r0 = new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                int i2;
                String[] strArr;
                if (userCurrentHeightUnit == Length.CENTIMETERS) {
                    strArr = new String[]{Strings.toString(customLocalizedNumberEditText.getText()), "0"};
                    i2 = R.string.enter_valid_height;
                } else {
                    strArr = new String[]{Strings.toString(Integer.valueOf(numberPicker.getValue())), Strings.toString(Integer.valueOf(numberPicker2.getValue()))};
                    i2 = R.string.select_height;
                }
                if (HeightDialogFragment.this.userHeightService.setHeight(strArr, userCurrentHeightUnit)) {
                    ((Session) HeightDialogFragment.this.session.get()).getUser().updatePropertyNamed(Basic.HEIGHT_IN_INCHES);
                    HeightDialogFragment.this.messageBus.post(new HeightChangedEvent());
                    return;
                }
                new MfpAlertDialogBuilder(dialogContextThemeWrapper).setMessage((CharSequence) dialogContextThemeWrapper.getString(i2)).setCancelable(true).setPositiveButton(dialogContextThemeWrapper.getResources().getText(R.string.dismiss), (OnClickListener) $$Lambda$HeightDialogFragment$1$iPIraImVWzck_5DT8uHOlVCjsQ.INSTANCE).setTitle(dialogContextThemeWrapper.getResources().getText(R.string.alert)).show();
            }
        };
        return view.setPositiveButton((int) R.string.setBtn, (OnClickListener) r0).setNegativeButton((int) R.string.cancel, (OnClickListener) null).create();
    }

    private void setNumberPickerParams(NumberPicker numberPicker, int[] iArr) {
        numberPicker.setMinValue(iArr[0]);
        numberPicker.setMaxValue(iArr[iArr.length - 1]);
        ViewUtils.setPickerCommonProperties(numberPicker);
    }
}
