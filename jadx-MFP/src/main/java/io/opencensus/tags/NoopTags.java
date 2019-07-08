package io.opencensus.tags;

import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import io.opencensus.internal.Utils;
import io.opencensus.tags.propagation.TagContextBinarySerializer;
import io.opencensus.tags.propagation.TagPropagationComponent;
import java.util.Collections;
import java.util.Iterator;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

final class NoopTags {

    @Immutable
    private static final class NoopTagContext extends TagContext {
        static final TagContext INSTANCE = new NoopTagContext();

        private NoopTagContext() {
        }

        /* access modifiers changed from: protected */
        public Iterator<Tag> getIterator() {
            return Collections.emptySet().iterator();
        }
    }

    @Immutable
    private static final class NoopTagContextBinarySerializer extends TagContextBinarySerializer {
        static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
        static final TagContextBinarySerializer INSTANCE = new NoopTagContextBinarySerializer();

        private NoopTagContextBinarySerializer() {
        }

        public byte[] toByteArray(TagContext tagContext) {
            Utils.checkNotNull(tagContext, "tags");
            return EMPTY_BYTE_ARRAY;
        }

        public TagContext fromByteArray(byte[] bArr) {
            Utils.checkNotNull(bArr, "bytes");
            return NoopTags.getNoopTagContext();
        }
    }

    @Immutable
    private static final class NoopTagContextBuilder extends TagContextBuilder {
        static final TagContextBuilder INSTANCE = new NoopTagContextBuilder();

        private NoopTagContextBuilder() {
        }

        public TagContextBuilder put(TagKey tagKey, TagValue tagValue) {
            Utils.checkNotNull(tagKey, IpcUtil.KEY_CODE);
            Utils.checkNotNull(tagValue, "value");
            return this;
        }

        public TagContext build() {
            return NoopTags.getNoopTagContext();
        }
    }

    @Immutable
    private static final class NoopTagPropagationComponent extends TagPropagationComponent {
        static final TagPropagationComponent INSTANCE = new NoopTagPropagationComponent();

        private NoopTagPropagationComponent() {
        }

        public TagContextBinarySerializer getBinarySerializer() {
            return NoopTags.getNoopTagContextBinarySerializer();
        }
    }

    @Immutable
    private static final class NoopTagger extends Tagger {
        static final Tagger INSTANCE = new NoopTagger();

        private NoopTagger() {
        }

        public TagContext empty() {
            return NoopTags.getNoopTagContext();
        }

        public TagContext getCurrentTagContext() {
            return NoopTags.getNoopTagContext();
        }

        public TagContextBuilder toBuilder(TagContext tagContext) {
            Utils.checkNotNull(tagContext, "tags");
            return NoopTags.getNoopTagContextBuilder();
        }
    }

    @ThreadSafe
    private static final class NoopTagsComponent extends TagsComponent {
        private NoopTagsComponent() {
        }

        public Tagger getTagger() {
            return NoopTags.getNoopTagger();
        }

        public TagPropagationComponent getTagPropagationComponent() {
            return NoopTags.getNoopTagPropagationComponent();
        }
    }

    private NoopTags() {
    }

    static TagsComponent newNoopTagsComponent() {
        return new NoopTagsComponent();
    }

    static Tagger getNoopTagger() {
        return NoopTagger.INSTANCE;
    }

    static TagContextBuilder getNoopTagContextBuilder() {
        return NoopTagContextBuilder.INSTANCE;
    }

    static TagContext getNoopTagContext() {
        return NoopTagContext.INSTANCE;
    }

    static TagPropagationComponent getNoopTagPropagationComponent() {
        return NoopTagPropagationComponent.INSTANCE;
    }

    static TagContextBinarySerializer getNoopTagContextBinarySerializer() {
        return NoopTagContextBinarySerializer.INSTANCE;
    }
}
