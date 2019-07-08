package com.myfitnesspal.feature.diary.ui.item;

import android.support.annotation.DrawableRes;
import android.view.View.OnClickListener;
import com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.ViewType;
import com.myfitnesspal.feature.premium.service.PremiumFeature;

public class DiaryPromoItem implements DiaryAdapterItem {
    public static final String DIARY_MEAL_GOAL_CARD = "diary_meal_goal_card";
    public static final String TAG = "DiaryPromo";
    private String action;
    private int imageId;
    private String message;
    private OnClickListener onDismissActionListener;
    private OnClickListener onPositiveActionListener;
    private PremiumFeature premiumFeature;
    private String title;

    public DiaryPromoItem(String str, String str2, String str3, @DrawableRes int i, PremiumFeature premiumFeature2, OnClickListener onClickListener, OnClickListener onClickListener2) {
        this.title = str;
        this.message = str2;
        this.action = str3;
        this.imageId = i;
        this.premiumFeature = premiumFeature2;
        this.onPositiveActionListener = onClickListener;
        this.onDismissActionListener = onClickListener2;
    }

    public ViewType getItemType() {
        return ViewType.Promo;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }

    public String getAction() {
        return this.action;
    }

    public int getImageId() {
        return this.imageId;
    }

    public PremiumFeature getPremiumFeature() {
        return this.premiumFeature;
    }

    public OnClickListener getOnPositiveActionListener() {
        return this.onPositiveActionListener;
    }

    public OnClickListener onDismissActionListener() {
        return this.onDismissActionListener;
    }
}
