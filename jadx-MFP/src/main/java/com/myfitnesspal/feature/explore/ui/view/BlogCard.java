package com.myfitnesspal.feature.explore.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.blog.util.BlogEndpointHelper;
import com.myfitnesspal.feature.explore.analytics.ExploreAnalytics.BlogCategory;
import com.myfitnesspal.feature.explore.analytics.ExploreAnalytics.CardType;
import com.myfitnesspal.feature.explore.service.ExploreService.BlogPost;
import com.myfitnesspal.shared.model.Size;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BlogCard extends ExploreCardBase {
    private static final String DEFAULT_ASPECT_RATIO = "h,16:9";
    private static final String ENGLISH = "en";
    private static final String GERMAN = "de";
    private static final String URL_KEY_UTM_CONTENT = "utm_content";
    private static final String URL_KEY_UTM_MEDIUM = "utm_medium";
    private static final String URL_KEY_UTM_SOURCE = "utm_source";
    private static final String URL_VAL_LEAD_ARTICLE = "lead_article";
    private static final String URL_VAL_UTM_MEDIUM = "app_android";
    private static final String URL_VAL_UTM_SOURCE = "mfp";
    /* access modifiers changed from: private */
    public static final Map<Integer, String> VIEW_ID_TO_BLOG_CATEGORY = new HashMap();
    /* access modifiers changed from: private */
    public static final Map<Integer, String> VIEW_ID_TO_BLOG_URL = new HashMap();
    private TextView blogDate;
    private TextView blogTitle;
    /* access modifiers changed from: private */
    public String blogUrl;
    private View buttonsDe;
    private View buttonsEn;
    private View imageContainer;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public View progress;

    public String getAnalyticsType() {
        return CardType.BLOG;
    }

    /* access modifiers changed from: protected */
    public int getButtonTextId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getContentLayoutId() {
        return R.layout.explore_card_blog;
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
        return R.string.explore_card_title_blog;
    }

    static {
        VIEW_ID_TO_BLOG_URL.put(Integer.valueOf(0), BlogEndpointHelper.BLOG_URL_EN);
        VIEW_ID_TO_BLOG_URL.put(Integer.valueOf(R.id.videosButtonEn), "http://blog.myfitnesspal.com/video/");
        VIEW_ID_TO_BLOG_URL.put(Integer.valueOf(R.id.nutritionButtonEn), "http://blog.myfitnesspal.com/category/nutrition/");
        VIEW_ID_TO_BLOG_URL.put(Integer.valueOf(R.id.inspirationButtonEn), "http://blog.myfitnesspal.com/category/inspiration/");
        VIEW_ID_TO_BLOG_URL.put(Integer.valueOf(R.id.eatButtonDe), "http://blog.myfitnesspal.de/category/essen/");
        VIEW_ID_TO_BLOG_URL.put(Integer.valueOf(R.id.inspirationButtonDe), "http://blog.myfitnesspal.de/category/leben/");
        VIEW_ID_TO_BLOG_URL.put(Integer.valueOf(R.id.exerciseButtonDe), "http://blog.myfitnesspal.de/category/bewegen/");
        VIEW_ID_TO_BLOG_CATEGORY.put(Integer.valueOf(R.id.videosButtonEn), BlogCategory.VIDEOS);
        VIEW_ID_TO_BLOG_CATEGORY.put(Integer.valueOf(R.id.nutritionButtonEn), "nutrition");
        VIEW_ID_TO_BLOG_CATEGORY.put(Integer.valueOf(R.id.inspirationButtonEn), BlogCategory.INSPIRATION);
        VIEW_ID_TO_BLOG_CATEGORY.put(Integer.valueOf(R.id.eatButtonDe), BlogCategory.EAT);
        VIEW_ID_TO_BLOG_CATEGORY.put(Integer.valueOf(R.id.inspirationButtonDe), BlogCategory.INSPIRATION);
        VIEW_ID_TO_BLOG_CATEGORY.put(Integer.valueOf(R.id.exerciseButtonDe), "exercise");
    }

    public BlogCard(Context context) {
        super(context);
    }

    public BlogCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BlogCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void render(BlogPost blogPost) {
        String lowerCase = Locale.getDefault().getLanguage().toLowerCase();
        if (!(ENGLISH.equals(lowerCase) || GERMAN.equals(lowerCase)) || blogPost == null) {
            ViewUtils.setGone(this);
            return;
        }
        ViewUtils.setVisible(this);
        LayoutParams layoutParams = (LayoutParams) this.imageContainer.getLayoutParams();
        layoutParams.dimensionRatio = DEFAULT_ASPECT_RATIO;
        Size imageSize = blogPost.getImageSize();
        if (imageSize != null && imageSize.getHeight() > 0 && imageSize.getHeight() > 0) {
            layoutParams.dimensionRatio = String.format(Locale.ENGLISH, "h,%d:%d", new Object[]{Integer.valueOf(imageSize.getWidth()), Integer.valueOf(imageSize.getHeight())});
            this.imageContainer.requestLayout();
        }
        ViewUtils.setVisible(this.progress);
        Glide.with(getContext()).load(blogPost.getImageUrl()).apply(new RequestOptions().centerCrop()).listener(new RequestListener<Drawable>() {
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                ViewUtils.setGone(BlogCard.this.progress);
                return false;
            }

            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                ViewUtils.setGone(BlogCard.this.progress);
                return false;
            }
        }).into(this.imageView);
        this.blogTitle.setText(blogPost.getTitle());
        this.blogDate.setText(blogPost.getDate());
        this.blogUrl = blogPost.getUrl();
        if (ENGLISH.equals(lowerCase)) {
            ViewUtils.setGone(this.buttonsDe);
            ViewUtils.setVisible(this.buttonsEn);
            return;
        }
        ViewUtils.setGone(this.buttonsEn);
        ViewUtils.setVisible(this.buttonsDe);
    }

    /* access modifiers changed from: protected */
    public void onInflated() {
        setOnContentViewClickListener(new OnClickListener() {
            public void onClick(View view) {
                String access$100 = Strings.isEmpty(BlogCard.this.blogUrl) ? (String) BlogCard.VIEW_ID_TO_BLOG_URL.get(Integer.valueOf(0)) : BlogCard.this.blogUrl;
                BlogCard.this.getAnalytics().reportBlogArticleTapped(access$100);
                BlogCard.this.goToUrl(access$100, BlogCard.URL_VAL_LEAD_ARTICLE);
            }
        });
        this.imageContainer = findViewById(R.id.imageContainer);
        this.progress = findViewById(R.id.progress);
        this.imageView = (ImageView) findViewById(R.id.image);
        this.buttonsEn = findViewById(R.id.buttonsEn);
        this.buttonsDe = findViewById(R.id.buttonsDe);
        this.blogTitle = (TextView) findViewById(R.id.blogTitle);
        this.blogDate = (TextView) findViewById(R.id.blogDate);
        AnonymousClass3 r0 = new OnClickListener() {
            public void onClick(View view) {
                int id = view.getId();
                String str = (String) BlogCard.VIEW_ID_TO_BLOG_CATEGORY.get(Integer.valueOf(id));
                String str2 = (String) BlogCard.VIEW_ID_TO_BLOG_URL.get(Integer.valueOf(id));
                BlogCard.this.getAnalytics().reportBlogCategoryTapped(str);
                BlogCard.this.goToUrl(str2, str);
            }
        };
        findViewById(R.id.videosButtonEn).setOnClickListener(r0);
        findViewById(R.id.nutritionButtonEn).setOnClickListener(r0);
        findViewById(R.id.inspirationButtonEn).setOnClickListener(r0);
        findViewById(R.id.eatButtonDe).setOnClickListener(r0);
        findViewById(R.id.inspirationButtonDe).setOnClickListener(r0);
        findViewById(R.id.exerciseButtonDe).setOnClickListener(r0);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x002f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void goToUrl(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            boolean r0 = com.uacf.core.util.Strings.notEmpty(r4)     // Catch:{ Exception -> 0x003e }
            if (r0 == 0) goto L_0x003e
            android.net.Uri r0 = android.net.Uri.parse(r4)     // Catch:{ Exception -> 0x002f }
            android.net.Uri$Builder r0 = r0.buildUpon()     // Catch:{ Exception -> 0x002f }
            java.lang.String r1 = "utm_source"
            java.lang.String r2 = "mfp"
            r0.appendQueryParameter(r1, r2)     // Catch:{ Exception -> 0x002f }
            java.lang.String r1 = "utm_medium"
            java.lang.String r2 = "app_android"
            r0.appendQueryParameter(r1, r2)     // Catch:{ Exception -> 0x002f }
            boolean r1 = com.uacf.core.util.Strings.notEmpty(r5)     // Catch:{ Exception -> 0x002f }
            if (r1 == 0) goto L_0x0027
            java.lang.String r1 = "utm_content"
            r0.appendQueryParameter(r1, r5)     // Catch:{ Exception -> 0x002f }
        L_0x0027:
            android.net.Uri r5 = r0.build()     // Catch:{ Exception -> 0x002f }
            java.lang.String r4 = r5.toString()     // Catch:{ Exception -> 0x002f }
        L_0x002f:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r5 = r3.getNavigationHelper()     // Catch:{ Exception -> 0x003e }
            android.content.Intent r4 = com.myfitnesspal.shared.ui.navigation.SharedIntents.newUriIntent(r4)     // Catch:{ Exception -> 0x003e }
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r4 = r5.withIntent(r4)     // Catch:{ Exception -> 0x003e }
            r4.startActivity()     // Catch:{ Exception -> 0x003e }
        L_0x003e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.explore.ui.view.BlogCard.goToUrl(java.lang.String, java.lang.String):void");
    }
}
