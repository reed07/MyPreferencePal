package com.myfitnesspal.shared.service.syncv1.packets;

import com.myfitnesspal.shared.service.syncv1.packets.response.ApiResponsePacket;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public final class PacketUtils {
    public static <T extends ApiResponsePacket> T findFirstPacketOfType(List<ApiResponsePacket> list, final int i) {
        return (ApiResponsePacket) Enumerable.firstOrDefault(list, new ReturningFunction1<Boolean, ApiResponsePacket>() {
            public Boolean execute(ApiResponsePacket apiResponsePacket) {
                return Boolean.valueOf(apiResponsePacket.getPacketType() == i);
            }
        });
    }

    public static <T extends ApiResponsePacket> List<T> findAllPacketsOfType(List<ApiResponsePacket> list, final int i) {
        return (List) Enumerable.where((Collection<T>) list, (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, ApiResponsePacket>() {
            public Boolean execute(ApiResponsePacket apiResponsePacket) {
                return Boolean.valueOf(apiResponsePacket.getPacketType() == i);
            }
        });
    }

    public static <T extends ApiResponsePacket> List<T> findAllPacketsOfTypes(List<ApiResponsePacket> list, List<Integer> list2) {
        final HashSet hashSet = new HashSet(list2);
        return (List) Enumerable.where((Collection<T>) list, (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, ApiResponsePacket>() {
            public Boolean execute(ApiResponsePacket apiResponsePacket) {
                return Boolean.valueOf(hashSet.contains(Integer.valueOf(apiResponsePacket.getPacketType())));
            }
        });
    }

    public static <T> T getPayloadForFirstPacketOfType(List<ApiResponsePacket> list, int i) {
        if (i == 154) {
            Ln.d("VALIDATION: getPayloadForFirstPacketOfType packet list has %s elements", Integer.valueOf(CollectionUtils.size((Collection<?>) list)));
        }
        ApiResponsePacket findFirstPacketOfType = findFirstPacketOfType(list, i);
        if (i == 154) {
            Ln.d("VALIDATION: getPayloadForFirstPacketOfType found packet %s", findFirstPacketOfType);
        }
        T payload = findFirstPacketOfType != null ? findFirstPacketOfType.getPayload() : null;
        if (i == 154) {
            Ln.d("VALIDATION: getPayloadForFirstPacketOfType found payload %s", payload);
        }
        return payload;
    }

    public static <T> List<T> getPayloadForAllPacketsOfType(List<ApiResponsePacket> list, int i) {
        return Enumerable.select((Collection<T>) findAllPacketsOfType(list, i), false, (ReturningFunction1<U, T>) new ReturningFunction1<T, ApiResponsePacket<T>>() {
            public T execute(ApiResponsePacket<T> apiResponsePacket) {
                return apiResponsePacket.getPayload();
            }
        });
    }

    public static <T> List<T> getPayloadForAllPacketsOfTypes(List<ApiResponsePacket> list, List<Integer> list2) {
        return Enumerable.select((Collection<T>) findAllPacketsOfTypes(list, list2), false, (ReturningFunction1<U, T>) new ReturningFunction1<T, ApiResponsePacket<T>>() {
            public T execute(ApiResponsePacket<T> apiResponsePacket) {
                return apiResponsePacket.getPayload();
            }
        });
    }
}
