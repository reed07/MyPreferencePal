package com.myfitnesspal.feature.addfriends.service;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Data;
import android.support.annotation.WorkerThread;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.friends.ui.activity.InviteFriendActivity;
import com.myfitnesspal.shared.model.v1.EmailFriend;
import com.myfitnesspal.shared.model.v15.AssociatedMfpFriend;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import com.myfitnesspal.shared.service.syncv1.packets.request.ApiRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.EmailFriendCheckRequestPacket;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ContactsOnMfpService extends FriendOnMfpService<EmailFriend> {
    /* access modifiers changed from: protected */
    public int getResponsePacketType() {
        return PacketTypes.CheckFriendsByEmailResponse;
    }

    @Inject
    ContactsOnMfpService(FriendOnMfpConstructorArgs friendOnMfpConstructorArgs) {
        super(friendOnMfpConstructorArgs);
    }

    /* access modifiers changed from: protected */
    public ApiRequestPacket supplyPacketForRequest(List<EmailFriend> list) {
        return new EmailFriendCheckRequestPacket(list);
    }

    public void sendExternalInvites(List<EmailFriend> list, String str, InviteSucceeded inviteSucceeded) {
        this.navigationHelper.withIntent(InviteFriendActivity.newStartIntent(this.context, str, this.context.getString(R.string.addfriends_invite_message))).startActivity(18);
    }

    /* access modifiers changed from: protected */
    public boolean matches(AssociatedMfpFriend associatedMfpFriend, EmailFriend emailFriend) {
        return emailFriend.hasEmailAddress(associatedMfpFriend.getEmailOrId());
    }

    @WorkerThread
    public List<EmailFriend> getFriends() {
        Uri uri = Data.CONTENT_URI;
        ArrayList arrayList = new ArrayList();
        arrayList.add("contact_id");
        arrayList.add("data1");
        arrayList.add("display_name");
        arrayList.add("photo_thumb_uri");
        String format = String.format("%s %s, %s", new Object[]{"display_name", "COLLATE LOCALIZED ASC", "raw_contact_id"});
        Cursor query = this.context.getContentResolver().query(uri, (String[]) arrayList.toArray(new String[arrayList.size()]), "mimetype='vnd.android.cursor.item/email_v2'", null, format);
        ArrayList arrayList2 = new ArrayList();
        if (query != null) {
            EmailFriend emailFriend = null;
            String str = null;
            while (query.moveToNext()) {
                try {
                    String string = query.getString(query.getColumnIndex("contact_id"));
                    String string2 = query.getString(query.getColumnIndex("photo_thumb_uri"));
                    String string3 = query.getString(query.getColumnIndex("data1"));
                    String string4 = query.getString(query.getColumnIndex("display_name"));
                    if (emailFriend == null || !Strings.equals(string, str)) {
                        emailFriend = new EmailFriend(string, string2, string4);
                        arrayList2.add(emailFriend);
                        str = string;
                    }
                    emailFriend.addEmailAddress(string3);
                } finally {
                    query.close();
                }
            }
        }
        return arrayList2;
    }
}
