package com.mopub.nativeads;

import android.support.annotation.NonNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ViewBinder {
    final int callToActionId;
    @NonNull
    final Map<String, Integer> extras;
    final int iconImageId;
    final int layoutId;
    final int mainImageId;
    final int privacyInformationIconImageId;
    final int textId;
    final int titleId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public int callToActionId;
        /* access modifiers changed from: private */
        @NonNull
        public Map<String, Integer> extras = Collections.emptyMap();
        /* access modifiers changed from: private */
        public int iconImageId;
        /* access modifiers changed from: private */
        public final int layoutId;
        /* access modifiers changed from: private */
        public int mainImageId;
        /* access modifiers changed from: private */
        public int privacyInformationIconImageId;
        /* access modifiers changed from: private */
        public int textId;
        /* access modifiers changed from: private */
        public int titleId;

        public Builder(int i) {
            this.layoutId = i;
            this.extras = new HashMap();
        }

        @NonNull
        public final Builder titleId(int i) {
            this.titleId = i;
            return this;
        }

        @NonNull
        public final Builder textId(int i) {
            this.textId = i;
            return this;
        }

        @NonNull
        public final Builder callToActionId(int i) {
            this.callToActionId = i;
            return this;
        }

        @NonNull
        public final Builder mainImageId(int i) {
            this.mainImageId = i;
            return this;
        }

        @NonNull
        public final Builder iconImageId(int i) {
            this.iconImageId = i;
            return this;
        }

        @NonNull
        public final Builder privacyInformationIconImageId(int i) {
            this.privacyInformationIconImageId = i;
            return this;
        }

        @NonNull
        public final Builder addExtras(Map<String, Integer> map) {
            this.extras = new HashMap(map);
            return this;
        }

        @NonNull
        public final Builder addExtra(String str, int i) {
            this.extras.put(str, Integer.valueOf(i));
            return this;
        }

        @NonNull
        public final ViewBinder build() {
            return new ViewBinder(this);
        }
    }

    private ViewBinder(@NonNull Builder builder) {
        this.layoutId = builder.layoutId;
        this.titleId = builder.titleId;
        this.textId = builder.textId;
        this.callToActionId = builder.callToActionId;
        this.mainImageId = builder.mainImageId;
        this.iconImageId = builder.iconImageId;
        this.privacyInformationIconImageId = builder.privacyInformationIconImageId;
        this.extras = builder.extras;
    }
}