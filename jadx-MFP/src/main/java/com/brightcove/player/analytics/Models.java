package com.brightcove.player.analytics;

import io.requery.meta.EntityModel;
import io.requery.meta.EntityModelBuilder;

public class Models {
    public static final EntityModel DEFAULT = new EntityModelBuilder("default").addType(AnalyticsEvent.$TYPE).build();

    private Models() {
    }
}
