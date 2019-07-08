package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v15.SearchResult;
import com.myfitnesspal.shared.model.v15.SearchResultFactory;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;

public class SearchResponsePacket extends ApiResponsePacketImpl<List<SearchResult>> {
    private final Lazy<DbConnectionManager> dbConnectionManager;

    public int getPacketType() {
        return 19;
    }

    public boolean processForSync() {
        return false;
    }

    public SearchResponsePacket(Lazy<DbConnectionManager> lazy) {
        this.dbConnectionManager = lazy;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        long decode4ByteInt = binaryDecoder.decode4ByteInt();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; ((long) i) < decode4ByteInt; i++) {
            SearchResult createSearchResult = SearchResultFactory.createSearchResult(binaryDecoder.decode2ByteInt(), binaryDecoder, this.dbConnectionManager);
            if (createSearchResult != null) {
                arrayList.add(createSearchResult);
            }
        }
        setPayload(arrayList);
    }
}
