package com.myfitnesspal.shared.util;

import com.myfitnesspal.shared.constants.SyncConstants;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.UserV1DBAdapter;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.uacf.core.util.Strings;

public final class UserV1Utils {
    public static UserV1 loadUserOnCurrentThread(String str, DbConnectionManager dbConnectionManager) {
        if (!Strings.notEmpty(str) || Strings.equals(str, SyncConstants.LOCAL_USERNAME)) {
            return null;
        }
        UserV1 user = getUser(dbConnectionManager, str, false);
        if (user == null) {
            user = getUser(dbConnectionManager, str, true);
        }
        return user;
    }

    private static UserV1 getUser(DbConnectionManager dbConnectionManager, String str, boolean z) {
        if (!Strings.notEmpty(str)) {
            return null;
        }
        UserV1DBAdapter usersDbAdapter = dbConnectionManager.usersDbAdapter();
        if (!z) {
            return usersDbAdapter.fetchUserWithUsername(str);
        }
        long fetchUserIdForGivenEmailAddress = dbConnectionManager.userPropertiesDbAdapter().fetchUserIdForGivenEmailAddress(str);
        if (fetchUserIdForGivenEmailAddress > 0) {
            return usersDbAdapter.fetchUserWithUserLocalId(fetchUserIdForGivenEmailAddress);
        }
        return null;
    }
}
