package com.myfitnesspal.shared.ui.dialog;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.CheckableListItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.VersionUtils;
import com.uacf.core.util.ViewUtils;
import java.util.List;

public class MfpAlertDialogBuilder<T extends MfpAlertDialogBuilder> {
    private boolean cancelable;
    private boolean canceledOnTouchOutside;
    private ListView contentListView;
    protected final Context context;
    private View customView;
    private boolean dismissOnItemClick;
    private Drawable icon;
    private List items;
    private ListAdapter listAdapter;
    private ListType listType;
    private CharSequence message;
    private OnClickListener negativeButtonClickListener;
    private CharSequence negativeButtonText;
    private OnClickListener neutralButtonClickListener;
    private CharSequence neutralButtonText;
    private OnCancelListener onCancelListener;
    private OnDismissListener onDismissListener;
    private OnItemClickListener onItemClickListener;
    private OnKeyListener onKeyListener;
    private OnClickListener positiveButtonClickListener;
    private CharSequence positiveButtonText;
    private int selection;
    private CharSequence title;

    private enum ListType {
        Simple,
        SingleChoice
    }

    public MfpAlertDialogBuilder(Context context2) {
        this.context = context2;
        init();
    }

    private void init() {
        this.cancelable = true;
        this.canceledOnTouchOutside = true;
        this.dismissOnItemClick = true;
    }

    public T setTitle(CharSequence charSequence) {
        this.title = charSequence;
        return this;
    }

    public T setTitle(int i) {
        this.title = this.context.getString(i);
        return this;
    }

    public T setMessage(CharSequence charSequence) {
        this.message = charSequence;
        return this;
    }

    public T setMessage(int i) {
        return setMessage((CharSequence) this.context.getString(i));
    }

    public T setPositiveButton(CharSequence charSequence, OnClickListener onClickListener) {
        this.positiveButtonText = charSequence;
        this.positiveButtonClickListener = onClickListener;
        return this;
    }

    public T setPositiveButton(int i, OnClickListener onClickListener) {
        return setPositiveButton((CharSequence) this.context.getString(i), onClickListener);
    }

    public T setNegativeButton(CharSequence charSequence, OnClickListener onClickListener) {
        this.negativeButtonText = charSequence;
        this.negativeButtonClickListener = onClickListener;
        return this;
    }

    public T setNegativeButton(int i, OnClickListener onClickListener) {
        return setNegativeButton((CharSequence) this.context.getString(i), onClickListener);
    }

    public T setNeutralButton(CharSequence charSequence, OnClickListener onClickListener) {
        this.neutralButtonText = charSequence;
        this.neutralButtonClickListener = onClickListener;
        return this;
    }

    public T setNeutralButton(int i, OnClickListener onClickListener) {
        return setNeutralButton((CharSequence) this.context.getString(i), onClickListener);
    }

    public T setIcon(Drawable drawable) {
        this.icon = drawable;
        return this;
    }

    public T setIcon(int i) {
        return setIcon(this.context.getDrawable(i));
    }

    public T setView(View view) {
        this.customView = view;
        return this;
    }

    public T setCancelable(boolean z) {
        this.cancelable = z;
        return this;
    }

    public T setCanceledOnTouchOutside(boolean z) {
        this.canceledOnTouchOutside = z;
        return this;
    }

    public T setOnDismissListener(OnDismissListener onDismissListener2) {
        this.onDismissListener = onDismissListener2;
        return this;
    }

    public T setOnCancelListener(OnCancelListener onCancelListener2) {
        this.onCancelListener = onCancelListener2;
        return this;
    }

    public T setOnKeyListener(OnKeyListener onKeyListener2) {
        this.onKeyListener = onKeyListener2;
        return this;
    }

    public T setItems(List<? extends DialogListItem> list, OnItemClickListener onItemClickListener2) {
        return setListAndOnItemClickListener(list, onItemClickListener2, ListType.Simple);
    }

