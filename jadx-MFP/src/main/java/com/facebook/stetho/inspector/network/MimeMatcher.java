package com.facebook.stetho.inspector.network;

import android.annotation.SuppressLint;
import com.brightcove.player.event.EventType;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class MimeMatcher<T> {
    private final ArrayList<MimeMatcherRule> mRuleMap = new ArrayList<>();

    @SuppressLint({"BadMethodUse-java.lang.String.length"})
    private class MimeMatcherRule {
        private final boolean mHasWildcard;
        private final String mMatchPrefix;
        private final T mResultIfMatched;

        public MimeMatcherRule(String str, T t) {
            if (str.endsWith(EventType.ANY)) {
                this.mHasWildcard = true;
                this.mMatchPrefix = str.substring(0, str.length() - 1);
            } else {
                this.mHasWildcard = false;
                this.mMatchPrefix = str;
            }
            if (!this.mMatchPrefix.contains(EventType.ANY)) {
                this.mResultIfMatched = t;
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Multiple wildcards present in rule expression ");
            sb.append(str);
            throw new IllegalArgumentException(sb.toString());
        }

        public boolean match(String str) {
            boolean z = false;
            if (!str.startsWith(this.mMatchPrefix)) {
                return false;
            }
            if (this.mHasWildcard || str.length() == this.mMatchPrefix.length()) {
                z = true;
            }
            return z;
        }

        public T getResultIfMatched() {
            return this.mResultIfMatched;
        }
    }

    public void addRule(String str, T t) {
        this.mRuleMap.add(new MimeMatcherRule(str, t));
    }

    public void clear() {
        this.mRuleMap.clear();
    }

    @Nullable
    public T match(String str) {
        int size = this.mRuleMap.size();
        for (int i = 0; i < size; i++) {
            MimeMatcherRule mimeMatcherRule = (MimeMatcherRule) this.mRuleMap.get(i);
            if (mimeMatcherRule.match(str)) {
                return mimeMatcherRule.getResultIfMatched();
            }
        }
        return null;
    }
}
