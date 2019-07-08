package com.myfitnesspal.feature.explore.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.explore.analytics.ExploreAnalytics.CardType;
import com.myfitnesspal.feature.explore.analytics.ExploreAnalytics.ShopCategory;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.uacf.core.util.ViewUtils;
import java.util.HashMap;
import java.util.Map;

public class ShopCard extends ExploreCardBase {
    private static final String BUTTON_TAG = "ShopCardButton";
    private static final Map<Integer, String> VIEW_ID_TO_CATEGORY = new HashMap();
    private static final Map<Integer, String> VIEW_ID_TO_URL = new HashMap();

    public String getAnalyticsType() {
        return CardType.SHOP;
    }

    /* access modifiers changed from: protected */
    public int getButtonTextId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getContentLayoutId() {
        return R.layout.explore_card_shop;
    }

    /* access modifiers changed from: protected */
    public int getLeftBadgeLayoutId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getRightBadgeLayoutId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getTitleTextId() {
        return R.string.explore_card_title_shop;
    }

    static {
        VIEW_ID_TO_URL.put(Integer.valueOf(R.id.shopWomen), "https://www.underarmour.com/top-gear-picks/mfp/womens?cid=MMF|REF|MFPal|Site|womens");
        VIEW_ID_TO_URL.put(Integer.valueOf(R.id.shopGirls), "https://www.underarmour.com/girls");
        VIEW_ID_TO_URL.put(Integer.valueOf(R.id.shopMen), "https://www.underarmour.com/top-gear-picks/mfp/mens?cid=MMF|REF|MFPal|App|mens|atf");
        VIEW_ID_TO_URL.put(Integer.valueOf(R.id.shopBoys), "https://www.underarmour.com/boys");
        VIEW_ID_TO_URL.put(Integer.valueOf(R.id.shopShoes), "https://www.underarmour.com/footwear");
        VIEW_ID_TO_URL.put(Integer.valueOf(R.id.shopTechnology), "https://www.underarmour.com/technology");
        VIEW_ID_TO_URL.put(Integer.valueOf(R.id.shopNewArrivals), "https://www.underarmour.com/new-arrivals/");
        VIEW_ID_TO_URL.put(Integer.valueOf(R.id.shopOutlet), "https://www.underarmour.com/outlet/");
        VIEW_ID_TO_CATEGORY.put(Integer.valueOf(R.id.shopWomen), ShopCategory.WOMEN);
        VIEW_ID_TO_CATEGORY.put(Integer.valueOf(R.id.shopGirls), ShopCategory.GIRLS);
        VIEW_ID_TO_CATEGORY.put(Integer.valueOf(R.id.shopMen), ShopCategory.MEN);
        VIEW_ID_TO_CATEGORY.put(Integer.valueOf(R.id.shopBoys), ShopCategory.BOYS);
        VIEW_ID_TO_CATEGORY.put(Integer.valueOf(R.id.shopShoes), ShopCategory.SHOES);
        VIEW_ID_TO_CATEGORY.put(Integer.valueOf(R.id.shopTechnology), ShopCategory.TECHNOLOGY);
        VIEW_ID_TO_CATEGORY.put(Integer.valueOf(R.id.shopNewArrivals), ShopCategory.NEW_ARRIVALS);
        VIEW_ID_TO_CATEGORY.put(Integer.valueOf(R.id.shopOutlet), ShopCategory.OUTLET);
    }

    public ShopCard(Context context) {
        super(context);
    }

    public ShopCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ShopCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onInflated() {
        AnonymousClass1 r0 = new OnClickListener() {
            public void onClick(View view) {
                ShopCard.this.navigateToShopLink(view.getId());
            }
        };
        for (TextView textView : ViewUtils.findByType(this, TextView.class)) {
            if ((textView.getTag() instanceof String) && textView.getTag().equals(BUTTON_TAG)) {
                textView.setOnClickListener(r0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void navigateToShopLink(int i) {
        getAnalytics().reportShopCategoryTapped((String) VIEW_ID_TO_CATEGORY.get(Integer.valueOf(i)));
        String str = (String) VIEW_ID_TO_URL.get(Integer.valueOf(i));
        if (str != null) {
            try {
                getNavigationHelper().withIntent(SharedIntents.newUriIntent(str)).startActivity();
            } catch (Exception unused) {
            }
        }
    }
}
