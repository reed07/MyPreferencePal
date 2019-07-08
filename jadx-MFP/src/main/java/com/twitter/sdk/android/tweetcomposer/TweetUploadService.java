package com.twitter.sdk.android.tweetcomposer;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Media;
import com.twitter.sdk.android.core.models.Tweet;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TweetUploadService extends IntentService {
    DependencyProvider dependencyProvider;
    Intent intent;

    static class DependencyProvider {
        DependencyProvider() {
        }

        /* access modifiers changed from: 0000 */
        public TwitterApiClient getTwitterApiClient(TwitterSession twitterSession) {
            return TwitterCore.getInstance().getApiClient(twitterSession);
        }
    }

    public TweetUploadService() {
        this(new DependencyProvider());
    }

    TweetUploadService(DependencyProvider dependencyProvider2) {
        super("TweetUploadService");
        this.dependencyProvider = dependencyProvider2;
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent2) {
        TwitterAuthToken twitterAuthToken = (TwitterAuthToken) intent2.getParcelableExtra("EXTRA_USER_TOKEN");
        this.intent = intent2;
        uploadTweet(new TwitterSession(twitterAuthToken, -1, ""), intent2.getStringExtra("EXTRA_TWEET_TEXT"), (Uri) intent2.getParcelableExtra("EXTRA_IMAGE_URI"));
    }

    /* access modifiers changed from: 0000 */
    public void uploadTweet(final TwitterSession twitterSession, final String str, Uri uri) {
        if (uri != null) {
            uploadMedia(twitterSession, uri, new Callback<Media>() {
                public void success(Result<Media> result) {
                    TweetUploadService.this.uploadTweetWithMedia(twitterSession, str, ((Media) result.data).mediaIdString);
                }

                public void failure(TwitterException twitterException) {
                    TweetUploadService.this.fail(twitterException);
                }
            });
        } else {
            uploadTweetWithMedia(twitterSession, str, null);
        }
    }

    /* access modifiers changed from: 0000 */
    public void uploadTweetWithMedia(TwitterSession twitterSession, String str, String str2) {
        this.dependencyProvider.getTwitterApiClient(twitterSession).getStatusesService().update(str, null, null, null, null, null, null, Boolean.valueOf(true), str2).enqueue(new Callback<Tweet>() {
            public void success(Result<Tweet> result) {
                TweetUploadService.this.sendSuccessBroadcast(((Tweet) result.data).getId());
                TweetUploadService.this.stopSelf();
            }

            public void failure(TwitterException twitterException) {
                TweetUploadService.this.fail(twitterException);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void uploadMedia(TwitterSession twitterSession, Uri uri, Callback<Media> callback) {
        TwitterApiClient twitterApiClient = this.dependencyProvider.getTwitterApiClient(twitterSession);
        String path = FileUtils.getPath(this, uri);
        if (path == null) {
            fail(new TwitterException("Uri file path resolved to null"));
            return;
        }
        File file = new File(path);
        twitterApiClient.getMediaService().upload(RequestBody.create(MediaType.parse(FileUtils.getMimeType(file)), file), null, null).enqueue(callback);
    }

    /* access modifiers changed from: 0000 */
    public void fail(TwitterException twitterException) {
        sendFailureBroadcast(this.intent);
        Twitter.getLogger().e("TweetUploadService", "Post Tweet failed", twitterException);
        stopSelf();
    }

    /* access modifiers changed from: 0000 */
    public void sendSuccessBroadcast(long j) {
        Intent intent2 = new Intent("com.twitter.sdk.android.tweetcomposer.UPLOAD_SUCCESS");
        intent2.putExtra("EXTRA_TWEET_ID", j);
        intent2.setPackage(getApplicationContext().getPackageName());
        sendBroadcast(intent2);
    }

    /* access modifiers changed from: 0000 */
    public void sendFailureBroadcast(Intent intent2) {
        Intent intent3 = new Intent("com.twitter.sdk.android.tweetcomposer.UPLOAD_FAILURE");
        intent3.putExtra("EXTRA_RETRY_INTENT", intent2);
        intent3.setPackage(getApplicationContext().getPackageName());
        sendBroadcast(intent3);
    }
}
