package com.myfitnesspal.shared.service.session;

import com.myfitnesspal.shared.model.User;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0007H&J\b\u0010\t\u001a\u00020\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\n"}, d2 = {"Lcom/myfitnesspal/shared/service/session/Session;", "", "user", "Lcom/myfitnesspal/shared/model/User;", "getUser", "()Lcom/myfitnesspal/shared/model/User;", "logout", "", "logoutAndKeepFacebookData", "logoutAndNavigateToLoginActivity", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: Session.kt */
public interface Session {
    @NotNull
    User getUser();

    void logout();

    void logoutAndKeepFacebookData();

    void logoutAndNavigateToLoginActivity();
}
