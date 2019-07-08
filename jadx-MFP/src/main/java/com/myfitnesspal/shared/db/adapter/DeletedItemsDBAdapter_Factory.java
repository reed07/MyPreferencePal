package com.myfitnesspal.shared.db.adapter;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DeletedItemsDBAdapter_Factory implements Factory<DeletedItemsDBAdapter> {
    private final Provider<Context> contextProvider;

    public DeletedItemsDBAdapter_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public DeletedItemsDBAdapter get() {
        return provideInstance(this.contextProvider);
    }

    public static DeletedItemsDBAdapter provideInstance(Provider<Context> provider) {
        return new DeletedItemsDBAdapter((Context) provider.get());
    }

    public static DeletedItemsDBAdapter_Factory create(Provider<Context> provider) {
        return new DeletedItemsDBAdapter_Factory(provider);
    }

    public static DeletedItemsDBAdapter newDeletedItemsDBAdapter(Context context) {
        return new DeletedItemsDBAdapter(context);
    }
}
