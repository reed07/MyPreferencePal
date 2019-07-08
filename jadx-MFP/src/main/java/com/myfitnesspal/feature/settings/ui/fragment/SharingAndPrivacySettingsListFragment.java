package com.myfitnesspal.feature.settings.ui.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.help.ui.activity.EmailSettings;
import com.myfitnesspal.feature.help.ui.activity.NewsFeedSettings;
import com.myfitnesspal.feature.settings.event.DiarySharingSettingChangeEvent;
import com.myfitnesspal.feature.settings.ui.activity.AutoPlaySettings;
import com.myfitnesspal.feature.settings.ui.activity.FacebookSettings;
import com.myfitnesspal.feature.settings.util.DiarySharingSettingsManager;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.PincodeHelper;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class SharingAndPrivacySettingsListFragment extends MfpFragment {
    /* access modifiers changed from: private */
    public int adsSettingIndex;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @Inject
    DiarySharingSettingsManager diarySharingSettingsManager;
    /* access modifiers changed from: private */
    public int facebookSettingsIndex;
    @Inject
    Lazy<GlobalSettingsService> globalSettingsService;
    private ListView listView;
    private OnItemClickListener onListItemClick = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (i == 0) {
                SharingAndPrivacySettingsListFragment.this.goToNewsFeedPrivacySettings();
            } else if (i == 1) {
                SharingAndPrivacySettingsListFragment.this.diarySharingSettingsManager.showChooser(SharingAndPrivacySettingsListFragment.this.getActivity());
            } else if (i == 2) {
                SharingAndPrivacySettingsListFragment.this.getNavigationHelper().withIntent(EmailSettings.newStartIntent(SharingAndPrivacySettingsListFragment.this.getActivity())).startActivity();
            } else if (i == SharingAndPrivacySettingsListFragment.this.facebookSettingsIndex) {
                SharingAndPrivacySettingsListFragment.this.goToFacebookSettings();
            } else if (i == SharingAndPrivacySettingsListFragment.this.pincodeSettingsIndex) {
                SharingAndPrivacySettingsListFragment.this.goToPincode();
            } else if (i == SharingAndPrivacySettingsListFragment.this.pincodeEntryIndex) {
                SharingAndPrivacySettingsListFragment.this.goToPincodeInput();
            } else if (i == SharingAndPrivacySettingsListFragment.this.adsSettingIndex) {
                SharingAndPrivacySettingsListFragment.this.getAnalyticsService().reportEvent(Events.AUTO_PLAY_SETTING_VIEWED);
                SharingAndPrivacySettingsListFragment.this.getNavigationHelper().withIntent(AutoPlaySettings.newStartIntent(SharingAndPrivacySettingsListFragment.this.getActivity())).startActivity();
            }
        }
    };
    /* access modifiers changed from: private */
    public int pincodeEntryIndex;
    /* access modifiers changed from: private */
    public int pincodeSettingsIndex;

    public class SharingAndPrivacySettingsListAdapter extends BaseAdapter {
        private final String[] settings;

        public long getItemId(int i) {
            return (long) i;
        }

        public SharingAndPrivacySettingsListAdapter(String[] strArr) {
            this.settings = strArr;
        }

        public int getCount() {
            return this.settings.length;
        }

        public Object getItem(int i) {
            return this.settings[i];
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(SharingAndPrivacySettingsListFragment.this.getActivity()).inflate(R.layout.settings_item, viewGroup, false);
            }
            TextView textView = (TextView) view.findViewById(R.id.setting_name);
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.enabled);
            TextView textView2 = (TextView) view.findViewById(R.id.selected_setting);
            textView.setText((String) getItem(i));
            if (i == 1) {
                ViewUtils.setVisible(false, checkBox);
                textView2.setText(SharingAndPrivacySettingsListFragment.this.diarySharingSettingsManager.getLocalizedStringForCurrentSetting());
                ViewUtils.setVisible(true, textView2);
            } else if (i == SharingAndPrivacySettingsListFragment.this.pincodeSettingsIndex) {
                ViewUtils.setVisible(true, checkBox);
                checkBox.setClickable(false);
                checkBox.setChecked(SharingAndPrivacySettingsListFragment.this.isPinCodeRequired());
                ViewUtils.setVisible(false, textView2);
            } else if (i == SharingAndPrivacySettingsListFragment.this.pincodeEntryIndex) {
                ViewUtils.setVisible(false, checkBox);
                ViewUtils.setVisible(false, textView2);
                textView.setTextColor(SharingAndPrivacySettingsListFragment.this.getResources().getColor(SharingAndPrivacySettingsListFragment.this.isPinCodeRequired() ? R.color.black : R.color.button_grey_disabled_fg));
            } else {
                checkBox.setVisibility(8);
                textView2.setVisibility(8);
            }
            return view;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.generic_list_fragment, viewGroup, false);
        this.listView = (ListView) inflate.findViewById(R.id.list);
        ArrayList arrayList = new ArrayList();
        setupSettingsItemList(getCountryService().getCurrentCountry().isFacebookSupported(), arrayList);
        this.listView.setAdapter(new SharingAndPrivacySettingsListAdapter((String[]) arrayList.toArray(new String[0])));
        this.listView.setOnItemClickListener(this.onListItemClick);
        return inflate;
    }

    private void setupSettingsItemList(boolean z, ArrayList<String> arrayList) {
        arrayList.add(getResources().getString(R.string.news_feed_privacy_settings));
        arrayList.add(getResources().getString(R.string.diary_sharing_settings));
        arrayList.add(getResources().getString(R.string.email_pref_title));
        if (z) {
            arrayList.add(getResources().getString(R.string.facebook_settings));
            this.facebookSettingsIndex = getLastIndex(arrayList);
        }
        arrayList.add(getResources().getString(R.string.native_ads_settings));
        this.adsSettingIndex = getLastIndex(arrayList);
        arrayList.add(getResources().getString(R.string.require_passcode));
        this.pincodeSettingsIndex = getLastIndex(arrayList);
        arrayList.add(getResources().getString(R.string.edit_passcode));
        this.pincodeEntryIndex = getLastIndex(arrayList);
    }

    private int getLastIndex(List<String> list) {
        return list.size() - 1;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    /* access modifiers changed from: private */
    public void goToPincodeInput() {
        if (isPinCodeRequired()) {
            showPasscodeInputDialog();
        }
    }

    /* access modifiers changed from: private */
    public void goToPincode() {
        if (isPinCodeRequired()) {
            setPincode(null);
        } else {
            showPasscodeInputDialog();
        }
    }

    /* access modifiers changed from: private */
    public void goToFacebookSettings() {
        getNavigationHelper().withIntent(FacebookSettings.newStartIntent(getActivity())).startActivity();
    }

    /* access modifiers changed from: private */
    public void goToNewsFeedPrivacySettings() {
        getNavigationHelper().withIntent(NewsFeedSettings.newStartIntent(getActivity())).startActivity();
    }

    @Subscribe
    public void onDiarySharingSettingChange(DiarySharingSettingChangeEvent diarySharingSettingChangeEvent) {
        ((SharingAndPrivacySettingsListAdapter) this.listView.getAdapter()).notifyDataSetChanged();
        ((MfpActivity) getActivity()).scheduleSync();
    }

    /* access modifiers changed from: private */
    public void setPincode(String str) {
        PincodeHelper.current().updatePincode(str, getSession(), (GlobalSettingsService) this.globalSettingsService.get(), (DbConnectionManager) this.dbConnectionManager.get());
        ((SharingAndPrivacySettingsListAdapter) this.listView.getAdapter()).notifyDataSetChanged();
    }

    private void showPasscodeInputDialog() {
        View inflate = View.inflate(getActivity(), R.layout.passcode_input, null);
        final EditText editText = (EditText) ViewUtils.findById(inflate, 16908291);
        final String pincode = PincodeHelper.current().getPincode(getSession(), (DbConnectionManager) this.dbConnectionManager.get());
        editText.setText(pincode);
        new MfpAlertDialogBuilder(getActivity()).setTitle((int) R.string.edit_passcode).setView(inflate).setPositiveButton(17039370, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String strings = Strings.toString(editText.getText());
                if (!PincodeHelper.isPincodeValid(strings)) {
                    new MfpAlertDialogBuilder(SharingAndPrivacySettingsListFragment.this.getActivity()).setTitle((int) R.string.invalid_input).setMessage((int) R.string.passcode_entry_invalid).setPositiveButton(17039370, (OnClickListener) null).show();
                    SharingAndPrivacySettingsListFragment.this.setPincode(pincode);
                } else {
                    SharingAndPrivacySettingsListFragment.this.setPincode(strings);
                }
                SharingAndPrivacySettingsListFragment.this.getImmHelper().hideSoftInput((View) editText);
            }
        }).setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                SharingAndPrivacySettingsListFragment.this.setPincode(pincode);
                SharingAndPrivacySettingsListFragment.this.getImmHelper().hideSoftInput((View) editText);
            }
        }).show();
    }

    /* access modifiers changed from: private */
    public boolean isPinCodeRequired() {
        return ((GlobalSettingsService) this.globalSettingsService.get()).getRequiresPinCodeOnAppEntry();
    }
}
