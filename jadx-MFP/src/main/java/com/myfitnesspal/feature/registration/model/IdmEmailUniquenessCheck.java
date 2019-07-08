package com.myfitnesspal.feature.registration.model;

import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import com.uacf.identity.sdk.model.UacfUser;
import io.uacf.core.app.UacfUserAccountDomain;
import java.util.Collection;
import java.util.List;

public class IdmEmailUniquenessCheck {
    private final boolean mfpEmailTaken;
    private final boolean uacfEmailTaken;
    private final List<UacfUser> users;

    public IdmEmailUniquenessCheck(List<UacfUser> list, boolean z, boolean z2) {
        this.users = list;
        this.mfpEmailTaken = z;
        this.uacfEmailTaken = z2;
    }

    public boolean isMfpEmailTaken() {
        return this.mfpEmailTaken;
    }

    public boolean isUacfEmailTaken() {
        return this.uacfEmailTaken;
    }

    public String getUacfUserId() {
        if (CollectionUtils.notEmpty((Collection<?>) this.users)) {
            for (UacfUser uacfUser : this.users) {
                if (uacfUser.getDomain() == UacfUserAccountDomain.UACF && Strings.notEmpty(uacfUser.getUserId())) {
                    return uacfUser.getUserId();
                }
            }
        }
        return null;
    }

    public List<UacfUser> getUsers() {
        return this.users;
    }
}
