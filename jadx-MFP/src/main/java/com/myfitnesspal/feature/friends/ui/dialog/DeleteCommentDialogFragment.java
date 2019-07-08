package com.myfitnesspal.feature.friends.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.friends.ui.listener.DeleteCommentListener;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.DialogListTextResItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import java.util.ArrayList;

public class DeleteCommentDialogFragment extends CustomLayoutBaseDialogFragment {
    /* access modifiers changed from: private */
    public DeleteCommentListener deleteCommentListener;

    public static DeleteCommentDialogFragment newInstance(String str, int i) {
        DeleteCommentDialogFragment deleteCommentDialogFragment = new DeleteCommentDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Extras.COMMENT_ID, str);
        bundle.putInt("position", i);
        deleteCommentDialogFragment.setArguments(bundle);
        return deleteCommentDialogFragment;
    }

    public void setDeleteCommentListener(DeleteCommentListener deleteCommentListener2) {
        this.deleteCommentListener = deleteCommentListener2;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        final String string = arguments.getString(Extras.COMMENT_ID);
        final int i = arguments.getInt("position");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DialogListTextResItem(R.string.delete));
        return new MfpAlertDialogBuilder(getActivity()).setItems(arrayList, new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0 && DeleteCommentDialogFragment.this.deleteCommentListener != null) {
                    DeleteCommentDialogFragment.this.deleteCommentListener.onDeleteCommentClick(string, i);
                }
            }
        }).setCanceledOnTouchOutside(true).create();
    }

    public void onDestroy() {
        super.onDestroy();
        this.deleteCommentListener = null;
    }
}
