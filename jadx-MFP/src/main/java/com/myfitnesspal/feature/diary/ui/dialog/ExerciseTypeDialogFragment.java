package com.myfitnesspal.feature.diary.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.event.ExerciseTypeEvent;
import com.myfitnesspal.shared.constants.Constants.Exercise.ExerciseTypeName;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.DialogListTextItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import java.util.ArrayList;
import javax.inject.Inject;

public class ExerciseTypeDialogFragment extends CustomLayoutBaseDialogFragment {
    @Inject
    LocalizedStringsUtil localizedStringsUtil;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DialogListTextItem(this.localizedStringsUtil.getExerciseTypeNameString(ExerciseTypeName.CARDIOVASCULAR)));
        arrayList.add(new DialogListTextItem(this.localizedStringsUtil.getExerciseTypeNameString(ExerciseTypeName.STRENGTH)));
        return new MfpAlertDialogBuilder(getDialogContextThemeWrapper()).setTitle((int) R.string.exercises).setItems(arrayList, new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ExerciseTypeDialogFragment.this.messageBus.post(new ExerciseTypeEvent(i));
            }
        }).create();
    }
}
