package com.myfitnesspal.shared.service.facebook;

import android.app.Activity;
import com.facebook.FacebookRequestError;
import com.myfitnesspal.shared.model.v1.FacebookFriend;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import java.util.List;

public interface FacebookService {
    void changePasswordAndDisconnect(String str, Function0 function0, Function2<Integer, String> function2);

    void connect(Activity activity, Function1<String> function1, Function2<Integer, String> function2);

    void connectEmailCompulsory(Activity activity, Function1<String> function1, Function2<Integer, String> function2);

    void disconnect(Function0 function0, Function2<Integer, String> function2);

    void getUserFriendsList(Function1<List<FacebookFriend>> function1, Function1<FacebookRequestError> function12);

    boolean requiresReconnect();
}
