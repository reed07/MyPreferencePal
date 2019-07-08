package com.myfitnesspal.shared.service.syncv1.packets;

import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import com.myfitnesspal.shared.service.syncv1.packets.response.AddExerciseResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.AddFoodResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.AddInboxMessageResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.AddReminderResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.AddStatusCommentResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.AddStatusUpdateResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.AddThirdPartyAccountResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.ApiResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.ApiResponsePacketHeader;
import com.myfitnesspal.shared.service.syncv1.packets.response.AutomaticGoalRecalculationRecommendedResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.CompleteDiaryDayResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.DeleteItemResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.DiaryDaySummaryResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.DissociateThirdPartyResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.EmailUniquenessCheckResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.ExerciseEntryResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.FoodEntryResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.FriendCheckResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.FriendRequestResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.InformationOrActionResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.ItemRetrievalResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.MealIngredientsResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.PendingItemTalliesResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.RetrieveUserSummaryResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.SearchResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.SetDiaryNoteResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.SetFoodNotesResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.SyncSummaryPacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.ThirdPartyAccountInfoResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.UserPropertyUpdateResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.UsernameSuggestionResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.UsernameValidationResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.VerifyThirdPartyResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.WaterEntryResponsePacket;
import dagger.Lazy;

public class PacketFactoryImpl implements PacketFactory {
    private final Lazy<DbConnectionManager> dbConnectionManager;

    public PacketFactoryImpl(Lazy<DbConnectionManager> lazy) {
        this.dbConnectionManager = lazy;
    }

    public ApiResponsePacket createPacket(int i) {
        switch (i) {
            case 0:
                return new ApiResponsePacketHeader();
            case 2:
                return new SyncSummaryPacket();
            case 3:
            case 31:
                return new AddFoodResponsePacket();
            case 4:
                return new AddExerciseResponsePacket();
            case 5:
                return new FoodEntryResponsePacket();
            case 6:
                return new ExerciseEntryResponsePacket();
            case 11:
                return new MealIngredientsResponsePacket();
            case 13:
                return new UserPropertyUpdateResponsePacket();
            case 16:
                return new WaterEntryResponsePacket();
            case 17:
                return new DeleteItemResponsePacket();
            case 19:
                return new SearchResponsePacket(this.dbConnectionManager);
            case 23:
                return new SetDiaryNoteResponsePacket();
            case 26:
                return new AddStatusUpdateResponsePacket();
            case 27:
                return new AddInboxMessageResponsePacket();
            case 28:
                return new AddStatusCommentResponsePacket();
            case 35:
                return new AddReminderResponsePacket();
            case 39:
                return new AddThirdPartyAccountResponsePacket();
            case 44:
                return new SetFoodNotesResponsePacket();
            case 102:
                return new InformationOrActionResponsePacket();
            case 106:
                return new ItemRetrievalResponsePacket();
            case 118:
                return new FriendRequestResponsePacket();
            case 120:
                return new RetrieveUserSummaryResponsePacket();
            case 126:
                return new CompleteDiaryDayResponsePacket();
            case 128:
                return new AutomaticGoalRecalculationRecommendedResponsePacket();
            case 129:
                return new PendingItemTalliesResponsePacket();
            case PacketTypes.DiaryDaySummary /*132*/:
                return new DiaryDaySummaryResponsePacket();
            case 134:
                return new VerifyThirdPartyResponsePacket();
            case 136:
            case PacketTypes.CheckFriendsByEmailResponse /*139*/:
                return new FriendCheckResponsePacket(i);
            case PacketTypes.SuggestUsernameResponse /*147*/:
                return new UsernameSuggestionResponsePacket();
            case PacketTypes.ThirdPartyDissociateResponse /*148*/:
                return new DissociateThirdPartyResponsePacket();
            case 150:
                return new ThirdPartyAccountInfoResponsePacket();
            case PacketTypes.EmailUniquenessCheckResponse /*152*/:
                return new EmailUniquenessCheckResponsePacket();
            case PacketTypes.UsernameValidationResponse /*154*/:
                return new UsernameValidationResponsePacket();
            default:
                return null;
        }
    }
}
