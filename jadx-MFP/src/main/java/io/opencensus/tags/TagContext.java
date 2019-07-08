package io.opencensus.tags;

import java.util.HashMap;
import java.util.Iterator;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class TagContext {
    /* access modifiers changed from: protected */
    public abstract Iterator<Tag> getIterator();

    public String toString() {
        return "TagContext";
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof TagContext)) {
            return false;
        }
        TagContext tagContext = (TagContext) obj;
        Iterator iterator = getIterator();
        Iterator iterator2 = tagContext.getIterator();
        HashMap hashMap = new HashMap();
        while (iterator != null && iterator.hasNext()) {
            Tag tag = (Tag) iterator.next();
            if (hashMap.containsKey(tag)) {
                hashMap.put(tag, Integer.valueOf(((Integer) hashMap.get(tag)).intValue() + 1));
            } else {
                hashMap.put(tag, Integer.valueOf(1));
            }
        }
        while (iterator2 != null && iterator2.hasNext()) {
            Tag tag2 = (Tag) iterator2.next();
            if (!hashMap.containsKey(tag2)) {
                return false;
            }
            int intValue = ((Integer) hashMap.get(tag2)).intValue();
            if (intValue > 1) {
                hashMap.put(tag2, Integer.valueOf(intValue - 1));
            } else {
                hashMap.remove(tag2);
            }
        }
        return hashMap.isEmpty();
    }

    public final int hashCode() {
        Iterator iterator = getIterator();
        int i = 0;
        if (iterator == null) {
            return 0;
        }
        while (iterator.hasNext()) {
            Tag tag = (Tag) iterator.next();
            if (tag != null) {
                i += tag.hashCode();
            }
        }
        return i;
    }
}
