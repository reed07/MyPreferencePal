package com.myfitnesspal.shared.model.mapper;

import com.myfitnesspal.shared.model.mapper.BinaryMapper;
import com.uacf.core.mapping.Mapper2;

public interface BinaryMapper<TMapper extends BinaryMapper> extends Mapper2<TMapper, byte[]> {
}
