package com.myfitnesspal.feature.settings.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.ui.activity.DiarySettingsActivity;
import com.myfitnesspal.feature.exercise.ui.fragment.ExerciseList;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.premium.ui.activity.SubscriptionStatus;
import com.myfitnesspal.feature.registration.ui.activity.LogoutActivity;
import com.myfitnesspal.feature.settings.model.Setting;
import com.myfitnesspal.feature.settings.ui.activity.EditProfile;
import com.myfitnesspal.feature.settings.ui.activity.LegacyDiarySettingsActivity;
import com.myfitnesspal.feature.settings.ui.activity.NotificationSettingsView;
import com.myfitnesspal.feature.settings.ui.activity.SharingAndPrivacySettings;
import com.myfitnesspal.feature.settings.ui.activity.WeeklyNutritionSettings;
import com.myfitnesspal.shared.service.premium.PremiumAnalyticsHelper;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.view.ViewHolder;
import com.myfitnesspal.shared.util.ConfigUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class SettingsListFragment extends MfpFragment {
    private static final String PREMIUM_FEATURE_ID_SETTINGS_SCREEN_CTA = "settings_screen_cta";
    private OnItemClickListener onListItemClick = new OnItemClickListener() {
        public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
            SettingsListFragment.lambda$new$2(SettingsListFragment.this, adapterView, view, i, j);
        }
    };
    @Inject
    Lazy<PremiumAnalyticsHelper> premiumAnalyticsHelper;
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Lazy<UserWeightService> userWeightService;

    public class SettingsListAdapter extends BaseAdapter {
        private static final int PREMIUM_CTA_TYPE = 1;
        private static final int SETTING_VIEW_TYPE = 0;
        private final List<Setting> settings;

        private class PremiumCtaViewHolder extends ViewHolder<Setting> {
            private final TextView subtext = ((TextView) findById(R.id.subtext));

            protected PremiumCtaViewHolder(View view) {
                super(view);
            }

            public void setData(Setting setting, int i) {
                ((PremiumAnalyticsHelper) SettingsListFragment.this.premiumAnalyticsHelper.get()).reportSettingsScreenPremiumCtaViewed();
                float goalPerWeekWeight = ((UserWeightService) SettingsListFragment.this.userWeightService.get()).getGoalPerWeekWeight();
                Context context = SettingsListFragment.this.getContext();
                if (goalPerWeekWeight == BitmapDescriptorFactory.HUE_RED) {
                    this.subtext.setText(SettingsListFragment.this.getContext().getString(R.string.goals_premium_cta_subtitle_maintain));
                } else {
                    this.subtext.setText(context.getString(goalPerWeekWeight < BitmapDescriptorFactory.HUE_RED ? R.string.goals_premium_cta_subtitle_gain : R.string.goals_premium_cta_subtitle_loss));
                }
            }
        }

        private class SettingViewHolder extends ViewHolder<Setting> {
            private final TextView settingName = ((TextView) findById(R.id.setting_name));

            protected SettingViewHolder(View view) {
                super(view);
            }

            public void setData(Setting setting, int i) {
                this.settingName.setText(setting.getStringResId());
            }
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public SettingsListAdapter(List<Setting> list) {
            this.settings = list;
        }

        public int getCount() {
            return this.settings.size();
        }

        public Setting getItem(int i) {
            return (Setting) this.settings.get(i);
        }

        public int getItemViewType(int i) {
            return getItem(i) == Setting.PremiumCta ? 1 : 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            SettingViewHolder settingViewHolder;
            PremiumCtaViewHolder premiumCtaViewHolder;
            if (getItemViewType(i) == 1) {
                if (view == null || (view.getTag() instanceof SettingViewHolder)) {
                    view = LayoutInflater.from(SettingsListFragment.this.getActivity()).inflate(R.layout.goal_premium_cta, viewGroup, false);
                    premiumCtaViewHolder = new PremiumCtaViewHolder(view);
                    view.setTag(premiumCtaViewHolder);
                } else {
                    premiumCtaViewHolder = (PremiumCtaViewHolder) view.getTag();
                }
                premiumCtaViewHolder.setData(getItem(i), i);
            } else {
                if (view == null || (view.getTag() instanceof PremiumCtaViewHolder)) {
                    view = LayoutInflater.from(SettingsListFragment.this.getActivity()).inflate(R.layout.settings_list_item, viewGroup, false);
                    settingViewHolder = new SettingViewHolder(view);
                    view.setTag(settingViewHolder);
                } else {
                    settingViewHolder = (SettingViewHolder) view.getTag();
                }
                settingViewHolder.setData(getItem(i), i);
            }
            return view;
        }
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Setting[] values;
        View inflate = layoutInflater.inflate(R.layout.generic_list_fragment, viewGroup, false);
        ListView listView = (ListView) inflate.findViewById(R.id.list);
        ArrayList arrayList = new ArrayList();
        for (Setting setting : Setting.values()) {
            if (setting == Setting.PremiumSubscription) {
                if (((PremiumService) this.premiumService.get()).isPremiumSubscribed()) {
                    arrayList.add(setting);
                }
            } else if (setting != Setting.PremiumCta) {
                arrayList.add(setting);
            } else if (ConfigUtils.showSettingsScreenPremiumCta(getConfigService(), (PremiumService) this.premiumService.get())) {
                arrayList.add(setting);
            }
        }
        listView.setAdapter(new SettingsListAdapter(arrayList));
        listView.setOnItemClickListener(this.onListItemClick);
        listView.setFooterDividersEnabled(false);
        listView.setOverscrollFooter(new ColorDrawable(0));
        return inflate;
    }

    public static /* synthetic */ void lambda$new$2(SettingsListFragment settingsListFragment, AdapterView adapterView, View view, int i, long j) {
        Intent intent;
        switch ((Setting) adapterView.getAdapter().getItem(i)) {
            case Profile:
                settingsListFragment.getNavigationHelper().withIntent(EditProfile.newStartIntent(settingsListFragment.getActivity(), settingsListFragment.getSession())).startActivity();
                return;
            case DiarySettings:
                if (ConfigUtils.isAppNavUpdateDiaryEnabled(settingsListFragment.getConfigService())) {
                    intent = DiarySettingsActivity.newStartIntent(settingsListFragment.getActivity(), "settings");
                } else {
                    intent = LegacyDiarySettingsActivity.newStartIntent(settingsListFragment.getActivity());
                }
                settingsListFragment.getNavigationHelper().withIntent(intent).startActivity();
                return;
            case PrivacyPrefs:
                settingsListFragment.getNavigationHelper().withIntent(SharingAndPrivacySettings.newStartIntent(settingsListFragment.getActivity())).startActivity();
                return;
            case MyExercises:
                settingsListFragment.getNavigationHelper().withIntent(ExerciseList.newStartIntent(settingsListFragment.getActivity())).startActivity();
                return;
            case WeeklyNutrition:
                settingsListFragment.getNavigationHelper().withIntent(WeeklyNutritionSettings.newStartIntent(settingsListFragment.getActivity())).startActivity();
                return;
            case NotificationSettings:
                settingsListFragment.goToNotificationPreferences();
                return;
            case PremiumSubscription:
                settingsListFragment.getNavigationHelper().withIntent(SubscriptionStatus.newStartIntent(settingsListFragment.getActivity())).startActivity();
                return;
            case Logout:
                new MfpAlertDialogBuilder(settingsListFragment.getActivity()).setTitle((int) R.string.logout_title).setMessage((int) R.string.logout_message).setPositiveButton((int) R.string.yesBtn, (OnClickListener) new OnClickListener() {
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        SettingsListFragment.this.getNavigationHelper().finishActivityAfterNavigation().withIntent(LogoutActivity.newStartIntent(SettingsListFragment.this.getActivity())).startActivity();
                    }
                }).setNegativeButton((int) R.string.noBtn, (OnClickListener) $$Lambda$SettingsListFragment$LOLJXI9IIDFUiXwFVkatoxwqTI.INSTANCE).show();
                return;
            case PremiumCta:
                settingsListFragment.getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) settingsListFragment.getActivity(), PREMIUM_FEATURE_ID_SETTINGS_SCREEN_CTA)).startActivity();
                return;
            default:
                return;
        }
    }

    private void goToNotificationPreferences() {
        getNavigationHelper().withIntent(NotificationSettingsView.newStartIntent(getActivity())).startActivity();
    }
}
