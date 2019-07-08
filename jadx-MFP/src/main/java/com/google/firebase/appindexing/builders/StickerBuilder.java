package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;

public final class StickerBuilder extends IndexableBuilder<StickerBuilder> {
    StickerBuilder() {
        super("Sticker");
    }

    public final StickerBuilder setIsPartOf(@NonNull StickerPackBuilder stickerPackBuilder) {
        return (StickerBuilder) put("isPartOf", (S[]) new StickerPackBuilder[]{stickerPackBuilder});
    }
}
