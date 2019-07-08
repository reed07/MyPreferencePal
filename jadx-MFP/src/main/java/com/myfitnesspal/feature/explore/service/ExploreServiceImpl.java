package com.myfitnesspal.feature.explore.service;

import android.location.Location;
import android.support.annotation.Nullable;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.challenges.model.ChallengeSummary;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.explore.service.ExploreService.BlogPost;
import com.myfitnesspal.feature.explore.service.ExploreService.Response;
import com.myfitnesspal.feature.home.util.NewsFeedCardUtils;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.restaurantlogging.model.VenuesRequestData;
import com.myfitnesspal.feature.restaurantlogging.service.VenueService;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Challenges;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.model.Size;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.Holder;
import com.uacf.core.util.Tuple2;
import dagger.Lazy;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Provider;

public class ExploreServiceImpl implements ExploreService {
    private static final int DEFAULT_VENUES_RADIUS_METERS = 2000;
    private static final int MAX_BLOG_POSTS = 1;
    private static final int MAX_MEALS = 2;
    private static final String URL_KEY_LIMIT = "limit";
    private static final String URL_KEY_ORDER = "order";
    private static final String URL_VALUE_CREATED_AT = "created_at";
    private final Provider<MfpV2Api> api;
    private final Lazy<ChallengesService> challengesService;
    private final Lazy<MealService> mealService;
    private final Lazy<VenueService> venueService;

    private static class BlogPostImpl implements BlogPost {
        @Expose
        String createdAt;
        @Expose
        Data data;
        @Expose
        String id;
        private Size imageSize;
        private String imageUrl;

        public static class API_RESPONSE_MAPPER extends ApiResponse<BlogPostImpl> {
        }

        private static class Data {
            @Expose
            Post post;

            private Data() {
            }
        }

        private static class Link {
            @Expose
            String blogSource;
            @Expose
            String blogSourceDeepLinkUrl;
            @Expose
            String blogSourceUrl;
            @Expose
            String deepLink;
            @Expose
            String postDescription;
            @Expose
            String text;
            @Expose
            String webUrl;

            private Link() {
            }
        }

        private static class Post {
            @Expose
            Link link;
            @Expose
            Thumbnail thumbnail;

            private Post() {
            }
        }

        private static class Thumbnail {
            @Expose
            String mainAsset;
            @Expose
            List<String> sizes;

            private Thumbnail() {
            }
        }

        private BlogPostImpl() {
        }

        public String getTitle() {
            if (valid()) {
                return this.data.post.link.text;
            }
            return null;
        }

        public String getUrl() {
            if (valid()) {
                return this.data.post.link.webUrl;
            }
            return null;
        }

        public String getImageUrl() {
            checkSizeAndUrl();
            return this.imageUrl;
        }

        public String getDate() {
            try {
                return DateTimeUtils.formatCanonical(Format.newIso8601DateTimeFormat().parse(this.createdAt));
            } catch (Exception unused) {
                return this.createdAt;
            }
        }

        public Size getImageSize() {
            checkSizeAndUrl();
            return this.imageSize;
        }

        private void checkSizeAndUrl() {
            if (this.imageUrl == null && valid()) {
                Tuple2 bestFitImageSizeAndUrl = NewsFeedCardUtils.getBestFitImageSizeAndUrl(this.data.post.thumbnail.mainAsset, this.data.post.thumbnail.sizes);
                this.imageSize = (Size) bestFitImageSizeAndUrl.getItem1();
                this.imageUrl = (String) bestFitImageSizeAndUrl.getItem2();
            }
        }

        private boolean valid() {
            Data data2 = this.data;
            return (data2 == null || data2.post == null || this.data.post.link == null || this.data.post.thumbnail == null) ? false : true;
        }
    }

    private static class ResponseImpl implements Response {
        private BlogPost blogPost;
        private List<ChallengeSummary> challenges;
        private List<Venue> nearbyVenues;

        private ResponseImpl() {
        }

        public List<ChallengeSummary> getNewChallenges() {
            List<ChallengeSummary> list = this.challenges;
            return list == null ? new ArrayList() : new ArrayList(list);
        }

        public void setChallenges(List<ChallengeSummary> list) {
            this.challenges = list;
        }

        public BlogPost getBlogPost() {
            return this.blogPost;
        }

        public void setBlogPost(BlogPost blogPost2) {
            this.blogPost = blogPost2;
        }

        public List<Venue> getNearbyVenues() {
            return this.nearbyVenues;
        }

        public void setNearbyVenues(List<Venue> list) {
            this.nearbyVenues = list;
        }
    }

