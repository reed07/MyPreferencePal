package com.myfitnesspal.feature.settings.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.event.DiarySharingSettingChangeEvent;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.constants.DiarySharingSetting;
import com.myfitnesspal.shared.model.CheckableListItem;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.squareup.otto.Bus;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class DiarySharingSettingsManager {
    private final Context context;
    private final Bus messageBus;
    private final Lazy<Session> session;

    @Inject
    public DiarySharingSettingsManager(Context context2, Lazy<Session> lazy, Bus bus) {
        this.context = context2;
        this.session = lazy;
        this.messageBus = bus;
    }

    public void showChooser(Activity activity) {
        List asList = Arrays.asList(new String[]{this.context.getString(R.string.diary_sharing_private), this.context.getString(R.string.diary_sharing_public), this.context.getString(R.string.diary_sharing_friends_only), this.context.getString(R.string.diary_sharing_locked_with_key)});
        List asList2 = Arrays.asList(new DiarySharingSetting[]{DiarySharingSetting.Private, DiarySharingSetting.Public, DiarySharingSetting.FriendsOnly, DiarySharingSetting.Password});
        UserProfile profile = ((Session) this.session.get()).getUser().getProfile();
        int indexOf = asList2.indexOf(profile.getDiarySharingSetting());
        int size = CollectionUtils.size((Collection<?>) asList);
        ArrayList arrayList = new ArrayList(size);
        int i = 0;
        while (i < size) {
            arrayList.add(new CheckableListItem((String) asList.get(i), indexOf == i));
            i++;
        }
        new MfpAlertDialogBuilder(activity).setSingleChoiceItems((List<? extends CheckableListItem>) arrayList, (OnItemClickListener) new OnItemClickListener(asList2, profile, activity) {
            private final /* synthetic */ List f$1;
            private final /* synthetic */ UserProfile f$2;
            private final /* synthetic */ Activity f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                DiarySharingSettingsManager.lambda$showChooser$2(DiarySharingSettingsManager.this, this.f$1, this.f$2, this.f$3, adapterView, view, i, j);
            }
        }).setTitle((int) R.string.diary_sharing_header).show();
    }

    public static /* synthetic */ void lambda$showChooser$2(DiarySharingSettingsManager diarySharingSettingsManager, List list, UserProfile userProfile, Activity activity, AdapterView adapterView, View view, int i, long j) {
        DiarySharingSetting diarySharingSetting = (DiarySharingSetting) list.get(i);
        if (diarySharingSetting == DiarySharingSetting.Password) {
            View inflate = View.inflate(diarySharingSettingsManager.context, R.layout.diary_password_dialog, null);
            TextView textView = (TextView) ViewUtils.findById(inflate, R.id.diarySharingPassword);
            textView.setText(userProfile.getDiaryPassword());
            textView.setSelectAllOnFocus(true);
            MfpAlertDialogBuilder view2 = new MfpAlertDialogBuilder(activity).setTitle((int) R.string.diary_password).setView(inflate);
            $$Lambda$DiarySharingSettingsManager$p1IfjB0RUKrYGiJHkrH4ZyyldM r0 = new OnClickListener(textView, userProfile, diarySharingSetting, activity) {
                private final /* synthetic */ TextView f$1;
                private final /* synthetic */ UserProfile f$2;
                private final /* synthetic */ DiarySharingSetting f$3;
                private final /* synthetic */ Activity f$4;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    DiarySharingSettingsManager.lambda$null$1(DiarySharingSettingsManager.this, this.f$1, this.f$2, this.f$3, this.f$4, dialogInterface, i);
                }
            };
            view2.setPositiveButton(17039370, (OnClickListener) r0).show();
            return;
        }
        diarySharingSettingsManager.commitSharingChange(diarySharingSetting);
    }

    public static /* synthetic */ void lambda$null$1(DiarySharingSettingsManager diarySharingSettingsManager, TextView textView, UserProfile userProfile, DiarySharingSetting diarySharingSetting, Activity activity, DialogInterface dialogInterface, int i) {
        String strings = Strings.toString(textView.getText());
        if (Strings.notEmpty(strings)) {
            userProfile.setDiaryPassword(strings);
            diarySharingSettingsManager.commitSharingChange(diarySharingSetting);
        } else if (!activity.isFinishing()) {
            new MfpAlertDialogBuilder(activity).setMessage((CharSequence) activity.getString(R.string.diary_password_cannot_be_blank)).setCancelable(true).setPositiveButton(activity.getResources().getText(R.string.dismiss), (OnClickListener) $$Lambda$DiarySharingSettingsManager$kchCIj8NuxlDneyEIIgkIlU3Ask.INSTANCE).setTitle(activity.getResources().getText(R.string.alert)).show();
        }
    }

    private void commitSharingChange(DiarySharingSetting diarySharingSetting) {
        User user = ((Session) this.session.get()).getUser();
        user.getProfile().setDiarySharingSetting(diarySharingSetting);
        this.messageBus.post(new DiarySharingSettingChangeEvent());
        user.updatePropertyNamed(Basic.DIARY_PRIVACY);
        user.updatePropertyNamed(Basic.DIARY_PASSWORD);
    }

    public DiarySharingSetting getCurrentUserSetting() {
        return ((Session) this.session.get()).getUser().getProfile().getDiarySharingSetting();
    }

    public String getLocalizedStringForCurrentSetting() {
        int i;
        switch (getCurrentUserSetting()) {
            case Public:
                i = R.string.diary_sharing_public;
                break;
            case FriendsOnly:
                i = R.string.diary_sharing_friends_only;
                break;
            case Password:
                i = R.string.diary_sharing_locked_with_key;
                break;
            default:
                i = R.string.diary_sharing_private;
                break;
        }
        return this.context.getString(i);
    }
}
