package com.myfitnesspal.feature.settings.ui.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.uacf.core.util.Strings;
import java.util.List;

public class CustomMealNamesAdapter extends Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public final List<String> mealNames;

    private class MealNamesEditTextListener implements TextWatcher, OnClickListener, OnFocusChangeListener {
        private OnMealNameStateListener listener;
        private int position;

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        private MealNamesEditTextListener() {
        }

        public void setOnMealNameStateListener(OnMealNameStateListener onMealNameStateListener) {
            this.listener = onMealNameStateListener;
        }

        public void updatePosition(int i) {
            this.position = i;
        }

        public void afterTextChanged(Editable editable) {
            CustomMealNamesAdapter.this.mealNames.set(this.position, Strings.toString(editable));
            this.listener.mealNameStateChanged();
        }

        public void onFocusChange(View view, boolean z) {
            this.listener.mealNameStateChanged();
        }

        public void onClick(View view) {
            EditText editText = (EditText) view.getTag();
            editText.requestFocus();
            editText.setSelection(editText.length());
        }
    }

    interface OnMealNameStateListener {
        void mealNameStateChanged();
    }

    public static class ViewHolder extends RecyclerViewHolder<String> {
        @BindView(2131363027)
        TextView mealLabel;
        @BindView(2131363031)
        EditText mealNameTxt;
        /* access modifiers changed from: private */
        public MealNamesEditTextListener mealNamesEditTextListener;
        @BindView(2131363074)
        View moveCursorToEndView;

        public ViewHolder(ViewGroup viewGroup, MealNamesEditTextListener mealNamesEditTextListener2) {
            super(R.layout.custom_meal_name_item, viewGroup);
            this.mealNamesEditTextListener = mealNamesEditTextListener2;
            this.mealNameTxt.addTextChangedListener(mealNamesEditTextListener2);
            this.mealNameTxt.setOnFocusChangeListener(mealNamesEditTextListener2);
            this.moveCursorToEndView.setOnClickListener(mealNamesEditTextListener2);
            this.moveCursorToEndView.setTag(this.mealNameTxt);
        }

        public void setData(String str, int i) {
            this.mealNamesEditTextListener.updatePosition(i);
            this.mealLabel.setText(String.format(this.context.getResources().getString(R.string.meal_label_txt), new Object[]{Integer.valueOf(i + 1)}));
            this.mealNameTxt.setText(str);
            styleMealLabelByState();
        }

        /* access modifiers changed from: private */
        public void styleMealLabelByState() {
            this.mealLabel.setTextColor(this.context.getResources().getColor(Strings.isEmpty(Strings.toString(this.mealNameTxt.getText())) && !this.mealNameTxt.hasFocus() ? R.color.black_hint_text : R.color.black_text));
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.mealLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.meal_label, "field 'mealLabel'", TextView.class);
            viewHolder.mealNameTxt = (EditText) Utils.findRequiredViewAsType(view, R.id.meal_name, "field 'mealNameTxt'", EditText.class);
            viewHolder.moveCursorToEndView = Utils.findRequiredView(view, R.id.move_cursor_to_end, "field 'moveCursorToEndView'");
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder != null) {
                this.target = null;
                viewHolder.mealLabel = null;
                viewHolder.mealNameTxt = null;
                viewHolder.moveCursorToEndView = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public CustomMealNamesAdapter(List<String> list) {
        this.mealNames = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final ViewHolder viewHolder = new ViewHolder(viewGroup, new MealNamesEditTextListener());
        viewHolder.mealNamesEditTextListener.setOnMealNameStateListener(new OnMealNameStateListener() {
            public void mealNameStateChanged() {
                viewHolder.styleMealLabelByState();
            }
        });
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setData((String) this.mealNames.get(i), i);
    }

    public int getItemCount() {
        return this.mealNames.size();
    }

    public List<String> getMealNames() {
        return this.mealNames;
    }
}
