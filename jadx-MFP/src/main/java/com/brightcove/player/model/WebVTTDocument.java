package com.brightcove.player.model;

import com.brightcove.player.captioning.BrightcoveClosedCaption;
import java.util.List;
import java.util.Map;

public class WebVTTDocument extends CaptionsDocument {
    public WebVTTDocument(List<BrightcoveClosedCaption> list) {
        if (list != null) {
            this.captions = list;
            return;
        }
        throw new IllegalArgumentException("must provide collections for captions");
    }

    public WebVTTDocument(Map<String, Region> map, List<BrightcoveClosedCaption> list) {
        if (map == null || list == null) {
            throw new IllegalArgumentException("must provide collections for regions and captions");
        }
        this.regions = map;
        this.captions = list;
    }
}
