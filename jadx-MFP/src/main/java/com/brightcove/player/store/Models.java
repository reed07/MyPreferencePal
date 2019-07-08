package com.brightcove.player.store;

import io.requery.meta.EntityModel;
import io.requery.meta.EntityModelBuilder;

public class Models {
    public static final EntityModel DEFAULT = new EntityModelBuilder("default").addType(DownloadRequest.$TYPE).addType(OfflineVideo.$TYPE).addType(DownloadRequestSet.$TYPE).build();

    private Models() {
    }
}