    public T setAdapter(ListAdapter listAdapter2, OnItemClickListener onItemClickListener2) {
        return setAdapterAndOnItemClickListener(listAdapter2, onItemClickListener2, ListType.Simple);
    }

    public T setSingleChoiceItems(List<? extends CheckableListItem> list, OnItemClickListener onItemClickListener2) {
        return setListAndOnItemClickListener(list, onItemClickListener2, ListType.SingleChoice);
    }

    public T setSingleChoiceItems(ListAdapter listAdapter2, OnItemClickListener onItemClickListener2) {
        return setAdapterAndOnItemClickListener(listAdapter2, onItemClickListener2, ListType.SingleChoice, 0);
    }

    public T setSingleChoiceItems(ListAdapter listAdapter2, OnItemClickListener onItemClickListener2, int i) {
        return setAdapterAndOnItemClickListener(listAdapter2, onItemClickListener2, ListType.SingleChoice, i);
    }

    public T setDismissOnItemClick(boolean z) {
        this.dismissOnItemClick = z;
        return this;
    }

    private T setListAndOnItemClickListener(List list, OnItemClickListener onItemClickListener2, ListType listType2) {
        this.items = list;
        this.onItemClickListener = onItemClickListener2;
        this.listType = listType2;
        return this;
    }

    private T setAdapterAndOnItemClickListener(ListAdapter listAdapter2, OnItemClickListener onItemClickListener2, ListType listType2) {
        return setAdapterAndOnItemClickListener(listAdapter2, onItemClickListener2, listType2, 0);
    }

    private T setAdapterAndOnItemClickListener(ListAdapter listAdapter2, OnItemClickListener onItemClickListener2, ListType listType2, int i) {
        this.listAdapter = listAdapter2;
        this.onItemClickListener = onItemClickListener2;
        this.listType = listType2;
        this.selection = i;
        return this;
    }

    /* access modifiers changed from: protected */
    public Builder build() {
        Builder builder = new Builder(this.context);
        builder.setTitle(this.title).setPositiveButton(this.positiveButtonText, this.positiveButtonClickListener).setNegativeButton(this.negativeButtonText, this.negativeButtonClickListener).setNeutralButton(this.neutralButtonText, this.neutralButtonClickListener).setIcon(this.icon).setCancelable(this.cancelable);
        if (this.items == null && this.listAdapter == null) {
            View view = this.customView;
            if (view != null) {
                builder.setView(view);
            } else if (VersionUtils.isLollipopOrHigher()) {
                View inflate = LayoutInflater.from(this.context).inflate(R.layout.alert_dialog_message, null);
                ((TextView) inflate.findViewById(R.id.alert_dialog_text)).setText(this.message);
                builder.setView(inflate);
            } else {
                builder.setMessage(this.message);
            }
        } else {
            View inflate2 = LayoutInflater.from(this.context).inflate(R.layout.list_dialog, null);
            this.contentListView = (ListView) ViewUtils.findById(inflate2, R.id.listViewList);
            this.contentListView.setAdapter(getAdapter());
            LayoutParams layoutParams = (LayoutParams) this.contentListView.getLayoutParams();
            if (TextUtils.isEmpty(this.title)) {
                layoutParams.topMargin = 0;
                layoutParams.bottomMargin = 0;
            } else if (TextUtils.isEmpty(this.positiveButtonText) && TextUtils.isEmpty(this.negativeButtonText)) {
                layoutParams.bottomMargin = this.context.getResources().getDimensionPixelSize(R.dimen.dialog_list_marginBottom_no_button);
            }
            this.contentListView.requestLayout();
            this.contentListView.setSelection(this.selection);
            builder.setView(inflate2);
        }
        return builder;
    }

