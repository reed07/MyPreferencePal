package com.myfitnesspal.feature.help.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountAnalyticsHelper;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountActivity;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountActivity.ExportMode;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountPremiumActivity;
import com.myfitnesspal.feature.help.ui.activity.AboutUs;
import com.myfitnesspal.feature.help.ui.activity.DebugLogs;
import com.myfitnesspal.feature.help.ui.activity.Faq;
import com.myfitnesspal.feature.help.ui.activity.SamsungGearHelp;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.registration.ui.activity.TermsOfUseActivity;
import com.myfitnesspal.feature.settings.ui.activity.TroubleshootingActivity;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Settings.App.URLs;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import javax.inject.Inject;

public class HelpListFragment extends MfpFragment {
    @Inject
    Lazy<DeleteAccountAnalyticsHelper> deleteAccountAnalyticsHelper;
    private ListView listView;
    private OnItemClickListener onListItemClick = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Intent intent;
            NavigationHelper navigationHelper = HelpListFragment.this.getNavigationHelper();
            HelpListItem helpListItem = (HelpListItem) adapterView.getItemAtPosition(i);
            FragmentActivity activity = HelpListFragment.this.getActivity();
            switch (AnonymousClass3.$SwitchMap$com$myfitnesspal$feature$help$ui$fragment$HelpListFragment$HelpListItem[helpListItem.ordinal()]) {
                case 1:
                    HelpListFragment.this.getAnalyticsService().reportEvent(Events.FAQ_CLICK);
                    intent = Faq.newStartIntent(activity);
                    break;
                case 2:
                    intent = AboutUs.newStartIntent(activity);
                    break;
                case 3:
                    intent = TermsOfUseActivity.newStartIntent(activity);
                    break;
                case 4:
                    intent = TroubleshootingActivity.newStartIntent(activity);
                    break;
                case 5:
                    intent = SamsungGearHelp.newStartIntent(activity);
                    break;
                case 6:
                    navigationHelper.fromFragment(HelpListFragment.this);
                    intent = DebugLogs.newStartIntent(activity);
                    break;
                case 7:
                    intent = SharedIntents.newUriIntent(URLs.BETA_SIGN_UP);
                    break;
                case 8:
                    ((DeleteAccountAnalyticsHelper) HelpListFragment.this.deleteAccountAnalyticsHelper.get()).reportAccountDeleteStarted("", "settings");
                    if (!((PremiumService) HelpListFragment.this.premiumService.get()).isPremiumSubscribed()) {
                        intent = DeleteAccountActivity.newStartIntent(activity, ExportMode.Disabled, "settings");
                        break;
                    } else {
                        intent = DeleteAccountPremiumActivity.newStartIntent(activity, ExportMode.Disabled, "settings");
                        break;
                    }
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unhandled setting: ");
                    sb.append(helpListItem);
                    throw new IllegalStateException(sb.toString());
            }
            navigationHelper.withIntent(intent).startActivity();
        }
    };
    @Inject
    Lazy<PremiumService> premiumService;

    /* renamed from: com.myfitnesspal.feature.help.ui.fragment.HelpListFragment$3 reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$myfitnesspal$feature$help$ui$fragment$HelpListFragment$HelpListItem = new int[HelpListItem.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.myfitnesspal.feature.help.ui.fragment.HelpListFragment$HelpListItem[] r0 = com.myfitnesspal.feature.help.ui.fragment.HelpListFragment.HelpListItem.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$myfitnesspal$feature$help$ui$fragment$HelpListFragment$HelpListItem = r0
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$help$ui$fragment$HelpListFragment$HelpListItem     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.myfitnesspal.feature.help.ui.fragment.HelpListFragment$HelpListItem r1 = com.myfitnesspal.feature.help.ui.fragment.HelpListFragment.HelpListItem.FAQs     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$help$ui$fragment$HelpListFragment$HelpListItem     // Catch:{ NoSuchFieldError -> 0x001f }
                com.myfitnesspal.feature.help.ui.fragment.HelpListFragment$HelpListItem r1 = com.myfitnesspal.feature.help.ui.fragment.HelpListFragment.HelpListItem.AboutUs     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$help$ui$fragment$HelpListFragment$HelpListItem     // Catch:{ NoSuchFieldError -> 0x002a }
                com.myfitnesspal.feature.help.ui.fragment.HelpListFragment$HelpListItem r1 = com.myfitnesspal.feature.help.ui.fragment.HelpListFragment.HelpListItem.TermsOfService     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$help$ui$fragment$HelpListFragment$HelpListItem     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.myfitnesspal.feature.help.ui.fragment.HelpListFragment$HelpListItem r1 = com.myfitnesspal.feature.help.ui.fragment.HelpListFragment.HelpListItem.Troubleshooting     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$help$ui$fragment$HelpListFragment$HelpListItem     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.myfitnesspal.feature.help.ui.fragment.HelpListFragment$HelpListItem r1 = com.myfitnesspal.feature.help.ui.fragment.HelpListFragment.HelpListItem.SamsungGearHelp     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$help$ui$fragment$HelpListFragment$HelpListItem     // Catch:{ NoSuchFieldError -> 0x004b }
                com.myfitnesspal.feature.help.ui.fragment.HelpListFragment$HelpListItem r1 = com.myfitnesspal.feature.help.ui.fragment.HelpListFragment.HelpListItem.DebugLogs     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$help$ui$fragment$HelpListFragment$HelpListItem     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.myfitnesspal.feature.help.ui.fragment.HelpListFragment$HelpListItem r1 = com.myfitnesspal.feature.help.ui.fragment.HelpListFragment.HelpListItem.JoinBeta     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$help$ui$fragment$HelpListFragment$HelpListItem     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.myfitnesspal.feature.help.ui.fragment.HelpListFragment$HelpListItem r1 = com.myfitnesspal.feature.help.ui.fragment.HelpListFragment.HelpListItem.DeleteAccount     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.help.ui.fragment.HelpListFragment.AnonymousClass3.<clinit>():void");
        }
    }

    private enum HelpListItem {
        FAQs(R.string.faq_title),
        AboutUs(R.string.about_us),
        TermsOfService(R.string.settings_title_terms_of_service),
        Troubleshooting(R.string.troubleShootingHeader),
        SamsungGearHelp(R.string.samsung_gear_help),
        DebugLogs(R.string.debugLogs),
        JoinBeta(R.string.join_our_beta_program),
        DeleteAccount(R.string.delete_account);
        
        /* access modifiers changed from: private */
        public final int titleStringResId;

        private HelpListItem(int i) {
            this.titleStringResId = i;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        HelpListItem[] values;
        View inflate = layoutInflater.inflate(R.layout.generic_list_fragment, viewGroup, false);
        this.listView = (ListView) inflate.findViewById(R.id.list);
        ConfigService configService = getConfigService();
        ArrayList arrayList = new ArrayList();
        for (HelpListItem helpListItem : HelpListItem.values()) {
            if ((helpListItem != HelpListItem.JoinBeta || ConfigUtils.isBetaSignupProgramEnabled(configService)) && ((helpListItem != HelpListItem.SamsungGearHelp || ConfigUtils.isSamsungGearEnabled(configService)) && (helpListItem != HelpListItem.DeleteAccount || ConfigUtils.showDeleteAccount(configService)))) {
                arrayList.add(helpListItem);
            }
        }
        AnonymousClass1 r0 = new ArrayAdapter<HelpListItem>(getActivity(), R.layout.settings_list_item, R.id.setting_name, arrayList) {
            @NonNull
            public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                ((TextView) ViewUtils.findById(view2, R.id.setting_name)).setText(((HelpListItem) getItem(i)).titleStringResId);
                return view2;
            }
        };
        this.listView.setAdapter(r0);
        this.listView.setOnItemClickListener(this.onListItemClick);
        return inflate;
    }
}
