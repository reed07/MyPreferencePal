package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.uacf.core.util.ViewUtils;

public class EmptyStateView extends FrameLayout {
    @BindView(2131362464)
    ImageView image;
    @BindView(2131362465)
    TextView message;
    @BindView(2131362462)
    TextView primaryButton;
    @BindView(2131362463)
    TextView secondaryButton;
    @BindView(2131362466)
    TextView title;

    public enum Type {
        AnalyticEvent(0, R.string.empty_string, R.string.no_events_to_display, R.string.empty_string, R.string.empty_string),
        Cardio(0, R.string.empty_string, R.string.no_personal_cardio_exercises_created_yet, R.string.empty_string, R.string.empty_string),
        Food(R.drawable.empty_state_my_foods, R.string.my_foods_empty_state_title, R.string.my_foods_empty_state_message, R.string.my_foods_empty_state_button, R.string.empty_string),
        Frequent(R.drawable.empty_state_frequent_foods, R.string.frequent_empty_state_title, R.string.frequent_empty_state_message, R.string.empty_string, R.string.empty_string),
        Meal(R.drawable.empty_state_meals, R.string.meals_empty_state_title, R.string.meals_empty_state_message, R.string.meals_empty_state_button, R.string.empty_string),
        Recent(R.drawable.empty_state_recent_foods, R.string.recent_empty_state_title, R.string.recent_empty_state_message, R.string.empty_string, R.string.empty_string),
        Recipe(R.drawable.empty_state_recipes, R.string.recipes_empty_state_title, R.string.recipes_empty_state_message, R.string.recipes_empty_state_button, R.string.empty_string),
        Strength(0, R.string.empty_string, R.string.no_personal_strength_exercises_created_yet, R.string.empty_string, R.string.empty_string),
        Inbox(R.drawable.ic_empty_inbox, R.string.inboxBtn, R.string.empty_inbox, R.string.empty_string, R.string.empty_string),
        SentMessages(R.drawable.ic_empty_sent_messages, R.string.sent_messages, R.string.empty_sent, R.string.empty_string, R.string.empty_string),
        RecipeIngredients(R.drawable.empty_state_ingredients, R.string.no_ingredients, R.string.add_list_ingredients, R.string.add_ingredients, R.string.empty_string),
        Profile(0, R.string.explore_offline_title, R.string.explore_offline_message, R.string.try_again, R.string.empty_string),
        Explore(0, R.string.explore_offline_title, R.string.explore_offline_message, R.string.try_again, R.string.empty_string),
        MealCollections(0, R.string.explore_offline_title, R.string.explore_offline_message, R.string.try_again, R.string.empty_string);
        
        private final int imageId;
        @StringRes
        private final int messageId;
        @StringRes
        private final int primaryButtonTextId;
        @StringRes
        private final int secondaryButtonTextId;
        @StringRes
        private final int titleId;

        private Type(int i, int i2, int i3, @StringRes int i4, @StringRes int i5) {
            this.imageId = i;
            this.titleId = i2;
            this.messageId = i3;
            this.primaryButtonTextId = i4;
            this.secondaryButtonTextId = i5;
        }

        public int getImageId() {
            return this.imageId;
        }

        public int getTitleId() {
            return this.titleId;
        }

        public int getMessageId() {
            return this.messageId;
        }

        public int getPrimaryButtonTextId() {
            return this.primaryButtonTextId;
        }

        public int getSecondaryButtonTextId() {
            return this.secondaryButtonTextId;
        }
    }

    public EmptyStateView(Context context) {
        super(context);
        init(context);
    }

    public EmptyStateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public EmptyStateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void setImage(@DrawableRes int i) {
        this.image.setImageDrawable(ContextCompat.getDrawable(getContext(), i));
    }

    public void setTitleText(@StringRes int i) {
        this.title.setText(i);
    }

    public void setMessageText(@StringRes int i) {
        this.message.setText(i);
    }

    public void toggleTitleVisibility(boolean z) {
        ViewUtils.setVisible(z, this.title);
    }

    public void initializeForType(Type type) {
        initializeForType(type, null, null);
    }

    public void initializeForType(Type type, OnClickListener onClickListener) {
        initializeForType(type, onClickListener, null);
    }

    public void initializeForType(Type type, OnClickListener onClickListener, OnClickListener onClickListener2) {
        boolean z = type.getImageId() != 0;
        ViewUtils.setVisible(z, this.image);
        if (z) {
            setImage(type.getImageId());
        }
        setTitleText(type.getTitleId());
        setMessageText(type.getMessageId());
        initButton(this.primaryButton, type.getPrimaryButtonTextId(), onClickListener);
        initButton(this.secondaryButton, type.getSecondaryButtonTextId(), onClickListener2);
    }

    private void initButton(TextView textView, @StringRes int i, OnClickListener onClickListener) {
        if (onClickListener == null || i == R.string.empty_string) {
            textView.setVisibility(8);
            textView.setOnClickListener(null);
            return;
        }
        textView.setText(i);
        textView.setVisibility(0);
        textView.setOnClickListener(onClickListener);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.empty_state_screen, this, true);
        ButterKnife.bind((View) this);
    }
}
