package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.db.adapter.UserV1DBAdapter;
import com.myfitnesspal.shared.model.SoftwareUpdateDescription;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.model.v15.SyncPointer;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;

public class SyncSummaryPacket extends ApiResponsePacketImpl {
    String errorMessage;
    int flags;
    private boolean isMoreDataAvailable;
    String optionalMessage;
    long packetCount;
    int resultCode;
    @Inject
    Lazy<Session> session;
    private SoftwareUpdateDescription softwareUpdateDesc;
    List<SyncPointer> syncPointers;
    long userMasterId;
    @Inject
    Lazy<UserV1DBAdapter> userV1DBAdapter;
    String username;

    private static final class Flags {
        public static final int MORE_DATA_AVAILABLE = 1;
        public static final int NEW_VERSION = 2;

        private Flags() {
        }
    }

    public int getPacketType() {
        return 2;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getOptionalMessage() {
        return this.optionalMessage;
    }

    public long getUserMasterId() {
        return this.userMasterId;
    }

    public String getUsername() {
        return this.username;
    }

    public int getFlags() {
        return this.flags;
    }

    private void setFlags(int i) {
        this.flags = i;
        this.isMoreDataAvailable = checkFlag(1);
        if (checkFlag(2)) {
            this.softwareUpdateDesc = new SoftwareUpdateDescription(this.optionalMessage);
        }
    }

    public boolean isMoreDataAvailable() {
        return this.isMoreDataAvailable;
    }

    public boolean isSoftwareUpdateAvailable() {
        return this.softwareUpdateDesc != null;
    }

    public long getPacketCount() {
        return this.packetCount;
    }

    public List<SyncPointer> getSyncPointers() {
        return this.syncPointers;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.resultCode = binaryDecoder.decode2ByteInt();
        this.errorMessage = binaryDecoder.decodeString();
        this.optionalMessage = binaryDecoder.decodeString();
        this.userMasterId = binaryDecoder.decode4ByteInt();
        this.username = binaryDecoder.decodeString();
        setFlags(binaryDecoder.decode2ByteInt());
        int decode2ByteInt = binaryDecoder.decode2ByteInt();
        this.packetCount = binaryDecoder.decode4ByteInt();
        this.syncPointers = binaryDecoder.decodeList(decode2ByteInt, SyncPointer.BINARY_CREATOR);
    }

    public boolean processForSync() {
        if (this.resultCode != 0) {
            return false;
        }
        UserV1 userV1 = ((Session) this.session.get()).getUser().getUserV1();
        if (!userV1.isValid() || !userV1.hasMasterDatabaseId() || this.userMasterId == 0 || userV1.getMasterDatabaseId() == this.userMasterId) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(userV1.getUsername());
        sb.append(" (deleted)");
        userV1.setUsername(sb.toString());
        ((UserV1DBAdapter) this.userV1DBAdapter.get()).updateUsersRowForUser(userV1);
        this.resultCode = 5;
        return false;
    }

    private boolean checkFlag(int i) {
        return (getFlags() & i) == i;
    }
}
