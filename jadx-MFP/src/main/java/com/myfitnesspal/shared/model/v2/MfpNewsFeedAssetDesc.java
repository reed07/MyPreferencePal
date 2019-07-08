package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;

public class MfpNewsFeedAssetDesc {
    private boolean hasSizeBeenParsed;
    private int height;
    @Expose
    private boolean roundAsset;
    @Expose
    private String size;
    @Expose
    private String url;
    private int width;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String str) {
        this.size = str;
    }

    public void parseSizeToWidthHeight() {
        int i = 0;
        if (Strings.notEmpty(this.size)) {
            String[] split = this.size.split(AvidJSONUtil.KEY_X);
            this.width = NumberUtils.tryParseInt(split[0]);
            if (split.length > 1) {
                i = NumberUtils.tryParseInt(split[1]);
            }
            this.height = i;
        } else {
            this.height = 0;
            this.width = 0;
        }
        this.hasSizeBeenParsed = true;
    }

    public int getWidth() {
        if (!this.hasSizeBeenParsed) {
            parseSizeToWidthHeight();
        }
        return this.width;
    }

    public int getHeight() {
        if (!this.hasSizeBeenParsed) {
            parseSizeToWidthHeight();
        }
        return this.height;
    }

    public boolean isRoundAsset() {
        return this.roundAsset;
    }

    public void setRoundAsset(boolean z) {
        this.roundAsset = z;
    }
}
