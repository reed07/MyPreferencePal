package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

class VastResource implements Serializable {
    private static final List<String> VALID_APPLICATION_TYPES = Arrays.asList(new String[]{"application/x-javascript"});
    private static final List<String> VALID_IMAGE_TYPES = Arrays.asList(new String[]{"image/jpeg", "image/png", "image/bmp", "image/gif"});
    private static final long serialVersionUID = 0;
    @NonNull
    private CreativeType mCreativeType;
    private int mHeight;
    @NonNull
    private String mResource;
    @NonNull
    private Type mType;
    private int mWidth;

    enum CreativeType {
        NONE,
        IMAGE,
        JAVASCRIPT
    }

    enum Type {
        STATIC_RESOURCE,
        HTML_RESOURCE,
        IFRAME_RESOURCE
    }

    @Nullable
    static VastResource fromVastResourceXmlManager(@NonNull VastResourceXmlManager vastResourceXmlManager, @NonNull Type type, int i, int i2) {
        CreativeType creativeType;
        Preconditions.checkNotNull(vastResourceXmlManager);
        Preconditions.checkNotNull(type);
        String iFrameResource = vastResourceXmlManager.getIFrameResource();
        String hTMLResource = vastResourceXmlManager.getHTMLResource();
        String staticResource = vastResourceXmlManager.getStaticResource();
        String staticResourceType = vastResourceXmlManager.getStaticResourceType();
        if (type != Type.STATIC_RESOURCE || staticResource == null || staticResourceType == null || (!VALID_IMAGE_TYPES.contains(staticResourceType) && !VALID_APPLICATION_TYPES.contains(staticResourceType))) {
            if (type == Type.HTML_RESOURCE && hTMLResource != null) {
                creativeType = CreativeType.NONE;
                staticResource = hTMLResource;
            } else if (type != Type.IFRAME_RESOURCE || iFrameResource == null) {
                return null;
            } else {
                creativeType = CreativeType.NONE;
                staticResource = iFrameResource;
            }
        } else if (VALID_IMAGE_TYPES.contains(staticResourceType)) {
            creativeType = CreativeType.IMAGE;
        } else {
            creativeType = CreativeType.JAVASCRIPT;
        }
        VastResource vastResource = new VastResource(staticResource, type, creativeType, i, i2);
        return vastResource;
    }

    VastResource(@NonNull String str, @NonNull Type type, @NonNull CreativeType creativeType, int i, int i2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(creativeType);
        this.mResource = str;
        this.mType = type;
        this.mCreativeType = creativeType;
        this.mWidth = i;
        this.mHeight = i2;
    }

    @NonNull
    public String getResource() {
        return this.mResource;
    }

    @NonNull
    public Type getType() {
        return this.mType;
    }

    @NonNull
    public CreativeType getCreativeType() {
        return this.mCreativeType;
    }

    public void initializeWebView(@NonNull VastWebView vastWebView) {
        Preconditions.checkNotNull(vastWebView);
        if (this.mType == Type.IFRAME_RESOURCE) {
            StringBuilder sb = new StringBuilder();
            sb.append("<iframe frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" style=\"border: 0px; margin: 0px;\" width=\"");
            sb.append(this.mWidth);
            sb.append("\" height=\"");
            sb.append(this.mHeight);
            sb.append("\" src=\"");
            sb.append(this.mResource);
            sb.append("\"></iframe>");
            vastWebView.loadData(sb.toString());
        } else if (this.mType == Type.HTML_RESOURCE) {
            vastWebView.loadData(this.mResource);
        } else if (this.mType != Type.STATIC_RESOURCE) {
        } else {
            if (this.mCreativeType == CreativeType.IMAGE) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("<html><head></head><body style=\"margin:0;padding:0\"><img src=\"");
                sb2.append(this.mResource);
                sb2.append("\" width=\"100%\" style=\"max-width:100%;max-height:100%;\" /></body></html>");
                vastWebView.loadData(sb2.toString());
            } else if (this.mCreativeType == CreativeType.JAVASCRIPT) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("<script src=\"");
                sb3.append(this.mResource);
                sb3.append("\"></script>");
                vastWebView.loadData(sb3.toString());
            }
        }
    }

    @Nullable
    public String getCorrectClickThroughUrl(@Nullable String str, @Nullable String str2) {
        switch (this.mType) {
            case STATIC_RESOURCE:
                if (CreativeType.IMAGE == this.mCreativeType) {
                    return str;
                }
                if (CreativeType.JAVASCRIPT == this.mCreativeType) {
                    return str2;
                }
                return null;
            case HTML_RESOURCE:
            case IFRAME_RESOURCE:
                return str2;
            default:
                return null;
        }
    }
}
