package com.myfitnesspal.feature.help.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.UserProperties.NewsFeed;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.inject.Inject;

public class NewsFeedSettings extends MfpActivity {
    private static final String BLOG_NEWSFEED_SETTING_TOGGLED = "blog_newsfeed_setting_toggled";
    private static final String VIDEOS_NEWSFEED_SETTING_TOGGLED = "videos_newsfeed_setting_toggled";
    @Inject
    Lazy<ConfigService> configService;
    /* access modifiers changed from: private */
    public HashMap<String, Boolean> editedFeedSettings;
    private boolean editedShowBlogArticles;
    private boolean editedShowBlogVideos;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    ListView newsFeedSettingsListView;
    private HashMap<String, Boolean> originalFeedSettings;
    @Inject
    Lazy<PremiumService> premiumService;

    public String getAnalyticsScreenTag() {
        return Screens.NEWS_FEED_SETTINGS;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, NewsFeedSettings.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.news_feed_setting_view);
        component().inject(this);
        setTitle(R.string.news_feed_privacy_settings);
        this.originalFeedSettings = getSession().getUser().getFeedSettings();
        this.editedFeedSettings = (HashMap) this.originalFeedSettings.clone();
        this.editedShowBlogArticles = ((LocalSettingsService) this.localSettingsService.get()).shouldShowBlogArticlesInNewsFeed();
        this.editedShowBlogVideos = ((LocalSettingsService) this.localSettingsService.get()).shouldShowBlogVideosInNewsFeed();
        buildList();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(3);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        saveChanges();
    }

    private void buildList() {
        this.newsFeedSettingsListView = (ListView) findViewById(R.id.list);
        this.newsFeedSettingsListView.addHeaderView(getLayoutInflater().inflate(R.layout.news_settings_header, this.newsFeedSettingsListView, false), null, false);
        this.newsFeedSettingsListView.setHeaderDividersEnabled(true);
        final boolean isPremiumSubscribed = ((PremiumService) this.premiumService.get()).isPremiumSubscribed();
        final boolean isNewsfeedVideosSettingOn = ConfigUtils.isNewsfeedVideosSettingOn((ConfigService) this.configService.get());
        ArrayList arrayList = new ArrayList(Arrays.asList(getSession().getUser().getFeedSettingPropertyKeys()));
        arrayList.add(NewsFeed.SHOW_BLOG_ARTICLES);
        if (isNewsfeedVideosSettingOn && isPremiumSubscribed) {
            arrayList.add(NewsFeed.SHOW_BLOG_VIDEOS);
        }
        ListView listView = this.newsFeedSettingsListView;
        AnonymousClass1 r1 = new ArrayAdapter<String>(this, R.layout.newsfeed_settings_row, R.id.chkDescription, arrayList) {
            private final HashMap<String, String> titleMap = new HashMap<>();

            {
                this.titleMap.put(NewsFeed.CREATE_STATUS_ON_NEW_FRIENDS, NewsFeedSettings.this.getString(R.string.become_friends));
                this.titleMap.put(NewsFeed.CREATE_STATUS_ON_REPLIED_TO_TOPIC, NewsFeedSettings.this.getString(R.string.reply_to_message_board_topic));
                this.titleMap.put(NewsFeed.CREATE_STATUS_ON_CREATED_TOPIC, NewsFeedSettings.this.getString(R.string.create_message_board_topic));
                this.titleMap.put(NewsFeed.CREATE_STATUS_ON_BLOG_POSTS, NewsFeedSettings.this.getString(R.string.create_blogpost));
                this.titleMap.put(NewsFeed.CREATE_STATUS_ON_STATUS_COMMENTS, NewsFeedSettings.this.getString(R.string.comment_on_feed_update));
                this.titleMap.put(NewsFeed.CREATE_STATUS_ON_WALL_POSTS, NewsFeedSettings.this.getString(R.string.wrote_on_anothers_profile));
                this.titleMap.put(NewsFeed.CREATE_STATUS_ON_HASNT_LOGGED_IN, NewsFeedSettings.this.getString(R.string.not_logged_onto_mfp));
                this.titleMap.put(NewsFeed.CREATE_STATUS_ON_CONSECUTIVE_LOGINS, NewsFeedSettings.this.getString(R.string.logged_in_days_in_a_row));
                this.titleMap.put(NewsFeed.CREATE_STATUS_ON_LOST_WEIGHT, NewsFeedSettings.this.getString(R.string.lost_weight));
                this.titleMap.put(NewsFeed.CREATE_STATUS_ON_COMPLETED_DIARY, NewsFeedSettings.this.getString(R.string.completed_diary));
                this.titleMap.put(NewsFeed.CREATE_STATUS_ON_PERFORMED_EXERCISE, NewsFeedSettings.this.getString(R.string.perform_cardio));
                this.titleMap.put(NewsFeed.SHOW_BLOG_ARTICLES, NewsFeedSettings.this.getString(R.string.with_blog_articles));
                if (isNewsfeedVideosSettingOn && isPremiumSubscribed) {
                    this.titleMap.put(NewsFeed.SHOW_BLOG_VIDEOS, NewsFeedSettings.this.getString(R.string.blog_videos_setting));
                }
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                CheckedTextView checkedTextView = (CheckedTextView) ViewUtils.findById(view2, R.id.chkDescription);
                String str = (String) getItem(i);
                checkedTextView.setText((CharSequence) this.titleMap.get(str));
                checkedTextView.setChecked(NewsFeedSettings.this.isNewsFeedBlogItem(str) ? NewsFeedSettings.this.getNewsFeedBlogSetting(str) : ((Boolean) NewsFeedSettings.this.editedFeedSettings.get(str)).booleanValue());
                return view2;
            }
        };
        listView.setAdapter(r1);
        this.newsFeedSettingsListView.setOnItemClickListener(new OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                NewsFeedSettings.lambda$buildList$0(NewsFeedSettings.this, adapterView, view, i, j);
            }
        });
    }

    public static /* synthetic */ void lambda$buildList$0(NewsFeedSettings newsFeedSettings, AdapterView adapterView, View view, int i, long j) {
        String str = (String) adapterView.getItemAtPosition(i);
        if (newsFeedSettings.isNewsFeedBlogArticles(str)) {
            newsFeedSettings.editedShowBlogArticles = !newsFeedSettings.editedShowBlogArticles;
        } else if (newsFeedSettings.isNewsFeedBlogVideo(str)) {
            newsFeedSettings.editedShowBlogVideos = !newsFeedSettings.editedShowBlogVideos;
        } else {
            newsFeedSettings.editedFeedSettings.put(str, Boolean.valueOf(!((Boolean) newsFeedSettings.editedFeedSettings.get(str)).booleanValue()));
        }
        ListViewUtils.notifyDataSetChanged(newsFeedSettings.newsFeedSettingsListView);
    }

    /* access modifiers changed from: private */
    public boolean getNewsFeedBlogSetting(String str) {
        return isNewsFeedBlogArticles(str) ? this.editedShowBlogArticles : this.editedShowBlogVideos;
    }

    /* access modifiers changed from: private */
    public boolean isNewsFeedBlogItem(String str) {
        return isNewsFeedBlogArticles(str) || isNewsFeedBlogVideo(str);
    }

    private boolean isNewsFeedBlogArticles(String str) {
        return Strings.equals(str, NewsFeed.SHOW_BLOG_ARTICLES);
    }

    private boolean isNewsFeedBlogVideo(String str) {
        return Strings.equals(str, NewsFeed.SHOW_BLOG_VIDEOS);
    }

    private void saveChanges() {
        User user = getSession().getUser();
        boolean z = false;
        for (String str : this.editedFeedSettings.keySet()) {
            boolean booleanValue = ((Boolean) this.editedFeedSettings.get(str)).booleanValue();
            if (booleanValue != ((Boolean) this.originalFeedSettings.get(str)).booleanValue()) {
                user.setProperty(str, booleanValue ? "true" : "false");
                user.updatePropertyNamed(str);
                z = true;
            }
        }
        if (z) {
            scheduleSync();
        }
        if (this.editedShowBlogArticles != ((LocalSettingsService) this.localSettingsService.get()).shouldShowBlogArticlesInNewsFeed()) {
            reportBlogArticlesSettingToggled(this.editedShowBlogArticles);
            ((LocalSettingsService) this.localSettingsService.get()).setShouldShowBlogArticlesInNewsFeed(this.editedShowBlogArticles);
        }
        if (this.editedShowBlogVideos != ((LocalSettingsService) this.localSettingsService.get()).shouldShowBlogVideosInNewsFeed()) {
            reportBlogVideosSettingToggled(this.editedShowBlogVideos);
            ((LocalSettingsService) this.localSettingsService.get()).setShouldShowBlogVideosInNewsFeed(this.editedShowBlogVideos);
        }
    }

    private void reportBlogArticlesSettingToggled(boolean z) {
        AnalyticsService analyticsService = getAnalyticsService();
        String str = BLOG_NEWSFEED_SETTING_TOGGLED;
        String[] strArr = new String[2];
        strArr[0] = "value";
        strArr[1] = z ? "show_blogs" : "hide_blogs";
        analyticsService.reportEvent(str, MapUtil.createMap(strArr));
    }

    private void reportBlogVideosSettingToggled(boolean z) {
        AnalyticsService analyticsService = getAnalyticsService();
        String str = VIDEOS_NEWSFEED_SETTING_TOGGLED;
        String[] strArr = new String[2];
        strArr[0] = "value";
        strArr[1] = z ? "show_videos" : "hide_videos";
        analyticsService.reportEvent(str, MapUtil.createMap(strArr));
    }
}
