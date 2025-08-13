package com.example.MSA_test.common.util;
import org.springframework.beans.BeanUtils;
public class BeanCopyUtils {
    public static <T> T toDto(Object src, Class<T> target) {
        T dto = BeanUtils.instantiateClass(target);
        BeanUtils.copyProperties(src, dto);
        return dto;
    }
    public static <T> T toEntity(Object src, Class<T> target) {
        return toDto(src, target);
    }
}