package com.brightcove.player.model;

import com.brightcove.player.captioning.BrightcoveClosedCaption;
import java.util.List;
import java.util.Map;

public class TTMLDocument extends CaptionsDocument {
    private Block body;
    private Map<String, StyledElement> styles;

    public TTMLDocument(Map<String, Region> map, Map<String, StyledElement> map2, Block block, List<BrightcoveClosedCaption> list) {
        if (map == null || map2 == null || block == null || list == null) {
            throw new IllegalArgumentException("must provide collections for regions, styles, body and captions");
        }
        this.regions = map;
        this.styles = map2;
        this.body = block;
        this.captions = list;
    }

    public Map<String, StyledElement> getStyles() {
        return this.styles;
    }

    public Block getBody() {
        return this.body;
    }
}
