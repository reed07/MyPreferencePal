package com.myfitnesspal.shared.service.userdata;

import android.content.Context;
import com.myfitnesspal.shared.db.table.ProfileImagesTable;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UserImageServiceImpl_Factory implements Factory<UserImageServiceImpl> {
    private final Provider<Context> contextProvider;
    private final Provider<ProfileImagesTable> profileImagesTableProvider;
    private final Provider<Session> sessionProvider;

    public UserImageServiceImpl_Factory(Provider<Context> provider, Provider<Session> provider2, Provider<ProfileImagesTable> provider3) {
        this.contextProvider = provider;
        this.sessionProvider = provider2;
        this.profileImagesTableProvider = provider3;
    }

    public UserImageServiceImpl get() {
        return provideInstance(this.contextProvider, this.sessionProvider, this.profileImagesTableProvider);
    }

    public static UserImageServiceImpl provideInstance(Provider<Context> provider, Provider<Session> provider2, Provider<ProfileImagesTable> provider3) {
        return new UserImageServiceImpl((Context) provider.get(), DoubleCheck.lazy(provider2), (ProfileImagesTable) provider3.get());
    }

    public static UserImageServiceImpl_Factory create(Provider<Context> provider, Provider<Session> provider2, Provider<ProfileImagesTable> provider3) {
        return new UserImageServiceImpl_Factory(provider, provider2, provider3);
    }

    public static UserImageServiceImpl newUserImageServiceImpl(Context context, Lazy<Session> lazy, ProfileImagesTable profileImagesTable) {
        return new UserImageServiceImpl(context, lazy, profileImagesTable);
    }
}