    public AlertDialog create() {
        AlertDialog create = build().create();
        create.setCanceledOnTouchOutside(this.canceledOnTouchOutside);
        create.setOnDismissListener(this.onDismissListener);
        create.setOnCancelListener(this.onCancelListener);
        create.setOnKeyListener(this.onKeyListener);
        if (!(this.items == null && this.listAdapter == null)) {
            setOnItemClickListener(create);
        }
        return create;
    }

    public void show() {
        create().show();
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [android.widget.ListAdapter] */
    /* JADX WARNING: type inference failed for: r0v4, types: [com.myfitnesspal.shared.ui.adapter.DialogSingleChoiceAdapter] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.widget.ListAdapter getAdapter() {
        /*
            r9 = this;
            android.widget.ListAdapter r0 = r9.listAdapter
            if (r0 == 0) goto L_0x0005
            return r0
        L_0x0005:
            r0 = 0
            int[] r1 = com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder.AnonymousClass2.$SwitchMap$com$myfitnesspal$shared$ui$dialog$MfpAlertDialogBuilder$ListType
            com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder$ListType r2 = r9.listType
            int r2 = r2.ordinal()
            r1 = r1[r2]
            switch(r1) {
                case 1: goto L_0x001e;
                case 2: goto L_0x0014;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x002f
        L_0x0014:
            com.myfitnesspal.shared.ui.adapter.DialogSingleChoiceAdapter r0 = new com.myfitnesspal.shared.ui.adapter.DialogSingleChoiceAdapter
            android.content.Context r1 = r9.context
            java.util.List r2 = r9.items
            r0.<init>(r1, r2)
            goto L_0x002f
        L_0x001e:
            com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder$1 r0 = new com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder$1
            android.content.Context r5 = r9.context
            r6 = 2131558588(0x7f0d00bc, float:1.8742496E38)
            r7 = 2131363782(0x7f0a07c6, float:1.8347383E38)
            java.util.List r8 = r9.items
            r3 = r0
            r4 = r9
            r3.<init>(r5, r6, r7, r8)
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder.getAdapter():android.widget.ListAdapter");
    }

    private void setOnItemClickListener(AlertDialog alertDialog) {
        OnItemClickListener onItemClickListener2;
        switch (this.listType) {
            case Simple:
                onItemClickListener2 = this.onItemClickListener;
                break;
            case SingleChoice:
                onItemClickListener2 = new OnItemClickListener() {
                    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                        MfpAlertDialogBuilder.lambda$setOnItemClickListener$0(MfpAlertDialogBuilder.this, adapterView, view, i, j);
                    }
                };
                break;
            default:
                onItemClickListener2 = null;
                break;
        }
        ListView listView = this.contentListView;
        if (this.dismissOnItemClick) {
            onItemClickListener2 = new OnItemClickListener(onItemClickListener2, alertDialog) {
                private final /* synthetic */ OnItemClickListener f$0;
                private final /* synthetic */ AlertDialog f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                    MfpAlertDialogBuilder.lambda$setOnItemClickListener$1(this.f$0, this.f$1, adapterView, view, i, j);
                }
            };
        }
        listView.setOnItemClickListener(onItemClickListener2);
    }

    public static /* synthetic */ void lambda$setOnItemClickListener$0(MfpAlertDialogBuilder mfpAlertDialogBuilder, AdapterView adapterView, View view, int i, long j) {
        OnItemClickListener onItemClickListener2 = mfpAlertDialogBuilder.onItemClickListener;
        if (onItemClickListener2 != null) {
            onItemClickListener2.onItemClick(adapterView, view, i, j);
        }
        ((BaseAdapter) adapterView.getAdapter()).notifyDataSetChanged();
    }

    static /* synthetic */ void lambda$setOnItemClickListener$1(OnItemClickListener onItemClickListener2, AlertDialog alertDialog, AdapterView adapterView, View view, int i, long j) {
        if (onItemClickListener2 != null) {
            onItemClickListener2.onItemClick(adapterView, view, i, j);
        }
        alertDialog.dismiss();
    }
}
