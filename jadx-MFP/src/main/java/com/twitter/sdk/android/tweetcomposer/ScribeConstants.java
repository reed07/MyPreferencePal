package com.twitter.sdk.android.tweetcomposer;

import com.twitter.sdk.android.core.internal.scribe.EventNamespace.Builder;

final class ScribeConstants {
    static final Builder ComposerEventBuilder = new Builder().setClient("tfw").setPage("android").setSection("composer");

    private ScribeConstants() {
    }
}
