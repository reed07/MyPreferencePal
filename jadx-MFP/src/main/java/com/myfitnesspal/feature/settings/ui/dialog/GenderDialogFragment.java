package com.myfitnesspal.feature.settings.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.ui.view.GenderItem;
import com.myfitnesspal.shared.model.CheckableListItem;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.Gender;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GenderDialogFragment extends CustomLayoutBaseDialogFragment {
    /* access modifiers changed from: private */
    public ArrayList<GenderItem> genders;
    private Gender originalGender;

    public static class GenderChangedEvent {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreateDialog(bundle);
        ContextThemeWrapper dialogContextThemeWrapper = getDialogContextThemeWrapper();
        this.genders = new ArrayList<>();
        Resources resources = dialogContextThemeWrapper.getResources();
        boolean equalsIgnoreCase = Strings.equalsIgnoreCase(getSession().getUser().getProfile().getGenderString(), Gender.Female);
        this.originalGender = equalsIgnoreCase ? Gender.Female : Gender.Male;
        GenderItem genderItem = new GenderItem(resources.getString(R.string.maleTxt), Gender.Male);
        genderItem.setState(!equalsIgnoreCase);
        GenderItem genderItem2 = new GenderItem(resources.getString(R.string.femaleTxt), Gender.Female);
        genderItem2.setState(equalsIgnoreCase);
        this.genders.add(genderItem2);
        this.genders.add(genderItem);
        return new MfpAlertDialogBuilder(dialogContextThemeWrapper).setTitle((int) R.string.GenderTxt).setSingleChoiceItems((List<? extends CheckableListItem>) this.genders, (OnItemClickListener) new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = 0;
                while (i2 < CollectionUtils.size((Collection<?>) GenderDialogFragment.this.genders)) {
                    ((GenderItem) GenderDialogFragment.this.genders.get(i2)).setState(i2 == i);
                    i2++;
                }
            }
        }).setDismissOnItemClick(false).setPositiveButton((int) R.string.setBtn, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                GenderDialogFragment.this.doSave();
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) null).create();
    }

    /* access modifiers changed from: private */
    public void doSave() {
        User user = getSession().getUser();
        Iterator it = this.genders.iterator();
        while (it.hasNext()) {
            GenderItem genderItem = (GenderItem) it.next();
            if (genderItem.getState() && genderItem.getGender() != this.originalGender) {
                user.getProfile().setGenderString(Strings.toString(genderItem.getGender()));
                user.updatePropertyNamed("gender");
                this.messageBus.post(new GenderChangedEvent());
            }
        }
    }
}
