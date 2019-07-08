package com.myfitnesspal.shared.model.mapper;

import com.myfitnesspal.shared.model.mapper.JsonMapper;
import com.uacf.core.mapping.Mapper2;

public interface JsonMapper<TMapper extends JsonMapper> extends Mapper2<TMapper, String> {
}
