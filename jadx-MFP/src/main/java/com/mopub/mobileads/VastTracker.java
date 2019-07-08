package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import java.io.Serializable;

public class VastTracker implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean mCalled;
    @NonNull
    private final String mContent;
    private boolean mIsRepeatable;
    @NonNull
    private final MessageType mMessageType;

    enum MessageType {
        TRACKING_URL,
        QUARTILE_EVENT
    }

    public VastTracker(@NonNull MessageType messageType, @NonNull String str) {
        Preconditions.checkNotNull(messageType);
        Preconditions.checkNotNull(str);
        this.mMessageType = messageType;
        this.mContent = str;
    }

    public VastTracker(@NonNull String str) {
        this(MessageType.TRACKING_URL, str);
    }

    public VastTracker(@NonNull String str, boolean z) {
        this(str);
        this.mIsRepeatable = z;
    }

    @NonNull
    public MessageType getMessageType() {
        return this.mMessageType;
    }

    @NonNull
    public String getContent() {
        return this.mContent;
    }

    public void setTracked() {
        this.mCalled = true;
    }

    public boolean isTracked() {
        return this.mCalled;
    }

    public boolean isRepeatable() {
        return this.mIsRepeatable;
    }
}