    static /* synthetic */ void lambda$getData$1(Response response) throws Exception {
    }

    public ExploreServiceImpl(Provider<MfpV2Api> provider, Lazy<ChallengesService> lazy, Lazy<MealService> lazy2, Lazy<VenueService> lazy3) {
        this.api = provider;
        this.challengesService = lazy;
        this.mealService = lazy2;
        this.venueService = lazy3;
    }

    public Response getData(@Nullable Location location) throws Throwable {
        Holder holder = new Holder();
        ResponseImpl responseImpl = new ResponseImpl();
        Observable.zip(getRestaurantsNearby(responseImpl, location), getChallenges(responseImpl), getBlog(responseImpl), new Function3(responseImpl) {
            private final /* synthetic */ ResponseImpl f$1;

            {
                this.f$1 = r2;
            }

            public final Object apply(Object obj, Object obj2, Object obj3) {
                return ExploreServiceImpl.lambda$getData$0(Holder.this, this.f$1, (Boolean) obj, (Boolean) obj2, (Boolean) obj3);
            }
        }).subscribeOn(Schedulers.io()).blockingSubscribe($$Lambda$ExploreServiceImpl$rm_lw4kbtmdmmbc_lIPCbZlmhWg.INSTANCE, new Consumer() {
            public final void accept(Object obj) {
                Holder.this.setValue((Throwable) obj);
            }
        });
        if (holder.getValue() == null) {
            return responseImpl;
        }
        throw ((Throwable) holder.getValue());
    }

    static /* synthetic */ Response lambda$getData$0(Holder holder, ResponseImpl responseImpl, Boolean bool, Boolean bool2, Boolean bool3) throws Exception {
        if (!bool.booleanValue() && !bool2.booleanValue() && !bool3.booleanValue()) {
            holder.setValue(new Exception("resource load failed. device probably offline."));
        }
        return responseImpl;
    }

    private Observable<Boolean> getRestaurantsNearby(ResponseImpl responseImpl, @Nullable Location location) {
        return Observable.fromCallable(new Callable(location, responseImpl) {
            private final /* synthetic */ Location f$1;
            private final /* synthetic */ ResponseImpl f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object call() {
                return ExploreServiceImpl.lambda$getRestaurantsNearby$3(ExploreServiceImpl.this, this.f$1, this.f$2);
            }
        });
    }

    public static /* synthetic */ Boolean lambda$getRestaurantsNearby$3(@Nullable ExploreServiceImpl exploreServiceImpl, Location location, ResponseImpl responseImpl) throws Exception {
        if (location == null) {
            responseImpl.setNearbyVenues(new ArrayList());
            return Boolean.valueOf(true);
        }
        try {
            responseImpl.setNearbyVenues(((VenueService) exploreServiceImpl.venueService.get()).getVenuesForLocationAndRadius(new VenuesRequestData(location, "", 2000.0f)));
            return Boolean.valueOf(true);
        } catch (Exception unused) {
            return Boolean.valueOf(false);
        }
    }

    private Observable<Boolean> getChallenges(ResponseImpl responseImpl) {
        return Observable.fromCallable(new Callable(responseImpl) {
            private final /* synthetic */ ResponseImpl f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return ExploreServiceImpl.lambda$getChallenges$4(ExploreServiceImpl.this, this.f$1);
            }
        });
    }

    public static /* synthetic */ Boolean lambda$getChallenges$4(ExploreServiceImpl exploreServiceImpl, ResponseImpl responseImpl) throws Exception {
        try {
            responseImpl.setChallenges(((ChallengesService) exploreServiceImpl.challengesService.get()).getChallenges(Challenges.USER_STATUS_ELIGIBLE));
            return Boolean.valueOf(true);
        } catch (Exception unused) {
            return Boolean.valueOf(false);
        }
    }

    private Observable<Boolean> getBlog(ResponseImpl responseImpl) {
        return Observable.fromCallable(new Callable(responseImpl) {
            private final /* synthetic */ ResponseImpl f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return ExploreServiceImpl.lambda$getBlog$5(ExploreServiceImpl.this, this.f$1);
            }
        });
    }

    public static /* synthetic */ Boolean lambda$getBlog$5(ExploreServiceImpl exploreServiceImpl, ResponseImpl responseImpl) throws Exception {
        try {
            responseImpl.setBlogPost((BlogPost) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) exploreServiceImpl.api.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(Uri.BLOG_POSTS, URL_KEY_LIMIT, String.valueOf(1), URL_KEY_ORDER, "created_at")).getItems().get(0));
            return Boolean.valueOf(true);
        } catch (Exception unused) {
            return Boolean.valueOf(false);
        }
    }
}
