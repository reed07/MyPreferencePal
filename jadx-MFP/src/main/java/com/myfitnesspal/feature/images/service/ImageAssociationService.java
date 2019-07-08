package com.myfitnesspal.feature.images.service;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpImageAssociation;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import java.util.List;

public interface ImageAssociationService extends SyncItemHandler<MfpImageAssociation> {

    public interface ResourceType {
        public static final String FOOD_ENTRY = "food_entry";
        public static final String MEASUREMENT = "measurement";
    }

    boolean associate(String str, String str2, long j, String str3);

    boolean associateWithExistingImage(long j, String str, String str2, long j2, String str3);

    int copyAssociationsToResource(String str, long j, String str2, String str3, long j2, String str4);

    List<MfpImageAssociation> findAssociationsForImage(long j, String str);

    List<MfpImageAssociation> findAssociationsForResource(long j, String str);

    List<MfpImageAssociation> getPendingAssociations();

    List<MfpImageAssociation> getPendingDisassociations();

    boolean markForRemoteDisassociation(long j, String str, String str2);

    boolean markForRemoteDisassociation(MfpImageAssociation mfpImageAssociation);

    MfpImageAssociation remoteAssociate(String str, long j, String str2, String str3, String str4) throws ApiException;

    void remoteDisassociate(MfpImageAssociation mfpImageAssociation) throws ApiException;

    int removeLocalDisassociations();

    boolean update(long j, MfpImageAssociation mfpImageAssociation);

    boolean updateImageUidForAssociationsWithImageLocalId(long j, String str);

    boolean updateResourceIds(long j, long j2, String str);

    boolean updateSyncFlag(String str, int i);
}
