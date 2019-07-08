package com.twitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

public class Extractor {
    protected boolean extractURLWithoutProtocol = true;

    public static class Entity {
        protected String displayURL;
        protected int end;
        protected String expandedURL;
        protected final String listSlug;
        protected int start;
        protected final Type type;
        protected final String value;

        public enum Type {
            URL,
            HASHTAG,
            MENTION,
            CASHTAG
        }

        public Entity(int i, int i2, String str, String str2, Type type2) {
            this.displayURL = null;
            this.expandedURL = null;
            this.start = i;
            this.end = i2;
            this.value = str;
            this.listSlug = str2;
            this.type = type2;
        }

        public Entity(int i, int i2, String str, Type type2) {
            this(i, i2, str, null, type2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Entity)) {
                return false;
            }
            Entity entity = (Entity) obj;
            return this.type.equals(entity.type) && this.start == entity.start && this.end == entity.end && this.value.equals(entity.value);
        }

        public int hashCode() {
            return this.type.hashCode() + this.value.hashCode() + this.start + this.end;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.value);
            sb.append("(");
            sb.append(this.type);
            sb.append(") [");
            sb.append(this.start);
            sb.append(",");
            sb.append(this.end);
            sb.append("]");
            return sb.toString();
        }
    }

    public List<Entity> extractURLsWithIndices(String str) {
        if (!(str == null || str.length() == 0)) {
            if (str.indexOf(this.extractURLWithoutProtocol ? 46 : 58) != -1) {
                ArrayList arrayList = new ArrayList();
                Matcher matcher = Regex.VALID_URL.matcher(str);
                while (matcher.find()) {
                    if (matcher.group(4) != null || (this.extractURLWithoutProtocol && !Regex.INVALID_URL_WITHOUT_PROTOCOL_MATCH_BEGIN.matcher(matcher.group(2)).matches())) {
                        String group = matcher.group(3);
                        int start = matcher.start(3);
                        int end = matcher.end(3);
                        Matcher matcher2 = Regex.VALID_TCO_URL.matcher(group);
                        if (matcher2.find()) {
                            group = matcher2.group();
                            end = group.length() + start;
                        }
                        arrayList.add(new Entity(start, end, group, Type.URL));
                    }
                }
                return arrayList;
            }
        }
        return Collections.emptyList();
    }

    public void setExtractURLWithoutProtocol(boolean z) {
        this.extractURLWithoutProtocol = z;
    }
}
