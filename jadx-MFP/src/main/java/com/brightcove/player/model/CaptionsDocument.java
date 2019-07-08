package com.brightcove.player.model;

import com.brightcove.player.captioning.BrightcoveClosedCaption;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CaptionsDocument implements Serializable {
    protected List<BrightcoveClosedCaption> captions;
    protected Map<String, Region> regions;

    public Map<String, Region> getRegions() {
        return this.regions;
    }

    public List<BrightcoveClosedCaption> getCaptions() {
        return this.captions;
    }
}
