package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;

public final class StickerPackBuilder extends IndexableBuilder<StickerPackBuilder> {
    StickerPackBuilder() {
        super("StickerPack");
    }

    public final StickerPackBuilder setHasSticker(@NonNull StickerBuilder... stickerBuilderArr) {
        return (StickerPackBuilder) put("hasSticker", (S[]) stickerBuilderArr);
    }
}
