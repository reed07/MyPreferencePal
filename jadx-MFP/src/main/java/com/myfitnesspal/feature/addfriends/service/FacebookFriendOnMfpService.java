package com.myfitnesspal.feature.addfriends.service;

import android.app.Activity;
import com.facebook.FacebookRequestError;
import com.myfitnesspal.shared.model.v1.FacebookFriend;
import com.myfitnesspal.shared.model.v15.AssociatedMfpFriend;
import com.myfitnesspal.shared.service.facebook.FacebookService;
import com.myfitnesspal.shared.service.syncv1.packets.request.ApiRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.ThirdPartyFriendCheckRequestPacket;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class FacebookFriendOnMfpService extends FriendOnMfpService<FacebookFriend> {
    private final Lazy<FacebookService> facebookService;

    /* access modifiers changed from: protected */
    public int getResponsePacketType() {
        return 136;
    }

    public void sendExternalInvites(List<FacebookFriend> list, String str, InviteSucceeded inviteSucceeded) {
    }

    @Inject
    public FacebookFriendOnMfpService(FacebookFriendOnMfpConstructorArgs facebookFriendOnMfpConstructorArgs) {
        super(facebookFriendOnMfpConstructorArgs);
        this.facebookService = facebookFriendOnMfpConstructorArgs.getFacebookService();
    }

    public void setActivityContext(Activity activity) {
        super.setActivityContext(activity);
    }

    /* access modifiers changed from: protected */
    public List<FacebookFriend> getFriends() {
        ArrayList arrayList = new ArrayList();
        ((FacebookService) this.facebookService.get()).getUserFriendsList(new Function1(arrayList) {
            private final /* synthetic */ List f$0;

            {
                this.f$0 = r1;
            }

            public final void execute(Object obj) {
                FacebookFriendOnMfpService.lambda$getFriends$1(this.f$0, (List) obj);
            }
        }, $$Lambda$FacebookFriendOnMfpService$ZF7SA8A5LGX_uXf_9e60KNsspD4.INSTANCE);
        return arrayList;
    }

    static /* synthetic */ void lambda$getFriends$1(List list, List list2) throws RuntimeException {
        if (CollectionUtils.notEmpty((Collection<?>) list2)) {
            Ln.d("FACEBOOK: got friends: %s", Integer.valueOf(list2.size()));
            Collections.sort(list2, $$Lambda$FacebookFriendOnMfpService$qs5NzTNP1RjVRG3MBWLwk2x0ExA.INSTANCE);
            list.addAll(list2);
        }
    }

    static /* synthetic */ int lambda$null$0(FacebookFriend facebookFriend, FacebookFriend facebookFriend2) {
        if (facebookFriend == facebookFriend2) {
            return 0;
        }
        if (facebookFriend != null && facebookFriend2 == null) {
            return 1;
        }
        if (facebookFriend != null || facebookFriend2 == null) {
            return Strings.toString(facebookFriend.getName()).compareToIgnoreCase(Strings.toString(facebookFriend2.getName()));
        }
        return -1;
    }

    static /* synthetic */ void lambda$getFriends$2(FacebookRequestError facebookRequestError) throws RuntimeException {
        StringBuilder sb = new StringBuilder();
        sb.append("failed to get friends with error ");
        sb.append(facebookRequestError != null ? Strings.toString(facebookRequestError.getErrorMessage()) : "");
        Ln.d(sb.toString(), new Object[0]);
    }

    /* access modifiers changed from: protected */
    public ApiRequestPacket supplyPacketForRequest(List<FacebookFriend> list) {
        return new ThirdPartyFriendCheckRequestPacket(1, list);
    }

    /* access modifiers changed from: protected */
    public boolean matches(AssociatedMfpFriend associatedMfpFriend, FacebookFriend facebookFriend) {
        return Strings.equals(associatedMfpFriend.getEmailOrId(), facebookFriend.getId());
    }
}
