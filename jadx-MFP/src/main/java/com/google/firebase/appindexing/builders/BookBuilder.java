package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;

public class BookBuilder extends IndexableBuilder<BookBuilder> {
    BookBuilder() {
        super("Book");
    }

    public BookBuilder setAuthor(@NonNull PersonBuilder... personBuilderArr) {
        return (BookBuilder) put("author", (S[]) personBuilderArr);
    }
}
