package com.myfitnesspal.feature.registration.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.nutrition.ui.view.CustomDatePicker;
import com.myfitnesspal.feature.registration.event.UnderageRegistrationFailureEvent;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.feature.registration.task.RegionLookupTask;
import com.myfitnesspal.feature.registration.task.RegionLookupTask.CompletedEvent;
import com.myfitnesspal.feature.registration.ui.activity.SignUpActivity;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.event.DialogCalendarEvent;
import com.myfitnesspal.shared.model.v1.Country;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.ui.fragment.impl.DatePickerFragment;
import com.myfitnesspal.shared.ui.view.CountrySpinnerAdapter;
import com.myfitnesspal.shared.ui.view.CustomDateDialog.OnDateSetListener;
import com.myfitnesspal.shared.util.AccountUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.Gender;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;

public class SignUpGenderAgeFragment extends SignUpFragmentBase implements OnDateSetListener {
    private static final int COUNTRY_SHORT_NAME_LENGTH = 2;
    private static final String DATE_PICKER_DIALOG_TAG = "birth_date_picker";
    private static final String EXTRA_DATE = "extra_date";
    @BindView(2131361941)
    EditText birthDate;
    @BindView(2131362972)
    View countryAndZip;
    @BindView(2131362222)
    Spinner countrySpinner;
    private Date date;
    @BindView(2131362569)
    RadioButton female;
    @BindView(2131362991)
    RadioButton male;
    @Inject
    SignUpModel model;
    @Inject
    Lazy<SignUpService> signUpService;
    @BindView(2131364231)
    EditText zipcode;

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return Screens.SIGN_UP_GENDER_BIRTHDATE;
    }

    public static SignUpGenderAgeFragment newInstance() {
        return new SignUpGenderAgeFragment();
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return layoutInflater.inflate(R.layout.sign_up_gender_age, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setTitle(R.string.you_info, new Object[0]);
        Date birthday = this.model.getBirthday();
        if (bundle != null) {
            birthday = (Date) BundleUtils.getSerializable(bundle, "extra_date", Date.class.getClassLoader());
        }
        initListeners();
        initViews(birthday);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("extra_date", this.date);
    }

    public void validate() {
        if (this.male.isChecked() || this.female.isChecked()) {
            Date date2 = this.date;
            if (date2 == null) {
                showErrorDialog((int) R.string.select_birth_date);
            } else if (!AccountUtils.validateAge(date2)) {
                postEventAfterResume(new UnderageRegistrationFailureEvent());
            } else {
                String strings = Strings.toString(this.zipcode.getText());
                if (isUnitedStatesSelected()) {
                    if (Strings.isEmpty(strings)) {
                        showErrorDialog((int) R.string.enter_valid_zip);
                        return;
                    } else if (!validateUSZipcodeLength(strings)) {
                        return;
                    }
                }
                String longCountryName = getCountryService().getLongCountryName((Country) this.countrySpinner.getSelectedItem());
                this.model.setCountryName(longCountryName);
                this.model.setZipCode(strings);
                this.model.setBirthday(this.date);
                if (getActivity() instanceof SignUpActivity) {
                    ((SignUpActivity) getActivity()).fetchConsentStatus(longCountryName);
                }
                onValidated();
            }
        } else {
            showErrorDialog((int) R.string.select_gender);
        }
    }

    public void onNavigatedBack() {
        super.onNavigatedBack();
        this.model.setBirthday(this.date);
        this.model.setZipCode(Strings.toString(this.zipcode.getText()));
    }

    private void initViews(Date date2) {
        if (Strings.equals(this.model.getGender(), Gender.Female.toString())) {
            this.female.setChecked(true);
        } else if (Strings.equals(this.model.getGender(), Gender.Male.toString())) {
            this.male.setChecked(true);
        }
        setDate(date2);
        ViewUtils.setVisible(this.countryAndZip);
        setCountrySpinner();
        if (isUnitedStatesSelected()) {
            this.zipcode.setInputType(2);
        }
        if (Strings.notEmpty(this.model.getZipCode())) {
            this.zipcode.setText(this.model.getZipCode());
        }
        updateSelectedCountryInSpinner();
    }

    private void initListeners() {
        this.birthDate.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                SignUpGenderAgeFragment.lambda$initListeners$0(SignUpGenderAgeFragment.this, view);
            }
        });
        $$Lambda$SignUpGenderAgeFragment$UIIbf3bWUv5qQwnLmrFpCIrF0g r0 = new OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SignUpGenderAgeFragment.lambda$initListeners$1(SignUpGenderAgeFragment.this, compoundButton, z);
            }
        };
        this.male.setOnCheckedChangeListener(r0);
        this.female.setOnCheckedChangeListener(r0);
        this.countrySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Country country = (Country) SignUpGenderAgeFragment.this.countrySpinner.getSelectedItem();
                SignUpGenderAgeFragment.this.model.setCountryName(country.getLongName());
                new RegionLookupTask(country.getShortName(), SignUpGenderAgeFragment.this.signUpService).run(SignUpGenderAgeFragment.this.getRunner());
                SignUpGenderAgeFragment.this.zipcode.setInputType(SignUpGenderAgeFragment.this.isUnitedStatesSelected() ? 2 : 1);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                SignUpGenderAgeFragment.this.zipcode.setInputType(1);
            }
        });
    }

    public static /* synthetic */ void lambda$initListeners$0(SignUpGenderAgeFragment signUpGenderAgeFragment, View view) {
        Calendar calendar;
        if (signUpGenderAgeFragment.date != null) {
            calendar = Calendar.getInstance();
            calendar.setTime(signUpGenderAgeFragment.date);
        } else {
            calendar = null;
        }
        signUpGenderAgeFragment.showDateOfBirthDialog(calendar);
    }

    public static /* synthetic */ void lambda$initListeners$1(SignUpGenderAgeFragment signUpGenderAgeFragment, CompoundButton compoundButton, boolean z) {
        if (z) {
            signUpGenderAgeFragment.model.setGender((compoundButton == signUpGenderAgeFragment.female ? Gender.Female : Gender.Male).toString());
        }
    }

    public void onDateSet(CustomDatePicker customDatePicker, int i, int i2, int i3) {
        setDate(i, i2, i3);
    }

    @Subscribe
    public void onDateSetFromDatePicker(DialogCalendarEvent dialogCalendarEvent) {
        Calendar calendar = dialogCalendarEvent.getCalendar();
        setDate(calendar.get(1), calendar.get(2), calendar.get(5));
    }

    @Subscribe
    public void onRegionLookupTaskApiResponseEvent(CompletedEvent completedEvent) {
        if (Strings.notEmpty((String) completedEvent.getResult())) {
            String str = (String) completedEvent.getResult();
            if (Strings.notEmpty(str)) {
                this.model.setRegion(str);
            }
        }
    }

    private void showDateOfBirthDialog(Calendar calendar) {
        showDialogFragment(DatePickerFragment.newInstance(calendar == null ? SignUpModel.getDefaultDate() : calendar.getTime()), DATE_PICKER_DIALOG_TAG);
    }

    private void setDate(int i, int i2, int i3) {
        Calendar instance = Calendar.getInstance();
        instance.set(1, i);
        instance.set(2, i2);
        instance.set(5, i3);
        if (AccountUtils.validateAge(instance)) {
            setDate(instance.getTime());
        } else {
            postEventAfterResume(new UnderageRegistrationFailureEvent());
        }
    }

    private void setDate(Date date2) {
        this.date = date2;
        if (date2 != null) {
            this.birthDate.setText(DateTimeUtils.getMediumLocaleFormattedDate(getActivity(), this.date));
        }
    }

    private boolean validateUSZipcodeLength(String str) {
        if (str.length() == 5) {
            return true;
        }
        getAnalyticsService().reportEvent(Events.SIGNUP_ZIPCODE_VALIDATION_FAILED);
        showErrorDialog((int) R.string.zipcode_length);
        return false;
    }

    private void setCountrySpinner() {
        this.countrySpinner.setAdapter(new CountrySpinnerAdapter(getActivity(), getCountryService(), R.layout.sign_up_spinner_selection, R.layout.sign_up_spinner_item));
        updateSelectedCountryInSpinner();
    }

    private void updateSelectedCountryInSpinner() {
        int i;
        CountryService countryService = getCountryService();
        String countryName = this.model.getCountryName();
        if (Strings.isEmpty(countryName)) {
            this.countrySpinner.setSelection(countryService.getIndexOfCurrentCountry(), true);
            return;
        }
        if (countryName.length() == 2) {
            i = countryService.getIndexOfShortName(countryName);
        } else {
            i = countryService.getIndexOfLongName(countryName);
        }
        if (i == -1) {
            this.model.setCountryName(countryService.getCurrentCountryLongName());
            i = countryService.getIndexOfCurrentCountry();
        }
        this.countrySpinner.setSelection(i);
    }

    /* access modifiers changed from: private */
    public boolean isUnitedStatesSelected() {
        return ((Country) this.countrySpinner.getSelectedItem()).isUnitedStates();
    }
}
