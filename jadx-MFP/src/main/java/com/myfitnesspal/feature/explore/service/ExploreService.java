package com.myfitnesspal.feature.explore.service;

import android.location.Location;
import com.myfitnesspal.feature.challenges.model.ChallengeSummary;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.shared.model.Size;
import java.util.List;

public interface ExploreService {

    public interface BlogPost {
        String getDate();

        Size getImageSize();

        String getImageUrl();

        String getTitle();

        String getUrl();
    }

    public interface Response {
        BlogPost getBlogPost();

        List<Venue> getNearbyVenues();

        List<ChallengeSummary> getNewChallenges();
    }

    Response getData(Location location) throws Throwable;
}
